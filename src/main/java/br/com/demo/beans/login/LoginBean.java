package br.com.demo.beans.login;

import br.com.demo.custom.CustomURLEncoderDecoder;
import br.com.demo.persistence.dao.LoginDAO;
import br.com.demo.persistence.model.support.Token;

import javax.faces.context.ExternalContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

@Named
@ViewScoped
public class LoginBean implements Serializable {

    private final LoginDAO loginDAO;
    private final ExternalContext externalContext;
    private String username;
    private String password;

    @Inject
    public LoginBean(LoginDAO loginDAO, ExternalContext externalContext) {
        this.loginDAO = loginDAO;
        this.externalContext = externalContext;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() throws UnsupportedEncodingException {
        Token token = loginDAO.loginReturningToken(this.username, this.password);
        if (token == null) return null;
        addCookie(token.getToken(), token.getExpirationTime().toString());
        return "index.xhtml?faces-redirect=true";
    }

    public String logout() {
        removeCookie();
        return "login.xhtml?faces-redirect=true";
    }

    private void removeCookie() {
        addCookie(null, null);
    }

    private void addCookie(String token, String expirationTime) {
        externalContext.addResponseCookie("token", CustomURLEncoderDecoder.encodeUTF8(token), null);
        externalContext.addResponseCookie("expirationTime", expirationTime, null);
    }


}
