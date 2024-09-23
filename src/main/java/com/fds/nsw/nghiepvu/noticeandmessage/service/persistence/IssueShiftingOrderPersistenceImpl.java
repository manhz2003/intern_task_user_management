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

package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;

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
import com.fds.nsw.nghiepvu.model.IssueShiftingOrder;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.IssueShiftingOrderRepository;
import com.fds.nsw.nghiepvu.modelImpl.IssueShiftingOrderModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueShiftingOrderPersistenceImpl extends BasePersistence {
	@Autowired
	IssueShiftingOrderRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssueShiftingOrder> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IssueShiftingOrderUtil} to access the issue shifting order persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public IssueShiftingOrder create(long id) {
		IssueShiftingOrder issueShiftingOrder = new IssueShiftingOrder();

		
		//issueShiftingOrder.setPrimaryKey(id);

		return issueShiftingOrder;
	}

	/**
	 * Removes the issue shifting order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue shifting order
	 * @return the issue shifting order that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder remove(long id)
		throws NoSuchIssueShiftingOrderException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the issue shifting order with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue shifting order
	 * @return the issue shifting order that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueShiftingOrder remove(Serializable primaryKey)
		throws NoSuchIssueShiftingOrderException, SystemException {
		

		try {
			

			IssueShiftingOrder issueShiftingOrder = findByPrimaryKey(primaryKey);

			if (issueShiftingOrder == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssueShiftingOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(issueShiftingOrder);
			return issueShiftingOrder;
		}
		catch (NoSuchIssueShiftingOrderException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public IssueShiftingOrder remove(IssueShiftingOrder IssueShiftingOrder) throws SystemException {
	removeImpl(IssueShiftingOrder);
	return IssueShiftingOrder;
}

protected IssueShiftingOrder removeImpl(
		IssueShiftingOrder issueShiftingOrder) throws SystemException {
		try {
			repository.delete(issueShiftingOrder);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueShiftingOrder;
	}

	
	public IssueShiftingOrder updateImpl(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrder issueShiftingOrder,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(issueShiftingOrder);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueShiftingOrder;
	}

	
	public IssueShiftingOrder findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue shifting order with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException} if it could not be found.
	 *
	 * @param id the primary key of the issue shifting order
	 * @return the issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByPrimaryKey(long id)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByPrimaryKey(id);

		if (issueShiftingOrder == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchIssueShiftingOrderException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return issueShiftingOrder;
	}

	/**
	 * Returns the issue shifting order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue shifting order
	 * @return the issue shifting order, or <code>null</code> if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueShiftingOrder fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue shifting order with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the issue shifting order
	 * @return the issue shifting order, or <code>null</code> if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByPrimaryKey(long id)
		throws SystemException {
		IssueShiftingOrder issueShiftingOrder = null;

		

		if (issueShiftingOrder == null) {
			

			boolean hasException = false;

			try {
				

				Optional<IssueShiftingOrder> optional = repository.findById(id);
				if (optional.isPresent()) {
					issueShiftingOrder = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return issueShiftingOrder;
	}

	/**
	 * Returns all the issue shifting orders where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting orders where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @return the range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting orders where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<IssueShiftingOrder>)queryFactory.getResultList(builder);
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
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByfindIssueShiftingOrderByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssueShiftingOrder> list = findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByfindIssueShiftingOrderByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByfindIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
				documentYear);

		List<IssueShiftingOrder> list = findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue shifting orders before and after the current issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current issue shifting order
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder[] findByfindIssueShiftingOrderByDocumentYearAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = findByPrimaryKey(id);

		

		try {
			

			IssueShiftingOrder[] array = new IssueShiftingOrder[3];

			array[0] = getByfindIssueShiftingOrderByDocumentYearAndDocumentYear_PrevAndNext(
					issueShiftingOrder, documentName, documentYear,
					orderByComparator, true);

			array[1] = issueShiftingOrder;

			array[2] = getByfindIssueShiftingOrderByDocumentYearAndDocumentYear_PrevAndNext(
					issueShiftingOrder, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueShiftingOrder getByfindIssueShiftingOrderByDocumentYearAndDocumentYear_PrevAndNext(
		 IssueShiftingOrder issueShiftingOrder,
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

		query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

		query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueShiftingOrder);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueShiftingOrder> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue shifting orders where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the issue shifting orders where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @return the range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end) throws SystemException {
		return findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting orders where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				list = (List<IssueShiftingOrder>)queryFactory.getResultList(builder);
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
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_First(documentName,
				documentYear, requestState, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestState=");
		msg.append(requestState);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_Last(documentName,
				documentYear, requestState, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestState=");
		msg.append(requestState);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState);

		List<IssueShiftingOrder> list = findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue shifting orders before and after the current issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param id the primary key of the current issue shifting order
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder[] findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		long id, long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = findByPrimaryKey(id);

		

		try {
			

			IssueShiftingOrder[] array = new IssueShiftingOrder[3];

			array[0] = getByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issueShiftingOrder, documentName, documentYear,
					requestState, orderByComparator, true);

			array[1] = issueShiftingOrder;

			array[2] = getByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issueShiftingOrder, documentName, documentYear,
					requestState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueShiftingOrder getByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		 IssueShiftingOrder issueShiftingOrder,
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

		query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

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
			query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("requestState", requestState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueShiftingOrder);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueShiftingOrder> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue shifting orders where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting orders where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @return the range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting orders where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssueShiftingOrder> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<IssueShiftingOrder>)queryFactory.getResultList(builder);
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
	 * Returns the first issue shifting order in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the first issue shifting order in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue shifting order in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the last issue shifting order in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<IssueShiftingOrder> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue shifting orders before and after the current issue shifting order in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current issue shifting order
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = findByPrimaryKey(id);

		

		try {
			

			IssueShiftingOrder[] array = new IssueShiftingOrder[3];

			array[0] = getByRequestCode_PrevAndNext(
					issueShiftingOrder, requestCode, orderByComparator, true);

			array[1] = issueShiftingOrder;

			array[2] = getByRequestCode_PrevAndNext(
					issueShiftingOrder, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueShiftingOrder getByRequestCode_PrevAndNext(
		IssueShiftingOrder issueShiftingOrder, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

		if (requestCode == null) {
			query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
		}
		else {
			if (requestCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
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
			query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueShiftingOrder);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueShiftingOrder> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue shifting orders where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws SystemException {
		return findByDocumentNameAndDocumentYearAndVersionNo(documentName,
			documentYear, versionNo, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting orders where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @return the range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo, int start,
		int end) throws SystemException {
		return findByDocumentNameAndDocumentYearAndVersionNo(documentName,
			documentYear, versionNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting orders where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTYEAR_2);

			if (versionNo == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_1);
			}
			else {
				if (versionNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (versionNo != null) {
					builder.appendNamedParameterMap("versionNo", versionNo);
				}

				list = (List<IssueShiftingOrder>)queryFactory.getResultList(builder);
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
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByDocumentNameAndDocumentYearAndVersionNo_First(
		long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByDocumentNameAndDocumentYearAndVersionNo_First(documentName,
				documentYear, versionNo, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", versionNo=");
		msg.append(versionNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the first issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByDocumentNameAndDocumentYearAndVersionNo_First(
		long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = findByDocumentNameAndDocumentYearAndVersionNo(documentName,
				documentYear, versionNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder findByDocumentNameAndDocumentYearAndVersionNo_Last(
		long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = fetchByDocumentNameAndDocumentYearAndVersionNo_Last(documentName,
				documentYear, versionNo, orderByComparator);

		if (issueShiftingOrder != null) {
			return issueShiftingOrder;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", versionNo=");
		msg.append(versionNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderException(msg.toString());
	}

	/**
	 * Returns the last issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order, or <code>null</code> if a matching issue shifting order could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder fetchByDocumentNameAndDocumentYearAndVersionNo_Last(
		long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAndDocumentYearAndVersionNo(documentName,
				documentYear, versionNo);

		List<IssueShiftingOrder> list = findByDocumentNameAndDocumentYearAndVersionNo(documentName,
				documentYear, versionNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue shifting orders before and after the current issue shifting order in the ordered set where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param id the primary key of the current issue shifting order
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue shifting order
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderException if a issue shifting order with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrder[] findByDocumentNameAndDocumentYearAndVersionNo_PrevAndNext(
		long id, long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderException, SystemException {
		IssueShiftingOrder issueShiftingOrder = findByPrimaryKey(id);

		

		try {
			

			IssueShiftingOrder[] array = new IssueShiftingOrder[3];

			array[0] = getByDocumentNameAndDocumentYearAndVersionNo_PrevAndNext(
					issueShiftingOrder, documentName, documentYear, versionNo,
					orderByComparator, true);

			array[1] = issueShiftingOrder;

			array[2] = getByDocumentNameAndDocumentYearAndVersionNo_PrevAndNext(
					issueShiftingOrder, documentName, documentYear, versionNo,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueShiftingOrder getByDocumentNameAndDocumentYearAndVersionNo_PrevAndNext(
		 IssueShiftingOrder issueShiftingOrder,
		long documentName, int documentYear, String versionNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUESHIFTINGORDER_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTYEAR_2);

		if (versionNo == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_1);
		}
		else {
			if (versionNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_2);
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
			query.append(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (versionNo != null) {
			builder.appendNamedParameterMap("versionNo", versionNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueShiftingOrder);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueShiftingOrder> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue shifting orders.
	 *
	 * @return the issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @return the range of issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting orders.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue shifting orders
	 * @param end the upper bound of the range of issue shifting orders (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrder> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrder> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ISSUESHIFTINGORDER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUESHIFTINGORDER.concat(IssueShiftingOrderModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<IssueShiftingOrder>) queryFactory.getResultList(builder);
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
	 * Removes all the issue shifting orders where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		for (IssueShiftingOrder issueShiftingOrder : findByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(issueShiftingOrder);
		}
	}

	/**
	 * Removes all the issue shifting orders where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		for (IssueShiftingOrder issueShiftingOrder : findByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
				documentName, documentYear, requestState)) {
			repository.delete(issueShiftingOrder);
		}
	}

	/**
	 * Removes all the issue shifting orders where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (IssueShiftingOrder issueShiftingOrder : findByRequestCode(
				requestCode)) {
			repository.delete(issueShiftingOrder);
		}
	}

	/**
	 * Removes all the issue shifting orders where documentName = &#63; and documentYear = &#63; and versionNo = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws SystemException {
		for (IssueShiftingOrder issueShiftingOrder : findByDocumentNameAndDocumentYearAndVersionNo(
				documentName, documentYear, versionNo)) {
			repository.delete(issueShiftingOrder);
		}
	}

	/**
	 * Removes all the issue shifting orders from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (IssueShiftingOrder issueShiftingOrder : findAll()) {
			repository.delete(issueShiftingOrder);
		}
	}

	/**
	 * Returns the number of issue shifting orders where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssueShiftingOrderByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
	 * Returns the number of issue shifting orders where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssueShiftingOrderByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

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
	 * Returns the number of issue shifting orders where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUESHIFTINGORDER_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

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
	 * Returns the number of issue shifting orders where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the number of matching issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUESHIFTINGORDER_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTYEAR_2);

			if (versionNo == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_1);
			}
			else {
				if (versionNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (versionNo != null) {
					builder.appendNamedParameterMap("versionNo", versionNo);
				}

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
	 * Returns the number of issue shifting orders.
	 *
	 * @return the number of issue shifting orders
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUESHIFTINGORDER).build();

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
	 * Initializes the issue shifting order persistence.
	 */
	private static final String _SQL_SELECT_ISSUESHIFTINGORDER = "SELECT issueShiftingOrder FROM IssueShiftingOrder issueShiftingOrder";
	private static final String _SQL_SELECT_ISSUESHIFTINGORDER_WHERE = "SELECT issueShiftingOrder FROM IssueShiftingOrder issueShiftingOrder WHERE ";
	private static final String _SQL_COUNT_ISSUESHIFTINGORDER = "SELECT COUNT(issueShiftingOrder) FROM IssueShiftingOrder issueShiftingOrder";
	private static final String _SQL_COUNT_ISSUESHIFTINGORDER_WHERE = "SELECT COUNT(issueShiftingOrder) FROM IssueShiftingOrder issueShiftingOrder WHERE ";
	private static final String _FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"issueShiftingOrder.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"issueShiftingOrder.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2 =
		"issueShiftingOrder.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2 =
		"issueShiftingOrder.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_FINDISSUESHIFTINGORDERBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2 =
		"issueShiftingOrder.requestState =:requestState";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "issueShiftingOrder.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "issueShiftingOrder.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(issueShiftingOrder.requestCode IS NULL OR issueShiftingOrder.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTNAME_2 =
		"issueShiftingOrder.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTYEAR_2 =
		"issueShiftingOrder.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_1 =
		"issueShiftingOrder.versionNo IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_2 =
		"issueShiftingOrder.versionNo =:versionNo";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_3 =
		"(issueShiftingOrder.versionNo IS NULL OR issueShiftingOrder.versionNo =:versionNo)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "issueShiftingOrder.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssueShiftingOrder exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssueShiftingOrder exists with the key {";
	

	
}
