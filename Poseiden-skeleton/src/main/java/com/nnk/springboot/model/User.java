package com.nnk.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

  @NotNull
  @Size(min = 2, max = 30)
  private String username;

  @NotNull
  @Size(min = 8, max = 50)
  // @ValidPassword
  private String password;

  @NotNull
  @Size(min = 2, max = 30)
  private String fullname;

  @NotNull
  private String role;

}
