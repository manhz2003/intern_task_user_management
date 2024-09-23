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

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmGtRouteConfig;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtRouteConfigRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtRouteConfigModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtRouteConfigPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtRouteConfigRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtRouteConfig> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtRouteConfigUtil} to access the dm gt route config persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtRouteConfig create(long id) {
		DmGtRouteConfig dmGtRouteConfig = new DmGtRouteConfig();

		
		//dmGtRouteConfig.setPrimaryKey(id);

		return dmGtRouteConfig;
	}

	/**
	 * Removes the dm gt route config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt route config
	 * @return the dm gt route config that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig remove(long id)
		throws NoSuchDmGtRouteConfigException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm gt route config with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt route config
	 * @return the dm gt route config that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtRouteConfig remove(Serializable primaryKey)
		throws NoSuchDmGtRouteConfigException, SystemException {
		

		try {
			

			DmGtRouteConfig dmGtRouteConfig = findByPrimaryKey(primaryKey);

			if (dmGtRouteConfig == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtRouteConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtRouteConfig);
			return dmGtRouteConfig;
		}
		catch (NoSuchDmGtRouteConfigException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtRouteConfig remove(DmGtRouteConfig DmGtRouteConfig) throws SystemException {
	removeImpl(DmGtRouteConfig);
	return DmGtRouteConfig;
}

protected DmGtRouteConfig removeImpl(DmGtRouteConfig dmGtRouteConfig)
		throws SystemException {
		try {
			repository.delete(dmGtRouteConfig);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtRouteConfig;
	}

	
	public DmGtRouteConfig updateImpl(
		DmGtRouteConfig dmGtRouteConfig, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmGtRouteConfig);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtRouteConfig;
	}

	
	public DmGtRouteConfig findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt route config with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt route config
	 * @return the dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByPrimaryKey(long id)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByPrimaryKey(id);

		if (dmGtRouteConfig == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtRouteConfigException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtRouteConfig;
	}

	/**
	 * Returns the dm gt route config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt route config
	 * @return the dm gt route config, or <code>null</code> if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtRouteConfig fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt route config with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt route config
	 * @return the dm gt route config, or <code>null</code> if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByPrimaryKey(long id) throws SystemException {
		DmGtRouteConfig dmGtRouteConfig = null;

		

		if (dmGtRouteConfig == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtRouteConfig> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtRouteConfig = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtRouteConfig;
	}

	/**
	 * Returns the dm gt route config where routeCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException} if it could not be found.
	 *
	 * @param routeCode the route code
	 * @return the matching dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByRouteCode(String routeCode)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByRouteCode(routeCode);

		if (dmGtRouteConfig == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("routeCode=");
			msg.append(routeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtRouteConfigException(msg.toString());
		}

		return dmGtRouteConfig;
	}

	/**
	 * Returns the dm gt route config where routeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param routeCode the route code
	 * @return the matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByRouteCode(String routeCode)
		throws SystemException {
		return fetchByRouteCode(routeCode, true);
	}

	/**
	 * Returns the dm gt route config where routeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param routeCode the route code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByRouteCode(String routeCode,
		boolean retrieveFromCache) throws SystemException {
		DmGtRouteConfig dmGtRouteConfig = null;
		if (dmGtRouteConfig == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMGTROUTECONFIG_WHERE);

			if (routeCode == null) {
				query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_1);
			}
			else {
				if (routeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_2);
				}
			}

			query.append(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtRouteConfig.class).build();

				

				if (routeCode != null) {
					builder.appendNamedParameterMap("routeCode", routeCode);
				}

				dmGtRouteConfig = (DmGtRouteConfig) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGtRouteConfig;
	}

	/**
	 * Returns all the dm gt route configs where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @return the matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByIsDelete(int isDelete)
		throws SystemException {
		return findByIsDelete(isDelete, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm gt route configs where isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @return the range of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByIsDelete(int isDelete, int start, int end)
		throws SystemException {
		return findByIsDelete(isDelete, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt route configs where isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByIsDelete(int isDelete, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmGtRouteConfig> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGTROUTECONFIG_WHERE);

			query.append(_FINDER_COLUMN_ISDELETE_ISDELETE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

				list = (List<DmGtRouteConfig>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt route config in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByIsDelete_First(int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByIsDelete_First(isDelete,
				orderByComparator);

		if (dmGtRouteConfig != null) {
			return dmGtRouteConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtRouteConfigException(msg.toString());
	}

	/**
	 * Returns the first dm gt route config in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByIsDelete_First(int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtRouteConfig> list = findByIsDelete(isDelete, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt route config in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByIsDelete_Last(int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByIsDelete_Last(isDelete,
				orderByComparator);

		if (dmGtRouteConfig != null) {
			return dmGtRouteConfig;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtRouteConfigException(msg.toString());
	}

	/**
	 * Returns the last dm gt route config in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByIsDelete_Last(int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByIsDelete(isDelete);

		List<DmGtRouteConfig> list = findByIsDelete(isDelete, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt route configs before and after the current dm gt route config in the ordered set where isDelete = &#63;.
	 *
	 * @param id the primary key of the current dm gt route config
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig[] findByIsDelete_PrevAndNext(long id, int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = findByPrimaryKey(id);

		

		try {
			

			DmGtRouteConfig[] array = new DmGtRouteConfig[3];

			array[0] = getByIsDelete_PrevAndNext(dmGtRouteConfig,
					isDelete, orderByComparator, true);

			array[1] = dmGtRouteConfig;

			array[2] = getByIsDelete_PrevAndNext(dmGtRouteConfig,
					isDelete, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtRouteConfig getByIsDelete_PrevAndNext(
		DmGtRouteConfig dmGtRouteConfig, int isDelete,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTROUTECONFIG_WHERE);

		query.append(_FINDER_COLUMN_ISDELETE_ISDELETE_2);

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
			query.append(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("isDelete", isDelete);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtRouteConfig);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtRouteConfig> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt route configs where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @return the matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByLocCode(String locCode, int isDelete)
		throws SystemException {
		return findByLocCode(locCode, isDelete, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt route configs where locCode = &#63; and isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @return the range of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByLocCode(String locCode, int isDelete,
		int start, int end) throws SystemException {
		return findByLocCode(locCode, isDelete, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt route configs where locCode = &#63; and isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findByLocCode(String locCode, int isDelete,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGtRouteConfig> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMGTROUTECONFIG_WHERE);

			if (locCode == null) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
			}
			else {
				if (locCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_LOCCODE_ISDELETE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (locCode != null) {
					builder.appendNamedParameterMap("locCode", locCode);
				}

				builder.appendNamedParameterMap("isDelete", isDelete);

				list = (List<DmGtRouteConfig>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt route config in the ordered set where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByLocCode_First(String locCode, int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByLocCode_First(locCode,
				isDelete, orderByComparator);

		if (dmGtRouteConfig != null) {
			return dmGtRouteConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(", isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtRouteConfigException(msg.toString());
	}

	/**
	 * Returns the first dm gt route config in the ordered set where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByLocCode_First(String locCode, int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtRouteConfig> list = findByLocCode(locCode, isDelete, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt route config in the ordered set where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig findByLocCode_Last(String locCode, int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = fetchByLocCode_Last(locCode,
				isDelete, orderByComparator);

		if (dmGtRouteConfig != null) {
			return dmGtRouteConfig;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(", isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtRouteConfigException(msg.toString());
	}

	/**
	 * Returns the last dm gt route config in the ordered set where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt route config, or <code>null</code> if a matching dm gt route config could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig fetchByLocCode_Last(String locCode, int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLocCode(locCode, isDelete);

		List<DmGtRouteConfig> list = findByLocCode(locCode, isDelete,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt route configs before and after the current dm gt route config in the ordered set where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param id the primary key of the current dm gt route config
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt route config
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtRouteConfigException if a dm gt route config with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig[] findByLocCode_PrevAndNext(long id, String locCode,
		int isDelete, OrderByComparator orderByComparator)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = findByPrimaryKey(id);

		

		try {
			

			DmGtRouteConfig[] array = new DmGtRouteConfig[3];

			array[0] = getByLocCode_PrevAndNext(dmGtRouteConfig,
					locCode, isDelete, orderByComparator, true);

			array[1] = dmGtRouteConfig;

			array[2] = getByLocCode_PrevAndNext(dmGtRouteConfig,
					locCode, isDelete, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtRouteConfig getByLocCode_PrevAndNext(
		DmGtRouteConfig dmGtRouteConfig, String locCode, int isDelete,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTROUTECONFIG_WHERE);

		if (locCode == null) {
			query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
		}
		else {
			if (locCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
			}
		}

		query.append(_FINDER_COLUMN_LOCCODE_ISDELETE_2);

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
			query.append(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (locCode != null) {
			builder.appendNamedParameterMap("locCode", locCode);
		}

		builder.appendNamedParameterMap("isDelete", isDelete);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtRouteConfig);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtRouteConfig> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt route configs.
	 *
	 * @return the dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt route configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @return the range of dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt route configs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt route configs
	 * @param end the upper bound of the range of dm gt route configs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtRouteConfig> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtRouteConfig> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTROUTECONFIG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTROUTECONFIG.concat(DmGtRouteConfigModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtRouteConfig>) queryFactory.getResultList(builder);
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
	 * Removes the dm gt route config where routeCode = &#63; from the database.
	 *
	 * @param routeCode the route code
	 * @return the dm gt route config that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtRouteConfig removeByRouteCode(String routeCode)
		throws NoSuchDmGtRouteConfigException, SystemException {
		DmGtRouteConfig dmGtRouteConfig = findByRouteCode(routeCode);

		repository.delete(dmGtRouteConfig);
			return dmGtRouteConfig;
	}

	/**
	 * Removes all the dm gt route configs where isDelete = &#63; from the database.
	 *
	 * @param isDelete the is delete
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByIsDelete(int isDelete) throws SystemException {
		for (DmGtRouteConfig dmGtRouteConfig : findByIsDelete(isDelete)) {
			repository.delete(dmGtRouteConfig);
		}
	}

	/**
	 * Removes all the dm gt route configs where locCode = &#63; and isDelete = &#63; from the database.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLocCode(String locCode, int isDelete)
		throws SystemException {
		for (DmGtRouteConfig dmGtRouteConfig : findByLocCode(locCode, isDelete)) {
			repository.delete(dmGtRouteConfig);
		}
	}

	/**
	 * Removes all the dm gt route configs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtRouteConfig dmGtRouteConfig : findAll()) {
			repository.delete(dmGtRouteConfig);
		}
	}

	/**
	 * Returns the number of dm gt route configs where routeCode = &#63;.
	 *
	 * @param routeCode the route code
	 * @return the number of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRouteCode(String routeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTROUTECONFIG_WHERE);

			if (routeCode == null) {
				query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_1);
			}
			else {
				if (routeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ROUTECODE_ROUTECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (routeCode != null) {
					builder.appendNamedParameterMap("routeCode", routeCode);
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
	 * Returns the number of dm gt route configs where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @return the number of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByIsDelete(int isDelete) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTROUTECONFIG_WHERE);

			query.append(_FINDER_COLUMN_ISDELETE_ISDELETE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

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
	 * Returns the number of dm gt route configs where locCode = &#63; and isDelete = &#63;.
	 *
	 * @param locCode the loc code
	 * @param isDelete the is delete
	 * @return the number of matching dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLocCode(String locCode, int isDelete)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMGTROUTECONFIG_WHERE);

			if (locCode == null) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
			}
			else {
				if (locCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_LOCCODE_ISDELETE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (locCode != null) {
					builder.appendNamedParameterMap("locCode", locCode);
				}

				builder.appendNamedParameterMap("isDelete", isDelete);

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
	 * Returns the number of dm gt route configs.
	 *
	 * @return the number of dm gt route configs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTROUTECONFIG).build();

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
	 * Initializes the dm gt route config persistence.
	 */
	private static final String _SQL_SELECT_DMGTROUTECONFIG = "SELECT dmGtRouteConfig FROM DmGtRouteConfig dmGtRouteConfig";
	private static final String _SQL_SELECT_DMGTROUTECONFIG_WHERE = "SELECT dmGtRouteConfig FROM DmGtRouteConfig dmGtRouteConfig WHERE ";
	private static final String _SQL_COUNT_DMGTROUTECONFIG = "SELECT COUNT(dmGtRouteConfig) FROM DmGtRouteConfig dmGtRouteConfig";
	private static final String _SQL_COUNT_DMGTROUTECONFIG_WHERE = "SELECT COUNT(dmGtRouteConfig) FROM DmGtRouteConfig dmGtRouteConfig WHERE ";
	private static final String _FINDER_COLUMN_ROUTECODE_ROUTECODE_1 = "dmGtRouteConfig.routeCode IS NULL";
	private static final String _FINDER_COLUMN_ROUTECODE_ROUTECODE_2 = "dmGtRouteConfig.routeCode =:routeCode";
	private static final String _FINDER_COLUMN_ROUTECODE_ROUTECODE_3 = "(dmGtRouteConfig.routeCode IS NULL OR dmGtRouteConfig.routeCode =:routeCode)";
	private static final String _FINDER_COLUMN_ISDELETE_ISDELETE_2 = "dmGtRouteConfig.isDelete =:isDelete";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_1 = "dmGtRouteConfig.locCode IS NULL AND ";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_2 = "dmGtRouteConfig.locCode =:locCode AND ";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_3 = "(dmGtRouteConfig.locCode IS NULL OR dmGtRouteConfig.locCode =:locCode) AND ";
	private static final String _FINDER_COLUMN_LOCCODE_ISDELETE_2 = "dmGtRouteConfig.isDelete =:isDelete";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtRouteConfig.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtRouteConfig exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtRouteConfig exists with the key {";
	

	
}
