package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERITEMS")
public class OrderItem extends BaseEntity{
    @OneToMany(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_storeMenuItem")
    private StoreItem storeItem;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_order")
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2,nullable = false)
    private BigDecimal price;

}