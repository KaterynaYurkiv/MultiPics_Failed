package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.service.TypeOfClothingService;

@Controller
public class TypeOfClothingContrl {
	
	@Autowired
	private TypeOfClothingService typeOfClothingService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/type")
	public String index(Model model){
	model.addAttribute("typesOfClothing", typeOfClothingService.findAll());
	return "user-typeOfClothing";
}
	@RequestMapping("/user/type/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("typeOfClothing", typeOfClothingService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findByTypeOfClothingId(id));
		return "user-typeOfClothingItem";
	}

}
