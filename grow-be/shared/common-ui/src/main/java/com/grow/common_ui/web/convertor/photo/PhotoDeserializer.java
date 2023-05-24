package com.grow.common_ui.web.convertor.photo;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.Photo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.net.URL;

@JsonComponent
public class PhotoDeserializer extends JsonDeserializer<Photo> {

    @Override
    public Photo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String photo = jsonParser.getValueAsString();
        if (StringUtils.isNoneBlank(photo)) {
            return new Photo(new URL(photo));
        }
        return null;
    }

}
