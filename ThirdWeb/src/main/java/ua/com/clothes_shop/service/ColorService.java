package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.Color;

public interface ColorService {
	
    void save(Color color);
	
	List<Color> findAll();
	
	Color findOne(int id);
	
	void delete(int id);
	
	void update (Color color);
	
	Color findByColor(String color);
	
	Page<Color> findAll(Pageable pageable, SimpleFilter filter);
	
//	Color fetchColorWithItemsOfClothing(int marking);
//	
//	List<Color> findByItemOfClothingId(int id);

}
