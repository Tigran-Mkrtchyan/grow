package com.grow.common_ui.web.convertor.image;

import com.grow.common_core.domain.value_objects.MediaType;
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
public class MediaDto {
    MediaType type;
    Name name;
    URL url;
}
