package com.walmart.hack.discount.deal;

import java.time.Instant;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.hack.discount.model.Deal;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deal")
public class DealController {
  @NonNull
  private DealService dealService;

  @PostMapping("/")
  public Deal createDeal(@RequestBody Deal deal, @RequestParam("timeToLive") long timeToLiveSeconds) {
    deal.setExpiration(Instant.now().plusSeconds(timeToLiveSeconds));
    return dealService.createDeal(deal, timeToLiveSeconds);
  }

  @GetMapping("/")
  public Page<Deal> getAllDeals(Pageable pageable) {
    return dealService.getAllDeals(pageable);
  }
}
