package edu.neu.ros.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

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
import edu.neu.ros.domain.Menu;
import edu.neu.ros.domain.User;
import edu.neu.ros.service.ManagementService;
import edu.neu.ros.service.UserService;

@Controller
@SessionAttributes("user")
public class ManagementController {
	
	@Autowired
	ManagementService mgnService;
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/roles/viewOrders", method = RequestMethod.GET)
	public String initCookForm(Model model){
		ArrayList<FoodOrder> finishedOrders = new ArrayList<FoodOrder>(mgnService.findFinishedOrders());
		model.addAttribute("finishedOrders", finishedOrders);
		model.addAttribute("view", "order");
		return "roles/manager";
	}
	
	@RequestMapping(value = "/roles/{menuId}/addDish", method=RequestMethod.GET)
	public String initDishForm(@PathVariable Long menuId, Model model){
		Menu menu = mgnService.getMenu(menuId);
		int dnum = menu.getNrOfDishes();
		System.out.println(dnum);
		Dish dish = new Dish();
//		menu.addDish(dish);
		for(Dish d : menu.getDishes()){
			String name = d.getName();
			System.out.println(name);
		}
		dish.setMenu(menu);
		model.addAttribute("menu", menu);
		model.addAttribute(dish);
		model.addAttribute("view", "create");
		return "roles/manager";
	}
	
	@RequestMapping(value = "/roles/{menuId}/addDish", method = RequestMethod.POST)
	public String processDishForm(@PathVariable Long menuId, 
			@ModelAttribute("dish") @Valid Dish dish, BindingResult result, Model model){
		Menu menu = mgnService.getMenu(menuId);
		if(result.hasErrors()){
			return "roles/updateOrCreateMenu";
		}
		dish.setAvailability(true);
		dish.setMenu(menu);
		mgnService.saveDish(dish);
		return "redirect:updateOrCreateMenu";
	}
	
	@RequestMapping(value = "/roles/updateOrCreateMenu", method=RequestMethod.GET)
	public String initMenuForm(Model model){
		Menu menu = new Menu();
		model.addAttribute("menu", menu);
		model.addAttribute("menulist", mgnService.findAllMenus());
		model.addAttribute("dishList", mgnService.findAllDishes());
		model.addAttribute("view", "create");
		return "roles/manager";
	}
	
	@RequestMapping(value = "/roles/{menuId}/updateOrCreateMenu", method=RequestMethod.GET)
	public String showMenuForm(@PathVariable Long menuId, Model model){
		Menu menu = mgnService.getMenu(menuId);
		model.addAttribute("menu", menu);
		model.addAttribute("menulist", mgnService.findAllMenus());
		model.addAttribute("dishList", mgnService.findAllDishes());
		model.addAttribute("view", "create");
		return "roles/manager";
	}
	
	@RequestMapping(value = "/roles/updateOrCreateMenu", method=RequestMethod.POST)
	public String processMenuForm(@ModelAttribute("menu")@Valid Menu menu, BindingResult result, Model model){
		if(result.hasErrors()){
			model.addAttribute("menulist", mgnService.findAllMenus());
			return "roles/updateOrCreateMenu";
		}
		mgnService.saveMenu(menu);
		return "redirect:updateOrCreateMenu";
	}
	
	@RequestMapping(value="/roles/manager", method=RequestMethod.GET)
	public String initManagePage(Model model) throws UsernameNotFoundException{
		UserDetails userDetails;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        userDetails = (UserDetails)auth.getPrincipal();
        User user = userService.findByUserName(userDetails.getUsername());
		if(user == null){
			throw new UsernameNotFoundException("Not able to find user");
		}
		model.addAttribute("user", user);

		return "roles/manager";
	}
	
	@RequestMapping(value="manager/{userId}/photo", method=RequestMethod.GET)
	public @ResponseBody String viewPhoto(@PathVariable Long userId, HttpServletResponse response) throws IOException {
		User user = userService.get(userId);
		byte[] photoBytes = user.getPhotoBytes();
		if (photoBytes != null) {
			int photoLength = photoBytes.length;
			try (ServletOutputStream sos = response.getOutputStream()) {
				response.setContentType(user.getPhotoContentType());
				response.setContentLength(photoLength);
				response.setHeader("Content-Disposition", "inline; filename=\"" + user.getPhotoFilename() + "\"");
				
				sos.write(photoBytes);
				sos.flush();
			}
		}
		return "";
	}
	
	@RequestMapping(value="manager/dish/{dishId}/photo", method=RequestMethod.GET)
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
