package com.IJMpiloto.service;

import java.util.List;

import com.IJMpiloto.dto.ProductDto;

public interface ProductService {

	public void saveProduct(ProductDto  product);
	
	void updateProduct(ProductDto productDto, String code);
	
	public void deleteProduct(String code);
	
	public ProductDto findProductDtoByCode(String code);

	public List<ProductDto> findAllProductsDto();

	public boolean isProductExist(String code);
	
}
