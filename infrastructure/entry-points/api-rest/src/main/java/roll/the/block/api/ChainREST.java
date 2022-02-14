package roll.the.block.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roll.the.block.api.models.GenericResponse;
import roll.the.block.model.chain.Chain;
import roll.the.block.usecase.chain.ChainUseCase;

import static roll.the.block.api.constants.Endpoints.URL_CHAIN_V1;

/**
 * ChainREST class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@RestController
@ResponseBody
@RequestMapping(value = URL_CHAIN_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class ChainREST {

    private final ChainUseCase chainUseCase;

    public ChainREST(
            @Autowired ChainUseCase chainUseCase
    ) {
        this.chainUseCase = chainUseCase;
    }

    @GetMapping
    public GenericResponse fullChain() throws JsonProcessingException {

        Chain chain = chainUseCase.getFullChain();
        return GenericResponse.builder()
                .data(chain)
                .build();
    }
}
