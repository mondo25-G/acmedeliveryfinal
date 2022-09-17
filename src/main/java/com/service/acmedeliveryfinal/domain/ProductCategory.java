package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
@Entity
@Table(name = "PRODUCT_CATEGORIES")
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductCategory extends BaseEntity {


    @Column(unique = true)
    private String name;


}
