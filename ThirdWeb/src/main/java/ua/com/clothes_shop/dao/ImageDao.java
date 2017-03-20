package ua.com.clothes_shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.com.clothes_shop.entity.Image;

public interface ImageDao  extends JpaRepository<Image, Integer>, JpaSpecificationExecutor<Image>{

	@Query("SELECT i FROM Image i join i.itemsOfClothing ioc where ioc.id = ?1")
	List<Image> findByItemId(int id);

}
