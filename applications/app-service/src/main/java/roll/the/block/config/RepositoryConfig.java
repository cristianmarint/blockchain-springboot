package roll.the.block.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roll.the.block.localrepository.BlockRepositoryImp;
import roll.the.block.localrepository.ChainRepositoryImp;
import roll.the.block.model.block.gateways.BlockRepository;
import roll.the.block.model.chain.gateways.ChainRepository;
import roll.the.block.model.node.gateways.NodeRepository;
import roll.the.block.localrepository.NodeRepositoryImp;

@Configuration
public class RepositoryConfig {

    @Bean
    public BlockRepository createBlockRepository(){
        return new BlockRepositoryImp();
    }

    @Bean
    public ChainRepository createChainRepository(){
        return new ChainRepositoryImp();
    }

    @Bean
    public NodeRepository createNodeRepository(){
        return new NodeRepositoryImp();
    }
}
