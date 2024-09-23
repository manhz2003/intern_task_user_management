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
import com.fds.nsw.nghiepvu.model.IssueAcceptance;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.IssueAcceptanceRepository;
import com.fds.nsw.nghiepvu.modelImpl.IssueAcceptanceModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueAcceptancePersistenceImpl extends BasePersistence {
	@Autowired
	IssueAcceptanceRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssueAcceptance> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IssueAcceptanceUtil} to access the issue acceptance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public IssueAcceptance create(long id) {
		IssueAcceptance issueAcceptance = new IssueAcceptance();

		
		//issueAcceptance.setPrimaryKey(id);

		return issueAcceptance;
	}

	/**
	 * Removes the issue acceptance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue acceptance
	 * @return the issue acceptance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance remove(long id)
		throws NoSuchIssueAcceptanceException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the issue acceptance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue acceptance
	 * @return the issue acceptance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueAcceptance remove(Serializable primaryKey)
		throws NoSuchIssueAcceptanceException, SystemException {
		

		try {
			

			IssueAcceptance issueAcceptance = findByPrimaryKey(primaryKey);

			if (issueAcceptance == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssueAcceptanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(issueAcceptance);
			return issueAcceptance;
		}
		catch (NoSuchIssueAcceptanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public IssueAcceptance remove(IssueAcceptance IssueAcceptance) throws SystemException {
	removeImpl(IssueAcceptance);
	return IssueAcceptance;
}

protected IssueAcceptance removeImpl(IssueAcceptance issueAcceptance)
		throws SystemException {
		try {
			repository.delete(issueAcceptance);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueAcceptance;
	}

	
	public IssueAcceptance updateImpl(
		com.fds.nsw.nghiepvu.model.IssueAcceptance issueAcceptance,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(issueAcceptance);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueAcceptance;
	}

	
	public IssueAcceptance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue acceptance with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException} if it could not be found.
	 *
	 * @param id the primary key of the issue acceptance
	 * @return the issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByPrimaryKey(long id)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByPrimaryKey(id);

		if (issueAcceptance == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchIssueAcceptanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return issueAcceptance;
	}

	/**
	 * Returns the issue acceptance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue acceptance
	 * @return the issue acceptance, or <code>null</code> if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueAcceptance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue acceptance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the issue acceptance
	 * @return the issue acceptance, or <code>null</code> if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByPrimaryKey(long id) throws SystemException {
		IssueAcceptance issueAcceptance = null;

		

		if (issueAcceptance == null) {
			

			boolean hasException = false;

			try {
				

				Optional<IssueAcceptance> optional = repository.findById(id);
				if (optional.isPresent()) {
					issueAcceptance = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return issueAcceptance;
	}

	/**
	 * Returns all the issue acceptances where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue acceptances where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @return the range of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue acceptances where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueAcceptance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ISSUEACCEPTANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueAcceptanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<IssueAcceptance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (issueAcceptance != null) {
			return issueAcceptance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueAcceptanceException(msg.toString());
	}

	/**
	 * Returns the first issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssueAcceptance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (issueAcceptance != null) {
			return issueAcceptance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueAcceptanceException(msg.toString());
	}

	/**
	 * Returns the last issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear);

		List<IssueAcceptance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue acceptances before and after the current issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current issue acceptance
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance[] findByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = findByPrimaryKey(id);

		

		try {
			

			IssueAcceptance[] array = new IssueAcceptance[3];

			array[0] = getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
					issueAcceptance, documentName, documentYear,
					orderByComparator, true);

			array[1] = issueAcceptance;

			array[2] = getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
					issueAcceptance, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueAcceptance getByfindIssuePortClearanceByDocumentYearAndDocumentYear_PrevAndNext(
		 IssueAcceptance issueAcceptance, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEACCEPTANCE_WHERE);

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
			query.append(IssueAcceptanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueAcceptance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueAcceptance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue acceptances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the issue acceptances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @return the range of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end) throws SystemException {
		return findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue acceptances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<IssueAcceptance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ISSUEACCEPTANCE_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueAcceptanceModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				list = (List<IssueAcceptance>)queryFactory.getResultList(builder);
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
	 * Returns the first issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(documentName,
				documentYear, requestState, orderByComparator);

		if (issueAcceptance != null) {
			return issueAcceptance;
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

		throw new NoSuchIssueAcceptanceException(msg.toString());
	}

	/**
	 * Returns the first issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueAcceptance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(documentName,
				documentYear, requestState, orderByComparator);

		if (issueAcceptance != null) {
			return issueAcceptance;
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

		throw new NoSuchIssueAcceptanceException(msg.toString());
	}

	/**
	 * Returns the last issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState);

		List<IssueAcceptance> list = findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue acceptances before and after the current issue acceptance in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param id the primary key of the current issue acceptance
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a issue acceptance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance[] findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		long id, long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = findByPrimaryKey(id);

		

		try {
			

			IssueAcceptance[] array = new IssueAcceptance[3];

			array[0] = getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issueAcceptance, documentName, documentYear, requestState,
					orderByComparator, true);

			array[1] = issueAcceptance;

			array[2] = getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
					issueAcceptance, documentName, documentYear, requestState,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueAcceptance getByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState_PrevAndNext(
		 IssueAcceptance issueAcceptance, long documentName,
		int documentYear, int requestState,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEACCEPTANCE_WHERE);

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
			query.append(IssueAcceptanceModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("requestState", requestState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueAcceptance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueAcceptance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the issue acceptance where requestCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching issue acceptance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceException if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance findByRequestCode(String requestCode)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = fetchByRequestCode(requestCode);

		if (issueAcceptance == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchIssueAcceptanceException(msg.toString());
		}

		return issueAcceptance;
	}

	/**
	 * Returns the issue acceptance where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the issue acceptance where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching issue acceptance, or <code>null</code> if a matching issue acceptance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		IssueAcceptance issueAcceptance = null;
		if (issueAcceptance == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_ISSUEACCEPTANCE_WHERE);

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

			query.append(IssueAcceptanceModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(IssueAcceptance.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				issueAcceptance = (IssueAcceptance) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return issueAcceptance;
	}

	/**
	 * Returns all the issue acceptances.
	 *
	 * @return the issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue acceptances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @return the range of issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue acceptances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue acceptances
	 * @param end the upper bound of the range of issue acceptances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueAcceptance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueAcceptance> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ISSUEACCEPTANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUEACCEPTANCE.concat(IssueAcceptanceModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<IssueAcceptance>) queryFactory.getResultList(builder);
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
	 * Removes all the issue acceptances where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		for (IssueAcceptance issueAcceptance : findByfindIssuePortClearanceByDocumentYearAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(issueAcceptance);
		}
	}

	/**
	 * Removes all the issue acceptances where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		for (IssueAcceptance issueAcceptance : findByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
				documentName, documentYear, requestState)) {
			repository.delete(issueAcceptance);
		}
	}

	/**
	 * Removes the issue acceptance where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the issue acceptance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public IssueAcceptance removeByRequestCode(String requestCode)
		throws NoSuchIssueAcceptanceException, SystemException {
		IssueAcceptance issueAcceptance = findByRequestCode(requestCode);

		repository.delete(issueAcceptance);
			return issueAcceptance;
	}

	/**
	 * Removes all the issue acceptances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (IssueAcceptance issueAcceptance : findAll()) {
			repository.delete(issueAcceptance);
		}
	}

	/**
	 * Returns the number of issue acceptances where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssuePortClearanceByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ISSUEACCEPTANCE_WHERE);

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
	 * Returns the number of issue acceptances where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssuePortClearanceByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUEACCEPTANCE_WHERE);

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
	 * Returns the number of issue acceptances where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUEACCEPTANCE_WHERE);

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
	 * Returns the number of issue acceptances.
	 *
	 * @return the number of issue acceptances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUEACCEPTANCE).build();

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
	 * Initializes the issue acceptance persistence.
	 */
	private static final String _SQL_SELECT_ISSUEACCEPTANCE = "SELECT issueAcceptance FROM IssueAcceptance issueAcceptance";
	private static final String _SQL_SELECT_ISSUEACCEPTANCE_WHERE = "SELECT issueAcceptance FROM IssueAcceptance issueAcceptance WHERE ";
	private static final String _SQL_COUNT_ISSUEACCEPTANCE = "SELECT COUNT(issueAcceptance) FROM IssueAcceptance issueAcceptance";
	private static final String _SQL_COUNT_ISSUEACCEPTANCE_WHERE = "SELECT COUNT(issueAcceptance) FROM IssueAcceptance issueAcceptance WHERE ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"issueAcceptance.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"issueAcceptance.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2 =
		"issueAcceptance.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2 =
		"issueAcceptance.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPORTCLEARANCEBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2 =
		"issueAcceptance.requestState =:requestState";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "issueAcceptance.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "issueAcceptance.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(issueAcceptance.requestCode IS NULL OR issueAcceptance.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "issueAcceptance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssueAcceptance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssueAcceptance exists with the key {";
	

	
}
