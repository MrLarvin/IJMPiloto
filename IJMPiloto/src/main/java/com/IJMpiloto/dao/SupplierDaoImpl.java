package com.IJMpiloto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.IJMpiloto.model.Supplier;

@Repository("supplierDao")
public class SupplierDaoImpl extends AbstractDao<Long, Supplier> implements SupplierDao {

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
		super.delete(supplier);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findAll() {
		Criteria criteria = createEntityCriteria();
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return (List<Supplier>) criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findByCode(String code) {
		Criteria criteria = getSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("code", code));
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Supplier> findByName(String name) {
		Criteria criteria = getSession().createCriteria(Supplier.class);
		criteria.add(Restrictions.eq("name", name));
		return criteria.list();
	}
}