package com.service.acmedeliveryfinal.transfer;

import java.math.BigDecimal;
import java.util.Date;

public interface AccountOrderHeaderDto {
    Long getOrderId();
    String getStoreName();
    String getDeliveryAddress();
    Date getSubmittedDate();
    BigDecimal getCost();
}
