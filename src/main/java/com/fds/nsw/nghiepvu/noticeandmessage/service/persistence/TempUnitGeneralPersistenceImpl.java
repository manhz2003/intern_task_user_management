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
////import com.fds.nsw.nghiepvu.model.TempUnitGeneral;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.TempUnitGeneralRepository;
////import com.fds.nsw.nghiepvu.modelImpl.TempUnitGeneralModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class TempUnitGeneralPersistenceImpl extends BasePersistence {
////	@Autowired
////	TempUnitGeneralRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<TempUnitGeneral> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link TempUnitGeneralUtil} to access the temp unit general persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public TempUnitGeneral create(long id) {
////		TempUnitGeneral tempUnitGeneral = new TempUnitGeneral();
////
////
////		//tempUnitGeneral.setPrimaryKey(id);
////
////		return tempUnitGeneral;
////	}
////
////	/**
////	 * Removes the temp unit general with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the temp unit general
////	 * @return the temp unit general that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral remove(long id)
////		throws NoSuchTempUnitGeneralException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the temp unit general with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the temp unit general
////	 * @return the temp unit general that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempUnitGeneral remove(Serializable primaryKey)
////		throws NoSuchTempUnitGeneralException, SystemException {
////
////
////		try {
////
////
////			TempUnitGeneral tempUnitGeneral = findByPrimaryKey(primaryKey);
////
////			if (tempUnitGeneral == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchTempUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(tempUnitGeneral);
////			return tempUnitGeneral;
////		}
////		catch (NoSuchTempUnitGeneralException nsee) {
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
////	public TempUnitGeneral remove(TempUnitGeneral TempUnitGeneral) throws SystemException {
//	removeImpl(TempUnitGeneral);
//	return TempUnitGeneral;
//}
//
//protected TempUnitGeneral removeImpl(TempUnitGeneral tempUnitGeneral)
////		throws SystemException {
////		try {
////			repository.delete(tempUnitGeneral);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempUnitGeneral;
////	}
////
////
////	public TempUnitGeneral updateImpl(
////		com.fds.nsw.nghiepvu.model.TempUnitGeneral tempUnitGeneral,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(tempUnitGeneral);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempUnitGeneral;
////	}
////
////
////	public TempUnitGeneral findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp unit general with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException} if it could not be found.
////	 *
////	 * @param id the primary key of the temp unit general
////	 * @return the temp unit general
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral findByPrimaryKey(long id)
////		throws NoSuchTempUnitGeneralException, SystemException {
////		TempUnitGeneral tempUnitGeneral = fetchByPrimaryKey(id);
////
////		if (tempUnitGeneral == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchTempUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return tempUnitGeneral;
////	}
////
////	/**
////	 * Returns the temp unit general with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the temp unit general
////	 * @return the temp unit general, or <code>null</code> if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempUnitGeneral fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp unit general with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the temp unit general
////	 * @return the temp unit general, or <code>null</code> if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral fetchByPrimaryKey(long id) throws SystemException {
////		TempUnitGeneral tempUnitGeneral = null;
////
////
////
////		if (tempUnitGeneral == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<TempUnitGeneral> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					tempUnitGeneral = optional.get();
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
////		return tempUnitGeneral;
////	}
////
////	/**
////	 * Returns all the temp unit generals where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the matching temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findByDocumentNameAndDocumentYear(
////		long documentName, int documentYear) throws SystemException {
////		return findByDocumentNameAndDocumentYear(documentName, documentYear,
////			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp unit generals where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp unit generals
////	 * @param end the upper bound of the range of temp unit generals (not inclusive)
////	 * @return the range of matching temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findByDocumentNameAndDocumentYear(
////		long documentName, int documentYear, int start, int end)
////		throws SystemException {
////		return findByDocumentNameAndDocumentYear(documentName, documentYear,
////			start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp unit generals where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp unit generals
////	 * @param end the upper bound of the range of temp unit generals (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findByDocumentNameAndDocumentYear(
////		long documentName, int documentYear, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempUnitGeneral> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(4 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(4);
////			}
////
////			query.append(_SQL_SELECT_TEMPUNITGENERAL_WHERE);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(TempUnitGeneralModelImpl.ORDER_BY_JPQL);
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
////				builder.appendNamedParameterMap("documentName", documentName);
////
////				builder.appendNamedParameterMap("documentYear", documentYear);
////
////				list = (List<TempUnitGeneral>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp unit general in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp unit general
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a matching temp unit general could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral findByDocumentNameAndDocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempUnitGeneralException, SystemException {
////		TempUnitGeneral tempUnitGeneral = fetchByDocumentNameAndDocumentYear_First(documentName,
////				documentYear, orderByComparator);
////
////		if (tempUnitGeneral != null) {
////			return tempUnitGeneral;
////		}
////
////		StringBundler msg = new StringBundler(6);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("documentName=");
////		msg.append(documentName);
////
////		msg.append(", documentYear=");
////		msg.append(documentYear);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempUnitGeneralException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp unit general in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp unit general, or <code>null</code> if a matching temp unit general could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral fetchByDocumentNameAndDocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempUnitGeneral> list = findByDocumentNameAndDocumentYear(documentName,
////				documentYear, 0, 1, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last temp unit general in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp unit general
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a matching temp unit general could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral findByDocumentNameAndDocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempUnitGeneralException, SystemException {
////		TempUnitGeneral tempUnitGeneral = fetchByDocumentNameAndDocumentYear_Last(documentName,
////				documentYear, orderByComparator);
////
////		if (tempUnitGeneral != null) {
////			return tempUnitGeneral;
////		}
////
////		StringBundler msg = new StringBundler(6);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("documentName=");
////		msg.append(documentName);
////
////		msg.append(", documentYear=");
////		msg.append(documentYear);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempUnitGeneralException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp unit general in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp unit general, or <code>null</code> if a matching temp unit general could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral fetchByDocumentNameAndDocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countByDocumentNameAndDocumentYear(documentName,
////				documentYear);
////
////		List<TempUnitGeneral> list = findByDocumentNameAndDocumentYear(documentName,
////				documentYear, count - 1, count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the temp unit generals before and after the current temp unit general in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param id the primary key of the current temp unit general
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp unit general
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempUnitGeneralException if a temp unit general with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempUnitGeneral[] findByDocumentNameAndDocumentYear_PrevAndNext(
////		long id, long documentName, int documentYear,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempUnitGeneralException, SystemException {
////		TempUnitGeneral tempUnitGeneral = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempUnitGeneral[] array = new TempUnitGeneral[3];
////
////			array[0] = getByDocumentNameAndDocumentYear_PrevAndNext(
////					tempUnitGeneral, documentName, documentYear,
////					orderByComparator, true);
////
////			array[1] = tempUnitGeneral;
////
////			array[2] = getByDocumentNameAndDocumentYear_PrevAndNext(
////					tempUnitGeneral, documentName, documentYear,
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
////	protected TempUnitGeneral getByDocumentNameAndDocumentYear_PrevAndNext(
////		 TempUnitGeneral tempUnitGeneral, long documentName,
////		int documentYear, OrderByComparator orderByComparator, boolean previous) {
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
////		query.append(_SQL_SELECT_TEMPUNITGENERAL_WHERE);
////
////		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);
////
////		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);
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
////			query.append(TempUnitGeneralModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		builder.appendNamedParameterMap("documentName", documentName);
////
////		builder.appendNamedParameterMap("documentYear", documentYear);
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(tempUnitGeneral);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempUnitGeneral> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp unit generals.
////	 *
////	 * @return the temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp unit generals.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp unit generals
////	 * @param end the upper bound of the range of temp unit generals (not inclusive)
////	 * @return the range of temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp unit generals.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp unit generals
////	 * @param end the upper bound of the range of temp unit generals (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempUnitGeneral> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempUnitGeneral> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_TEMPUNITGENERAL);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_TEMPUNITGENERAL.concat(TempUnitGeneralModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<TempUnitGeneral>) queryFactory.getResultList(builder);
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
////	 * Removes all the temp unit generals where documentName = &#63; and documentYear = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByDocumentNameAndDocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		for (TempUnitGeneral tempUnitGeneral : findByDocumentNameAndDocumentYear(
////				documentName, documentYear)) {
////			repository.delete(tempUnitGeneral);
////		}
////	}
////
////	/**
////	 * Removes all the temp unit generals from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (TempUnitGeneral tempUnitGeneral : findAll()) {
////			repository.delete(tempUnitGeneral);
////		}
////	}
////
////	/**
////	 * Returns the number of temp unit generals where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the number of matching temp unit generals
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByDocumentNameAndDocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_COUNT_TEMPUNITGENERAL_WHERE);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);
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
////				builder.appendNamedParameterMap("documentName", documentName);
////
////				builder.appendNamedParameterMap("documentYear", documentYear);
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
////	 * Returns the number of temp unit generals.
////	 *
////	 * @return the number of temp unit generals
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPUNITGENERAL).build();
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
////	 * Initializes the temp unit general persistence.
////	 */
////	private static final String _SQL_SELECT_TEMPUNITGENERAL = "SELECT tempUnitGeneral FROM TempUnitGeneral tempUnitGeneral";
////	private static final String _SQL_SELECT_TEMPUNITGENERAL_WHERE = "SELECT tempUnitGeneral FROM TempUnitGeneral tempUnitGeneral WHERE ";
////	private static final String _SQL_COUNT_TEMPUNITGENERAL = "SELECT COUNT(tempUnitGeneral) FROM TempUnitGeneral tempUnitGeneral";
////	private static final String _SQL_COUNT_TEMPUNITGENERAL_WHERE = "SELECT COUNT(tempUnitGeneral) FROM TempUnitGeneral tempUnitGeneral WHERE ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
////		"tempUnitGeneral.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
////		"tempUnitGeneral.documentYear =:documentYear";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "tempUnitGeneral.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempUnitGeneral exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempUnitGeneral exists with the key {";
////
////
////
////}
