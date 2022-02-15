package roll.the.block.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "roll.the.block",
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)
//@EnableSwagger2
public class UseCasesConfig {

//        @Bean
//        public Docket api() {
//                return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//                        .paths(PathSelectors.any()).build();
//        }
}
