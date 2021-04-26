package com.nnk.springboot.repositories;

import com.nnk.springboot.model.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

  @Override
  void deleteById(Integer id);

  @Override
  boolean existsById(Integer id);

}
