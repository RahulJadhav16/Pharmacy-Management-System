package com.pms.order.service.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.order.service.Impl.orderServiceImpl;
import com.pms.order.service.Model.order;

@RestController
@RequestMapping("/orderService")
public class OrderServiceController {
	
	@Autowired
	private orderServiceImpl orderService;
	
	@PostMapping("/addOrder")
	public ResponseEntity<order>createOrder(@RequestBody order orderobj)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderobj));
	}
	
	@GetMapping("/showOrder/{id}")
	public ResponseEntity<List<order>>getOrders(@PathVariable String id)
	{
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrders(id));
	}

}
