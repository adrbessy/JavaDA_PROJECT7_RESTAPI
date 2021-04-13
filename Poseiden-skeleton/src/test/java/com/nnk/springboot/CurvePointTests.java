package com.nnk.springboot;

import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import java.util.Optional;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CurvePointTests {

  private CurvePoint curvePoint;

  @Autowired
  private CurvePointRepository curvePointRepository;

  @BeforeEach
  private void setUp() {
    curvePoint = new CurvePoint();
    curvePoint.setCurveId(10);
    curvePoint.setTerm(10d);
    curvePoint.setValue(30d);
  }

  @Test
  public void curvePointTest() {
    // Save
    curvePoint = curvePointRepository.save(curvePoint);
    Assert.assertNotNull(curvePoint.getId());
    Assert.assertTrue(curvePoint.getCurveId() == 10);

    // Update
    curvePoint.setCurveId(20);
    curvePoint = curvePointRepository.save(curvePoint);
    Assert.assertTrue(curvePoint.getCurveId() == 20);

    // Find
    List<CurvePoint> listResult = curvePointRepository.findAll();
    Assert.assertTrue(listResult.size() > 0);

    // Delete
    Integer id = curvePoint.getId();
    curvePointRepository.delete(curvePoint);
    Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
    Assert.assertFalse(curvePointList.isPresent());
  }

}
