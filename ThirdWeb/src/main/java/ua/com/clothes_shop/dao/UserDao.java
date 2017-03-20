package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.clothes_shop.entity.User;

public interface UserDao  extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);

}
