package com.nnk.springboot.service;

import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CurvePointServiceImpl implements CurvePointService {

  private static final Logger logger = LogManager.getLogger(CurvePointServiceImpl.class);

  @Autowired
  private CurvePointRepository curvePointRepository;

  /**
   * Get all curvePoints
   * 
   * @return all curvePoints
   */
  @Override
  public List<CurvePoint> getCurvePoints() {
    logger.debug("in the method getCurvePoints in the class CurvePointServiceImpl");
    List<CurvePoint> curvePointList = new ArrayList<>();
    try {
      curvePointList = curvePointRepository.findAll();
    } catch (Exception exception) {
      logger.error("Error in the method getcurvePoints :" + exception.getMessage());
    }
    return curvePointList;
  }

  /**
   * Save a curve point
   * 
   * @param curvePoint A curve point to save
   * @return the saved curve point
   */
  @Override
  public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
    logger.debug("in the method saveCurvePoint in the class CurvePointServiceImpl");
    CurvePoint savedCurvePoint = null;
    try {
      savedCurvePoint = curvePointRepository.save(curvePoint);
    } catch (Exception exception) {
      logger.error("Error when we try to save a curve point :" + exception.getMessage());
    }
    return savedCurvePoint;
  }

  /**
   * Check if the given curve point id exists.
   * 
   * @param id The curve point id
   * @return true if the id exists, otherwise returns false
   */
  @Override
  public boolean curvePointExist(Integer id) {
    logger.debug("in the method curvePointExist in the class CurvePointServiceImpl");
    boolean curvePointExist = false;
    try {
      curvePointExist = curvePointRepository.existsById(id);
    } catch (Exception exception) {
      logger.error("Error in the method curvePointExist :" + exception.getMessage());
    }
    return curvePointExist;
  }

  /**
   * Delete a curve point
   * 
   * @param id An id
   * @return the deleted curve point
   */
  @Override
  @Transactional
  public CurvePoint deleteCurvePoint(Integer id) {
    logger.debug("in the method deleteCurvePoint in the class CurvePointServiceImpl");
    CurvePoint curvePoint = null;
    try {
      curvePoint = curvePointRepository.findById(id);
      curvePointRepository.deleteById(id);
    } catch (Exception exception) {
      logger.error("Error in the method deleteCurvePoint :" + exception.getMessage());
    }
    return curvePoint;
  }

  /**
   * Get a curve point from an id
   * 
   * @param id The id of the curve point table
   * @return The curve point
   */
  @Override
  public CurvePoint getCurvePoint(Integer id) {
    logger.debug("in the method getCurvePoint in the class CurvePointServiceImpl");
    CurvePoint curvePoint = null;
    try {
      curvePoint = curvePointRepository.findById(id);
    } catch (Exception exception) {
      logger.error("Error in the method getCurvePoint :" + exception.getMessage());
    }
    return curvePoint;
  }

}
