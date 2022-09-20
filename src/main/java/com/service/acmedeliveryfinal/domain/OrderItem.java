package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_storeItem")
    private StoreItem storeItem;

    @ToString.Exclude
    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_order")
    @JsonIgnore
    private Order order;

    @Column(nullable = false)
    private Integer quantity;

    @Column(precision = 10, scale = 2,nullable = false)
    private BigDecimal price;

}