package com.grow.common_ui.web.convertor.username;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.Username;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UsernameSerializer extends JsonSerializer<Username> {

    @Override
    public void serialize(Username username, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (username != null) {
            jsonGenerator.writeString(username.value());
        }
    }

}
