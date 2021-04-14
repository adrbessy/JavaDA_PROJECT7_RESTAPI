package com.nnk.springboot;

import com.nnk.springboot.model.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

  private Rating rating;

  @Autowired
  private RatingRepository ratingRepository;

  @BeforeEach
  private void setUp() {
    rating = new Rating();
    rating.setMoodysRating("moodysRating");
    rating.setSandpRating("sandpRating");
    rating.setFitchRating("fitchRating");
    rating.setOrderNumber(10);
  }

  @Test
  public void ratingTest() {

    // Save
    rating = ratingRepository.save(rating);
    Assert.assertNotNull(rating.getId());
    Assert.assertTrue(rating.getOrderNumber() == 10);

    // Update
    rating.setOrderNumber(20);
    rating = ratingRepository.save(rating);
    Assert.assertTrue(rating.getOrderNumber() == 20);

    // Find
    List<Rating> listResult = ratingRepository.findAll();
    Assert.assertTrue(listResult.size() > 0);

    // Delete
    Integer id = rating.getId();
    ratingRepository.delete(rating);
    Optional<Rating> ratingList = ratingRepository.findById(id);
    Assert.assertFalse(ratingList.isPresent());
  }
}
