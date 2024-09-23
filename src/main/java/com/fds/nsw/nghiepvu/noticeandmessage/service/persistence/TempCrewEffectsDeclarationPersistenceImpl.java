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
import com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempCrewEffectsDeclarationRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempCrewEffectsDeclarationModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempCrewEffectsDeclarationPersistenceImpl extends BasePersistence {
	@Autowired
	TempCrewEffectsDeclarationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempCrewEffectsDeclaration> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempCrewEffectsDeclarationUtil} to access the temp crew effects declaration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempCrewEffectsDeclaration create(long id) {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = new TempCrewEffectsDeclaration();

		
		//tempCrewEffectsDeclaration.setPrimaryKey(id);

		return tempCrewEffectsDeclaration;
	}

	/**
	 * Removes the temp crew effects declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration remove(long id)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp crew effects declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewEffectsDeclaration remove(Serializable primaryKey)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		

		try {
			

			TempCrewEffectsDeclaration tempCrewEffectsDeclaration = findByPrimaryKey(primaryKey);

			if (tempCrewEffectsDeclaration == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempCrewEffectsDeclarationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempCrewEffectsDeclaration);
			return tempCrewEffectsDeclaration;
		}
		catch (NoSuchTempCrewEffectsDeclarationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempCrewEffectsDeclaration remove(TempCrewEffectsDeclaration TempCrewEffectsDeclaration) throws SystemException {
	removeImpl(TempCrewEffectsDeclaration);
	return TempCrewEffectsDeclaration;
}

protected TempCrewEffectsDeclaration removeImpl(
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration)
		throws SystemException {
		try {
			repository.delete(tempCrewEffectsDeclaration);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewEffectsDeclaration;
	}

	
	public TempCrewEffectsDeclaration updateImpl(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsDeclaration tempCrewEffectsDeclaration,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempCrewEffectsDeclaration);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewEffectsDeclaration;
	}

	
	public TempCrewEffectsDeclaration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew effects declaration with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException} if it could not be found.
	 *
	 * @param id the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findByPrimaryKey(long id)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchByPrimaryKey(id);

		if (tempCrewEffectsDeclaration == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempCrewEffectsDeclarationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempCrewEffectsDeclaration;
	}

	/**
	 * Returns the temp crew effects declaration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration, or <code>null</code> if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewEffectsDeclaration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew effects declaration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp crew effects declaration
	 * @return the temp crew effects declaration, or <code>null</code> if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchByPrimaryKey(long id)
		throws SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = null;

		

		if (tempCrewEffectsDeclaration == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempCrewEffectsDeclaration> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempCrewEffectsDeclaration = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempCrewEffectsDeclaration;
	}

	/**
	 * Returns all the temp crew effects declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew effects declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @return the range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempCrewEffectsDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchBydocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the first temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempCrewEffectsDeclaration> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchBydocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentNameAnddocumentYear(documentName,
				documentYear);

		List<TempCrewEffectsDeclaration> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew effects declarations before and after the current temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp crew effects declaration
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration[] findBydocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = findByPrimaryKey(id);

		

		try {
			

			TempCrewEffectsDeclaration[] array = new TempCrewEffectsDeclaration[3];

			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempCrewEffectsDeclaration, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempCrewEffectsDeclaration;

			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempCrewEffectsDeclaration, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewEffectsDeclaration getBydocumentNameAnddocumentYear_PrevAndNext(
		 TempCrewEffectsDeclaration tempCrewEffectsDeclaration,
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

		query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

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
			query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewEffectsDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewEffectsDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew effects declarations where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		return findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp crew effects declarations where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @return the range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end) throws SystemException {
		return findBydocumentNameAnddocumentYearRequestState(documentName,
			documentYear, requestState, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects declarations where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("requestState", requestState);

				list = (List<TempCrewEffectsDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findBydocumentNameAnddocumentYearRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchBydocumentNameAnddocumentYearRequestState_First(documentName,
				documentYear, requestState, orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
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

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the first temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchBydocumentNameAnddocumentYearRequestState_First(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsDeclaration> list = findBydocumentNameAnddocumentYearRequestState(documentName,
				documentYear, requestState, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findBydocumentNameAnddocumentYearRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchBydocumentNameAnddocumentYearRequestState_Last(documentName,
				documentYear, requestState, orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
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

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchBydocumentNameAnddocumentYearRequestState_Last(
		long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydocumentNameAnddocumentYearRequestState(documentName,
				documentYear, requestState);

		List<TempCrewEffectsDeclaration> list = findBydocumentNameAnddocumentYearRequestState(documentName,
				documentYear, requestState, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew effects declarations before and after the current temp crew effects declaration in the ordered set where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param id the primary key of the current temp crew effects declaration
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration[] findBydocumentNameAnddocumentYearRequestState_PrevAndNext(
		long id, long documentName, int documentYear, int requestState,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = findByPrimaryKey(id);

		

		try {
			

			TempCrewEffectsDeclaration[] array = new TempCrewEffectsDeclaration[3];

			array[0] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
					tempCrewEffectsDeclaration, documentName, documentYear,
					requestState, orderByComparator, true);

			array[1] = tempCrewEffectsDeclaration;

			array[2] = getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
					tempCrewEffectsDeclaration, documentName, documentYear,
					requestState, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewEffectsDeclaration getBydocumentNameAnddocumentYearRequestState_PrevAndNext(
		 TempCrewEffectsDeclaration tempCrewEffectsDeclaration,
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

		query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

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
			query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("requestState", requestState);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewEffectsDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewEffectsDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew effects declarations where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findByRequestCode(
		String requestCode) throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew effects declarations where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @return the range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findByRequestCode(
		String requestCode, int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects declarations where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findByRequestCode(
		String requestCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

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
				query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempCrewEffectsDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew effects declaration in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findByRequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the first temp crew effects declaration in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchByRequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempCrewEffectsDeclaration> list = findByRequestCode(requestCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration findByRequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempCrewEffectsDeclaration != null) {
			return tempCrewEffectsDeclaration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsDeclarationException(msg.toString());
	}

	/**
	 * Returns the last temp crew effects declaration in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects declaration, or <code>null</code> if a matching temp crew effects declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration fetchByRequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempCrewEffectsDeclaration> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew effects declarations before and after the current temp crew effects declaration in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp crew effects declaration
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew effects declaration
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsDeclarationException if a temp crew effects declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsDeclaration[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsDeclarationException, SystemException {
		TempCrewEffectsDeclaration tempCrewEffectsDeclaration = findByPrimaryKey(id);

		

		try {
			

			TempCrewEffectsDeclaration[] array = new TempCrewEffectsDeclaration[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempCrewEffectsDeclaration, requestCode, orderByComparator,
					true);

			array[1] = tempCrewEffectsDeclaration;

			array[2] = getByRequestCode_PrevAndNext(
					tempCrewEffectsDeclaration, requestCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewEffectsDeclaration getByRequestCode_PrevAndNext(
		 TempCrewEffectsDeclaration tempCrewEffectsDeclaration,
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

		query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE);

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
			query.append(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewEffectsDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewEffectsDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew effects declarations.
	 *
	 * @return the temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew effects declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @return the range of temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew effects declarations
	 * @param end the upper bound of the range of temp crew effects declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsDeclaration> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPCREWEFFECTSDECLARATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPCREWEFFECTSDECLARATION.concat(TempCrewEffectsDeclarationModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempCrewEffectsDeclaration>) queryFactory.getResultList(builder);
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
	 * Removes all the temp crew effects declarations where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (TempCrewEffectsDeclaration tempCrewEffectsDeclaration : findBydocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(tempCrewEffectsDeclaration);
		}
	}

	/**
	 * Removes all the temp crew effects declarations where documentName = &#63; and documentYear = &#63; and requestState = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		for (TempCrewEffectsDeclaration tempCrewEffectsDeclaration : findBydocumentNameAnddocumentYearRequestState(
				documentName, documentYear, requestState)) {
			repository.delete(tempCrewEffectsDeclaration);
		}
	}

	/**
	 * Removes all the temp crew effects declarations where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempCrewEffectsDeclaration tempCrewEffectsDeclaration : findByRequestCode(
				requestCode)) {
			repository.delete(tempCrewEffectsDeclaration);
		}
	}

	/**
	 * Removes all the temp crew effects declarations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempCrewEffectsDeclaration tempCrewEffectsDeclaration : findAll()) {
			repository.delete(tempCrewEffectsDeclaration);
		}
	}

	/**
	 * Returns the number of temp crew effects declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPCREWEFFECTSDECLARATION_WHERE);

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
	 * Returns the number of temp crew effects declarations where documentName = &#63; and documentYear = &#63; and requestState = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestState the request state
	 * @return the number of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYearRequestState(
		long documentName, int documentYear, int requestState)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPCREWEFFECTSDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2);

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
	 * Returns the number of temp crew effects declarations where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPCREWEFFECTSDECLARATION_WHERE);

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
	 * Returns the number of temp crew effects declarations.
	 *
	 * @return the number of temp crew effects declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPCREWEFFECTSDECLARATION).build();

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
	 * Initializes the temp crew effects declaration persistence.
	 */
	private static final String _SQL_SELECT_TEMPCREWEFFECTSDECLARATION = "SELECT tempCrewEffectsDeclaration FROM TempCrewEffectsDeclaration tempCrewEffectsDeclaration";
	private static final String _SQL_SELECT_TEMPCREWEFFECTSDECLARATION_WHERE = "SELECT tempCrewEffectsDeclaration FROM TempCrewEffectsDeclaration tempCrewEffectsDeclaration WHERE ";
	private static final String _SQL_COUNT_TEMPCREWEFFECTSDECLARATION = "SELECT COUNT(tempCrewEffectsDeclaration) FROM TempCrewEffectsDeclaration tempCrewEffectsDeclaration";
	private static final String _SQL_COUNT_TEMPCREWEFFECTSDECLARATION_WHERE = "SELECT COUNT(tempCrewEffectsDeclaration) FROM TempCrewEffectsDeclaration tempCrewEffectsDeclaration WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempCrewEffectsDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempCrewEffectsDeclaration.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTNAME_2 =
		"tempCrewEffectsDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_DOCUMENTYEAR_2 =
		"tempCrewEffectsDeclaration.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARREQUESTSTATE_REQUESTSTATE_2 =
		"tempCrewEffectsDeclaration.requestState =:requestState";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempCrewEffectsDeclaration.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempCrewEffectsDeclaration.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempCrewEffectsDeclaration.requestCode IS NULL OR tempCrewEffectsDeclaration.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempCrewEffectsDeclaration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempCrewEffectsDeclaration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempCrewEffectsDeclaration exists with the key {";
	

	
}
