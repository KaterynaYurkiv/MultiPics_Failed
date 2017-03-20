package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.com.clothes_shop.dao.TargetAudienceDao;
import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.service.TargetAudienceService;

@Service //в контексті спрінга створює біни
public class TargetAudienceServiceImpl implements TargetAudienceService{
	
	@Autowired
	private TargetAudienceDao targetAudienceDao;

	@Override
	public void save(TargetAudience targetAudience) {
		targetAudienceDao.save(targetAudience);
	}

	@Override
	public List<TargetAudience> findAll() {
		return targetAudienceDao.findAll();
	}

	@Override
	public TargetAudience findOne(int id) {
		return targetAudienceDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		try{
			targetAudienceDao.delete(id);
		} catch(Exception e){

		}
	}

	@Override
	public void update(TargetAudience targetAudience) {
		targetAudienceDao.save(targetAudience);
	}

	@Override
	public void save(String category) {
		TargetAudience targetAudience = new TargetAudience();
		targetAudience.setCategory(category);
		targetAudienceDao.save(targetAudience);
	}

	@Override
	public TargetAudience findByCategory(String category) {
		return targetAudienceDao.findByCategory(category);
	}

	@Override
	public Page<TargetAudience> findAll(Pageable pageable, SimpleFilter filter) {
		return targetAudienceDao.findAll(findByNameLike(filter), pageable);
	}
	
	private Specification<TargetAudience> findByNameLike(SimpleFilter filter) {
		return (root, query, cb)->{
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(cb.lower(root.get("category")), filter.getSearch().toLowerCase()+"%");
		};
	}

}
