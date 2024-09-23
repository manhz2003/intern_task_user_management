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
import com.fds.nsw.nghiepvu.model.IssuePortClearance;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.IssuePortClearanceRepository;
import com.fds.nsw.nghiepvu.modelImpl.IssuePortClearanceModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssuePortClearancePersistenceImpl extends BasePersistence {
	@Autowired
	IssuePortClearanceRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssuePortClearance> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IssuePortClearanceUtil} to access the issue port clearance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public IssuePortClearance create(long id) {
		IssuePortClearance issuePortClearance = new IssuePortClearance();

		
		//issuePortClearance.setPrimaryKey(id);

		return issuePortClearance;
	}

	/**
	 * Removes the issue port clearance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue port clearance
	 * @return the issue port clearance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance remove(long id)
		throws NoSuchIssuePortClearanceException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the issue port clearance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue port clearance
	 * @return the issue port clearance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssuePortClearance remove(Serializable primaryKey)
		throws NoSuchIssuePortClearanceException, SystemException {
		

		try {
			

			IssuePortClearance issuePortClearance = findByPrimaryKey(primaryKey);

			if (issuePortClearance == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssuePortClearanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(issuePortClearance);
			return issuePortClearance;
		}
		catch (NoSuchIssuePortClearanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public IssuePortClearance remove(IssuePortClearance IssuePortClearance) throws SystemException {
	removeImpl(IssuePortClearance);
	return IssuePortClearance;
}

protected IssuePortClearance removeImpl(
		IssuePortClearance issuePortClearance) throws SystemException {
		try {
			repository.delete(issuePortClearance);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issuePortClearance;
	}

	
	public IssuePortClearance updateImpl(
		com.fds.nsw.nghiepvu.model.IssuePortClearance issuePortClearance,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(issuePortClearance);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issuePortClearance;
	}

	
	public IssuePortClearance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue port clearance with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException} if it could not be found.
	 *
	 * @param id the primary key of the issue port clearance
	 * @return the issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByPrimaryKey(long id)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByPrimaryKey(id);

		if (issuePortClearance == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchIssuePortClearanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return issuePortClearance;
	}

	/**
	 * Returns the issue port clearance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue port clearance
	 * @return the issue port clearance, or <code>null</code> if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssuePortClearance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue port clearance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the issue port clearance
	 * @return the issue port clearance, or <code>null</code> if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByPrimaryKey(long id)
		throws SystemException {
		IssuePortClearance issuePortClearance = null;

		

		if (issuePortClearance == null) {
			

			boolean hasException = false;

			try {
				

				Optional<IssuePortClearance> optional = repository.findById(id);
				if (optional.isPresent()) {
					issuePortClearance = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return issuePortClearance;
	}

	/**
	 * Returns all the issue port clearances where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue port clearances where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @return the range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue port clearances where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<IssuePortClearance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the first issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePortClearance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the last issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear);

		List<IssuePortClearance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue port clearances before and after the current issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current issue port clearance
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance[] findByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = findByPrimaryKey(id);

		

		try {
			

			IssuePortClearance[] array = new IssuePortClearance[3];

			array[0] = getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
					issuePortClearance, documentName, documentYear,
					orderByComparator, true);

			array[1] = issuePortClearance;

			array[2] = getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
					issuePortClearance, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePortClearance getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
		 IssuePortClearance issuePortClearance,
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

		query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

		query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePortClearance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePortClearance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue port clearances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the issue port clearances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @return the range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end) throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue port clearances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				list = (List<IssuePortClearance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(documentName,
				documentYear, requestState, orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
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

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the first issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(documentName,
				documentYear, requestState, orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
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

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the last issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState);

		List<IssuePortClearance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue port clearances before and after the current issue port clearance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param id the primary key of the current issue port clearance
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance[] findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		long id, long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = findByPrimaryKey(id);

		

		try {
			

			IssuePortClearance[] array = new IssuePortClearance[3];

			array[0] = getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issuePortClearance, documentName, documentYear,
					requestState, orderByComparator, true);

			array[1] = issuePortClearance;

			array[2] = getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issuePortClearance, documentName, documentYear,
					requestState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePortClearance getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		 IssuePortClearance issuePortClearance,
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

		query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

		query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

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
			query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("requestState", requestState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePortClearance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePortClearance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue port clearances where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue port clearances where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @return the range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue port clearances where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePortClearance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

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
				query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<IssuePortClearance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue port clearance in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the first issue port clearance in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue port clearance in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the last issue port clearance in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<IssuePortClearance> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue port clearances before and after the current issue port clearance in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current issue port clearance
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = findByPrimaryKey(id);

		

		try {
			

			IssuePortClearance[] array = new IssuePortClearance[3];

			array[0] = getByRequestCode_PrevAndNext(
					issuePortClearance, requestCode, orderByComparator, true);

			array[1] = issuePortClearance;

			array[2] = getByRequestCode_PrevAndNext(
					issuePortClearance, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePortClearance getByRequestCode_PrevAndNext(
		IssuePortClearance issuePortClearance, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

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
			query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePortClearance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePortClearance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue port clearances where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @return the matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByF_LAST_numberPortClearance(
		String numberPortClearance) throws SystemException {
		return findByF_LAST_numberPortClearance(numberPortClearance,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue port clearances where numberPortClearance = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param numberPortClearance the number port clearance
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @return the range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByF_LAST_numberPortClearance(
		String numberPortClearance, int start, int end)
		throws SystemException {
		return findByF_LAST_numberPortClearance(numberPortClearance, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the issue port clearances where numberPortClearance = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param numberPortClearance the number port clearance
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findByF_LAST_numberPortClearance(
		String numberPortClearance, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

			if (numberPortClearance == null) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_1);
			}
			else {
				if (numberPortClearance.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (numberPortClearance != null) {
					builder.appendNamedParameterMap("numberPortClearance", numberPortClearance);
				}

				list = (List<IssuePortClearance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue port clearance in the ordered set where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByF_LAST_numberPortClearance_First(
		String numberPortClearance, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByF_LAST_numberPortClearance_First(numberPortClearance,
				orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("numberPortClearance=");
		msg.append(numberPortClearance);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the first issue port clearance in the ordered set where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByF_LAST_numberPortClearance_First(
		String numberPortClearance, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePortClearance> list = findByF_LAST_numberPortClearance(numberPortClearance,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue port clearance in the ordered set where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance findByF_LAST_numberPortClearance_Last(
		String numberPortClearance, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = fetchByF_LAST_numberPortClearance_Last(numberPortClearance,
				orderByComparator);

		if (issuePortClearance != null) {
			return issuePortClearance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("numberPortClearance=");
		msg.append(numberPortClearance);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePortClearanceException(msg.toString());
	}

	/**
	 * Returns the last issue port clearance in the ordered set where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue port clearance, or <code>null</code> if a matching issue port clearance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance fetchByF_LAST_numberPortClearance_Last(
		String numberPortClearance, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_LAST_numberPortClearance(numberPortClearance);

		List<IssuePortClearance> list = findByF_LAST_numberPortClearance(numberPortClearance,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue port clearances before and after the current issue port clearance in the ordered set where numberPortClearance = &#63;.
	 *
	 * @param id the primary key of the current issue port clearance
	 * @param numberPortClearance the number port clearance
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue port clearance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePortClearanceException if a issue port clearance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePortClearance[] findByF_LAST_numberPortClearance_PrevAndNext(
		long id, String numberPortClearance, OrderByComparator orderByComparator)
		throws NoSuchIssuePortClearanceException, SystemException {
		IssuePortClearance issuePortClearance = findByPrimaryKey(id);

		

		try {
			

			IssuePortClearance[] array = new IssuePortClearance[3];

			array[0] = getByF_LAST_numberPortClearance_PrevAndNext(
					issuePortClearance, numberPortClearance, orderByComparator,
					true);

			array[1] = issuePortClearance;

			array[2] = getByF_LAST_numberPortClearance_PrevAndNext(
					issuePortClearance, numberPortClearance, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePortClearance getByF_LAST_numberPortClearance_PrevAndNext(
		 IssuePortClearance issuePortClearance,
		String numberPortClearance, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEPORTCLEARANCE_WHERE);

		if (numberPortClearance == null) {
			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_1);
		}
		else {
			if (numberPortClearance.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_2);
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
			query.append(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (numberPortClearance != null) {
			builder.appendNamedParameterMap("numberPortClearance", numberPortClearance);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePortClearance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePortClearance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue port clearances.
	 *
	 * @return the issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue port clearances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @return the range of issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue port clearances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue port clearances
	 * @param end the upper bound of the range of issue port clearances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePortClearance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePortClearance> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ISSUEPORTCLEARANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUEPORTCLEARANCE.concat(IssuePortClearanceModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<IssuePortClearance>) queryFactory.getResultList(builder);
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
	 * Removes all the issue port clearances where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		for (IssuePortClearance issuePortClearance : findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(issuePortClearance);
		}
	}

	/**
	 * Removes all the issue port clearances where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		for (IssuePortClearance issuePortClearance : findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
				documentName, documentYear, requestState)) {
			repository.delete(issuePortClearance);
		}
	}

	/**
	 * Removes all the issue port clearances where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (IssuePortClearance issuePortClearance : findByRequestCode(
				requestCode)) {
			repository.delete(issuePortClearance);
		}
	}

	/**
	 * Removes all the issue port clearances where numberPortClearance = &#63; from the database.
	 *
	 * @param numberPortClearance the number port clearance
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_LAST_numberPortClearance(String numberPortClearance)
		throws SystemException {
		for (IssuePortClearance issuePortClearance : findByF_LAST_numberPortClearance(
				numberPortClearance)) {
			repository.delete(issuePortClearance);
		}
	}

	/**
	 * Removes all the issue port clearances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (IssuePortClearance issuePortClearance : findAll()) {
			repository.delete(issuePortClearance);
		}
	}

	/**
	 * Returns the number of issue port clearances where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ISSUEPORTCLEARANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
	 * Returns the number of issue port clearances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUEPORTCLEARANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

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
	 * Returns the number of issue port clearances where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUEPORTCLEARANCE_WHERE);

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
	 * Returns the number of issue port clearances where numberPortClearance = &#63;.
	 *
	 * @param numberPortClearance the number port clearance
	 * @return the number of matching issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_LAST_numberPortClearance(String numberPortClearance)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUEPORTCLEARANCE_WHERE);

			if (numberPortClearance == null) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_1);
			}
			else {
				if (numberPortClearance.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (numberPortClearance != null) {
					builder.appendNamedParameterMap("numberPortClearance", numberPortClearance);
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
	 * Returns the number of issue port clearances.
	 *
	 * @return the number of issue port clearances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUEPORTCLEARANCE).build();

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
	 * Initializes the issue port clearance persistence.
	 */
	private static final String _SQL_SELECT_ISSUEPORTCLEARANCE = "SELECT issuePortClearance FROM IssuePortClearance issuePortClearance";
	private static final String _SQL_SELECT_ISSUEPORTCLEARANCE_WHERE = "SELECT issuePortClearance FROM IssuePortClearance issuePortClearance WHERE ";
	private static final String _SQL_COUNT_ISSUEPORTCLEARANCE = "SELECT COUNT(issuePortClearance) FROM IssuePortClearance issuePortClearance";
	private static final String _SQL_COUNT_ISSUEPORTCLEARANCE_WHERE = "SELECT COUNT(issuePortClearance) FROM IssuePortClearance issuePortClearance WHERE ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"issuePortClearance.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"issuePortClearance.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2 =
		"issuePortClearance.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2 =
		"issuePortClearance.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2 =
		"issuePortClearance.requestState =:requestState";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "issuePortClearance.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "issuePortClearance.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(issuePortClearance.requestCode IS NULL OR issuePortClearance.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_1 =
		"issuePortClearance.numberPortClearance IS NULL";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_2 =
		"issuePortClearance.numberPortClearance =:numberPortClearance";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPORTCLEARANCE_3 =
		"(issuePortClearance.numberPortClearance IS NULL OR issuePortClearance.numberPortClearance =:numberPortClearance)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "issuePortClearance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssuePortClearance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssuePortClearance exists with the key {";
	

	
}
