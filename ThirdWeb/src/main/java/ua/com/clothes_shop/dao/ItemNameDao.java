package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.ItemName;

public interface ItemNameDao extends JpaRepository<ItemName, Integer>, JpaSpecificationExecutor<ItemName>{
	
	ItemName findByName(String name);

}
