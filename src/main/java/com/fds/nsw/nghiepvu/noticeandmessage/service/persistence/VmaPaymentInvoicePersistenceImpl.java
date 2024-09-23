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
import com.fds.nsw.nghiepvu.model.VmaPaymentInvoice;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaPaymentInvoiceRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaPaymentInvoiceModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaPaymentInvoicePersistenceImpl extends BasePersistence {
	@Autowired
	VmaPaymentInvoiceRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaPaymentInvoice> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaPaymentInvoiceUtil} to access the vma payment invoice persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaPaymentInvoice create(long id) {
		VmaPaymentInvoice vmaPaymentInvoice = new VmaPaymentInvoice();

		
		//vmaPaymentInvoice.setPrimaryKey(id);

		return vmaPaymentInvoice;
	}

	/**
	 * Removes the vma payment invoice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma payment invoice
	 * @return the vma payment invoice that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice remove(long id)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma payment invoice with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma payment invoice
	 * @return the vma payment invoice that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPaymentInvoice remove(Serializable primaryKey)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		

		try {
			

			VmaPaymentInvoice vmaPaymentInvoice = findByPrimaryKey(primaryKey);

			if (vmaPaymentInvoice == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaPaymentInvoiceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaPaymentInvoice);
			return vmaPaymentInvoice;
		}
		catch (NoSuchVmaPaymentInvoiceException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaPaymentInvoice remove(VmaPaymentInvoice VmaPaymentInvoice) throws SystemException {
	removeImpl(VmaPaymentInvoice);
	return VmaPaymentInvoice;
}

protected VmaPaymentInvoice removeImpl(VmaPaymentInvoice vmaPaymentInvoice)
		throws SystemException {
		try {
			repository.delete(vmaPaymentInvoice);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPaymentInvoice;
	}

	
	public VmaPaymentInvoice updateImpl(
		com.fds.nsw.nghiepvu.model.VmaPaymentInvoice vmaPaymentInvoice,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaPaymentInvoice);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPaymentInvoice;
	}

	
	public VmaPaymentInvoice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma payment invoice with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException} if it could not be found.
	 *
	 * @param id the primary key of the vma payment invoice
	 * @return the vma payment invoice
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice findByPrimaryKey(long id)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		VmaPaymentInvoice vmaPaymentInvoice = fetchByPrimaryKey(id);

		if (vmaPaymentInvoice == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaPaymentInvoiceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaPaymentInvoice;
	}

	/**
	 * Returns the vma payment invoice with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma payment invoice
	 * @return the vma payment invoice, or <code>null</code> if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPaymentInvoice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma payment invoice with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma payment invoice
	 * @return the vma payment invoice, or <code>null</code> if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice fetchByPrimaryKey(long id)
		throws SystemException {
		VmaPaymentInvoice vmaPaymentInvoice = null;

		

		if (vmaPaymentInvoice == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaPaymentInvoice> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaPaymentInvoice = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaPaymentInvoice;
	}

	/**
	 * Returns all the vma payment invoices where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment invoices where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma payment invoices
	 * @param end the upper bound of the range of vma payment invoices (not inclusive)
	 * @return the range of matching vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment invoices where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma payment invoices
	 * @param end the upper bound of the range of vma payment invoices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPaymentInvoice> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAPAYMENTINVOICE_WHERE);

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

				list = (List<VmaPaymentInvoice>)queryFactory.getResultList(builder);
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
	 * Returns the first vma payment invoice in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment invoice
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a matching vma payment invoice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		VmaPaymentInvoice vmaPaymentInvoice = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaPaymentInvoice != null) {
			return vmaPaymentInvoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentInvoiceException(msg.toString());
	}

	/**
	 * Returns the first vma payment invoice in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma payment invoice, or <code>null</code> if a matching vma payment invoice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentInvoice> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma payment invoice in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment invoice
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a matching vma payment invoice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		VmaPaymentInvoice vmaPaymentInvoice = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaPaymentInvoice != null) {
			return vmaPaymentInvoice;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPaymentInvoiceException(msg.toString());
	}

	/**
	 * Returns the last vma payment invoice in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma payment invoice, or <code>null</code> if a matching vma payment invoice could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaPaymentInvoice> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma payment invoices before and after the current vma payment invoice in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma payment invoice
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma payment invoice
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaPaymentInvoiceException if a vma payment invoice with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPaymentInvoice[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaPaymentInvoiceException, SystemException {
		VmaPaymentInvoice vmaPaymentInvoice = findByPrimaryKey(id);

		

		try {
			

			VmaPaymentInvoice[] array = new VmaPaymentInvoice[3];

			array[0] = getByitineraryNo_PrevAndNext(vmaPaymentInvoice,
					itineraryNo, orderByComparator, true);

			array[1] = vmaPaymentInvoice;

			array[2] = getByitineraryNo_PrevAndNext(vmaPaymentInvoice,
					itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPaymentInvoice getByitineraryNo_PrevAndNext(
		VmaPaymentInvoice vmaPaymentInvoice, String itineraryNo,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPAYMENTINVOICE_WHERE);

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
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPaymentInvoice);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPaymentInvoice> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma payment invoices.
	 *
	 * @return the vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma payment invoices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma payment invoices
	 * @param end the upper bound of the range of vma payment invoices (not inclusive)
	 * @return the range of vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma payment invoices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma payment invoices
	 * @param end the upper bound of the range of vma payment invoices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPaymentInvoice> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPaymentInvoice> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAPAYMENTINVOICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAPAYMENTINVOICE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaPaymentInvoice>) queryFactory.getResultList(builder);
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
	 * Removes all the vma payment invoices where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaPaymentInvoice vmaPaymentInvoice : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaPaymentInvoice);
		}
	}

	/**
	 * Removes all the vma payment invoices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaPaymentInvoice vmaPaymentInvoice : findAll()) {
			repository.delete(vmaPaymentInvoice);
		}
	}

	/**
	 * Returns the number of vma payment invoices where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPAYMENTINVOICE_WHERE);

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
	 * Returns the number of vma payment invoices.
	 *
	 * @return the number of vma payment invoices
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAPAYMENTINVOICE).build();

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
	 * Initializes the vma payment invoice persistence.
	 */
	private static final String _SQL_SELECT_VMAPAYMENTINVOICE = "SELECT vmaPaymentInvoice FROM VmaPaymentInvoice vmaPaymentInvoice";
	private static final String _SQL_SELECT_VMAPAYMENTINVOICE_WHERE = "SELECT vmaPaymentInvoice FROM VmaPaymentInvoice vmaPaymentInvoice WHERE ";
	private static final String _SQL_COUNT_VMAPAYMENTINVOICE = "SELECT COUNT(vmaPaymentInvoice) FROM VmaPaymentInvoice vmaPaymentInvoice";
	private static final String _SQL_COUNT_VMAPAYMENTINVOICE_WHERE = "SELECT COUNT(vmaPaymentInvoice) FROM VmaPaymentInvoice vmaPaymentInvoice WHERE ";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaPaymentInvoice.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaPaymentInvoice.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaPaymentInvoice.itineraryNo IS NULL OR vmaPaymentInvoice.itineraryNo =:itineraryNo)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaPaymentInvoice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaPaymentInvoice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaPaymentInvoice exists with the key {";
	

	
}
