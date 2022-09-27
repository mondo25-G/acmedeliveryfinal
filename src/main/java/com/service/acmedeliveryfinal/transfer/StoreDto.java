package com.service.acmedeliveryfinal.transfer;

import java.util.Set;

public interface StoreDto {
    Long getId();
    String getStoreName();
    String getEmailAddress();
    String getAddress();
    String getCity();
    String getPhoneNumber();

    Set<StoreItemDto> getStoreItems();
}
