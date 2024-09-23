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
import com.fds.nsw.nghiepvu.model.DmHistoryShipAgency;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryShipAgencyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryShipAgencyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryShipAgencyPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryShipAgencyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryShipAgency> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryShipAgencyUtil} to access the dm history ship agency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryShipAgency create(int id) {
		DmHistoryShipAgency dmHistoryShipAgency = new DmHistoryShipAgency();

		
		//dmHistoryShipAgency.setPrimaryKey(id);

		return dmHistoryShipAgency;
	}

	/**
	 * Removes the dm history ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history ship agency
	 * @return the dm history ship agency that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency remove(int id)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history ship agency
	 * @return the dm history ship agency that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipAgency remove(Serializable primaryKey)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		

		try {
			

			DmHistoryShipAgency dmHistoryShipAgency = findByPrimaryKey(primaryKey);

			if (dmHistoryShipAgency == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryShipAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryShipAgency);
			return dmHistoryShipAgency;
		}
		catch (NoSuchDmHistoryShipAgencyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryShipAgency remove(DmHistoryShipAgency DmHistoryShipAgency) throws SystemException {
	removeImpl(DmHistoryShipAgency);	return DmHistoryShipAgency;
}

protected DmHistoryShipAgency removeImpl

(
		DmHistoryShipAgency dmHistoryShipAgency) throws SystemException {
		try {
			repository.delete(dmHistoryShipAgency);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipAgency;
	}

	
	public DmHistoryShipAgency updateImpl(
		DmHistoryShipAgency dmHistoryShipAgency,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryShipAgency);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipAgency;
	}

	
	public DmHistoryShipAgency findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history ship agency with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException} if it could not be found.
	 *
	 * @param id the primary key of the dm history ship agency
	 * @return the dm history ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency findByPrimaryKey(int id)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = fetchByPrimaryKey(id);

		if (dmHistoryShipAgency == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryShipAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryShipAgency;
	}

	/**
	 * Returns the dm history ship agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history ship agency
	 * @return the dm history ship agency, or <code>null</code> if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipAgency fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history ship agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history ship agency
	 * @return the dm history ship agency, or <code>null</code> if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = null;

		

		if (dmHistoryShipAgency == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryShipAgency> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryShipAgency = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryShipAgency;
	}

	/**
	 * Returns all the dm history ship agencies where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the matching dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		return findByShipAgencyCode(shipAgencyCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship agencies where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of dm history ship agencies
	 * @param end the upper bound of the range of dm history ship agencies (not inclusive)
	 * @return the range of matching dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findByShipAgencyCode(
		String shipAgencyCode, int start, int end) throws SystemException {
		return findByShipAgencyCode(shipAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship agencies where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of dm history ship agencies
	 * @param end the upper bound of the range of dm history ship agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findByShipAgencyCode(
		String shipAgencyCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipAgency> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryShipAgencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				list = (List<DmHistoryShipAgency>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency findByShipAgencyCode_First(
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = fetchByShipAgencyCode_First(shipAgencyCode,
				orderByComparator);

		if (dmHistoryShipAgency != null) {
			return dmHistoryShipAgency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipAgencyException(msg.toString());
	}

	/**
	 * Returns the first dm history ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history ship agency, or <code>null</code> if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency fetchByShipAgencyCode_First(
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryShipAgency> list = findByShipAgencyCode(shipAgencyCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency findByShipAgencyCode_Last(
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = fetchByShipAgencyCode_Last(shipAgencyCode,
				orderByComparator);

		if (dmHistoryShipAgency != null) {
			return dmHistoryShipAgency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryShipAgencyException(msg.toString());
	}

	/**
	 * Returns the last dm history ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history ship agency, or <code>null</code> if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency fetchByShipAgencyCode_Last(
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByShipAgencyCode(shipAgencyCode);

		List<DmHistoryShipAgency> list = findByShipAgencyCode(shipAgencyCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history ship agencies before and after the current dm history ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param id the primary key of the current dm history ship agency
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a dm history ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency[] findByShipAgencyCode_PrevAndNext(int id,
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = findByPrimaryKey(id);

		

		try {
			

			DmHistoryShipAgency[] array = new DmHistoryShipAgency[3];

			array[0] = getByShipAgencyCode_PrevAndNext(
					dmHistoryShipAgency, shipAgencyCode, orderByComparator, true);

			array[1] = dmHistoryShipAgency;

			array[2] = getByShipAgencyCode_PrevAndNext(
					dmHistoryShipAgency, shipAgencyCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryShipAgency getByShipAgencyCode_PrevAndNext(
		 DmHistoryShipAgency dmHistoryShipAgency,
		String shipAgencyCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSHIPAGENCY_WHERE);

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
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
			query.append(DmHistoryShipAgencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryShipAgency);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryShipAgency> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history ship agency where shipAgencyCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException} if it could not be found.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param syncVersion the sync version
	 * @return the matching dm history ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipAgencyException if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency findByShipAgencyCodeAndSyncVersion(
		String shipAgencyCode, String syncVersion)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = fetchByShipAgencyCodeAndSyncVersion(shipAgencyCode,
				syncVersion);

		if (dmHistoryShipAgency == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shipAgencyCode=");
			msg.append(shipAgencyCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryShipAgencyException(msg.toString());
		}

		return dmHistoryShipAgency;
	}

	/**
	 * Returns the dm history ship agency where shipAgencyCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param syncVersion the sync version
	 * @return the matching dm history ship agency, or <code>null</code> if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency fetchByShipAgencyCodeAndSyncVersion(
		String shipAgencyCode, String syncVersion) throws SystemException {
		return fetchByShipAgencyCodeAndSyncVersion(shipAgencyCode, syncVersion,
			true);
	}

	/**
	 * Returns the dm history ship agency where shipAgencyCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history ship agency, or <code>null</code> if a matching dm history ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency fetchByShipAgencyCodeAndSyncVersion(
		String shipAgencyCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = null;
		if (dmHistoryShipAgency == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryShipAgencyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryShipAgency.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryShipAgency = (DmHistoryShipAgency) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryShipAgency;
	}

	/**
	 * Returns all the dm history ship agencies.
	 *
	 * @return the dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ship agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship agencies
	 * @param end the upper bound of the range of dm history ship agencies (not inclusive)
	 * @return the range of dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ship agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ship agencies
	 * @param end the upper bound of the range of dm history ship agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipAgency> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipAgency> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSHIPAGENCY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSHIPAGENCY.concat(DmHistoryShipAgencyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryShipAgency>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history ship agencies where shipAgencyCode = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		for (DmHistoryShipAgency dmHistoryShipAgency : findByShipAgencyCode(
				shipAgencyCode)) {
			repository.delete(dmHistoryShipAgency);
		}
	}

	/**
	 * Removes the dm history ship agency where shipAgencyCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param syncVersion the sync version
	 * @return the dm history ship agency that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipAgency removeByShipAgencyCodeAndSyncVersion(
		String shipAgencyCode, String syncVersion)
		throws NoSuchDmHistoryShipAgencyException, SystemException {
		DmHistoryShipAgency dmHistoryShipAgency = findByShipAgencyCodeAndSyncVersion(shipAgencyCode,
				syncVersion);

		repository.delete(dmHistoryShipAgency);
			return dmHistoryShipAgency;
	}

	/**
	 * Removes all the dm history ship agencies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryShipAgency dmHistoryShipAgency : findAll()) {
			repository.delete(dmHistoryShipAgency);
		}
	}

	/**
	 * Returns the number of dm history ship agencies where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the number of matching dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
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
	 * Returns the number of dm history ship agencies where shipAgencyCode = &#63; and syncVersion = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipAgencyCodeAndSyncVersion(String shipAgencyCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
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
	 * Returns the number of dm history ship agencies.
	 *
	 * @return the number of dm history ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSHIPAGENCY).build();

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
	 * Initializes the dm history ship agency persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSHIPAGENCY = "SELECT dmHistoryShipAgency FROM DmHistoryShipAgency dmHistoryShipAgency";
	private static final String _SQL_SELECT_DMHISTORYSHIPAGENCY_WHERE = "SELECT dmHistoryShipAgency FROM DmHistoryShipAgency dmHistoryShipAgency WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSHIPAGENCY = "SELECT COUNT(dmHistoryShipAgency) FROM DmHistoryShipAgency dmHistoryShipAgency";
	private static final String _SQL_COUNT_DMHISTORYSHIPAGENCY_WHERE = "SELECT COUNT(dmHistoryShipAgency) FROM DmHistoryShipAgency dmHistoryShipAgency WHERE ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1 = "dmHistoryShipAgency.shipAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2 = "dmHistoryShipAgency.shipAgencyCode =:shipAgencyCode";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3 = "(dmHistoryShipAgency.shipAgencyCode IS NULL OR dmHistoryShipAgency.shipAgencyCode =:shipAgencyCode)";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_1 =
		"dmHistoryShipAgency.shipAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_2 =
		"dmHistoryShipAgency.shipAgencyCode =:shipAgencyCode AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SHIPAGENCYCODE_3 =
		"(dmHistoryShipAgency.shipAgencyCode IS NULL OR dmHistoryShipAgency.shipAgencyCode =:shipAgencyCode) AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryShipAgency.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryShipAgency.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryShipAgency.syncVersion IS NULL OR dmHistoryShipAgency.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryShipAgency.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryShipAgency exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryShipAgency exists with the key {";
	

	
}
