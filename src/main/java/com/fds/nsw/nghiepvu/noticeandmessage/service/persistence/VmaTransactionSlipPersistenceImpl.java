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
import com.fds.nsw.nghiepvu.model.VmaTransactionSlip;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionSlipRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionSlipModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionSlipPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionSlipRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionSlip> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionSlipUtil} to access the vma transaction slip persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionSlip create(long id) {
		VmaTransactionSlip vmaTransactionSlip = new VmaTransactionSlip();

		
		//vmaTransactionSlip.setPrimaryKey(id);

		return vmaTransactionSlip;
	}

	/**
	 * Removes the vma transaction slip with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction slip
	 * @return the vma transaction slip that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip remove(long id)
		throws NoSuchVmaTransactionSlipException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction slip with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction slip
	 * @return the vma transaction slip that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlip remove(Serializable primaryKey)
		throws NoSuchVmaTransactionSlipException, SystemException {
		

		try {
			

			VmaTransactionSlip vmaTransactionSlip = findByPrimaryKey(primaryKey);

			if (vmaTransactionSlip == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionSlipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionSlip);
			return vmaTransactionSlip;
		}
		catch (NoSuchVmaTransactionSlipException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionSlip remove(VmaTransactionSlip VmaTransactionSlip) throws SystemException {
	removeImpl(VmaTransactionSlip);
	return VmaTransactionSlip;
}

protected VmaTransactionSlip removeImpl(
		VmaTransactionSlip vmaTransactionSlip) throws SystemException {
		try {
			repository.delete(vmaTransactionSlip);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlip;
	}

	
	public VmaTransactionSlip updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionSlip vmaTransactionSlip,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionSlip);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlip;
	}

	
	public VmaTransactionSlip findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip
	 * @return the vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByPrimaryKey(long id)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByPrimaryKey(id);

		if (vmaTransactionSlip == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionSlipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction slip
	 * @return the vma transaction slip, or <code>null</code> if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlip fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip
	 * @return the vma transaction slip, or <code>null</code> if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionSlip vmaTransactionSlip = null;

		

		if (vmaTransactionSlip == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionSlip> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionSlip = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns all the vma transaction slips where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slips where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @return the range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slips where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionSlip> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

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

				list = (List<VmaTransactionSlip>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction slip in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the first vma transaction slip in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlip> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaTransactionSlip> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction slips before and after the current vma transaction slip in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma transaction slip
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionSlip[] array = new VmaTransactionSlip[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaTransactionSlip, itineraryNo, orderByComparator, true);

			array[1] = vmaTransactionSlip;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaTransactionSlip, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionSlip getByitineraryNo_PrevAndNext(
		VmaTransactionSlip vmaTransactionSlip, String itineraryNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionSlip);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionSlip> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and sequenceNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_sequenceNo(String itineraryNo,
		int sequenceNo)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo);

		if (vmaTransactionSlip == null) {
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

			throw new NoSuchVmaTransactionSlipException(msg.toString());
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo) throws SystemException {
		return fetchByitineraryNo_sequenceNo(itineraryNo, sequenceNo, true);
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and sequenceNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionSlip vmaTransactionSlip = null;
		if (vmaTransactionSlip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

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

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlip.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				vmaTransactionSlip = (VmaTransactionSlip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and noticeShipType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		if (vmaTransactionSlip == null) {
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

			throw new NoSuchVmaTransactionSlipException(msg.toString());
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return fetchByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			true);
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and noticeShipType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionSlip vmaTransactionSlip = null;
		if (vmaTransactionSlip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

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
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlip.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				vmaTransactionSlip = (VmaTransactionSlip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and documentaryCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode);

		if (vmaTransactionSlip == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", documentaryCode=");
			msg.append(documentaryCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionSlipException(msg.toString());
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and documentaryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode) throws SystemException {
		return fetchByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			true);
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and documentaryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionSlip vmaTransactionSlip = null;
		if (vmaTransactionSlip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

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
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlip.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
				}

				vmaTransactionSlip = (VmaTransactionSlip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlip;
	}

	/**
	 * Returns all the vma transaction slips where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @return the matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo_paymentStatus(
		String itineraryNo, int paymentStatus) throws SystemException {
		return findByitineraryNo_paymentStatus(itineraryNo, paymentStatus,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slips where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @return the range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo_paymentStatus(
		String itineraryNo, int paymentStatus, int start, int end)
		throws SystemException {
		return findByitineraryNo_paymentStatus(itineraryNo, paymentStatus,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slips where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByitineraryNo_paymentStatus(
		String itineraryNo, int paymentStatus, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlip> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_PAYMENTSTATUS_2);

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

				builder.appendNamedParameterMap("paymentStatus", paymentStatus);

				list = (List<VmaTransactionSlip>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction slip in the ordered set where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_paymentStatus_First(
		String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_paymentStatus_First(itineraryNo,
				paymentStatus, orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", paymentStatus=");
		msg.append(paymentStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the first vma transaction slip in the ordered set where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_paymentStatus_First(
		String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlip> list = findByitineraryNo_paymentStatus(itineraryNo,
				paymentStatus, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_paymentStatus_Last(
		String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_paymentStatus_Last(itineraryNo,
				paymentStatus, orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", paymentStatus=");
		msg.append(paymentStatus);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_paymentStatus_Last(
		String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_paymentStatus(itineraryNo, paymentStatus);

		List<VmaTransactionSlip> list = findByitineraryNo_paymentStatus(itineraryNo,
				paymentStatus, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction slips before and after the current vma transaction slip in the ordered set where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param id the primary key of the current vma transaction slip
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip[] findByitineraryNo_paymentStatus_PrevAndNext(
		long id, String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionSlip[] array = new VmaTransactionSlip[3];

			array[0] = getByitineraryNo_paymentStatus_PrevAndNext(
					vmaTransactionSlip, itineraryNo, paymentStatus,
					orderByComparator, true);

			array[1] = vmaTransactionSlip;

			array[2] = getByitineraryNo_paymentStatus_PrevAndNext(
					vmaTransactionSlip, itineraryNo, paymentStatus,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionSlip getByitineraryNo_paymentStatus_PrevAndNext(
		 VmaTransactionSlip vmaTransactionSlip,
		String itineraryNo, int paymentStatus,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_PAYMENTSTATUS_2);

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

		builder.appendNamedParameterMap("paymentStatus", paymentStatus);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionSlip);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionSlip> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and debitnoteid = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByitineraryNo_debitnoteid(itineraryNo,
				debitnoteid);

		if (vmaTransactionSlip == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", debitnoteid=");
			msg.append(debitnoteid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionSlipException(msg.toString());
		}

		return vmaTransactionSlip;
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and debitnoteid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid) throws SystemException {
		return fetchByitineraryNo_debitnoteid(itineraryNo, debitnoteid, true);
	}

	/**
	 * Returns the vma transaction slip where itineraryNo = &#63; and debitnoteid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionSlip vmaTransactionSlip = null;
		if (vmaTransactionSlip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlip.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

				vmaTransactionSlip = (VmaTransactionSlip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlip;
	}

	/**
	 * Returns all the vma transaction slips where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByF_debitnoteid(int debitnoteid)
		throws SystemException {
		return findByF_debitnoteid(debitnoteid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slips where debitnoteid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param debitnoteid the debitnoteid
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @return the range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByF_debitnoteid(int debitnoteid,
		int start, int end) throws SystemException {
		return findByF_debitnoteid(debitnoteid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slips where debitnoteid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param debitnoteid the debitnoteid
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findByF_debitnoteid(int debitnoteid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionSlip> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

			query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

				list = (List<VmaTransactionSlip>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction slip in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByF_debitnoteid_First(int debitnoteid,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByF_debitnoteid_First(debitnoteid,
				orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("debitnoteid=");
		msg.append(debitnoteid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the first vma transaction slip in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByF_debitnoteid_First(int debitnoteid,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlip> list = findByF_debitnoteid(debitnoteid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip findByF_debitnoteid_Last(int debitnoteid,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = fetchByF_debitnoteid_Last(debitnoteid,
				orderByComparator);

		if (vmaTransactionSlip != null) {
			return vmaTransactionSlip;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("debitnoteid=");
		msg.append(debitnoteid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipException(msg.toString());
	}

	/**
	 * Returns the last vma transaction slip in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip, or <code>null</code> if a matching vma transaction slip could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip fetchByF_debitnoteid_Last(int debitnoteid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_debitnoteid(debitnoteid);

		List<VmaTransactionSlip> list = findByF_debitnoteid(debitnoteid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction slips before and after the current vma transaction slip in the ordered set where debitnoteid = &#63;.
	 *
	 * @param id the primary key of the current vma transaction slip
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction slip
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipException if a vma transaction slip with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip[] findByF_debitnoteid_PrevAndNext(long id,
		int debitnoteid, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionSlip[] array = new VmaTransactionSlip[3];

			array[0] = getByF_debitnoteid_PrevAndNext(
					vmaTransactionSlip, debitnoteid, orderByComparator, true);

			array[1] = vmaTransactionSlip;

			array[2] = getByF_debitnoteid_PrevAndNext(
					vmaTransactionSlip, debitnoteid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionSlip getByF_debitnoteid_PrevAndNext(
		 VmaTransactionSlip vmaTransactionSlip,
		int debitnoteid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONSLIP_WHERE);

		query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

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

		

		builder.appendNamedParameterMap("debitnoteid", debitnoteid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionSlip);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionSlip> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction slips.
	 *
	 * @return the vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slips.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @return the range of vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slips.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slips
	 * @param end the upper bound of the range of vma transaction slips (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlip> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlip> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONSLIP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONSLIP;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionSlip>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction slips where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaTransactionSlip vmaTransactionSlip : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaTransactionSlip);
		}
	}

	/**
	 * Removes the vma transaction slip where itineraryNo = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the vma transaction slip that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip removeByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo);

		repository.delete(vmaTransactionSlip);
			return vmaTransactionSlip;
	}

	/**
	 * Removes the vma transaction slip where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the vma transaction slip that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip removeByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		repository.delete(vmaTransactionSlip);
			return vmaTransactionSlip;
	}

	/**
	 * Removes the vma transaction slip where itineraryNo = &#63; and documentaryCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the vma transaction slip that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip removeByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode);

		repository.delete(vmaTransactionSlip);
			return vmaTransactionSlip;
	}

	/**
	 * Removes all the vma transaction slips where itineraryNo = &#63; and paymentStatus = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_paymentStatus(String itineraryNo,
		int paymentStatus) throws SystemException {
		for (VmaTransactionSlip vmaTransactionSlip : findByitineraryNo_paymentStatus(
				itineraryNo, paymentStatus)) {
			repository.delete(vmaTransactionSlip);
		}
	}

	/**
	 * Removes the vma transaction slip where itineraryNo = &#63; and debitnoteid = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the vma transaction slip that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlip removeByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid)
		throws NoSuchVmaTransactionSlipException, SystemException {
		VmaTransactionSlip vmaTransactionSlip = findByitineraryNo_debitnoteid(itineraryNo,
				debitnoteid);

		repository.delete(vmaTransactionSlip);
			return vmaTransactionSlip;
	}

	/**
	 * Removes all the vma transaction slips where debitnoteid = &#63; from the database.
	 *
	 * @param debitnoteid the debitnoteid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_debitnoteid(int debitnoteid)
		throws SystemException {
		for (VmaTransactionSlip vmaTransactionSlip : findByF_debitnoteid(
				debitnoteid)) {
			repository.delete(vmaTransactionSlip);
		}
	}

	/**
	 * Removes all the vma transaction slips from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionSlip vmaTransactionSlip : findAll()) {
			repository.delete(vmaTransactionSlip);
		}
	}

	/**
	 * Returns the number of vma transaction slips where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

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
	 * Returns the number of vma transaction slips where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo(String itineraryNo, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

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
	 * Returns the number of vma transaction slips where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

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
	 * Returns the number of vma transaction slips where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

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
	 * Returns the number of vma transaction slips where itineraryNo = &#63; and paymentStatus = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param paymentStatus the payment status
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_paymentStatus(String itineraryNo,
		int paymentStatus) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_PAYMENTSTATUS_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("paymentStatus", paymentStatus);

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
	 * Returns the number of vma transaction slips where itineraryNo = &#63; and debitnoteid = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_debitnoteid(String itineraryNo,
		int debitnoteid) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

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
	 * Returns the number of vma transaction slips where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @return the number of matching vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_debitnoteid(int debitnoteid) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIP_WHERE);

			query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

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
	 * Returns the number of vma transaction slips.
	 *
	 * @return the number of vma transaction slips
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONSLIP).build();

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
	 * Initializes the vma transaction slip persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONSLIP = "SELECT vmaTransactionSlip FROM VmaTransactionSlip vmaTransactionSlip";
	private static final String _SQL_SELECT_VMATRANSACTIONSLIP_WHERE = "SELECT vmaTransactionSlip FROM VmaTransactionSlip vmaTransactionSlip WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIP = "SELECT COUNT(vmaTransactionSlip) FROM VmaTransactionSlip vmaTransactionSlip";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIP_WHERE = "SELECT COUNT(vmaTransactionSlip) FROM VmaTransactionSlip vmaTransactionSlip WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaTransactionSlip.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaTransactionSlip.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1 =
		"vmaTransactionSlip.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2 =
		"vmaTransactionSlip.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3 =
		"(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2 =
		"vmaTransactionSlip.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaTransactionSlip.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaTransactionSlip.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaTransactionSlip.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1 =
		"vmaTransactionSlip.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2 =
		"vmaTransactionSlip.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3 =
		"(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1 =
		"vmaTransactionSlip.documentaryCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2 =
		"vmaTransactionSlip.documentaryCode =:documentaryCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3 =
		"(vmaTransactionSlip.documentaryCode IS NULL OR vmaTransactionSlip.documentaryCode =:documentaryCode)";
	private static final String _FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_1 =
		"vmaTransactionSlip.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_2 =
		"vmaTransactionSlip.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_ITINERARYNO_3 =
		"(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_PAYMENTSTATUS_PAYMENTSTATUS_2 =
		"vmaTransactionSlip.paymentStatus =:paymentStatus";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1 =
		"vmaTransactionSlip.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2 =
		"vmaTransactionSlip.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3 =
		"(vmaTransactionSlip.itineraryNo IS NULL OR vmaTransactionSlip.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2 =
		"vmaTransactionSlip.debitnoteid =:debitnoteid";
	private static final String _FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2 = "vmaTransactionSlip.debitnoteid =:debitnoteid";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionSlip.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionSlip exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionSlip exists with the key {";
	

	
}
