package ua.com.clothes_shop.controller.user;

//import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import ua.com.clothes_shop.entity.User;
import ua.com.clothes_shop.service.UserService;
import ua.com.clothes_shop.validator.UserValidator;

@Controller
public class IndexController {
	
	@Autowired
	private UserService userService;
	
	//��� ����� ���� ��������� �� ����� ���������� ����� �� ����
	@RequestMapping("/")
	public String index(){
		//������� �������� index.jsp (� ����� user)
		return "user-index";
	}
           //����� ������ ������� ���������� Principal principal � ��� ������� �� � ��������...Mapping, �� �������� ���� ��� ��������� �������
//	@RequestMapping("/")
//	 	public String index(Principal principal){
//	 		if(principal!=null){
//	 			System.out.println(principal.getName());
//	 		}
//	  		return "user-index";
//	  	}
	
	//� ��� ��� ���� ���������� ������� �� �o������� /admin
	@RequestMapping("/admin")
	public String admin(){
		//��� admin.jsp (� ����� admin)
		return "admin-admin";
	}
	
	@GetMapping("/registration")
	public String registration(Model model){
		model.addAttribute("user", new User());
		return "user-registration";
	}
	
//	@PostMapping("/registration")
//	public String save(@ModelAttribute("user") User user){
//		userService.save(user);
//		return "redirect:/login"; 
//	}
	
//	@ModelAttribute("user")
//	public User getForm(){
//		return new User();
//	}
	
	@InitBinder("user")
	protected void bind(WebDataBinder binder){
		binder.setValidator(new UserValidator(userService));
	}
	
	@PostMapping("/registration")
	public String save(@ModelAttribute("user") @Valid User user,  BindingResult br, Model model, SessionStatus status){
		if(br.hasErrors()) return "user-registration";
		userService.save(user);
		status.setComplete();
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String login(){
		return "user-login";
	}
	
}
