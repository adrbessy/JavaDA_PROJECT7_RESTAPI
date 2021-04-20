package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Rule;
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
public class RuleController {
  // TODO: Inject Rule service

  @Autowired
  private RuleRestController ruleRestController;

  @RequestMapping("/rule/list")
  public String home(Model model) {
    List<Rule> ruleList = ruleRestController.getRules();
    model.addAttribute("ruleList", ruleList);
    return "rule/list";
  }

  @GetMapping("/rule/add")
  public String addRuleForm(Rule bid) {
    return "rule/add";
  }

  @PostMapping("/rule/validate")
  public String validate(Rule rule, BindingResult result, Model model) {
    // TODO: check data valid and save to db, after saving return Rule list
    return "rule/add";
  }

  @GetMapping("/rule/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    Rule newRule = new Rule();
    model.addAttribute("id", id);
    model.addAttribute("newRule", newRule);
    return "rule/update";
  }

  @PostMapping("/rule/update/{id}")
  public String updateRule(@PathVariable("id") Integer id, Rule rule,
      BindingResult result, Model model) {
    Rule updatedRule = ruleRestController.updateRule(id, rule);
    return "redirect:/rule/list";
  }

  @GetMapping("/rule/delete/{id}")
  public String deleteRule(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Rule by Id and delete the Rule, return to Rule list
    return "redirect:/rule/list";
  }
}
