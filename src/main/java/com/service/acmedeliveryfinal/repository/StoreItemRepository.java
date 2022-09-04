package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem , Long> {
    List<StoreItem> findByStore(Store store);

    StoreItem findBySerial(String serial);
    @Query(value = "SELECT * FROM Product ORDER BY NAME ASC FETCH FIRST 10 ROWS ONLY", nativeQuery = true)
    List<StoreItem> topProductsByRanking(Integer rankingThreshold);

}
