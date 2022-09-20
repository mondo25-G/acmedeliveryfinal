package com.service.acmedeliveryfinal.transfer;

import lombok.Value;
public record KeyValue<K, V>(K key, V value) {
}
