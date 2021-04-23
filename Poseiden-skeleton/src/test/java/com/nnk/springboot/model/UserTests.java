package com.nnk.springboot.model;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@SpringBootTest
public class UserTests {

  @Test
  public void simpleEqualsUser() {
    EqualsVerifier.forClass(User.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
