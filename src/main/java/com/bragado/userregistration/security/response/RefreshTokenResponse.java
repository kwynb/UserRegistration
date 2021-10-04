package com.bragado.userregistration.security.response;

public class RefreshTokenResponse {

    private String type = "Bearer";
    private String refreshToken;
    private String accessToken;

    public RefreshTokenResponse(String type, String refreshToken, String accessToken) {
        this.type = type;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }
}
