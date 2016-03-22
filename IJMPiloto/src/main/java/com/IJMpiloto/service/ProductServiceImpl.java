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
		
		Supplier supplier = instanceSupplier(productDTO.getSupplier_id());
		if(supplier!=null)
		{
			product.setSupplier(supplier);
		}
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
	public boolean isProductExist(ProductDto product) {
		return (productDAO.findByCode(product.getCode()).isEmpty());
	}

	@Override
	public Supplier instanceSupplier(long id) {
		
		return supplierDao.findById(id);
	}

}
