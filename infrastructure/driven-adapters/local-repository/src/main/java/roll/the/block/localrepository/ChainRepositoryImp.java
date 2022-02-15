package roll.the.block.localrepository;

import roll.the.block.model.chain.Chain;
import roll.the.block.model.chain.gateways.ChainRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * ChainRepositoryImp class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
public class ChainRepositoryImp implements ChainRepository {

    private Set<Chain> chain;

    public ChainRepositoryImp(){
        this.chain = new HashSet<>();
    }

    @Override
    public void resetChain(Set<Chain> newChain) {
        chain = newChain;
    }
}
