package ua.com.clothes_shop.serviceImpl;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ua.com.clothes_shop.dao.ItemOfClothingDao;
import ua.com.clothes_shop.dao.ShoppingCartDao;
import ua.com.clothes_shop.dao.UserDao;
import ua.com.clothes_shop.entity.ItemOfClothing;
import ua.com.clothes_shop.entity.Role;
import ua.com.clothes_shop.entity.ShoppingCart;
import ua.com.clothes_shop.entity.User;
import ua.com.clothes_shop.service.UserService;

@Service("userDetailsService")
public class UserServiceImpl implements UserService,UserDetailsService{

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private ShoppingCartDao shoppingCartDao;
	
	@Autowired
	private ItemOfClothingDao itemOfClothingDao;

	@Override
	public void save(User user) {
		//заеодовуємо пароль
		user.setPassword(encoder.encode(user.getPassword()));
		user.setRole(Role.ROLE_USER);
		userDao.save(user);
	}

	@Override
	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userDao.findByEmail(username);
	}
	
	//створення адміна як користувача
	@PostConstruct
	public void addAdmin(){
		User user = userDao.findByEmail("admin");
		if(user==null){
			user = new User();
			user.setEmail("admin");
			user.setPassword(encoder.encode("admin"));
			user.setRole(Role.ROLE_ADMIN);
			userDao.save(user);
		}
	}

//	@Override
//	public int createNewUser() {
//		return userDao.saveAndFlush(new User()).getId();
//	}
	@Override
	public int createNewUser() {
		User user = new User();
		user.setEmail("unknown");
		user.setName("unknown");
		user.setPassword("unknown");
		user.setPhoneNumber("000");
		userDao.saveAndFlush(user);
		return user.getId();
	}

	@Override
	@Transactional
	public void addToShoppingCart(int userId, int itemId) {
		User user = userDao.findOne(userId);
		if(user == null){
			createNewUser();
		}
		ShoppingCart cart = user.getShoppingCart();
		if(cart==null){
			cart = shoppingCartDao.save(new ShoppingCart());
			user.setShoppingCart(cart);
		}
		ItemOfClothing itemOfClothing = itemOfClothingDao.findOne(itemId);
		cart.add(itemOfClothing);
	}
	
//	@Scheduled(fixedDelay=500)
//	public void task(){
//		System.out.println("Hello");
//	}
}