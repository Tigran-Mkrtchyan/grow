package com.grow.common_infra.storage.model;

import com.grow.common_core.domain.value_objects.FileType;
import lombok.Getter;

import static com.grow.kernel.domain.validation.Preconditions.requireNonNull;

@Getter
public class StorageFile {

    private byte[] content;
    private FileType contentType;

    public StorageFile(byte[] content, FileType contentType) {
        requireNonNull(content, new IllegalArgumentException("Content must not be null"));
        requireNonNull(contentType, new IllegalArgumentException("Content type must not be null"));
        this.content = content;
        this.contentType = contentType;
    }

}
