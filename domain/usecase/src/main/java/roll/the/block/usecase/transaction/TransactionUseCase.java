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

    public Long createTransaction(Transaction trans) throws JsonProcessingException {
        return blockchain.addTransaction(trans.getSender(), trans.getRecipient(), trans.getAmount());
    }
}
