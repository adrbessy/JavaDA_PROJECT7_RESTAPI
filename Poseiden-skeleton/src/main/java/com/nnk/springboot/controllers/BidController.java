package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Bid;
import com.nnk.springboot.service.BidService;
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
public class BidController {
  @Autowired
  private BidService bidService;

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
  public String validate(Bid bid, BindingResult result, Model model) {
    // TODO: check data valid and save to db, after saving return bid list
    return "bidList/add";
  }

  @GetMapping("/bidList/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Bid bidToUpdate = bidService.getBid(id);
    Bid newBid = new Bid();
    model.addAttribute("bidToUpdate", bidToUpdate);
    model.addAttribute("newBid", newBid);
    return "bidList/update";
  }

  @PostMapping("/bidList/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, Bid bid,
      BindingResult result, Model model) {
    // TODO: check required fields, if valid call service to update Bid and return
    // list Bid
    Bid updatedBid = bidRestController.updateBid(id, bid);
    return "redirect:/bidList/list";
  }

  @GetMapping("/bidList/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Bid by Id and delete the bid, return to Bid list
    return "redirect:/bidList/list";
  }
}
