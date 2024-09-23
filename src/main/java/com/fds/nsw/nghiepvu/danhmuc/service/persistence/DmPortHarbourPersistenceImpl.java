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
import com.fds.nsw.nghiepvu.model.DmPortHarbour;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPortHarbourRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPortHarbourModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPortHarbourPersistenceImpl extends BasePersistence {
	@Autowired
	DmPortHarbourRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortHarbour> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPortHarbourUtil} to access the dm port harbour persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPortHarbour create(int id) {
		DmPortHarbour dmPortHarbour = new DmPortHarbour();

		
		//dmPortHarbour.setPrimaryKey(id);

		return dmPortHarbour;
	}

	/**
	 * Removes the dm port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port harbour
	 * @return the dm port harbour that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour remove(int id)
		throws NoSuchDmPortHarbourException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm port harbour with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm port harbour
	 * @return the dm port harbour that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortHarbour remove(Serializable primaryKey)
		throws NoSuchDmPortHarbourException, SystemException {
		

		try {
			

			DmPortHarbour dmPortHarbour = findByPrimaryKey(primaryKey);

			if (dmPortHarbour == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPortHarbourException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPortHarbour);
			return dmPortHarbour;
		}
		catch (NoSuchDmPortHarbourException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPortHarbour remove(DmPortHarbour DmPortHarbour) throws SystemException {
	removeImpl(DmPortHarbour);	return DmPortHarbour;
}

protected DmPortHarbour removeImpl

(DmPortHarbour dmPortHarbour)
		throws SystemException {
		try {
			repository.delete(dmPortHarbour);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortHarbour;
	}

	
	public DmPortHarbour updateImpl(
		DmPortHarbour dmPortHarbour, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmPortHarbour);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortHarbour;
	}

	
	public DmPortHarbour findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port harbour with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPortHarbourException} if it could not be found.
	 *
	 * @param id the primary key of the dm port harbour
	 * @return the dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPrimaryKey(int id)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPrimaryKey(id);

		if (dmPortHarbour == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPortHarbourException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPortHarbour;
	}

	/**
	 * Returns the dm port harbour with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm port harbour
	 * @return the dm port harbour, or <code>null</code> if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortHarbour fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port harbour with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm port harbour
	 * @return the dm port harbour, or <code>null</code> if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPrimaryKey(int id) throws SystemException {
		DmPortHarbour dmPortHarbour = null;

		

		if (dmPortHarbour == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPortHarbour> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPortHarbour = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPortHarbour;
	}

	/**
	 * Returns all the dm port harbours where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @return the matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortHarbourCode(String portHarbourCode)
		throws SystemException {
		return findByPortHarbourCode(portHarbourCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port harbours where portHarbourCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portHarbourCode the port harbour code
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @return the range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortHarbourCode(String portHarbourCode,
		int start, int end) throws SystemException {
		return findByPortHarbourCode(portHarbourCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port harbours where portHarbourCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portHarbourCode the port harbour code
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortHarbourCode(String portHarbourCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
				}

				list = (List<DmPortHarbour>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortHarbourCode_First(String portHarbourCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortHarbourCode_First(portHarbourCode,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portHarbourCode=");
		msg.append(portHarbourCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the first dm port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortHarbourCode_First(String portHarbourCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortHarbour> list = findByPortHarbourCode(portHarbourCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortHarbourCode_Last(String portHarbourCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortHarbourCode_Last(portHarbourCode,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portHarbourCode=");
		msg.append(portHarbourCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortHarbourCode_Last(String portHarbourCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortHarbourCode(portHarbourCode);

		List<DmPortHarbour> list = findByPortHarbourCode(portHarbourCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port harbours before and after the current dm port harbour in the ordered set where portHarbourCode = &#63;.
	 *
	 * @param id the primary key of the current dm port harbour
	 * @param portHarbourCode the port harbour code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour[] findByPortHarbourCode_PrevAndNext(int id,
		String portHarbourCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = findByPrimaryKey(id);

		

		try {
			

			DmPortHarbour[] array = new DmPortHarbour[3];

			array[0] = getByPortHarbourCode_PrevAndNext(dmPortHarbour,
					portHarbourCode, orderByComparator, true);

			array[1] = dmPortHarbour;

			array[2] = getByPortHarbourCode_PrevAndNext(dmPortHarbour,
					portHarbourCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortHarbour getByPortHarbourCode_PrevAndNext(
		DmPortHarbour dmPortHarbour, String portHarbourCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

		if (portHarbourCode == null) {
			query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
		}
		else {
			if (portHarbourCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
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
			query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portHarbourCode != null) {
			builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortHarbour);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortHarbour> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port harbours where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegionCode(String portRegionCode)
		throws SystemException {
		return findByPortRegionCode(portRegionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port harbours where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @return the range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegionCode(String portRegionCode,
		int start, int end) throws SystemException {
		return findByPortRegionCode(portRegionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port harbours where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegionCode(String portRegionCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

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
				query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				list = (List<DmPortHarbour>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortRegionCode_First(portRegionCode,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the first dm port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortHarbour> list = findByPortRegionCode(portRegionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortRegionCode_Last(portRegionCode,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortRegionCode(portRegionCode);

		List<DmPortHarbour> list = findByPortRegionCode(portRegionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port harbours before and after the current dm port harbour in the ordered set where portRegionCode = &#63;.
	 *
	 * @param id the primary key of the current dm port harbour
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour[] findByPortRegionCode_PrevAndNext(int id,
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = findByPrimaryKey(id);

		

		try {
			

			DmPortHarbour[] array = new DmPortHarbour[3];

			array[0] = getByPortRegionCode_PrevAndNext(dmPortHarbour,
					portRegionCode, orderByComparator, true);

			array[1] = dmPortHarbour;

			array[2] = getByPortRegionCode_PrevAndNext(dmPortHarbour,
					portRegionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortHarbour getByPortRegionCode_PrevAndNext(
		DmPortHarbour dmPortHarbour, String portRegionCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

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
			query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionCode != null) {
			builder.appendNamedParameterMap("portRegionCode", portRegionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortHarbour);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortHarbour> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port harbours where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @return the matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegion(String portRegion)
		throws SystemException {
		return findByPortRegion(portRegion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port harbours where portRegion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegion the port region
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @return the range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegion(String portRegion, int start,
		int end) throws SystemException {
		return findByPortRegion(portRegion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port harbours where portRegion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegion the port region
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findByPortRegion(String portRegion, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

			if (portRegion == null) {
				query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_1);
			}
			else {
				if (portRegion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegion != null) {
					builder.appendNamedParameterMap("portRegion", portRegion);
				}

				list = (List<DmPortHarbour>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port harbour in the ordered set where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortRegion_First(String portRegion,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortRegion_First(portRegion,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegion=");
		msg.append(portRegion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the first dm port harbour in the ordered set where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortRegion_First(String portRegion,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortHarbour> list = findByPortRegion(portRegion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour findByPortRegion_Last(String portRegion,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = fetchByPortRegion_Last(portRegion,
				orderByComparator);

		if (dmPortHarbour != null) {
			return dmPortHarbour;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegion=");
		msg.append(portRegion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortHarbourException(msg.toString());
	}

	/**
	 * Returns the last dm port harbour in the ordered set where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port harbour, or <code>null</code> if a matching dm port harbour could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour fetchByPortRegion_Last(String portRegion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortRegion(portRegion);

		List<DmPortHarbour> list = findByPortRegion(portRegion, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port harbours before and after the current dm port harbour in the ordered set where portRegion = &#63;.
	 *
	 * @param id the primary key of the current dm port harbour
	 * @param portRegion the port region
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port harbour
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortHarbourException if a dm port harbour with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortHarbour[] findByPortRegion_PrevAndNext(int id,
		String portRegion, OrderByComparator orderByComparator)
		throws NoSuchDmPortHarbourException, SystemException {
		DmPortHarbour dmPortHarbour = findByPrimaryKey(id);

		

		try {
			

			DmPortHarbour[] array = new DmPortHarbour[3];

			array[0] = getByPortRegion_PrevAndNext(dmPortHarbour,
					portRegion, orderByComparator, true);

			array[1] = dmPortHarbour;

			array[2] = getByPortRegion_PrevAndNext(dmPortHarbour,
					portRegion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortHarbour getByPortRegion_PrevAndNext(
		DmPortHarbour dmPortHarbour, String portRegion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTHARBOUR_WHERE);

		if (portRegion == null) {
			query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_1);
		}
		else {
			if (portRegion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_2);
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
			query.append(DmPortHarbourModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegion != null) {
			builder.appendNamedParameterMap("portRegion", portRegion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortHarbour);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortHarbour> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port harbours.
	 *
	 * @return the dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port harbours.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @return the range of dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port harbours.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port harbours
	 * @param end the upper bound of the range of dm port harbours (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortHarbour> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortHarbour> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPORTHARBOUR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPORTHARBOUR.concat(DmPortHarbourModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPortHarbour>) queryFactory.getResultList(builder);
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
	 * Removes all the dm port harbours where portHarbourCode = &#63; from the database.
	 *
	 * @param portHarbourCode the port harbour code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortHarbourCode(String portHarbourCode)
		throws SystemException {
		for (DmPortHarbour dmPortHarbour : findByPortHarbourCode(
				portHarbourCode)) {
			repository.delete(dmPortHarbour);
		}
	}

	/**
	 * Removes all the dm port harbours where portRegionCode = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionCode(String portRegionCode)
		throws SystemException {
		for (DmPortHarbour dmPortHarbour : findByPortRegionCode(portRegionCode)) {
			repository.delete(dmPortHarbour);
		}
	}

	/**
	 * Removes all the dm port harbours where portRegion = &#63; from the database.
	 *
	 * @param portRegion the port region
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegion(String portRegion) throws SystemException {
		for (DmPortHarbour dmPortHarbour : findByPortRegion(portRegion)) {
			repository.delete(dmPortHarbour);
		}
	}

	/**
	 * Removes all the dm port harbours from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPortHarbour dmPortHarbour : findAll()) {
			repository.delete(dmPortHarbour);
		}
	}

	/**
	 * Returns the number of dm port harbours where portHarbourCode = &#63;.
	 *
	 * @param portHarbourCode the port harbour code
	 * @return the number of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortHarbourCode(String portHarbourCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTHARBOUR_WHERE);

			if (portHarbourCode == null) {
				query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1);
			}
			else {
				if (portHarbourCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portHarbourCode != null) {
					builder.appendNamedParameterMap("portHarbourCode", portHarbourCode);
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
	 * Returns the number of dm port harbours where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the number of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCode(String portRegionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTHARBOUR_WHERE);

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
	 * Returns the number of dm port harbours where portRegion = &#63;.
	 *
	 * @param portRegion the port region
	 * @return the number of matching dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegion(String portRegion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTHARBOUR_WHERE);

			if (portRegion == null) {
				query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_1);
			}
			else {
				if (portRegion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGION_PORTREGION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portRegion != null) {
					builder.appendNamedParameterMap("portRegion", portRegion);
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
	 * Returns the number of dm port harbours.
	 *
	 * @return the number of dm port harbours
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPORTHARBOUR).build();

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
	 * Initializes the dm port harbour persistence.
	 */
	private static final String _SQL_SELECT_DMPORTHARBOUR = "SELECT dmPortHarbour FROM DmPortHarbour dmPortHarbour";
	private static final String _SQL_SELECT_DMPORTHARBOUR_WHERE = "SELECT dmPortHarbour FROM DmPortHarbour dmPortHarbour WHERE ";
	private static final String _SQL_COUNT_DMPORTHARBOUR = "SELECT COUNT(dmPortHarbour) FROM DmPortHarbour dmPortHarbour";
	private static final String _SQL_COUNT_DMPORTHARBOUR_WHERE = "SELECT COUNT(dmPortHarbour) FROM DmPortHarbour dmPortHarbour WHERE ";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_1 =
		"dmPortHarbour.portHarbourCode IS NULL";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_2 =
		"dmPortHarbour.portHarbourCode =:portHarbourCode";
	private static final String _FINDER_COLUMN_PORTHARBOURCODE_PORTHARBOURCODE_3 =
		"(dmPortHarbour.portHarbourCode IS NULL OR dmPortHarbour.portHarbourCode =:portHarbourCode)";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1 = "dmPortHarbour.portRegionCode IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2 = "dmPortHarbour.portRegionCode =:portRegionCode";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3 = "(dmPortHarbour.portRegionCode IS NULL OR dmPortHarbour.portRegionCode =:portRegionCode)";
	private static final String _FINDER_COLUMN_PORTREGION_PORTREGION_1 = "dmPortHarbour.portRegion IS NULL";
	private static final String _FINDER_COLUMN_PORTREGION_PORTREGION_2 = "dmPortHarbour.portRegion =:portRegion";
	private static final String _FINDER_COLUMN_PORTREGION_PORTREGION_3 = "(dmPortHarbour.portRegion IS NULL OR dmPortHarbour.portRegion =:portRegion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPortHarbour.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPortHarbour exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPortHarbour exists with the key {";
	

	
}
