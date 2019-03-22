package com.walmart.hack.discount.model;

import java.math.BigDecimal;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.walmart.hack.discount.deal.DealService;
import lombok.Data;

@Entity
@Table(schema = "deals")
@Where(clause = "status = true and expiration >= current_timestamp")
@EntityListeners(DealService.class)
@Data
public class Deal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private Integer store;
  private Long item;
  private Integer department;
  private Integer category;
  @Column(name = "evt")
  private Integer event;
  private Boolean status;
  private Instant expiration;

  private BigDecimal oldPrice;
  private BigDecimal newPrice;

  @CreatedDate
  @Column(updatable = false)
  private Instant created;
  @LastModifiedDate
  private Instant lastUpdated;


}
