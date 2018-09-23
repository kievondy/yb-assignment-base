package com.yieldbroker.assignment.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.yieldbroker.assignment.model.OrderBook;
import com.yieldbroker.assignment.model.OrderBook.Order;
import com.yieldbroker.assignment.model.YbOrder;
import com.yieldbroker.assignment.repository.YbOrderRepository;

@Service
public class OrderBookService {

	@Autowired
	YbOrderRepository ybOrderRepository;

	public OrderBook getOrderBook() {

		OrderBook orderBook = new OrderBook();

		List<YbOrder> allYbOrders = ybOrderRepository.findAll();

		List<Order> buyOrders = allYbOrders.stream()
				.filter(ybOrder -> ybOrder.getSide().equals(OrderBook.ORDER_SIDE_BUY))
				.map(ybOrder -> orderBook.new Order(ybOrder.getPrice(), ybOrder.getVolume()))
				.collect(Collectors.toList());
		orderBook.getBuyOrders().addAll(buyOrders);

		List<Order> sellOrders = allYbOrders.stream()
				.filter(ybOrder -> ybOrder.getSide().equals(OrderBook.ORDER_SIDE_SELL))
				.map(ybOrder -> orderBook.new Order(ybOrder.getPrice(), ybOrder.getVolume()))
				.collect(Collectors.toList());
		orderBook.getSellOrders().addAll(sellOrders);

		return orderBook;
	}

	public void placeOrder(int clientOrderId, String side, BigDecimal price, int volume) {
		YbOrder ybOrder = new YbOrder();
		ybOrder.setClientOrderId(clientOrderId);
		ybOrder.setSide(side);
		ybOrder.setPrice(price);
		ybOrder.setVolume(volume);
		ybOrderRepository.save(ybOrder);
	}

	public void cancelOrder(int clientOrderId) {

		YbOrder ybOrder = new YbOrder();
		ybOrder.setClientOrderId(clientOrderId);

		List<YbOrder> foundYbOrders = ybOrderRepository.findAll(Example.of(ybOrder));

		if (foundYbOrders != null && foundYbOrders.size() > 0) {
			ybOrderRepository.deleteAll(foundYbOrders);
		}
	}

}
