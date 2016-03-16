package com.IJMpiloto.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import com.IJMpiloto.entities.Supplier;
public class SupplierDAOImpl extends AbstractDAO<Integer,Supplier> implements SupplierDAO{

	@Override
	public Supplier findById(long id) {
		Session session = this.getSession();
		session.beginTransaction();
		Supplier supplier = (Supplier) session.get(Supplier.class, id);
		session.getTransaction().commit();
		return supplier;
	}
	@Override
	public boolean addSupplier(Supplier supplier) {
	       try{
	        	Session session = this.getSession();
	    		session.beginTransaction();
	        	persist(supplier);
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
	public boolean updateSupplier(Supplier supplier) {
		Session session = this.getSession();
		session.beginTransaction();
		session.update(supplier);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public boolean deleteSupplier(Supplier supplier) {
		Session session = this.getSession();
		session.beginTransaction();
		delete(supplier);
		session.getTransaction().commit();
		return true;
	}

	@Override
	public List<Supplier> findAll() {
		Session session = this.getSession();
		session.beginTransaction();
		Criteria cr = session.createCriteria(Supplier.class);
		 List<Supplier> list = new LinkedList<>();
		for( Object o : cr.list()) {
		    list.add((Supplier)o);
		}
		session.getTransaction().commit();
		return list;
	}

}
