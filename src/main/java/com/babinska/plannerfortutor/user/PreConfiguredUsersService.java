package com.babinska.plannerfortutor.user;

import com.babinska.plannerfortutor.configuration.PreConfiguredUsersConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = "preconfigured-users", value = "enabled", havingValue = "true")
class PreConfiguredUsersService implements CommandLineRunner {

  private final PreConfiguredUsersConfiguration preConfiguredUsersConfiguration;
  private final UserService userService;

  @Override
  public void run(final String... args) {
    preConfiguredUsersConfiguration.getUsers()
        .forEach(userService::create);
  }

}
