package roll.the.block.model.node;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * NodeMessage class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Builder
@JsonInclude(Include.NON_NULL)
@Data
public class NodeMessage {
    @Builder.Default
    @JsonProperty("message")
    private String message = null;

    @Builder.Default
    @JsonProperty("nodes")
    private Set<Node> nodes = null;
}
