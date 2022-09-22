package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


import java.util.Set;

//@formatter:off
@NamedNativeQuery(name = "Store.findTop10Stores",
        query ="""
			with storeOrders as (select count(o.store_id) as ordersPlaced, o.store_id as storeId from orders o group by o.store_id) 
			select s.id as storeId, s.storeName as storeName from stores s inner join storeOrders c on s.id=c.storeId  
			order by c.ordersPlaced desc,c.storeId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10Stores")
@SqlResultSetMapping(name = "findTop10Stores",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeId", type = Long.class),
                        @ColumnResult(name = "storeName", type = String.class)
                }
        )
)
@NamedNativeQuery(name = "Store.findTop10StoresByCategoryId",
        query ="""
		with storeOrders as (select count(o.store_id) as ordersPlaced, o.store_id as storeId from orders o group by o.store_id) 
		select s.id as storeId,s.storeName as storeName from stores s inner join storeOrders c on s.id=c.storeId 
		inner join store_categories sc on sc.id=s.storecategory_id 
		where sc.id=?1 order by c.ordersPlaced desc,c.storeId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10StoresByCategoryId")
@SqlResultSetMapping(name = "findTop10StoresByCategoryId",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeId", type = Long.class),
                        @ColumnResult(name = "storeName", type = String.class)
                }
        )
)
@NamedNativeQuery(name = "Store.findTop10StoresByCategoryName",
        query ="""
		with storeOrders as (select count(o.store_id) as ordersPlaced, o.store_id as storeId from orders o group by o.store_id) 
		select s.id as storeId,s.storeName as storeName from stores s inner join storeOrders c on s.id=c.storeId 
		inner join store_categories sc on sc.id=s.storecategory_id 
		where sc.name=?1 order by c.ordersPlaced desc,c.storeId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10StoresByCategoryName")
@SqlResultSetMapping(name = "findTop10StoresByCategoryName",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeId", type = Long.class),
                        @ColumnResult(name = "storeName", type = String.class)
                }
        )
)
//@formatter:on
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

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.MERGE)
    @JoinColumn(name = "storecategory_id")
    private StoreCategory category;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval = true, mappedBy="store", fetch = FetchType.LAZY)
    private Set<@NotNull StoreItem> storeItems;

}