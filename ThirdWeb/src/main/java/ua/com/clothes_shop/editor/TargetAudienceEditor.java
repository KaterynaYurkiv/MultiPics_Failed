package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.service.TargetAudienceService;

public class TargetAudienceEditor extends PropertyEditorSupport{
	
	private final TargetAudienceService targetAudienceService;

	public TargetAudienceEditor(TargetAudienceService targetAudienceService) {
		this.targetAudienceService = targetAudienceService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TargetAudience targetAudience = targetAudienceService.findOne(Integer.valueOf(text));
		setValue(targetAudience);
	}

}
