package roll.the.block.model.transaction;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Transaction class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    @JsonProperty("sender")
    private String sender;

    @JsonProperty("recipient")
    private String recipient;

    @JsonProperty("amount")
    private BigDecimal amount;
}
