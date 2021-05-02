package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class RuleServiceImplTest {

  @Autowired
  private RuleService ruleService;

  @MockBean
  private RuleRepository ruleRepositoryMock;

  private Rule rule;

  /**
   * test to get all the rules.
   * 
   */
  @Test
  public void testGetRules() {
    rule = new Rule();
    List<Rule> ruleList = new ArrayList<>();
    ruleList.add(rule);

    when(ruleRepositoryMock.findAll()).thenReturn(ruleList);

    List<Rule> result = ruleService.getRules();
    assertThat(result).isEqualTo(ruleList);
  }

  /**
   * test to save a rule
   * 
   */
  @Test
  public void testSaveRule() {
    rule = new Rule();

    when(ruleRepositoryMock.save(rule)).thenReturn(rule);

    Rule result = ruleService.saveRule(rule);
    assertThat(result).isEqualTo(rule);
  }

  /**
   * test to know if a rule exists.
   * 
   */
  @Test
  public void testRuleExist() {
    Integer id = 1;

    when(ruleRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = ruleService.ruleExist(id);
    assertTrue(result);
  }


  /**
   * test to delete a rule.
   * 
   */
  @Test
  public void testDeleteRule() {
    Integer id = 1;
    rule = new Rule();

    when(ruleRepositoryMock.findById(id)).thenReturn(rule);
    doNothing().when(ruleRepositoryMock).deleteById(id);

    Rule result = ruleService.deleteRule(id);
    assertThat(result).isEqualTo(rule);
  }

  /**
   * test to get a rule.
   * 
   */
  @Test
  public void testGetRule() {
    Integer id = 1;
    rule = new Rule();

    when(ruleRepositoryMock.findById(id)).thenReturn(rule);

    assertThat(ruleService.getRule(id)).isEqualTo(rule);
  }

}
