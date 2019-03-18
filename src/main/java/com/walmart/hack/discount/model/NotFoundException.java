package com.walmart.hack.discount.model;

public class NotFoundException extends RuntimeException {

  public NotFoundException(String msg) {

    super(msg);
  }
}