package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import lombok.*;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="ORDERS")
@ToString(callSuper = true)
public class Order extends BaseModel {

   /* @ManyToOne(fetch =  FetchType.LAZY, optional = false)
    @JoinColumn(name="fk_store")
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "fk_account")
    private Account account; */ //Implementation of Store, Account

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date submittedDate;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY, mappedBy="order")
    private Set<OrderItem> orderItems;

    @Enumerated(EnumType.STRING)
    @Column(length = 13, nullable = false)
    private PaymentMethod paymentMethod;

    @Column(precision = 10, scale = 2, nullable = false )
    private BigDecimal cost;

}
