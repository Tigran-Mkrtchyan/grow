package com.grow.common_ui.web.convertor.photo;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.common_core.domain.value_objects.Photo;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
@JsonComponent
public class PhotoSerializer extends JsonSerializer<Photo> {

    @Override
    public void serialize(Photo photo, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (photo != null) {
            jsonGenerator.writeString(photo.getValue().toString());
        }
    }

}
