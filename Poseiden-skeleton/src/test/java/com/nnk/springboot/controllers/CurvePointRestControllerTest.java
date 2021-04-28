package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.service.CurvePointService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointRestControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CurvePointService curvePointServiceMock;

  private CurvePoint curvePoint;

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testGetCurvePoints() throws Exception {
    mockMvc.perform(get("/curvePoints")).andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testCreateCurvePoint() throws Exception {
    curvePoint = new CurvePoint();
    curvePoint.setCurveId(1);
    curvePoint.setTerm(1);
    curvePoint.setValue(10);

    when(curvePointServiceMock.saveCurvePoint(curvePoint)).thenReturn(curvePoint);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/curvePoint")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(curvePoint));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteCurvePoint() throws Exception {
    Integer id = 1;
    curvePoint = new CurvePoint();
    CurvePoint curvePoint2 = null;

    when(curvePointServiceMock.curvePointExist(id)).thenReturn(true);
    when(curvePointServiceMock.deleteCurvePoint(id)).thenReturn(curvePoint2);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/curvePoint?id=1"))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testDeleteCurvePointIfidDoesntExist() throws Exception {
    Integer id = 1;
    curvePoint = new CurvePoint();

    when(curvePointServiceMock.curvePointExist(id)).thenReturn(false);

    mockMvc
        .perform(MockMvcRequestBuilders.delete("/curvePoint?id=1"))
        .andExpect(status().isNotFound());
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testUpdateCurvePoint() throws Exception {
    Integer id = 1;
    curvePoint = new CurvePoint();
    curvePoint.setCurveId(1);
    curvePoint.setTerm(1);
    curvePoint.setValue(10);

    when(curvePointServiceMock.curvePointExist(id)).thenReturn(true);
    when(curvePointServiceMock.getCurvePoint(id)).thenReturn(curvePoint);

    MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
        .put("/curvePoint/1")
        .contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
        .content(new ObjectMapper().writeValueAsString(curvePoint));
    this.mockMvc.perform(builder).andExpect(MockMvcResultMatchers.status().isOk());
  }

}
