package com.IJMpiloto.dao;

import java.util.List;

import com.IJMpiloto.entities.Supplier;

public interface SupplierDAO {
	//agregar
	public boolean addSupplier(Supplier supplier);
	
	//update
	public boolean updateSupplier(Supplier supplier);
	
	//borrar
	public boolean deleteSupplier(Supplier supplier);
	
	//obtenermedicos, buscando por su cedula
	public Supplier findById(long id);
	
	public List<Supplier> findAll();
}
