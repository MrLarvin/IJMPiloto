package com.IJMpiloto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.IJMpiloto.dao.SupplierDao;
import com.IJMpiloto.dto.SupplierDto;
import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Transactional
@Service("supplierService")
public class SupplierServiceImpl implements SupplierService {

	@Autowired
	private SupplierDao supplierDao;

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
		if(supplierDao.findByCode(supplier.getCode())!=null||supplierDao.findByName(supplier.getName())!=null)
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
}