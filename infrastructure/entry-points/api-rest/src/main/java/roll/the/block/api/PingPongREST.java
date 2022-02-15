package roll.the.block.api;

import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roll.the.block.model.rest.models.GenericData;
import roll.the.block.model.rest.models.GenericResponse;

import static roll.the.block.model.constants.Endpoints.URL_BASE_V1;

@RestController
@RequestMapping(value = URL_BASE_V1, produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PingPongREST {

    @GetMapping(path = "/ping")
    public GenericResponse commandName() {

        GenericData response = GenericData.builder()
                .message("Pong \uD83C\uDFD3")
                .build();

        return GenericResponse.builder()
                .data(response)
                .build();
    }
}
