package com.prospring.ch16.util;

import org.springframework.web.util.UriUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {
    public static String encodeUrlPathSegment(String pathSegment, HttpServletRequest httpServletRequest) {
        String characterEncoding = httpServletRequest.getCharacterEncoding();
        if (characterEncoding == null) {
            characterEncoding = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }
        return UriUtils.encodePathSegment(pathSegment, characterEncoding);

    }
}
