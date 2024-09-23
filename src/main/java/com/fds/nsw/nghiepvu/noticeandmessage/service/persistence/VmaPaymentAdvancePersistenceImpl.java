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
import com.fds.nsw.nghiepvu.model.VmaPaymentAdvance;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaPaymentAdvanceRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaPaymentAdvanceModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaPaymentAdvancePersistenceImpl extends BasePersistence {
	@Autowired
	VmaPaymentAdvanceRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaPaymentAdvance> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaPaymentAdvanceUtil} to access the vma payment advance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaPaymentAdvance create(long id) {
		VmaPaymentAdvance vmaPaymentAdvance = new VmaPaymentAdvance();

		
		//vmaPaymentAdvance.setPrimaryKey(id);

		return vmaPaymentAdvance;
	}

	/**
	 * Removes the vma payment advance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma payment advance
	 * @return the vma payment advance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance remove(long id)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma payment advance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma payment advance
	 * @return the vma payment advance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPaymentAdvance remove(Serializable primaryKey)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		

		try {
			

			VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(primaryKey);

			if (vmaPaymentAdvance == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaPaymentAdvanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaPaymentAdvance);
			return vmaPaymentAdvance;
		}
		catch (NoSuchVmaPaymentAdvanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaPaymentAdvance remove(VmaPaymentAdvance VmaPaymentAdvance) throws SystemException {
	removeImpl(VmaPaymentAdvance);
	return VmaPaymentAdvance;
}

protected VmaPaymentAdvance removeImpl(VmaPaymentAdvance vmaPaymentAdvance)
		throws SystemException {
		try {
			repository.delete(vmaPaymentAdvance);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPaymentAdvance;
	}

	
	public VmaPaymentAdvance updateImpl(
		com.fds.nsw.nghiepvu.model.VmaPaymentAdvance vmaPaymentAdvance,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaPaymentAdvance);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPaymentAdvance;
	}

	
	public VmaPaymentAdvance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma payment advance with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException} if it could not be found.
	 *
	 * @param id the primary key of the vma payment advance
	 * @return the vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByPrimaryKey(long id)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByPrimaryKey(id);

		if (vmaPaymentAdvance == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaPaymentAdvanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaPaymentAdvance;
	}

	/**
	 * Returns the vma payment advance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma payment advance
	 * @return the vma payment advance, or <code>null</code> if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPaymentAdvance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma payment advance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma payment advance
	 * @return the vma payment advance, or <code>null</code> if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByPrimaryKey(long id)
		throws SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = null;

		

		if (vmaPaymentAdvance == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaPaymentAdvance> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaPaymentAdvance = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaPaymentAdvance;
	}

	/**
	 * Returns all the vma payment advances where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode(String shipAgencyCode)
		throws SystemException {
		return findByshipAgencyCode(shipAgencyCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode(String shipAgencyCode,
		int start, int end) throws SystemException {
		return findByshipAgencyCode(shipAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode(String shipAgencyCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				list = (List<VmaPaymentAdvance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_First(String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_First(shipAgencyCode,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_First(
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPaymentAdvance> list = findByshipAgencyCode(shipAgencyCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_Last(String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_Last(shipAgencyCode,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_Last(String shipAgencyCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByshipAgencyCode(shipAgencyCode);

		List<VmaPaymentAdvance> list = findByshipAgencyCode(shipAgencyCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment advances before and after the current vma payment advance in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param id the primary key of the current vma payment advance
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance[] findByshipAgencyCode_PrevAndNext(long id,
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentAdvance[] array = new VmaPaymentAdvance[3];

			array[0] = getByshipAgencyCode_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, orderByComparator, true);

			array[1] = vmaPaymentAdvance;

			array[2] = getByshipAgencyCode_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentAdvance getByshipAgencyCode_PrevAndNext(
		 VmaPaymentAdvance vmaPaymentAdvance,
		String shipAgencyCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
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

		

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentAdvance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentAdvance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment advances where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @return the matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_paymentType(
		String shipAgencyCode, int paymentType) throws SystemException {
		return findByshipAgencyCode_paymentType(shipAgencyCode, paymentType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_paymentType(
		String shipAgencyCode, int paymentType, int start, int end)
		throws SystemException {
		return findByshipAgencyCode_paymentType(shipAgencyCode, paymentType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_paymentType(
		String shipAgencyCode, int paymentType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_PAYMENTTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				builder.appendNamedParameterMap("paymentType", paymentType);

				list = (List<VmaPaymentAdvance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_paymentType_First(
		String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_paymentType_First(shipAgencyCode,
				paymentType, orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(", paymentType=");
		msg.append(paymentType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_paymentType_First(
		String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = findByshipAgencyCode_paymentType(shipAgencyCode,
				paymentType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_paymentType_Last(
		String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_paymentType_Last(shipAgencyCode,
				paymentType, orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(", paymentType=");
		msg.append(paymentType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_paymentType_Last(
		String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByshipAgencyCode_paymentType(shipAgencyCode,
				paymentType);

		List<VmaPaymentAdvance> list = findByshipAgencyCode_paymentType(shipAgencyCode,
				paymentType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment advances before and after the current vma payment advance in the ordered set where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param id the primary key of the current vma payment advance
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance[] findByshipAgencyCode_paymentType_PrevAndNext(
		long id, String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentAdvance[] array = new VmaPaymentAdvance[3];

			array[0] = getByshipAgencyCode_paymentType_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, paymentType,
					orderByComparator, true);

			array[1] = vmaPaymentAdvance;

			array[2] = getByshipAgencyCode_paymentType_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, paymentType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentAdvance getByshipAgencyCode_paymentType_PrevAndNext(
		 VmaPaymentAdvance vmaPaymentAdvance,
		String shipAgencyCode, int paymentType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_2);
			}
		}

		query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_PAYMENTTYPE_2);

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

		

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		builder.appendNamedParameterMap("paymentType", paymentType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentAdvance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentAdvance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment advances where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findBytransactionTypeCode(
		String transactionTypeCode) throws SystemException {
		return findBytransactionTypeCode(transactionTypeCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances where transactionTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findBytransactionTypeCode(
		String transactionTypeCode, int start, int end)
		throws SystemException {
		return findBytransactionTypeCode(transactionTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances where transactionTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findBytransactionTypeCode(
		String transactionTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				list = (List<VmaPaymentAdvance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment advance in the ordered set where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findBytransactionTypeCode_First(
		String transactionTypeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchBytransactionTypeCode_First(transactionTypeCode,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("transactionTypeCode=");
		msg.append(transactionTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the first vma payment advance in the ordered set where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchBytransactionTypeCode_First(
		String transactionTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPaymentAdvance> list = findBytransactionTypeCode(transactionTypeCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment advance in the ordered set where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findBytransactionTypeCode_Last(
		String transactionTypeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchBytransactionTypeCode_Last(transactionTypeCode,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("transactionTypeCode=");
		msg.append(transactionTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the last vma payment advance in the ordered set where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchBytransactionTypeCode_Last(
		String transactionTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBytransactionTypeCode(transactionTypeCode);

		List<VmaPaymentAdvance> list = findBytransactionTypeCode(transactionTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment advances before and after the current vma payment advance in the ordered set where transactionTypeCode = &#63;.
	 *
	 * @param id the primary key of the current vma payment advance
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance[] findBytransactionTypeCode_PrevAndNext(long id,
		String transactionTypeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentAdvance[] array = new VmaPaymentAdvance[3];

			array[0] = getBytransactionTypeCode_PrevAndNext(
					vmaPaymentAdvance, transactionTypeCode, orderByComparator,
					true);

			array[1] = vmaPaymentAdvance;

			array[2] = getBytransactionTypeCode_PrevAndNext(
					vmaPaymentAdvance, transactionTypeCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentAdvance getBytransactionTypeCode_PrevAndNext(
		 VmaPaymentAdvance vmaPaymentAdvance,
		String transactionTypeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

		if (transactionTypeCode == null) {
			query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
		}
		else {
			if (transactionTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
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

		

		if (transactionTypeCode != null) {
			builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentAdvance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentAdvance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment advances where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_transactionTypeCode(
		String shipAgencyCode, String transactionTypeCode)
		throws SystemException {
		return findByshipAgencyCode_transactionTypeCode(shipAgencyCode,
			transactionTypeCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_transactionTypeCode(
		String shipAgencyCode, String transactionTypeCode, int start, int end)
		throws SystemException {
		return findByshipAgencyCode_transactionTypeCode(shipAgencyCode,
			transactionTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByshipAgencyCode_transactionTypeCode(
		String shipAgencyCode, String transactionTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				list = (List<VmaPaymentAdvance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_transactionTypeCode_First(
		String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_transactionTypeCode_First(shipAgencyCode,
				transactionTypeCode, orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(", transactionTypeCode=");
		msg.append(transactionTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the first vma payment advance in the ordered set where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_transactionTypeCode_First(
		String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = findByshipAgencyCode_transactionTypeCode(shipAgencyCode,
				transactionTypeCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByshipAgencyCode_transactionTypeCode_Last(
		String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByshipAgencyCode_transactionTypeCode_Last(shipAgencyCode,
				transactionTypeCode, orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(", transactionTypeCode=");
		msg.append(transactionTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the last vma payment advance in the ordered set where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByshipAgencyCode_transactionTypeCode_Last(
		String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByshipAgencyCode_transactionTypeCode(shipAgencyCode,
				transactionTypeCode);

		List<VmaPaymentAdvance> list = findByshipAgencyCode_transactionTypeCode(shipAgencyCode,
				transactionTypeCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment advances before and after the current vma payment advance in the ordered set where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param id the primary key of the current vma payment advance
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance[] findByshipAgencyCode_transactionTypeCode_PrevAndNext(
		long id, String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentAdvance[] array = new VmaPaymentAdvance[3];

			array[0] = getByshipAgencyCode_transactionTypeCode_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, transactionTypeCode,
					orderByComparator, true);

			array[1] = vmaPaymentAdvance;

			array[2] = getByshipAgencyCode_transactionTypeCode_PrevAndNext(
					vmaPaymentAdvance, shipAgencyCode, transactionTypeCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentAdvance getByshipAgencyCode_transactionTypeCode_PrevAndNext(
		 VmaPaymentAdvance vmaPaymentAdvance,
		String shipAgencyCode, String transactionTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2);
			}
		}

		if (transactionTypeCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
		}
		else {
			if (transactionTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
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

		

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		if (transactionTypeCode != null) {
			builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentAdvance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentAdvance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment advances where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByportofAuthority(String portofAuthority)
		throws SystemException {
		return findByportofAuthority(portofAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByportofAuthority(
		String portofAuthority, int start, int end) throws SystemException {
		return findByportofAuthority(portofAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findByportofAuthority(
		String portofAuthority, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				list = (List<VmaPaymentAdvance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment advance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByportofAuthority_First(portofAuthority,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the first vma payment advance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPaymentAdvance> list = findByportofAuthority(portofAuthority,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment advance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance findByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = fetchByportofAuthority_Last(portofAuthority,
				orderByComparator);

		if (vmaPaymentAdvance != null) {
			return vmaPaymentAdvance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentAdvanceException(msg.toString());
	}

	/**
	 * Returns the last vma payment advance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment advance, or <code>null</code> if a matching vma payment advance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance fetchByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByportofAuthority(portofAuthority);

		List<VmaPaymentAdvance> list = findByportofAuthority(portofAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment advances before and after the current vma payment advance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma payment advance
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment advance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentAdvanceException if a vma payment advance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentAdvance[] findByportofAuthority_PrevAndNext(long id,
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentAdvanceException, SystemException {
		VmaPaymentAdvance vmaPaymentAdvance = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentAdvance[] array = new VmaPaymentAdvance[3];

			array[0] = getByportofAuthority_PrevAndNext(
					vmaPaymentAdvance, portofAuthority, orderByComparator, true);

			array[1] = vmaPaymentAdvance;

			array[2] = getByportofAuthority_PrevAndNext(
					vmaPaymentAdvance, portofAuthority, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentAdvance getByportofAuthority_PrevAndNext(
		 VmaPaymentAdvance vmaPaymentAdvance,
		String portofAuthority, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTADVANCE_WHERE);

		if (portofAuthority == null) {
			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
		}
		else {
			if (portofAuthority.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
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

		

		if (portofAuthority != null) {
			builder.appendNamedParameterMap("portofAuthority", portofAuthority);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentAdvance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentAdvance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment advances.
	 *
	 * @return the vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment advances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @return the range of vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment advances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma payment advances
	 * @param end the upper bound of the range of vma payment advances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentAdvance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentAdvance> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAPAYMENTADVANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAPAYMENTADVANCE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaPaymentAdvance>) queryFactory.getResultList(builder);
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
	 * Removes all the vma payment advances where shipAgencyCode = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByshipAgencyCode(String shipAgencyCode)
		throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findByshipAgencyCode(
				shipAgencyCode)) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Removes all the vma payment advances where shipAgencyCode = &#63; and paymentType = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByshipAgencyCode_paymentType(String shipAgencyCode,
		int paymentType) throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findByshipAgencyCode_paymentType(
				shipAgencyCode, paymentType)) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Removes all the vma payment advances where transactionTypeCode = &#63; from the database.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBytransactionTypeCode(String transactionTypeCode)
		throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findBytransactionTypeCode(
				transactionTypeCode)) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Removes all the vma payment advances where shipAgencyCode = &#63; and transactionTypeCode = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByshipAgencyCode_transactionTypeCode(
		String shipAgencyCode, String transactionTypeCode)
		throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findByshipAgencyCode_transactionTypeCode(
				shipAgencyCode, transactionTypeCode)) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Removes all the vma payment advances where portofAuthority = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority(String portofAuthority)
		throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findByportofAuthority(
				portofAuthority)) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Removes all the vma payment advances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaPaymentAdvance vmaPaymentAdvance : findAll()) {
			repository.delete(vmaPaymentAdvance);
		}
	}

	/**
	 * Returns the number of vma payment advances where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the number of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipAgencyCode(String shipAgencyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
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
	 * Returns the number of vma payment advances where shipAgencyCode = &#63; and paymentType = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param paymentType the payment type
	 * @return the number of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipAgencyCode_paymentType(String shipAgencyCode,
		int paymentType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_PAYMENTTYPE_2);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				builder.appendNamedParameterMap("paymentType", paymentType);

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
	 * Returns the number of vma payment advances where transactionTypeCode = &#63;.
	 *
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countBytransactionTypeCode(String transactionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPAYMENTADVANCE_WHERE);

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
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
	 * Returns the number of vma payment advances where shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipAgencyCode_transactionTypeCode(
		String shipAgencyCode, String transactionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMAPAYMENTADVANCE_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
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
	 * Returns the number of vma payment advances where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the number of matching vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority(String portofAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPAYMENTADVANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
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
	 * Returns the number of vma payment advances.
	 *
	 * @return the number of vma payment advances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAPAYMENTADVANCE).build();

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
	 * Initializes the vma payment advance persistence.
	 */
	private static final String _SQL_SELECT_VMAPAYMENTADVANCE = "SELECT vmaPaymentAdvance FROM VmaPaymentAdvance vmaPaymentAdvance";
	private static final String _SQL_SELECT_VMAPAYMENTADVANCE_WHERE = "SELECT vmaPaymentAdvance FROM VmaPaymentAdvance vmaPaymentAdvance WHERE ";
	private static final String _SQL_COUNT_VMAPAYMENTADVANCE = "SELECT COUNT(vmaPaymentAdvance) FROM VmaPaymentAdvance vmaPaymentAdvance";
	private static final String _SQL_COUNT_VMAPAYMENTADVANCE_WHERE = "SELECT COUNT(vmaPaymentAdvance) FROM VmaPaymentAdvance vmaPaymentAdvance WHERE ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1 = "vmaPaymentAdvance.shipAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2 = "vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3 = "(vmaPaymentAdvance.shipAgencyCode IS NULL OR vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode)";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_1 =
		"vmaPaymentAdvance.shipAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_2 =
		"vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_SHIPAGENCYCODE_3 =
		"(vmaPaymentAdvance.shipAgencyCode IS NULL OR vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode) AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_PAYMENTTYPE_PAYMENTTYPE_2 =
		"vmaPaymentAdvance.paymentType =:paymentType";
	private static final String _FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaPaymentAdvance.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaPaymentAdvance.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaPaymentAdvance.transactionTypeCode IS NULL OR vmaPaymentAdvance.transactionTypeCode =:transactionTypeCode)";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1 =
		"vmaPaymentAdvance.shipAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2 =
		"vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3 =
		"(vmaPaymentAdvance.shipAgencyCode IS NULL OR vmaPaymentAdvance.shipAgencyCode =:shipAgencyCode) AND ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaPaymentAdvance.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaPaymentAdvance.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaPaymentAdvance.transactionTypeCode IS NULL OR vmaPaymentAdvance.transactionTypeCode =:transactionTypeCode)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaPaymentAdvance.portofAuthority IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaPaymentAdvance.portofAuthority =:portofAuthority";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaPaymentAdvance.portofAuthority IS NULL OR vmaPaymentAdvance.portofAuthority =:portofAuthority)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaPaymentAdvance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaPaymentAdvance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaPaymentAdvance exists with the key {";
	

	
}
