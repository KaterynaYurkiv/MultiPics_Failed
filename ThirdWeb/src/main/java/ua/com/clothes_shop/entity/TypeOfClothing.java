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
@Table(name="type_of_clothing")
public class TypeOfClothing {
	
	//Первинний ключ
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того щоб стовпці мали назву як і в БД
	@Column(name="item_type")
	private String itemType;
	
	@OneToMany(mappedBy="typeOfClothing") 
	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	
	public TypeOfClothing() {
		// TODO Auto-generated constructor stub
	}

	public TypeOfClothing(String itemType) {
		super();
		this.itemType = itemType;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public List<ItemOfClothing> getItemsOfClothing() {
		return itemsOfClothing;
	}

	public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
		this.itemsOfClothing = itemsOfClothing;
	}

	@Override
	public String toString() {
		return "TypeOfClothing [id=" + id + ", itemType=" + itemType + "]";
	}

}
