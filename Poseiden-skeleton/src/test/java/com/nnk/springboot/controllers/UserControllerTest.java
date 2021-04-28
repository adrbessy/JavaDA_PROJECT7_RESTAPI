package com.nnk.springboot.controllers;

import com.nnk.springboot.repositories.UserRepository;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

  // @Autowired
  // private MockMvc mockMvc;

  @MockBean
  private UserRestController userRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;

  // private User user;

  /*
   * @Test
   * 
   * @WithMockUser(username = "superadri") public void testHome() throws Exception
   * { user = new User(); user.setUsername("adrien"); List<User> users = new
   * ArrayList<>();
   * 
   * when(userRepositoryMock.findAll()) .thenReturn(users);
   * 
   * mockMvc.perform(get("/user/list"))
   * .andExpect(status().isOk()).andExpect(view().name("user/list")); }
   */

}
