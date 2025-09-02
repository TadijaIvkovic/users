package com.tadza.users.auth;

import com.tadza.users.dtos.LoginDto;
import com.tadza.users.dtos.LoginResponse;
import com.tadza.users.users.User;
import com.tadza.users.users.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public User getCurrentUser(){

        var authentication = SecurityContextHolder.getContext().getAuthentication();
        var userId = (Long) authentication.getPrincipal();
        return userRepository.findById(userId).orElse(null);
    }

    public LoginResponse login(LoginDto loginDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),loginDto.getPassword()));

        var user = userRepository.findByEmail(loginDto.getEmail()).orElseThrow();
        var accessToken = jwtService.generateAccessToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponse(refreshToken, accessToken);
    }

    public Jwt refreshAccessToken(String refreshToken){

        Jwt jwt = jwtService.parseToken(refreshToken);
        if(jwt==null || jwt.isExpired()){
            throw new BadCredentialsException("Invalid refresh token");
        }

        var user = userRepository.findById(jwt.getUserId()).orElseThrow();
        return jwtService.generateAccessToken(user);

    }

}
