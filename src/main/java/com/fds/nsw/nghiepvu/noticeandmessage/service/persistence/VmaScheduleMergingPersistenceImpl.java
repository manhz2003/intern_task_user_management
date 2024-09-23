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
import com.fds.nsw.nghiepvu.model.VmaScheduleMerging;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleMergingRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleMergingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleMergingPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleMergingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleMerging> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleMergingUtil} to access the vma schedule merging persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleMerging create(long id) {
		VmaScheduleMerging vmaScheduleMerging = new VmaScheduleMerging();

		
		//vmaScheduleMerging.setPrimaryKey(id);

		return vmaScheduleMerging;
	}

	/**
	 * Removes the vma schedule merging with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule merging
	 * @return the vma schedule merging that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging remove(long id)
		throws NoSuchVmaScheduleMergingException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule merging with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule merging
	 * @return the vma schedule merging that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleMerging remove(Serializable primaryKey)
		throws NoSuchVmaScheduleMergingException, SystemException {
		

		try {
			

			VmaScheduleMerging vmaScheduleMerging = findByPrimaryKey(primaryKey);

			if (vmaScheduleMerging == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleMergingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleMerging);
			return vmaScheduleMerging;
		}
		catch (NoSuchVmaScheduleMergingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleMerging remove(VmaScheduleMerging VmaScheduleMerging) throws SystemException {
	removeImpl(VmaScheduleMerging);
	return VmaScheduleMerging;
}

protected VmaScheduleMerging removeImpl(
		VmaScheduleMerging vmaScheduleMerging) throws SystemException {
		try {
			repository.delete(vmaScheduleMerging);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleMerging;
	}

	
	public VmaScheduleMerging updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleMerging vmaScheduleMerging,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleMerging);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleMerging;
	}

	
	public VmaScheduleMerging findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule merging with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule merging
	 * @return the vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByPrimaryKey(long id)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByPrimaryKey(id);

		if (vmaScheduleMerging == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleMergingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleMerging;
	}

	/**
	 * Returns the vma schedule merging with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule merging
	 * @return the vma schedule merging, or <code>null</code> if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleMerging fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule merging with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule merging
	 * @return the vma schedule merging, or <code>null</code> if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleMerging vmaScheduleMerging = null;

		

		if (vmaScheduleMerging == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleMerging> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleMerging = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleMerging;
	}

	/**
	 * Returns all the vma schedule mergings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule mergings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @return the range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule mergings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleMerging> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

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

				list = (List<VmaScheduleMerging>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule merging in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule merging in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleMerging> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleMerging> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule mergings before and after the current vma schedule merging in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule merging
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleMerging[] array = new VmaScheduleMerging[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleMerging, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleMerging;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleMerging, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleMerging getByitineraryNo_PrevAndNext(
		VmaScheduleMerging vmaScheduleMerging, String itineraryNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleMerging);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleMerging> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule mergings where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryScheduleId(
		long itineraryScheduleId) throws SystemException {
		return findByitineraryScheduleId(itineraryScheduleId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule mergings where itineraryScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @return the range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryScheduleId(
		long itineraryScheduleId, int start, int end) throws SystemException {
		return findByitineraryScheduleId(itineraryScheduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule mergings where itineraryScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryScheduleId(
		long itineraryScheduleId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleMerging> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

			query.append(_FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

				list = (List<VmaScheduleMerging>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule merging in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryScheduleId_First(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryScheduleId_First(itineraryScheduleId,
				orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryScheduleId=");
		msg.append(itineraryScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule merging in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryScheduleId_First(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleMerging> list = findByitineraryScheduleId(itineraryScheduleId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryScheduleId_Last(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryScheduleId_Last(itineraryScheduleId,
				orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryScheduleId=");
		msg.append(itineraryScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryScheduleId_Last(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByitineraryScheduleId(itineraryScheduleId);

		List<VmaScheduleMerging> list = findByitineraryScheduleId(itineraryScheduleId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule mergings before and after the current vma schedule merging in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param id the primary key of the current vma schedule merging
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging[] findByitineraryScheduleId_PrevAndNext(long id,
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleMerging[] array = new VmaScheduleMerging[3];

			array[0] = getByitineraryScheduleId_PrevAndNext(
					vmaScheduleMerging, itineraryScheduleId, orderByComparator,
					true);

			array[1] = vmaScheduleMerging;

			array[2] = getByitineraryScheduleId_PrevAndNext(
					vmaScheduleMerging, itineraryScheduleId, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleMerging getByitineraryScheduleId_PrevAndNext(
		 VmaScheduleMerging vmaScheduleMerging,
		long itineraryScheduleId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

		query.append(_FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

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

		

		builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleMerging);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleMerging> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule mergings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule mergings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @return the range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule mergings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleMerging> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

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

				list = (List<VmaScheduleMerging>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule merging in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule merging in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleMerging> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleMerging != null) {
			return vmaScheduleMerging;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleMergingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule merging in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule merging, or <code>null</code> if a matching vma schedule merging could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaScheduleMerging> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule mergings before and after the current vma schedule merging in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule merging
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule merging
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleMergingException if a vma schedule merging with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleMerging[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleMergingException, SystemException {
		VmaScheduleMerging vmaScheduleMerging = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleMerging[] array = new VmaScheduleMerging[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleMerging, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaScheduleMerging;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleMerging, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleMerging getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaScheduleMerging vmaScheduleMerging,
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEMERGING_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleMerging);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleMerging> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule mergings.
	 *
	 * @return the vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule mergings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @return the range of vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule mergings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule mergings
	 * @param end the upper bound of the range of vma schedule mergings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleMerging> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleMerging> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEMERGING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEMERGING;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleMerging>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule mergings where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleMerging vmaScheduleMerging : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleMerging);
		}
	}

	/**
	 * Removes all the vma schedule mergings where itineraryScheduleId = &#63; from the database.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		for (VmaScheduleMerging vmaScheduleMerging : findByitineraryScheduleId(
				itineraryScheduleId)) {
			repository.delete(vmaScheduleMerging);
		}
	}

	/**
	 * Removes all the vma schedule mergings where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaScheduleMerging vmaScheduleMerging : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaScheduleMerging);
		}
	}

	/**
	 * Removes all the vma schedule mergings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleMerging vmaScheduleMerging : findAll()) {
			repository.delete(vmaScheduleMerging);
		}
	}

	/**
	 * Returns the number of vma schedule mergings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEMERGING_WHERE);

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
	 * Returns the number of vma schedule mergings where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the number of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEMERGING_WHERE);

			query.append(_FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

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
	 * Returns the number of vma schedule mergings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEMERGING_WHERE);

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
	 * Returns the number of vma schedule mergings.
	 *
	 * @return the number of vma schedule mergings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEMERGING).build();

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
	 * Initializes the vma schedule merging persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEMERGING = "SELECT vmaScheduleMerging FROM VmaScheduleMerging vmaScheduleMerging";
	private static final String _SQL_SELECT_VMASCHEDULEMERGING_WHERE = "SELECT vmaScheduleMerging FROM VmaScheduleMerging vmaScheduleMerging WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULEMERGING = "SELECT COUNT(vmaScheduleMerging) FROM VmaScheduleMerging vmaScheduleMerging";
	private static final String _SQL_COUNT_VMASCHEDULEMERGING_WHERE = "SELECT COUNT(vmaScheduleMerging) FROM VmaScheduleMerging vmaScheduleMerging WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleMerging.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleMerging.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleMerging.itineraryNo IS NULL OR vmaScheduleMerging.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2 =
		"vmaScheduleMerging.itineraryScheduleId =:itineraryScheduleId";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaScheduleMerging.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaScheduleMerging.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaScheduleMerging.itineraryNo IS NULL OR vmaScheduleMerging.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaScheduleMerging.noticeShipType =:noticeShipType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleMerging.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleMerging exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleMerging exists with the key {";
	

	
}
