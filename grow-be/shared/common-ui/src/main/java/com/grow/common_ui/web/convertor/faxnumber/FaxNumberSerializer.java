package com.grow.common_ui.web.convertor.faxnumber;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.FaxNumber;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class FaxNumberSerializer extends JsonSerializer<FaxNumber> {

    @Override
    public void serialize(FaxNumber faxNumber, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (faxNumber != null) {
            jsonGenerator.writeString(faxNumber.getValue());
        }
    }

}
