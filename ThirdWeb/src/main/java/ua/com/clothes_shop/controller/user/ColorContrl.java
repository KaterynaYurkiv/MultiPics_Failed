package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.ColorService;
import ua.com.clothes_shop.service.ItemOfClothingService;

@Controller
public class ColorContrl {
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/color")
	public String index(Model model){
	model.addAttribute("colors", colorService.findAll());
	return "user-color";
    }
	
	@RequestMapping("/user/color/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("color", colorService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findByColorId(id));
		return "user-colorItem";
	}

}
