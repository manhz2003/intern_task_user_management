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
import com.fds.nsw.nghiepvu.model.DmHistoryGoodsType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryGoodsTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryGoodsTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryGoodsTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryGoodsTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryGoodsType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryGoodsTypeUtil} to access the dm history goods type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryGoodsType create(int id) {
		DmHistoryGoodsType dmHistoryGoodsType = new DmHistoryGoodsType();

		
		//dmHistoryGoodsType.setPrimaryKey(id);

		return dmHistoryGoodsType;
	}

	/**
	 * Removes the dm history goods type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history goods type
	 * @return the dm history goods type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType remove(int id)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history goods type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history goods type
	 * @return the dm history goods type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryGoodsType remove(Serializable primaryKey)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		

		try {
			

			DmHistoryGoodsType dmHistoryGoodsType = findByPrimaryKey(primaryKey);

			if (dmHistoryGoodsType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryGoodsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryGoodsType);
			return dmHistoryGoodsType;
		}
		catch (NoSuchDmHistoryGoodsTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryGoodsType remove(DmHistoryGoodsType DmHistoryGoodsType) throws SystemException {
	removeImpl(DmHistoryGoodsType);	return DmHistoryGoodsType;
}

protected DmHistoryGoodsType removeImpl

(
		DmHistoryGoodsType dmHistoryGoodsType) throws SystemException {
		try {
			repository.delete(dmHistoryGoodsType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryGoodsType;
	}

	
	public DmHistoryGoodsType updateImpl(
		DmHistoryGoodsType dmHistoryGoodsType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryGoodsType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryGoodsType;
	}

	
	public DmHistoryGoodsType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history goods type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history goods type
	 * @return the dm history goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType findByPrimaryKey(int id)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = fetchByPrimaryKey(id);

		if (dmHistoryGoodsType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryGoodsTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryGoodsType;
	}

	/**
	 * Returns the dm history goods type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history goods type
	 * @return the dm history goods type, or <code>null</code> if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryGoodsType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history goods type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history goods type
	 * @return the dm history goods type, or <code>null</code> if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = null;

		

		if (dmHistoryGoodsType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryGoodsType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryGoodsType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryGoodsType;
	}

	/**
	 * Returns all the dm history goods types where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the matching dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		return findByGoodsTypeCode(goodsTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history goods types where goodsTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeCode the goods type code
	 * @param start the lower bound of the range of dm history goods types
	 * @param end the upper bound of the range of dm history goods types (not inclusive)
	 * @return the range of matching dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findByGoodsTypeCode(String goodsTypeCode,
		int start, int end) throws SystemException {
		return findByGoodsTypeCode(goodsTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history goods types where goodsTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsTypeCode the goods type code
	 * @param start the lower bound of the range of dm history goods types
	 * @param end the upper bound of the range of dm history goods types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findByGoodsTypeCode(String goodsTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryGoodsType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYGOODSTYPE_WHERE);

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
				query.append(DmHistoryGoodsTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				list = (List<DmHistoryGoodsType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType findByGoodsTypeCode_First(String goodsTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = fetchByGoodsTypeCode_First(goodsTypeCode,
				orderByComparator);

		if (dmHistoryGoodsType != null) {
			return dmHistoryGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeCode=");
		msg.append(goodsTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the first dm history goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history goods type, or <code>null</code> if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType fetchByGoodsTypeCode_First(String goodsTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryGoodsType> list = findByGoodsTypeCode(goodsTypeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType findByGoodsTypeCode_Last(String goodsTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = fetchByGoodsTypeCode_Last(goodsTypeCode,
				orderByComparator);

		if (dmHistoryGoodsType != null) {
			return dmHistoryGoodsType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeCode=");
		msg.append(goodsTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryGoodsTypeException(msg.toString());
	}

	/**
	 * Returns the last dm history goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history goods type, or <code>null</code> if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType fetchByGoodsTypeCode_Last(String goodsTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGoodsTypeCode(goodsTypeCode);

		List<DmHistoryGoodsType> list = findByGoodsTypeCode(goodsTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history goods types before and after the current dm history goods type in the ordered set where goodsTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm history goods type
	 * @param goodsTypeCode the goods type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a dm history goods type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType[] findByGoodsTypeCode_PrevAndNext(int id,
		String goodsTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = findByPrimaryKey(id);

		

		try {
			

			DmHistoryGoodsType[] array = new DmHistoryGoodsType[3];

			array[0] = getByGoodsTypeCode_PrevAndNext(
					dmHistoryGoodsType, goodsTypeCode, orderByComparator, true);

			array[1] = dmHistoryGoodsType;

			array[2] = getByGoodsTypeCode_PrevAndNext(
					dmHistoryGoodsType, goodsTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryGoodsType getByGoodsTypeCode_PrevAndNext(
		 DmHistoryGoodsType dmHistoryGoodsType,
		String goodsTypeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYGOODSTYPE_WHERE);

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
			query.append(DmHistoryGoodsTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (goodsTypeCode != null) {
			builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryGoodsType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryGoodsType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history goods type where goodsTypeCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException} if it could not be found.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion the sync version
	 * @return the matching dm history goods type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsTypeException if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType findByGoodsTypeCodeAndSyncVersion(
		String goodsTypeCode, String syncVersion)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = fetchByGoodsTypeCodeAndSyncVersion(goodsTypeCode,
				syncVersion);

		if (dmHistoryGoodsType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("goodsTypeCode=");
			msg.append(goodsTypeCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryGoodsTypeException(msg.toString());
		}

		return dmHistoryGoodsType;
	}

	/**
	 * Returns the dm history goods type where goodsTypeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion the sync version
	 * @return the matching dm history goods type, or <code>null</code> if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType fetchByGoodsTypeCodeAndSyncVersion(
		String goodsTypeCode, String syncVersion) throws SystemException {
		return fetchByGoodsTypeCodeAndSyncVersion(goodsTypeCode, syncVersion,
			true);
	}

	/**
	 * Returns the dm history goods type where goodsTypeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history goods type, or <code>null</code> if a matching dm history goods type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType fetchByGoodsTypeCodeAndSyncVersion(
		String goodsTypeCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = null;
		if (dmHistoryGoodsType == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYGOODSTYPE_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_1);
			}
			else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryGoodsTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryGoodsType.class).build();

				

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryGoodsType = (DmHistoryGoodsType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryGoodsType;
	}

	/**
	 * Returns all the dm history goods types.
	 *
	 * @return the dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history goods types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history goods types
	 * @param end the upper bound of the range of dm history goods types (not inclusive)
	 * @return the range of dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history goods types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history goods types
	 * @param end the upper bound of the range of dm history goods types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoodsType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryGoodsType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYGOODSTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYGOODSTYPE.concat(DmHistoryGoodsTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryGoodsType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history goods types where goodsTypeCode = &#63; from the database.
	 *
	 * @param goodsTypeCode the goods type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		for (DmHistoryGoodsType dmHistoryGoodsType : findByGoodsTypeCode(
				goodsTypeCode)) {
			repository.delete(dmHistoryGoodsType);
		}
	}

	/**
	 * Removes the dm history goods type where goodsTypeCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion the sync version
	 * @return the dm history goods type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoodsType removeByGoodsTypeCodeAndSyncVersion(
		String goodsTypeCode, String syncVersion)
		throws NoSuchDmHistoryGoodsTypeException, SystemException {
		DmHistoryGoodsType dmHistoryGoodsType = findByGoodsTypeCodeAndSyncVersion(goodsTypeCode,
				syncVersion);

		repository.delete(dmHistoryGoodsType);
			return dmHistoryGoodsType;
	}

	/**
	 * Removes all the dm history goods types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryGoodsType dmHistoryGoodsType : findAll()) {
			repository.delete(dmHistoryGoodsType);
		}
	}

	/**
	 * Returns the number of dm history goods types where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the number of matching dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsTypeCode(String goodsTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYGOODSTYPE_WHERE);

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
	 * Returns the number of dm history goods types where goodsTypeCode = &#63; and syncVersion = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsTypeCodeAndSyncVersion(String goodsTypeCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYGOODSTYPE_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_1);
			}
			else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
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
	 * Returns the number of dm history goods types.
	 *
	 * @return the number of dm history goods types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYGOODSTYPE).build();

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
	 * Initializes the dm history goods type persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYGOODSTYPE = "SELECT dmHistoryGoodsType FROM DmHistoryGoodsType dmHistoryGoodsType";
	private static final String _SQL_SELECT_DMHISTORYGOODSTYPE_WHERE = "SELECT dmHistoryGoodsType FROM DmHistoryGoodsType dmHistoryGoodsType WHERE ";
	private static final String _SQL_COUNT_DMHISTORYGOODSTYPE = "SELECT COUNT(dmHistoryGoodsType) FROM DmHistoryGoodsType dmHistoryGoodsType";
	private static final String _SQL_COUNT_DMHISTORYGOODSTYPE_WHERE = "SELECT COUNT(dmHistoryGoodsType) FROM DmHistoryGoodsType dmHistoryGoodsType WHERE ";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_1 = "dmHistoryGoodsType.goodsTypeCode IS NULL";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_2 = "dmHistoryGoodsType.goodsTypeCode =:goodsTypeCode";
	private static final String _FINDER_COLUMN_GOODSTYPECODE_GOODSTYPECODE_3 = "(dmHistoryGoodsType.goodsTypeCode IS NULL OR dmHistoryGoodsType.goodsTypeCode =:goodsTypeCode)";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_1 =
		"dmHistoryGoodsType.goodsTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_2 =
		"dmHistoryGoodsType.goodsTypeCode =:goodsTypeCode AND ";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_GOODSTYPECODE_3 =
		"(dmHistoryGoodsType.goodsTypeCode IS NULL OR dmHistoryGoodsType.goodsTypeCode =:goodsTypeCode) AND ";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryGoodsType.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryGoodsType.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_GOODSTYPECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryGoodsType.syncVersion IS NULL OR dmHistoryGoodsType.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryGoodsType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryGoodsType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryGoodsType exists with the key {";
	

	
}
