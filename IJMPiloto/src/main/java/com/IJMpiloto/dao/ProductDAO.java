package com.IJMpiloto.dao;


import com.IJMpiloto.entities.Product;
import java.util.List;

public interface ProductDAO {
	//agregar
	public boolean addProduct(Product product);
	
	//update
	public boolean updateProduct(Product product);
	
	//borrar
	public boolean deleteProduct(Product product);
	
	//obtenermedicos, buscando por su cedula
	public Product getProduct(long id);
	
	public List<Product> findAll();
	
	public Product findById(long id);
}
