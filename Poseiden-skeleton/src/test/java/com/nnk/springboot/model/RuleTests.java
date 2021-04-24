package com.nnk.springboot.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.repositories.RuleRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@SpringBootTest
public class RuleTests {

  private Rule rule;

  @Autowired
  private RuleRepository ruleNameRepository;

  @BeforeEach
  private void setUp() {
    rule = new Rule();
    rule.setName("Rule Name");
    rule.setDescription("Description");
    rule.setJson("Json");
    rule.setTemplate("Template");
    rule.setSqlStr("SQL");
    rule.setSqlPart("SQL Part");
  }

  @Test
  public void ruleTest() {

    // Save
    rule = ruleNameRepository.save(rule);
    assertNotNull(rule.getId());
    assertTrue(rule.getName().equals("Rule Name"));

    // Update
    rule.setName("Rule Name Update");
    rule = ruleNameRepository.save(rule);
    assertTrue(rule.getName().equals("Rule Name Update"));

    // Find
    List<Rule> listResult = ruleNameRepository.findAll();
    assertTrue(listResult.size() > 0);

    // Delete
    Integer id = rule.getId();
    ruleNameRepository.delete(rule);
    Rule ruleList = ruleNameRepository.findById(id);
    assertNull(ruleList);
  }

  @Test
  public void simpleEqualsRule() {
    EqualsVerifier.forClass(Rule.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
