package br.com.demo.persistence.dao;

import br.com.demo.annotation.ExceptionHandler;
import br.com.demo.custom.CustomRestTemplate;
import br.com.demo.persistence.model.support.Token;
import br.com.demo.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;

import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable {

    private final String BASE_URL = "http://localhost:8085/login";
    private final CustomRestTemplate restTemplate;

    @Inject
    private JsonUtil jsonUtil;

    @Inject
    public LoginDAO(CustomRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ExceptionHandler
    public Token loginReturningToken(String username, String password) {
        String loginJson = "{\"username\":" + addQuotes(username) + ",\"password\":" + addQuotes(password) + "}";
        ResponseEntity<Token> tokenResponseEntity = restTemplate
                .exchange(BASE_URL, POST, new HttpEntity<>(loginJson, jsonUtil.createJsonHeader()), Token.class);
        return tokenResponseEntity.getBody();
    }

    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }



}
