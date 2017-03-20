package ua.com.clothes_shop.dto.form;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.entity.Image;
import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.entity.TypeOfClothing;

public class ItemOfClothingForm {
	
	//важливо тут змінити числові поля на стрічкові
	//в даному випадку замінили маркування і ціну на стрінг

	private int id;
	
	private String marking;
	
	private String price;
	
	private ItemName itemName;
	
	private TargetAudience targetAudience;
	
	private TypeOfClothing typeOfClothing;
	
	private Brand brand;
	
	private Color color;

	private Size size;
	
    private List<Image> images;
	
	public List<Image> getImages() {
		return images;
	}
	public void setImages(List<Image> images) {
		this.images = images;
	}

	private MultipartFile file;

	public MultipartFile getFile() {
			return file;
	 }
	 public void setFile(MultipartFile file) {
	 		this.file = file;
	 }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarking() {
		return marking;
	}

	public void setMarking(String marking) {
		this.marking = marking;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public ItemName getItemName() {
		return itemName;
	}

	public void setItemName(ItemName itemName) {
		this.itemName = itemName;
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

}
