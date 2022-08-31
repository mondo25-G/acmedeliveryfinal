package com.service.acmedeliveryfinal.domain;

import lombok.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTS")
public class Product extends BaseModel{

    @Column
    private String serial;
    @Column
    private String name;
    @Column
    private BigDecimal price;
    //private ProductCategory productCategory;

}
