package com.ea.domain;
// Generated Sep 23, 2016 11:52:16 AM by Hibernate Tools 5.1.0.Beta1

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders", catalog = "food_delivery")
public class Orders implements java.io.Serializable {

	private Integer orderId;
	private Consumer consumer;
	private Date date;
	private Integer status;
	private Integer totalPrice;
	private Integer travelledMiles;
	private Set<OrderGroup> orderGroups = new HashSet<OrderGroup>(0);

	public Orders() {
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "orderID", unique = true, nullable = false)
	public Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "consumerID")
	public Consumer getConsumer() {
		return this.consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "date", length = 10)
	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "totalPrice")
	public Integer getTotalPrice() {
		return this.totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Column(name = "travelledMiles")
	public Integer getTravelledMiles() {
		return this.travelledMiles;
	}

	public void setTravelledMiles(Integer travelledMiles) {
		this.travelledMiles = travelledMiles;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
	public Set<OrderGroup> getOrderGroups() {
		return this.orderGroups;
	}

	public void setOrderGroups(Set<OrderGroup> orderGroups) {
		this.orderGroups = orderGroups;
	}

}
