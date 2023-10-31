package com.babinska.plannerfortutor.user;

import com.babinska.plannerfortutor.configuration.PreConfiguredUsersConfiguration;
import com.babinska.plannerfortutor.auth.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public UserDetails create(RegisterRequest request) {

    // TODO validate unique email; strong password (8 chars, big letter, numeric sing, special sign, etc...)

    User userToSave = User.builder()
        .firstName(request.getFirstname())
        .lastName(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();

    final User savedUser = userRepository.save(userToSave);

    log.info("Saved new User with id: {} and email: {}", savedUser.getId(), savedUser.getEmail());

    return savedUser;
  }

  public void create(PreConfiguredUsersConfiguration.User user) {

    // TODO validate unique email; strong password (8 chars, big letter, numeric sing, special sign, etc...)

    User userToSave = User.builder()
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .email(user.getEmail())
        .password(passwordEncoder.encode(user.getPassword()))
        .role(user.getRole())
        .build();

    User savedUser = userRepository.save(userToSave);

    log.info("Saved new User with id: {} and email: {}", savedUser.getId(), savedUser.getEmail());
  }

  public UserDetails findByEmail(final String email) {
    return userRepository.findByEmail(email)
        .orElseThrow(() -> new UsernameNotFoundException("User with email %s not found".formatted(email))); // TODO create custom exception
  }

}
