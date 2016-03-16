package com.IJMpiloto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.IJMpiloto.model.Product;

@Repository("productDao")
public class ProductDaoImpls extends AbstractDaos<Long, Product> implements ProductDaos {

	@Override
	public Product findById(long id) {
		return getByKey(id);
	}

	@Override
	public void save(Product product) {
		persist(product);
	}

	@Override
	public void delete(Product product) {
		delete(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {
		Criteria criteria = createEntityCriteria();
		return (List<Product>) criteria.list();
	}
}
