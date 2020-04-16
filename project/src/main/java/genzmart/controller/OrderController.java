package genzmart.controller;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import genzmart.services.LoginServices;
import genzmart.services.OrderService;
import genzmart.model.Order;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderSer;

	@Autowired
	private LoginServices loginServices;

	@GetMapping(path = "/discount/{item_id}")
	public List<Long> getDiscountData(@PathVariable(value = "item_id") Long item_id) {
		return orderSer.disData(item_id);
	}

	@GetMapping(path = "/discount/{item_id}/{discount}")
	public Long getDiscountData(@PathVariable(value = "item_id") Long item_id,
			@PathVariable(value = "discount") Long discount, @RequestHeader(name = "token") String token) {
		if (token != null && loginServices.validateToken(token)) {
			return orderSer.disDataForItem(item_id, discount);
		} else
			return null;
	}

	@GetMapping(path = "/discounts/{item_id}")
	public List<JSONObject> getDiscountDatas(@PathVariable(value = "item_id") Long item_id) {
		return orderSer.disDataForItems(item_id);
	}

}
