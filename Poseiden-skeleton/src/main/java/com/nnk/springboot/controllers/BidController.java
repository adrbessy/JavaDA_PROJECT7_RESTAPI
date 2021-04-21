package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Bid;
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
public class BidController {

  @Autowired
  private BidRestController bidRestController;

  @RequestMapping("/bidList/list")
  public String home(Model model) {
    List<Bid> bidList = bidRestController.getBids();
    model.addAttribute("bidList", bidList);
    return "bidList/list";
  }

  @GetMapping("/bidList/add")
  public String addBidForm(Bid bid) {
    return "bidList/add";
  }

  @PostMapping("/bidList/validate")
  public ModelAndView validate(Bid bid, BindingResult result, Model model) {
    // TODO: check data valid and save to db, after saving return bid list
    bidRestController.createBid(bid);
    return new ModelAndView("redirect:/bidList/list");
  }

  @GetMapping("/bidList/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Bid newBid = new Bid();
    model.addAttribute("id", id);
    model.addAttribute("newBid", newBid);
    return "bidList/update";
  }

  @PostMapping("/bidList/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, Bid bid,
      BindingResult result, Model model) {
    Bid updatedBid = bidRestController.updateBid(id, bid);
    return "redirect:/bidList/list";
  }

  @GetMapping("/bidList/delete/{id}")
  public ModelAndView deleteBid(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Bid by Id and delete the bid, return to Bid list
    bidRestController.deleteBid(id);
    return new ModelAndView("redirect:/bidList/list");
  }
}
