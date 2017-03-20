package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.Size;

public interface SizeDao extends JpaRepository<Size, Integer>, JpaSpecificationExecutor<Size>{
	
	Size findBySize(String size);

}
