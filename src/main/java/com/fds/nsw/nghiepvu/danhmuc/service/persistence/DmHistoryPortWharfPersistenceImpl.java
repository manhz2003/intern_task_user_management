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
import com.fds.nsw.nghiepvu.model.DmHistoryPortWharf;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPortWharfRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPortWharfModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPortWharfPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPortWharfRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPortWharf> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPortWharfUtil} to access the dm history port wharf persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPortWharf create(int id) {
		DmHistoryPortWharf dmHistoryPortWharf = new DmHistoryPortWharf();

		
		//dmHistoryPortWharf.setPrimaryKey(id);

		return dmHistoryPortWharf;
	}

	/**
	 * Removes the dm history port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history port wharf
	 * @return the dm history port wharf that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf remove(int id)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history port wharf with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history port wharf
	 * @return the dm history port wharf that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortWharf remove(Serializable primaryKey)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		

		try {
			

			DmHistoryPortWharf dmHistoryPortWharf = findByPrimaryKey(primaryKey);

			if (dmHistoryPortWharf == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPortWharfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPortWharf);
			return dmHistoryPortWharf;
		}
		catch (NoSuchDmHistoryPortWharfException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPortWharf remove(DmHistoryPortWharf DmHistoryPortWharf) throws SystemException {
	removeImpl(DmHistoryPortWharf);	return DmHistoryPortWharf;
}

protected DmHistoryPortWharf removeImpl

(
		DmHistoryPortWharf dmHistoryPortWharf) throws SystemException {
		try {
			repository.delete(dmHistoryPortWharf);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortWharf;
	}

	
	public DmHistoryPortWharf updateImpl(
		DmHistoryPortWharf dmHistoryPortWharf,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPortWharf);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPortWharf;
	}

	
	public DmHistoryPortWharf findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port wharf with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException} if it could not be found.
	 *
	 * @param id the primary key of the dm history port wharf
	 * @return the dm history port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf findByPrimaryKey(int id)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = fetchByPrimaryKey(id);

		if (dmHistoryPortWharf == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryPortWharfException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryPortWharf;
	}

	/**
	 * Returns the dm history port wharf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history port wharf
	 * @return the dm history port wharf, or <code>null</code> if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPortWharf fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history port wharf with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history port wharf
	 * @return the dm history port wharf, or <code>null</code> if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = null;

		

		if (dmHistoryPortWharf == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPortWharf> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryPortWharf = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPortWharf;
	}

	/**
	 * Returns all the dm history port wharfs where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @return the matching dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findByPortWharfCode(String portWharfCode)
		throws SystemException {
		return findByPortWharfCode(portWharfCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port wharfs where portWharfCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param start the lower bound of the range of dm history port wharfs
	 * @param end the upper bound of the range of dm history port wharfs (not inclusive)
	 * @return the range of matching dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findByPortWharfCode(String portWharfCode,
		int start, int end) throws SystemException {
		return findByPortWharfCode(portWharfCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port wharfs where portWharfCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portWharfCode the port wharf code
	 * @param start the lower bound of the range of dm history port wharfs
	 * @param end the upper bound of the range of dm history port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findByPortWharfCode(String portWharfCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYPORTWHARF_WHERE);

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
				query.append(DmHistoryPortWharfModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
				}

				list = (List<DmHistoryPortWharf>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf findByPortWharfCode_First(String portWharfCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = fetchByPortWharfCode_First(portWharfCode,
				orderByComparator);

		if (dmHistoryPortWharf != null) {
			return dmHistoryPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortWharfException(msg.toString());
	}

	/**
	 * Returns the first dm history port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history port wharf, or <code>null</code> if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf fetchByPortWharfCode_First(String portWharfCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortWharf> list = findByPortWharfCode(portWharfCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf findByPortWharfCode_Last(String portWharfCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = fetchByPortWharfCode_Last(portWharfCode,
				orderByComparator);

		if (dmHistoryPortWharf != null) {
			return dmHistoryPortWharf;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portWharfCode=");
		msg.append(portWharfCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryPortWharfException(msg.toString());
	}

	/**
	 * Returns the last dm history port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history port wharf, or <code>null</code> if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf fetchByPortWharfCode_Last(String portWharfCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortWharfCode(portWharfCode);

		List<DmHistoryPortWharf> list = findByPortWharfCode(portWharfCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history port wharfs before and after the current dm history port wharf in the ordered set where portWharfCode = &#63;.
	 *
	 * @param id the primary key of the current dm history port wharf
	 * @param portWharfCode the port wharf code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a dm history port wharf with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf[] findByPortWharfCode_PrevAndNext(int id,
		String portWharfCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = findByPrimaryKey(id);

		

		try {
			

			DmHistoryPortWharf[] array = new DmHistoryPortWharf[3];

			array[0] = getByPortWharfCode_PrevAndNext(
					dmHistoryPortWharf, portWharfCode, orderByComparator, true);

			array[1] = dmHistoryPortWharf;

			array[2] = getByPortWharfCode_PrevAndNext(
					dmHistoryPortWharf, portWharfCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryPortWharf getByPortWharfCode_PrevAndNext(
		 DmHistoryPortWharf dmHistoryPortWharf,
		String portWharfCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYPORTWHARF_WHERE);

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
			query.append(DmHistoryPortWharfModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portWharfCode != null) {
			builder.appendNamedParameterMap("portWharfCode", portWharfCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryPortWharf);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryPortWharf> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history port wharf where portWharfCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException} if it could not be found.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the matching dm history port wharf
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPortWharfException if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf findByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = fetchByPortWharfCodeAndSyncVersion(portWharfCode,
				syncVersion);

		if (dmHistoryPortWharf == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portWharfCode=");
			msg.append(portWharfCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPortWharfException(msg.toString());
		}

		return dmHistoryPortWharf;
	}

	/**
	 * Returns the dm history port wharf where portWharfCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the matching dm history port wharf, or <code>null</code> if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf fetchByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion) throws SystemException {
		return fetchByPortWharfCodeAndSyncVersion(portWharfCode, syncVersion,
			true);
	}

	/**
	 * Returns the dm history port wharf where portWharfCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history port wharf, or <code>null</code> if a matching dm history port wharf could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf fetchByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = null;
		if (dmHistoryPortWharf == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPORTWHARF_WHERE);

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

			query.append(DmHistoryPortWharfModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPortWharf.class).build();

				

				if (portWharfCode != null) {
					builder.appendNamedParameterMap("portWharfCode", portWharfCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryPortWharf = (DmHistoryPortWharf) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPortWharf;
	}

	/**
	 * Returns all the dm history port wharfs.
	 *
	 * @return the dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history port wharfs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port wharfs
	 * @param end the upper bound of the range of dm history port wharfs (not inclusive)
	 * @return the range of dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history port wharfs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history port wharfs
	 * @param end the upper bound of the range of dm history port wharfs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPortWharf> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPortWharf> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPORTWHARF);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPORTWHARF.concat(DmHistoryPortWharfModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPortWharf>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history port wharfs where portWharfCode = &#63; from the database.
	 *
	 * @param portWharfCode the port wharf code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortWharfCode(String portWharfCode)
		throws SystemException {
		for (DmHistoryPortWharf dmHistoryPortWharf : findByPortWharfCode(
				portWharfCode)) {
			repository.delete(dmHistoryPortWharf);
		}
	}

	/**
	 * Removes the dm history port wharf where portWharfCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the dm history port wharf that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPortWharf removeByPortWharfCodeAndSyncVersion(
		String portWharfCode, String syncVersion)
		throws NoSuchDmHistoryPortWharfException, SystemException {
		DmHistoryPortWharf dmHistoryPortWharf = findByPortWharfCodeAndSyncVersion(portWharfCode,
				syncVersion);

		repository.delete(dmHistoryPortWharf);
			return dmHistoryPortWharf;
	}

	/**
	 * Removes all the dm history port wharfs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPortWharf dmHistoryPortWharf : findAll()) {
			repository.delete(dmHistoryPortWharf);
		}
	}

	/**
	 * Returns the number of dm history port wharfs where portWharfCode = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @return the number of matching dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortWharfCode(String portWharfCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYPORTWHARF_WHERE);

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
	 * Returns the number of dm history port wharfs where portWharfCode = &#63; and syncVersion = &#63;.
	 *
	 * @param portWharfCode the port wharf code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortWharfCodeAndSyncVersion(String portWharfCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPORTWHARF_WHERE);

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
	 * Returns the number of dm history port wharfs.
	 *
	 * @return the number of dm history port wharfs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPORTWHARF).build();

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
	 * Initializes the dm history port wharf persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPORTWHARF = "SELECT dmHistoryPortWharf FROM DmHistoryPortWharf dmHistoryPortWharf";
	private static final String _SQL_SELECT_DMHISTORYPORTWHARF_WHERE = "SELECT dmHistoryPortWharf FROM DmHistoryPortWharf dmHistoryPortWharf WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPORTWHARF = "SELECT COUNT(dmHistoryPortWharf) FROM DmHistoryPortWharf dmHistoryPortWharf";
	private static final String _SQL_COUNT_DMHISTORYPORTWHARF_WHERE = "SELECT COUNT(dmHistoryPortWharf) FROM DmHistoryPortWharf dmHistoryPortWharf WHERE ";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_1 = "dmHistoryPortWharf.portWharfCode IS NULL";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_2 = "dmHistoryPortWharf.portWharfCode =:portWharfCode";
	private static final String _FINDER_COLUMN_PORTWHARFCODE_PORTWHARFCODE_3 = "(dmHistoryPortWharf.portWharfCode IS NULL OR dmHistoryPortWharf.portWharfCode =:portWharfCode)";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_1 =
		"dmHistoryPortWharf.portWharfCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_2 =
		"dmHistoryPortWharf.portWharfCode =:portWharfCode AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_PORTWHARFCODE_3 =
		"(dmHistoryPortWharf.portWharfCode IS NULL OR dmHistoryPortWharf.portWharfCode =:portWharfCode) AND ";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPortWharf.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPortWharf.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PORTWHARFCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPortWharf.syncVersion IS NULL OR dmHistoryPortWharf.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPortWharf.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPortWharf exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPortWharf exists with the key {";
	

	
}
