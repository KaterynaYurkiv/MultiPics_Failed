package ua.com.clothes_shop.editor;

import java.beans.PropertyEditorSupport;

import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.service.ItemNameService;

public class ItemNameEditor extends PropertyEditorSupport{
	
	private final ItemNameService itemNameService;

	public ItemNameEditor(ItemNameService itemNameService) {
		this.itemNameService = itemNameService;
	}

	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		ItemName itemName = itemNameService.findOne(Integer.valueOf(text));
		setValue(itemName);
	}

}
