package com.togo.mapservice.controller;

import com.togo.mapservice.model.Marker;
import com.togo.mapservice.service.MarkerService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/markers")
@RequiredArgsConstructor
public class MarkerController {

  private final MarkerService markerService;

  @GetMapping
  public ResponseEntity<List<Marker>> getMarkers(
      @RequestParam String search,
      @RequestParam double lat,
      @RequestParam double lon,
      @RequestParam String rad) {

    return new ResponseEntity<>(markerService.getMarkers(search,lat,lon,rad), HttpStatus.OK);
  }
}
