package com.IJMpiloto.dao;

import java.util.List;

import com.IJMpiloto.model.Supplier;

public interface SupplierDao {

	public void save(Supplier supplier);
	
	public void update(Supplier supplier);

	public void delete(Supplier supplier);
	
	public Supplier findById(long id);
	
	public List<Supplier> findAll();
	
	public Supplier findByCode(String code);
	
	public Supplier findByName(String name);
}