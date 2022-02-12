package roll.the.block.api;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
public class PingPongRest {
//    private final MyUseCase useCase;


    @GetMapping(path = "/ping")
    public String commandName() {
//      return useCase.doAction();
        return "Pong \uD83C\uDFD3";
    }
}
