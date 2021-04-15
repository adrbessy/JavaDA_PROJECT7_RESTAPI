package com.nnk.springboot.service;

import com.nnk.springboot.model.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RuleServiceImpl implements RuleService {

  private static final Logger logger = LogManager.getLogger(RuleServiceImpl.class);

  @Autowired
  private RuleRepository ruleRepository;

  /**
   * Get all rules
   * 
   * @return all rules
   */
  @Override
  public List<Rule> getRules() {
    logger.debug("in the method getRules in the class RuleServiceImpl");
    List<Rule> ruleList = new ArrayList<>();
    try {
      ruleList = ruleRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getRules :" + exception.getMessage());
    }
    return ruleList;
  }

  /**
   * Save a rule
   * 
   * @param rule A rule to save
   * @return the saved rule
   */
  @Override
  public Rule saveRule(Rule rule) {
    logger.debug("in the method saveRule in the class RuleServiceImpl");
    Rule savedRule = null;
    try {
      savedRule = ruleRepository.save(rule);
    } catch (Exception exception) {
      logger.error("Error when we try to save a rule :" + exception.getMessage());
    }
    return savedRule;
  }

  /**
   * Check if the given rule id exists.
   * 
   * @param id The rule id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean ruleExist(Integer id) {
    logger.debug("in the method ruleExist in the class RuleServiceImpl");
    boolean ruleExist = false;
    try {
      ruleExist = ruleRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method ruleExist :" + exception.getMessage());
    }
    return ruleExist;
  }

  /**
   * Delete a rule
   * 
   * @param id An id
   * @return the deleted rule
   */
  @Override
  public Optional<Rule> deleteRule(Integer id) {
    logger.debug("in the method deleteRule in the class RuleServiceImpl");
    Optional<Rule> rule = null;
    try {
      rule = ruleRepository.findById(id);
      ruleRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteRule :" + exception.getMessage());
    }
    return rule;
  }

  /**
   * Get a rule from an id
   * 
   * @param id The id of the rule table
   * @return The bid
   */
  @Override
  public Rule getRule(Integer id) {
    logger.debug("in the method getRule in the class RuleServiceImpl");
    Optional<Rule> rule = null;
    try {
      rule = ruleRepository.findById(id);
    } catch (Exception exception) {
      logger.error("Error in the method getRule :" + exception.getMessage());
    }
    if (rule.isPresent()) {
      Rule ruleToUpdate = rule.get();
      return ruleToUpdate;
    } else {
      return null;
    }
  }

}
