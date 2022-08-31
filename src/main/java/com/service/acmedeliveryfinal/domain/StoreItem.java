package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "STOREITEM")
public class StoreItem extends BaseModel {


}
