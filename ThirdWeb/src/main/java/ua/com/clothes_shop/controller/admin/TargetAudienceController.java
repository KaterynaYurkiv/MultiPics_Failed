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
import ua.com.clothes_shop.entity.TargetAudience;
import ua.com.clothes_shop.service.TargetAudienceService;
import ua.com.clothes_shop.validator.TargetAudienceValidator;
import static ua.com.clothes_shop.util.ParamBuilder.*;

@Controller
@RequestMapping("/admin/targeta")
@SessionAttributes("targeta")
public class TargetAudienceController {
	
	@Autowired
	private TargetAudienceService targetAudienceService;
	
	@InitBinder("targeta")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new TargetAudienceValidator(targetAudienceService));
	}
	
	@ModelAttribute("targeta")
	public TargetAudience getForm(){
		return new TargetAudience();
	}
	
	@ModelAttribute("filter")
	 	public SimpleFilter getFilter(){
	 		return new SimpleFilter();
	 	}
	
	@RequestMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("page", targetAudienceService.findAll(pageable, filter));
		return "admin-targetAudience";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("targeta", targetAudienceService.findOne(id));
		return show(model, pageable, filter);
	}

		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			targetAudienceService.delete(id);
			return "redirect:/admin/targeta"+getParams(pageable, filter);
		}
		
		@PostMapping
		public String save(@ModelAttribute("targeta") @Valid TargetAudience targetAudience, BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			if(br.hasErrors()) return show(model, pageable, filter);
			targetAudienceService.save(targetAudience);
			status.setComplete();
			return "redirect:/admin/targeta"+getParams(pageable, filter);
		}
		
//				@RequestMapping(method=POST)
//				public String save(@RequestParam String category){
//					targetAudienceService.save(category);
//					return "redirect:/admin/targeta";
//				}

}
