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
import com.fds.nsw.nghiepvu.model.VmaTransactionReceipt;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionReceiptRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionReceiptModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionReceiptPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionReceiptRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionReceipt> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionReceiptUtil} to access the vma transaction receipt persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionReceipt create(long id) {
		VmaTransactionReceipt vmaTransactionReceipt = new VmaTransactionReceipt();

		
		//vmaTransactionReceipt.setPrimaryKey(id);

		return vmaTransactionReceipt;
	}

	/**
	 * Removes the vma transaction receipt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction receipt
	 * @return the vma transaction receipt that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt remove(long id)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction receipt with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction receipt
	 * @return the vma transaction receipt that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionReceipt remove(Serializable primaryKey)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		

		try {
			

			VmaTransactionReceipt vmaTransactionReceipt = findByPrimaryKey(primaryKey);

			if (vmaTransactionReceipt == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionReceiptException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionReceipt);
			return vmaTransactionReceipt;
		}
		catch (NoSuchVmaTransactionReceiptException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionReceipt remove(VmaTransactionReceipt VmaTransactionReceipt) throws SystemException {
	removeImpl(VmaTransactionReceipt);
	return VmaTransactionReceipt;
}

protected VmaTransactionReceipt removeImpl(
		VmaTransactionReceipt vmaTransactionReceipt) throws SystemException {
		try {
			repository.delete(vmaTransactionReceipt);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionReceipt;
	}

	
	public VmaTransactionReceipt updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionReceipt vmaTransactionReceipt,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionReceipt);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionReceipt;
	}

	
	public VmaTransactionReceipt findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction receipt with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction receipt
	 * @return the vma transaction receipt
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt findByPrimaryKey(long id)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		VmaTransactionReceipt vmaTransactionReceipt = fetchByPrimaryKey(id);

		if (vmaTransactionReceipt == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionReceiptException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionReceipt;
	}

	/**
	 * Returns the vma transaction receipt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction receipt
	 * @return the vma transaction receipt, or <code>null</code> if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionReceipt fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction receipt with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction receipt
	 * @return the vma transaction receipt, or <code>null</code> if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionReceipt vmaTransactionReceipt = null;

		

		if (vmaTransactionReceipt == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionReceipt> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionReceipt = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionReceipt;
	}

	/**
	 * Returns all the vma transaction receipts where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findByMaritimeCode(String maritimeCode)
		throws SystemException {
		return findByMaritimeCode(maritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction receipts where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of vma transaction receipts
	 * @param end the upper bound of the range of vma transaction receipts (not inclusive)
	 * @return the range of matching vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findByMaritimeCode(String maritimeCode,
		int start, int end) throws SystemException {
		return findByMaritimeCode(maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction receipts where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of vma transaction receipts
	 * @param end the upper bound of the range of vma transaction receipts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findByMaritimeCode(String maritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionReceipt> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONRECEIPT_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaTransactionReceiptModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<VmaTransactionReceipt>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction receipt in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction receipt
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a matching vma transaction receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt findByMaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		VmaTransactionReceipt vmaTransactionReceipt = fetchByMaritimeCode_First(maritimeCode,
				orderByComparator);

		if (vmaTransactionReceipt != null) {
			return vmaTransactionReceipt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionReceiptException(msg.toString());
	}

	/**
	 * Returns the first vma transaction receipt in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction receipt, or <code>null</code> if a matching vma transaction receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt fetchByMaritimeCode_First(
		String maritimeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionReceipt> list = findByMaritimeCode(maritimeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction receipt in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction receipt
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a matching vma transaction receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt findByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		VmaTransactionReceipt vmaTransactionReceipt = fetchByMaritimeCode_Last(maritimeCode,
				orderByComparator);

		if (vmaTransactionReceipt != null) {
			return vmaTransactionReceipt;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionReceiptException(msg.toString());
	}

	/**
	 * Returns the last vma transaction receipt in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction receipt, or <code>null</code> if a matching vma transaction receipt could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt fetchByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMaritimeCode(maritimeCode);

		List<VmaTransactionReceipt> list = findByMaritimeCode(maritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction receipts before and after the current vma transaction receipt in the ordered set where maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current vma transaction receipt
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction receipt
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionReceiptException if a vma transaction receipt with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionReceipt[] findByMaritimeCode_PrevAndNext(long id,
		String maritimeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionReceiptException, SystemException {
		VmaTransactionReceipt vmaTransactionReceipt = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionReceipt[] array = new VmaTransactionReceipt[3];

			array[0] = getByMaritimeCode_PrevAndNext(
					vmaTransactionReceipt, maritimeCode, orderByComparator, true);

			array[1] = vmaTransactionReceipt;

			array[2] = getByMaritimeCode_PrevAndNext(
					vmaTransactionReceipt, maritimeCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionReceipt getByMaritimeCode_PrevAndNext(
		 VmaTransactionReceipt vmaTransactionReceipt,
		String maritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONRECEIPT_WHERE);

		if (maritimeCode == null) {
			query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (maritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
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

		else {
			query.append(VmaTransactionReceiptModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionReceipt);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionReceipt> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction receipts.
	 *
	 * @return the vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction receipts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction receipts
	 * @param end the upper bound of the range of vma transaction receipts (not inclusive)
	 * @return the range of vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction receipts.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction receipts
	 * @param end the upper bound of the range of vma transaction receipts (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionReceipt> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionReceipt> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONRECEIPT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONRECEIPT.concat(VmaTransactionReceiptModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionReceipt>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction receipts where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMaritimeCode(String maritimeCode)
		throws SystemException {
		for (VmaTransactionReceipt vmaTransactionReceipt : findByMaritimeCode(
				maritimeCode)) {
			repository.delete(vmaTransactionReceipt);
		}
	}

	/**
	 * Removes all the vma transaction receipts from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionReceipt vmaTransactionReceipt : findAll()) {
			repository.delete(vmaTransactionReceipt);
		}
	}

	/**
	 * Returns the number of vma transaction receipts where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONRECEIPT_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of vma transaction receipts.
	 *
	 * @return the number of vma transaction receipts
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONRECEIPT).build();

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
	 * Initializes the vma transaction receipt persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONRECEIPT = "SELECT vmaTransactionReceipt FROM VmaTransactionReceipt vmaTransactionReceipt";
	private static final String _SQL_SELECT_VMATRANSACTIONRECEIPT_WHERE = "SELECT vmaTransactionReceipt FROM VmaTransactionReceipt vmaTransactionReceipt WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONRECEIPT = "SELECT COUNT(vmaTransactionReceipt) FROM VmaTransactionReceipt vmaTransactionReceipt";
	private static final String _SQL_COUNT_VMATRANSACTIONRECEIPT_WHERE = "SELECT COUNT(vmaTransactionReceipt) FROM VmaTransactionReceipt vmaTransactionReceipt WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "vmaTransactionReceipt.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "vmaTransactionReceipt.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(vmaTransactionReceipt.maritimeCode IS NULL OR vmaTransactionReceipt.maritimeCode =:maritimeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionReceipt.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionReceipt exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionReceipt exists with the key {";
	

	
}
