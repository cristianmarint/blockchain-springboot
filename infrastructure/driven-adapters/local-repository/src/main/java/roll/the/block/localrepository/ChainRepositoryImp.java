package roll.the.block.localrepository;

import roll.the.block.model.chain.Chain;
import roll.the.block.model.chain.gateways.ChainRepository;

/**
 * ChainRepositoryImp class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
public class ChainRepositoryImp implements ChainRepository {

    private Chain chain;

    public ChainRepositoryImp() {
        this.chain = new Chain();
    }

    @Override
    public void resetChain(Chain newChain) {
        chain = newChain;
    }

    @Override
    public Chain getChain() {
        return chain;
    }
}
