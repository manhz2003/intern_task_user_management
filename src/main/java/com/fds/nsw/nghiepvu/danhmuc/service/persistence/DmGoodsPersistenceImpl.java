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
import com.fds.nsw.nghiepvu.model.DmGoods;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGoodRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGoodsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGoodsPersistenceImpl extends BasePersistence {
	@Autowired
	DmGoodRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGoods> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGoodsUtil} to access the dm goods persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGoods create(int id) {
		DmGoods dmGoods = new DmGoods();

		
		//dmGoods.setPrimaryKey(id);

		return dmGoods;
	}

	/**
	 * Removes the dm goods with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm goods
	 * @return the dm goods that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods remove(int id)
		throws NoSuchDmGoodsException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm goods with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm goods
	 * @return the dm goods that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGoods remove(Serializable primaryKey)
		throws NoSuchDmGoodsException, SystemException {
		

		try {
			

			DmGoods dmGoods = findByPrimaryKey(primaryKey);

			if (dmGoods == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGoodsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGoods);
			return dmGoods;
		}
		catch (NoSuchDmGoodsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGoods remove(DmGoods DmGoods) throws SystemException {
	removeImpl(DmGoods);	return DmGoods;
}

protected DmGoods removeImpl

(DmGoods dmGoods) throws SystemException {
		try {
			repository.delete(dmGoods);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGoods;
	}

	
	public DmGoods updateImpl(DmGoods dmGoods,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGoods);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGoods;
	}

	
	public DmGoods findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm goods with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmGoodsException} if it could not be found.
	 *
	 * @param id the primary key of the dm goods
	 * @return the dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods findByPrimaryKey(int id)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = fetchByPrimaryKey(id);

		if (dmGoods == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGoodsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGoods;
	}

	/**
	 * Returns the dm goods with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm goods
	 * @return the dm goods, or <code>null</code> if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGoods fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm goods with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm goods
	 * @return the dm goods, or <code>null</code> if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods fetchByPrimaryKey(int id) throws SystemException {
		DmGoods dmGoods = null;

		

		if (dmGoods == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGoods> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGoods = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGoods;
	}

	/**
	 * Returns all the dm goodses where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @return the matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		return findByGoodsItemCode(goodsItemCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goodses where goodsItemCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemCode the goods item code
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @return the range of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByGoodsItemCode(String goodsItemCode, int start,
		int end) throws SystemException {
		return findByGoodsItemCode(goodsItemCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goodses where goodsItemCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemCode the goods item code
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByGoodsItemCode(String goodsItemCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmGoods> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGOODS_WHERE);

			if (goodsItemCode == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_1);
			}
			else {
				if (goodsItemCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGoodsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (goodsItemCode != null) {
					builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
				}

				list = (List<DmGoods>)queryFactory.getResultList(builder);
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
	 * Returns the first dm goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods findByGoodsItemCode_First(String goodsItemCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = fetchByGoodsItemCode_First(goodsItemCode,
				orderByComparator);

		if (dmGoods != null) {
			return dmGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemCode=");
		msg.append(goodsItemCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsException(msg.toString());
	}

	/**
	 * Returns the first dm goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods, or <code>null</code> if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods fetchByGoodsItemCode_First(String goodsItemCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoods> list = findByGoodsItemCode(goodsItemCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods findByGoodsItemCode_Last(String goodsItemCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = fetchByGoodsItemCode_Last(goodsItemCode,
				orderByComparator);

		if (dmGoods != null) {
			return dmGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemCode=");
		msg.append(goodsItemCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsException(msg.toString());
	}

	/**
	 * Returns the last dm goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods, or <code>null</code> if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods fetchByGoodsItemCode_Last(String goodsItemCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGoodsItemCode(goodsItemCode);

		List<DmGoods> list = findByGoodsItemCode(goodsItemCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm goodses before and after the current dm goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param id the primary key of the current dm goods
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods[] findByGoodsItemCode_PrevAndNext(int id,
		String goodsItemCode, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = findByPrimaryKey(id);

		

		try {
			

			DmGoods[] array = new DmGoods[3];

			array[0] = getByGoodsItemCode_PrevAndNext(dmGoods,
					goodsItemCode, orderByComparator, true);

			array[1] = dmGoods;

			array[2] = getByGoodsItemCode_PrevAndNext(dmGoods,
					goodsItemCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGoods getByGoodsItemCode_PrevAndNext(
		DmGoods dmGoods, String goodsItemCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGOODS_WHERE);

		if (goodsItemCode == null) {
			query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_1);
		}
		else {
			if (goodsItemCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_2);
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
			query.append(DmGoodsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (goodsItemCode != null) {
			builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGoods);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGoods> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm goodses where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @return the matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByF_goodsItemNamebyLike(String goodsItemName)
		throws SystemException {
		return findByF_goodsItemNamebyLike(goodsItemName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goodses where goodsItemName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemName the goods item name
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @return the range of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByF_goodsItemNamebyLike(String goodsItemName,
		int start, int end) throws SystemException {
		return findByF_goodsItemNamebyLike(goodsItemName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goodses where goodsItemName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemName the goods item name
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findByF_goodsItemNamebyLike(String goodsItemName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGoods> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGOODS_WHERE);

			if (goodsItemName == null) {
				query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_1);
			}
			else {
				if (goodsItemName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGoodsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (goodsItemName != null) {
					builder.appendNamedParameterMap("goodsItemName", goodsItemName);
				}

				list = (List<DmGoods>)queryFactory.getResultList(builder);
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
	 * Returns the first dm goods in the ordered set where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods findByF_goodsItemNamebyLike_First(String goodsItemName,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = fetchByF_goodsItemNamebyLike_First(goodsItemName,
				orderByComparator);

		if (dmGoods != null) {
			return dmGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemName=");
		msg.append(goodsItemName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsException(msg.toString());
	}

	/**
	 * Returns the first dm goods in the ordered set where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm goods, or <code>null</code> if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods fetchByF_goodsItemNamebyLike_First(String goodsItemName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoods> list = findByF_goodsItemNamebyLike(goodsItemName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm goods in the ordered set where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods findByF_goodsItemNamebyLike_Last(String goodsItemName,
		OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = fetchByF_goodsItemNamebyLike_Last(goodsItemName,
				orderByComparator);

		if (dmGoods != null) {
			return dmGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemName=");
		msg.append(goodsItemName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGoodsException(msg.toString());
	}

	/**
	 * Returns the last dm goods in the ordered set where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm goods, or <code>null</code> if a matching dm goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods fetchByF_goodsItemNamebyLike_Last(String goodsItemName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_goodsItemNamebyLike(goodsItemName);

		List<DmGoods> list = findByF_goodsItemNamebyLike(goodsItemName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm goodses before and after the current dm goods in the ordered set where goodsItemName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm goods
	 * @param goodsItemName the goods item name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmGoodsException if a dm goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGoods[] findByF_goodsItemNamebyLike_PrevAndNext(int id,
		String goodsItemName, OrderByComparator orderByComparator)
		throws NoSuchDmGoodsException, SystemException {
		DmGoods dmGoods = findByPrimaryKey(id);

		

		try {
			

			DmGoods[] array = new DmGoods[3];

			array[0] = getByF_goodsItemNamebyLike_PrevAndNext(dmGoods,
					goodsItemName, orderByComparator, true);

			array[1] = dmGoods;

			array[2] = getByF_goodsItemNamebyLike_PrevAndNext(dmGoods,
					goodsItemName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGoods getByF_goodsItemNamebyLike_PrevAndNext(
		DmGoods dmGoods, String goodsItemName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGOODS_WHERE);

		if (goodsItemName == null) {
			query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_1);
		}
		else {
			if (goodsItemName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_2);
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
			query.append(DmGoodsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (goodsItemName != null) {
			builder.appendNamedParameterMap("goodsItemName", goodsItemName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGoods);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGoods> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm goodses.
	 *
	 * @return the dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm goodses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @return the range of dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm goodses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm goodses
	 * @param end the upper bound of the range of dm goodses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGoods> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGoods> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGOODS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGOODS.concat(DmGoodsModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGoods>) queryFactory.getResultList(builder);
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
	 * Removes all the dm goodses where goodsItemCode = &#63; from the database.
	 *
	 * @param goodsItemCode the goods item code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		for (DmGoods dmGoods : findByGoodsItemCode(goodsItemCode)) {
			repository.delete(dmGoods);
		}
	}

	/**
	 * Removes all the dm goodses where goodsItemName LIKE &#63; from the database.
	 *
	 * @param goodsItemName the goods item name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_goodsItemNamebyLike(String goodsItemName)
		throws SystemException {
		for (DmGoods dmGoods : findByF_goodsItemNamebyLike(goodsItemName)) {
			repository.delete(dmGoods);
		}
	}

	/**
	 * Removes all the dm goodses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGoods dmGoods : findAll()) {
			repository.delete(dmGoods);
		}
	}

	/**
	 * Returns the number of dm goodses where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @return the number of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGOODS_WHERE);

			if (goodsItemCode == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_1);
			}
			else {
				if (goodsItemCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (goodsItemCode != null) {
					builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
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
	 * Returns the number of dm goodses where goodsItemName LIKE &#63;.
	 *
	 * @param goodsItemName the goods item name
	 * @return the number of matching dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_goodsItemNamebyLike(String goodsItemName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGOODS_WHERE);

			if (goodsItemName == null) {
				query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_1);
			}
			else {
				if (goodsItemName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (goodsItemName != null) {
					builder.appendNamedParameterMap("goodsItemName", goodsItemName);
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
	 * Returns the number of dm goodses.
	 *
	 * @return the number of dm goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGOODS).build();

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
	 * Initializes the dm goods persistence.
	 */
	private static final String _SQL_SELECT_DMGOODS = "SELECT dmGoods FROM DmGoods dmGoods";
	private static final String _SQL_SELECT_DMGOODS_WHERE = "SELECT dmGoods FROM DmGoods dmGoods WHERE ";
	private static final String _SQL_COUNT_DMGOODS = "SELECT COUNT(dmGoods) FROM DmGoods dmGoods";
	private static final String _SQL_COUNT_DMGOODS_WHERE = "SELECT COUNT(dmGoods) FROM DmGoods dmGoods WHERE ";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_1 = "dmGoods.goodsItemCode IS NULL";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_2 = "dmGoods.goodsItemCode =:goodsItemCode";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_3 = "(dmGoods.goodsItemCode IS NULL OR dmGoods.goodsItemCode =:goodsItemCode)";
	private static final String _FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_1 =
		"dmGoods.goodsItemName LIKE NULL";
	private static final String _FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_2 =
		"dmGoods.goodsItemName LIKE :goodsItemName";
	private static final String _FINDER_COLUMN_F_GOODSITEMNAMEBYLIKE_GOODSITEMNAME_3 =
		"(dmGoods.goodsItemName IS NULL OR dmGoods.goodsItemName LIKE :goodsItemName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGoods.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGoods exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGoods exists with the key {";
	

	
}
