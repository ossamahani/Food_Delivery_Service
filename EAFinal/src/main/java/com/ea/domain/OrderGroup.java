package com.ea.domain;
// Generated Sep 23, 2016 11:52:16 AM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "order_group", catalog = "food_delivery")
public class OrderGroup implements java.io.Serializable {

	private Integer orderGroupId;
	private Item item;
	private Orders order;
	private String status;

	public OrderGroup() {
	}

	public OrderGroup(String status) {
		this.status = status;
	}

	public OrderGroup(Item item, Orders order, String status) {
		this.item = item;
		this.order = order;
		this.status = status;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "orderGroupID", unique = true, nullable = false)
	public Integer getOrderGroupId() {
		return this.orderGroupId;
	}

	public void setOrderGroupId(Integer orderGroupId) {
		this.orderGroupId = orderGroupId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "itemID")
	public Item getItem() {
		return this.item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "orderID")
	public Orders getOrder() {
		return this.order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

	@Column(name = "status", nullable = true, length = 50)
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
