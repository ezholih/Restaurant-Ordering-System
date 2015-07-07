package edu.neu.ros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.neu.ros.dao.DishDao;
import edu.neu.ros.dao.FoodOrderDao;
import edu.neu.ros.dao.MenuDao;
import edu.neu.ros.domain.FoodOrder;
import edu.neu.ros.domain.Menu;

@Service
public class OrderingServiceImpl implements OrderingService {
	
	@Autowired
	private MenuDao menuDao;
	
	@Autowired
	private DishDao dishDao;
	
	@Autowired
	private FoodOrderDao orderDao;
	
	@Override
	@Transactional
	public void saveOrder(FoodOrder fdOrder) {
		orderDao.save(fdOrder);
	}

	@Override
	@Transactional
	public List<FoodOrder> findOrdersByUser(Long userId) {
		
		return orderDao.findOrderByUser(userId);
	}

	@Override
	@Transactional
	public List<FoodOrder> findAllOrders() {
		// TODO Auto-generated method stub
		return orderDao.findAll();
	}

	@Override
	@Transactional
	public List<FoodOrder> getLiveOrders() {
		// TODO Auto-generated method stub
		return orderDao.findUnfinishedOrders();
	}

	@Override
	@Transactional
	public List<Menu> findaAllMenus() {
		// TODO Auto-generated method stub
		return (List<Menu>) menuDao.findAll();
	}

	@Override
	public FoodOrder getOrder(Long orderId) {
		return orderDao.get(orderId);
	}

}
