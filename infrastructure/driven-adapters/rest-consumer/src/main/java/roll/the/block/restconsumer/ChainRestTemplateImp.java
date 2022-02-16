package roll.the.block.restconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.chain.Chain;
import roll.the.block.model.chain.gateways.ChainRestTemplate;
import roll.the.block.model.rest.models.GenericResponse;

import java.io.IOException;

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
    public Chain requestChain(String uri) throws IOException {
        GenericResponse genericResponse = restConsumer.requestGet(uri);
        return (Chain) genericResponse.getData().getContent();
    }
}
