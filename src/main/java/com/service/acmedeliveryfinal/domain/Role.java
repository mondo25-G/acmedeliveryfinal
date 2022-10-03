package com.service.acmedeliveryfinal.domain;

import com.service.acmedeliveryfinal.domain.enumeration.ERole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "idGenerator", sequenceName = "ROLES_SEQ", initialValue = 1, allocationSize = 1)
public class Role extends BaseEntity {


    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;
}