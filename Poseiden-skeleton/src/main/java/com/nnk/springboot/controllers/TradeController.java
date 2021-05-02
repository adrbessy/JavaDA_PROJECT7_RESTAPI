package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Trade;
import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.TradeRepository;
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
public class TradeController {

  private static final Logger logger = LogManager.getLogger(TradeController.class);

  @Autowired
  private TradeRestController tradeRestController;
  @Autowired
  private UserService userService;
  @Autowired
  private TradeRepository tradeRepository;

  @RequestMapping("/trade/list")
  public String home(Model model, @CurrentSecurityContext(expression = "authentication?.name") String username) {
    logger.info(
        "request of the endpoint '/trade/list'");
    List<Trade> tradeList = tradeRestController.getTrades();
    model.addAttribute("tradeList", tradeList);
    User user = userService.getUser(username);
    model.addAttribute("user", user);
    return "trade/list";
  }

  @GetMapping("/trade/add")
  public String addTradeForm(Trade bid) {
    logger.info(
        "request of the endpoint '/trade/add'");
    return "trade/add";
  }

  @PostMapping("/trade/validate")
  public ModelAndView validate(Trade trade, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/trade/validate'");
    tradeRestController.createTrade(trade);
    return new ModelAndView("redirect:/trade/list");
  }

  @GetMapping("/trade/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/trade/update/{id}'");
    Trade trade = tradeRepository.findById(id);
    model.addAttribute("id", id);
    model.addAttribute("trade", trade);
    return "trade/update";
  }

  @PostMapping("/trade/update/{id}")
  public String updateTrade(@PathVariable("id") Integer id, Trade trade,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/trade/update/{id}'");
    tradeRestController.updateTrade(id, trade);
    return "redirect:/trade/list";
  }

  @GetMapping("/trade/delete/{id}")
  public ModelAndView deleteTrade(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "request of the endpoint '/trade/delete/{id}'");
    tradeRestController.deleteTrade(id);
    return new ModelAndView("redirect:/trade/list");
  }
}
