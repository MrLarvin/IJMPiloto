package com.IJMpiloto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.IJMpiloto.dao.ProductImageDao;
import com.IJMpiloto.dto.ProductImageDto;
import com.IJMpiloto.model.ProductImage;

public class ProductImageServiceImpl implements ProductImageService{

	@Autowired
	ProductImageDao productImageDao;
	
	@Override
	public void saveProductImage(ProductImageDto productImageDto) {
		
		productImageDao.save(DtoToEntity(productImageDto));
		
	}

	@Override
	public void updateProductImage(ProductImageDto productImageDto) {
		ProductImage productImage = DtoToEntity(productImageDto);
		productImageDao.update(productImage);
		
	}

	@Override
	public void deleteProductImage(long id) {
		ProductImage productImage = productImageDao.findById(id);
		productImageDao.delete(productImage);
		
	}

	@Override
	public List<ProductImageDto> findAllProductsImageDto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ProductImageDto EntityToDto(ProductImage productImage) {
		ProductImageDto productImageDto = new ProductImageDto();
		productImageDto.setId(productImage.getId());
		productImageDto.setPicture(new String(productImage.getPicture()));
		return productImageDto;
	}

	@Override
	public ProductImage DtoToEntity(ProductImageDto productImageDto) {
		ProductImage productImage = new ProductImage();
		byte[] newPicture = productImageDto.getPicture().getBytes();
		productImage.setPicture(newPicture);
		return productImage;
	}
	
	public ProductImageDto findProductImageDtoById(long id)
	{
		ProductImage productImage =productImageDao.findById(id);
		return EntityToDto(productImage);
	}

}
