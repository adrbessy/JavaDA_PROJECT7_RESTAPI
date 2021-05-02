package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Bid;
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
public class BidController {

  private static final Logger logger = LogManager.getLogger(BidController.class);

  @Autowired
  private BidRestController bidRestController;
  @Autowired
  private UserService userService;

  @RequestMapping("/bid/list")
  public String home(Model model,
      @CurrentSecurityContext(expression = "authentication?.name") String username) {
    logger.info(
        "request of the endpoint '/bid/list'");
    List<Bid> bidList = bidRestController.getBids();
    model.addAttribute("bidList", bidList);
    User user = userService.getUser(username);
    model.addAttribute("user", user);
    return "bid/list";
  }

  @GetMapping("/bid/add")
  public String addBidForm(Bid bid) {
    logger.info(
        "request of the endpoint '/bid/add'");
    return "bid/add";
  }

  @PostMapping("/bid/validate")
  public ModelAndView validate(Bid bid, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/bid/validate'");
    bidRestController.createBid(bid);
    return new ModelAndView("redirect:/bid/list");
  }

  @GetMapping("/bid/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/bid/update/{id}'");
    Bid newBid = new Bid();
    model.addAttribute("id", id);
    model.addAttribute("newBid", newBid);
    return "bid/update";
  }

  @PostMapping("/bid/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, Bid bid,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/bid/update/{id}'");
    bidRestController.updateBid(id, bid);
    return "redirect:/bid/list";
  }

  @GetMapping("/bid/delete/{id}")
  public ModelAndView deleteBid(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "request of the endpoint '/bid/delete/{id}'");
    bidRestController.deleteBid(id);
    return new ModelAndView("redirect:/bid/list");
  }
}
