package com.IJMpiloto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.IJMpiloto.model.Supplier;

@Repository("supplierDao")
public class SupplierDaoImpls extends AbstractDaos<Long, Supplier> implements SupplierDaos {

	@Override
	public Supplier findById(long id) {
		return getByKey(id);
	}
	
	@Override
	public void save(Supplier supplier) {
		persist(supplier);
	}

	@Override
	public void delete(Supplier supplier) {
		delete(supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<Supplier>) criteria.list();
	}
}