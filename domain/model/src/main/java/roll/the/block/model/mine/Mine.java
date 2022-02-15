package roll.the.block.model.mine;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import roll.the.block.model.transaction.Transaction;

import java.util.List;

/**
 * Mine class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Data
@JsonInclude(Include.NON_NULL)
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Mine {
    private String message;
    private Long index;
    private List<Transaction> transactions;
    private Long proof;
    private String previousHsh;
}

