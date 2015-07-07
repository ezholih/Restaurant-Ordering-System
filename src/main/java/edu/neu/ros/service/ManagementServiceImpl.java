package edu.neu.ros.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.ros.dao.DishDao;
import edu.neu.ros.dao.FoodOrderDao;
import edu.neu.ros.dao.MenuDao;
import edu.neu.ros.domain.Dish;
import edu.neu.ros.domain.FoodOrder;
import edu.neu.ros.domain.Menu;

@Service
public class ManagementServiceImpl implements ManagementService {
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private FoodOrderDao orderDao;
	
	@Override
	@Transactional
	public void saveDish(Dish dish) {
		dishDao.save(dish);
	}

	@Override
	@Transactional
	public void saveMenu(Menu menu) {
		menuDao.save(menu);
	}

	@Override
	@Transactional
	public List<Menu> findAllMenus() {
		return (List<Menu>) menuDao.findAll();
	}

	@Override
	@Transactional
	public List<FoodOrder> findFinishedOrders() {
		return orderDao.findFnishedOrders();
	}
	
	@Override
	@Transactional
	public double calRevenue(){
		double revenue = 0;
		ArrayList<FoodOrder> fdOrders = new ArrayList<FoodOrder>(findFinishedOrders());
		for(FoodOrder fdo : fdOrders){
			revenue = revenue+ fdo.getTotalPrice();
		}	
		return revenue;
	}

	@Override
	@Transactional
	public Menu getMenu(Long id) {
		// TODO Auto-generated method stub
		return menuDao.get(id);
	}

	@Override
	@Transactional
	public Dish getDish(Long id) {
		// TODO Auto-generated method stub
		return dishDao.get(id);
	}

	@Override
	@Transactional
	public List<Dish> findAllDishes() {
		// TODO Auto-generated method stub
		return dishDao.findAll();
	}

}
