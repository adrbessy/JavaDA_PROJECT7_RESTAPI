package com.nnk.springboot.controllers;

import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
import javax.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

  private static final Logger logger = LogManager.getLogger(UserController.class);

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private UserService userService;

  @RequestMapping("/user/list")
  public String home(Model model, @CurrentSecurityContext(expression = "authentication?.name") String username) {
    logger.info(
        "request of the endpoint '/user/list'");
    model.addAttribute("users", userRepository.findAll());
    User user = userService.getUser(username);
    model.addAttribute("user", user);
    return "user/list";
  }

  @GetMapping("/user/add")
  public String addUser(User bid) {
    logger.info(
        "request of the endpoint '/user/add'");
    return "user/add";
  }

  @PostMapping("/user/validate")
  public String validate(@Valid User user, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/user/validate'");
    if (!result.hasErrors()) {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
      user.setPassword(encoder.encode(user.getPassword()));
      userRepository.save(user);
      model.addAttribute("users", userRepository.findAll());
      return "redirect:/user/list";
    }
    return "user/add";
  }

  @GetMapping("/user/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/user/update/{id}'");
    User user = userRepository.findById(id);
    user.setPassword("");
    model.addAttribute("user", user);
    return "user/update";
  }

  @PostMapping("/user/update/{id}")
  public String updateUser(@PathVariable("id") Integer id, User user,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/user/update/{id}'");
    if (result.hasErrors()) {
      return "user/update";
    }
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    user.setPassword(encoder.encode(user.getPassword()));
    user.setId(id);
    userRepository.save(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }

  @GetMapping("/user/delete/{id}")
  public String deleteUser(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/user/delete/{id}'");
    User user = userRepository.findById(id);
    userRepository.delete(user);
    model.addAttribute("users", userRepository.findAll());
    return "redirect:/user/list";
  }
}
