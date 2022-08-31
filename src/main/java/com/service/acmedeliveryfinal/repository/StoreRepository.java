package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store,Long> {
}
