package com.grow.common_ui.web.convertor.text;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.Text;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class TextDeserializer extends JsonDeserializer<Text> {

    @Override
    public Text deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        String value = jsonParser.getValueAsString();
        if (value != null) {
            return new Text(value);
        }
        return null;
    }

}
