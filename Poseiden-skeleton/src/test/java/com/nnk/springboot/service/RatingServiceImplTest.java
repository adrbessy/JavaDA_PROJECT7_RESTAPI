package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class RatingServiceImplTest {

  @Autowired
  private RatingService ratingService;

  @MockBean
  private RatingRepository ratingRepositoryMock;

  private Rating rating;

  /**
   * test to get all the ratings.
   * 
   */
  @Test
  public void testGetRatings() {
    rating = new Rating();
    List<Rating> ratingList = new ArrayList<>();
    ratingList.add(rating);

    when(ratingRepositoryMock.findAll()).thenReturn(ratingList);

    List<Rating> result = ratingService.getRatings();
    assertThat(result).isEqualTo(ratingList);
  }

  /**
   * test to save a rating
   * 
   */
  @Test
  public void testSaveRating() {
    rating = new Rating();

    when(ratingRepositoryMock.save(rating)).thenReturn(rating);

    Rating result = ratingService.saveRating(rating);
    assertThat(result).isEqualTo(rating);
  }

  /**
   * test to know if a rating exists.
   * 
   */
  @Test
  public void testRatingExist() {
    Integer id = 1;

    when(ratingRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = ratingService.ratingExist(id);
    assertTrue(result);
  }


  /**
   * test to delete a rating.
   * 
   */
  @Test
  public void testDeleteRating() {
    Integer id = 1;
    rating = new Rating();

    when(ratingRepositoryMock.findById(id)).thenReturn(rating);
    doNothing().when(ratingRepositoryMock).deleteById(id);

    Rating result = ratingService.deleteRating(id);
    assertThat(result).isEqualTo(rating);
  }

  /**
   * test to get a rating.
   * 
   */
  @Test
  public void testGetRating() {
    Integer id = 1;
    rating = new Rating();

    when(ratingRepositoryMock.findById(id)).thenReturn(rating);

    assertThat(ratingService.getRating(id)).isEqualTo(rating);
  }

}
