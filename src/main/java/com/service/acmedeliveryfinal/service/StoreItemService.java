package com.service.acmedeliveryfinal.service;

import com.service.acmedeliveryfinal.domain.Store;
import com.service.acmedeliveryfinal.domain.StoreItem;

import java.util.List;

public interface StoreItemService extends BaseService<StoreItem> {


    List<StoreItem> getMenu(Store store);    //By The Order of Mister G.Prassas I Present you the new Method
    StoreItem findBySerial(String serial);
    List<StoreItem> topProductsByRanking(Integer rankingThreshold);
}