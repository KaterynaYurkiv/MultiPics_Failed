package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.ItemOfClothingFilter;
import ua.com.clothes_shop.dto.form.ItemOfClothingForm;
import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.entity.ItemOfClothing;
import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.entity.TypeOfClothing;

public interface ItemOfClothingService {
	
	//коли робимо валідацію пишемо вже ItemOfClothingForm, а не ItemOfClothing..
    void save(ItemOfClothingForm itemOfClothing);
	
	List<ItemOfClothing> findAll();
	
//	List<ItemOfClothing> findAllWithoutColorsAndSizes();
	
	ItemOfClothing findOne(int id);
	
	void delete(int id);
	
	void update (ItemOfClothing itemOfClothing);
	
//	ItemOfClothing findByMarking(int marking);
	
	List<ItemOfClothing> findByColorId(int id);
	
	List<ItemOfClothing> findByBrandId (int id);
	
	List<ItemOfClothing> findByTargetAudienceId (int id);
	
	List<ItemOfClothing> findByTypeOfClothingId (int id);
	
	List<ItemOfClothing> findBySizeId (int id);
	
	List<ItemOfClothing> findByNameId (int id);
	
	ItemOfClothingForm findForm(int id);
	
	ItemOfClothing findUnique(String price, ItemName itemName, String marking, Brand brand, TargetAudience targetAudience, TypeOfClothing typeOfClothing, Size size, Color color);
	
	Page<ItemOfClothing> findAll(Pageable pageable, ItemOfClothingFilter filter);

}
