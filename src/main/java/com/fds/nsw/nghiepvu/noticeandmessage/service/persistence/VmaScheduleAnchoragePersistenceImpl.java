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
import com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleAnchorageRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleAnchorageModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleAnchoragePersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleAnchorageRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleAnchorage> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleAnchorageUtil} to access the vma schedule anchorage persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleAnchorage create(long id) {
		VmaScheduleAnchorage vmaScheduleAnchorage = new VmaScheduleAnchorage();

		
		//vmaScheduleAnchorage.setPrimaryKey(id);

		return vmaScheduleAnchorage;
	}

	/**
	 * Removes the vma schedule anchorage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule anchorage
	 * @return the vma schedule anchorage that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage remove(long id)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule anchorage with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule anchorage
	 * @return the vma schedule anchorage that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleAnchorage remove(Serializable primaryKey)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		

		try {
			

			VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(primaryKey);

			if (vmaScheduleAnchorage == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleAnchorageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleAnchorage);
			return vmaScheduleAnchorage;
		}
		catch (NoSuchVmaScheduleAnchorageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleAnchorage remove(VmaScheduleAnchorage VmaScheduleAnchorage) throws SystemException {
	removeImpl(VmaScheduleAnchorage);
	return VmaScheduleAnchorage;
}

protected VmaScheduleAnchorage removeImpl(
		VmaScheduleAnchorage vmaScheduleAnchorage) throws SystemException {
		try {
			repository.delete(vmaScheduleAnchorage);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleAnchorage;
	}

	
	public VmaScheduleAnchorage updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleAnchorage vmaScheduleAnchorage,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleAnchorage);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleAnchorage;
	}

	
	public VmaScheduleAnchorage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule anchorage with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule anchorage
	 * @return the vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByPrimaryKey(long id)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByPrimaryKey(id);

		if (vmaScheduleAnchorage == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleAnchorageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule anchorage
	 * @return the vma schedule anchorage, or <code>null</code> if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleAnchorage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule anchorage with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule anchorage
	 * @return the vma schedule anchorage, or <code>null</code> if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = null;

		

		if (vmaScheduleAnchorage == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleAnchorage> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleAnchorage = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleAnchorage;
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleAnchorage> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
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

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule anchorage where itineraryNo = &#63; and sequenceNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo);

		if (vmaScheduleAnchorage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", sequenceNo=");
			msg.append(sequenceNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleAnchorageException(msg.toString());
		}

		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage where itineraryNo = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo) throws SystemException {
		return fetchByitineraryNo_sequenceNo(itineraryNo, sequenceNo, true);
	}

	/**
	 * Returns the vma schedule anchorage where itineraryNo = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = null;
		if (vmaScheduleAnchorage == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleAnchorage.class).build();;

				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				vmaScheduleAnchorage = (VmaScheduleAnchorage) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage where itineraryScheduleId = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException} if it could not be found.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryScheduleId(
		long itineraryScheduleId)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryScheduleId(itineraryScheduleId);

		if (vmaScheduleAnchorage == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryScheduleId=");
			msg.append(itineraryScheduleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleAnchorageException(msg.toString());
		}

		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage where itineraryScheduleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryScheduleId(
		long itineraryScheduleId) throws SystemException {
		return fetchByitineraryScheduleId(itineraryScheduleId, true);
	}

	/**
	 * Returns the vma schedule anchorage where itineraryScheduleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryScheduleId(
		long itineraryScheduleId, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = null;
		if (vmaScheduleAnchorage == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			query.append(_FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleAnchorage.class).build();


				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

				vmaScheduleAnchorage = (VmaScheduleAnchorage) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage where anchorageSupplement = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException} if it could not be found.
	 *
	 * @param anchorageSupplement the anchorage supplement
	 * @return the matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByanchorageSupplement(
		long anchorageSupplement)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByanchorageSupplement(anchorageSupplement);

		if (vmaScheduleAnchorage == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("anchorageSupplement=");
			msg.append(anchorageSupplement);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleAnchorageException(msg.toString());
		}

		return vmaScheduleAnchorage;
	}

	/**
	 * Returns the vma schedule anchorage where anchorageSupplement = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param anchorageSupplement the anchorage supplement
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByanchorageSupplement(
		long anchorageSupplement) throws SystemException {
		return fetchByanchorageSupplement(anchorageSupplement, true);
	}

	/**
	 * Returns the vma schedule anchorage where anchorageSupplement = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param anchorageSupplement the anchorage supplement
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByanchorageSupplement(
		long anchorageSupplement, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = null;
		if (vmaScheduleAnchorage == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			query.append(_FINDER_COLUMN_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleAnchorage.class).build();
				

				builder.appendNamedParameterMap("anchorageSupplement", anchorageSupplement);

				vmaScheduleAnchorage = (VmaScheduleAnchorage) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleAnchorage;
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType_makePayment(
		String itineraryNo, int noticeShipType, int makePayment)
		throws SystemException {
		return findByitineraryNo_noticeShipType_makePayment(itineraryNo,
			noticeShipType, makePayment, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType_makePayment(
		String itineraryNo, int noticeShipType, int makePayment, int start,
		int end) throws SystemException {
		return findByitineraryNo_noticeShipType_makePayment(itineraryNo,
			noticeShipType, makePayment, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType_makePayment(
		String itineraryNo, int noticeShipType, int makePayment, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_NOTICESHIPTYPE_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_MAKEPAYMENT_2);

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

				builder.appendNamedParameterMap("makePayment", makePayment);

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_noticeShipType_makePayment_First(
		String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_noticeShipType_makePayment_First(itineraryNo,
				noticeShipType, makePayment, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_noticeShipType_makePayment_First(
		String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo_noticeShipType_makePayment(itineraryNo,
				noticeShipType, makePayment, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_noticeShipType_makePayment_Last(
		String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_noticeShipType_makePayment_Last(itineraryNo,
				noticeShipType, makePayment, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_noticeShipType_makePayment_Last(
		String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType_makePayment(itineraryNo,
				noticeShipType, makePayment);

		List<VmaScheduleAnchorage> list = findByitineraryNo_noticeShipType_makePayment(itineraryNo,
				noticeShipType, makePayment, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_noticeShipType_makePayment_PrevAndNext(
		long id, String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_noticeShipType_makePayment_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, noticeShipType,
					makePayment, orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_noticeShipType_makePayment_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, noticeShipType,
					makePayment, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_noticeShipType_makePayment_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
		String itineraryNo, int noticeShipType, int makePayment,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_NOTICESHIPTYPE_2);

		query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_MAKEPAYMENT_2);

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

		builder.appendNamedParameterMap("makePayment", makePayment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment(
		String itineraryNo, int makePayment) throws SystemException {
		return findByitineraryNo_makePayment(itineraryNo, makePayment,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment(
		String itineraryNo, int makePayment, int start, int end)
		throws SystemException {
		return findByitineraryNo_makePayment(itineraryNo, makePayment, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment(
		String itineraryNo, int makePayment, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_MAKEPAYMENT_2);

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

				builder.appendNamedParameterMap("makePayment", makePayment);

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_makePayment_First(
		String itineraryNo, int makePayment, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_makePayment_First(itineraryNo,
				makePayment, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_makePayment_First(
		String itineraryNo, int makePayment, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo_makePayment(itineraryNo,
				makePayment, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_makePayment_Last(
		String itineraryNo, int makePayment, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_makePayment_Last(itineraryNo,
				makePayment, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_makePayment_Last(
		String itineraryNo, int makePayment, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByitineraryNo_makePayment(itineraryNo, makePayment);

		List<VmaScheduleAnchorage> list = findByitineraryNo_makePayment(itineraryNo,
				makePayment, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_makePayment_PrevAndNext(
		long id, String itineraryNo, int makePayment,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_makePayment_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, makePayment,
					orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_makePayment_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, makePayment,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_makePayment_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
		String itineraryNo, int makePayment,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_MAKEPAYMENT_2);

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

		builder.appendNamedParameterMap("makePayment", makePayment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment_anchorageSupplement(
		String itineraryNo, int makePayment, long anchorageSupplement)
		throws SystemException {
		return findByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
			makePayment, anchorageSupplement, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment_anchorageSupplement(
		String itineraryNo, int makePayment, long anchorageSupplement,
		int start, int end) throws SystemException {
		return findByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
			makePayment, anchorageSupplement, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_makePayment_anchorageSupplement(
		String itineraryNo, int makePayment, long anchorageSupplement,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_MAKEPAYMENT_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2);

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

				builder.appendNamedParameterMap("makePayment", makePayment);

				builder.appendNamedParameterMap("anchorageSupplement", anchorageSupplement);

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_makePayment_anchorageSupplement_First(
		String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_makePayment_anchorageSupplement_First(itineraryNo,
				makePayment, anchorageSupplement, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(", anchorageSupplement=");
		msg.append(anchorageSupplement);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_makePayment_anchorageSupplement_First(
		String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
				makePayment, anchorageSupplement, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_makePayment_anchorageSupplement_Last(
		String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_makePayment_anchorageSupplement_Last(itineraryNo,
				makePayment, anchorageSupplement, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(", anchorageSupplement=");
		msg.append(anchorageSupplement);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_makePayment_anchorageSupplement_Last(
		String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
				makePayment, anchorageSupplement);

		List<VmaScheduleAnchorage> list = findByitineraryNo_makePayment_anchorageSupplement(itineraryNo,
				makePayment, anchorageSupplement, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_makePayment_anchorageSupplement_PrevAndNext(
		long id, String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_makePayment_anchorageSupplement_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, makePayment,
					anchorageSupplement, orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_makePayment_anchorageSupplement_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, makePayment,
					anchorageSupplement, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_makePayment_anchorageSupplement_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
		String itineraryNo, int makePayment, long anchorageSupplement,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_MAKEPAYMENT_2);

		query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2);

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

		builder.appendNamedParameterMap("makePayment", makePayment);

		builder.appendNamedParameterMap("anchorageSupplement", anchorageSupplement);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode) throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end)
		throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_documentaryCode_First(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_documentaryCode_Last(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode);

		List<VmaScheduleAnchorage> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_documentaryCode_PrevAndNext(
		long id, String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, documentaryCode,
					orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, documentaryCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_documentaryCode_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
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

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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

				list = (List<VmaScheduleAnchorage>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the first vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleAnchorage != null) {
			return vmaScheduleAnchorage;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleAnchorageException(msg.toString());
	}

	/**
	 * Returns the last vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule anchorage, or <code>null</code> if a matching vma schedule anchorage could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaScheduleAnchorage> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule anchorages before and after the current vma schedule anchorage in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule anchorage
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule anchorage
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleAnchorageException if a vma schedule anchorage with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleAnchorage[] array = new VmaScheduleAnchorage[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaScheduleAnchorage;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleAnchorage, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleAnchorage getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaScheduleAnchorage vmaScheduleAnchorage,
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

		query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleAnchorage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleAnchorage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule anchorages.
	 *
	 * @return the vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule anchorages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @return the range of vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule anchorages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule anchorages
	 * @param end the upper bound of the range of vma schedule anchorages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleAnchorage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleAnchorage> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEANCHORAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEANCHORAGE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleAnchorage>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes the vma schedule anchorage where itineraryNo = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the vma schedule anchorage that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage removeByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo);

		repository.delete(vmaScheduleAnchorage);
			return vmaScheduleAnchorage;
	}

	/**
	 * Removes the vma schedule anchorage where itineraryScheduleId = &#63; from the database.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the vma schedule anchorage that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage removeByitineraryScheduleId(
		long itineraryScheduleId)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByitineraryScheduleId(itineraryScheduleId);

		repository.delete(vmaScheduleAnchorage);
			return vmaScheduleAnchorage;
	}

	/**
	 * Removes the vma schedule anchorage where anchorageSupplement = &#63; from the database.
	 *
	 * @param anchorageSupplement the anchorage supplement
	 * @return the vma schedule anchorage that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleAnchorage removeByanchorageSupplement(
		long anchorageSupplement)
		throws NoSuchVmaScheduleAnchorageException, SystemException {
		VmaScheduleAnchorage vmaScheduleAnchorage = findByanchorageSupplement(anchorageSupplement);

		repository.delete(vmaScheduleAnchorage);
			return vmaScheduleAnchorage;
	}

	/**
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType_makePayment(
		String itineraryNo, int noticeShipType, int makePayment)
		throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo_noticeShipType_makePayment(
				itineraryNo, noticeShipType, makePayment)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_makePayment(String itineraryNo,
		int makePayment) throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo_makePayment(
				itineraryNo, makePayment)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_makePayment_anchorageSupplement(
		String itineraryNo, int makePayment, long anchorageSupplement)
		throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo_makePayment_anchorageSupplement(
				itineraryNo, makePayment, anchorageSupplement)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; and documentaryCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo_documentaryCode(
				itineraryNo, documentaryCode)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes all the vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Removes all the vma schedule anchorages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleAnchorage vmaScheduleAnchorage : findAll()) {
			repository.delete(vmaScheduleAnchorage);
		}
	}

	/**
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo(String itineraryNo, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

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
	 * Returns the number of vma schedule anchorages where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

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
	 * Returns the number of vma schedule anchorages where anchorageSupplement = &#63;.
	 *
	 * @param anchorageSupplement the anchorage supplement
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByanchorageSupplement(long anchorageSupplement)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

			query.append(_FINDER_COLUMN_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				builder.appendNamedParameterMap("anchorageSupplement", anchorageSupplement);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param makePayment the make payment
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType_makePayment(
		String itineraryNo, int noticeShipType, int makePayment)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_NOTICESHIPTYPE_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_MAKEPAYMENT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				builder.appendNamedParameterMap("makePayment", makePayment);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_makePayment(String itineraryNo,
		int makePayment) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_MAKEPAYMENT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("makePayment", makePayment);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and makePayment = &#63; and anchorageSupplement = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param makePayment the make payment
	 * @param anchorageSupplement the anchorage supplement
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_makePayment_anchorageSupplement(
		String itineraryNo, int makePayment, long anchorageSupplement)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_MAKEPAYMENT_2);

			query.append(_FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("makePayment", makePayment);

				builder.appendNamedParameterMap("anchorageSupplement", anchorageSupplement);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

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
	 * Returns the number of vma schedule anchorages where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE);

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
	 * Returns the number of vma schedule anchorages.
	 *
	 * @return the number of vma schedule anchorages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEANCHORAGE).build();

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
	 * Initializes the vma schedule anchorage persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEANCHORAGE = "SELECT vmaScheduleAnchorage FROM VmaScheduleAnchorage vmaScheduleAnchorage";
	private static final String _SQL_SELECT_VMASCHEDULEANCHORAGE_WHERE = "SELECT vmaScheduleAnchorage FROM VmaScheduleAnchorage vmaScheduleAnchorage WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULEANCHORAGE = "SELECT COUNT(vmaScheduleAnchorage) FROM VmaScheduleAnchorage vmaScheduleAnchorage";
	private static final String _SQL_COUNT_VMASCHEDULEANCHORAGE_WHERE = "SELECT COUNT(vmaScheduleAnchorage) FROM VmaScheduleAnchorage vmaScheduleAnchorage WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleAnchorage.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleAnchorage.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2 =
		"vmaScheduleAnchorage.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2 =
		"vmaScheduleAnchorage.itineraryScheduleId =:itineraryScheduleId";
	private static final String _FINDER_COLUMN_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2 =
		"vmaScheduleAnchorage.anchorageSupplement =:anchorageSupplement";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_NOTICESHIPTYPE_2 =
		"vmaScheduleAnchorage.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_MAKEPAYMENT_MAKEPAYMENT_2 =
		"vmaScheduleAnchorage.makePayment =:makePayment";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_MAKEPAYMENT_2 =
		"vmaScheduleAnchorage.makePayment =:makePayment";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_MAKEPAYMENT_2 =
		"vmaScheduleAnchorage.makePayment =:makePayment AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_MAKEPAYMENT_ANCHORAGESUPPLEMENT_ANCHORAGESUPPLEMENT_2 =
		"vmaScheduleAnchorage.anchorageSupplement =:anchorageSupplement";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1 =
		"vmaScheduleAnchorage.documentaryCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2 =
		"vmaScheduleAnchorage.documentaryCode =:documentaryCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3 =
		"(vmaScheduleAnchorage.documentaryCode IS NULL OR vmaScheduleAnchorage.documentaryCode =:documentaryCode)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaScheduleAnchorage.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaScheduleAnchorage.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaScheduleAnchorage.itineraryNo IS NULL OR vmaScheduleAnchorage.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaScheduleAnchorage.noticeShipType =:noticeShipType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleAnchorage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleAnchorage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleAnchorage exists with the key {";
	

	
}
