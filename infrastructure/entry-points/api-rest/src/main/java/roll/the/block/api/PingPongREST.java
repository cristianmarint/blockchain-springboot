package roll.the.block.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static roll.the.block.api.constants.Endpoints.URL_BASE_V1;

@RestController
@RequestMapping(value = URL_BASE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PingPongREST {

    @GetMapping(path = "/ping")
    public String commandName() {
        return "Pong \uD83C\uDFD3";
    }
}
