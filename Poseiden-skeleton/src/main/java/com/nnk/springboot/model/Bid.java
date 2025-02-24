package com.nnk.springboot.model;

import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "bid")
public class Bid {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @NotBlank(message = "Account is mandatory")
  private String account;

  @NotBlank(message = "Type is mandatory")
  private String type;

  private double bidQuantity;

  private double askQuantity;

  private double bid;

  private double ask;

  private String benchmark;

  private Timestamp bidDate;

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

  private String sourceId;

  private String side;

  /*
   * public Bid(String account, String type, double bidQuantity) { this.account =
   * account; this.type = type; this.bidQuantity = bidQuantity; }
   */

}
