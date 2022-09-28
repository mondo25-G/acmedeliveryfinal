package com.service.acmedeliveryfinal.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Getter
@ToString
@MappedSuperclass
public abstract class BaseEntity  implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idGenerator")
    private Long id;

}
