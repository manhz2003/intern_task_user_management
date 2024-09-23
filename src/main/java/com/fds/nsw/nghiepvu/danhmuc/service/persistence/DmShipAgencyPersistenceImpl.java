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
import com.fds.nsw.nghiepvu.model.DmShipAgency;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmShipAgencyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmShipAgencyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmShipAgencyPersistenceImpl extends BasePersistence {
	@Autowired
	DmShipAgencyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmShipAgency> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmShipAgencyUtil} to access the dm ship agency persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmShipAgency create(int id) {
		DmShipAgency dmShipAgency = new DmShipAgency();

		
		//dmShipAgency.setPrimaryKey(id);

		return dmShipAgency;
	}

	/**
	 * Removes the dm ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm ship agency
	 * @return the dm ship agency that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency remove(int id)
		throws NoSuchDmShipAgencyException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm ship agency with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm ship agency
	 * @return the dm ship agency that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmShipAgency remove(Serializable primaryKey)
		throws NoSuchDmShipAgencyException, SystemException {
		

		try {
			

			DmShipAgency dmShipAgency = findByPrimaryKey(primaryKey);

			if (dmShipAgency == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmShipAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmShipAgency);
			return dmShipAgency;
		}
		catch (NoSuchDmShipAgencyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmShipAgency remove(DmShipAgency DmShipAgency) throws SystemException {
	removeImpl(DmShipAgency);	return DmShipAgency;
}

protected DmShipAgency removeImpl

(DmShipAgency dmShipAgency)
		throws SystemException {
		try {
			repository.delete(dmShipAgency);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmShipAgency;
	}

	
	public DmShipAgency updateImpl(
		DmShipAgency dmShipAgency, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmShipAgency);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmShipAgency;
	}

	
	public DmShipAgency findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm ship agency with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmShipAgencyException} if it could not be found.
	 *
	 * @param id the primary key of the dm ship agency
	 * @return the dm ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency findByPrimaryKey(int id)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = fetchByPrimaryKey(id);

		if (dmShipAgency == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmShipAgencyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmShipAgency;
	}

	/**
	 * Returns the dm ship agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm ship agency
	 * @return the dm ship agency, or <code>null</code> if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmShipAgency fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm ship agency with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm ship agency
	 * @return the dm ship agency, or <code>null</code> if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency fetchByPrimaryKey(int id) throws SystemException {
		DmShipAgency dmShipAgency = null;

		

		if (dmShipAgency == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmShipAgency> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmShipAgency = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmShipAgency;
	}

	/**
	 * Returns all the dm ship agencies where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the matching dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		return findByShipAgencyCode(shipAgencyCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ship agencies where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of dm ship agencies
	 * @param end the upper bound of the range of dm ship agencies (not inclusive)
	 * @return the range of matching dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findByShipAgencyCode(String shipAgencyCode,
		int start, int end) throws SystemException {
		return findByShipAgencyCode(shipAgencyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ship agencies where shipAgencyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param start the lower bound of the range of dm ship agencies
	 * @param end the upper bound of the range of dm ship agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findByShipAgencyCode(String shipAgencyCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmShipAgency> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmShipAgencyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				list = (List<DmShipAgency>)queryFactory.getResultList(builder);
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
	 * Returns the first dm ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency findByShipAgencyCode_First(String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = fetchByShipAgencyCode_First(shipAgencyCode,
				orderByComparator);

		if (dmShipAgency != null) {
			return dmShipAgency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipAgencyException(msg.toString());
	}

	/**
	 * Returns the first dm ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm ship agency, or <code>null</code> if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency fetchByShipAgencyCode_First(String shipAgencyCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmShipAgency> list = findByShipAgencyCode(shipAgencyCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency findByShipAgencyCode_Last(String shipAgencyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = fetchByShipAgencyCode_Last(shipAgencyCode,
				orderByComparator);

		if (dmShipAgency != null) {
			return dmShipAgency;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shipAgencyCode=");
		msg.append(shipAgencyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmShipAgencyException(msg.toString());
	}

	/**
	 * Returns the last dm ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm ship agency, or <code>null</code> if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency fetchByShipAgencyCode_Last(String shipAgencyCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByShipAgencyCode(shipAgencyCode);

		List<DmShipAgency> list = findByShipAgencyCode(shipAgencyCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm ship agencies before and after the current dm ship agency in the ordered set where shipAgencyCode = &#63;.
	 *
	 * @param id the primary key of the current dm ship agency
	 * @param shipAgencyCode the ship agency code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a dm ship agency with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency[] findByShipAgencyCode_PrevAndNext(int id,
		String shipAgencyCode, OrderByComparator orderByComparator)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = findByPrimaryKey(id);

		

		try {
			

			DmShipAgency[] array = new DmShipAgency[3];

			array[0] = getByShipAgencyCode_PrevAndNext(dmShipAgency,
					shipAgencyCode, orderByComparator, true);

			array[1] = dmShipAgency;

			array[2] = getByShipAgencyCode_PrevAndNext(dmShipAgency,
					shipAgencyCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmShipAgency getByShipAgencyCode_PrevAndNext(
		DmShipAgency dmShipAgency, String shipAgencyCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMSHIPAGENCY_WHERE);

		if (shipAgencyCode == null) {
			query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
		}
		else {
			if (shipAgencyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
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
			query.append(DmShipAgencyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (shipAgencyCode != null) {
			builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmShipAgency);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmShipAgency> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm ship agency where shipAgencyCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmShipAgencyException} if it could not be found.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the matching dm ship agency
	 * @throws vn.gt.dao.danhmuc.NoSuchDmShipAgencyException if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency findByF_shipAgencyCode(String shipAgencyCode)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = fetchByF_shipAgencyCode(shipAgencyCode);

		if (dmShipAgency == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shipAgencyCode=");
			msg.append(shipAgencyCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmShipAgencyException(msg.toString());
		}

		return dmShipAgency;
	}

	/**
	 * Returns the dm ship agency where shipAgencyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the matching dm ship agency, or <code>null</code> if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency fetchByF_shipAgencyCode(String shipAgencyCode)
		throws SystemException {
		return fetchByF_shipAgencyCode(shipAgencyCode, true);
	}

	/**
	 * Returns the dm ship agency where shipAgencyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm ship agency, or <code>null</code> if a matching dm ship agency could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency fetchByF_shipAgencyCode(String shipAgencyCode,
		boolean retrieveFromCache) throws SystemException {
		DmShipAgency dmShipAgency = null;
		if (dmShipAgency == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			query.append(DmShipAgencyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmShipAgency.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
				}

				dmShipAgency = (DmShipAgency) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmShipAgency;
	}

	/**
	 * Returns all the dm ship agencies.
	 *
	 * @return the dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ship agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ship agencies
	 * @param end the upper bound of the range of dm ship agencies (not inclusive)
	 * @return the range of dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ship agencies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ship agencies
	 * @param end the upper bound of the range of dm ship agencies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmShipAgency> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmShipAgency> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMSHIPAGENCY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMSHIPAGENCY.concat(DmShipAgencyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmShipAgency>) queryFactory.getResultList(builder);
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
	 * Removes all the dm ship agencies where shipAgencyCode = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		for (DmShipAgency dmShipAgency : findByShipAgencyCode(shipAgencyCode)) {
			repository.delete(dmShipAgency);
		}
	}

	/**
	 * Removes the dm ship agency where shipAgencyCode = &#63; from the database.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the dm ship agency that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmShipAgency removeByF_shipAgencyCode(String shipAgencyCode)
		throws NoSuchDmShipAgencyException, SystemException {
		DmShipAgency dmShipAgency = findByF_shipAgencyCode(shipAgencyCode);

		repository.delete(dmShipAgency);
			return dmShipAgency;
	}

	/**
	 * Removes all the dm ship agencies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmShipAgency dmShipAgency : findAll()) {
			repository.delete(dmShipAgency);
		}
	}

	/**
	 * Returns the number of dm ship agencies where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the number of matching dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShipAgencyCode(String shipAgencyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
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
	 * Returns the number of dm ship agencies where shipAgencyCode = &#63;.
	 *
	 * @param shipAgencyCode the ship agency code
	 * @return the number of matching dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipAgencyCode(String shipAgencyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMSHIPAGENCY_WHERE);

			if (shipAgencyCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_1);
			}
			else {
				if (shipAgencyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (shipAgencyCode != null) {
					builder.appendNamedParameterMap("shipAgencyCode", shipAgencyCode);
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
	 * Returns the number of dm ship agencies.
	 *
	 * @return the number of dm ship agencies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMSHIPAGENCY).build();

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
	 * Initializes the dm ship agency persistence.
	 */
	private static final String _SQL_SELECT_DMSHIPAGENCY = "SELECT dmShipAgency FROM DmShipAgency dmShipAgency";
	private static final String _SQL_SELECT_DMSHIPAGENCY_WHERE = "SELECT dmShipAgency FROM DmShipAgency dmShipAgency WHERE ";
	private static final String _SQL_COUNT_DMSHIPAGENCY = "SELECT COUNT(dmShipAgency) FROM DmShipAgency dmShipAgency";
	private static final String _SQL_COUNT_DMSHIPAGENCY_WHERE = "SELECT COUNT(dmShipAgency) FROM DmShipAgency dmShipAgency WHERE ";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_1 = "dmShipAgency.shipAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_2 = "dmShipAgency.shipAgencyCode =:shipAgencyCode";
	private static final String _FINDER_COLUMN_SHIPAGENCYCODE_SHIPAGENCYCODE_3 = "(dmShipAgency.shipAgencyCode IS NULL OR dmShipAgency.shipAgencyCode =:shipAgencyCode)";
	private static final String _FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_1 =
		"dmShipAgency.shipAgencyCode IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_2 =
		"dmShipAgency.shipAgencyCode =:shipAgencyCode";
	private static final String _FINDER_COLUMN_F_SHIPAGENCYCODE_SHIPAGENCYCODE_3 =
		"(dmShipAgency.shipAgencyCode IS NULL OR dmShipAgency.shipAgencyCode =:shipAgencyCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmShipAgency.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmShipAgency exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmShipAgency exists with the key {";
	

	
}
