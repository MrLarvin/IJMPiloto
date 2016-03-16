package com.IJMpiloto.service;

import java.util.List;
import java.util.Set;

import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
public interface SupplierService {

	public void saveSupplier(Supplier supplier);

	public void updateSupplier(Supplier supplier);

	public void deleteSupplier(Supplier supplier);

	public Supplier findSupplierById(long id);

	public List<Supplier> findAllSuppliers();

	public boolean isSupplierExist(Supplier supplier);
	
	public Set<Product> findSupplierProductsById(long id);
}
