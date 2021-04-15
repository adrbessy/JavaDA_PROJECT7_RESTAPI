package com.nnk.springboot;

import com.nnk.springboot.model.Rule;
import com.nnk.springboot.repositories.RuleRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
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
    Assert.assertNotNull(rule.getId());
    Assert.assertTrue(rule.getName().equals("Rule Name"));

    // Update
    rule.setName("Rule Name Update");
    rule = ruleNameRepository.save(rule);
    Assert.assertTrue(rule.getName().equals("Rule Name Update"));

    // Find
    List<Rule> listResult = ruleNameRepository.findAll();
    Assert.assertTrue(listResult.size() > 0);

    // Delete
    Integer id = rule.getId();
    ruleNameRepository.delete(rule);
    Optional<Rule> ruleList = ruleNameRepository.findById(id);
    Assert.assertFalse(ruleList.isPresent());
  }
}
