package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.Rule;
import com.nnk.springboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RuleRestController ruleRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testHome() throws Exception {
    List<Rule> rules = new ArrayList<>();

    when(ruleRestControllerMock.getRules())
        .thenReturn(rules);

    mockMvc.perform(get("/rule/list"))
        .andExpect(status().isOk()).andExpect(view().name("rule/list"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddRuleForm() throws Exception {
    mockMvc.perform(get("/rule/add"))
        .andExpect(status().isOk()).andExpect(view().name("rule/add"));
  }

  /*
   * @Test
   * 
   * @WithMockUser(roles = "ADMIN") public void testValidate() throws Exception {
   * Rule rule = new Rule();
   * 
   * when(ruleRestControllerMock.createRule(rule)).thenReturn(rule);
   * 
   * mockMvc.perform(get("/rule/validate")) .andExpect(status().isOk()); }
   */


}
