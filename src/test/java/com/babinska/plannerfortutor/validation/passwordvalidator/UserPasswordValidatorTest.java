package com.babinska.plannerfortutor.validation.passwordvalidator;

import com.babinska.plannerfortutor.validation.ValidationResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserPasswordValidatorTest {

  private UserPasswordValidator underTest;

  @BeforeEach
  void beforeEach() {
    underTest = new UserPasswordValidator();
  }

  @Test
  void shouldBeNotValidForPasswordShorterThan8Characters() {
    String candidate = "aA!4567";

    ValidationResult result = underTest.validate(candidate);

    assertTrue(result.hasErrors());
    assertEquals(1, result.getErrors().size());
    assertEquals("Password must have at least 8 chars", result.getErrors().get(0));
  }

  @Test
  void shouldBeNotValidForPasswordContainingWhitespace() {
    String candidate = "aA!4567  asd";

    ValidationResult result = underTest.validate(candidate);

    assertTrue(result.hasErrors());
    assertEquals(1, result.getErrors().size());
    assertEquals("Password cant contain whitespaces", result.getErrors().get(0));
  }

  @Test
  void shouldBeNotValidForPasswordContainingWhitespaces() {
    String candidate = "aA!4567  asd  asd";

    ValidationResult result = underTest.validate(candidate);

    assertTrue(result.hasErrors());
    assertEquals(1, result.getErrors().size());
    assertEquals("Password cant contain whitespaces", result.getErrors().get(0));
  }

  @Test
  void shouldBeNotValidForPasswordLongerThan20CharactersAndNotContainingDigitAndUpperCaseLetter() {
    String candidate = "abcdeabcdeabcdeabcdea";

    ValidationResult result = underTest.validate(candidate);

    List<String> actualErrors = result.getErrors();

    List<String> expectedErrors = List.of("Password can have maximum 20 chars", "Password must contain digit",
        "Password must contain upper case letter");

    assertTrue(result.hasErrors());
    assertEquals(expectedErrors.size(), actualErrors.size());
    Assertions.assertThatCollection(actualErrors).containsAll(expectedErrors);
  }

  @Test
  void shouldBeValidPassword() {
    String candidate = "aA1!jsdf7";

    ValidationResult result = underTest.validate(candidate);

    assertFalse(result.hasErrors());
    assertEquals(0, result.getErrors().size());
  }

}