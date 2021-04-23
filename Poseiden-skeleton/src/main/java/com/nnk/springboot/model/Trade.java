package com.nnk.springboot.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "trade")
public class Trade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String account;

  private String type;

  private double buyQuantity;

  private double sellQuantity;

  private double buyPrice;

  private double sellPrice;

  private String benchmark;

  private Timestamp tradeDate;

  private String security;

  private String status;

  private String trader;

  private String book;

  private String creationName;

  private Timestamp creationDate;

  private String revisionName;

  private Timestamp revisionDate;

  private String dealName;

  private String dealType;

  private String sourceListId;

  private String side;

}
