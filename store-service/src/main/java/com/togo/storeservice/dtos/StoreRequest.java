package com.togo.storeservice.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreRequest {

  private String storeName;

  private Double longitude;

  private Double latitude;

  private String category;
}
