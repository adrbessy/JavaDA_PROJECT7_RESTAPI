package com.nnk.springboot.repositories;

import com.nnk.springboot.model.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Long>, JpaSpecificationExecutor<CurvePoint> {

  void deleteById(Integer id);

  boolean existsById(Integer id);

  CurvePoint findById(Integer id);

}
