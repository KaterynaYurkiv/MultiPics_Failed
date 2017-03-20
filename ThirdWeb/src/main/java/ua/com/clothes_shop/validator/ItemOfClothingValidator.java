package ua.com.clothes_shop.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.dto.form.ItemOfClothingForm;
import ua.com.clothes_shop.service.ItemOfClothingService;

public class ItemOfClothingValidator implements Validator{
	
	private static final  Pattern REG = Pattern.compile("^([0-9]{1,17}\\.[0-9]{1,2})|([0-9]{1,17}\\,[0-9]{1,2})|([0-9]{1,17})$");
	
	private static final  Pattern REGS = Pattern.compile("^[0-9]+$");

	private final ItemOfClothingService itemOfClothingService;

	public ItemOfClothingValidator(ItemOfClothingService itemOfClothingService) {
		this.itemOfClothingService = itemOfClothingService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return ItemOfClothingForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ItemOfClothingForm form = (ItemOfClothingForm)target;
		if(!REG.matcher(form.getPrice()).matches()){
			errors.rejectValue("price", "", "Enter the price! You can enter only digits separated by '.' or ',' ");
		}
		if(!REGS.matcher(form.getMarking()).matches()){
			errors.rejectValue("marking", "", "Enter marking! Only digits allowed ");
		}
		if(errors.getFieldError("price")==null && errors.getFieldError("marking")==null){
			if(itemOfClothingService.findUnique(form.getPrice(), form.getItemName(), form.getMarking(), form.getBrand(), form.getTargetAudience(), form.getTypeOfClothing(), form.getSize(), form.getColor())!=null){
				// пишемо для itemName бо воно перше поле в jsp і над ним будемо виводити повідомлення якщо такий об'єкт вже є в базі
				errors.rejectValue("itemName", "", "This Item already exists in the database");
			}
		}
	}

}
