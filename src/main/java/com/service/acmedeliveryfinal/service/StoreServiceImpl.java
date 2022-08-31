package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl extends BaseServiceImpl<Store> implements StoreService{

    private final StoreRepository storeRepository;
    @Override
    public JpaRepository<Store, Long> getRepository() {return storeRepository;}
}
