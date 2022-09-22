package com.service.acmedeliveryfinal.repository;

import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StoreItemRepository extends JpaRepository<StoreItem , Long> {

    @Query(name="StoreItem.findTop10Products",nativeQuery = true)
    List<KeyValue<Long,String>> top10StoreItemsBasedOnOrderLines();
}
