package com.walmart.hack.discount.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.walmart.hack.discount.model.Device;
import com.walmart.hack.discount.model.NotFoundException;
import com.walmart.hack.discount.model.repos.DeviceRepo;

@Service
public class DeviceService {

  @Autowired
   DeviceRepo deviceRepo;

  public String addDevice(Device device){
    Device deviceCreated = deviceRepo.save(device);
    return deviceCreated.getDeviceId();
  }

  public Device getDevice(String deviceId) {
    Device device =  deviceRepo.findById(deviceId).orElseThrow(() -> new NotFoundException("Unable to find deviceId:"+ deviceId));
    return device;
  }
}


