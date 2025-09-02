package com.tadza.users.dtos;

import com.tadza.users.auth.Jwt;
import lombok.Getter;

@Getter
public class LoginResponse {

    private Jwt refreshToken;
    private Jwt accessToken;

    public LoginResponse(Jwt refreshToken, Jwt accessToken){
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
    }

}
