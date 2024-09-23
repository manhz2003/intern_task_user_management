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
////import com.fds.nsw.nghiepvu.model.TempDeclarationForAnimalQuarantine;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.TempDeclarationForAnimalQuarantineRepository;
////import com.fds.nsw.nghiepvu.modelImpl.TempDeclarationForAnimalQuarantineModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class TempDeclarationForAnimalQuarantinePersistenceImpl extends BasePersistence {
////	@Autowired
////	TempDeclarationForAnimalQuarantineRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<TempDeclarationForAnimalQuarantine> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link TempDeclarationForAnimalQuarantineUtil} to access the temp declaration for animal quarantine persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public TempDeclarationForAnimalQuarantine create(long id) {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = new TempDeclarationForAnimalQuarantine();
////
////
////		//tempDeclarationForAnimalQuarantine.setPrimaryKey(id);
////
////		return tempDeclarationForAnimalQuarantine;
////	}
////
////	/**
////	 * Removes the temp declaration for animal quarantine with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the temp declaration for animal quarantine
////	 * @return the temp declaration for animal quarantine that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine remove(long id)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the temp declaration for animal quarantine with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the temp declaration for animal quarantine
////	 * @return the temp declaration for animal quarantine that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempDeclarationForAnimalQuarantine remove(Serializable primaryKey)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////
////
////		try {
////
////
////			TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine =
////				findByPrimaryKey(primaryKey);
////
////			if (tempDeclarationForAnimalQuarantine == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchTempDeclarationForAnimalQuarantineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(tempDeclarationForAnimalQuarantine);
////			return tempDeclarationForAnimalQuarantine;
////		}
////		catch (NoSuchTempDeclarationForAnimalQuarantineException nsee) {
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
////	public TempDeclarationForAnimalQuarantine remove(TempDeclarationForAnimalQuarantine TempDeclarationForAnimalQuarantine) throws SystemException {
//	removeImpl(TempDeclarationForAnimalQuarantine);
//	return TempDeclarationForAnimalQuarantine;
//}
//
//protected TempDeclarationForAnimalQuarantine removeImpl(
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine)
////		throws SystemException {
////		try {
////			repository.delete(tempDeclarationForAnimalQuarantine);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempDeclarationForAnimalQuarantine;
////	}
////
////
////	public TempDeclarationForAnimalQuarantine updateImpl(
////		com.fds.nsw.nghiepvu.model.TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(tempDeclarationForAnimalQuarantine);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempDeclarationForAnimalQuarantine;
////	}
////
////
////	public TempDeclarationForAnimalQuarantine findByPrimaryKey(
////		Serializable primaryKey) throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp declaration for animal quarantine with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException} if it could not be found.
////	 *
////	 * @param id the primary key of the temp declaration for animal quarantine
////	 * @return the temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findByPrimaryKey(long id)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchByPrimaryKey(id);
////
////		if (tempDeclarationForAnimalQuarantine == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchTempDeclarationForAnimalQuarantineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return tempDeclarationForAnimalQuarantine;
////	}
////
////	/**
////	 * Returns the temp declaration for animal quarantine with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the temp declaration for animal quarantine
////	 * @return the temp declaration for animal quarantine, or <code>null</code> if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempDeclarationForAnimalQuarantine fetchByPrimaryKey(
////		Serializable primaryKey) throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp declaration for animal quarantine with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the temp declaration for animal quarantine
////	 * @return the temp declaration for animal quarantine, or <code>null</code> if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchByPrimaryKey(long id)
////		throws SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = null;
////
////
////
////		if (tempDeclarationForAnimalQuarantine == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<TempDeclarationForAnimalQuarantine> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					tempDeclarationForAnimalQuarantine = optional.get();
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
////		return tempDeclarationForAnimalQuarantine;
////	}
////
////	/**
////	 * Returns all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear) throws SystemException {
////		return findBydocumentNameAnddocumentYear(documentName, documentYear,
////			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @return the range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear, int start, int end)
////		throws SystemException {
////		return findBydocumentNameAnddocumentYear(documentName, documentYear,
////			start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = null;
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
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////				query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////				list = (List<TempDeclarationForAnimalQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findBydocumentNameAnddocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchBydocumentNameAnddocumentYear_First(documentName,
////				documentYear, orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
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
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchBydocumentNameAnddocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = findBydocumentNameAnddocumentYear(documentName,
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
////	 * Returns the last temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findBydocumentNameAnddocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchBydocumentNameAnddocumentYear_Last(documentName,
////				documentYear, orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
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
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchBydocumentNameAnddocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countBydocumentNameAnddocumentYear(documentName,
////				documentYear);
////
////		List<TempDeclarationForAnimalQuarantine> list = findBydocumentNameAnddocumentYear(documentName,
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
////	 * Returns the temp declaration for animal quarantines before and after the current temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for animal quarantine
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine[] findBydocumentNameAnddocumentYear_PrevAndNext(
////		long id, long documentName, int documentYear,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForAnimalQuarantine[] array = new TempDeclarationForAnimalQuarantine[3];
////
////			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, documentName,
////					documentYear, orderByComparator, true);
////
////			array[1] = tempDeclarationForAnimalQuarantine;
////
////			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, documentName,
////					documentYear, orderByComparator, false);
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
////	protected TempDeclarationForAnimalQuarantine getBydocumentNameAnddocumentYear_PrevAndNext(
////
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine,
////		long documentName, int documentYear,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////			query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForAnimalQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForAnimalQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @return the matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		return findBydocumentNameAnddocumentYearRequestState(documentName,
////			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
////			null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @return the range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState, int start,
////		int end) throws SystemException {
////		return findBydocumentNameAnddocumentYearRequestState(documentName,
////			documentYear, requestState, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState, int start,
////		int end, OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(5 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(5);
////			}
////
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////				builder.appendNamedParameterMap("requestState", requestState);
////
////				list = (List<TempDeclarationForAnimalQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findBydocumentNameAnddocumentYearRequestState_First(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchBydocumentNameAnddocumentYearRequestState_First(documentName,
////				documentYear, requestState, orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
////		}
////
////		StringBundler msg = new StringBundler(8);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("documentName=");
////		msg.append(documentName);
////
////		msg.append(", documentYear=");
////		msg.append(documentYear);
////
////		msg.append(", requestState=");
////		msg.append(requestState);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchBydocumentNameAnddocumentYearRequestState_First(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = findBydocumentNameAnddocumentYearRequestState(documentName,
////				documentYear, requestState, 0, 1, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findBydocumentNameAnddocumentYearRequestState_Last(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchBydocumentNameAnddocumentYearRequestState_Last(documentName,
////				documentYear, requestState, orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
////		}
////
////		StringBundler msg = new StringBundler(8);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("documentName=");
////		msg.append(documentName);
////
////		msg.append(", documentYear=");
////		msg.append(documentYear);
////
////		msg.append(", requestState=");
////		msg.append(requestState);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchBydocumentNameAnddocumentYearRequestState_Last(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countBydocumentNameAnddocumentYearRequestState(documentName,
////				documentYear, requestState);
////
////		List<TempDeclarationForAnimalQuarantine> list = findBydocumentNameAnddocumentYearRequestState(documentName,
////				documentYear, requestState, count - 1, count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the temp declaration for animal quarantines before and after the current temp declaration for animal quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for animal quarantine
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine[] findBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////		long id, long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForAnimalQuarantine[] array = new TempDeclarationForAnimalQuarantine[3];
////
////			array[0] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, documentName,
////					documentYear, requestState, orderByComparator, true);
////
////			array[1] = tempDeclarationForAnimalQuarantine;
////
////			array[2] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, documentName,
////					documentYear, requestState, orderByComparator, false);
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
////	protected TempDeclarationForAnimalQuarantine getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine,
////		long documentName, int documentYear, int requestState,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
////
////		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);
////
////		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);
////
////		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);
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
////			query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////		builder.appendNamedParameterMap("requestState", requestState);
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForAnimalQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForAnimalQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for animal quarantines where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findByRequestCode(
////		String requestCode) throws SystemException {
////		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for animal quarantines where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @return the range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findByRequestCode(
////		String requestCode, int start, int end) throws SystemException {
////		return findByRequestCode(requestCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for animal quarantines where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findByRequestCode(
////		String requestCode, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = null;
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
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////				query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////				list = (List<TempDeclarationForAnimalQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for animal quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchByRequestCode_First(requestCode,
////				orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
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
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for animal quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = findByRequestCode(requestCode,
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
////	 * Returns the last temp declaration for animal quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine findByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = fetchByRequestCode_Last(requestCode,
////				orderByComparator);
////
////		if (tempDeclarationForAnimalQuarantine != null) {
////			return tempDeclarationForAnimalQuarantine;
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
////		throw new NoSuchTempDeclarationForAnimalQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for animal quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for animal quarantine, or <code>null</code> if a matching temp declaration for animal quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine fetchByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countByRequestCode(requestCode);
////
////		List<TempDeclarationForAnimalQuarantine> list = findByRequestCode(requestCode,
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
////	 * Returns the temp declaration for animal quarantines before and after the current temp declaration for animal quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for animal quarantine
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for animal quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForAnimalQuarantineException if a temp declaration for animal quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForAnimalQuarantine[] findByRequestCode_PrevAndNext(
////		long id, String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForAnimalQuarantineException,
////			SystemException {
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForAnimalQuarantine[] array = new TempDeclarationForAnimalQuarantine[3];
////
////			array[0] = getByRequestCode_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, requestCode,
////					orderByComparator, true);
////
////			array[1] = tempDeclarationForAnimalQuarantine;
////
////			array[2] = getByRequestCode_PrevAndNext(
////					tempDeclarationForAnimalQuarantine, requestCode,
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
////	protected TempDeclarationForAnimalQuarantine getByRequestCode_PrevAndNext(
////
////		TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////			query.append(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForAnimalQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForAnimalQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for animal quarantines.
////	 *
////	 * @return the temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findAll()
////		throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for animal quarantines.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @return the range of temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for animal quarantines.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp declaration for animal quarantines
////	 * @param end the upper bound of the range of temp declaration for animal quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForAnimalQuarantine> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForAnimalQuarantine> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE.concat(TempDeclarationForAnimalQuarantineModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<TempDeclarationForAnimalQuarantine>) queryFactory.getResultList(builder);
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
////	 * Removes all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeBydocumentNameAnddocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		for (TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine : findBydocumentNameAnddocumentYear(
////				documentName, documentYear)) {
////			repository.delete(tempDeclarationForAnimalQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		for (TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine : findBydocumentNameAnddocumentYearRequestState(
////				documentName, documentYear, requestState)) {
////			repository.delete(tempDeclarationForAnimalQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for animal quarantines where requestCode = &#63; from the database.
////	 *
////	 * @param requestCode the request code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByRequestCode(String requestCode)
////		throws SystemException {
////		for (TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine : findByRequestCode(
////				requestCode)) {
////			repository.delete(tempDeclarationForAnimalQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for animal quarantines from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine : findAll()) {
////			repository.delete(tempDeclarationForAnimalQuarantine);
////		}
////	}
////
////	/**
////	 * Returns the number of temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the number of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countBydocumentNameAnddocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////	 * Returns the number of temp declaration for animal quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @return the number of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(4);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);
////
////			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);
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
////				builder.appendNamedParameterMap("requestState", requestState);
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
////	 * Returns the number of temp declaration for animal quarantines where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the number of matching temp declaration for animal quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByRequestCode(String requestCode) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE);
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
////	 * Returns the number of temp declaration for animal quarantines.
////	 *
////	 * @return the number of temp declaration for animal quarantines
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE).build();
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
////	 * Initializes the temp declaration for animal quarantine persistence.
////	 */
////	private static final String _SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE = "SELECT tempDeclarationForAnimalQuarantine FROM TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine";
////	private static final String _SQL_SELECT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE =
////		"SELECT tempDeclarationForAnimalQuarantine FROM TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine WHERE ";
////	private static final String _SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE = "SELECT COUNT(tempDeclarationForAnimalQuarantine) FROM TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine";
////	private static final String _SQL_COUNT_TEMPDECLARATIONFORANIMALQUARANTINE_WHERE =
////		"SELECT COUNT(tempDeclarationForAnimalQuarantine) FROM TempDeclarationForAnimalQuarantine tempDeclarationForAnimalQuarantine WHERE ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
////		"tempDeclarationForAnimalQuarantine.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
////		"tempDeclarationForAnimalQuarantine.documentYear =:documentYear";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2 =
////		"tempDeclarationForAnimalQuarantine.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2 =
////		"tempDeclarationForAnimalQuarantine.documentYear =:documentYear AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2 =
////		"tempDeclarationForAnimalQuarantine.requestState =:requestState";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempDeclarationForAnimalQuarantine.requestCode IS NULL";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempDeclarationForAnimalQuarantine.requestCode =:requestCode";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempDeclarationForAnimalQuarantine.requestCode IS NULL OR tempDeclarationForAnimalQuarantine.requestCode =:requestCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "tempDeclarationForAnimalQuarantine.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempDeclarationForAnimalQuarantine exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempDeclarationForAnimalQuarantine exists with the key {";
////
////
////
////}
