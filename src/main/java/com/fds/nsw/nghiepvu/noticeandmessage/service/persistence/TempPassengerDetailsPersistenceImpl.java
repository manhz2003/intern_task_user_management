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
import com.fds.nsw.nghiepvu.model.TempPassengerDetails;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempPassengerDetailRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempPassengerDetailsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempPassengerDetailsPersistenceImpl extends BasePersistence {
	@Autowired
	TempPassengerDetailRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempPassengerDetails> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempPassengerDetailsUtil} to access the temp passenger details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempPassengerDetails create(long id) {
		TempPassengerDetails tempPassengerDetails = new TempPassengerDetails();

		
		//tempPassengerDetails.setPrimaryKey(id);

		return tempPassengerDetails;
	}

	/**
	 * Removes the temp passenger details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp passenger details
	 * @return the temp passenger details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails remove(long id)
		throws NoSuchTempPassengerDetailsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp passenger details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp passenger details
	 * @return the temp passenger details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempPassengerDetails remove(Serializable primaryKey)
		throws NoSuchTempPassengerDetailsException, SystemException {
		

		try {
			

			TempPassengerDetails tempPassengerDetails = findByPrimaryKey(primaryKey);

			if (tempPassengerDetails == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempPassengerDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempPassengerDetails);
			return tempPassengerDetails;
		}
		catch (NoSuchTempPassengerDetailsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempPassengerDetails remove(TempPassengerDetails TempPassengerDetails) throws SystemException {
	removeImpl(TempPassengerDetails);
	return TempPassengerDetails;
}

protected TempPassengerDetails removeImpl(
		TempPassengerDetails tempPassengerDetails) throws SystemException {
		try {
			repository.delete(tempPassengerDetails);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempPassengerDetails;
	}

	
	public TempPassengerDetails updateImpl(
		com.fds.nsw.nghiepvu.model.TempPassengerDetails tempPassengerDetails,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempPassengerDetails);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempPassengerDetails;
	}

	
	public TempPassengerDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp passenger details with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException} if it could not be found.
	 *
	 * @param id the primary key of the temp passenger details
	 * @return the temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails findByPrimaryKey(long id)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = fetchByPrimaryKey(id);

		if (tempPassengerDetails == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempPassengerDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempPassengerDetails;
	}

	/**
	 * Returns the temp passenger details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp passenger details
	 * @return the temp passenger details, or <code>null</code> if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempPassengerDetails fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp passenger details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp passenger details
	 * @return the temp passenger details, or <code>null</code> if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails fetchByPrimaryKey(long id)
		throws SystemException {
		TempPassengerDetails tempPassengerDetails = null;

		

		if (tempPassengerDetails == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempPassengerDetails> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempPassengerDetails = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempPassengerDetails;
	}

	/**
	 * Returns all the temp passenger detailses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp passenger detailses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @return the range of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp passenger detailses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempPassengerDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPPASSENGERDETAILS_WHERE);

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
				query.append(TempPassengerDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempPassengerDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first temp passenger details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempPassengerDetails != null) {
			return tempPassengerDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPassengerDetailsException(msg.toString());
	}

	/**
	 * Returns the first temp passenger details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp passenger details, or <code>null</code> if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPassengerDetails> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp passenger details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempPassengerDetails != null) {
			return tempPassengerDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPassengerDetailsException(msg.toString());
	}

	/**
	 * Returns the last temp passenger details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp passenger details, or <code>null</code> if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempPassengerDetails> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp passenger detailses before and after the current temp passenger details in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp passenger details
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = findByPrimaryKey(id);

		

		try {
			

			TempPassengerDetails[] array = new TempPassengerDetails[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempPassengerDetails, requestCode, orderByComparator, true);

			array[1] = tempPassengerDetails;

			array[2] = getByRequestCode_PrevAndNext(
					tempPassengerDetails, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempPassengerDetails getByRequestCode_PrevAndNext(
		 TempPassengerDetails tempPassengerDetails,
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

		query.append(_SQL_SELECT_TEMPPASSENGERDETAILS_WHERE);

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
			query.append(TempPassengerDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempPassengerDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempPassengerDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp passenger detailses where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @return the matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByPassengerCode(String passengerCode)
		throws SystemException {
		return findByPassengerCode(passengerCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp passenger detailses where passengerCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passengerCode the passenger code
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @return the range of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByPassengerCode(
		String passengerCode, int start, int end) throws SystemException {
		return findByPassengerCode(passengerCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp passenger detailses where passengerCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passengerCode the passenger code
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findByPassengerCode(
		String passengerCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPassengerDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPPASSENGERDETAILS_WHERE);

			if (passengerCode == null) {
				query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_1);
			}
			else {
				if (passengerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempPassengerDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (passengerCode != null) {
					builder.appendNamedParameterMap("passengerCode", passengerCode);
				}

				list = (List<TempPassengerDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first temp passenger details in the ordered set where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails findByPassengerCode_First(
		String passengerCode, OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = fetchByPassengerCode_First(passengerCode,
				orderByComparator);

		if (tempPassengerDetails != null) {
			return tempPassengerDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passengerCode=");
		msg.append(passengerCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPassengerDetailsException(msg.toString());
	}

	/**
	 * Returns the first temp passenger details in the ordered set where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp passenger details, or <code>null</code> if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails fetchByPassengerCode_First(
		String passengerCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempPassengerDetails> list = findByPassengerCode(passengerCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp passenger details in the ordered set where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails findByPassengerCode_Last(String passengerCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = fetchByPassengerCode_Last(passengerCode,
				orderByComparator);

		if (tempPassengerDetails != null) {
			return tempPassengerDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passengerCode=");
		msg.append(passengerCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempPassengerDetailsException(msg.toString());
	}

	/**
	 * Returns the last temp passenger details in the ordered set where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp passenger details, or <code>null</code> if a matching temp passenger details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails fetchByPassengerCode_Last(
		String passengerCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPassengerCode(passengerCode);

		List<TempPassengerDetails> list = findByPassengerCode(passengerCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp passenger detailses before and after the current temp passenger details in the ordered set where passengerCode = &#63;.
	 *
	 * @param id the primary key of the current temp passenger details
	 * @param passengerCode the passenger code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp passenger details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempPassengerDetailsException if a temp passenger details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempPassengerDetails[] findByPassengerCode_PrevAndNext(long id,
		String passengerCode, OrderByComparator orderByComparator)
		throws NoSuchTempPassengerDetailsException, SystemException {
		TempPassengerDetails tempPassengerDetails = findByPrimaryKey(id);

		

		try {
			

			TempPassengerDetails[] array = new TempPassengerDetails[3];

			array[0] = getByPassengerCode_PrevAndNext(
					tempPassengerDetails, passengerCode, orderByComparator, true);

			array[1] = tempPassengerDetails;

			array[2] = getByPassengerCode_PrevAndNext(
					tempPassengerDetails, passengerCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempPassengerDetails getByPassengerCode_PrevAndNext(
		 TempPassengerDetails tempPassengerDetails,
		String passengerCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPPASSENGERDETAILS_WHERE);

		if (passengerCode == null) {
			query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_1);
		}
		else {
			if (passengerCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_2);
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
			query.append(TempPassengerDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (passengerCode != null) {
			builder.appendNamedParameterMap("passengerCode", passengerCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempPassengerDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempPassengerDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp passenger detailses.
	 *
	 * @return the temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp passenger detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @return the range of temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp passenger detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp passenger detailses
	 * @param end the upper bound of the range of temp passenger detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempPassengerDetails> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempPassengerDetails> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPPASSENGERDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPPASSENGERDETAILS.concat(TempPassengerDetailsModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempPassengerDetails>) queryFactory.getResultList(builder);
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
	 * Removes all the temp passenger detailses where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempPassengerDetails tempPassengerDetails : findByRequestCode(
				requestCode)) {
			repository.delete(tempPassengerDetails);
		}
	}

	/**
	 * Removes all the temp passenger detailses where passengerCode = &#63; from the database.
	 *
	 * @param passengerCode the passenger code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPassengerCode(String passengerCode)
		throws SystemException {
		for (TempPassengerDetails tempPassengerDetails : findByPassengerCode(
				passengerCode)) {
			repository.delete(tempPassengerDetails);
		}
	}

	/**
	 * Removes all the temp passenger detailses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempPassengerDetails tempPassengerDetails : findAll()) {
			repository.delete(tempPassengerDetails);
		}
	}

	/**
	 * Returns the number of temp passenger detailses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPPASSENGERDETAILS_WHERE);

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
	 * Returns the number of temp passenger detailses where passengerCode = &#63;.
	 *
	 * @param passengerCode the passenger code
	 * @return the number of matching temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPassengerCode(String passengerCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPPASSENGERDETAILS_WHERE);

			if (passengerCode == null) {
				query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_1);
			}
			else {
				if (passengerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (passengerCode != null) {
					builder.appendNamedParameterMap("passengerCode", passengerCode);
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
	 * Returns the number of temp passenger detailses.
	 *
	 * @return the number of temp passenger detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPPASSENGERDETAILS).build();

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
	 * Initializes the temp passenger details persistence.
	 */
	private static final String _SQL_SELECT_TEMPPASSENGERDETAILS = "SELECT tempPassengerDetails FROM TempPassengerDetails tempPassengerDetails";
	private static final String _SQL_SELECT_TEMPPASSENGERDETAILS_WHERE = "SELECT tempPassengerDetails FROM TempPassengerDetails tempPassengerDetails WHERE ";
	private static final String _SQL_COUNT_TEMPPASSENGERDETAILS = "SELECT COUNT(tempPassengerDetails) FROM TempPassengerDetails tempPassengerDetails";
	private static final String _SQL_COUNT_TEMPPASSENGERDETAILS_WHERE = "SELECT COUNT(tempPassengerDetails) FROM TempPassengerDetails tempPassengerDetails WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempPassengerDetails.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempPassengerDetails.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempPassengerDetails.requestCode IS NULL OR tempPassengerDetails.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_1 = "tempPassengerDetails.passengerCode IS NULL";
	private static final String _FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_2 = "tempPassengerDetails.passengerCode =:passengerCode";
	private static final String _FINDER_COLUMN_PASSENGERCODE_PASSENGERCODE_3 = "(tempPassengerDetails.passengerCode IS NULL OR tempPassengerDetails.passengerCode =:passengerCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempPassengerDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempPassengerDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempPassengerDetails exists with the key {";
	

	
}
