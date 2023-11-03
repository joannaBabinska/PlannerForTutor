package com.babinska.plannerfortutor.auth;

import com.babinska.plannerfortutor.configuration.JwtService;
import com.babinska.plannerfortutor.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AuthenticationService {

  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;

  public AuthenticationResponse register(RegisterRequest request) {
    UserDetails userDetails = userService.create(request);
    var jwtToken = jwtService.generateToken(userDetails);
    return new AuthenticationResponse(jwtToken);
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    UserDetails user = userService.findByEmail(request.getEmail());
    var jwtToken = jwtService.generateToken(user);
    return new AuthenticationResponse(jwtToken);
  }
}
