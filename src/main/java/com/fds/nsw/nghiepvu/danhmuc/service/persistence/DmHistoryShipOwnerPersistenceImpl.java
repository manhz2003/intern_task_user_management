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
import com.fds.nsw.nghiepvu.model.DmHistoryShipOwner;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryShipOwnerRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryShipOwnerModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryShipOwnerPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryShipOwnerRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryShipOwner> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryShipOwnerUtil} to access the dm history ship owner persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryShipOwner create(long id) {
		DmHistoryShipOwner dmHistoryShipOwner = new DmHistoryShipOwner();

		
		//dmHistoryShipOwner.setPrimaryKey(id);

		return dmHistoryShipOwner;
	}

	/**
	 * Removes the dm history ship owner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm history ship owner
	 * @return the dm history ship owner that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner remove(long Id)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm history ship owner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history ship owner
	 * @return the dm history ship owner that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipOwner remove(Serializable primaryKey)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		

		try {
			

			DmHistoryShipOwner dmHistoryShipOwner = findByPrimaryKey(primaryKey);

			if (dmHistoryShipOwner == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryShipOwnerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryShipOwner);
			return dmHistoryShipOwner;
		}
		catch (NoSuchDmHistoryShipOwnerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryShipOwner remove(DmHistoryShipOwner DmHistoryShipOwner) throws SystemException {
	removeImpl(DmHistoryShipOwner);	return DmHistoryShipOwner;
}

protected DmHistoryShipOwner removeImpl

(
		DmHistoryShipOwner dmHistoryShipOwner) throws SystemException {
		try {
			repository.delete(dmHistoryShipOwner);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipOwner;
	}

	
	public DmHistoryShipOwner updateImpl(
		DmHistoryShipOwner dmHistoryShipOwner,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryShipOwner);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipOwner;
	}

	
	public DmHistoryShipOwner findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history ship owner with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException} if it could not be found.
	 *
	 * @param Id the primary key of the dm history ship owner
	 * @return the dm history ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner findByPrimaryKey(long Id)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = fetchByPrimaryKey(Id);

		if (dmHistoryShipOwner == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmHistoryShipOwnerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmHistoryShipOwner;
	}

	/**
	 * Returns the dm history ship owner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history ship owner
	 * @return the dm history ship owner, or <code>null</code> if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipOwner fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history ship owner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm history ship owner
	 * @return the dm history ship owner, or <code>null</code> if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner fetchByPrimaryKey(long Id)
		throws SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = null;

		

		if (dmHistoryShipOwner == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryShipOwner> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmHistoryShipOwner = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryShipOwner;
	}

	/**
	 * Returns all the dm history ship owners where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship owners where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm history ship owners
	 * @param end the upper bound of the range of dm history ship owners (not inclusive)
	 * @return the range of matching dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship owners where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm history ship owners
	 * @param end the upper bound of the range of dm history ship owners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryShipOwner> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSHIPOWNER_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryShipOwnerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmHistoryShipOwner>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmHistoryShipOwner != null) {
			return dmHistoryShipOwner;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipOwnerException(msg.toString());
	}

	/**
	 * Returns the first dm history ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship owner, or <code>null</code> if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipOwner> list = findByF_maritimeCode(MaritimeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmHistoryShipOwner != null) {
			return dmHistoryShipOwner;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipOwnerException(msg.toString());
	}

	/**
	 * Returns the last dm history ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship owner, or <code>null</code> if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmHistoryShipOwner> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history ship owners before and after the current dm history ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm history ship owner
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a dm history ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = findByPrimaryKey(Id);

		

		try {
			

			DmHistoryShipOwner[] array = new DmHistoryShipOwner[3];

			array[0] = getByF_maritimeCode_PrevAndNext(
					dmHistoryShipOwner, MaritimeCode, orderByComparator, true);

			array[1] = dmHistoryShipOwner;

			array[2] = getByF_maritimeCode_PrevAndNext(
					dmHistoryShipOwner, MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryShipOwner getByF_maritimeCode_PrevAndNext(
		 DmHistoryShipOwner dmHistoryShipOwner,
		String MaritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSHIPOWNER_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
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
			query.append(DmHistoryShipOwnerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryShipOwner);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryShipOwner> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history ship owner where ShipOwnerCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException} if it could not be found.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param SyncVersion the sync version
	 * @return the matching dm history ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipOwnerException if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner findByF_shipOwnerCode_syncVersion(
		String ShipOwnerCode, String SyncVersion)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = fetchByF_shipOwnerCode_syncVersion(ShipOwnerCode,
				SyncVersion);

		if (dmHistoryShipOwner == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipOwnerCode=");
			msg.append(ShipOwnerCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryShipOwnerException(msg.toString());
		}

		return dmHistoryShipOwner;
	}

	/**
	 * Returns the dm history ship owner where ShipOwnerCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param SyncVersion the sync version
	 * @return the matching dm history ship owner, or <code>null</code> if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner fetchByF_shipOwnerCode_syncVersion(
		String ShipOwnerCode, String SyncVersion) throws SystemException {
		return fetchByF_shipOwnerCode_syncVersion(ShipOwnerCode, SyncVersion,
			true);
	}

	/**
	 * Returns the dm history ship owner where ShipOwnerCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history ship owner, or <code>null</code> if a matching dm history ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner fetchByF_shipOwnerCode_syncVersion(
		String ShipOwnerCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = null;
		if (dmHistoryShipOwner == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSHIPOWNER_WHERE);

			if (ShipOwnerCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_1);
			}
			else {
				if (ShipOwnerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryShipOwnerModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryShipOwner.class).build();

				

				if (ShipOwnerCode != null) {
					builder.appendNamedParameterMap("ShipOwnerCode", ShipOwnerCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryShipOwner = (DmHistoryShipOwner) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryShipOwner;
	}

	/**
	 * Returns all the dm history ship owners.
	 *
	 * @return the dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship owners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship owners
	 * @param end the upper bound of the range of dm history ship owners (not inclusive)
	 * @return the range of dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship owners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship owners
	 * @param end the upper bound of the range of dm history ship owners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipOwner> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipOwner> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSHIPOWNER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSHIPOWNER.concat(DmHistoryShipOwnerModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryShipOwner>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history ship owners where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmHistoryShipOwner dmHistoryShipOwner : findByF_maritimeCode(
				MaritimeCode)) {
			repository.delete(dmHistoryShipOwner);
		}
	}

	/**
	 * Removes the dm history ship owner where ShipOwnerCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param SyncVersion the sync version
	 * @return the dm history ship owner that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipOwner removeByF_shipOwnerCode_syncVersion(
		String ShipOwnerCode, String SyncVersion)
		throws NoSuchDmHistoryShipOwnerException, SystemException {
		DmHistoryShipOwner dmHistoryShipOwner = findByF_shipOwnerCode_syncVersion(ShipOwnerCode,
				SyncVersion);

		repository.delete(dmHistoryShipOwner);
			return dmHistoryShipOwner;
	}

	/**
	 * Removes all the dm history ship owners from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryShipOwner dmHistoryShipOwner : findAll()) {
			repository.delete(dmHistoryShipOwner);
		}
	}

	/**
	 * Returns the number of dm history ship owners where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSHIPOWNER_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
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
	 * Returns the number of dm history ship owners where ShipOwnerCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipOwnerCode_syncVersion(String ShipOwnerCode,
		String SyncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSHIPOWNER_WHERE);

			if (ShipOwnerCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_1);
			}
			else {
				if (ShipOwnerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipOwnerCode != null) {
					builder.appendNamedParameterMap("ShipOwnerCode", ShipOwnerCode);
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
	 * Returns the number of dm history ship owners.
	 *
	 * @return the number of dm history ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSHIPOWNER).build();

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
	 * Initializes the dm history ship owner persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSHIPOWNER = "SELECT dmHistoryShipOwner FROM DmHistoryShipOwner dmHistoryShipOwner";
	private static final String _SQL_SELECT_DMHISTORYSHIPOWNER_WHERE = "SELECT dmHistoryShipOwner FROM DmHistoryShipOwner dmHistoryShipOwner WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSHIPOWNER = "SELECT COUNT(dmHistoryShipOwner) FROM DmHistoryShipOwner dmHistoryShipOwner";
	private static final String _SQL_COUNT_DMHISTORYSHIPOWNER_WHERE = "SELECT COUNT(dmHistoryShipOwner) FROM DmHistoryShipOwner dmHistoryShipOwner WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmHistoryShipOwner.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmHistoryShipOwner.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmHistoryShipOwner.MaritimeCode IS NULL OR dmHistoryShipOwner.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_1 =
		"dmHistoryShipOwner.ShipOwnerCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_2 =
		"dmHistoryShipOwner.ShipOwnerCode =:ShipOwnerCode AND ";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SHIPOWNERCODE_3 =
		"(dmHistoryShipOwner.ShipOwnerCode IS NULL OR dmHistoryShipOwner.ShipOwnerCode =:ShipOwnerCode) AND ";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryShipOwner.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryShipOwner.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryShipOwner.SyncVersion IS NULL OR dmHistoryShipOwner.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryShipOwner.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryShipOwner exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryShipOwner exists with the key {";
	

	
}
