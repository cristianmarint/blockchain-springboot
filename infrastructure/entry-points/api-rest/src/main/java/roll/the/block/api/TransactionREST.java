package roll.the.block.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roll.the.block.model.rest.models.GenericData;
import roll.the.block.model.rest.models.GenericResponse;
import roll.the.block.model.transaction.Transaction;
import roll.the.block.usecase.transaction.TransactionUseCase;

import javax.validation.Valid;

import static roll.the.block.model.constants.Endpoints.URL_TRANSACTION_V1;

/**
 * ChainREST class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@RestController
@ResponseBody
@RequestMapping(value = URL_TRANSACTION_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class TransactionREST {

    private final TransactionUseCase transactionUseCase;

    public TransactionREST(
            @Autowired TransactionUseCase transactionUseCase
    ) {
        this.transactionUseCase = transactionUseCase;
    }

    /**
     * Creates a new transaction to go into the next mined Block
     *
     * @param transaction incoming transaction
     * @return The index of the Block that will hold this transaction
     */
    @PostMapping
    public GenericResponse createTransaction(@RequestBody @Valid Transaction transaction) throws JsonProcessingException {

        Long blockNumber = transactionUseCase.createTransaction(transaction);

        GenericData response = GenericData.builder()
                .message("Transaction will be added to Block")
                .content(blockNumber)
                .build();

        return GenericResponse.builder()
                .data(response)
                .build();
    }
}
