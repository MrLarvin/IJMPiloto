package com.IJMpiloto.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dto.SupplierDto;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
public interface SupplierService {

	public void saveSupplier(Supplier supplier);

	public void updateSupplier(Supplier supplier);

	public void deleteSupplier(Supplier supplier);

	public Supplier findSupplierById(long id);
	
	public SupplierDto findSupplierDtoByCode(String code);
	
	public Supplier findSupplierByCode(String code);

	public List<Supplier> findAllSuppliers();

	public boolean isSupplierExist(Supplier supplier);
	
	public List<Product> findSupplierProductsById(long id);
	
	public Supplier DtoToEntity(SupplierDto supplierDto);
	
	public SupplierDto EntityToDto(Supplier supplier);
}
