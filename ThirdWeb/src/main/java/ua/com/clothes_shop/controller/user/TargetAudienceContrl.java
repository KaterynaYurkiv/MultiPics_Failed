package ua.com.clothes_shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.service.TargetAudienceService;

@Controller
public class TargetAudienceContrl {
	
	@Autowired
	private TargetAudienceService targetAudienceService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@RequestMapping("/user/targeta")
	public String index(Model model){
	model.addAttribute("targetAudiences", targetAudienceService.findAll());
	return "user-targetAudience";
    }
	
	@RequestMapping("/user/targeta/{id}")
	public String category(@PathVariable int id, Model model){
		model.addAttribute("targetAudience", targetAudienceService.findOne(id));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findByTargetAudienceId(id));
		return "user-targetAudienceItem";
	}

}
