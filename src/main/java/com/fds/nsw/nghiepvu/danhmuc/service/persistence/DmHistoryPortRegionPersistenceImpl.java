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
import com.fds.nsw.nghiepvu.model.DmHistoryPortRegion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPortRegionRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPortRegionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPortRegionPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPortRegionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPortRegion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPortRegionUtil} to access the dm history port region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPortRegion create(int id) {
		DmHistoryPortRegion dmHistoryPortRegion = new DmHistoryPortRegion();

		
		//dmHistoryPortRegion.setPrimaryKey(id);

		return dmHistoryPortRegion;
	}

	/**
	 * Removes the dm history port region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history port region
	 * @return the dm history port region that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion remove(int id)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history port region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history port region
	 * @return the dm history port region that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortRegion remove(Serializable primaryKey)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		

		try {
			

			DmHistoryPortRegion dmHistoryPortRegion = findByPrimaryKey(primaryKey);

			if (dmHistoryPortRegion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPortRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPortRegion);
			return dmHistoryPortRegion;
		}
		catch (NoSuchDmHistoryPortRegionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPortRegion remove(DmHistoryPortRegion DmHistoryPortRegion) throws SystemException {
	removeImpl(DmHistoryPortRegion);	return DmHistoryPortRegion;
}

protected DmHistoryPortRegion removeImpl

(
		DmHistoryPortRegion dmHistoryPortRegion) throws SystemException {
		try {
			repository.delete(dmHistoryPortRegion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortRegion;
	}

	
	public DmHistoryPortRegion updateImpl(
		DmHistoryPortRegion dmHistoryPortRegion,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPortRegion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortRegion;
	}

	
	public DmHistoryPortRegion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port region with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException} if it could not be found.
	 *
	 * @param id the primary key of the dm history port region
	 * @return the dm history port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion findByPrimaryKey(int id)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = fetchByPrimaryKey(id);

		if (dmHistoryPortRegion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryPortRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryPortRegion;
	}

	/**
	 * Returns the dm history port region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history port region
	 * @return the dm history port region, or <code>null</code> if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortRegion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history port region
	 * @return the dm history port region, or <code>null</code> if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = null;

		

		if (dmHistoryPortRegion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPortRegion> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryPortRegion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPortRegion;
	}

	/**
	 * Returns all the dm history port regions where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the matching dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findByPortRegionCode(String portRegionCode)
		throws SystemException {
		return findByPortRegionCode(portRegionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port regions where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm history port regions
	 * @param end the upper bound of the range of dm history port regions (not inclusive)
	 * @return the range of matching dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findByPortRegionCode(
		String portRegionCode, int start, int end) throws SystemException {
		return findByPortRegionCode(portRegionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port regions where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm history port regions
	 * @param end the upper bound of the range of dm history port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findByPortRegionCode(
		String portRegionCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPORTREGION_WHERE);

			if (portRegionCode == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1);
			}
			else {
				if (portRegionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryPortRegionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				list = (List<DmHistoryPortRegion>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion findByPortRegionCode_First(
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = fetchByPortRegionCode_First(portRegionCode,
				orderByComparator);

		if (dmHistoryPortRegion != null) {
			return dmHistoryPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortRegionException(msg.toString());
	}

	/**
	 * Returns the first dm history port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port region, or <code>null</code> if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion fetchByPortRegionCode_First(
		String portRegionCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryPortRegion> list = findByPortRegionCode(portRegionCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion findByPortRegionCode_Last(
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = fetchByPortRegionCode_Last(portRegionCode,
				orderByComparator);

		if (dmHistoryPortRegion != null) {
			return dmHistoryPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortRegionException(msg.toString());
	}

	/**
	 * Returns the last dm history port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port region, or <code>null</code> if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion fetchByPortRegionCode_Last(
		String portRegionCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPortRegionCode(portRegionCode);

		List<DmHistoryPortRegion> list = findByPortRegionCode(portRegionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history port regions before and after the current dm history port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param id the primary key of the current dm history port region
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a dm history port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion[] findByPortRegionCode_PrevAndNext(int id,
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPortRegion[] array = new DmHistoryPortRegion[3];

			array[0] = getByPortRegionCode_PrevAndNext(
					dmHistoryPortRegion, portRegionCode, orderByComparator, true);

			array[1] = dmHistoryPortRegion;

			array[2] = getByPortRegionCode_PrevAndNext(
					dmHistoryPortRegion, portRegionCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPortRegion getByPortRegionCode_PrevAndNext(
		 DmHistoryPortRegion dmHistoryPortRegion,
		String portRegionCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYPORTREGION_WHERE);

		if (portRegionCode == null) {
			query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1);
		}
		else {
			if (portRegionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2);
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
			query.append(DmHistoryPortRegionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionCode != null) {
			builder.appendNamedParameterMap("portRegionCode", portRegionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPortRegion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPortRegion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history port region where portRegionCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException} if it could not be found.
	 *
	 * @param portRegionCode the port region code
	 * @param syncVersion the sync version
	 * @return the matching dm history port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortRegionException if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion findByPortRegionCodeAndSyncVersion(
		String portRegionCode, String syncVersion)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = fetchByPortRegionCodeAndSyncVersion(portRegionCode,
				syncVersion);

		if (dmHistoryPortRegion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portRegionCode=");
			msg.append(portRegionCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPortRegionException(msg.toString());
		}

		return dmHistoryPortRegion;
	}

	/**
	 * Returns the dm history port region where portRegionCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portRegionCode the port region code
	 * @param syncVersion the sync version
	 * @return the matching dm history port region, or <code>null</code> if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion fetchByPortRegionCodeAndSyncVersion(
		String portRegionCode, String syncVersion) throws SystemException {
		return fetchByPortRegionCodeAndSyncVersion(portRegionCode, syncVersion,
			true);
	}

	/**
	 * Returns the dm history port region where portRegionCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portRegionCode the port region code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history port region, or <code>null</code> if a matching dm history port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion fetchByPortRegionCodeAndSyncVersion(
		String portRegionCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = null;
		if (dmHistoryPortRegion == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPORTREGION_WHERE);

			if (portRegionCode == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_1);
			}
			else {
				if (portRegionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPortRegionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPortRegion.class).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryPortRegion = (DmHistoryPortRegion) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPortRegion;
	}

	/**
	 * Returns all the dm history port regions.
	 *
	 * @return the dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port regions
	 * @param end the upper bound of the range of dm history port regions (not inclusive)
	 * @return the range of dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port regions
	 * @param end the upper bound of the range of dm history port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortRegion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPORTREGION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPORTREGION.concat(DmHistoryPortRegionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPortRegion>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history port regions where portRegionCode = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionCode(String portRegionCode)
		throws SystemException {
		for (DmHistoryPortRegion dmHistoryPortRegion : findByPortRegionCode(
				portRegionCode)) {
			repository.delete(dmHistoryPortRegion);
		}
	}

	/**
	 * Removes the dm history port region where portRegionCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @param syncVersion the sync version
	 * @return the dm history port region that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortRegion removeByPortRegionCodeAndSyncVersion(
		String portRegionCode, String syncVersion)
		throws NoSuchDmHistoryPortRegionException, SystemException {
		DmHistoryPortRegion dmHistoryPortRegion = findByPortRegionCodeAndSyncVersion(portRegionCode,
				syncVersion);

		repository.delete(dmHistoryPortRegion);
			return dmHistoryPortRegion;
	}

	/**
	 * Removes all the dm history port regions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPortRegion dmHistoryPortRegion : findAll()) {
			repository.delete(dmHistoryPortRegion);
		}
	}

	/**
	 * Returns the number of dm history port regions where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the number of matching dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCode(String portRegionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPORTREGION_WHERE);

			if (portRegionCode == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1);
			}
			else {
				if (portRegionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
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
	 * Returns the number of dm history port regions where portRegionCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCodeAndSyncVersion(String portRegionCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPORTREGION_WHERE);

			if (portRegionCode == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_1);
			}
			else {
				if (portRegionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
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
	 * Returns the number of dm history port regions.
	 *
	 * @return the number of dm history port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPORTREGION).build();

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
	 * Initializes the dm history port region persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPORTREGION = "SELECT dmHistoryPortRegion FROM DmHistoryPortRegion dmHistoryPortRegion";
	private static final String _SQL_SELECT_DMHISTORYPORTREGION_WHERE = "SELECT dmHistoryPortRegion FROM DmHistoryPortRegion dmHistoryPortRegion WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPORTREGION = "SELECT COUNT(dmHistoryPortRegion) FROM DmHistoryPortRegion dmHistoryPortRegion";
	private static final String _SQL_COUNT_DMHISTORYPORTREGION_WHERE = "SELECT COUNT(dmHistoryPortRegion) FROM DmHistoryPortRegion dmHistoryPortRegion WHERE ";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1 = "dmHistoryPortRegion.portRegionCode IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2 = "dmHistoryPortRegion.portRegionCode =:portRegionCode";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3 = "(dmHistoryPortRegion.portRegionCode IS NULL OR dmHistoryPortRegion.portRegionCode =:portRegionCode)";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_1 =
		"dmHistoryPortRegion.portRegionCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_2 =
		"dmHistoryPortRegion.portRegionCode =:portRegionCode AND ";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_PORTREGIONCODE_3 =
		"(dmHistoryPortRegion.portRegionCode IS NULL OR dmHistoryPortRegion.portRegionCode =:portRegionCode) AND ";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPortRegion.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPortRegion.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PORTREGIONCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPortRegion.syncVersion IS NULL OR dmHistoryPortRegion.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPortRegion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPortRegion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPortRegion exists with the key {";
	

	
}
