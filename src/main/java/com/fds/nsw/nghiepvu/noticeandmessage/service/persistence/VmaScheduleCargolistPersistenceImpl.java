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
import com.fds.nsw.nghiepvu.model.VmaScheduleCargolist;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleCargolistRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleCargolistModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleCargolistPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleCargolistRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleCargolist> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleCargolistUtil} to access the vma schedule cargolist persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleCargolist create(long id) {
		VmaScheduleCargolist vmaScheduleCargolist = new VmaScheduleCargolist();

		
		//vmaScheduleCargolist.setPrimaryKey(id);

		return vmaScheduleCargolist;
	}

	/**
	 * Removes the vma schedule cargolist with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule cargolist
	 * @return the vma schedule cargolist that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist remove(long id)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule cargolist with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule cargolist
	 * @return the vma schedule cargolist that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleCargolist remove(Serializable primaryKey)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		

		try {
			

			VmaScheduleCargolist vmaScheduleCargolist = findByPrimaryKey(primaryKey);

			if (vmaScheduleCargolist == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleCargolistException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleCargolist);
			return vmaScheduleCargolist;
		}
		catch (NoSuchVmaScheduleCargolistException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleCargolist remove(VmaScheduleCargolist VmaScheduleCargolist) throws SystemException {
	removeImpl(VmaScheduleCargolist);
	return VmaScheduleCargolist;
}

protected VmaScheduleCargolist removeImpl(
		VmaScheduleCargolist vmaScheduleCargolist) throws SystemException {
		try {
			repository.delete(vmaScheduleCargolist);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleCargolist;
	}

	
	public VmaScheduleCargolist updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleCargolist vmaScheduleCargolist,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleCargolist);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleCargolist;
	}

	
	public VmaScheduleCargolist findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule cargolist with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule cargolist
	 * @return the vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByPrimaryKey(long id)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByPrimaryKey(id);

		if (vmaScheduleCargolist == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleCargolistException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleCargolist;
	}

	/**
	 * Returns the vma schedule cargolist with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule cargolist
	 * @return the vma schedule cargolist, or <code>null</code> if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleCargolist fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule cargolist with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule cargolist
	 * @return the vma schedule cargolist, or <code>null</code> if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = null;

		

		if (vmaScheduleCargolist == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleCargolist> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleCargolist = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleCargolist;
	}

	/**
	 * Returns all the vma schedule cargolists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule cargolists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @return the range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule cargolists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleCargolist> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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

				list = (List<VmaScheduleCargolist>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleCargolist> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule cargolists before and after the current vma schedule cargolist in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule cargolist
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleCargolist[] array = new VmaScheduleCargolist[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleCargolist;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleCargolist getByitineraryNo_PrevAndNext(
		 VmaScheduleCargolist vmaScheduleCargolist,
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

		query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleCargolist);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleCargolist> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule cargolist where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		if (vmaScheduleCargolist == null) {
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

			throw new NoSuchVmaScheduleCargolistException(msg.toString());
		}

		return vmaScheduleCargolist;
	}

	/**
	 * Returns the vma schedule cargolist where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		return fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
			noticeShipType, sequenceNo, true);
	}

	/**
	 * Returns the vma schedule cargolist where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = null;
		if (vmaScheduleCargolist == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleCargolist.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				vmaScheduleCargolist = (VmaScheduleCargolist) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleCargolist;
	}

	/**
	 * Returns all the vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @return the range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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

				list = (List<VmaScheduleCargolist>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaScheduleCargolist> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule cargolists before and after the current vma schedule cargolist in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule cargolist
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleCargolist[] array = new VmaScheduleCargolist[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaScheduleCargolist;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleCargolist getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaScheduleCargolist vmaScheduleCargolist,
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

		query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleCargolist);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleCargolist> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule cargolists where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryScheduleId(
		long itineraryScheduleId) throws SystemException {
		return findByitineraryScheduleId(itineraryScheduleId,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule cargolists where itineraryScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @return the range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryScheduleId(
		long itineraryScheduleId, int start, int end) throws SystemException {
		return findByitineraryScheduleId(itineraryScheduleId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule cargolists where itineraryScheduleId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryScheduleId(
		long itineraryScheduleId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

			query.append(_FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

				list = (List<VmaScheduleCargolist>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule cargolist in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryScheduleId_First(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryScheduleId_First(itineraryScheduleId,
				orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryScheduleId=");
		msg.append(itineraryScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the first vma schedule cargolist in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryScheduleId_First(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleCargolist> list = findByitineraryScheduleId(itineraryScheduleId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryScheduleId_Last(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryScheduleId_Last(itineraryScheduleId,
				orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryScheduleId=");
		msg.append(itineraryScheduleId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryScheduleId_Last(
		long itineraryScheduleId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByitineraryScheduleId(itineraryScheduleId);

		List<VmaScheduleCargolist> list = findByitineraryScheduleId(itineraryScheduleId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule cargolists before and after the current vma schedule cargolist in the ordered set where itineraryScheduleId = &#63;.
	 *
	 * @param id the primary key of the current vma schedule cargolist
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist[] findByitineraryScheduleId_PrevAndNext(
		long id, long itineraryScheduleId, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleCargolist[] array = new VmaScheduleCargolist[3];

			array[0] = getByitineraryScheduleId_PrevAndNext(
					vmaScheduleCargolist, itineraryScheduleId,
					orderByComparator, true);

			array[1] = vmaScheduleCargolist;

			array[2] = getByitineraryScheduleId_PrevAndNext(
					vmaScheduleCargolist, itineraryScheduleId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleCargolist getByitineraryScheduleId_PrevAndNext(
		 VmaScheduleCargolist vmaScheduleCargolist,
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

		query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleCargolist);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleCargolist> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule cargolists where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode) throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule cargolists where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @return the range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end)
		throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule cargolists where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2);
				}
			}

			if (documentaryCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1);
			}
			else {
				if (documentaryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2);
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

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
				}

				list = (List<VmaScheduleCargolist>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_documentaryCode_First(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the first vma schedule cargolist in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByitineraryNo_documentaryCode_Last(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaScheduleCargolist != null) {
			return vmaScheduleCargolist;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleCargolistException(msg.toString());
	}

	/**
	 * Returns the last vma schedule cargolist in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode);

		List<VmaScheduleCargolist> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule cargolists before and after the current vma schedule cargolist in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param id the primary key of the current vma schedule cargolist
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a vma schedule cargolist with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist[] findByitineraryNo_documentaryCode_PrevAndNext(
		long id, String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleCargolist[] array = new VmaScheduleCargolist[3];

			array[0] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, documentaryCode,
					orderByComparator, true);

			array[1] = vmaScheduleCargolist;

			array[2] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaScheduleCargolist, itineraryNo, documentaryCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleCargolist getByitineraryNo_documentaryCode_PrevAndNext(
		 VmaScheduleCargolist vmaScheduleCargolist,
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2);
			}
		}

		if (documentaryCode == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1);
		}
		else {
			if (documentaryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2);
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

		if (documentaryCode != null) {
			builder.appendNamedParameterMap("documentaryCode", documentaryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleCargolist);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleCargolist> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule cargolist where scheduleAnchorageId = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException} if it could not be found.
	 *
	 * @param scheduleAnchorageId the schedule anchorage ID
	 * @return the matching vma schedule cargolist
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleCargolistException if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist findByF_scheduleAnchorageId(
		long scheduleAnchorageId)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = fetchByF_scheduleAnchorageId(scheduleAnchorageId);

		if (vmaScheduleCargolist == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("scheduleAnchorageId=");
			msg.append(scheduleAnchorageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleCargolistException(msg.toString());
		}

		return vmaScheduleCargolist;
	}

	/**
	 * Returns the vma schedule cargolist where scheduleAnchorageId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param scheduleAnchorageId the schedule anchorage ID
	 * @return the matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByF_scheduleAnchorageId(
		long scheduleAnchorageId) throws SystemException {
		return fetchByF_scheduleAnchorageId(scheduleAnchorageId, true);
	}

	/**
	 * Returns the vma schedule cargolist where scheduleAnchorageId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param scheduleAnchorageId the schedule anchorage ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule cargolist, or <code>null</code> if a matching vma schedule cargolist could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist fetchByF_scheduleAnchorageId(
		long scheduleAnchorageId, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = null;
		if (vmaScheduleCargolist == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASCHEDULECARGOLIST_WHERE);

			query.append(_FINDER_COLUMN_F_SCHEDULEANCHORAGEID_SCHEDULEANCHORAGEID_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleCargolist.class).build();

				builder.appendNamedParameterMap("scheduleAnchorageId", scheduleAnchorageId);

				vmaScheduleCargolist = (VmaScheduleCargolist) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleCargolist;
	}

	/**
	 * Returns all the vma schedule cargolists.
	 *
	 * @return the vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule cargolists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @return the range of vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule cargolists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule cargolists
	 * @param end the upper bound of the range of vma schedule cargolists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleCargolist> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleCargolist> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULECARGOLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULECARGOLIST;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleCargolist>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule cargolists where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleCargolist vmaScheduleCargolist : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleCargolist);
		}
	}

	/**
	 * Removes the vma schedule cargolist where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the vma schedule cargolist that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist removeByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		repository.delete(vmaScheduleCargolist);
			return vmaScheduleCargolist;
	}

	/**
	 * Removes all the vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaScheduleCargolist vmaScheduleCargolist : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaScheduleCargolist);
		}
	}

	/**
	 * Removes all the vma schedule cargolists where itineraryScheduleId = &#63; from the database.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		for (VmaScheduleCargolist vmaScheduleCargolist : findByitineraryScheduleId(
				itineraryScheduleId)) {
			repository.delete(vmaScheduleCargolist);
		}
	}

	/**
	 * Removes all the vma schedule cargolists where itineraryNo = &#63; and documentaryCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		for (VmaScheduleCargolist vmaScheduleCargolist : findByitineraryNo_documentaryCode(
				itineraryNo, documentaryCode)) {
			repository.delete(vmaScheduleCargolist);
		}
	}

	/**
	 * Removes the vma schedule cargolist where scheduleAnchorageId = &#63; from the database.
	 *
	 * @param scheduleAnchorageId the schedule anchorage ID
	 * @return the vma schedule cargolist that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleCargolist removeByF_scheduleAnchorageId(
		long scheduleAnchorageId)
		throws NoSuchVmaScheduleCargolistException, SystemException {
		VmaScheduleCargolist vmaScheduleCargolist = findByF_scheduleAnchorageId(scheduleAnchorageId);

		repository.delete(vmaScheduleCargolist);
			return vmaScheduleCargolist;
	}

	/**
	 * Removes all the vma schedule cargolists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleCargolist vmaScheduleCargolist : findAll()) {
			repository.delete(vmaScheduleCargolist);
		}
	}

	/**
	 * Returns the number of vma schedule cargolists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

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
	 * Returns the number of vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

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
	 * Returns the number of vma schedule cargolists where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

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
	 * Returns the number of vma schedule cargolists where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

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
	 * Returns the number of vma schedule cargolists where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2);
				}
			}

			if (documentaryCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1);
			}
			else {
				if (documentaryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
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
	 * Returns the number of vma schedule cargolists where scheduleAnchorageId = &#63;.
	 *
	 * @param scheduleAnchorageId the schedule anchorage ID
	 * @return the number of matching vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_scheduleAnchorageId(long scheduleAnchorageId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULECARGOLIST_WHERE);

			query.append(_FINDER_COLUMN_F_SCHEDULEANCHORAGEID_SCHEDULEANCHORAGEID_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				builder.appendNamedParameterMap("scheduleAnchorageId", scheduleAnchorageId);

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
	 * Returns the number of vma schedule cargolists.
	 *
	 * @return the number of vma schedule cargolists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULECARGOLIST).build();

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
	 * Initializes the vma schedule cargolist persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULECARGOLIST = "SELECT vmaScheduleCargolist FROM VmaScheduleCargolist vmaScheduleCargolist";
	private static final String _SQL_SELECT_VMASCHEDULECARGOLIST_WHERE = "SELECT vmaScheduleCargolist FROM VmaScheduleCargolist vmaScheduleCargolist WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULECARGOLIST = "SELECT COUNT(vmaScheduleCargolist) FROM VmaScheduleCargolist vmaScheduleCargolist";
	private static final String _SQL_COUNT_VMASCHEDULECARGOLIST_WHERE = "SELECT COUNT(vmaScheduleCargolist) FROM VmaScheduleCargolist vmaScheduleCargolist WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleCargolist.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleCargolist.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleCargolist.itineraryNo IS NULL OR vmaScheduleCargolist.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_1 =
		"vmaScheduleCargolist.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_2 =
		"vmaScheduleCargolist.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_3 =
		"(vmaScheduleCargolist.itineraryNo IS NULL OR vmaScheduleCargolist.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_NOTICESHIPTYPE_2 =
		"vmaScheduleCargolist.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_SEQUENCENO_2 =
		"vmaScheduleCargolist.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaScheduleCargolist.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaScheduleCargolist.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaScheduleCargolist.itineraryNo IS NULL OR vmaScheduleCargolist.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaScheduleCargolist.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2 =
		"vmaScheduleCargolist.itineraryScheduleId =:itineraryScheduleId";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1 =
		"vmaScheduleCargolist.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2 =
		"vmaScheduleCargolist.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3 =
		"(vmaScheduleCargolist.itineraryNo IS NULL OR vmaScheduleCargolist.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1 =
		"vmaScheduleCargolist.documentaryCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2 =
		"vmaScheduleCargolist.documentaryCode =:documentaryCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3 =
		"(vmaScheduleCargolist.documentaryCode IS NULL OR vmaScheduleCargolist.documentaryCode =:documentaryCode)";
	private static final String _FINDER_COLUMN_F_SCHEDULEANCHORAGEID_SCHEDULEANCHORAGEID_2 =
		"vmaScheduleCargolist.scheduleAnchorageId =:scheduleAnchorageId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleCargolist.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleCargolist exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleCargolist exists with the key {";
	

	
}
