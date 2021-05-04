package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.Rule;
import com.nnk.springboot.service.RuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RuleRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RuleService ruleServiceMock;

  private Rule rule;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetRules() throws Exception {
    mockMvc.perform(get("/rules")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateRule() throws Exception {
    rule = new Rule();
    rule.setName("name");
    rule.setDescription("description");
    rule.setJson("json");

    when(ruleServiceMock.saveRule(rule)).thenReturn(rule);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rule")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(rule));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteRule() throws Exception {
    Integer id = 1;
    rule = new Rule();
    Rule rule2 = null;

    when(ruleServiceMock.ruleExist(id)).thenReturn(true);
    when(ruleServiceMock.deleteRule(id)).thenReturn(rule2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/rule?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteRuleIfidDoesntExist() throws Exception {
    Integer id = 1;
    rule = new Rule();

    when(ruleServiceMock.ruleExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/rule?id=1"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateRule() throws Exception {
    Integer id = 1;
    rule = new Rule();
    rule.setName("name");
    rule.setDescription("description");
    rule.setJson("json");

    when(ruleServiceMock.ruleExist(id)).thenReturn(true);
    when(ruleServiceMock.getRule(id)).thenReturn(rule);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/rule/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(rule));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateRuleIfIdDoesntExist() throws Exception {
    Integer id = 1;
    rule = new Rule();
    rule.setName("name");
    rule.setDescription("description");
    rule.setJson("json");

    when(ruleServiceMock.ruleExist(id)).thenReturn(false);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/rule/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(rule));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isNotFound());
  }

}
