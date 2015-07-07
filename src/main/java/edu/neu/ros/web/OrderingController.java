package edu.neu.ros.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.neu.ros.domain.Dish;
import edu.neu.ros.domain.FoodOrder;
import edu.neu.ros.domain.User;
import edu.neu.ros.service.ManagementService;
import edu.neu.ros.service.OrderingService;
import edu.neu.ros.service.UserService;

@Controller
@SessionAttributes("user")
public class OrderingController {
	
	@Autowired
	private ManagementService mgnService;
	
	@Autowired
	private OrderingService orderingService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/roles/chef", method=RequestMethod.GET)
	public String initChefPage(Model model) throws UsernameNotFoundException{
		UserDetails userDetails;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
		if(user == null){
			throw new UsernameNotFoundException("Not able to find user");
		}
		model.addAttribute("user", user);
		return "roles/chef";
	}
	
	@RequestMapping(value = "/roles/handleOrder", method = RequestMethod.GET)
	public String initCookForm(Model model){
		ArrayList<FoodOrder> unfinishedOrders = new ArrayList<FoodOrder>(orderingService.getLiveOrders());
		ArrayList<FoodOrder> finishedOrders = new ArrayList<FoodOrder>(mgnService.findFinishedOrders());
		model.addAttribute("unfinishedOrders", unfinishedOrders);
		model.addAttribute("finishedOrders", finishedOrders);
		model.addAttribute("view", "handle");
		return "roles/chef";
	}
	
	@RequestMapping(value="/roles/handleOrder/{orderId}", method=RequestMethod.POST)
	public String processCookForm(@PathVariable Long orderId, Model model){
		FoodOrder fdOrder = orderingService.getOrder(orderId);
		fdOrder.setFinished(true);
		orderingService.saveOrder(fdOrder);
		return "redirect:";
	}
	
	@RequestMapping(value = "/roles/createOrder", method = RequestMethod.GET)
	public String initOrderingForm(Model model){
		List<Dish> dishes = mgnService.findAllDishes();
		model.addAttribute("dishlist", dishes);
		FoodOrder fdOrder = new FoodOrder();
		model.addAttribute("fdOrder", fdOrder);
		model.addAttribute("view", "create");
		return "roles/waiter";
	}
	
	@RequestMapping(value = "/roles/createOrder", method = RequestMethod.POST)
	public String showOrderItems(@ModelAttribute("fdOrder") @Valid FoodOrder fdOrder, 
			BindingResult result, @ModelAttribute("user") User user, Model model){
		if(fdOrder.getDishId() != null){
			for(Long id : fdOrder.getDishId()){
				Dish dish = mgnService.getDish(id);
				fdOrder.addDish(dish);
			}
		}
		fdOrder.setCreateTime(new DateTime());
		fdOrder.setFinished(false);
		fdOrder.setTotalPrice(fdOrder.getTotalPrice());
		fdOrder.setUser(user);
		orderingService.saveOrder(fdOrder);
		model.addAttribute("view", "create");
		return "redirect:waiter";
	}
	
	@RequestMapping(value="/roles/orderHistory", method=RequestMethod.GET)
	public String initOrderHistory(@ModelAttribute("user") User user, Model model){
		Long id = user.getId();
		ArrayList<FoodOrder> orderlist = 
				new ArrayList<FoodOrder>(orderingService.findOrdersByUser(id));
		model.addAttribute("orderlist", orderlist);
		model.addAttribute("view", "history");
		return "roles/waiter";
	}
	
	@RequestMapping(value="/roles/waiter", method=RequestMethod.GET)
	public String initWaiterPage(Model model) throws UsernameNotFoundException{
		UserDetails userDetails;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
		if(user == null){
			throw new UsernameNotFoundException("Not able to find user");
		}
		model.addAttribute("user", user);
		return "roles/waiter";
	}
	
	@RequestMapping(value="waiter/dish/{dishId}/photo", method=RequestMethod.GET)
	public @ResponseBody String viewDishPhoto(@PathVariable Long dishId, HttpServletResponse response) throws IOException {
		Dish dish = mgnService.getDish(dishId);
		byte[] photoBytes = dish.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(dish.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + dish.getPhotoFilename() + "\"");
				
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
}
