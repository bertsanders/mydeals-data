package com.walmart.hack.discount.model;

import lombok.Data;

@Data
public class DeviceSubscription {

  private String id;
  private String deviceId;
  private Integer dept;
  private Integer catg;
  private Integer store;

}
