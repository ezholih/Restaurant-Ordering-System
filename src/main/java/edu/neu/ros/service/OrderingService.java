package edu.neu.ros.service;

import java.util.List;

import edu.neu.ros.domain.FoodOrder;
import edu.neu.ros.domain.Menu;

public interface OrderingService {
	void saveOrder(FoodOrder fdOrder);
	FoodOrder getOrder(Long orderId);
	List<FoodOrder> findOrdersByUser(Long userId);
	List<FoodOrder> findAllOrders();
	List<FoodOrder> getLiveOrders();
	List<Menu> findaAllMenus();
}
