package com.grow.common_core.domain.value_objects;

import java.util.Arrays;

public enum FileType {
    DOC("application/msword"),
    HTML("text/html"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    PDF("application/pdf"),
    TXT("text/plain");

    private String type;

    FileType(String type) {
        this.type = type;
    }

    public static FileType from(String source) {
        return Arrays.stream(FileType.values())
                .filter(each -> each.type.equalsIgnoreCase(source))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format(
                        "Unable to construct file type from '%s'", source)));
    }

}
