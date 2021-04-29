package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.User;
import com.nnk.springboot.service.UserService;
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
public class UserRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userServiceMock;

  private User user;
  private User user2;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetUsers() throws Exception {
    mockMvc.perform(get("/users")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateUser() throws Exception {
    user = new User();
    user.setUsername("username");
    user.setPassword("aZer0%54a");
    user.setFullname("fullname");
    user.setRole("user");

    when(userServiceMock.saveUser(user)).thenReturn(user);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/user")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(user));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteUser() throws Exception {
    Integer id = 1;
    user = new User();
    User user2 = null;

    when(userServiceMock.userExist(id)).thenReturn(true);
    when(userServiceMock.deleteUser(id)).thenReturn(user2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/user?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteUserIfidDoesntExist() throws Exception {
    Integer id = 1;
    user = new User();

    when(userServiceMock.userExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/user?id=1"))
        .andExpect(status().isNotFound());
  }

  /*
   * @Test
   * 
   * @WithMockUser(roles = "ADMIN") public void testUpdateUser() throws Exception
   * { Integer id = 1; user = new User(); user.setId(id);
   * user.setUsername("username"); user.setPassword("aZer0%54aPOP");
   * user.setFullname("fullname"); user.setRole("USER"); user2 = new User();
   * user2.setUsername("username2");
   * 
   * when(userServiceMock.userExist(id)).thenReturn(true);
   * when(userServiceMock.getUser(id)).thenReturn(user);
   * when(userServiceMock.saveUser(user)).thenReturn(user);
   * 
   * MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/user/1")
   * .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.
   * APPLICATION_JSON).characterEncoding("UTF-8") .content(new
   * ObjectMapper().writeValueAsString(user2));
   * this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk()
   * ); }
   */

}
