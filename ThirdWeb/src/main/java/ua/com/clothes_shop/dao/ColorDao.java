package ua.com.clothes_shop.dao;

//import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import ua.com.clothes_shop.entity.Color;

public interface ColorDao extends JpaRepository<Color, Integer>, JpaSpecificationExecutor<Color>{
	
//	@Query("select c from Color c left join fetch c.itemsOfClothing i where i.marking=:param")
//	Color fetchColorWithItemsOfClothing(@Param("param")int marking);
//	
//	@Query("SELECT c FROM Color c JOIN c.itemsOfClothing i WHERE i.id = ?1")
//	List<Color> findByItemOfClothingId(int id);
	
	Color findByColor(String color);
}
