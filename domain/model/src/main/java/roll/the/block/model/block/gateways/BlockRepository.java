package roll.the.block.model.block.gateways;

import org.springframework.stereotype.Repository;
import roll.the.block.model.block.Block;

import java.util.List;

@Repository
public interface BlockRepository {

    List<Block> getGeneratedBlocks();

    Integer countBlocks();

    void addBlock(Block block);

    Block getLastBlock();
}
