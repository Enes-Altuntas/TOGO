package com.togo.mapservice.service.impl;

import com.togo.basedomains.dto.Store.StoreDTO;
import com.togo.mapservice.model.Marker;
import com.togo.mapservice.repository.MarkerRepository;
import com.togo.mapservice.service.MarkerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarkerServiceImpl implements MarkerService {

  private final MarkerRepository markerRepository;

  @Override
  public List<Marker> getMarkers(String searchTerm, double lat, double lon, String rad) {

    return markerRepository.findByCategoryOrStoreName(searchTerm,lat,lon,rad);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topic.save}", groupId = "${spring.kafka.consumer.group-id}")
  private void saveMarker(StoreDTO storeDTO) {
    GeoPoint location = new GeoPoint(storeDTO.getLatitude(), storeDTO.getLongitude());

    Marker marker = new Marker();
    marker.setId(storeDTO.getId());
    marker.setLocation(location);
    marker.setStoreName(storeDTO.getStoreName());
    marker.setCategory(storeDTO.getCategory());
    marker.setCampaignStatus(storeDTO.getCampaignStatus());

    markerRepository.save(marker);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topic.update}", groupId = "${spring.kafka.consumer.group-id}")
  private void updateMarker(StoreDTO storeDTO) {
    Marker marker = markerRepository.findById(storeDTO.getId()).orElseThrow();

    GeoPoint location = new GeoPoint(storeDTO.getLatitude(), storeDTO.getLongitude());

    marker.setId(storeDTO.getId());
    marker.setStoreName(storeDTO.getStoreName());
    marker.setCategory(storeDTO.getCategory());
    marker.setLocation(location);

    markerRepository.save(marker);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topic.status}",
      groupId = "${spring.kafka.consumer.group-id}")
  private void changeMarkerStatus(StoreDTO storeDTO) {
    Marker marker = markerRepository.findById(storeDTO.getId()).orElseThrow();

    marker.setCampaignStatus(storeDTO.getCampaignStatus());

    markerRepository.save(marker);
  }

  @KafkaListener(topics = "${spring.kafka.consumer.topic.delete}",
      groupId = "${spring.kafka.consumer.group-id}")
  private void deleteMarker(StoreDTO storeDTO) {
    Marker marker = markerRepository.findById(storeDTO.getId()).orElseThrow();

    markerRepository.delete(marker);
  }
}
