package com.walmart.hack.discount.deal;


import javax.persistence.PostPersist;

import org.springframework.stereotype.Component;

import com.walmart.hack.discount.model.Deal;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class DealListener {

  @PostPersist
  public void dealPersisted(Deal deal) {
    log.info("PostPersist: " + deal.toString());
  }

}
