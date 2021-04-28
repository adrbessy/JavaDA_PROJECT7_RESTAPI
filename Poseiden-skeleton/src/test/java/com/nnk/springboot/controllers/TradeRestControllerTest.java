package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.Trade;
import com.nnk.springboot.service.TradeService;
import java.sql.Timestamp;
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
public class TradeRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TradeService tradeServiceMock;

  private Trade trade;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetTrades() throws Exception {
    mockMvc.perform(get("/trades")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateTrade() throws Exception {
    trade = new Trade();
    trade.setAccount("account");
    trade.setType("type");
    trade.setBuyQuantity(10);

    when(tradeServiceMock.saveTrade(trade)).thenReturn(trade);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/trade")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(trade));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteTrade() throws Exception {
    Integer id = 1;
    trade = new Trade();
    Trade trade2 = null;

    when(tradeServiceMock.tradeExist(id)).thenReturn(true);
    when(tradeServiceMock.deleteTrade(id)).thenReturn(trade2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/trade?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteTradeIfidDoesntExist() throws Exception {
    Integer id = 1;
    trade = new Trade();

    when(tradeServiceMock.tradeExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/trade?id=1"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateTrade() throws Exception {
    Integer id = 1;
    trade = new Trade();
    trade.setAccount("account");
    trade.setType("type");
    trade.setBuyQuantity(10);
    trade.setSellQuantity(10);
    trade.setBuyPrice(10);
    trade.setSellPrice(10);
    trade.setBenchmark("a");
    trade.setTradeDate(new Timestamp(System.currentTimeMillis()));
    trade.setSecurity("");
    trade.setTrader("a");
    trade.setBook("");
    trade.setStatus("");
    trade.setCreationName("a");
    trade.setCreationDate(new Timestamp(System.currentTimeMillis()));
    trade.setRevisionName("");
    trade.setDealName("a");
    trade.setRevisionDate(new Timestamp(System.currentTimeMillis()));
    trade.setDealType("");
    trade.setSourceListId("");
    trade.setSide("");

    when(tradeServiceMock.tradeExist(id)).thenReturn(true);
    when(tradeServiceMock.getTrade(id)).thenReturn(trade);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/trade/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(trade));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

}
