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
import ua.com.clothes_shop.entity.Brand;
import ua.com.clothes_shop.service.BrandService;
import ua.com.clothes_shop.validator.BrandValidator;
import static ua.com.clothes_shop.util.ParamBuilder.*;

@Controller
//якщо над класом стоїть @RequestMapping з посиланням це означає що всі такі анотації
//продовжують це посилання якщо мають такий атрибут, якщо ж не мають то працюють за цим посиланням
@RequestMapping("/admin/brand")
@SessionAttributes("brand")
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@InitBinder("brand")
		protected void bind(WebDataBinder binder){
			binder.setValidator(new BrandValidator(brandService));
		}
	
	@ModelAttribute("brand")
	public Brand getForm(){
		return new Brand();
	}
	
	@ModelAttribute("filter")
		public SimpleFilter getFilter(){
			return new SimpleFilter();
		}

	@GetMapping
	public String show(Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		//Model інтерфейс за допомогою якого передаємо дані на представлення
		//перший параметр методу вказує яке буде імя в списку категорій на представлені
		model.addAttribute("page", brandService.findAll(pageable, filter));
		return "admin-brand";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable int id, Model model, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
		model.addAttribute("brand", brandService.findOne(id));
		return show(model, pageable, filter);
	}
	
	//а цей метод спрацює за посиланням /admin/brand/delete/{якась цифра}
		@RequestMapping("/delete/{id}")
		//@PathVariable - анотація яка витягує певну змінну частину з посилання
		//використовується тоді коли посилання без цієї частини не працює
		//в цьому прикладі додаток не реагує на посилання /admin/brand/delete
		//також можна відразу вказати необхідний тип даних в результаті виконання цього методу
		//обов'язково потрібно перенаправити користувача на інше посилання
		//тому що якщо користувач залишиться за цим посиланням і оновить сторінку то викличе повторне спрацьовування цього методу, а такого id в базі вже немає(
		public String delete(@PathVariable int id, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			brandService.delete(id);
			return "redirect:/admin/brand"+getParams(pageable, filter);
		}
		
		@PostMapping
		public String save(@ModelAttribute("brand") @Valid Brand brand,  BindingResult br, Model model, SessionStatus status, @PageableDefault Pageable pageable, @ModelAttribute("filter") SimpleFilter filter){
			if(br.hasErrors()) return show(model, pageable, filter);
			brandService.save(brand);
			status.setComplete();
			return "redirect:/admin/brand"+getParams(pageable, filter);
		}
		
//		//вказуємо що це метод POST (статичний імпорт)
//		@RequestMapping(method=POST)
//		//вказуємо що в параметрах буде параметр name
//		//може бути кілька @RequestParam, просто ставимо кому після brandName, і пишемо знов @RequestParam тип і назву
//		public String save(@RequestParam String brandName){
//			//передаємо в сервіс для збереження об'єкту Brand
//			brandService.save(brandName);
//			//після пост методів в більшості випадків потрібно робити радірект, щоб запобігти
//			//повторній відправці форми після редіректу всі параметри будуть видалені
//			return "redirect:/admin/brand";
//		}

}
