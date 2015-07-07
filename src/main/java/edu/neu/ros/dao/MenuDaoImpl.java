package edu.neu.ros.dao;

import java.util.Collection;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.neu.ros.domain.Menu;

@Repository
public class MenuDaoImpl implements MenuDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Menu get(Long id) {
		return (Menu) sessionFactory.getCurrentSession().get(Menu.class, id);
	}

	@Override
	@SuppressWarnings("unchecked")
	public Collection<Menu> findAll() {
		return sessionFactory.getCurrentSession().createQuery("FROM Menu ORDER BY id").list();
	}

	@Override
	public void save(Menu menu) {
		if(menu.getId() == null){
			sessionFactory.getCurrentSession().save(menu);
		}else{
			sessionFactory.getCurrentSession().merge(menu);
		}
		sessionFactory.getCurrentSession().flush();
	}

}
