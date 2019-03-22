package com.walmart.hack.discount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.hack.discount.model.Device;
import com.walmart.hack.discount.model.NotFoundException;
import com.walmart.hack.discount.model.repo.DeviceRepo;

@Service
public class DeviceService {

  @Autowired
  DeviceRepo deviceRepo;

  public Device addDevice(Device device) {
    Device deviceCreated = deviceRepo.save(device);
    return deviceCreated;
  }

  public Device getDevice(String token) {
    Device device = deviceRepo.findById(token).orElseThrow(() -> new NotFoundException("Unable to find deviceId:" + token));
    return device;
  }
}


