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
import com.fds.nsw.nghiepvu.model.DmPortRegion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmPortRegionRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmPortRegionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmPortRegionPersistenceImpl extends BasePersistence {
	@Autowired
	DmPortRegionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmPortRegion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmPortRegionUtil} to access the dm port region persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmPortRegion create(int id) {
		DmPortRegion dmPortRegion = new DmPortRegion();

		
		//dmPortRegion.setPrimaryKey(id);

		return dmPortRegion;
	}

	/**
	 * Removes the dm port region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm port region
	 * @return the dm port region that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion remove(int id)
		throws NoSuchDmPortRegionException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm port region with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm port region
	 * @return the dm port region that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortRegion remove(Serializable primaryKey)
		throws NoSuchDmPortRegionException, SystemException {
		

		try {
			

			DmPortRegion dmPortRegion = findByPrimaryKey(primaryKey);

			if (dmPortRegion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmPortRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmPortRegion);
			return dmPortRegion;
		}
		catch (NoSuchDmPortRegionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmPortRegion remove(DmPortRegion DmPortRegion) throws SystemException {
	removeImpl(DmPortRegion);	return DmPortRegion;
}

protected DmPortRegion removeImpl

(DmPortRegion dmPortRegion)
		throws SystemException {
		try {
			repository.delete(dmPortRegion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortRegion;
	}

	
	public DmPortRegion updateImpl(
		DmPortRegion dmPortRegion, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmPortRegion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmPortRegion;
	}

	
	public DmPortRegion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port region with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmPortRegionException} if it could not be found.
	 *
	 * @param id the primary key of the dm port region
	 * @return the dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPrimaryKey(int id)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPrimaryKey(id);

		if (dmPortRegion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmPortRegionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmPortRegion;
	}

	/**
	 * Returns the dm port region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm port region
	 * @return the dm port region, or <code>null</code> if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmPortRegion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm port region with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm port region
	 * @return the dm port region, or <code>null</code> if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPrimaryKey(int id) throws SystemException {
		DmPortRegion dmPortRegion = null;

		

		if (dmPortRegion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmPortRegion> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmPortRegion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmPortRegion;
	}

	/**
	 * Returns all the dm port regions where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionCode(String portRegionCode)
		throws SystemException {
		return findByPortRegionCode(portRegionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port regions where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @return the range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionCode(String portRegionCode,
		int start, int end) throws SystemException {
		return findByPortRegionCode(portRegionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port regions where portRegionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionCode the port region code
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionCode(String portRegionCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTREGION_WHERE);

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
				query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionCode != null) {
					builder.appendNamedParameterMap("portRegionCode", portRegionCode);
				}

				list = (List<DmPortRegion>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortRegionCode_First(portRegionCode,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the first dm port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortRegionCode_First(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortRegion> list = findByPortRegionCode(portRegionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortRegionCode_Last(portRegionCode,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionCode=");
		msg.append(portRegionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the last dm port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortRegionCode_Last(String portRegionCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortRegionCode(portRegionCode);

		List<DmPortRegion> list = findByPortRegionCode(portRegionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port regions before and after the current dm port region in the ordered set where portRegionCode = &#63;.
	 *
	 * @param id the primary key of the current dm port region
	 * @param portRegionCode the port region code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion[] findByPortRegionCode_PrevAndNext(int id,
		String portRegionCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = findByPrimaryKey(id);

		

		try {
			

			DmPortRegion[] array = new DmPortRegion[3];

			array[0] = getByPortRegionCode_PrevAndNext(dmPortRegion,
					portRegionCode, orderByComparator, true);

			array[1] = dmPortRegion;

			array[2] = getByPortRegionCode_PrevAndNext(dmPortRegion,
					portRegionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortRegion getByPortRegionCode_PrevAndNext(
		DmPortRegion dmPortRegion, String portRegionCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTREGION_WHERE);

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
			query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionCode != null) {
			builder.appendNamedParameterMap("portRegionCode", portRegionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortRegion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortRegion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port regions where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @return the matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionRef(String portRegionRef)
		throws SystemException {
		return findByPortRegionRef(portRegionRef, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port regions where portRegionRef = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionRef the port region ref
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @return the range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionRef(String portRegionRef,
		int start, int end) throws SystemException {
		return findByPortRegionRef(portRegionRef, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port regions where portRegionRef = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portRegionRef the port region ref
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortRegionRef(String portRegionRef,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTREGION_WHERE);

			if (portRegionRef == null) {
				query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_1);
			}
			else {
				if (portRegionRef.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portRegionRef != null) {
					builder.appendNamedParameterMap("portRegionRef", portRegionRef);
				}

				list = (List<DmPortRegion>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port region in the ordered set where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortRegionRef_First(String portRegionRef,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortRegionRef_First(portRegionRef,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionRef=");
		msg.append(portRegionRef);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the first dm port region in the ordered set where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortRegionRef_First(String portRegionRef,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortRegion> list = findByPortRegionRef(portRegionRef, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port region in the ordered set where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortRegionRef_Last(String portRegionRef,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortRegionRef_Last(portRegionRef,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portRegionRef=");
		msg.append(portRegionRef);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the last dm port region in the ordered set where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortRegionRef_Last(String portRegionRef,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortRegionRef(portRegionRef);

		List<DmPortRegion> list = findByPortRegionRef(portRegionRef, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port regions before and after the current dm port region in the ordered set where portRegionRef = &#63;.
	 *
	 * @param id the primary key of the current dm port region
	 * @param portRegionRef the port region ref
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion[] findByPortRegionRef_PrevAndNext(int id,
		String portRegionRef, OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = findByPrimaryKey(id);

		

		try {
			

			DmPortRegion[] array = new DmPortRegion[3];

			array[0] = getByPortRegionRef_PrevAndNext(dmPortRegion,
					portRegionRef, orderByComparator, true);

			array[1] = dmPortRegion;

			array[2] = getByPortRegionRef_PrevAndNext(dmPortRegion,
					portRegionRef, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortRegion getByPortRegionRef_PrevAndNext(
		DmPortRegion dmPortRegion, String portRegionRef,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTREGION_WHERE);

		if (portRegionRef == null) {
			query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_1);
		}
		else {
			if (portRegionRef.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_2);
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
			query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portRegionRef != null) {
			builder.appendNamedParameterMap("portRegionRef", portRegionRef);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortRegion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortRegion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port regions where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortCodeName(String portCode)
		throws SystemException {
		return findByPortCodeName(portCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port regions where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @return the range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortCodeName(String portCode, int start,
		int end) throws SystemException {
		return findByPortCodeName(portCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port regions where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findByPortCodeName(String portCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMPORTREGION_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				list = (List<DmPortRegion>)queryFactory.getResultList(builder);
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
	 * Returns the first dm port region in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortCodeName_First(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortCodeName_First(portCode,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the first dm port region in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortCodeName_First(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortRegion> list = findByPortCodeName(portCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm port region in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion findByPortCodeName_Last(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = fetchByPortCodeName_Last(portCode,
				orderByComparator);

		if (dmPortRegion != null) {
			return dmPortRegion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmPortRegionException(msg.toString());
	}

	/**
	 * Returns the last dm port region in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm port region, or <code>null</code> if a matching dm port region could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion fetchByPortCodeName_Last(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortCodeName(portCode);

		List<DmPortRegion> list = findByPortCodeName(portCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm port regions before and after the current dm port region in the ordered set where portCode = &#63;.
	 *
	 * @param id the primary key of the current dm port region
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm port region
	 * @throws vn.gt.dao.danhmuc.NoSuchDmPortRegionException if a dm port region with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmPortRegion[] findByPortCodeName_PrevAndNext(int id,
		String portCode, OrderByComparator orderByComparator)
		throws NoSuchDmPortRegionException, SystemException {
		DmPortRegion dmPortRegion = findByPrimaryKey(id);

		

		try {
			

			DmPortRegion[] array = new DmPortRegion[3];

			array[0] = getByPortCodeName_PrevAndNext(dmPortRegion,
					portCode, orderByComparator, true);

			array[1] = dmPortRegion;

			array[2] = getByPortCodeName_PrevAndNext(dmPortRegion,
					portCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmPortRegion getByPortCodeName_PrevAndNext(
		DmPortRegion dmPortRegion, String portCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMPORTREGION_WHERE);

		if (portCode == null) {
			query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_1);
		}
		else {
			if (portCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_2);
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
			query.append(DmPortRegionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portCode != null) {
			builder.appendNamedParameterMap("portCode", portCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmPortRegion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmPortRegion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm port regions.
	 *
	 * @return the dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm port regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @return the range of dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm port regions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm port regions
	 * @param end the upper bound of the range of dm port regions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmPortRegion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmPortRegion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMPORTREGION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMPORTREGION.concat(DmPortRegionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmPortRegion>) queryFactory.getResultList(builder);
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
	 * Removes all the dm port regions where portRegionCode = &#63; from the database.
	 *
	 * @param portRegionCode the port region code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionCode(String portRegionCode)
		throws SystemException {
		for (DmPortRegion dmPortRegion : findByPortRegionCode(portRegionCode)) {
			repository.delete(dmPortRegion);
		}
	}

	/**
	 * Removes all the dm port regions where portRegionRef = &#63; from the database.
	 *
	 * @param portRegionRef the port region ref
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortRegionRef(String portRegionRef)
		throws SystemException {
		for (DmPortRegion dmPortRegion : findByPortRegionRef(portRegionRef)) {
			repository.delete(dmPortRegion);
		}
	}

	/**
	 * Removes all the dm port regions where portCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortCodeName(String portCode) throws SystemException {
		for (DmPortRegion dmPortRegion : findByPortCodeName(portCode)) {
			repository.delete(dmPortRegion);
		}
	}

	/**
	 * Removes all the dm port regions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmPortRegion dmPortRegion : findAll()) {
			repository.delete(dmPortRegion);
		}
	}

	/**
	 * Returns the number of dm port regions where portRegionCode = &#63;.
	 *
	 * @param portRegionCode the port region code
	 * @return the number of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionCode(String portRegionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTREGION_WHERE);

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
	 * Returns the number of dm port regions where portRegionRef = &#63;.
	 *
	 * @param portRegionRef the port region ref
	 * @return the number of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortRegionRef(String portRegionRef)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTREGION_WHERE);

			if (portRegionRef == null) {
				query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_1);
			}
			else {
				if (portRegionRef.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portRegionRef != null) {
					builder.appendNamedParameterMap("portRegionRef", portRegionRef);
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
	 * Returns the number of dm port regions where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the number of matching dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCodeName(String portCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMPORTREGION_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODENAME_PORTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
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
	 * Returns the number of dm port regions.
	 *
	 * @return the number of dm port regions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMPORTREGION).build();

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
	 * Initializes the dm port region persistence.
	 */
	private static final String _SQL_SELECT_DMPORTREGION = "SELECT dmPortRegion FROM DmPortRegion dmPortRegion";
	private static final String _SQL_SELECT_DMPORTREGION_WHERE = "SELECT dmPortRegion FROM DmPortRegion dmPortRegion WHERE ";
	private static final String _SQL_COUNT_DMPORTREGION = "SELECT COUNT(dmPortRegion) FROM DmPortRegion dmPortRegion";
	private static final String _SQL_COUNT_DMPORTREGION_WHERE = "SELECT COUNT(dmPortRegion) FROM DmPortRegion dmPortRegion WHERE ";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_1 = "dmPortRegion.portRegionCode IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_2 = "dmPortRegion.portRegionCode =:portRegionCode";
	private static final String _FINDER_COLUMN_PORTREGIONCODE_PORTREGIONCODE_3 = "(dmPortRegion.portRegionCode IS NULL OR dmPortRegion.portRegionCode =:portRegionCode)";
	private static final String _FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_1 = "dmPortRegion.portRegionRef IS NULL";
	private static final String _FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_2 = "dmPortRegion.portRegionRef =:portRegionRef";
	private static final String _FINDER_COLUMN_PORTREGIONREF_PORTREGIONREF_3 = "(dmPortRegion.portRegionRef IS NULL OR dmPortRegion.portRegionRef =:portRegionRef)";
	private static final String _FINDER_COLUMN_PORTCODENAME_PORTCODE_1 = "dmPortRegion.portCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODENAME_PORTCODE_2 = "dmPortRegion.portCode =:portCode";
	private static final String _FINDER_COLUMN_PORTCODENAME_PORTCODE_3 = "(dmPortRegion.portCode IS NULL OR dmPortRegion.portCode =:portCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmPortRegion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmPortRegion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmPortRegion exists with the key {";
	

	
}
