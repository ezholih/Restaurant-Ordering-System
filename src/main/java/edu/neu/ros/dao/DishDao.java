package edu.neu.ros.dao;

import java.util.List;

import edu.neu.ros.domain.Dish;

public interface DishDao {
	Dish get(Long id);
	List<Dish> findAll();
	void save(Dish dish);
}
