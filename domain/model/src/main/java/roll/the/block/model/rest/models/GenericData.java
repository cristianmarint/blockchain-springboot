package roll.the.block.model.rest.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * GenericData class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Builder
@JsonInclude(Include.NON_NULL)
@Data
public class GenericData {
    @Builder.Default
    @JsonProperty("message")
    private String message = null;

    @Builder.Default
    @JsonProperty("content")
    private Object content = null;
}
