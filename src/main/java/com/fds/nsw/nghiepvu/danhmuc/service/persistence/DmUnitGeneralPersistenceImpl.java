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
import com.fds.nsw.nghiepvu.model.DmUnitGeneral;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmUnitGeneralRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmUnitGeneralModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmUnitGeneralPersistenceImpl extends BasePersistence {
	@Autowired
	DmUnitGeneralRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmUnitGeneral> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmUnitGeneralUtil} to access the dm unit general persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmUnitGeneral create(int id) {
		DmUnitGeneral dmUnitGeneral = new DmUnitGeneral();

		
		//dmUnitGeneral.setPrimaryKey(id);

		return dmUnitGeneral;
	}

	/**
	 * Removes the dm unit general with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm unit general
	 * @return the dm unit general that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral remove(int id)
		throws NoSuchDmUnitGeneralException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm unit general with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm unit general
	 * @return the dm unit general that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmUnitGeneral remove(Serializable primaryKey)
		throws NoSuchDmUnitGeneralException, SystemException {
		

		try {
			

			DmUnitGeneral dmUnitGeneral = findByPrimaryKey(primaryKey);

			if (dmUnitGeneral == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmUnitGeneral);
			return dmUnitGeneral;
		}
		catch (NoSuchDmUnitGeneralException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmUnitGeneral remove(DmUnitGeneral DmUnitGeneral) throws SystemException {
	removeImpl(DmUnitGeneral);	return DmUnitGeneral;
}

protected DmUnitGeneral removeImpl

(DmUnitGeneral dmUnitGeneral)
		throws SystemException {
		try {
			repository.delete(dmUnitGeneral);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmUnitGeneral;
	}

	
	public DmUnitGeneral updateImpl(
		DmUnitGeneral dmUnitGeneral, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmUnitGeneral);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmUnitGeneral;
	}

	
	public DmUnitGeneral findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm unit general with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException} if it could not be found.
	 *
	 * @param id the primary key of the dm unit general
	 * @return the dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByPrimaryKey(int id)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByPrimaryKey(id);

		if (dmUnitGeneral == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmUnitGeneralException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmUnitGeneral;
	}

	/**
	 * Returns the dm unit general with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm unit general
	 * @return the dm unit general, or <code>null</code> if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmUnitGeneral fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm unit general with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm unit general
	 * @return the dm unit general, or <code>null</code> if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByPrimaryKey(int id) throws SystemException {
		DmUnitGeneral dmUnitGeneral = null;

		

		if (dmUnitGeneral == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmUnitGeneral> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmUnitGeneral = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmUnitGeneral;
	}

	/**
	 * Returns all the dm unit generals where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @return the matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByUnitCode(String unitCode)
		throws SystemException {
		return findByUnitCode(unitCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm unit generals where unitCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitCode the unit code
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @return the range of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByUnitCode(String unitCode, int start,
		int end) throws SystemException {
		return findByUnitCode(unitCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm unit generals where unitCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitCode the unit code
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByUnitCode(String unitCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmUnitGeneral> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMUNITGENERAL_WHERE);

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
				query.append(DmUnitGeneralModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
				}

				list = (List<DmUnitGeneral>)queryFactory.getResultList(builder);
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
	 * Returns the first dm unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByUnitCode_First(String unitCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByUnitCode_First(unitCode,
				orderByComparator);

		if (dmUnitGeneral != null) {
			return dmUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitCode=");
		msg.append(unitCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the first dm unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByUnitCode_First(String unitCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmUnitGeneral> list = findByUnitCode(unitCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByUnitCode_Last(String unitCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByUnitCode_Last(unitCode,
				orderByComparator);

		if (dmUnitGeneral != null) {
			return dmUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitCode=");
		msg.append(unitCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the last dm unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByUnitCode_Last(String unitCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByUnitCode(unitCode);

		List<DmUnitGeneral> list = findByUnitCode(unitCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm unit generals before and after the current dm unit general in the ordered set where unitCode = &#63;.
	 *
	 * @param id the primary key of the current dm unit general
	 * @param unitCode the unit code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral[] findByUnitCode_PrevAndNext(int id, String unitCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = findByPrimaryKey(id);

		

		try {
			

			DmUnitGeneral[] array = new DmUnitGeneral[3];

			array[0] = getByUnitCode_PrevAndNext(dmUnitGeneral,
					unitCode, orderByComparator, true);

			array[1] = dmUnitGeneral;

			array[2] = getByUnitCode_PrevAndNext(dmUnitGeneral,
					unitCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmUnitGeneral getByUnitCode_PrevAndNext(
		DmUnitGeneral dmUnitGeneral, String unitCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMUNITGENERAL_WHERE);

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
			query.append(DmUnitGeneralModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (unitCode != null) {
			builder.appendNamedParameterMap("unitCode", unitCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmUnitGeneral);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmUnitGeneral> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm unit general where unitCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException} if it could not be found.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the matching dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByUnitCodeAndSyncVersion(unitCode,
				syncVersion);

		if (dmUnitGeneral == null) {
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

			throw new NoSuchDmUnitGeneralException(msg.toString());
		}

		return dmUnitGeneral;
	}

	/**
	 * Returns the dm unit general where unitCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion) throws SystemException {
		return fetchByUnitCodeAndSyncVersion(unitCode, syncVersion, true);
	}

	/**
	 * Returns the dm unit general where unitCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmUnitGeneral dmUnitGeneral = null;
		if (dmUnitGeneral == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMUNITGENERAL_WHERE);

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

			query.append(DmUnitGeneralModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmUnitGeneral.class).build();

				

				if (unitCode != null) {
					builder.appendNamedParameterMap("unitCode", unitCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmUnitGeneral = (DmUnitGeneral) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmUnitGeneral;
	}

	/**
	 * Returns all the dm unit generals where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @return the matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByF_unitname(String unitName)
		throws SystemException {
		return findByF_unitname(unitName, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm unit generals where unitName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitName the unit name
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @return the range of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByF_unitname(String unitName, int start,
		int end) throws SystemException {
		return findByF_unitname(unitName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm unit generals where unitName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param unitName the unit name
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findByF_unitname(String unitName, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmUnitGeneral> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMUNITGENERAL_WHERE);

			if (unitName == null) {
				query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_1);
			}
			else {
				if (unitName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmUnitGeneralModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (unitName != null) {
					builder.appendNamedParameterMap("unitName", unitName);
				}

				list = (List<DmUnitGeneral>)queryFactory.getResultList(builder);
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
	 * Returns the first dm unit general in the ordered set where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByF_unitname_First(String unitName,
		OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByF_unitname_First(unitName,
				orderByComparator);

		if (dmUnitGeneral != null) {
			return dmUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitName=");
		msg.append(unitName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the first dm unit general in the ordered set where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByF_unitname_First(String unitName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmUnitGeneral> list = findByF_unitname(unitName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm unit general in the ordered set where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral findByF_unitname_Last(String unitName,
		OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = fetchByF_unitname_Last(unitName,
				orderByComparator);

		if (dmUnitGeneral != null) {
			return dmUnitGeneral;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("unitName=");
		msg.append(unitName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmUnitGeneralException(msg.toString());
	}

	/**
	 * Returns the last dm unit general in the ordered set where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm unit general, or <code>null</code> if a matching dm unit general could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral fetchByF_unitname_Last(String unitName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_unitname(unitName);

		List<DmUnitGeneral> list = findByF_unitname(unitName, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm unit generals before and after the current dm unit general in the ordered set where unitName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm unit general
	 * @param unitName the unit name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm unit general
	 * @throws vn.gt.dao.danhmuc.NoSuchDmUnitGeneralException if a dm unit general with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral[] findByF_unitname_PrevAndNext(int id,
		String unitName, OrderByComparator orderByComparator)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = findByPrimaryKey(id);

		

		try {
			

			DmUnitGeneral[] array = new DmUnitGeneral[3];

			array[0] = getByF_unitname_PrevAndNext(dmUnitGeneral,
					unitName, orderByComparator, true);

			array[1] = dmUnitGeneral;

			array[2] = getByF_unitname_PrevAndNext(dmUnitGeneral,
					unitName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmUnitGeneral getByF_unitname_PrevAndNext(
		DmUnitGeneral dmUnitGeneral, String unitName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMUNITGENERAL_WHERE);

		if (unitName == null) {
			query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_1);
		}
		else {
			if (unitName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_2);
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
			query.append(DmUnitGeneralModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (unitName != null) {
			builder.appendNamedParameterMap("unitName", unitName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmUnitGeneral);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmUnitGeneral> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm unit generals.
	 *
	 * @return the dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm unit generals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @return the range of dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm unit generals.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm unit generals
	 * @param end the upper bound of the range of dm unit generals (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmUnitGeneral> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmUnitGeneral> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMUNITGENERAL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMUNITGENERAL.concat(DmUnitGeneralModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmUnitGeneral>) queryFactory.getResultList(builder);
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
	 * Removes all the dm unit generals where unitCode = &#63; from the database.
	 *
	 * @param unitCode the unit code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUnitCode(String unitCode) throws SystemException {
		for (DmUnitGeneral dmUnitGeneral : findByUnitCode(unitCode)) {
			repository.delete(dmUnitGeneral);
		}
	}

	/**
	 * Removes the dm unit general where unitCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the dm unit general that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmUnitGeneral removeByUnitCodeAndSyncVersion(String unitCode,
		String syncVersion)
		throws NoSuchDmUnitGeneralException, SystemException {
		DmUnitGeneral dmUnitGeneral = findByUnitCodeAndSyncVersion(unitCode,
				syncVersion);

		repository.delete(dmUnitGeneral);
			return dmUnitGeneral;
	}

	/**
	 * Removes all the dm unit generals where unitName LIKE &#63; from the database.
	 *
	 * @param unitName the unit name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_unitname(String unitName) throws SystemException {
		for (DmUnitGeneral dmUnitGeneral : findByF_unitname(unitName)) {
			repository.delete(dmUnitGeneral);
		}
	}

	/**
	 * Removes all the dm unit generals from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmUnitGeneral dmUnitGeneral : findAll()) {
			repository.delete(dmUnitGeneral);
		}
	}

	/**
	 * Returns the number of dm unit generals where unitCode = &#63;.
	 *
	 * @param unitCode the unit code
	 * @return the number of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUnitCode(String unitCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMUNITGENERAL_WHERE);

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
	 * Returns the number of dm unit generals where unitCode = &#63; and syncVersion = &#63;.
	 *
	 * @param unitCode the unit code
	 * @param syncVersion the sync version
	 * @return the number of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUnitCodeAndSyncVersion(String unitCode, String syncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMUNITGENERAL_WHERE);

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
	 * Returns the number of dm unit generals where unitName LIKE &#63;.
	 *
	 * @param unitName the unit name
	 * @return the number of matching dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_unitname(String unitName) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMUNITGENERAL_WHERE);

			if (unitName == null) {
				query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_1);
			}
			else {
				if (unitName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_UNITNAME_UNITNAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (unitName != null) {
					builder.appendNamedParameterMap("unitName", unitName);
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
	 * Returns the number of dm unit generals.
	 *
	 * @return the number of dm unit generals
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMUNITGENERAL).build();

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
	 * Initializes the dm unit general persistence.
	 */
	private static final String _SQL_SELECT_DMUNITGENERAL = "SELECT dmUnitGeneral FROM DmUnitGeneral dmUnitGeneral";
	private static final String _SQL_SELECT_DMUNITGENERAL_WHERE = "SELECT dmUnitGeneral FROM DmUnitGeneral dmUnitGeneral WHERE ";
	private static final String _SQL_COUNT_DMUNITGENERAL = "SELECT COUNT(dmUnitGeneral) FROM DmUnitGeneral dmUnitGeneral";
	private static final String _SQL_COUNT_DMUNITGENERAL_WHERE = "SELECT COUNT(dmUnitGeneral) FROM DmUnitGeneral dmUnitGeneral WHERE ";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_1 = "dmUnitGeneral.unitCode IS NULL";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_2 = "dmUnitGeneral.unitCode =:unitCode";
	private static final String _FINDER_COLUMN_UNITCODE_UNITCODE_3 = "(dmUnitGeneral.unitCode IS NULL OR dmUnitGeneral.unitCode =:unitCode)";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_1 =
		"dmUnitGeneral.unitCode IS NULL AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_2 =
		"dmUnitGeneral.unitCode =:unitCode AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_UNITCODE_3 =
		"(dmUnitGeneral.unitCode IS NULL OR dmUnitGeneral.unitCode =:unitCode) AND ";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmUnitGeneral.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmUnitGeneral.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_UNITCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmUnitGeneral.syncVersion IS NULL OR dmUnitGeneral.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_F_UNITNAME_UNITNAME_1 = "dmUnitGeneral.unitName LIKE NULL";
	private static final String _FINDER_COLUMN_F_UNITNAME_UNITNAME_2 = "dmUnitGeneral.unitName LIKE :unitName";
	private static final String _FINDER_COLUMN_F_UNITNAME_UNITNAME_3 = "(dmUnitGeneral.unitName IS NULL OR dmUnitGeneral.unitName LIKE :unitName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmUnitGeneral.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmUnitGeneral exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmUnitGeneral exists with the key {";
	

	
}
