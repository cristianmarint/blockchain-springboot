package roll.the.block.usecase.chain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import roll.the.block.model.block.gateways.BlockRepository;
import roll.the.block.model.chain.Chain;

/**
 * ChainUseCase class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Service
public class ChainUseCase {

    private final BlockRepository blockRepository;

    @Autowired
    public ChainUseCase(
            BlockRepository blockRepository
    ) {
        this.blockRepository = blockRepository;
    }

    /**
     * Show the complete latest full blockchain
     *
     * @return the current full chain
     */
    public Chain getFullChain() {
        return Chain.builder()
                .blocks(blockRepository.getBlocks())
                .length(Math.toIntExact(blockRepository.countBlocks()))
                .build();
    }

}
