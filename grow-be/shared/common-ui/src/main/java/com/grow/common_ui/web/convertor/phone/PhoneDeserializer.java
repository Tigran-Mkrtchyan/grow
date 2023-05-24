package com.grow.common_ui.web.convertor.phone;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.grow.common_core.domain.value_objects.PhoneNumber;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class PhoneDeserializer extends JsonDeserializer<PhoneNumber> {

    @Override
    public PhoneNumber deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String phone = jsonParser.getValueAsString();
        if (StringUtils.isNoneBlank(phone)) {
            return new PhoneNumber(phone);
        }
        return null;
    }
}
