package com.yieldbroker.assignment.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
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
		orderBook.getBuyOrders().addAll(findBuyOrders());
		orderBook.getSellOrders().addAll(findSellOrders());
		return orderBook;
	}

	private List<Order> findBuyOrders() {
		return findAllOrders(OrderBook.ORDER_SIDE_BUY,
				Arrays.asList(org.springframework.data.domain.Sort.Order.desc("price"), org.springframework.data.domain.Sort.Order.asc("receivedTime")));
	}

	private List<Order> findSellOrders() {
		return findAllOrders(OrderBook.ORDER_SIDE_SELL,
				Arrays.asList(org.springframework.data.domain.Sort.Order.asc("price"), org.springframework.data.domain.Sort.Order.asc("receivedTime")));
	}

	private List<Order> findAllOrders(String side, List<org.springframework.data.domain.Sort.Order> sortOrders) {

		OrderBook orderBook = new OrderBook();

		YbOrder ybOrder = new YbOrder();
		ybOrder.setSide(side);
		Example<YbOrder> example = Example.of(ybOrder);

		Sort sort = Sort.by(sortOrders);

		List<YbOrder> foundOrders = ybOrderRepository.findAll(example, sort);

		return foundOrders.stream().map(yb -> orderBook.new Order(yb.getPrice(), yb.getVolume())).collect(Collectors.toList());

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
