package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Trade;
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
public class TradeController {

  @Autowired
  private TradeRestController tradeRestController;

  @RequestMapping("/trade/list")
  public String home(Model model) {
    List<Trade> tradeList = tradeRestController.getTrades();
    model.addAttribute("tradeList", tradeList);
    return "trade/list";
  }

  @GetMapping("/trade/add")
  public String addUser(Trade bid) {
    return "trade/add";
  }

  @PostMapping("/trade/validate")
  public ModelAndView validate(Trade trade, BindingResult result, Model model) {
    tradeRestController.createTrade(trade);
    return new ModelAndView("redirect:/trade/list");
  }

  @GetMapping("/trade/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Trade newTrade = new Trade();
    model.addAttribute("id", id);
    model.addAttribute("newTrade", newTrade);
    return "trade/update";
  }

  @PostMapping("/trade/update/{id}")
  public String updateTrade(@PathVariable("id") Integer id, Trade trade,
      BindingResult result, Model model) {
    tradeRestController.updateTrade(id, trade);
    return "redirect:/trade/list";
  }

  @GetMapping("/trade/delete/{id}")
  public ModelAndView deleteTrade(@PathVariable("id") Integer id, Model model) {
    tradeRestController.deleteTrade(id);
    return new ModelAndView("redirect:/trade/list");
  }
}
