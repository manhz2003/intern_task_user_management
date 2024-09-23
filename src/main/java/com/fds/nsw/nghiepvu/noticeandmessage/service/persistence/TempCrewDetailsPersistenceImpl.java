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
import com.fds.nsw.nghiepvu.model.TempCrewDetails;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempCrewDetailRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempCrewDetailsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempCrewDetailsPersistenceImpl extends BasePersistence {
	@Autowired
	TempCrewDetailRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempCrewDetails> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempCrewDetailsUtil} to access the temp crew details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempCrewDetails create(long id) {
		TempCrewDetails tempCrewDetails = new TempCrewDetails();

		
		//tempCrewDetails.setPrimaryKey(id);

		return tempCrewDetails;
	}

	/**
	 * Removes the temp crew details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew details
	 * @return the temp crew details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails remove(long id)
		throws NoSuchTempCrewDetailsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp crew details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp crew details
	 * @return the temp crew details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewDetails remove(Serializable primaryKey)
		throws NoSuchTempCrewDetailsException, SystemException {
		

		try {
			

			TempCrewDetails tempCrewDetails = findByPrimaryKey(primaryKey);

			if (tempCrewDetails == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempCrewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempCrewDetails);
			return tempCrewDetails;
		}
		catch (NoSuchTempCrewDetailsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempCrewDetails remove(TempCrewDetails TempCrewDetails) throws SystemException {
	removeImpl(TempCrewDetails);
	return TempCrewDetails;
}

protected TempCrewDetails removeImpl(TempCrewDetails tempCrewDetails)
		throws SystemException {
		try {
			repository.delete(tempCrewDetails);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewDetails;
	}

	
	public TempCrewDetails updateImpl(
		com.fds.nsw.nghiepvu.model.TempCrewDetails tempCrewDetails,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempCrewDetails);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewDetails;
	}

	
	public TempCrewDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew details with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException} if it could not be found.
	 *
	 * @param id the primary key of the temp crew details
	 * @return the temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByPrimaryKey(long id)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByPrimaryKey(id);

		if (tempCrewDetails == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempCrewDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempCrewDetails;
	}

	/**
	 * Returns the temp crew details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp crew details
	 * @return the temp crew details, or <code>null</code> if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewDetails fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp crew details
	 * @return the temp crew details, or <code>null</code> if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByPrimaryKey(long id) throws SystemException {
		TempCrewDetails tempCrewDetails = null;

		

		if (tempCrewDetails == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempCrewDetails> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempCrewDetails = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempCrewDetails;
	}

	/**
	 * Returns all the temp crew detailses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew detailses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @return the range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew detailses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempCrewDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

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
				query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempCrewDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the first temp crew details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the last temp crew details in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempCrewDetails> list = findByRequestCode(requestCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew detailses before and after the current temp crew details in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp crew details
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = findByPrimaryKey(id);

		

		try {
			

			TempCrewDetails[] array = new TempCrewDetails[3];

			array[0] = getByRequestCode_PrevAndNext(tempCrewDetails,
					requestCode, orderByComparator, true);

			array[1] = tempCrewDetails;

			array[2] = getByRequestCode_PrevAndNext(tempCrewDetails,
					requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewDetails getByRequestCode_PrevAndNext(
		TempCrewDetails tempCrewDetails, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

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
			query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew detailses where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @return the matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByGivenNameAndPassportNumber(
		String givenName, String passportNumber) throws SystemException {
		return findByGivenNameAndPassportNumber(givenName, passportNumber,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew detailses where givenName = &#63; and passportNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @return the range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByGivenNameAndPassportNumber(
		String givenName, String passportNumber, int start, int end)
		throws SystemException {
		return findByGivenNameAndPassportNumber(givenName, passportNumber,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew detailses where givenName = &#63; and passportNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByGivenNameAndPassportNumber(
		String givenName, String passportNumber, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

			if (givenName == null) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_1);
			}
			else {
				if (givenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_2);
				}
			}

			if (passportNumber == null) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_1);
			}
			else {
				if (passportNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (givenName != null) {
					builder.appendNamedParameterMap("givenName", givenName);
				}

				if (passportNumber != null) {
					builder.appendNamedParameterMap("passportNumber", passportNumber);
				}

				list = (List<TempCrewDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew details in the ordered set where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByGivenNameAndPassportNumber_First(
		String givenName, String passportNumber,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByGivenNameAndPassportNumber_First(givenName,
				passportNumber, orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("givenName=");
		msg.append(givenName);

		msg.append(", passportNumber=");
		msg.append(passportNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the first temp crew details in the ordered set where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByGivenNameAndPassportNumber_First(
		String givenName, String passportNumber,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = findByGivenNameAndPassportNumber(givenName,
				passportNumber, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew details in the ordered set where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByGivenNameAndPassportNumber_Last(
		String givenName, String passportNumber,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByGivenNameAndPassportNumber_Last(givenName,
				passportNumber, orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("givenName=");
		msg.append(givenName);

		msg.append(", passportNumber=");
		msg.append(passportNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the last temp crew details in the ordered set where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByGivenNameAndPassportNumber_Last(
		String givenName, String passportNumber,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGivenNameAndPassportNumber(givenName, passportNumber);

		List<TempCrewDetails> list = findByGivenNameAndPassportNumber(givenName,
				passportNumber, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew detailses before and after the current temp crew details in the ordered set where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param id the primary key of the current temp crew details
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails[] findByGivenNameAndPassportNumber_PrevAndNext(
		long id, String givenName, String passportNumber,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = findByPrimaryKey(id);

		

		try {
			

			TempCrewDetails[] array = new TempCrewDetails[3];

			array[0] = getByGivenNameAndPassportNumber_PrevAndNext(
					tempCrewDetails, givenName, passportNumber,
					orderByComparator, true);

			array[1] = tempCrewDetails;

			array[2] = getByGivenNameAndPassportNumber_PrevAndNext(
					tempCrewDetails, givenName, passportNumber,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewDetails getByGivenNameAndPassportNumber_PrevAndNext(
		 TempCrewDetails tempCrewDetails, String givenName,
		String passportNumber, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

		if (givenName == null) {
			query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_1);
		}
		else {
			if (givenName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_2);
			}
		}

		if (passportNumber == null) {
			query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_1);
		}
		else {
			if (passportNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_2);
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
			query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (givenName != null) {
			builder.appendNamedParameterMap("givenName", givenName);
		}

		if (passportNumber != null) {
			builder.appendNamedParameterMap("passportNumber", passportNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew detailses where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @return the matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByCrewCode(String crewcode)
		throws SystemException {
		return findByCrewCode(crewcode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the temp crew detailses where crewcode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param crewcode the crewcode
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @return the range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByCrewCode(String crewcode, int start,
		int end) throws SystemException {
		return findByCrewCode(crewcode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew detailses where crewcode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param crewcode the crewcode
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findByCrewCode(String crewcode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

			if (crewcode == null) {
				query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_1);
			}
			else {
				if (crewcode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (crewcode != null) {
					builder.appendNamedParameterMap("crewcode", crewcode);
				}

				list = (List<TempCrewDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew details in the ordered set where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByCrewCode_First(String crewcode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByCrewCode_First(crewcode,
				orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("crewcode=");
		msg.append(crewcode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the first temp crew details in the ordered set where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByCrewCode_First(String crewcode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = findByCrewCode(crewcode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew details in the ordered set where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails findByCrewCode_Last(String crewcode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = fetchByCrewCode_Last(crewcode,
				orderByComparator);

		if (tempCrewDetails != null) {
			return tempCrewDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("crewcode=");
		msg.append(crewcode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewDetailsException(msg.toString());
	}

	/**
	 * Returns the last temp crew details in the ordered set where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew details, or <code>null</code> if a matching temp crew details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails fetchByCrewCode_Last(String crewcode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByCrewCode(crewcode);

		List<TempCrewDetails> list = findByCrewCode(crewcode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew detailses before and after the current temp crew details in the ordered set where crewcode = &#63;.
	 *
	 * @param id the primary key of the current temp crew details
	 * @param crewcode the crewcode
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewDetailsException if a temp crew details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewDetails[] findByCrewCode_PrevAndNext(long id,
		String crewcode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewDetailsException, SystemException {
		TempCrewDetails tempCrewDetails = findByPrimaryKey(id);

		

		try {
			

			TempCrewDetails[] array = new TempCrewDetails[3];

			array[0] = getByCrewCode_PrevAndNext(tempCrewDetails,
					crewcode, orderByComparator, true);

			array[1] = tempCrewDetails;

			array[2] = getByCrewCode_PrevAndNext(tempCrewDetails,
					crewcode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewDetails getByCrewCode_PrevAndNext(
		TempCrewDetails tempCrewDetails, String crewcode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPCREWDETAILS_WHERE);

		if (crewcode == null) {
			query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_1);
		}
		else {
			if (crewcode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_2);
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
			query.append(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (crewcode != null) {
			builder.appendNamedParameterMap("crewcode", crewcode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew detailses.
	 *
	 * @return the temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @return the range of temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew detailses
	 * @param end the upper bound of the range of temp crew detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewDetails> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewDetails> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPCREWDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPCREWDETAILS.concat(TempCrewDetailsModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempCrewDetails>) queryFactory.getResultList(builder);
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
	 * Removes all the temp crew detailses where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempCrewDetails tempCrewDetails : findByRequestCode(requestCode)) {
			repository.delete(tempCrewDetails);
		}
	}

	/**
	 * Removes all the temp crew detailses where givenName = &#63; and passportNumber = &#63; from the database.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGivenNameAndPassportNumber(String givenName,
		String passportNumber) throws SystemException {
		for (TempCrewDetails tempCrewDetails : findByGivenNameAndPassportNumber(
				givenName, passportNumber)) {
			repository.delete(tempCrewDetails);
		}
	}

	/**
	 * Removes all the temp crew detailses where crewcode = &#63; from the database.
	 *
	 * @param crewcode the crewcode
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCrewCode(String crewcode) throws SystemException {
		for (TempCrewDetails tempCrewDetails : findByCrewCode(crewcode)) {
			repository.delete(tempCrewDetails);
		}
	}

	/**
	 * Removes all the temp crew detailses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempCrewDetails tempCrewDetails : findAll()) {
			repository.delete(tempCrewDetails);
		}
	}

	/**
	 * Returns the number of temp crew detailses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPCREWDETAILS_WHERE);

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
	 * Returns the number of temp crew detailses where givenName = &#63; and passportNumber = &#63;.
	 *
	 * @param givenName the given name
	 * @param passportNumber the passport number
	 * @return the number of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGivenNameAndPassportNumber(String givenName,
		String passportNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_TEMPCREWDETAILS_WHERE);

			if (givenName == null) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_1);
			}
			else {
				if (givenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_2);
				}
			}

			if (passportNumber == null) {
				query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_1);
			}
			else {
				if (passportNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (givenName != null) {
					builder.appendNamedParameterMap("givenName", givenName);
				}

				if (passportNumber != null) {
					builder.appendNamedParameterMap("passportNumber", passportNumber);
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
	 * Returns the number of temp crew detailses where crewcode = &#63;.
	 *
	 * @param crewcode the crewcode
	 * @return the number of matching temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCrewCode(String crewcode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPCREWDETAILS_WHERE);

			if (crewcode == null) {
				query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_1);
			}
			else {
				if (crewcode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWCODE_CREWCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (crewcode != null) {
					builder.appendNamedParameterMap("crewcode", crewcode);
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
	 * Returns the number of temp crew detailses.
	 *
	 * @return the number of temp crew detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPCREWDETAILS).build();

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
	 * Initializes the temp crew details persistence.
	 */
	private static final String _SQL_SELECT_TEMPCREWDETAILS = "SELECT tempCrewDetails FROM TempCrewDetails tempCrewDetails";
	private static final String _SQL_SELECT_TEMPCREWDETAILS_WHERE = "SELECT tempCrewDetails FROM TempCrewDetails tempCrewDetails WHERE ";
	private static final String _SQL_COUNT_TEMPCREWDETAILS = "SELECT COUNT(tempCrewDetails) FROM TempCrewDetails tempCrewDetails";
	private static final String _SQL_COUNT_TEMPCREWDETAILS_WHERE = "SELECT COUNT(tempCrewDetails) FROM TempCrewDetails tempCrewDetails WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempCrewDetails.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempCrewDetails.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempCrewDetails.requestCode IS NULL OR tempCrewDetails.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_1 =
		"tempCrewDetails.givenName IS NULL AND ";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_2 =
		"tempCrewDetails.givenName =:givenName AND ";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_GIVENNAME_3 =
		"(tempCrewDetails.givenName IS NULL OR tempCrewDetails.givenName =:givenName) AND ";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_1 =
		"tempCrewDetails.passportNumber IS NULL";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_2 =
		"tempCrewDetails.passportNumber =:passportNumber";
	private static final String _FINDER_COLUMN_GIVENNAMEANDPASSPORTNUMBER_PASSPORTNUMBER_3 =
		"(tempCrewDetails.passportNumber IS NULL OR tempCrewDetails.passportNumber =:passportNumber)";
	private static final String _FINDER_COLUMN_CREWCODE_CREWCODE_1 = "tempCrewDetails.crewcode IS NULL";
	private static final String _FINDER_COLUMN_CREWCODE_CREWCODE_2 = "tempCrewDetails.crewcode =:crewcode";
	private static final String _FINDER_COLUMN_CREWCODE_CREWCODE_3 = "(tempCrewDetails.crewcode IS NULL OR tempCrewDetails.crewcode =:crewcode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempCrewDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempCrewDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempCrewDetails exists with the key {";
	

	
}
