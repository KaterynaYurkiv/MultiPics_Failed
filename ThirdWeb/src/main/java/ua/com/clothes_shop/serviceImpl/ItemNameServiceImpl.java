package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.clothes_shop.dao.ItemNameDao;
import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.service.ItemNameService;

@Service
public class ItemNameServiceImpl implements ItemNameService{
	
	@Autowired
	private ItemNameDao itemNameDao;

	@Override
	public void save(ItemName itemName) {
		itemNameDao.save(itemName);
	}

	@Override
	public List<ItemName> findAll() {
		return itemNameDao.findAll();
	}

	@Override
	public ItemName findOne(int id) {
		return itemNameDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		try{
		    itemNameDao.delete(id);
		} catch(Exception e){

		}
	}

	@Override
	public void update(ItemName itemName) {
		itemNameDao.save(itemName);
	}

	@Override
	public ItemName findByName(String name) {
		return itemNameDao.findByName(name);
	}

	@Override
	public Page<ItemName> findAll(Pageable pageable, SimpleFilter filter) {
		return itemNameDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<ItemName> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("name")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
