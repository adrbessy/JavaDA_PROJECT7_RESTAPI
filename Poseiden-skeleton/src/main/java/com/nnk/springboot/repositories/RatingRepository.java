package com.nnk.springboot.repositories;

import com.nnk.springboot.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long>, JpaSpecificationExecutor<Rating> {

  void deleteById(Integer id);

  boolean existsById(Integer id);

  Rating findById(Integer id);

}
