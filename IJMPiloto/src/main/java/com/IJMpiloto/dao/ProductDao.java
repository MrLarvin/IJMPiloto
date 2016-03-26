package com.IJMpiloto.dao;

import java.util.List;

import com.IJMpiloto.model.Product;

public interface ProductDao {

	public void save(Product product);

	public void delete(Product product);

	public List<Product> findAll();

	public Product findById(long id);

}