package com.togo.storeservice.models;

import com.togo.storeservice.models.entity.ApprovalStatus;
import com.togo.storeservice.models.entity.CampaignStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "STORE")
@Table
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Store {

  @Id
  @Column(name = "ID", nullable = false, updatable = false)
  @SequenceGenerator(name = "GEN_STORE", sequenceName = "SEQ_STORE", allocationSize = 1)
  @GeneratedValue(generator = "GEN_STORE", strategy = GenerationType.SEQUENCE)
  private Long id;

  @Column(name = "STORE_NAME")
  private String storeName;

  @Column(name = "LONGITUDE")
  private Double longitude;

  @Column(name = "LATITUDE")
  private Double latitude;

  @Column(name = "CATEGORY")
  private String category;

  @Column(name = "CAMPAIGN_STATUS")
  @Enumerated(EnumType.STRING)
  private CampaignStatus campaignStatus;

  @Column(name = "APPROVAL_STATUS")
  @Enumerated(EnumType.STRING)
  private ApprovalStatus approvalStatus;

}
