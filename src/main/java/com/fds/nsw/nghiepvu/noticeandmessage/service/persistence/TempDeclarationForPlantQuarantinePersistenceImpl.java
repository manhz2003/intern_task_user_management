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
////import com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.TempDeclarationForPlantQuarantineRepository;
////import com.fds.nsw.nghiepvu.modelImpl.TempDeclarationForPlantQuarantineModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class TempDeclarationForPlantQuarantinePersistenceImpl extends BasePersistence {
////	@Autowired
////	TempDeclarationForPlantQuarantineRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<TempDeclarationForPlantQuarantine> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link TempDeclarationForPlantQuarantineUtil} to access the temp declaration for plant quarantine persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public TempDeclarationForPlantQuarantine create(long id) {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = new TempDeclarationForPlantQuarantine();
////
////
////		//tempDeclarationForPlantQuarantine.setPrimaryKey(id);
////
////		return tempDeclarationForPlantQuarantine;
////	}
////
////	/**
////	 * Removes the temp declaration for plant quarantine with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the temp declaration for plant quarantine
////	 * @return the temp declaration for plant quarantine that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine remove(long id)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the temp declaration for plant quarantine with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the temp declaration for plant quarantine
////	 * @return the temp declaration for plant quarantine that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempDeclarationForPlantQuarantine remove(Serializable primaryKey)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////
////
////		try {
////
////
////			TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = findByPrimaryKey(primaryKey);
////
////			if (tempDeclarationForPlantQuarantine == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchTempDeclarationForPlantQuarantineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(tempDeclarationForPlantQuarantine);
////			return tempDeclarationForPlantQuarantine;
////		}
////		catch (NoSuchTempDeclarationForPlantQuarantineException nsee) {
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
////	public TempDeclarationForPlantQuarantine remove(TempDeclarationForPlantQuarantine TempDeclarationForPlantQuarantine) throws SystemException {
//	removeImpl(TempDeclarationForPlantQuarantine);
//	return TempDeclarationForPlantQuarantine;
//}
//
//protected TempDeclarationForPlantQuarantine removeImpl(
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine)
////		throws SystemException {
////		try {
////			repository.delete(tempDeclarationForPlantQuarantine);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempDeclarationForPlantQuarantine;
////	}
////
////
////	public TempDeclarationForPlantQuarantine updateImpl(
////		com.fds.nsw.nghiepvu.model.TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(tempDeclarationForPlantQuarantine);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return tempDeclarationForPlantQuarantine;
////	}
////
////
////	public TempDeclarationForPlantQuarantine findByPrimaryKey(
////		Serializable primaryKey) throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp declaration for plant quarantine with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException} if it could not be found.
////	 *
////	 * @param id the primary key of the temp declaration for plant quarantine
////	 * @return the temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findByPrimaryKey(long id)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchByPrimaryKey(id);
////
////		if (tempDeclarationForPlantQuarantine == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchTempDeclarationForPlantQuarantineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return tempDeclarationForPlantQuarantine;
////	}
////
////	/**
////	 * Returns the temp declaration for plant quarantine with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the temp declaration for plant quarantine
////	 * @return the temp declaration for plant quarantine, or <code>null</code> if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public TempDeclarationForPlantQuarantine fetchByPrimaryKey(
////		Serializable primaryKey) throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the temp declaration for plant quarantine with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the temp declaration for plant quarantine
////	 * @return the temp declaration for plant quarantine, or <code>null</code> if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchByPrimaryKey(long id)
////		throws SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = null;
////
////
////
////		if (tempDeclarationForPlantQuarantine == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<TempDeclarationForPlantQuarantine> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					tempDeclarationForPlantQuarantine = optional.get();
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
////		return tempDeclarationForPlantQuarantine;
////	}
////
////	/**
////	 * Returns all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear) throws SystemException {
////		return findBydocumentNameAnddocumentYear(documentName, documentYear,
////			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @return the range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear, int start, int end)
////		throws SystemException {
////		return findBydocumentNameAnddocumentYear(documentName, documentYear,
////			start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYear(
////		long documentName, int documentYear, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = null;
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
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////				query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////				list = (List<TempDeclarationForPlantQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findBydocumentNameAnddocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchBydocumentNameAnddocumentYear_First(documentName,
////				documentYear, orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchBydocumentNameAnddocumentYear_First(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = findBydocumentNameAnddocumentYear(documentName,
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
////	 * Returns the last temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findBydocumentNameAnddocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchBydocumentNameAnddocumentYear_Last(documentName,
////				documentYear, orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchBydocumentNameAnddocumentYear_Last(
////		long documentName, int documentYear, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countBydocumentNameAnddocumentYear(documentName,
////				documentYear);
////
////		List<TempDeclarationForPlantQuarantine> list = findBydocumentNameAnddocumentYear(documentName,
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
////	 * Returns the temp declaration for plant quarantines before and after the current temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for plant quarantine
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine[] findBydocumentNameAnddocumentYear_PrevAndNext(
////		long id, long documentName, int documentYear,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForPlantQuarantine[] array = new TempDeclarationForPlantQuarantine[3];
////
////			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
////					tempDeclarationForPlantQuarantine, documentName,
////					documentYear, orderByComparator, true);
////
////			array[1] = tempDeclarationForPlantQuarantine;
////
////			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
////					tempDeclarationForPlantQuarantine, documentName,
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
////	protected TempDeclarationForPlantQuarantine getBydocumentNameAnddocumentYear_PrevAndNext(
////
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////			query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForPlantQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForPlantQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @return the matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		return findBydocumentNameAnddocumentYearRequestState(documentName,
////			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
////			null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @return the range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState, int start,
////		int end) throws SystemException {
////		return findBydocumentNameAnddocumentYearRequestState(documentName,
////			documentYear, requestState, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState, int start,
////		int end, OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = null;
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
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////				query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////				list = (List<TempDeclarationForPlantQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findBydocumentNameAnddocumentYearRequestState_First(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchBydocumentNameAnddocumentYearRequestState_First(documentName,
////				documentYear, requestState, orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchBydocumentNameAnddocumentYearRequestState_First(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = findBydocumentNameAnddocumentYearRequestState(documentName,
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
////	 * Returns the last temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findBydocumentNameAnddocumentYearRequestState_Last(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchBydocumentNameAnddocumentYearRequestState_Last(documentName,
////				documentYear, requestState, orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchBydocumentNameAnddocumentYearRequestState_Last(
////		long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countBydocumentNameAnddocumentYearRequestState(documentName,
////				documentYear, requestState);
////
////		List<TempDeclarationForPlantQuarantine> list = findBydocumentNameAnddocumentYearRequestState(documentName,
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
////	 * Returns the temp declaration for plant quarantines before and after the current temp declaration for plant quarantine in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for plant quarantine
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine[] findBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////		long id, long documentName, int documentYear, int requestState,
////		OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForPlantQuarantine[] array = new TempDeclarationForPlantQuarantine[3];
////
////			array[0] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////					tempDeclarationForPlantQuarantine, documentName,
////					documentYear, requestState, orderByComparator, true);
////
////			array[1] = tempDeclarationForPlantQuarantine;
////
////			array[2] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////					tempDeclarationForPlantQuarantine, documentName,
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
////	protected TempDeclarationForPlantQuarantine getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
////
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////			query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForPlantQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForPlantQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for plant quarantines where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findByRequestCode(
////		String requestCode) throws SystemException {
////		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for plant quarantines where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @return the range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findByRequestCode(
////		String requestCode, int start, int end) throws SystemException {
////		return findByRequestCode(requestCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for plant quarantines where requestCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param requestCode the request code
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findByRequestCode(
////		String requestCode, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = null;
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
////			query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////				query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////				list = (List<TempDeclarationForPlantQuarantine>)queryFactory.getResultList(builder);
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
////	 * Returns the first temp declaration for plant quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchByRequestCode_First(requestCode,
////				orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the first temp declaration for plant quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchByRequestCode_First(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = findByRequestCode(requestCode,
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
////	 * Returns the last temp declaration for plant quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine findByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = fetchByRequestCode_Last(requestCode,
////				orderByComparator);
////
////		if (tempDeclarationForPlantQuarantine != null) {
////			return tempDeclarationForPlantQuarantine;
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
////		throw new NoSuchTempDeclarationForPlantQuarantineException(msg.toString());
////	}
////
////	/**
////	 * Returns the last temp declaration for plant quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching temp declaration for plant quarantine, or <code>null</code> if a matching temp declaration for plant quarantine could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine fetchByRequestCode_Last(
////		String requestCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countByRequestCode(requestCode);
////
////		List<TempDeclarationForPlantQuarantine> list = findByRequestCode(requestCode,
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
////	 * Returns the temp declaration for plant quarantines before and after the current temp declaration for plant quarantine in the ordered set where requestCode = &#63;.
////	 *
////	 * @param id the primary key of the current temp declaration for plant quarantine
////	 * @param requestCode the request code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next temp declaration for plant quarantine
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDeclarationForPlantQuarantineException if a temp declaration for plant quarantine with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public TempDeclarationForPlantQuarantine[] findByRequestCode_PrevAndNext(
////		long id, String requestCode, OrderByComparator orderByComparator)
////		throws NoSuchTempDeclarationForPlantQuarantineException, SystemException {
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			TempDeclarationForPlantQuarantine[] array = new TempDeclarationForPlantQuarantine[3];
////
////			array[0] = getByRequestCode_PrevAndNext(
////					tempDeclarationForPlantQuarantine, requestCode,
////					orderByComparator, true);
////
////			array[1] = tempDeclarationForPlantQuarantine;
////
////			array[2] = getByRequestCode_PrevAndNext(
////					tempDeclarationForPlantQuarantine, requestCode,
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
////	protected TempDeclarationForPlantQuarantine getByRequestCode_PrevAndNext(
////
////		TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine,
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
////		query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////			query.append(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
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
////			Object[] values = orderByComparator.getOrderByConditionValues(tempDeclarationForPlantQuarantine);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<TempDeclarationForPlantQuarantine> list = queryFactory.getResultList(builder);
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
////	 * Returns all the temp declaration for plant quarantines.
////	 *
////	 * @return the temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findAll()
////		throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the temp declaration for plant quarantines.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @return the range of temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the temp declaration for plant quarantines.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of temp declaration for plant quarantines
////	 * @param end the upper bound of the range of temp declaration for plant quarantines (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<TempDeclarationForPlantQuarantine> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<TempDeclarationForPlantQuarantine> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE.concat(TempDeclarationForPlantQuarantineModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<TempDeclarationForPlantQuarantine>) queryFactory.getResultList(builder);
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
////	 * Removes all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeBydocumentNameAnddocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		for (TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine : findBydocumentNameAnddocumentYear(
////				documentName, documentYear)) {
////			repository.delete(tempDeclarationForPlantQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		for (TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine : findBydocumentNameAnddocumentYearRequestState(
////				documentName, documentYear, requestState)) {
////			repository.delete(tempDeclarationForPlantQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for plant quarantines where requestCode = &#63; from the database.
////	 *
////	 * @param requestCode the request code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByRequestCode(String requestCode)
////		throws SystemException {
////		for (TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine : findByRequestCode(
////				requestCode)) {
////			repository.delete(tempDeclarationForPlantQuarantine);
////		}
////	}
////
////	/**
////	 * Removes all the temp declaration for plant quarantines from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine : findAll()) {
////			repository.delete(tempDeclarationForPlantQuarantine);
////		}
////	}
////
////	/**
////	 * Returns the number of temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the number of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countBydocumentNameAnddocumentYear(long documentName,
////		int documentYear) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////	 * Returns the number of temp declaration for plant quarantines where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param requestState the request state
////	 * @return the number of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countBydocumentNameAnddocumentYearRequestState(
////		long documentName, int documentYear, int requestState)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(4);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////	 * Returns the number of temp declaration for plant quarantines where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the number of matching temp declaration for plant quarantines
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByRequestCode(String requestCode) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE);
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
////	 * Returns the number of temp declaration for plant quarantines.
////	 *
////	 * @return the number of temp declaration for plant quarantines
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE).build();
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
////	 * Initializes the temp declaration for plant quarantine persistence.
////	 */
////	private static final String _SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE = "SELECT tempDeclarationForPlantQuarantine FROM TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine";
////	private static final String _SQL_SELECT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE =
////		"SELECT tempDeclarationForPlantQuarantine FROM TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine WHERE ";
////	private static final String _SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE = "SELECT COUNT(tempDeclarationForPlantQuarantine) FROM TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine";
////	private static final String _SQL_COUNT_TEMPDECLARATIONFORPLANTQUARANTINE_WHERE =
////		"SELECT COUNT(tempDeclarationForPlantQuarantine) FROM TempDeclarationForPlantQuarantine tempDeclarationForPlantQuarantine WHERE ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
////		"tempDeclarationForPlantQuarantine.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
////		"tempDeclarationForPlantQuarantine.documentYear =:documentYear";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2 =
////		"tempDeclarationForPlantQuarantine.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2 =
////		"tempDeclarationForPlantQuarantine.documentYear =:documentYear AND ";
////	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2 =
////		"tempDeclarationForPlantQuarantine.requestState =:requestState";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempDeclarationForPlantQuarantine.requestCode IS NULL";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempDeclarationForPlantQuarantine.requestCode =:requestCode";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempDeclarationForPlantQuarantine.requestCode IS NULL OR tempDeclarationForPlantQuarantine.requestCode =:requestCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "tempDeclarationForPlantQuarantine.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempDeclarationForPlantQuarantine exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempDeclarationForPlantQuarantine exists with the key {";
////
////
////
////}
