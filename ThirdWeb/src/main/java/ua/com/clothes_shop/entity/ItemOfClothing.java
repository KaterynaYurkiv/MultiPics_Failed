package ua.com.clothes_shop.entity;

import java.math.BigDecimal;
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
import javax.persistence.ManyToOne;

import javax.persistence.Table;


//Означає що це сутність БД
@Entity
//для того щоб таблиці мали назву як і в БД
@Table(name="item_of_clothing")
public class ItemOfClothing {
	
	//Первинний ключ
		@Id
		//AUTO_INCTEMENT
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		
		//для того щоб стовпці мали назву як і в БД
		@Column(name="marking")
		private int marking;
		
		@Column(name="price")
		private BigDecimal price;
		
		@ManyToOne
		private ItemName itemName;
		
		@ManyToOne
		private TargetAudience targetAudience;
		
		@ManyToOne
		private TypeOfClothing typeOfClothing;
		
		@ManyToOne
		private Brand brand;
		
		@ManyToOne
		private Color color;
		
		@ManyToOne
		private Size size;

		@ManyToMany
		@JoinTable(name="item_customer_connection",
		joinColumns=@JoinColumn(name="id_item_of_clothing"),
		inverseJoinColumns=@JoinColumn(name="id_customer"))
		private List<Customer> customers = new ArrayList<>();
		
		@ManyToMany
		@JoinTable(name="item_cart_connection",
		joinColumns=@JoinColumn(name="id_item_of_clothing"),
		inverseJoinColumns=@JoinColumn(name="id_shopping_cart"))
		private List<ShoppingCart> shoppingCarts = new ArrayList<>();
		
		@ManyToMany
		@JoinTable(name="item_image_connection",
		joinColumns=@JoinColumn(name="id_item_of_clothing"),
		inverseJoinColumns=@JoinColumn(name="id_image"))
		private List<Image> images = new ArrayList<>();
		
		public List<Image> getImages() {
			return images;
		}

		public void setImages(List<Image> images) {
			this.images = images;
		}

		public List<ShoppingCart> getShoppingCarts() {
			return shoppingCarts;
		}

		public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
			this.shoppingCarts = shoppingCarts;
		}

		public ItemOfClothing() {
			// TODO Auto-generated constructor stub
		}

		public ItemOfClothing(int marking, BigDecimal price) {
			super();
			this.marking = marking;
			this.price = price;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getMarking() {
			return marking;
		}

		public void setMarking(int marking) {
			this.marking = marking;
		}

		public BigDecimal getPrice() {
			return price;
		}

		public void setPrice(BigDecimal price) {
			this.price = price;
		}

		public TargetAudience getTargetAudience() {
			return targetAudience;
		}

		public void setTargetAudience(TargetAudience targetAudience) {
			this.targetAudience = targetAudience;
		}

		public TypeOfClothing getTypeOfClothing() {
			return typeOfClothing;
		}

		public void setTypeOfClothing(TypeOfClothing typeOfClothing) {
			this.typeOfClothing = typeOfClothing;
		}

		public Brand getBrand() {
			return brand;
		}

		public void setBrand(Brand brand) {
			this.brand = brand;
		}

		public Color getColor() {
			return color;
		}

		public void setColor(Color color) {
			this.color = color;
		}

		public Size getSize() {
			return size;
		}

		public void setSize(Size size) {
			this.size = size;
		}

		public List<Customer> getCustomers() {
			return customers;
		}

		public void setCustomers(List<Customer> customers) {
			this.customers = customers;
		}

		public ItemName getItemName() {
			return itemName;
		}

		public void setItemName(ItemName itemName) {
			this.itemName = itemName;
		}

		@Override
		public String toString() {
			return "ItemOfClothing [id=" + id + ", marking=" + marking
					+ ", price=" + price + "]";
		}
		

}
