package com.grow.common_ui.web.convertor.password;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.Password;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PasswordSerializer extends JsonSerializer<Password> {

    @Override
    public void serialize(Password password, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (password != null) {
            jsonGenerator.writeString(password.getPassword());
        }
    }

}
