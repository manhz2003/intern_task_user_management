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
import com.fds.nsw.nghiepvu.model.VmaTransactionType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionTypePersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionTypeUtil} to access the vma transaction type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionType create(long id) {
		VmaTransactionType vmaTransactionType = new VmaTransactionType();

		
		//vmaTransactionType.setPrimaryKey(id);

		return vmaTransactionType;
	}

	/**
	 * Removes the vma transaction type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction type
	 * @return the vma transaction type that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType remove(long id)
		throws NoSuchVmaTransactionTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction type
	 * @return the vma transaction type that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionType remove(Serializable primaryKey)
		throws NoSuchVmaTransactionTypeException, SystemException {
		

		try {
			

			VmaTransactionType vmaTransactionType = findByPrimaryKey(primaryKey);

			if (vmaTransactionType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionType);
			return vmaTransactionType;
		}
		catch (NoSuchVmaTransactionTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionType remove(VmaTransactionType VmaTransactionType) throws SystemException {
	removeImpl(VmaTransactionType);
	return VmaTransactionType;
}

protected VmaTransactionType removeImpl(
		VmaTransactionType vmaTransactionType) throws SystemException {
		try {
			repository.delete(vmaTransactionType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionType;
	}

	
	public VmaTransactionType updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionType vmaTransactionType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionType;
	}

	
	public VmaTransactionType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction type with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction type
	 * @return the vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType findByPrimaryKey(long id)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = fetchByPrimaryKey(id);

		if (vmaTransactionType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionType;
	}

	/**
	 * Returns the vma transaction type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction type
	 * @return the vma transaction type, or <code>null</code> if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction type
	 * @return the vma transaction type, or <code>null</code> if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionType vmaTransactionType = null;

		

		if (vmaTransactionType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionType> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionType;
	}

	/**
	 * Returns all the vma transaction types where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority(
		String portofAuthority) throws SystemException {
		return findByportofAuthority(portofAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction types where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @return the range of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority(
		String portofAuthority, int start, int end) throws SystemException {
		return findByportofAuthority(portofAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction types where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority(
		String portofAuthority, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONTYPE_WHERE);

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

				list = (List<VmaTransactionType>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction type in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType findByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = fetchByportofAuthority_First(portofAuthority,
				orderByComparator);

		if (vmaTransactionType != null) {
			return vmaTransactionType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionTypeException(msg.toString());
	}

	/**
	 * Returns the first vma transaction type in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction type, or <code>null</code> if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType fetchByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionType> list = findByportofAuthority(portofAuthority,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction type in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType findByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = fetchByportofAuthority_Last(portofAuthority,
				orderByComparator);

		if (vmaTransactionType != null) {
			return vmaTransactionType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionTypeException(msg.toString());
	}

	/**
	 * Returns the last vma transaction type in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction type, or <code>null</code> if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType fetchByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByportofAuthority(portofAuthority);

		List<VmaTransactionType> list = findByportofAuthority(portofAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction types before and after the current vma transaction type in the ordered set where portofAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma transaction type
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType[] findByportofAuthority_PrevAndNext(long id,
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionType[] array = new VmaTransactionType[3];

			array[0] = getByportofAuthority_PrevAndNext(
					vmaTransactionType, portofAuthority, orderByComparator, true);

			array[1] = vmaTransactionType;

			array[2] = getByportofAuthority_PrevAndNext(
					vmaTransactionType, portofAuthority, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionType getByportofAuthority_PrevAndNext(
		 VmaTransactionType vmaTransactionType,
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

		query.append(_SQL_SELECT_VMATRANSACTIONTYPE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction types where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @return the matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority_transactionLevel(
		String portofAuthority, int transactionLevel) throws SystemException {
		return findByportofAuthority_transactionLevel(portofAuthority,
			transactionLevel, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction types where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @return the range of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority_transactionLevel(
		String portofAuthority, int transactionLevel, int start, int end)
		throws SystemException {
		return findByportofAuthority_transactionLevel(portofAuthority,
			transactionLevel, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction types where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findByportofAuthority_transactionLevel(
		String portofAuthority, int transactionLevel, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONTYPE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_2);
				}
			}

			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_TRANSACTIONLEVEL_2);

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

				builder.appendNamedParameterMap("transactionLevel", transactionLevel);

				list = (List<VmaTransactionType>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction type in the ordered set where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType findByportofAuthority_transactionLevel_First(
		String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = fetchByportofAuthority_transactionLevel_First(portofAuthority,
				transactionLevel, orderByComparator);

		if (vmaTransactionType != null) {
			return vmaTransactionType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(", transactionLevel=");
		msg.append(transactionLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionTypeException(msg.toString());
	}

	/**
	 * Returns the first vma transaction type in the ordered set where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction type, or <code>null</code> if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType fetchByportofAuthority_transactionLevel_First(
		String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionType> list = findByportofAuthority_transactionLevel(portofAuthority,
				transactionLevel, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction type in the ordered set where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType findByportofAuthority_transactionLevel_Last(
		String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = fetchByportofAuthority_transactionLevel_Last(portofAuthority,
				transactionLevel, orderByComparator);

		if (vmaTransactionType != null) {
			return vmaTransactionType;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(", transactionLevel=");
		msg.append(transactionLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionTypeException(msg.toString());
	}

	/**
	 * Returns the last vma transaction type in the ordered set where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction type, or <code>null</code> if a matching vma transaction type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType fetchByportofAuthority_transactionLevel_Last(
		String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByportofAuthority_transactionLevel(portofAuthority,
				transactionLevel);

		List<VmaTransactionType> list = findByportofAuthority_transactionLevel(portofAuthority,
				transactionLevel, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction types before and after the current vma transaction type in the ordered set where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param id the primary key of the current vma transaction type
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionTypeException if a vma transaction type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionType[] findByportofAuthority_transactionLevel_PrevAndNext(
		long id, String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionTypeException, SystemException {
		VmaTransactionType vmaTransactionType = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionType[] array = new VmaTransactionType[3];

			array[0] = getByportofAuthority_transactionLevel_PrevAndNext(
					vmaTransactionType, portofAuthority, transactionLevel,
					orderByComparator, true);

			array[1] = vmaTransactionType;

			array[2] = getByportofAuthority_transactionLevel_PrevAndNext(
					vmaTransactionType, portofAuthority, transactionLevel,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionType getByportofAuthority_transactionLevel_PrevAndNext(
		 VmaTransactionType vmaTransactionType,
		String portofAuthority, int transactionLevel,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONTYPE_WHERE);

		if (portofAuthority == null) {
			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_1);
		}
		else {
			if (portofAuthority.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_2);
			}
		}

		query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_TRANSACTIONLEVEL_2);

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

		builder.appendNamedParameterMap("transactionLevel", transactionLevel);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction types.
	 *
	 * @return the vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @return the range of vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction types
	 * @param end the upper bound of the range of vma transaction types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONTYPE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionType>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction types where portofAuthority = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority(String portofAuthority)
		throws SystemException {
		for (VmaTransactionType vmaTransactionType : findByportofAuthority(
				portofAuthority)) {
			repository.delete(vmaTransactionType);
		}
	}

	/**
	 * Removes all the vma transaction types where portofAuthority = &#63; and transactionLevel = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority_transactionLevel(
		String portofAuthority, int transactionLevel) throws SystemException {
		for (VmaTransactionType vmaTransactionType : findByportofAuthority_transactionLevel(
				portofAuthority, transactionLevel)) {
			repository.delete(vmaTransactionType);
		}
	}

	/**
	 * Removes all the vma transaction types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionType vmaTransactionType : findAll()) {
			repository.delete(vmaTransactionType);
		}
	}

	/**
	 * Returns the number of vma transaction types where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the number of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority(String portofAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONTYPE_WHERE);

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
	 * Returns the number of vma transaction types where portofAuthority = &#63; and transactionLevel = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionLevel the transaction level
	 * @return the number of matching vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority_transactionLevel(String portofAuthority,
		int transactionLevel) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONTYPE_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_2);
				}
			}

			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_TRANSACTIONLEVEL_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();


				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				builder.appendNamedParameterMap("transactionLevel", transactionLevel);

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
	 * Returns the number of vma transaction types.
	 *
	 * @return the number of vma transaction types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONTYPE).build();

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
	 * Initializes the vma transaction type persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONTYPE = "SELECT vmaTransactionType FROM VmaTransactionType vmaTransactionType";
	private static final String _SQL_SELECT_VMATRANSACTIONTYPE_WHERE = "SELECT vmaTransactionType FROM VmaTransactionType vmaTransactionType WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONTYPE = "SELECT COUNT(vmaTransactionType) FROM VmaTransactionType vmaTransactionType";
	private static final String _SQL_COUNT_VMATRANSACTIONTYPE_WHERE = "SELECT COUNT(vmaTransactionType) FROM VmaTransactionType vmaTransactionType WHERE ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaTransactionType.portofAuthority IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaTransactionType.portofAuthority =:portofAuthority";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaTransactionType.portofAuthority IS NULL OR vmaTransactionType.portofAuthority =:portofAuthority)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_1 =
		"vmaTransactionType.portofAuthority IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_2 =
		"vmaTransactionType.portofAuthority =:portofAuthority AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_PORTOFAUTHORITY_3 =
		"(vmaTransactionType.portofAuthority IS NULL OR vmaTransactionType.portofAuthority =:portofAuthority) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONLEVEL_TRANSACTIONLEVEL_2 =
		"vmaTransactionType.transactionLevel =:transactionLevel";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionType exists with the key {";
	

	
}
