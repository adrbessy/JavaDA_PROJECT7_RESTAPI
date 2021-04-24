package com.nnk.springboot.service;

import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * Get all users
   * 
   * @return all users
   */
  @Override
  public List<User> getUsers() {
    logger.debug("in the method getUsers in the class UserServiceImpl");
    List<User> userList = new ArrayList<>();
    try {
      userList = userRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getUsers :" + exception.getMessage());
    }
    return userList;
  }

  /**
   * Save a user
   * 
   * @param user A user to save
   * @return the saved user
   */
  @Override
  public User saveUser(User user) {
    logger.debug("in the method saveUser in the class UserServiceImpl");
    User savedUser = null;
    try {
      savedUser = userRepository.save(user);
    } catch (Exception exception) {
      logger.error("Error when we try to save a user :" + exception.getMessage());
    }
    return savedUser;
  }

  /**
   * Check if the given user id exists.
   * 
   * @param id The user id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean userExist(Integer id) {
    logger.debug("in the method userExist in the class UserServiceImpl");
    boolean userExist = false;
    try {
      userExist = userRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method userExist :" + exception.getMessage());
    }
    return userExist;
  }

  /**
   * Delete a user
   * 
   * @param id An id
   * @return the deleted user
   */
  @Override
  public User deleteUser(Integer id) {
    logger.debug("in the method deleteUser in the class UserServiceImpl");
    User user = null;
    try {
      user = userRepository.findById(id);
      userRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteUser :" + exception.getMessage());
    }
    return user;
  }

  /**
   * Get a user from an id
   * 
   * @param id The id of the user table
   * @return The bid
   */
  @Override
  public User getUser(Integer id) {
    logger.debug("in the method getUser in the class UserServiceImpl");
    User user = null;
    try {
      user = userRepository.findById(id);
    } catch (Exception exception) {
      logger.error("Error in the method getUser :" + exception.getMessage());
    }
    return user;
  }

}
