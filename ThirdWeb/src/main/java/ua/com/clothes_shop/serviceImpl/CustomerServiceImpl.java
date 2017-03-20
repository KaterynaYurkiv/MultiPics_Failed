package ua.com.clothes_shop.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.com.clothes_shop.dao.CustomerDao;
import ua.com.clothes_shop.entity.Customer;
import ua.com.clothes_shop.service.CustomerService;

@Service //в контексті спрінга створює біни
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerDao customerDao;

	@Override
	public void save(Customer customer) {
//		customer.getUserName().toUpperCase();  //можна прописувати додаткову логіку
		customerDao.save(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

	@Override
	public Customer findOne(int id) {
		return customerDao.findOne(id);
	}

	@Override
	public void delete(int id) {
		customerDao.delete(id);
	}

	@Override
	public void update(Customer customer) {
		customerDao.save(customer); //save а не update
	}

	@Override
	public Customer findByUserName(String userName) {
		return customerDao.findByUserName(userName);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerDao.findByEmail(email);
	}
	

}
