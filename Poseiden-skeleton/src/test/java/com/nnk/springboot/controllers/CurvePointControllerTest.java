package com.nnk.springboot.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import com.nnk.springboot.model.CurvePoint;
import com.nnk.springboot.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class CurvePointControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CurvePointRestController curvePointRestControllerMock;
  @MockBean
  private UserRepository userRepositoryMock;


  @Test
  @WithMockUser(roles = "ADMIN")
  public void testHome() throws Exception {
    List<CurvePoint> curvePoints = new ArrayList<>();

    when(curvePointRestControllerMock.getCurvePoints())
        .thenReturn(curvePoints);

    mockMvc.perform(get("/curvePoint/list"))
        .andExpect(status().isOk()).andExpect(view().name("curvePoint/list"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testAddCurvePointForm() throws Exception {
    mockMvc.perform(get("/curvePoint/add"))
        .andExpect(status().isOk()).andExpect(view().name("curvePoint/add"));
  }

  @Test
  @WithMockUser(roles = "ADMIN")
  public void testValidate() throws Exception {
    CurvePoint curvePoint = new CurvePoint();

    when(curvePointRestControllerMock.createCurvePoint(curvePoint)).thenReturn(curvePoint);

    mockMvc
        .perform(post("/curvePoint/validate").contentType("text/html;charset=UTF-8").sessionAttr("curvePoint",
            curvePoint))
        .andExpect(status().is3xxRedirection());
  }


}
