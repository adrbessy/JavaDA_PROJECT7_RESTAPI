package com.nnk.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "User Name is mandatory")
  private String username;

  // ^ # start-of-string
  // (?=.*[0-9]) # a digit must occur at least once
  // (?=.*[a-z]) # a lower case letter must occur at least once
  // (?=.*[A-Z]) # an upper case letter must occur at least once
  // (?=.*[@#$%^&+=]) # a special character must occur at least once
  // (?=\S+$) # no whitespace allowed in the entire string
  // .{8,} # anything, at least eight places though
  // $ # end-of-string

  @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "The password must contain at least one capital letter, one number, one symbol and be at least 8 characters long")
  private String password;

  @NotBlank(message = "Full Name is mandatory")
  private String fullname;

  @NotBlank(message = "Role is mandatory")
  private String role;

}
