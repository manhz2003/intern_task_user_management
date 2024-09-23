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
import com.fds.nsw.nghiepvu.model.DmHistoryGoods;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryGoodRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryGoodsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryGoodsPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryGoodRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryGoods> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryGoodsUtil} to access the dm history goods persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryGoods create(int id) {
		DmHistoryGoods dmHistoryGoods = new DmHistoryGoods();


		//dmHistoryGoods.setPrimaryKey(id);

		return dmHistoryGoods;
	}

	/**
	 * Removes the dm history goods with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history goods
	 * @return the dm history goods that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods remove(int id)
		throws NoSuchDmHistoryGoodsException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history goods with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history goods
	 * @return the dm history goods that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public DmHistoryGoods remove(Serializable primaryKey)
		throws NoSuchDmHistoryGoodsException, SystemException {


		try {


			DmHistoryGoods dmHistoryGoods = findByPrimaryKey(primaryKey);

			if (dmHistoryGoods == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryGoodsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryGoods);
			return dmHistoryGoods;
		}
		catch (NoSuchDmHistoryGoodsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}


	public DmHistoryGoods remove(DmHistoryGoods DmHistoryGoods) throws SystemException {
	removeImpl(DmHistoryGoods);	return DmHistoryGoods;
}

protected DmHistoryGoods removeImpl

(DmHistoryGoods dmHistoryGoods)
		throws SystemException {
		try {
			repository.delete(dmHistoryGoods);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryGoods;
	}


	public DmHistoryGoods updateImpl(
		DmHistoryGoods dmHistoryGoods, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryGoods);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryGoods;
	}


	public DmHistoryGoods findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history goods with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException} if it could not be found.
	 *
	 * @param id the primary key of the dm history goods
	 * @return the dm history goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods findByPrimaryKey(int id)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = fetchByPrimaryKey(id);

		if (dmHistoryGoods == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryGoodsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryGoods;
	}

	/**
	 * Returns the dm history goods with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history goods
	 * @return the dm history goods, or <code>null</code> if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public DmHistoryGoods fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history goods with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history goods
	 * @return the dm history goods, or <code>null</code> if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryGoods dmHistoryGoods = null;



		if (dmHistoryGoods == null) {


			boolean hasException = false;

			try {


				Optional<DmHistoryGoods> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryGoods = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {



			}
		}

		return dmHistoryGoods;
	}

	/**
	 * Returns all the dm history goodses where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @return the matching dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		return findByGoodsItemCode(goodsItemCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history goodses where goodsItemCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemCode the goods item code
	 * @param start the lower bound of the range of dm history goodses
	 * @param end the upper bound of the range of dm history goodses (not inclusive)
	 * @return the range of matching dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findByGoodsItemCode(String goodsItemCode,
		int start, int end) throws SystemException {
		return findByGoodsItemCode(goodsItemCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history goodses where goodsItemCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param goodsItemCode the goods item code
	 * @param start the lower bound of the range of dm history goodses
	 * @param end the upper bound of the range of dm history goodses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findByGoodsItemCode(String goodsItemCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryGoods> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYGOODS_WHERE);

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
				query.append(DmHistoryGoodsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();



				if (goodsItemCode != null) {
					builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
				}

				list = (List<DmHistoryGoods>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods findByGoodsItemCode_First(String goodsItemCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = fetchByGoodsItemCode_First(goodsItemCode,
				orderByComparator);

		if (dmHistoryGoods != null) {
			return dmHistoryGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemCode=");
		msg.append(goodsItemCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryGoodsException(msg.toString());
	}

	/**
	 * Returns the first dm history goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history goods, or <code>null</code> if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods fetchByGoodsItemCode_First(String goodsItemCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryGoods> list = findByGoodsItemCode(goodsItemCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods findByGoodsItemCode_Last(String goodsItemCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = fetchByGoodsItemCode_Last(goodsItemCode,
				orderByComparator);

		if (dmHistoryGoods != null) {
			return dmHistoryGoods;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsItemCode=");
		msg.append(goodsItemCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryGoodsException(msg.toString());
	}

	/**
	 * Returns the last dm history goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history goods, or <code>null</code> if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods fetchByGoodsItemCode_Last(String goodsItemCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByGoodsItemCode(goodsItemCode);

		List<DmHistoryGoods> list = findByGoodsItemCode(goodsItemCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history goodses before and after the current dm history goods in the ordered set where goodsItemCode = &#63;.
	 *
	 * @param id the primary key of the current dm history goods
	 * @param goodsItemCode the goods item code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a dm history goods with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods[] findByGoodsItemCode_PrevAndNext(int id,
		String goodsItemCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = findByPrimaryKey(id);



		try {


			DmHistoryGoods[] array = new DmHistoryGoods[3];

			array[0] = getByGoodsItemCode_PrevAndNext(dmHistoryGoods,
					goodsItemCode, orderByComparator, true);

			array[1] = dmHistoryGoods;

			array[2] = getByGoodsItemCode_PrevAndNext(dmHistoryGoods,
					goodsItemCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {

		}
	}

	protected DmHistoryGoods getByGoodsItemCode_PrevAndNext(
		DmHistoryGoods dmHistoryGoods, String goodsItemCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYGOODS_WHERE);

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
			query.append(DmHistoryGoodsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();



		if (goodsItemCode != null) {
			builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryGoods);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryGoods> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history goods where goodsItemCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException} if it could not be found.
	 *
	 * @param goodsItemCode the goods item code
	 * @param syncVersion the sync version
	 * @return the matching dm history goods
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryGoodsException if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods findByGoodsItemCodeAndSyncVersion(
		String goodsItemCode, String syncVersion)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = fetchByGoodsItemCodeAndSyncVersion(goodsItemCode,
				syncVersion);

		if (dmHistoryGoods == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("goodsItemCode=");
			msg.append(goodsItemCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryGoodsException(msg.toString());
		}

		return dmHistoryGoods;
	}

	/**
	 * Returns the dm history goods where goodsItemCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param goodsItemCode the goods item code
	 * @param syncVersion the sync version
	 * @return the matching dm history goods, or <code>null</code> if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods fetchByGoodsItemCodeAndSyncVersion(
		String goodsItemCode, String syncVersion) throws SystemException {
		return fetchByGoodsItemCodeAndSyncVersion(goodsItemCode, syncVersion,
			true);
	}

	/**
	 * Returns the dm history goods where goodsItemCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param goodsItemCode the goods item code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history goods, or <code>null</code> if a matching dm history goods could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods fetchByGoodsItemCodeAndSyncVersion(
		String goodsItemCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryGoods dmHistoryGoods = null;
		if (dmHistoryGoods == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYGOODS_WHERE);

			if (goodsItemCode == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_1);
			}
			else {
				if (goodsItemCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryGoodsModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryGoods.class).build();



				if (goodsItemCode != null) {
					builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryGoods = (DmHistoryGoods) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {



			}
		}
		return dmHistoryGoods;
	}

	/**
	 * Returns all the dm history goodses.
	 *
	 * @return the dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history goodses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history goodses
	 * @param end the upper bound of the range of dm history goodses (not inclusive)
	 * @return the range of dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history goodses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history goodses
	 * @param end the upper bound of the range of dm history goodses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryGoods> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryGoods> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYGOODS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYGOODS.concat(DmHistoryGoodsModelImpl.ORDER_BY_JPQL);
			}



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryGoods>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history goodses where goodsItemCode = &#63; from the database.
	 *
	 * @param goodsItemCode the goods item code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		for (DmHistoryGoods dmHistoryGoods : findByGoodsItemCode(goodsItemCode)) {
			repository.delete(dmHistoryGoods);
		}
	}

	/**
	 * Removes the dm history goods where goodsItemCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param goodsItemCode the goods item code
	 * @param syncVersion the sync version
	 * @return the dm history goods that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryGoods removeByGoodsItemCodeAndSyncVersion(
		String goodsItemCode, String syncVersion)
		throws NoSuchDmHistoryGoodsException, SystemException {
		DmHistoryGoods dmHistoryGoods = findByGoodsItemCodeAndSyncVersion(goodsItemCode,
				syncVersion);

		repository.delete(dmHistoryGoods);
			return dmHistoryGoods;
	}

	/**
	 * Removes all the dm history goodses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryGoods dmHistoryGoods : findAll()) {
			repository.delete(dmHistoryGoods);
		}
	}

	/**
	 * Returns the number of dm history goodses where goodsItemCode = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @return the number of matching dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsItemCode(String goodsItemCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYGOODS_WHERE);

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
	 * Returns the number of dm history goodses where goodsItemCode = &#63; and syncVersion = &#63;.
	 *
	 * @param goodsItemCode the goods item code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByGoodsItemCodeAndSyncVersion(String goodsItemCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYGOODS_WHERE);

			if (goodsItemCode == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_1);
			}
			else {
				if (goodsItemCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();



			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (goodsItemCode != null) {
					builder.appendNamedParameterMap("goodsItemCode", goodsItemCode);
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
	 * Returns the number of dm history goodses.
	 *
	 * @return the number of dm history goodses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {


			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYGOODS).build();

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
	 * Initializes the dm history goods persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYGOODS = "SELECT dmHistoryGoods FROM DmHistoryGoods dmHistoryGoods";
	private static final String _SQL_SELECT_DMHISTORYGOODS_WHERE = "SELECT dmHistoryGoods FROM DmHistoryGoods dmHistoryGoods WHERE ";
	private static final String _SQL_COUNT_DMHISTORYGOODS = "SELECT COUNT(dmHistoryGoods) FROM DmHistoryGoods dmHistoryGoods";
	private static final String _SQL_COUNT_DMHISTORYGOODS_WHERE = "SELECT COUNT(dmHistoryGoods) FROM DmHistoryGoods dmHistoryGoods WHERE ";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_1 = "dmHistoryGoods.goodsItemCode IS NULL";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_2 = "dmHistoryGoods.goodsItemCode =:goodsItemCode";
	private static final String _FINDER_COLUMN_GOODSITEMCODE_GOODSITEMCODE_3 = "(dmHistoryGoods.goodsItemCode IS NULL OR dmHistoryGoods.goodsItemCode =:goodsItemCode)";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_1 =
		"dmHistoryGoods.goodsItemCode IS NULL AND ";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_2 =
		"dmHistoryGoods.goodsItemCode =:goodsItemCode AND ";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_GOODSITEMCODE_3 =
		"(dmHistoryGoods.goodsItemCode IS NULL OR dmHistoryGoods.goodsItemCode =:goodsItemCode) AND ";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryGoods.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryGoods.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_GOODSITEMCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryGoods.syncVersion IS NULL OR dmHistoryGoods.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryGoods.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryGoods exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryGoods exists with the key {";
	

	
}
