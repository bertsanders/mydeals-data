package com.walmart.hack.discount.deal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.walmart.hack.discount.model.ClearanceInfo;

public interface DealRepository extends PagingAndSortingRepository<ClearanceInfo, Long> {

}
