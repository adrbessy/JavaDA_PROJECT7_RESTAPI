package com.nnk.springboot.controllers;

import com.nnk.springboot.model.CurvePoint;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurvePointController {
  // TODO: Inject Curve Point service

  @Autowired
  private CurvePointRestController curvePointRestController;

  @RequestMapping("/curvePoint/list")
  public String home(Model model) {
    List<CurvePoint> curvePointList = curvePointRestController.getCurvePoints();
    model.addAttribute("curvePointList", curvePointList);
    return "curvePoint/list";
  }

  @GetMapping("/curvePoint/add")
  public String addBidForm(CurvePoint bid) {
    return "curvePoint/add";
  }

  @PostMapping("/curvePoint/validate")
  public String validate(CurvePoint curvePoint, BindingResult result, Model model) {
    // TODO: check data valid and save to db, after saving return Curve list
    return "curvePoint/add";
  }

  @GetMapping("/curvePoint/update/{id}")
  public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
    // TODO: get CurvePoint by Id and to model then show to the form
    return "curvePoint/update";
  }

  @PostMapping("/curvePoint/update/{id}")
  public String updateBid(@PathVariable("id") Integer id, CurvePoint curvePoint,
      BindingResult result, Model model) {
    // TODO: check required fields, if valid call service to update Curve and return
    // Curve list
    return "redirect:/curvePoint/list";
  }

  @GetMapping("/curvePoint/delete/{id}")
  public String deleteBid(@PathVariable("id") Integer id, Model model) {
    // TODO: Find Curve by Id and delete the Curve, return to Curve list
    return "redirect:/curvePoint/list";
  }
}
