package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.Rating;
import com.nnk.springboot.service.RatingService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RatingRestController {

  private static final Logger logger = LogManager.getLogger(RatingRestController.class);

  @Autowired
  private RatingService ratingService;

  /**
   * Read - Get all ratings
   * 
   * @return - An Iterable object of ratings full filled
   */
  @GetMapping("/ratings")
  public List<Rating> getRatings() {
    List<Rating> ratingList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'ratings'");
      ratingList = ratingService.getRatings();
      logger.info(
          "response following the GET on the endpoint 'ratings'.");
    } catch (Exception exception) {
      logger.error("Error in the RatingRestController in the method getRatings :"
          + exception.getMessage());
    }
    return ratingList;
  }

  /**
   * Add a new rating
   * 
   * @param rating An object rating
   * @return The rating object saved
   */
  @PostMapping("/rating")
  public Rating createRating(@RequestBody Rating rating) {
    Rating newRating = null;
    try {
      logger.info("Post request with the endpoint 'rating'");
      newRating = ratingService.saveRating(rating);
      logger.info(
          "response following the Post on the endpoint 'rating' with the given rating : {"
              + rating.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the RatingRestController in the method createRating :"
          + exception.getMessage());
    }
    return newRating;
  }

  /**
   * Delete - Delete a rating
   * 
   * @param id An id
   * @return - The deleted rating
   */
  @DeleteMapping("/rating")
  public Optional<Rating> deleteRating(@RequestParam Integer id) {
    Optional<Rating> rating = null;
    boolean existingRating = false;
    try {
      logger.info("Delete request with the endpoint 'rating'");
      existingRating = ratingService.ratingExist(id);
      if (existingRating) {
        rating = ratingService.deleteRating(id);
        logger.info(
            "response following the DELETE on the endpoint 'rating'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the RatingRestController in the method deleteRating :"
          + exception.getMessage());
    }
    if (!existingRating) {
      logger.error("The rating with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The rating with the id " + id + " doesn't exist.");
    }
    return rating;
  }

  /**
   * Update an existing rating from a given id
   * 
   * @param id     An id
   * @param rating A rating object with modifications
   * @return The updated rating object
   */
  @PutMapping("/rating/{id}")
  public Rating updateRating(@PathVariable("id") final Integer id,
      @RequestBody Rating rating) {
    Rating ratingToUpdate = null;
    boolean existingRatingId = false;
    try {
      logger.info(
          "Put request of the endpoint 'rating' with the id : {" + id + "}");
      existingRatingId = ratingService.ratingExist(id);
      if (existingRatingId) {
        ratingToUpdate = ratingService.getRating(id);
        logger.info(
            "response following the Put on the endpoint 'rating' with the given id : {"
                + id + "}");
        if (ratingToUpdate != null) {
          String moodysRating = rating.getMoodysRating();
          if (moodysRating != null) {
            ratingToUpdate.setMoodysRating(moodysRating);
          }
          String sandpRating = rating.getSandpRating();
          if (sandpRating != null) {
            ratingToUpdate.setSandpRating(sandpRating);
          }
          String fitchRating = rating.getFitchRating();
          if (fitchRating != null) {
            ratingToUpdate.setFitchRating(fitchRating);
          }
          Integer orderNumber = rating.getOrderNumber();
          if (orderNumber != null) {
            ratingToUpdate.setOrderNumber(orderNumber);
          }
          ratingService.saveRating(ratingToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the RatingRestController in the method updateRating :"
          + exception.getMessage());
    }
    if (!existingRatingId) {
      logger.error("The rating with the id " + id + " doesn't exist.");
      throw new NonexistentException("The rating with the id " + id + " doesn't exist.");
    }
    return ratingToUpdate;
  }

}
