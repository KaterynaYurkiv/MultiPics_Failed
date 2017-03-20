package ua.com.clothes_shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.service.ItemNameService;

public class ItemNameValidator implements Validator{
	
	private final ItemNameService itemNameService;

	public ItemNameValidator(ItemNameService itemNameService) {
		this.itemNameService = itemNameService;
	}


	@Override
	public boolean supports(Class<?> clazz) {
		return ItemName.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemName itemName = (ItemName) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "", "Can't be empty");
		if(itemNameService.findByName(itemName.getName())!=null){
			errors.rejectValue("name", "", "Already exists");
		}
	}

}
