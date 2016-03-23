package com.IJMpiloto.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.SupplierDao;
import com.IJMpiloto.dto.ProductDto;
import com.IJMpiloto.dto.SupplierDto;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDao supplierDao;
	@Autowired
	private ProductService productService;

	@Override
	public void saveSupplier(SupplierDto supplierDto) {
		Supplier supplier = new Supplier();
		supplier.setCode(supplierDto.getCode());
		supplier.setName(supplierDto.getName());
		supplierDao.save(supplier);
	}

	@Override
	public void updateSupplier(SupplierDto supplierDto, String code) {
		Supplier supplier = supplierDao.findByCode(code);
		supplier.setName(supplierDto.getName());
		supplier.setCode(supplierDto.getCode());
		supplierDao.update(supplier);
	}

	@Override
	public void deleteSupplier(String code) {
		Supplier supplier = findSupplierByCode(code);
		supplierDao.delete(supplier);
	}

	@Override
	public Supplier findSupplierById(long id) {
		return supplierDao.findById(id);
	}

	@Override
	public List<Supplier> findAllSuppliers() {
		return supplierDao.findAll();
	}

	@Override
	public boolean isSupplierExist(String code) {
		if(supplierDao.findByCode(code)!=null)
		{
			return true;
		}
		else return false;
	}

	@Override
	public List<Product> findSupplierProductsById(long id) {
		/*Supplier entity = supplierDao.findById(id);
		return entity.getProduct();*/
		return null;
	}

	@Override
	public SupplierDto findSupplierDtoByCode(String code) {
		Supplier supplier = supplierDao.findByCode(code);
		SupplierDto supplierDto = new SupplierDto();
		supplierDto.setCode(supplier.getCode());
		supplierDto.setName(supplier.getName());
		//need to map Product to ProductDto
		Set<ProductDto> productsDto = productService.EntityToDto(supplier.getProducts());
		supplierDto.setProductsDto(productsDto);
		return supplierDto;
	}

	@Override
	public Supplier findSupplierByCode(String code) {
		return supplierDao.findByCode(code);
	}

	@Override
	public Supplier DtoToEntity(SupplierDto supplierDto) {
		return supplierDao.findByCode(supplierDto.getCode());
	}

	@Override
	public SupplierDto EntityToDto(Supplier supplier) {
		SupplierDto supplierDto = new SupplierDto();
		supplierDto.setCode(supplier.getCode());
		supplierDto.setName(supplier.getName());
		return supplierDto;
	}

	@Override
	public List<SupplierDto> findAllSuppliersDto() {
		List<Supplier> suppliers = supplierDao.findAll();
		List<SupplierDto> suppliersDto = new LinkedList<>();
		for (Supplier supplier : suppliers) {
			SupplierDto supplierDto = new SupplierDto();
			supplierDto.setCode(supplier.getCode());
			supplierDto.setName(supplier.getName());
			suppliersDto.add(supplierDto);
		}
		return suppliersDto;
	}
}