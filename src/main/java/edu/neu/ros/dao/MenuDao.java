package edu.neu.ros.dao;

import java.util.Collection;

import edu.neu.ros.domain.Menu;

public interface MenuDao {
	Menu get(Long id);
	Collection<Menu> findAll();
	void save(Menu menu);
}
