package com.yieldbroker.assignment.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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

}
