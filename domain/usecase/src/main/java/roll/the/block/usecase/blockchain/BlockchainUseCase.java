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

    //    TODO: Crear repository
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
     * @param blocks A valid blocks
     * @param mapper object mapper
     * @return True if valid, False if not
     * @throws JsonProcessingException when validation hash
     */
    public static boolean validateBlocks(List<Block> blocks, ObjectMapper mapper) throws JsonProcessingException {

        if (blocks == null || blocks.isEmpty())
            return false;

        Block lastBlock = blocks.get(0);

        for (int currentIndex = 1; currentIndex < blocks.size(); currentIndex++) {

            Block currentBlock = blocks.get(currentIndex);

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

    /**
     * Creates a new transaction to go into the next mined Block
     *
     * @param sender    Address of the Sender
     * @param recipient Address of the Recipient
     * @param amount    Amount
     * @return The index of the Block that will hold this transaction
     */
    public Long addTransaction(String sender, String recipient, BigDecimal amount) {

        Transaction transaction = Transaction.builder().sender(sender).recipient(recipient).amount(amount).build();

        currentTransactions.add(transaction);

        return blockRepository.getLastBlock().getIndex() + 1L;
    }

    /**
     * Create a new Block in the Blockchain
     *
     * @param proof        The proof given by the Proof of Work algorithm
     * @param previousHash Hash of previous Block
     * @return New Block
     * @throws JsonProcessingException when validation hash
     */
    public Block createBlock(Long proof, String previousHash) throws JsonProcessingException {

        Block block = Block.builder()
                .index(blockRepository.countBlocks() + 1L)
                .previousHash((previousHash != null) ? previousHash : blockRepository.getLastBlock().hash(mapper)).proof(proof)
                .timestamp(new Date().getTime())
                .transactions(currentTransactions)
                .build();

        blockRepository.addBlock(block);

        return block;
    }

    /**
     * Determine if a given blockchain is valid
     *
     * @return True if valid, False if not
     * @throws JsonProcessingException when validation hash
     */
    @SuppressWarnings("unused")
    public boolean validateBlocks() throws JsonProcessingException {
        return validateBlocks(blockRepository.getBlocks(), mapper);
    }

    /**
     * This is our consensus algorithm, it resolves conflicts by
     * replacing our chain with the longest one in the network.
     *
     * @param nodes list of registered nodes
     * @return True if our chain was replaced, False if not
     * @throws java.net.URISyntaxException when node url is invalid
     * @throws java.io.IOException         when can't request to others nodes or
     */
    public Boolean resolveConflicts(Set<Node> nodes) throws URISyntaxException, IOException {
        Chain newChain = null;
        int maxLength = chainRepository.getChain().getLength();

        for (Node node : nodes) {
            Chain response = restTemplateRepository.requestChain(node.getAddress().toURI() + URL_CHAIN_V1);

            if (response != null) {
                int length = response.getLength();
                List<Block> blocks = response.getBlocks();

                if (length > maxLength && validateBlocks(blocks, mapper)) {
                    maxLength = length;

                    newChain = new Chain();
                    newChain.setLength(maxLength);
                    newChain.setBlocks(blocks);
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
