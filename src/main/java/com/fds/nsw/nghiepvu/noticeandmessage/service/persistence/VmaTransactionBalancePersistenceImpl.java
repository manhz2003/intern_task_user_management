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
import com.fds.nsw.nghiepvu.model.VmaTransactionBalance;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionBalanceRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionBalanceModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionBalancePersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionBalanceRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionBalance> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionBalanceUtil} to access the vma transaction balance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionBalance create(long id) {
		VmaTransactionBalance vmaTransactionBalance = new VmaTransactionBalance();

		
		//vmaTransactionBalance.setPrimaryKey(id);

		return vmaTransactionBalance;
	}

	/**
	 * Removes the vma transaction balance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction balance
	 * @return the vma transaction balance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance remove(long id)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction balance with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction balance
	 * @return the vma transaction balance that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionBalance remove(Serializable primaryKey)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		

		try {
			

			VmaTransactionBalance vmaTransactionBalance = findByPrimaryKey(primaryKey);

			if (vmaTransactionBalance == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionBalance);
			return vmaTransactionBalance;
		}
		catch (NoSuchVmaTransactionBalanceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionBalance remove(VmaTransactionBalance VmaTransactionBalance) throws SystemException {
	removeImpl(VmaTransactionBalance);
	return VmaTransactionBalance;
}

protected VmaTransactionBalance removeImpl(
		VmaTransactionBalance vmaTransactionBalance) throws SystemException {
		try {
			repository.delete(vmaTransactionBalance);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionBalance;
	}

	
	public VmaTransactionBalance updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionBalance vmaTransactionBalance,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionBalance);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionBalance;
	}

	
	public VmaTransactionBalance findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction balance with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction balance
	 * @return the vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByPrimaryKey(long id)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByPrimaryKey(id);

		if (vmaTransactionBalance == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionBalanceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionBalance;
	}

	/**
	 * Returns the vma transaction balance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction balance
	 * @return the vma transaction balance, or <code>null</code> if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionBalance fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction balance with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction balance
	 * @return the vma transaction balance, or <code>null</code> if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionBalance vmaTransactionBalance = null;

		

		if (vmaTransactionBalance == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionBalance> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionBalance = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionBalance;
	}

	/**
	 * Returns all the vma transaction balances where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority(
		String portofAuthority) throws SystemException {
		return findByportofAuthority(portofAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction balances where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @return the range of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority(
		String portofAuthority, int start, int end) throws SystemException {
		return findByportofAuthority(portofAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction balances where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority(
		String portofAuthority, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionBalance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

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

				list = (List<VmaTransactionBalance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction balance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_First(portofAuthority,
				orderByComparator);

		if (vmaTransactionBalance != null) {
			return vmaTransactionBalance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionBalanceException(msg.toString());
	}

	/**
	 * Returns the first vma transaction balance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionBalance> list = findByportofAuthority(portofAuthority,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction balance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_Last(portofAuthority,
				orderByComparator);

		if (vmaTransactionBalance != null) {
			return vmaTransactionBalance;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionBalanceException(msg.toString());
	}

	/**
	 * Returns the last vma transaction balance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByportofAuthority(portofAuthority);

		List<VmaTransactionBalance> list = findByportofAuthority(portofAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction balances before and after the current vma transaction balance in the ordered set where portofAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma transaction balance
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance[] findByportofAuthority_PrevAndNext(long id,
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionBalance[] array = new VmaTransactionBalance[3];

			array[0] = getByportofAuthority_PrevAndNext(
					vmaTransactionBalance, portofAuthority, orderByComparator,
					true);

			array[1] = vmaTransactionBalance;

			array[2] = getByportofAuthority_PrevAndNext(
					vmaTransactionBalance, portofAuthority, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionBalance getByportofAuthority_PrevAndNext(
		 VmaTransactionBalance vmaTransactionBalance,
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

		query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionBalance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionBalance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and transactionTypeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException} if it could not be found.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_transactionTypeCode(portofAuthority,
				transactionTypeCode);

		if (vmaTransactionBalance == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portofAuthority=");
			msg.append(portofAuthority);

			msg.append(", transactionTypeCode=");
			msg.append(transactionTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionBalanceException(msg.toString());
		}

		return vmaTransactionBalance;
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws SystemException {
		return fetchByportofAuthority_transactionTypeCode(portofAuthority,
			transactionTypeCode, true);
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode,
		boolean retrieveFromCache) throws SystemException {
		VmaTransactionBalance vmaTransactionBalance = null;
		if (vmaTransactionBalance == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionBalance.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				vmaTransactionBalance = (VmaTransactionBalance) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionBalance;
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and shipAgencyCode = &#63; and transactionTypeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException} if it could not be found.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_shipAgencyCode_transactionTypeCode(
		String portofAuthority, String shipAgencyCode,
		String transactionTypeCode)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_shipAgencyCode_transactionTypeCode(portofAuthority,
				shipAgencyCode, transactionTypeCode);

		if (vmaTransactionBalance == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portofAuthority=");
			msg.append(portofAuthority);

			msg.append(", shipAgencyCode=");
			msg.append(shipAgencyCode);

			msg.append(", transactionTypeCode=");
			msg.append(transactionTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionBalanceException(msg.toString());
		}

		return vmaTransactionBalance;
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and shipAgencyCode = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_shipAgencyCode_transactionTypeCode(
		String portofAuthority, String shipAgencyCode,
		String transactionTypeCode) throws SystemException {
		return fetchByportofAuthority_shipAgencyCode_transactionTypeCode(portofAuthority,
			shipAgencyCode, transactionTypeCode, true);
	}

	/**
	 * Returns the vma transaction balance where portofAuthority = &#63; and shipAgencyCode = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_shipAgencyCode_transactionTypeCode(
		String portofAuthority, String shipAgencyCode,
		String transactionTypeCode, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionBalance vmaTransactionBalance = null;
		if (vmaTransactionBalance == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2);
				}
			}

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionBalance.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				vmaTransactionBalance = (VmaTransactionBalance) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionBalance;
	}

	/**
	 * Returns all the vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @return the matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority_shipAgencyCode(
		String portofAuthority, String shipAgencyCode)
		throws SystemException {
		return findByportofAuthority_shipAgencyCode(portofAuthority,
			shipAgencyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @return the range of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority_shipAgencyCode(
		String portofAuthority, String shipAgencyCode, int start, int end)
		throws SystemException {
		return findByportofAuthority_shipAgencyCode(portofAuthority,
			shipAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findByportofAuthority_shipAgencyCode(
		String portofAuthority, String shipAgencyCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionBalance> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_2);
				}
			}

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
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

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				list = (List<VmaTransactionBalance>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction balance in the ordered set where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_shipAgencyCode_First(
		String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_shipAgencyCode_First(portofAuthority,
				shipAgencyCode, orderByComparator);

		if (vmaTransactionBalance != null) {
			return vmaTransactionBalance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(", shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionBalanceException(msg.toString());
	}

	/**
	 * Returns the first vma transaction balance in the ordered set where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_shipAgencyCode_First(
		String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionBalance> list = findByportofAuthority_shipAgencyCode(portofAuthority,
				shipAgencyCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction balance in the ordered set where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance findByportofAuthority_shipAgencyCode_Last(
		String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = fetchByportofAuthority_shipAgencyCode_Last(portofAuthority,
				shipAgencyCode, orderByComparator);

		if (vmaTransactionBalance != null) {
			return vmaTransactionBalance;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(", shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionBalanceException(msg.toString());
	}

	/**
	 * Returns the last vma transaction balance in the ordered set where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction balance, or <code>null</code> if a matching vma transaction balance could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance fetchByportofAuthority_shipAgencyCode_Last(
		String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByportofAuthority_shipAgencyCode(portofAuthority,
				shipAgencyCode);

		List<VmaTransactionBalance> list = findByportofAuthority_shipAgencyCode(portofAuthority,
				shipAgencyCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction balances before and after the current vma transaction balance in the ordered set where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param id the primary key of the current vma transaction balance
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction balance
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionBalanceException if a vma transaction balance with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance[] findByportofAuthority_shipAgencyCode_PrevAndNext(
		long id, String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionBalance[] array = new VmaTransactionBalance[3];

			array[0] = getByportofAuthority_shipAgencyCode_PrevAndNext(
					vmaTransactionBalance, portofAuthority, shipAgencyCode,
					orderByComparator, true);

			array[1] = vmaTransactionBalance;

			array[2] = getByportofAuthority_shipAgencyCode_PrevAndNext(
					vmaTransactionBalance, portofAuthority, shipAgencyCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionBalance getByportofAuthority_shipAgencyCode_PrevAndNext(
		 VmaTransactionBalance vmaTransactionBalance,
		String portofAuthority, String shipAgencyCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONBALANCE_WHERE);

		if (portofAuthority == null) {
			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_1);
		}
		else {
			if (portofAuthority.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_2);
			}
		}

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
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

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionBalance);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionBalance> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction balances.
	 *
	 * @return the vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction balances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @return the range of vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction balances.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction balances
	 * @param end the upper bound of the range of vma transaction balances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionBalance> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionBalance> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONBALANCE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONBALANCE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionBalance>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction balances where portofAuthority = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority(String portofAuthority)
		throws SystemException {
		for (VmaTransactionBalance vmaTransactionBalance : findByportofAuthority(
				portofAuthority)) {
			repository.delete(vmaTransactionBalance);
		}
	}

	/**
	 * Removes the vma transaction balance where portofAuthority = &#63; and transactionTypeCode = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the vma transaction balance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance removeByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = findByportofAuthority_transactionTypeCode(portofAuthority,
				transactionTypeCode);

		repository.delete(vmaTransactionBalance);
			return vmaTransactionBalance;
	}

	/**
	 * Removes the vma transaction balance where portofAuthority = &#63; and shipAgencyCode = &#63; and transactionTypeCode = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the vma transaction balance that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionBalance removeByportofAuthority_shipAgencyCode_transactionTypeCode(
		String portofAuthority, String shipAgencyCode,
		String transactionTypeCode)
		throws NoSuchVmaTransactionBalanceException, SystemException {
		VmaTransactionBalance vmaTransactionBalance = findByportofAuthority_shipAgencyCode_transactionTypeCode(portofAuthority,
				shipAgencyCode, transactionTypeCode);

		repository.delete(vmaTransactionBalance);
			return vmaTransactionBalance;
	}

	/**
	 * Removes all the vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority_shipAgencyCode(String portofAuthority,
		String shipAgencyCode) throws SystemException {
		for (VmaTransactionBalance vmaTransactionBalance : findByportofAuthority_shipAgencyCode(
				portofAuthority, shipAgencyCode)) {
			repository.delete(vmaTransactionBalance);
		}
	}

	/**
	 * Removes all the vma transaction balances from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionBalance vmaTransactionBalance : findAll()) {
			repository.delete(vmaTransactionBalance);
		}
	}

	/**
	 * Returns the number of vma transaction balances where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the number of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority(String portofAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONBALANCE_WHERE);

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
	 * Returns the number of vma transaction balances where portofAuthority = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
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
	 * Returns the number of vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority_shipAgencyCode_transactionTypeCode(
		String portofAuthority, String shipAgencyCode,
		String transactionTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2);
				}
			}

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

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
	 * Returns the number of vma transaction balances where portofAuthority = &#63; and shipAgencyCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param shipAgencyCode the ship agency code
	 * @return the number of matching vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority_shipAgencyCode(String portofAuthority,
		String shipAgencyCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONBALANCE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_2);
				}
			}

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

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
	 * Returns the number of vma transaction balances.
	 *
	 * @return the number of vma transaction balances
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONBALANCE).build();

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
	 * Initializes the vma transaction balance persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONBALANCE = "SELECT vmaTransactionBalance FROM VmaTransactionBalance vmaTransactionBalance";
	private static final String _SQL_SELECT_VMATRANSACTIONBALANCE_WHERE = "SELECT vmaTransactionBalance FROM VmaTransactionBalance vmaTransactionBalance WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONBALANCE = "SELECT COUNT(vmaTransactionBalance) FROM VmaTransactionBalance vmaTransactionBalance";
	private static final String _SQL_COUNT_VMATRANSACTIONBALANCE_WHERE = "SELECT COUNT(vmaTransactionBalance) FROM VmaTransactionBalance vmaTransactionBalance WHERE ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaTransactionBalance.portofAuthority IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaTransactionBalance.portofAuthority =:portofAuthority";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaTransactionBalance.portofAuthority IS NULL OR vmaTransactionBalance.portofAuthority =:portofAuthority)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1 =
		"vmaTransactionBalance.portofAuthority IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2 =
		"vmaTransactionBalance.portofAuthority =:portofAuthority AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3 =
		"(vmaTransactionBalance.portofAuthority IS NULL OR vmaTransactionBalance.portofAuthority =:portofAuthority) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaTransactionBalance.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaTransactionBalance.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaTransactionBalance.transactionTypeCode IS NULL OR vmaTransactionBalance.transactionTypeCode =:transactionTypeCode)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1 =
		"vmaTransactionBalance.portofAuthority IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2 =
		"vmaTransactionBalance.portofAuthority =:portofAuthority AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3 =
		"(vmaTransactionBalance.portofAuthority IS NULL OR vmaTransactionBalance.portofAuthority =:portofAuthority) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_1 =
		"vmaTransactionBalance.shipAgencyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_2 =
		"vmaTransactionBalance.shipAgencyCode =:shipAgencyCode AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_SHIPAGENCYCODE_3 =
		"(vmaTransactionBalance.shipAgencyCode IS NULL OR vmaTransactionBalance.shipAgencyCode =:shipAgencyCode) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaTransactionBalance.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaTransactionBalance.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaTransactionBalance.transactionTypeCode IS NULL OR vmaTransactionBalance.transactionTypeCode =:transactionTypeCode)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_1 =
		"vmaTransactionBalance.portofAuthority IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_2 =
		"vmaTransactionBalance.portofAuthority =:portofAuthority AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_PORTOFAUTHORITY_3 =
		"(vmaTransactionBalance.portofAuthority IS NULL OR vmaTransactionBalance.portofAuthority =:portofAuthority) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_1 =
		"vmaTransactionBalance.shipAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_2 =
		"vmaTransactionBalance.shipAgencyCode =:shipAgencyCode";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_SHIPAGENCYCODE_SHIPAGENCYCODE_3 =
		"(vmaTransactionBalance.shipAgencyCode IS NULL OR vmaTransactionBalance.shipAgencyCode =:shipAgencyCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionBalance.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionBalance exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionBalance exists with the key {";
	

	
}
