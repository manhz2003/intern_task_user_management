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

package com.fds.nsw.nghiepvu.result.service.persistence;

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
import com.fds.nsw.nghiepvu.model.ResultNotification;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultNotificationRepository;
import com.fds.nsw.nghiepvu.modelImpl.ResultNotificationModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultNotificationPersistenceImpl extends BasePersistence {
	@Autowired
	ResultNotificationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultNotification> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultNotificationUtil} to access the result notification persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultNotification create(long id) {
		ResultNotification resultNotification = new ResultNotification();

		
		//resultNotification.setPrimaryKey(id);

		return resultNotification;
	}

	/**
	 * Removes the result notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result notification
	 * @return the result notification that was removed
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification remove(long id)
		throws NoSuchResultNotificationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result notification with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result notification
	 * @return the result notification that was removed
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultNotification remove(Serializable primaryKey)
		throws NoSuchResultNotificationException, SystemException {
		

		try {
			

			ResultNotification resultNotification = findByPrimaryKey(primaryKey);

			if (resultNotification == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(resultNotification);
			return resultNotification;
		}
		catch (NoSuchResultNotificationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultNotification remove(ResultNotification ResultNotification) throws SystemException {
	removeImpl(ResultNotification);
	return ResultNotification;
}

protected ResultNotification removeImpl(
		ResultNotification resultNotification) throws SystemException {
		try {
			repository.delete(resultNotification);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultNotification;
	}

	
	public ResultNotification updateImpl(
		com.fds.nsw.nghiepvu.model.ResultNotification resultNotification,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(resultNotification);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultNotification;
	}

	
	public ResultNotification findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result notification with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultNotificationException} if it could not be found.
	 *
	 * @param id the primary key of the result notification
	 * @return the result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification findByPrimaryKey(long id)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = fetchByPrimaryKey(id);

		if (resultNotification == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultNotificationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultNotification;
	}

	/**
	 * Returns the result notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result notification
	 * @return the result notification, or <code>null</code> if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultNotification fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result notification with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result notification
	 * @return the result notification, or <code>null</code> if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification fetchByPrimaryKey(long id)
		throws SystemException {
		ResultNotification resultNotification = null;

		

		if (resultNotification == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ResultNotification> optional = repository.findById(id);
				if (optional.isPresent()) {
					resultNotification = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultNotification;
	}

	/**
	 * Returns all the result notifications where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByBusinessTypeCode(
		int businessTypeCode, long documentName, int documentYear)
		throws SystemException {
		return findByBusinessTypeCode(businessTypeCode, documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result notifications where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @return the range of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByBusinessTypeCode(
		int businessTypeCode, long documentName, int documentYear, int start,
		int end) throws SystemException {
		return findByBusinessTypeCode(businessTypeCode, documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result notifications where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByBusinessTypeCode(
		int businessTypeCode, long documentName, int documentYear, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultNotification> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultNotification>)queryFactory.getResultList(builder);
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
	 * Returns the first result notification in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification findByBusinessTypeCode_First(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = fetchByBusinessTypeCode_First(businessTypeCode,
				documentName, documentYear, orderByComparator);

		if (resultNotification != null) {
			return resultNotification;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultNotificationException(msg.toString());
	}

	/**
	 * Returns the first result notification in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result notification, or <code>null</code> if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification fetchByBusinessTypeCode_First(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultNotification> list = findByBusinessTypeCode(businessTypeCode,
				documentName, documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result notification in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification findByBusinessTypeCode_Last(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = fetchByBusinessTypeCode_Last(businessTypeCode,
				documentName, documentYear, orderByComparator);

		if (resultNotification != null) {
			return resultNotification;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultNotificationException(msg.toString());
	}

	/**
	 * Returns the last result notification in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result notification, or <code>null</code> if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification fetchByBusinessTypeCode_Last(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByBusinessTypeCode(businessTypeCode, documentName,
				documentYear);

		List<ResultNotification> list = findByBusinessTypeCode(businessTypeCode,
				documentName, documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result notifications before and after the current result notification in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result notification
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification[] findByBusinessTypeCode_PrevAndNext(long id,
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = findByPrimaryKey(id);

		

		try {
			

			ResultNotification[] array = new ResultNotification[3];

			array[0] = getByBusinessTypeCode_PrevAndNext(
					resultNotification, businessTypeCode, documentName,
					documentYear, orderByComparator, true);

			array[1] = resultNotification;

			array[2] = getByBusinessTypeCode_PrevAndNext(
					resultNotification, businessTypeCode, documentName,
					documentYear, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultNotification getByBusinessTypeCode_PrevAndNext(
		 ResultNotification resultNotification,
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTNOTIFICATION_WHERE);

		query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

		query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTYEAR_2);

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
			query.append(ResultNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultNotification);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultNotification> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result notifications where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result notifications where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @return the range of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result notifications where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultNotification> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultNotificationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultNotification>)queryFactory.getResultList(builder);
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
	 * Returns the first result notification in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification findByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = fetchByDocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultNotification != null) {
			return resultNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultNotificationException(msg.toString());
	}

	/**
	 * Returns the first result notification in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result notification, or <code>null</code> if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification fetchByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultNotification> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result notification in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification findByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = fetchByDocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultNotification != null) {
			return resultNotification;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultNotificationException(msg.toString());
	}

	/**
	 * Returns the last result notification in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result notification, or <code>null</code> if a matching result notification could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification fetchByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYear(documentName,
				documentYear);

		List<ResultNotification> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result notifications before and after the current result notification in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result notification
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result notification
	 * @throws vn.gt.dao.result.NoSuchResultNotificationException if a result notification with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultNotification[] findByDocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultNotificationException, SystemException {
		ResultNotification resultNotification = findByPrimaryKey(id);

		

		try {
			

			ResultNotification[] array = new ResultNotification[3];

			array[0] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultNotification, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultNotification;

			array[2] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultNotification, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultNotification getByDocumentNameAnddocumentYear_PrevAndNext(
		 ResultNotification resultNotification,
		long documentName, int documentYear,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTNOTIFICATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(ResultNotificationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultNotification);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultNotification> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result notifications.
	 *
	 * @return the result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @return the range of result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result notifications.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result notifications
	 * @param end the upper bound of the range of result notifications (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultNotification> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultNotification> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTNOTIFICATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTNOTIFICATION.concat(ResultNotificationModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultNotification>) queryFactory.getResultList(builder);
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
	 * Removes all the result notifications where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByBusinessTypeCode(int businessTypeCode,
		long documentName, int documentYear) throws SystemException {
		for (ResultNotification resultNotification : findByBusinessTypeCode(
				businessTypeCode, documentName, documentYear)) {
			repository.delete(resultNotification);
		}
	}

	/**
	 * Removes all the result notifications where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (ResultNotification resultNotification : findByDocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(resultNotification);
		}
	}

	/**
	 * Removes all the result notifications from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultNotification resultNotification : findAll()) {
			repository.delete(resultNotification);
		}
	}

	/**
	 * Returns the number of result notifications where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByBusinessTypeCode(int businessTypeCode, long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTYEAR_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of result notifications where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTNOTIFICATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of result notifications.
	 *
	 * @return the number of result notifications
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTNOTIFICATION).build();

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
	 * Initializes the result notification persistence.
	 */
	private static final String _SQL_SELECT_RESULTNOTIFICATION = "SELECT resultNotification FROM ResultNotification resultNotification";
	private static final String _SQL_SELECT_RESULTNOTIFICATION_WHERE = "SELECT resultNotification FROM ResultNotification resultNotification WHERE ";
	private static final String _SQL_COUNT_RESULTNOTIFICATION = "SELECT COUNT(resultNotification) FROM ResultNotification resultNotification";
	private static final String _SQL_COUNT_RESULTNOTIFICATION_WHERE = "SELECT COUNT(resultNotification) FROM ResultNotification resultNotification WHERE ";
	private static final String _FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2 =
		"resultNotification.businessTypeCode =:businessTypeCode AND ";
	private static final String _FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTNAME_2 = "resultNotification.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_BUSINESSTYPECODE_DOCUMENTYEAR_2 = "resultNotification.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultNotification.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultNotification.documentYear =:documentYear";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultNotification.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultNotification exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultNotification exists with the key {";
	

	
}
