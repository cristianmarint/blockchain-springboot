package roll.the.block.usecase.mineblock;

import org.springframework.beans.factory.annotation.Autowired;
import roll.the.block.model.block.Block;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class MineBlockUseCase {

    @Autowired
    private BlockchainUseCase blockchain;

    public static final String NODE_ID = UUID.randomUUID().toString().replace("-", "");
    public static final String NODE_ACCOUNT_ADDRESS = "0";
    public static final BigDecimal MINING_CASH_AWARD = BigDecimal.ONE;

    public Mine calculeBlock() {

        // (1) - Calculate the Proof of Work
        Block lastBlock = blockchain.lastBlock();

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
