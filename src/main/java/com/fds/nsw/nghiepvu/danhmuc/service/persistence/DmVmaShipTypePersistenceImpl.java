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

package com.fds.nsw.nghiepvu.danhmuc.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmVmaShipType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaShipTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaShipTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaShipTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaShipTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaShipType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaShipTypeUtil} to access the dm vma ship type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaShipType create(long id) {
		DmVmaShipType dmVmaShipType = new DmVmaShipType();

		
		//dmVmaShipType.setPrimaryKey(id);

		return dmVmaShipType;
	}

	/**
	 * Removes the dm vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm vma ship type
	 * @return the dm vma ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException if a dm vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType remove(long id)
		throws NoSuchDmVmaShipTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma ship type
	 * @return the dm vma ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException if a dm vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipType remove(Serializable primaryKey)
		throws NoSuchDmVmaShipTypeException, SystemException {
		

		try {
			

			DmVmaShipType dmVmaShipType = findByPrimaryKey(primaryKey);

			if (dmVmaShipType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaShipType);
			return dmVmaShipType;
		}
		catch (NoSuchDmVmaShipTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaShipType remove(DmVmaShipType DmVmaShipType) throws SystemException {
	removeImpl(DmVmaShipType);	return DmVmaShipType;
}

protected DmVmaShipType removeImpl

(DmVmaShipType dmVmaShipType)
		throws SystemException {
		try {
			repository.delete(dmVmaShipType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipType;
	}

	
	public DmVmaShipType updateImpl(
		DmVmaShipType dmVmaShipType, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmVmaShipType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipType;
	}

	
	public DmVmaShipType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma ship type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm vma ship type
	 * @return the dm vma ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException if a dm vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType findByPrimaryKey(long id)
		throws NoSuchDmVmaShipTypeException, SystemException {
		DmVmaShipType dmVmaShipType = fetchByPrimaryKey(id);

		if (dmVmaShipType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmVmaShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmVmaShipType;
	}

	/**
	 * Returns the dm vma ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma ship type
	 * @return the dm vma ship type, or <code>null</code> if a dm vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm vma ship type
	 * @return the dm vma ship type, or <code>null</code> if a dm vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType fetchByPrimaryKey(long id) throws SystemException {
		DmVmaShipType dmVmaShipType = null;

		

		if (dmVmaShipType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaShipType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmVmaShipType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaShipType;
	}

	/**
	 * Returns the dm vma ship type where ShipTypeCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException} if it could not be found.
	 *
	 * @param ShipTypeCode the ship type code
	 * @return the matching dm vma ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipTypeException if a matching dm vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType findByF_shipTypeCode(String ShipTypeCode)
		throws NoSuchDmVmaShipTypeException, SystemException {
		DmVmaShipType dmVmaShipType = fetchByF_shipTypeCode(ShipTypeCode);

		if (dmVmaShipType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipTypeCode=");
			msg.append(ShipTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaShipTypeException(msg.toString());
		}

		return dmVmaShipType;
	}

	/**
	 * Returns the dm vma ship type where ShipTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipTypeCode the ship type code
	 * @return the matching dm vma ship type, or <code>null</code> if a matching dm vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType fetchByF_shipTypeCode(String ShipTypeCode)
		throws SystemException {
		return fetchByF_shipTypeCode(ShipTypeCode, true);
	}

	/**
	 * Returns the dm vma ship type where ShipTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma ship type, or <code>null</code> if a matching dm vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType fetchByF_shipTypeCode(String ShipTypeCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaShipType dmVmaShipType = null;
		if (dmVmaShipType == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASHIPTYPE_WHERE);

			if (ShipTypeCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (ShipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			query.append(DmVmaShipTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaShipType.class).build();

				

				if (ShipTypeCode != null) {
					builder.appendNamedParameterMap("ShipTypeCode", ShipTypeCode);
				}

				dmVmaShipType = (DmVmaShipType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaShipType;
	}

	/**
	 * Returns all the dm vma ship types.
	 *
	 * @return the dm vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma ship types
	 * @param end the upper bound of the range of dm vma ship types (not inclusive)
	 * @return the range of dm vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma ship types
	 * @param end the upper bound of the range of dm vma ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaShipType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMASHIPTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMASHIPTYPE.concat(DmVmaShipTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaShipType>) queryFactory.getResultList(builder);
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
	 * Removes the dm vma ship type where ShipTypeCode = &#63; from the database.
	 *
	 * @param ShipTypeCode the ship type code
	 * @return the dm vma ship type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipType removeByF_shipTypeCode(String ShipTypeCode)
		throws NoSuchDmVmaShipTypeException, SystemException {
		DmVmaShipType dmVmaShipType = findByF_shipTypeCode(ShipTypeCode);

		repository.delete(dmVmaShipType);
			return dmVmaShipType;
	}

	/**
	 * Removes all the dm vma ship types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaShipType dmVmaShipType : findAll()) {
			repository.delete(dmVmaShipType);
		}
	}

	/**
	 * Returns the number of dm vma ship types where ShipTypeCode = &#63;.
	 *
	 * @param ShipTypeCode the ship type code
	 * @return the number of matching dm vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipTypeCode(String ShipTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPTYPE_WHERE);

			if (ShipTypeCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (ShipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipTypeCode != null) {
					builder.appendNamedParameterMap("ShipTypeCode", ShipTypeCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of dm vma ship types.
	 *
	 * @return the number of dm vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMASHIPTYPE).build();

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
	 * Initializes the dm vma ship type persistence.
	 */
	private static final String _SQL_SELECT_DMVMASHIPTYPE = "SELECT dmVmaShipType FROM DmVmaShipType dmVmaShipType";
	private static final String _SQL_SELECT_DMVMASHIPTYPE_WHERE = "SELECT dmVmaShipType FROM DmVmaShipType dmVmaShipType WHERE ";
	private static final String _SQL_COUNT_DMVMASHIPTYPE = "SELECT COUNT(dmVmaShipType) FROM DmVmaShipType dmVmaShipType";
	private static final String _SQL_COUNT_DMVMASHIPTYPE_WHERE = "SELECT COUNT(dmVmaShipType) FROM DmVmaShipType dmVmaShipType WHERE ";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_1 = "dmVmaShipType.ShipTypeCode IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_2 = "dmVmaShipType.ShipTypeCode =:ShipTypeCode";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SHIPTYPECODE_3 = "(dmVmaShipType.ShipTypeCode IS NULL OR dmVmaShipType.ShipTypeCode =:ShipTypeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaShipType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaShipType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaShipType exists with the key {";
	

	
}
