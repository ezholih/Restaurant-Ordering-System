package edu.neu.ros.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import edu.neu.ros.dao.UserDao;
import edu.neu.ros.domain.User;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserDao userDao;
	
	
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		User newUser = userDao.findByUserName(user.getUserName());
		if(newUser != null){
			errors.rejectValue("userName", "userName.alreadyExist", "User name is not available, try something else!");
		}

	}

}
