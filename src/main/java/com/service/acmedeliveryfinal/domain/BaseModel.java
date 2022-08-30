package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.base.BaseComponent;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseModel  implements Serializable {

    @Serial
    private static final Long serialVersionUID = 1L;
    @Id
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
