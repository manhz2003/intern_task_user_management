package com.fds.nsw.kernel.dao.orm.jpa;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("lrQueryFactory")
public class LRQueryFactoryImpl<T> implements QueryFactory<T> {

	@PersistenceContext(unitName = "liferayEntityManagerFactory")
	private EntityManager entityManager;

	@Override
	public Query createQuery(QueryBuilder builder) {
		Query query = null;

		if (builder.isSqlQuery()) {
			query = entityManager.createNativeQuery(builder.getQueryString(), builder.getEntityClass());
		} else {
			query = entityManager.createQuery(builder.getQueryString());
		}

		if (builder.namedParameterMap != null) {
			for (var entry : builder.getNamedParameterMap().entrySet()) {
				//System.out.println(entry.getKey() + "/" + entry.getValue());
				query.setParameter(entry.getKey(), entry.getValue());
			}

		}
		if (builder.getFirstResult() >= 0 && builder.getMaxResults() > 0) {
			query.setFirstResult(builder.getFirstResult());

			query.setMaxResults(builder.getMaxResults());
		}

		return query;
	}

	@Override
	public List<T> getResultList(QueryBuilder builder) {
		@SuppressWarnings("unchecked")
		List<T> resultList = createQuery(builder).getResultList();
		return resultList;
	}

	@Override
	public Object getSingleResult(QueryBuilder builder) {
		return createQuery(builder).getSingleResult();

	}

	@Override
	public Stream<T> getResultStream(QueryBuilder builder) {
		@SuppressWarnings("unchecked")
		Stream<T> resultStream = createQuery(builder).getResultStream();
		return resultStream;

	}
	
	@Override
	@Transactional("liferayTransactionManager")
	public int executeUpdate(QueryBuilder builder) {
		return createQuery(builder).executeUpdate();
	}

	@Override
	public EntityManager getEntityManager() {
		return entityManager;
	}
}
