package com.grow.common_ui.web.convertor.faxnumber;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.FaxNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class FaxNumberDeserializer extends JsonDeserializer<FaxNumber> {

    @Override
    public FaxNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String faxNumber = jsonParser.getValueAsString();
        if (StringUtils.isNoneBlank(faxNumber)) {
            return new FaxNumber(faxNumber);
        }
        return null;
    }
}
