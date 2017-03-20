package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.clothes_shop.dao.ColorDao;
import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.service.ColorService;
import ua.com.clothes_shop.service.FileWriter;
import ua.com.clothes_shop.service.FileWriter.Folder;

@Service //в контексті спрінга створює біни
public class ColorServiceImpl implements ColorService{
	
	@Autowired
	private ColorDao colorDao;
	
	//робим для того, щоб зв'язувати з картинками
	@Autowired
	private FileWriter fileWriter;

//	@Override
//	public void save(Color color) {
//		colorDao.save(color);
//	}
	
	//зберігаємо сутність і якщо додали картинку до неї, щоб перезаписувалось це в БД
	@Override
	public void save(Color entity) {
		MultipartFile file = entity.getFile();
		entity = colorDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.COLOR, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			colorDao.save(entity);
		}
	}

	@Override
	public List<Color> findAll() {
		return colorDao.findAll();
	}

	@Override
	public Color findOne(int id) {
		return colorDao.findOne(id);
	}

//	@Override
//	public void delete(int id) {
//		colorDao.delete(id);
//	}
	
	//try catch для того, щоб при видалені об'єкту, який використовується в іншій таблиці не вилітала помилка
	@Override
	public void delete(int id) {
		try{
		    colorDao.delete(id);
		} catch(Exception e){

		}
	}

	@Override
	public void update(Color color) {
		colorDao.save(color);
	}

	@Override
	public Color findByColor(String color) {
		return colorDao.findByColor(color);
	}

	@Override
	public Page<Color> findAll(Pageable pageable, SimpleFilter filter) {
		return colorDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<Color> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("color")), filter.getSearch().toLowerCase()+"%");
		};
	}


//	@Override
//	public Color fetchColorWithItemsOfClothing(int marking) {
//		return colorDao.fetchColorWithItemsOfClothing(marking);
//	}

//	@Override
//	public List<Color> findByItemOfClothingId(int id) {
//		return colorDao.findByItemOfClothingId(id);
//	}

}
