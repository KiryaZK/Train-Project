package com.aston.frontendpracticeservice.service.implementation;

import com.aston.frontendpracticeservice.domain.entity.User;
import com.aston.frontendpracticeservice.domain.request.AuthRequest;
import com.aston.frontendpracticeservice.domain.response.JwtResponse;
import com.aston.frontendpracticeservice.exception.auth.AuthException;
import com.aston.frontendpracticeservice.service.UserService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;

    private final JwtService jwtService;

    private final Map<String, String> refreshStorage = new ConcurrentHashMap<>();

    public JwtResponse authAndGetToken(AuthRequest authRequest) {
        final User user = userService.getByLogin(authRequest.login());

        if (!user.getPassword().equals(authRequest.password())) {
            throw new AuthException("Incorrect password");
        }

        final String accessToken = jwtService.generateAccessToken(user);
        final String refreshToken = jwtService.generateRefreshToken(user);
        refreshStorage.put(user.getLogin(), refreshToken);

        return JwtResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }


    public JwtResponse getAccessToken(String refreshToken) {
        if (jwtService.validateRefreshToken(refreshToken)) {
            final Claims claims = jwtService.getRefreshClaims(refreshToken);
            final String login = claims.getSubject();
            final String saveRefreshToken = refreshStorage.get(login);
            if (saveRefreshToken != null && saveRefreshToken.equals(refreshToken)) {
                final User user = userService.getByLogin(login);
                final String accessToken = jwtService.generateAccessToken(user);
                return new JwtResponse(accessToken, jwtService.generateRefreshToken(user));
            }
        }
        return new JwtResponse(null, null);
    }

}
