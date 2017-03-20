package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.service.SizeService;

@Controller
public class SizeContrl {
	
	@Autowired
	private SizeService sizeService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/size")
	public String index(Model model){
	model.addAttribute("sizes", sizeService.findAll());
	return "user-size";
    }
	
	@RequestMapping("/user/size/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("size", sizeService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findBySizeId(id));
		return "user-sizeItem";
	}

}
