package com.IJMpiloto.dao;

import java.util.List;

import com.IJMpiloto.model.ProductImage;

public interface ProductImageDao {
	public void save(ProductImage productImage);

	public void delete(ProductImage productImage);

	public List<ProductImage> findAll();

	public ProductImage findById(long id);

	public void update(ProductImage productImage);
}
