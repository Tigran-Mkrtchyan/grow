package com.grow.common_core.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JsonUtil {

    private static ObjectMapper objectMapper;

    public static String toJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static <T> T fromJson(String json, Class<T> tClass) {
        try {
            return objectMapper.readValue(json, tClass);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format(
                    "Unable to construct instance of '%s' type from json string '%s'.Cause '%s",
                    tClass, json, e.getMessage()));
        }
    }

    @Autowired
    private void init(ObjectMapper objectMapper) {
        JsonUtil.objectMapper = objectMapper;
    }

}