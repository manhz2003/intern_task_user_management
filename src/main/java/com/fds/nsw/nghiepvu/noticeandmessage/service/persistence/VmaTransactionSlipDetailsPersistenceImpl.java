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
import com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionSlipDetailRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionSlipDetailsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionSlipDetailsPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionSlipDetailRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionSlipDetails> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionSlipDetailsUtil} to access the vma transaction slip details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionSlipDetails create(long id) {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = new VmaTransactionSlipDetails();

		
		//vmaTransactionSlipDetails.setPrimaryKey(id);

		return vmaTransactionSlipDetails;
	}

	/**
	 * Removes the vma transaction slip details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction slip details
	 * @return the vma transaction slip details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails remove(long id)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction slip details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction slip details
	 * @return the vma transaction slip details that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlipDetails remove(Serializable primaryKey)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		

		try {
			

			VmaTransactionSlipDetails vmaTransactionSlipDetails = findByPrimaryKey(primaryKey);

			if (vmaTransactionSlipDetails == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionSlipDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionSlipDetails);
			return vmaTransactionSlipDetails;
		}
		catch (NoSuchVmaTransactionSlipDetailsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionSlipDetails remove(VmaTransactionSlipDetails VmaTransactionSlipDetails) throws SystemException {
	removeImpl(VmaTransactionSlipDetails);
	return VmaTransactionSlipDetails;
}

protected VmaTransactionSlipDetails removeImpl(
		VmaTransactionSlipDetails vmaTransactionSlipDetails)
		throws SystemException {
		try {
			repository.delete(vmaTransactionSlipDetails);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlipDetails;
	}

	
	public VmaTransactionSlipDetails updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionSlipDetails vmaTransactionSlipDetails,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionSlipDetails);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlipDetails;
	}

	
	public VmaTransactionSlipDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip details with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip details
	 * @return the vma transaction slip details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails findByPrimaryKey(long id)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = fetchByPrimaryKey(id);

		if (vmaTransactionSlipDetails == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionSlipDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionSlipDetails;
	}

	/**
	 * Returns the vma transaction slip details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction slip details
	 * @return the vma transaction slip details, or <code>null</code> if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlipDetails fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip details
	 * @return the vma transaction slip details, or <code>null</code> if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = null;

		

		if (vmaTransactionSlipDetails == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionSlipDetails> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionSlipDetails = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionSlipDetails;
	}

	/**
	 * Returns all the vma transaction slip detailses where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findByItineraryNo(String itineraryNo)
		throws SystemException {
		return findByItineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slip detailses where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma transaction slip detailses
	 * @param end the upper bound of the range of vma transaction slip detailses (not inclusive)
	 * @return the range of matching vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findByItineraryNo(
		String itineraryNo, int start, int end) throws SystemException {
		return findByItineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slip detailses where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma transaction slip detailses
	 * @param end the upper bound of the range of vma transaction slip detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findByItineraryNo(
		String itineraryNo, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlipDetails> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONSLIPDETAILS_WHERE);

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

				list = (List<VmaTransactionSlipDetails>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction slip details in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails findByItineraryNo_First(
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = fetchByItineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaTransactionSlipDetails != null) {
			return vmaTransactionSlipDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipDetailsException(msg.toString());
	}

	/**
	 * Returns the first vma transaction slip details in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip details, or <code>null</code> if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails fetchByItineraryNo_First(
		String itineraryNo, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionSlipDetails> list = findByItineraryNo(itineraryNo,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction slip details in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails findByItineraryNo_Last(
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = fetchByItineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaTransactionSlipDetails != null) {
			return vmaTransactionSlipDetails;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipDetailsException(msg.toString());
	}

	/**
	 * Returns the last vma transaction slip details in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip details, or <code>null</code> if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails fetchByItineraryNo_Last(
		String itineraryNo, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByItineraryNo(itineraryNo);

		List<VmaTransactionSlipDetails> list = findByItineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction slip detailses before and after the current vma transaction slip details in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma transaction slip details
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction slip details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a vma transaction slip details with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails[] findByItineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionSlipDetails[] array = new VmaTransactionSlipDetails[3];

			array[0] = getByItineraryNo_PrevAndNext(
					vmaTransactionSlipDetails, itineraryNo, orderByComparator,
					true);

			array[1] = vmaTransactionSlipDetails;

			array[2] = getByItineraryNo_PrevAndNext(
					vmaTransactionSlipDetails, itineraryNo, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionSlipDetails getByItineraryNo_PrevAndNext(
		 VmaTransactionSlipDetails vmaTransactionSlipDetails,
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

		query.append(_SQL_SELECT_VMATRANSACTIONSLIPDETAILS_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionSlipDetails);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionSlipDetails> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction slip details where itineraryNo = &#63; and documentaryCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma transaction slip details
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipDetailsException if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails findByItineraryNo_DocumentaryCode(
		String itineraryNo, String documentaryCode)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = fetchByItineraryNo_DocumentaryCode(itineraryNo,
				documentaryCode);

		if (vmaTransactionSlipDetails == null) {
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

			throw new NoSuchVmaTransactionSlipDetailsException(msg.toString());
		}

		return vmaTransactionSlipDetails;
	}

	/**
	 * Returns the vma transaction slip details where itineraryNo = &#63; and documentaryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma transaction slip details, or <code>null</code> if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails fetchByItineraryNo_DocumentaryCode(
		String itineraryNo, String documentaryCode) throws SystemException {
		return fetchByItineraryNo_DocumentaryCode(itineraryNo, documentaryCode,
			true);
	}

	/**
	 * Returns the vma transaction slip details where itineraryNo = &#63; and documentaryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip details, or <code>null</code> if a matching vma transaction slip details could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails fetchByItineraryNo_DocumentaryCode(
		String itineraryNo, String documentaryCode, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = null;
		if (vmaTransactionSlipDetails == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIPDETAILS_WHERE);

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

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlipDetails.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
				}

				vmaTransactionSlipDetails = (VmaTransactionSlipDetails) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlipDetails;
	}

	/**
	 * Returns all the vma transaction slip detailses.
	 *
	 * @return the vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slip detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slip detailses
	 * @param end the upper bound of the range of vma transaction slip detailses (not inclusive)
	 * @return the range of vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slip detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slip detailses
	 * @param end the upper bound of the range of vma transaction slip detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipDetails> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlipDetails> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONSLIPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONSLIPDETAILS;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionSlipDetails>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction slip detailses where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByItineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaTransactionSlipDetails vmaTransactionSlipDetails : findByItineraryNo(
				itineraryNo)) {
			repository.delete(vmaTransactionSlipDetails);
		}
	}

	/**
	 * Removes the vma transaction slip details where itineraryNo = &#63; and documentaryCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the vma transaction slip details that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipDetails removeByItineraryNo_DocumentaryCode(
		String itineraryNo, String documentaryCode)
		throws NoSuchVmaTransactionSlipDetailsException, SystemException {
		VmaTransactionSlipDetails vmaTransactionSlipDetails = findByItineraryNo_DocumentaryCode(itineraryNo,
				documentaryCode);

		repository.delete(vmaTransactionSlipDetails);
			return vmaTransactionSlipDetails;
	}

	/**
	 * Removes all the vma transaction slip detailses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionSlipDetails vmaTransactionSlipDetails : findAll()) {
			repository.delete(vmaTransactionSlipDetails);
		}
	}

	/**
	 * Returns the number of vma transaction slip detailses where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByItineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIPDETAILS_WHERE);

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
	 * Returns the number of vma transaction slip detailses where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the number of matching vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByItineraryNo_DocumentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIPDETAILS_WHERE);

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
	 * Returns the number of vma transaction slip detailses.
	 *
	 * @return the number of vma transaction slip detailses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONSLIPDETAILS).build();

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
	 * Initializes the vma transaction slip details persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONSLIPDETAILS = "SELECT vmaTransactionSlipDetails FROM VmaTransactionSlipDetails vmaTransactionSlipDetails";
	private static final String _SQL_SELECT_VMATRANSACTIONSLIPDETAILS_WHERE = "SELECT vmaTransactionSlipDetails FROM VmaTransactionSlipDetails vmaTransactionSlipDetails WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIPDETAILS = "SELECT COUNT(vmaTransactionSlipDetails) FROM VmaTransactionSlipDetails vmaTransactionSlipDetails";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIPDETAILS_WHERE = "SELECT COUNT(vmaTransactionSlipDetails) FROM VmaTransactionSlipDetails vmaTransactionSlipDetails WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaTransactionSlipDetails.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaTransactionSlipDetails.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaTransactionSlipDetails.itineraryNo IS NULL OR vmaTransactionSlipDetails.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1 =
		"vmaTransactionSlipDetails.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2 =
		"vmaTransactionSlipDetails.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3 =
		"(vmaTransactionSlipDetails.itineraryNo IS NULL OR vmaTransactionSlipDetails.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1 =
		"vmaTransactionSlipDetails.documentaryCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2 =
		"vmaTransactionSlipDetails.documentaryCode =:documentaryCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3 =
		"(vmaTransactionSlipDetails.documentaryCode IS NULL OR vmaTransactionSlipDetails.documentaryCode =:documentaryCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionSlipDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionSlipDetails exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionSlipDetails exists with the key {";
	

	
}
