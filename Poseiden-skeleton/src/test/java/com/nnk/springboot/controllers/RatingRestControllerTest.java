package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.service.RatingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private RatingService ratingServiceMock;

  private Rating rating;

  @Test
  // @WithMockUser(roles = "ADMIN")
  public void testGetRatings() throws Exception {
    mockMvc.perform(get("/ratings")).andExpect(status().isOk());
  }

  @Test
  // @WithMockUser(roles = "ADMIN")
  public void testCreateRating() throws Exception {
    rating = new Rating();
    rating.setMoodysRating("moodysRating");
    rating.setSandpRating("sandpRating");
    rating.setFitchRating("fitchRating");
    rating.setOrderNumber(10);

    when(ratingServiceMock.saveRating(rating)).thenReturn(rating);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/rating")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(rating));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  // @WithMockUser(roles = "ADMIN")
  public void testDeleteRating() throws Exception {
    Integer id = 1;
    rating = new Rating();
    Rating rating2 = null;

    when(ratingServiceMock.ratingExist(id)).thenReturn(true);
    when(ratingServiceMock.deleteRating(id)).thenReturn(rating2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/rating?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  // @WithMockUser(roles = "ADMIN")
  public void testDeleteRatingIfidDoesntExist() throws Exception {
    Integer id = 1;
    rating = new Rating();

    when(ratingServiceMock.ratingExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/rating?id=1"))
        .andExpect(status().isNotFound());
  }

  @Test
  public void testUpdateRating() throws Exception {
    Integer id = 1;
    rating = new Rating();
    rating.setMoodysRating("moodysRating");
    rating.setSandpRating("sandpRating");
    rating.setFitchRating("fitchRating");
    rating.setOrderNumber(10);

    when(ratingServiceMock.ratingExist(id)).thenReturn(true);
    when(ratingServiceMock.getRating(id)).thenReturn(rating);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/rating/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(rating));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

}
