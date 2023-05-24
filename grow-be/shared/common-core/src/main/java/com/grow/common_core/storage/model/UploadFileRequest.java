package com.grow.common_core.storage.model;

import com.grow.common_core.domain.value_objects.Name;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

@Getter
@Accessors(fluent = true)
public class UploadFileRequest {

    private byte[] content;
    private String contentType;
    private Name name;

    public UploadFileRequest(byte[] content,
                             String contentType,
                             Name name) {
        this.setContent(content);
        this.setContentType(contentType);
        this.setName(name);
    }

    private void setContentType(String contentType) {
        if (StringUtils.isBlank(contentType)) {
            throw new IllegalStateException("File upload request content type must not be null/empty");
        }
        this.contentType = contentType;
    }

    private void setContent(byte[] content) {
        if (content == null) {
            throw new IllegalStateException("File upload request content must not be null");
        }
        this.content = content;
    }

    private void setName(Name name) {
        if (name == null) {
            throw new IllegalStateException("Name must not be null");
        }
        this.name = name;
    }

}
