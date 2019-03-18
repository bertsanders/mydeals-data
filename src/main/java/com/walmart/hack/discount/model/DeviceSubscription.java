package com.walmart.hack.discount.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DeviceSubscription {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private String id;
  private String deviceId;
  private Integer dept;
  private Integer catg;
  private Integer store;

}
