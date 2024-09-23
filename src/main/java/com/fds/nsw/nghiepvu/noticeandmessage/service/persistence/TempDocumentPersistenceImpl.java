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
import com.fds.nsw.nghiepvu.model.TempDocument;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempDocumentRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempDocumentModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempDocumentPersistenceImpl extends BasePersistence {
	@Autowired
	TempDocumentRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempDocument> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempDocumentUtil} to access the temp document persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempDocument create(long id) {
		TempDocument tempDocument = new TempDocument();

		
		//tempDocument.setPrimaryKey(id);

		return tempDocument;
	}

	/**
	 * Removes the temp document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp document
	 * @return the temp document that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument remove(long id)
		throws NoSuchTempDocumentException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp document with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp document
	 * @return the temp document that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempDocument remove(Serializable primaryKey)
		throws NoSuchTempDocumentException, SystemException {
		

		try {
			

			TempDocument tempDocument = findByPrimaryKey(primaryKey);

			if (tempDocument == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempDocumentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempDocument);
			return tempDocument;
		}
		catch (NoSuchTempDocumentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempDocument remove(TempDocument TempDocument) throws SystemException {
	removeImpl(TempDocument);
	return TempDocument;
}

protected TempDocument removeImpl(TempDocument tempDocument)
		throws SystemException {
		try {
			repository.delete(tempDocument);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempDocument;
	}

	
	public TempDocument updateImpl(
		com.fds.nsw.nghiepvu.model.TempDocument tempDocument,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempDocument);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempDocument;
	}

	
	public TempDocument findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp document with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempDocumentException} if it could not be found.
	 *
	 * @param id the primary key of the temp document
	 * @return the temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByPrimaryKey(long id)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByPrimaryKey(id);

		if (tempDocument == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempDocumentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempDocument;
	}

	/**
	 * Returns the temp document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp document
	 * @return the temp document, or <code>null</code> if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempDocument fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp document with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp document
	 * @return the temp document, or <code>null</code> if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByPrimaryKey(long id) throws SystemException {
		TempDocument tempDocument = null;

		

		if (tempDocument == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempDocument> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempDocument = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempDocument;
	}

	/**
	 * Returns all the temp documents where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByTemDocumentByDocumentNameAndDocumentYear(documentName,
			documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp documents where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByTemDocumentByDocumentNameAndDocumentYear(documentName,
			documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp documents where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempDocument>)queryFactory.getResultList(builder);
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
	 * Returns the first temp document in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTemDocumentByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTemDocumentByDocumentNameAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the first temp document in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTemDocumentByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDocument> list = findByTemDocumentByDocumentNameAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp document in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTemDocumentByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTemDocumentByDocumentNameAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the last temp document in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTemDocumentByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByTemDocumentByDocumentNameAndDocumentYear(documentName,
				documentYear);

		List<TempDocument> list = findByTemDocumentByDocumentNameAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp documents before and after the current temp document in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp document
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument[] findByTemDocumentByDocumentNameAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByPrimaryKey(id);

		

		try {
			

			TempDocument[] array = new TempDocument[3];

			array[0] = getByTemDocumentByDocumentNameAndDocumentYear_PrevAndNext(
					tempDocument, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempDocument;

			array[2] = getByTemDocumentByDocumentNameAndDocumentYear_PrevAndNext(
					tempDocument, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempDocument getByTemDocumentByDocumentNameAndDocumentYear_PrevAndNext(
		 TempDocument tempDocument, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

		query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDocument);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDocument> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp documents where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return findByTemDocumentByDocumentNameAndDocumentYearRequestState(documentName,
			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp documents where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYearRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end) throws SystemException {
		return findByTemDocumentByDocumentNameAndDocumentYearRequestState(documentName,
			documentYear, requestState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp documents where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTemDocumentByDocumentNameAndDocumentYearRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				list = (List<TempDocument>)queryFactory.getResultList(builder);
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
	 * Returns the first temp document in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTemDocumentByDocumentNameAndDocumentYearRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTemDocumentByDocumentNameAndDocumentYearRequestState_First(documentName,
				documentYear, requestState, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
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

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the first temp document in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTemDocumentByDocumentNameAndDocumentYearRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = findByTemDocumentByDocumentNameAndDocumentYearRequestState(documentName,
				documentYear, requestState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp document in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTemDocumentByDocumentNameAndDocumentYearRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTemDocumentByDocumentNameAndDocumentYearRequestState_Last(documentName,
				documentYear, requestState, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
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

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the last temp document in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTemDocumentByDocumentNameAndDocumentYearRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTemDocumentByDocumentNameAndDocumentYearRequestState(documentName,
				documentYear, requestState);

		List<TempDocument> list = findByTemDocumentByDocumentNameAndDocumentYearRequestState(documentName,
				documentYear, requestState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp documents before and after the current temp document in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param id the primary key of the current temp document
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument[] findByTemDocumentByDocumentNameAndDocumentYearRequestState_PrevAndNext(
		long id, long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByPrimaryKey(id);

		

		try {
			

			TempDocument[] array = new TempDocument[3];

			array[0] = getByTemDocumentByDocumentNameAndDocumentYearRequestState_PrevAndNext(
					tempDocument, documentName, documentYear, requestState,
					orderByComparator, true);

			array[1] = tempDocument;

			array[2] = getByTemDocumentByDocumentNameAndDocumentYearRequestState_PrevAndNext(
					tempDocument, documentName, documentYear, requestState,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempDocument getByTemDocumentByDocumentNameAndDocumentYearRequestState_PrevAndNext(
		 TempDocument tempDocument, long documentName,
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

		query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

		query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

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
			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("requestState", requestState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDocument);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDocument> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the temp document where documentName = &#63; and documentYear = &#63; and documentTypeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempDocumentException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByF_B_N_Y_TYPE(long documentName, int documentYear,
		String documentTypeCode)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByF_B_N_Y_TYPE(documentName,
				documentYear, documentTypeCode);

		if (tempDocument == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", documentTypeCode=");
			msg.append(documentTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempDocumentException(msg.toString());
		}

		return tempDocument;
	}

	/**
	 * Returns the temp document where documentName = &#63; and documentYear = &#63; and documentTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByF_B_N_Y_TYPE(long documentName,
		int documentYear, String documentTypeCode) throws SystemException {
		return fetchByF_B_N_Y_TYPE(documentName, documentYear,
			documentTypeCode, true);
	}

	/**
	 * Returns the temp document where documentName = &#63; and documentYear = &#63; and documentTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByF_B_N_Y_TYPE(long documentName,
		int documentYear, String documentTypeCode, boolean retrieveFromCache)
		throws SystemException {
		TempDocument tempDocument = null;
		if (tempDocument == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTYEAR_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_2);
				}
			}

			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempDocument.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				tempDocument = (TempDocument) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return tempDocument;
	}

	/**
	 * Returns all the temp documents where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp documents where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByRequestCode(String requestCode, int start,
		int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp documents where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByRequestCode(String requestCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

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
				query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempDocument>)queryFactory.getResultList(builder);
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
	 * Returns the first temp document in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the first temp document in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp document in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the last temp document in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempDocument> list = findByRequestCode(requestCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp documents before and after the current temp document in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp document
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByPrimaryKey(id);

		

		try {
			

			TempDocument[] array = new TempDocument[3];

			array[0] = getByRequestCode_PrevAndNext(tempDocument,
					requestCode, orderByComparator, true);

			array[1] = tempDocument;

			array[2] = getByRequestCode_PrevAndNext(tempDocument,
					requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempDocument getByRequestCode_PrevAndNext(
		TempDocument tempDocument, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

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
			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDocument);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDocument> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp documents where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTempDocumentByShipNameSMSAndDocumentTypeCode(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode) throws SystemException {
		return findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
			callSign, shipTypeCode, documentYear, documentTypeCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp documents where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTempDocumentByShipNameSMSAndDocumentTypeCode(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode, int start, int end)
		throws SystemException {
		return findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
			callSign, shipTypeCode, documentYear, documentTypeCode, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the temp documents where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByTempDocumentByShipNameSMSAndDocumentTypeCode(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(7 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(7);
			}

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

			if (shipName == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_1);
			}
			else {
				if (shipName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_2);
				}
			}

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_2);
				}
			}

			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTYEAR_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipName != null) {
					builder.appendNamedParameterMap("shipName", shipName);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				list = (List<TempDocument>)queryFactory.getResultList(builder);
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
	 * Returns the first temp document in the ordered set where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTempDocumentByShipNameSMSAndDocumentTypeCode_First(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTempDocumentByShipNameSMSAndDocumentTypeCode_First(shipName,
				callSign, shipTypeCode, documentYear, documentTypeCode,
				orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipName=");
		msg.append(shipName);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(", shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the first temp document in the ordered set where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTempDocumentByShipNameSMSAndDocumentTypeCode_First(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
				callSign, shipTypeCode, documentYear, documentTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp document in the ordered set where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByTempDocumentByShipNameSMSAndDocumentTypeCode_Last(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByTempDocumentByShipNameSMSAndDocumentTypeCode_Last(shipName,
				callSign, shipTypeCode, documentYear, documentTypeCode,
				orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(12);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipName=");
		msg.append(shipName);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(", shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the last temp document in the ordered set where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByTempDocumentByShipNameSMSAndDocumentTypeCode_Last(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
				callSign, shipTypeCode, documentYear, documentTypeCode);

		List<TempDocument> list = findByTempDocumentByShipNameSMSAndDocumentTypeCode(shipName,
				callSign, shipTypeCode, documentYear, documentTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp documents before and after the current temp document in the ordered set where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param id the primary key of the current temp document
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument[] findByTempDocumentByShipNameSMSAndDocumentTypeCode_PrevAndNext(
		long id, String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByPrimaryKey(id);

		

		try {
			

			TempDocument[] array = new TempDocument[3];

			array[0] = getByTempDocumentByShipNameSMSAndDocumentTypeCode_PrevAndNext(
					tempDocument, shipName, callSign, shipTypeCode,
					documentYear, documentTypeCode, orderByComparator, true);

			array[1] = tempDocument;

			array[2] = getByTempDocumentByShipNameSMSAndDocumentTypeCode_PrevAndNext(
					tempDocument, shipName, callSign, shipTypeCode,
					documentYear, documentTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempDocument getByTempDocumentByShipNameSMSAndDocumentTypeCode_PrevAndNext(
		 TempDocument tempDocument, String shipName,
		String callSign, String shipTypeCode, int documentYear,
		String documentTypeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

		if (shipName == null) {
			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_1);
		}
		else {
			if (shipName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_2);
			}
		}

		if (callSign == null) {
			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_1);
		}
		else {
			if (callSign.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_2);
			}
		}

		if (shipTypeCode == null) {
			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_1);
		}
		else {
			if (shipTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_2);
			}
		}

		query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTYEAR_2);

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
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
			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipName != null) {
			builder.appendNamedParameterMap("shipName", shipName);
		}

		if (callSign != null) {
			builder.appendNamedParameterMap("callSign", callSign);
		}

		if (shipTypeCode != null) {
			builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
		}

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDocument);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDocument> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp documents where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @return the matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByF_documentTypeCode_maritimeCode(
		String documentTypeCode, String maritimeCode) throws SystemException {
		return findByF_documentTypeCode_maritimeCode(documentTypeCode,
			maritimeCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp documents where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByF_documentTypeCode_maritimeCode(
		String documentTypeCode, String maritimeCode, int start, int end)
		throws SystemException {
		return findByF_documentTypeCode_maritimeCode(documentTypeCode,
			maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp documents where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findByF_documentTypeCode_maritimeCode(
		String documentTypeCode, String maritimeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_2);
				}
			}

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<TempDocument>)queryFactory.getResultList(builder);
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
	 * Returns the first temp document in the ordered set where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByF_documentTypeCode_maritimeCode_First(
		String documentTypeCode, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByF_documentTypeCode_maritimeCode_First(documentTypeCode,
				maritimeCode, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(", maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the first temp document in the ordered set where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByF_documentTypeCode_maritimeCode_First(
		String documentTypeCode, String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = findByF_documentTypeCode_maritimeCode(documentTypeCode,
				maritimeCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp document in the ordered set where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument findByF_documentTypeCode_maritimeCode_Last(
		String documentTypeCode, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = fetchByF_documentTypeCode_maritimeCode_Last(documentTypeCode,
				maritimeCode, orderByComparator);

		if (tempDocument != null) {
			return tempDocument;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(", maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDocumentException(msg.toString());
	}

	/**
	 * Returns the last temp document in the ordered set where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp document, or <code>null</code> if a matching temp document could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument fetchByF_documentTypeCode_maritimeCode_Last(
		String documentTypeCode, String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_documentTypeCode_maritimeCode(documentTypeCode,
				maritimeCode);

		List<TempDocument> list = findByF_documentTypeCode_maritimeCode(documentTypeCode,
				maritimeCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp documents before and after the current temp document in the ordered set where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current temp document
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp document
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempDocumentException if a temp document with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument[] findByF_documentTypeCode_maritimeCode_PrevAndNext(
		long id, String documentTypeCode, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByPrimaryKey(id);

		

		try {
			

			TempDocument[] array = new TempDocument[3];

			array[0] = getByF_documentTypeCode_maritimeCode_PrevAndNext(
					tempDocument, documentTypeCode, maritimeCode,
					orderByComparator, true);

			array[1] = tempDocument;

			array[2] = getByF_documentTypeCode_maritimeCode_PrevAndNext(
					tempDocument, documentTypeCode, maritimeCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempDocument getByF_documentTypeCode_maritimeCode_PrevAndNext(
		 TempDocument tempDocument, String documentTypeCode,
		String maritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDOCUMENT_WHERE);

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_2);
			}
		}

		if (maritimeCode == null) {
			query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (maritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_2);
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
			query.append(TempDocumentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDocument);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDocument> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp documents.
	 *
	 * @return the temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @return the range of temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp documents.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp documents
	 * @param end the upper bound of the range of temp documents (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDocument> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDocument> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPDOCUMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPDOCUMENT.concat(TempDocumentModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempDocument>) queryFactory.getResultList(builder);
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
	 * Removes all the temp documents where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		for (TempDocument tempDocument : findByTemDocumentByDocumentNameAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Removes all the temp documents where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTemDocumentByDocumentNameAndDocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		for (TempDocument tempDocument : findByTemDocumentByDocumentNameAndDocumentYearRequestState(
				documentName, documentYear, requestState)) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Removes the temp document where documentName = &#63; and documentYear = &#63; and documentTypeCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the temp document that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempDocument removeByF_B_N_Y_TYPE(long documentName,
		int documentYear, String documentTypeCode)
		throws NoSuchTempDocumentException, SystemException {
		TempDocument tempDocument = findByF_B_N_Y_TYPE(documentName,
				documentYear, documentTypeCode);

		repository.delete(tempDocument);
			return tempDocument;
	}

	/**
	 * Removes all the temp documents where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempDocument tempDocument : findByRequestCode(requestCode)) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Removes all the temp documents where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63; from the database.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByTempDocumentByShipNameSMSAndDocumentTypeCode(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode) throws SystemException {
		for (TempDocument tempDocument : findByTempDocumentByShipNameSMSAndDocumentTypeCode(
				shipName, callSign, shipTypeCode, documentYear, documentTypeCode)) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Removes all the temp documents where documentTypeCode = &#63; and maritimeCode = &#63; from the database.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_documentTypeCode_maritimeCode(
		String documentTypeCode, String maritimeCode) throws SystemException {
		for (TempDocument tempDocument : findByF_documentTypeCode_maritimeCode(
				documentTypeCode, maritimeCode)) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Removes all the temp documents from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempDocument tempDocument : findAll()) {
			repository.delete(tempDocument);
		}
	}

	/**
	 * Returns the number of temp documents where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTemDocumentByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
	 * Returns the number of temp documents where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTemDocumentByDocumentNameAndDocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

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
	 * Returns the number of temp documents where documentName = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_B_N_Y_TYPE(long documentName, int documentYear,
		String documentTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

			query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTYEAR_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
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
	 * Returns the number of temp documents where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

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
	 * Returns the number of temp documents where shipName = &#63; and callSign = &#63; and shipTypeCode = &#63; and documentYear = &#63; and documentTypeCode = &#63;.
	 *
	 * @param shipName the ship name
	 * @param callSign the call sign
	 * @param shipTypeCode the ship type code
	 * @param documentYear the document year
	 * @param documentTypeCode the document type code
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTempDocumentByShipNameSMSAndDocumentTypeCode(
		String shipName, String callSign, String shipTypeCode,
		int documentYear, String documentTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

			if (shipName == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_1);
			}
			else {
				if (shipName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_2);
				}
			}

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_2);
				}
			}

			query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTYEAR_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (shipName != null) {
					builder.appendNamedParameterMap("shipName", shipName);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
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
	 * Returns the number of temp documents where documentTypeCode = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param maritimeCode the maritime code
	 * @return the number of matching temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_documentTypeCode_maritimeCode(String documentTypeCode,
		String maritimeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPDOCUMENT_WHERE);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_2);
				}
			}

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of temp documents.
	 *
	 * @return the number of temp documents
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPDOCUMENT).build();

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
	 * Initializes the temp document persistence.
	 */
	private static final String _SQL_SELECT_TEMPDOCUMENT = "SELECT tempDocument FROM TempDocument tempDocument";
	private static final String _SQL_SELECT_TEMPDOCUMENT_WHERE = "SELECT tempDocument FROM TempDocument tempDocument WHERE ";
	private static final String _SQL_COUNT_TEMPDOCUMENT = "SELECT COUNT(tempDocument) FROM TempDocument tempDocument";
	private static final String _SQL_COUNT_TEMPDOCUMENT_WHERE = "SELECT COUNT(tempDocument) FROM TempDocument tempDocument WHERE ";
	private static final String _FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempDocument.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempDocument.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2 =
		"tempDocument.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2 =
		"tempDocument.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_TEMDOCUMENTBYDOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2 =
		"tempDocument.requestState =:requestState";
	private static final String _FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTNAME_2 = "tempDocument.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTYEAR_2 = "tempDocument.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_1 = "tempDocument.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_2 = "tempDocument.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_F_B_N_Y_TYPE_DOCUMENTTYPECODE_3 = "(tempDocument.documentTypeCode IS NULL OR tempDocument.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempDocument.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempDocument.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempDocument.requestCode IS NULL OR tempDocument.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_1 =
		"tempDocument.shipName IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_2 =
		"tempDocument.shipName =:shipName AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPNAME_3 =
		"(tempDocument.shipName IS NULL OR tempDocument.shipName =:shipName) AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_1 =
		"tempDocument.callSign IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_2 =
		"tempDocument.callSign =:callSign AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_CALLSIGN_3 =
		"(tempDocument.callSign IS NULL OR tempDocument.callSign =:callSign) AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_1 =
		"tempDocument.shipTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_2 =
		"tempDocument.shipTypeCode =:shipTypeCode AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_SHIPTYPECODE_3 =
		"(tempDocument.shipTypeCode IS NULL OR tempDocument.shipTypeCode =:shipTypeCode) AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTYEAR_2 =
		"tempDocument.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_1 =
		"tempDocument.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_2 =
		"tempDocument.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_TEMPDOCUMENTBYSHIPNAMESMSANDDOCUMENTTYPECODE_DOCUMENTTYPECODE_3 =
		"(tempDocument.documentTypeCode IS NULL OR tempDocument.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_1 =
		"tempDocument.documentTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_2 =
		"tempDocument.documentTypeCode =:documentTypeCode AND ";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_DOCUMENTTYPECODE_3 =
		"(tempDocument.documentTypeCode IS NULL OR tempDocument.documentTypeCode =:documentTypeCode) AND ";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_1 =
		"tempDocument.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_2 =
		"tempDocument.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPECODE_MARITIMECODE_MARITIMECODE_3 =
		"(tempDocument.maritimeCode IS NULL OR tempDocument.maritimeCode =:maritimeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempDocument.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempDocument exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempDocument exists with the key {";
	

	
}
