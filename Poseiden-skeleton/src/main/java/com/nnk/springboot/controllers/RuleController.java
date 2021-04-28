package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Rule;
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
public class RuleController {

  private static final Logger logger = LogManager.getLogger(RatingController.class);

  @Autowired
  private RuleRestController ruleRestController;

  @RequestMapping("/rule/list")
  public String home(Model model) {
    logger.info(
        "request of the endpoint '/rule/list'");
    List<Rule> ruleList = ruleRestController.getRules();
    model.addAttribute("ruleList", ruleList);
    return "rule/list";
  }

  @GetMapping("/rule/add")
  public String addRuleForm(Rule bid) {
    logger.info(
        "request of the endpoint '/rule/add'");
    return "rule/add";
  }

  @PostMapping("/rule/validate")
  public ModelAndView validate(Rule rule, BindingResult result, Model model) {
    logger.info(
        "request of the endpoint '/rule/validate'");
    ruleRestController.createRule(rule);
    return new ModelAndView("redirect:/rule/list");
  }

  @GetMapping("/rule/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "GET request of the endpoint '/rule/update/{id}'");
    Rule newRule = new Rule();
    model.addAttribute("id", id);
    model.addAttribute("newRule", newRule);
    return "rule/update";
  }

  @PostMapping("/rule/update/{id}")
  public String updateRule(@PathVariable("id") Integer id, Rule rule,
      BindingResult result, Model model) {
    logger.info(
        "POST request of the endpoint '/rule/update/{id}'");
    ruleRestController.updateRule(id, rule);
    return "redirect:/rule/list";
  }

  @GetMapping("/rule/delete/{id}")
  public ModelAndView deleteRule(@PathVariable("id") Integer id, Model model) {
    logger.info(
        "request of the endpoint '/rule/delete/{id}'");
    ruleRestController.deleteRule(id);
    return new ModelAndView("redirect:/rule/list");
  }
}
