package com.nnk.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
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
  @Size(min = 2, max = 30)
  private String password;

  @NotNull
  @Size(min = 2, max = 30)
  private String fullname;

  @NotNull
  private String role;

}
