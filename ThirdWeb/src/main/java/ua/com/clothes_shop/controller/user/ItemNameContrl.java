package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.ItemNameService;
import ua.com.clothes_shop.service.ItemOfClothingService;

@Controller
public class ItemNameContrl {
	
	@Autowired
	private ItemNameService itemNameService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/itemName")
	public String index(Model model){
	model.addAttribute("itemNames", itemNameService.findAll());
	return "user-itemName";
    }
	
	@RequestMapping("/user/itemName/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("itemName", itemNameService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findByNameId(id));
		return "user-itemNameItem";
	}

}
