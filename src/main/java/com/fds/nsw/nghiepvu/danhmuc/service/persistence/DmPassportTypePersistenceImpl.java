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
import com.fds.nsw.nghiepvu.model.DmPassportType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPassportTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPassportTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPassportTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmPassportTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPassportType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPassportTypeUtil} to access the dm passport type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPassportType create(int id) {
		DmPassportType dmPassportType = new DmPassportType();

		
		//dmPassportType.setPrimaryKey(id);

		return dmPassportType;
	}

	/**
	 * Removes the dm passport type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm passport type
	 * @return the dm passport type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType remove(int id)
		throws NoSuchDmPassportTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm passport type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm passport type
	 * @return the dm passport type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPassportType remove(Serializable primaryKey)
		throws NoSuchDmPassportTypeException, SystemException {
		

		try {
			

			DmPassportType dmPassportType = findByPrimaryKey(primaryKey);

			if (dmPassportType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPassportTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPassportType);
			return dmPassportType;
		}
		catch (NoSuchDmPassportTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPassportType remove(DmPassportType DmPassportType) throws SystemException {
	removeImpl(DmPassportType);	return DmPassportType;
}

protected DmPassportType removeImpl

(DmPassportType dmPassportType)
		throws SystemException {
		try {
			repository.delete(dmPassportType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPassportType;
	}

	
	public DmPassportType updateImpl(
		DmPassportType dmPassportType, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmPassportType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPassportType;
	}

	
	public DmPassportType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm passport type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPassportTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm passport type
	 * @return the dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType findByPrimaryKey(int id)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = fetchByPrimaryKey(id);

		if (dmPassportType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPassportTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPassportType;
	}

	/**
	 * Returns the dm passport type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm passport type
	 * @return the dm passport type, or <code>null</code> if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPassportType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm passport type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm passport type
	 * @return the dm passport type, or <code>null</code> if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType fetchByPrimaryKey(int id) throws SystemException {
		DmPassportType dmPassportType = null;

		

		if (dmPassportType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPassportType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPassportType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPassportType;
	}

	/**
	 * Returns all the dm passport types where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @return the matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByPassportTypeCode(String passportTypeCode)
		throws SystemException {
		return findByPassportTypeCode(passportTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm passport types where passportTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passportTypeCode the passport type code
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @return the range of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByPassportTypeCode(
		String passportTypeCode, int start, int end) throws SystemException {
		return findByPassportTypeCode(passportTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm passport types where passportTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passportTypeCode the passport type code
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByPassportTypeCode(
		String passportTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPassportType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPASSPORTTYPE_WHERE);

			if (passportTypeCode == null) {
				query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_1);
			}
			else {
				if (passportTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPassportTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (passportTypeCode != null) {
					builder.appendNamedParameterMap("passportTypeCode", passportTypeCode);
				}

				list = (List<DmPassportType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm passport type in the ordered set where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType findByPassportTypeCode_First(
		String passportTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = fetchByPassportTypeCode_First(passportTypeCode,
				orderByComparator);

		if (dmPassportType != null) {
			return dmPassportType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passportTypeCode=");
		msg.append(passportTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPassportTypeException(msg.toString());
	}

	/**
	 * Returns the first dm passport type in the ordered set where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm passport type, or <code>null</code> if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType fetchByPassportTypeCode_First(
		String passportTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPassportType> list = findByPassportTypeCode(passportTypeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm passport type in the ordered set where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType findByPassportTypeCode_Last(String passportTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = fetchByPassportTypeCode_Last(passportTypeCode,
				orderByComparator);

		if (dmPassportType != null) {
			return dmPassportType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passportTypeCode=");
		msg.append(passportTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPassportTypeException(msg.toString());
	}

	/**
	 * Returns the last dm passport type in the ordered set where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm passport type, or <code>null</code> if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType fetchByPassportTypeCode_Last(
		String passportTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPassportTypeCode(passportTypeCode);

		List<DmPassportType> list = findByPassportTypeCode(passportTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm passport types before and after the current dm passport type in the ordered set where passportTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm passport type
	 * @param passportTypeCode the passport type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType[] findByPassportTypeCode_PrevAndNext(int id,
		String passportTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = findByPrimaryKey(id);

		

		try {
			

			DmPassportType[] array = new DmPassportType[3];

			array[0] = getByPassportTypeCode_PrevAndNext(
					dmPassportType, passportTypeCode, orderByComparator, true);

			array[1] = dmPassportType;

			array[2] = getByPassportTypeCode_PrevAndNext(
					dmPassportType, passportTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPassportType getByPassportTypeCode_PrevAndNext(
		 DmPassportType dmPassportType,
		String passportTypeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPASSPORTTYPE_WHERE);

		if (passportTypeCode == null) {
			query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_1);
		}
		else {
			if (passportTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_2);
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
			query.append(DmPassportTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (passportTypeCode != null) {
			builder.appendNamedParameterMap("passportTypeCode", passportTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPassportType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPassportType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm passport types where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @return the matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByF_passportTypeNameVN(
		String passportTypeNameVN) throws SystemException {
		return findByF_passportTypeNameVN(passportTypeNameVN,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm passport types where passportTypeNameVN = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @return the range of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByF_passportTypeNameVN(
		String passportTypeNameVN, int start, int end)
		throws SystemException {
		return findByF_passportTypeNameVN(passportTypeNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm passport types where passportTypeNameVN = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findByF_passportTypeNameVN(
		String passportTypeNameVN, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPassportType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPASSPORTTYPE_WHERE);

			if (passportTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_1);
			}
			else {
				if (passportTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPassportTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (passportTypeNameVN != null) {
					builder.appendNamedParameterMap("passportTypeNameVN", passportTypeNameVN);
				}

				list = (List<DmPassportType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm passport type in the ordered set where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType findByF_passportTypeNameVN_First(
		String passportTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = fetchByF_passportTypeNameVN_First(passportTypeNameVN,
				orderByComparator);

		if (dmPassportType != null) {
			return dmPassportType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passportTypeNameVN=");
		msg.append(passportTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPassportTypeException(msg.toString());
	}

	/**
	 * Returns the first dm passport type in the ordered set where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm passport type, or <code>null</code> if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType fetchByF_passportTypeNameVN_First(
		String passportTypeNameVN, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPassportType> list = findByF_passportTypeNameVN(passportTypeNameVN,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm passport type in the ordered set where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType findByF_passportTypeNameVN_Last(
		String passportTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = fetchByF_passportTypeNameVN_Last(passportTypeNameVN,
				orderByComparator);

		if (dmPassportType != null) {
			return dmPassportType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("passportTypeNameVN=");
		msg.append(passportTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPassportTypeException(msg.toString());
	}

	/**
	 * Returns the last dm passport type in the ordered set where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm passport type, or <code>null</code> if a matching dm passport type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType fetchByF_passportTypeNameVN_Last(
		String passportTypeNameVN, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_passportTypeNameVN(passportTypeNameVN);

		List<DmPassportType> list = findByF_passportTypeNameVN(passportTypeNameVN,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm passport types before and after the current dm passport type in the ordered set where passportTypeNameVN = &#63;.
	 *
	 * @param id the primary key of the current dm passport type
	 * @param passportTypeNameVN the passport type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm passport type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPassportTypeException if a dm passport type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPassportType[] findByF_passportTypeNameVN_PrevAndNext(int id,
		String passportTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmPassportTypeException, SystemException {
		DmPassportType dmPassportType = findByPrimaryKey(id);

		

		try {
			

			DmPassportType[] array = new DmPassportType[3];

			array[0] = getByF_passportTypeNameVN_PrevAndNext(
					dmPassportType, passportTypeNameVN, orderByComparator, true);

			array[1] = dmPassportType;

			array[2] = getByF_passportTypeNameVN_PrevAndNext(
					dmPassportType, passportTypeNameVN, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPassportType getByF_passportTypeNameVN_PrevAndNext(
		 DmPassportType dmPassportType,
		String passportTypeNameVN, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPASSPORTTYPE_WHERE);

		if (passportTypeNameVN == null) {
			query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_1);
		}
		else {
			if (passportTypeNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_2);
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
			query.append(DmPassportTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (passportTypeNameVN != null) {
			builder.appendNamedParameterMap("passportTypeNameVN", passportTypeNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPassportType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPassportType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm passport types.
	 *
	 * @return the dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm passport types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @return the range of dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm passport types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm passport types
	 * @param end the upper bound of the range of dm passport types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPassportType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPassportType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPASSPORTTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPASSPORTTYPE.concat(DmPassportTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPassportType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm passport types where passportTypeCode = &#63; from the database.
	 *
	 * @param passportTypeCode the passport type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPassportTypeCode(String passportTypeCode)
		throws SystemException {
		for (DmPassportType dmPassportType : findByPassportTypeCode(
				passportTypeCode)) {
			repository.delete(dmPassportType);
		}
	}

	/**
	 * Removes all the dm passport types where passportTypeNameVN = &#63; from the database.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_passportTypeNameVN(String passportTypeNameVN)
		throws SystemException {
		for (DmPassportType dmPassportType : findByF_passportTypeNameVN(
				passportTypeNameVN)) {
			repository.delete(dmPassportType);
		}
	}

	/**
	 * Removes all the dm passport types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPassportType dmPassportType : findAll()) {
			repository.delete(dmPassportType);
		}
	}

	/**
	 * Returns the number of dm passport types where passportTypeCode = &#63;.
	 *
	 * @param passportTypeCode the passport type code
	 * @return the number of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPassportTypeCode(String passportTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPASSPORTTYPE_WHERE);

			if (passportTypeCode == null) {
				query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_1);
			}
			else {
				if (passportTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (passportTypeCode != null) {
					builder.appendNamedParameterMap("passportTypeCode", passportTypeCode);
				}

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
	 * Returns the number of dm passport types where passportTypeNameVN = &#63;.
	 *
	 * @param passportTypeNameVN the passport type name v n
	 * @return the number of matching dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_passportTypeNameVN(String passportTypeNameVN)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPASSPORTTYPE_WHERE);

			if (passportTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_1);
			}
			else {
				if (passportTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (passportTypeNameVN != null) {
					builder.appendNamedParameterMap("passportTypeNameVN", passportTypeNameVN);
				}

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
	 * Returns the number of dm passport types.
	 *
	 * @return the number of dm passport types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPASSPORTTYPE).build();

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
	 * Initializes the dm passport type persistence.
	 */
	private static final String _SQL_SELECT_DMPASSPORTTYPE = "SELECT dmPassportType FROM DmPassportType dmPassportType";
	private static final String _SQL_SELECT_DMPASSPORTTYPE_WHERE = "SELECT dmPassportType FROM DmPassportType dmPassportType WHERE ";
	private static final String _SQL_COUNT_DMPASSPORTTYPE = "SELECT COUNT(dmPassportType) FROM DmPassportType dmPassportType";
	private static final String _SQL_COUNT_DMPASSPORTTYPE_WHERE = "SELECT COUNT(dmPassportType) FROM DmPassportType dmPassportType WHERE ";
	private static final String _FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_1 =
		"dmPassportType.passportTypeCode IS NULL";
	private static final String _FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_2 =
		"dmPassportType.passportTypeCode =:passportTypeCode";
	private static final String _FINDER_COLUMN_PASSPORTTYPECODE_PASSPORTTYPECODE_3 =
		"(dmPassportType.passportTypeCode IS NULL OR dmPassportType.passportTypeCode =:passportTypeCode)";
	private static final String _FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_1 =
		"dmPassportType.passportTypeNameVN IS NULL";
	private static final String _FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_2 =
		"dmPassportType.passportTypeNameVN =:passportTypeNameVN";
	private static final String _FINDER_COLUMN_F_PASSPORTTYPENAMEVN_PASSPORTTYPENAMEVN_3 =
		"(dmPassportType.passportTypeNameVN IS NULL OR dmPassportType.passportTypeNameVN =:passportTypeNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPassportType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPassportType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPassportType exists with the key {";
	

	
}
