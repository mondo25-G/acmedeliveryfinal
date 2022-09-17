package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem , Long> {
}
