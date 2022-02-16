package roll.the.block.usecase.transaction;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.transaction.Transaction;
import roll.the.block.usecase.blockchain.BlockchainUseCase;

/**
 * TransactionUseCase class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public class TransactionUseCase {

    private final BlockchainUseCase blockchain;

    @Autowired
    public TransactionUseCase(
            BlockchainUseCase blockchain
    ) {
        this.blockchain = blockchain;
    }

    /**
     * Creates a new transaction to go into the next mined Block
     *
     * @param transaction incoming transaction
     * @return The index of the Block that will hold this transaction
     */
    public Long createTransaction(Transaction transaction) throws JsonProcessingException {
        return blockchain.addTransaction(transaction.getSender(), transaction.getRecipient(), transaction.getAmount());
    }
}
