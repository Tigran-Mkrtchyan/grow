package com.grow.common_ui.web.convertor.file;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.File;
import com.grow.kernel.exception.BadRequestException;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class FileDeserializer extends JsonDeserializer<File> {
    @Override
    public File deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) {
        FileDto dto;
        try {
            dto = jsonParser.readValueAs(FileDto.class);
            return new File(dto.url, dto.name);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
