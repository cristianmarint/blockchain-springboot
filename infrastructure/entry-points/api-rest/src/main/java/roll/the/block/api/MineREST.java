package roll.the.block.api;

import java.awt.PageAttributes.MediaType;

import static roll.the.block.api.constants.Endpoints.URL_MINE_V1;

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
@AllArgsConstructor
public class MineREST {
    private final Mine
}
