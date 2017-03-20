package ua.com.clothes_shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того щоб таблиці мали назву як і в БД
@Table(name="target_audience")
public class TargetAudience {
	
	//Первинний ключ
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того щоб стовпці мали назву як і в БД
	@Column(name="category")
	private String category;
	
	@OneToMany(mappedBy="targetAudience") 
	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	
	public TargetAudience() {
		// TODO Auto-generated constructor stub
	}
	
	public TargetAudience(String category) {
		super();
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ItemOfClothing> getItemsOfClothing() {
		return itemsOfClothing;
	}

	public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
		this.itemsOfClothing = itemsOfClothing;
	}

	@Override
	public String toString() {
		return "TargetAudience [id=" + id + ", category=" + category + "]";
	}

}
