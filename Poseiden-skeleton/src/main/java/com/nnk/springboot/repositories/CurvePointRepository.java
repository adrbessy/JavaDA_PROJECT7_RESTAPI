package com.nnk.springboot.repositories;

import com.nnk.springboot.model.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Long> {

  void deleteById(Integer id);

  CurvePoint findById(Integer id);

  boolean existsById(Integer id);

}
