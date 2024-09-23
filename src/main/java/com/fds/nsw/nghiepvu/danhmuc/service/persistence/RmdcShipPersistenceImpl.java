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
////import com.fds.nsw.nghiepvu.model.RmdcShip;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.RmdcShipRepository;
////import com.fds.nsw.nghiepvu.modelImpl.RmdcShipModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class RmdcShipPersistenceImpl extends BasePersistence {
////	@Autowired
////	RmdcShipRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<RmdcShip> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link RmdcShipUtil} to access the rmdc ship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public RmdcShip create(long id) {
////		RmdcShip rmdcShip = new RmdcShip();
////
////
////		//rmdcShip.setPrimaryKey(id);
////
////		return rmdcShip;
////	}
////
////	/**
////	 * Removes the rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the rmdc ship
////	 * @return the rmdc ship that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip remove(long id)
////		throws NoSuchRmdcShipException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the rmdc ship with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the rmdc ship
////	 * @return the rmdc ship that was removed
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public RmdcShip remove(Serializable primaryKey)
////		throws NoSuchRmdcShipException, SystemException {
////
////
////		try {
////
////
////			RmdcShip rmdcShip = findByPrimaryKey(primaryKey);
////
////			if (rmdcShip == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchRmdcShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(rmdcShip);
////			return rmdcShip;
////		}
////		catch (NoSuchRmdcShipException nsee) {
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
////	public RmdcShip remove(RmdcShip RmdcShip) throws SystemException {
//	removeImpl(RmdcShip);	return RmdcShip;
//}
//
//protected RmdcShip removeImpl
//
//(RmdcShip rmdcShip) throws SystemException {
////		try {
////			repository.delete(rmdcShip);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return rmdcShip;
////	}
////
////
////	public RmdcShip updateImpl(RmdcShip rmdcShip,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(rmdcShip);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return rmdcShip;
////	}
////
////
////	public RmdcShip findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the rmdc ship with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchRmdcShipException} if it could not be found.
////	 *
////	 * @param id the primary key of the rmdc ship
////	 * @return the rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip findByPrimaryKey(long id)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = fetchByPrimaryKey(id);
////
////		if (rmdcShip == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchRmdcShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return rmdcShip;
////	}
////
////	/**
////	 * Returns the rmdc ship with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the rmdc ship
////	 * @return the rmdc ship, or <code>null</code> if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public RmdcShip fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the rmdc ship with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the rmdc ship
////	 * @return the rmdc ship, or <code>null</code> if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip fetchByPrimaryKey(long id) throws SystemException {
////		RmdcShip rmdcShip = null;
////
////
////
////		if (rmdcShip == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<RmdcShip> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					rmdcShip = optional.get();
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
////		return rmdcShip;
////	}
////
////	/**
////	 * Returns all the rmdc ships where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @return the matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipId(int shipId) throws SystemException {
////		return findByShipId(shipId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the rmdc ships where shipId = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipId the ship ID
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @return the range of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipId(int shipId, int start, int end)
////		throws SystemException {
////		return findByShipId(shipId, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the rmdc ships where shipId = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipId the ship ID
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipId(int shipId, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<RmdcShip> list = null;
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
////			query.append(_SQL_SELECT_RMDCSHIP_WHERE);
////
////			query.append(_FINDER_COLUMN_SHIPID_SHIPID_2);
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(RmdcShipModelImpl.ORDER_BY_JPQL);
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
////				builder.appendNamedParameterMap("shipId", shipId);
////
////				list = (List<RmdcShip>)queryFactory.getResultList(builder);
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
////	 * Returns the first rmdc ship in the ordered set where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip findByShipId_First(int shipId,
////		OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = fetchByShipId_First(shipId, orderByComparator);
////
////		if (rmdcShip != null) {
////			return rmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("shipId=");
////		msg.append(shipId);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the first rmdc ship in the ordered set where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching rmdc ship, or <code>null</code> if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip fetchByShipId_First(int shipId,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<RmdcShip> list = findByShipId(shipId, 0, 1, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last rmdc ship in the ordered set where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip findByShipId_Last(int shipId,
////		OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = fetchByShipId_Last(shipId, orderByComparator);
////
////		if (rmdcShip != null) {
////			return rmdcShip;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("shipId=");
////		msg.append(shipId);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the last rmdc ship in the ordered set where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching rmdc ship, or <code>null</code> if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip fetchByShipId_Last(int shipId,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countByShipId(shipId);
////
////		List<RmdcShip> list = findByShipId(shipId, count - 1, count,
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
////	 * Returns the rmdc ships before and after the current rmdc ship in the ordered set where shipId = &#63;.
////	 *
////	 * @param id the primary key of the current rmdc ship
////	 * @param shipId the ship ID
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip[] findByShipId_PrevAndNext(long id, int shipId,
////		OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			RmdcShip[] array = new RmdcShip[3];
////
////			array[0] = getByShipId_PrevAndNext(rmdcShip, shipId,
////					orderByComparator, true);
////
////			array[1] = rmdcShip;
////
////			array[2] = getByShipId_PrevAndNext(rmdcShip, shipId,
////					orderByComparator, false);
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
////	protected RmdcShip getByShipId_PrevAndNext(
////		RmdcShip rmdcShip, int shipId, OrderByComparator orderByComparator,
////		boolean previous) {
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
////		query.append(_SQL_SELECT_RMDCSHIP_WHERE);
////
////		query.append(_FINDER_COLUMN_SHIPID_SHIPID_2);
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
////			query.append(RmdcShipModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		builder.appendNamedParameterMap("shipId", shipId);
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(rmdcShip);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<RmdcShip> list = queryFactory.getResultList(builder);
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
////	 * Returns all the rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @return the matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		return findByShipTypeCode(shipTypeCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @return the range of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipTypeCode(String shipTypeCode, int start,
////		int end) throws SystemException {
////		return findByShipTypeCode(shipTypeCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findByShipTypeCode(String shipTypeCode, int start,
////		int end, OrderByComparator orderByComparator) throws SystemException {
////		List<RmdcShip> list = null;
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
////			query.append(_SQL_SELECT_RMDCSHIP_WHERE);
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
////				query.append(RmdcShipModelImpl.ORDER_BY_JPQL);
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
////				list = (List<RmdcShip>)queryFactory.getResultList(builder);
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
////	 * Returns the first rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip findByShipTypeCode_First(String shipTypeCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = fetchByShipTypeCode_First(shipTypeCode,
////				orderByComparator);
////
////		if (rmdcShip != null) {
////			return rmdcShip;
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
////		throw new NoSuchRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the first rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching rmdc ship, or <code>null</code> if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip fetchByShipTypeCode_First(String shipTypeCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<RmdcShip> list = findByShipTypeCode(shipTypeCode, 0, 1,
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
////	 * Returns the last rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip findByShipTypeCode_Last(String shipTypeCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = fetchByShipTypeCode_Last(shipTypeCode,
////				orderByComparator);
////
////		if (rmdcShip != null) {
////			return rmdcShip;
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
////		throw new NoSuchRmdcShipException(msg.toString());
////	}
////
////	/**
////	 * Returns the last rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching rmdc ship, or <code>null</code> if a matching rmdc ship could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip fetchByShipTypeCode_Last(String shipTypeCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countByShipTypeCode(shipTypeCode);
////
////		List<RmdcShip> list = findByShipTypeCode(shipTypeCode, count - 1,
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
////	 * Returns the rmdc ships before and after the current rmdc ship in the ordered set where shipTypeCode = &#63;.
////	 *
////	 * @param id the primary key of the current rmdc ship
////	 * @param shipTypeCode the ship type code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next rmdc ship
////	 * @throws vn.gt.dao.danhmuc.NoSuchRmdcShipException if a rmdc ship with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public RmdcShip[] findByShipTypeCode_PrevAndNext(long id,
////		String shipTypeCode, OrderByComparator orderByComparator)
////		throws NoSuchRmdcShipException, SystemException {
////		RmdcShip rmdcShip = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			RmdcShip[] array = new RmdcShip[3];
////
////			array[0] = getByShipTypeCode_PrevAndNext(rmdcShip,
////					shipTypeCode, orderByComparator, true);
////
////			array[1] = rmdcShip;
////
////			array[2] = getByShipTypeCode_PrevAndNext(rmdcShip,
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
////	protected RmdcShip getByShipTypeCode_PrevAndNext(
////		RmdcShip rmdcShip, String shipTypeCode,
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
////		query.append(_SQL_SELECT_RMDCSHIP_WHERE);
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
////			query.append(RmdcShipModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(rmdcShip);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<RmdcShip> list = queryFactory.getResultList(builder);
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
////	 * Returns all the rmdc ships.
////	 *
////	 * @return the rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the rmdc ships.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @return the range of rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findAll(int start, int end) throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the rmdc ships.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of rmdc ships
////	 * @param end the upper bound of the range of rmdc ships (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<RmdcShip> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<RmdcShip> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_RMDCSHIP);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_RMDCSHIP.concat(RmdcShipModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<RmdcShip>) queryFactory.getResultList(builder);
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
////	 * Removes all the rmdc ships where shipId = &#63; from the database.
////	 *
////	 * @param shipId the ship ID
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByShipId(int shipId) throws SystemException {
////		for (RmdcShip rmdcShip : findByShipId(shipId)) {
////			repository.delete(rmdcShip);
////		}
////	}
////
////	/**
////	 * Removes all the rmdc ships where shipTypeCode = &#63; from the database.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		for (RmdcShip rmdcShip : findByShipTypeCode(shipTypeCode)) {
////			repository.delete(rmdcShip);
////		}
////	}
////
////	/**
////	 * Removes all the rmdc ships from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (RmdcShip rmdcShip : findAll()) {
////			repository.delete(rmdcShip);
////		}
////	}
////
////	/**
////	 * Returns the number of rmdc ships where shipId = &#63;.
////	 *
////	 * @param shipId the ship ID
////	 * @return the number of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByShipId(int shipId) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_RMDCSHIP_WHERE);
////
////			query.append(_FINDER_COLUMN_SHIPID_SHIPID_2);
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
////				builder.appendNamedParameterMap("shipId", shipId);
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
////	 * Returns the number of rmdc ships where shipTypeCode = &#63;.
////	 *
////	 * @param shipTypeCode the ship type code
////	 * @return the number of matching rmdc ships
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByShipTypeCode(String shipTypeCode)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_RMDCSHIP_WHERE);
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
////	 * Returns the number of rmdc ships.
////	 *
////	 * @return the number of rmdc ships
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RMDCSHIP).build();
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
////	 * Initializes the rmdc ship persistence.
////	 */
////	private static final String _SQL_SELECT_RMDCSHIP = "SELECT rmdcShip FROM RmdcShip rmdcShip";
////	private static final String _SQL_SELECT_RMDCSHIP_WHERE = "SELECT rmdcShip FROM RmdcShip rmdcShip WHERE ";
////	private static final String _SQL_COUNT_RMDCSHIP = "SELECT COUNT(rmdcShip) FROM RmdcShip rmdcShip";
////	private static final String _SQL_COUNT_RMDCSHIP_WHERE = "SELECT COUNT(rmdcShip) FROM RmdcShip rmdcShip WHERE ";
////	private static final String _FINDER_COLUMN_SHIPID_SHIPID_2 = "rmdcShip.shipId =:shipId";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1 = "rmdcShip.shipTypeCode IS NULL";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2 = "rmdcShip.shipTypeCode =:shipTypeCode";
////	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3 = "(rmdcShip.shipTypeCode IS NULL OR rmdcShip.shipTypeCode =:shipTypeCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "rmdcShip.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No RmdcShip exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No RmdcShip exists with the key {";
////
////
////
////}
