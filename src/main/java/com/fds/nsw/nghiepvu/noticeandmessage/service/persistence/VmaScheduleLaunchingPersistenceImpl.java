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
import com.fds.nsw.nghiepvu.model.VmaScheduleLaunching;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleLaunchingRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleLaunchingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleLaunchingPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleLaunchingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleLaunching> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleLaunchingUtil} to access the vma schedule launching persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleLaunching create(long id) {
		VmaScheduleLaunching vmaScheduleLaunching = new VmaScheduleLaunching();

		
		//vmaScheduleLaunching.setPrimaryKey(id);

		return vmaScheduleLaunching;
	}

	/**
	 * Removes the vma schedule launching with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule launching
	 * @return the vma schedule launching that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching remove(long id)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule launching with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule launching
	 * @return the vma schedule launching that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleLaunching remove(Serializable primaryKey)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		

		try {
			

			VmaScheduleLaunching vmaScheduleLaunching = findByPrimaryKey(primaryKey);

			if (vmaScheduleLaunching == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleLaunchingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleLaunching);
			return vmaScheduleLaunching;
		}
		catch (NoSuchVmaScheduleLaunchingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleLaunching remove(VmaScheduleLaunching VmaScheduleLaunching) throws SystemException {
	removeImpl(VmaScheduleLaunching);
	return VmaScheduleLaunching;
}

protected VmaScheduleLaunching removeImpl(
		VmaScheduleLaunching vmaScheduleLaunching) throws SystemException {
		try {
			repository.delete(vmaScheduleLaunching);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleLaunching;
	}

	
	public VmaScheduleLaunching updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleLaunching vmaScheduleLaunching,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleLaunching);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleLaunching;
	}

	
	public VmaScheduleLaunching findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule launching with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule launching
	 * @return the vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching findByPrimaryKey(long id)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = fetchByPrimaryKey(id);

		if (vmaScheduleLaunching == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleLaunchingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleLaunching;
	}

	/**
	 * Returns the vma schedule launching with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule launching
	 * @return the vma schedule launching, or <code>null</code> if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleLaunching fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule launching with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule launching
	 * @return the vma schedule launching, or <code>null</code> if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = null;

		

		if (vmaScheduleLaunching == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleLaunching> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleLaunching = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleLaunching;
	}

	/**
	 * Returns all the vma schedule launchings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule launchings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @return the range of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule launchings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleLaunching> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULELAUNCHING_WHERE);

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

				list = (List<VmaScheduleLaunching>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule launching in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleLaunching != null) {
			return vmaScheduleLaunching;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleLaunchingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule launching in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule launching, or <code>null</code> if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleLaunching> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule launching in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleLaunching != null) {
			return vmaScheduleLaunching;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleLaunchingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule launching in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule launching, or <code>null</code> if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleLaunching> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule launchings before and after the current vma schedule launching in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule launching
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleLaunching[] array = new VmaScheduleLaunching[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleLaunching, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleLaunching;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleLaunching, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleLaunching getByitineraryNo_PrevAndNext(
		 VmaScheduleLaunching vmaScheduleLaunching,
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

		query.append(_SQL_SELECT_VMASCHEDULELAUNCHING_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleLaunching);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleLaunching> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule launchings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule launchings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @return the range of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule launchings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleLaunching> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULELAUNCHING_WHERE);

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

				list = (List<VmaScheduleLaunching>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule launching in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleLaunching != null) {
			return vmaScheduleLaunching;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleLaunchingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule launching in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule launching, or <code>null</code> if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleLaunching> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule launching in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleLaunching != null) {
			return vmaScheduleLaunching;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleLaunchingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule launching in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule launching, or <code>null</code> if a matching vma schedule launching could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaScheduleLaunching> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule launchings before and after the current vma schedule launching in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule launching
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule launching
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleLaunchingException if a vma schedule launching with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleLaunching[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleLaunchingException, SystemException {
		VmaScheduleLaunching vmaScheduleLaunching = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleLaunching[] array = new VmaScheduleLaunching[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleLaunching, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaScheduleLaunching;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleLaunching, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleLaunching getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaScheduleLaunching vmaScheduleLaunching,
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

		query.append(_SQL_SELECT_VMASCHEDULELAUNCHING_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleLaunching);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleLaunching> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule launchings.
	 *
	 * @return the vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule launchings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @return the range of vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule launchings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule launchings
	 * @param end the upper bound of the range of vma schedule launchings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleLaunching> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleLaunching> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULELAUNCHING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULELAUNCHING;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleLaunching>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule launchings where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleLaunching vmaScheduleLaunching : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleLaunching);
		}
	}

	/**
	 * Removes all the vma schedule launchings where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaScheduleLaunching vmaScheduleLaunching : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaScheduleLaunching);
		}
	}

	/**
	 * Removes all the vma schedule launchings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleLaunching vmaScheduleLaunching : findAll()) {
			repository.delete(vmaScheduleLaunching);
		}
	}

	/**
	 * Returns the number of vma schedule launchings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULELAUNCHING_WHERE);

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
	 * Returns the number of vma schedule launchings where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULELAUNCHING_WHERE);

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
	 * Returns the number of vma schedule launchings.
	 *
	 * @return the number of vma schedule launchings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULELAUNCHING).build();

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
	 * Initializes the vma schedule launching persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULELAUNCHING = "SELECT vmaScheduleLaunching FROM VmaScheduleLaunching vmaScheduleLaunching";
	private static final String _SQL_SELECT_VMASCHEDULELAUNCHING_WHERE = "SELECT vmaScheduleLaunching FROM VmaScheduleLaunching vmaScheduleLaunching WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULELAUNCHING = "SELECT COUNT(vmaScheduleLaunching) FROM VmaScheduleLaunching vmaScheduleLaunching";
	private static final String _SQL_COUNT_VMASCHEDULELAUNCHING_WHERE = "SELECT COUNT(vmaScheduleLaunching) FROM VmaScheduleLaunching vmaScheduleLaunching WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleLaunching.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleLaunching.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleLaunching.itineraryNo IS NULL OR vmaScheduleLaunching.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaScheduleLaunching.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaScheduleLaunching.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaScheduleLaunching.itineraryNo IS NULL OR vmaScheduleLaunching.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaScheduleLaunching.noticeShipType =:noticeShipType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleLaunching.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleLaunching exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleLaunching exists with the key {";
	

	
}
