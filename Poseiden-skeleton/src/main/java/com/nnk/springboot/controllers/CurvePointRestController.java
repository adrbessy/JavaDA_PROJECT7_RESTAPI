package com.nnk.springboot.controllers;

import com.nnk.springboot.exceptions.IsForbiddenException;
import com.nnk.springboot.exceptions.NonexistentException;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import java.util.ArrayList;
import java.util.List;
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
public class CurvePointRestController {

  private static final Logger logger = LogManager.getLogger(CurvePointRestController.class);

  @Autowired
  private CurvePointService curvePointService;

  /**
   * Read - Get all curve points
   * 
   * @return - An Iterable object of curve points full filled
   */
  @GetMapping("/curvePoints")
  public List<CurvePoint> getCurvePoints() {
    List<CurvePoint> curvePointList = new ArrayList<>();
    try {
      logger.info("Get request with the endpoint 'curvePoints'");
      curvePointList = curvePointService.getCurvePoints();
      logger.info(
          "response following the GET on the endpoint 'curvePoints'.");
    } catch (Exception exception) {
      logger.error("Error in the CurvePointRestController in the method getCurvePoints :"
          + exception.getMessage());
    }
    return curvePointList;
  }

  /**
   * Add a new curve point
   * 
   * @param curvePoint An object curve point
   * @return The curvePoint object saved
   */
  @PostMapping("/curvePoint")
  public CurvePoint createCurvePoint(@RequestBody CurvePoint curvePoint) {
    CurvePoint newCurvePoint = null;
    if (curvePoint.getCurveId() == null) {
      logger.error("The new curve point has to get a curve id.");
      throw new IsForbiddenException(
          "The new curve point has to get a curve id.");
    }
    try {
      logger.info("Post request with the endpoint 'curvePoint'");
      newCurvePoint = curvePointService.saveCurvePoint(curvePoint);
      logger.info(
          "response following the Post on the endpoint 'curvePoint' with the given curvePoint : {"
              + curvePoint.toString() + "}");
    } catch (Exception exception) {
      logger.error("Error in the CurvePointRestController in the method createCurvePoint :"
          + exception.getMessage());
    }
    return newCurvePoint;
  }

  /**
   * Delete - Delete a curve Point
   * 
   * @param id An id
   * @return - The deleted curve point
   */
  @DeleteMapping("/curvePoint")
  public CurvePoint deleteCurvePoint(@RequestParam Integer id) {
    CurvePoint curvePoint = null;
    boolean existingCurvePoint = false;
    try {
      logger.info("Delete request with the endpoint 'curvePoint'");
      existingCurvePoint = curvePointService.curvePointExist(id);
      if (existingCurvePoint) {
        curvePoint = curvePointService.deleteCurvePoint(id);
        logger.info(
            "response following the DELETE on the endpoint 'curvePoint'.");
      }
    } catch (Exception exception) {
      logger.error("Error in the CurvePointRestController in the method deleteCurvePoint :"
          + exception.getMessage());
    }
    if (!existingCurvePoint) {
      logger.error("The curvePoint with the id " + id + " doesn't exist.");
      throw new NonexistentException(
          "The curvePoint with the id " + id + " doesn't exist.");
    }
    return curvePoint;
  }

  /**
   * Update an existing curve point from a given id
   * 
   * @param id         An id
   * @param curvePoint A curvePoint object with modifications
   * @return The updated curvePoint object
   */
  @PutMapping("/curvePoint/{id}")
  public CurvePoint updateCurvePoint(@PathVariable("id") final Integer id,
      @RequestBody CurvePoint curvePoint) {
    CurvePoint curvePointToUpdate = null;
    boolean existingCurvePointId = false;
    try {
      logger.info(
          "Put request of the endpoint 'curvePoint' with the id : {" + id + "}");
      existingCurvePointId = curvePointService.curvePointExist(id);
      if (existingCurvePointId) {
        curvePointToUpdate = curvePointService.getCurvePoint(id);
        logger.info(
            "response following the Put on the endpoint 'curvePoint' with the given id : {"
                + id + "}");
        if (curvePointToUpdate != null) {
          Integer curveId = curvePoint.getCurveId();
          if (curveId != null) {
            curvePointToUpdate.setCurveId(curveId);
          }
          double term = curvePoint.getTerm();
          if (term != 0) {
            curvePointToUpdate.setTerm(term);
          }
          double value = curvePoint.getValue();
          if (value != 0) {
            curvePointToUpdate.setValue(value);
          }
          curvePointService.saveCurvePoint(curvePointToUpdate);
        }
      }
    } catch (Exception exception) {
      logger.error("Error in the CurvePointRestController in the method updateCurvePoint :"
          + exception.getMessage());
    }
    if (!existingCurvePointId) {
      logger.error("The curve point with the id " + id + " doesn't exist.");
      throw new NonexistentException("The curve point with the id " + id + " doesn't exist.");
    }
    return curvePointToUpdate;
  }

}
