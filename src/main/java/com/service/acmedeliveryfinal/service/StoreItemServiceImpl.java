package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.StoreItem;
import com.service.acmedeliveryfinal.repository.StoreItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreItemServiceImpl extends BaseServiceImpl<StoreItem> implements StoreItemService {

    private final StoreItemRepository storeItemRepository;

    @Override
    public JpaRepository<StoreItem , Long> getRepository(){
        return storeItemRepository;
    }

    @Override
    public StoreItem findBySerial(final String serial){
        return storeItemRepository.findBySerial(serial);
    }

    public List<StoreItem> topProductsByRanking(Integer rankingThreshold){
        List<StoreItem> topProducts = storeItemRepository.topProductsByRanking(rankingThreshold);
        return topProducts;
    }
}