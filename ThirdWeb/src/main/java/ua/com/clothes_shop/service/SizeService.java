package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.Size;

public interface SizeService {
	
    void save(Size size);
    
    void save(String size);
	
	List<Size> findAll();
	
	Size findOne(int id);
	
	void delete(int id);
	
	void update (Size size);
	
	Size findBySize(String size);
	
	Page<Size> findAll(Pageable pageable, SimpleFilter filter);

}
