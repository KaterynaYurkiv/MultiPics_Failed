package ua.com.clothes_shop.controller.admin;

//import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.clothes_shop.dto.filter.SimpleFilter;
import ua.com.clothes_shop.entity.TypeOfClothing;
import ua.com.clothes_shop.service.TypeOfClothingService;
import ua.com.clothes_shop.validator.TypeOfClothingValidator;
import static ua.com.clothes_shop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/toc")
@SessionAttributes("toc")
public class TypeOfClothingController {
	
	@Autowired
	private TypeOfClothingService typeOfClothingService;
	
	@InitBinder("toc")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new TypeOfClothingValidator(typeOfClothingService));
	}
	
	@ModelAttribute("toc")
	public TypeOfClothing getForm(){
		return new TypeOfClothing();
	}
	
	@ModelAttribute("filter")
	 	public SimpleFilter getFilter(){
	 		return new SimpleFilter();
	 	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", typeOfClothingService.findAll(pageable, filter));
		return "admin-typeOfClothing";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("toc", typeOfClothingService.findOne(id));
		return show(model, pageable, filter);
	}

		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			typeOfClothingService.delete(id);
			return "redirect:/admin/toc"+getParams(pageable, filter);
		}
		
		@PostMapping
		public String save(@ModelAttribute("toc") @Valid TypeOfClothing typeOfClothing, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			if(br.hasErrors()) return show(model, pageable, filter);
			typeOfClothingService.save(typeOfClothing);
			status.setComplete();
			return "redirect:/admin/toc"+getParams(pageable, filter);
		}
		
//		@RequestMapping(method=POST)
//		public String save(@RequestParam String itemType){
//			typeOfClothingService.save(itemType);
//			return "redirect:/admin/toc";
//		}

}
