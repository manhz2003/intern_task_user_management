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
import com.fds.nsw.nghiepvu.model.TempWasteList;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempWasteListRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempWasteListModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempWasteListPersistenceImpl extends BasePersistence {
	@Autowired
	TempWasteListRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempWasteList> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempWasteListUtil} to access the temp waste list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempWasteList create(long id) {
		TempWasteList tempWasteList = new TempWasteList();

		
		//tempWasteList.setPrimaryKey(id);

		return tempWasteList;
	}

	/**
	 * Removes the temp waste list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp waste list
	 * @return the temp waste list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList remove(long id)
		throws NoSuchTempWasteListException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp waste list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp waste list
	 * @return the temp waste list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempWasteList remove(Serializable primaryKey)
		throws NoSuchTempWasteListException, SystemException {
		

		try {
			

			TempWasteList tempWasteList = findByPrimaryKey(primaryKey);

			if (tempWasteList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempWasteListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempWasteList);
			return tempWasteList;
		}
		catch (NoSuchTempWasteListException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempWasteList remove(TempWasteList TempWasteList) throws SystemException {
	removeImpl(TempWasteList);
	return TempWasteList;
}

protected TempWasteList removeImpl(TempWasteList tempWasteList)
		throws SystemException {
		try {
			repository.delete(tempWasteList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempWasteList;
	}

	
	public TempWasteList updateImpl(
		com.fds.nsw.nghiepvu.model.TempWasteList tempWasteList,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempWasteList);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempWasteList;
	}

	
	public TempWasteList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp waste list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempWasteListException} if it could not be found.
	 *
	 * @param id the primary key of the temp waste list
	 * @return the temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findByPrimaryKey(long id)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchByPrimaryKey(id);

		if (tempWasteList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempWasteListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempWasteList;
	}

	/**
	 * Returns the temp waste list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp waste list
	 * @return the temp waste list, or <code>null</code> if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempWasteList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp waste list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp waste list
	 * @return the temp waste list, or <code>null</code> if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchByPrimaryKey(long id) throws SystemException {
		TempWasteList tempWasteList = null;

		

		if (tempWasteList == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempWasteList> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempWasteList = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempWasteList;
	}

	/**
	 * Returns all the temp waste lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp waste lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @return the range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp waste lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempWasteList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp waste list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchBydocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the first temp waste list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempWasteList> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp waste list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchBydocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the last temp waste list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentNameAnddocumentYear(documentName,
				documentYear);

		List<TempWasteList> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp waste lists before and after the current temp waste list in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp waste list
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList[] findBydocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = findByPrimaryKey(id);

		

		try {
			

			TempWasteList[] array = new TempWasteList[3];

			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempWasteList, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempWasteList;

			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempWasteList, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempWasteList getBydocumentNameAnddocumentYear_PrevAndNext(
		 TempWasteList tempWasteList, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

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
			query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempWasteList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempWasteList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp waste lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		return findBydocumentNameAnddocumentYearAndRequestCode(documentName,
			documentYear, requestCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp waste lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @return the range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode, int start,
		int end) throws SystemException {
		return findBydocumentNameAnddocumentYearAndRequestCode(documentName,
			documentYear, requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp waste lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

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
				query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempWasteList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp waste list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findBydocumentNameAnddocumentYearAndRequestCode_First(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchBydocumentNameAnddocumentYearAndRequestCode_First(documentName,
				documentYear, requestCode, orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
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

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the first temp waste list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchBydocumentNameAnddocumentYearAndRequestCode_First(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = findBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp waste list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findBydocumentNameAnddocumentYearAndRequestCode_Last(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchBydocumentNameAnddocumentYearAndRequestCode_Last(documentName,
				documentYear, requestCode, orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
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

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the last temp waste list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchBydocumentNameAnddocumentYearAndRequestCode_Last(
		long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode);

		List<TempWasteList> list = findBydocumentNameAnddocumentYearAndRequestCode(documentName,
				documentYear, requestCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp waste lists before and after the current temp waste list in the ordered set where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp waste list
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList[] findBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
		long id, long documentName, int documentYear, String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = findByPrimaryKey(id);

		

		try {
			

			TempWasteList[] array = new TempWasteList[3];

			array[0] = getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
					tempWasteList, documentName, documentYear, requestCode,
					orderByComparator, true);

			array[1] = tempWasteList;

			array[2] = getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
					tempWasteList, documentName, documentYear, requestCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempWasteList getBydocumentNameAnddocumentYearAndRequestCode_PrevAndNext(
		 TempWasteList tempWasteList, long documentName,
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

		query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

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
			query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempWasteList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempWasteList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp waste lists where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp waste lists where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @return the range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findByRequestCode(String requestCode, int start,
		int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp waste lists where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findByRequestCode(String requestCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

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
				query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempWasteList>)queryFactory.getResultList(builder);
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
	 * Returns the first temp waste list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the first temp waste list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp waste list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempWasteList != null) {
			return tempWasteList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempWasteListException(msg.toString());
	}

	/**
	 * Returns the last temp waste list in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempWasteList> list = findByRequestCode(requestCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp waste lists before and after the current temp waste list in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp waste list
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a temp waste list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = findByPrimaryKey(id);

		

		try {
			

			TempWasteList[] array = new TempWasteList[3];

			array[0] = getByRequestCode_PrevAndNext(tempWasteList,
					requestCode, orderByComparator, true);

			array[1] = tempWasteList;

			array[2] = getByRequestCode_PrevAndNext(tempWasteList,
					requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempWasteList getByRequestCode_PrevAndNext(
		TempWasteList tempWasteList, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

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
			query.append(TempWasteListModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempWasteList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempWasteList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the temp waste list where requestCode = &#63; and typeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempWasteListException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @param typeCode the type code
	 * @return the matching temp waste list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempWasteListException if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList findByRequestCode_TypeCode(String requestCode,
		String typeCode) throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = fetchByRequestCode_TypeCode(requestCode,
				typeCode);

		if (tempWasteList == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(", typeCode=");
			msg.append(typeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempWasteListException(msg.toString());
		}

		return tempWasteList;
	}

	/**
	 * Returns the temp waste list where requestCode = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @param typeCode the type code
	 * @return the matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchByRequestCode_TypeCode(String requestCode,
		String typeCode) throws SystemException {
		return fetchByRequestCode_TypeCode(requestCode, typeCode, true);
	}

	/**
	 * Returns the temp waste list where requestCode = &#63; and typeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param typeCode the type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp waste list, or <code>null</code> if a matching temp waste list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList fetchByRequestCode_TypeCode(String requestCode,
		String typeCode, boolean retrieveFromCache) throws SystemException {
		TempWasteList tempWasteList = null;
		if (tempWasteList == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TEMPWASTELIST_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_2);
				}
			}

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_1);
			}
			else {
				if (typeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_2);
				}
			}

			query.append(TempWasteListModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempWasteList.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				if (typeCode != null) {
					builder.appendNamedParameterMap("typeCode", typeCode);
				}

				tempWasteList = (TempWasteList) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return tempWasteList;
	}

	/**
	 * Returns all the temp waste lists.
	 *
	 * @return the temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp waste lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @return the range of temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp waste lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp waste lists
	 * @param end the upper bound of the range of temp waste lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempWasteList> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempWasteList> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPWASTELIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPWASTELIST.concat(TempWasteListModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempWasteList>) queryFactory.getResultList(builder);
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
	 * Removes all the temp waste lists where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (TempWasteList tempWasteList : findBydocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(tempWasteList);
		}
	}

	/**
	 * Removes all the temp waste lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		for (TempWasteList tempWasteList : findBydocumentNameAnddocumentYearAndRequestCode(
				documentName, documentYear, requestCode)) {
			repository.delete(tempWasteList);
		}
	}

	/**
	 * Removes all the temp waste lists where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempWasteList tempWasteList : findByRequestCode(requestCode)) {
			repository.delete(tempWasteList);
		}
	}

	/**
	 * Removes the temp waste list where requestCode = &#63; and typeCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @param typeCode the type code
	 * @return the temp waste list that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempWasteList removeByRequestCode_TypeCode(String requestCode,
		String typeCode) throws NoSuchTempWasteListException, SystemException {
		TempWasteList tempWasteList = findByRequestCode_TypeCode(requestCode,
				typeCode);

		repository.delete(tempWasteList);
			return tempWasteList;
	}

	/**
	 * Removes all the temp waste lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempWasteList tempWasteList : findAll()) {
			repository.delete(tempWasteList);
		}
	}

	/**
	 * Returns the number of temp waste lists where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPWASTELIST_WHERE);

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
	 * Returns the number of temp waste lists where documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the number of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYearAndRequestCode(
		long documentName, int documentYear, String requestCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPWASTELIST_WHERE);

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
	 * Returns the number of temp waste lists where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPWASTELIST_WHERE);

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
	 * Returns the number of temp waste lists where requestCode = &#63; and typeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param typeCode the type code
	 * @return the number of matching temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode_TypeCode(String requestCode, String typeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPWASTELIST_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_2);
				}
			}

			if (typeCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_1);
			}
			else {
				if (typeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				if (typeCode != null) {
					builder.appendNamedParameterMap("typeCode", typeCode);
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
	 * Returns the number of temp waste lists.
	 *
	 * @return the number of temp waste lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPWASTELIST).build();

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
	 * Initializes the temp waste list persistence.
	 */
	private static final String _SQL_SELECT_TEMPWASTELIST = "SELECT tempWasteList FROM TempWasteList tempWasteList";
	private static final String _SQL_SELECT_TEMPWASTELIST_WHERE = "SELECT tempWasteList FROM TempWasteList tempWasteList WHERE ";
	private static final String _SQL_COUNT_TEMPWASTELIST = "SELECT COUNT(tempWasteList) FROM TempWasteList tempWasteList";
	private static final String _SQL_COUNT_TEMPWASTELIST_WHERE = "SELECT COUNT(tempWasteList) FROM TempWasteList tempWasteList WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempWasteList.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempWasteList.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTNAME_2 =
		"tempWasteList.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_DOCUMENTYEAR_2 =
		"tempWasteList.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_1 =
		"tempWasteList.requestCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_2 =
		"tempWasteList.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDREQUESTCODE_REQUESTCODE_3 =
		"(tempWasteList.requestCode IS NULL OR tempWasteList.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempWasteList.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempWasteList.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempWasteList.requestCode IS NULL OR tempWasteList.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_1 =
		"tempWasteList.requestCode IS NULL AND ";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_2 =
		"tempWasteList.requestCode =:requestCode AND ";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_REQUESTCODE_3 =
		"(tempWasteList.requestCode IS NULL OR tempWasteList.requestCode =:requestCode) AND ";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_1 = "tempWasteList.typeCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_2 = "tempWasteList.typeCode =:typeCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_TYPECODE_TYPECODE_3 = "(tempWasteList.typeCode IS NULL OR tempWasteList.typeCode =:typeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempWasteList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempWasteList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempWasteList exists with the key {";
	

	
}
