package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.service.acmedeliveryfinal.transfer.KeyValue;
import com.service.acmedeliveryfinal.transfer.PopularItemDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@NamedNativeQuery(name = "StoreItem.findTop10Products",
        query ="""
		with popularItems as (select oi.storeItem_id as storeItemId, count(oi.storeItem_id) as purchases
		from orderItems oi group by oi.storeItem_id) select si.store_id as storeId,si.id as storeItemId,si.itemName as storeItemName,p.purchases as timesOrdered, 
		pc.name as productCategory from storeitems si 
		inner join popularItems p on p.storeItemId=si.id
		inner join product_categories pc on si.productcategory_id=pc.id
		order by p.purchases desc,p.storeItemId fetch first 10 rows only
			""",
        resultSetMapping = "findTop10Products")
@SqlResultSetMapping(name = "findTop10Products",
        classes = @ConstructorResult(
                targetClass = PopularItemDto.class,
                columns = {
                        @ColumnResult(name = "storeId", type = Long.class),
                        @ColumnResult(name = "storeItemId", type = Long.class),
                        @ColumnResult(name = "storeItemName", type = String.class),
                        @ColumnResult(name = "timesOrdered", type = Integer.class),
                        @ColumnResult(name = "productCategory", type = String.class)
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
@SequenceGenerator(name = "idGenerator", sequenceName = "STOREITEMS_SEQ", initialValue = 1, allocationSize = 1)
public class StoreItem extends BaseEntity {

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "store_id")
    @JsonBackReference("storeItems")
    private Store store;

    @NotNull
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY, optional = false,cascade = CascadeType.MERGE)
    @JoinColumn(name = "productCategory_id")
    private ProductCategory productCategory;

    @NotNull
    @Column
    private String itemName;

    @NotNull
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

}
