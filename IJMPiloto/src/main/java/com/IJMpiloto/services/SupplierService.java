package com.IJMpiloto.services;

import com.IJMpiloto.entities.Supplier;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;
@Transactional
public interface SupplierService {
	//agregar
	public boolean addSupplier(Supplier  supplier);
	
	//update
	public boolean updateSupplier(Supplier supplier);
	
	//borrar
	public boolean deleteSupplier(Supplier supplier);
	
	//obtener proveedor, buscando por su id
	public Supplier findById(long id);

	public List<Supplier> findAll();

	public boolean isSupplierExist(Supplier supplier);
}
