package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.domain.enumeration.ProductCategory;
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
@Table(name = "PRODUCTS")


public class StoreItem extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_store")
    private Store store;

    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(nullable = false, unique = true)
    String name; //difference in Name

    @Column(precision = 10, scale = 2, nullable = false)
    BigDecimal price; //difference in price, but same name

}

