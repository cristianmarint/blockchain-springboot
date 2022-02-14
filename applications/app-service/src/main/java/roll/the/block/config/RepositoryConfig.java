package roll.the.block.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "roll.the.block")
@EntityScan("roll.the.block")
public class RepositoryConfig {
}
