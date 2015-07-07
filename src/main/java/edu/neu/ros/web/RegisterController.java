package edu.neu.ros.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.neu.ros.domain.User;
import edu.neu.ros.service.UserService;
import edu.neu.ros.util.UserValidator;

@Controller
@SessionAttributes("user")
public class RegisterController {
	
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserValidator userValidator;
	
	@RequestMapping(value = "/register", method =  RequestMethod.GET)
	public String get(Model model){
		model.addAttribute(new User());
		return "/register";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String post(Model model, @Valid User user, BindingResult result){
		userValidator.validate(user, result);
		if(result.hasErrors()){
			return "/register";
		}
		PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/roles/{userId}/updateUserInfo", method = RequestMethod.GET)
	public String showUserInfo(@PathVariable Long userId, Model model){
		User user = userService.get(userId);
		model.addAttribute("user", user);
		return "/roles/updateUserInfo";
	}
	
	@RequestMapping(value = "/roles/updateUserInfo", method = {RequestMethod.PUT, RequestMethod.POST})
	public String updateUserInfo(@ModelAttribute("user")@Valid User user, BindingResult result){
		if(result.hasErrors()){
			return "/roles/updateUserInfo";
		}
		PasswordEncoder passwordEncoder = new StandardPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userService.save(user);
		return "redirect:/";
	}
}
