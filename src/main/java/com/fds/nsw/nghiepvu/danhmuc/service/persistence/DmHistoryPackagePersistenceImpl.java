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
import com.fds.nsw.nghiepvu.model.DmHistoryPackage;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPackageRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPackageModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPackagePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPackageRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPackage> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPackageUtil} to access the dm history package persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPackage create(int id) {
		DmHistoryPackage dmHistoryPackage = new DmHistoryPackage();

		
		//dmHistoryPackage.setPrimaryKey(id);

		return dmHistoryPackage;
	}

	/**
	 * Removes the dm history package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history package
	 * @return the dm history package that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage remove(int id)
		throws NoSuchDmHistoryPackageException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history package
	 * @return the dm history package that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPackage remove(Serializable primaryKey)
		throws NoSuchDmHistoryPackageException, SystemException {
		

		try {
			

			DmHistoryPackage dmHistoryPackage = findByPrimaryKey(primaryKey);

			if (dmHistoryPackage == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPackage);
			return dmHistoryPackage;
		}
		catch (NoSuchDmHistoryPackageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPackage remove(DmHistoryPackage DmHistoryPackage) throws SystemException {
	removeImpl(DmHistoryPackage);	return DmHistoryPackage;
}

protected DmHistoryPackage removeImpl

(DmHistoryPackage dmHistoryPackage)
		throws SystemException {
		try {
			repository.delete(dmHistoryPackage);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPackage;
	}

	
	public DmHistoryPackage updateImpl(
		DmHistoryPackage dmHistoryPackage, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPackage);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPackage;
	}

	
	public DmHistoryPackage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history package with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException} if it could not be found.
	 *
	 * @param id the primary key of the dm history package
	 * @return the dm history package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage findByPrimaryKey(int id)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = fetchByPrimaryKey(id);

		if (dmHistoryPackage == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryPackage;
	}

	/**
	 * Returns the dm history package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history package
	 * @return the dm history package, or <code>null</code> if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPackage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history package
	 * @return the dm history package, or <code>null</code> if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryPackage dmHistoryPackage = null;

		

		if (dmHistoryPackage == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPackage> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryPackage = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPackage;
	}

	/**
	 * Returns all the dm history packages where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @return the matching dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findByPackageCode(String packageCode)
		throws SystemException {
		return findByPackageCode(packageCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history packages where packageCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageCode the package code
	 * @param start the lower bound of the range of dm history packages
	 * @param end the upper bound of the range of dm history packages (not inclusive)
	 * @return the range of matching dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findByPackageCode(String packageCode,
		int start, int end) throws SystemException {
		return findByPackageCode(packageCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history packages where packageCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageCode the package code
	 * @param start the lower bound of the range of dm history packages
	 * @param end the upper bound of the range of dm history packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findByPackageCode(String packageCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryPackage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPACKAGE_WHERE);

			if (packageCode == null) {
				query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_1);
			}
			else {
				if (packageCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryPackageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (packageCode != null) {
					builder.appendNamedParameterMap("packageCode", packageCode);
				}

				list = (List<DmHistoryPackage>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage findByPackageCode_First(String packageCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = fetchByPackageCode_First(packageCode,
				orderByComparator);

		if (dmHistoryPackage != null) {
			return dmHistoryPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageCode=");
		msg.append(packageCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPackageException(msg.toString());
	}

	/**
	 * Returns the first dm history package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history package, or <code>null</code> if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage fetchByPackageCode_First(String packageCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPackage> list = findByPackageCode(packageCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage findByPackageCode_Last(String packageCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = fetchByPackageCode_Last(packageCode,
				orderByComparator);

		if (dmHistoryPackage != null) {
			return dmHistoryPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageCode=");
		msg.append(packageCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPackageException(msg.toString());
	}

	/**
	 * Returns the last dm history package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history package, or <code>null</code> if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage fetchByPackageCode_Last(String packageCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPackageCode(packageCode);

		List<DmHistoryPackage> list = findByPackageCode(packageCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history packages before and after the current dm history package in the ordered set where packageCode = &#63;.
	 *
	 * @param id the primary key of the current dm history package
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a dm history package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage[] findByPackageCode_PrevAndNext(int id,
		String packageCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPackage[] array = new DmHistoryPackage[3];

			array[0] = getByPackageCode_PrevAndNext(dmHistoryPackage,
					packageCode, orderByComparator, true);

			array[1] = dmHistoryPackage;

			array[2] = getByPackageCode_PrevAndNext(dmHistoryPackage,
					packageCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPackage getByPackageCode_PrevAndNext(
		DmHistoryPackage dmHistoryPackage, String packageCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYPACKAGE_WHERE);

		if (packageCode == null) {
			query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_1);
		}
		else {
			if (packageCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_2);
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
			query.append(DmHistoryPackageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (packageCode != null) {
			builder.appendNamedParameterMap("packageCode", packageCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPackage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPackage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history package where packageCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException} if it could not be found.
	 *
	 * @param packageCode the package code
	 * @param syncVersion the sync version
	 * @return the matching dm history package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPackageException if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage findByPackageCodeAndSyncVersion(
		String packageCode, String syncVersion)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = fetchByPackageCodeAndSyncVersion(packageCode,
				syncVersion);

		if (dmHistoryPackage == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("packageCode=");
			msg.append(packageCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPackageException(msg.toString());
		}

		return dmHistoryPackage;
	}

	/**
	 * Returns the dm history package where packageCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param packageCode the package code
	 * @param syncVersion the sync version
	 * @return the matching dm history package, or <code>null</code> if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage fetchByPackageCodeAndSyncVersion(
		String packageCode, String syncVersion) throws SystemException {
		return fetchByPackageCodeAndSyncVersion(packageCode, syncVersion, true);
	}

	/**
	 * Returns the dm history package where packageCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param packageCode the package code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history package, or <code>null</code> if a matching dm history package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage fetchByPackageCodeAndSyncVersion(
		String packageCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPackage dmHistoryPackage = null;
		if (dmHistoryPackage == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPACKAGE_WHERE);

			if (packageCode == null) {
				query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_1);
			}
			else {
				if (packageCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPackageModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPackage.class).build();

				

				if (packageCode != null) {
					builder.appendNamedParameterMap("packageCode", packageCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryPackage = (DmHistoryPackage) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPackage;
	}

	/**
	 * Returns all the dm history packages.
	 *
	 * @return the dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history packages
	 * @param end the upper bound of the range of dm history packages (not inclusive)
	 * @return the range of dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history packages
	 * @param end the upper bound of the range of dm history packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPackage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPackage> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPACKAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPACKAGE.concat(DmHistoryPackageModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPackage>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history packages where packageCode = &#63; from the database.
	 *
	 * @param packageCode the package code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPackageCode(String packageCode)
		throws SystemException {
		for (DmHistoryPackage dmHistoryPackage : findByPackageCode(packageCode)) {
			repository.delete(dmHistoryPackage);
		}
	}

	/**
	 * Removes the dm history package where packageCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param packageCode the package code
	 * @param syncVersion the sync version
	 * @return the dm history package that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPackage removeByPackageCodeAndSyncVersion(
		String packageCode, String syncVersion)
		throws NoSuchDmHistoryPackageException, SystemException {
		DmHistoryPackage dmHistoryPackage = findByPackageCodeAndSyncVersion(packageCode,
				syncVersion);

		repository.delete(dmHistoryPackage);
			return dmHistoryPackage;
	}

	/**
	 * Removes all the dm history packages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPackage dmHistoryPackage : findAll()) {
			repository.delete(dmHistoryPackage);
		}
	}

	/**
	 * Returns the number of dm history packages where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @return the number of matching dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPackageCode(String packageCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPACKAGE_WHERE);

			if (packageCode == null) {
				query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_1);
			}
			else {
				if (packageCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODE_PACKAGECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (packageCode != null) {
					builder.appendNamedParameterMap("packageCode", packageCode);
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
	 * Returns the number of dm history packages where packageCode = &#63; and syncVersion = &#63;.
	 *
	 * @param packageCode the package code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPackageCodeAndSyncVersion(String packageCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPACKAGE_WHERE);

			if (packageCode == null) {
				query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_1);
			}
			else {
				if (packageCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (packageCode != null) {
					builder.appendNamedParameterMap("packageCode", packageCode);
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
	 * Returns the number of dm history packages.
	 *
	 * @return the number of dm history packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPACKAGE).build();

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
	 * Initializes the dm history package persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPACKAGE = "SELECT dmHistoryPackage FROM DmHistoryPackage dmHistoryPackage";
	private static final String _SQL_SELECT_DMHISTORYPACKAGE_WHERE = "SELECT dmHistoryPackage FROM DmHistoryPackage dmHistoryPackage WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPACKAGE = "SELECT COUNT(dmHistoryPackage) FROM DmHistoryPackage dmHistoryPackage";
	private static final String _SQL_COUNT_DMHISTORYPACKAGE_WHERE = "SELECT COUNT(dmHistoryPackage) FROM DmHistoryPackage dmHistoryPackage WHERE ";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_1 = "dmHistoryPackage.packageCode IS NULL";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_2 = "dmHistoryPackage.packageCode =:packageCode";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_3 = "(dmHistoryPackage.packageCode IS NULL OR dmHistoryPackage.packageCode =:packageCode)";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_1 =
		"dmHistoryPackage.packageCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_2 =
		"dmHistoryPackage.packageCode =:packageCode AND ";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_PACKAGECODE_3 =
		"(dmHistoryPackage.packageCode IS NULL OR dmHistoryPackage.packageCode =:packageCode) AND ";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPackage.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPackage.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PACKAGECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPackage.syncVersion IS NULL OR dmHistoryPackage.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPackage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPackage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPackage exists with the key {";
	

	
}
