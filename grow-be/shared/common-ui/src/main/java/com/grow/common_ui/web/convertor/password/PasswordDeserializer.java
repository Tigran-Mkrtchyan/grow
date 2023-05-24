package com.grow.common_ui.web.convertor.password;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.Password;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PasswordDeserializer extends JsonDeserializer<Password> {

    @Override
    public Password deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String password = jsonParser.getValueAsString();
        if (StringUtils.isNoneBlank(password)) {
            return new Password(password);
        }
        return null;
    }
}
