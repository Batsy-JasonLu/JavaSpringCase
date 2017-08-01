package com.lym.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, PK extends Serializable> {

	public void save(T t);
	
	public void update(T t);
	
	public void delete(T t);
	
	public List<T> findAll();
	
	public List<T> findByPageList(int pageNo, int pageSize);
	
	public List<T> findBySingleCriteria(String criteria, PK pk);

}
