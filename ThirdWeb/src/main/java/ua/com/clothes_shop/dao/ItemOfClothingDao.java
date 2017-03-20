package ua.com.clothes_shop.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.clothes_shop.entity.ItemOfClothing;

public interface ItemOfClothingDao extends JpaRepository<ItemOfClothing, Integer>, JpaSpecificationExecutor<ItemOfClothing>{
	
	ItemOfClothing findByMarking(int marking);
	
	//робимо distinct, щоб вит€гувати екземпл€рм, €к≥ в≥др≥зн€ютьс€
//	@Query("SELECT distinct i FROM ItemOfClothing i LEFT JOIN FETCH i.brand LEFT JOIN FETCH i.targetAudience LEFT JOIN FETCH i.typeOfClothing JOIN FETCH i.colors")
//	List<ItemOfClothing> findAll();
	
	@Query("SELECT DISTINCT i FROM ItemOfClothing i LEFT JOIN FETCH i.brand LEFT JOIN FETCH i.targetAudience LEFT JOIN FETCH i.typeOfClothing LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size LEFT JOIN FETCH i.itemName JOIN FETCH i.images")
	List<ItemOfClothing> findAll();
	
	@Query("SELECT DISTINCT i FROM ItemOfClothing i LEFT JOIN FETCH i.brand LEFT JOIN FETCH i.targetAudience LEFT JOIN FETCH i.typeOfClothing LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size LEFT JOIN FETCH i.itemName JOIN FETCH i.images WHERE i.id=?1")
	ItemOfClothing findOne(int id);
//	
//	@Query("SELECT i FROM ItemOfClothing i LEFT JOIN FETCH i.brand LEFT JOIN FETCH i.targetAudience LEFT JOIN FETCH i.typeOfClothing")
//	List<ItemOfClothing> findAllWithoutColorsAndSizes();
//	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.color.id = ?1")
	List<ItemOfClothing> findByColorId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.brand.id = ?1")
	List<ItemOfClothing> findByBrandId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.targetAudience.id = ?1")
	List<ItemOfClothing> findByTargetAudienceId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.typeOfClothing.id = ?1")
	List<ItemOfClothing> findByTypeOfClothingId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.size.id = ?1")
	List<ItemOfClothing> findBySizeId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.itemName.id = ?1")
	List<ItemOfClothing> findByNameId(int id);
	
	@Query("SELECT i FROM ItemOfClothing i WHERE i.price = ?1 and i.itemName.id = ?2 and i.marking = ?3 and i.brand.id = ?4 and i.targetAudience.id = ?5 and i.typeOfClothing.id = ?6 and i.size.id = ?7 and i.color.id = ?8")
	ItemOfClothing findUnique(BigDecimal price, int itemNameId, int marking, int brandId, int targetAudienceId, int typeOfClothingId, int sizeId, int colorId);
	
	@Query(value="SELECT DISTINCT i FROM ItemOfClothing i LEFT JOIN FETCH i.brand LEFT JOIN FETCH i.targetAudience LEFT JOIN FETCH i.typeOfClothing LEFT JOIN FETCH i.color LEFT JOIN FETCH i.size LEFT JOIN FETCH i.itemName JOIN FETCH i.images",
	 			countQuery="SELECT count(i.id) FROM ItemOfClothing i")
	 	Page<ItemOfClothing> findAll(Pageable pageable);


}
