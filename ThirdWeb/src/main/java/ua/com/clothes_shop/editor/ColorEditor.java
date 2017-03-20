package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.service.ColorService;

public class ColorEditor extends PropertyEditorSupport{
	
	private final ColorService colorService;

	public ColorEditor(ColorService colorService) {
		this.colorService = colorService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Color color = colorService.findOne(Integer.valueOf(text));
		setValue(color);
	}

}
