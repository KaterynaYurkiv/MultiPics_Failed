package ua.com.clothes_shop.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//Означає що це сутність БД
@Entity
//для того щоб таблиці мали назву як і в БД
// якщо кілька індексів писати, то треба буде брати в {} напр. indexes={@Index(columnList = "time, title"),@Index(columnList = "time"), @Index(columnList = "title")}
@Table(name="brand", indexes=@Index(columnList = "brand_name"))
public class Brand {
	
	//Первинний ключ
		@Id
		//AUTO_INCTEMENT
		@GeneratedValue(strategy=GenerationType.IDENTITY)
		private int id;
		//для того щоб стовпці мали назву як і в БД
		@Column(name="brand_name")
		private String brandName;
		
		@OneToMany(mappedBy="brand")
		//@OneToMany(mappedBy="brand",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
		//FetchType - Lazy по замовчуванню (загружає лише "первинні поля класу"), а Eager підгружає ще і ліст речей, але це надлишок, якщо буде потрібно викликати можна окремо
		//cascade = CascadeType.REMOVE означає, що коли ми видалимо бренд, то автоматично видалиться і товар, який з ним був зв'язаний
		private List<ItemOfClothing> itemsOfClothing = new ArrayList<>();
		
		public Brand() {
			// TODO Auto-generated constructor stub
		}

		public Brand(String brandName) {
			super();
			this.brandName = brandName;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getBrandName() {
			return brandName;
		}

		public void setBrandName(String brandName) {
			this.brandName = brandName;
		}

		public List<ItemOfClothing> getItemsOfClothing() {
			return itemsOfClothing;
		}

		public void setItemsOfClothing(List<ItemOfClothing> itemsOfClothing) {
			this.itemsOfClothing = itemsOfClothing;
		}

		@Override
		public String toString() {
			return "Brand [id=" + id + ", brandName=" + brandName + "]";
		}

}
