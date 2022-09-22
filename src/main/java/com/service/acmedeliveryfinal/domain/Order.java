package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ORDERS")
public class Order extends BaseEntity{

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    @ToString.Exclude
    @JsonIgnore
    private Store store;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date submittedDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY, mappedBy="order")
    private Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(length = 13, nullable = false)
    private PaymentMethod paymentMethod;

    @Column(precision = 10, scale = 2, nullable = false )
    private BigDecimal cost;

}
