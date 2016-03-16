package com.IJMpiloto.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import com.IJMpiloto.entities.Product;

@Repository("ProductDAO")
public class ProductDAOImpl extends AbstractDAO<Integer,Product> implements ProductDAO{

    public Product findById(long id) {
		Session session = this.getSession();
		session.beginTransaction();
		Product product = (Product) session.get(Product.class, id);
		session.getTransaction().commit();
		return product;
    }
 
    public boolean addProduct(Product product) {
        try{
        	Session session = this.getSession();
    		session.beginTransaction();
        	persist(product);
        	session.getTransaction().commit();
        	return true;
        }
        catch (HibernateException he)
        {
        	System.out.println(he.toString());
        	return false;
        }
    }
 
	@Override
	public boolean updateProduct(Product product) {
		Session session = this.getSession();
		session.beginTransaction();
		session.update(product);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public boolean deleteProduct(Product product) {
		Session session = this.getSession();
		session.beginTransaction();
		delete(product);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public Product getProduct(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAll() {
		Session session = this.getSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Product.class);
		 List<Product> list = new LinkedList<>();
		for( Object o : cr.list()) {
		    list.add((Product)o);
		}
		session.getTransaction().commit();
		return list;
	}
}
