package com.service.acmedeliveryfinal.transfer;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

public interface OrderDetailsDto {
    @JsonIgnore
    Long getOrderId();
    String getItemName();
    BigDecimal getPrice();
    Integer getQuantity();
}
