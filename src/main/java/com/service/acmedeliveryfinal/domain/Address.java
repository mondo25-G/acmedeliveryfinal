package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "ADDRESSES")
public class Address extends BaseModel{

    @Column(nullable = false)
    private String street;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name="fk_account")
    private Account account;

}
