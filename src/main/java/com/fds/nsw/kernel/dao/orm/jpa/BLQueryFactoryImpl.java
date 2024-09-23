package com.fds.nsw.kernel.dao.orm.jpa;

import java.util.List;
import java.util.stream.Stream;

import com.fds.flex.common.ultility.GetterUtil;
import com.fds.nsw.kernel.exception.SystemException;
import jakarta.persistence.NoResultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;

@Service
@Qualifier("blQueryFactory")
@Slf4j
public class BLQueryFactoryImpl<T> implements QueryFactory<T> {
	@PersistenceContext(unitName = "nghiepvuEntityManagerFactory")
	private EntityManager entityManager;

	@Override
	public EntityManager getEntityManager() {
		return this.entityManager;
	}

	@Override
	public Query createQuery(QueryBuilder builder) {
//		log.info("Query:" + builder.getQueryString());
//		log.info(builder.getEntityClass() == null ? "null" : "oke");
//		log.info(builder.isSqlQuery() ? "true" : "false");

		Query query = null;

		if (GetterUtil.getBoolean(builder.isSqlQuery(), false)) {
			query = entityManager.createNativeQuery(builder.getQueryString(), builder.getEntityClass());
		} else {
			query = entityManager.createQuery(builder.getQueryString());
		}
		if (builder.namedParameterMap != null) {
			for (var entry : builder.getNamedParameterMap().entrySet()) {
				// System.out.println(entry.getKey() + "/" + entry.getValue());
				query.setParameter(entry.getKey(), entry.getValue());
			}

		}
		if (builder.getFirstResult() >= 0 && builder.getMaxResults() > 0) {
			query.setFirstResult(builder.getFirstResult());

			query.setMaxResults(builder.getMaxResults());
		}

		return query;
	}

	private void closeSessionTest() {
		if(this.entityManager != null) {
			this.entityManager.close();
		}
	}

	@Override
	public List<T> getResultList(QueryBuilder builder) {
		@SuppressWarnings("unchecked")
		List<T> resultList = createQuery(builder).getResultList();
		closeSessionTest();
		return resultList;
	}

	@Override
	public Object getSingleResult(QueryBuilder builder)throws SystemException {
		Object result = null;
		try {
			result = createQuery(builder).getSingleResult();
		} catch (Exception e) {
			if(e instanceof NoResultException) {
				return null;
			}

			throw new SystemException(e);
		} finally {
			closeSessionTest();

		}
		return result;
	}

	@Override
	public Stream<T> getResultStream(QueryBuilder builder) {
		@SuppressWarnings("unchecked")
		Stream<T> resultStream = createQuery(builder).getResultStream();
		closeSessionTest();
		return resultStream;

	}

	@Override
	@Transactional("nghiepvuTransactionManager")
	public int executeUpdate(QueryBuilder builder) {
		int result = createQuery(builder).executeUpdate();
		closeSessionTest();
		return result;
	}

}
