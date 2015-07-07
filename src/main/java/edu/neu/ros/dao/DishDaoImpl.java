package edu.neu.ros.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.ros.domain.Dish;

@Repository
public class DishDaoImpl implements DishDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Dish get(Long id) {
		return (Dish) sessionFactory.getCurrentSession().get(Dish.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Dish> findAll() {
		return sessionFactory.getCurrentSession().createQuery(
				"FROM Dish ORDER BY id")
			.list();
	}

	@Override
	public void save(Dish dish) {
		if(dish.getId() == null){
			sessionFactory.getCurrentSession().save(dish);
		}else{
			sessionFactory.getCurrentSession().merge(dish);
		}
		sessionFactory.getCurrentSession().flush();
	}

}
