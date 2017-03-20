package ua.com.clothes_shop.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.TargetAudience;

public interface TargetAudienceService {
	
    void save(TargetAudience targetAudience);
    
    void save(String category);
	
	List<TargetAudience> findAll();
	
	TargetAudience findOne(int id);
	
	void delete(int id);
	
	void update (TargetAudience targetAudience);
	
	TargetAudience findByCategory(String category);
	
	Page<TargetAudience> findAll(Pageable pageable, SimpleFilter filter);

}
