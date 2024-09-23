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
import com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleTugboatListRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTugboatListModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleTugboatListPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleTugboatListRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTugboatList> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleTugboatListUtil} to access the vma schedule tugboat list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleTugboatList create(long id) {
		VmaScheduleTugboatList vmaScheduleTugboatList = new VmaScheduleTugboatList();

		
		//vmaScheduleTugboatList.setPrimaryKey(id);

		return vmaScheduleTugboatList;
	}

	/**
	 * Removes the vma schedule tugboat list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule tugboat list
	 * @return the vma schedule tugboat list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList remove(long id)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule tugboat list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule tugboat list
	 * @return the vma schedule tugboat list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleTugboatList remove(Serializable primaryKey)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		

		try {
			

			VmaScheduleTugboatList vmaScheduleTugboatList = findByPrimaryKey(primaryKey);

			if (vmaScheduleTugboatList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleTugboatListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleTugboatList);
			return vmaScheduleTugboatList;
		}
		catch (NoSuchVmaScheduleTugboatListException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleTugboatList remove(VmaScheduleTugboatList VmaScheduleTugboatList) throws SystemException {
	removeImpl(VmaScheduleTugboatList);
	return VmaScheduleTugboatList;
}

protected VmaScheduleTugboatList removeImpl(
		VmaScheduleTugboatList vmaScheduleTugboatList)
		throws SystemException {
		try {
			repository.delete(vmaScheduleTugboatList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleTugboatList;
	}

	
	public VmaScheduleTugboatList updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleTugboatList vmaScheduleTugboatList,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleTugboatList);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleTugboatList;
	}

	
	public VmaScheduleTugboatList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule tugboat list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule tugboat list
	 * @return the vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByPrimaryKey(long id)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByPrimaryKey(id);

		if (vmaScheduleTugboatList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleTugboatListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleTugboatList;
	}

	/**
	 * Returns the vma schedule tugboat list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule tugboat list
	 * @return the vma schedule tugboat list, or <code>null</code> if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleTugboatList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule tugboat list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule tugboat list
	 * @return the vma schedule tugboat list, or <code>null</code> if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = null;

		

		if (vmaScheduleTugboatList == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleTugboatList> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleTugboatList = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleTugboatList;
	}

	/**
	 * Returns all the vma schedule tugboat lists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule tugboat lists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @return the range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule tugboat lists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleTugboatList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

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

				list = (List<VmaScheduleTugboatList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule tugboat list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the first vma schedule tugboat list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTugboatList> list = findByitineraryNo(itineraryNo, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleTugboatList> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule tugboat lists before and after the current vma schedule tugboat list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule tugboat list
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTugboatList[] array = new VmaScheduleTugboatList[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleTugboatList, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleTugboatList;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleTugboatList, itineraryNo, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTugboatList getByitineraryNo_PrevAndNext(
		 VmaScheduleTugboatList vmaScheduleTugboatList,
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

		query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTugboatList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTugboatList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule tugboat list where itineraryNo = &#63; and sequenceNo = &#63; and shipCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param shipCode the ship code
	 * @return the matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByitineraryNo_sequenceNo_shipCode(
		String itineraryNo, int sequenceNo, String shipCode)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByitineraryNo_sequenceNo_shipCode(itineraryNo,
				sequenceNo, shipCode);

		if (vmaScheduleTugboatList == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", sequenceNo=");
			msg.append(sequenceNo);

			msg.append(", shipCode=");
			msg.append(shipCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTugboatListException(msg.toString());
		}

		return vmaScheduleTugboatList;
	}

	/**
	 * Returns the vma schedule tugboat list where itineraryNo = &#63; and sequenceNo = &#63; and shipCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param shipCode the ship code
	 * @return the matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_sequenceNo_shipCode(
		String itineraryNo, int sequenceNo, String shipCode)
		throws SystemException {
		return fetchByitineraryNo_sequenceNo_shipCode(itineraryNo, sequenceNo,
			shipCode, true);
	}

	/**
	 * Returns the vma schedule tugboat list where itineraryNo = &#63; and sequenceNo = &#63; and shipCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param shipCode the ship code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_sequenceNo_shipCode(
		String itineraryNo, int sequenceNo, String shipCode,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = null;
		if (vmaScheduleTugboatList == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SEQUENCENO_2);

			if (shipCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (shipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTugboatList.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				if (shipCode != null) {
					builder.appendNamedParameterMap("shipCode", shipCode);
				}

				vmaScheduleTugboatList = (VmaScheduleTugboatList) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTugboatList;
	}

	/**
	 * Returns all the vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo) throws SystemException {
		return findByitineraryNo_sequenceNo(itineraryNo, sequenceNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @return the range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, int start, int end)
		throws SystemException {
		return findByitineraryNo_sequenceNo(itineraryNo, sequenceNo, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTugboatList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

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

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				list = (List<VmaScheduleTugboatList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule tugboat list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByitineraryNo_sequenceNo_First(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByitineraryNo_sequenceNo_First(itineraryNo,
				sequenceNo, orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the first vma schedule tugboat list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_sequenceNo_First(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleTugboatList> list = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByitineraryNo_sequenceNo_Last(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByitineraryNo_sequenceNo_Last(itineraryNo,
				sequenceNo, orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByitineraryNo_sequenceNo_Last(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByitineraryNo_sequenceNo(itineraryNo, sequenceNo);

		List<VmaScheduleTugboatList> list = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule tugboat lists before and after the current vma schedule tugboat list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule tugboat list
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList[] findByitineraryNo_sequenceNo_PrevAndNext(
		long id, String itineraryNo, int sequenceNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTugboatList[] array = new VmaScheduleTugboatList[3];

			array[0] = getByitineraryNo_sequenceNo_PrevAndNext(
					vmaScheduleTugboatList, itineraryNo, sequenceNo,
					orderByComparator, true);

			array[1] = vmaScheduleTugboatList;

			array[2] = getByitineraryNo_sequenceNo_PrevAndNext(
					vmaScheduleTugboatList, itineraryNo, sequenceNo,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTugboatList getByitineraryNo_sequenceNo_PrevAndNext(
		 VmaScheduleTugboatList vmaScheduleTugboatList,
		String itineraryNo, int sequenceNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

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

		builder.appendNamedParameterMap("sequenceNo", sequenceNo);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTugboatList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTugboatList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule tugboat lists where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @return the matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByshipCode_makePayment(
		String shipCode, int makePayment) throws SystemException {
		return findByshipCode_makePayment(shipCode, makePayment,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule tugboat lists where shipCode = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @return the range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByshipCode_makePayment(
		String shipCode, int makePayment, int start, int end)
		throws SystemException {
		return findByshipCode_makePayment(shipCode, makePayment, start, end,
			null);
	}

	/**
	 * Returns an ordered range of all the vma schedule tugboat lists where shipCode = &#63; and makePayment = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findByshipCode_makePayment(
		String shipCode, int makePayment, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTugboatList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

			if (shipCode == null) {
				query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_1);
			}
			else {
				if (shipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_MAKEPAYMENT_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipCode != null) {
					builder.appendNamedParameterMap("shipCode", shipCode);
				}

				builder.appendNamedParameterMap("makePayment", makePayment);

				list = (List<VmaScheduleTugboatList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule tugboat list in the ordered set where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByshipCode_makePayment_First(
		String shipCode, int makePayment, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByshipCode_makePayment_First(shipCode,
				makePayment, orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipCode=");
		msg.append(shipCode);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the first vma schedule tugboat list in the ordered set where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByshipCode_makePayment_First(
		String shipCode, int makePayment, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleTugboatList> list = findByshipCode_makePayment(shipCode,
				makePayment, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList findByshipCode_makePayment_Last(
		String shipCode, int makePayment, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = fetchByshipCode_makePayment_Last(shipCode,
				makePayment, orderByComparator);

		if (vmaScheduleTugboatList != null) {
			return vmaScheduleTugboatList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipCode=");
		msg.append(shipCode);

		msg.append(", makePayment=");
		msg.append(makePayment);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTugboatListException(msg.toString());
	}

	/**
	 * Returns the last vma schedule tugboat list in the ordered set where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule tugboat list, or <code>null</code> if a matching vma schedule tugboat list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList fetchByshipCode_makePayment_Last(
		String shipCode, int makePayment, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByshipCode_makePayment(shipCode, makePayment);

		List<VmaScheduleTugboatList> list = findByshipCode_makePayment(shipCode,
				makePayment, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule tugboat lists before and after the current vma schedule tugboat list in the ordered set where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param id the primary key of the current vma schedule tugboat list
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule tugboat list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTugboatListException if a vma schedule tugboat list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList[] findByshipCode_makePayment_PrevAndNext(
		long id, String shipCode, int makePayment,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTugboatList[] array = new VmaScheduleTugboatList[3];

			array[0] = getByshipCode_makePayment_PrevAndNext(
					vmaScheduleTugboatList, shipCode, makePayment,
					orderByComparator, true);

			array[1] = vmaScheduleTugboatList;

			array[2] = getByshipCode_makePayment_PrevAndNext(
					vmaScheduleTugboatList, shipCode, makePayment,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTugboatList getByshipCode_makePayment_PrevAndNext(
		 VmaScheduleTugboatList vmaScheduleTugboatList,
		String shipCode, int makePayment, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE);

		if (shipCode == null) {
			query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_1);
		}
		else {
			if (shipCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_2);
			}
		}

		query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_MAKEPAYMENT_2);

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

		

		if (shipCode != null) {
			builder.appendNamedParameterMap("shipCode", shipCode);
		}

		builder.appendNamedParameterMap("makePayment", makePayment);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTugboatList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTugboatList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule tugboat lists.
	 *
	 * @return the vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule tugboat lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @return the range of vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule tugboat lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule tugboat lists
	 * @param end the upper bound of the range of vma schedule tugboat lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTugboatList> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTugboatList> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULETUGBOATLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULETUGBOATLIST;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleTugboatList>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule tugboat lists where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleTugboatList vmaScheduleTugboatList : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleTugboatList);
		}
	}

	/**
	 * Removes the vma schedule tugboat list where itineraryNo = &#63; and sequenceNo = &#63; and shipCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param shipCode the ship code
	 * @return the vma schedule tugboat list that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTugboatList removeByitineraryNo_sequenceNo_shipCode(
		String itineraryNo, int sequenceNo, String shipCode)
		throws NoSuchVmaScheduleTugboatListException, SystemException {
		VmaScheduleTugboatList vmaScheduleTugboatList = findByitineraryNo_sequenceNo_shipCode(itineraryNo,
				sequenceNo, shipCode);

		repository.delete(vmaScheduleTugboatList);
			return vmaScheduleTugboatList;
	}

	/**
	 * Removes all the vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_sequenceNo(String itineraryNo,
		int sequenceNo) throws SystemException {
		for (VmaScheduleTugboatList vmaScheduleTugboatList : findByitineraryNo_sequenceNo(
				itineraryNo, sequenceNo)) {
			repository.delete(vmaScheduleTugboatList);
		}
	}

	/**
	 * Removes all the vma schedule tugboat lists where shipCode = &#63; and makePayment = &#63; from the database.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByshipCode_makePayment(String shipCode, int makePayment)
		throws SystemException {
		for (VmaScheduleTugboatList vmaScheduleTugboatList : findByshipCode_makePayment(
				shipCode, makePayment)) {
			repository.delete(vmaScheduleTugboatList);
		}
	}

	/**
	 * Removes all the vma schedule tugboat lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleTugboatList vmaScheduleTugboatList : findAll()) {
			repository.delete(vmaScheduleTugboatList);
		}
	}

	/**
	 * Returns the number of vma schedule tugboat lists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETUGBOATLIST_WHERE);

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
	 * Returns the number of vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63; and shipCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param shipCode the ship code
	 * @return the number of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo_shipCode(String itineraryNo,
		int sequenceNo, String shipCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULETUGBOATLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SEQUENCENO_2);

			if (shipCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (shipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				if (shipCode != null) {
					builder.appendNamedParameterMap("shipCode", shipCode);
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
	 * Returns the number of vma schedule tugboat lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo(String itineraryNo, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETUGBOATLIST_WHERE);

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
	 * Returns the number of vma schedule tugboat lists where shipCode = &#63; and makePayment = &#63;.
	 *
	 * @param shipCode the ship code
	 * @param makePayment the make payment
	 * @return the number of matching vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipCode_makePayment(String shipCode, int makePayment)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETUGBOATLIST_WHERE);

			if (shipCode == null) {
				query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_1);
			}
			else {
				if (shipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_MAKEPAYMENT_2);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (shipCode != null) {
					builder.appendNamedParameterMap("shipCode", shipCode);
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
	 * Returns the number of vma schedule tugboat lists.
	 *
	 * @return the number of vma schedule tugboat lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULETUGBOATLIST).build();

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
	 * Initializes the vma schedule tugboat list persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULETUGBOATLIST = "SELECT vmaScheduleTugboatList FROM VmaScheduleTugboatList vmaScheduleTugboatList";
	private static final String _SQL_SELECT_VMASCHEDULETUGBOATLIST_WHERE = "SELECT vmaScheduleTugboatList FROM VmaScheduleTugboatList vmaScheduleTugboatList WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULETUGBOATLIST = "SELECT COUNT(vmaScheduleTugboatList) FROM VmaScheduleTugboatList vmaScheduleTugboatList";
	private static final String _SQL_COUNT_VMASCHEDULETUGBOATLIST_WHERE = "SELECT COUNT(vmaScheduleTugboatList) FROM VmaScheduleTugboatList vmaScheduleTugboatList WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleTugboatList.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleTugboatList.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleTugboatList.itineraryNo IS NULL OR vmaScheduleTugboatList.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_1 =
		"vmaScheduleTugboatList.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_2 =
		"vmaScheduleTugboatList.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_ITINERARYNO_3 =
		"(vmaScheduleTugboatList.itineraryNo IS NULL OR vmaScheduleTugboatList.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SEQUENCENO_2 =
		"vmaScheduleTugboatList.sequenceNo =:sequenceNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_1 =
		"vmaScheduleTugboatList.shipCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_2 =
		"vmaScheduleTugboatList.shipCode =:shipCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SHIPCODE_SHIPCODE_3 =
		"(vmaScheduleTugboatList.shipCode IS NULL OR vmaScheduleTugboatList.shipCode =:shipCode)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1 =
		"vmaScheduleTugboatList.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2 =
		"vmaScheduleTugboatList.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3 =
		"(vmaScheduleTugboatList.itineraryNo IS NULL OR vmaScheduleTugboatList.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2 =
		"vmaScheduleTugboatList.sequenceNo =:sequenceNo";
	private static final String _FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_1 = "vmaScheduleTugboatList.shipCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_2 = "vmaScheduleTugboatList.shipCode =:shipCode AND ";
	private static final String _FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_SHIPCODE_3 = "(vmaScheduleTugboatList.shipCode IS NULL OR vmaScheduleTugboatList.shipCode =:shipCode) AND ";
	private static final String _FINDER_COLUMN_SHIPCODE_MAKEPAYMENT_MAKEPAYMENT_2 =
		"vmaScheduleTugboatList.makePayment =:makePayment";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleTugboatList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleTugboatList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleTugboatList exists with the key {";
	

	
}
