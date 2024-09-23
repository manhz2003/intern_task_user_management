package com.fds.nsw.kernel.dao.orm.jpa;

import java.util.List;
import java.util.stream.Stream;

import com.fds.nsw.kernel.exception.SystemException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;


public interface QueryFactory<T> {

	public Query createQuery(QueryBuilder builder);

	public List<T> getResultList(QueryBuilder builder);

	public Object getSingleResult(QueryBuilder builder) throws SystemException;

	public Stream<T> getResultStream(QueryBuilder builder);
	
	public int executeUpdate(QueryBuilder builder);

	public EntityManager getEntityManager();
}
