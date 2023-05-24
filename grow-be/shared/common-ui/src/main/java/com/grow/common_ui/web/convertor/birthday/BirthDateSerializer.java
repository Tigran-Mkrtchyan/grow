package com.grow.common_ui.web.convertor.birthday;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.BirthDate;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
@JsonComponent
public class BirthDateSerializer extends JsonSerializer<BirthDate> {

    @Override
    public void serialize(BirthDate birthDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (birthDate != null) {
            jsonGenerator.writeString(birthDate.getValue().toString());
        }
    }

}
