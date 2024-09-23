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
////import com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.IssueAcceptanceForTransitRepository;
////import com.fds.nsw.nghiepvu.modelImpl.IssueAcceptanceForTransitModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class IssueAcceptanceForTransitPersistenceImpl extends BasePersistence {
////	@Autowired
////	IssueAcceptanceForTransitRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<IssueAcceptanceForTransit> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link IssueAcceptanceForTransitUtil} to access the issue acceptance for transit persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public IssueAcceptanceForTransit create(long id) {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = new IssueAcceptanceForTransit();
////
////
////		//issueAcceptanceForTransit.setPrimaryKey(id);
////
////		return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Removes the issue acceptance for transit with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the issue acceptance for transit
////	 * @return the issue acceptance for transit that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException if a issue acceptance for transit with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit remove(long id)
////		throws NoSuchIssueAcceptanceForTransitException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the issue acceptance for transit with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the issue acceptance for transit
////	 * @return the issue acceptance for transit that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException if a issue acceptance for transit with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public IssueAcceptanceForTransit remove(Serializable primaryKey)
////		throws NoSuchIssueAcceptanceForTransitException, SystemException {
////
////
////		try {
////
////
////			IssueAcceptanceForTransit issueAcceptanceForTransit = findByPrimaryKey(primaryKey);
////
////			if (issueAcceptanceForTransit == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchIssueAcceptanceForTransitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(issueAcceptanceForTransit);
////			return issueAcceptanceForTransit;
////		}
////		catch (NoSuchIssueAcceptanceForTransitException nsee) {
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
////	public IssueAcceptanceForTransit remove(IssueAcceptanceForTransit IssueAcceptanceForTransit) throws SystemException {
//	removeImpl(IssueAcceptanceForTransit);
//	return IssueAcceptanceForTransit;
//}
//
//protected IssueAcceptanceForTransit removeImpl(
////		IssueAcceptanceForTransit issueAcceptanceForTransit)
////		throws SystemException {
////		try {
////			repository.delete(issueAcceptanceForTransit);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return issueAcceptanceForTransit;
////	}
////
////
////	public IssueAcceptanceForTransit updateImpl(
////		com.fds.nsw.nghiepvu.model.IssueAcceptanceForTransit issueAcceptanceForTransit,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(issueAcceptanceForTransit);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return issueAcceptanceForTransit;
////	}
////
////
////	public IssueAcceptanceForTransit findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the issue acceptance for transit with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException} if it could not be found.
////	 *
////	 * @param id the primary key of the issue acceptance for transit
////	 * @return the issue acceptance for transit
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException if a issue acceptance for transit with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit findByPrimaryKey(long id)
////		throws NoSuchIssueAcceptanceForTransitException, SystemException {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = fetchByPrimaryKey(id);
////
////		if (issueAcceptanceForTransit == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchIssueAcceptanceForTransitException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Returns the issue acceptance for transit with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the issue acceptance for transit
////	 * @return the issue acceptance for transit, or <code>null</code> if a issue acceptance for transit with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public IssueAcceptanceForTransit fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the issue acceptance for transit with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the issue acceptance for transit
////	 * @return the issue acceptance for transit, or <code>null</code> if a issue acceptance for transit with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit fetchByPrimaryKey(long id)
////		throws SystemException {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = null;
////
////
////
////		if (issueAcceptanceForTransit == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<IssueAcceptanceForTransit> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					issueAcceptanceForTransit = optional.get();
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
////		return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Returns the issue acceptance for transit where documentName = &#63; and documentYear = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException} if it could not be found.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the matching issue acceptance for transit
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueAcceptanceForTransitException if a matching issue acceptance for transit could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit findByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
////		long documentName, int documentYear)
////		throws NoSuchIssueAcceptanceForTransitException, SystemException {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = fetchByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(documentName,
////				documentYear);
////
////		if (issueAcceptanceForTransit == null) {
////			StringBundler msg = new StringBundler(6);
////
////			msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////			msg.append("documentName=");
////			msg.append(documentName);
////
////			msg.append(", documentYear=");
////			msg.append(documentYear);
////
////			msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////			if (log.isWarnEnabled()) {
////				log.warn(msg.toString());
////			}
////
////			throw new NoSuchIssueAcceptanceForTransitException(msg.toString());
////		}
////
////		return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Returns the issue acceptance for transit where documentName = &#63; and documentYear = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the matching issue acceptance for transit, or <code>null</code> if a matching issue acceptance for transit could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit fetchByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
////		long documentName, int documentYear) throws SystemException {
////		return fetchByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(documentName,
////			documentYear, true);
////	}
////
////	/**
////	 * Returns the issue acceptance for transit where documentName = &#63; and documentYear = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @param retrieveFromCache whether to use the finder cache
////	 * @return the matching issue acceptance for transit, or <code>null</code> if a matching issue acceptance for transit could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit fetchByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
////		long documentName, int documentYear, boolean retrieveFromCache)
////		throws SystemException {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = null;
////		if (issueAcceptanceForTransit == null) {
////			StringBundler query = new StringBundler(4);
////
////			query.append(_SQL_SELECT_ISSUEACCEPTANCEFORTRANSIT_WHERE);
////
////			query.append(_FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);
////
////			query.append(IssueAcceptanceForTransitModelImpl.ORDER_BY_JPQL);
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
////				issueAcceptanceForTransit = (IssueAcceptanceForTransit) queryFactory.getSingleResult(builder);
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
////		return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Returns all the issue acceptance for transits.
////	 *
////	 * @return the issue acceptance for transits
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<IssueAcceptanceForTransit> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the issue acceptance for transits.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of issue acceptance for transits
////	 * @param end the upper bound of the range of issue acceptance for transits (not inclusive)
////	 * @return the range of issue acceptance for transits
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<IssueAcceptanceForTransit> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the issue acceptance for transits.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of issue acceptance for transits
////	 * @param end the upper bound of the range of issue acceptance for transits (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of issue acceptance for transits
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<IssueAcceptanceForTransit> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<IssueAcceptanceForTransit> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_ISSUEACCEPTANCEFORTRANSIT);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_ISSUEACCEPTANCEFORTRANSIT.concat(IssueAcceptanceForTransitModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<IssueAcceptanceForTransit>) queryFactory.getResultList(builder);
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
////	 * Removes the issue acceptance for transit where documentName = &#63; and documentYear = &#63; from the database.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the issue acceptance for transit that was removed
////	 * @throws SystemException if a system exception occurred
////	 */
////	public IssueAcceptanceForTransit removeByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
////		long documentName, int documentYear)
////		throws NoSuchIssueAcceptanceForTransitException, SystemException {
////		IssueAcceptanceForTransit issueAcceptanceForTransit = findByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(documentName,
////				documentYear);
////
////		repository.delete(issueAcceptanceForTransit);
////			return issueAcceptanceForTransit;
////	}
////
////	/**
////	 * Removes all the issue acceptance for transits from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (IssueAcceptanceForTransit issueAcceptanceForTransit : findAll()) {
////			repository.delete(issueAcceptanceForTransit);
////		}
////	}
////
////	/**
////	 * Returns the number of issue acceptance for transits where documentName = &#63; and documentYear = &#63;.
////	 *
////	 * @param documentName the document name
////	 * @param documentYear the document year
////	 * @return the number of matching issue acceptance for transits
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByfindIssueAcceptanceForTransitByDocumentYearAndDocumentYear(
////		long documentName, int documentYear) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_COUNT_ISSUEACCEPTANCEFORTRANSIT_WHERE);
////
////			query.append(_FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2);
////
////			query.append(_FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2);
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
////	 * Returns the number of issue acceptance for transits.
////	 *
////	 * @return the number of issue acceptance for transits
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUEACCEPTANCEFORTRANSIT).build();
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
////	 * Initializes the issue acceptance for transit persistence.
////	 */
////	private static final String _SQL_SELECT_ISSUEACCEPTANCEFORTRANSIT = "SELECT issueAcceptanceForTransit FROM IssueAcceptanceForTransit issueAcceptanceForTransit";
////	private static final String _SQL_SELECT_ISSUEACCEPTANCEFORTRANSIT_WHERE = "SELECT issueAcceptanceForTransit FROM IssueAcceptanceForTransit issueAcceptanceForTransit WHERE ";
////	private static final String _SQL_COUNT_ISSUEACCEPTANCEFORTRANSIT = "SELECT COUNT(issueAcceptanceForTransit) FROM IssueAcceptanceForTransit issueAcceptanceForTransit";
////	private static final String _SQL_COUNT_ISSUEACCEPTANCEFORTRANSIT_WHERE = "SELECT COUNT(issueAcceptanceForTransit) FROM IssueAcceptanceForTransit issueAcceptanceForTransit WHERE ";
////	private static final String _FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTNAME_2 =
////		"issueAcceptanceForTransit.documentName =:documentName AND ";
////	private static final String _FINDER_COLUMN_FINDISSUEACCEPTANCEFORTRANSITBYDOCUMENTYEARANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
////		"issueAcceptanceForTransit.documentYear =:documentYear";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "issueAcceptanceForTransit.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssueAcceptanceForTransit exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssueAcceptanceForTransit exists with the key {";
////
////
////
////}
