package ua.com.clothes_shop.controller.admin;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

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
import ua.com.clothes_shop.service.BrandService;
import ua.com.clothes_shop.service.ColorService;
import ua.com.clothes_shop.service.ImageService;
import ua.com.clothes_shop.service.ItemNameService;
import ua.com.clothes_shop.service.ItemOfClothingService;
import ua.com.clothes_shop.service.SizeService;
import ua.com.clothes_shop.service.TargetAudienceService;
import ua.com.clothes_shop.service.TypeOfClothingService;
import ua.com.clothes_shop.validator.ItemOfClothingValidator;
import ua.com.clothes_shop.util.ParamBuilder;
//import static ua.com.clothes_shop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/ioc")
@SessionAttributes("itemOfClothing")
public class ItemOfClothingController {
	
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
	
	@Autowired
	private ImageService imageService;
	
	@InitBinder("itemOfClothing")
	protected void bind(WebDataBinder binder){
		binder.registerCustomEditor(Brand.class, new BrandEditor(brandService));
		binder.registerCustomEditor(Color.class, new ColorEditor(colorService));
		binder.registerCustomEditor(ItemName.class, new ItemNameEditor(itemNameService));
		binder.registerCustomEditor(Size.class, new SizeEditor(sizeService));
		binder.registerCustomEditor(TargetAudience.class, new TargetAudienceEditor(targetAudienceService));
		binder.registerCustomEditor(TypeOfClothing.class, new TypeOfClothingEditor(typeOfClothingService));
		binder.setValidator(new ItemOfClothingValidator(itemOfClothingService));
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
		model.addAttribute("images", imageService.findAll());
		return "admin-itemOfClothing";
	}
	
//	@RequestMapping
//	public String show(Model model){
//		model.addAttribute("itemsOfClothing", itemOfClothingService.findAll());
//		return "admin-itemOfClothing";
//	}

	@GetMapping("/delete/{id}")
	public String delete(@PathVariable int id,@PageableDefault Pageable pageable, @ModelAttribute("filter") ItemOfClothingFilter filter){
		itemOfClothingService.delete(id);
		return "redirect:/admin/ioc"+getParams(pageable, filter);
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model,@PageableDefault Pageable pageable, @ModelAttribute("filter") ItemOfClothingFilter filter){
                   //так було до валідації:		model.addAttribute("itemOfClothing", itemOfClothingService.findOne(id)); //а тепер стало:
		model.addAttribute("itemOfClothing", itemOfClothingService.findForm(id));
		return show(model, pageable, filter);
	}
               //до валідації було так:	
//	@PostMapping
//	public String save(@ModelAttribute("itemOfClothing") ItemOfClothing itemOfClothing, SessionStatus status){
//		itemOfClothingService.save(itemOfClothing);
//		status.setComplete();
//		return "redirect:/admin/ioc";
//	}
	
	@PostMapping
	public String save(@ModelAttribute("itemOfClothing") @Valid ItemOfClothingForm itemOfClothing,BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") ItemOfClothingFilter filter){
		if(br.hasErrors()) return show(model, pageable, filter);
		itemOfClothingService.save(itemOfClothing);
		status.setComplete();
		return "redirect:/admin/ioc"+getParams(pageable, filter);
	}
	
	private String getParams(Pageable pageable, ItemOfClothingFilter filter){
		String page = ParamBuilder.getParams(pageable);
		StringBuilder builder = new StringBuilder(page);
		if(!filter.getMax().isEmpty()){
			builder.append("&max=");
			builder.append(filter.getMax());
		}
		if(!filter.getMin().isEmpty()){
			builder.append("&min=");
			builder.append(filter.getMin());
		}
		if(!filter.getMaxM().isEmpty()){
			builder.append("&maxM=");
			builder.append(filter.getMaxM());
		}
		if(!filter.getMinM().isEmpty()){
			builder.append("&minM=");
			builder.append(filter.getMinM());
		}
		if(!filter.getItemNameId().isEmpty()){
			for (Integer id : filter.getItemNameId()) {
				builder.append("&itemNameId=");
				builder.append(id);
			}
		}
		if(!filter.getBrandId().isEmpty()){
			for (Integer id : filter.getBrandId()) {
				builder.append("&brandId=");
				builder.append(id);
			}
		}
		if(!filter.getTargetAudienceId().isEmpty()){
			for (Integer id : filter.getTargetAudienceId()) {
				builder.append("&targetAudienceId=");
				builder.append(id);
			}
		}
		if(!filter.getTypeOfClothingId().isEmpty()){
			for (Integer id : filter.getTypeOfClothingId()) {
				builder.append("&typeOfClothingId=");
				builder.append(id);
			}
		}
		if(!filter.getColorId().isEmpty()){
			for (Integer id : filter.getColorId()) {
				builder.append("&colorId=");
				builder.append(id);
			}
		}
		if(!filter.getSizeId().isEmpty()){
			for (Integer id : filter.getSizeId()) {
				builder.append("&sizeId=");
				builder.append(id);
			}
		}
		return builder.toString();
	}
	
//	@PostMapping
//	public String save(@RequestParam int itemNameId,
//			@RequestParam int marking,
//			@RequestParam BigDecimal price,
//			@RequestParam int brandId,
//			@RequestParam int colorId,
//			@RequestParam int sizeId,
//			@RequestParam int targetAudienceId,
//			@RequestParam int typeOfClothingId){
//		ItemName itemName = itemNameService.findOne(itemNameId);
//		Brand brand = brandService.findOne(brandId);
//		Color color = colorService.findOne(colorId);
//		Size size = sizeService.findOne(sizeId);
//		TargetAudience targetAudience = targetAudienceService.findOne(targetAudienceId);
//		TypeOfClothing typeOfClothing = typeOfClothingService.findOne(typeOfClothingId);
//		ItemOfClothing entity = new ItemOfClothing();
//		entity.setItemName(itemName);
//		entity.setMarking(marking);
//		entity.setPrice(price);
//		entity.setBrand(brand);
//		entity.setColor(color);
//		entity.setSize(size);
//		entity.setTargetAudience(targetAudience);
//		entity.setTypeOfClothing(typeOfClothing);
//		itemOfClothingService.save(entity);
//		return "redirect:/admin/ioc";
//	}
		

}
