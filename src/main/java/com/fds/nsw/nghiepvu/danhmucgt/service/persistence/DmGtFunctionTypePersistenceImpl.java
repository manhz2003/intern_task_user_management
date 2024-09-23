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
import com.fds.nsw.nghiepvu.model.DmGtFunctionType;
import com.fds.nsw.nghiepvu.modelImpl.DmGTFunctionTypeModelImpl;
import com.fds.nsw.nghiepvu.repository.DmGtFunctionTypeRepository;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmGtFunctionTypeException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtFunctionTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmGtFunctionTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtFunctionType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtFunctionTypeUtil} to access the dm g t function type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtFunctionType create(long id) {
		DmGtFunctionType dmGTFunctionType = new DmGtFunctionType();

		
		//dmGTFunctionType.setPrimaryKey(id);

		return dmGTFunctionType;
	}

	/**
	 * Removes the dm g t function type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm g t function type
	 * @return the dm g t function type that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType remove(long id)
		throws NoSuchDmGtFunctionTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm g t function type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm g t function type
	 * @return the dm g t function type that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtFunctionType remove(Serializable primaryKey)
		throws NoSuchDmGtFunctionTypeException, SystemException {
		

		try {
			

			DmGtFunctionType dmGTFunctionType = findByPrimaryKey(primaryKey);

			if (dmGTFunctionType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtFunctionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGTFunctionType);
			return dmGTFunctionType;
		}
		catch (NoSuchDmGtFunctionTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtFunctionType remove(DmGtFunctionType DmGtFunctionType) throws SystemException {
	removeImpl(DmGtFunctionType);
	return DmGtFunctionType;
}

protected DmGtFunctionType removeImpl(DmGtFunctionType dmGTFunctionType)
		throws SystemException {
		try {
			repository.delete(dmGTFunctionType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTFunctionType;
	}

	
	public DmGtFunctionType updateImpl(
		DmGtFunctionType dmGTFunctionType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGTFunctionType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTFunctionType;
	}

	
	public DmGtFunctionType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t function type with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm g t function type
	 * @return the dm g t function type
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType findByPrimaryKey(long id)
		throws NoSuchDmGtFunctionTypeException, SystemException {
		DmGtFunctionType dmGTFunctionType = fetchByPrimaryKey(id);

		if (dmGTFunctionType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtFunctionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGTFunctionType;
	}

	/**
	 * Returns the dm g t function type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm g t function type
	 * @return the dm g t function type, or <code>null</code> if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtFunctionType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t function type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm g t function type
	 * @return the dm g t function type, or <code>null</code> if a dm g t function type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtFunctionType dmGTFunctionType = null;

		

		if (dmGTFunctionType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtFunctionType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGTFunctionType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGTFunctionType;
	}

	/**
	 * Returns the dm g t function type where functionTypeCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException} if it could not be found.
	 *
	 * @param functionTypeCode the function type code
	 * @return the matching dm g t function type
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtFunctionTypeException if a matching dm g t function type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType findByFunctionTypeCode(String functionTypeCode)
		throws NoSuchDmGtFunctionTypeException, SystemException {
		DmGtFunctionType dmGTFunctionType = fetchByFunctionTypeCode(functionTypeCode);

		if (dmGTFunctionType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("functionTypeCode=");
			msg.append(functionTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtFunctionTypeException(msg.toString());
		}

		return dmGTFunctionType;
	}

	/**
	 * Returns the dm g t function type where functionTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param functionTypeCode the function type code
	 * @return the matching dm g t function type, or <code>null</code> if a matching dm g t function type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType fetchByFunctionTypeCode(String functionTypeCode)
		throws SystemException {
		return fetchByFunctionTypeCode(functionTypeCode, true);
	}

	/**
	 * Returns the dm g t function type where functionTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param functionTypeCode the function type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm g t function type, or <code>null</code> if a matching dm g t function type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType fetchByFunctionTypeCode(String functionTypeCode,
		boolean retrieveFromCache) throws SystemException {
		DmGtFunctionType dmGTFunctionType = null;
		if (dmGTFunctionType == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMGTFUNCTIONTYPE_WHERE);

			if (functionTypeCode == null) {
				query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_1);
			}
			else {
				if (functionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_2);
				}
			}

			query.append(DmGTFunctionTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtFunctionType.class).build();

				

				if (functionTypeCode != null) {
					builder.appendNamedParameterMap("functionTypeCode", functionTypeCode);
				}

				dmGTFunctionType = (DmGtFunctionType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGTFunctionType;
	}

	/**
	 * Returns all the dm g t function types.
	 *
	 * @return the dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtFunctionType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm g t function types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t function types
	 * @param end the upper bound of the range of dm g t function types (not inclusive)
	 * @return the range of dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtFunctionType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm g t function types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t function types
	 * @param end the upper bound of the range of dm g t function types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtFunctionType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtFunctionType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTFUNCTIONTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTFUNCTIONTYPE.concat(DmGTFunctionTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtFunctionType>) queryFactory.getResultList(builder);
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
	 * Removes the dm g t function type where functionTypeCode = &#63; from the database.
	 *
	 * @param functionTypeCode the function type code
	 * @return the dm g t function type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtFunctionType removeByFunctionTypeCode(String functionTypeCode)
		throws NoSuchDmGtFunctionTypeException, SystemException {
		DmGtFunctionType dmGTFunctionType = findByFunctionTypeCode(functionTypeCode);

		repository.delete(dmGTFunctionType);
			return dmGTFunctionType;
	}

	/**
	 * Removes all the dm g t function types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtFunctionType dmGTFunctionType : findAll()) {
			repository.delete(dmGTFunctionType);
		}
	}

	/**
	 * Returns the number of dm g t function types where functionTypeCode = &#63;.
	 *
	 * @param functionTypeCode the function type code
	 * @return the number of matching dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByFunctionTypeCode(String functionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTFUNCTIONTYPE_WHERE);

			if (functionTypeCode == null) {
				query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_1);
			}
			else {
				if (functionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (functionTypeCode != null) {
					builder.appendNamedParameterMap("functionTypeCode", functionTypeCode);
				}

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
	 * Returns the number of dm g t function types.
	 *
	 * @return the number of dm g t function types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTFUNCTIONTYPE).build();

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
	 * Initializes the dm g t function type persistence.
	 */
	private static final String _SQL_SELECT_DMGTFUNCTIONTYPE = "SELECT dmGTFunctionType FROM DmGTFunctionType dmGTFunctionType";
	private static final String _SQL_SELECT_DMGTFUNCTIONTYPE_WHERE = "SELECT dmGTFunctionType FROM DmGTFunctionType dmGTFunctionType WHERE ";
	private static final String _SQL_COUNT_DMGTFUNCTIONTYPE = "SELECT COUNT(dmGTFunctionType) FROM DmGTFunctionType dmGTFunctionType";
	private static final String _SQL_COUNT_DMGTFUNCTIONTYPE_WHERE = "SELECT COUNT(dmGTFunctionType) FROM DmGTFunctionType dmGTFunctionType WHERE ";
	private static final String _FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_1 =
		"dmGTFunctionType.functionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_2 =
		"dmGTFunctionType.functionTypeCode =:functionTypeCode";
	private static final String _FINDER_COLUMN_FUNCTIONTYPECODE_FUNCTIONTYPECODE_3 =
		"(dmGTFunctionType.functionTypeCode IS NULL OR dmGTFunctionType.functionTypeCode =:functionTypeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGTFunctionType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGTFunctionType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGTFunctionType exists with the key {";
	
	
}
