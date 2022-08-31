package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STOREITEM")
public class StoreItem extends BaseModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;


    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Store store;

    @Column(nullable = false)
    private Integer stock;

    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal price;
}
