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

import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
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
import com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleXlineSailingRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleXlineSailingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleXlineSailingPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleXlineSailingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleXlineSailing> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleXlineSailingUtil} to access the vma schedule xline sailing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleXlineSailing create(long id) {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = new VmaScheduleXlineSailing();

		
		//vmaScheduleXlineSailing.setPrimaryKey(id);

		return vmaScheduleXlineSailing;
	}

	/**
	 * Removes the vma schedule xline sailing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule xline sailing
	 * @return the vma schedule xline sailing that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing remove(long id)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule xline sailing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule xline sailing
	 * @return the vma schedule xline sailing that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleXlineSailing remove(Serializable primaryKey)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		

		try {
			

			VmaScheduleXlineSailing vmaScheduleXlineSailing = findByPrimaryKey(primaryKey);

			if (vmaScheduleXlineSailing == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleXlineSailingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleXlineSailing);
			return vmaScheduleXlineSailing;
		}
		catch (NoSuchVmaScheduleXlineSailingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleXlineSailing remove(VmaScheduleXlineSailing VmaScheduleXlineSailing) throws SystemException {
	removeImpl(VmaScheduleXlineSailing);
	return VmaScheduleXlineSailing;
}

protected VmaScheduleXlineSailing removeImpl(
		VmaScheduleXlineSailing vmaScheduleXlineSailing)
		throws SystemException {
		try {
			repository.delete(vmaScheduleXlineSailing);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleXlineSailing;
	}

	
	public VmaScheduleXlineSailing updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleXlineSailing vmaScheduleXlineSailing,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleXlineSailing);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleXlineSailing;
	}

	
	public VmaScheduleXlineSailing findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule xline sailing with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule xline sailing
	 * @return the vma schedule xline sailing
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing findByPrimaryKey(long id)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = fetchByPrimaryKey(id);

		if (vmaScheduleXlineSailing == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleXlineSailingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleXlineSailing;
	}

	/**
	 * Returns the vma schedule xline sailing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule xline sailing
	 * @return the vma schedule xline sailing, or <code>null</code> if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleXlineSailing fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule xline sailing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule xline sailing
	 * @return the vma schedule xline sailing, or <code>null</code> if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = null;

		

		if (vmaScheduleXlineSailing == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleXlineSailing> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleXlineSailing = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleXlineSailing;
	}

	/**
	 * Returns all the vma schedule xline sailings where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @return the matching vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findByshipOperatorCode_scheduleYear_scheduleMonth(
		String shipOperatorCode, int scheduleYear, int scheduleMonth)
		throws SystemException {
		return findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
			scheduleYear, scheduleMonth, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the vma schedule xline sailings where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param start the lower bound of the range of vma schedule xline sailings
	 * @param end the upper bound of the range of vma schedule xline sailings (not inclusive)
	 * @return the range of matching vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findByshipOperatorCode_scheduleYear_scheduleMonth(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		int start, int end) throws SystemException {
		return findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
			scheduleYear, scheduleMonth, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule xline sailings where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param start the lower bound of the range of vma schedule xline sailings
	 * @param end the upper bound of the range of vma schedule xline sailings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findByshipOperatorCode_scheduleYear_scheduleMonth(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleXlineSailing> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_VMASCHEDULEXLINESAILING_WHERE);

			if (shipOperatorCode == null) {
				query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_1);
			}
			else {
				if (shipOperatorCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEYEAR_2);

			query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEMONTH_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipOperatorCode != null) {
					builder.appendNamedParameterMap("shipOperatorCode", shipOperatorCode);
				}

				builder.appendNamedParameterMap("scheduleYear", scheduleYear);

				builder.appendNamedParameterMap("scheduleMonth", scheduleMonth);

				list = (List<VmaScheduleXlineSailing>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule xline sailing in the ordered set where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule xline sailing
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a matching vma schedule xline sailing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing findByshipOperatorCode_scheduleYear_scheduleMonth_First(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = fetchByshipOperatorCode_scheduleYear_scheduleMonth_First(shipOperatorCode,
				scheduleYear, scheduleMonth, orderByComparator);

		if (vmaScheduleXlineSailing != null) {
			return vmaScheduleXlineSailing;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipOperatorCode=");
		msg.append(shipOperatorCode);

		msg.append(", scheduleYear=");
		msg.append(scheduleYear);

		msg.append(", scheduleMonth=");
		msg.append(scheduleMonth);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleXlineSailingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule xline sailing in the ordered set where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule xline sailing, or <code>null</code> if a matching vma schedule xline sailing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing fetchByshipOperatorCode_scheduleYear_scheduleMonth_First(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleXlineSailing> list = findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
				scheduleYear, scheduleMonth, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule xline sailing in the ordered set where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule xline sailing
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a matching vma schedule xline sailing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing findByshipOperatorCode_scheduleYear_scheduleMonth_Last(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = fetchByshipOperatorCode_scheduleYear_scheduleMonth_Last(shipOperatorCode,
				scheduleYear, scheduleMonth, orderByComparator);

		if (vmaScheduleXlineSailing != null) {
			return vmaScheduleXlineSailing;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipOperatorCode=");
		msg.append(shipOperatorCode);

		msg.append(", scheduleYear=");
		msg.append(scheduleYear);

		msg.append(", scheduleMonth=");
		msg.append(scheduleMonth);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleXlineSailingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule xline sailing in the ordered set where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule xline sailing, or <code>null</code> if a matching vma schedule xline sailing could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing fetchByshipOperatorCode_scheduleYear_scheduleMonth_Last(
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
				scheduleYear, scheduleMonth);

		List<VmaScheduleXlineSailing> list = findByshipOperatorCode_scheduleYear_scheduleMonth(shipOperatorCode,
				scheduleYear, scheduleMonth, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule xline sailings before and after the current vma schedule xline sailing in the ordered set where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param id the primary key of the current vma schedule xline sailing
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule xline sailing
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineSailingException if a vma schedule xline sailing with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXlineSailing[] findByshipOperatorCode_scheduleYear_scheduleMonth_PrevAndNext(
		long id, String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleXlineSailingException, SystemException {
		VmaScheduleXlineSailing vmaScheduleXlineSailing = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleXlineSailing[] array = new VmaScheduleXlineSailing[3];

			array[0] = getByshipOperatorCode_scheduleYear_scheduleMonth_PrevAndNext(
					vmaScheduleXlineSailing, shipOperatorCode, scheduleYear,
					scheduleMonth, orderByComparator, true);

			array[1] = vmaScheduleXlineSailing;

			array[2] = getByshipOperatorCode_scheduleYear_scheduleMonth_PrevAndNext(
					vmaScheduleXlineSailing, shipOperatorCode, scheduleYear,
					scheduleMonth, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleXlineSailing getByshipOperatorCode_scheduleYear_scheduleMonth_PrevAndNext(
		 VmaScheduleXlineSailing vmaScheduleXlineSailing,
		String shipOperatorCode, int scheduleYear, int scheduleMonth,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEXLINESAILING_WHERE);

		if (shipOperatorCode == null) {
			query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_1);
		}
		else {
			if (shipOperatorCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_2);
			}
		}

		query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEYEAR_2);

		query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEMONTH_2);

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

		

		if (shipOperatorCode != null) {
			builder.appendNamedParameterMap("shipOperatorCode", shipOperatorCode);
		}

		builder.appendNamedParameterMap("scheduleYear", scheduleYear);

		builder.appendNamedParameterMap("scheduleMonth", scheduleMonth);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleXlineSailing);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleXlineSailing> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule xline sailings.
	 *
	 * @return the vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule xline sailings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule xline sailings
	 * @param end the upper bound of the range of vma schedule xline sailings (not inclusive)
	 * @return the range of vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule xline sailings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule xline sailings
	 * @param end the upper bound of the range of vma schedule xline sailings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXlineSailing> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleXlineSailing> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEXLINESAILING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEXLINESAILING;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleXlineSailing>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule xline sailings where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63; from the database.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByshipOperatorCode_scheduleYear_scheduleMonth(
		String shipOperatorCode, int scheduleYear, int scheduleMonth)
		throws SystemException {
		for (VmaScheduleXlineSailing vmaScheduleXlineSailing : findByshipOperatorCode_scheduleYear_scheduleMonth(
				shipOperatorCode, scheduleYear, scheduleMonth)) {
			repository.delete(vmaScheduleXlineSailing);
		}
	}

	/**
	 * Removes all the vma schedule xline sailings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleXlineSailing vmaScheduleXlineSailing : findAll()) {
			repository.delete(vmaScheduleXlineSailing);
		}
	}

	/**
	 * Returns the number of vma schedule xline sailings where shipOperatorCode = &#63; and scheduleYear = &#63; and scheduleMonth = &#63;.
	 *
	 * @param shipOperatorCode the ship operator code
	 * @param scheduleYear the schedule year
	 * @param scheduleMonth the schedule month
	 * @return the number of matching vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipOperatorCode_scheduleYear_scheduleMonth(
		String shipOperatorCode, int scheduleYear, int scheduleMonth)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEXLINESAILING_WHERE);

			if (shipOperatorCode == null) {
				query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_1);
			}
			else {
				if (shipOperatorCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEYEAR_2);

			query.append(_FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEMONTH_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (shipOperatorCode != null) {
					builder.appendNamedParameterMap("shipOperatorCode", shipOperatorCode);
				}

				builder.appendNamedParameterMap("scheduleYear", scheduleYear);

				builder.appendNamedParameterMap("scheduleMonth", scheduleMonth);

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
	 * Returns the number of vma schedule xline sailings.
	 *
	 * @return the number of vma schedule xline sailings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEXLINESAILING).build();

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
	 * Initializes the vma schedule xline sailing persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEXLINESAILING = "SELECT vmaScheduleXlineSailing FROM VmaScheduleXlineSailing vmaScheduleXlineSailing";
	private static final String _SQL_SELECT_VMASCHEDULEXLINESAILING_WHERE = "SELECT vmaScheduleXlineSailing FROM VmaScheduleXlineSailing vmaScheduleXlineSailing WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULEXLINESAILING = "SELECT COUNT(vmaScheduleXlineSailing) FROM VmaScheduleXlineSailing vmaScheduleXlineSailing";
	private static final String _SQL_COUNT_VMASCHEDULEXLINESAILING_WHERE = "SELECT COUNT(vmaScheduleXlineSailing) FROM VmaScheduleXlineSailing vmaScheduleXlineSailing WHERE ";
	private static final String _FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_1 =
		"vmaScheduleXlineSailing.shipOperatorCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_2 =
		"vmaScheduleXlineSailing.shipOperatorCode =:shipOperatorCode AND ";
	private static final String _FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SHIPOPERATORCODE_3 =
		"(vmaScheduleXlineSailing.shipOperatorCode IS NULL OR vmaScheduleXlineSailing.shipOperatorCode =:shipOperatorCode) AND ";
	private static final String _FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEYEAR_2 =
		"vmaScheduleXlineSailing.scheduleYear =:scheduleYear AND ";
	private static final String _FINDER_COLUMN_SHIPOPERATORCODE_SCHEDULEYEAR_SCHEDULEMONTH_SCHEDULEMONTH_2 =
		"vmaScheduleXlineSailing.scheduleMonth =:scheduleMonth";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleXlineSailing.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleXlineSailing exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleXlineSailing exists with the key {";
	

	
}
