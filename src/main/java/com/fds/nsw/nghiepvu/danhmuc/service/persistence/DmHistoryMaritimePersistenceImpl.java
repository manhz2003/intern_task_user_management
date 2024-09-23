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
import com.fds.nsw.nghiepvu.model.DmHistoryMaritime;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryMaritimeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryMaritimeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryMaritimePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryMaritimeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryMaritime> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryMaritimeUtil} to access the dm history maritime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryMaritime create(int id) {
		DmHistoryMaritime dmHistoryMaritime = new DmHistoryMaritime();

		
		//dmHistoryMaritime.setPrimaryKey(id);

		return dmHistoryMaritime;
	}

	/**
	 * Removes the dm history maritime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history maritime
	 * @return the dm history maritime that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime remove(int id)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history maritime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history maritime
	 * @return the dm history maritime that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryMaritime remove(Serializable primaryKey)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		

		try {
			

			DmHistoryMaritime dmHistoryMaritime = findByPrimaryKey(primaryKey);

			if (dmHistoryMaritime == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryMaritimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryMaritime);
			return dmHistoryMaritime;
		}
		catch (NoSuchDmHistoryMaritimeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryMaritime remove(DmHistoryMaritime DmHistoryMaritime) throws SystemException {
	removeImpl(DmHistoryMaritime);	return DmHistoryMaritime;
}

protected DmHistoryMaritime removeImpl

(DmHistoryMaritime dmHistoryMaritime)
		throws SystemException {
		try {
			repository.delete(dmHistoryMaritime);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryMaritime;
	}

	
	public DmHistoryMaritime updateImpl(
		DmHistoryMaritime dmHistoryMaritime,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryMaritime);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryMaritime;
	}

	
	public DmHistoryMaritime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history maritime with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history maritime
	 * @return the dm history maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime findByPrimaryKey(int id)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = fetchByPrimaryKey(id);

		if (dmHistoryMaritime == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryMaritimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryMaritime;
	}

	/**
	 * Returns the dm history maritime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history maritime
	 * @return the dm history maritime, or <code>null</code> if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryMaritime fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history maritime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history maritime
	 * @return the dm history maritime, or <code>null</code> if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryMaritime dmHistoryMaritime = null;

		

		if (dmHistoryMaritime == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryMaritime> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryMaritime = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryMaritime;
	}

	/**
	 * Returns all the dm history maritimes where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findByMaritimeCode(String maritimeCode)
		throws SystemException {
		return findByMaritimeCode(maritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history maritimes where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of dm history maritimes
	 * @param end the upper bound of the range of dm history maritimes (not inclusive)
	 * @return the range of matching dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findByMaritimeCode(String maritimeCode,
		int start, int end) throws SystemException {
		return findByMaritimeCode(maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history maritimes where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of dm history maritimes
	 * @param end the upper bound of the range of dm history maritimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findByMaritimeCode(String maritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryMaritime> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryMaritimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<DmHistoryMaritime>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history maritime in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime findByMaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = fetchByMaritimeCode_First(maritimeCode,
				orderByComparator);

		if (dmHistoryMaritime != null) {
			return dmHistoryMaritime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryMaritimeException(msg.toString());
	}

	/**
	 * Returns the first dm history maritime in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history maritime, or <code>null</code> if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime fetchByMaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryMaritime> list = findByMaritimeCode(maritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history maritime in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime findByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = fetchByMaritimeCode_Last(maritimeCode,
				orderByComparator);

		if (dmHistoryMaritime != null) {
			return dmHistoryMaritime;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryMaritimeException(msg.toString());
	}

	/**
	 * Returns the last dm history maritime in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history maritime, or <code>null</code> if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime fetchByMaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMaritimeCode(maritimeCode);

		List<DmHistoryMaritime> list = findByMaritimeCode(maritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history maritimes before and after the current dm history maritime in the ordered set where maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current dm history maritime
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a dm history maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime[] findByMaritimeCode_PrevAndNext(int id,
		String maritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = findByPrimaryKey(id);

		

		try {
			

			DmHistoryMaritime[] array = new DmHistoryMaritime[3];

			array[0] = getByMaritimeCode_PrevAndNext(
					dmHistoryMaritime, maritimeCode, orderByComparator, true);

			array[1] = dmHistoryMaritime;

			array[2] = getByMaritimeCode_PrevAndNext(
					dmHistoryMaritime, maritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryMaritime getByMaritimeCode_PrevAndNext(
		DmHistoryMaritime dmHistoryMaritime, String maritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYMARITIME_WHERE);

		if (maritimeCode == null) {
			query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (maritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
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
			query.append(DmHistoryMaritimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryMaritime);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryMaritime> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history maritime where maritimeCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException} if it could not be found.
	 *
	 * @param maritimeCode the maritime code
	 * @param syncVersion the sync version
	 * @return the matching dm history maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryMaritimeException if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime findByMaritimeCodeAndSyncVersion(
		String maritimeCode, String syncVersion)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = fetchByMaritimeCodeAndSyncVersion(maritimeCode,
				syncVersion);

		if (dmHistoryMaritime == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeCode=");
			msg.append(maritimeCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryMaritimeException(msg.toString());
		}

		return dmHistoryMaritime;
	}

	/**
	 * Returns the dm history maritime where maritimeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param syncVersion the sync version
	 * @return the matching dm history maritime, or <code>null</code> if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime fetchByMaritimeCodeAndSyncVersion(
		String maritimeCode, String syncVersion) throws SystemException {
		return fetchByMaritimeCodeAndSyncVersion(maritimeCode, syncVersion, true);
	}

	/**
	 * Returns the dm history maritime where maritimeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history maritime, or <code>null</code> if a matching dm history maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime fetchByMaritimeCodeAndSyncVersion(
		String maritimeCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryMaritime dmHistoryMaritime = null;
		if (dmHistoryMaritime == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryMaritimeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryMaritime.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryMaritime = (DmHistoryMaritime) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryMaritime;
	}

	/**
	 * Returns all the dm history maritimes.
	 *
	 * @return the dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history maritimes
	 * @param end the upper bound of the range of dm history maritimes (not inclusive)
	 * @return the range of dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history maritimes
	 * @param end the upper bound of the range of dm history maritimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMaritime> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryMaritime> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYMARITIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYMARITIME.concat(DmHistoryMaritimeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryMaritime>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history maritimes where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMaritimeCode(String maritimeCode)
		throws SystemException {
		for (DmHistoryMaritime dmHistoryMaritime : findByMaritimeCode(
				maritimeCode)) {
			repository.delete(dmHistoryMaritime);
		}
	}

	/**
	 * Removes the dm history maritime where maritimeCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @param syncVersion the sync version
	 * @return the dm history maritime that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMaritime removeByMaritimeCodeAndSyncVersion(
		String maritimeCode, String syncVersion)
		throws NoSuchDmHistoryMaritimeException, SystemException {
		DmHistoryMaritime dmHistoryMaritime = findByMaritimeCodeAndSyncVersion(maritimeCode,
				syncVersion);

		repository.delete(dmHistoryMaritime);
			return dmHistoryMaritime;
	}

	/**
	 * Removes all the dm history maritimes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryMaritime dmHistoryMaritime : findAll()) {
			repository.delete(dmHistoryMaritime);
		}
	}

	/**
	 * Returns the number of dm history maritimes where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of dm history maritimes where maritimeCode = &#63; and syncVersion = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCodeAndSyncVersion(String maritimeCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of dm history maritimes.
	 *
	 * @return the number of dm history maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYMARITIME).build();

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
	 * Initializes the dm history maritime persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYMARITIME = "SELECT dmHistoryMaritime FROM DmHistoryMaritime dmHistoryMaritime";
	private static final String _SQL_SELECT_DMHISTORYMARITIME_WHERE = "SELECT dmHistoryMaritime FROM DmHistoryMaritime dmHistoryMaritime WHERE ";
	private static final String _SQL_COUNT_DMHISTORYMARITIME = "SELECT COUNT(dmHistoryMaritime) FROM DmHistoryMaritime dmHistoryMaritime";
	private static final String _SQL_COUNT_DMHISTORYMARITIME_WHERE = "SELECT COUNT(dmHistoryMaritime) FROM DmHistoryMaritime dmHistoryMaritime WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "dmHistoryMaritime.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "dmHistoryMaritime.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(dmHistoryMaritime.maritimeCode IS NULL OR dmHistoryMaritime.maritimeCode =:maritimeCode)";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_1 =
		"dmHistoryMaritime.maritimeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_2 =
		"dmHistoryMaritime.maritimeCode =:maritimeCode AND ";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_MARITIMECODE_3 =
		"(dmHistoryMaritime.maritimeCode IS NULL OR dmHistoryMaritime.maritimeCode =:maritimeCode) AND ";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryMaritime.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryMaritime.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_MARITIMECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryMaritime.syncVersion IS NULL OR dmHistoryMaritime.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryMaritime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryMaritime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryMaritime exists with the key {";
	

	
}
