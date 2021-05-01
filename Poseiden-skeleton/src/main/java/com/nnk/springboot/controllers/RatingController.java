package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Rating;
import com.nnk.springboot.model.User;
import com.nnk.springboot.service.UserService;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RatingController {

  private static final Logger logger = LogManager.getLogger(RatingController.class);

  @Autowired
  private RatingRestController ratingRestController;
  @Autowired
  private UserService userService;

  @RequestMapping("/rating/list")
  public String home(Model model, @CurrentSecurityContext(expression = "authentication?.name") String username) {
    logger.info(
        "request of the endpoint '/rating/list'");
    List<Rating> ratingList = ratingRestController.getRatings();
    model.addAttribute("ratingList", ratingList);
    User user = userService.getUser(username);
    model.addAttribute("user", user);
    return "rating/list";
  }

  @GetMapping("/rating/add")
  public String addRatingForm(Rating rating) {
    logger.info(
        "request of the endpoint '/rating/add'");
    return "rating/add";
  }

  @PostMapping("/rating/validate")
  public ModelAndView validate(Rating rating, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/rating/validate'");
    ratingRestController.createRating(rating);
    return new ModelAndView("redirect:/rating/list");
  }

  @GetMapping("/rating/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/rating/update/{id}'");
    Rating newRating = new Rating();
    model.addAttribute("id", id);
    model.addAttribute("newRating", newRating);
    return "rating/update";
  }

  @PostMapping("/rating/update/{id}")
  public String updateRating(@PathVariable("id") Integer id, Rating rating,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/rating/update/{id}'");
    ratingRestController.updateRating(id, rating);
    return "redirect:/rating/list";
  }

  @GetMapping("/rating/delete/{id}")
  public ModelAndView deleteRating(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "request of the endpoint '/rating/delete/{id}'");
    ratingRestController.deleteRating(id);
    return new ModelAndView("redirect:/rating/list");
  }
}
