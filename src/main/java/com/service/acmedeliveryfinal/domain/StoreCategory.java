package com.service.acmedeliveryfinal.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "idGenerator", sequenceName = "STORE_CATEGORIES_SEQ", initialValue = 1, allocationSize = 1)
public class StoreCategory extends BaseEntity{

    @NotNull
    @Column(unique = true)
    private String name;
}