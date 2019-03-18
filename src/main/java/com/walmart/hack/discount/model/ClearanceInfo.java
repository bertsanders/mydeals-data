package com.walmart.hack.discount.model;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class ClearanceInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private Integer store;
  private Long item;
  private Integer dept;
  private Integer catg;
  private Integer event;
  private Boolean status;
  private OffsetDateTime createDateTime;
  private Long ttl;

}
