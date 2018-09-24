package com.yieldbroker.assignment.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	@PostMapping("/placeorder")
	public void placeOrder(@RequestParam("clientOrderId") int clientOrderId, @RequestParam("side") String side, @RequestParam("price") BigDecimal price,
			@RequestParam("volume") int volume) throws SQLException {

		if (!OrderBook.ORDER_SIDES.contains(side)) {
			throw new IllegalArgumentException(String.format("Invalid order side: must be '%s' or '%s'", OrderBook.ORDER_SIDE_BUY, OrderBook.ORDER_SIDE_SELL));
		}

		orderBookService.placeOrder(clientOrderId, side, price, volume);
	}

	/**
	 * Removes the clients order id from the order book
	 * 
	 * @param clientOrderId
	 */
	@DeleteMapping("/cancelOrder")
	public void cancelOrder(@RequestParam("clientOrderId") int clientOrderId) {
		orderBookService.cancelOrder(clientOrderId);
	}

	@ExceptionHandler({ IllegalArgumentException.class, ConstraintViolationException.class })
	public void handleValidationException(Exception e, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.BAD_REQUEST.value());
	}

}
