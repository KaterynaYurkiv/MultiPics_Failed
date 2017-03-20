package ua.com.clothes_shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.entity.TypeOfClothing;
import ua.com.clothes_shop.service.TypeOfClothingService;

public class TypeOfClothingValidator implements Validator{
	
	private final TypeOfClothingService typeOfClothingService;

	public TypeOfClothingValidator(TypeOfClothingService typeOfClothingService) {
		this.typeOfClothingService = typeOfClothingService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TypeOfClothing.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TypeOfClothing typeOfClothing = (TypeOfClothing) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemType", "", "Can't be empty");
		if(typeOfClothingService.findByItemType(typeOfClothing.getItemType())!=null){
			errors.rejectValue("itemType", "", "Already exists");
		}
	}

}
