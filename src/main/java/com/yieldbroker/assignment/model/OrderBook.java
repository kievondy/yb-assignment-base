package com.yieldbroker.assignment.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class OrderBook {

	private List<Order> buyOrders = new ArrayList<>();
	private List<Order> sellOrders = new ArrayList<>();

	public List<Order> getBuyOrders() {
		return buyOrders;
	}

	public List<Order> getSellOrders() {
		return sellOrders;
	}

	public class Order {
		private BigDecimal price;
		private int volume;

		public Order(BigDecimal price, int volume) {
			super();
			this.price = price;
			this.volume = volume;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public int getVolume() {
			return volume;
		}

		public void setVolume(int volume) {
			this.volume = volume;
		}

		@Override
		public String toString() {
			return "Order [price=" + price + ", volume=" + volume + "]";
		}

	}

	@Override
	public String toString() {
		return "OrderBook [buyOrders=" + buyOrders + ", sellOrders=" + sellOrders + "]";
	}

}
