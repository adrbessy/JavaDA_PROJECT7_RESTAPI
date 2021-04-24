package com.nnk.springboot.service;

import com.nnk.springboot.model.Rating;
import java.util.List;

public interface RatingService {

  /**
   * Get all ratings
   * 
   * @return all ratings
   */
  List<Rating> getRatings();

  /**
   * Save a rating
   * 
   * @param rating A rating to save
   * @return the saved rating
   */
  Rating saveRating(Rating rating);

  /**
   * Check if the given rating id exists.
   * 
   * @param id The rating id
   * @return true if the id exists, otherwise returns false
   */
  boolean ratingExist(Integer id);

  /**
   * Delete a rating
   * 
   * @param id An id
   * @return the deleted rating
   */
  Rating deleteRating(Integer id);

  /**
   * Get a rating from an id
   * 
   * @param id The id of the rating table
   * @return The bid
   */
  Rating getRating(Integer id);

}
