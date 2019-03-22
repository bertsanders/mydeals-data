package com.walmart.hack.discount.subscription;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.walmart.hack.discount.model.DeviceSubscription;

public interface DeviceSubscriptionRepo extends CrudRepository<DeviceSubscription, Long> {

  List<DeviceSubscription> findAllByToken(String token);

  List<DeviceSubscription> findAllByStoreAndDepartmentAndCategoryIsNull(int store, int department);

  List<DeviceSubscription> findAllByStoreAndDepartmentAndCategory(int store, int department, int category);

}
