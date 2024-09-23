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
import com.fds.nsw.nghiepvu.service.exception.*;

import com.fds.nsw.nghiepvu.modelImpl.DmDataItemModelImpl;

import com.fds.nsw.nghiepvu.repository.DmDataitemRepository;
import com.fds.nsw.nghiepvu.model.DmDataitem;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmDataItemPersistenceImpl extends BasePersistence {
	@Autowired
	DmDataitemRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmDataitem> queryFactory;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * DmDataitemUtil} to access the dm data item persistence. Modify
	 * <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmDataitem create(long id) {
		DmDataitem dmDataItem = new DmDataitem();

		// dmDataItem.setPrimaryKey(id);

		return dmDataItem;
	}

	/**
	 * Removes the dm data item with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm data item
	 * @return the dm data item that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem remove(long Id) throws NoSuchDmDataItemException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm data item with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm data item
	 * @return the dm data item that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */

	public DmDataitem remove(Serializable primaryKey) throws NoSuchDmDataItemException, SystemException {

		try {

			DmDataitem dmDataItem = findByPrimaryKey(primaryKey);

			if (dmDataItem == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmDataItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(dmDataItem);
			return dmDataItem;
		} catch (NoSuchDmDataItemException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public DmDataitem remove(DmDataitem DmDataitem) throws SystemException {
	removeImpl(DmDataitem);	return DmDataitem;
}

protected DmDataitem removeImpl

(DmDataitem dmDataItem) throws SystemException {
		try {
			repository.delete(dmDataItem);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmDataItem;
	}

	public DmDataitem updateImpl(DmDataitem dmDataItem, boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmDataItem);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmDataItem;
	}

	public DmDataitem findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the dm data item with the primary key or throws a
	 * {@link vn.gt.dao.danhmuc.NoSuchDmDataItemException} if it could not be found.
	 *
	 * @param Id the primary key of the dm data item
	 * @return the dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByPrimaryKey(long Id) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByPrimaryKey(Id);

		if (dmDataItem == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmDataItemException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
		}

		return dmDataItem;
	}

	/**
	 * Returns the dm data item with the primary key or returns <code>null</code> if
	 * it could not be found.
	 *
	 * @param primaryKey the primary key of the dm data item
	 * @return the dm data item, or <code>null</code> if a dm data item with the
	 *         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public DmDataitem fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the dm data item with the primary key or returns <code>null</code> if
	 * it could not be found.
	 *
	 * @param Id the primary key of the dm data item
	 * @return the dm data item, or <code>null</code> if a dm data item with the
	 *         primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByPrimaryKey(long Id) throws SystemException {
		DmDataitem dmDataItem = null;

		if (dmDataItem == null) {

			boolean hasException = false;

			try {

				Optional<DmDataitem> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmDataItem = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return dmDataItem;
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and code = &#63; or
	 * throws a {@link vn.gt.dao.danhmuc.NoSuchDmDataItemException} if it could not
	 * be found.
	 *
	 * @param DataGroupId the data group ID
	 * @param code       the code0
	 * @return the matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndCode0(long DataGroupId, String code)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndCode0(DataGroupId, code);

		if (dmDataItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("DataGroupId=");
			msg.append(DataGroupId);

			msg.append(", code=");
			msg.append(code);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmDataItemException(msg.toString());
		}

		return dmDataItem;
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and code = &#63; or
	 * returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param DataGroupId the data group ID
	 * @param code       the code0
	 * @return the matching dm data item, or <code>null</code> if a matching dm data
	 *         item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndCode0(long DataGroupId, String code) throws SystemException {
		return fetchByDataGroupIdAndCode0(DataGroupId, code, true);
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and code = &#63; or
	 * returns <code>null</code> if it could not be found, optionally using the
	 * finder cache.
	 *
	 * @param DataGroupId       the data group ID
	 * @param code             the code0
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm data item, or <code>null</code> if a matching dm data
	 *         item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndCode0(long DataGroupId, String code, boolean retrieveFromCache)
			throws SystemException {
		DmDataitem dmDataItem = null;
		if (dmDataItem == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_DATAGROUPID_2);

			if (code == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_1);
			} else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_2);
				}
			}

			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (code != null) {
					builder.appendNamedParameterMap("code", code);
				}

				dmDataItem = (DmDataitem) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return dmDataItem;
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63; and Node1 = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node1       the node1
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode1(long DataGroupId, String Node1) throws SystemException {
		return findByDataGroupIdAndNode1(DataGroupId, Node1, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63; and Node1
	 * = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param Node1       the node1
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode1(long DataGroupId, String Node1, int start, int end)
			throws SystemException {
		return findByDataGroupIdAndNode1(DataGroupId, Node1, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;
	 * and Node1 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode1(long DataGroupId, String Node1, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_DATAGROUPID_2);

			if (Node1 == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_1);
			} else {
				if (Node1.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node1 != null) {
					builder.appendNamedParameterMap("Node1", Node1);
				}

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Node1 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndNode1_First(long DataGroupId, String Node1,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndNode1_First(DataGroupId, Node1, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Node1=");
		msg.append(Node1);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Node1 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndNode1_First(long DataGroupId, String Node1,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = findByDataGroupIdAndNode1(DataGroupId, Node1, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Node1 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndNode1_Last(long DataGroupId, String Node1,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndNode1_Last(DataGroupId, Node1, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Node1=");
		msg.append(Node1);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Node1 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndNode1_Last(long DataGroupId, String Node1,
			OrderByComparator orderByComparator) throws SystemException {
		int count = countByDataGroupIdAndNode1(DataGroupId, Node1);

		List<DmDataitem> list = findByDataGroupIdAndNode1(DataGroupId, Node1, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63; and Node1 = &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param Node1             the node1
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupIdAndNode1_PrevAndNext(long Id, long DataGroupId, String Node1,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupIdAndNode1_PrevAndNext(dmDataItem, DataGroupId, Node1, orderByComparator, true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupIdAndNode1_PrevAndNext(dmDataItem, DataGroupId, Node1, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupIdAndNode1_PrevAndNext(DmDataitem dmDataItem, long DataGroupId, String Node1,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_DATAGROUPID_2);

		if (Node1 == null) {
			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_1);
		} else {
			if (Node1.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_3);
			} else {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_2);
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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		if (Node1 != null) {
			builder.appendNamedParameterMap("Node1", Node1);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63; and Node2 = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode2(long DataGroupId, String Node2) throws SystemException {
		return findByDataGroupIdAndNode2(DataGroupId, Node2, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63; and Node2
	 * = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode2(long DataGroupId, String Node2, int start, int end)
			throws SystemException {
		return findByDataGroupIdAndNode2(DataGroupId, Node2, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;
	 * and Node2 = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndNode2(long DataGroupId, String Node2, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_DATAGROUPID_2);

			if (Node2 == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_1);
			} else {
				if (Node2.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node2 != null) {
					builder.appendNamedParameterMap("Node2", Node2);
				}

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Node2 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndNode2_First(long DataGroupId, String Node2,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndNode2_First(DataGroupId, Node2, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Node2=");
		msg.append(Node2);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Node2 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndNode2_First(long DataGroupId, String Node2,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = findByDataGroupIdAndNode2(DataGroupId, Node2, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Node2 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndNode2_Last(long DataGroupId, String Node2,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndNode2_Last(DataGroupId, Node2, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Node2=");
		msg.append(Node2);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Node2 = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndNode2_Last(long DataGroupId, String Node2,
			OrderByComparator orderByComparator) throws SystemException {
		int count = countByDataGroupIdAndNode2(DataGroupId, Node2);

		List<DmDataitem> list = findByDataGroupIdAndNode2(DataGroupId, Node2, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63; and Node2 = &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupIdAndNode2_PrevAndNext(long Id, long DataGroupId, String Node2,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupIdAndNode2_PrevAndNext(dmDataItem, DataGroupId, Node2, orderByComparator, true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupIdAndNode2_PrevAndNext(dmDataItem, DataGroupId, Node2, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupIdAndNode2_PrevAndNext(DmDataitem dmDataItem, long DataGroupId, String Node2,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_DATAGROUPID_2);

		if (Node2 == null) {
			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_1);
		} else {
			if (Node2.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_3);
			} else {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_2);
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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		if (Node2 != null) {
			builder.appendNamedParameterMap("Node2", Node2);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63; and Level = &#63; and
	 * ShortName = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @param ShortName   the short name
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName)
			throws SystemException {
		return findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName, QueryUtil.ALL_POS,
				QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63; and Level
	 * = &#63; and ShortName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @param ShortName   the short name
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName,
			int start, int end) throws SystemException {
		return findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;
	 * and Level = &#63; and ShortName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName,
			int start, int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_DATAGROUPID_2);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_LEVEL_2);

			if (ShortName == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_1);
			} else {
				if (ShortName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				builder.appendNamedParameterMap("Level", Level);

				if (ShortName != null) {
					builder.appendNamedParameterMap("ShortName", ShortName);
				}

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63; and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndLevelandShortName_First(long DataGroupId, int Level, String ShortName,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndLevelandShortName_First(DataGroupId, Level, ShortName,
				orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Level=");
		msg.append(Level);

		msg.append(", ShortName=");
		msg.append(ShortName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63; and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndLevelandShortName_First(long DataGroupId, int Level, String ShortName,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63; and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndLevelandShortName_Last(long DataGroupId, int Level, String ShortName,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndLevelandShortName_Last(DataGroupId, Level, ShortName,
				orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Level=");
		msg.append(Level);

		msg.append(", ShortName=");
		msg.append(ShortName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63; and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndLevelandShortName_Last(long DataGroupId, int Level, String ShortName,
			OrderByComparator orderByComparator) throws SystemException {
		int count = countByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName);

		List<DmDataitem> list = findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63; and Level = &#63; and ShortName =
	 * &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupIdAndLevelandShortName_PrevAndNext(long Id, long DataGroupId, int Level,
			String ShortName, OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupIdAndLevelandShortName_PrevAndNext(dmDataItem, DataGroupId, Level, ShortName,
					orderByComparator, true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupIdAndLevelandShortName_PrevAndNext(dmDataItem, DataGroupId, Level, ShortName,
					orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupIdAndLevelandShortName_PrevAndNext(DmDataitem dmDataItem, long DataGroupId,
			int Level, String ShortName, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_DATAGROUPID_2);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_LEVEL_2);

		if (ShortName == null) {
			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_1);
		} else {
			if (ShortName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_3);
			} else {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_2);
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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		builder.appendNamedParameterMap("Level", Level);

		if (ShortName != null) {
			builder.appendNamedParameterMap("ShortName", ShortName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63; and Level = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevel(long DataGroupId, int Level) throws SystemException {
		return findByDataGroupIdAndLevel(DataGroupId, Level, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63; and Level
	 * = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevel(long DataGroupId, int Level, int start, int end)
			throws SystemException {
		return findByDataGroupIdAndLevel(DataGroupId, Level, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;
	 * and Level = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndLevel(long DataGroupId, int Level, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_DATAGROUPID_2);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_LEVEL_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				builder.appendNamedParameterMap("Level", Level);

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndLevel_First(long DataGroupId, int Level, OrderByComparator orderByComparator)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndLevel_First(DataGroupId, Level, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Level=");
		msg.append(Level);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndLevel_First(long DataGroupId, int Level, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmDataitem> list = findByDataGroupIdAndLevel(DataGroupId, Level, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndLevel_Last(long DataGroupId, int Level, OrderByComparator orderByComparator)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndLevel_Last(DataGroupId, Level, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", Level=");
		msg.append(Level);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and Level = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndLevel_Last(long DataGroupId, int Level, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByDataGroupIdAndLevel(DataGroupId, Level);

		List<DmDataitem> list = findByDataGroupIdAndLevel(DataGroupId, Level, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63; and Level = &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param Level             the level
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupIdAndLevel_PrevAndNext(long Id, long DataGroupId, int Level,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupIdAndLevel_PrevAndNext(dmDataItem, DataGroupId, Level, orderByComparator, true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupIdAndLevel_PrevAndNext(dmDataItem, DataGroupId, Level, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupIdAndLevel_PrevAndNext(DmDataitem dmDataItem, long DataGroupId, int Level,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_DATAGROUPID_2);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_LEVEL_2);

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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		builder.appendNamedParameterMap("Level", Level);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupId(long DataGroupId) throws SystemException {
		return findByDataGroupId(DataGroupId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupId(long DataGroupId, int start, int end) throws SystemException {
		return findByDataGroupId(DataGroupId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupId(long DataGroupId, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPID_DATAGROUPID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupId_First(long DataGroupId, OrderByComparator orderByComparator)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupId_First(DataGroupId, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupId_First(long DataGroupId, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmDataitem> list = findByDataGroupId(DataGroupId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupId_Last(long DataGroupId, OrderByComparator orderByComparator)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupId_Last(DataGroupId, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupId_Last(long DataGroupId, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByDataGroupId(DataGroupId);

		List<DmDataitem> list = findByDataGroupId(DataGroupId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupId_PrevAndNext(long Id, long DataGroupId, OrderByComparator orderByComparator)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupId_PrevAndNext(dmDataItem, DataGroupId, orderByComparator, true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupId_PrevAndNext(dmDataItem, DataGroupId, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupId_PrevAndNext(DmDataitem dmDataItem, long DataGroupId,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPID_DATAGROUPID_2);

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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm data items where DataGroupId = &#63; and ShortName =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param ShortName   the short name
	 * @return the matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndShortName(long DataGroupId, String ShortName) throws SystemException {
		return findByDataGroupIdAndShortName(DataGroupId, ShortName, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items where DataGroupId = &#63; and
	 * ShortName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId the data group ID
	 * @param ShortName   the short name
	 * @param start       the lower bound of the range of dm data items
	 * @param end         the upper bound of the range of dm data items (not
	 *                    inclusive)
	 * @return the range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndShortName(long DataGroupId, String ShortName, int start, int end)
			throws SystemException {
		return findByDataGroupIdAndShortName(DataGroupId, ShortName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items where DataGroupId = &#63;
	 * and ShortName = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findByDataGroupIdAndShortName(long DataGroupId, String ShortName, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_DATAGROUPID_2);

			if (ShortName == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_1);
			} else {
				if (ShortName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (ShortName != null) {
					builder.appendNamedParameterMap("ShortName", ShortName);
				}

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndShortName_First(long DataGroupId, String ShortName,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndShortName_First(DataGroupId, ShortName, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", ShortName=");
		msg.append(ShortName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the first dm data item in the ordered set where DataGroupId = &#63;
	 * and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm data item, or <code>null</code> if a matching
	 *         dm data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndShortName_First(long DataGroupId, String ShortName,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = findByDataGroupIdAndShortName(DataGroupId, ShortName, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByDataGroupIdAndShortName_Last(long DataGroupId, String ShortName,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByDataGroupIdAndShortName_Last(DataGroupId, ShortName, orderByComparator);

		if (dmDataItem != null) {
			return dmDataItem;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("DataGroupId=");
		msg.append(DataGroupId);

		msg.append(", ShortName=");
		msg.append(ShortName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDataItemException(msg.toString());
	}

	/**
	 * Returns the last dm data item in the ordered set where DataGroupId = &#63;
	 * and ShortName = &#63;.
	 *
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm data item, or <code>null</code> if a matching dm
	 *         data item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByDataGroupIdAndShortName_Last(long DataGroupId, String ShortName,
			OrderByComparator orderByComparator) throws SystemException {
		int count = countByDataGroupIdAndShortName(DataGroupId, ShortName);

		List<DmDataitem> list = findByDataGroupIdAndShortName(DataGroupId, ShortName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm data items before and after the current dm data item in the
	 * ordered set where DataGroupId = &#63; and ShortName = &#63;.
	 *
	 * @param Id                the primary key of the current dm data item
	 * @param DataGroupId       the data group ID
	 * @param ShortName         the short name
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a dm data item with
	 *                                                     the primary key could not
	 *                                                     be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem[] findByDataGroupIdAndShortName_PrevAndNext(long Id, long DataGroupId, String ShortName,
			OrderByComparator orderByComparator) throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByPrimaryKey(Id);

		try {

			DmDataitem[] array = new DmDataitem[3];

			array[0] = getByDataGroupIdAndShortName_PrevAndNext(dmDataItem, DataGroupId, ShortName, orderByComparator,
					true);

			array[1] = dmDataItem;

			array[2] = getByDataGroupIdAndShortName_PrevAndNext(dmDataItem, DataGroupId, ShortName, orderByComparator,
					false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmDataitem getByDataGroupIdAndShortName_PrevAndNext(DmDataitem dmDataItem, long DataGroupId,
			String ShortName, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDATAITEM_WHERE);

		query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_DATAGROUPID_2);

		if (ShortName == null) {
			query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_1);
		} else {
			if (ShortName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_3);
			} else {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_2);
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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		builder.appendNamedParameterMap("DataGroupId", DataGroupId);

		if (ShortName != null) {
			builder.appendNamedParameterMap("ShortName", ShortName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDataItem);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmDataitem> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and Node2 = &#63; or
	 * throws a {@link vn.gt.dao.danhmuc.NoSuchDmDataItemException} if it could not
	 * be found.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the matching dm data item
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDataItemException if a matching dm data
	 *                                                     item could not be found
	 * @throws SystemException                             if a system exception
	 *                                                     occurred
	 */
	public DmDataitem findByF_dataGroupId_node2(long DataGroupId, String Node2)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = fetchByF_dataGroupId_node2(DataGroupId, Node2);

		if (dmDataItem == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("DataGroupId=");
			msg.append(DataGroupId);

			msg.append(", Node2=");
			msg.append(Node2);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmDataItemException(msg.toString());
		}

		return dmDataItem;
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and Node2 = &#63; or
	 * returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the matching dm data item, or <code>null</code> if a matching dm data
	 *         item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByF_dataGroupId_node2(long DataGroupId, String Node2) throws SystemException {
		return fetchByF_dataGroupId_node2(DataGroupId, Node2, true);
	}

	/**
	 * Returns the dm data item where DataGroupId = &#63; and Node2 = &#63; or
	 * returns <code>null</code> if it could not be found, optionally using the
	 * finder cache.
	 *
	 * @param DataGroupId       the data group ID
	 * @param Node2             the node2
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm data item, or <code>null</code> if a matching dm data
	 *         item could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem fetchByF_dataGroupId_node2(long DataGroupId, String Node2, boolean retrieveFromCache)
			throws SystemException {
		DmDataitem dmDataItem = null;
		if (dmDataItem == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_DATAGROUPID_2);

			if (Node2 == null) {
				query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_1);
			} else {
				if (Node2.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_3);
				} else {
					query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_2);
				}
			}

			query.append(DmDataItemModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmDataitem.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node2 != null) {
					builder.appendNamedParameterMap("Node2", Node2);
				}

				dmDataItem = (DmDataitem) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return dmDataItem;
	}

	/**
	 * Returns all the dm data items.
	 *
	 * @return the dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm data items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm data items
	 * @param end   the upper bound of the range of dm data items (not inclusive)
	 * @return the range of dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm data items.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start             the lower bound of the range of dm data items
	 * @param end               the upper bound of the range of dm data items (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDataitem> findAll(int start, int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmDataitem> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMDATAITEM);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_DMDATAITEM.concat(DmDataItemModelImpl.ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmDataitem.class).build();

				list = (List<DmDataitem>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes the dm data item where DataGroupId = &#63; and code = &#63; from the
	 * database.
	 *
	 * @param DataGroupId the data group ID
	 * @param code       the code0
	 * @return the dm data item that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem removeByDataGroupIdAndCode0(long DataGroupId, String code)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByDataGroupIdAndCode0(DataGroupId, code);

		repository.delete(dmDataItem);
		return dmDataItem;
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; and Node1 = &#63;
	 * from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node1       the node1
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupIdAndNode1(long DataGroupId, String Node1) throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupIdAndNode1(DataGroupId, Node1)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; and Node2 = &#63;
	 * from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupIdAndNode2(long DataGroupId, String Node2) throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupIdAndNode2(DataGroupId, Node2)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; and Level = &#63; and
	 * ShortName = &#63; from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @param ShortName   the short name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName)
			throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupIdAndLevelandShortName(DataGroupId, Level, ShortName)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; and Level = &#63;
	 * from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupIdAndLevel(long DataGroupId, int Level) throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupIdAndLevel(DataGroupId, Level)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupId(long DataGroupId) throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupId(DataGroupId)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes all the dm data items where DataGroupId = &#63; and ShortName = &#63;
	 * from the database.
	 *
	 * @param DataGroupId the data group ID
	 * @param ShortName   the short name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDataGroupIdAndShortName(long DataGroupId, String ShortName) throws SystemException {
		for (DmDataitem dmDataItem : findByDataGroupIdAndShortName(DataGroupId, ShortName)) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Removes the dm data item where DataGroupId = &#63; and Node2 = &#63; from the
	 * database.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the dm data item that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmDataitem removeByF_dataGroupId_node2(long DataGroupId, String Node2)
			throws NoSuchDmDataItemException, SystemException {
		DmDataitem dmDataItem = findByF_dataGroupId_node2(DataGroupId, Node2);

		repository.delete(dmDataItem);
		return dmDataItem;
	}

	/**
	 * Removes all the dm data items from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmDataitem dmDataItem : findAll()) {
			repository.delete(dmDataItem);
		}
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and code =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param code       the code0
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndCode0(long DataGroupId, String code) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_DATAGROUPID_2);

			if (code == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_1);
			} else {
				if (code.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (code != null) {
					builder.appendNamedParameterMap("code", code);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and Node1 =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node1       the node1
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndNode1(long DataGroupId, String Node1) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_DATAGROUPID_2);

			if (Node1 == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_1);
			} else {
				if (Node1.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node1 != null) {
					builder.appendNamedParameterMap("Node1", Node1);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and Node2 =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndNode2(long DataGroupId, String Node2) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_DATAGROUPID_2);

			if (Node2 == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_1);
			} else {
				if (Node2.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node2 != null) {
					builder.appendNamedParameterMap("Node2", Node2);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and Level =
	 * &#63; and ShortName = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @param ShortName   the short name
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndLevelandShortName(long DataGroupId, int Level, String ShortName)
			throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_DATAGROUPID_2);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_LEVEL_2);

			if (ShortName == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_1);
			} else {
				if (ShortName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				builder.appendNamedParameterMap("Level", Level);

				if (ShortName != null) {
					builder.appendNamedParameterMap("ShortName", ShortName);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and Level =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Level       the level
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndLevel(long DataGroupId, int Level) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_DATAGROUPID_2);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDLEVEL_LEVEL_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				builder.appendNamedParameterMap("Level", Level);

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupId(long DataGroupId) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPID_DATAGROUPID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and ShortName =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param ShortName   the short name
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDataGroupIdAndShortName(long DataGroupId, String ShortName) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_DATAGROUPID_2);

			if (ShortName == null) {
				query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_1);
			} else {
				if (ShortName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_3);
				} else {
					query.append(_FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (ShortName != null) {
					builder.appendNamedParameterMap("ShortName", ShortName);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items where DataGroupId = &#63; and Node2 =
	 * &#63;.
	 *
	 * @param DataGroupId the data group ID
	 * @param Node2       the node2
	 * @return the number of matching dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_dataGroupId_node2(long DataGroupId, String Node2) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMDATAITEM_WHERE);

			query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_DATAGROUPID_2);

			if (Node2 == null) {
				query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_1);
			} else {
				if (Node2.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_3);
				} else {
					query.append(_FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("DataGroupId", DataGroupId);

				if (Node2 != null) {
					builder.appendNamedParameterMap("Node2", Node2);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of dm data items.
	 *
	 * @return the number of dm data items
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMDATAITEM)
						.build();

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the dm data item persistence.
	 */
	private static final String _SQL_SELECT_DMDATAITEM = "SELECT dmDataItem FROM DmDataitem dmDataItem";
	private static final String _SQL_SELECT_DMDATAITEM_WHERE = "SELECT dmDataItem FROM DmDataitem dmDataItem WHERE ";
	private static final String _SQL_COUNT_DMDATAITEM = "SELECT COUNT(dmDataItem) FROM DmDataitem dmDataItem";
	private static final String _SQL_COUNT_DMDATAITEM_WHERE = "SELECT COUNT(dmDataItem) FROM DmDataitem dmDataItem WHERE ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDCODE0_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_1 = "dmDataItem.code IS NULL";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_2 = "dmDataItem.code =:code";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDCODE0_CODE0_3 = "(dmDataItem.code IS NULL OR dmDataItem.code =:code)";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE1_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_1 = "dmDataItem.Node1 IS NULL";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_2 = "dmDataItem.Node1 =:Node1";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE1_NODE1_3 = "(dmDataItem.Node1 IS NULL OR dmDataItem.Node1 =:Node1)";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE2_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_1 = "dmDataItem.Node2 IS NULL";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_2 = "dmDataItem.Node2 =:Node2";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDNODE2_NODE2_3 = "(dmDataItem.Node2 IS NULL OR dmDataItem.Node2 =:Node2)";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_LEVEL_2 = "dmDataItem.Level =:Level AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_1 = "dmDataItem.ShortName IS NULL";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_2 = "dmDataItem.ShortName =:ShortName";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVELANDSHORTNAME_SHORTNAME_3 = "(dmDataItem.ShortName IS NULL OR dmDataItem.ShortName =:ShortName)";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVEL_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDLEVEL_LEVEL_2 = "dmDataItem.Level =:Level";
	private static final String _FINDER_COLUMN_DATAGROUPID_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_1 = "dmDataItem.ShortName IS NULL";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_2 = "dmDataItem.ShortName =:ShortName";
	private static final String _FINDER_COLUMN_DATAGROUPIDANDSHORTNAME_SHORTNAME_3 = "(dmDataItem.ShortName IS NULL OR dmDataItem.ShortName =:ShortName)";
	private static final String _FINDER_COLUMN_F_DATAGROUPID_NODE2_DATAGROUPID_2 = "dmDataItem.datagroupid =:DataGroupId AND ";
	private static final String _FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_1 = "dmDataItem.Node2 IS NULL";
	private static final String _FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_2 = "dmDataItem.Node2 =:Node2";
	private static final String _FINDER_COLUMN_F_DATAGROUPID_NODE2_NODE2_3 = "(dmDataItem.Node2 IS NULL OR dmDataItem.Node2 =:Node2)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmDataItem.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmDataitem exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmDataitem exists with the key {";
}
