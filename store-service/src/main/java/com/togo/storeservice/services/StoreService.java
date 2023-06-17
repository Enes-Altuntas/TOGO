package com.togo.storeservice.services;

import com.togo.basedomains.dto.Pagination.PageableDTO;
import com.togo.storeservice.dtos.StoreRequest;
import com.togo.storeservice.models.Store;
import java.util.List;

public interface StoreService {

  void updateStore(Long storeId, StoreRequest storeRequest);

  void saveStore(StoreRequest storeRequest);

  void deleteStore(Long storeId);

  void approveStore(Long storeId);

  void rejectStore(Long storeId);

  Store getStoreById(Long storeId);

  List<Store> getAllStores(PageableDTO pageableDTO);
}
