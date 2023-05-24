package com.opp.util;

import java.net.URL;

public class UrlUtils {
    public static URL to(String url) {
        try {
            return new URL(url);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
