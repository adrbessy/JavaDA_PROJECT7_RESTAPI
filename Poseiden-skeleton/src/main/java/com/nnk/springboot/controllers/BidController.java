package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Bid;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

  private static final Logger logger = LogManager.getLogger(BidController.class);

  @Autowired
  private BidRestController bidRestController;

  @RequestMapping("/bidList/list")
  public String home(Model model) {
    logger.info(
        "request of the endpoint '/bidList/list'");
    List<Bid> bidList = bidRestController.getBids();
    model.addAttribute("bidList", bidList);
    return "bidList/list";
  }

  @GetMapping("/bidList/add")
  public String addBidForm(Bid bid) {
    logger.info(
        "request of the endpoint '/bidList/add'");
    return "bidList/add";
  }

  @PostMapping("/bidList/validate")
  public ModelAndView validate(Bid bid, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/bidList/validate'");
    bidRestController.createBid(bid);
    return new ModelAndView("redirect:/bidList/list");
  }

  @GetMapping("/bidList/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/bidList/update/{id}'");
    Bid newBid = new Bid();
    model.addAttribute("id", id);
    model.addAttribute("newBid", newBid);
    return "bidList/update";
  }

  @PostMapping("/bidList/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, Bid bid,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/bidList/update/{id}'");
    bidRestController.updateBid(id, bid);
    return "redirect:/bidList/list";
  }

  @GetMapping("/bidList/delete/{id}")
  public ModelAndView deleteBid(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "request of the endpoint '/bidList/delete/{id}'");
    bidRestController.deleteBid(id);
    return new ModelAndView("redirect:/bidList/list");
  }
}
