package com.nnk.springboot.service;

import com.nnk.springboot.model.Rating;
import java.util.List;
import java.util.Optional;

public interface RatingService {

  List<Rating> getRatings();

  Rating saveRating(Rating rating);

  boolean ratingExist(Integer id);

  Optional<Rating> deleteRating(Integer id);

  Rating getRating(Integer id);

}
