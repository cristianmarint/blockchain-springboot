package roll.the.block.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * SwaggerConfig class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI createOpenAPIConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("Roll The Block")
                        .version("V1.0.0")
                        .description("Remember that a blockchain is an immutable, sequential chain of records called Blocks. They can contain transactions, files or any data you like, really. But the important thing is that they’re chained together using hashes")
                        .license(
                                new License()
                                        .name("MIT License")
                                        .url("https://github.com/cristianmarint/blockchain-springboot/blob/main/LICENSE")
                        )
                        .contact(
                                new Contact()
                                        .name("Cristian Mar\u00EDn")
                                        .email("cristianmarint@gmail.com")
                                        .url("https://github.com/cristianmarint/")
                        )
                        .termsOfService("https://github.com/cristianmarint/blockchain-springboot/blob/main/LICENSE")
                );
    }
}
