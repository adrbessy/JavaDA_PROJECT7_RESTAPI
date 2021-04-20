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
  public String validate(Rating rating, BindingResult result, Model model) {
    // TODO: check data valid and save to db, after saving return Rating list
    return "rating/add";
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
    Rating updatedRating = ratingRestController.updateRating(id, rating);
    return "redirect:/rating/list";
  }

  @GetMapping("/rating/delete/{id}")
  public String deleteRating(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Rating by Id and delete the Rating, return to Rating list
    return "redirect:/rating/list";
  }
}
