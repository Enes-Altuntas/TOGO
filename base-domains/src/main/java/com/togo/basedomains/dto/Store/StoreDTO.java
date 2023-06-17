package com.togo.basedomains.dto.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StoreDTO {

  private Long id;

  private String storeName;

  private Double longitude;

  private Double latitude;

  private String category;

  private String campaignStatus;

}
