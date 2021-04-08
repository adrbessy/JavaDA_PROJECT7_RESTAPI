package com.nnk.springboot.domain;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "bid")
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer bidId;

  private String account;

  private String type;

  private double bidQuantity;

  private double askQuantity;

  private double bid;

  private double ask;

  private String benchMark;

  private Timestamp bidListDate;

  private String commentary;

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

  private String sid;

}
