package ua.com.clothes_shop.service;

import java.util.List;

import ua.com.clothes_shop.entity.Customer;

public interface CustomerService {
	
    void save(Customer customer);
	
	List<Customer> findAll();
	
	Customer findOne(int id);
	
	void delete(int id);
	
	void update (Customer customer);
	
	Customer findByUserName(String userName);
	
	Customer findByEmail(String email);

}
