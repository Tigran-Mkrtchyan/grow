package com.grow.commonui.web.convertor.email;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.EmailAddress;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
@JsonComponent
public class EmailSerializer extends JsonSerializer<EmailAddress> {

    @Override
    public void serialize(EmailAddress emailAddress, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (emailAddress != null) {
            jsonGenerator.writeString(emailAddress.getValue());
        }
    }

}
