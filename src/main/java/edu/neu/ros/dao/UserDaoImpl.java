package edu.neu.ros.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.ros.domain.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User get(Long id) {
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}

	@Override
	public void save(User user) {
		if(user.getId() == null){
			sessionFactory.getCurrentSession().save(user);
		}else{
			sessionFactory.getCurrentSession().update(user);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void delete(User user) {
		sessionFactory.getCurrentSession().delete(user);

	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<User> findAll() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM User ORDER BY id")
			.list();
	}

	@Override
	public User findByUserName(String username) {
		return (User) sessionFactory.getCurrentSession().createQuery(
				"FROM User u WHERE u.userName = :username ")
			.setString("username", username).uniqueResult();
	}
	
	

}
