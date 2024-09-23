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

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmGtShipPosition;
import com.fds.nsw.nghiepvu.modelImpl.DmGTShipPositionModelImpl;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtShipPositionRepository;



import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtShipPositionPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtShipPositionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtShipPosition> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtShipPositionUtil} to access the dm g t ship position persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtShipPosition create(long id) {
		DmGtShipPosition dmGTShipPosition = new DmGtShipPosition();

		
		//dmGTShipPosition.setPrimaryKey(id);

		return dmGTShipPosition;
	}

	/**
	 * Removes the dm g t ship position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm g t ship position
	 * @return the dm g t ship position that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition remove(long id)
		throws NoSuchDmGtShipPositionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm g t ship position with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm g t ship position
	 * @return the dm g t ship position that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtShipPosition remove(Serializable primaryKey)
		throws NoSuchDmGtShipPositionException, SystemException {
		

		try {
			

			DmGtShipPosition dmGTShipPosition = findByPrimaryKey(primaryKey);

			if (dmGTShipPosition == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtShipPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGTShipPosition);
			return dmGTShipPosition;
		}
		catch (NoSuchDmGtShipPositionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtShipPosition remove(DmGtShipPosition DmGtShipPosition) throws SystemException {
	removeImpl(DmGtShipPosition);
	return DmGtShipPosition;
}

protected DmGtShipPosition removeImpl(DmGtShipPosition dmGTShipPosition)
		throws SystemException {
		try {
			repository.delete(dmGTShipPosition);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTShipPosition;
	}

	
	public DmGtShipPosition updateImpl(
		DmGtShipPosition dmGTShipPosition,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGTShipPosition);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGTShipPosition;
	}

	
	public DmGtShipPosition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t ship position with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException} if it could not be found.
	 *
	 * @param id the primary key of the dm g t ship position
	 * @return the dm g t ship position
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition findByPrimaryKey(long id)
		throws NoSuchDmGtShipPositionException, SystemException {
		DmGtShipPosition dmGTShipPosition = fetchByPrimaryKey(id);

		if (dmGTShipPosition == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtShipPositionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGTShipPosition;
	}

	/**
	 * Returns the dm g t ship position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm g t ship position
	 * @return the dm g t ship position, or <code>null</code> if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtShipPosition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm g t ship position with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm g t ship position
	 * @return the dm g t ship position, or <code>null</code> if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtShipPosition dmGTShipPosition = null;

		

		if (dmGTShipPosition == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtShipPosition> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGTShipPosition = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGTShipPosition;
	}

	/**
	 * Returns all the dm g t ship positions where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @return the matching dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findByPositionCode(String positionCode)
		throws SystemException {
		return findByPositionCode(positionCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm g t ship positions where positionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param positionCode the position code
	 * @param start the lower bound of the range of dm g t ship positions
	 * @param end the upper bound of the range of dm g t ship positions (not inclusive)
	 * @return the range of matching dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findByPositionCode(String positionCode,
		int start, int end) throws SystemException {
		return findByPositionCode(positionCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm g t ship positions where positionCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param positionCode the position code
	 * @param start the lower bound of the range of dm g t ship positions
	 * @param end the upper bound of the range of dm g t ship positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findByPositionCode(String positionCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmGtShipPosition> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGTSHIPPOSITION_WHERE);

			if (positionCode == null) {
				query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_1);
			}
			else {
				if (positionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGTShipPositionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (positionCode != null) {
					builder.appendNamedParameterMap("positionCode", positionCode);
				}

				list = (List<DmGtShipPosition>)queryFactory.getResultList(builder);
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
	 * Returns the first dm g t ship position in the ordered set where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm g t ship position
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a matching dm g t ship position could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition findByPositionCode_First(String positionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtShipPositionException, SystemException {
		DmGtShipPosition dmGTShipPosition = fetchByPositionCode_First(positionCode,
				orderByComparator);

		if (dmGTShipPosition != null) {
			return dmGTShipPosition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("positionCode=");
		msg.append(positionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtShipPositionException(msg.toString());
	}

	/**
	 * Returns the first dm g t ship position in the ordered set where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm g t ship position, or <code>null</code> if a matching dm g t ship position could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition fetchByPositionCode_First(String positionCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtShipPosition> list = findByPositionCode(positionCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm g t ship position in the ordered set where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm g t ship position
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a matching dm g t ship position could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition findByPositionCode_Last(String positionCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtShipPositionException, SystemException {
		DmGtShipPosition dmGTShipPosition = fetchByPositionCode_Last(positionCode,
				orderByComparator);

		if (dmGTShipPosition != null) {
			return dmGTShipPosition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("positionCode=");
		msg.append(positionCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtShipPositionException(msg.toString());
	}

	/**
	 * Returns the last dm g t ship position in the ordered set where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm g t ship position, or <code>null</code> if a matching dm g t ship position could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition fetchByPositionCode_Last(String positionCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPositionCode(positionCode);

		List<DmGtShipPosition> list = findByPositionCode(positionCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm g t ship positions before and after the current dm g t ship position in the ordered set where positionCode = &#63;.
	 *
	 * @param id the primary key of the current dm g t ship position
	 * @param positionCode the position code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm g t ship position
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtShipPositionException if a dm g t ship position with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtShipPosition[] findByPositionCode_PrevAndNext(long id,
		String positionCode, OrderByComparator orderByComparator)
		throws NoSuchDmGtShipPositionException, SystemException {
		DmGtShipPosition dmGTShipPosition = findByPrimaryKey(id);

		

		try {
			

			DmGtShipPosition[] array = new DmGtShipPosition[3];

			array[0] = getByPositionCode_PrevAndNext(dmGTShipPosition,
					positionCode, orderByComparator, true);

			array[1] = dmGTShipPosition;

			array[2] = getByPositionCode_PrevAndNext(dmGTShipPosition,
					positionCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtShipPosition getByPositionCode_PrevAndNext(
		DmGtShipPosition dmGTShipPosition, String positionCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTSHIPPOSITION_WHERE);

		if (positionCode == null) {
			query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_1);
		}
		else {
			if (positionCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_2);
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
			query.append(DmGTShipPositionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (positionCode != null) {
			builder.appendNamedParameterMap("positionCode", positionCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGTShipPosition);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtShipPosition> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm g t ship positions.
	 *
	 * @return the dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm g t ship positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t ship positions
	 * @param end the upper bound of the range of dm g t ship positions (not inclusive)
	 * @return the range of dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm g t ship positions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm g t ship positions
	 * @param end the upper bound of the range of dm g t ship positions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtShipPosition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtShipPosition> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTSHIPPOSITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTSHIPPOSITION.concat(DmGTShipPositionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtShipPosition>) queryFactory.getResultList(builder);
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
	 * Removes all the dm g t ship positions where positionCode = &#63; from the database.
	 *
	 * @param positionCode the position code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPositionCode(String positionCode)
		throws SystemException {
		for (DmGtShipPosition dmGTShipPosition : findByPositionCode(
				positionCode)) {
			repository.delete(dmGTShipPosition);
		}
	}

	/**
	 * Removes all the dm g t ship positions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtShipPosition dmGTShipPosition : findAll()) {
			repository.delete(dmGTShipPosition);
		}
	}

	/**
	 * Returns the number of dm g t ship positions where positionCode = &#63;.
	 *
	 * @param positionCode the position code
	 * @return the number of matching dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPositionCode(String positionCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTSHIPPOSITION_WHERE);

			if (positionCode == null) {
				query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_1);
			}
			else {
				if (positionCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_POSITIONCODE_POSITIONCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (positionCode != null) {
					builder.appendNamedParameterMap("positionCode", positionCode);
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
	 * Returns the number of dm g t ship positions.
	 *
	 * @return the number of dm g t ship positions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTSHIPPOSITION).build();

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
	 * Initializes the dm g t ship position persistence.
	 */
	private static final String _SQL_SELECT_DMGTSHIPPOSITION = "SELECT dmGTShipPosition FROM DmGtShipPosition dmGTShipPosition";
	private static final String _SQL_SELECT_DMGTSHIPPOSITION_WHERE = "SELECT dmGTShipPosition FROM DmGtShipPosition dmGTShipPosition WHERE ";
	private static final String _SQL_COUNT_DMGTSHIPPOSITION = "SELECT COUNT(dmGTShipPosition) FROM DmGtShipPosition dmGTShipPosition";
	private static final String _SQL_COUNT_DMGTSHIPPOSITION_WHERE = "SELECT COUNT(dmGTShipPosition) FROM DmGtShipPosition dmGTShipPosition WHERE ";
	private static final String _FINDER_COLUMN_POSITIONCODE_POSITIONCODE_1 = "dmGTShipPosition.positionCode IS NULL";
	private static final String _FINDER_COLUMN_POSITIONCODE_POSITIONCODE_2 = "dmGTShipPosition.positionCode =:positionCode";
	private static final String _FINDER_COLUMN_POSITIONCODE_POSITIONCODE_3 = "(dmGTShipPosition.positionCode IS NULL OR dmGTShipPosition.positionCode =:positionCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGTShipPosition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtShipPosition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtShipPosition exists with the key {";
	
}
