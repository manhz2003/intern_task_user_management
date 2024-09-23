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
import com.fds.nsw.nghiepvu.model.DmState;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmStateRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmStateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmStatePersistenceImpl extends BasePersistence {
	@Autowired
	DmStateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmState> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmStateUtil} to access the dm state persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmState create(int id) {
		DmState dmState = new DmState();

		
		//dmState.setPrimaryKey(id);

		return dmState;
	}

	/**
	 * Removes the dm state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm state
	 * @return the dm state that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState remove(int id)
		throws NoSuchDmStateException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm state with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm state
	 * @return the dm state that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmState remove(Serializable primaryKey)
		throws NoSuchDmStateException, SystemException {
		

		try {
			

			DmState dmState = findByPrimaryKey(primaryKey);

			if (dmState == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmState);
			return dmState;
		}
		catch (NoSuchDmStateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmState remove(DmState DmState) throws SystemException {
	removeImpl(DmState);	return DmState;
}

protected DmState removeImpl

(DmState dmState) throws SystemException {
		try {
			repository.delete(dmState);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmState;
	}

	
	public DmState updateImpl(DmState dmState,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmState);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmState;
	}

	
	public DmState findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm state with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmStateException} if it could not be found.
	 *
	 * @param id the primary key of the dm state
	 * @return the dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState findByPrimaryKey(int id)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = fetchByPrimaryKey(id);

		if (dmState == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmStateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmState;
	}

	/**
	 * Returns the dm state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm state
	 * @return the dm state, or <code>null</code> if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmState fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm state with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm state
	 * @return the dm state, or <code>null</code> if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState fetchByPrimaryKey(int id) throws SystemException {
		DmState dmState = null;

		

		if (dmState == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmState> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmState = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmState;
	}

	/**
	 * Returns all the dm states where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @return the matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByStateCode(String stateCode)
		throws SystemException {
		return findByStateCode(stateCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm states where stateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @return the range of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByStateCode(String stateCode, int start, int end)
		throws SystemException {
		return findByStateCode(stateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm states where stateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateCode the state code
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByStateCode(String stateCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmState> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSTATE_WHERE);

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
				query.append(DmStateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (stateCode != null) {
					builder.appendNamedParameterMap("stateCode", stateCode);
				}

				list = (List<DmState>)queryFactory.getResultList(builder);
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
	 * Returns the first dm state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState findByStateCode_First(String stateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = fetchByStateCode_First(stateCode, orderByComparator);

		if (dmState != null) {
			return dmState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmStateException(msg.toString());
	}

	/**
	 * Returns the first dm state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm state, or <code>null</code> if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState fetchByStateCode_First(String stateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmState> list = findByStateCode(stateCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState findByStateCode_Last(String stateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = fetchByStateCode_Last(stateCode, orderByComparator);

		if (dmState != null) {
			return dmState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateCode=");
		msg.append(stateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmStateException(msg.toString());
	}

	/**
	 * Returns the last dm state in the ordered set where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm state, or <code>null</code> if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState fetchByStateCode_Last(String stateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStateCode(stateCode);

		List<DmState> list = findByStateCode(stateCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm states before and after the current dm state in the ordered set where stateCode = &#63;.
	 *
	 * @param id the primary key of the current dm state
	 * @param stateCode the state code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState[] findByStateCode_PrevAndNext(int id, String stateCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = findByPrimaryKey(id);

		

		try {
			

			DmState[] array = new DmState[3];

			array[0] = getByStateCode_PrevAndNext(dmState, stateCode,
					orderByComparator, true);

			array[1] = dmState;

			array[2] = getByStateCode_PrevAndNext(dmState, stateCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmState getByStateCode_PrevAndNext(
		DmState dmState, String stateCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSTATE_WHERE);

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
			query.append(DmStateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (stateCode != null) {
			builder.appendNamedParameterMap("stateCode", stateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmState);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmState> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm states where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @return the matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByF_stateNamebyLike(String stateName)
		throws SystemException {
		return findByF_stateNamebyLike(stateName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm states where stateName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateName the state name
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @return the range of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByF_stateNamebyLike(String stateName, int start,
		int end) throws SystemException {
		return findByF_stateNamebyLike(stateName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm states where stateName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param stateName the state name
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findByF_stateNamebyLike(String stateName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmState> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSTATE_WHERE);

			if (stateName == null) {
				query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_1);
			}
			else {
				if (stateName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmStateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (stateName != null) {
					builder.appendNamedParameterMap("stateName", stateName);
				}

				list = (List<DmState>)queryFactory.getResultList(builder);
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
	 * Returns the first dm state in the ordered set where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState findByF_stateNamebyLike_First(String stateName,
		OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = fetchByF_stateNamebyLike_First(stateName,
				orderByComparator);

		if (dmState != null) {
			return dmState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateName=");
		msg.append(stateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmStateException(msg.toString());
	}

	/**
	 * Returns the first dm state in the ordered set where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm state, or <code>null</code> if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState fetchByF_stateNamebyLike_First(String stateName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmState> list = findByF_stateNamebyLike(stateName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm state in the ordered set where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState findByF_stateNamebyLike_Last(String stateName,
		OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = fetchByF_stateNamebyLike_Last(stateName,
				orderByComparator);

		if (dmState != null) {
			return dmState;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("stateName=");
		msg.append(stateName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmStateException(msg.toString());
	}

	/**
	 * Returns the last dm state in the ordered set where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm state, or <code>null</code> if a matching dm state could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState fetchByF_stateNamebyLike_Last(String stateName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_stateNamebyLike(stateName);

		List<DmState> list = findByF_stateNamebyLike(stateName, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm states before and after the current dm state in the ordered set where stateName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm state
	 * @param stateName the state name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm state
	 * @throws vn.gt.dao.danhmuc.NoSuchDmStateException if a dm state with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmState[] findByF_stateNamebyLike_PrevAndNext(int id,
		String stateName, OrderByComparator orderByComparator)
		throws NoSuchDmStateException, SystemException {
		DmState dmState = findByPrimaryKey(id);

		

		try {
			

			DmState[] array = new DmState[3];

			array[0] = getByF_stateNamebyLike_PrevAndNext(dmState,
					stateName, orderByComparator, true);

			array[1] = dmState;

			array[2] = getByF_stateNamebyLike_PrevAndNext(dmState,
					stateName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmState getByF_stateNamebyLike_PrevAndNext(
		DmState dmState, String stateName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSTATE_WHERE);

		if (stateName == null) {
			query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_1);
		}
		else {
			if (stateName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_2);
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
			query.append(DmStateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (stateName != null) {
			builder.appendNamedParameterMap("stateName", stateName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmState);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmState> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm states.
	 *
	 * @return the dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @return the range of dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm states.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm states
	 * @param end the upper bound of the range of dm states (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm states
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmState> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmState> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMSTATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMSTATE.concat(DmStateModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmState>) queryFactory.getResultList(builder);
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
	 * Removes all the dm states where stateCode = &#63; from the database.
	 *
	 * @param stateCode the state code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStateCode(String stateCode) throws SystemException {
		for (DmState dmState : findByStateCode(stateCode)) {
			repository.delete(dmState);
		}
	}

	/**
	 * Removes all the dm states where stateName LIKE &#63; from the database.
	 *
	 * @param stateName the state name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_stateNamebyLike(String stateName)
		throws SystemException {
		for (DmState dmState : findByF_stateNamebyLike(stateName)) {
			repository.delete(dmState);
		}
	}

	/**
	 * Removes all the dm states from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmState dmState : findAll()) {
			repository.delete(dmState);
		}
	}

	/**
	 * Returns the number of dm states where stateCode = &#63;.
	 *
	 * @param stateCode the state code
	 * @return the number of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStateCode(String stateCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSTATE_WHERE);

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
	 * Returns the number of dm states where stateName LIKE &#63;.
	 *
	 * @param stateName the state name
	 * @return the number of matching dm states
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_stateNamebyLike(String stateName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSTATE_WHERE);

			if (stateName == null) {
				query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_1);
			}
			else {
				if (stateName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (stateName != null) {
					builder.appendNamedParameterMap("stateName", stateName);
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
	 * Returns the number of dm states.
	 *
	 * @return the number of dm states
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMSTATE).build();

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
	 * Initializes the dm state persistence.
	 */
	private static final String _SQL_SELECT_DMSTATE = "SELECT dmState FROM DmState dmState";
	private static final String _SQL_SELECT_DMSTATE_WHERE = "SELECT dmState FROM DmState dmState WHERE ";
	private static final String _SQL_COUNT_DMSTATE = "SELECT COUNT(dmState) FROM DmState dmState";
	private static final String _SQL_COUNT_DMSTATE_WHERE = "SELECT COUNT(dmState) FROM DmState dmState WHERE ";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_1 = "dmState.stateCode IS NULL";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_2 = "dmState.stateCode =:stateCode";
	private static final String _FINDER_COLUMN_STATECODE_STATECODE_3 = "(dmState.stateCode IS NULL OR dmState.stateCode =:stateCode)";
	private static final String _FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_1 = "dmState.stateName LIKE NULL";
	private static final String _FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_2 = "dmState.stateName LIKE :stateName";
	private static final String _FINDER_COLUMN_F_STATENAMEBYLIKE_STATENAME_3 = "(dmState.stateName IS NULL OR dmState.stateName LIKE :stateName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmState.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmState exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmState exists with the key {";
	

	
}
