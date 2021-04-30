package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

  private static final Logger logger = LogManager.getLogger(LoginController.class);

  @Autowired
  private UserRepository userRepository;

  /**
   * Just calls the login page
   * 
   * @return - The name of the html page
   */
  @GetMapping("/login")
  public ModelAndView login() {
    logger.info("Get request with the endpoint 'login'");
    ModelAndView mav = new ModelAndView();
    mav.setViewName("login");
    return mav;
  }

  @GetMapping("secure/article-details")
  public ModelAndView getAllUserArticles() {
    logger.info("Get request with the endpoint 'secure/article-details'");
    ModelAndView mav = new ModelAndView();
    mav.addObject("users", userRepository.findAll());
    mav.setViewName("/user/list");
    return mav;
  }

  @GetMapping("error")
  public ModelAndView error() {
    logger.info("Get request with the endpoint 'error'");
    ModelAndView mav = new ModelAndView();
    String errorMessage = "You are not authorized for the requested data.";
    mav.addObject("errorMsg", errorMessage);
    mav.setViewName("403");
    return mav;
  }
}
