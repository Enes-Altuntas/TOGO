package com.togo.mapservice.service;

import com.togo.mapservice.model.Marker;
import java.util.List;

public interface MarkerService {

  List<Marker> getMarkers(String searchTerm, double lat, double lon, String rad);
}
