package com.walmart.hack.discount.deal;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.hack.discount.model.ClearanceInfo;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/deal")
public class DealController {

  @NonNull
  private DealRepository dealRepository;

  @PostMapping("/")
  public ClearanceInfo createDeal(ClearanceInfo clearanceInfo) {
    return dealRepository.save(clearanceInfo);
  }

}
