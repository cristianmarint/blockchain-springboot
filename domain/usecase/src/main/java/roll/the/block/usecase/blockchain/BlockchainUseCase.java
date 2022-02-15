package roll.the.block.usecase.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.block.Block;
import roll.the.block.model.block.gateways.BlockRepository;
import roll.the.block.model.chain.Chain;
import roll.the.block.model.chain.gateways.ChainRepository;
import roll.the.block.model.chain.gateways.ChainRestTemplate;
import roll.the.block.model.node.Node;
import roll.the.block.model.node.gateways.NodeRepository;
import roll.the.block.model.transaction.Transaction;
import roll.the.block.usecase.util.BlockProofOfWorkGenerator;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import static roll.the.block.model.constants.Endpoints.URL_CHAIN_V1;

@Data
@Slf4j
@Service
public class BlockchainUseCase {

    private final ObjectMapper mapper;
    private final List<Transaction> currentTransactions;
    private final BlockRepository blockRepository;
    private final NodeRepository nodeRepository;
    private final ChainRestTemplate restTemplateRepository;
    private final ChainRepository chainRepository;

    @Autowired
    public BlockchainUseCase(
            ObjectMapper mapper,
            BlockRepository blockRepository,
            NodeRepository nodeRepository,
            ChainRestTemplate chainRestTemplate,
            ChainRepository chainRepository
    ) throws JsonProcessingException {
        this.blockRepository = blockRepository;
        this.mapper = mapper;
        this.restTemplateRepository = chainRestTemplate;
        this.chainRepository = chainRepository;
        this.nodeRepository = nodeRepository;
        currentTransactions = new ArrayList<>();

        createBlock(Block.GENESIS_BLOCK_PROOF, Block.GENESIS_BLOCK_PREV_HASH);
    }

    /**
     * Determine if a given blockchain is valid
     *
     * @param chain  A blockchain
     * @param mapper
     * @return True if valid, False if not
     * @throws JsonProcessingException
     */
    public static boolean validChain(List<Block> chain, ObjectMapper mapper) throws JsonProcessingException {

        if (chain == null || chain.isEmpty())
            return false;

        Block lastBlock = chain.get(0);

        for (int currentIndex = 1; currentIndex < chain.size(); currentIndex++) {

            Block currentBlock = chain.get(currentIndex);

            log.debug("lastBlock={}", lastBlock);
            log.debug("currentBlock={}", currentBlock);

            if (!currentBlock.getPreviousHash().equals(lastBlock.hash(mapper))) {
                return false;
            }

            if (!BlockProofOfWorkGenerator.validProof(lastBlock.getProof(), currentBlock.getProof())) {
                return false;
            }

            lastBlock = currentBlock;
        }

        return true;
    }

    public Long addTransaction(String sender, String recipient, BigDecimal amount) {

        Transaction transaction = Transaction.builder().sender(sender).recipient(recipient).amount(amount).build();

        currentTransactions.add(transaction);

        return blockRepository.getLastBlock().getIndex() + 1L;
    }

    public Block createBlock(Long proof, String previusHash) throws JsonProcessingException {

        Block block = Block.builder()
                .index(blockRepository.countBlocks() + 1L)
                .previousHash((previusHash != null) ? previusHash : blockRepository.getLastBlock().hash(mapper)).proof(proof)
                .timestamp(new Date().getTime())
                .transactions(currentTransactions)
                .build();

        blockRepository.addBlock(block);

        return block;
    }

    public boolean validChain() throws JsonProcessingException {
        return validChain(blockRepository.getBlocks(), mapper);
    }

    /**
     * This is our consensus algorithm, it resolves conflicts by replacing our chain with the longest one in the network.
     *
     * @param nodes
     * @return True if our chain was replaced, False if not
     */
    public Boolean resolveConflicts(Set<Node> nodes) throws URISyntaxException, IOException {
        Set<Chain> newChain = null;
        int maxLength = nodes.size();

        for (Node node : nodes) {
            Set<Chain> response = restTemplateRepository.requestChain(node.getAddress().toURI() + URL_CHAIN_V1);

            if (response != null) {
                int length = response.size();
                Set<Chain> chain = response;

                if (length > maxLength) {
                    maxLength = length;
                    newChain = chain;
                }
            }
        }

        if (newChain != null) {
            chainRepository.resetChain(newChain);
            return true;
        }
        return false;
    }

    public void registerNode(Node node) {
        nodeRepository.addNode(node);
    }
}
