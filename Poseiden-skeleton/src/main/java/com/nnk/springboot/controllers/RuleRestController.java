package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.Rule;
import com.nnk.springboot.service.RuleService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RuleRestController {

  private static final Logger logger = LogManager.getLogger(RuleRestController.class);

  @Autowired
  private RuleService ruleService;

  /**
   * Read - Get all rules
   * 
   * @return - An Iterable object of rules full filled
   */
  @GetMapping("/rules")
  public List<Rule> getRules() {
    List<Rule> ruleList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'rules'");
      ruleList = ruleService.getRules();
      logger.info(
          "response following the GET on the endpoint 'rules'.");
    } catch (Exception exception) {
      logger.error("Error in the RuleRestController in the method getRules :"
          + exception.getMessage());
    }
    return ruleList;
  }

  /**
   * Add a new rule
   * 
   * @param rule An object rule
   * @return The rule object saved
   */
  @PostMapping("/rule")
  public Rule createRule(@RequestBody Rule rule) {
    Rule newRule = null;
    try {
      logger.info("Post request with the endpoint 'rule'");
      newRule = ruleService.saveRule(rule);
      logger.info(
          "response following the Post on the endpoint 'rule' with the given rule : {"
              + rule.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the RuleRestController in the method createRule :"
          + exception.getMessage());
    }
    return newRule;
  }

  /**
   * Delete - Delete a rule
   * 
   * @param id An id
   * @return - The deleted rule
   */
  @DeleteMapping("/rule")
  public Optional<Rule> deleteRule(@RequestParam Integer id) {
    Optional<Rule> rule = null;
    boolean existingRule = false;
    try {
      logger.info("Delete request with the endpoint 'rule'");
      existingRule = ruleService.ruleExist(id);
      if (existingRule) {
        rule = ruleService.deleteRule(id);
        logger.info(
            "response following the DELETE on the endpoint 'rule'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the RuleRestController in the method deleteRule :"
          + exception.getMessage());
    }
    if (!existingRule) {
      logger.error("The rule with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The rule with the id " + id + " doesn't exist.");
    }
    return rule;
  }

  /**
   * Update an existing rule from a given id
   * 
   * @param id   An id
   * @param rule A rule object with modifications
   * @return The updated rule object
   */
  @PutMapping("/rule/{id}")
  public Rule updateRule(@PathVariable("id") final Integer id,
      @RequestBody Rule rule) {
    Rule ruleToUpdate = null;
    boolean existingRuleId = false;
    try {
      logger.info(
          "Put request of the endpoint 'rule' with the id : {" + id + "}");
      existingRuleId = ruleService.ruleExist(id);
      if (existingRuleId) {
        ruleToUpdate = ruleService.getRule(id);
        logger.info(
            "response following the Put on the endpoint 'rule' with the given id : {"
                + id + "}");
        if (ruleToUpdate != null) {
          String name = rule.getName();
          if (name != null) {
            ruleToUpdate.setName(name);
          }
          String description = rule.getDescription();
          if (description != null) {
            ruleToUpdate.setDescription(description);
          }
          String json = rule.getJson();
          if (json != null) {
            ruleToUpdate.setJson(json);
          }
          String template = rule.getTemplate();
          if (template != null) {
            ruleToUpdate.setTemplate(template);
          }
          String sqlStr = rule.getSqlStr();
          if (sqlStr != null) {
            ruleToUpdate.setSqlStr(sqlStr);
          }
          String sqlPart = rule.getSqlPart();
          if (sqlPart != null) {
            ruleToUpdate.setSqlPart(sqlPart);
          }
          ruleService.saveRule(ruleToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the RuleRestController in the method updateRule :"
          + exception.getMessage());
    }
    if (!existingRuleId) {
      logger.error("The rule with the id " + id + " doesn't exist.");
      throw new NonexistentException("The rule with the id " + id + " doesn't exist.");
    }
    return ruleToUpdate;
  }

}
