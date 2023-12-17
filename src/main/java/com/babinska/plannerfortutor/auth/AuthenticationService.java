package com.babinska.plannerfortutor.auth;

import com.babinska.plannerfortutor.configuration.JwtService;
import com.babinska.plannerfortutor.token.Token;
import com.babinska.plannerfortutor.token.TokenRepository;
import com.babinska.plannerfortutor.token.TokenType;
import com.babinska.plannerfortutor.user.User;
import com.babinska.plannerfortutor.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
class AuthenticationService {

    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenRepository tokenRepository;

    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {
        User user = userService.create(request);
        var jwtToken = jwtService.generateToken(user);
        saveUserToken(user, jwtToken);
        return new AuthenticationResponse(jwtToken);
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
        saveUserToken(user, jwtToken);

        return new AuthenticationResponse(jwtToken);
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
}
