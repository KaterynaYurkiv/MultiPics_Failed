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
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того щоб таблиці мали назву як і в БД
@Table(name="customer")
public class Customer {
	

	//Первинний ключ
	@Id
	//AUTO_INCTEMENT
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	//для того щоб стовпці мали назву як і в БД
	@Column(name="name")
	private String userName; //назвати name бо вже userName є зарезервоване
	@Column(name="email")
	private String email;
	@Column(name="phone_number")
    private String phoneNumber;
	@Column(name="password")
    private String password;
	
	@ManyToMany
	@JoinTable(name="item_customer_connection",
	joinColumns=@JoinColumn(name="id_customer"),
	inverseJoinColumns=@JoinColumn(name="id_item_of_clothing"))
	private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String userName, String email, String phoneNumber,
			String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ItemOfClothing> getItemsOfClothing() {
		return itemsOfClothing;
	}

	public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
		this.itemsOfClothing = itemsOfClothing;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", userName=" + userName + ", email="
				+ email + ", phoneNumber=" + phoneNumber + ", password="
				+ password + "]";
	}
	

}
