package com.IJMpiloto.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.ProductImageDao;
import com.IJMpiloto.dto.ProductImageDto;
import com.IJMpiloto.model.ProductImage;
import com.IJMpiloto.util.ChecksumConverter;

@Transactional
@Service("productImageService")
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
		List<ProductImage> productsImage = productImageDao.findAll();
		List<ProductImageDto> productsImageDto = new LinkedList<>();
		for (ProductImage productImage : productsImage) {
			 ProductImageDto productImageDto =EntityToDto(productImage);
			productsImageDto.add(productImageDto);
		}
		return productsImageDto;
	}

	@Override
	public ProductImageDto EntityToDto(ProductImage productImage) {
		ProductImageDto productImageDto = new ProductImageDto();
		productImageDto.setId(productImage.getId());
		productImageDto.setPicture(Base64.getEncoder().encodeToString((productImage.getPicture())));
		System.out.print(productImageDto.getPicture());
		return productImageDto;
	}

	@Override
	public ProductImage DtoToEntity(ProductImageDto productImageDto) {
		ProductImage productImage = new ProductImage();
		byte[] newPicture = Base64.getDecoder().decode(productImageDto.getPicture());
		productImage.setPicture(newPicture);
		try {
			productImage.setChecksum(ChecksumConverter.getSHA(newPicture));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return productImage;
	}
	
	public ProductImageDto findProductImageDtoById(long id)
	{
		ProductImage productImage =productImageDao.findById(id);
		return EntityToDto(productImage);
	}

}
