package com.walmart.hack.discount.deal;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.walmart.hack.discount.model.Deal;

public interface DealRepository extends PagingAndSortingRepository<Deal, Long> {

}
