package ua.com.clothes_shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.service.SizeService;

public class SizeValidator implements Validator{
	
	private final SizeService sizeService;

	public SizeValidator(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Size.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Size size = (Size) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "size", "", "Can't be empty");
		if(sizeService.findBySize(size.getSize())!=null){
			errors.rejectValue("size", "", "Already exists");
		}
	}

}
