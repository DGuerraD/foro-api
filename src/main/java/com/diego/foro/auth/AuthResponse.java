package com.diego.foro.auth;

public class AuthResponse {
    private String type = "Bearer";
    private String token;

    public AuthResponse(String token) { this.token = token; }

    public String getType() { return type; }
    public String getToken() { return token; }
}
