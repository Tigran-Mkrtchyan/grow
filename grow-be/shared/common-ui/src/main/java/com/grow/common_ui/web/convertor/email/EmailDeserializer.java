package com.grow.common_ui.web.convertor.email;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.EmailAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class EmailDeserializer extends JsonDeserializer<EmailAddress> {

    @Override
    public EmailAddress deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String email = jsonParser.getValueAsString();
        if (StringUtils.isNoneBlank(email)) {
            return new EmailAddress(email);
        }
        return null;
    }

}
