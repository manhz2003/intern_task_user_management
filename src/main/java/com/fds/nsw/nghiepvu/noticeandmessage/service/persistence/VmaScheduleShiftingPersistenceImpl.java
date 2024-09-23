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
import com.fds.nsw.nghiepvu.model.VmaScheduleShifting;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleShiftingRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleShiftingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleShiftingPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleShiftingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleShifting> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleShiftingUtil} to access the vma schedule shifting persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleShifting create(long id) {
		VmaScheduleShifting vmaScheduleShifting = new VmaScheduleShifting();

		
		//vmaScheduleShifting.setPrimaryKey(id);

		return vmaScheduleShifting;
	}

	/**
	 * Removes the vma schedule shifting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule shifting
	 * @return the vma schedule shifting that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting remove(long id)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule shifting with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule shifting
	 * @return the vma schedule shifting that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleShifting remove(Serializable primaryKey)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		

		try {
			

			VmaScheduleShifting vmaScheduleShifting = findByPrimaryKey(primaryKey);

			if (vmaScheduleShifting == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleShiftingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleShifting);
			return vmaScheduleShifting;
		}
		catch (NoSuchVmaScheduleShiftingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleShifting remove(VmaScheduleShifting VmaScheduleShifting) throws SystemException {
	removeImpl(VmaScheduleShifting);
	return VmaScheduleShifting;
}

protected VmaScheduleShifting removeImpl(
		VmaScheduleShifting vmaScheduleShifting) throws SystemException {
		try {
			repository.delete(vmaScheduleShifting);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleShifting;
	}

	
	public VmaScheduleShifting updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleShifting vmaScheduleShifting,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleShifting);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleShifting;
	}

	
	public VmaScheduleShifting findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule shifting with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule shifting
	 * @return the vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting findByPrimaryKey(long id)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = fetchByPrimaryKey(id);

		if (vmaScheduleShifting == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleShiftingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleShifting;
	}

	/**
	 * Returns the vma schedule shifting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule shifting
	 * @return the vma schedule shifting, or <code>null</code> if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleShifting fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule shifting with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule shifting
	 * @return the vma schedule shifting, or <code>null</code> if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleShifting vmaScheduleShifting = null;

		

		if (vmaScheduleShifting == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleShifting> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleShifting = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleShifting;
	}

	/**
	 * Returns all the vma schedule shiftings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule shiftings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule shiftings
	 * @param end the upper bound of the range of vma schedule shiftings (not inclusive)
	 * @return the range of matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule shiftings where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule shiftings
	 * @param end the upper bound of the range of vma schedule shiftings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleShifting> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMASCHEDULESHIFTING_WHERE);

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

				list = (List<VmaScheduleShifting>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule shifting in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleShifting != null) {
			return vmaScheduleShifting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleShiftingException(msg.toString());
	}

	/**
	 * Returns the first vma schedule shifting in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleShifting> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule shifting in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleShifting != null) {
			return vmaScheduleShifting;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleShiftingException(msg.toString());
	}

	/**
	 * Returns the last vma schedule shifting in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleShifting> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule shiftings before and after the current vma schedule shifting in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule shifting
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a vma schedule shifting with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleShifting[] array = new VmaScheduleShifting[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleShifting, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleShifting;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleShifting, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleShifting getByitineraryNo_PrevAndNext(
		 VmaScheduleShifting vmaScheduleShifting,
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

		query.append(_SQL_SELECT_VMASCHEDULESHIFTING_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleShifting);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleShifting> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and certificateNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param certificateNo the certificate no
	 * @return the matching vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting findByitineraryNo_certificateNo(
		String itineraryNo, String certificateNo)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = fetchByitineraryNo_certificateNo(itineraryNo,
				certificateNo);

		if (vmaScheduleShifting == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", certificateNo=");
			msg.append(certificateNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleShiftingException(msg.toString());
		}

		return vmaScheduleShifting;
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param certificateNo the certificate no
	 * @return the matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_certificateNo(
		String itineraryNo, String certificateNo) throws SystemException {
		return fetchByitineraryNo_certificateNo(itineraryNo, certificateNo, true);
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param certificateNo the certificate no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_certificateNo(
		String itineraryNo, String certificateNo, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleShifting vmaScheduleShifting = null;
		if (vmaScheduleShifting == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULESHIFTING_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_2);
				}
			}

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleShifting.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
				}

				vmaScheduleShifting = (VmaScheduleShifting) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleShifting;
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and versionNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param versionNo the version no
	 * @return the matching vma schedule shifting
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleShiftingException if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting findByitineraryNo_versionNo(String itineraryNo,
		String versionNo)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = fetchByitineraryNo_versionNo(itineraryNo,
				versionNo);

		if (vmaScheduleShifting == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", versionNo=");
			msg.append(versionNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleShiftingException(msg.toString());
		}

		return vmaScheduleShifting;
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and versionNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param versionNo the version no
	 * @return the matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_versionNo(
		String itineraryNo, String versionNo) throws SystemException {
		return fetchByitineraryNo_versionNo(itineraryNo, versionNo, true);
	}

	/**
	 * Returns the vma schedule shifting where itineraryNo = &#63; and versionNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param versionNo the version no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule shifting, or <code>null</code> if a matching vma schedule shifting could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting fetchByitineraryNo_versionNo(
		String itineraryNo, String versionNo, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleShifting vmaScheduleShifting = null;
		if (vmaScheduleShifting == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULESHIFTING_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_2);
				}
			}

			if (versionNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_1);
			}
			else {
				if (versionNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleShifting.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (versionNo != null) {
					builder.appendNamedParameterMap("versionNo", versionNo);
				}

				vmaScheduleShifting = (VmaScheduleShifting) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleShifting;
	}

	/**
	 * Returns all the vma schedule shiftings.
	 *
	 * @return the vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule shiftings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule shiftings
	 * @param end the upper bound of the range of vma schedule shiftings (not inclusive)
	 * @return the range of vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule shiftings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule shiftings
	 * @param end the upper bound of the range of vma schedule shiftings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleShifting> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleShifting> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULESHIFTING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULESHIFTING;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleShifting>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule shiftings where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleShifting vmaScheduleShifting : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleShifting);
		}
	}

	/**
	 * Removes the vma schedule shifting where itineraryNo = &#63; and certificateNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param certificateNo the certificate no
	 * @return the vma schedule shifting that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting removeByitineraryNo_certificateNo(
		String itineraryNo, String certificateNo)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = findByitineraryNo_certificateNo(itineraryNo,
				certificateNo);

		repository.delete(vmaScheduleShifting);
			return vmaScheduleShifting;
	}

	/**
	 * Removes the vma schedule shifting where itineraryNo = &#63; and versionNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param versionNo the version no
	 * @return the vma schedule shifting that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleShifting removeByitineraryNo_versionNo(
		String itineraryNo, String versionNo)
		throws NoSuchVmaScheduleShiftingException, SystemException {
		VmaScheduleShifting vmaScheduleShifting = findByitineraryNo_versionNo(itineraryNo,
				versionNo);

		repository.delete(vmaScheduleShifting);
			return vmaScheduleShifting;
	}

	/**
	 * Removes all the vma schedule shiftings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleShifting vmaScheduleShifting : findAll()) {
			repository.delete(vmaScheduleShifting);
		}
	}

	/**
	 * Returns the number of vma schedule shiftings where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULESHIFTING_WHERE);

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
	 * Returns the number of vma schedule shiftings where itineraryNo = &#63; and certificateNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param certificateNo the certificate no
	 * @return the number of matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_certificateNo(String itineraryNo,
		String certificateNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULESHIFTING_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_2);
				}
			}

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

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
	 * Returns the number of vma schedule shiftings where itineraryNo = &#63; and versionNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param versionNo the version no
	 * @return the number of matching vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_versionNo(String itineraryNo, String versionNo)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULESHIFTING_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_2);
				}
			}

			if (versionNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_1);
			}
			else {
				if (versionNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (versionNo != null) {
					builder.appendNamedParameterMap("versionNo", versionNo);
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
	 * Returns the number of vma schedule shiftings.
	 *
	 * @return the number of vma schedule shiftings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULESHIFTING).build();

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
	 * Initializes the vma schedule shifting persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULESHIFTING = "SELECT vmaScheduleShifting FROM VmaScheduleShifting vmaScheduleShifting";
	private static final String _SQL_SELECT_VMASCHEDULESHIFTING_WHERE = "SELECT vmaScheduleShifting FROM VmaScheduleShifting vmaScheduleShifting WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULESHIFTING = "SELECT COUNT(vmaScheduleShifting) FROM VmaScheduleShifting vmaScheduleShifting";
	private static final String _SQL_COUNT_VMASCHEDULESHIFTING_WHERE = "SELECT COUNT(vmaScheduleShifting) FROM VmaScheduleShifting vmaScheduleShifting WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleShifting.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleShifting.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleShifting.itineraryNo IS NULL OR vmaScheduleShifting.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_1 =
		"vmaScheduleShifting.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_2 =
		"vmaScheduleShifting.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_ITINERARYNO_3 =
		"(vmaScheduleShifting.itineraryNo IS NULL OR vmaScheduleShifting.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_1 =
		"vmaScheduleShifting.certificateNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_2 =
		"vmaScheduleShifting.certificateNo =:certificateNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_CERTIFICATENO_CERTIFICATENO_3 =
		"(vmaScheduleShifting.certificateNo IS NULL OR vmaScheduleShifting.certificateNo =:certificateNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_1 =
		"vmaScheduleShifting.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_2 =
		"vmaScheduleShifting.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_ITINERARYNO_3 =
		"(vmaScheduleShifting.itineraryNo IS NULL OR vmaScheduleShifting.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_1 =
		"vmaScheduleShifting.versionNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_2 =
		"vmaScheduleShifting.versionNo =:versionNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_VERSIONNO_VERSIONNO_3 =
		"(vmaScheduleShifting.versionNo IS NULL OR vmaScheduleShifting.versionNo =:versionNo)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleShifting.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleShifting exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleShifting exists with the key {";
	

	
}
