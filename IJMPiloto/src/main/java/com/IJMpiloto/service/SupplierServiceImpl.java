package com.IJMpiloto.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.SupplierDaos;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDaos supplierDao;

	@Override
	public void saveSupplier(Supplier supplier) {
		supplierDao.save(supplier);
	}

	@Override
	public void updateSupplier(Supplier supplier) {
		Supplier entity = supplierDao.findById(supplier.getId());
		if (entity != null) {
			entity.setCode(supplier.getCode());
			entity.setName(supplier.getName());
		}
	}

	@Override
	public void deleteSupplier(Supplier supplier) {
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
	public boolean isSupplierExist(Supplier supplier) {
		return (supplierDao.findById(supplier.getId()) != null);
	}

	@Override
	public Set<Product> findSupplierProductsById(long id) {
		Supplier entity = supplierDao.findById(id);
		return entity.getProduct();
	}
}