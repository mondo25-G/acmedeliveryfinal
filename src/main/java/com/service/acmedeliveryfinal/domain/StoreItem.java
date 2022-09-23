package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.transfer.KeyValue;
import lombok.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedNativeQuery(name = "StoreItem.findTop10Products",
        query ="""
		with popularItems as (select oi.storeItem_id as storeItemId, count(oi.storeItem_id) as purchases
		from orderItems oi group by oi.storeItem_id) select si.id as storeItemId,si.itemName as storeItemName from storeitems si 
		inner join popularItems p on p.storeItemId=si.id
		order by p.purchases desc,p.storeItemId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10Products")
@SqlResultSetMapping(name = "findTop10Products",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeItemId", type = Long.class),
                        @ColumnResult(name = "storeItemName", type = String.class)
                }
        )
)
@NamedNativeQuery(name = "StoreItem.findTop10ProductsByStoreId",
        query ="""
		with popularItems as (select oi.storeItem_id as storeItemId, count(oi.storeItem_id) as purchases
		from orderItems oi group by oi.storeItem_id) select si.id as storeItemId,si.itemName as storeItemName from storeitems si 
		inner join popularItems p on p.storeItemId=si.id where si.store_id=?1
		order by p.purchases desc,p.storeItemId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10ProductsByStoreId")
@SqlResultSetMapping(name = "findTop10ProductsByStoreId",
        classes = @ConstructorResult(
                targetClass = KeyValue.class,
                columns = {
                        @ColumnResult(name = "storeItemId", type = Long.class),
                        @ColumnResult(name = "storeItemName", type = String.class)
                }
        )
)
@Getter
@Setter
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STOREITEMS")
public class StoreItem extends BaseEntity {

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    @JsonIgnore
    private Store store;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;

    @NotNull
    @Column
    private String itemName;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

}
