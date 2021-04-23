package com.nnk.springboot.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

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
    assertNotNull(rating.getId());
    assertTrue(rating.getOrderNumber() == 10);

    // Update
    rating.setOrderNumber(20);
    rating = ratingRepository.save(rating);
    assertTrue(rating.getOrderNumber() == 20);

    // Find
    List<Rating> listResult = ratingRepository.findAll();
    assertTrue(listResult.size() > 0);

    // Delete
    Integer id = rating.getId();
    ratingRepository.delete(rating);
    Optional<Rating> ratingList = ratingRepository.findById(id);
    assertFalse(ratingList.isPresent());
  }

  @Test
  public void simpleEqualsRating() {
    EqualsVerifier.forClass(Rating.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
