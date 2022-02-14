package roll.the.block.usecase.blockchain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.block.Block;
import roll.the.block.model.block.gateways.BlockRepository;
import roll.the.block.model.transaction.Transaction;
import roll.the.block.usecase.util.BlockProofOfWorkGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Slf4j
@Service
public class BlockchainUseCase {

    private final ObjectMapper mapper;
    private BlockRepository blockRepository;
    private List<Transaction> currentTransactions;

    @Autowired
    public BlockchainUseCase(
            ObjectMapper mapper,
            BlockRepository blockRepository
    ) throws JsonProcessingException {
        this.blockRepository = blockRepository;
        this.mapper = mapper;
        currentTransactions = new ArrayList<>();

        createBlock(Block.GENESIS_BLOCK_PROOF, Block.GENESIS_BLOCK_PREV_HASH);
    }

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
        return validChain(blockRepository.getGeneratedBlocks(), mapper);
    }
}
