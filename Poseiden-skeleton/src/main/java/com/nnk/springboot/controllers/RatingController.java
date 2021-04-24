package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Rating;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
  // TODO: Inject Rating service

  @Autowired
  private RatingRestController ratingRestController;

  @RequestMapping("/rating/list")
  public String home(Model model) {
    List<Rating> ratingList = ratingRestController.getRatings();
    model.addAttribute("ratingList", ratingList);
    return "rating/list";
  }

  @GetMapping("/rating/add")
  public String addRatingForm(Rating rating) {
    return "rating/add";
  }

  @PostMapping("/rating/validate")
  public ModelAndView validate(Rating rating, BindingResult result, Model model) {
    ratingRestController.createRating(rating);
    return new ModelAndView("redirect:/rating/list");
  }

  @GetMapping("/rating/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Rating newRating = new Rating();
    model.addAttribute("id", id);
    model.addAttribute("newRating", newRating);
    return "rating/update";
  }

  @PostMapping("/rating/update/{id}")
  public String updateRating(@PathVariable("id") Integer id, Rating rating,
      BindingResult result, Model model) {
    ratingRestController.updateRating(id, rating);
    return "redirect:/rating/list";
  }

  @GetMapping("/rating/delete/{id}")
  public ModelAndView deleteRating(@PathVariable("id") Integer id, Model model) {
    ratingRestController.deleteRating(id);
    return new ModelAndView("redirect:/rating/list");
  }
}
