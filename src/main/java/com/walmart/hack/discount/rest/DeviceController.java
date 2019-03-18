package com.walmart.hack.discount.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.hack.discount.model.Device;
import com.walmart.hack.discount.service.DeviceService;

@RestController
@RequestMapping("/device")
public class DeviceController {

  @Autowired
  DeviceService deviceService;

  @PostMapping("/register")
  public String addDevice(Device device) {
    return deviceService.addDevice(device);
  }

  @GetMapping()
  public Device getDevice(String deviceId) {
    return deviceService.getDevice(deviceId);
  }

}
