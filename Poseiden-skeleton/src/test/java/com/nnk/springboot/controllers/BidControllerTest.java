package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.Bid;
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
public class BidControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BidRestController bidRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testHome() throws Exception {
    List<Bid> bids = new ArrayList<>();

    when(bidRestControllerMock.getBids())
        .thenReturn(bids);

    mockMvc.perform(get("/bidList/list"))
        .andExpect(status().isOk()).andExpect(view().name("bidList/list"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddBidForm() throws Exception {
    mockMvc.perform(get("/bidList/add"))
        .andExpect(status().isOk()).andExpect(view().name("bidList/add"));
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testValidate() throws Exception {
    Bid bid = new Bid();

    when(bidRestControllerMock.createBid(bid)).thenReturn(bid);

    mockMvc.perform(post("/bidList/validate").contentType("text/html;charset=UTF-8").sessionAttr("bid", bid))
        .andExpect(status().is3xxRedirection());
  }



}