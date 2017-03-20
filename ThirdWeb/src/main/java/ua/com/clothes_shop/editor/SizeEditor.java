package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.service.SizeService;

public class SizeEditor extends PropertyEditorSupport{
	
	private final SizeService sizeService;

	public SizeEditor(SizeService sizeService) {
		this.sizeService = sizeService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		Size size = sizeService.findOne(Integer.valueOf(text));
		setValue(size);
	}

}
