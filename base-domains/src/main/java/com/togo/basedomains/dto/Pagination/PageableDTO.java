package com.togo.basedomains.dto.Pagination;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageableDTO {

  private int pageNumber;

  private int pageSize;

  private String sortProperty;
}
