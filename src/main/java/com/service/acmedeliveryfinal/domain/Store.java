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
public class Store extends BaseModel {



    @Column(nullable = false)
    String storeName;

    @Column
    String businessName;

    @Column
    private String phoneNumber;

    @Column
    private Boolean isActive;

//
//    @ToString.Exclude
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy="store")
//    Set<StoreItem> menuItems;


    @Column
    private String storeCategory;

}
