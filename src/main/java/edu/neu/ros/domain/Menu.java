package edu.neu.ros.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "menu")
public class Menu {
	
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@NotNull
	private String name;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu", fetch = FetchType.EAGER)
//	@JoinTable(name = "menu_dish",
//			joinColumns = @JoinColumn( name="menu_ID"),
//            inverseJoinColumns = @JoinColumn( name="dish_ID"))
	private Set<Dish> dishes;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private Set<Dish> getDishesInternal(){
		if(this.dishes == null){
			dishes = new HashSet<Dish>();
		}
		return this.dishes;
	}
 
	public List<Dish> getDishes() {
		List<Dish> listDish = new ArrayList<Dish>(getDishesInternal());
		return listDish;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}

	public Long getId() {
		return id;
	}
	
	public void addDish(Dish dish){
		Set<Dish> d = getDishesInternal();
		d.add(dish);
	} 
	
	public int getNrOfDishes(){
		return getDishesInternal().size();
	}

}
