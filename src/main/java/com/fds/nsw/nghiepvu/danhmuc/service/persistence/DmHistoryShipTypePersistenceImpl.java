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
import com.fds.nsw.nghiepvu.model.DmHistoryShipType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryShipTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryShipTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryShipTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryShipTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryShipType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryShipTypeUtil} to access the dm history ship type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryShipType create(int id) {
		DmHistoryShipType dmHistoryShipType = new DmHistoryShipType();

		
		//dmHistoryShipType.setPrimaryKey(id);

		return dmHistoryShipType;
	}

	/**
	 * Removes the dm history ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history ship type
	 * @return the dm history ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType remove(int id)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history ship type
	 * @return the dm history ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipType remove(Serializable primaryKey)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		

		try {
			

			DmHistoryShipType dmHistoryShipType = findByPrimaryKey(primaryKey);

			if (dmHistoryShipType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryShipType);
			return dmHistoryShipType;
		}
		catch (NoSuchDmHistoryShipTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryShipType remove(DmHistoryShipType DmHistoryShipType) throws SystemException {
	removeImpl(DmHistoryShipType);	return DmHistoryShipType;
}

protected DmHistoryShipType removeImpl

(DmHistoryShipType dmHistoryShipType)
		throws SystemException {
		try {
			repository.delete(dmHistoryShipType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipType;
	}

	
	public DmHistoryShipType updateImpl(
		DmHistoryShipType dmHistoryShipType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryShipType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipType;
	}

	
	public DmHistoryShipType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history ship type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history ship type
	 * @return the dm history ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType findByPrimaryKey(int id)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = fetchByPrimaryKey(id);

		if (dmHistoryShipType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryShipType;
	}

	/**
	 * Returns the dm history ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history ship type
	 * @return the dm history ship type, or <code>null</code> if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history ship type
	 * @return the dm history ship type, or <code>null</code> if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryShipType dmHistoryShipType = null;

		

		if (dmHistoryShipType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryShipType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryShipType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryShipType;
	}

	/**
	 * Returns all the dm history ship types where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @return the matching dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findByShipTypeCode(String shipTypeCode)
		throws SystemException {
		return findByShipTypeCode(shipTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship types where shipTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeCode the ship type code
	 * @param start the lower bound of the range of dm history ship types
	 * @param end the upper bound of the range of dm history ship types (not inclusive)
	 * @return the range of matching dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findByShipTypeCode(String shipTypeCode,
		int start, int end) throws SystemException {
		return findByShipTypeCode(shipTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship types where shipTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeCode the ship type code
	 * @param start the lower bound of the range of dm history ship types
	 * @param end the upper bound of the range of dm history ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findByShipTypeCode(String shipTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryShipType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryShipTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				list = (List<DmHistoryShipType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType findByShipTypeCode_First(String shipTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = fetchByShipTypeCode_First(shipTypeCode,
				orderByComparator);

		if (dmHistoryShipType != null) {
			return dmHistoryShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipTypeException(msg.toString());
	}

	/**
	 * Returns the first dm history ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship type, or <code>null</code> if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType fetchByShipTypeCode_First(String shipTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipType> list = findByShipTypeCode(shipTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType findByShipTypeCode_Last(String shipTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = fetchByShipTypeCode_Last(shipTypeCode,
				orderByComparator);

		if (dmHistoryShipType != null) {
			return dmHistoryShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipTypeException(msg.toString());
	}

	/**
	 * Returns the last dm history ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship type, or <code>null</code> if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType fetchByShipTypeCode_Last(String shipTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByShipTypeCode(shipTypeCode);

		List<DmHistoryShipType> list = findByShipTypeCode(shipTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history ship types before and after the current dm history ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm history ship type
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a dm history ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType[] findByShipTypeCode_PrevAndNext(int id,
		String shipTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = findByPrimaryKey(id);

		

		try {
			

			DmHistoryShipType[] array = new DmHistoryShipType[3];

			array[0] = getByShipTypeCode_PrevAndNext(
					dmHistoryShipType, shipTypeCode, orderByComparator, true);

			array[1] = dmHistoryShipType;

			array[2] = getByShipTypeCode_PrevAndNext(
					dmHistoryShipType, shipTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryShipType getByShipTypeCode_PrevAndNext(
		DmHistoryShipType dmHistoryShipType, String shipTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSHIPTYPE_WHERE);

		if (shipTypeCode == null) {
			query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
		}
		else {
			if (shipTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmHistoryShipTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipTypeCode != null) {
			builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryShipType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryShipType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history ship type where shipTypeCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException} if it could not be found.
	 *
	 * @param shipTypeCode the ship type code
	 * @param syncVersion the sync version
	 * @return the matching dm history ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipTypeException if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType findByShipTypeCodeAndSyncVersion(
		String shipTypeCode, String syncVersion)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = fetchByShipTypeCodeAndSyncVersion(shipTypeCode,
				syncVersion);

		if (dmHistoryShipType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shipTypeCode=");
			msg.append(shipTypeCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryShipTypeException(msg.toString());
		}

		return dmHistoryShipType;
	}

	/**
	 * Returns the dm history ship type where shipTypeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shipTypeCode the ship type code
	 * @param syncVersion the sync version
	 * @return the matching dm history ship type, or <code>null</code> if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType fetchByShipTypeCodeAndSyncVersion(
		String shipTypeCode, String syncVersion) throws SystemException {
		return fetchByShipTypeCodeAndSyncVersion(shipTypeCode, syncVersion, true);
	}

	/**
	 * Returns the dm history ship type where shipTypeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shipTypeCode the ship type code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history ship type, or <code>null</code> if a matching dm history ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType fetchByShipTypeCodeAndSyncVersion(
		String shipTypeCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryShipType dmHistoryShipType = null;
		if (dmHistoryShipType == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryShipTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryShipType.class).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryShipType = (DmHistoryShipType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryShipType;
	}

	/**
	 * Returns all the dm history ship types.
	 *
	 * @return the dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship types
	 * @param end the upper bound of the range of dm history ship types (not inclusive)
	 * @return the range of dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship types
	 * @param end the upper bound of the range of dm history ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSHIPTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSHIPTYPE.concat(DmHistoryShipTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryShipType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history ship types where shipTypeCode = &#63; from the database.
	 *
	 * @param shipTypeCode the ship type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByShipTypeCode(String shipTypeCode)
		throws SystemException {
		for (DmHistoryShipType dmHistoryShipType : findByShipTypeCode(
				shipTypeCode)) {
			repository.delete(dmHistoryShipType);
		}
	}

	/**
	 * Removes the dm history ship type where shipTypeCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param shipTypeCode the ship type code
	 * @param syncVersion the sync version
	 * @return the dm history ship type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipType removeByShipTypeCodeAndSyncVersion(
		String shipTypeCode, String syncVersion)
		throws NoSuchDmHistoryShipTypeException, SystemException {
		DmHistoryShipType dmHistoryShipType = findByShipTypeCodeAndSyncVersion(shipTypeCode,
				syncVersion);

		repository.delete(dmHistoryShipType);
			return dmHistoryShipType;
	}

	/**
	 * Removes all the dm history ship types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryShipType dmHistoryShipType : findAll()) {
			repository.delete(dmHistoryShipType);
		}
	}

	/**
	 * Returns the number of dm history ship types where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @return the number of matching dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipTypeCode(String shipTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
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
	 * Returns the number of dm history ship types where shipTypeCode = &#63; and syncVersion = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipTypeCodeAndSyncVersion(String shipTypeCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
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
	 * Returns the number of dm history ship types.
	 *
	 * @return the number of dm history ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSHIPTYPE).build();

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
	 * Initializes the dm history ship type persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSHIPTYPE = "SELECT dmHistoryShipType FROM DmHistoryShipType dmHistoryShipType";
	private static final String _SQL_SELECT_DMHISTORYSHIPTYPE_WHERE = "SELECT dmHistoryShipType FROM DmHistoryShipType dmHistoryShipType WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSHIPTYPE = "SELECT COUNT(dmHistoryShipType) FROM DmHistoryShipType dmHistoryShipType";
	private static final String _SQL_COUNT_DMHISTORYSHIPTYPE_WHERE = "SELECT COUNT(dmHistoryShipType) FROM DmHistoryShipType dmHistoryShipType WHERE ";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1 = "dmHistoryShipType.shipTypeCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2 = "dmHistoryShipType.shipTypeCode =:shipTypeCode";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3 = "(dmHistoryShipType.shipTypeCode IS NULL OR dmHistoryShipType.shipTypeCode =:shipTypeCode)";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_1 =
		"dmHistoryShipType.shipTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_2 =
		"dmHistoryShipType.shipTypeCode =:shipTypeCode AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SHIPTYPECODE_3 =
		"(dmHistoryShipType.shipTypeCode IS NULL OR dmHistoryShipType.shipTypeCode =:shipTypeCode) AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryShipType.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryShipType.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SHIPTYPECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryShipType.syncVersion IS NULL OR dmHistoryShipType.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryShipType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryShipType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryShipType exists with the key {";
	

	
}
