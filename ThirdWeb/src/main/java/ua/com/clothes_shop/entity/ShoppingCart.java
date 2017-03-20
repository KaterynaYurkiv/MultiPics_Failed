package ua.com.clothes_shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="shopping_cart")
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToMany(mappedBy="shoppingCart")
	private List<User> users = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name="item_cart_connection",
	joinColumns=@JoinColumn(name="id_shopping_cart"),
	inverseJoinColumns=@JoinColumn(name="id_item_of_clothing"))
	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	
	@Column(name="_count")
	private int count;
	
	public void add(ItemOfClothing e) {
		itemsOfClothing.add(e);
		count = itemsOfClothing.size();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public List<ItemOfClothing> getItemsOfClothing() {
		return itemsOfClothing;
	}

	public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
		this.itemsOfClothing = itemsOfClothing;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
