package roll.the.block.model.chain.gateways;

import org.springframework.stereotype.Repository;
import roll.the.block.model.chain.Chain;

import java.util.Set;

/**
 * ChainRepository class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Repository
public interface ChainRepository {
    void resetChain(Set<Chain> newChain);
}
