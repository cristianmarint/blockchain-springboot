package roll.the.block.localrepository;

import roll.the.block.model.block.Block;
import roll.the.block.model.block.gateways.BlockRepository;

import java.util.ArrayList;
import java.util.List;

public class BlockRepositoryImp implements BlockRepository {

    private List<Block> blocks;

    public BlockRepositoryImp() {
        blocks = new ArrayList<>();
    }

    @Override
    public List<Block> getBlocks() {
        return blocks;
    }

    @Override
    public Integer countBlocks() {
        return blocks.size();
    }

    @Override
    public void addBlock(Block block) {
        blocks.add(block);
    }

    @Override
    public Block getLastBlock() {
        return blocks.get(blocks.size() - 1);
    }
}