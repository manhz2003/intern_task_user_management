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
import com.fds.nsw.nghiepvu.model.IssuePermissionForTransit;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.IssuePermissionForTransitRepository;
import com.fds.nsw.nghiepvu.modelImpl.IssuePermissionForTransitModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssuePermissionForTransitPersistenceImpl extends BasePersistence {
	@Autowired
	IssuePermissionForTransitRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssuePermissionForTransit> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IssuePermissionForTransitUtil} to access the issue permission for transit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public IssuePermissionForTransit create(long id) {
		IssuePermissionForTransit issuePermissionForTransit = new IssuePermissionForTransit();

		
		//issuePermissionForTransit.setPrimaryKey(id);

		return issuePermissionForTransit;
	}

	/**
	 * Removes the issue permission for transit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the issue permission for transit
	 * @return the issue permission for transit that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit remove(long id)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the issue permission for transit with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue permission for transit
	 * @return the issue permission for transit that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssuePermissionForTransit remove(Serializable primaryKey)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		

		try {
			

			IssuePermissionForTransit issuePermissionForTransit = findByPrimaryKey(primaryKey);

			if (issuePermissionForTransit == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssuePermissionForTransitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(issuePermissionForTransit);
			return issuePermissionForTransit;
		}
		catch (NoSuchIssuePermissionForTransitException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public IssuePermissionForTransit remove(IssuePermissionForTransit IssuePermissionForTransit) throws SystemException {
	removeImpl(IssuePermissionForTransit);
	return IssuePermissionForTransit;
}

protected IssuePermissionForTransit removeImpl(
		IssuePermissionForTransit issuePermissionForTransit)
		throws SystemException {
		try {
			repository.delete(issuePermissionForTransit);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issuePermissionForTransit;
	}

	
	public IssuePermissionForTransit updateImpl(
		com.fds.nsw.nghiepvu.model.IssuePermissionForTransit issuePermissionForTransit,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(issuePermissionForTransit);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issuePermissionForTransit;
	}

	
	public IssuePermissionForTransit findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue permission for transit with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException} if it could not be found.
	 *
	 * @param id the primary key of the issue permission for transit
	 * @return the issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByPrimaryKey(long id)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByPrimaryKey(id);

		if (issuePermissionForTransit == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchIssuePermissionForTransitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return issuePermissionForTransit;
	}

	/**
	 * Returns the issue permission for transit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue permission for transit
	 * @return the issue permission for transit, or <code>null</code> if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssuePermissionForTransit fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the issue permission for transit with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the issue permission for transit
	 * @return the issue permission for transit, or <code>null</code> if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByPrimaryKey(long id)
		throws SystemException {
		IssuePermissionForTransit issuePermissionForTransit = null;

		

		if (issuePermissionForTransit == null) {
			

			boolean hasException = false;

			try {
				

				Optional<IssuePermissionForTransit> optional = repository.findById(id);
				if (optional.isPresent()) {
					issuePermissionForTransit = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return issuePermissionForTransit;
	}

	/**
	 * Returns all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @return the range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePermissionForTransit> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<IssuePermissionForTransit>)queryFactory.getResultList(builder);
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
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePermissionForTransit> list = findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
				documentYear);

		List<IssuePermissionForTransit> list = findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue permission for transits before and after the current issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current issue permission for transit
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit[] findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByPrimaryKey(id);

		

		try {
			

			IssuePermissionForTransit[] array = new IssuePermissionForTransit[3];

			array[0] = getByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					orderByComparator, true);

			array[1] = issuePermissionForTransit;

			array[2] = getByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePermissionForTransit getByfindIssuePermissionForTransitByDocumentYearAndDocumentYear_PrevAndNext(
		 IssuePermissionForTransit issuePermissionForTransit,
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

		query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

		query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePermissionForTransit);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePermissionForTransit> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and requestState = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState);

		if (issuePermissionForTransit == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", requestState=");
			msg.append(requestState);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchIssuePermissionForTransitException(msg.toString());
		}

		return issuePermissionForTransit;
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and requestState = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return fetchByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName,
			documentYear, requestState, true);
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and requestState = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState,
		boolean retrieveFromCache) throws SystemException {
		IssuePermissionForTransit issuePermissionForTransit = null;
		if (issuePermissionForTransit == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(IssuePermissionForTransit.class).build();


				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				issuePermissionForTransit = (IssuePermissionForTransit) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return issuePermissionForTransit;
	}

	/**
	 * Returns all the issue permission for transits where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByrequestCode(String requestCode)
		throws SystemException {
		return findByrequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue permission for transits where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @return the range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByrequestCode(
		String requestCode, int start, int end) throws SystemException {
		return findByrequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue permission for transits where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByrequestCode(
		String requestCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePermissionForTransit> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
				query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<IssuePermissionForTransit>)queryFactory.getResultList(builder);
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
	 * Returns the first issue permission for transit in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByrequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByrequestCode_First(requestCode,
				orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the first issue permission for transit in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByrequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePermissionForTransit> list = findByrequestCode(requestCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByrequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByrequestCode_Last(requestCode,
				orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByrequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByrequestCode(requestCode);

		List<IssuePermissionForTransit> list = findByrequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue permission for transits before and after the current issue permission for transit in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current issue permission for transit
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit[] findByrequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByPrimaryKey(id);

		

		try {
			

			IssuePermissionForTransit[] array = new IssuePermissionForTransit[3];

			array[0] = getByrequestCode_PrevAndNext(
					issuePermissionForTransit, requestCode, orderByComparator,
					true);

			array[1] = issuePermissionForTransit;

			array[2] = getByrequestCode_PrevAndNext(
					issuePermissionForTransit, requestCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePermissionForTransit getByrequestCode_PrevAndNext(
		 IssuePermissionForTransit issuePermissionForTransit,
		String requestCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePermissionForTransit);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePermissionForTransit> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentNameAndDocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @return the range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentNameAndDocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findBydocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePermissionForTransit> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<IssuePermissionForTransit>)queryFactory.getResultList(builder);
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
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findBydocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchBydocumentNameAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchBydocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePermissionForTransit> list = findBydocumentNameAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findBydocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchBydocumentNameAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchBydocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentNameAndDocumentYear(documentName,
				documentYear);

		List<IssuePermissionForTransit> list = findBydocumentNameAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue permission for transits before and after the current issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current issue permission for transit
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit[] findBydocumentNameAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByPrimaryKey(id);

		

		try {
			

			IssuePermissionForTransit[] array = new IssuePermissionForTransit[3];

			array[0] = getBydocumentNameAndDocumentYear_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					orderByComparator, true);

			array[1] = issuePermissionForTransit;

			array[2] = getBydocumentNameAndDocumentYear_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePermissionForTransit getBydocumentNameAndDocumentYear_PrevAndNext(
		 IssuePermissionForTransit issuePermissionForTransit,
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

		query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePermissionForTransit);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePermissionForTransit> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and versionNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByDocumentNameAndDocumentYearAndVersionNo(documentName,
				documentYear, versionNo);

		if (issuePermissionForTransit == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", versionNo=");
			msg.append(versionNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchIssuePermissionForTransitException(msg.toString());
		}

		return issuePermissionForTransit;
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and versionNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws SystemException {
		return fetchByDocumentNameAndDocumentYearAndVersionNo(documentName,
			documentYear, versionNo, true);
	}

	/**
	 * Returns the issue permission for transit where documentName = &#63; and documentYear = &#63; and versionNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo,
		boolean retrieveFromCache) throws SystemException {
		IssuePermissionForTransit issuePermissionForTransit = null;
		if (issuePermissionForTransit == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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

			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(IssuePermissionForTransit.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (versionNo != null) {
					builder.appendNamedParameterMap("versionNo", versionNo);
				}

				issuePermissionForTransit = (IssuePermissionForTransit) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return issuePermissionForTransit;
	}

	/**
	 * Returns all the issue permission for transits where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @return the matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByF_LAST_numberPortClearance(
		long documentName, int documentYear, String numberPermissionForTransit)
		throws SystemException {
		return findByF_LAST_numberPortClearance(documentName, documentYear,
			numberPermissionForTransit, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the issue permission for transits where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @return the range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByF_LAST_numberPortClearance(
		long documentName, int documentYear, String numberPermissionForTransit,
		int start, int end) throws SystemException {
		return findByF_LAST_numberPortClearance(documentName, documentYear,
			numberPermissionForTransit, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue permission for transits where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findByF_LAST_numberPortClearance(
		long documentName, int documentYear, String numberPermissionForTransit,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssuePermissionForTransit> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTYEAR_2);

			if (numberPermissionForTransit == null) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_1);
			}
			else {
				if (numberPermissionForTransit.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (numberPermissionForTransit != null) {
					builder.appendNamedParameterMap("numberPermissionForTransit", numberPermissionForTransit);
				}

				list = (List<IssuePermissionForTransit>)queryFactory.getResultList(builder);
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
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByF_LAST_numberPortClearance_First(
		long documentName, int documentYear, String numberPermissionForTransit,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByF_LAST_numberPortClearance_First(documentName,
				documentYear, numberPermissionForTransit, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", numberPermissionForTransit=");
		msg.append(numberPermissionForTransit);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the first issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByF_LAST_numberPortClearance_First(
		long documentName, int documentYear, String numberPermissionForTransit,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePermissionForTransit> list = findByF_LAST_numberPortClearance(documentName,
				documentYear, numberPermissionForTransit, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit findByF_LAST_numberPortClearance_Last(
		long documentName, int documentYear, String numberPermissionForTransit,
		OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = fetchByF_LAST_numberPortClearance_Last(documentName,
				documentYear, numberPermissionForTransit, orderByComparator);

		if (issuePermissionForTransit != null) {
			return issuePermissionForTransit;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", numberPermissionForTransit=");
		msg.append(numberPermissionForTransit);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssuePermissionForTransitException(msg.toString());
	}

	/**
	 * Returns the last issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue permission for transit, or <code>null</code> if a matching issue permission for transit could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit fetchByF_LAST_numberPortClearance_Last(
		long documentName, int documentYear, String numberPermissionForTransit,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_LAST_numberPortClearance(documentName,
				documentYear, numberPermissionForTransit);

		List<IssuePermissionForTransit> list = findByF_LAST_numberPortClearance(documentName,
				documentYear, numberPermissionForTransit, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue permission for transits before and after the current issue permission for transit in the ordered set where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param id the primary key of the current issue permission for transit
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue permission for transit
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssuePermissionForTransitException if a issue permission for transit with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit[] findByF_LAST_numberPortClearance_PrevAndNext(
		long id, long documentName, int documentYear,
		String numberPermissionForTransit, OrderByComparator orderByComparator)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByPrimaryKey(id);

		

		try {
			

			IssuePermissionForTransit[] array = new IssuePermissionForTransit[3];

			array[0] = getByF_LAST_numberPortClearance_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					numberPermissionForTransit, orderByComparator, true);

			array[1] = issuePermissionForTransit;

			array[2] = getByF_LAST_numberPortClearance_PrevAndNext(
					issuePermissionForTransit, documentName, documentYear,
					numberPermissionForTransit, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssuePermissionForTransit getByF_LAST_numberPortClearance_PrevAndNext(
		 IssuePermissionForTransit issuePermissionForTransit,
		long documentName, int documentYear, String numberPermissionForTransit,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE);

		query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTYEAR_2);

		if (numberPermissionForTransit == null) {
			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_1);
		}
		else {
			if (numberPermissionForTransit.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_2);
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
			query.append(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (numberPermissionForTransit != null) {
			builder.appendNamedParameterMap("numberPermissionForTransit", numberPermissionForTransit);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issuePermissionForTransit);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssuePermissionForTransit> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue permission for transits.
	 *
	 * @return the issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue permission for transits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @return the range of issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue permission for transits.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue permission for transits
	 * @param end the upper bound of the range of issue permission for transits (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssuePermissionForTransit> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssuePermissionForTransit> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ISSUEPERMISSIONFORTRANSIT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUEPERMISSIONFORTRANSIT.concat(IssuePermissionForTransitModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<IssuePermissionForTransit>) queryFactory.getResultList(builder);
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
	 * Removes all the issue permission for transits where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		for (IssuePermissionForTransit issuePermissionForTransit : findByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(issuePermissionForTransit);
		}
	}

	/**
	 * Removes the issue permission for transit where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the issue permission for transit that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit removeByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(documentName,
				documentYear, requestState);

		repository.delete(issuePermissionForTransit);
			return issuePermissionForTransit;
	}

	/**
	 * Removes all the issue permission for transits where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByrequestCode(String requestCode)
		throws SystemException {
		for (IssuePermissionForTransit issuePermissionForTransit : findByrequestCode(
				requestCode)) {
			repository.delete(issuePermissionForTransit);
		}
	}

	/**
	 * Removes all the issue permission for transits where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (IssuePermissionForTransit issuePermissionForTransit : findBydocumentNameAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(issuePermissionForTransit);
		}
	}

	/**
	 * Removes the issue permission for transit where documentName = &#63; and documentYear = &#63; and versionNo = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the issue permission for transit that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public IssuePermissionForTransit removeByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws NoSuchIssuePermissionForTransitException, SystemException {
		IssuePermissionForTransit issuePermissionForTransit = findByDocumentNameAndDocumentYearAndVersionNo(documentName,
				documentYear, versionNo);

		repository.delete(issuePermissionForTransit);
			return issuePermissionForTransit;
	}

	/**
	 * Removes all the issue permission for transits where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_LAST_numberPortClearance(long documentName,
		int documentYear, String numberPermissionForTransit)
		throws SystemException {
		for (IssuePermissionForTransit issuePermissionForTransit : findByF_LAST_numberPortClearance(
				documentName, documentYear, numberPermissionForTransit)) {
			repository.delete(issuePermissionForTransit);
		}
	}

	/**
	 * Removes all the issue permission for transits from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (IssuePermissionForTransit issuePermissionForTransit : findAll()) {
			repository.delete(issuePermissionForTransit);
		}
	}

	/**
	 * Returns the number of issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfindIssuePermissionForTransitByDocumentYearAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
	 * Returns the number of issue permission for transits where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByIssuePermissionForTransitByDocumentYearAndDocumentYearAndRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2);

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
	 * Returns the number of issue permission for transits where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByrequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
	 * Returns the number of issue permission for transits where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
	 * Returns the number of issue permission for transits where documentName = &#63; and documentYear = &#63; and versionNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param versionNo the version no
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndDocumentYearAndVersionNo(
		long documentName, int documentYear, String versionNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

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
	 * Returns the number of issue permission for transits where documentName = &#63; and documentYear = &#63; and numberPermissionForTransit = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param numberPermissionForTransit the number permission for transit
	 * @return the number of matching issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_LAST_numberPortClearance(long documentName,
		int documentYear, String numberPermissionForTransit)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE);

			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTYEAR_2);

			if (numberPermissionForTransit == null) {
				query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_1);
			}
			else {
				if (numberPermissionForTransit.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (numberPermissionForTransit != null) {
					builder.appendNamedParameterMap("numberPermissionForTransit", numberPermissionForTransit);
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
	 * Returns the number of issue permission for transits.
	 *
	 * @return the number of issue permission for transits
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUEPERMISSIONFORTRANSIT).build();

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
	 * Initializes the issue permission for transit persistence.
	 */
	private static final String _SQL_SELECT_ISSUEPERMISSIONFORTRANSIT = "SELECT issuePermissionForTransit FROM IssuePermissionForTransit issuePermissionForTransit";
	private static final String _SQL_SELECT_ISSUEPERMISSIONFORTRANSIT_WHERE = "SELECT issuePermissionForTransit FROM IssuePermissionForTransit issuePermissionForTransit WHERE ";
	private static final String _SQL_COUNT_ISSUEPERMISSIONFORTRANSIT = "SELECT COUNT(issuePermissionForTransit) FROM IssuePermissionForTransit issuePermissionForTransit";
	private static final String _SQL_COUNT_ISSUEPERMISSIONFORTRANSIT_WHERE = "SELECT COUNT(issuePermissionForTransit) FROM IssuePermissionForTransit issuePermissionForTransit WHERE ";
	private static final String _FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"issuePermissionForTransit.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FINDISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"issuePermissionForTransit.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTNAME_2 =
		"issuePermissionForTransit.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_DOCUMENTYEAR_2 =
		"issuePermissionForTransit.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_ISSUEPERMISSIONFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEARANDREQUESTSTATE_REQUESTSTATE_2 =
		"issuePermissionForTransit.requestState =:requestState";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "issuePermissionForTransit.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "issuePermissionForTransit.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(issuePermissionForTransit.requestCode IS NULL OR issuePermissionForTransit.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"issuePermissionForTransit.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"issuePermissionForTransit.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTNAME_2 =
		"issuePermissionForTransit.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_DOCUMENTYEAR_2 =
		"issuePermissionForTransit.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_1 =
		"issuePermissionForTransit.versionNo IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_2 =
		"issuePermissionForTransit.versionNo =:versionNo";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDVERSIONNO_VERSIONNO_3 =
		"(issuePermissionForTransit.versionNo IS NULL OR issuePermissionForTransit.versionNo =:versionNo)";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTNAME_2 =
		"issuePermissionForTransit.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_DOCUMENTYEAR_2 =
		"issuePermissionForTransit.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_1 =
		"issuePermissionForTransit.numberPermissionForTransit IS NULL";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_2 =
		"issuePermissionForTransit.numberPermissionForTransit =:numberPermissionForTransit";
	private static final String _FINDER_COLUMN_F_LAST_NUMBERPORTCLEARANCE_NUMBERPERMISSIONFORTRANSIT_3 =
		"(issuePermissionForTransit.numberPermissionForTransit IS NULL OR issuePermissionForTransit.numberPermissionForTransit =:numberPermissionForTransit)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "issuePermissionForTransit.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssuePermissionForTransit exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssuePermissionForTransit exists with the key {";
	

	
}
