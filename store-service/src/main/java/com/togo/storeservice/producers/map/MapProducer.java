package com.togo.storeservice.producers.map;

import com.togo.basedomains.dto.Store.StoreDTO;
import com.togo.storeservice.models.Store;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MapProducer {

  private final KafkaTemplate<String, StoreDTO> kafkaTemplate;

  public void produce(String topic, Store store) {
      StoreDTO storeDTO = new StoreDTO();

      storeDTO.setId(store.getId());
      storeDTO.setStoreName(store.getStoreName());
      storeDTO.setCategory(store.getCategory());
      storeDTO.setLatitude(store.getLatitude());
      storeDTO.setLongitude(store.getLongitude());
      storeDTO.setCampaignStatus(store.getCampaignStatus().name());

      kafkaTemplate.send(topic, storeDTO);
  }
}
