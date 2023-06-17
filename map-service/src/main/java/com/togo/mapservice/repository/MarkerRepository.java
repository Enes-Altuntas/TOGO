package com.togo.mapservice.repository;

import com.togo.mapservice.model.Marker;
import java.util.List;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface MarkerRepository extends ElasticsearchRepository<Marker, Long> {

  @Query("{\"bool\":{\"must\":[{\"bool\":{\"should\":[{\"match\":{\"category\":\"?0\"}},{\"match\":{\"storeName\":\"?0\"}},{\"match\":{\"campaignStatus\":\"?0\"}}]}},{\"geo_distance\":{\"distance\":\"?3\",\"location\":{\"lat\":?1,\"lon\":?2}}}]}}")
  List<Marker> findByCategoryOrStoreName(String searchTerm, double lat, double lon, String rad);
}
