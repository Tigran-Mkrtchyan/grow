package com.grow.common_ui.web.convertor.phone;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
@JsonComponent
public class PhoneSerializer extends JsonSerializer<PhoneNumber> {

    @Override
    public void serialize(PhoneNumber phoneNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (phoneNumber != null) {
            jsonGenerator.writeString(phoneNumber.getValue());
        }
    }

}
