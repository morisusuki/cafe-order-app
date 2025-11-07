package com.example.cafeapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.cafeapp.entity.Order;
import com.example.cafeapp.repository.OrderRepository;

@Controller
@RequestMapping("/admin/orders")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;

	@GetMapping
	public String listOrders(Model model) {
		List<Order> orders = orderRepository.findAll();
		model.addAttribute("orders", orders);
		return "admin/order_list";
	}

	@GetMapping("/{id}")
	public String showOrderDetail(@PathVariable Long id, Model model,
			RedirectAttributes redirectAttributes) {
		Optional<Order> order = orderRepository.findById(id);
		if (order.isPresent()) {
			model.addAttribute("order", order);
			return "admin/order_detail";
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "注文が見つかりませんでした");
			return "redirect:/admin/orders";
		}
	}

}
