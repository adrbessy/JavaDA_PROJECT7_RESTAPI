package com.nnk.springboot.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest()
public class CurvePointServiceImplTest {

  @Autowired
  private CurvePointService curvePointService;

  @MockBean
  private CurvePointRepository curvePointRepositoryMock;

  private CurvePoint curvePoint;

  /**
   * test to get all the curvePoints.
   * 
   */
  @Test
  public void testGetCurvePoints() {
    curvePoint = new CurvePoint();
    List<CurvePoint> curvePointList = new ArrayList<>();
    curvePointList.add(curvePoint);

    when(curvePointRepositoryMock.findAll()).thenReturn(curvePointList);

    List<CurvePoint> result = curvePointService.getCurvePoints();
    assertThat(result).isEqualTo(curvePointList);
  }

  /**
   * test to save a curvePoint
   * 
   */
  @Test
  public void testSaveCurvePoint() {
    curvePoint = new CurvePoint();

    when(curvePointRepositoryMock.save(curvePoint)).thenReturn(curvePoint);

    CurvePoint result = curvePointService.saveCurvePoint(curvePoint);
    assertThat(result).isEqualTo(curvePoint);
  }

  /**
   * test to know if a curvePoint exists.
   * 
   */
  @Test
  public void testCurvePointExist() {
    Integer id = 1;

    when(curvePointRepositoryMock.existsById(id)).thenReturn(true);

    boolean result = curvePointService.curvePointExist(id);
    assertTrue(result);
  }


  /**
   * test to delete a curvePoint.
   * 
   */
  @Test
  public void testDeleteCurvePoint() {
    Integer id = 1;
    curvePoint = new CurvePoint();

    when(curvePointRepositoryMock.findById(id)).thenReturn(curvePoint);
    doNothing().when(curvePointRepositoryMock).deleteById(id);

    CurvePoint result = curvePointService.deleteCurvePoint(id);
    assertThat(result).isEqualTo(curvePoint);
  }

  /**
   * test to get a curvePoint.
   * 
   */
  @Test
  public void testGetCurvePoint() {
    Integer id = 1;
    curvePoint = new CurvePoint();

    when(curvePointRepositoryMock.findById(id)).thenReturn(curvePoint);

    assertThat(curvePointService.getCurvePoint(id)).isEqualTo(curvePoint);
  }

}
