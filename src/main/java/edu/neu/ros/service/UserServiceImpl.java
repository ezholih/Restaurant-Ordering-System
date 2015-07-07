package edu.neu.ros.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.ros.dao.UserDao;
import edu.neu.ros.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public User get(Long id) {
		return userDao.get(id);
	}

	@Override
	@Transactional
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	@Transactional
	public void delete(User user) {
		userDao.delete(user);
	}

	@Override
	@Transactional
	public Collection<User> findAll() {
		return userDao.findAll();
	}

	@Override
	@Transactional
	public User findByUserName(String username) {
		return userDao.findByUserName(username);
	}

}
