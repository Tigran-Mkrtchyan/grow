package com.grow.common_ui.web.convertor.image;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.Media;
import com.grow.kernel.exception.BadRequestException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class MediaDeserializer extends JsonDeserializer<Media> {
    @Override
    public Media deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        MediaDto dto;
        try {
            dto = jsonParser.readValueAs(MediaDto.class);
            return new Media(dto.type, dto.url, dto.name);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
