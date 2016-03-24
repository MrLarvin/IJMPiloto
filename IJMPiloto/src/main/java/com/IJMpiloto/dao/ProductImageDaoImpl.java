package com.IJMpiloto.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.IJMpiloto.model.ProductImage;

public class ProductImageDaoImpl extends AbstractDao<Long,ProductImage> implements ProductImageDao{

	@Override
	public void save(ProductImage productImage) {
		super.persist(productImage);
		
	}

	@Override
	public void delete(ProductImage productImage) {
		super.delete(productImage);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<ProductImage> findAll() {
		Criteria criteria = createEntityCriteria();
		System.out.print(criteria.list());
		return (List<ProductImage>) criteria.list();
	}

	@Override
	public ProductImage findById(long id) {
		Criteria criteria = getSession().createCriteria(ProductImage.class);
		criteria.add(Restrictions.eq("id",id));
		return (ProductImage) criteria.uniqueResult();
	}

	@Override
	public void update(ProductImage productImage) {
		super.update(productImage);
	}

}
