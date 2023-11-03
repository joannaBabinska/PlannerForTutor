package com.babinska.plannerfortutor.vo;

import java.util.function.IntPredicate;
import java.util.regex.Pattern;

public record Password(String value) {

  public boolean containsCharacterType(IntPredicate input) {
    return value.chars().noneMatch(input);
  }

  public boolean isLongerThan(int input) {
    return value.length() > input;
  }

  public boolean isShorterThan(int input) {
    return value.length() < input;
  }

  public boolean containsWhitespaces() {
    return value.contains(" ");
  }

  public boolean containsSpecialCharacter() {
    return Pattern
        .compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]")
        .matcher(value)
        .find();
  }

}
