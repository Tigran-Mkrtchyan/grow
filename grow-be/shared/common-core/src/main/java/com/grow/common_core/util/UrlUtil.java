package com.grow.common_core.util;

import org.apache.http.client.utils.URIBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Map;

public class UrlUtil {

    public static String construct(String uri, Map<String, String> queryParams) {
        URIBuilder builder = builder(uri);
        queryParams.forEach(builder::addParameter);
        return build(builder).toString();
    }

    public static URL construct(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public static class Params {

        public static final String ACTION_TYPE = "actionType";
        public static final String USERNAME = "username";
        public static final String RESOURCE_ID = "resourceId";

    }

    private static URIBuilder builder(String uri) {
        try {
            return new URIBuilder(uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private static URI build(URIBuilder builder) {
        try {
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
