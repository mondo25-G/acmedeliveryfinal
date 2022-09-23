package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "storeItem_id")
    private StoreItem storeItem;

    @NotNull
    @ToString.Exclude
    @ManyToOne( fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id")
    @JsonIgnore
    private Order order;

    @NotNull
    @Column(nullable = false)
    private Integer quantity;

    @NotNull
    @Column(precision = 10, scale = 2,nullable = false)
    private BigDecimal price;

}