package com.nnk.springboot.service;

import com.nnk.springboot.model.CurvePoint;
import java.util.List;
import java.util.Optional;

public interface CurvePointService {

  /**
   * Get all curvePoints
   * 
   * @return all curvePoints
   */
  List<CurvePoint> getCurvePoints();

  /**
   * Save a curve point
   * 
   * @param curvePoint A curve point to save
   * @return the saved curve point
   */
  CurvePoint saveCurvePoint(CurvePoint curvePoint);

  /**
   * Check if the given curve point id exists.
   * 
   * @param id The curve point id
   * @return true if the id exists, otherwise returns false
   */
  boolean curvePointExist(Integer id);

  /**
   * Delete a curve point
   * 
   * @param id An id
   * @return the deleted curve point
   */
  Optional<CurvePoint> deleteCurvePoint(Integer id);

  /**
   * Get a curve point from an id
   * 
   * @param id The id of the curve point table
   * @return The curve point
   */
  CurvePoint getCurvePoint(Integer id);

}
