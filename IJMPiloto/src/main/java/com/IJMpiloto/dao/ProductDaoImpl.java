package com.IJMpiloto.dao;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.IJMpiloto.model.Product;
import com.IJMpiloto.model.Supplier;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Long, Product> implements ProductDao {

	public Product findById(long id) {
		return getByKey(id);
	}

	public void save(Product product) {
		persist(product);
	}

	public void delete(Product product) {
		super.delete(product);
	}

	@SuppressWarnings("unchecked")
	public List<Product> findAll() {
		Criteria criteria = createEntityCriteria();
		System.out.print(criteria.list());
		return (List<Product>) criteria.list();
	}

	public Product findByCode(String code) {
		Criteria criteria = getSession().createCriteria(Product.class);
		criteria.add(Restrictions.eq("code",code));
		return (Product) criteria.uniqueResult();
	}
}
