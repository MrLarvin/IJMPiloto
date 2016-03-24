package com.IJMpiloto.service;

import java.util.List;

import com.IJMpiloto.dto.ProductImageDto;
import com.IJMpiloto.model.ProductImage;


public interface ProductImageService {

	public void saveProductImage(ProductImageDto  productImageDto);
	
	void updateProductImage(ProductImageDto ProductImageDto);
	
	public void deleteProductImage(long id);

	public List<ProductImageDto> findAllProductsImageDto();
	
	public ProductImageDto EntityToDto(ProductImage productImage);
	
	public ProductImage DtoToEntity(ProductImageDto productImageDto);
	
	public ProductImageDto findProductImageDtoById (long id);
}
