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
import com.fds.nsw.nghiepvu.model.DmSecurityLevel;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmSecurityLevelRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmSecurityLevelModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmSecurityLevelPersistenceImpl extends BasePersistence {
	@Autowired
	DmSecurityLevelRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmSecurityLevel> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmSecurityLevelUtil} to access the dm security level persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmSecurityLevel create(int id) {
		DmSecurityLevel dmSecurityLevel = new DmSecurityLevel();

		
		//dmSecurityLevel.setPrimaryKey(id);

		return dmSecurityLevel;
	}

	/**
	 * Removes the dm security level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm security level
	 * @return the dm security level that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel remove(int id)
		throws NoSuchDmSecurityLevelException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm security level with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm security level
	 * @return the dm security level that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmSecurityLevel remove(Serializable primaryKey)
		throws NoSuchDmSecurityLevelException, SystemException {
		

		try {
			

			DmSecurityLevel dmSecurityLevel = findByPrimaryKey(primaryKey);

			if (dmSecurityLevel == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmSecurityLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmSecurityLevel);
			return dmSecurityLevel;
		}
		catch (NoSuchDmSecurityLevelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmSecurityLevel remove(DmSecurityLevel DmSecurityLevel) throws SystemException {
	removeImpl(DmSecurityLevel);	return DmSecurityLevel;
}

protected DmSecurityLevel removeImpl

(DmSecurityLevel dmSecurityLevel)
		throws SystemException {
		try {
			repository.delete(dmSecurityLevel);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmSecurityLevel;
	}

	
	public DmSecurityLevel updateImpl(
		DmSecurityLevel dmSecurityLevel, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmSecurityLevel);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmSecurityLevel;
	}

	
	public DmSecurityLevel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm security level with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException} if it could not be found.
	 *
	 * @param id the primary key of the dm security level
	 * @return the dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findByPrimaryKey(int id)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchByPrimaryKey(id);

		if (dmSecurityLevel == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmSecurityLevelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmSecurityLevel;
	}

	/**
	 * Returns the dm security level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm security level
	 * @return the dm security level, or <code>null</code> if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmSecurityLevel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm security level with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm security level
	 * @return the dm security level, or <code>null</code> if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchByPrimaryKey(int id) throws SystemException {
		DmSecurityLevel dmSecurityLevel = null;

		

		if (dmSecurityLevel == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmSecurityLevel> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmSecurityLevel = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmSecurityLevel;
	}

	/**
	 * Returns all the dm security levels where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @return the matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findBySecurityLevelCode(
		String securityLevelCode) throws SystemException {
		return findBySecurityLevelCode(securityLevelCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm security levels where securityLevelCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelCode the security level code
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @return the range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findBySecurityLevelCode(
		String securityLevelCode, int start, int end) throws SystemException {
		return findBySecurityLevelCode(securityLevelCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm security levels where securityLevelCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelCode the security level code
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findBySecurityLevelCode(
		String securityLevelCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmSecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

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
				query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
				}

				list = (List<DmSecurityLevel>)queryFactory.getResultList(builder);
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
	 * Returns the first dm security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findBySecurityLevelCode_First(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchBySecurityLevelCode_First(securityLevelCode,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelCode=");
		msg.append(securityLevelCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the first dm security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchBySecurityLevelCode_First(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmSecurityLevel> list = findBySecurityLevelCode(securityLevelCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findBySecurityLevelCode_Last(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchBySecurityLevelCode_Last(securityLevelCode,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelCode=");
		msg.append(securityLevelCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchBySecurityLevelCode_Last(
		String securityLevelCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countBySecurityLevelCode(securityLevelCode);

		List<DmSecurityLevel> list = findBySecurityLevelCode(securityLevelCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm security levels before and after the current dm security level in the ordered set where securityLevelCode = &#63;.
	 *
	 * @param id the primary key of the current dm security level
	 * @param securityLevelCode the security level code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel[] findBySecurityLevelCode_PrevAndNext(int id,
		String securityLevelCode, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = findByPrimaryKey(id);

		

		try {
			

			DmSecurityLevel[] array = new DmSecurityLevel[3];

			array[0] = getBySecurityLevelCode_PrevAndNext(
					dmSecurityLevel, securityLevelCode, orderByComparator, true);

			array[1] = dmSecurityLevel;

			array[2] = getBySecurityLevelCode_PrevAndNext(
					dmSecurityLevel, securityLevelCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmSecurityLevel getBySecurityLevelCode_PrevAndNext(
		 DmSecurityLevel dmSecurityLevel,
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

		query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

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
			query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (securityLevelCode != null) {
			builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmSecurityLevel);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmSecurityLevel> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm security level where securityLevelCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException} if it could not be found.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchBySecurityLevelCodeAndSyncVersion(securityLevelCode,
				syncVersion);

		if (dmSecurityLevel == null) {
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

			throw new NoSuchDmSecurityLevelException(msg.toString());
		}

		return dmSecurityLevel;
	}

	/**
	 * Returns the dm security level where securityLevelCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion) throws SystemException {
		return fetchBySecurityLevelCodeAndSyncVersion(securityLevelCode,
			syncVersion, true);
	}

	/**
	 * Returns the dm security level where securityLevelCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmSecurityLevel dmSecurityLevel = null;
		if (dmSecurityLevel == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

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

			query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmSecurityLevel.class).build();

				

				if (securityLevelCode != null) {
					builder.appendNamedParameterMap("securityLevelCode", securityLevelCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmSecurityLevel = (DmSecurityLevel) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmSecurityLevel;
	}

	/**
	 * Returns all the dm security levels where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @return the matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevel(String securityLevel)
		throws SystemException {
		return findByF_securityLevel(securityLevel, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm security levels where securityLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevel the security level
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @return the range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevel(String securityLevel,
		int start, int end) throws SystemException {
		return findByF_securityLevel(securityLevel, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm security levels where securityLevel = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevel the security level
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevel(String securityLevel,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmSecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

			if (securityLevel == null) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_1);
			}
			else {
				if (securityLevel.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (securityLevel != null) {
					builder.appendNamedParameterMap("securityLevel", securityLevel);
				}

				list = (List<DmSecurityLevel>)queryFactory.getResultList(builder);
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
	 * Returns the first dm security level in the ordered set where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findByF_securityLevel_First(String securityLevel,
		OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchByF_securityLevel_First(securityLevel,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevel=");
		msg.append(securityLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the first dm security level in the ordered set where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchByF_securityLevel_First(String securityLevel,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmSecurityLevel> list = findByF_securityLevel(securityLevel, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findByF_securityLevel_Last(String securityLevel,
		OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchByF_securityLevel_Last(securityLevel,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevel=");
		msg.append(securityLevel);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchByF_securityLevel_Last(String securityLevel,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_securityLevel(securityLevel);

		List<DmSecurityLevel> list = findByF_securityLevel(securityLevel,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm security levels before and after the current dm security level in the ordered set where securityLevel = &#63;.
	 *
	 * @param id the primary key of the current dm security level
	 * @param securityLevel the security level
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel[] findByF_securityLevel_PrevAndNext(int id,
		String securityLevel, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = findByPrimaryKey(id);

		

		try {
			

			DmSecurityLevel[] array = new DmSecurityLevel[3];

			array[0] = getByF_securityLevel_PrevAndNext(
					dmSecurityLevel, securityLevel, orderByComparator, true);

			array[1] = dmSecurityLevel;

			array[2] = getByF_securityLevel_PrevAndNext(
					dmSecurityLevel, securityLevel, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmSecurityLevel getByF_securityLevel_PrevAndNext(
		 DmSecurityLevel dmSecurityLevel, String securityLevel,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

		if (securityLevel == null) {
			query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_1);
		}
		else {
			if (securityLevel.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_2);
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
			query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (securityLevel != null) {
			builder.appendNamedParameterMap("securityLevel", securityLevel);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmSecurityLevel);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmSecurityLevel> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm security levels where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @return the matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevelName(
		String securityLevelName) throws SystemException {
		return findByF_securityLevelName(securityLevelName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm security levels where securityLevelName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelName the security level name
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @return the range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevelName(
		String securityLevelName, int start, int end) throws SystemException {
		return findByF_securityLevelName(securityLevelName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm security levels where securityLevelName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param securityLevelName the security level name
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findByF_securityLevelName(
		String securityLevelName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmSecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

			if (securityLevelName == null) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_1);
			}
			else {
				if (securityLevelName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (securityLevelName != null) {
					builder.appendNamedParameterMap("securityLevelName", securityLevelName);
				}

				list = (List<DmSecurityLevel>)queryFactory.getResultList(builder);
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
	 * Returns the first dm security level in the ordered set where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findByF_securityLevelName_First(
		String securityLevelName, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchByF_securityLevelName_First(securityLevelName,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelName=");
		msg.append(securityLevelName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the first dm security level in the ordered set where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchByF_securityLevelName_First(
		String securityLevelName, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmSecurityLevel> list = findByF_securityLevelName(securityLevelName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel findByF_securityLevelName_Last(
		String securityLevelName, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = fetchByF_securityLevelName_Last(securityLevelName,
				orderByComparator);

		if (dmSecurityLevel != null) {
			return dmSecurityLevel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("securityLevelName=");
		msg.append(securityLevelName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmSecurityLevelException(msg.toString());
	}

	/**
	 * Returns the last dm security level in the ordered set where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm security level, or <code>null</code> if a matching dm security level could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel fetchByF_securityLevelName_Last(
		String securityLevelName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_securityLevelName(securityLevelName);

		List<DmSecurityLevel> list = findByF_securityLevelName(securityLevelName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm security levels before and after the current dm security level in the ordered set where securityLevelName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm security level
	 * @param securityLevelName the security level name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm security level
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSecurityLevelException if a dm security level with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel[] findByF_securityLevelName_PrevAndNext(int id,
		String securityLevelName, OrderByComparator orderByComparator)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = findByPrimaryKey(id);

		

		try {
			

			DmSecurityLevel[] array = new DmSecurityLevel[3];

			array[0] = getByF_securityLevelName_PrevAndNext(
					dmSecurityLevel, securityLevelName, orderByComparator, true);

			array[1] = dmSecurityLevel;

			array[2] = getByF_securityLevelName_PrevAndNext(
					dmSecurityLevel, securityLevelName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmSecurityLevel getByF_securityLevelName_PrevAndNext(
		 DmSecurityLevel dmSecurityLevel,
		String securityLevelName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSECURITYLEVEL_WHERE);

		if (securityLevelName == null) {
			query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_1);
		}
		else {
			if (securityLevelName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_2);
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
			query.append(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (securityLevelName != null) {
			builder.appendNamedParameterMap("securityLevelName", securityLevelName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmSecurityLevel);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmSecurityLevel> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm security levels.
	 *
	 * @return the dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm security levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @return the range of dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm security levels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm security levels
	 * @param end the upper bound of the range of dm security levels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSecurityLevel> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmSecurityLevel> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMSECURITYLEVEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMSECURITYLEVEL.concat(DmSecurityLevelModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmSecurityLevel>) queryFactory.getResultList(builder);
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
	 * Removes all the dm security levels where securityLevelCode = &#63; from the database.
	 *
	 * @param securityLevelCode the security level code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySecurityLevelCode(String securityLevelCode)
		throws SystemException {
		for (DmSecurityLevel dmSecurityLevel : findBySecurityLevelCode(
				securityLevelCode)) {
			repository.delete(dmSecurityLevel);
		}
	}

	/**
	 * Removes the dm security level where securityLevelCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the dm security level that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmSecurityLevel removeBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion)
		throws NoSuchDmSecurityLevelException, SystemException {
		DmSecurityLevel dmSecurityLevel = findBySecurityLevelCodeAndSyncVersion(securityLevelCode,
				syncVersion);

		repository.delete(dmSecurityLevel);
			return dmSecurityLevel;
	}

	/**
	 * Removes all the dm security levels where securityLevel = &#63; from the database.
	 *
	 * @param securityLevel the security level
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_securityLevel(String securityLevel)
		throws SystemException {
		for (DmSecurityLevel dmSecurityLevel : findByF_securityLevel(
				securityLevel)) {
			repository.delete(dmSecurityLevel);
		}
	}

	/**
	 * Removes all the dm security levels where securityLevelName LIKE &#63; from the database.
	 *
	 * @param securityLevelName the security level name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_securityLevelName(String securityLevelName)
		throws SystemException {
		for (DmSecurityLevel dmSecurityLevel : findByF_securityLevelName(
				securityLevelName)) {
			repository.delete(dmSecurityLevel);
		}
	}

	/**
	 * Removes all the dm security levels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmSecurityLevel dmSecurityLevel : findAll()) {
			repository.delete(dmSecurityLevel);
		}
	}

	/**
	 * Returns the number of dm security levels where securityLevelCode = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @return the number of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySecurityLevelCode(String securityLevelCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSECURITYLEVEL_WHERE);

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
	 * Returns the number of dm security levels where securityLevelCode = &#63; and syncVersion = &#63;.
	 *
	 * @param securityLevelCode the security level code
	 * @param syncVersion the sync version
	 * @return the number of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySecurityLevelCodeAndSyncVersion(
		String securityLevelCode, String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMSECURITYLEVEL_WHERE);

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
	 * Returns the number of dm security levels where securityLevel = &#63;.
	 *
	 * @param securityLevel the security level
	 * @return the number of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_securityLevel(String securityLevel)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSECURITYLEVEL_WHERE);

			if (securityLevel == null) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_1);
			}
			else {
				if (securityLevel.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (securityLevel != null) {
					builder.appendNamedParameterMap("securityLevel", securityLevel);
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
	 * Returns the number of dm security levels where securityLevelName LIKE &#63;.
	 *
	 * @param securityLevelName the security level name
	 * @return the number of matching dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_securityLevelName(String securityLevelName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSECURITYLEVEL_WHERE);

			if (securityLevelName == null) {
				query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_1);
			}
			else {
				if (securityLevelName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (securityLevelName != null) {
					builder.appendNamedParameterMap("securityLevelName", securityLevelName);
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
	 * Returns the number of dm security levels.
	 *
	 * @return the number of dm security levels
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMSECURITYLEVEL).build();

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
	 * Initializes the dm security level persistence.
	 */
	private static final String _SQL_SELECT_DMSECURITYLEVEL = "SELECT dmSecurityLevel FROM DmSecurityLevel dmSecurityLevel";
	private static final String _SQL_SELECT_DMSECURITYLEVEL_WHERE = "SELECT dmSecurityLevel FROM DmSecurityLevel dmSecurityLevel WHERE ";
	private static final String _SQL_COUNT_DMSECURITYLEVEL = "SELECT COUNT(dmSecurityLevel) FROM DmSecurityLevel dmSecurityLevel";
	private static final String _SQL_COUNT_DMSECURITYLEVEL_WHERE = "SELECT COUNT(dmSecurityLevel) FROM DmSecurityLevel dmSecurityLevel WHERE ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_1 =
		"dmSecurityLevel.securityLevelCode IS NULL";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_2 =
		"dmSecurityLevel.securityLevelCode =:securityLevelCode";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODE_SECURITYLEVELCODE_3 =
		"(dmSecurityLevel.securityLevelCode IS NULL OR dmSecurityLevel.securityLevelCode =:securityLevelCode)";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_1 =
		"dmSecurityLevel.securityLevelCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_2 =
		"dmSecurityLevel.securityLevelCode =:securityLevelCode AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SECURITYLEVELCODE_3 =
		"(dmSecurityLevel.securityLevelCode IS NULL OR dmSecurityLevel.securityLevelCode =:securityLevelCode) AND ";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmSecurityLevel.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmSecurityLevel.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SECURITYLEVELCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmSecurityLevel.syncVersion IS NULL OR dmSecurityLevel.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_1 = "dmSecurityLevel.securityLevel IS NULL";
	private static final String _FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_2 = "dmSecurityLevel.securityLevel =:securityLevel";
	private static final String _FINDER_COLUMN_F_SECURITYLEVEL_SECURITYLEVEL_3 = "(dmSecurityLevel.securityLevel IS NULL OR dmSecurityLevel.securityLevel =:securityLevel)";
	private static final String _FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_1 =
		"dmSecurityLevel.securityLevelName LIKE NULL";
	private static final String _FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_2 =
		"dmSecurityLevel.securityLevelName LIKE :securityLevelName";
	private static final String _FINDER_COLUMN_F_SECURITYLEVELNAME_SECURITYLEVELNAME_3 =
		"(dmSecurityLevel.securityLevelName IS NULL OR dmSecurityLevel.securityLevelName LIKE :securityLevelName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmSecurityLevel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmSecurityLevel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmSecurityLevel exists with the key {";
	

	
}
