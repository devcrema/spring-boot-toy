package devcrema.spring_boot_toy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomObjectMapper extends ObjectMapper {

    public CustomObjectMapper(){
        registerModule(new JavaTimeModule());
    }

    public ErrorResponse map(String jsonValue) throws IOException {
        return readValue(jsonValue, ErrorResponse.class);
    }
}
