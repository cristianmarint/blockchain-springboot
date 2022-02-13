package roll.the.block.model.chain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import roll.the.block.model.block.Block;

import java.util.List;

/**
 * Chain class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Chain {
    private Integer length;
    private List<Block> chain;
}
