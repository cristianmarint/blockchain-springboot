package roll.the.block.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * GenericResponse class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Builder
@Data
public class GenericResponse {

    @Builder.Default
    @JsonProperty("data")
    private Object data = null;
}
