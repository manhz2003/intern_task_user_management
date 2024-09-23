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
import com.fds.nsw.nghiepvu.model.DmHistorySecurityLevel;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistorySecurityLevelRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistorySecurityLevelModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistorySecurityLevelPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistorySecurityLevelRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistorySecurityLevel> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistorySecurityLevelUtil} to access the dm history security level persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistorySecurityLevel create(int id) {
		DmHistorySecurityLevel dmHistorySecurityLevel = new DmHistorySecurityLevel();

		
		//dmHistorySecurityLevel.setPrimaryKey(id);

		return dmHistorySecurityLevel;
	}

	/**
	 * Removes the dm history security level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history security level
	 * @return the dm history security level that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel remove(int id)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history security level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history security level
	 * @return the dm history security level that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistorySecurityLevel remove(Serializable primaryKey)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		

		try {
			

			DmHistorySecurityLevel dmHistorySecurityLevel = findByPrimaryKey(primaryKey);

			if (dmHistorySecurityLevel == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistorySecurityLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistorySecurityLevel);
			return dmHistorySecurityLevel;
		}
		catch (NoSuchDmHistorySecurityLevelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistorySecurityLevel remove(DmHistorySecurityLevel DmHistorySecurityLevel) throws SystemException {
	removeImpl(DmHistorySecurityLevel);	return DmHistorySecurityLevel;
}

protected DmHistorySecurityLevel removeImpl

(
		DmHistorySecurityLevel dmHistorySecurityLevel)
		throws SystemException {
		try {
			repository.delete(dmHistorySecurityLevel);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistorySecurityLevel;
	}

	
	public DmHistorySecurityLevel updateImpl(
		DmHistorySecurityLevel dmHistorySecurityLevel,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistorySecurityLevel);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistorySecurityLevel;
	}

	
	public DmHistorySecurityLevel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history security level with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException} if it could not be found.
	 *
	 * @param id the primary key of the dm history security level
	 * @return the dm history security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel findByPrimaryKey(int id)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = fetchByPrimaryKey(id);

		if (dmHistorySecurityLevel == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistorySecurityLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistorySecurityLevel;
	}

	/**
	 * Returns the dm history security level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history security level
	 * @return the dm history security level, or <code>null</code> if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistorySecurityLevel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history security level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history security level
	 * @return the dm history security level, or <code>null</code> if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = null;

		

		if (dmHistorySecurityLevel == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistorySecurityLevel> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistorySecurityLevel = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistorySecurityLevel;
	}

	/**
	 * Returns all the dm history security levels where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @return the matching dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findBySecurityLevelCode(
		String securityLevelCode) throws SystemException {
		return findBySecurityLevelCode(securityLevelCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history security levels where securityLevelCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelCode the security level code
	 * @param start the lower bound of the range of dm history security levels
	 * @param end the upper bound of the range of dm history security levels (not inclusive)
	 * @return the range of matching dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findBySecurityLevelCode(
		String securityLevelCode, int start, int end) throws SystemException {
		return findBySecurityLevelCode(securityLevelCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history security levels where securityLevelCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelCode the security level code
	 * @param start the lower bound of the range of dm history security levels
	 * @param end the upper bound of the range of dm history security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findBySecurityLevelCode(
		String securityLevelCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistorySecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYSECURITYLEVEL_WHERE);

			if (securityLevelCode == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_1);
			}
			else {
				if (securityLevelCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistorySecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
				}

				list = (List<DmHistorySecurityLevel>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel findBySecurityLevelCode_First(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = fetchBySecurityLevelCode_First(securityLevelCode,
				orderByComparator);

		if (dmHistorySecurityLevel != null) {
			return dmHistorySecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelCode=");
		msg.append(securityLevelCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistorySecurityLevelException(msg.toString());
	}

	/**
	 * Returns the first dm history security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history security level, or <code>null</code> if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel fetchBySecurityLevelCode_First(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistorySecurityLevel> list = findBySecurityLevelCode(securityLevelCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel findBySecurityLevelCode_Last(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = fetchBySecurityLevelCode_Last(securityLevelCode,
				orderByComparator);

		if (dmHistorySecurityLevel != null) {
			return dmHistorySecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelCode=");
		msg.append(securityLevelCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistorySecurityLevelException(msg.toString());
	}

	/**
	 * Returns the last dm history security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history security level, or <code>null</code> if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel fetchBySecurityLevelCode_Last(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySecurityLevelCode(securityLevelCode);

		List<DmHistorySecurityLevel> list = findBySecurityLevelCode(securityLevelCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history security levels before and after the current dm history security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param id the primary key of the current dm history security level
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a dm history security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel[] findBySecurityLevelCode_PrevAndNext(
		int id, String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = findByPrimaryKey(id);

		

		try {
			

			DmHistorySecurityLevel[] array = new DmHistorySecurityLevel[3];

			array[0] = getBySecurityLevelCode_PrevAndNext(
					dmHistorySecurityLevel, securityLevelCode,
					orderByComparator, true);

			array[1] = dmHistorySecurityLevel;

			array[2] = getBySecurityLevelCode_PrevAndNext(
					dmHistorySecurityLevel, securityLevelCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistorySecurityLevel getBySecurityLevelCode_PrevAndNext(
		 DmHistorySecurityLevel dmHistorySecurityLevel,
		String securityLevelCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYSECURITYLEVEL_WHERE);

		if (securityLevelCode == null) {
			query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_1);
		}
		else {
			if (securityLevelCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_2);
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
			query.append(DmHistorySecurityLevelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (securityLevelCode != null) {
			builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistorySecurityLevel);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistorySecurityLevel> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history security level where securityLevelCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException} if it could not be found.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the matching dm history security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityLevelException if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel findBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = fetchBySecurityLevelCodeAndSyncVersion(securityLevelCode,
				syncVersion);

		if (dmHistorySecurityLevel == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("securityLevelCode=");
			msg.append(securityLevelCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistorySecurityLevelException(msg.toString());
		}

		return dmHistorySecurityLevel;
	}

	/**
	 * Returns the dm history security level where securityLevelCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the matching dm history security level, or <code>null</code> if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel fetchBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion) throws SystemException {
		return fetchBySecurityLevelCodeAndSyncVersion(securityLevelCode,
			syncVersion, true);
	}

	/**
	 * Returns the dm history security level where securityLevelCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history security level, or <code>null</code> if a matching dm history security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel fetchBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = null;
		if (dmHistorySecurityLevel == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSECURITYLEVEL_WHERE);

			if (securityLevelCode == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_1);
			}
			else {
				if (securityLevelCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistorySecurityLevelModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistorySecurityLevel.class).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistorySecurityLevel = (DmHistorySecurityLevel) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistorySecurityLevel;
	}

	/**
	 * Returns all the dm history security levels.
	 *
	 * @return the dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history security levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history security levels
	 * @param end the upper bound of the range of dm history security levels (not inclusive)
	 * @return the range of dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history security levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history security levels
	 * @param end the upper bound of the range of dm history security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityLevel> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistorySecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSECURITYLEVEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSECURITYLEVEL.concat(DmHistorySecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistorySecurityLevel>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history security levels where securityLevelCode = &#63; from the database.
	 *
	 * @param securityLevelCode the security level code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySecurityLevelCode(String securityLevelCode)
		throws SystemException {
		for (DmHistorySecurityLevel dmHistorySecurityLevel : findBySecurityLevelCode(
				securityLevelCode)) {
			repository.delete(dmHistorySecurityLevel);
		}
	}

	/**
	 * Removes the dm history security level where securityLevelCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the dm history security level that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityLevel removeBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion)
		throws NoSuchDmHistorySecurityLevelException, SystemException {
		DmHistorySecurityLevel dmHistorySecurityLevel = findBySecurityLevelCodeAndSyncVersion(securityLevelCode,
				syncVersion);

		repository.delete(dmHistorySecurityLevel);
			return dmHistorySecurityLevel;
	}

	/**
	 * Removes all the dm history security levels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistorySecurityLevel dmHistorySecurityLevel : findAll()) {
			repository.delete(dmHistorySecurityLevel);
		}
	}

	/**
	 * Returns the number of dm history security levels where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @return the number of matching dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySecurityLevelCode(String securityLevelCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYSECURITYLEVEL_WHERE);

			if (securityLevelCode == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_1);
			}
			else {
				if (securityLevelCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
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
	 * Returns the number of dm history security levels where securityLevelCode = &#63; and syncVersion = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSECURITYLEVEL_WHERE);

			if (securityLevelCode == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_1);
			}
			else {
				if (securityLevelCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
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
	 * Returns the number of dm history security levels.
	 *
	 * @return the number of dm history security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSECURITYLEVEL).build();

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
	 * Initializes the dm history security level persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSECURITYLEVEL = "SELECT dmHistorySecurityLevel FROM DmHistorySecurityLevel dmHistorySecurityLevel";
	private static final String _SQL_SELECT_DMHISTORYSECURITYLEVEL_WHERE = "SELECT dmHistorySecurityLevel FROM DmHistorySecurityLevel dmHistorySecurityLevel WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSECURITYLEVEL = "SELECT COUNT(dmHistorySecurityLevel) FROM DmHistorySecurityLevel dmHistorySecurityLevel";
	private static final String _SQL_COUNT_DMHISTORYSECURITYLEVEL_WHERE = "SELECT COUNT(dmHistorySecurityLevel) FROM DmHistorySecurityLevel dmHistorySecurityLevel WHERE ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_1 =
		"dmHistorySecurityLevel.securityLevelCode IS NULL";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_2 =
		"dmHistorySecurityLevel.securityLevelCode =:securityLevelCode";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_3 =
		"(dmHistorySecurityLevel.securityLevelCode IS NULL OR dmHistorySecurityLevel.securityLevelCode =:securityLevelCode)";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_1 =
		"dmHistorySecurityLevel.securityLevelCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_2 =
		"dmHistorySecurityLevel.securityLevelCode =:securityLevelCode AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_3 =
		"(dmHistorySecurityLevel.securityLevelCode IS NULL OR dmHistorySecurityLevel.securityLevelCode =:securityLevelCode) AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistorySecurityLevel.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistorySecurityLevel.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistorySecurityLevel.syncVersion IS NULL OR dmHistorySecurityLevel.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistorySecurityLevel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistorySecurityLevel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistorySecurityLevel exists with the key {";
	

	
}
