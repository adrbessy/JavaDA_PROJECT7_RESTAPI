package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.AuthenticationRequest;
import com.nnk.springboot.model.AuthenticationResponse;
import com.nnk.springboot.model.User;
import com.nnk.springboot.security.JwtUtil;
import com.nnk.springboot.service.CustomUserDetailsService;
import com.nnk.springboot.service.UserService;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

  private static final Logger logger = LogManager.getLogger(UserRestController.class);

  @Autowired
  private UserService userService;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private CustomUserDetailsService userDetailsService;

  @Autowired
  private JwtUtil jwtTokenUtil;


  @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
  public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
      throws Exception {

    try {
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
              authenticationRequest.getPassword()));
    } catch (BadCredentialsException e) {
      throw new Exception("Incorrect username or password", e);
    }


    final UserDetails userDetails = userDetailsService
        .loadUserByUsername(authenticationRequest.getUsername());

    final String jwt = jwtTokenUtil.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(jwt));
  }


  /**
   * Read - Get all users
   * 
   * @return - An Iterable object of users full filled
   */
  @GetMapping("/users")
  public List<User> getUsers() {
    List<User> userList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'users'");
      userList = userService.getUsers();
      logger.info(
          "response following the GET on the endpoint 'users'.");
    } catch (Exception exception) {
      logger.error("Error in the UserRestController in the method getUsers :"
          + exception.getMessage());
    }
    return userList;
  }

  /**
   * Add a new user
   * 
   * @param user An object user
   * @return The user object saved
   */
  @PostMapping("/user")
  public User createUser(@RequestBody User user) {
    User newUser = null;
    try {
      logger.info("Post request with the endpoint 'user'");
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      newUser = userService.saveUser(user);
      logger.info(
          "response following the Post on the endpoint 'user' with the given user : {"
              + user.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the UserRestController in the method createUser :"
          + exception.getMessage());
    }
    return newUser;
  }

  /**
   * Delete - Delete a user
   * 
   * @param id An id
   * @return - The deleted user
   */
  @DeleteMapping("/user")
  public User deleteUser(@RequestParam Integer id) {
    User user = null;
    boolean existingUser = false;
    try {
      logger.info("Delete request with the endpoint 'user'");
      existingUser = userService.userExist(id);
      if (existingUser) {
        user = userService.deleteUser(id);
        logger.info(
            "response following the DELETE on the endpoint 'user'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the UserRestController in the method deleteUser :"
          + exception.getMessage());
    }
    if (!existingUser) {
      logger.error("The user with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The user with the id " + id + " doesn't exist.");
    }
    return user;
  }

  /**
   * Update an existing user from a given id
   * 
   * @param id   An id
   * @param user A user object with modifications
   * @return The updated user object
   */
  @PutMapping("/user/{id}")
  public User updateUser(@PathVariable("id") final Integer id,
      @RequestBody User user) {
    User userToUpdate = null;
    boolean existingUserId = false;
    try {
      logger.info(
          "Put request of the endpoint 'user' with the id : {" + id + "}");
      existingUserId = userService.userExist(id);
      if (existingUserId) {
        userToUpdate = userService.getUser(id);
        logger.info(
            "response following the Put on the endpoint 'user' with the given id : {"
                + id + "}");
        if (userToUpdate != null) {
          String username = user.getUsername();
          if (username != null) {
            userToUpdate.setUsername(username);
          }
          String password = user.getPassword();
          if (password != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            userToUpdate.setPassword(encoder.encode(user.getPassword()));
          }
          String fullname = user.getFullname();
          if (fullname != null) {
            userToUpdate.setFullname(fullname);
          }
          String role = user.getRole();
          if (role != null) {
            userToUpdate.setRole(role);
          }
          userService.saveUser(userToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the UserRestController in the method updateUser :"
          + exception.getMessage());
    }
    if (!existingUserId) {
      logger.error("The user with the id " + id + " doesn't exist.");
      throw new NonexistentException("The user with the id " + id + " doesn't exist.");
    }
    return userToUpdate;
  }

}
