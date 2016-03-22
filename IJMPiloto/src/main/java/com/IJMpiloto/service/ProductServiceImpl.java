package com.IJMpiloto.service;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.ProductDao;
import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.dto.SupplierDto;

import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDao productDAO;
	@Autowired
	private SupplierService supplierService;

	@Override
	public void saveProduct(ProductDto productDto) {
		//codigo de mapeo
		Product product = new Product();
		product.setCode(productDto.getCode());
		product.setDescription(productDto.getDescription());
		Supplier supplier = supplierService.DtoToEntity(productDto.getSupplierDto());
		product.setSupplier(supplier);
		productDAO.save(product);
	}

	@Override
	public void updateProduct(ProductDto productDto, String code) {
		Product product = productDAO.findByCode(code);
		if (product != null) {
			product.setCode(productDto.getCode());
			product.setDescription(productDto.getDescription());
			Supplier supplier = supplierService.DtoToEntity(productDto.getSupplierDto());
			product.setSupplier(supplier);
		}
		productDAO.update(product);
	}

	@Override
	public void deleteProduct(String code) {
		Product product = productDAO.findByCode(code);
		productDAO.delete(product);
	}

	@Override
	public ProductDto findProductDtoByCode(String code) {
		Product product = productDAO.findByCode(code);
		//mapping entity to Dto
		ProductDto productDto = new ProductDto();
		productDto.setCode(product.getCode());
		productDto.setDescription(product.getDescription());
		SupplierDto supplierDto = supplierService.EntityToDto(product.getSupplier());
		productDto.setSupplierDto(supplierDto);
		return productDto;
	}

	@Override
	public List<ProductDto> findAllProductsDto() {
		List<Product> products = productDAO.findAll();
		List<ProductDto> productsDto = new LinkedList<>();
		for (Product product : products) {
			ProductDto productDto = new ProductDto();
			productDto.setCode(product.getCode());
			productDto.setDescription(product.getDescription());
			SupplierDto supplierDto = supplierService.EntityToDto(product.getSupplier());
			productDto.setSupplierDto(supplierDto);
			productsDto.add(productDto);
		}
		return productsDto;
	}

	@Override
	public boolean isProductExist(String code) {
		return (productDAO.findByCode(code)!=null);
	}

	@Override
	public Set<ProductDto> EntityToDto(Set<Product> products) {
		Set<ProductDto> productsDto = new HashSet<ProductDto>();
		for (Product product : products) {
		    ProductDto productDto = new ProductDto();
		    productDto.setCode(product.getCode());
		    productDto.setDescription(product.getDescription());
		    productsDto.add(productDto);
		}
		return productsDto;
	}

}
