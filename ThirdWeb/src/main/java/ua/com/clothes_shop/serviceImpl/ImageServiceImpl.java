package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import ua.com.clothes_shop.dao.ImageDao;
import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.Image;
import ua.com.clothes_shop.service.FileWriter;
import ua.com.clothes_shop.service.ImageService;
import ua.com.clothes_shop.service.FileWriter.Folder;

@Service
public class ImageServiceImpl implements ImageService{
	
	@Autowired
	private ImageDao imageDao;
	
	@Autowired
	private FileWriter fileWriter;

	@Override
	public void save(Image entity) {
		MultipartFile file = entity.getFile();
		entity = imageDao.saveAndFlush(entity);
		if(fileWriter.write(Folder.CLOTHES, file, entity.getId())){
			entity.setVersion(entity.getVersion()+1);
			imageDao.save(entity);
		}
	}

	@Override
	public List<Image> findAll() {
		return imageDao.findAll();
	}

//	@Override
//	public Image findOne(int id) {
//		return imageDao.findOne(id);
//	}
//
//	@Override
//	public void delete(int id) {
//		imageDao.delete(id);
//	}
//
//	@Override
//	public void update(Image image) {
//		imageDao.save(image);
//	}
//
//	@Override
//	public Page<Image> findAll(Pageable pageable, SimpleFilter filter) {
//		return imageDao.findAll(findByNameLike(filter), pageable);
//	}
//	
//	private Specification<Image> findByNameLike(SimpleFilter filter) {
//		return (root, query, cb)->{
//			if(filter.getSearch().isEmpty()) return null;
//			return cb.like(cb.lower(root.get("" + "version")), filter.getSearch().toLowerCase()+"%");
//		};
//	}

}
