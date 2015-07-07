package edu.neu.ros.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.ros.domain.FoodOrder;

@Repository
public class FoodOrderDaoImpl implements FoodOrderDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public FoodOrder get(Long id) {
		return (FoodOrder) sessionFactory.getCurrentSession().get(FoodOrder.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FoodOrder> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM FoodOrder ORDER BY id").list();
	}

	@Override
	public void save(FoodOrder fdorder) {
		if(fdorder.getId() == null){
			sessionFactory.getCurrentSession().save(fdorder);
		}else{
			sessionFactory.getCurrentSession().merge(fdorder);
		}
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FoodOrder> findFnishedOrders() {
		return sessionFactory.getCurrentSession().createQuery
				("FROM FoodOrder fdorder WHERE fdorder.isFinished = true").list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FoodOrder> findOrderByUser(Long userId) {
		String hql = "FROM FoodOrder fdorder WHERE fdorder.user.id = " + userId;
		return sessionFactory.getCurrentSession().createQuery
				(hql).list();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<FoodOrder> findUnfinishedOrders() {
		return sessionFactory.getCurrentSession().createQuery
				("FROM FoodOrder fdorder WHERE fdorder.isFinished = false").list();
	}

}
