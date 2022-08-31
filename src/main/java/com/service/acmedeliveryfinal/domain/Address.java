package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ADDRESSES")
public class Address extends BaseModel{

    @Column
    private String street;

}
