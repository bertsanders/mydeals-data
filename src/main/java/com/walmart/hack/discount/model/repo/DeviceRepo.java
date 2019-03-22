package com.walmart.hack.discount.model.repo;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.walmart.hack.discount.model.Device;

public interface DeviceRepo extends CrudRepository<Device, String> {

  Optional<Device> findById(String token);

}