package com.nnk.springboot.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "curve_point")
public class CurvePoint {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private Integer curveId;

  private Timestamp asOfDate;

  private double term;

  private double value;

  private Timestamp creationDate;

  public CurvePoint(Integer id, double term, double value) {
    this.id = id;
    this.term = term;
    this.value = value;
  }

}
