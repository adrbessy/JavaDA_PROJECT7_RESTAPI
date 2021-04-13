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
   * Check if the given bid id exists.
   * 
   * @param id The bid id
   * @return true if the id exists, otherwise returns false
   */
  boolean curvePointExist(Integer id);

  /**
   * Delete a bid
   * 
   * @param id An id
   * @return the deleted bid
   */
  Optional<CurvePoint> deleteCurvePoint(Integer id);

  /**
   * Get a bid from an id
   * 
   * @param id The id of the bid table
   * @return The bid
   */
  CurvePoint getCurvePoint(Integer id);

}
