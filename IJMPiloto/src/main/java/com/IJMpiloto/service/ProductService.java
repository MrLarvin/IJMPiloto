package com.IJMpiloto.service;

import java.util.List;
import java.util.Set;

import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.model.Product;

public interface ProductService {

	public void saveProduct(ProductDto  product);
	
	void updateProduct(ProductDto productDto, String code);
	
	public void deleteProduct(String code);
	
	public ProductDto findProductDtoByCode(String code);

	public List<ProductDto> findAllProductsDto();

	public boolean isProductExist(String code);
	
	public Set<ProductDto> EntityToDto(Set<Product> products);
	
}
