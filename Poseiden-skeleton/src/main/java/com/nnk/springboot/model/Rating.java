package com.nnk.springboot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "rating")
public class Rating {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String moodysRating;

  private String sandPRating;

  private String fitchRating;

  private Integer orderNumber;

  public Rating(String moodysRating, String sandPRating, String fitchRating, Integer id) {
    this.moodysRating = moodysRating;
    this.sandPRating = sandPRating;
    this.fitchRating = fitchRating;
    this.id = id;
  }

}
