package roll.the.block.model.node.gateways;

import roll.the.block.model.node.Node;

import java.util.Set;

/**
 * NodeGateway class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
public interface NodeRepository {

    Set<Node> getBlocks();

    void addNode(Node node);

    void resetNodes(Set<Node> newChain);
}
