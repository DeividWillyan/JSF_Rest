package br.com.demo.util;

import static  br.com.demo.custom.CustomURLEncoderDecoder.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import javax.faces.annotation.RequestCookieMap;
import javax.inject.Inject;
import javax.servlet.http.Cookie;
import java.util.Map;

public class JsonUtil {

    @Inject
    @RequestCookieMap
    private Map<String, Object> cookieMap;

    public HttpHeaders createJsonHeader() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return headers;
    }

    public HttpHeaders createTokenizedHeader() {
        HttpHeaders headers = createJsonHeader();
        Cookie tokenCookie = (Cookie) cookieMap.get("token");
        headers.add("Authorization", decodeUTF8(tokenCookie.getValue()));
        return headers;
    }



}
