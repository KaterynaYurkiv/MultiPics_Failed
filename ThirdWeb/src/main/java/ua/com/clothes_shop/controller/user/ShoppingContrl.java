package ua.com.clothes_shop.controller.user;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import ua.com.clothes_shop.dto.filter.ItemOfClothingFilter;
import ua.com.clothes_shop.dto.form.ItemOfClothingForm;
import ua.com.clothes_shop.editor.BrandEditor;
import ua.com.clothes_shop.editor.ColorEditor;
import ua.com.clothes_shop.editor.ItemNameEditor;
import ua.com.clothes_shop.editor.SizeEditor;
import ua.com.clothes_shop.editor.TargetAudienceEditor;
import ua.com.clothes_shop.editor.TypeOfClothingEditor;
import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.entity.Color;
import ua.com.clothes_shop.entity.ItemName;
import ua.com.clothes_shop.entity.Size;
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.entity.TypeOfClothing;
import ua.com.clothes_shop.entity.User;
import ua.com.clothes_shop.service.BrandService;
import ua.com.clothes_shop.service.ColorService;
import ua.com.clothes_shop.service.ItemNameService;
import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.service.SizeService;
import ua.com.clothes_shop.service.TargetAudienceService;
import ua.com.clothes_shop.service.TypeOfClothingService;
import ua.com.clothes_shop.service.UserService;


@Controller
@RequestMapping("/shopping")
@SessionAttributes("itemOfClothing")
public class ShoppingContrl {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ItemOfClothingService itemOfClothingService;
	
	@Autowired
	private ItemNameService itemNameService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private TargetAudienceService targetAudienceService;
	
	@Autowired
	private TypeOfClothingService typeOfClothingService;
	
	@Autowired
	private ColorService colorService;
	
	@Autowired
	private SizeService sizeService;
	
	@InitBinder("itemOfClothing")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Color.class, new ColorEditor(colorService));
		binder.registerCustomEditor(ItemName.class, new ItemNameEditor(itemNameService));
		binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
		binder.registerCustomEditor(TargetAudience.class, new TargetAudienceEditor(targetAudienceService));
		binder.registerCustomEditor(TypeOfClothing.class, new TypeOfClothingEditor(typeOfClothingService));
	}
	
	@ModelAttribute("filter")
		public ItemOfClothingFilter getFilter(){
			return new ItemOfClothingFilter();
		}

// До валідації тут було просто 	public ItemOfClothing getForm(){ return new ItemOfClothing();}
	@ModelAttribute("itemOfClothing")
	public ItemOfClothingForm getForm(){
		return new ItemOfClothingForm();
	}
	
	//витягуємо всі значення з таблиць, які світяться зеленим коли навести на таблицю ItemOfClothing, для того щоб зробити селекти
	@GetMapping
	public String show(Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") ItemOfClothingFilter filter){
		model.addAttribute("page", itemOfClothingService.findAll(pageable, filter));
		model.addAttribute("itemsOfClothing", itemOfClothingService.findAll());
		model.addAttribute("itemNames", itemNameService.findAll());
		model.addAttribute("brands", brandService.findAll());
		model.addAttribute("targetAudiences", targetAudienceService.findAll());
		model.addAttribute("typesOfClothing", typeOfClothingService.findAll());
		model.addAttribute("colors", colorService.findAll());
		model.addAttribute("sizes", sizeService.findAll());
		return "user-shopping";
	}
	
//	@GetMapping("/addToCart/{itemId}")
//	public String buy(@CookieValue(defaultValue="0", name="userId") int userId, @PathVariable int itemId){
//		System.out.println(userId);
//		userService.addToShoppingCart(userId, itemId);
//		return "redirect:/shopping";
//	}
	
	@GetMapping("/addToCart/{itemId}")
	public String buy(Principal principal, @PathVariable int itemId){
		if(principal!=null){
 			String email = principal.getName();
 			User user = userService.findByEmail(email);
 			int userId = user.getId();
 			userService.addToShoppingCart(userId, itemId);
 		}
		else{
//			int userId = 0;
// 			userService.addToShoppingCart(userId, itemId);
			return "redirect:/shopping";
 		}
		return "redirect:/shopping";
	}
	
//	@RequestMapping
//	public String show(Model model){
//		model.addAttribute("itemsOfClothing", itemOfClothingService.findAll());
//		return "admin-itemOfClothing";
//	}
	


}
