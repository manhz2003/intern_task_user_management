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
import com.fds.nsw.nghiepvu.model.VmaTransactionFunction;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionFunctionRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionFunctionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionFunctionPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionFunctionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionFunction> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionFunctionUtil} to access the vma transaction function persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionFunction create(long id) {
		VmaTransactionFunction vmaTransactionFunction = new VmaTransactionFunction();

		
		//vmaTransactionFunction.setPrimaryKey(id);

		return vmaTransactionFunction;
	}

	/**
	 * Removes the vma transaction function with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction function
	 * @return the vma transaction function that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction remove(long id)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction function with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction function
	 * @return the vma transaction function that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionFunction remove(Serializable primaryKey)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		

		try {
			

			VmaTransactionFunction vmaTransactionFunction = findByPrimaryKey(primaryKey);

			if (vmaTransactionFunction == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionFunctionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionFunction);
			return vmaTransactionFunction;
		}
		catch (NoSuchVmaTransactionFunctionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionFunction remove(VmaTransactionFunction VmaTransactionFunction) throws SystemException {
	removeImpl(VmaTransactionFunction);
	return VmaTransactionFunction;
}

protected VmaTransactionFunction removeImpl(
		VmaTransactionFunction vmaTransactionFunction)
		throws SystemException {
		try {
			repository.delete(vmaTransactionFunction);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionFunction;
	}

	
	public VmaTransactionFunction updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionFunction vmaTransactionFunction,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionFunction);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionFunction;
	}

	
	public VmaTransactionFunction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction function with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction function
	 * @return the vma transaction function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction findByPrimaryKey(long id)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = fetchByPrimaryKey(id);

		if (vmaTransactionFunction == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionFunctionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionFunction;
	}

	/**
	 * Returns the vma transaction function with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction function
	 * @return the vma transaction function, or <code>null</code> if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionFunction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction function with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction function
	 * @return the vma transaction function, or <code>null</code> if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionFunction vmaTransactionFunction = null;

		

		if (vmaTransactionFunction == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionFunction> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionFunction = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionFunction;
	}

	/**
	 * Returns all the vma transaction functions where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the matching vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findByportofAuthority(
		String portofAuthority) throws SystemException {
		return findByportofAuthority(portofAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction functions where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction functions
	 * @param end the upper bound of the range of vma transaction functions (not inclusive)
	 * @return the range of matching vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findByportofAuthority(
		String portofAuthority, int start, int end) throws SystemException {
		return findByportofAuthority(portofAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction functions where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma transaction functions
	 * @param end the upper bound of the range of vma transaction functions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findByportofAuthority(
		String portofAuthority, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionFunction> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONFUNCTION_WHERE);

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

				list = (List<VmaTransactionFunction>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction function in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction findByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = fetchByportofAuthority_First(portofAuthority,
				orderByComparator);

		if (vmaTransactionFunction != null) {
			return vmaTransactionFunction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionFunctionException(msg.toString());
	}

	/**
	 * Returns the first vma transaction function in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction function, or <code>null</code> if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction fetchByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionFunction> list = findByportofAuthority(portofAuthority,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction function in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction findByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = fetchByportofAuthority_Last(portofAuthority,
				orderByComparator);

		if (vmaTransactionFunction != null) {
			return vmaTransactionFunction;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionFunctionException(msg.toString());
	}

	/**
	 * Returns the last vma transaction function in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction function, or <code>null</code> if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction fetchByportofAuthority_Last(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByportofAuthority(portofAuthority);

		List<VmaTransactionFunction> list = findByportofAuthority(portofAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction functions before and after the current vma transaction function in the ordered set where portofAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma transaction function
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a vma transaction function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction[] findByportofAuthority_PrevAndNext(long id,
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionFunction[] array = new VmaTransactionFunction[3];

			array[0] = getByportofAuthority_PrevAndNext(
					vmaTransactionFunction, portofAuthority, orderByComparator,
					true);

			array[1] = vmaTransactionFunction;

			array[2] = getByportofAuthority_PrevAndNext(
					vmaTransactionFunction, portofAuthority, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionFunction getByportofAuthority_PrevAndNext(
		 VmaTransactionFunction vmaTransactionFunction,
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

		query.append(_SQL_SELECT_VMATRANSACTIONFUNCTION_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionFunction);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionFunction> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction function where portofAuthority = &#63; and transactionTypeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException} if it could not be found.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionFunctionException if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction findByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = fetchByportofAuthority_transactionTypeCode(portofAuthority,
				transactionTypeCode);

		if (vmaTransactionFunction == null) {
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

			throw new NoSuchVmaTransactionFunctionException(msg.toString());
		}

		return vmaTransactionFunction;
	}

	/**
	 * Returns the vma transaction function where portofAuthority = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction function, or <code>null</code> if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction fetchByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws SystemException {
		return fetchByportofAuthority_transactionTypeCode(portofAuthority,
			transactionTypeCode, true);
	}

	/**
	 * Returns the vma transaction function where portofAuthority = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction function, or <code>null</code> if a matching vma transaction function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction fetchByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode,
		boolean retrieveFromCache) throws SystemException {
		VmaTransactionFunction vmaTransactionFunction = null;
		if (vmaTransactionFunction == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONFUNCTION_WHERE);

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
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionFunction.class).build();

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				vmaTransactionFunction = (VmaTransactionFunction) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionFunction;
	}

	/**
	 * Returns all the vma transaction functions.
	 *
	 * @return the vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction functions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction functions
	 * @param end the upper bound of the range of vma transaction functions (not inclusive)
	 * @return the range of vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction functions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction functions
	 * @param end the upper bound of the range of vma transaction functions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionFunction> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionFunction> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONFUNCTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONFUNCTION;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionFunction>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction functions where portofAuthority = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority(String portofAuthority)
		throws SystemException {
		for (VmaTransactionFunction vmaTransactionFunction : findByportofAuthority(
				portofAuthority)) {
			repository.delete(vmaTransactionFunction);
		}
	}

	/**
	 * Removes the vma transaction function where portofAuthority = &#63; and transactionTypeCode = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the vma transaction function that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionFunction removeByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws NoSuchVmaTransactionFunctionException, SystemException {
		VmaTransactionFunction vmaTransactionFunction = findByportofAuthority_transactionTypeCode(portofAuthority,
				transactionTypeCode);

		repository.delete(vmaTransactionFunction);
			return vmaTransactionFunction;
	}

	/**
	 * Removes all the vma transaction functions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionFunction vmaTransactionFunction : findAll()) {
			repository.delete(vmaTransactionFunction);
		}
	}

	/**
	 * Returns the number of vma transaction functions where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the number of matching vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority(String portofAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONFUNCTION_WHERE);

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
	 * Returns the number of vma transaction functions where portofAuthority = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority_transactionTypeCode(
		String portofAuthority, String transactionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONFUNCTION_WHERE);

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

			

			try {QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
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
	 * Returns the number of vma transaction functions.
	 *
	 * @return the number of vma transaction functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONFUNCTION).build();

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
	 * Initializes the vma transaction function persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONFUNCTION = "SELECT vmaTransactionFunction FROM VmaTransactionFunction vmaTransactionFunction";
	private static final String _SQL_SELECT_VMATRANSACTIONFUNCTION_WHERE = "SELECT vmaTransactionFunction FROM VmaTransactionFunction vmaTransactionFunction WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONFUNCTION = "SELECT COUNT(vmaTransactionFunction) FROM VmaTransactionFunction vmaTransactionFunction";
	private static final String _SQL_COUNT_VMATRANSACTIONFUNCTION_WHERE = "SELECT COUNT(vmaTransactionFunction) FROM VmaTransactionFunction vmaTransactionFunction WHERE ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaTransactionFunction.portofAuthority IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaTransactionFunction.portofAuthority =:portofAuthority";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaTransactionFunction.portofAuthority IS NULL OR vmaTransactionFunction.portofAuthority =:portofAuthority)";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_1 =
		"vmaTransactionFunction.portofAuthority IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_2 =
		"vmaTransactionFunction.portofAuthority =:portofAuthority AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_PORTOFAUTHORITY_3 =
		"(vmaTransactionFunction.portofAuthority IS NULL OR vmaTransactionFunction.portofAuthority =:portofAuthority) AND ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaTransactionFunction.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaTransactionFunction.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaTransactionFunction.transactionTypeCode IS NULL OR vmaTransactionFunction.transactionTypeCode =:transactionTypeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionFunction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionFunction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionFunction exists with the key {";
	

	
}
