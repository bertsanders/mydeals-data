package com.walmart.hack.discount.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class ClearanceInfo {

  private String id;
  private Integer store;
  private Long item;
  private Integer dept;
  private Integer catg;
  private Integer event;
  private Boolean status;
  private OffsetDateTime createDateTime;
  private Long ttl;

}
