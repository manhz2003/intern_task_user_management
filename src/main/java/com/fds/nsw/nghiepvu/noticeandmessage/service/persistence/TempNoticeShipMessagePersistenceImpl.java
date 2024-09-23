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
import com.fds.nsw.nghiepvu.model.TempNoticeShipMessage;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempNoticeShipMessageRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempNoTiceShipMessageModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempNoticeShipMessagePersistenceImpl extends BasePersistence {
	@Autowired
	TempNoticeShipMessageRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempNoticeShipMessage> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempNoTiceShipMessageUtil} to access the temp no tice ship message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempNoticeShipMessage create(long id) {
		TempNoticeShipMessage tempNoTiceShipMessage = new TempNoticeShipMessage();

		
		//tempNoTiceShipMessage.setPrimaryKey(id);

		return tempNoTiceShipMessage;
	}

	/**
	 * Removes the temp no tice ship message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp no tice ship message
	 * @return the temp no tice ship message that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage remove(long id)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp no tice ship message with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp no tice ship message
	 * @return the temp no tice ship message that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempNoticeShipMessage remove(Serializable primaryKey)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		

		try {
			

			TempNoticeShipMessage tempNoTiceShipMessage = findByPrimaryKey(primaryKey);

			if (tempNoTiceShipMessage == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempNoticeShipMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempNoTiceShipMessage);
			return tempNoTiceShipMessage;
		}
		catch (NoSuchTempNoticeShipMessageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempNoticeShipMessage remove(TempNoticeShipMessage TempNoticeShipMessage) throws SystemException {
	removeImpl(TempNoticeShipMessage);
	return TempNoticeShipMessage;
}

protected TempNoticeShipMessage removeImpl(
		TempNoticeShipMessage tempNoTiceShipMessage) throws SystemException {
		try {
			repository.delete(tempNoTiceShipMessage);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempNoTiceShipMessage;
	}

	
	public TempNoticeShipMessage updateImpl(
		com.fds.nsw.nghiepvu.model.TempNoticeShipMessage tempNoTiceShipMessage,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempNoTiceShipMessage);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempNoTiceShipMessage;
	}

	
	public TempNoticeShipMessage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp no tice ship message with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException} if it could not be found.
	 *
	 * @param id the primary key of the temp no tice ship message
	 * @return the temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findByPrimaryKey(long id)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchByPrimaryKey(id);

		if (tempNoTiceShipMessage == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempNoticeShipMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempNoTiceShipMessage;
	}

	/**
	 * Returns the temp no tice ship message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp no tice ship message
	 * @return the temp no tice ship message, or <code>null</code> if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempNoticeShipMessage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp no tice ship message with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp no tice ship message
	 * @return the temp no tice ship message, or <code>null</code> if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchByPrimaryKey(long id)
		throws SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = null;

		

		if (tempNoTiceShipMessage == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempNoticeShipMessage> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempNoTiceShipMessage = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempNoTiceShipMessage;
	}

	/**
	 * Returns all the temp no tice ship messages where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp no tice ship messages where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @return the range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp no tice ship messages where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempNoticeShipMessage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempNoticeShipMessage>)queryFactory.getResultList(builder);
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
	 * Returns the first temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchBydocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the first temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchBydocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempNoticeShipMessage> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchBydocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchBydocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentNameAnddocumentYear(documentName,
				documentYear);

		List<TempNoticeShipMessage> list = findBydocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp no tice ship messages before and after the current temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp no tice ship message
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage[] findBydocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = findByPrimaryKey(id);

		

		try {
			

			TempNoticeShipMessage[] array = new TempNoticeShipMessage[3];

			array[0] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempNoTiceShipMessage, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempNoTiceShipMessage;

			array[2] = getBydocumentNameAnddocumentYear_PrevAndNext(
					tempNoTiceShipMessage, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempNoticeShipMessage getBydocumentNameAnddocumentYear_PrevAndNext(
		 TempNoticeShipMessage tempNoTiceShipMessage,
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

		query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

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
			query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempNoTiceShipMessage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempNoticeShipMessage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp no tice ship messages where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, String noticeShipType)
		throws SystemException {
		return findBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
			documentYear, noticeShipType, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp no tice ship messages where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @return the range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, String noticeShipType, int start,
		int end) throws SystemException {
		return findBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
			documentYear, noticeShipType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp no tice ship messages where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, String noticeShipType, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempNoticeShipMessage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTYEAR_2);

			if (noticeShipType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_1);
			}
			else {
				if (noticeShipType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (noticeShipType != null) {
					builder.appendNamedParameterMap("noticeShipType", noticeShipType);
				}

				list = (List<TempNoticeShipMessage>)queryFactory.getResultList(builder);
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
	 * Returns the first temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findBydocumentNameAndDocumentYearAndNoticeShipType_First(
		long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchBydocumentNameAndDocumentYearAndNoticeShipType_First(documentName,
				documentYear, noticeShipType, orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the first temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchBydocumentNameAndDocumentYearAndNoticeShipType_First(
		long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempNoticeShipMessage> list = findBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
				documentYear, noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findBydocumentNameAndDocumentYearAndNoticeShipType_Last(
		long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchBydocumentNameAndDocumentYearAndNoticeShipType_Last(documentName,
				documentYear, noticeShipType, orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchBydocumentNameAndDocumentYearAndNoticeShipType_Last(
		long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
				documentYear, noticeShipType);

		List<TempNoticeShipMessage> list = findBydocumentNameAndDocumentYearAndNoticeShipType(documentName,
				documentYear, noticeShipType, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp no tice ship messages before and after the current temp no tice ship message in the ordered set where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current temp no tice ship message
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage[] findBydocumentNameAndDocumentYearAndNoticeShipType_PrevAndNext(
		long id, long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = findByPrimaryKey(id);

		

		try {
			

			TempNoticeShipMessage[] array = new TempNoticeShipMessage[3];

			array[0] = getBydocumentNameAndDocumentYearAndNoticeShipType_PrevAndNext(
					tempNoTiceShipMessage, documentName, documentYear,
					noticeShipType, orderByComparator, true);

			array[1] = tempNoTiceShipMessage;

			array[2] = getBydocumentNameAndDocumentYearAndNoticeShipType_PrevAndNext(
					tempNoTiceShipMessage, documentName, documentYear,
					noticeShipType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempNoticeShipMessage getBydocumentNameAndDocumentYearAndNoticeShipType_PrevAndNext(
		 TempNoticeShipMessage tempNoTiceShipMessage,
		long documentName, int documentYear, String noticeShipType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTYEAR_2);

		if (noticeShipType == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_1);
		}
		else {
			if (noticeShipType.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_2);
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
			query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (noticeShipType != null) {
			builder.appendNamedParameterMap("noticeShipType", noticeShipType);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempNoTiceShipMessage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempNoticeShipMessage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp no tice ship messages where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp no tice ship messages where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @return the range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp no tice ship messages where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempNoticeShipMessage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

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
				query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempNoticeShipMessage>)queryFactory.getResultList(builder);
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
	 * Returns the first temp no tice ship message in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the first temp no tice ship message in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempNoticeShipMessage> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempNoTiceShipMessage != null) {
			return tempNoTiceShipMessage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempNoticeShipMessageException(msg.toString());
	}

	/**
	 * Returns the last temp no tice ship message in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp no tice ship message, or <code>null</code> if a matching temp no tice ship message could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempNoticeShipMessage> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp no tice ship messages before and after the current temp no tice ship message in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp no tice ship message
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp no tice ship message
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempNoticeShipMessageException if a temp no tice ship message with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempNoticeShipMessage[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempNoticeShipMessageException, SystemException {
		TempNoticeShipMessage tempNoTiceShipMessage = findByPrimaryKey(id);

		

		try {
			

			TempNoticeShipMessage[] array = new TempNoticeShipMessage[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempNoTiceShipMessage, requestCode, orderByComparator, true);

			array[1] = tempNoTiceShipMessage;

			array[2] = getByRequestCode_PrevAndNext(
					tempNoTiceShipMessage, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempNoticeShipMessage getByRequestCode_PrevAndNext(
		 TempNoticeShipMessage tempNoTiceShipMessage,
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

		query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE);

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
			query.append(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempNoTiceShipMessage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempNoticeShipMessage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp no tice ship messages.
	 *
	 * @return the temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp no tice ship messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @return the range of temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp no tice ship messages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp no tice ship messages
	 * @param end the upper bound of the range of temp no tice ship messages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempNoticeShipMessage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempNoticeShipMessage> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPNOTICESHIPMESSAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPNOTICESHIPMESSAGE.concat(TempNoTiceShipMessageModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempNoticeShipMessage>) queryFactory.getResultList(builder);
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
	 * Removes all the temp no tice ship messages where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (TempNoticeShipMessage tempNoTiceShipMessage : findBydocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(tempNoTiceShipMessage);
		}
	}

	/**
	 * Removes all the temp no tice ship messages where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, String noticeShipType)
		throws SystemException {
		for (TempNoticeShipMessage tempNoTiceShipMessage : findBydocumentNameAndDocumentYearAndNoticeShipType(
				documentName, documentYear, noticeShipType)) {
			repository.delete(tempNoTiceShipMessage);
		}
	}

	/**
	 * Removes all the temp no tice ship messages where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempNoticeShipMessage tempNoTiceShipMessage : findByRequestCode(
				requestCode)) {
			repository.delete(tempNoTiceShipMessage);
		}
	}

	/**
	 * Removes all the temp no tice ship messages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempNoticeShipMessage tempNoTiceShipMessage : findAll()) {
			repository.delete(tempNoTiceShipMessage);
		}
	}

	/**
	 * Returns the number of temp no tice ship messages where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPNOTICESHIPMESSAGE_WHERE);

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
	 * Returns the number of temp no tice ship messages where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the number of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameAndDocumentYearAndNoticeShipType(
		long documentName, int documentYear, String noticeShipType)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPNOTICESHIPMESSAGE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTYEAR_2);

			if (noticeShipType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_1);
			}
			else {
				if (noticeShipType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (noticeShipType != null) {
					builder.appendNamedParameterMap("noticeShipType", noticeShipType);
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
	 * Returns the number of temp no tice ship messages where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPNOTICESHIPMESSAGE_WHERE);

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
	 * Returns the number of temp no tice ship messages.
	 *
	 * @return the number of temp no tice ship messages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPNOTICESHIPMESSAGE).build();

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
	 * Initializes the temp no tice ship message persistence.
	 */
	private static final String _SQL_SELECT_TEMPNOTICESHIPMESSAGE = "SELECT tempNoTiceShipMessage FROM TempNoticeShipMessage tempNoTiceShipMessage";
	private static final String _SQL_SELECT_TEMPNOTICESHIPMESSAGE_WHERE = "SELECT tempNoTiceShipMessage FROM TempNoticeShipMessage tempNoTiceShipMessage WHERE ";
	private static final String _SQL_COUNT_TEMPNOTICESHIPMESSAGE = "SELECT COUNT(tempNoTiceShipMessage) FROM TempNoticeShipMessage tempNoTiceShipMessage";
	private static final String _SQL_COUNT_TEMPNOTICESHIPMESSAGE_WHERE = "SELECT COUNT(tempNoTiceShipMessage) FROM TempNoticeShipMessage tempNoTiceShipMessage WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempNoTiceShipMessage.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempNoTiceShipMessage.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTNAME_2 =
		"tempNoTiceShipMessage.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_DOCUMENTYEAR_2 =
		"tempNoTiceShipMessage.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_1 =
		"tempNoTiceShipMessage.noticeShipType IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"tempNoTiceShipMessage.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDNOTICESHIPTYPE_NOTICESHIPTYPE_3 =
		"(tempNoTiceShipMessage.noticeShipType IS NULL OR tempNoTiceShipMessage.noticeShipType =:noticeShipType)";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempNoTiceShipMessage.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempNoTiceShipMessage.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempNoTiceShipMessage.requestCode IS NULL OR tempNoTiceShipMessage.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempNoTiceShipMessage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempNoticeShipMessage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempNoticeShipMessage exists with the key {";
	

	
}
