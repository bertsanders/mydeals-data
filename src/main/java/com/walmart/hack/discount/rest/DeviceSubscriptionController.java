package com.walmart.hack.discount.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.hack.discount.model.Device;
import com.walmart.hack.discount.model.DeviceSubscription;
import com.walmart.hack.discount.service.SubscriptionService;

@RestController
@RequestMapping("/subscription")
public class DeviceSubscriptionController {

  @Autowired
  SubscriptionService subscriptionService;

  @PostMapping("/")
  public Long addDevice(DeviceSubscription deviceSubscription) {
    return subscriptionService.addSubscription(deviceSubscription);
  }

  @GetMapping("/token/{token}")
  public List<DeviceSubscription> getSubcriptions(@PathVariable("token") String token) {
    return subscriptionService.getSubscriptionByToken(token);
  }

  @PutMapping("/")
  public Long updateSubscriptions(DeviceSubscription deviceSubscription) {
    return subscriptionService.addSubscription(deviceSubscription);
  }

  @DeleteMapping
  public void deleteSubscriptions(Long id) {
    subscriptionService.deleteSubscription(id);
  }
}
