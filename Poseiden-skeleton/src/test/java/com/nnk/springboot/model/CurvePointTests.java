package com.nnk.springboot.model;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import com.nnk.springboot.repositories.CurvePointRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

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
    assertNotNull(curvePoint.getId());
    assertTrue(curvePoint.getCurveId() == 10);

    // Update
    curvePoint.setCurveId(20);
    curvePoint = curvePointRepository.save(curvePoint);
    assertTrue(curvePoint.getCurveId() == 20);

    // Find
    List<CurvePoint> listResult = curvePointRepository.findAll();
    assertTrue(listResult.size() > 0);

    // Delete
    Integer id = curvePoint.getId();
    curvePointRepository.delete(curvePoint);
    CurvePoint curvePointList = curvePointRepository.findById(id);
    assertNull(curvePointList);
  }

  @Test
  public void simpleEqualsCurvePoint() {
    EqualsVerifier.forClass(CurvePoint.class).suppress(Warning.ALL_FIELDS_SHOULD_BE_USED).verify();
  }

}
