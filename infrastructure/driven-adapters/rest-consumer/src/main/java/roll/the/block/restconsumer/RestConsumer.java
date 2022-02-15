package roll.the.block.restconsumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.stereotype.Service;
import roll.the.block.model.rest.models.GenericResponse;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class RestConsumer// implements Gateway from domain
{

//    @Value("${adapter.restconsumer.url}")
    private String url;
    private final OkHttpClient client;
    private final ObjectMapper mapper;


    // these methods are an example that illustrates the implementation of OKHTTP Client.
    // You should use the methods that you implement from the Gateway from the domain.

    public GenericResponse requestGet(String uri) throws IOException {

        Request request = new Request.Builder()
                .url(uri)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        return mapper.readValue(client.newCall(request).execute().body().string(), GenericResponse.class);
    }

    public ObjectResponse requestPost() throws IOException {
        String json = mapper.writeValueAsString(ObjectRequest.builder()
                .val1("exampleval1")
                .val2("exampleval1")
                .build()
        );

        RequestBody requestBody = RequestBody
                .create(MediaType.parse("application/json; charset=utf-8"), json);

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .addHeader("Content-Type", "application/json")
                .build();

        return mapper.readValue(client.newCall(request).execute().body().string(), ObjectResponse.class);

    }
}