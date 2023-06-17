package com.togo.mapservice.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

@Document(indexName = "marker")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Marker {

  @Id
  private Long id;

  @Field(type = FieldType.Text, name = "storeName")
  private String storeName;

  @Field(type = FieldType.Double, name = "location")
  private GeoPoint location;

  @Field(type = FieldType.Text, name = "category")
  private String category;

  @Field(type = FieldType.Text, name = "campaignStatus")
  private String campaignStatus;
}
