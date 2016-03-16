package com.IJMpiloto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.ProductDaos;
import com.IJMpiloto.model.Product;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDaos productDAO;

	@Override
	public void saveProduct(Product product) {
		productDAO.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		Product entity = productDAO.findById(product.getId());
		if (entity != null) {
			entity.setCode(product.getCode());
			entity.setDescription(product.getDescription());
			entity.setSupplier(product.getSupplier());
		}
	}

	@Override
	public void deleteProduct(Product product) {
		productDAO.delete(product);
	}

	@Override
	public Product findProductById(long id) {
		return productDAO.findById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		return productDAO.findAll();
	}

	@Override
	public boolean isProductExist(Product product) {
		return (productDAO.findById(product.getId()) != null);
	}

}
