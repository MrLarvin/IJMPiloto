package com.IJMpiloto.dao;

import java.io.Serializable;

import java.lang.reflect.ParameterizedType;
 
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public abstract class AbstractDAO<PK extends Serializable, T> {
     
    private final Class<T> persistentClass;
    private SessionFactory sessionFactory;
   
    @SuppressWarnings("unchecked")
    public AbstractDAO(){
    	 this.persistentClass =(Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    	 try {
    		 Configuration configuration = new Configuration().configure();
    		 StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().
    		 applySettings(configuration.getProperties());
    		 sessionFactory = configuration.buildSessionFactory(builder.build());
         } catch (Exception ex) {
             ex.printStackTrace();
             throw new ExceptionInInitializerError(ex);
         }
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @SuppressWarnings("unchecked")
	public T getByKey(PK key) {
        return (T) getSession().get(persistentClass, key);
    }
 
    public void persist(T entity) {
        getSession().persist(entity);
    }
 
    public void delete(T entity) {
        getSession().delete(entity);
    }
     
    protected Criteria createEntityCriteria(){
        return getSession().createCriteria(persistentClass);
    }
 
}