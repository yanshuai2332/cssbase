package com.css.cssbase.base.shiro;

import org.apache.shiro.authc.AuthenticationToken;

public class JwtToken implements AuthenticationToken {
    private static final long serialVersionUID = 7096657413752383147L;

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }
    @Override
    public Object getPrincipal() {
        return token;
    }
    @Override
    public Object getCredentials() {
        return token;
    }
}
