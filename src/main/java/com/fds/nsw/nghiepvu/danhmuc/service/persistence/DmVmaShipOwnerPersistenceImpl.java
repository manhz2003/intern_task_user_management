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

package com.fds.nsw.nghiepvu.danhmuc.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmVmaShipOwner;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaShipOwnerRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaShipOwnerModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaShipOwnerPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaShipOwnerRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaShipOwner> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaShipOwnerUtil} to access the dm vma ship owner persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaShipOwner create(long id) {
		DmVmaShipOwner dmVmaShipOwner = new DmVmaShipOwner();

		
		//dmVmaShipOwner.setPrimaryKey(id);

		return dmVmaShipOwner;
	}

	/**
	 * Removes the dm vma ship owner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma ship owner
	 * @return the dm vma ship owner that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner remove(long Id)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma ship owner with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma ship owner
	 * @return the dm vma ship owner that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipOwner remove(Serializable primaryKey)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		

		try {
			

			DmVmaShipOwner dmVmaShipOwner = findByPrimaryKey(primaryKey);

			if (dmVmaShipOwner == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaShipOwnerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaShipOwner);
			return dmVmaShipOwner;
		}
		catch (NoSuchDmVmaShipOwnerException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaShipOwner remove(DmVmaShipOwner DmVmaShipOwner) throws SystemException {
	removeImpl(DmVmaShipOwner);	return DmVmaShipOwner;
}

protected DmVmaShipOwner removeImpl

(DmVmaShipOwner dmVmaShipOwner)
		throws SystemException {
		try {
			repository.delete(dmVmaShipOwner);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipOwner;
	}

	
	public DmVmaShipOwner updateImpl(
		DmVmaShipOwner dmVmaShipOwner, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmVmaShipOwner);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaShipOwner;
	}

	
	public DmVmaShipOwner findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma ship owner with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma ship owner
	 * @return the dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner findByPrimaryKey(long Id)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = fetchByPrimaryKey(Id);

		if (dmVmaShipOwner == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaShipOwnerException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaShipOwner;
	}

	/**
	 * Returns the dm vma ship owner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma ship owner
	 * @return the dm vma ship owner, or <code>null</code> if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaShipOwner fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma ship owner with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma ship owner
	 * @return the dm vma ship owner, or <code>null</code> if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByPrimaryKey(long Id) throws SystemException {
		DmVmaShipOwner dmVmaShipOwner = null;

		

		if (dmVmaShipOwner == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaShipOwner> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaShipOwner = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaShipOwner;
	}

	/**
	 * Returns all the dm vma ship owners where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma ship owners where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma ship owners
	 * @param end the upper bound of the range of dm vma ship owners (not inclusive)
	 * @return the range of matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma ship owners where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma ship owners
	 * @param end the upper bound of the range of dm vma ship owners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaShipOwner> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMASHIPOWNER_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmVmaShipOwnerModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaShipOwner>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaShipOwner != null) {
			return dmVmaShipOwner;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaShipOwnerException(msg.toString());
	}

	/**
	 * Returns the first dm vma ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaShipOwner> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaShipOwner != null) {
			return dmVmaShipOwner;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaShipOwnerException(msg.toString());
	}

	/**
	 * Returns the last dm vma ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaShipOwner> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma ship owners before and after the current dm vma ship owner in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma ship owner
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a dm vma ship owner with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = findByPrimaryKey(Id);

		

		try {
			

			DmVmaShipOwner[] array = new DmVmaShipOwner[3];

			array[0] = getByF_maritimeCode_PrevAndNext(dmVmaShipOwner,
					MaritimeCode, orderByComparator, true);

			array[1] = dmVmaShipOwner;

			array[2] = getByF_maritimeCode_PrevAndNext(dmVmaShipOwner,
					MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaShipOwner getByF_maritimeCode_PrevAndNext(
		DmVmaShipOwner dmVmaShipOwner, String MaritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMASHIPOWNER_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
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
			query.append(DmVmaShipOwnerModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaShipOwner);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaShipOwner> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma ship owner where ShipOwnerCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException} if it could not be found.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @return the matching dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner findByF_shipOwnerCode(String ShipOwnerCode)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = fetchByF_shipOwnerCode(ShipOwnerCode);

		if (dmVmaShipOwner == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipOwnerCode=");
			msg.append(ShipOwnerCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaShipOwnerException(msg.toString());
		}

		return dmVmaShipOwner;
	}

	/**
	 * Returns the dm vma ship owner where ShipOwnerCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @return the matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_shipOwnerCode(String ShipOwnerCode)
		throws SystemException {
		return fetchByF_shipOwnerCode(ShipOwnerCode, true);
	}

	/**
	 * Returns the dm vma ship owner where ShipOwnerCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_shipOwnerCode(String ShipOwnerCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaShipOwner dmVmaShipOwner = null;
		if (dmVmaShipOwner == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASHIPOWNER_WHERE);

			if (ShipOwnerCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_1);
			}
			else {
				if (ShipOwnerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_2);
				}
			}

			query.append(DmVmaShipOwnerModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaShipOwner.class).build();

				

				if (ShipOwnerCode != null) {
					builder.appendNamedParameterMap("ShipOwnerCode", ShipOwnerCode);
				}

				dmVmaShipOwner = (DmVmaShipOwner) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaShipOwner;
	}

	/**
	 * Returns the dm vma ship owner where TaxCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException} if it could not be found.
	 *
	 * @param TaxCode the tax code
	 * @return the matching dm vma ship owner
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaShipOwnerException if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner findByF_taxCode(String TaxCode)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = fetchByF_taxCode(TaxCode);

		if (dmVmaShipOwner == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TaxCode=");
			msg.append(TaxCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaShipOwnerException(msg.toString());
		}

		return dmVmaShipOwner;
	}

	/**
	 * Returns the dm vma ship owner where TaxCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TaxCode the tax code
	 * @return the matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_taxCode(String TaxCode)
		throws SystemException {
		return fetchByF_taxCode(TaxCode, true);
	}

	/**
	 * Returns the dm vma ship owner where TaxCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TaxCode the tax code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma ship owner, or <code>null</code> if a matching dm vma ship owner could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner fetchByF_taxCode(String TaxCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaShipOwner dmVmaShipOwner = null;
		if (dmVmaShipOwner == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMASHIPOWNER_WHERE);

			if (TaxCode == null) {
				query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_1);
			}
			else {
				if (TaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_2);
				}
			}

			query.append(DmVmaShipOwnerModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaShipOwner.class).build();

				

				if (TaxCode != null) {
					builder.appendNamedParameterMap("TaxCode", TaxCode);
				}

				dmVmaShipOwner = (DmVmaShipOwner) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaShipOwner;
	}

	/**
	 * Returns all the dm vma ship owners.
	 *
	 * @return the dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma ship owners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma ship owners
	 * @param end the upper bound of the range of dm vma ship owners (not inclusive)
	 * @return the range of dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma ship owners.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma ship owners
	 * @param end the upper bound of the range of dm vma ship owners (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaShipOwner> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaShipOwner> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMASHIPOWNER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMASHIPOWNER.concat(DmVmaShipOwnerModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaShipOwner>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma ship owners where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaShipOwner dmVmaShipOwner : findByF_maritimeCode(MaritimeCode)) {
			repository.delete(dmVmaShipOwner);
		}
	}

	/**
	 * Removes the dm vma ship owner where ShipOwnerCode = &#63; from the database.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @return the dm vma ship owner that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner removeByF_shipOwnerCode(String ShipOwnerCode)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = findByF_shipOwnerCode(ShipOwnerCode);

		repository.delete(dmVmaShipOwner);
			return dmVmaShipOwner;
	}

	/**
	 * Removes the dm vma ship owner where TaxCode = &#63; from the database.
	 *
	 * @param TaxCode the tax code
	 * @return the dm vma ship owner that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaShipOwner removeByF_taxCode(String TaxCode)
		throws NoSuchDmVmaShipOwnerException, SystemException {
		DmVmaShipOwner dmVmaShipOwner = findByF_taxCode(TaxCode);

		repository.delete(dmVmaShipOwner);
			return dmVmaShipOwner;
	}

	/**
	 * Removes all the dm vma ship owners from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaShipOwner dmVmaShipOwner : findAll()) {
			repository.delete(dmVmaShipOwner);
		}
	}

	/**
	 * Returns the number of dm vma ship owners where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPOWNER_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
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
	 * Returns the number of dm vma ship owners where ShipOwnerCode = &#63;.
	 *
	 * @param ShipOwnerCode the ship owner code
	 * @return the number of matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipOwnerCode(String ShipOwnerCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPOWNER_WHERE);

			if (ShipOwnerCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_1);
			}
			else {
				if (ShipOwnerCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipOwnerCode != null) {
					builder.appendNamedParameterMap("ShipOwnerCode", ShipOwnerCode);
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
	 * Returns the number of dm vma ship owners where TaxCode = &#63;.
	 *
	 * @param TaxCode the tax code
	 * @return the number of matching dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_taxCode(String TaxCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMASHIPOWNER_WHERE);

			if (TaxCode == null) {
				query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_1);
			}
			else {
				if (TaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TAXCODE_TAXCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (TaxCode != null) {
					builder.appendNamedParameterMap("TaxCode", TaxCode);
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
	 * Returns the number of dm vma ship owners.
	 *
	 * @return the number of dm vma ship owners
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMASHIPOWNER).build();

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
	 * Initializes the dm vma ship owner persistence.
	 */
	private static final String _SQL_SELECT_DMVMASHIPOWNER = "SELECT dmVmaShipOwner FROM DmVmaShipOwner dmVmaShipOwner";
	private static final String _SQL_SELECT_DMVMASHIPOWNER_WHERE = "SELECT dmVmaShipOwner FROM DmVmaShipOwner dmVmaShipOwner WHERE ";
	private static final String _SQL_COUNT_DMVMASHIPOWNER = "SELECT COUNT(dmVmaShipOwner) FROM DmVmaShipOwner dmVmaShipOwner";
	private static final String _SQL_COUNT_DMVMASHIPOWNER_WHERE = "SELECT COUNT(dmVmaShipOwner) FROM DmVmaShipOwner dmVmaShipOwner WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaShipOwner.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaShipOwner.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaShipOwner.MaritimeCode IS NULL OR dmVmaShipOwner.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_1 = "dmVmaShipOwner.ShipOwnerCode IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_2 = "dmVmaShipOwner.ShipOwnerCode =:ShipOwnerCode";
	private static final String _FINDER_COLUMN_F_SHIPOWNERCODE_SHIPOWNERCODE_3 = "(dmVmaShipOwner.ShipOwnerCode IS NULL OR dmVmaShipOwner.ShipOwnerCode =:ShipOwnerCode)";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_1 = "dmVmaShipOwner.TaxCode IS NULL";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_2 = "dmVmaShipOwner.TaxCode =:TaxCode";
	private static final String _FINDER_COLUMN_F_TAXCODE_TAXCODE_3 = "(dmVmaShipOwner.TaxCode IS NULL OR dmVmaShipOwner.TaxCode =:TaxCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaShipOwner.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaShipOwner exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaShipOwner exists with the key {";
	

	
}
