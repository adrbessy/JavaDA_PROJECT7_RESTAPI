package com.nnk.springboot.model;

import com.sun.istack.NotNull;
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
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotNull
  private Integer curveId;

  private Timestamp asOfDate;

  private double term;

  private double value;

  private Timestamp creationDate;

}
