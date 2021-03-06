package roll.the.block.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import roll.the.block.model.node.Node;
import roll.the.block.model.node.NodeMessage;
import roll.the.block.model.rest.models.GenericData;
import roll.the.block.model.rest.models.GenericResponse;
import roll.the.block.usecase.node.NodeUseCase;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

import static roll.the.block.model.constants.Endpoints.URL_NODE_V1;

/**
 * ChainREST class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@RestController
@ResponseBody
@RequestMapping(value = URL_NODE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
public class NodeREST {

    private final NodeUseCase nodeUseCase;

    public NodeREST(
            @Autowired NodeUseCase nodeUseCase
    ) {
        this.nodeUseCase = nodeUseCase;
    }

    /**
     * Add a new node to the list of nodes
     *
     * @param nodes List of nodes
     * @return current nodes
     */
    @Operation(summary = "Add a new node to the list of nodes")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "List of current nodes"
            )
    })
    @PostMapping("/register")
    public GenericResponse registerNode(@RequestBody @Valid Set<Node> nodes) {
        Set<Node> storedNodes = nodeUseCase.registerNode(nodes);

        GenericData response = GenericData.builder()
                .message("New nodes have been added")
                .content(storedNodes)
                .build();

        return GenericResponse.builder()
                .data(response)
                .build();
    }

    /**
     * This is our consensus algorithm, it resolves conflicts by
     * replacing our chain with the longest one in the network.
     *
     * @return True if our chain was replaced, False if not
     * @throws java.net.URISyntaxException when node url is invalid
     * @throws java.io.IOException         when can't request to others nodes or
     */
    @GetMapping("/resolve")
    public GenericResponse resolveNode() throws URISyntaxException, IOException {
        NodeMessage nodeMessage = nodeUseCase.resolveConflict();

        GenericData response = GenericData.builder()
                .message(nodeMessage.getMessage())
                .content(nodeMessage.getNodes())
                .build();

        return GenericResponse.builder()
                .data(response)
                .build();
    }
}
