package ua.com.clothes_shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ua.com.clothes_shop.entity.ShoppingCart;

public interface ShoppingCartDao extends JpaRepository<ShoppingCart, Integer>{

}
