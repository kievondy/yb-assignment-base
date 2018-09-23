package com.yieldbroker.assignment.controller;

import java.math.BigDecimal;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yieldbroker.assignment.model.OrderBook;
import com.yieldbroker.assignment.service.OrderBookService;

@RestController
@RequestMapping("/market")
public class OrderBookController {

	@Autowired
	OrderBookService orderBookService;

	/**
	 * Gets the current contents of the order book
	 * 
	 * @return order book containing buy and sell orders
	 */
	@GetMapping("/orderbook")
	public OrderBook getOrderBook() {
		return orderBookService.getOrderBook();
	}

	/**
	 * Place an order into the order book
	 * 
	 * @param clientOrderId
	 *            - Order ID selected by client to identify the order
	 * @param side
	 *            - either "buy" or "sell"
	 * @param price
	 *            - the price to buy or sell at. (must be positive)
	 * @param volume
	 *            - the amount to buy or sell. (must be positive)
	 * @throws SQLException
	 */
	public void placeOrder(int clientOrderId, String side, BigDecimal price, int volume) throws SQLException {

	}

	/**
	 * Removes the clients order id from the order book
	 * 
	 * @param clientOrderId
	 */
	public void cancelOrder(int clientOrderId) {

	}
}
