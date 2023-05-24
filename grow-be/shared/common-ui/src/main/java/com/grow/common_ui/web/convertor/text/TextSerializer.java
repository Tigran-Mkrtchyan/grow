package com.grow.common_ui.web.convertor.text;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.Text;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class TextSerializer extends JsonSerializer<Text> {

    @Override
    public void serialize(Text text, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (text != null) {
            jsonGenerator.writeString(text.getValue());
        }
    }

}
