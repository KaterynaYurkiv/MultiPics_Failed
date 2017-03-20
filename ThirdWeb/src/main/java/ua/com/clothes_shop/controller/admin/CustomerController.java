package ua.com.clothes_shop.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.com.clothes_shop.service.CustomerService;

@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	@RequestMapping
	public String show(Model model){
		model.addAttribute("customers", customerService.findAll());
		return "admin-customer";
	}

		@RequestMapping("/delete/{id}")
		public String delete(@PathVariable int id){
			customerService.delete(id);
			return "redirect:/admin/customer";
		}

}
