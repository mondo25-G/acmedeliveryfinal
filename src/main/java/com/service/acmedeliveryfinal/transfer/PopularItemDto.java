package com.service.acmedeliveryfinal.transfer;

import lombok.Value;

@Value
public class PopularItemDto {
    Long storeId;
    Long storeItemId;
    String storeItemName;
    Integer timesOrdered;
    String productCategory;
}
