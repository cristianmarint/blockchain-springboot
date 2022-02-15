package roll.the.block.usecase.mineblock;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.block.Block;
import roll.the.block.model.block.gateways.BlockRepository;
import roll.the.block.model.mine.Mine;
import roll.the.block.usecase.blockchain.BlockchainUseCase;
import roll.the.block.usecase.util.BlockProofOfWorkGenerator;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * MineBlockUseCase class
 *
 * @author Praveendra Singh
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public class MineBlockUseCase {

    public static final String NODE_ID = UUID.randomUUID().toString().replace("-", "");
    public static final String NODE_ACCOUNT_ADDRESS = "0";
    public static final BigDecimal MINING_CASH_AWARD = BigDecimal.ONE;
    private final BlockRepository blockRepository;
    private final BlockchainUseCase blockchain;
    private ObjectMapper mapper;
    @Autowired
    public MineBlockUseCase(
            BlockchainUseCase blockchain,
            ObjectMapper mapper,
            BlockRepository blockRepository
    ) {
        this.blockchain = blockchain;
        this.mapper = mapper;
        this.blockRepository = blockRepository;
    }

    public Mine calculeBlock() throws JsonProcessingException {

        // (1) - Calculate the Proof of Work
        Block lastBlock = blockRepository.getLastBlock();

        Long lastProof = lastBlock.getProof();

        Long proof = BlockProofOfWorkGenerator.proofOfWork(lastProof);

        // (2) - Reward the miner (us) by adding a transaction granting us 1
        // coin
        blockchain.addTransaction(NODE_ACCOUNT_ADDRESS, NODE_ID, MINING_CASH_AWARD);

        // (3) - Forge the new Block by adding it to the chain
        Block newBlock = blockchain.createBlock(proof, lastBlock.hash(mapper));

        return Mine.builder()
                .message("New Block Forged")
                .index(newBlock.getIndex())
                .transactions(newBlock.getTransactions()).proof(newBlock.getProof())
                .previousHsh(newBlock.getPreviousHash()).build();
    }
}
