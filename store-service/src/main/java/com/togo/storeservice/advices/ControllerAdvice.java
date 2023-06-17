package com.togo.storeservice.advices;

import com.togo.storeservice.dtos.ErrorResponse;
import com.togo.storeservice.exceptions.BusinessNotFoundException;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

  @ExceptionHandler(BusinessNotFoundException.class)
  public ResponseEntity<ErrorResponse> businessNotFoundExceptionHandler(BusinessNotFoundException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setErrorCode(1);
    errorResponse.setHttpCode(HttpStatus.NOT_FOUND.value());
    errorResponse.setErrorMessage(e.getMessage());
    errorResponse.setValidationMessages(new ArrayList<>());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
  }
}
