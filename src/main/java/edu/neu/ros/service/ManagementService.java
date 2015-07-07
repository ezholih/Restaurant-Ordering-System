package edu.neu.ros.service;

import java.util.List;

import edu.neu.ros.domain.Dish;
import edu.neu.ros.domain.FoodOrder;
import edu.neu.ros.domain.Menu;

public interface ManagementService {
	
	void saveDish(Dish dish);
	void saveMenu(Menu menu);
	Menu getMenu(Long id);
	Dish getDish(Long id);
	List<Menu> findAllMenus();
	List<FoodOrder> findFinishedOrders();
	List<Dish> findAllDishes();
	double calRevenue();
}
