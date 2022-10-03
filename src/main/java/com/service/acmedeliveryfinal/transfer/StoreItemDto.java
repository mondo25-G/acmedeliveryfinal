package com.service.acmedeliveryfinal.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public interface StoreItemDto {
    @JsonIgnore
    Long getStoreId();
    Long getId();
    String getItemName();
    BigDecimal getPrice();
    String getProductCategory();
}
