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
import com.fds.nsw.nghiepvu.model.VmaItinerary;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaItineraryRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaItineraryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaItineraryPersistenceImpl extends BasePersistence {
	@Autowired
	VmaItineraryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaItinerary> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaItineraryUtil} to access the vma itinerary persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaItinerary create(long id) {
		VmaItinerary vmaItinerary = new VmaItinerary();

		
		//vmaItinerary.setPrimaryKey(id);

		return vmaItinerary;
	}

	/**
	 * Removes the vma itinerary with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma itinerary
	 * @return the vma itinerary that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary remove(long id)
		throws NoSuchVmaItineraryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma itinerary with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma itinerary
	 * @return the vma itinerary that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItinerary remove(Serializable primaryKey)
		throws NoSuchVmaItineraryException, SystemException {
		

		try {
			

			VmaItinerary vmaItinerary = findByPrimaryKey(primaryKey);

			if (vmaItinerary == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaItineraryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaItinerary);
			return vmaItinerary;
		}
		catch (NoSuchVmaItineraryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaItinerary remove(VmaItinerary VmaItinerary) throws SystemException {
	removeImpl(VmaItinerary);
	return VmaItinerary;
}

protected VmaItinerary removeImpl(VmaItinerary vmaItinerary)
		throws SystemException {
		try {
			repository.delete(vmaItinerary);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItinerary;
	}

	
	public VmaItinerary updateImpl(
		com.fds.nsw.nghiepvu.model.VmaItinerary vmaItinerary,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaItinerary);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaItinerary;
	}

	
	public VmaItinerary findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException} if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary
	 * @return the vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByPrimaryKey(long id)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByPrimaryKey(id);

		if (vmaItinerary == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaItineraryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma itinerary
	 * @return the vma itinerary, or <code>null</code> if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaItinerary fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma itinerary with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma itinerary
	 * @return the vma itinerary, or <code>null</code> if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByPrimaryKey(long id) throws SystemException {
		VmaItinerary vmaItinerary = null;

		

		if (vmaItinerary == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaItinerary> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaItinerary = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary where itineraryNo = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByitineraryNo(String itineraryNo)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByitineraryNo(itineraryNo);

		if (vmaItinerary == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaItineraryException(msg.toString());
		}

		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary where itineraryNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByitineraryNo(String itineraryNo)
		throws SystemException {
		return fetchByitineraryNo(itineraryNo, true);
	}

	/**
	 * Returns the vma itinerary where itineraryNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByitineraryNo(String itineraryNo,
		boolean retrieveFromCache) throws SystemException {
		VmaItinerary vmaItinerary = null;
		if (vmaItinerary == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

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


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerary.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				vmaItinerary = (VmaItinerary) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerary;
	}

	/**
	 * Returns all the vma itineraries where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber(String imoNumber)
		throws SystemException {
		return findByimoNumber(imoNumber, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the vma itineraries where imoNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber(String imoNumber, int start,
		int end) throws SystemException {
		return findByimoNumber(imoNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where imoNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber(String imoNumber, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByimoNumber_First(String imoNumber,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByimoNumber_First(imoNumber,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByimoNumber_First(String imoNumber,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = findByimoNumber(imoNumber, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByimoNumber_Last(String imoNumber,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByimoNumber_Last(imoNumber,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByimoNumber_Last(String imoNumber,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByimoNumber(imoNumber);

		List<VmaItinerary> list = findByimoNumber(imoNumber, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where imoNumber = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param imoNumber the imo number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findByimoNumber_PrevAndNext(long id,
		String imoNumber, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getByimoNumber_PrevAndNext(vmaItinerary,
					imoNumber, orderByComparator, true);

			array[1] = vmaItinerary;

			array[2] = getByimoNumber_PrevAndNext(vmaItinerary,
					imoNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getByimoNumber_PrevAndNext(
		VmaItinerary vmaItinerary, String imoNumber,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (imoNumber == null) {
			query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_1);
		}
		else {
			if (imoNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_2);
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

		

		if (imoNumber != null) {
			builder.appendNamedParameterMap("imoNumber", imoNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itineraries where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findBycallSign(String callSign)
		throws SystemException {
		return findBycallSign(callSign, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the vma itineraries where callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findBycallSign(String callSign, int start, int end)
		throws SystemException {
		return findBycallSign(callSign, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findBycallSign(String callSign, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (callSign == null) {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findBycallSign_First(String callSign,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchBycallSign_First(callSign,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBycallSign_First(String callSign,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = findBycallSign(callSign, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findBycallSign_Last(String callSign,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchBycallSign_Last(callSign,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBycallSign_Last(String callSign,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBycallSign(callSign);

		List<VmaItinerary> list = findBycallSign(callSign, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where callSign = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findBycallSign_PrevAndNext(long id, String callSign,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getBycallSign_PrevAndNext(vmaItinerary,
					callSign, orderByComparator, true);

			array[1] = vmaItinerary;

			array[2] = getBycallSign_PrevAndNext(vmaItinerary,
					callSign, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getBycallSign_PrevAndNext(
		VmaItinerary vmaItinerary, String callSign,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (callSign == null) {
			query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_1);
		}
		else {
			if (callSign.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_2);
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

		

		if (callSign != null) {
			builder.appendNamedParameterMap("callSign", callSign);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itineraries where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode(String vrCode)
		throws SystemException {
		return findByvrCode(vrCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itineraries where vrCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param vrCode the vr code
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode(String vrCode, int start, int end)
		throws SystemException {
		return findByvrCode(vrCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where vrCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param vrCode the vr code
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode(String vrCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (vrCode == null) {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_1);
			}
			else {
				if (vrCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByvrCode_First(String vrCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByvrCode_First(vrCode,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vrCode=");
		msg.append(vrCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByvrCode_First(String vrCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = findByvrCode(vrCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByvrCode_Last(String vrCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByvrCode_Last(vrCode, orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("vrCode=");
		msg.append(vrCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByvrCode_Last(String vrCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByvrCode(vrCode);

		List<VmaItinerary> list = findByvrCode(vrCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where vrCode = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param vrCode the vr code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findByvrCode_PrevAndNext(long id, String vrCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getByvrCode_PrevAndNext(vmaItinerary, vrCode,
					orderByComparator, true);

			array[1] = vmaItinerary;

			array[2] = getByvrCode_PrevAndNext(vmaItinerary, vrCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getByvrCode_PrevAndNext(
		VmaItinerary vmaItinerary, String vrCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (vrCode == null) {
			query.append(_FINDER_COLUMN_VRCODE_VRCODE_1);
		}
		else {
			if (vrCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_2);
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

		

		if (vrCode != null) {
			builder.appendNamedParameterMap("vrCode", vrCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itineraries where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByregistryNumber(String registryNumber)
		throws SystemException {
		return findByregistryNumber(registryNumber, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itineraries where registryNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param registryNumber the registry number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByregistryNumber(String registryNumber,
		int start, int end) throws SystemException {
		return findByregistryNumber(registryNumber, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where registryNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param registryNumber the registry number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByregistryNumber(String registryNumber,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByregistryNumber_First(String registryNumber,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByregistryNumber_First(registryNumber,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("registryNumber=");
		msg.append(registryNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByregistryNumber_First(String registryNumber,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = findByregistryNumber(registryNumber, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByregistryNumber_Last(String registryNumber,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByregistryNumber_Last(registryNumber,
				orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("registryNumber=");
		msg.append(registryNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByregistryNumber_Last(String registryNumber,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByregistryNumber(registryNumber);

		List<VmaItinerary> list = findByregistryNumber(registryNumber,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where registryNumber = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findByregistryNumber_PrevAndNext(long id,
		String registryNumber, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getByregistryNumber_PrevAndNext(vmaItinerary,
					registryNumber, orderByComparator, true);

			array[1] = vmaItinerary;

			array[2] = getByregistryNumber_PrevAndNext(vmaItinerary,
					registryNumber, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getByregistryNumber_PrevAndNext(
		VmaItinerary vmaItinerary, String registryNumber,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (registryNumber == null) {
			query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1);
		}
		else {
			if (registryNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2);
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

		

		if (registryNumber != null) {
			builder.appendNamedParameterMap("registryNumber", registryNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itineraries where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber_callSign(String imoNumber,
		String callSign) throws SystemException {
		return findByimoNumber_callSign(imoNumber, callSign, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itineraries where imoNumber = &#63; and callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber_callSign(String imoNumber,
		String callSign, int start, int end) throws SystemException {
		return findByimoNumber_callSign(imoNumber, callSign, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where imoNumber = &#63; and callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByimoNumber_callSign(String imoNumber,
		String callSign, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByimoNumber_callSign_First(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByimoNumber_callSign_First(imoNumber,
				callSign, orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByimoNumber_callSign_First(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerary> list = findByimoNumber_callSign(imoNumber, callSign,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByimoNumber_callSign_Last(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByimoNumber_callSign_Last(imoNumber,
				callSign, orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByimoNumber_callSign_Last(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByimoNumber_callSign(imoNumber, callSign);

		List<VmaItinerary> list = findByimoNumber_callSign(imoNumber, callSign,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findByimoNumber_callSign_PrevAndNext(long id,
		String imoNumber, String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getByimoNumber_callSign_PrevAndNext(
					vmaItinerary, imoNumber, callSign, orderByComparator, true);

			array[1] = vmaItinerary;

			array[2] = getByimoNumber_callSign_PrevAndNext(
					vmaItinerary, imoNumber, callSign, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getByimoNumber_callSign_PrevAndNext(
		 VmaItinerary vmaItinerary, String imoNumber,
		String callSign, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (imoNumber == null) {
			query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
		}
		else {
			if (imoNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
			}
		}

		if (callSign == null) {
			query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
		}
		else {
			if (callSign.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
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

		

		if (imoNumber != null) {
			builder.appendNamedParameterMap("imoNumber", imoNumber);
		}

		if (callSign != null) {
			builder.appendNamedParameterMap("callSign", callSign);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma itineraries where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		return findByvrCode_registryNumber(imoNumber, registryNumber,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itineraries where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode_registryNumber(String imoNumber,
		String registryNumber, int start, int end) throws SystemException {
		return findByvrCode_registryNumber(imoNumber, registryNumber, start,
			end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findByvrCode_registryNumber(String imoNumber,
		String registryNumber, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				list = (List<VmaItinerary>)queryFactory.getResultList(builder);
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
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByvrCode_registryNumber_First(String imoNumber,
		String registryNumber, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByvrCode_registryNumber_First(imoNumber,
				registryNumber, orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", registryNumber=");
		msg.append(registryNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the first vma itinerary in the ordered set where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByvrCode_registryNumber_First(String imoNumber,
		String registryNumber, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaItinerary> list = findByvrCode_registryNumber(imoNumber,
				registryNumber, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findByvrCode_registryNumber_Last(String imoNumber,
		String registryNumber, OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchByvrCode_registryNumber_Last(imoNumber,
				registryNumber, orderByComparator);

		if (vmaItinerary != null) {
			return vmaItinerary;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", registryNumber=");
		msg.append(registryNumber);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaItineraryException(msg.toString());
	}

	/**
	 * Returns the last vma itinerary in the ordered set where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchByvrCode_registryNumber_Last(String imoNumber,
		String registryNumber, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByvrCode_registryNumber(imoNumber, registryNumber);

		List<VmaItinerary> list = findByvrCode_registryNumber(imoNumber,
				registryNumber, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma itineraries before and after the current vma itinerary in the ordered set where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param id the primary key of the current vma itinerary
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a vma itinerary with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary[] findByvrCode_registryNumber_PrevAndNext(long id,
		String imoNumber, String registryNumber,
		OrderByComparator orderByComparator)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByPrimaryKey(id);

		

		try {
			

			VmaItinerary[] array = new VmaItinerary[3];

			array[0] = getByvrCode_registryNumber_PrevAndNext(
					vmaItinerary, imoNumber, registryNumber, orderByComparator,
					true);

			array[1] = vmaItinerary;

			array[2] = getByvrCode_registryNumber_PrevAndNext(
					vmaItinerary, imoNumber, registryNumber, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaItinerary getByvrCode_registryNumber_PrevAndNext(
		 VmaItinerary vmaItinerary, String imoNumber,
		String registryNumber, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAITINERARY_WHERE);

		if (imoNumber == null) {
			query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1);
		}
		else {
			if (imoNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2);
			}
		}

		if (registryNumber == null) {
			query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1);
		}
		else {
			if (registryNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2);
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

		

		if (imoNumber != null) {
			builder.appendNamedParameterMap("imoNumber", imoNumber);
		}

		if (registryNumber != null) {
			builder.appendNamedParameterMap("registryNumber", registryNumber);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaItinerary);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaItinerary> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma itinerary where documentNameIN = &#63; and documentYearIN = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException} if it could not be found.
	 *
	 * @param documentNameIN the document name i n
	 * @param documentYearIN the document year i n
	 * @return the matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findBydocumentNameIN_documentYearIN(
		long documentNameIN, int documentYearIN)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchBydocumentNameIN_documentYearIN(documentNameIN,
				documentYearIN);

		if (vmaItinerary == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentNameIN=");
			msg.append(documentNameIN);

			msg.append(", documentYearIN=");
			msg.append(documentYearIN);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaItineraryException(msg.toString());
		}

		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary where documentNameIN = &#63; and documentYearIN = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentNameIN the document name i n
	 * @param documentYearIN the document year i n
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBydocumentNameIN_documentYearIN(
		long documentNameIN, int documentYearIN) throws SystemException {
		return fetchBydocumentNameIN_documentYearIN(documentNameIN,
			documentYearIN, true);
	}

	/**
	 * Returns the vma itinerary where documentNameIN = &#63; and documentYearIN = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentNameIN the document name i n
	 * @param documentYearIN the document year i n
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBydocumentNameIN_documentYearIN(
		long documentNameIN, int documentYearIN, boolean retrieveFromCache)
		throws SystemException {
		VmaItinerary vmaItinerary = null;
		if (vmaItinerary == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTNAMEIN_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTYEARIN_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerary.class).build();
				

				builder.appendNamedParameterMap("documentNameIN", documentNameIN);

				builder.appendNamedParameterMap("documentYearIN", documentYearIN);

				vmaItinerary = (VmaItinerary) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary where documentNameOUT = &#63; and documentYearOUT = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException} if it could not be found.
	 *
	 * @param documentNameOUT the document name o u t
	 * @param documentYearOUT the document year o u t
	 * @return the matching vma itinerary
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaItineraryException if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary findBydocumentNameOUT_documentYearOUT(
		long documentNameOUT, int documentYearOUT)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = fetchBydocumentNameOUT_documentYearOUT(documentNameOUT,
				documentYearOUT);

		if (vmaItinerary == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentNameOUT=");
			msg.append(documentNameOUT);

			msg.append(", documentYearOUT=");
			msg.append(documentYearOUT);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaItineraryException(msg.toString());
		}

		return vmaItinerary;
	}

	/**
	 * Returns the vma itinerary where documentNameOUT = &#63; and documentYearOUT = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentNameOUT the document name o u t
	 * @param documentYearOUT the document year o u t
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBydocumentNameOUT_documentYearOUT(
		long documentNameOUT, int documentYearOUT) throws SystemException {
		return fetchBydocumentNameOUT_documentYearOUT(documentNameOUT,
			documentYearOUT, true);
	}

	/**
	 * Returns the vma itinerary where documentNameOUT = &#63; and documentYearOUT = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentNameOUT the document name o u t
	 * @param documentYearOUT the document year o u t
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma itinerary, or <code>null</code> if a matching vma itinerary could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary fetchBydocumentNameOUT_documentYearOUT(
		long documentNameOUT, int documentYearOUT, boolean retrieveFromCache)
		throws SystemException {
		VmaItinerary vmaItinerary = null;
		if (vmaItinerary == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAITINERARY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTNAMEOUT_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTYEAROUT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaItinerary.class).build();

				builder.appendNamedParameterMap("documentNameOUT", documentNameOUT);

				builder.appendNamedParameterMap("documentYearOUT", documentYearOUT);

				vmaItinerary = (VmaItinerary) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaItinerary;
	}

	/**
	 * Returns all the vma itineraries.
	 *
	 * @return the vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma itineraries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @return the range of vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma itineraries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma itineraries
	 * @param end the upper bound of the range of vma itineraries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaItinerary> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaItinerary> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAITINERARY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAITINERARY;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaItinerary>) queryFactory.getResultList(builder);
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
	 * Removes the vma itinerary where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the vma itinerary that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary removeByitineraryNo(String itineraryNo)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findByitineraryNo(itineraryNo);

		repository.delete(vmaItinerary);
			return vmaItinerary;
	}

	/**
	 * Removes all the vma itineraries where imoNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByimoNumber(String imoNumber) throws SystemException {
		for (VmaItinerary vmaItinerary : findByimoNumber(imoNumber)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes all the vma itineraries where callSign = &#63; from the database.
	 *
	 * @param callSign the call sign
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBycallSign(String callSign) throws SystemException {
		for (VmaItinerary vmaItinerary : findBycallSign(callSign)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes all the vma itineraries where vrCode = &#63; from the database.
	 *
	 * @param vrCode the vr code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByvrCode(String vrCode) throws SystemException {
		for (VmaItinerary vmaItinerary : findByvrCode(vrCode)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes all the vma itineraries where registryNumber = &#63; from the database.
	 *
	 * @param registryNumber the registry number
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByregistryNumber(String registryNumber)
		throws SystemException {
		for (VmaItinerary vmaItinerary : findByregistryNumber(registryNumber)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes all the vma itineraries where imoNumber = &#63; and callSign = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		for (VmaItinerary vmaItinerary : findByimoNumber_callSign(imoNumber,
				callSign)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes all the vma itineraries where imoNumber = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		for (VmaItinerary vmaItinerary : findByvrCode_registryNumber(
				imoNumber, registryNumber)) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Removes the vma itinerary where documentNameIN = &#63; and documentYearIN = &#63; from the database.
	 *
	 * @param documentNameIN the document name i n
	 * @param documentYearIN the document year i n
	 * @return the vma itinerary that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary removeBydocumentNameIN_documentYearIN(
		long documentNameIN, int documentYearIN)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findBydocumentNameIN_documentYearIN(documentNameIN,
				documentYearIN);

		repository.delete(vmaItinerary);
			return vmaItinerary;
	}

	/**
	 * Removes the vma itinerary where documentNameOUT = &#63; and documentYearOUT = &#63; from the database.
	 *
	 * @param documentNameOUT the document name o u t
	 * @param documentYearOUT the document year o u t
	 * @return the vma itinerary that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaItinerary removeBydocumentNameOUT_documentYearOUT(
		long documentNameOUT, int documentYearOUT)
		throws NoSuchVmaItineraryException, SystemException {
		VmaItinerary vmaItinerary = findBydocumentNameOUT_documentYearOUT(documentNameOUT,
				documentYearOUT);

		repository.delete(vmaItinerary);
			return vmaItinerary;
	}

	/**
	 * Removes all the vma itineraries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaItinerary vmaItinerary : findAll()) {
			repository.delete(vmaItinerary);
		}
	}

	/**
	 * Returns the number of vma itineraries where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

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
	 * Returns the number of vma itineraries where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber(String imoNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
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
	 * Returns the number of vma itineraries where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycallSign(String callSign) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (callSign == null) {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
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
	 * Returns the number of vma itineraries where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode(String vrCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (vrCode == null) {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_1);
			}
			else {
				if (vrCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
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
	 * Returns the number of vma itineraries where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByregistryNumber(String registryNumber)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
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
	 * Returns the number of vma itineraries where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
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
	 * Returns the number of vma itineraries where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
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
	 * Returns the number of vma itineraries where documentNameIN = &#63; and documentYearIN = &#63;.
	 *
	 * @param documentNameIN the document name i n
	 * @param documentYearIN the document year i n
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameIN_documentYearIN(long documentNameIN,
		int documentYearIN) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTNAMEIN_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTYEARIN_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				builder.appendNamedParameterMap("documentNameIN", documentNameIN);

				builder.appendNamedParameterMap("documentYearIN", documentYearIN);

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
	 * Returns the number of vma itineraries where documentNameOUT = &#63; and documentYearOUT = &#63;.
	 *
	 * @param documentNameOUT the document name o u t
	 * @param documentYearOUT the document year o u t
	 * @return the number of matching vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countBydocumentNameOUT_documentYearOUT(long documentNameOUT,
		int documentYearOUT) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAITINERARY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTNAMEOUT_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTYEAROUT_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				builder.appendNamedParameterMap("documentNameOUT", documentNameOUT);

				builder.appendNamedParameterMap("documentYearOUT", documentYearOUT);

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
	 * Returns the number of vma itineraries.
	 *
	 * @return the number of vma itineraries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAITINERARY).build();

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
	 * Initializes the vma itinerary persistence.
	 */
	private static final String _SQL_SELECT_VMAITINERARY = "SELECT vmaItinerary FROM VmaItinerary vmaItinerary";
	private static final String _SQL_SELECT_VMAITINERARY_WHERE = "SELECT vmaItinerary FROM VmaItinerary vmaItinerary WHERE ";
	private static final String _SQL_COUNT_VMAITINERARY = "SELECT COUNT(vmaItinerary) FROM VmaItinerary vmaItinerary";
	private static final String _SQL_COUNT_VMAITINERARY_WHERE = "SELECT COUNT(vmaItinerary) FROM VmaItinerary vmaItinerary WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaItinerary.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaItinerary.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaItinerary.itineraryNo IS NULL OR vmaItinerary.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_1 = "vmaItinerary.imoNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_2 = "vmaItinerary.imoNumber =:imoNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_3 = "(vmaItinerary.imoNumber IS NULL OR vmaItinerary.imoNumber =:imoNumber)";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_1 = "vmaItinerary.callSign IS NULL";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_2 = "vmaItinerary.callSign =:callSign";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_3 = "(vmaItinerary.callSign IS NULL OR vmaItinerary.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_1 = "vmaItinerary.vrCode IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_2 = "vmaItinerary.vrCode =:vrCode";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_3 = "(vmaItinerary.vrCode IS NULL OR vmaItinerary.vrCode =:vrCode)";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1 = "vmaItinerary.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2 = "vmaItinerary.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3 = "(vmaItinerary.registryNumber IS NULL OR vmaItinerary.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1 = "vmaItinerary.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2 = "vmaItinerary.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3 = "(vmaItinerary.imoNumber IS NULL OR vmaItinerary.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1 = "vmaItinerary.callSign IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2 = "vmaItinerary.callSign =:callSign";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3 = "(vmaItinerary.callSign IS NULL OR vmaItinerary.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaItinerary.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaItinerary.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaItinerary.imoNumber IS NULL OR vmaItinerary.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaItinerary.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaItinerary.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaItinerary.registryNumber IS NULL OR vmaItinerary.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTNAMEIN_2 =
		"vmaItinerary.documentNameIN =:documentNameIN AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEIN_DOCUMENTYEARIN_DOCUMENTYEARIN_2 =
		"vmaItinerary.documentYearIN =:documentYearIN";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTNAMEOUT_2 =
		"vmaItinerary.documentNameOUT =:documentNameOUT AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEOUT_DOCUMENTYEAROUT_DOCUMENTYEAROUT_2 =
		"vmaItinerary.documentYearOUT =:documentYearOUT";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaItinerary.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaItinerary exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaItinerary exists with the key {";
	

	
}
