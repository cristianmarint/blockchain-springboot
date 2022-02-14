package roll.the.block.model.block.gateways;

import org.springframework.stereotype.Repository;
import roll.the.block.model.block.Block;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BlockRepositoryImp implements BlockRepository {
    private List<Block> blocksStored;

    public BlockRepositoryImp() {
        blocksStored = new ArrayList<>();
    }

    @Override
    public List<Block> getGeneratedBlocks() {
        return blocksStored;
    }

    @Override
    public Integer countBlocks() {
        return blocksStored.size();
    }

    @Override
    public void addBlock(Block block) {
        blocksStored.add(block);
    }

    @Override
    public Block getLastBlock() {
        return blocksStored.get(blocksStored.size() - 1);
    }
}