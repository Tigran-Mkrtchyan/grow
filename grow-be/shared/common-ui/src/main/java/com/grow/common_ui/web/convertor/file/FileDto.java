package com.grow.common_ui.web.convertor.file;

import com.grow.common_core.domain.value_objects.File;
import com.grow.common_core.domain.value_objects.Name;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.net.URL;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileDto {
    Name name;
    URL url;

    static FileDto fromFile(File file) {
        if (file == null) return null;
        return new FileDto(file.getName(), file.getUrl());
    }
}
