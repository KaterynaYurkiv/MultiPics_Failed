package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.service.BrandService;

public class BrandEditor extends PropertyEditorSupport{
	
	private final BrandService brandService;

	public BrandEditor(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Brand brand = brandService.findOne(Integer.valueOf(text));
		setValue(brand);
	}

}
