package com.togo.storeservice.dtos;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

  private Integer httpCode;

  private Integer errorCode;

  private String errorMessage;

  private List<String> validationMessages;
}
