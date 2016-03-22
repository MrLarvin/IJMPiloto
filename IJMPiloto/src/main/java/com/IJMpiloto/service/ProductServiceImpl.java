package com.IJMpiloto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.ProductDao;
import com.IJMpiloto.dao.SupplierDaoImpl;
import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDAO;
	
	@Autowired
	private SupplierDaoImpl supplierDao;

	@Override
	public void saveProduct(ProductDto productDTO) {
		//codigo de mapeo
		
		Product product = new Product();
		product.setCode(productDTO.getCode());
		product.setDescription(productDTO.getDescription());
		
		
		
		productDAO.save(product);
	}

	@Override
	public void updateProduct(ProductDto productDto,long id) {
		Product product = productDAO.findById(id);
		if (product != null) {
			product.setCode(productDto.getCode());
			product.setDescription(productDto.getDescription());
			Supplier supplier = instanceSupplier(productDto.getSupplier_id());
			product.setSupplier(supplier);
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
	public boolean isProductExist(ProductDto product) {
		return (productDAO.findByCode(product.getCode()).isEmpty());
	}

	@Override
	public Supplier instanceSupplier(long id) {
		
		return supplierDao.findById(id);
	}

}
