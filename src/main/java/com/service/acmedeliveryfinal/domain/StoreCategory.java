package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "STORE_CATEGORIES")
@Getter
@Setter
@Builder
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class StoreCategory extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String name;
}