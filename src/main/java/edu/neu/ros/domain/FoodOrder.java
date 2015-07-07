package edu.neu.ros.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "foodorder")
public class FoodOrder {
	@Id
	@Column(name = "ID")
	@GeneratedValue
	private Long id;
	
	@NotNull
	private int tbNumber;
	
    @Column(name = "time")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private DateTime createTime;
	
	private double totalPrice;
	
	private boolean isFinished;
	
	private ArrayList<Long> dishId;
	
	public ArrayList<Long> getDishId() {
		if(dishId == null){
			this.dishId = new ArrayList<Long>();
		}
		return dishId;
	}

	public void setDishId(ArrayList<Long> dishId) {
		this.dishId = dishId;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToMany(cascade = {CascadeType.ALL}, 
			targetEntity = Dish.class, fetch = FetchType.EAGER)
	@JoinTable(name = "foodorder_dish", joinColumns={@JoinColumn(name="foodorder_ID")}, 
            inverseJoinColumns={@JoinColumn(name="dish_ID")})
	private Set<Dish> dishes;
	
	@ManyToOne(targetEntity = User.class)
	@JoinColumn(name = "user_ID")
	private User user;
	
	public boolean isFinished() {
		return isFinished;
	}

	public void setFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}

	public int getTbNumber() {
		return tbNumber;
	}

	public void setTbNumber(int tbNumber) {
		this.tbNumber = tbNumber;
	}

	public double getTotalPrice() {
		if(dishes != null && totalPrice == 0){
			for(Dish d : dishes){
				this.totalPrice = this.totalPrice+d.getPrice();
			}
		}
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Set<Dish> getDishes() {
		if(dishes == null){
			dishes = new HashSet<Dish>();
		}
		return dishes;
	}

	public void setDishes(Set<Dish> dishes) {
		this.dishes = dishes;
	}
	
	public void addDish(Dish dish){
		getDishes().add(dish);
	}
	
	public void setValue(Dish dish){
		getDishes().add(dish);
	}

	public Long getId() {
		return id;
	}

	public DateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(DateTime createTime) {
		this.createTime = createTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
