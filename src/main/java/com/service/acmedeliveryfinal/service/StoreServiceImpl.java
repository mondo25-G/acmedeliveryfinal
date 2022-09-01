package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Product;
import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private final StoreRepository storeRepository;
    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}


    public List<Store> getStoresByName(String name){
        List<Store> storesByName = storeRepository.getStoresByName(name);
        return storesByName;
    }

    public List<Store> getStoresByCategory(StoreCategory category){
        List<Store> storesByName = storeRepository.getStoresByCategory(category.toString());
        return storesByName;
    }
}
