package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.Rating;
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
public class RatingControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RatingRestController ratingRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testHome() throws Exception {
    List<Rating> ratings = new ArrayList<>();

    when(ratingRestControllerMock.getRatings())
        .thenReturn(ratings);

    mockMvc.perform(get("/rating/list"))
        .andExpect(status().isOk()).andExpect(view().name("rating/list"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddRatingForm() throws Exception {
    mockMvc.perform(get("/rating/add"))
        .andExpect(status().isOk()).andExpect(view().name("rating/add"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testValidate() throws Exception {
    Rating rating = new Rating();

    when(ratingRestControllerMock.createRating(rating)).thenReturn(rating);

    mockMvc
        .perform(post("/rating/validate").contentType("text/html;charset=UTF-8").sessionAttr("rating",
            rating))
        .andExpect(status().is3xxRedirection());
  }


}