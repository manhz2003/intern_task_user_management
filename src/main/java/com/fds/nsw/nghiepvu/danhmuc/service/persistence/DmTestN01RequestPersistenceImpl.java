///**
// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
// *
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Lesser General Public License as published by the Free
// * Software Foundation; either version 2.1 of the License, or (at your option)
// * any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// * details.
// */
//
//package com.fds.nsw.nghiepvu.danhmuc.service.persistence;
//
//import java.io.Serializable;
//import java.sql.Types;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.fds.flex.common.utility.string.StringBundler;
//import com.fds.flex.common.utility.string.StringPool;
//import com.fds.nsw.kernel.dao.orm.BasePersistence;
//import com.fds.nsw.kernel.dao.orm.QueryUtil;
//import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
//import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
//import com.fds.nsw.kernel.exception.SystemException;
//import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
//import com.fds.nsw.kernel.util.OrderByComparator;
//import com.fds.nsw.nghiepvu.model.DmTestN01Request;
//import com.fds.nsw.nghiepvu.model.DmTestN01Request;
//import com.fds.nsw.nghiepvu.service.exception.*;
//import com.fds.nsw.nghiepvu.repository.DmTestN01RequestRepository;
//import com.fds.nsw.nghiepvu.modelImpl.DmTestN01RequestModelImpl;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class DmTestN01RequestPersistenceImpl extends BasePersistence {
//	@Autowired
//	DmTestN01RequestRepository repository;
//	@Autowired
//	@Qualifier("blQueryFactory")
//	QueryFactory<DmTestN01Request> queryFactory;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify or reference this class directly. Always use {@link DmTestN01RequestUtil} to access the dm test n01 request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
//	 */
//	public DmTestN01Request create(int requestID) {
//		DmTestN01Request dmTestN01Request = new DmTestN01Request();
//
//
//		//dmTestN01Request.setPrimaryKey(id);
//
//		return dmTestN01Request;
//	}
//
//	/**
//	 * Removes the dm test n01 request with the primary key from the database. Also notifies the appropriate model listeners.
//	 *
//	 * @param requestID the primary key of the dm test n01 request
//	 * @return the dm test n01 request that was removed
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmTestN01RequestException if a dm test n01 request with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmTestN01Request remove(int requestID)
//		throws NoSuchDmTestN01RequestException, SystemException {
//		return remove(Integer.valueOf(requestID));
//	}
//
//	/**
//	 * Removes the dm test n01 request with the primary key from the database. Also notifies the appropriate model listeners.
//	 *
//	 * @param primaryKey the primary key of the dm test n01 request
//	 * @return the dm test n01 request that was removed
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmTestN01RequestException if a dm test n01 request with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//
//	public DmTestN01Request remove(Serializable primaryKey)
//		throws NoSuchDmTestN01RequestException, SystemException {
//
//
//		try {
//
//
//			DmTestN01Request dmTestN01Request = findByPrimaryKey(primaryKey);
//
//			if (dmTestN01Request == null) {
//				if (log.isWarnEnabled()) {
//					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
//				}
//
//				throw new NoSuchDmTestN01RequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
//					primaryKey);
//			}
//
//			repository.delete(dmTestN01Request);
//			return dmTestN01Request;
//		}
//		catch (NoSuchDmTestN01RequestException nsee) {
//			throw nsee;
//		}
//		catch (Exception e) {
//			throw processException(e);
//		}
//		finally {
//
//		}
//	}
//
//
//	public DmTestN01Request remove(DmTestN01Request DmTestN01Request) throws SystemException {
//	removeImpl(DmTestN01Request);	return DmTestN01Request;
//}

//protected DmTestN01Request removeImpl
//
//(DmTestN01Request dmTestN01Request)
//		throws SystemException {
//		try {
//			repository.delete(dmTestN01Request);
//		} catch (Exception e) {
//			throw processException(e);
//		} finally {
//			// TODO update cache
//		}
//
//		return dmTestN01Request;
//	}
//
//
//	public DmTestN01Request updateImpl(
//		DmTestN01Request dmTestN01Request, boolean merge)
//		throws SystemException {
//		try {
//
//			repository.saveAndFlush(dmTestN01Request);
//
//		} catch (Exception e) {
//			throw processException(e);
//		} finally {
//			// TODO update cache
//		}
//
//		return dmTestN01Request;
//	}
//
//
//	public DmTestN01Request findByPrimaryKey(Serializable primaryKey)
//		throws NoSuchModelException, SystemException {
//		return findByPrimaryKey(((Integer)primaryKey).intValue());
//	}
//
//	/**
//	 * Returns the dm test n01 request with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmTestN01RequestException} if it could not be found.
//	 *
//	 * @param requestID the primary key of the dm test n01 request
//	 * @return the dm test n01 request
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmTestN01RequestException if a dm test n01 request with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmTestN01Request findByPrimaryKey(int requestID)
//		throws NoSuchDmTestN01RequestException, SystemException {
//		DmTestN01Request dmTestN01Request = fetchByPrimaryKey(requestID);
//
//		if (dmTestN01Request == null) {
//			if (log.isWarnEnabled()) {
//				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + requestID);
//			}
//
//			throw new NoSuchDmTestN01RequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
//				requestID);
//		}
//
//		return dmTestN01Request;
//	}
//
//	/**
//	 * Returns the dm test n01 request with the primary key or returns <code>null</code> if it could not be found.
//	 *
//	 * @param primaryKey the primary key of the dm test n01 request
//	 * @return the dm test n01 request, or <code>null</code> if a dm test n01 request with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//
//	public DmTestN01Request fetchByPrimaryKey(Serializable primaryKey)
//		throws SystemException {
//		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
//	}
//
//	/**
//	 * Returns the dm test n01 request with the primary key or returns <code>null</code> if it could not be found.
//	 *
//	 * @param requestID the primary key of the dm test n01 request
//	 * @return the dm test n01 request, or <code>null</code> if a dm test n01 request with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmTestN01Request fetchByPrimaryKey(int requestID)
//		throws SystemException {
//		DmTestN01Request dmTestN01Request = (DmTestN01Request)EntityCacheUtil.getResult(DmTestN01RequestModelImpl.ENTITY_CACHE_ENABLED,
//				DmTestN01RequestImpl.class, requestID);
//
//
//
//		if (dmTestN01Request == null) {
//
//
//			boolean hasException = false;
//
//			try {
//
//
//				Optional<DmTestN01Request> optional = repository.findById(id);
//				if (optional.isPresent()) {
//					dmTestN01Request = optional.get();
//				}
//			}
//			catch (Exception e) {
//				hasException = true;
//
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return dmTestN01Request;
//	}
//
//	/**
//	 * Returns all the dm test n01 requests.
//	 *
//	 * @return the dm test n01 requests
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmTestN01Request> findAll() throws SystemException {
//		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
//	}
//
//	/**
//	 * Returns a range of all the dm test n01 requests.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param start the lower bound of the range of dm test n01 requests
//	 * @param end the upper bound of the range of dm test n01 requests (not inclusive)
//	 * @return the range of dm test n01 requests
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmTestN01Request> findAll(int start, int end)
//		throws SystemException {
//		return findAll(start, end, null);
//	}
//
//	/**
//	 * Returns an ordered range of all the dm test n01 requests.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param start the lower bound of the range of dm test n01 requests
//	 * @param end the upper bound of the range of dm test n01 requests (not inclusive)
//	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
//	 * @return the ordered range of dm test n01 requests
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmTestN01Request> findAll(int start, int end,
//		OrderByComparator orderByComparator) throws SystemException {
//		List<DmTestN01Request> list = null;
//		if (list == null) {
//			StringBundler query = null;
//			String sql = null;
//
//			if (orderByComparator != null) {
//				query = new StringBundler(2 +
//						(orderByComparator.getOrderByFields().length * 3));
//
//				query.append(_SQL_SELECT_DMTESTN01REQUEST);
//
//				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
//					orderByComparator);
//
//				sql = query.toString();
//			}
//			else {
//				sql = _SQL_SELECT_DMTESTN01REQUEST.concat(DmTestN01RequestModelImpl.ORDER_BY_JPQL);
//			}
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//				list = (List<DmTestN01Request>) queryFactory.getResultList(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return list;
//	}
//
//	/**
//	 * Removes all the dm test n01 requests from the database.
//	 *
//	 * @throws SystemException if a system exception occurred
//	 */
//	public void removeAll() throws SystemException {
//		for (DmTestN01Request dmTestN01Request : findAll()) {
//			repository.delete(dmTestN01Request);
//		}
//	}
//
//	/**
//	 * Returns the number of dm test n01 requests.
//	 *
//	 * @return the number of dm test n01 requests
//	 * @throws SystemException if a system exception occurred
//	 */
//	public int countAll() throws SystemException {
//		Long count = null;
//
//		if (count == null) {
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMTESTN01REQUEST).build();
//
//				count = (Long)queryFactory.getSingleResult(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//				if (count == null) {
//					count = Long.valueOf(0);
//				}
//
//
//
//
//			}
//		}
//
//		return count.intValue();
//	}
//
//	/**
//	 * Initializes the dm test n01 request persistence.
//	 */
//	private static final String _SQL_SELECT_DMTESTN01REQUEST = "SELECT dmTestN01Request FROM DmTestN01Request dmTestN01Request";
//	private static final String _SQL_COUNT_DMTESTN01REQUEST = "SELECT COUNT(dmTestN01Request) FROM DmTestN01Request dmTestN01Request";
//	private static final String _ORDER_BY_ENTITY_ALIAS = "dmTestN01Request.";
//	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmTestN01Request exists with the primary key ";
//
//
//
//}
