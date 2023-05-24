package com.grow.common_ui.web.convertor.domainobjectid;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.grow.kernel.domain.base.DomainObjectId;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

@JsonComponent
public class DomainObjectIdSerializer extends JsonSerializer<DomainObjectId> {

    @Override
    public void serialize(DomainObjectId domainObjectId, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if (domainObjectId != null) {
            jsonGenerator.writeString(domainObjectId.getId());
        }
    }

}
