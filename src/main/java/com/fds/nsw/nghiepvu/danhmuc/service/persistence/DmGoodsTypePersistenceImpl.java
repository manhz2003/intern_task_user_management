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
import com.fds.nsw.nghiepvu.model.DmGoodsType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGoodsTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGoodsTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGoodsTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmGoodsTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGoodsType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGoodsTypeUtil} to access the dm goods type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGoodsType create(int id) {
		DmGoodsType dmGoodsType = new DmGoodsType();

		
		//dmGoodsType.setPrimaryKey(id);

		return dmGoodsType;
	}

	/**
	 * Removes the dm goods type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm goods type
	 * @return the dm goods type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType remove(int id)
		throws NoSuchDmGoodsTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm goods type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm goods type
	 * @return the dm goods type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGoodsType remove(Serializable primaryKey)
		throws NoSuchDmGoodsTypeException, SystemException {
		

		try {
			

			DmGoodsType dmGoodsType = findByPrimaryKey(primaryKey);

			if (dmGoodsType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGoodsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGoodsType);
			return dmGoodsType;
		}
		catch (NoSuchDmGoodsTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGoodsType remove(DmGoodsType DmGoodsType) throws SystemException {
	removeImpl(DmGoodsType);	return DmGoodsType;
}

protected DmGoodsType removeImpl

(DmGoodsType dmGoodsType)
		throws SystemException {
		try {
			repository.delete(dmGoodsType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGoodsType;
	}

	
	public DmGoodsType updateImpl(
		DmGoodsType dmGoodsType, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmGoodsType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGoodsType;
	}

	
	public DmGoodsType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm goods type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm goods type
	 * @return the dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType findByPrimaryKey(int id)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = fetchByPrimaryKey(id);

		if (dmGoodsType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGoodsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGoodsType;
	}

	/**
	 * Returns the dm goods type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm goods type
	 * @return the dm goods type, or <code>null</code> if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGoodsType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm goods type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm goods type
	 * @return the dm goods type, or <code>null</code> if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType fetchByPrimaryKey(int id) throws SystemException {
		DmGoodsType dmGoodsType = null;

		

		if (dmGoodsType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGoodsType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGoodsType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGoodsType;
	}

	/**
	 * Returns all the dm goods types where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		return findByGoodsTypeCode(goodsTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goods types where goodsTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeCode the goods type code
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @return the range of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByGoodsTypeCode(String goodsTypeCode,
		int start, int end) throws SystemException {
		return findByGoodsTypeCode(goodsTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goods types where goodsTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeCode the goods type code
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByGoodsTypeCode(String goodsTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGoodsType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGOODSTYPE_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_1);
			}
			else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGoodsTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				list = (List<DmGoodsType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType findByGoodsTypeCode_First(String goodsTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = fetchByGoodsTypeCode_First(goodsTypeCode,
				orderByComparator);

		if (dmGoodsType != null) {
			return dmGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeCode=");
		msg.append(goodsTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the first dm goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods type, or <code>null</code> if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType fetchByGoodsTypeCode_First(String goodsTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoodsType> list = findByGoodsTypeCode(goodsTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType findByGoodsTypeCode_Last(String goodsTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = fetchByGoodsTypeCode_Last(goodsTypeCode,
				orderByComparator);

		if (dmGoodsType != null) {
			return dmGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeCode=");
		msg.append(goodsTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the last dm goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods type, or <code>null</code> if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType fetchByGoodsTypeCode_Last(String goodsTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGoodsTypeCode(goodsTypeCode);

		List<DmGoodsType> list = findByGoodsTypeCode(goodsTypeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm goods types before and after the current dm goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm goods type
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType[] findByGoodsTypeCode_PrevAndNext(int id,
		String goodsTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = findByPrimaryKey(id);

		

		try {
			

			DmGoodsType[] array = new DmGoodsType[3];

			array[0] = getByGoodsTypeCode_PrevAndNext(dmGoodsType,
					goodsTypeCode, orderByComparator, true);

			array[1] = dmGoodsType;

			array[2] = getByGoodsTypeCode_PrevAndNext(dmGoodsType,
					goodsTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGoodsType getByGoodsTypeCode_PrevAndNext(
		DmGoodsType dmGoodsType, String goodsTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGOODSTYPE_WHERE);

		if (goodsTypeCode == null) {
			query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_1);
		}
		else {
			if (goodsTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_2);
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
			query.append(DmGoodsTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (goodsTypeCode != null) {
			builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGoodsType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGoodsType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm goods types where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @return the matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByF_goodsTypeNameVNbyLike(
		String goodsTypeNameVN) throws SystemException {
		return findByF_goodsTypeNameVNbyLike(goodsTypeNameVN,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goods types where goodsTypeNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @return the range of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByF_goodsTypeNameVNbyLike(
		String goodsTypeNameVN, int start, int end) throws SystemException {
		return findByF_goodsTypeNameVNbyLike(goodsTypeNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goods types where goodsTypeNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findByF_goodsTypeNameVNbyLike(
		String goodsTypeNameVN, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoodsType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGOODSTYPE_WHERE);

			if (goodsTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_1);
			}
			else {
				if (goodsTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGoodsTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (goodsTypeNameVN != null) {
					builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
				}

				list = (List<DmGoodsType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm goods type in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType findByF_goodsTypeNameVNbyLike_First(
		String goodsTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = fetchByF_goodsTypeNameVNbyLike_First(goodsTypeNameVN,
				orderByComparator);

		if (dmGoodsType != null) {
			return dmGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeNameVN=");
		msg.append(goodsTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the first dm goods type in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods type, or <code>null</code> if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType fetchByF_goodsTypeNameVNbyLike_First(
		String goodsTypeNameVN, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGoodsType> list = findByF_goodsTypeNameVNbyLike(goodsTypeNameVN,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm goods type in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType findByF_goodsTypeNameVNbyLike_Last(
		String goodsTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = fetchByF_goodsTypeNameVNbyLike_Last(goodsTypeNameVN,
				orderByComparator);

		if (dmGoodsType != null) {
			return dmGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeNameVN=");
		msg.append(goodsTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the last dm goods type in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods type, or <code>null</code> if a matching dm goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType fetchByF_goodsTypeNameVNbyLike_Last(
		String goodsTypeNameVN, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_goodsTypeNameVNbyLike(goodsTypeNameVN);

		List<DmGoodsType> list = findByF_goodsTypeNameVNbyLike(goodsTypeNameVN,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm goods types before and after the current dm goods type in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param id the primary key of the current dm goods type
	 * @param goodsTypeNameVN the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsTypeException if a dm goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoodsType[] findByF_goodsTypeNameVNbyLike_PrevAndNext(int id,
		String goodsTypeNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsTypeException, SystemException {
		DmGoodsType dmGoodsType = findByPrimaryKey(id);

		

		try {
			

			DmGoodsType[] array = new DmGoodsType[3];

			array[0] = getByF_goodsTypeNameVNbyLike_PrevAndNext(
					dmGoodsType, goodsTypeNameVN, orderByComparator, true);

			array[1] = dmGoodsType;

			array[2] = getByF_goodsTypeNameVNbyLike_PrevAndNext(
					dmGoodsType, goodsTypeNameVN, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGoodsType getByF_goodsTypeNameVNbyLike_PrevAndNext(
		 DmGoodsType dmGoodsType, String goodsTypeNameVN,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGOODSTYPE_WHERE);

		if (goodsTypeNameVN == null) {
			query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_1);
		}
		else {
			if (goodsTypeNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_2);
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
			query.append(DmGoodsTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (goodsTypeNameVN != null) {
			builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGoodsType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGoodsType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm goods types.
	 *
	 * @return the dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goods types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @return the range of dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goods types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm goods types
	 * @param end the upper bound of the range of dm goods types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoodsType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoodsType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGOODSTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGOODSTYPE.concat(DmGoodsTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGoodsType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm goods types where goodsTypeCode = &#63; from the database.
	 *
	 * @param goodsTypeCode the goods type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		for (DmGoodsType dmGoodsType : findByGoodsTypeCode(goodsTypeCode)) {
			repository.delete(dmGoodsType);
		}
	}

	/**
	 * Removes all the dm goods types where goodsTypeNameVN LIKE &#63; from the database.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_goodsTypeNameVNbyLike(String goodsTypeNameVN)
		throws SystemException {
		for (DmGoodsType dmGoodsType : findByF_goodsTypeNameVNbyLike(
				goodsTypeNameVN)) {
			repository.delete(dmGoodsType);
		}
	}

	/**
	 * Removes all the dm goods types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGoodsType dmGoodsType : findAll()) {
			repository.delete(dmGoodsType);
		}
	}

	/**
	 * Returns the number of dm goods types where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the number of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGOODSTYPE_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_1);
			}
			else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
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
	 * Returns the number of dm goods types where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @return the number of matching dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_goodsTypeNameVNbyLike(String goodsTypeNameVN)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGOODSTYPE_WHERE);

			if (goodsTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_1);
			}
			else {
				if (goodsTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (goodsTypeNameVN != null) {
					builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
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
	 * Returns the number of dm goods types.
	 *
	 * @return the number of dm goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGOODSTYPE).build();

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
	 * Initializes the dm goods type persistence.
	 */
	private static final String _SQL_SELECT_DMGOODSTYPE = "SELECT dmGoodsType FROM DmGoodsType dmGoodsType";
	private static final String _SQL_SELECT_DMGOODSTYPE_WHERE = "SELECT dmGoodsType FROM DmGoodsType dmGoodsType WHERE ";
	private static final String _SQL_COUNT_DMGOODSTYPE = "SELECT COUNT(dmGoodsType) FROM DmGoodsType dmGoodsType";
	private static final String _SQL_COUNT_DMGOODSTYPE_WHERE = "SELECT COUNT(dmGoodsType) FROM DmGoodsType dmGoodsType WHERE ";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_1 = "dmGoodsType.goodsTypeCode IS NULL";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_2 = "dmGoodsType.goodsTypeCode =:goodsTypeCode";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_3 = "(dmGoodsType.goodsTypeCode IS NULL OR dmGoodsType.goodsTypeCode =:goodsTypeCode)";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_1 =
		"dmGoodsType.goodsTypeNameVN LIKE NULL";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_2 =
		"dmGoodsType.goodsTypeNameVN LIKE :goodsTypeNameVN";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVNBYLIKE_GOODSTYPENAMEVN_3 =
		"(dmGoodsType.goodsTypeNameVN IS NULL OR dmGoodsType.goodsTypeNameVN LIKE :goodsTypeNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGoodsType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGoodsType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGoodsType exists with the key {";
	

	
}
