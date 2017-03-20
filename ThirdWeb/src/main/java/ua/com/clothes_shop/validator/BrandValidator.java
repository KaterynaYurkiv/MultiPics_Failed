package ua.com.clothes_shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.service.BrandService;

public class BrandValidator implements Validator{
	
	private final BrandService brandService;
	
	public BrandValidator(BrandService brandService) {
		this.brandService = brandService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Brand.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Brand brand = (Brand) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "brandName", "", "Can't be empty");
		if(brandService.findByBrandName(brand.getBrandName())!=null){
			errors.rejectValue("brandName", "", "Already exists");
		}
	}

}
