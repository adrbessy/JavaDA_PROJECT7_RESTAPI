package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.User;
import com.nnk.springboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class UserServiceImplTest {

  @Autowired
  private UserService userService;

  @MockBean
  private UserRepository userRepositoryMock;

  private User user;

  /**
   * test to get all the users.
   * 
   */
  @Test
  public void testGetUsers() {
    user = new User();
    List<User> userList = new ArrayList<>();
    userList.add(user);

    when(userRepositoryMock.findAll()).thenReturn(userList);

    List<User> result = userService.getUsers();
    assertThat(result).isEqualTo(userList);
  }

  /**
   * test to save a user
   * 
   */
  @Test
  public void testSaveUser() {
    user = new User();

    when(userRepositoryMock.save(user)).thenReturn(user);

    User result = userService.saveUser(user);
    assertThat(result).isEqualTo(user);
  }

  /**
   * test to know if a user exists.
   * 
   */
  @Test
  public void testUserExist() {
    Integer id = 1;

    when(userRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = userService.userExist(id);
    assertTrue(result);
  }


  /**
   * test to delete a user.
   * 
   */
  @Test
  public void testDeleteUser() {
    Integer id = 1;
    user = new User();

    when(userRepositoryMock.findById(id)).thenReturn(user);
    doNothing().when(userRepositoryMock).deleteById(id);

    User result = userService.deleteUser(id);
    assertThat(result).isEqualTo(user);
  }

  /**
   * test to get a user.
   * 
   */
  @Test
  public void testGetUser() {
    Integer id = 1;
    user = new User();

    when(userRepositoryMock.findById(id)).thenReturn(user);

    assertThat(userService.getUser(id)).isEqualTo(user);
  }

}
