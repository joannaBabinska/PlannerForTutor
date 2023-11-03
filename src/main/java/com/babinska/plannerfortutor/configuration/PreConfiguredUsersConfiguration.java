package com.babinska.plannerfortutor.configuration;

import com.babinska.plannerfortutor.user.Role;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "preconfigured-users")
public class PreConfiguredUsersConfiguration {

  private String enabled;
  private List<User> users;

  @Getter
  @Setter
  public static class User {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
  }

}
