package com.IJMpiloto.service;

import java.util.List;

import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

public interface ProductService {

	public void saveProduct(ProductDto  product);
	
	public void updateProduct(Product product);
	
	public void deleteProduct(Product product);
	
	public Product findProductById(long id);

	public List<Product> findAllProducts();

	public boolean isProductExist(ProductDto product);
	
	public Supplier instanceSupplier(long id);
}
