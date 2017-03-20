package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.BrandService;
import ua.com.clothes_shop.service.ItemOfClothingService;

@Controller
public class BrandContrl {
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/brand")
	public String index(Model model){
	model.addAttribute("brands", brandService.findAll());
	return "user-brand";
}
	@RequestMapping("/user/brand/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("brand", brandService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findByBrandId(id));
		return "user-brandItem";
	}

}
