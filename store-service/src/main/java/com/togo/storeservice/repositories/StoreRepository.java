package com.togo.storeservice.repositories;

import com.togo.storeservice.models.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {

}
