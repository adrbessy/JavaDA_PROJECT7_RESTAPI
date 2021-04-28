package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.service.BidService;
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
public class BidRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BidService bidServiceMock;

  private Bid bid;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetBids() throws Exception {
    mockMvc.perform(get("/bids")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateBid() throws Exception {
    bid = new Bid();
    bid.setAccount("account");
    bid.setType("type");
    bid.setAskQuantity(10);

    when(bidServiceMock.saveBid(bid)).thenReturn(bid);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/bid")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(bid));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteBid() throws Exception {
    Integer id = 1;
    bid = new Bid();
    Bid bid2 = null;

    when(bidServiceMock.bidExist(id)).thenReturn(true);
    when(bidServiceMock.deleteBid(id)).thenReturn(bid2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/bid?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteBidIfidDoesntExist() throws Exception {
    Integer id = 1;
    bid = new Bid();

    when(bidServiceMock.bidExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/bid?id=1"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateBid() throws Exception {
    Integer id = 1;
    bid = new Bid();
    bid.setAccount("account");
    bid.setType("type");
    bid.setAskQuantity(10);

    when(bidServiceMock.bidExist(id)).thenReturn(true);
    when(bidServiceMock.getBid(id)).thenReturn(bid);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/bid/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(bid));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

}
