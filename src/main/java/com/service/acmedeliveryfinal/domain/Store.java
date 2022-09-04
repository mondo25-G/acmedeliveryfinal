package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.domain.enumeration.StoreCategory;
import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Entity

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORES")
public class Store extends BaseEntity {


    @Column(nullable = false)
    String storeName;


    @ToString.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="store")
    Set<StoreItem> menuItems;


    @Enumerated(EnumType.STRING)
    StoreCategory storeCategory;

}
