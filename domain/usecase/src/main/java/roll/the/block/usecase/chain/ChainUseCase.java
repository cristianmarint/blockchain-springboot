package roll.the.block.usecase.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.chain.Chain;
import roll.the.block.usecase.blockchain.BlockchainUseCase;

/**
 * ChainUseCase class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public class ChainUseCase {

    private final BlockchainUseCase blockchain;

    @Autowired
    public ChainUseCase(
            BlockchainUseCase blockchain
    ) {
        this.blockchain = blockchain;
    }

    public Chain getFullChain() {
        return Chain.builder()
                .chain(blockchain.getChain())
                .length(blockchain.getChain().size())
                .build();
    }

}
