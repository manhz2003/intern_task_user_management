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
import com.fds.nsw.nghiepvu.model.DmHistoryVmaShipType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryVmashipTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryVmaShipTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryVmaShipTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryVmashipTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryVmaShipType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryVmaShipTypeUtil} to access the dm history vma ship type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryVmaShipType create(long id) {
		DmHistoryVmaShipType dmHistoryVmaShipType = new DmHistoryVmaShipType();

		
		//dmHistoryVmaShipType.setPrimaryKey(id);

		return dmHistoryVmaShipType;
	}

	/**
	 * Removes the dm history vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history vma ship type
	 * @return the dm history vma ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException if a dm history vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType remove(long id)
		throws NoSuchDmHistoryVmaShipTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm history vma ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history vma ship type
	 * @return the dm history vma ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException if a dm history vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryVmaShipType remove(Serializable primaryKey)
		throws NoSuchDmHistoryVmaShipTypeException, SystemException {
		

		try {
			

			DmHistoryVmaShipType dmHistoryVmaShipType = findByPrimaryKey(primaryKey);

			if (dmHistoryVmaShipType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryVmaShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryVmaShipType);
			return dmHistoryVmaShipType;
		}
		catch (NoSuchDmHistoryVmaShipTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryVmaShipType remove(DmHistoryVmaShipType DmHistoryVmaShipType) throws SystemException {
	removeImpl(DmHistoryVmaShipType);	return DmHistoryVmaShipType;
}

protected DmHistoryVmaShipType removeImpl

(
		DmHistoryVmaShipType dmHistoryVmaShipType) throws SystemException {
		try {
			repository.delete(dmHistoryVmaShipType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryVmaShipType;
	}

	
	public DmHistoryVmaShipType updateImpl(
		DmHistoryVmaShipType dmHistoryVmaShipType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryVmaShipType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryVmaShipType;
	}

	
	public DmHistoryVmaShipType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history vma ship type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history vma ship type
	 * @return the dm history vma ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException if a dm history vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType findByPrimaryKey(long id)
		throws NoSuchDmHistoryVmaShipTypeException, SystemException {
		DmHistoryVmaShipType dmHistoryVmaShipType = fetchByPrimaryKey(id);

		if (dmHistoryVmaShipType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryVmaShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryVmaShipType;
	}

	/**
	 * Returns the dm history vma ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history vma ship type
	 * @return the dm history vma ship type, or <code>null</code> if a dm history vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryVmaShipType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history vma ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history vma ship type
	 * @return the dm history vma ship type, or <code>null</code> if a dm history vma ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType fetchByPrimaryKey(long id)
		throws SystemException {
		DmHistoryVmaShipType dmHistoryVmaShipType = null;

		

		if (dmHistoryVmaShipType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryVmaShipType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryVmaShipType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryVmaShipType;
	}

	/**
	 * Returns the dm history vma ship type where ShipTypeCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException} if it could not be found.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param SyncVersion the sync version
	 * @return the matching dm history vma ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryVmaShipTypeException if a matching dm history vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType findByF_shipTypeCode_syncVersion(
		String ShipTypeCode, String SyncVersion)
		throws NoSuchDmHistoryVmaShipTypeException, SystemException {
		DmHistoryVmaShipType dmHistoryVmaShipType = fetchByF_shipTypeCode_syncVersion(ShipTypeCode,
				SyncVersion);

		if (dmHistoryVmaShipType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipTypeCode=");
			msg.append(ShipTypeCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryVmaShipTypeException(msg.toString());
		}

		return dmHistoryVmaShipType;
	}

	/**
	 * Returns the dm history vma ship type where ShipTypeCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param SyncVersion the sync version
	 * @return the matching dm history vma ship type, or <code>null</code> if a matching dm history vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType fetchByF_shipTypeCode_syncVersion(
		String ShipTypeCode, String SyncVersion) throws SystemException {
		return fetchByF_shipTypeCode_syncVersion(ShipTypeCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history vma ship type where ShipTypeCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history vma ship type, or <code>null</code> if a matching dm history vma ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType fetchByF_shipTypeCode_syncVersion(
		String ShipTypeCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryVmaShipType dmHistoryVmaShipType = null;
		if (dmHistoryVmaShipType == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYVMASHIPTYPE_WHERE);

			if (ShipTypeCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_1);
			}
			else {
				if (ShipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryVmaShipTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryVmaShipType.class).build();

				

				if (ShipTypeCode != null) {
					builder.appendNamedParameterMap("ShipTypeCode", ShipTypeCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryVmaShipType = (DmHistoryVmaShipType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryVmaShipType;
	}

	/**
	 * Returns all the dm history vma ship types.
	 *
	 * @return the dm history vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryVmaShipType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history vma ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history vma ship types
	 * @param end the upper bound of the range of dm history vma ship types (not inclusive)
	 * @return the range of dm history vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryVmaShipType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history vma ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history vma ship types
	 * @param end the upper bound of the range of dm history vma ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryVmaShipType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryVmaShipType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYVMASHIPTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYVMASHIPTYPE.concat(DmHistoryVmaShipTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryVmaShipType>) queryFactory.getResultList(builder);
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
	 * Removes the dm history vma ship type where ShipTypeCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param SyncVersion the sync version
	 * @return the dm history vma ship type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryVmaShipType removeByF_shipTypeCode_syncVersion(
		String ShipTypeCode, String SyncVersion)
		throws NoSuchDmHistoryVmaShipTypeException, SystemException {
		DmHistoryVmaShipType dmHistoryVmaShipType = findByF_shipTypeCode_syncVersion(ShipTypeCode,
				SyncVersion);

		repository.delete(dmHistoryVmaShipType);
			return dmHistoryVmaShipType;
	}

	/**
	 * Removes all the dm history vma ship types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryVmaShipType dmHistoryVmaShipType : findAll()) {
			repository.delete(dmHistoryVmaShipType);
		}
	}

	/**
	 * Returns the number of dm history vma ship types where ShipTypeCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param ShipTypeCode the ship type code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipTypeCode_syncVersion(String ShipTypeCode,
		String SyncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYVMASHIPTYPE_WHERE);

			if (ShipTypeCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_1);
			}
			else {
				if (ShipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipTypeCode != null) {
					builder.appendNamedParameterMap("ShipTypeCode", ShipTypeCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
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
	 * Returns the number of dm history vma ship types.
	 *
	 * @return the number of dm history vma ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYVMASHIPTYPE).build();

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
	 * Initializes the dm history vma ship type persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYVMASHIPTYPE = "SELECT dmHistoryVmaShipType FROM DmHistoryVmaShipType dmHistoryVmaShipType";
	private static final String _SQL_SELECT_DMHISTORYVMASHIPTYPE_WHERE = "SELECT dmHistoryVmaShipType FROM DmHistoryVmaShipType dmHistoryVmaShipType WHERE ";
	private static final String _SQL_COUNT_DMHISTORYVMASHIPTYPE = "SELECT COUNT(dmHistoryVmaShipType) FROM DmHistoryVmaShipType dmHistoryVmaShipType";
	private static final String _SQL_COUNT_DMHISTORYVMASHIPTYPE_WHERE = "SELECT COUNT(dmHistoryVmaShipType) FROM DmHistoryVmaShipType dmHistoryVmaShipType WHERE ";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_1 =
		"dmHistoryVmaShipType.ShipTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_2 =
		"dmHistoryVmaShipType.ShipTypeCode =:ShipTypeCode AND ";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SHIPTYPECODE_3 =
		"(dmHistoryVmaShipType.ShipTypeCode IS NULL OR dmHistoryVmaShipType.ShipTypeCode =:ShipTypeCode) AND ";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryVmaShipType.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryVmaShipType.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_SHIPTYPECODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryVmaShipType.SyncVersion IS NULL OR dmHistoryVmaShipType.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryVmaShipType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryVmaShipType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryVmaShipType exists with the key {";
	

	
}
