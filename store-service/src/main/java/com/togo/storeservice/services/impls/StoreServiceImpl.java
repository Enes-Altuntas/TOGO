package com.togo.storeservice.services.impls;

import com.togo.basedomains.dto.Pagination.PageableDTO;
import com.togo.storeservice.dtos.StoreRequest;
import com.togo.storeservice.exceptions.BusinessNotFoundException;
import com.togo.storeservice.models.Store;
import com.togo.storeservice.models.entity.ApprovalStatus;
import com.togo.storeservice.models.entity.CampaignStatus;
import com.togo.storeservice.producers.map.MapProducer;
import com.togo.storeservice.repositories.StoreRepository;
import com.togo.storeservice.services.StoreService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

  private final StoreRepository storeRepository;

  private final MapProducer mapProducer;

  @Value("${spring.kafka.producer.topic.save}")
  private String saveMarkerTopic;

  @Value("${spring.kafka.producer.topic.update}")
  private String updateMarkerTopic;

  @Value("${spring.kafka.producer.topic.delete}")
  private String deleteMarkerTopic;

  @Override
  public void saveStore(StoreRequest storeRequest) {
    Store store = new Store();

    store.setStoreName(storeRequest.getStoreName());
    store.setCategory(storeRequest.getCategory());
    store.setLatitude(storeRequest.getLatitude());
    store.setLongitude(storeRequest.getLongitude());
    store.setCampaignStatus(CampaignStatus.INACTIVE);
    store.setApprovalStatus(ApprovalStatus.WAITING);

    storeRepository.save(store);
  }

  @Override
  public void updateStore(Long storeId, StoreRequest storeRequest) {

    Store store = getStoreById(storeId);

    store.setStoreName(storeRequest.getStoreName());
    store.setCategory(storeRequest.getCategory());
    store.setLatitude(storeRequest.getLatitude());
    store.setLongitude(storeRequest.getLongitude());

    Store updatedStore = storeRepository.save(store);

    /*mapProducer.produce(updateMarkerTopic, updatedStore);*/
  }

  @Override
  public void deleteStore(Long storeId) {

    Store store = getStoreById(storeId);

    storeRepository.delete(store);

    /*mapProducer.produce(deleteMarkerTopic, store);*/
  }

  @Override
  public void approveStore(Long storeId) {

    Store store = getStoreById(storeId);

    store.setApprovalStatus(ApprovalStatus.APPROVED);

    Store approvedStore = storeRepository.save(store);

    /*mapProducer.produce(saveMarkerTopic, approvedStore);*/
  }

  @Override
  public void rejectStore(Long storeId) {

    Store store = getStoreById(storeId);

    store.setApprovalStatus(ApprovalStatus.REJECTED);

    Store approvedStore = storeRepository.save(store);

    //TODO Mail Service
  }

  @Override
  public Store getStoreById(Long storeId) {

    return storeRepository.findById(storeId).orElseThrow(() ->
        new BusinessNotFoundException(String.format("Store couldn't found with id: %d",storeId)));
  }

  @Override
  public List<Store> getAllStores(PageableDTO pageableDTO) {

    Pageable sortedByName =
        PageRequest.of(
            pageableDTO.getPageNumber(),
            pageableDTO.getPageSize(),
            Sort.by(pageableDTO.getSortProperty()));

    return storeRepository.findAll(sortedByName).getContent();
  }
}
