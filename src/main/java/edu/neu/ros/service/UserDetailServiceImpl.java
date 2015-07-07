package edu.neu.ros.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.ros.dao.UserDao;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	private UserDao userDao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		edu.neu.ros.domain.User user = userDao.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Not able to find user");
		}
		GrantedAuthority grantedAuthority;
		if(user.getRole().equals("ROLE_MANAGER") ){
			grantedAuthority = new SimpleGrantedAuthority("ROLE_MANAGER");
		}else if(user.getRole().equals("ROLE_WAITER")){
			grantedAuthority = new SimpleGrantedAuthority("ROLE_WAITER");			
		}else{
			grantedAuthority = new SimpleGrantedAuthority("ROLE_CHEF");	
		}
		List<GrantedAuthority> grantedAuthorities = Arrays.asList(grantedAuthority);		
		return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
	}

}
