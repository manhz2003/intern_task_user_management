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
import com.fds.nsw.nghiepvu.model.DmHistoryState;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryStateRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryStateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryStatePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryStateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryState> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryStateUtil} to access the dm history state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryState create(int id) {
		DmHistoryState dmHistoryState = new DmHistoryState();

		
		//dmHistoryState.setPrimaryKey(id);

		return dmHistoryState;
	}

	/**
	 * Removes the dm history state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history state
	 * @return the dm history state that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState remove(int id)
		throws NoSuchDmHistoryStateException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history state
	 * @return the dm history state that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryState remove(Serializable primaryKey)
		throws NoSuchDmHistoryStateException, SystemException {
		

		try {
			

			DmHistoryState dmHistoryState = findByPrimaryKey(primaryKey);

			if (dmHistoryState == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryState);
			return dmHistoryState;
		}
		catch (NoSuchDmHistoryStateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryState remove(DmHistoryState DmHistoryState) throws SystemException {
	removeImpl(DmHistoryState);	return DmHistoryState;
}

protected DmHistoryState removeImpl

(DmHistoryState dmHistoryState)
		throws SystemException {
		try {
			repository.delete(dmHistoryState);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryState;
	}

	
	public DmHistoryState updateImpl(
		DmHistoryState dmHistoryState, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryState);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryState;
	}

	
	public DmHistoryState findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history state with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryStateException} if it could not be found.
	 *
	 * @param id the primary key of the dm history state
	 * @return the dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findByPrimaryKey(int id)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchByPrimaryKey(id);

		if (dmHistoryState == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryState;
	}

	/**
	 * Returns the dm history state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history state
	 * @return the dm history state, or <code>null</code> if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryState fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history state
	 * @return the dm history state, or <code>null</code> if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryState dmHistoryState = null;

		

		if (dmHistoryState == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryState> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryState = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryState;
	}

	/**
	 * Returns all the dm history states where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findBySyncVersion(String syncVersion)
		throws SystemException {
		return findBySyncVersion(syncVersion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history states where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @return the range of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findBySyncVersion(String syncVersion,
		int start, int end) throws SystemException {
		return findBySyncVersion(syncVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history states where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findBySyncVersion(String syncVersion,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryState> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSTATE_WHERE);

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryStateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				list = (List<DmHistoryState>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history state in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchBySyncVersion_First(syncVersion,
				orderByComparator);

		if (dmHistoryState != null) {
			return dmHistoryState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryStateException(msg.toString());
	}

	/**
	 * Returns the first dm history state in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryState> list = findBySyncVersion(syncVersion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history state in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchBySyncVersion_Last(syncVersion,
				orderByComparator);

		if (dmHistoryState != null) {
			return dmHistoryState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryStateException(msg.toString());
	}

	/**
	 * Returns the last dm history state in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySyncVersion(syncVersion);

		List<DmHistoryState> list = findBySyncVersion(syncVersion, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history states before and after the current dm history state in the ordered set where syncVersion = &#63;.
	 *
	 * @param id the primary key of the current dm history state
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState[] findBySyncVersion_PrevAndNext(int id,
		String syncVersion, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = findByPrimaryKey(id);

		

		try {
			

			DmHistoryState[] array = new DmHistoryState[3];

			array[0] = getBySyncVersion_PrevAndNext(dmHistoryState,
					syncVersion, orderByComparator, true);

			array[1] = dmHistoryState;

			array[2] = getBySyncVersion_PrevAndNext(dmHistoryState,
					syncVersion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryState getBySyncVersion_PrevAndNext(
		DmHistoryState dmHistoryState, String syncVersion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSTATE_WHERE);

		if (syncVersion == null) {
			query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
		}
		else {
			if (syncVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
			}
			else {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
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
			query.append(DmHistoryStateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (syncVersion != null) {
			builder.appendNamedParameterMap("syncVersion", syncVersion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryState);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryState> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm history states where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @return the matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findByStateCode(String stateCode)
		throws SystemException {
		return findByStateCode(stateCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm history states where stateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @return the range of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findByStateCode(String stateCode, int start,
		int end) throws SystemException {
		return findByStateCode(stateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history states where stateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findByStateCode(String stateCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryState> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSTATE_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODE_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODE_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODE_STATECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryStateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
				}

				list = (List<DmHistoryState>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findByStateCode_First(String stateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchByStateCode_First(stateCode,
				orderByComparator);

		if (dmHistoryState != null) {
			return dmHistoryState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryStateException(msg.toString());
	}

	/**
	 * Returns the first dm history state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchByStateCode_First(String stateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryState> list = findByStateCode(stateCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findByStateCode_Last(String stateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchByStateCode_Last(stateCode,
				orderByComparator);

		if (dmHistoryState != null) {
			return dmHistoryState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryStateException(msg.toString());
	}

	/**
	 * Returns the last dm history state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchByStateCode_Last(String stateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStateCode(stateCode);

		List<DmHistoryState> list = findByStateCode(stateCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history states before and after the current dm history state in the ordered set where stateCode = &#63;.
	 *
	 * @param id the primary key of the current dm history state
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a dm history state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState[] findByStateCode_PrevAndNext(int id,
		String stateCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = findByPrimaryKey(id);

		

		try {
			

			DmHistoryState[] array = new DmHistoryState[3];

			array[0] = getByStateCode_PrevAndNext(dmHistoryState,
					stateCode, orderByComparator, true);

			array[1] = dmHistoryState;

			array[2] = getByStateCode_PrevAndNext(dmHistoryState,
					stateCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryState getByStateCode_PrevAndNext(
		DmHistoryState dmHistoryState, String stateCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSTATE_WHERE);

		if (stateCode == null) {
			query.append(_FINDER_COLUMN_STATECODE_STATECODE_1);
		}
		else {
			if (stateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_STATECODE_STATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_STATECODE_STATECODE_2);
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
			query.append(DmHistoryStateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (stateCode != null) {
			builder.appendNamedParameterMap("stateCode", stateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryState);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryState> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history state where stateCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryStateException} if it could not be found.
	 *
	 * @param stateCode the state code
	 * @param syncVersion the sync version
	 * @return the matching dm history state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryStateException if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState findByStateCodeAndSyncVersion(String stateCode,
		String syncVersion)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = fetchByStateCodeAndSyncVersion(stateCode,
				syncVersion);

		if (dmHistoryState == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("stateCode=");
			msg.append(stateCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryStateException(msg.toString());
		}

		return dmHistoryState;
	}

	/**
	 * Returns the dm history state where stateCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param stateCode the state code
	 * @param syncVersion the sync version
	 * @return the matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchByStateCodeAndSyncVersion(String stateCode,
		String syncVersion) throws SystemException {
		return fetchByStateCodeAndSyncVersion(stateCode, syncVersion, true);
	}

	/**
	 * Returns the dm history state where stateCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param stateCode the state code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history state, or <code>null</code> if a matching dm history state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState fetchByStateCodeAndSyncVersion(String stateCode,
		String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryState dmHistoryState = null;
		if (dmHistoryState == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSTATE_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryStateModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryState.class).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryState = (DmHistoryState) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryState;
	}

	/**
	 * Returns all the dm history states.
	 *
	 * @return the dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @return the range of dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history states
	 * @param end the upper bound of the range of dm history states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryState> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryState> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSTATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSTATE.concat(DmHistoryStateModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryState>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history states where syncVersion = &#63; from the database.
	 *
	 * @param syncVersion the sync version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySyncVersion(String syncVersion)
		throws SystemException {
		for (DmHistoryState dmHistoryState : findBySyncVersion(syncVersion)) {
			repository.delete(dmHistoryState);
		}
	}

	/**
	 * Removes all the dm history states where stateCode = &#63; from the database.
	 *
	 * @param stateCode the state code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStateCode(String stateCode) throws SystemException {
		for (DmHistoryState dmHistoryState : findByStateCode(stateCode)) {
			repository.delete(dmHistoryState);
		}
	}

	/**
	 * Removes the dm history state where stateCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param stateCode the state code
	 * @param syncVersion the sync version
	 * @return the dm history state that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryState removeByStateCodeAndSyncVersion(String stateCode,
		String syncVersion)
		throws NoSuchDmHistoryStateException, SystemException {
		DmHistoryState dmHistoryState = findByStateCodeAndSyncVersion(stateCode,
				syncVersion);

		repository.delete(dmHistoryState);
			return dmHistoryState;
	}

	/**
	 * Removes all the dm history states from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryState dmHistoryState : findAll()) {
			repository.delete(dmHistoryState);
		}
	}

	/**
	 * Returns the number of dm history states where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the number of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySyncVersion(String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSTATE_WHERE);

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

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
	 * Returns the number of dm history states where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @return the number of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStateCode(String stateCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSTATE_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODE_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODE_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODE_STATECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
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
	 * Returns the number of dm history states where stateCode = &#63; and syncVersion = &#63;.
	 *
	 * @param stateCode the state code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStateCodeAndSyncVersion(String stateCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSTATE_WHERE);

			if (stateCode == null) {
				query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_1);
			}
			else {
				if (stateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
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
	 * Returns the number of dm history states.
	 *
	 * @return the number of dm history states
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSTATE).build();

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
	 * Initializes the dm history state persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSTATE = "SELECT dmHistoryState FROM DmHistoryState dmHistoryState";
	private static final String _SQL_SELECT_DMHISTORYSTATE_WHERE = "SELECT dmHistoryState FROM DmHistoryState dmHistoryState WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSTATE = "SELECT COUNT(dmHistoryState) FROM DmHistoryState dmHistoryState";
	private static final String _SQL_COUNT_DMHISTORYSTATE_WHERE = "SELECT COUNT(dmHistoryState) FROM DmHistoryState dmHistoryState WHERE ";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1 = "dmHistoryState.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2 = "dmHistoryState.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3 = "(dmHistoryState.syncVersion IS NULL OR dmHistoryState.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_1 = "dmHistoryState.stateCode IS NULL";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_2 = "dmHistoryState.stateCode =:stateCode";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_3 = "(dmHistoryState.stateCode IS NULL OR dmHistoryState.stateCode =:stateCode)";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_1 =
		"dmHistoryState.stateCode IS NULL AND ";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_2 =
		"dmHistoryState.stateCode =:stateCode AND ";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_STATECODE_3 =
		"(dmHistoryState.stateCode IS NULL OR dmHistoryState.stateCode =:stateCode) AND ";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryState.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryState.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_STATECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryState.syncVersion IS NULL OR dmHistoryState.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryState.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryState exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryState exists with the key {";
	

	
}
