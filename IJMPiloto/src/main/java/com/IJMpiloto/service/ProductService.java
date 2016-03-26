package com.IJMpiloto.service;

import java.util.List;

import com.IJMpiloto.model.Product;

public interface ProductService {

	public void saveProduct(Product product);

	public void updateProduct(Product product);

	public void deleteProduct(Product product);

	public Product findProductById(long id);

	public List<Product> findAllProducts();

	public boolean isProductExist(Product product);
}