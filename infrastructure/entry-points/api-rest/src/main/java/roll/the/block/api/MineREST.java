package roll.the.block.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roll.the.block.model.mine.Mine;
import roll.the.block.model.rest.models.GenericData;
import roll.the.block.model.rest.models.GenericResponse;
import roll.the.block.usecase.mineblock.MineBlockUseCase;

import static roll.the.block.model.constants.Endpoints.URL_MINE_V1;

/**
 * MineREST class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@RestController
@ResponseBody
@RequestMapping(value = URL_MINE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class MineREST {

    private final MineBlockUseCase mineBlockUseCase;

    public MineREST(
            @Autowired MineBlockUseCase mineBlockUseCase
    ) {
        this.mineBlockUseCase = mineBlockUseCase;
    }

    /**
     * Forge the new Block by adding it to the chain
     *
     * @return Mine a new mine block
     * @throws JsonProcessingException when creating hash fails
     */
    @GetMapping
    public GenericResponse mineBlock() throws JsonProcessingException {

        Mine calculeBlock = mineBlockUseCase.calculeBlock();

        GenericData response = GenericData.builder()
                .content(calculeBlock)
                .build();

        return GenericResponse.builder()
                .data(response)
                .build();
    }
}
