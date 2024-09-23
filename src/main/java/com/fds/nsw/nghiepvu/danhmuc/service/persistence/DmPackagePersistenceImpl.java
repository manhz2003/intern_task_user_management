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
import com.fds.nsw.nghiepvu.model.DmPackage;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPackageRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPackageModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPackagePersistenceImpl extends BasePersistence {
	@Autowired
	DmPackageRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPackage> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPackageUtil} to access the dm package persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPackage create(int id) {
		DmPackage dmPackage = new DmPackage();

		
		//dmPackage.setPrimaryKey(id);

		return dmPackage;
	}

	/**
	 * Removes the dm package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm package
	 * @return the dm package that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage remove(int id)
		throws NoSuchDmPackageException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm package with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm package
	 * @return the dm package that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPackage remove(Serializable primaryKey)
		throws NoSuchDmPackageException, SystemException {
		

		try {
			

			DmPackage dmPackage = findByPrimaryKey(primaryKey);

			if (dmPackage == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPackage);
			return dmPackage;
		}
		catch (NoSuchDmPackageException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPackage remove(DmPackage DmPackage) throws SystemException {
	removeImpl(DmPackage);	return DmPackage;
}

protected DmPackage removeImpl

(DmPackage dmPackage)
		throws SystemException {
		try {
			repository.delete(dmPackage);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPackage;
	}

	
	public DmPackage updateImpl(DmPackage dmPackage,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmPackage);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPackage;
	}

	
	public DmPackage findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm package with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPackageException} if it could not be found.
	 *
	 * @param id the primary key of the dm package
	 * @return the dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage findByPrimaryKey(int id)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = fetchByPrimaryKey(id);

		if (dmPackage == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPackageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPackage;
	}

	/**
	 * Returns the dm package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm package
	 * @return the dm package, or <code>null</code> if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPackage fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm package with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm package
	 * @return the dm package, or <code>null</code> if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage fetchByPrimaryKey(int id) throws SystemException {
		DmPackage dmPackage = null;

		

		if (dmPackage == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPackage> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPackage = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPackage;
	}

	/**
	 * Returns all the dm packages where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @return the matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByPackageCode(String packageCode)
		throws SystemException {
		return findByPackageCode(packageCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm packages where packageCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageCode the package code
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @return the range of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByPackageCode(String packageCode, int start,
		int end) throws SystemException {
		return findByPackageCode(packageCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm packages where packageCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageCode the package code
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByPackageCode(String packageCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmPackage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPACKAGE_WHERE);

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
				query.append(DmPackageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (packageCode != null) {
					builder.appendNamedParameterMap("packageCode", packageCode);
				}

				list = (List<DmPackage>)queryFactory.getResultList(builder);
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
	 * Returns the first dm package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage findByPackageCode_First(String packageCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = fetchByPackageCode_First(packageCode,
				orderByComparator);

		if (dmPackage != null) {
			return dmPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageCode=");
		msg.append(packageCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPackageException(msg.toString());
	}

	/**
	 * Returns the first dm package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm package, or <code>null</code> if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage fetchByPackageCode_First(String packageCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPackage> list = findByPackageCode(packageCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage findByPackageCode_Last(String packageCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = fetchByPackageCode_Last(packageCode,
				orderByComparator);

		if (dmPackage != null) {
			return dmPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageCode=");
		msg.append(packageCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPackageException(msg.toString());
	}

	/**
	 * Returns the last dm package in the ordered set where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm package, or <code>null</code> if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage fetchByPackageCode_Last(String packageCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPackageCode(packageCode);

		List<DmPackage> list = findByPackageCode(packageCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm packages before and after the current dm package in the ordered set where packageCode = &#63;.
	 *
	 * @param id the primary key of the current dm package
	 * @param packageCode the package code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage[] findByPackageCode_PrevAndNext(int id,
		String packageCode, OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = findByPrimaryKey(id);

		

		try {
			

			DmPackage[] array = new DmPackage[3];

			array[0] = getByPackageCode_PrevAndNext(dmPackage,
					packageCode, orderByComparator, true);

			array[1] = dmPackage;

			array[2] = getByPackageCode_PrevAndNext(dmPackage,
					packageCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPackage getByPackageCode_PrevAndNext(
		DmPackage dmPackage, String packageCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPACKAGE_WHERE);

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
			query.append(DmPackageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (packageCode != null) {
			builder.appendNamedParameterMap("packageCode", packageCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPackage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPackage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm packages where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @return the matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByF_packgageNameVN(String packageNameVN)
		throws SystemException {
		return findByF_packgageNameVN(packageNameVN, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm packages where packageNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageNameVN the package name v n
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @return the range of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByF_packgageNameVN(String packageNameVN,
		int start, int end) throws SystemException {
		return findByF_packgageNameVN(packageNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm packages where packageNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param packageNameVN the package name v n
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findByF_packgageNameVN(String packageNameVN,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPackage> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPACKAGE_WHERE);

			if (packageNameVN == null) {
				query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_1);
			}
			else {
				if (packageNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPackageModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (packageNameVN != null) {
					builder.appendNamedParameterMap("packageNameVN", packageNameVN);
				}

				list = (List<DmPackage>)queryFactory.getResultList(builder);
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
	 * Returns the first dm package in the ordered set where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage findByF_packgageNameVN_First(String packageNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = fetchByF_packgageNameVN_First(packageNameVN,
				orderByComparator);

		if (dmPackage != null) {
			return dmPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageNameVN=");
		msg.append(packageNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPackageException(msg.toString());
	}

	/**
	 * Returns the first dm package in the ordered set where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm package, or <code>null</code> if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage fetchByF_packgageNameVN_First(String packageNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPackage> list = findByF_packgageNameVN(packageNameVN, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm package in the ordered set where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage findByF_packgageNameVN_Last(String packageNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = fetchByF_packgageNameVN_Last(packageNameVN,
				orderByComparator);

		if (dmPackage != null) {
			return dmPackage;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("packageNameVN=");
		msg.append(packageNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPackageException(msg.toString());
	}

	/**
	 * Returns the last dm package in the ordered set where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm package, or <code>null</code> if a matching dm package could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage fetchByF_packgageNameVN_Last(String packageNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_packgageNameVN(packageNameVN);

		List<DmPackage> list = findByF_packgageNameVN(packageNameVN, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm packages before and after the current dm package in the ordered set where packageNameVN LIKE &#63;.
	 *
	 * @param id the primary key of the current dm package
	 * @param packageNameVN the package name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm package
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPackageException if a dm package with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPackage[] findByF_packgageNameVN_PrevAndNext(int id,
		String packageNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmPackageException, SystemException {
		DmPackage dmPackage = findByPrimaryKey(id);

		

		try {
			

			DmPackage[] array = new DmPackage[3];

			array[0] = getByF_packgageNameVN_PrevAndNext(dmPackage,
					packageNameVN, orderByComparator, true);

			array[1] = dmPackage;

			array[2] = getByF_packgageNameVN_PrevAndNext(dmPackage,
					packageNameVN, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPackage getByF_packgageNameVN_PrevAndNext(
		DmPackage dmPackage, String packageNameVN,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPACKAGE_WHERE);

		if (packageNameVN == null) {
			query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_1);
		}
		else {
			if (packageNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_2);
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
			query.append(DmPackageModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (packageNameVN != null) {
			builder.appendNamedParameterMap("packageNameVN", packageNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPackage);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPackage> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm packages.
	 *
	 * @return the dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @return the range of dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm packages.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm packages
	 * @param end the upper bound of the range of dm packages (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPackage> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPackage> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPACKAGE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPACKAGE.concat(DmPackageModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPackage>) queryFactory.getResultList(builder);
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
	 * Removes all the dm packages where packageCode = &#63; from the database.
	 *
	 * @param packageCode the package code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPackageCode(String packageCode)
		throws SystemException {
		for (DmPackage dmPackage : findByPackageCode(packageCode)) {
			repository.delete(dmPackage);
		}
	}

	/**
	 * Removes all the dm packages where packageNameVN LIKE &#63; from the database.
	 *
	 * @param packageNameVN the package name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_packgageNameVN(String packageNameVN)
		throws SystemException {
		for (DmPackage dmPackage : findByF_packgageNameVN(packageNameVN)) {
			repository.delete(dmPackage);
		}
	}

	/**
	 * Removes all the dm packages from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPackage dmPackage : findAll()) {
			repository.delete(dmPackage);
		}
	}

	/**
	 * Returns the number of dm packages where packageCode = &#63;.
	 *
	 * @param packageCode the package code
	 * @return the number of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPackageCode(String packageCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPACKAGE_WHERE);

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
	 * Returns the number of dm packages where packageNameVN LIKE &#63;.
	 *
	 * @param packageNameVN the package name v n
	 * @return the number of matching dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_packgageNameVN(String packageNameVN)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPACKAGE_WHERE);

			if (packageNameVN == null) {
				query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_1);
			}
			else {
				if (packageNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (packageNameVN != null) {
					builder.appendNamedParameterMap("packageNameVN", packageNameVN);
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
	 * Returns the number of dm packages.
	 *
	 * @return the number of dm packages
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPACKAGE).build();

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
	 * Initializes the dm package persistence.
	 */
	private static final String _SQL_SELECT_DMPACKAGE = "SELECT dmPackage FROM DmPackage dmPackage";
	private static final String _SQL_SELECT_DMPACKAGE_WHERE = "SELECT dmPackage FROM DmPackage dmPackage WHERE ";
	private static final String _SQL_COUNT_DMPACKAGE = "SELECT COUNT(dmPackage) FROM DmPackage dmPackage";
	private static final String _SQL_COUNT_DMPACKAGE_WHERE = "SELECT COUNT(dmPackage) FROM DmPackage dmPackage WHERE ";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_1 = "dmPackage.packageCode IS NULL";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_2 = "dmPackage.packageCode =:packageCode";
	private static final String _FINDER_COLUMN_PACKAGECODE_PACKAGECODE_3 = "(dmPackage.packageCode IS NULL OR dmPackage.packageCode =:packageCode)";
	private static final String _FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_1 = "dmPackage.packageNameVN LIKE NULL";
	private static final String _FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_2 = "dmPackage.packageNameVN LIKE :packageNameVN";
	private static final String _FINDER_COLUMN_F_PACKGAGENAMEVN_PACKAGENAMEVN_3 = "(dmPackage.packageNameVN IS NULL OR dmPackage.packageNameVN LIKE :packageNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPackage.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPackage exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPackage exists with the key {";
	

	
}
