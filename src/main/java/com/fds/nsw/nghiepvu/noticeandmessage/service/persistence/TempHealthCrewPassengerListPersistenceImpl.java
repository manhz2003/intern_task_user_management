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
////package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;
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
////import com.fds.nsw.nghiepvu.model.TempHealthCrewPassengerList;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.TempHealthCrewPassengerListRepository;
////import com.fds.nsw.nghiepvu.modelImpl.TempHealthCrewPassengerListModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class TempHealthCrewPassengerListPersistenceImpl extends BasePersistence {
////	@Autowired
////	TempHealthCrewPassengerListRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<TempHealthCrewPassengerList> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link TempHealthCrewPassengerListUtil} to access the temp health crew passenger list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public TempHealthCrewPassengerList create(long id) {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = new TempHealthCrewPassengerList();
////
////
////		//tempHealthCrewPassengerList.setPrimaryKey(id);
////
////		return tempHealthCrewPassengerList;
////	}
////
////	/**
////	 * Removes the temp health crew passenger list with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the temp health crew passenger list
////	 * @return the temp health crew passenger list that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList remove(long id)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the temp health crew passenger list with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the temp health crew passenger list
////	 * @return the temp health crew passenger list that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempHealthCrewPassengerList remove(Serializable primaryKey)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////
////
////		try {
////
////
////			TempHealthCrewPassengerList tempHealthCrewPassengerList = findByPrimaryKey(primaryKey);
////
////			if (tempHealthCrewPassengerList == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchTempHealthCrewPassengerListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(tempHealthCrewPassengerList);
////			return tempHealthCrewPassengerList;
////		}
////		catch (NoSuchTempHealthCrewPassengerListException nsee) {
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
////	public TempHealthCrewPassengerList remove(TempHealthCrewPassengerList TempHealthCrewPassengerList) throws SystemException {
//	removeImpl(TempHealthCrewPassengerList);
//	return TempHealthCrewPassengerList;
//}
//
//protected TempHealthCrewPassengerList removeImpl(
////		TempHealthCrewPassengerList tempHealthCrewPassengerList)
////		throws SystemException {
////		try {
////			repository.delete(tempHealthCrewPassengerList);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempHealthCrewPassengerList;
////	}
////
////
////	public TempHealthCrewPassengerList updateImpl(
////		com.fds.nsw.nghiepvu.model.TempHealthCrewPassengerList tempHealthCrewPassengerList,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(tempHealthCrewPassengerList);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempHealthCrewPassengerList;
////	}
////
////
////	public TempHealthCrewPassengerList findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp health crew passenger list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException} if it could not be found.
////	 *
////	 * @param id the primary key of the temp health crew passenger list
////	 * @return the temp health crew passenger list
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList findByPrimaryKey(long id)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = fetchByPrimaryKey(id);
////
////		if (tempHealthCrewPassengerList == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchTempHealthCrewPassengerListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return tempHealthCrewPassengerList;
////	}
////
////	/**
////	 * Returns the temp health crew passenger list with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the temp health crew passenger list
////	 * @return the temp health crew passenger list, or <code>null</code> if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempHealthCrewPassengerList fetchByPrimaryKey(
////		Serializable primaryKey) throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp health crew passenger list with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the temp health crew passenger list
////	 * @return the temp health crew passenger list, or <code>null</code> if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList fetchByPrimaryKey(long id)
////		throws SystemException {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = null;
////
////
////
////		if (tempHealthCrewPassengerList == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<TempHealthCrewPassengerList> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					tempHealthCrewPassengerList = optional.get();
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
////		return tempHealthCrewPassengerList;
////	}
////
////	/**
////	 * Returns all the temp health crew passenger lists where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the matching temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findByRequestCode(
////		String requestCode) throws SystemException {
////		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp health crew passenger lists where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp health crew passenger lists
////	 * @param end the upper bound of the range of temp health crew passenger lists (not inclusive)
////	 * @return the range of matching temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findByRequestCode(
////		String requestCode, int start, int end) throws SystemException {
////		return findByRequestCode(requestCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp health crew passenger lists where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp health crew passenger lists
////	 * @param end the upper bound of the range of temp health crew passenger lists (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findByRequestCode(
////		String requestCode, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempHealthCrewPassengerList> list = null;
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
////			query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE);
////
////			if (requestCode == null) {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
////			}
////			else {
////				if (requestCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
////				}
////			}
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(TempHealthCrewPassengerListModelImpl.ORDER_BY_JPQL);
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
////				if (requestCode != null) {
////					builder.appendNamedParameterMap("requestCode", requestCode);
////				}
////
////				list = (List<TempHealthCrewPassengerList>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp health crew passenger list in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp health crew passenger list
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a matching temp health crew passenger list could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList findByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = fetchByRequestCode_First(requestCode,
////				orderByComparator);
////
////		if (tempHealthCrewPassengerList != null) {
////			return tempHealthCrewPassengerList;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("requestCode=");
////		msg.append(requestCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempHealthCrewPassengerListException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp health crew passenger list in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp health crew passenger list, or <code>null</code> if a matching temp health crew passenger list could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList fetchByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempHealthCrewPassengerList> list = findByRequestCode(requestCode,
////				0, 1, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last temp health crew passenger list in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp health crew passenger list
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a matching temp health crew passenger list could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList findByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = fetchByRequestCode_Last(requestCode,
////				orderByComparator);
////
////		if (tempHealthCrewPassengerList != null) {
////			return tempHealthCrewPassengerList;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("requestCode=");
////		msg.append(requestCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempHealthCrewPassengerListException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp health crew passenger list in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp health crew passenger list, or <code>null</code> if a matching temp health crew passenger list could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList fetchByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countByRequestCode(requestCode);
////
////		List<TempHealthCrewPassengerList> list = findByRequestCode(requestCode,
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
////	 * Returns the temp health crew passenger lists before and after the current temp health crew passenger list in the ordered set where requestCode = &#63;.
////	 *
////	 * @param id the primary key of the current temp health crew passenger list
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp health crew passenger list
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthCrewPassengerListException if a temp health crew passenger list with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempHealthCrewPassengerList[] findByRequestCode_PrevAndNext(
////		long id, String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempHealthCrewPassengerListException, SystemException {
////		TempHealthCrewPassengerList tempHealthCrewPassengerList = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempHealthCrewPassengerList[] array = new TempHealthCrewPassengerList[3];
////
////			array[0] = getByRequestCode_PrevAndNext(
////					tempHealthCrewPassengerList, requestCode,
////					orderByComparator, true);
////
////			array[1] = tempHealthCrewPassengerList;
////
////			array[2] = getByRequestCode_PrevAndNext(
////					tempHealthCrewPassengerList, requestCode,
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
////	protected TempHealthCrewPassengerList getByRequestCode_PrevAndNext(
////
////		TempHealthCrewPassengerList tempHealthCrewPassengerList,
////		String requestCode, OrderByComparator orderByComparator,
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
////		query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE);
////
////		if (requestCode == null) {
////			query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
////		}
////		else {
////			if (requestCode.equals(StringPool.BLANK)) {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
////			}
////			else {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
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
////			query.append(TempHealthCrewPassengerListModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		if (requestCode != null) {
////			builder.appendNamedParameterMap("requestCode", requestCode);
////		}
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(tempHealthCrewPassengerList);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempHealthCrewPassengerList> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp health crew passenger lists.
////	 *
////	 * @return the temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findAll()
////		throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp health crew passenger lists.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp health crew passenger lists
////	 * @param end the upper bound of the range of temp health crew passenger lists (not inclusive)
////	 * @return the range of temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp health crew passenger lists.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp health crew passenger lists
////	 * @param end the upper bound of the range of temp health crew passenger lists (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempHealthCrewPassengerList> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempHealthCrewPassengerList> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST.concat(TempHealthCrewPassengerListModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<TempHealthCrewPassengerList>) queryFactory.getResultList(builder);
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
////	 * Removes all the temp health crew passenger lists where requestCode = &#63; from the database.
////	 *
////	 * @param requestCode the request code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByRequestCode(String requestCode)
////		throws SystemException {
////		for (TempHealthCrewPassengerList tempHealthCrewPassengerList : findByRequestCode(
////				requestCode)) {
////			repository.delete(tempHealthCrewPassengerList);
////		}
////	}
////
////	/**
////	 * Removes all the temp health crew passenger lists from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (TempHealthCrewPassengerList tempHealthCrewPassengerList : findAll()) {
////			repository.delete(tempHealthCrewPassengerList);
////		}
////	}
////
////	/**
////	 * Returns the number of temp health crew passenger lists where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the number of matching temp health crew passenger lists
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByRequestCode(String requestCode) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST_WHERE);
////
////			if (requestCode == null) {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
////			}
////			else {
////				if (requestCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
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
////				if (requestCode != null) {
////					builder.appendNamedParameterMap("requestCode", requestCode);
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
////	 * Returns the number of temp health crew passenger lists.
////	 *
////	 * @return the number of temp health crew passenger lists
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST).build();
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
////	 * Initializes the temp health crew passenger list persistence.
////	 */
////	private static final String _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST = "SELECT tempHealthCrewPassengerList FROM TempHealthCrewPassengerList tempHealthCrewPassengerList";
////	private static final String _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE = "SELECT tempHealthCrewPassengerList FROM TempHealthCrewPassengerList tempHealthCrewPassengerList WHERE ";
////	private static final String _SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST = "SELECT COUNT(tempHealthCrewPassengerList) FROM TempHealthCrewPassengerList tempHealthCrewPassengerList";
////	private static final String _SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST_WHERE = "SELECT COUNT(tempHealthCrewPassengerList) FROM TempHealthCrewPassengerList tempHealthCrewPassengerList WHERE ";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempHealthCrewPassengerList.requestCode IS NULL";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempHealthCrewPassengerList.requestCode =:requestCode";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempHealthCrewPassengerList.requestCode IS NULL OR tempHealthCrewPassengerList.requestCode =:requestCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "tempHealthCrewPassengerList.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempHealthCrewPassengerList exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempHealthCrewPassengerList exists with the key {";
////
////
////
////}
