package br.com.demo.persistence.dao;

import br.com.demo.annotation.ExceptionHandler;
import br.com.demo.custom.CustomRestTemplate;
import br.com.demo.persistence.model.support.Token;
import br.com.demo.util.JsonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import javax.inject.Inject;
import java.io.Serializable;

import static br.com.demo.util.ApiUtil.*;
import static org.springframework.http.HttpMethod.POST;

public class LoginDAO implements Serializable {

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
                .exchange(LOGIN_URL, POST, new HttpEntity<>(loginJson, jsonUtil.createJsonHeader()), Token.class);
        return tokenResponseEntity.getBody();
    }

    private String addQuotes(String value) {
        return new StringBuilder(300).append("\"").append(value).append("\"").toString();
    }



}
