package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "STORES", indexes = {@Index(name = "STORE_IDX_01", columnList = "storename")})
public class Store extends BaseEntity {

    @NotNull
    @Column
    private String storeName;

    @NotNull
    @Column
    private String emailAddress;

    @NotNull
    @Column
    private String address;

    @NotNull
    @Column
    private String city;

    @NotNull
    @Column(unique = true)
    private String phoneNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.MERGE)
    @JoinColumn(name = "storecategory_id")
    private StoreCategory category;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="store", fetch = FetchType.EAGER)
    private Set<@NotNull StoreItem> storeItems;

}