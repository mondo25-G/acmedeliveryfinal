package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;

import java.util.List;


public interface StoreService extends BaseService<Store>{
    Store getLazy(Long id);

    List<Store> getLazyAll();
}
