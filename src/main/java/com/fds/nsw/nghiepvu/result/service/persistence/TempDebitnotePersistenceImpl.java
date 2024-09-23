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

package com.fds.nsw.nghiepvu.result.service.persistence;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import com.fds.flex.common.ultility.array.ArrayUtil;
import com.fds.flex.common.utility.string.StringUtil;
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
import com.fds.nsw.nghiepvu.model.TempDebitnote;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempDebitnoteRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempDebitNoteModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempDebitnotePersistenceImpl extends BasePersistence {
	@Autowired
	TempDebitnoteRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempDebitnote> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempDebitNoteUtil} to access the temp debit note persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempDebitnote create(long id) {
		TempDebitnote tempDebitNote = new TempDebitnote();


		//query.setPrimaryKey(id);

		return tempDebitNote;
	}

	/**
	 * Removes the temp debit note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp debit note
	 * @return the temp debit note that was removed
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote remove(long id)
		throws NoSuchTempDebitNoteException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp debit note with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp debit note
	 * @return the temp debit note that was removed
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public TempDebitnote remove(Serializable primaryKey)
		throws NoSuchTempDebitNoteException, SystemException {


		try {


			TempDebitnote tempDebitNote = findByPrimaryKey(primaryKey);

			if (tempDebitNote == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempDebitNoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempDebitNote);
			return tempDebitNote;
		}
		catch (NoSuchTempDebitNoteException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}


	public TempDebitnote remove(TempDebitnote TempDebitnote) throws SystemException {
	removeImpl(TempDebitnote);
	return TempDebitnote;
}

protected TempDebitnote removeImpl(TempDebitnote tempDebitNote)
		throws SystemException {
		try {
			repository.delete(tempDebitNote);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempDebitNote;
	}


	public TempDebitnote updateImpl(
		com.fds.nsw.nghiepvu.model.TempDebitnote tempDebitNote, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(tempDebitNote);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempDebitNote;
	}


	public TempDebitnote findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp debit note with the primary key or throws a {@link vn.gt.dao.result.NoSuchTempDebitNoteException} if it could not be found.
	 *
	 * @param id the primary key of the temp debit note
	 * @return the temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByPrimaryKey(long id)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByPrimaryKey(id);

		if (tempDebitNote == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempDebitNoteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempDebitNote;
	}

	/**
	 * Returns the temp debit note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp debit note
	 * @return the temp debit note, or <code>null</code> if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public TempDebitnote fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp debit note with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp debit note
	 * @return the temp debit note, or <code>null</code> if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByPrimaryKey(long id) throws SystemException {
		TempDebitnote tempDebitNote = null;



		if (tempDebitNote == null) {


			boolean hasException = false;

			try {


				Optional<TempDebitnote> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempDebitNote = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {



			}
		}

		return tempDebitNote;
	}

	/**
	 * Returns all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int markasdeleted)
		throws SystemException {
		return findByDocumentNameAnddocumentYearDone(documentName,
			documentYear, markasdeleted, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int markasdeleted, int start,
		int end) throws SystemException {
		return findByDocumentNameAnddocumentYearDone(documentName,
			documentYear, markasdeleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int markasdeleted, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYearDone_First(
		long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYearDone_First(documentName,
				documentYear, markasdeleted, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYearDone_First(
		long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findByDocumentNameAnddocumentYearDone(documentName,
				documentYear, markasdeleted, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYearDone_Last(
		long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYearDone_Last(documentName,
				documentYear, markasdeleted, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYearDone_Last(
		long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAnddocumentYearDone(documentName,
				documentYear, markasdeleted);

		List<TempDebitnote> list = findByDocumentNameAnddocumentYearDone(documentName,
				documentYear, markasdeleted, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByDocumentNameAnddocumentYearDone_PrevAndNext(
		long id, long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByDocumentNameAnddocumentYearDone_PrevAndNext(
					tempDebitNote, documentName, documentYear, markasdeleted,
					orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByDocumentNameAnddocumentYearDone_PrevAndNext(
					tempDebitNote, documentName, documentYear, markasdeleted,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByDocumentNameAnddocumentYearDone_PrevAndNext(
		 TempDebitnote tempDebitNote, long documentName,
		int documentYear, int markasdeleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_2);

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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleteds the markasdeleteds
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int[] markasdeleteds)
		throws SystemException {
		return findByDocumentNameAnddocumentYearDone(documentName,
			documentYear, markasdeleteds, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleteds the markasdeleteds
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int[] markasdeleteds, int start,
		int end) throws SystemException {
		return findByDocumentNameAnddocumentYearDone(documentName,
			documentYear, markasdeleteds, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleteds the markasdeleteds
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearDone(
		long documentName, int documentYear, int[] markasdeleteds, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {


		List<TempDebitnote> list = null;

		if ((list != null) && !list.isEmpty()) {
			for (TempDebitnote tempDebitNote : list) {
				if ((documentName != tempDebitNote.getDocumentName()) ||
						(documentYear != tempDebitNote.getDocumentYear()) ||
						!ArrayUtil.contains(markasdeleteds,
							tempDebitNote.getMarkasdeleted())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_5);

			conjunctionable = true;

			if ((markasdeleteds == null) || (markasdeleteds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < markasdeleteds.length; i++) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_5);

					if ((i + 1) < markasdeleteds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (markasdeleteds != null) {
					builder.appendNamedParameterMap("markasdeleteds", markasdeleteds);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns all the temp debit notes where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYear(documentName,
				documentYear);

		List<TempDebitnote> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByDocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByDocumentNameAnddocumentYear_PrevAndNext(
					tempDebitNote, documentName, documentYear,
					orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByDocumentNameAnddocumentYear_PrevAndNext(
					tempDebitNote, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByDocumentNameAnddocumentYear_PrevAndNext(
		 TempDebitnote tempDebitNote, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearMarkasdeleted(
		int mariTimeCode, long documentName, int documentYear, int markasdeleted)
		throws SystemException {
		return findByDocumentNameAnddocumentYearMarkasdeleted(mariTimeCode,
			documentName, documentYear, markasdeleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearMarkasdeleted(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, int start, int end) throws SystemException {
		return findByDocumentNameAnddocumentYearMarkasdeleted(mariTimeCode,
			documentName, documentYear, markasdeleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByDocumentNameAnddocumentYearMarkasdeleted(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARKASDELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYearMarkasdeleted_First(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYearMarkasdeleted_First(mariTimeCode,
				documentName, documentYear, markasdeleted, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYearMarkasdeleted_First(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = findByDocumentNameAnddocumentYearMarkasdeleted(mariTimeCode,
				documentName, documentYear, markasdeleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByDocumentNameAnddocumentYearMarkasdeleted_Last(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByDocumentNameAnddocumentYearMarkasdeleted_Last(mariTimeCode,
				documentName, documentYear, markasdeleted, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByDocumentNameAnddocumentYearMarkasdeleted_Last(
		int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYearMarkasdeleted(mariTimeCode,
				documentName, documentYear, markasdeleted);

		List<TempDebitnote> list = findByDocumentNameAnddocumentYearMarkasdeleted(mariTimeCode,
				documentName, documentYear, markasdeleted, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByDocumentNameAnddocumentYearMarkasdeleted_PrevAndNext(
		long id, int mariTimeCode, long documentName, int documentYear,
		int markasdeleted, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByDocumentNameAnddocumentYearMarkasdeleted_PrevAndNext(
					tempDebitNote, mariTimeCode, documentName, documentYear,
					markasdeleted, orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByDocumentNameAnddocumentYearMarkasdeleted_PrevAndNext(
					tempDebitNote, mariTimeCode, documentName, documentYear,
					markasdeleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByDocumentNameAnddocumentYearMarkasdeleted_PrevAndNext(
		 TempDebitnote tempDebitNote, int mariTimeCode,
		long documentName, int documentYear, int markasdeleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARITIMECODE_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTYEAR_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARKASDELETED_2);

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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode)
		throws SystemException {
		return findBydocumentNameSearch(mariTimeCode, markasdeleted,
			documentName, documentTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		int start, int end) throws SystemException {
		return findBydocumentNameSearch(mariTimeCode, markasdeleted,
			documentName, documentTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("documentName", documentName);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findBydocumentNameSearch_First(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchBydocumentNameSearch_First(mariTimeCode,
				markasdeleted, documentName, documentTypeCode, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBydocumentNameSearch_First(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findBydocumentNameSearch(mariTimeCode,
				markasdeleted, documentName, documentTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findBydocumentNameSearch_Last(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchBydocumentNameSearch_Last(mariTimeCode,
				markasdeleted, documentName, documentTypeCode, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBydocumentNameSearch_Last(int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydocumentNameSearch(mariTimeCode, markasdeleted,
				documentName, documentTypeCode);

		List<TempDebitnote> list = findBydocumentNameSearch(mariTimeCode,
				markasdeleted, documentName, documentTypeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findBydocumentNameSearch_PrevAndNext(long id,
		int mariTimeCode, int markasdeleted, long documentName,
		String documentTypeCode, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getBydocumentNameSearch_PrevAndNext(
					tempDebitNote, mariTimeCode, markasdeleted, documentName,
					documentTypeCode, orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getBydocumentNameSearch_PrevAndNext(
					tempDebitNote, mariTimeCode, markasdeleted, documentName,
					documentTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getBydocumentNameSearch_PrevAndNext(
		 TempDebitnote tempDebitNote, int mariTimeCode,
		int markasdeleted, long documentName, String documentTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_2);

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_2);
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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		builder.appendNamedParameterMap("documentName", documentName);

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCodes the document type codes
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String[] documentTypeCodes)
		throws SystemException {
		return findBydocumentNameSearch(mariTimeCode, markasdeleted,
			documentName, documentTypeCodes, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String[] documentTypeCodes,
		int start, int end) throws SystemException {
		return findBydocumentNameSearch(mariTimeCode, markasdeleted,
			documentName, documentTypeCodes, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydocumentNameSearch(int mariTimeCode,
		int markasdeleted, long documentName, String[] documentTypeCodes,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {


		List<TempDebitnote> list = null;

		if ((list != null) && !list.isEmpty()) {
			for (TempDebitnote tempDebitNote : list) {
				if ((mariTimeCode != Integer.parseInt(tempDebitNote.getMariTimeCode())) ||
						(markasdeleted != tempDebitNote.getMarkasdeleted()) ||
						(documentName != tempDebitNote.getDocumentName()) ||
						!ArrayUtil.contains(documentTypeCodes,
							tempDebitNote.getDocumentTypeCode())) {
					list = null;

					break;
				}
			}
		}

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_5);

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("documentName", documentName);

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode)
		throws SystemException {
		return findBydebitnotenumberSearch(mariTimeCode, markasdeleted,
			debitnotenumber, documentTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		int start, int end) throws SystemException {
		return findBydebitnotenumberSearch(mariTimeCode, markasdeleted,
			debitnotenumber, documentTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_2);

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_1);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_2);
				}
			}

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
				}

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findBydebitnotenumberSearch_First(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchBydebitnotenumberSearch_First(mariTimeCode,
				markasdeleted, debitnotenumber, documentTypeCode,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", debitnotenumber=");
		msg.append(debitnotenumber);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBydebitnotenumberSearch_First(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findBydebitnotenumberSearch(mariTimeCode,
				markasdeleted, debitnotenumber, documentTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findBydebitnotenumberSearch_Last(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchBydebitnotenumberSearch_Last(mariTimeCode,
				markasdeleted, debitnotenumber, documentTypeCode,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", debitnotenumber=");
		msg.append(debitnotenumber);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBydebitnotenumberSearch_Last(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBydebitnotenumberSearch(mariTimeCode, markasdeleted,
				debitnotenumber, documentTypeCode);

		List<TempDebitnote> list = findBydebitnotenumberSearch(mariTimeCode,
				markasdeleted, debitnotenumber, documentTypeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findBydebitnotenumberSearch_PrevAndNext(long id,
		int mariTimeCode, int markasdeleted, String debitnotenumber,
		String documentTypeCode, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getBydebitnotenumberSearch_PrevAndNext(
					tempDebitNote, mariTimeCode, markasdeleted,
					debitnotenumber, documentTypeCode, orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getBydebitnotenumberSearch_PrevAndNext(
					tempDebitNote, mariTimeCode, markasdeleted,
					debitnotenumber, documentTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getBydebitnotenumberSearch_PrevAndNext(
		 TempDebitnote tempDebitNote, int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_2);

		query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_2);

		if (debitnotenumber == null) {
			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_1);
		}
		else {
			if (debitnotenumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_2);
			}
		}

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_2);
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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		if (debitnotenumber != null) {
			builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
		}

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCodes the document type codes
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String[] documentTypeCodes)
		throws SystemException {
		return findBydebitnotenumberSearch(mariTimeCode, markasdeleted,
			debitnotenumber, documentTypeCodes, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String[] documentTypeCodes,
		int start, int end) throws SystemException {
		return findBydebitnotenumberSearch(mariTimeCode, markasdeleted,
			debitnotenumber, documentTypeCodes, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String[] documentTypeCodes,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = null;

		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_4);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_6);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_5);
				}
			}

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
				}

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String documentTypeCode) throws SystemException {
		return findByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String documentTypeCode, int start, int end)
		throws SystemException {
		return findByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String documentTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_2);

			query.append(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByMarkasdeleted_First(int markasdeleted,
		int mariTimeCode, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByMarkasdeleted_First(markasdeleted,
				mariTimeCode, documentTypeCode, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByMarkasdeleted_First(int markasdeleted,
		int mariTimeCode, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findByMarkasdeleted(markasdeleted,
				mariTimeCode, documentTypeCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByMarkasdeleted_Last(int markasdeleted,
		int mariTimeCode, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByMarkasdeleted_Last(markasdeleted,
				mariTimeCode, documentTypeCode, orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("markasdeleted=");
		msg.append(markasdeleted);

		msg.append(", mariTimeCode=");
		msg.append(mariTimeCode);

		msg.append(", documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByMarkasdeleted_Last(int markasdeleted,
		int mariTimeCode, String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMarkasdeleted(markasdeleted, mariTimeCode,
				documentTypeCode);

		List<TempDebitnote> list = findByMarkasdeleted(markasdeleted,
				mariTimeCode, documentTypeCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByMarkasdeleted_PrevAndNext(long id,
		int markasdeleted, int mariTimeCode, String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByMarkasdeleted_PrevAndNext(tempDebitNote,
					markasdeleted, mariTimeCode, documentTypeCode,
					orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByMarkasdeleted_PrevAndNext(tempDebitNote,
					markasdeleted, mariTimeCode, documentTypeCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByMarkasdeleted_PrevAndNext(
		TempDebitnote tempDebitNote, int markasdeleted, int mariTimeCode,
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

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_2);

		query.append(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_2);

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_2);
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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCodes the document type codes
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String[] documentTypeCodes) throws SystemException {
		return findByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCodes, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String[] documentTypeCodes, int start, int end)
		throws SystemException {
		return findByMarkasdeleted(markasdeleted, mariTimeCode,
			documentTypeCodes, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = any &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCodes the document type codes
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeleted(int markasdeleted,
		int mariTimeCode, String[] documentTypeCodes, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {

		List<TempDebitnote> list = null;



		if (list == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_5);

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
				}

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns all the temp debit notes where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeletedAll(int markasdeleted)
		throws SystemException {
		return findByMarkasdeletedAll(markasdeleted, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeletedAll(int markasdeleted,
		int start, int end) throws SystemException {
		return findByMarkasdeletedAll(markasdeleted, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where markasdeleted = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param markasdeleted the markasdeleted
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByMarkasdeletedAll(int markasdeleted,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARKASDELETEDALL_MARKASDELETED_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				list = (List<TempDebitnote>)queryFactory.getResultList(builder);
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
	 * Returns the first temp debit note in the ordered set where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByMarkasdeletedAll_First(int markasdeleted,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByMarkasdeletedAll_First(markasdeleted,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByMarkasdeletedAll_First(int markasdeleted,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findByMarkasdeletedAll(markasdeleted, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByMarkasdeletedAll_Last(int markasdeleted,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByMarkasdeletedAll_Last(markasdeleted,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("markasdeleted=");
		msg.append(markasdeleted);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByMarkasdeletedAll_Last(int markasdeleted,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMarkasdeletedAll(markasdeleted);

		List<TempDebitnote> list = findByMarkasdeletedAll(markasdeleted,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where markasdeleted = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param markasdeleted the markasdeleted
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByMarkasdeletedAll_PrevAndNext(long id,
		int markasdeleted, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByMarkasdeletedAll_PrevAndNext(
					tempDebitNote, markasdeleted, orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByMarkasdeletedAll_PrevAndNext(
					tempDebitNote, markasdeleted, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByMarkasdeletedAll_PrevAndNext(
		TempDebitnote tempDebitNote, int markasdeleted,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		query.append(_FINDER_COLUMN_MARKASDELETEDALL_MARKASDELETED_2);

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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		builder.appendNamedParameterMap("markasdeleted", markasdeleted);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the temp debit note where debitnotenumber = &#63; or throws a {@link vn.gt.dao.result.NoSuchTempDebitNoteException} if it could not be found.
	 *
	 * @param debitnotenumber the debitnotenumber
	 * @return the matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByF_debitnotenumber(String debitnotenumber)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByF_debitnotenumber(debitnotenumber);

		if (tempDebitNote == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("debitnotenumber=");
			msg.append(debitnotenumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempDebitNoteException(msg.toString());
		}

		return tempDebitNote;
	}

	/**
	 * Returns the temp debit note where debitnotenumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param debitnotenumber the debitnotenumber
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByF_debitnotenumber(String debitnotenumber)
		throws SystemException {
		return fetchByF_debitnotenumber(debitnotenumber, true);
	}

	/**
	 * Returns the temp debit note where debitnotenumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param debitnotenumber the debitnotenumber
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByF_debitnotenumber(String debitnotenumber,
		boolean retrieveFromCache) throws SystemException {
		TempDebitnote tempDebitnote = null;
		if (tempDebitnote == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_1);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_2);
				}
			}

			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempDebitnote.class).build();



				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
				}

				tempDebitnote = (TempDebitnote) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}
		return tempDebitnote;
	}

	/**
	 * Returns the temp debit note where transactionid = &#63; or throws a {@link vn.gt.dao.result.NoSuchTempDebitNoteException} if it could not be found.
	 *
	 * @param transactionid the transactionid
	 * @return the matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByTransactionId(String transactionid)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByTransactionId(transactionid);

		if (tempDebitNote == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("transactionid=");
			msg.append(transactionid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempDebitNoteException(msg.toString());
		}

		return tempDebitNote;
	}

	/**
	 * Returns the temp debit note where transactionid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param transactionid the transactionid
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByTransactionId(String transactionid)
		throws SystemException {
		return fetchByTransactionId(transactionid, true);
	}

	/**
	 * Returns the temp debit note where transactionid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param transactionid the transactionid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByTransactionId(String transactionid,
		boolean retrieveFromCache) throws SystemException {
		TempDebitnote tempDebitnote = null;
		if (tempDebitnote == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			if (transactionid == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_1);
			}
			else {
				if (transactionid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);
				}
			}

			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempDebitnote.class).build();



				if (transactionid != null) {
					builder.appendNamedParameterMap("transactionid", transactionid);
				}

				tempDebitnote = (TempDebitnote) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}
		return tempDebitnote;
	}

	/**
	 * Returns the temp debit note where mariTimeCode = &#63; and itineraryNo = &#63; or throws a {@link vn.gt.dao.result.NoSuchTempDebitNoteException} if it could not be found.
	 *
	 * @param mariTimeCode the mari time code
	 * @param itineraryNo the itinerary no
	 * @return the matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findBymariTimeCode_itineraryNo(int mariTimeCode,
		String itineraryNo)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchBymariTimeCode_itineraryNo(mariTimeCode,
				itineraryNo);

		if (tempDebitNote == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("mariTimeCode=");
			msg.append(mariTimeCode);

			msg.append(", itineraryNo=");
			msg.append(itineraryNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempDebitNoteException(msg.toString());
		}

		return tempDebitNote;
	}

	/**
	 * Returns the temp debit note where mariTimeCode = &#63; and itineraryNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param mariTimeCode the mari time code
	 * @param itineraryNo the itinerary no
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBymariTimeCode_itineraryNo(int mariTimeCode,
		String itineraryNo) throws SystemException {
		return fetchBymariTimeCode_itineraryNo(mariTimeCode, itineraryNo, true);
	}

	/**
	 * Returns the temp debit note where mariTimeCode = &#63; and itineraryNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param mariTimeCode the mari time code
	 * @param itineraryNo the itinerary no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchBymariTimeCode_itineraryNo(int mariTimeCode,
		String itineraryNo, boolean retrieveFromCache)
		throws SystemException {
		TempDebitnote tempDebitnote = null;
		if (tempDebitnote == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_MARITIMECODE_2);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_2);
				}
			}

			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempDebitnote.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				tempDebitnote = (TempDebitnote) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}
		return tempDebitnote;
	}

	/**
	 * Returns the temp debit note where itineraryNo = &#63; and documentName = &#63; and documentYear = &#63; or throws a {@link vn.gt.dao.result.NoSuchTempDebitNoteException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByitineraryNo_documentName_documentYear(
		String itineraryNo, long documentName, int documentYear)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByitineraryNo_documentName_documentYear(itineraryNo,
				documentName, documentYear);

		if (tempDebitNote == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchTempDebitNoteException(msg.toString());
		}

		return tempDebitNote;
	}

	/**
	 * Returns the temp debit note where itineraryNo = &#63; and documentName = &#63; and documentYear = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByitineraryNo_documentName_documentYear(
		String itineraryNo, long documentName, int documentYear)
		throws SystemException {
		return fetchByitineraryNo_documentName_documentYear(itineraryNo,
			documentName, documentYear, true);
	}

	/**
	 * Returns the temp debit note where itineraryNo = &#63; and documentName = &#63; and documentYear = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByitineraryNo_documentName_documentYear(
		String itineraryNo, long documentName, int documentYear,
		boolean retrieveFromCache) throws SystemException {
		TempDebitnote tempDebitnote = null;
		if (tempDebitnote == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2);

			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(TempDebitnote.class).build();



				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				tempDebitnote = (TempDebitnote) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}
		return tempDebitnote;
	}

	/**
	 * Returns all the temp debit notes where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByItineraryNo(String itineraryNo)
		throws SystemException {
		return findByItineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByItineraryNo(String itineraryNo, int start,
		int end) throws SystemException {
		return findByItineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findByItineraryNo(String itineraryNo, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> tempDebitnote = null;
		if (tempDebitnote == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				tempDebitnote = (List<TempDebitnote>)queryFactory.getResultList(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}

		return tempDebitnote;
	}

	/**
	 * Returns the first temp debit note in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByItineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByItineraryNo_First(itineraryNo,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the first temp debit note in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByItineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = findByItineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp debit note in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote findByItineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = fetchByItineraryNo_Last(itineraryNo,
				orderByComparator);

		if (tempDebitNote != null) {
			return tempDebitNote;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempDebitNoteException(msg.toString());
	}

	/**
	 * Returns the last temp debit note in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp debit note, or <code>null</code> if a matching temp debit note could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote fetchByItineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByItineraryNo(itineraryNo);

		List<TempDebitnote> list = findByItineraryNo(itineraryNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp debit notes before and after the current temp debit note in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current temp debit note
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp debit note
	 * @throws vn.gt.dao.result.NoSuchTempDebitNoteException if a temp debit note with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote[] findByItineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByPrimaryKey(id);



		try {


			TempDebitnote[] array = new TempDebitnote[3];

			array[0] = getByItineraryNo_PrevAndNext(tempDebitNote,
					itineraryNo, orderByComparator, true);

			array[1] = tempDebitNote;

			array[2] = getByItineraryNo_PrevAndNext(tempDebitNote,
					itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected TempDebitnote getByItineraryNo_PrevAndNext(
		TempDebitnote tempDebitNote, String itineraryNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPDEBITNOTE_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
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
			query.append(TempDebitNoteModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		if (itineraryNo != null) {
			builder.appendNamedParameterMap("itineraryNo", itineraryNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempDebitNote);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempDebitnote> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp debit notes.
	 *
	 * @return the temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp debit notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @return the range of temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp debit notes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp debit notes
	 * @param end the upper bound of the range of temp debit notes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempDebitnote> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempDebitnote> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPDEBITNOTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPDEBITNOTE.concat(TempDebitNoteModelImpl.ORDER_BY_JPQL);
			}



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempDebitnote>) queryFactory.getResultList(builder);
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
	 * Removes all the temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYearDone(long documentName,
		int documentYear, int markasdeleted) throws SystemException {
		for (TempDebitnote tempDebitNote : findByDocumentNameAnddocumentYearDone(
				documentName, documentYear, markasdeleted)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (TempDebitnote tempDebitNote : findByDocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63; from the database.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYearMarkasdeleted(
		int mariTimeCode, long documentName, int documentYear, int markasdeleted)
		throws SystemException {
		for (TempDebitnote tempDebitNote : findByDocumentNameAnddocumentYearMarkasdeleted(
				mariTimeCode, documentName, documentYear, markasdeleted)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63; from the database.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentNameSearch(int mariTimeCode, int markasdeleted,
		long documentName, String documentTypeCode) throws SystemException {
		for (TempDebitnote tempDebitNote : findBydocumentNameSearch(
				mariTimeCode, markasdeleted, documentName, documentTypeCode)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63; from the database.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode)
		throws SystemException {
		for (TempDebitnote tempDebitNote : findBydebitnotenumberSearch(
				mariTimeCode, markasdeleted, debitnotenumber, documentTypeCode)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63; from the database.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMarkasdeleted(int markasdeleted, int mariTimeCode,
		String documentTypeCode) throws SystemException {
		for (TempDebitnote tempDebitNote : findByMarkasdeleted(markasdeleted,
				mariTimeCode, documentTypeCode)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes where markasdeleted = &#63; from the database.
	 *
	 * @param markasdeleted the markasdeleted
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMarkasdeletedAll(int markasdeleted)
		throws SystemException {
		for (TempDebitnote tempDebitNote : findByMarkasdeletedAll(markasdeleted)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes the temp debit note where debitnotenumber = &#63; from the database.
	 *
	 * @param debitnotenumber the debitnotenumber
	 * @return the temp debit note that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote removeByF_debitnotenumber(String debitnotenumber)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByF_debitnotenumber(debitnotenumber);

		repository.delete(tempDebitNote);
			return tempDebitNote;
	}

	/**
	 * Removes the temp debit note where transactionid = &#63; from the database.
	 *
	 * @param transactionid the transactionid
	 * @return the temp debit note that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote removeByTransactionId(String transactionid)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByTransactionId(transactionid);

		repository.delete(tempDebitNote);
			return tempDebitNote;
	}

	/**
	 * Removes the temp debit note where mariTimeCode = &#63; and itineraryNo = &#63; from the database.
	 *
	 * @param mariTimeCode the mari time code
	 * @param itineraryNo the itinerary no
	 * @return the temp debit note that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote removeBymariTimeCode_itineraryNo(int mariTimeCode,
		String itineraryNo)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findBymariTimeCode_itineraryNo(mariTimeCode,
				itineraryNo);

		repository.delete(tempDebitNote);
			return tempDebitNote;
	}

	/**
	 * Removes the temp debit note where itineraryNo = &#63; and documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the temp debit note that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public TempDebitnote removeByitineraryNo_documentName_documentYear(
		String itineraryNo, long documentName, int documentYear)
		throws NoSuchTempDebitNoteException, SystemException {
		TempDebitnote tempDebitNote = findByitineraryNo_documentName_documentYear(itineraryNo,
				documentName, documentYear);

		repository.delete(tempDebitNote);
			return tempDebitNote;
	}

	/**
	 * Removes all the temp debit notes where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByItineraryNo(String itineraryNo)
		throws SystemException {
		for (TempDebitnote tempDebitNote : findByItineraryNo(itineraryNo)) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Removes all the temp debit notes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempDebitnote tempDebitNote : findAll()) {
			repository.delete(tempDebitNote);
		}
	}

	/**
	 * Returns the number of temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearDone(long documentName,
		int documentYear, int markasdeleted) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_2);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

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
	 * Returns the number of temp debit notes where documentName = &#63; and documentYear = &#63; and markasdeleted = any &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleteds the markasdeleteds
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearDone(long documentName,
		int documentYear, int[] markasdeleteds) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_5);

			conjunctionable = true;

			if ((markasdeleteds == null) || (markasdeleteds.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < markasdeleteds.length; i++) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_5);

					if ((i + 1) < markasdeleteds.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (markasdeleteds != null) {
					builder.appendNamedParameterMap("markasdeleteds", markasdeleteds);
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
	 * Returns the number of temp debit notes where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and documentName = &#63; and documentYear = &#63; and markasdeleted = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param markasdeleted the markasdeleted
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearMarkasdeleted(
		int mariTimeCode, long documentName, int documentYear, int markasdeleted)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARKASDELETED_2);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCode the document type code
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameSearch(int mariTimeCode, int markasdeleted,
		long documentName, String documentTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("documentName", documentName);

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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and documentName = &#63; and documentTypeCode = any &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param documentName the document name
	 * @param documentTypeCodes the document type codes
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameSearch(int mariTimeCode, int markasdeleted,
		long documentName, String[] documentTypeCodes)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_5);

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("documentName", documentName);

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCode the document type code
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String documentTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_2);

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_2);

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_1);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_2);
				}
			}

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
				}

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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and markasdeleted = &#63; and debitnotenumber = &#63; and documentTypeCode = any &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param markasdeleted the markasdeleted
	 * @param debitnotenumber the debitnotenumber
	 * @param documentTypeCodes the document type codes
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydebitnotenumberSearch(int mariTimeCode,
		int markasdeleted, String debitnotenumber, String[] documentTypeCodes)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_4);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_6);
				}
				else {
					query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_5);
				}
			}

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
				}

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
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
	 * Returns the number of temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCode the document type code
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMarkasdeleted(int markasdeleted, int mariTimeCode,
		String documentTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_2);

			query.append(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_2);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

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
	 * Returns the number of temp debit notes where markasdeleted = &#63; and mariTimeCode = &#63; and documentTypeCode = any &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @param mariTimeCode the mari time code
	 * @param documentTypeCodes the document type codes
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMarkasdeleted(int markasdeleted, int mariTimeCode,
		String[] documentTypeCodes) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler();

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			boolean conjunctionable = false;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_5);

			conjunctionable = true;

			if (conjunctionable) {
				query.append(WHERE_AND);
			}

			query.append(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_5);

			conjunctionable = true;

			if ((documentTypeCodes == null) || (documentTypeCodes.length > 0)) {
				if (conjunctionable) {
					query.append(WHERE_AND);
				}

				query.append(StringPool.OPEN_PARENTHESIS);

				for (int i = 0; i < documentTypeCodes.length; i++) {
					String documentTypeCode = documentTypeCodes[i];

					if (documentTypeCode == null) {
						query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_4);
					}
					else {
						if (documentTypeCode.equals(StringPool.BLANK)) {
							query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_6);
						}
						else {
							query.append(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_5);
						}
					}

					if ((i + 1) < documentTypeCodes.length) {
						query.append(WHERE_OR);
					}
				}

				query.append(StringPool.CLOSE_PARENTHESIS);

				conjunctionable = true;
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				if (documentTypeCodes != null) {
					builder.appendNamedParameterMap("documentTypeCodes", documentTypeCodes);
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
	 * Returns the number of temp debit notes where markasdeleted = &#63;.
	 *
	 * @param markasdeleted the markasdeleted
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMarkasdeletedAll(int markasdeleted)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARKASDELETEDALL_MARKASDELETED_2);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("markasdeleted", markasdeleted);

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
	 * Returns the number of temp debit notes where debitnotenumber = &#63;.
	 *
	 * @param debitnotenumber the debitnotenumber
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_debitnotenumber(String debitnotenumber)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			if (debitnotenumber == null) {
				query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_1);
			}
			else {
				if (debitnotenumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (debitnotenumber != null) {
					builder.appendNamedParameterMap("debitnotenumber", debitnotenumber);
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
	 * Returns the number of temp debit notes where transactionid = &#63;.
	 *
	 * @param transactionid the transactionid
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByTransactionId(String transactionid)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			if (transactionid == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_1);
			}
			else {
				if (transactionid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (transactionid != null) {
					builder.appendNamedParameterMap("transactionid", transactionid);
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
	 * Returns the number of temp debit notes where mariTimeCode = &#63; and itineraryNo = &#63;.
	 *
	 * @param mariTimeCode the mari time code
	 * @param itineraryNo the itinerary no
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymariTimeCode_itineraryNo(int mariTimeCode,
		String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_MARITIMECODE_2);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("mariTimeCode", mariTimeCode);

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
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
	 * Returns the number of temp debit notes where itineraryNo = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentName_documentYear(
		String itineraryNo, long documentName, int documentYear)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

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
	 * Returns the number of temp debit notes where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByItineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPDEBITNOTE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
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
	 * Returns the number of temp debit notes.
	 *
	 * @return the number of temp debit notes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {


			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPDEBITNOTE).build();

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
	 * Initializes the temp debit note persistence.
	 */
	private static final String _SQL_SELECT_TEMPDEBITNOTE = "SELECT tempDebitNote FROM TempDebitnote tempDebitNote";
	private static final String _SQL_SELECT_TEMPDEBITNOTE_WHERE = "SELECT tempDebitNote FROM TempDebitnote tempDebitNote WHERE ";
	private static final String _SQL_COUNT_TEMPDEBITNOTE = "SELECT COUNT(tempDebitNote) FROM TempDebitnote tempDebitNote";
	private static final String _SQL_COUNT_TEMPDEBITNOTE_WHERE = "SELECT COUNT(tempDebitNote) FROM TempDebitnote tempDebitNote WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_2 =
		"tempDebitNote.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTNAME_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_2 =
		"tempDebitNote.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_DOCUMENTYEAR_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_2 =
		"tempDebitNote.markasdeleted =:markasdeleted";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARDONE_MARKASDELETED_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempDebitNote.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempDebitNote.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARITIMECODE_2 =
		"tempDebitNote.mariTimeCode =:mariTimeCode AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTNAME_2 =
		"tempDebitNote.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_DOCUMENTYEAR_2 =
		"tempDebitNote.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMARKASDELETED_MARKASDELETED_2 =
		"tempDebitNote.markasdeleted =:markasdeleted";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_2 =
		"tempDebitNote.mariTimeCode =:mariTimeCode AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARITIMECODE_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_2 =
		"tempDebitNote.markasdeleted =:markasdeleted AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_MARKASDELETED_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_2 =
		"tempDebitNote.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTNAME_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_1 =
		"tempDebitNote.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_2 =
		"tempDebitNote.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_3 =
		"(tempDebitNote.documentTypeCode IS NULL OR tempDebitNote.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_4 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_1) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_2) +
		")";
	private static final String _FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_6 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DOCUMENTNAMESEARCH_DOCUMENTTYPECODE_3) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_2 =
		"tempDebitNote.mariTimeCode =:mariTimeCode AND ";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARITIMECODE_2) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_2 =
		"tempDebitNote.markasdeleted =:markasdeleted AND ";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_MARKASDELETED_2) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_1 =
		"tempDebitNote.debitnotenumber IS NULL AND ";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_2 =
		"tempDebitNote.debitnotenumber =:debitnotenumber AND ";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_3 =
		"(tempDebitNote.debitnotenumber IS NULL OR tempDebitNote.debitnotenumber =:debitnotenumber) AND ";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_4 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_1) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_2) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_6 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DEBITNOTENUMBER_3) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_1 =
		"tempDebitNote.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_2 =
		"tempDebitNote.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_3 =
		"(tempDebitNote.documentTypeCode IS NULL OR tempDebitNote.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_4 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_1) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_5 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_2) +
		")";
	private static final String _FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_6 =
		"(" +
		_removeConjunction(_FINDER_COLUMN_DEBITNOTENUMBERSEARCH_DOCUMENTTYPECODE_3) +
		")";
	private static final String _FINDER_COLUMN_MARKASDELETED_MARKASDELETED_2 = "tempDebitNote.markasdeleted =:markasdeleted AND ";
	private static final String _FINDER_COLUMN_MARKASDELETED_MARKASDELETED_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_MARKASDELETED_MARKASDELETED_2) + ")";
	private static final String _FINDER_COLUMN_MARKASDELETED_MARITIMECODE_2 = "tempDebitNote.mariTimeCode =:mariTimeCode AND ";
	private static final String _FINDER_COLUMN_MARKASDELETED_MARITIMECODE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_MARKASDELETED_MARITIMECODE_2) + ")";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_1 = "tempDebitNote.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_2 = "tempDebitNote.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_3 = "(tempDebitNote.documentTypeCode IS NULL OR tempDebitNote.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_4 = "(" +
		_removeConjunction(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_1) +
		")";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_5 = "(" +
		_removeConjunction(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_2) +
		")";
	private static final String _FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_6 = "(" +
		_removeConjunction(_FINDER_COLUMN_MARKASDELETED_DOCUMENTTYPECODE_3) +
		")";
	private static final String _FINDER_COLUMN_MARKASDELETEDALL_MARKASDELETED_2 = "tempDebitNote.markasdeleted =:markasdeleted";
	private static final String _FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_1 =
		"tempDebitNote.debitnotenumber IS NULL";
	private static final String _FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_2 =
		"tempDebitNote.debitnotenumber =:debitnotenumber";
	private static final String _FINDER_COLUMN_F_DEBITNOTENUMBER_DEBITNOTENUMBER_3 =
		"(tempDebitNote.debitnotenumber IS NULL OR tempDebitNote.debitnotenumber =:debitnotenumber)";
	private static final String _FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_1 = "tempDebitNote.transactionid IS NULL";
	private static final String _FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_2 = "tempDebitNote.transactionid =:transactionid";
	private static final String _FINDER_COLUMN_TRANSACTIONID_TRANSACTIONID_3 = "(tempDebitNote.transactionid IS NULL OR tempDebitNote.transactionid =:transactionid)";
	private static final String _FINDER_COLUMN_MARITIMECODE_ITINERARYNO_MARITIMECODE_2 =
		"tempDebitNote.mariTimeCode =:mariTimeCode AND ";
	private static final String _FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_1 =
		"tempDebitNote.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_2 =
		"tempDebitNote.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_MARITIMECODE_ITINERARYNO_ITINERARYNO_3 =
		"(tempDebitNote.itineraryNo IS NULL OR tempDebitNote.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_1 =
		"tempDebitNote.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_2 =
		"tempDebitNote.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_ITINERARYNO_3 =
		"(tempDebitNote.itineraryNo IS NULL OR tempDebitNote.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2 =
		"tempDebitNote.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2 =
		"tempDebitNote.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "tempDebitNote.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "tempDebitNote.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(tempDebitNote.itineraryNo IS NULL OR tempDebitNote.itineraryNo =:itineraryNo)";

	private static String _removeConjunction(String sql) {
		int pos = sql.indexOf(" AND ");

		if (pos != -1) {
			sql = sql.substring(0, pos);
		}

		return sql;
	}

	private static final String _ORDER_BY_ENTITY_ALIAS = "tempDebitNote.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempDebitnote exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempDebitnote exists with the key {";
	

	
}
