package roll.the.block.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * OkHttpClientConfig class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@Configuration
public class BeanLoaderConfig {
    @Bean
    public OkHttpClient OkHttpClientFactory() {
        return new OkHttpClient();
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }
}
