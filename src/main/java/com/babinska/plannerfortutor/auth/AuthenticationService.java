package com.babinska.plannerfortutor.auth;

import com.babinska.plannerfortutor.configuration.JwtService;
import com.babinska.plannerfortutor.token.Token;
import com.babinska.plannerfortutor.token.TokenRepository;
import com.babinska.plannerfortutor.token.TokenType;
import com.babinska.plannerfortutor.user.User;
import com.babinska.plannerfortutor.user.UserRepository;
import com.babinska.plannerfortutor.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static com.babinska.plannerfortutor.configuration.JwtAuthenticationFilter.BEARER;

@Service
@RequiredArgsConstructor
@Slf4j
class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        User user = userService.create(request);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    @Transactional
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        User user = userService.findByEmail(request.getEmail());
        revokeAllUserToken(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken, refreshToken);
    }

    private void revokeAllUserToken(User user) {
        int revokedTokens = tokenRepository.revokeAndExpireTokensByUserId(user.getId());
        log.info("Revoked {} for user {} with id {}", revokedTokens, user.getEmail(), user.getId());
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    @Transactional
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;

        if (authHeader == null || !authHeader.startsWith(BEARER)) {
            return;
        }
        refreshToken = authHeader.substring(BEARER.length());
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            User userDetails = userRepository.findByEmail(userEmail).orElseThrow();
            if (jwtService.isTokenValid(refreshToken, userDetails)) {
                var accessToken = jwtService.generateRefreshToken(userDetails);
                tokenRepository.revokeAndExpireTokensByUserId(userDetails.getId());
                saveUserToken(userDetails,accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}
