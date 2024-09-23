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
import com.fds.nsw.nghiepvu.model.TempPersonList;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempPersonListRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempPersonListModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempPersonListPersistenceImpl extends BasePersistence {
	@Autowired
	TempPersonListRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempPersonList> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempPersonListUtil} to access the temp person list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempPersonList create(long id) {
		TempPersonList tempPersonList = new TempPersonList();

		
		//tempPersonList.setPrimaryKey(id);

		return tempPersonList;
	}

	/**
	 * Removes the temp person list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp person list
	 * @return the temp person list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList remove(long id)
		throws NoSuchTempPersonListException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp person list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp person list
	 * @return the temp person list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempPersonList remove(Serializable primaryKey)
		throws NoSuchTempPersonListException, SystemException {
		

		try {
			

			TempPersonList tempPersonList = findByPrimaryKey(primaryKey);

			if (tempPersonList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempPersonListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempPersonList);
			return tempPersonList;
		}
		catch (NoSuchTempPersonListException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempPersonList remove(TempPersonList TempPersonList) throws SystemException {
	removeImpl(TempPersonList);
	return TempPersonList;
}

protected TempPersonList removeImpl(TempPersonList tempPersonList)
		throws SystemException {
		try {
			repository.delete(tempPersonList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempPersonList;
	}

	
	public TempPersonList updateImpl(
		com.fds.nsw.nghiepvu.model.TempPersonList tempPersonList,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempPersonList);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempPersonList;
	}

	
	public TempPersonList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp person list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempPersonListException} if it could not be found.
	 *
	 * @param id the primary key of the temp person list
	 * @return the temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findByPrimaryKey(long id)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchByPrimaryKey(id);

		if (tempPersonList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempPersonListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempPersonList;
	}

	/**
	 * Returns the temp person list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp person list
	 * @return the temp person list, or <code>null</code> if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempPersonList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp person list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp person list
	 * @return the temp person list, or <code>null</code> if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchByPrimaryKey(long id) throws SystemException {
		TempPersonList tempPersonList = null;

		

		if (tempPersonList == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempPersonList> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempPersonList = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempPersonList;
	}

	/**
	 * Returns all the temp person lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp person lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @return the range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp person lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPersonList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempPersonList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp person list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchBydocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the first temp person list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempPersonList> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp person list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchBydocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the last temp person list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentNameAnddocumentYear(documentName,
				documentYear);

		List<TempPersonList> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp person lists before and after the current temp person list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp person list
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList[] findBydocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = findByPrimaryKey(id);

		

		try {
			

			TempPersonList[] array = new TempPersonList[3];

			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempPersonList, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempPersonList;

			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempPersonList, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempPersonList getBydocumentNameAnddocumentYear_PrevAndNext(
		 TempPersonList tempPersonList, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

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
			query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempPersonList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempPersonList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp person lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		return findBydocumentNameAnddocumentYearAndRequestCode(documentName,
			documentYear, requestCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp person lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @return the range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode, int start,
		int end) throws SystemException {
		return findBydocumentNameAnddocumentYearAndRequestCode(documentName,
			documentYear, requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp person lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempPersonList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempPersonList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp person list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findBydocumentNameAnddocumentYearAndRequestCode_First(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchBydocumentNameAnddocumentYearAndRequestCode_First(documentName,
				documentYear, requestCode, orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the first temp person list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchBydocumentNameAnddocumentYearAndRequestCode_First(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPersonList> list = findBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp person list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findBydocumentNameAnddocumentYearAndRequestCode_Last(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchBydocumentNameAnddocumentYearAndRequestCode_Last(documentName,
				documentYear, requestCode, orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the last temp person list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchBydocumentNameAnddocumentYearAndRequestCode_Last(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode);

		List<TempPersonList> list = findBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp person lists before and after the current temp person list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp person list
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList[] findBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
		long id, long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = findByPrimaryKey(id);

		

		try {
			

			TempPersonList[] array = new TempPersonList[3];

			array[0] = getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
					tempPersonList, documentName, documentYear, requestCode,
					orderByComparator, true);

			array[1] = tempPersonList;

			array[2] = getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
					tempPersonList, documentName, documentYear, requestCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempPersonList getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
		 TempPersonList tempPersonList, long documentName,
		int documentYear, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTYEAR_2);

		if (requestCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_1);
		}
		else {
			if (requestCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_2);
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
			query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempPersonList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempPersonList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp person lists where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp person lists where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @return the range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp person lists where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempPersonList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

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
				query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempPersonList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp person list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the first temp person list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPersonList> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp person list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempPersonList != null) {
			return tempPersonList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPersonListException(msg.toString());
	}

	/**
	 * Returns the last temp person list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp person list, or <code>null</code> if a matching temp person list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempPersonList> list = findByRequestCode(requestCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp person lists before and after the current temp person list in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp person list
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp person list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPersonListException if a temp person list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPersonList[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempPersonListException, SystemException {
		TempPersonList tempPersonList = findByPrimaryKey(id);

		

		try {
			

			TempPersonList[] array = new TempPersonList[3];

			array[0] = getByRequestCode_PrevAndNext(tempPersonList,
					requestCode, orderByComparator, true);

			array[1] = tempPersonList;

			array[2] = getByRequestCode_PrevAndNext(tempPersonList,
					requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempPersonList getByRequestCode_PrevAndNext(
		TempPersonList tempPersonList, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPPERSONLIST_WHERE);

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
			query.append(TempPersonListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempPersonList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempPersonList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp person lists.
	 *
	 * @return the temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp person lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @return the range of temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp person lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp person lists
	 * @param end the upper bound of the range of temp person lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPersonList> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPersonList> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPPERSONLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPPERSONLIST.concat(TempPersonListModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempPersonList>) queryFactory.getResultList(builder);
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
	 * Removes all the temp person lists where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (TempPersonList tempPersonList : findBydocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(tempPersonList);
		}
	}

	/**
	 * Removes all the temp person lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		for (TempPersonList tempPersonList : findBydocumentNameAnddocumentYearAndRequestCode(
				documentName, documentYear, requestCode)) {
			repository.delete(tempPersonList);
		}
	}

	/**
	 * Removes all the temp person lists where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempPersonList tempPersonList : findByRequestCode(requestCode)) {
			repository.delete(tempPersonList);
		}
	}

	/**
	 * Removes all the temp person lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempPersonList tempPersonList : findAll()) {
			repository.delete(tempPersonList);
		}
	}

	/**
	 * Returns the number of temp person lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPPERSONLIST_WHERE);

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
	 * Returns the number of temp person lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the number of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPPERSONLIST_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

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
	 * Returns the number of temp person lists where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPPERSONLIST_WHERE);

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
	 * Returns the number of temp person lists.
	 *
	 * @return the number of temp person lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPPERSONLIST).build();

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
	 * Initializes the temp person list persistence.
	 */
	private static final String _SQL_SELECT_TEMPPERSONLIST = "SELECT tempPersonList FROM TempPersonList tempPersonList";
	private static final String _SQL_SELECT_TEMPPERSONLIST_WHERE = "SELECT tempPersonList FROM TempPersonList tempPersonList WHERE ";
	private static final String _SQL_COUNT_TEMPPERSONLIST = "SELECT COUNT(tempPersonList) FROM TempPersonList tempPersonList";
	private static final String _SQL_COUNT_TEMPPERSONLIST_WHERE = "SELECT COUNT(tempPersonList) FROM TempPersonList tempPersonList WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempPersonList.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempPersonList.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTNAME_2 =
		"tempPersonList.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTYEAR_2 =
		"tempPersonList.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_1 =
		"tempPersonList.requestCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_2 =
		"tempPersonList.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_3 =
		"(tempPersonList.requestCode IS NULL OR tempPersonList.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempPersonList.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempPersonList.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempPersonList.requestCode IS NULL OR tempPersonList.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempPersonList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempPersonList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempPersonList exists with the key {";
	

	
}