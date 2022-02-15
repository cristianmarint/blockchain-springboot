package roll.the.block.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.chain.Chain;
import roll.the.block.model.chain.gateways.ChainRestTemplate;

import java.io.IOException;
import java.util.Set;

/**
 * ChainRestTemplateImp class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public class ChainRestTemplateImp implements ChainRestTemplate {

    private final RestConsumer restConsumer;

    @Autowired
    public ChainRestTemplateImp(
            RestConsumer restConsumer
    ) {
        this.restConsumer = restConsumer;
    }

    @Override
    public Set<Chain> requestChain(String uri) throws IOException {
        restConsumer.requestGet(uri);
        return null;
    }
}
