package roll.the.block.model.block.gateways;

import roll.the.block.model.block.Block;

import java.util.List;

public interface BlockRepository {

    List<Block> getBlocks();

    Integer countBlocks();

    void addBlock(Block block);

    Block getLastBlock();
}
