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
import com.fds.nsw.nghiepvu.model.DmPortWharf;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPortWharfRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPortWharfModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPortWharfPersistenceImpl extends BasePersistence {
	@Autowired
	DmPortWharfRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortWharf> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPortWharfUtil} to access the dm port wharf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPortWharf create(int id) {
		DmPortWharf dmPortWharf = new DmPortWharf();

		
		//dmPortWharf.setPrimaryKey(id);

		return dmPortWharf;
	}

	/**
	 * Removes the dm port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port wharf
	 * @return the dm port wharf that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf remove(int id)
		throws NoSuchDmPortWharfException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm port wharf
	 * @return the dm port wharf that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortWharf remove(Serializable primaryKey)
		throws NoSuchDmPortWharfException, SystemException {
		

		try {
			

			DmPortWharf dmPortWharf = findByPrimaryKey(primaryKey);

			if (dmPortWharf == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPortWharfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPortWharf);
			return dmPortWharf;
		}
		catch (NoSuchDmPortWharfException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPortWharf remove(DmPortWharf DmPortWharf) throws SystemException {
	removeImpl(DmPortWharf);	return DmPortWharf;
}

protected DmPortWharf removeImpl

(DmPortWharf dmPortWharf)
		throws SystemException {
		try {
			repository.delete(dmPortWharf);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortWharf;
	}

	
	public DmPortWharf updateImpl(
		DmPortWharf dmPortWharf, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmPortWharf);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortWharf;
	}

	
	public DmPortWharf findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port wharf with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPortWharfException} if it could not be found.
	 *
	 * @param id the primary key of the dm port wharf
	 * @return the dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPrimaryKey(int id)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPrimaryKey(id);

		if (dmPortWharf == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPortWharfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPortWharf;
	}

	/**
	 * Returns the dm port wharf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm port wharf
	 * @return the dm port wharf, or <code>null</code> if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortWharf fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port wharf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm port wharf
	 * @return the dm port wharf, or <code>null</code> if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPrimaryKey(int id) throws SystemException {
		DmPortWharf dmPortWharf = null;

		

		if (dmPortWharf == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPortWharf> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPortWharf = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPortWharf;
	}

	/**
	 * Returns all the dm port wharfs where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @return the matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCode(String portWharfCode)
		throws SystemException {
		return findByPortWharfCode(portWharfCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port wharfs where portWharfCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @return the range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCode(String portWharfCode,
		int start, int end) throws SystemException {
		return findByPortWharfCode(portWharfCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port wharfs where portWharfCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCode(String portWharfCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

			if (portWharfCode == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_1);
			}
			else {
				if (portWharfCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
				}

				list = (List<DmPortWharf>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortWharfCode_First(String portWharfCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortWharfCode_First(portWharfCode,
				orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the first dm port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortWharfCode_First(String portWharfCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortWharf> list = findByPortWharfCode(portWharfCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortWharfCode_Last(String portWharfCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortWharfCode_Last(portWharfCode,
				orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortWharfCode_Last(String portWharfCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortWharfCode(portWharfCode);

		List<DmPortWharf> list = findByPortWharfCode(portWharfCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port wharfs before and after the current dm port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param id the primary key of the current dm port wharf
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf[] findByPortWharfCode_PrevAndNext(int id,
		String portWharfCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = findByPrimaryKey(id);

		

		try {
			

			DmPortWharf[] array = new DmPortWharf[3];

			array[0] = getByPortWharfCode_PrevAndNext(dmPortWharf,
					portWharfCode, orderByComparator, true);

			array[1] = dmPortWharf;

			array[2] = getByPortWharfCode_PrevAndNext(dmPortWharf,
					portWharfCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortWharf getByPortWharfCode_PrevAndNext(
		DmPortWharf dmPortWharf, String portWharfCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

		if (portWharfCode == null) {
			query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_1);
		}
		else {
			if (portWharfCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_2);
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
			query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portWharfCode != null) {
			builder.appendNamedParameterMap("portWharfCode", portWharfCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortWharf);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortWharf> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port wharfs where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion) throws SystemException {
		return findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port wharfs where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @return the range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion, int start, int end)
		throws SystemException {
		return findByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port wharfs where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

			if (portWharfCode == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_1);
			}
			else {
				if (portWharfCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				list = (List<DmPortWharf>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port wharf in the ordered set where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortWharfCodeAndSyncVersion_First(
		String portWharfCode, String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortWharfCodeAndSyncVersion_First(portWharfCode,
				syncVersion, orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(", syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the first dm port wharf in the ordered set where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortWharfCodeAndSyncVersion_First(
		String portWharfCode, String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortWharf> list = findByPortWharfCodeAndSyncVersion(portWharfCode,
				syncVersion, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortWharfCodeAndSyncVersion_Last(
		String portWharfCode, String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortWharfCodeAndSyncVersion_Last(portWharfCode,
				syncVersion, orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(", syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortWharfCodeAndSyncVersion_Last(
		String portWharfCode, String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortWharfCodeAndSyncVersion(portWharfCode,
				syncVersion);

		List<DmPortWharf> list = findByPortWharfCodeAndSyncVersion(portWharfCode,
				syncVersion, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port wharfs before and after the current dm port wharf in the ordered set where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param id the primary key of the current dm port wharf
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf[] findByPortWharfCodeAndSyncVersion_PrevAndNext(int id,
		String portWharfCode, String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = findByPrimaryKey(id);

		

		try {
			

			DmPortWharf[] array = new DmPortWharf[3];

			array[0] = getByPortWharfCodeAndSyncVersion_PrevAndNext(
					dmPortWharf, portWharfCode, syncVersion, orderByComparator,
					true);

			array[1] = dmPortWharf;

			array[2] = getByPortWharfCodeAndSyncVersion_PrevAndNext(
					dmPortWharf, portWharfCode, syncVersion, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortWharf getByPortWharfCodeAndSyncVersion_PrevAndNext(
		 DmPortWharf dmPortWharf, String portWharfCode,
		String syncVersion, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

		if (portWharfCode == null) {
			query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_1);
		}
		else {
			if (portWharfCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_2);
			}
		}

		if (syncVersion == null) {
			query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_1);
		}
		else {
			if (syncVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_2);
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
			query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portWharfCode != null) {
			builder.appendNamedParameterMap("portWharfCode", portWharfCode);
		}

		if (syncVersion != null) {
			builder.appendNamedParameterMap("syncVersion", syncVersion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortWharf);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortWharf> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port wharfs where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortRegionCode(String portRegionCode)
		throws SystemException {
		return findByPortRegionCode(portRegionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port wharfs where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @return the range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortRegionCode(String portRegionCode,
		int start, int end) throws SystemException {
		return findByPortRegionCode(portRegionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port wharfs where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findByPortRegionCode(String portRegionCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

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
				query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				list = (List<DmPortWharf>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port wharf in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortRegionCode_First(portRegionCode,
				orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the first dm port wharf in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortWharf> list = findByPortRegionCode(portRegionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf findByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = fetchByPortRegionCode_Last(portRegionCode,
				orderByComparator);

		if (dmPortWharf != null) {
			return dmPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortWharfException(msg.toString());
	}

	/**
	 * Returns the last dm port wharf in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port wharf, or <code>null</code> if a matching dm port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf fetchByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortRegionCode(portRegionCode);

		List<DmPortWharf> list = findByPortRegionCode(portRegionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port wharfs before and after the current dm port wharf in the ordered set where portRegionCode = &#63;.
	 *
	 * @param id the primary key of the current dm port wharf
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortWharfException if a dm port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortWharf[] findByPortRegionCode_PrevAndNext(int id,
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortWharfException, SystemException {
		DmPortWharf dmPortWharf = findByPrimaryKey(id);

		

		try {
			

			DmPortWharf[] array = new DmPortWharf[3];

			array[0] = getByPortRegionCode_PrevAndNext(dmPortWharf,
					portRegionCode, orderByComparator, true);

			array[1] = dmPortWharf;

			array[2] = getByPortRegionCode_PrevAndNext(dmPortWharf,
					portRegionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortWharf getByPortRegionCode_PrevAndNext(
		DmPortWharf dmPortWharf, String portRegionCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTWHARF_WHERE);

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
			query.append(DmPortWharfModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionCode != null) {
			builder.appendNamedParameterMap("portRegionCode", portRegionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortWharf);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortWharf> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port wharfs.
	 *
	 * @return the dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port wharfs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @return the range of dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port wharfs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port wharfs
	 * @param end the upper bound of the range of dm port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortWharf> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPORTWHARF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPORTWHARF.concat(DmPortWharfModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPortWharf>) queryFactory.getResultList(builder);
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
	 * Removes all the dm port wharfs where portWharfCode = &#63; from the database.
	 *
	 * @param portWharfCode the port wharf code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortWharfCode(String portWharfCode)
		throws SystemException {
		for (DmPortWharf dmPortWharf : findByPortWharfCode(portWharfCode)) {
			repository.delete(dmPortWharf);
		}
	}

	/**
	 * Removes all the dm port wharfs where portWharfCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortWharfCodeAndSyncVersion(String portWharfCode,
		String syncVersion) throws SystemException {
		for (DmPortWharf dmPortWharf : findByPortWharfCodeAndSyncVersion(
				portWharfCode, syncVersion)) {
			repository.delete(dmPortWharf);
		}
	}

	/**
	 * Removes all the dm port wharfs where portRegionCode = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionCode(String portRegionCode)
		throws SystemException {
		for (DmPortWharf dmPortWharf : findByPortRegionCode(portRegionCode)) {
			repository.delete(dmPortWharf);
		}
	}

	/**
	 * Removes all the dm port wharfs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPortWharf dmPortWharf : findAll()) {
			repository.delete(dmPortWharf);
		}
	}

	/**
	 * Returns the number of dm port wharfs where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @return the number of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortWharfCode(String portWharfCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTWHARF_WHERE);

			if (portWharfCode == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_1);
			}
			else {
				if (portWharfCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
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
	 * Returns the number of dm port wharfs where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the number of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortWharfCodeAndSyncVersion(String portWharfCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMPORTWHARF_WHERE);

			if (portWharfCode == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_1);
			}
			else {
				if (portWharfCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
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
	 * Returns the number of dm port wharfs where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the number of matching dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCode(String portRegionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTWHARF_WHERE);

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
	 * Returns the number of dm port wharfs.
	 *
	 * @return the number of dm port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPORTWHARF).build();

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
	 * Initializes the dm port wharf persistence.
	 */
	private static final String _SQL_SELECT_DMPORTWHARF = "SELECT dmPortWharf FROM DmPortWharf dmPortWharf";
	private static final String _SQL_SELECT_DMPORTWHARF_WHERE = "SELECT dmPortWharf FROM DmPortWharf dmPortWharf WHERE ";
	private static final String _SQL_COUNT_DMPORTWHARF = "SELECT COUNT(dmPortWharf) FROM DmPortWharf dmPortWharf";
	private static final String _SQL_COUNT_DMPORTWHARF_WHERE = "SELECT COUNT(dmPortWharf) FROM DmPortWharf dmPortWharf WHERE ";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_1 = "dmPortWharf.portWharfCode IS NULL";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_2 = "dmPortWharf.portWharfCode =:portWharfCode";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_3 = "(dmPortWharf.portWharfCode IS NULL OR dmPortWharf.portWharfCode =:portWharfCode)";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_1 =
		"dmPortWharf.portWharfCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_2 =
		"dmPortWharf.portWharfCode =:portWharfCode AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_3 =
		"(dmPortWharf.portWharfCode IS NULL OR dmPortWharf.portWharfCode =:portWharfCode) AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmPortWharf.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmPortWharf.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmPortWharf.syncVersion IS NULL OR dmPortWharf.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1 = "dmPortWharf.portRegionCode IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2 = "dmPortWharf.portRegionCode =:portRegionCode";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3 = "(dmPortWharf.portRegionCode IS NULL OR dmPortWharf.portRegionCode =:portRegionCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPortWharf.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPortWharf exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPortWharf exists with the key {";
	

	
}
