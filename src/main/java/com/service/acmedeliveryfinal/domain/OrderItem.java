package com.service.acmedeliveryfinal.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem extends BaseModel {

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "fk_order")
	private Order order;

	@Column(nullable = false)
	private Integer quantity;

	@Column(precision = 10, scale = 2,nullable = false)
	private BigDecimal price;
}
