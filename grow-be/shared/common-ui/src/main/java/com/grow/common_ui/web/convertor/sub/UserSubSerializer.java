package com.grow.common_ui.web.convertor.sub;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.UserSub;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class UserSubSerializer extends JsonSerializer<UserSub> {

    @Override
    public void serialize(UserSub sub, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (sub != null) {
            jsonGenerator.writeString(sub.value());
        }
    }

}
