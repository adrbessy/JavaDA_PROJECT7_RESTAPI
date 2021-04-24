package com.nnk.springboot.service;

import com.nnk.springboot.model.User;
import java.util.List;

public interface UserService {

  /**
   * Get all users
   * 
   * @return all users
   */
  List<User> getUsers();

  /**
   * Save a user
   * 
   * @param user A user to save
   * @return the saved user
   */
  User saveUser(User user);

  /**
   * Check if the given user id exists.
   * 
   * @param id The user id
   * @return true if the id exists, otherwise returns false
   */
  boolean userExist(Integer id);

  /**
   * Delete a user
   * 
   * @param id An id
   * @return the deleted user
   */
  User deleteUser(Integer id);

  /**
   * Get a user from an id
   * 
   * @param id The id of the user table
   * @return The bid
   */
  User getUser(Integer id);

}
