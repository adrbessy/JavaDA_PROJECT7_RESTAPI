package com.nnk.springboot.service;

import com.nnk.springboot.model.Rule;
import java.util.List;

public interface RuleService {

  /**
   * Get all rules
   * 
   * @return all rules
   */
  List<Rule> getRules();

  /**
   * Save a rule
   * 
   * @param rule A rule to save
   * @return the saved rule
   */
  Rule saveRule(Rule rule);

  /**
   * Check if the given rule id exists.
   * 
   * @param id The rule id
   * @return true if the id exists, otherwise returns false
   */
  boolean ruleExist(Integer id);

  /**
   * Delete a rule
   * 
   * @param id An id
   * @return the deleted rule
   */
  Rule deleteRule(Integer id);

  /**
   * Get a rule from an id
   * 
   * @param id The id of the rule table
   * @return The bid
   */
  Rule getRule(Integer id);

}
