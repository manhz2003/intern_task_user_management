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
import com.fds.nsw.nghiepvu.model.VmaSchedulePilotList;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaSchedulePilotListRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaSchedulePilotListModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaSchedulePilotListPersistenceImpl extends BasePersistence {
	@Autowired
	VmaSchedulePilotListRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaSchedulePilotList> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaSchedulePilotListUtil} to access the vma schedule pilot list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaSchedulePilotList create(long id) {
		VmaSchedulePilotList vmaSchedulePilotList = new VmaSchedulePilotList();

		
		//vmaSchedulePilotList.setPrimaryKey(id);

		return vmaSchedulePilotList;
	}

	/**
	 * Removes the vma schedule pilot list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule pilot list
	 * @return the vma schedule pilot list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList remove(long id)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule pilot list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule pilot list
	 * @return the vma schedule pilot list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaSchedulePilotList remove(Serializable primaryKey)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		

		try {
			

			VmaSchedulePilotList vmaSchedulePilotList = findByPrimaryKey(primaryKey);

			if (vmaSchedulePilotList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaSchedulePilotListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaSchedulePilotList);
			return vmaSchedulePilotList;
		}
		catch (NoSuchVmaSchedulePilotListException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaSchedulePilotList remove(VmaSchedulePilotList VmaSchedulePilotList) throws SystemException {
	removeImpl(VmaSchedulePilotList);
	return VmaSchedulePilotList;
}

protected VmaSchedulePilotList removeImpl(
		VmaSchedulePilotList vmaSchedulePilotList) throws SystemException {
		try {
			repository.delete(vmaSchedulePilotList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaSchedulePilotList;
	}

	
	public VmaSchedulePilotList updateImpl(
		com.fds.nsw.nghiepvu.model.VmaSchedulePilotList vmaSchedulePilotList,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaSchedulePilotList);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaSchedulePilotList;
	}

	
	public VmaSchedulePilotList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule pilot list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule pilot list
	 * @return the vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByPrimaryKey(long id)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByPrimaryKey(id);

		if (vmaSchedulePilotList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaSchedulePilotListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaSchedulePilotList;
	}

	/**
	 * Returns the vma schedule pilot list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule pilot list
	 * @return the vma schedule pilot list, or <code>null</code> if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaSchedulePilotList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule pilot list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule pilot list
	 * @return the vma schedule pilot list, or <code>null</code> if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByPrimaryKey(long id)
		throws SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = null;

		

		if (vmaSchedulePilotList == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaSchedulePilotList> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaSchedulePilotList = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaSchedulePilotList;
	}

	/**
	 * Returns all the vma schedule pilot lists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilot lists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @return the range of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilot lists where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaSchedulePilotList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE);

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

				list = (List<VmaSchedulePilotList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule pilot list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaSchedulePilotList != null) {
			return vmaSchedulePilotList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotListException(msg.toString());
	}

	/**
	 * Returns the first vma schedule pilot list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilotList> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule pilot list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaSchedulePilotList != null) {
			return vmaSchedulePilotList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotListException(msg.toString());
	}

	/**
	 * Returns the last vma schedule pilot list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaSchedulePilotList> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule pilot lists before and after the current vma schedule pilot list in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule pilot list
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = findByPrimaryKey(id);

		

		try {
			

			VmaSchedulePilotList[] array = new VmaSchedulePilotList[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaSchedulePilotList, itineraryNo, orderByComparator, true);

			array[1] = vmaSchedulePilotList;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaSchedulePilotList, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaSchedulePilotList getByitineraryNo_PrevAndNext(
		 VmaSchedulePilotList vmaSchedulePilotList,
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

		query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaSchedulePilotList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaSchedulePilotList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule pilot list where itineraryNo = &#63; and sequenceNo = &#63; and pilotCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param pilotCode the pilot code
	 * @return the matching vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByitineraryNo_sequenceNo_pilotCode(
		String itineraryNo, int sequenceNo, String pilotCode)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByitineraryNo_sequenceNo_pilotCode(itineraryNo,
				sequenceNo, pilotCode);

		if (vmaSchedulePilotList == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", sequenceNo=");
			msg.append(sequenceNo);

			msg.append(", pilotCode=");
			msg.append(pilotCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaSchedulePilotListException(msg.toString());
		}

		return vmaSchedulePilotList;
	}

	/**
	 * Returns the vma schedule pilot list where itineraryNo = &#63; and sequenceNo = &#63; and pilotCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param pilotCode the pilot code
	 * @return the matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_sequenceNo_pilotCode(
		String itineraryNo, int sequenceNo, String pilotCode)
		throws SystemException {
		return fetchByitineraryNo_sequenceNo_pilotCode(itineraryNo, sequenceNo,
			pilotCode, true);
	}

	/**
	 * Returns the vma schedule pilot list where itineraryNo = &#63; and sequenceNo = &#63; and pilotCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param pilotCode the pilot code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_sequenceNo_pilotCode(
		String itineraryNo, int sequenceNo, String pilotCode,
		boolean retrieveFromCache) throws SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = null;
		if (vmaSchedulePilotList == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_SEQUENCENO_2);

			if (pilotCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (pilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaSchedulePilotList.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				if (pilotCode != null) {
					builder.appendNamedParameterMap("pilotCode", pilotCode);
				}

				vmaSchedulePilotList = (VmaSchedulePilotList) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaSchedulePilotList;
	}

	/**
	 * Returns all the vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo) throws SystemException {
		return findByitineraryNo_sequenceNo(itineraryNo, sequenceNo,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @return the range of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, int start, int end)
		throws SystemException {
		return findByitineraryNo_sequenceNo(itineraryNo, sequenceNo, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findByitineraryNo_sequenceNo(
		String itineraryNo, int sequenceNo, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilotList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE);

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

				list = (List<VmaSchedulePilotList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule pilot list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByitineraryNo_sequenceNo_First(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByitineraryNo_sequenceNo_First(itineraryNo,
				sequenceNo, orderByComparator);

		if (vmaSchedulePilotList != null) {
			return vmaSchedulePilotList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotListException(msg.toString());
	}

	/**
	 * Returns the first vma schedule pilot list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_sequenceNo_First(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaSchedulePilotList> list = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule pilot list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList findByitineraryNo_sequenceNo_Last(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = fetchByitineraryNo_sequenceNo_Last(itineraryNo,
				sequenceNo, orderByComparator);

		if (vmaSchedulePilotList != null) {
			return vmaSchedulePilotList;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", sequenceNo=");
		msg.append(sequenceNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaSchedulePilotListException(msg.toString());
	}

	/**
	 * Returns the last vma schedule pilot list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule pilot list, or <code>null</code> if a matching vma schedule pilot list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList fetchByitineraryNo_sequenceNo_Last(
		String itineraryNo, int sequenceNo, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByitineraryNo_sequenceNo(itineraryNo, sequenceNo);

		List<VmaSchedulePilotList> list = findByitineraryNo_sequenceNo(itineraryNo,
				sequenceNo, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule pilot lists before and after the current vma schedule pilot list in the ordered set where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule pilot list
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule pilot list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaSchedulePilotListException if a vma schedule pilot list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList[] findByitineraryNo_sequenceNo_PrevAndNext(
		long id, String itineraryNo, int sequenceNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = findByPrimaryKey(id);

		

		try {
			

			VmaSchedulePilotList[] array = new VmaSchedulePilotList[3];

			array[0] = getByitineraryNo_sequenceNo_PrevAndNext(
					vmaSchedulePilotList, itineraryNo, sequenceNo,
					orderByComparator, true);

			array[1] = vmaSchedulePilotList;

			array[2] = getByitineraryNo_sequenceNo_PrevAndNext(
					vmaSchedulePilotList, itineraryNo, sequenceNo,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaSchedulePilotList getByitineraryNo_sequenceNo_PrevAndNext(
		 VmaSchedulePilotList vmaSchedulePilotList,
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

		query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaSchedulePilotList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaSchedulePilotList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule pilot lists.
	 *
	 * @return the vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule pilot lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @return the range of vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule pilot lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule pilot lists
	 * @param end the upper bound of the range of vma schedule pilot lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaSchedulePilotList> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaSchedulePilotList> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEPILOTLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEPILOTLIST;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaSchedulePilotList>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule pilot lists where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaSchedulePilotList vmaSchedulePilotList : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaSchedulePilotList);
		}
	}

	/**
	 * Removes the vma schedule pilot list where itineraryNo = &#63; and sequenceNo = &#63; and pilotCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param pilotCode the pilot code
	 * @return the vma schedule pilot list that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaSchedulePilotList removeByitineraryNo_sequenceNo_pilotCode(
		String itineraryNo, int sequenceNo, String pilotCode)
		throws NoSuchVmaSchedulePilotListException, SystemException {
		VmaSchedulePilotList vmaSchedulePilotList = findByitineraryNo_sequenceNo_pilotCode(itineraryNo,
				sequenceNo, pilotCode);

		repository.delete(vmaSchedulePilotList);
			return vmaSchedulePilotList;
	}

	/**
	 * Removes all the vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_sequenceNo(String itineraryNo,
		int sequenceNo) throws SystemException {
		for (VmaSchedulePilotList vmaSchedulePilotList : findByitineraryNo_sequenceNo(
				itineraryNo, sequenceNo)) {
			repository.delete(vmaSchedulePilotList);
		}
	}

	/**
	 * Removes all the vma schedule pilot lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaSchedulePilotList vmaSchedulePilotList : findAll()) {
			repository.delete(vmaSchedulePilotList);
		}
	}

	/**
	 * Returns the number of vma schedule pilot lists where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULEPILOTLIST_WHERE);

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
	 * Returns the number of vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63; and pilotCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @param pilotCode the pilot code
	 * @return the number of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo_pilotCode(String itineraryNo,
		int sequenceNo, String pilotCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULEPILOTLIST_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_SEQUENCENO_2);

			if (pilotCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (pilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("sequenceNo", sequenceNo);

				if (pilotCode != null) {
					builder.appendNamedParameterMap("pilotCode", pilotCode);
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
	 * Returns the number of vma schedule pilot lists where itineraryNo = &#63; and sequenceNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param sequenceNo the sequence no
	 * @return the number of matching vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_sequenceNo(String itineraryNo, int sequenceNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULEPILOTLIST_WHERE);

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
	 * Returns the number of vma schedule pilot lists.
	 *
	 * @return the number of vma schedule pilot lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEPILOTLIST).build();

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
	 * Initializes the vma schedule pilot list persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEPILOTLIST = "SELECT vmaSchedulePilotList FROM VmaSchedulePilotList vmaSchedulePilotList";
	private static final String _SQL_SELECT_VMASCHEDULEPILOTLIST_WHERE = "SELECT vmaSchedulePilotList FROM VmaSchedulePilotList vmaSchedulePilotList WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULEPILOTLIST = "SELECT COUNT(vmaSchedulePilotList) FROM VmaSchedulePilotList vmaSchedulePilotList";
	private static final String _SQL_COUNT_VMASCHEDULEPILOTLIST_WHERE = "SELECT COUNT(vmaSchedulePilotList) FROM VmaSchedulePilotList vmaSchedulePilotList WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaSchedulePilotList.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaSchedulePilotList.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaSchedulePilotList.itineraryNo IS NULL OR vmaSchedulePilotList.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_1 =
		"vmaSchedulePilotList.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_2 =
		"vmaSchedulePilotList.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_ITINERARYNO_3 =
		"(vmaSchedulePilotList.itineraryNo IS NULL OR vmaSchedulePilotList.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_SEQUENCENO_2 =
		"vmaSchedulePilotList.sequenceNo =:sequenceNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_1 =
		"vmaSchedulePilotList.pilotCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_2 =
		"vmaSchedulePilotList.pilotCode =:pilotCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_PILOTCODE_PILOTCODE_3 =
		"(vmaSchedulePilotList.pilotCode IS NULL OR vmaSchedulePilotList.pilotCode =:pilotCode)";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_1 =
		"vmaSchedulePilotList.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_2 =
		"vmaSchedulePilotList.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_ITINERARYNO_3 =
		"(vmaSchedulePilotList.itineraryNo IS NULL OR vmaSchedulePilotList.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_SEQUENCENO_SEQUENCENO_2 =
		"vmaSchedulePilotList.sequenceNo =:sequenceNo";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaSchedulePilotList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaSchedulePilotList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaSchedulePilotList exists with the key {";
	

	
}
