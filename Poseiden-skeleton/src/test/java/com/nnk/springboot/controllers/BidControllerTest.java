package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.Bid;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.service.UserService;
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
  @MockBean
  private UserService userServiceMock;

  /*
   * @Test
   * 
   * @WithMockUser(roles = "ADMIN") public void testHome() throws Exception {
   * List<Bid> bids = new ArrayList<>(); User user = new User();
   * user.setRole("ADMIN"); user.setUsername("superadri");
   * 
   * when(bidRestControllerMock.getBids()) .thenReturn(bids);
   * when(userServiceMock.getUser("superadri")) .thenReturn(user);
   * 
   * mockMvc.perform(get("/bid/list"))
   * .andExpect(status().isOk()).andExpect(view().name("bid/list")); }
   */

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddBidForm() throws Exception {
    mockMvc.perform(get("/bid/add"))
        .andExpect(status().isOk()).andExpect(view().name("bid/add"));
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testValidate() throws Exception {
    Bid bid = new Bid();

    when(bidRestControllerMock.createBid(bid)).thenReturn(bid);

    mockMvc.perform(post("/bid/validate").contentType("text/html;charset=UTF-8").sessionAttr("bid", bid))
        .andExpect(status().is3xxRedirection());
  }



}