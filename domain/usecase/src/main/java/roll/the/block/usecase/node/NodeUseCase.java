package roll.the.block.usecase.node;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.node.Node;
import roll.the.block.model.node.NodeMessage;
import roll.the.block.model.node.gateways.NodeRepository;
import roll.the.block.usecase.blockchain.BlockchainUseCase;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Set;

/**
 * TransactionUseCase class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Slf4j
@Service
public class NodeUseCase {

    private final BlockchainUseCase blockchain;

    private final NodeRepository nodeRepository;

    @Autowired
    public NodeUseCase(
            BlockchainUseCase blockchain,
            NodeRepository nodeRepository
    ) {
        this.blockchain = blockchain;
        this.nodeRepository = nodeRepository;
    }

    /**
     * Add a new node to the list of nodes
     *
     * @param nodes List of nodes
     * @return set of registered nodes
     * @throws IllegalArgumentException when Nodes are empty
     */
    public Set<Node> registerNode(Set<Node> nodes) throws IllegalArgumentException {
        if (nodes.isEmpty()) {
            throw new IllegalArgumentException("Nodes are empty");
        }

        for (Node node : nodes) {
            blockchain.registerNode(node);
        }
        return nodeRepository.getBlocks();
    }

    /**
     * This is our consensus algorithm, it resolves conflicts by
     * replacing our chain with the longest one in the network.
     *
     * @return True if our chain was replaced, False if not
     * @throws java.net.URISyntaxException when node url is invalid
     * @throws java.io.IOException         when can't request to others nodes or
     */
    public NodeMessage resolveConflict() throws URISyntaxException, IOException {
        Set<Node> nodes = nodeRepository.getBlocks();
        boolean resolved = blockchain.resolveConflicts(nodes);

        NodeMessage nodeMessage = NodeMessage.builder()
                .nodes(nodes)
                .build();

        if (resolved) {
            nodeMessage.setMessage("Our chain was replaced");
        } else {
            nodeMessage.setMessage("Our chain is authoritative");
        }

        return nodeMessage;
    }
}
