package com.walmart.hack.discount.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.hack.discount.model.Device;
import com.walmart.hack.discount.model.DeviceNotFoundException;
import com.walmart.hack.discount.model.DeviceSubscription;
import com.walmart.hack.discount.model.repo.DeviceRepo;
import com.walmart.hack.discount.model.repo.DeviceSubscriptionRepo;

@Service
public class SubscriptionService {

  @Autowired
  DeviceSubscriptionRepo deviceSubscriptionRepo;

  @Autowired
  DeviceRepo deviceRepo;

  public Long addSubscription(DeviceSubscription deviceSubscription) {
    deviceRepo.findById(deviceSubscription.getToken()).orElse(deviceRepo.save(new Device(deviceSubscription.getToken(), null, null)));
    DeviceSubscription deviceSubscription1 = deviceSubscriptionRepo.save(deviceSubscription);
    return deviceSubscription1.getId();
  }

  public List<DeviceSubscription> getSubscriptionByToken(String token) {
    return deviceSubscriptionRepo.findAllByToken(token);
  }

  public Long updateSubscription(DeviceSubscription deviceSubscription) {
    DeviceSubscription deviceSubscription1 = deviceSubscriptionRepo.save(deviceSubscription);
    return deviceSubscription1.getId();
  }

  public void deleteSubscription(Long id) {
     deviceSubscriptionRepo.deleteById(id);
  }

  public List<DeviceSubscription> findMatchingSubscriptions(int store, int department, int category) {
    List<DeviceSubscription> subscriptions = deviceSubscriptionRepo.findAllByStoreAndDepartmentAndCategory(store, department, category);
    subscriptions.addAll(deviceSubscriptionRepo.findAllByStoreAndDepartmentAndCategoryIsNull(store, department));
    return subscriptions;
  }

}
