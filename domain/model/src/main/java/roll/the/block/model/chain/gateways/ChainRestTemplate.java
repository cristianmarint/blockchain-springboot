package roll.the.block.model.chain.gateways;

import org.springframework.stereotype.Service;
import roll.the.block.model.chain.Chain;

import java.io.IOException;
import java.util.Set;

/**
 * ChainRestTemplateRepository class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public interface ChainRestTemplate {

    Set<Chain> requestChain(String s) throws IOException;
}
