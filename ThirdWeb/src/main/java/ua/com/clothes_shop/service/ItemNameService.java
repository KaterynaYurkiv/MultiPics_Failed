package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.ItemName;

public interface ItemNameService {
	
    void save(ItemName itemName);
	
	List<ItemName> findAll();
	
	ItemName findOne(int id);
	
	void delete(int id);
	
	void update (ItemName itemName);
	
	ItemName findByName(String name);
	
	Page<ItemName> findAll(Pageable pageable, SimpleFilter filter);

}
