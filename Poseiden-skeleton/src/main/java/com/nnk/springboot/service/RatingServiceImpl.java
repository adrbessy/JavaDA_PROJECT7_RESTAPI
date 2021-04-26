package com.nnk.springboot.service;

import com.nnk.springboot.model.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

  private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);

  @Autowired
  private RatingRepository ratingRepository;

  /**
   * Get all ratings
   * 
   * @return all ratings
   */
  @Override
  public List<Rating> getRatings() {
    logger.debug("in the method getRatings in the class RatingServiceImpl");
    List<Rating> ratingList = new ArrayList<>();
    try {
      ratingList = ratingRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getRatings :" + exception.getMessage());
    }
    return ratingList;
  }

  /**
   * Save a rating
   * 
   * @param rating A rating to save
   * @return the saved rating
   */
  @Override
  public Rating saveRating(Rating rating) {
    logger.debug("in the method saveRating in the class RatingServiceImpl");
    Rating savedRating = null;
    try {
      savedRating = ratingRepository.save(rating);
    } catch (Exception exception) {
      logger.error("Error when we try to save a rating :" + exception.getMessage());
    }
    return savedRating;
  }

  /**
   * Check if the given rating id exists.
   * 
   * @param id The rating id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean ratingExist(Integer id) {
    logger.debug("in the method ratingExist in the class RatingServiceImpl");
    boolean ratingExist = false;
    try {
      ratingExist = ratingRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method ratingExist :" + exception.getMessage());
    }
    return ratingExist;
  }

  /**
   * Delete a rating
   * 
   * @param id An id
   * @return the deleted rating
   */
  @Override
  public Rating deleteRating(Integer id) {
    logger.debug("in the method deleteRating in the class RatingServiceImpl");
    Rating rating = null;
    try {
      rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
      ratingRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteRating :" + exception.getMessage());
    }
    return rating;
  }

  /**
   * Get a rating from an id
   * 
   * @param id The id of the rating table
   * @return The bid
   */
  @Override
  public Rating getRating(Integer id) {
    logger.debug("in the method getRating in the class RatingServiceImpl");
    Rating rating = null;
    try {
      rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
    } catch (Exception exception) {
      logger.error("Error in the method getRating :" + exception.getMessage());
    }
    return rating;
  }

}
