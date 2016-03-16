package com.IJMpiloto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.ProductDAO;
import com.IJMpiloto.entities.Product;
@Transactional
public class ProductServiceImpl implements ProductService{
	@Autowired
	private ProductDAO productDAO;
	
	public void setProductDAO(ProductDAO productDAO)
	{
		this.productDAO = productDAO;
	}
	@Override
	public boolean addProduct(Product  product) {
		return productDAO.addProduct(product);
	}

	@Override
	public boolean updateProduct(Product  product) {
		// TODO Auto-generated method stub
		return productDAO.updateProduct(product);
	}
	@Override
	public boolean deleteProduct(Product product) {
		productDAO.deleteProduct(product);
		return true;
	}
	@Override
	public Product findById(long id) {
		// TODO Auto-generated method stub
		return productDAO.findById(id);
	}
	@Override
	public List<Product> findAll() {
		return productDAO.findAll();
	}
	@Override
	public boolean isProductExist(Product product) {
		if(productDAO.findById(product.getId())!=null)
			return true;
		else return false;
	}

}
