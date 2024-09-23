/////**
//// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
//// *
//// * This library is free software; you can redistribute it and/or modify it under
//// * the terms of the GNU Lesser General Public License as published by the Free
//// * Software Foundation; either version 2.1 of the License, or (at your option)
//// * any later version.
//// *
//// * This library is distributed in the hope that it will be useful, but WITHOUT
//// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
//// * details.
//// */
////
////package com.fds.nsw.nghiepvu.danhmuc.service.persistence;
////
////import java.io.Serializable;
////import java.sql.Types;
////import java.util.List;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Qualifier;
////import org.springframework.stereotype.Service;
////
////import com.fds.flex.common.utility.string.StringBundler;
////import com.fds.flex.common.utility.string.StringPool;
////import com.fds.nsw.kernel.dao.orm.BasePersistence;
////import com.fds.nsw.kernel.dao.orm.QueryUtil;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
////import com.fds.nsw.kernel.exception.SystemException;
////import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
////import com.fds.nsw.kernel.util.OrderByComparator;
////import com.fds.nsw.nghiepvu.model.HistoryRmdcShip;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.HistoryRmdcShipRepository;
////import com.fds.nsw.nghiepvu.modelImpl.HistoryRmdcShipModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class HistoryRmdcShipPersistenceImpl extends BasePersistence {
////	@Autowired
////	HistoryRmdcShipRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<HistoryRmdcShip> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link HistoryRmdcShipUtil} to access the history rmdc ship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public HistoryRmdcShip create(long id) {
////		HistoryRmdcShip historyRmdcShip = new HistoryRmdcShip();
////
////
////		//historyRmdcShip.setPrimaryKey(id);
////
////		return historyRmdcShip;
////	}
////
////	/**
////	 * Removes the history rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the history rmdc ship
////	 * @return the history rmdc ship that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip remove(long id)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the history rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the history rmdc ship
////	 * @return the history rmdc ship that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public HistoryRmdcShip remove(Serializable primaryKey)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////
////
////		try {
////
////
////			HistoryRmdcShip historyRmdcShip = findByPrimaryKey(primaryKey);
////
////			if (historyRmdcShip == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchHistoryRmdcShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(historyRmdcShip);
////			return historyRmdcShip;
////		}
////		catch (NoSuchHistoryRmdcShipException nsee) {
////			throw nsee;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////
////	public HistoryRmdcShip remove(HistoryRmdcShip HistoryRmdcShip) throws SystemException {
//	removeImpl(HistoryRmdcShip);	return HistoryRmdcShip;
//}
//
//protected HistoryRmdcShip removeImpl
//
//(HistoryRmdcShip historyRmdcShip)
////		throws SystemException {
////		try {
////			repository.delete(historyRmdcShip);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return historyRmdcShip;
////	}
////
////
////	public HistoryRmdcShip updateImpl(
////		HistoryRmdcShip historyRmdcShip, boolean merge)
////		throws SystemException {
////		try {
////
////			repository.saveAndFlush(historyRmdcShip);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return historyRmdcShip;
////	}
////
////
////	public HistoryRmdcShip findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the history rmdc ship with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException} if it could not be found.
////	 *
////	 * @param id the primary key of the history rmdc ship
////	 * @return the history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip findByPrimaryKey(long id)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = fetchByPrimaryKey(id);
////
////		if (historyRmdcShip == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchHistoryRmdcShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return historyRmdcShip;
////	}
////
////	/**
////	 * Returns the history rmdc ship with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the history rmdc ship
////	 * @return the history rmdc ship, or <code>null</code> if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public HistoryRmdcShip fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the history rmdc ship with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the history rmdc ship
////	 * @return the history rmdc ship, or <code>null</code> if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip fetchByPrimaryKey(long id) throws SystemException {
////		HistoryRmdcShip historyRmdcShip = null;
////
////
////
////		if (historyRmdcShip == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<HistoryRmdcShip> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					historyRmdcShip = optional.get();
////				}
////			}
////			catch (Exception e) {
////				hasException = true;
////
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return historyRmdcShip;
////	}
////
////	/**
////	 * Returns all the history rmdc ships where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @return the matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findBySyncVersion(String syncVersion)
////		throws SystemException {
////		return findBySyncVersion(syncVersion, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history rmdc ships where syncVersion = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param syncVersion the sync version
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @return the range of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findBySyncVersion(String syncVersion,
////		int start, int end) throws SystemException {
////		return findBySyncVersion(syncVersion, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history rmdc ships where syncVersion = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param syncVersion the sync version
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findBySyncVersion(String syncVersion,
////		int start, int end, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<HistoryRmdcShip> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(3 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(3);
////			}
////
////			query.append(_SQL_SELECT_HISTORYRMDCSHIP_WHERE);
////
////			if (syncVersion == null) {
////				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
////			}
////			else {
////				if (syncVersion.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
////				}
////			}
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(HistoryRmdcShipModelImpl.ORDER_BY_JPQL);
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (syncVersion != null) {
////					builder.appendNamedParameterMap("syncVersion", syncVersion);
////				}
////
////				list = (List<HistoryRmdcShip>)queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Returns the first history rmdc ship in the ordered set where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip findBySyncVersion_First(String syncVersion,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = fetchBySyncVersion_First(syncVersion,
////				orderByComparator);
////
////		if (historyRmdcShip != null) {
////			return historyRmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("syncVersion=");
////		msg.append(syncVersion);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the first history rmdc ship in the ordered set where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history rmdc ship, or <code>null</code> if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip fetchBySyncVersion_First(String syncVersion,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryRmdcShip> list = findBySyncVersion(syncVersion, 0, 1,
////				orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last history rmdc ship in the ordered set where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip findBySyncVersion_Last(String syncVersion,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = fetchBySyncVersion_Last(syncVersion,
////				orderByComparator);
////
////		if (historyRmdcShip != null) {
////			return historyRmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("syncVersion=");
////		msg.append(syncVersion);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the last history rmdc ship in the ordered set where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history rmdc ship, or <code>null</code> if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip fetchBySyncVersion_Last(String syncVersion,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countBySyncVersion(syncVersion);
////
////		List<HistoryRmdcShip> list = findBySyncVersion(syncVersion, count - 1,
////				count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the history rmdc ships before and after the current history rmdc ship in the ordered set where syncVersion = &#63;.
////	 *
////	 * @param id the primary key of the current history rmdc ship
////	 * @param syncVersion the sync version
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip[] findBySyncVersion_PrevAndNext(long id,
////		String syncVersion, OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			HistoryRmdcShip[] array = new HistoryRmdcShip[3];
////
////			array[0] = getBySyncVersion_PrevAndNext(historyRmdcShip,
////					syncVersion, orderByComparator, true);
////
////			array[1] = historyRmdcShip;
////
////			array[2] = getBySyncVersion_PrevAndNext(historyRmdcShip,
////					syncVersion, orderByComparator, false);
////
////			return array;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////	protected HistoryRmdcShip getBySyncVersion_PrevAndNext(
////		HistoryRmdcShip historyRmdcShip, String syncVersion,
////		OrderByComparator orderByComparator, boolean previous) {
////		StringBundler query = null;
////
////		if (orderByComparator != null) {
////			query = new StringBundler(6 +
////					(orderByComparator.getOrderByFields().length * 6));
////		}
////		else {
////			query = new StringBundler(3);
////		}
////
////		query.append(_SQL_SELECT_HISTORYRMDCSHIP_WHERE);
////
////		if (syncVersion == null) {
////			query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
////		}
////		else {
////			if (syncVersion.equals(StringPool.BLANK)) {
////				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
////			}
////			else {
////				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
////			}
////		}
////
////		if (orderByComparator != null) {
////			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
////
////			if (orderByConditionFields.length > 0) {
////				query.append(WHERE_AND);
////			}
////
////			for (int i = 0; i < orderByConditionFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByConditionFields[i]);
////
////				if ((i + 1) < orderByConditionFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN_HAS_NEXT);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN);
////					}
////				}
////			}
////
////			query.append(ORDER_BY_CLAUSE);
////
////			String[] orderByFields = orderByComparator.getOrderByFields();
////
////			for (int i = 0; i < orderByFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByFields[i]);
////
////				if ((i + 1) < orderByFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC_HAS_NEXT);
////					}
////					else {
////						query.append(ORDER_BY_DESC_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC);
////					}
////					else {
////						query.append(ORDER_BY_DESC);
////					}
////				}
////			}
////		}
////
////		else {
////			query.append(HistoryRmdcShipModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		if (syncVersion != null) {
////			builder.appendNamedParameterMap("syncVersion", syncVersion);
////		}
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(historyRmdcShip);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<HistoryRmdcShip> list = queryFactory.getResultList(builder);
////
////		if (list.size() == 2) {
////			return list.get(1);
////		}
////		else {
////			return null;
////		}
////	}
////
////	/**
////	 * Returns all the history rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @return the matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		return findByShipTypeCode(shipTypeCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @return the range of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findByShipTypeCode(String shipTypeCode,
////		int start, int end) throws SystemException {
////		return findByShipTypeCode(shipTypeCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findByShipTypeCode(String shipTypeCode,
////		int start, int end, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<HistoryRmdcShip> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(3 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(3);
////			}
////
////			query.append(_SQL_SELECT_HISTORYRMDCSHIP_WHERE);
////
////			if (shipTypeCode == null) {
////				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
////			}
////			else {
////				if (shipTypeCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
////				}
////			}
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(HistoryRmdcShipModelImpl.ORDER_BY_JPQL);
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (shipTypeCode != null) {
////					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
////				}
////
////				list = (List<HistoryRmdcShip>)queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Returns the first history rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip findByShipTypeCode_First(String shipTypeCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = fetchByShipTypeCode_First(shipTypeCode,
////				orderByComparator);
////
////		if (historyRmdcShip != null) {
////			return historyRmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("shipTypeCode=");
////		msg.append(shipTypeCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the first history rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history rmdc ship, or <code>null</code> if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip fetchByShipTypeCode_First(String shipTypeCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryRmdcShip> list = findByShipTypeCode(shipTypeCode, 0, 1,
////				orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last history rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip findByShipTypeCode_Last(String shipTypeCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = fetchByShipTypeCode_Last(shipTypeCode,
////				orderByComparator);
////
////		if (historyRmdcShip != null) {
////			return historyRmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("shipTypeCode=");
////		msg.append(shipTypeCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the last history rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history rmdc ship, or <code>null</code> if a matching history rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip fetchByShipTypeCode_Last(String shipTypeCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countByShipTypeCode(shipTypeCode);
////
////		List<HistoryRmdcShip> list = findByShipTypeCode(shipTypeCode,
////				count - 1, count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the history rmdc ships before and after the current history rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param id the primary key of the current history rmdc ship
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next history rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchHistoryRmdcShipException if a history rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryRmdcShip[] findByShipTypeCode_PrevAndNext(long id,
////		String shipTypeCode, OrderByComparator orderByComparator)
////		throws NoSuchHistoryRmdcShipException, SystemException {
////		HistoryRmdcShip historyRmdcShip = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			HistoryRmdcShip[] array = new HistoryRmdcShip[3];
////
////			array[0] = getByShipTypeCode_PrevAndNext(historyRmdcShip,
////					shipTypeCode, orderByComparator, true);
////
////			array[1] = historyRmdcShip;
////
////			array[2] = getByShipTypeCode_PrevAndNext(historyRmdcShip,
////					shipTypeCode, orderByComparator, false);
////
////			return array;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////	protected HistoryRmdcShip getByShipTypeCode_PrevAndNext(
////		HistoryRmdcShip historyRmdcShip, String shipTypeCode,
////		OrderByComparator orderByComparator, boolean previous) {
////		StringBundler query = null;
////
////		if (orderByComparator != null) {
////			query = new StringBundler(6 +
////					(orderByComparator.getOrderByFields().length * 6));
////		}
////		else {
////			query = new StringBundler(3);
////		}
////
////		query.append(_SQL_SELECT_HISTORYRMDCSHIP_WHERE);
////
////		if (shipTypeCode == null) {
////			query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
////		}
////		else {
////			if (shipTypeCode.equals(StringPool.BLANK)) {
////				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
////			}
////			else {
////				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
////			}
////		}
////
////		if (orderByComparator != null) {
////			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
////
////			if (orderByConditionFields.length > 0) {
////				query.append(WHERE_AND);
////			}
////
////			for (int i = 0; i < orderByConditionFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByConditionFields[i]);
////
////				if ((i + 1) < orderByConditionFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN_HAS_NEXT);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN);
////					}
////				}
////			}
////
////			query.append(ORDER_BY_CLAUSE);
////
////			String[] orderByFields = orderByComparator.getOrderByFields();
////
////			for (int i = 0; i < orderByFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByFields[i]);
////
////				if ((i + 1) < orderByFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC_HAS_NEXT);
////					}
////					else {
////						query.append(ORDER_BY_DESC_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC);
////					}
////					else {
////						query.append(ORDER_BY_DESC);
////					}
////				}
////			}
////		}
////
////		else {
////			query.append(HistoryRmdcShipModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		if (shipTypeCode != null) {
////			builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
////		}
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(historyRmdcShip);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<HistoryRmdcShip> list = queryFactory.getResultList(builder);
////
////		if (list.size() == 2) {
////			return list.get(1);
////		}
////		else {
////			return null;
////		}
////	}
////
////	/**
////	 * Returns all the history rmdc ships.
////	 *
////	 * @return the history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history rmdc ships.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @return the range of history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history rmdc ships.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of history rmdc ships
////	 * @param end the upper bound of the range of history rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryRmdcShip> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryRmdcShip> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_HISTORYRMDCSHIP);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_HISTORYRMDCSHIP.concat(HistoryRmdcShipModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<HistoryRmdcShip>) queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Removes all the history rmdc ships where syncVersion = &#63; from the database.
////	 *
////	 * @param syncVersion the sync version
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeBySyncVersion(String syncVersion)
////		throws SystemException {
////		for (HistoryRmdcShip historyRmdcShip : findBySyncVersion(syncVersion)) {
////			repository.delete(historyRmdcShip);
////		}
////	}
////
////	/**
////	 * Removes all the history rmdc ships where shipTypeCode = &#63; from the database.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		for (HistoryRmdcShip historyRmdcShip : findByShipTypeCode(shipTypeCode)) {
////			repository.delete(historyRmdcShip);
////		}
////	}
////
////	/**
////	 * Removes all the history rmdc ships from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (HistoryRmdcShip historyRmdcShip : findAll()) {
////			repository.delete(historyRmdcShip);
////		}
////	}
////
////	/**
////	 * Returns the number of history rmdc ships where syncVersion = &#63;.
////	 *
////	 * @param syncVersion the sync version
////	 * @return the number of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countBySyncVersion(String syncVersion) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_HISTORYRMDCSHIP_WHERE);
////
////			if (syncVersion == null) {
////				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
////			}
////			else {
////				if (syncVersion.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (syncVersion != null) {
////					builder.appendNamedParameterMap("syncVersion", syncVersion);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of history rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @return the number of matching history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_HISTORYRMDCSHIP_WHERE);
////
////			if (shipTypeCode == null) {
////				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
////			}
////			else {
////				if (shipTypeCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (shipTypeCode != null) {
////					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of history rmdc ships.
////	 *
////	 * @return the number of history rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countAll() throws SystemException {
////		Long count = null;
////
////		if (count == null) {
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_HISTORYRMDCSHIP).build();
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Initializes the history rmdc ship persistence.
////	 */
////	private static final String _SQL_SELECT_HISTORYRMDCSHIP = "SELECT historyRmdcShip FROM HistoryRmdcShip historyRmdcShip";
////	private static final String _SQL_SELECT_HISTORYRMDCSHIP_WHERE = "SELECT historyRmdcShip FROM HistoryRmdcShip historyRmdcShip WHERE ";
////	private static final String _SQL_COUNT_HISTORYRMDCSHIP = "SELECT COUNT(historyRmdcShip) FROM HistoryRmdcShip historyRmdcShip";
////	private static final String _SQL_COUNT_HISTORYRMDCSHIP_WHERE = "SELECT COUNT(historyRmdcShip) FROM HistoryRmdcShip historyRmdcShip WHERE ";
////	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1 = "historyRmdcShip.syncVersion IS NULL";
////	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2 = "historyRmdcShip.syncVersion =:syncVersion";
////	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3 = "(historyRmdcShip.syncVersion IS NULL OR historyRmdcShip.syncVersion =:syncVersion)";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1 = "historyRmdcShip.shipTypeCode IS NULL";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2 = "historyRmdcShip.shipTypeCode =:shipTypeCode";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3 = "(historyRmdcShip.shipTypeCode IS NULL OR historyRmdcShip.shipTypeCode =:shipTypeCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "historyRmdcShip.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistoryRmdcShip exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HistoryRmdcShip exists with the key {";
////
////
////
////}
