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
import com.fds.nsw.nghiepvu.model.VmaItineraryRemark;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaItineraryRemarkRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryRemarksModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaItineraryRemarksPersistenceImpl extends BasePersistence {
	@Autowired
	VmaItineraryRemarkRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItineraryRemark> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaItineraryRemarksUtil} to access the vma itinerary remarks persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaItineraryRemark create(long id) {
		VmaItineraryRemark vmaItineraryRemarks = new VmaItineraryRemark();

		
		//vmaItineraryRemarks.setPrimaryKey(id);

		return vmaItineraryRemarks;
	}

	/**
	 * Removes the vma itinerary remarks with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma itinerary remarks
	 * @return the vma itinerary remarks that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark remove(long id)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma itinerary remarks with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma itinerary remarks
	 * @return the vma itinerary remarks that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItineraryRemark remove(Serializable primaryKey)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		

		try {
			

			VmaItineraryRemark vmaItineraryRemarks = findByPrimaryKey(primaryKey);

			if (vmaItineraryRemarks == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaItineraryRemarksException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaItineraryRemarks);
			return vmaItineraryRemarks;
		}
		catch (NoSuchVmaItineraryRemarksException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaItineraryRemark remove(VmaItineraryRemark VmaItineraryRemark) throws SystemException {
	removeImpl(VmaItineraryRemark);
	return VmaItineraryRemark;
}

protected VmaItineraryRemark removeImpl(
		VmaItineraryRemark vmaItineraryRemarks) throws SystemException {
		try {
			repository.delete(vmaItineraryRemarks);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItineraryRemarks;
	}

	
	public VmaItineraryRemark updateImpl(
		com.fds.nsw.nghiepvu.model.VmaItineraryRemark vmaItineraryRemarks,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaItineraryRemarks);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItineraryRemarks;
	}

	
	public VmaItineraryRemark findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary remarks with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException} if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary remarks
	 * @return the vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByPrimaryKey(long id)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByPrimaryKey(id);

		if (vmaItineraryRemarks == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaItineraryRemarksException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaItineraryRemarks;
	}

	/**
	 * Returns the vma itinerary remarks with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma itinerary remarks
	 * @return the vma itinerary remarks, or <code>null</code> if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItineraryRemark fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary remarks with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary remarks
	 * @return the vma itinerary remarks, or <code>null</code> if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByPrimaryKey(long id)
		throws SystemException {
		VmaItineraryRemark vmaItineraryRemarks = null;

		

		if (vmaItineraryRemarks == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaItineraryRemark> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaItineraryRemarks = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaItineraryRemarks;
	}

	/**
	 * Returns all the vma itinerary remarkses where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary remarkses where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @return the range of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary remarkses where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItineraryRemark> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARYREMARKS_WHERE);

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

				list = (List<VmaItineraryRemark>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary remarks in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaItineraryRemarks != null) {
			return vmaItineraryRemarks;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryRemarksException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary remarks in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItineraryRemark> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary remarks in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaItineraryRemarks != null) {
			return vmaItineraryRemarks;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryRemarksException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary remarks in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaItineraryRemark> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itinerary remarkses before and after the current vma itinerary remarks in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary remarks
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = findByPrimaryKey(id);

		

		try {
			

			VmaItineraryRemark[] array = new VmaItineraryRemark[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaItineraryRemarks, itineraryNo, orderByComparator, true);

			array[1] = vmaItineraryRemarks;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaItineraryRemarks, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItineraryRemark getByitineraryNo_PrevAndNext(
		 VmaItineraryRemark vmaItineraryRemarks,
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

		query.append(_SQL_SELECT_VMAITINERARYREMARKS_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItineraryRemarks);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItineraryRemark> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma itinerary remarks where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		if (vmaItineraryRemarks == null) {
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

			throw new NoSuchVmaItineraryRemarksException(msg.toString());
		}

		return vmaItineraryRemarks;
	}

	/**
	 * Returns the vma itinerary remarks where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		return fetchByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
			noticeShipType, sequenceNo, true);
	}

	/**
	 * Returns the vma itinerary remarks where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo,
		boolean retrieveFromCache) throws SystemException {
		VmaItineraryRemark vmaItineraryRemarks = null;
		if (vmaItineraryRemarks == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMAITINERARYREMARKS_WHERE);

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


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItineraryRemark.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				vmaItineraryRemarks = (VmaItineraryRemark) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItineraryRemarks;
	}

	/**
	 * Returns all the vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @return the range of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItineraryRemark> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAITINERARYREMARKS_WHERE);

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

				list = (List<VmaItineraryRemark>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary remarks in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaItineraryRemarks != null) {
			return vmaItineraryRemarks;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryRemarksException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary remarks in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItineraryRemark> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary remarks in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaItineraryRemarks != null) {
			return vmaItineraryRemarks;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryRemarksException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary remarks in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary remarks, or <code>null</code> if a matching vma itinerary remarks could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaItineraryRemark> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itinerary remarkses before and after the current vma itinerary remarks in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary remarks
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary remarks
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryRemarksException if a vma itinerary remarks with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = findByPrimaryKey(id);

		

		try {
			

			VmaItineraryRemark[] array = new VmaItineraryRemark[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaItineraryRemarks, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaItineraryRemarks;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaItineraryRemarks, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItineraryRemark getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaItineraryRemark vmaItineraryRemarks,
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

		query.append(_SQL_SELECT_VMAITINERARYREMARKS_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItineraryRemarks);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItineraryRemark> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itinerary remarkses.
	 *
	 * @return the vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itinerary remarkses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @return the range of vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itinerary remarkses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itinerary remarkses
	 * @param end the upper bound of the range of vma itinerary remarkses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItineraryRemark> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItineraryRemark> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAITINERARYREMARKS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAITINERARYREMARKS;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaItineraryRemark>) queryFactory.getResultList(builder);
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
	 * Removes all the vma itinerary remarkses where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaItineraryRemark vmaItineraryRemarks : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaItineraryRemarks);
		}
	}

	/**
	 * Removes the vma itinerary remarks where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the vma itinerary remarks that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItineraryRemark removeByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws NoSuchVmaItineraryRemarksException, SystemException {
		VmaItineraryRemark vmaItineraryRemarks = findByitineraryNo_noticeShipType_sequenceNo(itineraryNo,
				noticeShipType, sequenceNo);

		repository.delete(vmaItineraryRemarks);
			return vmaItineraryRemarks;
	}

	/**
	 * Removes all the vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaItineraryRemark vmaItineraryRemarks : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaItineraryRemarks);
		}
	}

	/**
	 * Removes all the vma itinerary remarkses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaItineraryRemark vmaItineraryRemarks : findAll()) {
			repository.delete(vmaItineraryRemarks);
		}
	}

	/**
	 * Returns the number of vma itinerary remarkses where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARYREMARKS_WHERE);

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
	 * Returns the number of vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType_sequenceNo(
		String itineraryNo, int noticeShipType, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMAITINERARYREMARKS_WHERE);

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
	 * Returns the number of vma itinerary remarkses where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARYREMARKS_WHERE);

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
	 * Returns the number of vma itinerary remarkses.
	 *
	 * @return the number of vma itinerary remarkses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAITINERARYREMARKS).build();

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
	 * Initializes the vma itinerary remarks persistence.
	 */
	private static final String _SQL_SELECT_VMAITINERARYREMARKS = "SELECT vmaItineraryRemarks FROM VmaItineraryRemark vmaItineraryRemarks";
	private static final String _SQL_SELECT_VMAITINERARYREMARKS_WHERE = "SELECT vmaItineraryRemarks FROM VmaItineraryRemark vmaItineraryRemarks WHERE ";
	private static final String _SQL_COUNT_VMAITINERARYREMARKS = "SELECT COUNT(vmaItineraryRemarks) FROM VmaItineraryRemark vmaItineraryRemarks";
	private static final String _SQL_COUNT_VMAITINERARYREMARKS_WHERE = "SELECT COUNT(vmaItineraryRemarks) FROM VmaItineraryRemark vmaItineraryRemarks WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaItineraryRemarks.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaItineraryRemarks.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaItineraryRemarks.itineraryNo IS NULL OR vmaItineraryRemarks.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_1 =
		"vmaItineraryRemarks.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_2 =
		"vmaItineraryRemarks.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_ITINERARYNO_3 =
		"(vmaItineraryRemarks.itineraryNo IS NULL OR vmaItineraryRemarks.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_NOTICESHIPTYPE_2 =
		"vmaItineraryRemarks.noticeShipType =:noticeShipType AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_SEQUENCENO_SEQUENCENO_2 =
		"vmaItineraryRemarks.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaItineraryRemarks.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaItineraryRemarks.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaItineraryRemarks.itineraryNo IS NULL OR vmaItineraryRemarks.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaItineraryRemarks.noticeShipType =:noticeShipType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaItineraryRemarks.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaItineraryRemark exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaItineraryRemark exists with the key {";
	

	
}
