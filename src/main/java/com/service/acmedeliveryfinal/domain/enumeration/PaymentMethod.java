package com.service.acmedeliveryfinal.domain.enumeration;
import com.fasterxml.jackson.annotation.JsonValue;


public enum PaymentMethod {
    @JsonValue
    CARD,CASH,PAYPAL
}
