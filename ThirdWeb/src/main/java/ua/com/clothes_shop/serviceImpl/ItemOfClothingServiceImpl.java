package ua.com.clothes_shop.serviceImpl;

import java.math.BigDecimal;
import java.util.List;


//import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import ua.com.clothes_shop.service.FileWriter;
import ua.com.clothes_shop.service.FileWriter.Folder;
import ua.com.clothes_shop.dao.ColorDao;
import ua.com.clothes_shop.dao.ImageDao;
import ua.com.clothes_shop.dao.ItemOfClothingDao;
import ua.com.clothes_shop.dto.filter.ItemOfClothingFilter;
import ua.com.clothes_shop.dto.form.ItemOfClothingForm;
import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.entity.Image;
import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.entity.ItemOfClothing;
import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.entity.TypeOfClothing;
import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.specification.ItemOfClothingSpecification;

@Service
// в контексті спрінга створює біни
public class ItemOfClothingServiceImpl implements ItemOfClothingService {

	@Autowired
	private ItemOfClothingDao itemOfClothingDao;

	@Autowired
	private ColorDao colorDao;

	@Autowired
	private ImageDao imageDao;

	@Autowired
	private FileWriter fileWriter;

	@Override
	@Transactional
	public void save(ItemOfClothingForm form) {
		ItemOfClothing entity = new ItemOfClothing();
		entity.setPrice(new BigDecimal(form.getPrice().replace(',', '.')));
		entity.setId(form.getId());
		entity.setItemName(form.getItemName());
		entity.setBrand(form.getBrand());
		entity.setMarking(new Integer(form.getMarking()));
		entity.setColor(form.getColor());
		entity.setSize(form.getSize());
		entity.setTargetAudience(form.getTargetAudience());
		entity.setTypeOfClothing(form.getTypeOfClothing());
		entity.setImages(form.getImages());
		// itemOfClothingDao.save(entity);
		itemOfClothingDao.save(entity);
		if (!form.getFile().isEmpty()) {
			Image image = new Image();
			image = imageDao.saveAndFlush(image);
			if (fileWriter.write(Folder.CLOTHES, form.getFile(), image.getId())) {
				image.setVersion(image.getVersion() + 1);
				image.getItemsOfClothing().add(entity);
				List<Image> images =  imageDao.findByItemId(entity.getId());
				images.forEach(e->e.getItemsOfClothing().add(entity));
				imageDao.save(image);
			}
		}
	}

	// @Override
	// @Transactional
	// public List<ItemOfClothing> findAll() {
	// List<ItemOfClothing> list = itemOfClothingDao.findAll();
	// for (ItemOfClothing itemOfClothing : list) {
	// Hibernate.initialize(itemOfClothing.getSizes());
	// }
	// return itemOfClothingDao.findAll();
	// }

	@Override
	public List<ItemOfClothing> findAll() {
		return itemOfClothingDao.findAll();
	}

	// @Override
	// public List<ItemOfClothing> findAllWithoutColorsAndSizes() {
	//
	// return itemOfClothingDao.findAllWithoutColorsAndSizes();
	// }

	@Override
	public ItemOfClothing findOne(int id) {
		return itemOfClothingDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		itemOfClothingDao.delete(id);
	}

	@Override
	public void update(ItemOfClothing itemOfClothing) {
		itemOfClothingDao.save(itemOfClothing);
	}

	// @Override
	// public ItemOfClothing findByMarking(int marking) {
	// return itemOfClothingDao.findByMarking(marking);
	// }
	//
	@Override
	public List<ItemOfClothing> findByColorId(int id) {
		return itemOfClothingDao.findByColorId(id);
	}

	@Override
	public List<ItemOfClothing> findByBrandId(int id) {
		return itemOfClothingDao.findByBrandId(id);
	}

	@Override
	public List<ItemOfClothing> findByTargetAudienceId(int id) {
		return itemOfClothingDao.findByTargetAudienceId(id);
	}

	@Override
	public List<ItemOfClothing> findByTypeOfClothingId(int id) {
		return itemOfClothingDao.findByTypeOfClothingId(id);
	}

	@Override
	public List<ItemOfClothing> findBySizeId(int id) {
		return itemOfClothingDao.findBySizeId(id);
	}

	@Override
	public List<ItemOfClothing> findByNameId(int id) {
		return itemOfClothingDao.findByNameId(id);
	}

	@Override
	public ItemOfClothingForm findForm(int id) {
		ItemOfClothing entity = findOne(id);
		ItemOfClothingForm form = new ItemOfClothingForm();
		form.setPrice(String.valueOf(entity.getPrice()));
		form.setId(entity.getId());
		form.setItemName(entity.getItemName());
		form.setMarking(String.valueOf(entity.getMarking()));
		form.setBrand(entity.getBrand());
		form.setTargetAudience(entity.getTargetAudience());
		form.setTypeOfClothing(entity.getTypeOfClothing());
		form.setSize(entity.getSize());
		form.setColor(entity.getColor());
		return form;
	}

	@Override
	public ItemOfClothing findUnique(String price, ItemName itemName,
			String marking, Brand brand, TargetAudience targetAudience,
			TypeOfClothing typeOfClothing, Size size, Color color) {
		return itemOfClothingDao.findUnique(
				new BigDecimal(price.replace(',', '.')), itemName.getId(),
				new Integer(marking), brand.getId(), targetAudience.getId(),
				typeOfClothing.getId(), size.getId(), color.getId());
	}

	@Override
	public Page<ItemOfClothing> findAll(Pageable pageable,
			ItemOfClothingFilter filter) {
		return itemOfClothingDao.findAll(
				new ItemOfClothingSpecification(filter), pageable);
	}

}
