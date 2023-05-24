package com.grow.common_infra.descriptor;

import com.grow.common_core.domain.value_objects.Photo;
import com.grow.common_infra.basictype.StringWrapperBasicType;

import java.net.MalformedURLException;
import java.net.URL;

public class PhotoBasicType extends StringWrapperBasicType<Photo> {

    public PhotoBasicType() throws Exception {
        super(Photo.class, (url) -> {
            try {
                return new Photo(new URL(url));
            } catch (MalformedURLException e) {
                throw new RuntimeException(String.format("Unable to construct URL from string %s", url));
            }
        }, (photo) -> photo.getValue().toString(), false);
    }

}
