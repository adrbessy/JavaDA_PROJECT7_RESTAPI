package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

  @Override
  void deleteById(Integer id);

  @Override
  boolean existsById(Integer id);

}
