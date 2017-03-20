package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.Brand;

public interface BrandDao extends JpaRepository<Brand, Integer>, JpaSpecificationExecutor<Brand>{
	
	Brand findByBrandName(String brandName);

}
