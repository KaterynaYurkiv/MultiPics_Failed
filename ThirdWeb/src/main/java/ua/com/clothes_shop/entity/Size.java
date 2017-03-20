package ua.com.clothes_shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.JoinTable;
//import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того щоб таблиці мали назву як і в БД
@Table(name="size")
public class Size {
	
	//Первинний ключ
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того щоб стовпці мали назву як і в БД
	@Column(name="size")
	private String size;
	
//	@ManyToMany
//	@JoinTable(name="item_size_connection",
//	joinColumns=@JoinColumn(name="id_size"),
//	inverseJoinColumns=@JoinColumn(name="id_item_of_clothing"))
//	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	@OneToMany(mappedBy="size")
	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	
	public Size() {
		// TODO Auto-generated constructor stub
	}

	public Size(String size) {
		super();
		this.size = size;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<ItemOfClothing> getItemsOfClothing() {
		return itemsOfClothing;
	}

	public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
		this.itemsOfClothing = itemsOfClothing;
	}

	@Override
	public String toString() {
		return "Size [id=" + id + ", size=" + size + "]";
	}

}
