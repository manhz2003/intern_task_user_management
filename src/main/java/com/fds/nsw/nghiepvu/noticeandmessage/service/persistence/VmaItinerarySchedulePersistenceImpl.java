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
import com.fds.nsw.nghiepvu.model.VmaItinerarySchedule;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaItineraryScheduleRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryScheduleModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaItinerarySchedulePersistenceImpl extends BasePersistence {
	@Autowired
	VmaItineraryScheduleRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItinerarySchedule> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaItineraryScheduleUtil} to access the vma itinerary schedule persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaItinerarySchedule create(long id) {
		VmaItinerarySchedule vmaItinerarySchedule = new VmaItinerarySchedule();

		
		//vmaItinerarySchedule.setPrimaryKey(id);

		return vmaItinerarySchedule;
	}

	/**
	 * Removes the vma itinerary schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma itinerary schedule
	 * @return the vma itinerary schedule that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule remove(long id)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma itinerary schedule with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma itinerary schedule
	 * @return the vma itinerary schedule that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItinerarySchedule remove(Serializable primaryKey)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		

		try {
			

			VmaItinerarySchedule vmaItinerarySchedule = findByPrimaryKey(primaryKey);

			if (vmaItinerarySchedule == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaItineraryScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaItinerarySchedule);
			return vmaItinerarySchedule;
		}
		catch (NoSuchVmaItineraryScheduleException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaItinerarySchedule remove(VmaItinerarySchedule VmaItinerarySchedule) throws SystemException {
	removeImpl(VmaItinerarySchedule);
	return VmaItinerarySchedule;
}

protected VmaItinerarySchedule removeImpl(
		VmaItinerarySchedule vmaItinerarySchedule) throws SystemException {
		try {
			repository.delete(vmaItinerarySchedule);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItinerarySchedule;
	}

	
	public VmaItinerarySchedule updateImpl(
		com.fds.nsw.nghiepvu.model.VmaItinerarySchedule vmaItinerarySchedule,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaItinerarySchedule);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItinerarySchedule;
	}

	
	public VmaItinerarySchedule findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary schedule with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException} if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary schedule
	 * @return the vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findByPrimaryKey(long id)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchByPrimaryKey(id);

		if (vmaItinerarySchedule == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaItineraryScheduleException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma itinerary schedule
	 * @return the vma itinerary schedule, or <code>null</code> if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItinerarySchedule fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary schedule with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary schedule
	 * @return the vma itinerary schedule, or <code>null</code> if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByPrimaryKey(long id)
		throws SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = null;

		

		if (vmaItinerarySchedule == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaItinerarySchedule> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaItinerarySchedule = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaItinerarySchedule;
	}

	/**
	 * Returns all the vma itinerary schedules where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary schedules where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @return the range of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary schedules where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerarySchedule> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

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

				list = (List<VmaItinerarySchedule>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary schedule in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaItinerarySchedule != null) {
			return vmaItinerarySchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryScheduleException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary schedule in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerarySchedule> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary schedule in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaItinerarySchedule != null) {
			return vmaItinerarySchedule;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryScheduleException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary schedule in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaItinerarySchedule> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itinerary schedules before and after the current vma itinerary schedule in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary schedule
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = findByPrimaryKey(id);

		

		try {
			

			VmaItinerarySchedule[] array = new VmaItinerarySchedule[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaItinerarySchedule, itineraryNo, orderByComparator, true);

			array[1] = vmaItinerarySchedule;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaItinerarySchedule, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerarySchedule getByitineraryNo_PrevAndNext(
		 VmaItinerarySchedule vmaItinerarySchedule,
		String itineraryNo, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerarySchedule);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerarySchedule> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		if (vmaItinerarySchedule == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", noticeShipType=");
			msg.append(noticeShipType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaItineraryScheduleException(msg.toString());
		}

		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return fetchByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			true);
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, boolean retrieveFromCache)
		throws SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = null;
		if (vmaItinerarySchedule == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

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


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerarySchedule.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				vmaItinerarySchedule = (VmaItinerarySchedule) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findByitineraryNo_certificateNo_noticeShipType(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchByitineraryNo_certificateNo_noticeShipType(itineraryNo,
				noticeShipType, certificateNo);

		if (vmaItinerarySchedule == null) {
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

			throw new NoSuchVmaItineraryScheduleException(msg.toString());
		}

		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_certificateNo_noticeShipType(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws SystemException {
		return fetchByitineraryNo_certificateNo_noticeShipType(itineraryNo,
			noticeShipType, certificateNo, true);
	}

	/**
	 * Returns the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchByitineraryNo_certificateNo_noticeShipType(
		String itineraryNo, int noticeShipType, String certificateNo,
		boolean retrieveFromCache) throws SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = null;
		if (vmaItinerarySchedule == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerarySchedule.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
				}

				vmaItinerarySchedule = (VmaItinerarySchedule) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findBydocumentName_documentYear_noticeShipType(
		long documentName, int documentYear, int noticeShipType)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchBydocumentName_documentYear_noticeShipType(documentName,
				documentYear, noticeShipType);

		if (vmaItinerarySchedule == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", noticeShipType=");
			msg.append(noticeShipType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaItineraryScheduleException(msg.toString());
		}

		return vmaItinerarySchedule;
	}

	/**
	 * Returns the vma itinerary schedule where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchBydocumentName_documentYear_noticeShipType(
		long documentName, int documentYear, int noticeShipType)
		throws SystemException {
		return fetchBydocumentName_documentYear_noticeShipType(documentName,
			documentYear, noticeShipType, true);
	}

	/**
	 * Returns the vma itinerary schedule where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchBydocumentName_documentYear_noticeShipType(
		long documentName, int documentYear, int noticeShipType,
		boolean retrieveFromCache) throws SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = null;
		if (vmaItinerarySchedule == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerarySchedule.class).build();
				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				vmaItinerarySchedule = (VmaItinerarySchedule) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerarySchedule;
	}

	/**
	 * Returns all the vma itinerary schedules where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findBydocumentName_documentYear(
		long documentName, int documentYear) throws SystemException {
		return findBydocumentName_documentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary schedules where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @return the range of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findBydocumentName_documentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findBydocumentName_documentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary schedules where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findBydocumentName_documentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerarySchedule> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<VmaItinerarySchedule>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary schedule in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findBydocumentName_documentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchBydocumentName_documentYear_First(documentName,
				documentYear, orderByComparator);

		if (vmaItinerarySchedule != null) {
			return vmaItinerarySchedule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryScheduleException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary schedule in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchBydocumentName_documentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerarySchedule> list = findBydocumentName_documentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary schedule in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule findBydocumentName_documentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = fetchBydocumentName_documentYear_Last(documentName,
				documentYear, orderByComparator);

		if (vmaItinerarySchedule != null) {
			return vmaItinerarySchedule;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryScheduleException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary schedule in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary schedule, or <code>null</code> if a matching vma itinerary schedule could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule fetchBydocumentName_documentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBydocumentName_documentYear(documentName, documentYear);

		List<VmaItinerarySchedule> list = findBydocumentName_documentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itinerary schedules before and after the current vma itinerary schedule in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary schedule
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary schedule
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryScheduleException if a vma itinerary schedule with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule[] findBydocumentName_documentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = findByPrimaryKey(id);

		

		try {
			

			VmaItinerarySchedule[] array = new VmaItinerarySchedule[3];

			array[0] = getBydocumentName_documentYear_PrevAndNext(
					vmaItinerarySchedule, documentName, documentYear,
					orderByComparator, true);

			array[1] = vmaItinerarySchedule;

			array[2] = getBydocumentName_documentYear_PrevAndNext(
					vmaItinerarySchedule, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerarySchedule getBydocumentName_documentYear_PrevAndNext(
		 VmaItinerarySchedule vmaItinerarySchedule,
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

		query.append(_SQL_SELECT_VMAITINERARYSCHEDULE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2);

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

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerarySchedule);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerarySchedule> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itinerary schedules.
	 *
	 * @return the vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @return the range of vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary schedules.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itinerary schedules
	 * @param end the upper bound of the range of vma itinerary schedules (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerarySchedule> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerarySchedule> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAITINERARYSCHEDULE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAITINERARYSCHEDULE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaItinerarySchedule>) queryFactory.getResultList(builder);
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
	 * Removes all the vma itinerary schedules where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaItinerarySchedule vmaItinerarySchedule : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaItinerarySchedule);
		}
	}

	/**
	 * Removes the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the vma itinerary schedule that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule removeByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		repository.delete(vmaItinerarySchedule);
			return vmaItinerarySchedule;
	}

	/**
	 * Removes the vma itinerary schedule where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the vma itinerary schedule that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule removeByitineraryNo_certificateNo_noticeShipType(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = findByitineraryNo_certificateNo_noticeShipType(itineraryNo,
				noticeShipType, certificateNo);

		repository.delete(vmaItinerarySchedule);
			return vmaItinerarySchedule;
	}

	/**
	 * Removes the vma itinerary schedule where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the vma itinerary schedule that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerarySchedule removeBydocumentName_documentYear_noticeShipType(
		long documentName, int documentYear, int noticeShipType)
		throws NoSuchVmaItineraryScheduleException, SystemException {
		VmaItinerarySchedule vmaItinerarySchedule = findBydocumentName_documentYear_noticeShipType(documentName,
				documentYear, noticeShipType);

		repository.delete(vmaItinerarySchedule);
			return vmaItinerarySchedule;
	}

	/**
	 * Removes all the vma itinerary schedules where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBydocumentName_documentYear(long documentName,
		int documentYear) throws SystemException {
		for (VmaItinerarySchedule vmaItinerarySchedule : findBydocumentName_documentYear(
				documentName, documentYear)) {
			repository.delete(vmaItinerarySchedule);
		}
	}

	/**
	 * Removes all the vma itinerary schedules from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaItinerarySchedule vmaItinerarySchedule : findAll()) {
			repository.delete(vmaItinerarySchedule);
		}
	}

	/**
	 * Returns the number of vma itinerary schedules where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARYSCHEDULE_WHERE);

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
	 * Returns the number of vma itinerary schedules where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARYSCHEDULE_WHERE);

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
	 * Returns the number of vma itinerary schedules where itineraryNo = &#63; and noticeShipType = &#63; and certificateNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param certificateNo the certificate no
	 * @return the number of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_certificateNo_noticeShipType(
		String itineraryNo, int noticeShipType, String certificateNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMAITINERARYSCHEDULE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_2);
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
	 * Returns the number of vma itinerary schedules where documentName = &#63; and documentYear = &#63; and noticeShipType = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentName_documentYear_noticeShipType(
		long documentName, int documentYear, int noticeShipType)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMAITINERARYSCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTYEAR_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

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
	 * Returns the number of vma itinerary schedules where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentName_documentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARYSCHEDULE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2);

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
	 * Returns the number of vma itinerary schedules.
	 *
	 * @return the number of vma itinerary schedules
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAITINERARYSCHEDULE).build();

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
	 * Initializes the vma itinerary schedule persistence.
	 */
	private static final String _SQL_SELECT_VMAITINERARYSCHEDULE = "SELECT vmaItinerarySchedule FROM VmaItinerarySchedule vmaItinerarySchedule";
	private static final String _SQL_SELECT_VMAITINERARYSCHEDULE_WHERE = "SELECT vmaItinerarySchedule FROM VmaItinerarySchedule vmaItinerarySchedule WHERE ";
	private static final String _SQL_COUNT_VMAITINERARYSCHEDULE = "SELECT COUNT(vmaItinerarySchedule) FROM VmaItinerarySchedule vmaItinerarySchedule";
	private static final String _SQL_COUNT_VMAITINERARYSCHEDULE_WHERE = "SELECT COUNT(vmaItinerarySchedule) FROM VmaItinerarySchedule vmaItinerarySchedule WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaItinerarySchedule.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaItinerarySchedule.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaItinerarySchedule.itineraryNo IS NULL OR vmaItinerarySchedule.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaItinerarySchedule.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaItinerarySchedule.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaItinerarySchedule.itineraryNo IS NULL OR vmaItinerarySchedule.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaItinerarySchedule.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaItinerarySchedule.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaItinerarySchedule.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaItinerarySchedule.itineraryNo IS NULL OR vmaItinerarySchedule.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaItinerarySchedule.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_1 =
		"vmaItinerarySchedule.certificateNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_2 =
		"vmaItinerarySchedule.certificateNo =:certificateNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_NOTICESHIPTYPE_CERTIFICATENO_3 =
		"(vmaItinerarySchedule.certificateNo IS NULL OR vmaItinerarySchedule.certificateNo =:certificateNo)";
	private static final String _FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTNAME_2 =
		"vmaItinerarySchedule.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_DOCUMENTYEAR_2 =
		"vmaItinerarySchedule.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaItinerarySchedule.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTNAME_2 =
		"vmaItinerarySchedule.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAME_DOCUMENTYEAR_DOCUMENTYEAR_2 =
		"vmaItinerarySchedule.documentYear =:documentYear";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaItinerarySchedule.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaItinerarySchedule exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaItinerarySchedule exists with the key {";
	

	
}
