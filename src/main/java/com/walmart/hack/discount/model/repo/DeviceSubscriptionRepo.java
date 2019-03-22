package com.walmart.hack.discount.model.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.walmart.hack.discount.model.DeviceSubscription;

public interface DeviceSubscriptionRepo extends CrudRepository<DeviceSubscription, Long> {

  List<DeviceSubscription> findAllByToken(String token);

}
