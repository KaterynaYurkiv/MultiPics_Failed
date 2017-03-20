package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.TypeOfClothing;

public interface TypeOfClothingDao extends JpaRepository<TypeOfClothing, Integer>, JpaSpecificationExecutor<TypeOfClothing>{
	
	TypeOfClothing findByItemType(String itemType);

}
