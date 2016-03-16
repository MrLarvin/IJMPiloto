package com.IJMpiloto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.IJMpiloto.dao.SupplierDAO;
import com.IJMpiloto.entities.Supplier;

public class SupplierServiceImpl implements SupplierService{

	@Autowired
	private SupplierDAO supplierDAO;
	public void setSupplierDAO(SupplierDAO supplierDAO)
	{
		this.supplierDAO = supplierDAO;
	}
	@Override
	public boolean addSupplier(Supplier supplier) {
		System.out.println("ADDSupplierService");
		return supplierDAO.addSupplier(supplier);
	}

	@Override
	public boolean updateSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.updateSupplier(supplier);
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		return supplierDAO.deleteSupplier(supplier);
	}

	@Override
	public Supplier findById(long id) {
		// TODO Auto-generated method stub
		return supplierDAO.findById(id);
	}
	@Override
	public List<Supplier> findAll() {
		return supplierDAO.findAll();
		
	}
	@Override
	public boolean isSupplierExist(Supplier supplier) {
		if(supplierDAO.findById(supplier.getId())!=null)
			return true;
		else return false;
	}
	

}
