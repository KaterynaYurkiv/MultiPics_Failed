package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.TypeOfClothing;

public interface TypeOfClothingService {
	
    void save(TypeOfClothing typeOfClothing);
    
    void save(String itemType);
	
	List<TypeOfClothing> findAll();
	
	TypeOfClothing findOne(int id);
	
	void delete(int id);
	
	void update (TypeOfClothing typeOfClothing);
	
	TypeOfClothing findByItemType(String itemType);
	
	Page<TypeOfClothing> findAll(Pageable pageable, SimpleFilter filter);

}
