package com.service.acmedeliveryfinal.transfer;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface OrderDto {
    Long getOrderId();
    String getStoreName();
    String getDeliveryAddress();
    Date getSubmittedDate();
    BigDecimal getCost();
    String getPaymentMethod();

    List<OrderItemDto> getOrderItems();
}
