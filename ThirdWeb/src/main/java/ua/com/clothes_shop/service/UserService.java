package ua.com.clothes_shop.service;

import ua.com.clothes_shop.entity.User;

public interface UserService {
	
	void save(User user);
	
	User findByEmail(String email);
	
	int createNewUser();

	void addToShoppingCart(int userId, int itemId);

}
