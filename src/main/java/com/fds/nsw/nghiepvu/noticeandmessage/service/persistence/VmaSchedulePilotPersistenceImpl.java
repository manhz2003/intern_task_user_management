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
import com.fds.nsw.nghiepvu.model.VmaSchedulePilot;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaSchedulePilotRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaSchedulePilotModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaSchedulePilotPersistenceImpl extends BasePersistence {
	@Autowired
	VmaSchedulePilotRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaSchedulePilot> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaSchedulePilotUtil} to access the vma schedule pilot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaSchedulePilot create(long id) {
		VmaSchedulePilot vmaSchedulePilot = new VmaSchedulePilot();

		
		//vmaSchedulePilot.setPrimaryKey(id);

		return vmaSchedulePilot;
	}

	/**
	 * Removes the vma schedule pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule pilot
	 * @return the vma schedule pilot that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot remove(long id)
		throws NoSuchVmaSchedulePilotException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule pilot
	 * @return the vma schedule pilot that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaSchedulePilot remove(Serializable primaryKey)
		throws NoSuchVmaSchedulePilotException, SystemException {
		

		try {
			

			VmaSchedulePilot vmaSchedulePilot = findByPrimaryKey(primaryKey);

			if (vmaSchedulePilot == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaSchedulePilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaSchedulePilot);
			return vmaSchedulePilot;
		}
		catch (NoSuchVmaSchedulePilotException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaSchedulePilot remove(VmaSchedulePilot VmaSchedulePilot) throws SystemException {
	removeImpl(VmaSchedulePilot);
	return VmaSchedulePilot;
}

protected VmaSchedulePilot removeImpl(VmaSchedulePilot vmaSchedulePilot)
		throws SystemException {
		try {
			repository.delete(vmaSchedulePilot);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaSchedulePilot;
	}

	
	public VmaSchedulePilot updateImpl(
		com.fds.nsw.nghiepvu.model.VmaSchedulePilot vmaSchedulePilot,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaSchedulePilot);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaSchedulePilot;
	}

	
	public VmaSchedulePilot findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule pilot with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule pilot
	 * @return the vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByPrimaryKey(long id)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByPrimaryKey(id);

		if (vmaSchedulePilot == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaSchedulePilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaSchedulePilot;
	}

	/**
	 * Returns the vma schedule pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule pilot
	 * @return the vma schedule pilot, or <code>null</code> if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaSchedulePilot fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule pilot
	 * @return the vma schedule pilot, or <code>null</code> if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByPrimaryKey(long id)
		throws SystemException {
		VmaSchedulePilot vmaSchedulePilot = null;

		

		if (vmaSchedulePilot == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaSchedulePilot> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaSchedulePilot = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaSchedulePilot;
	}

	/**
	 * Returns all the vma schedule pilots where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilots where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @return the range of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilots where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaSchedulePilot> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

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

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				list = (List<VmaSchedulePilot>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule pilot in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaSchedulePilot != null) {
			return vmaSchedulePilot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotException(msg.toString());
	}

	/**
	 * Returns the first vma schedule pilot in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilot> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule pilot in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaSchedulePilot != null) {
			return vmaSchedulePilot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotException(msg.toString());
	}

	/**
	 * Returns the last vma schedule pilot in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaSchedulePilot> list = findByitineraryNo(itineraryNo, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule pilots before and after the current vma schedule pilot in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule pilot
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = findByPrimaryKey(id);

		

		try {
			

			VmaSchedulePilot[] array = new VmaSchedulePilot[3];

			array[0] = getByitineraryNo_PrevAndNext(vmaSchedulePilot,
					itineraryNo, orderByComparator, true);

			array[1] = vmaSchedulePilot;

			array[2] = getByitineraryNo_PrevAndNext(vmaSchedulePilot,
					itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaSchedulePilot getByitineraryNo_PrevAndNext(
		VmaSchedulePilot vmaSchedulePilot, String itineraryNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (itineraryNo != null) {
			builder.appendNamedParameterMap("itineraryNo", itineraryNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaSchedulePilot);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaSchedulePilot> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		if (vmaSchedulePilot == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", noticeShipType=");
			msg.append(noticeShipType);

			msg.append(", sequenceNo=");
			msg.append(sequenceNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaSchedulePilotException(msg.toString());
		}

		return vmaSchedulePilot;
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		return fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
			noticeShipType, sequenceNo, true);
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo,
		boolean retrieveFromCache) throws SystemException {
		VmaSchedulePilot vmaSchedulePilot = null;
		if (vmaSchedulePilot == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_NOTICESHIPTYPE_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_SEQUENCENO_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaSchedulePilot.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				vmaSchedulePilot = (VmaSchedulePilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaSchedulePilot;
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_noticeShipType_certificateNo(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo,
				noticeShipType, certificateNo);

		if (vmaSchedulePilot == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", noticeShipType=");
			msg.append(noticeShipType);

			msg.append(", certificateNo=");
			msg.append(certificateNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaSchedulePilotException(msg.toString());
		}

		return vmaSchedulePilot;
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_certificateNo(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws SystemException {
		return fetchByitineraryNo_noticeShipType_certificateNo(itineraryNo,
			noticeShipType, certificateNo, true);
	}

	/**
	 * Returns the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_certificateNo(
		String itineraryNo, int noticeShipType, String certificateNo,
		boolean retrieveFromCache) throws SystemException {
		VmaSchedulePilot vmaSchedulePilot = null;
		if (vmaSchedulePilot == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_NOTICESHIPTYPE_2);

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaSchedulePilot.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
				}

				vmaSchedulePilot = (VmaSchedulePilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaSchedulePilot;
	}

	/**
	 * Returns all the vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @return the range of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilot> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				list = (List<VmaSchedulePilot>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule pilot in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaSchedulePilot != null) {
			return vmaSchedulePilot;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotException(msg.toString());
	}

	/**
	 * Returns the first vma schedule pilot in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilot> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule pilot in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaSchedulePilot != null) {
			return vmaSchedulePilot;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotException(msg.toString());
	}

	/**
	 * Returns the last vma schedule pilot in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot, or <code>null</code> if a matching vma schedule pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaSchedulePilot> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule pilots before and after the current vma schedule pilot in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule pilot
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule pilot
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotException if a vma schedule pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = findByPrimaryKey(id);

		

		try {
			

			VmaSchedulePilot[] array = new VmaSchedulePilot[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaSchedulePilot, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaSchedulePilot;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaSchedulePilot, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaSchedulePilot getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaSchedulePilot vmaSchedulePilot, String itineraryNo,
		int noticeShipType, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEPILOT_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (itineraryNo != null) {
			builder.appendNamedParameterMap("itineraryNo", itineraryNo);
		}

		builder.appendNamedParameterMap("noticeShipType", noticeShipType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaSchedulePilot);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaSchedulePilot> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule pilots.
	 *
	 * @return the vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @return the range of vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule pilots
	 * @param end the upper bound of the range of vma schedule pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilot> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilot> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEPILOT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEPILOT;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaSchedulePilot>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule pilots where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaSchedulePilot vmaSchedulePilot : findByitineraryNo(itineraryNo)) {
			repository.delete(vmaSchedulePilot);
		}
	}

	/**
	 * Removes the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the vma schedule pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot removeByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = findByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		repository.delete(vmaSchedulePilot);
			return vmaSchedulePilot;
	}

	/**
	 * Removes the vma schedule pilot where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the vma schedule pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilot removeByitineraryNo_noticeShipType_certificateNo(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws NoSuchVmaSchedulePilotException, SystemException {
		VmaSchedulePilot vmaSchedulePilot = findByitineraryNo_noticeShipType_certificateNo(itineraryNo,
				noticeShipType, certificateNo);

		repository.delete(vmaSchedulePilot);
			return vmaSchedulePilot;
	}

	/**
	 * Removes all the vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaSchedulePilot vmaSchedulePilot : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaSchedulePilot);
		}
	}

	/**
	 * Removes all the vma schedule pilots from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaSchedulePilot vmaSchedulePilot : findAll()) {
			repository.delete(vmaSchedulePilot);
		}
	}

	/**
	 * Returns the number of vma schedule pilots where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEPILOT_WHERE);

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
	 * Returns the number of vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_NOTICESHIPTYPE_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_SEQUENCENO_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

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
	 * Returns the number of vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the number of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType_certificateNo(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_NOTICESHIPTYPE_2);

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
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
	 * Returns the number of vma schedule pilots where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEPILOT_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

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
	 * Returns the number of vma schedule pilots.
	 *
	 * @return the number of vma schedule pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEPILOT).build();

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
	 * Initializes the vma schedule pilot persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEPILOT = "SELECT vmaSchedulePilot FROM VmaSchedulePilot vmaSchedulePilot";
	private static final String _SQL_SELECT_VMASCHEDULEPILOT_WHERE = "SELECT vmaSchedulePilot FROM VmaSchedulePilot vmaSchedulePilot WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULEPILOT = "SELECT COUNT(vmaSchedulePilot) FROM VmaSchedulePilot vmaSchedulePilot";
	private static final String _SQL_COUNT_VMASCHEDULEPILOT_WHERE = "SELECT COUNT(vmaSchedulePilot) FROM VmaSchedulePilot vmaSchedulePilot WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaSchedulePilot.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaSchedulePilot.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaSchedulePilot.itineraryNo IS NULL OR vmaSchedulePilot.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_1 =
		"vmaSchedulePilot.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_2 =
		"vmaSchedulePilot.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_3 =
		"(vmaSchedulePilot.itineraryNo IS NULL OR vmaSchedulePilot.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_NOTICESHIPTYPE_2 =
		"vmaSchedulePilot.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_SEQUENCENO_2 =
		"vmaSchedulePilot.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_1 =
		"vmaSchedulePilot.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_2 =
		"vmaSchedulePilot.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_ITINERARYNO_3 =
		"(vmaSchedulePilot.itineraryNo IS NULL OR vmaSchedulePilot.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_NOTICESHIPTYPE_2 =
		"vmaSchedulePilot.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_1 =
		"vmaSchedulePilot.certificateNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_2 =
		"vmaSchedulePilot.certificateNo =:certificateNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_CERTIFICATENO_CERTIFICATENO_3 =
		"(vmaSchedulePilot.certificateNo IS NULL OR vmaSchedulePilot.certificateNo =:certificateNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaSchedulePilot.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaSchedulePilot.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaSchedulePilot.itineraryNo IS NULL OR vmaSchedulePilot.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaSchedulePilot.noticeShipType =:noticeShipType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaSchedulePilot.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaSchedulePilot exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaSchedulePilot exists with the key {";
	

	
}
