package roll.the.block.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
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
                        .description("cristianmarint")
                        .contact(
                                new Contact().name("Dont do it pls \uD83C\uDFC3\uD83C\uDFFE\u200D\u2642\uFE0F")
                                )
                );
    }
}
