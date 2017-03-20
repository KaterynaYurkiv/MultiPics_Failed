package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.TargetAudience;

public interface TargetAudienceDao extends JpaRepository<TargetAudience, Integer>, JpaSpecificationExecutor<TargetAudience>{
	
	TargetAudience findByCategory(String category);

}
