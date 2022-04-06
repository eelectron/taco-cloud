package tacos.web;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import tacos.Order;
import tacos.User;
import tacos.data.OrderRepository;
import tacos.data.UserRepository;

@Slf4j
@Controller
@SessionAttributes("order")
@RequestMapping("/orders")
public class OrderController {
	private OrderRepository orderRepo;
	private UserRepository userRepo;
	
	public OrderController(OrderRepository orderRepo, UserRepository userRepo) {
		this.orderRepo = orderRepo;
		this.userRepo = userRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		model.addAttribute("order", new Order());
		return "orderForm";
	}
	
	
	@PostMapping()
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus, Principal principal) {
		// return order form if any error has occurred
		if(errors.hasErrors()) {
			return "orderForm";
		}
		
		log.info("Order Submitted " + order);
		
		// get User who placed the order
		User user = userRepo.findByUsername(principal.getName());
		order.setUser(user);
		
		// save the order
		orderRepo.save(order);
		sessionStatus.setComplete();
		return "redirect:/";
	}
}
