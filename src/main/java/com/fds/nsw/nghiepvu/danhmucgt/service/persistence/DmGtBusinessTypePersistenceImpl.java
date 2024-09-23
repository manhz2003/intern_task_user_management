/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fds.flex.common.utility.string.StringBundler;
import com.fds.flex.common.utility.string.StringPool;
import com.fds.nsw.kernel.dao.orm.BasePersistence;
import com.fds.nsw.kernel.dao.orm.QueryUtil;
import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
import com.fds.nsw.kernel.exception.SystemException;
import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
import com.fds.nsw.kernel.util.OrderByComparator;

import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.model.DmGtBusinessType;
import com.fds.nsw.nghiepvu.modelImpl.DmGTBusinessTypeModelImpl;
import com.fds.nsw.nghiepvu.repository.DmGtBusinessTypeRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class DmGtBusinessTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmGtBusinessTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtBusinessType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGTBusinessTypeUtil} to access the dm g t business type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtBusinessType create(long id) {
		DmGtBusinessType dmGTBusinessType = new DmGtBusinessType();

		
		//dmGTBusinessType.setPrimaryKey(id);

		return dmGTBusinessType;
	}

	/**
	 * Removes the dm g t business type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm g t business type
	 * @return the dm g t business type that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType remove(long id)
		throws NoSuchDmGtBusinessTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm g t business type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm g t business type
	 * @return the dm g t business type that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtBusinessType remove(Serializable primaryKey)
		throws NoSuchDmGtBusinessTypeException, SystemException {
		

		try {
			

			DmGtBusinessType dmGTBusinessType = findByPrimaryKey(primaryKey);

			if (dmGTBusinessType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtBusinessTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGTBusinessType);
			return dmGTBusinessType;
		}
		catch (NoSuchDmGtBusinessTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtBusinessType remove(DmGtBusinessType DmGtBusinessType) throws SystemException {
	removeImpl(DmGtBusinessType);
	return DmGtBusinessType;
}

protected DmGtBusinessType removeImpl(DmGtBusinessType dmGTBusinessType)
		throws SystemException {
		try {
			repository.delete(dmGTBusinessType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTBusinessType;
	}

	
	public DmGtBusinessType updateImpl(
		DmGtBusinessType dmGTBusinessType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGTBusinessType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTBusinessType;
	}

	
	public DmGtBusinessType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t business type with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm g t business type
	 * @return the dm g t business type
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType findByPrimaryKey(long id)
		throws NoSuchDmGtBusinessTypeException, SystemException {
		DmGtBusinessType dmGTBusinessType = fetchByPrimaryKey(id);

		if (dmGTBusinessType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtBusinessTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGTBusinessType;
	}

	/**
	 * Returns the dm g t business type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm g t business type
	 * @return the dm g t business type, or <code>null</code> if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtBusinessType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t business type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm g t business type
	 * @return the dm g t business type, or <code>null</code> if a dm g t business type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtBusinessType dmGTBusinessType = null;

		

		if (dmGTBusinessType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtBusinessType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGTBusinessType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGTBusinessType;
	}

	/**
	 * Returns the dm g t business type where businessTypeCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException} if it could not be found.
	 *
	 * @param businessTypeCode the business type code
	 * @return the matching dm g t business type
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtBusinessTypeException if a matching dm g t business type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType findByBusinessTypeCode(int businessTypeCode)
		throws NoSuchDmGtBusinessTypeException, SystemException {
		DmGtBusinessType dmGTBusinessType = fetchByBusinessTypeCode(businessTypeCode);

		if (dmGTBusinessType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("businessTypeCode=");
			msg.append(businessTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtBusinessTypeException(msg.toString());
		}

		return dmGTBusinessType;
	}

	/**
	 * Returns the dm g t business type where businessTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param businessTypeCode the business type code
	 * @return the matching dm g t business type, or <code>null</code> if a matching dm g t business type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType fetchByBusinessTypeCode(int businessTypeCode)
		throws SystemException {
		return fetchByBusinessTypeCode(businessTypeCode, true);
	}

	/**
	 * Returns the dm g t business type where businessTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param businessTypeCode the business type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm g t business type, or <code>null</code> if a matching dm g t business type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType fetchByBusinessTypeCode(int businessTypeCode,
		boolean retrieveFromCache) throws SystemException {
		DmGtBusinessType dmGTBusinessType = null;
		if (dmGTBusinessType == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMGTBUSINESSTYPE_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			query.append(DmGTBusinessTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtBusinessType.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				dmGTBusinessType = (DmGtBusinessType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGTBusinessType;
	}

	/**
	 * Returns all the dm g t business types.
	 *
	 * @return the dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtBusinessType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm g t business types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t business types
	 * @param end the upper bound of the range of dm g t business types (not inclusive)
	 * @return the range of dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtBusinessType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm g t business types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t business types
	 * @param end the upper bound of the range of dm g t business types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtBusinessType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtBusinessType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTBUSINESSTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTBUSINESSTYPE.concat(DmGTBusinessTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtBusinessType>) queryFactory.getResultList(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}

		return list;
	}

	/**
	 * Removes the dm g t business type where businessTypeCode = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @return the dm g t business type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtBusinessType removeByBusinessTypeCode(int businessTypeCode)
		throws NoSuchDmGtBusinessTypeException, SystemException {
		DmGtBusinessType dmGTBusinessType = findByBusinessTypeCode(businessTypeCode);

		repository.delete(dmGTBusinessType);
			return dmGTBusinessType;
	}

	/**
	 * Removes all the dm g t business types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtBusinessType dmGTBusinessType : findAll()) {
			repository.delete(dmGTBusinessType);
		}
	}

	/**
	 * Returns the number of dm g t business types where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @return the number of matching dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByBusinessTypeCode(int businessTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTBUSINESSTYPE_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				count = (Long)queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				

				
			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm g t business types.
	 *
	 * @return the number of dm g t business types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTBUSINESSTYPE).build();

				count = (Long)queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

				

				
			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the dm g t business type persistence.
	 */
	private static final String _SQL_SELECT_DMGTBUSINESSTYPE = "SELECT dmGTBusinessType FROM DmGtBusinessType dmGTBusinessType";
	private static final String _SQL_SELECT_DMGTBUSINESSTYPE_WHERE = "SELECT dmGTBusinessType FROM DmGtBusinessType dmGTBusinessType WHERE ";
	private static final String _SQL_COUNT_DMGTBUSINESSTYPE = "SELECT COUNT(dmGTBusinessType) FROM DmGtBusinessType dmGTBusinessType";
	private static final String _SQL_COUNT_DMGTBUSINESSTYPE_WHERE = "SELECT COUNT(dmGTBusinessType) FROM DmGtBusinessType dmGTBusinessType WHERE ";
	private static final String _FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2 =
		"dmGTBusinessType.businessTypeCode =:businessTypeCode";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGTBusinessType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtBusinessType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtBusinessType exists with the key {";
	

	
}
