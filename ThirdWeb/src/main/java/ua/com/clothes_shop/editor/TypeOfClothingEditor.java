package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.TypeOfClothing;
import ua.com.clothes_shop.service.TypeOfClothingService;

public class TypeOfClothingEditor extends PropertyEditorSupport{
	
	private final TypeOfClothingService typeOfClothingService;

	public TypeOfClothingEditor(TypeOfClothingService typeOfClothingService) {
		this.typeOfClothingService = typeOfClothingService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		TypeOfClothing typeOfClothing = typeOfClothingService.findOne(Integer.valueOf(text));
		setValue(typeOfClothing);
	}

}
