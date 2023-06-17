package com.togo.storeservice.controllers;

import com.togo.basedomains.dto.Pagination.PageableDTO;
import com.togo.storeservice.dtos.StoreRequest;
import com.togo.storeservice.models.Store;
import com.togo.storeservice.services.StoreService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

  private final StoreService storeService;

  @PostMapping
  public ResponseEntity<Object> saveStore(@Valid @RequestBody StoreRequest storeRequest) {

    storeService.saveStore(storeRequest);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @PutMapping
  public ResponseEntity<Object> updateStore(@RequestParam Long storeId, @Valid @RequestBody StoreRequest storeRequest) {

    storeService.updateStore(storeId,storeRequest);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @DeleteMapping
  public ResponseEntity<Object> deleteStore(@RequestParam Long storeId) {

    storeService.deleteStore(storeId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/{storeId}")
  public ResponseEntity<Object> getStore(@PathVariable Long storeId) {

    Store store = storeService.getStoreById(storeId);

    return new ResponseEntity<>(store, HttpStatus.OK);
  }

  @GetMapping("/all")
  public ResponseEntity<Object> getAllStore(@RequestBody PageableDTO pageableDTO) {

    List<Store> stores = storeService.getAllStores(pageableDTO);

    return new ResponseEntity<>(stores, HttpStatus.OK);
  }

  @GetMapping("/approve")
  public ResponseEntity<Object> approveStore(@RequestParam Long storeId) {

    storeService.approveStore(storeId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

  @GetMapping("/reject")
  public ResponseEntity<Object> rejectStore(@RequestParam Long storeId) {

    storeService.rejectStore(storeId);

    return new ResponseEntity<>(HttpStatus.OK);
  }

}
