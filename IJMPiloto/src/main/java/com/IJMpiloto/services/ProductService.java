package com.IJMpiloto.services;

import com.IJMpiloto.entities.Product;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface ProductService {
	//agregar
	public boolean addProduct(Product  product);
	
	//update
	public boolean updateProduct(Product product);
	
	//borrar
	public boolean deleteProduct(Product product);
	
	//obtener producto, buscando por su codigo
	public Product findById(long id);

	public List<Product> findAll();

	public boolean isProductExist(Product product);
}
