package com.IJMpiloto.dao;

import java.util.List;

import com.IJMpiloto.model.Supplier;

public interface SupplierDao {

	public void save(Supplier supplier);

	public void delete(Supplier supplier);

	public Supplier findById(long id);

	public List<Supplier> findAll();

	public List<Supplier> findByCode(String code);

	public List<Supplier> findByName(String name);
}