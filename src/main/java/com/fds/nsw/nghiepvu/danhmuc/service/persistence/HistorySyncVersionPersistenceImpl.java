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
import com.fds.nsw.nghiepvu.model.HistorySyncVersion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.HistorySyncVersionRepository;
import com.fds.nsw.nghiepvu.modelImpl.HistorySyncVersionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistorySyncVersionPersistenceImpl extends BasePersistence {
	@Autowired
	HistorySyncVersionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<HistorySyncVersion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistorySyncVersionUtil} to access the history sync version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public HistorySyncVersion create(long id) {
		HistorySyncVersion historySyncVersion = new HistorySyncVersion();

		
		//historySyncVersion.setPrimaryKey(id);

		return historySyncVersion;
	}

	/**
	 * Removes the history sync version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the history sync version
	 * @return the history sync version that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion remove(long id)
		throws NoSuchHistorySyncVersionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the history sync version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the history sync version
	 * @return the history sync version that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public HistorySyncVersion remove(Serializable primaryKey)
		throws NoSuchHistorySyncVersionException, SystemException {
		

		try {
			

			HistorySyncVersion historySyncVersion = findByPrimaryKey(primaryKey);

			if (historySyncVersion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistorySyncVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(historySyncVersion);
			return historySyncVersion;
		}
		catch (NoSuchHistorySyncVersionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public HistorySyncVersion remove(HistorySyncVersion HistorySyncVersion) throws SystemException {
	removeImpl(HistorySyncVersion);	return HistorySyncVersion;
}

protected HistorySyncVersion removeImpl

(
		HistorySyncVersion historySyncVersion) throws SystemException {
		try {
			repository.delete(historySyncVersion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return historySyncVersion;
	}

	
	public HistorySyncVersion updateImpl(
		HistorySyncVersion historySyncVersion,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(historySyncVersion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return historySyncVersion;
	}

	
	public HistorySyncVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the history sync version with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException} if it could not be found.
	 *
	 * @param id the primary key of the history sync version
	 * @return the history sync version
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion findByPrimaryKey(long id)
		throws NoSuchHistorySyncVersionException, SystemException {
		HistorySyncVersion historySyncVersion = fetchByPrimaryKey(id);

		if (historySyncVersion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchHistorySyncVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return historySyncVersion;
	}

	/**
	 * Returns the history sync version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the history sync version
	 * @return the history sync version, or <code>null</code> if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public HistorySyncVersion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the history sync version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the history sync version
	 * @return the history sync version, or <code>null</code> if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion fetchByPrimaryKey(long id)
		throws SystemException {
		HistorySyncVersion historySyncVersion = null;

		

		if (historySyncVersion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<HistorySyncVersion> optional = repository.findById(id);
				if (optional.isPresent()) {
					historySyncVersion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return historySyncVersion;
	}

	/**
	 * Returns all the history sync versions where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the matching history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findBySyncVersion(String syncVersion)
		throws SystemException {
		return findBySyncVersion(syncVersion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the history sync versions where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of history sync versions
	 * @param end the upper bound of the range of history sync versions (not inclusive)
	 * @return the range of matching history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findBySyncVersion(String syncVersion,
		int start, int end) throws SystemException {
		return findBySyncVersion(syncVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the history sync versions where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of history sync versions
	 * @param end the upper bound of the range of history sync versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findBySyncVersion(String syncVersion,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<HistorySyncVersion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_HISTORYSYNCVERSION_WHERE);

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
				query.append(HistorySyncVersionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				list = (List<HistorySyncVersion>)queryFactory.getResultList(builder);
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
	 * Returns the first history sync version in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history sync version
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a matching history sync version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion findBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchHistorySyncVersionException, SystemException {
		HistorySyncVersion historySyncVersion = fetchBySyncVersion_First(syncVersion,
				orderByComparator);

		if (historySyncVersion != null) {
			return historySyncVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistorySyncVersionException(msg.toString());
	}

	/**
	 * Returns the first history sync version in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history sync version, or <code>null</code> if a matching history sync version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion fetchBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		List<HistorySyncVersion> list = findBySyncVersion(syncVersion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last history sync version in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history sync version
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a matching history sync version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion findBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchHistorySyncVersionException, SystemException {
		HistorySyncVersion historySyncVersion = fetchBySyncVersion_Last(syncVersion,
				orderByComparator);

		if (historySyncVersion != null) {
			return historySyncVersion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistorySyncVersionException(msg.toString());
	}

	/**
	 * Returns the last history sync version in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history sync version, or <code>null</code> if a matching history sync version could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion fetchBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySyncVersion(syncVersion);

		List<HistorySyncVersion> list = findBySyncVersion(syncVersion,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the history sync versions before and after the current history sync version in the ordered set where syncVersion = &#63;.
	 *
	 * @param id the primary key of the current history sync version
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next history sync version
	 * @throws vn.gt.dao.danhmuc.NoSuchHistorySyncVersionException if a history sync version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistorySyncVersion[] findBySyncVersion_PrevAndNext(long id,
		String syncVersion, OrderByComparator orderByComparator)
		throws NoSuchHistorySyncVersionException, SystemException {
		HistorySyncVersion historySyncVersion = findByPrimaryKey(id);

		

		try {
			

			HistorySyncVersion[] array = new HistorySyncVersion[3];

			array[0] = getBySyncVersion_PrevAndNext(
					historySyncVersion, syncVersion, orderByComparator, true);

			array[1] = historySyncVersion;

			array[2] = getBySyncVersion_PrevAndNext(
					historySyncVersion, syncVersion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected HistorySyncVersion getBySyncVersion_PrevAndNext(
		HistorySyncVersion historySyncVersion, String syncVersion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HISTORYSYNCVERSION_WHERE);

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
			query.append(HistorySyncVersionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (syncVersion != null) {
			builder.appendNamedParameterMap("syncVersion", syncVersion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historySyncVersion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<HistorySyncVersion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the history sync versions.
	 *
	 * @return the history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the history sync versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of history sync versions
	 * @param end the upper bound of the range of history sync versions (not inclusive)
	 * @return the range of history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the history sync versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of history sync versions
	 * @param end the upper bound of the range of history sync versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistorySyncVersion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<HistorySyncVersion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HISTORYSYNCVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTORYSYNCVERSION.concat(HistorySyncVersionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<HistorySyncVersion>) queryFactory.getResultList(builder);
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
	 * Removes all the history sync versions where syncVersion = &#63; from the database.
	 *
	 * @param syncVersion the sync version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySyncVersion(String syncVersion)
		throws SystemException {
		for (HistorySyncVersion historySyncVersion : findBySyncVersion(
				syncVersion)) {
			repository.delete(historySyncVersion);
		}
	}

	/**
	 * Removes all the history sync versions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HistorySyncVersion historySyncVersion : findAll()) {
			repository.delete(historySyncVersion);
		}
	}

	/**
	 * Returns the number of history sync versions where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the number of matching history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySyncVersion(String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORYSYNCVERSION_WHERE);

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
	 * Returns the number of history sync versions.
	 *
	 * @return the number of history sync versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_HISTORYSYNCVERSION).build();

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
	 * Initializes the history sync version persistence.
	 */
	private static final String _SQL_SELECT_HISTORYSYNCVERSION = "SELECT historySyncVersion FROM HistorySyncVersion historySyncVersion";
	private static final String _SQL_SELECT_HISTORYSYNCVERSION_WHERE = "SELECT historySyncVersion FROM HistorySyncVersion historySyncVersion WHERE ";
	private static final String _SQL_COUNT_HISTORYSYNCVERSION = "SELECT COUNT(historySyncVersion) FROM HistorySyncVersion historySyncVersion";
	private static final String _SQL_COUNT_HISTORYSYNCVERSION_WHERE = "SELECT COUNT(historySyncVersion) FROM HistorySyncVersion historySyncVersion WHERE ";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1 = "historySyncVersion.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2 = "historySyncVersion.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3 = "(historySyncVersion.syncVersion IS NULL OR historySyncVersion.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "historySyncVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistorySyncVersion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HistorySyncVersion exists with the key {";
	

	
}
