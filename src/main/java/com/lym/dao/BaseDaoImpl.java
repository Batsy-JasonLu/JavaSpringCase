package com.lym.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;


public abstract class BaseDaoImpl<T, PK extends Serializable> implements BaseDao<T, PK> {

	@Autowired
	private SessionFactory sessionFactory;
	
	private final Class<T> entityClass;
	
	public BaseDaoImpl(Class<T> entityClass) {
		this.entityClass = Objects.requireNonNull(entityClass);
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public void save(T t) {
		getSession().save(t);
	}
	
	public void update(T t) {
		getSession().update(t);
	}
	
	public void delete(T t) {
		getSession().delete(t);
	}
	
	public List<T> findAll() {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root);
		Query<T> createQuery = session.createQuery(query);
		return createQuery.getResultList();
	}

	public List<T> findByPageList(int pageNo, int pageSize) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root);
		Query<T> createQuery = session.createQuery(query);
		
		createQuery.setFirstResult((pageNo - 1) * pageSize);
		createQuery.setMaxResults(pageSize);
		
		return createQuery.getResultList();
	}
	
	public List<T> findBySingleCriteria(String criteria, PK pk) {
		Session session = getSession();
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<T> query = builder.createQuery(entityClass);
		Root<T> root = query.from(entityClass);
		query.select(root);
		query.where(builder.equal(root.get(criteria), pk));
		Query<T> createQuery = session.createQuery(query);
		return createQuery.getResultList();
	}
}
