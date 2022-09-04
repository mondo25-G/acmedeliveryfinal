package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.StoreItem;

import java.util.List;

public interface StoreItemService extends BaseService<StoreItem> {
    StoreItem findBySerial(String serial);
    List<StoreItem> topProductsByRanking(Integer rankingThreshold);
}