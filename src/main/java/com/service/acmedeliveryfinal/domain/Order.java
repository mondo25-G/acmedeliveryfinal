package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.acmedeliveryfinal.domain.enumeration.PaymentMethod;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    @ToString.Exclude
    private Store store;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date submittedDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.LAZY, mappedBy="order")
    private Set<@NotNull OrderItem> orderItems;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 13, nullable = false)
    private PaymentMethod paymentMethod;

    @NotNull
    @Column(precision = 10, scale = 2, nullable = false )
    private BigDecimal cost;

}
