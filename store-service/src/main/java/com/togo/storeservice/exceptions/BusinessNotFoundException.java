package com.togo.storeservice.exceptions;

public class BusinessNotFoundException extends RuntimeException{

  public BusinessNotFoundException(String message) {
    super(message);
  }
}
