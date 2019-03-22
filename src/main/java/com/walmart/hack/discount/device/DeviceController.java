package com.walmart.hack.discount.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.hack.discount.model.Device;

@RestController
  @RequestMapping("/device")
public class DeviceController {

  @Autowired
  DeviceService deviceService;

  @PostMapping("/")
  public Device addDevice(Device device) {
    return deviceService.addDevice(device);
  }

  @GetMapping("/")
  public Device getDevice(String token) {
    return deviceService.getDevice(token);
  }



}
