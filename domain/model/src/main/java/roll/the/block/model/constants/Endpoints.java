package roll.the.block.model.constants;

import lombok.NoArgsConstructor;

/**
 * Endpoints class
 *
 * @author cristianmarint
 * @project RollTheBlock
 * @since v1.0.0 - feb. 2022
 */
@NoArgsConstructor
public final class Endpoints {
    /**
     * The constant FULL_BASE_V1.
     */
    public static final String FULL_BASE_V1 = "http://localhost:8080";
    /**
     * The constant URL_BASE_V1.
     */
    public static final String URL_BASE_V1 = "/api/v1";
    /**
     * The constant URL_AUTH_V1.
     */
    public static final String URL_AUTH_V1 = "/api/v1/auth/";
    /**
     * The constant URL_STATIC_FILE_V1.
     */
    public static final String URL_STATIC_FILE_V1 = "/api/v1/static";

    public static final String URL_MINE_V1 = URL_BASE_V1 + "/mine";

    public static final String URL_CHAIN_V1 = URL_BASE_V1 + "/chain";

    public static final String URL_TRANSACTION_V1 = URL_BASE_V1 + "/transaction";

    public static final String URL_NODE_V1 = URL_BASE_V1 + "/node";

    public static final String[] URL_OPENAPI_WHITE_LIST = {
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
    };
}
