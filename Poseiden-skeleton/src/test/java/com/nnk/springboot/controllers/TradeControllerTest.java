package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.Trade;
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
public class TradeControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TradeRestController tradeRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testHome() throws Exception {
    List<Trade> trades = new ArrayList<>();

    when(tradeRestControllerMock.getTrades())
        .thenReturn(trades);

    mockMvc.perform(get("/trade/list"))
        .andExpect(status().isOk()).andExpect(view().name("trade/list"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddTradeForm() throws Exception {
    mockMvc.perform(get("/trade/add"))
        .andExpect(status().isOk()).andExpect(view().name("trade/add"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testValidate() throws Exception {
    Trade trade = new Trade();

    when(tradeRestControllerMock.createTrade(trade)).thenReturn(trade);

    mockMvc
        .perform(post("/trade/validate").contentType("text/html;charset=UTF-8").sessionAttr("trade",
            trade))
        .andExpect(status().is3xxRedirection());
  }


}
