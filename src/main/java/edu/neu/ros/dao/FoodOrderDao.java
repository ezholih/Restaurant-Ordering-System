package edu.neu.ros.dao;

import java.util.List;

import edu.neu.ros.domain.FoodOrder;

public interface FoodOrderDao {
	
	FoodOrder get(Long id);
	List<FoodOrder> findAll();
	void save(FoodOrder fdorder);
	List<FoodOrder> findFnishedOrders();
	List<FoodOrder>	findUnfinishedOrders();
	List<FoodOrder>	findOrderByUser(Long userId);
}
