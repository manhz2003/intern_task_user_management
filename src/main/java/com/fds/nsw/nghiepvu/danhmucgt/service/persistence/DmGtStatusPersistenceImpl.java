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
import com.fds.nsw.nghiepvu.model.DmGtStatus;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtStatusRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtStatusModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtStatusPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtStatusRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtStatus> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtStatusUtil} to access the dm gt status persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtStatus create(long id) {
		DmGtStatus dmGtStatus = new DmGtStatus();

		
		//dmGtStatus.setPrimaryKey(id);

		return dmGtStatus;
	}

	/**
	 * Removes the dm gt status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt status
	 * @return the dm gt status that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus remove(long id)
		throws NoSuchDmGtStatusException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm gt status with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt status
	 * @return the dm gt status that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtStatus remove(Serializable primaryKey)
		throws NoSuchDmGtStatusException, SystemException {
		

		try {
			

			DmGtStatus dmGtStatus = findByPrimaryKey(primaryKey);

			if (dmGtStatus == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtStatus);
			return dmGtStatus;
		}
		catch (NoSuchDmGtStatusException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtStatus remove(DmGtStatus DmGtStatus) throws SystemException {
	removeImpl(DmGtStatus);
	return DmGtStatus;
}

protected DmGtStatus removeImpl(DmGtStatus dmGtStatus)
		throws SystemException {
		try {
			repository.delete(dmGtStatus);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtStatus;
	}

	
	public DmGtStatus updateImpl(
		DmGtStatus dmGtStatus, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmGtStatus);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtStatus;
	}

	
	public DmGtStatus findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt status with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtStatusException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt status
	 * @return the dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus findByPrimaryKey(long id)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = fetchByPrimaryKey(id);

		if (dmGtStatus == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtStatusException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtStatus;
	}

	/**
	 * Returns the dm gt status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt status
	 * @return the dm gt status, or <code>null</code> if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtStatus fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt status with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt status
	 * @return the dm gt status, or <code>null</code> if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus fetchByPrimaryKey(long id) throws SystemException {
		DmGtStatus dmGtStatus = null;

		

		if (dmGtStatus == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtStatus> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtStatus = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtStatus;
	}

	/**
	 * Returns all the dm gt statuses where type = &#63;.
	 *
	 * @param type the type
	 * @return the matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByType(int type) throws SystemException {
		return findByType(type, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt statuses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @return the range of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByType(int type, int start, int end)
		throws SystemException {
		return findByType(type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt statuses where type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param type the type
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByType(int type, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtStatus> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("type", type);

				list = (List<DmGtStatus>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt status in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus findByType_First(int type,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = fetchByType_First(type, orderByComparator);

		if (dmGtStatus != null) {
			return dmGtStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtStatusException(msg.toString());
	}

	/**
	 * Returns the first dm gt status in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt status, or <code>null</code> if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus fetchByType_First(int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtStatus> list = findByType(type, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt status in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus findByType_Last(int type,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = fetchByType_Last(type, orderByComparator);

		if (dmGtStatus != null) {
			return dmGtStatus;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtStatusException(msg.toString());
	}

	/**
	 * Returns the last dm gt status in the ordered set where type = &#63;.
	 *
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt status, or <code>null</code> if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus fetchByType_Last(int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByType(type);

		List<DmGtStatus> list = findByType(type, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt statuses before and after the current dm gt status in the ordered set where type = &#63;.
	 *
	 * @param id the primary key of the current dm gt status
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus[] findByType_PrevAndNext(long id, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = findByPrimaryKey(id);

		

		try {
			

			DmGtStatus[] array = new DmGtStatus[3];

			array[0] = getByType_PrevAndNext(dmGtStatus, type,
					orderByComparator, true);

			array[1] = dmGtStatus;

			array[2] = getByType_PrevAndNext(dmGtStatus, type,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtStatus getByType_PrevAndNext(
		DmGtStatus dmGtStatus, int type, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTSTATUS_WHERE);

		query.append(_FINDER_COLUMN_TYPE_TYPE_2);

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
			query.append(DmGtStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("type", type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtStatus);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtStatus> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt statuses where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @return the matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByStatusCode(int statusCode, int type)
		throws SystemException {
		return findByStatusCode(statusCode, type, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt statuses where statusCode = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @return the range of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByStatusCode(int statusCode, int type,
		int start, int end) throws SystemException {
		return findByStatusCode(statusCode, type, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt statuses where statusCode = &#63; and type = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findByStatusCode(int statusCode, int type,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGtStatus> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMGTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_STATUSCODE_STATUSCODE_2);

			query.append(_FINDER_COLUMN_STATUSCODE_TYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtStatusModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("statusCode", statusCode);

				builder.appendNamedParameterMap("type", type);

				list = (List<DmGtStatus>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt status in the ordered set where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus findByStatusCode_First(int statusCode, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = fetchByStatusCode_First(statusCode, type,
				orderByComparator);

		if (dmGtStatus != null) {
			return dmGtStatus;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("statusCode=");
		msg.append(statusCode);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtStatusException(msg.toString());
	}

	/**
	 * Returns the first dm gt status in the ordered set where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt status, or <code>null</code> if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus fetchByStatusCode_First(int statusCode, int type,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtStatus> list = findByStatusCode(statusCode, type, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt status in the ordered set where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus findByStatusCode_Last(int statusCode, int type,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = fetchByStatusCode_Last(statusCode, type,
				orderByComparator);

		if (dmGtStatus != null) {
			return dmGtStatus;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("statusCode=");
		msg.append(statusCode);

		msg.append(", type=");
		msg.append(type);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtStatusException(msg.toString());
	}

	/**
	 * Returns the last dm gt status in the ordered set where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt status, or <code>null</code> if a matching dm gt status could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus fetchByStatusCode_Last(int statusCode, int type,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByStatusCode(statusCode, type);

		List<DmGtStatus> list = findByStatusCode(statusCode, type, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt statuses before and after the current dm gt status in the ordered set where statusCode = &#63; and type = &#63;.
	 *
	 * @param id the primary key of the current dm gt status
	 * @param statusCode the status code
	 * @param type the type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt status
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtStatusException if a dm gt status with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtStatus[] findByStatusCode_PrevAndNext(long id, int statusCode,
		int type, OrderByComparator orderByComparator)
		throws NoSuchDmGtStatusException, SystemException {
		DmGtStatus dmGtStatus = findByPrimaryKey(id);

		

		try {
			

			DmGtStatus[] array = new DmGtStatus[3];

			array[0] = getByStatusCode_PrevAndNext(dmGtStatus,
					statusCode, type, orderByComparator, true);

			array[1] = dmGtStatus;

			array[2] = getByStatusCode_PrevAndNext(dmGtStatus,
					statusCode, type, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtStatus getByStatusCode_PrevAndNext(
		DmGtStatus dmGtStatus, int statusCode, int type,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTSTATUS_WHERE);

		query.append(_FINDER_COLUMN_STATUSCODE_STATUSCODE_2);

		query.append(_FINDER_COLUMN_STATUSCODE_TYPE_2);

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
			query.append(DmGtStatusModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("statusCode", statusCode);

		builder.appendNamedParameterMap("type", type);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtStatus);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtStatus> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt statuses.
	 *
	 * @return the dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @return the range of dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt statuses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt statuses
	 * @param end the upper bound of the range of dm gt statuses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtStatus> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtStatus> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTSTATUS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTSTATUS.concat(DmGtStatusModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtStatus>) queryFactory.getResultList(builder);
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
	 * Removes all the dm gt statuses where type = &#63; from the database.
	 *
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByType(int type) throws SystemException {
		for (DmGtStatus dmGtStatus : findByType(type)) {
			repository.delete(dmGtStatus);
		}
	}

	/**
	 * Removes all the dm gt statuses where statusCode = &#63; and type = &#63; from the database.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByStatusCode(int statusCode, int type)
		throws SystemException {
		for (DmGtStatus dmGtStatus : findByStatusCode(statusCode, type)) {
			repository.delete(dmGtStatus);
		}
	}

	/**
	 * Removes all the dm gt statuses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtStatus dmGtStatus : findAll()) {
			repository.delete(dmGtStatus);
		}
	}

	/**
	 * Returns the number of dm gt statuses where type = &#63;.
	 *
	 * @param type the type
	 * @return the number of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByType(int type) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_TYPE_TYPE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("type", type);

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
	 * Returns the number of dm gt statuses where statusCode = &#63; and type = &#63;.
	 *
	 * @param statusCode the status code
	 * @param type the type
	 * @return the number of matching dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByStatusCode(int statusCode, int type)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMGTSTATUS_WHERE);

			query.append(_FINDER_COLUMN_STATUSCODE_STATUSCODE_2);

			query.append(_FINDER_COLUMN_STATUSCODE_TYPE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("statusCode", statusCode);

				builder.appendNamedParameterMap("type", type);

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
	 * Returns the number of dm gt statuses.
	 *
	 * @return the number of dm gt statuses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTSTATUS).build();

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
	 * Initializes the dm gt status persistence.
	 */
	private static final String _SQL_SELECT_DMGTSTATUS = "SELECT dmGtStatus FROM DmGtStatus dmGtStatus";
	private static final String _SQL_SELECT_DMGTSTATUS_WHERE = "SELECT dmGtStatus FROM DmGtStatus dmGtStatus WHERE ";
	private static final String _SQL_COUNT_DMGTSTATUS = "SELECT COUNT(dmGtStatus) FROM DmGtStatus dmGtStatus";
	private static final String _SQL_COUNT_DMGTSTATUS_WHERE = "SELECT COUNT(dmGtStatus) FROM DmGtStatus dmGtStatus WHERE ";
	private static final String _FINDER_COLUMN_TYPE_TYPE_2 = "dmGtStatus.type =:type";
	private static final String _FINDER_COLUMN_STATUSCODE_STATUSCODE_2 = "dmGtStatus.statusCode =:statusCode AND ";
	private static final String _FINDER_COLUMN_STATUSCODE_TYPE_2 = "dmGtStatus.type =:type";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtStatus.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtStatus exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtStatus exists with the key {";
	

	
}
