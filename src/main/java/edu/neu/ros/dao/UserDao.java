package edu.neu.ros.dao;

import java.util.Collection;
import edu.neu.ros.domain.User;

public interface UserDao {
	User get(Long id);
	void save(User user);
	void delete(User user);
	Collection<User> findAll();
	User findByUserName(String username);
} 
