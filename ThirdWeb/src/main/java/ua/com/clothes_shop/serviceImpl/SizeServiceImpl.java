package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.clothes_shop.dao.SizeDao;
import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.service.SizeService;

@Service //в контексті спрінга створює біни
public class SizeServiceImpl implements SizeService{
	
	@Autowired
	private SizeDao sizeDao;

	@Override
	public void save(Size size) {
		size.getSize().toUpperCase();  //можна прописувати додаткову логіку
		sizeDao.save(size);
	}

	@Override
	public List<Size> findAll() {
		return sizeDao.findAll();
	}

	@Override
	public Size findOne(int id) {
		return sizeDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		try{
		    sizeDao.delete(id);
		} catch(Exception e){

		}
	}

	@Override
	public void update(Size size) {
		sizeDao.save(size);
	}

	@Override
	public void save(String size) {
		Size s = new Size();
		s.setSize(size);
		sizeDao.save(s);
	}

	@Override
	public Size findBySize(String size) {
		return sizeDao.findBySize(size);
	}

	@Override
	public Page<Size> findAll(Pageable pageable, SimpleFilter filter) {
		return sizeDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<Size> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("size")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
