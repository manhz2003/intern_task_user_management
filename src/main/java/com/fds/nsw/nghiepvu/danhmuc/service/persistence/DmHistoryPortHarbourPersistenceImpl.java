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
import com.fds.nsw.nghiepvu.model.DmHistoryPortHarbour;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPortHarbourRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPortHarbourModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPortHarbourPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPortHarbourRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPortHarbour> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPortHarbourUtil} to access the dm history port harbour persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPortHarbour create(int id) {
		DmHistoryPortHarbour dmHistoryPortHarbour = new DmHistoryPortHarbour();

		
		//dmHistoryPortHarbour.setPrimaryKey(id);

		return dmHistoryPortHarbour;
	}

	/**
	 * Removes the dm history port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history port harbour
	 * @return the dm history port harbour that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour remove(int id)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history port harbour
	 * @return the dm history port harbour that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortHarbour remove(Serializable primaryKey)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		

		try {
			

			DmHistoryPortHarbour dmHistoryPortHarbour = findByPrimaryKey(primaryKey);

			if (dmHistoryPortHarbour == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPortHarbourException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPortHarbour);
			return dmHistoryPortHarbour;
		}
		catch (NoSuchDmHistoryPortHarbourException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPortHarbour remove(DmHistoryPortHarbour DmHistoryPortHarbour) throws SystemException {
	removeImpl(DmHistoryPortHarbour);	return DmHistoryPortHarbour;
}

protected DmHistoryPortHarbour removeImpl

(
		DmHistoryPortHarbour dmHistoryPortHarbour) throws SystemException {
		try {
			repository.delete(dmHistoryPortHarbour);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortHarbour;
	}

	
	public DmHistoryPortHarbour updateImpl(
		DmHistoryPortHarbour dmHistoryPortHarbour,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPortHarbour);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortHarbour;
	}

	
	public DmHistoryPortHarbour findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port harbour with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException} if it could not be found.
	 *
	 * @param id the primary key of the dm history port harbour
	 * @return the dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPrimaryKey(int id)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPrimaryKey(id);

		if (dmHistoryPortHarbour == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryPortHarbourException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryPortHarbour;
	}

	/**
	 * Returns the dm history port harbour with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history port harbour
	 * @return the dm history port harbour, or <code>null</code> if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortHarbour fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port harbour with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history port harbour
	 * @return the dm history port harbour, or <code>null</code> if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = null;

		

		if (dmHistoryPortHarbour == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPortHarbour> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryPortHarbour = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPortHarbour;
	}

	/**
	 * Returns all the dm history port harbours where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @return the matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortHarbourCode(
		String portHarbourCode) throws SystemException {
		return findByPortHarbourCode(portHarbourCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port harbours where portHarbourCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portHarbourCode the port harbour code
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @return the range of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortHarbourCode(
		String portHarbourCode, int start, int end) throws SystemException {
		return findByPortHarbourCode(portHarbourCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port harbours where portHarbourCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portHarbourCode the port harbour code
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortHarbourCode(
		String portHarbourCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
				}

				list = (List<DmHistoryPortHarbour>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPortHarbourCode_First(
		String portHarbourCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPortHarbourCode_First(portHarbourCode,
				orderByComparator);

		if (dmHistoryPortHarbour != null) {
			return dmHistoryPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portHarbourCode=");
		msg.append(portHarbourCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortHarbourException(msg.toString());
	}

	/**
	 * Returns the first dm history port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortHarbourCode_First(
		String portHarbourCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryPortHarbour> list = findByPortHarbourCode(portHarbourCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPortHarbourCode_Last(
		String portHarbourCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPortHarbourCode_Last(portHarbourCode,
				orderByComparator);

		if (dmHistoryPortHarbour != null) {
			return dmHistoryPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portHarbourCode=");
		msg.append(portHarbourCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortHarbourException(msg.toString());
	}

	/**
	 * Returns the last dm history port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortHarbourCode_Last(
		String portHarbourCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPortHarbourCode(portHarbourCode);

		List<DmHistoryPortHarbour> list = findByPortHarbourCode(portHarbourCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history port harbours before and after the current dm history port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param id the primary key of the current dm history port harbour
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour[] findByPortHarbourCode_PrevAndNext(int id,
		String portHarbourCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPortHarbour[] array = new DmHistoryPortHarbour[3];

			array[0] = getByPortHarbourCode_PrevAndNext(
					dmHistoryPortHarbour, portHarbourCode, orderByComparator,
					true);

			array[1] = dmHistoryPortHarbour;

			array[2] = getByPortHarbourCode_PrevAndNext(
					dmHistoryPortHarbour, portHarbourCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPortHarbour getByPortHarbourCode_PrevAndNext(
		 DmHistoryPortHarbour dmHistoryPortHarbour,
		String portHarbourCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE);

		if (portHarbourCode == null) {
			query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
		}
		else {
			if (portHarbourCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
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
			query.append(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portHarbourCode != null) {
			builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPortHarbour);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPortHarbour> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history port harbour where portHarbourCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException} if it could not be found.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param syncVersion the sync version
	 * @return the matching dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPortHarbourCodeAndSyncVersion(
		String portHarbourCode, String syncVersion)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPortHarbourCodeAndSyncVersion(portHarbourCode,
				syncVersion);

		if (dmHistoryPortHarbour == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portHarbourCode=");
			msg.append(portHarbourCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPortHarbourException(msg.toString());
		}

		return dmHistoryPortHarbour;
	}

	/**
	 * Returns the dm history port harbour where portHarbourCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param syncVersion the sync version
	 * @return the matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortHarbourCodeAndSyncVersion(
		String portHarbourCode, String syncVersion) throws SystemException {
		return fetchByPortHarbourCodeAndSyncVersion(portHarbourCode,
			syncVersion, true);
	}

	/**
	 * Returns the dm history port harbour where portHarbourCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortHarbourCodeAndSyncVersion(
		String portHarbourCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = null;
		if (dmHistoryPortHarbour == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPortHarbour.class).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryPortHarbour = (DmHistoryPortHarbour) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPortHarbour;
	}

	/**
	 * Returns all the dm history port harbours where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortRegionCode(
		String portRegionCode) throws SystemException {
		return findByPortRegionCode(portRegionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port harbours where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @return the range of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortRegionCode(
		String portRegionCode, int start, int end) throws SystemException {
		return findByPortRegionCode(portRegionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port harbours where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findByPortRegionCode(
		String portRegionCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE);

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
				query.append(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				list = (List<DmHistoryPortHarbour>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPortRegionCode_First(
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPortRegionCode_First(portRegionCode,
				orderByComparator);

		if (dmHistoryPortHarbour != null) {
			return dmHistoryPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortHarbourException(msg.toString());
	}

	/**
	 * Returns the first dm history port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortRegionCode_First(
		String portRegionCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryPortHarbour> list = findByPortRegionCode(portRegionCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour findByPortRegionCode_Last(
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = fetchByPortRegionCode_Last(portRegionCode,
				orderByComparator);

		if (dmHistoryPortHarbour != null) {
			return dmHistoryPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortHarbourException(msg.toString());
	}

	/**
	 * Returns the last dm history port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port harbour, or <code>null</code> if a matching dm history port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour fetchByPortRegionCode_Last(
		String portRegionCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByPortRegionCode(portRegionCode);

		List<DmHistoryPortHarbour> list = findByPortRegionCode(portRegionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history port harbours before and after the current dm history port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param id the primary key of the current dm history port harbour
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortHarbourException if a dm history port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour[] findByPortRegionCode_PrevAndNext(int id,
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPortHarbour[] array = new DmHistoryPortHarbour[3];

			array[0] = getByPortRegionCode_PrevAndNext(
					dmHistoryPortHarbour, portRegionCode, orderByComparator,
					true);

			array[1] = dmHistoryPortHarbour;

			array[2] = getByPortRegionCode_PrevAndNext(
					dmHistoryPortHarbour, portRegionCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPortHarbour getByPortRegionCode_PrevAndNext(
		 DmHistoryPortHarbour dmHistoryPortHarbour,
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

		query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE);

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
			query.append(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionCode != null) {
			builder.appendNamedParameterMap("portRegionCode", portRegionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPortHarbour);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPortHarbour> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm history port harbours.
	 *
	 * @return the dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port harbours.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @return the range of dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port harbours.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port harbours
	 * @param end the upper bound of the range of dm history port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortHarbour> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPORTHARBOUR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPORTHARBOUR.concat(DmHistoryPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPortHarbour>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history port harbours where portHarbourCode = &#63; from the database.
	 *
	 * @param portHarbourCode the port harbour code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortHarbourCode(String portHarbourCode)
		throws SystemException {
		for (DmHistoryPortHarbour dmHistoryPortHarbour : findByPortHarbourCode(
				portHarbourCode)) {
			repository.delete(dmHistoryPortHarbour);
		}
	}

	/**
	 * Removes the dm history port harbour where portHarbourCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param syncVersion the sync version
	 * @return the dm history port harbour that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortHarbour removeByPortHarbourCodeAndSyncVersion(
		String portHarbourCode, String syncVersion)
		throws NoSuchDmHistoryPortHarbourException, SystemException {
		DmHistoryPortHarbour dmHistoryPortHarbour = findByPortHarbourCodeAndSyncVersion(portHarbourCode,
				syncVersion);

		repository.delete(dmHistoryPortHarbour);
			return dmHistoryPortHarbour;
	}

	/**
	 * Removes all the dm history port harbours where portRegionCode = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionCode(String portRegionCode)
		throws SystemException {
		for (DmHistoryPortHarbour dmHistoryPortHarbour : findByPortRegionCode(
				portRegionCode)) {
			repository.delete(dmHistoryPortHarbour);
		}
	}

	/**
	 * Removes all the dm history port harbours from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPortHarbour dmHistoryPortHarbour : findAll()) {
			repository.delete(dmHistoryPortHarbour);
		}
	}

	/**
	 * Returns the number of dm history port harbours where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @return the number of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortHarbourCode(String portHarbourCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
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
	 * Returns the number of dm history port harbours where portHarbourCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortHarbourCodeAndSyncVersion(String portHarbourCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
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
	 * Returns the number of dm history port harbours where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the number of matching dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCode(String portRegionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPORTHARBOUR_WHERE);

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
	 * Returns the number of dm history port harbours.
	 *
	 * @return the number of dm history port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPORTHARBOUR).build();

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
	 * Initializes the dm history port harbour persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPORTHARBOUR = "SELECT dmHistoryPortHarbour FROM DmHistoryPortHarbour dmHistoryPortHarbour";
	private static final String _SQL_SELECT_DMHISTORYPORTHARBOUR_WHERE = "SELECT dmHistoryPortHarbour FROM DmHistoryPortHarbour dmHistoryPortHarbour WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPORTHARBOUR = "SELECT COUNT(dmHistoryPortHarbour) FROM DmHistoryPortHarbour dmHistoryPortHarbour";
	private static final String _SQL_COUNT_DMHISTORYPORTHARBOUR_WHERE = "SELECT COUNT(dmHistoryPortHarbour) FROM DmHistoryPortHarbour dmHistoryPortHarbour WHERE ";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1 =
		"dmHistoryPortHarbour.portHarbourCode IS NULL";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2 =
		"dmHistoryPortHarbour.portHarbourCode =:portHarbourCode";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3 =
		"(dmHistoryPortHarbour.portHarbourCode IS NULL OR dmHistoryPortHarbour.portHarbourCode =:portHarbourCode)";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_1 =
		"dmHistoryPortHarbour.portHarbourCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_2 =
		"dmHistoryPortHarbour.portHarbourCode =:portHarbourCode AND ";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_PORTHARBOURCODE_3 =
		"(dmHistoryPortHarbour.portHarbourCode IS NULL OR dmHistoryPortHarbour.portHarbourCode =:portHarbourCode) AND ";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPortHarbour.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPortHarbour.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PORTHARBOURCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPortHarbour.syncVersion IS NULL OR dmHistoryPortHarbour.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1 = "dmHistoryPortHarbour.portRegionCode IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2 = "dmHistoryPortHarbour.portRegionCode =:portRegionCode";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3 = "(dmHistoryPortHarbour.portRegionCode IS NULL OR dmHistoryPortHarbour.portRegionCode =:portRegionCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPortHarbour.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPortHarbour exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPortHarbour exists with the key {";
	

	
}
