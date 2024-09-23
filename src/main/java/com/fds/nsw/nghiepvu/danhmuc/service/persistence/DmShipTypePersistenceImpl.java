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
import com.fds.nsw.nghiepvu.model.DmShipType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmShipTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmShipTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmShipTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmShipTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmShipType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmShipTypeUtil} to access the dm ship type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmShipType create(int id) {
		DmShipType dmShipType = new DmShipType();

		
		//dmShipType.setPrimaryKey(id);

		return dmShipType;
	}

	/**
	 * Removes the dm ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm ship type
	 * @return the dm ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType remove(int id)
		throws NoSuchDmShipTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm ship type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm ship type
	 * @return the dm ship type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmShipType remove(Serializable primaryKey)
		throws NoSuchDmShipTypeException, SystemException {
		

		try {
			

			DmShipType dmShipType = findByPrimaryKey(primaryKey);

			if (dmShipType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmShipType);
			return dmShipType;
		}
		catch (NoSuchDmShipTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmShipType remove(DmShipType DmShipType) throws SystemException {
	removeImpl(DmShipType);	return DmShipType;
}

protected DmShipType removeImpl

(DmShipType dmShipType)
		throws SystemException {
		try {
			repository.delete(dmShipType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmShipType;
	}

	
	public DmShipType updateImpl(
		DmShipType dmShipType, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmShipType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmShipType;
	}

	
	public DmShipType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm ship type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmShipTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm ship type
	 * @return the dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType findByPrimaryKey(int id)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = fetchByPrimaryKey(id);

		if (dmShipType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmShipTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmShipType;
	}

	/**
	 * Returns the dm ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm ship type
	 * @return the dm ship type, or <code>null</code> if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmShipType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm ship type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm ship type
	 * @return the dm ship type, or <code>null</code> if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType fetchByPrimaryKey(int id) throws SystemException {
		DmShipType dmShipType = null;

		

		if (dmShipType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmShipType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmShipType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmShipType;
	}

	/**
	 * Returns all the dm ship types where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @return the matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByShipTypeCode(String shipTypeCode)
		throws SystemException {
		return findByShipTypeCode(shipTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ship types where shipTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeCode the ship type code
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @return the range of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByShipTypeCode(String shipTypeCode, int start,
		int end) throws SystemException {
		return findByShipTypeCode(shipTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ship types where shipTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeCode the ship type code
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByShipTypeCode(String shipTypeCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmShipType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmShipTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				list = (List<DmShipType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType findByShipTypeCode_First(String shipTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = fetchByShipTypeCode_First(shipTypeCode,
				orderByComparator);

		if (dmShipType != null) {
			return dmShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipTypeException(msg.toString());
	}

	/**
	 * Returns the first dm ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship type, or <code>null</code> if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType fetchByShipTypeCode_First(String shipTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmShipType> list = findByShipTypeCode(shipTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType findByShipTypeCode_Last(String shipTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = fetchByShipTypeCode_Last(shipTypeCode,
				orderByComparator);

		if (dmShipType != null) {
			return dmShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeCode=");
		msg.append(shipTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipTypeException(msg.toString());
	}

	/**
	 * Returns the last dm ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship type, or <code>null</code> if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType fetchByShipTypeCode_Last(String shipTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByShipTypeCode(shipTypeCode);

		List<DmShipType> list = findByShipTypeCode(shipTypeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ship types before and after the current dm ship type in the ordered set where shipTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm ship type
	 * @param shipTypeCode the ship type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType[] findByShipTypeCode_PrevAndNext(int id,
		String shipTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = findByPrimaryKey(id);

		

		try {
			

			DmShipType[] array = new DmShipType[3];

			array[0] = getByShipTypeCode_PrevAndNext(dmShipType,
					shipTypeCode, orderByComparator, true);

			array[1] = dmShipType;

			array[2] = getByShipTypeCode_PrevAndNext(dmShipType,
					shipTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmShipType getByShipTypeCode_PrevAndNext(
		DmShipType dmShipType, String shipTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSHIPTYPE_WHERE);

		if (shipTypeCode == null) {
			query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
		}
		else {
			if (shipTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
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
			query.append(DmShipTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipTypeCode != null) {
			builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmShipType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmShipType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm ship types where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @return the matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByF_shipTypeNameVN(String shipTypeNameVN)
		throws SystemException {
		return findByF_shipTypeNameVN(shipTypeNameVN, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ship types where shipTypeNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @return the range of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByF_shipTypeNameVN(String shipTypeNameVN,
		int start, int end) throws SystemException {
		return findByF_shipTypeNameVN(shipTypeNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ship types where shipTypeNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findByF_shipTypeNameVN(String shipTypeNameVN,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmShipType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSHIPTYPE_WHERE);

			if (shipTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_1);
			}
			else {
				if (shipTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmShipTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipTypeNameVN != null) {
					builder.appendNamedParameterMap("shipTypeNameVN", shipTypeNameVN);
				}

				list = (List<DmShipType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm ship type in the ordered set where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType findByF_shipTypeNameVN_First(String shipTypeNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = fetchByF_shipTypeNameVN_First(shipTypeNameVN,
				orderByComparator);

		if (dmShipType != null) {
			return dmShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeNameVN=");
		msg.append(shipTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipTypeException(msg.toString());
	}

	/**
	 * Returns the first dm ship type in the ordered set where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship type, or <code>null</code> if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType fetchByF_shipTypeNameVN_First(String shipTypeNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmShipType> list = findByF_shipTypeNameVN(shipTypeNameVN, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm ship type in the ordered set where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType findByF_shipTypeNameVN_Last(String shipTypeNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = fetchByF_shipTypeNameVN_Last(shipTypeNameVN,
				orderByComparator);

		if (dmShipType != null) {
			return dmShipType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipTypeNameVN=");
		msg.append(shipTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipTypeException(msg.toString());
	}

	/**
	 * Returns the last dm ship type in the ordered set where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship type, or <code>null</code> if a matching dm ship type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType fetchByF_shipTypeNameVN_Last(String shipTypeNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_shipTypeNameVN(shipTypeNameVN);

		List<DmShipType> list = findByF_shipTypeNameVN(shipTypeNameVN,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ship types before and after the current dm ship type in the ordered set where shipTypeNameVN LIKE &#63;.
	 *
	 * @param id the primary key of the current dm ship type
	 * @param shipTypeNameVN the ship type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm ship type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipTypeException if a dm ship type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipType[] findByF_shipTypeNameVN_PrevAndNext(int id,
		String shipTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmShipTypeException, SystemException {
		DmShipType dmShipType = findByPrimaryKey(id);

		

		try {
			

			DmShipType[] array = new DmShipType[3];

			array[0] = getByF_shipTypeNameVN_PrevAndNext(dmShipType,
					shipTypeNameVN, orderByComparator, true);

			array[1] = dmShipType;

			array[2] = getByF_shipTypeNameVN_PrevAndNext(dmShipType,
					shipTypeNameVN, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmShipType getByF_shipTypeNameVN_PrevAndNext(
		DmShipType dmShipType, String shipTypeNameVN,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSHIPTYPE_WHERE);

		if (shipTypeNameVN == null) {
			query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_1);
		}
		else {
			if (shipTypeNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_2);
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
			query.append(DmShipTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipTypeNameVN != null) {
			builder.appendNamedParameterMap("shipTypeNameVN", shipTypeNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmShipType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmShipType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm ship types.
	 *
	 * @return the dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @return the range of dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ship types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ship types
	 * @param end the upper bound of the range of dm ship types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmShipType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMSHIPTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMSHIPTYPE.concat(DmShipTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmShipType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm ship types where shipTypeCode = &#63; from the database.
	 *
	 * @param shipTypeCode the ship type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByShipTypeCode(String shipTypeCode)
		throws SystemException {
		for (DmShipType dmShipType : findByShipTypeCode(shipTypeCode)) {
			repository.delete(dmShipType);
		}
	}

	/**
	 * Removes all the dm ship types where shipTypeNameVN LIKE &#63; from the database.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_shipTypeNameVN(String shipTypeNameVN)
		throws SystemException {
		for (DmShipType dmShipType : findByF_shipTypeNameVN(shipTypeNameVN)) {
			repository.delete(dmShipType);
		}
	}

	/**
	 * Removes all the dm ship types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmShipType dmShipType : findAll()) {
			repository.delete(dmShipType);
		}
	}

	/**
	 * Returns the number of dm ship types where shipTypeCode = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @return the number of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipTypeCode(String shipTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSHIPTYPE_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
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
	 * Returns the number of dm ship types where shipTypeNameVN LIKE &#63;.
	 *
	 * @param shipTypeNameVN the ship type name v n
	 * @return the number of matching dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipTypeNameVN(String shipTypeNameVN)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSHIPTYPE_WHERE);

			if (shipTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_1);
			}
			else {
				if (shipTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipTypeNameVN != null) {
					builder.appendNamedParameterMap("shipTypeNameVN", shipTypeNameVN);
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
	 * Returns the number of dm ship types.
	 *
	 * @return the number of dm ship types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMSHIPTYPE).build();

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
	 * Initializes the dm ship type persistence.
	 */
	private static final String _SQL_SELECT_DMSHIPTYPE = "SELECT dmShipType FROM DmShipType dmShipType";
	private static final String _SQL_SELECT_DMSHIPTYPE_WHERE = "SELECT dmShipType FROM DmShipType dmShipType WHERE ";
	private static final String _SQL_COUNT_DMSHIPTYPE = "SELECT COUNT(dmShipType) FROM DmShipType dmShipType";
	private static final String _SQL_COUNT_DMSHIPTYPE_WHERE = "SELECT COUNT(dmShipType) FROM DmShipType dmShipType WHERE ";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_1 = "dmShipType.shipTypeCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_2 = "dmShipType.shipTypeCode =:shipTypeCode";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_SHIPTYPECODE_3 = "(dmShipType.shipTypeCode IS NULL OR dmShipType.shipTypeCode =:shipTypeCode)";
	private static final String _FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_1 =
		"dmShipType.shipTypeNameVN LIKE NULL";
	private static final String _FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_2 =
		"dmShipType.shipTypeNameVN LIKE :shipTypeNameVN";
	private static final String _FINDER_COLUMN_F_SHIPTYPENAMEVN_SHIPTYPENAMEVN_3 =
		"(dmShipType.shipTypeNameVN IS NULL OR dmShipType.shipTypeNameVN LIKE :shipTypeNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmShipType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmShipType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmShipType exists with the key {";
	

	
}
