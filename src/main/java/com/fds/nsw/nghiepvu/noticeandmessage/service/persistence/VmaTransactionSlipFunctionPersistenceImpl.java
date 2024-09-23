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
import com.fds.nsw.nghiepvu.model.VmaTransactionSlipFunction;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionSlipFunctionRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionSlipFunctionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionSlipFunctionPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionSlipFunctionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionSlipFunction> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionSlipFunctionUtil} to access the vma transaction slip function persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionSlipFunction create(long id) {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = new VmaTransactionSlipFunction();

		
		//vmaTransactionSlipFunction.setPrimaryKey(id);

		return vmaTransactionSlipFunction;
	}

	/**
	 * Removes the vma transaction slip function with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction slip function
	 * @return the vma transaction slip function that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction remove(long id)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction slip function with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction slip function
	 * @return the vma transaction slip function that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlipFunction remove(Serializable primaryKey)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		

		try {
			

			VmaTransactionSlipFunction vmaTransactionSlipFunction = findByPrimaryKey(primaryKey);

			if (vmaTransactionSlipFunction == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionSlipFunctionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionSlipFunction);
			return vmaTransactionSlipFunction;
		}
		catch (NoSuchVmaTransactionSlipFunctionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionSlipFunction remove(VmaTransactionSlipFunction VmaTransactionSlipFunction) throws SystemException {
	removeImpl(VmaTransactionSlipFunction);
	return VmaTransactionSlipFunction;
}

protected VmaTransactionSlipFunction removeImpl(
		VmaTransactionSlipFunction vmaTransactionSlipFunction)
		throws SystemException {
		try {
			repository.delete(vmaTransactionSlipFunction);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlipFunction;
	}

	
	public VmaTransactionSlipFunction updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionSlipFunction vmaTransactionSlipFunction,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionSlipFunction);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionSlipFunction;
	}

	
	public VmaTransactionSlipFunction findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip function with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip function
	 * @return the vma transaction slip function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction findByPrimaryKey(long id)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = fetchByPrimaryKey(id);

		if (vmaTransactionSlipFunction == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionSlipFunctionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionSlipFunction;
	}

	/**
	 * Returns the vma transaction slip function with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction slip function
	 * @return the vma transaction slip function, or <code>null</code> if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionSlipFunction fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction slip function with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction slip function
	 * @return the vma transaction slip function, or <code>null</code> if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = null;

		

		if (vmaTransactionSlipFunction == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionSlipFunction> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionSlipFunction = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionSlipFunction;
	}

	/**
	 * Returns the vma transaction slip function where itineraryNo = &#63; and documentaryCode = &#63; and transactionTypeCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction slip function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction findByitineraryNo_documentaryCode_transactionTypeCode(
		String itineraryNo, String documentaryCode, String transactionTypeCode)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = fetchByitineraryNo_documentaryCode_transactionTypeCode(itineraryNo,
				documentaryCode, transactionTypeCode);

		if (vmaTransactionSlipFunction == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", documentaryCode=");
			msg.append(documentaryCode);

			msg.append(", transactionTypeCode=");
			msg.append(transactionTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionSlipFunctionException(msg.toString());
		}

		return vmaTransactionSlipFunction;
	}

	/**
	 * Returns the vma transaction slip function where itineraryNo = &#63; and documentaryCode = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param transactionTypeCode the transaction type code
	 * @return the matching vma transaction slip function, or <code>null</code> if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction fetchByitineraryNo_documentaryCode_transactionTypeCode(
		String itineraryNo, String documentaryCode, String transactionTypeCode)
		throws SystemException {
		return fetchByitineraryNo_documentaryCode_transactionTypeCode(itineraryNo,
			documentaryCode, transactionTypeCode, true);
	}

	/**
	 * Returns the vma transaction slip function where itineraryNo = &#63; and documentaryCode = &#63; and transactionTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param transactionTypeCode the transaction type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction slip function, or <code>null</code> if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction fetchByitineraryNo_documentaryCode_transactionTypeCode(
		String itineraryNo, String documentaryCode, String transactionTypeCode,
		boolean retrieveFromCache) throws SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = null;
		if (vmaTransactionSlipFunction == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMATRANSACTIONSLIPFUNCTION_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_2);
				}
			}

			if (documentaryCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_1);
			}
			else {
				if (documentaryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionSlipFunction.class).build();

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
				}

				if (transactionTypeCode != null) {
					builder.appendNamedParameterMap("transactionTypeCode", transactionTypeCode);
				}

				vmaTransactionSlipFunction = (VmaTransactionSlipFunction) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionSlipFunction;
	}

	/**
	 * Returns all the vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the matching vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode) throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma transaction slip functions
	 * @param end the upper bound of the range of vma transaction slip functions (not inclusive)
	 * @return the range of matching vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end)
		throws SystemException {
		return findByitineraryNo_documentaryCode(itineraryNo, documentaryCode,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param start the lower bound of the range of vma transaction slip functions
	 * @param end the upper bound of the range of vma transaction slip functions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findByitineraryNo_documentaryCode(
		String itineraryNo, String documentaryCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlipFunction> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONSLIPFUNCTION_WHERE);

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

				if (documentaryCode != null) {
					builder.appendNamedParameterMap("documentaryCode", documentaryCode);
				}

				list = (List<VmaTransactionSlipFunction>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction slip function in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction findByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = fetchByitineraryNo_documentaryCode_First(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaTransactionSlipFunction != null) {
			return vmaTransactionSlipFunction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipFunctionException(msg.toString());
	}

	/**
	 * Returns the first vma transaction slip function in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction slip function, or <code>null</code> if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction fetchByitineraryNo_documentaryCode_First(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlipFunction> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction slip function in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction findByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = fetchByitineraryNo_documentaryCode_Last(itineraryNo,
				documentaryCode, orderByComparator);

		if (vmaTransactionSlipFunction != null) {
			return vmaTransactionSlipFunction;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", documentaryCode=");
		msg.append(documentaryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionSlipFunctionException(msg.toString());
	}

	/**
	 * Returns the last vma transaction slip function in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction slip function, or <code>null</code> if a matching vma transaction slip function could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction fetchByitineraryNo_documentaryCode_Last(
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode);

		List<VmaTransactionSlipFunction> list = findByitineraryNo_documentaryCode(itineraryNo,
				documentaryCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction slip functions before and after the current vma transaction slip function in the ordered set where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param id the primary key of the current vma transaction slip function
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction slip function
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionSlipFunctionException if a vma transaction slip function with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction[] findByitineraryNo_documentaryCode_PrevAndNext(
		long id, String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionSlipFunction[] array = new VmaTransactionSlipFunction[3];

			array[0] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaTransactionSlipFunction, itineraryNo, documentaryCode,
					orderByComparator, true);

			array[1] = vmaTransactionSlipFunction;

			array[2] = getByitineraryNo_documentaryCode_PrevAndNext(
					vmaTransactionSlipFunction, itineraryNo, documentaryCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionSlipFunction getByitineraryNo_documentaryCode_PrevAndNext(
		 VmaTransactionSlipFunction vmaTransactionSlipFunction,
		String itineraryNo, String documentaryCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONSLIPFUNCTION_WHERE);

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

		if (documentaryCode != null) {
			builder.appendNamedParameterMap("documentaryCode", documentaryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionSlipFunction);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionSlipFunction> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma transaction slip functions.
	 *
	 * @return the vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction slip functions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slip functions
	 * @param end the upper bound of the range of vma transaction slip functions (not inclusive)
	 * @return the range of vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction slip functions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction slip functions
	 * @param end the upper bound of the range of vma transaction slip functions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionSlipFunction> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionSlipFunction> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONSLIPFUNCTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONSLIPFUNCTION;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionSlipFunction>) queryFactory.getResultList(builder);
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
	 * Removes the vma transaction slip function where itineraryNo = &#63; and documentaryCode = &#63; and transactionTypeCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param transactionTypeCode the transaction type code
	 * @return the vma transaction slip function that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionSlipFunction removeByitineraryNo_documentaryCode_transactionTypeCode(
		String itineraryNo, String documentaryCode, String transactionTypeCode)
		throws NoSuchVmaTransactionSlipFunctionException, SystemException {
		VmaTransactionSlipFunction vmaTransactionSlipFunction = findByitineraryNo_documentaryCode_transactionTypeCode(itineraryNo,
				documentaryCode, transactionTypeCode);

		repository.delete(vmaTransactionSlipFunction);
			return vmaTransactionSlipFunction;
	}

	/**
	 * Removes all the vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		for (VmaTransactionSlipFunction vmaTransactionSlipFunction : findByitineraryNo_documentaryCode(
				itineraryNo, documentaryCode)) {
			repository.delete(vmaTransactionSlipFunction);
		}
	}

	/**
	 * Removes all the vma transaction slip functions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionSlipFunction vmaTransactionSlipFunction : findAll()) {
			repository.delete(vmaTransactionSlipFunction);
		}
	}

	/**
	 * Returns the number of vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63; and transactionTypeCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @param transactionTypeCode the transaction type code
	 * @return the number of matching vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentaryCode_transactionTypeCode(
		String itineraryNo, String documentaryCode, String transactionTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIPFUNCTION_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_2);
				}
			}

			if (documentaryCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_1);
			}
			else {
				if (documentaryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_2);
				}
			}

			if (transactionTypeCode == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1);
			}
			else {
				if (transactionTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2);
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
	 * Returns the number of vma transaction slip functions where itineraryNo = &#63; and documentaryCode = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param documentaryCode the documentary code
	 * @return the number of matching vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_documentaryCode(String itineraryNo,
		String documentaryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONSLIPFUNCTION_WHERE);

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
	 * Returns the number of vma transaction slip functions.
	 *
	 * @return the number of vma transaction slip functions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONSLIPFUNCTION).build();

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
	 * Initializes the vma transaction slip function persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONSLIPFUNCTION = "SELECT vmaTransactionSlipFunction FROM VmaTransactionSlipFunction vmaTransactionSlipFunction";
	private static final String _SQL_SELECT_VMATRANSACTIONSLIPFUNCTION_WHERE = "SELECT vmaTransactionSlipFunction FROM VmaTransactionSlipFunction vmaTransactionSlipFunction WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIPFUNCTION = "SELECT COUNT(vmaTransactionSlipFunction) FROM VmaTransactionSlipFunction vmaTransactionSlipFunction";
	private static final String _SQL_COUNT_VMATRANSACTIONSLIPFUNCTION_WHERE = "SELECT COUNT(vmaTransactionSlipFunction) FROM VmaTransactionSlipFunction vmaTransactionSlipFunction WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_1 =
		"vmaTransactionSlipFunction.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_2 =
		"vmaTransactionSlipFunction.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_ITINERARYNO_3 =
		"(vmaTransactionSlipFunction.itineraryNo IS NULL OR vmaTransactionSlipFunction.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_1 =
		"vmaTransactionSlipFunction.documentaryCode IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_2 =
		"vmaTransactionSlipFunction.documentaryCode =:documentaryCode AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_DOCUMENTARYCODE_3 =
		"(vmaTransactionSlipFunction.documentaryCode IS NULL OR vmaTransactionSlipFunction.documentaryCode =:documentaryCode) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_1 =
		"vmaTransactionSlipFunction.transactionTypeCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_2 =
		"vmaTransactionSlipFunction.transactionTypeCode =:transactionTypeCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_TRANSACTIONTYPECODE_TRANSACTIONTYPECODE_3 =
		"(vmaTransactionSlipFunction.transactionTypeCode IS NULL OR vmaTransactionSlipFunction.transactionTypeCode =:transactionTypeCode)";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_1 =
		"vmaTransactionSlipFunction.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_2 =
		"vmaTransactionSlipFunction.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_ITINERARYNO_3 =
		"(vmaTransactionSlipFunction.itineraryNo IS NULL OR vmaTransactionSlipFunction.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_1 =
		"vmaTransactionSlipFunction.documentaryCode IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_2 =
		"vmaTransactionSlipFunction.documentaryCode =:documentaryCode";
	private static final String _FINDER_COLUMN_ITINERARYNO_DOCUMENTARYCODE_DOCUMENTARYCODE_3 =
		"(vmaTransactionSlipFunction.documentaryCode IS NULL OR vmaTransactionSlipFunction.documentaryCode =:documentaryCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionSlipFunction.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionSlipFunction exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionSlipFunction exists with the key {";
	

	
}
