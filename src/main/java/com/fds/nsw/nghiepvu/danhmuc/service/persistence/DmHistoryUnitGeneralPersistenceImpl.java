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
import com.fds.nsw.nghiepvu.model.DmHistoryUnitGeneral;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryUnitGeneralRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryUnitGeneralModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryUnitGeneralPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryUnitGeneralRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryUnitGeneral> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryUnitGeneralUtil} to access the dm history unit general persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryUnitGeneral create(int id) {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = new DmHistoryUnitGeneral();

		
		//dmHistoryUnitGeneral.setPrimaryKey(id);

		return dmHistoryUnitGeneral;
	}

	/**
	 * Removes the dm history unit general with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history unit general
	 * @return the dm history unit general that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral remove(int id)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history unit general with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history unit general
	 * @return the dm history unit general that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryUnitGeneral remove(Serializable primaryKey)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		

		try {
			

			DmHistoryUnitGeneral dmHistoryUnitGeneral = findByPrimaryKey(primaryKey);

			if (dmHistoryUnitGeneral == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryUnitGeneral);
			return dmHistoryUnitGeneral;
		}
		catch (NoSuchDmHistoryUnitGeneralException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryUnitGeneral remove(DmHistoryUnitGeneral DmHistoryUnitGeneral) throws SystemException {
	removeImpl(DmHistoryUnitGeneral);	return DmHistoryUnitGeneral;
}

protected DmHistoryUnitGeneral removeImpl

(
		DmHistoryUnitGeneral dmHistoryUnitGeneral) throws SystemException {
		try {
			repository.delete(dmHistoryUnitGeneral);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryUnitGeneral;
	}

	
	public DmHistoryUnitGeneral updateImpl(
		DmHistoryUnitGeneral dmHistoryUnitGeneral,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryUnitGeneral);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryUnitGeneral;
	}

	
	public DmHistoryUnitGeneral findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history unit general with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException} if it could not be found.
	 *
	 * @param id the primary key of the dm history unit general
	 * @return the dm history unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral findByPrimaryKey(int id)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = fetchByPrimaryKey(id);

		if (dmHistoryUnitGeneral == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryUnitGeneral;
	}

	/**
	 * Returns the dm history unit general with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history unit general
	 * @return the dm history unit general, or <code>null</code> if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryUnitGeneral fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history unit general with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history unit general
	 * @return the dm history unit general, or <code>null</code> if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = null;

		

		if (dmHistoryUnitGeneral == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryUnitGeneral> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryUnitGeneral = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryUnitGeneral;
	}

	/**
	 * Returns all the dm history unit generals where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @return the matching dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findByUnitCode(String unitCode)
		throws SystemException {
		return findByUnitCode(unitCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm history unit generals where unitCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitCode the unit code
	 * @param start the lower bound of the range of dm history unit generals
	 * @param end the upper bound of the range of dm history unit generals (not inclusive)
	 * @return the range of matching dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findByUnitCode(String unitCode,
		int start, int end) throws SystemException {
		return findByUnitCode(unitCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history unit generals where unitCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitCode the unit code
	 * @param start the lower bound of the range of dm history unit generals
	 * @param end the upper bound of the range of dm history unit generals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findByUnitCode(String unitCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryUnitGeneral> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYUNITGENERAL_WHERE);

			if (unitCode == null) {
				query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_1);
			}
			else {
				if (unitCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryUnitGeneralModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
				}

				list = (List<DmHistoryUnitGeneral>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral findByUnitCode_First(String unitCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = fetchByUnitCode_First(unitCode,
				orderByComparator);

		if (dmHistoryUnitGeneral != null) {
			return dmHistoryUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitCode=");
		msg.append(unitCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the first dm history unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history unit general, or <code>null</code> if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral fetchByUnitCode_First(String unitCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryUnitGeneral> list = findByUnitCode(unitCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral findByUnitCode_Last(String unitCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = fetchByUnitCode_Last(unitCode,
				orderByComparator);

		if (dmHistoryUnitGeneral != null) {
			return dmHistoryUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitCode=");
		msg.append(unitCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the last dm history unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history unit general, or <code>null</code> if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral fetchByUnitCode_Last(String unitCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUnitCode(unitCode);

		List<DmHistoryUnitGeneral> list = findByUnitCode(unitCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history unit generals before and after the current dm history unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param id the primary key of the current dm history unit general
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a dm history unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral[] findByUnitCode_PrevAndNext(int id,
		String unitCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = findByPrimaryKey(id);

		

		try {
			

			DmHistoryUnitGeneral[] array = new DmHistoryUnitGeneral[3];

			array[0] = getByUnitCode_PrevAndNext(dmHistoryUnitGeneral,
					unitCode, orderByComparator, true);

			array[1] = dmHistoryUnitGeneral;

			array[2] = getByUnitCode_PrevAndNext(dmHistoryUnitGeneral,
					unitCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryUnitGeneral getByUnitCode_PrevAndNext(
		DmHistoryUnitGeneral dmHistoryUnitGeneral, String unitCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYUNITGENERAL_WHERE);

		if (unitCode == null) {
			query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_1);
		}
		else {
			if (unitCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_2);
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
			query.append(DmHistoryUnitGeneralModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (unitCode != null) {
			builder.appendNamedParameterMap("unitCode", unitCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryUnitGeneral);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryUnitGeneral> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history unit general where unitCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException} if it could not be found.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the matching dm history unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryUnitGeneralException if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral findByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = fetchByUnitCodeAndSyncVersion(unitCode,
				syncVersion);

		if (dmHistoryUnitGeneral == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("unitCode=");
			msg.append(unitCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryUnitGeneralException(msg.toString());
		}

		return dmHistoryUnitGeneral;
	}

	/**
	 * Returns the dm history unit general where unitCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the matching dm history unit general, or <code>null</code> if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral fetchByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion) throws SystemException {
		return fetchByUnitCodeAndSyncVersion(unitCode, syncVersion, true);
	}

	/**
	 * Returns the dm history unit general where unitCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history unit general, or <code>null</code> if a matching dm history unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral fetchByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = null;
		if (dmHistoryUnitGeneral == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYUNITGENERAL_WHERE);

			if (unitCode == null) {
				query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_1);
			}
			else {
				if (unitCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryUnitGeneralModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryUnitGeneral.class).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryUnitGeneral = (DmHistoryUnitGeneral) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryUnitGeneral;
	}

	/**
	 * Returns all the dm history unit generals.
	 *
	 * @return the dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history unit generals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history unit generals
	 * @param end the upper bound of the range of dm history unit generals (not inclusive)
	 * @return the range of dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history unit generals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history unit generals
	 * @param end the upper bound of the range of dm history unit generals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryUnitGeneral> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryUnitGeneral> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYUNITGENERAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYUNITGENERAL.concat(DmHistoryUnitGeneralModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryUnitGeneral>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history unit generals where unitCode = &#63; from the database.
	 *
	 * @param unitCode the unit code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUnitCode(String unitCode) throws SystemException {
		for (DmHistoryUnitGeneral dmHistoryUnitGeneral : findByUnitCode(
				unitCode)) {
			repository.delete(dmHistoryUnitGeneral);
		}
	}

	/**
	 * Removes the dm history unit general where unitCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the dm history unit general that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryUnitGeneral removeByUnitCodeAndSyncVersion(
		String unitCode, String syncVersion)
		throws NoSuchDmHistoryUnitGeneralException, SystemException {
		DmHistoryUnitGeneral dmHistoryUnitGeneral = findByUnitCodeAndSyncVersion(unitCode,
				syncVersion);

		repository.delete(dmHistoryUnitGeneral);
			return dmHistoryUnitGeneral;
	}

	/**
	 * Removes all the dm history unit generals from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryUnitGeneral dmHistoryUnitGeneral : findAll()) {
			repository.delete(dmHistoryUnitGeneral);
		}
	}

	/**
	 * Returns the number of dm history unit generals where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @return the number of matching dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUnitCode(String unitCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYUNITGENERAL_WHERE);

			if (unitCode == null) {
				query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_1);
			}
			else {
				if (unitCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODE_UNITCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
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
	 * Returns the number of dm history unit generals where unitCode = &#63; and syncVersion = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUnitCodeAndSyncVersion(String unitCode, String syncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYUNITGENERAL_WHERE);

			if (unitCode == null) {
				query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_1);
			}
			else {
				if (unitCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
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
	 * Returns the number of dm history unit generals.
	 *
	 * @return the number of dm history unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYUNITGENERAL).build();

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
	 * Initializes the dm history unit general persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYUNITGENERAL = "SELECT dmHistoryUnitGeneral FROM DmHistoryUnitGeneral dmHistoryUnitGeneral";
	private static final String _SQL_SELECT_DMHISTORYUNITGENERAL_WHERE = "SELECT dmHistoryUnitGeneral FROM DmHistoryUnitGeneral dmHistoryUnitGeneral WHERE ";
	private static final String _SQL_COUNT_DMHISTORYUNITGENERAL = "SELECT COUNT(dmHistoryUnitGeneral) FROM DmHistoryUnitGeneral dmHistoryUnitGeneral";
	private static final String _SQL_COUNT_DMHISTORYUNITGENERAL_WHERE = "SELECT COUNT(dmHistoryUnitGeneral) FROM DmHistoryUnitGeneral dmHistoryUnitGeneral WHERE ";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_1 = "dmHistoryUnitGeneral.unitCode IS NULL";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_2 = "dmHistoryUnitGeneral.unitCode =:unitCode";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_3 = "(dmHistoryUnitGeneral.unitCode IS NULL OR dmHistoryUnitGeneral.unitCode =:unitCode)";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_1 =
		"dmHistoryUnitGeneral.unitCode IS NULL AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_2 =
		"dmHistoryUnitGeneral.unitCode =:unitCode AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_3 =
		"(dmHistoryUnitGeneral.unitCode IS NULL OR dmHistoryUnitGeneral.unitCode =:unitCode) AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryUnitGeneral.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryUnitGeneral.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryUnitGeneral.syncVersion IS NULL OR dmHistoryUnitGeneral.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryUnitGeneral.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryUnitGeneral exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryUnitGeneral exists with the key {";
	

	
}
