package ua.com.clothes_shop.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.service.TargetAudienceService;

public class TargetAudienceValidator implements Validator{
	
	private final TargetAudienceService targetAudienceService;

	public TargetAudienceValidator(TargetAudienceService targetAudienceService) {
		this.targetAudienceService = targetAudienceService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return TargetAudience.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		TargetAudience targetAudience = (TargetAudience) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "category", "", "Can't be empty");
		if(targetAudienceService.findByCategory(targetAudience.getCategory())!=null){
			errors.rejectValue("category", "", "Already exists");
		}
	}

}
