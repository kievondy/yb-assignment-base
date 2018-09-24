package com.yieldbroker.assignment.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class YbOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@NotNull(message = "clientOrderId must be positive number")
	@Positive(message = "clientOrderId must be positive number")
	@Column(name = "CLIENT_ORDER_ID")
	private Integer clientOrderId;

	@NotNull(message = "price must be positive number")
	@Positive(message = "price must be positive number")
	@Column(name = "PRICE")
	private BigDecimal price;

	@Column(name = "RECEIVED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private java.util.Date receivedTime;

	@NotNull(message = "side must be 'sell' or 'buy'")
	@Column(name = "SIDE")
	private String side;

	@NotNull(message = "volume must be positive number")
	@Positive(message = "volume must be positive number")
	@Column(name = "VOLUME")
	private Integer volume;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(Integer clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public java.util.Date getReceivedTime() {
		return receivedTime;
	}

	public void setReceivedTime(java.util.Date receivedTime) {
		this.receivedTime = receivedTime;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public Integer getVolume() {
		return volume;
	}

	public void setVolume(Integer volume) {
		this.volume = volume;
	}

	@PrePersist
	public void addTimestamp() {
		this.receivedTime = new Date();
	}
}
