package roll.the.block.localrepository;

import roll.the.block.model.node.Node;
import roll.the.block.model.node.gateways.NodeRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * NodeRepositoryImp class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
public class NodeRepositoryImp implements NodeRepository {

    private Set<Node> nodes;

    public NodeRepositoryImp(){
        nodes = new HashSet<>();
    }

    @Override
    public Set<Node> getBlocks() {
        return nodes;
    }

    @Override
    public void addNode(Node node) {
        nodes.add(node);
    }

    @Override
    public void resetNodes(Set<Node> newChain) {
        nodes = newChain;
    }
}
