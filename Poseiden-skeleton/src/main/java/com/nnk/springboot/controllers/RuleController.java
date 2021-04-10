package com.nnk.springboot.controllers;

import com.nnk.springboot.model.Rule;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RuleController {
  // TODO: Inject RuleName service

  @RequestMapping("/rule/list")
  public String home(Model model) {
    // TODO: find all Rule, add to model
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
    // TODO: get Rule by Id and to model then show to the form
    return "rule/update";
  }

  @PostMapping("/rule/update/{id}")
  public String updateRule(@PathVariable("id") Integer id, Rule rule,
      BindingResult result, Model model) {
    // TODO: check required fields, if valid call service to update RuleName and
    // return RuleName list
    return "redirect:/ruleName/list";
  }

  @GetMapping("/ruleName/delete/{id}")
  public String deleteRule(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Rule by Id and delete the RuleName, return to Rule list
    return "redirect:/rule/list";
  }
}
