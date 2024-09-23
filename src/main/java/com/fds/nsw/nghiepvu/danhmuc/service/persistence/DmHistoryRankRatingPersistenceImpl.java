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
import com.fds.nsw.nghiepvu.model.DmHistoryRankRating;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryRankRatingRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryRankRatingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryRankRatingPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryRankRatingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryRankRating> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryRankRatingUtil} to access the dm history rank rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryRankRating create(int id) {
		DmHistoryRankRating dmHistoryRankRating = new DmHistoryRankRating();

		
		//dmHistoryRankRating.setPrimaryKey(id);

		return dmHistoryRankRating;
	}

	/**
	 * Removes the dm history rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history rank rating
	 * @return the dm history rank rating that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating remove(int id)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history rank rating
	 * @return the dm history rank rating that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryRankRating remove(Serializable primaryKey)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		

		try {
			

			DmHistoryRankRating dmHistoryRankRating = findByPrimaryKey(primaryKey);

			if (dmHistoryRankRating == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryRankRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryRankRating);
			return dmHistoryRankRating;
		}
		catch (NoSuchDmHistoryRankRatingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryRankRating remove(DmHistoryRankRating DmHistoryRankRating) throws SystemException {
	removeImpl(DmHistoryRankRating);	return DmHistoryRankRating;
}

protected DmHistoryRankRating removeImpl

(
		DmHistoryRankRating dmHistoryRankRating) throws SystemException {
		try {
			repository.delete(dmHistoryRankRating);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryRankRating;
	}

	
	public DmHistoryRankRating updateImpl(
		DmHistoryRankRating dmHistoryRankRating,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryRankRating);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryRankRating;
	}

	
	public DmHistoryRankRating findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history rank rating with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException} if it could not be found.
	 *
	 * @param id the primary key of the dm history rank rating
	 * @return the dm history rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating findByPrimaryKey(int id)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = fetchByPrimaryKey(id);

		if (dmHistoryRankRating == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryRankRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryRankRating;
	}

	/**
	 * Returns the dm history rank rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history rank rating
	 * @return the dm history rank rating, or <code>null</code> if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryRankRating fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history rank rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history rank rating
	 * @return the dm history rank rating, or <code>null</code> if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryRankRating dmHistoryRankRating = null;

		

		if (dmHistoryRankRating == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryRankRating> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryRankRating = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryRankRating;
	}

	/**
	 * Returns all the dm history rank ratings where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @return the matching dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findByRankCode(String rankCode)
		throws SystemException {
		return findByRankCode(rankCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm history rank ratings where rankCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankCode the rank code
	 * @param start the lower bound of the range of dm history rank ratings
	 * @param end the upper bound of the range of dm history rank ratings (not inclusive)
	 * @return the range of matching dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findByRankCode(String rankCode, int start,
		int end) throws SystemException {
		return findByRankCode(rankCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history rank ratings where rankCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankCode the rank code
	 * @param start the lower bound of the range of dm history rank ratings
	 * @param end the upper bound of the range of dm history rank ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findByRankCode(String rankCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryRankRating> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYRANKRATING_WHERE);

			if (rankCode == null) {
				query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_1);
			}
			else {
				if (rankCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryRankRatingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (rankCode != null) {
					builder.appendNamedParameterMap("rankCode", rankCode);
				}

				list = (List<DmHistoryRankRating>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating findByRankCode_First(String rankCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = fetchByRankCode_First(rankCode,
				orderByComparator);

		if (dmHistoryRankRating != null) {
			return dmHistoryRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankCode=");
		msg.append(rankCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryRankRatingException(msg.toString());
	}

	/**
	 * Returns the first dm history rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history rank rating, or <code>null</code> if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating fetchByRankCode_First(String rankCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryRankRating> list = findByRankCode(rankCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating findByRankCode_Last(String rankCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = fetchByRankCode_Last(rankCode,
				orderByComparator);

		if (dmHistoryRankRating != null) {
			return dmHistoryRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankCode=");
		msg.append(rankCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryRankRatingException(msg.toString());
	}

	/**
	 * Returns the last dm history rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history rank rating, or <code>null</code> if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating fetchByRankCode_Last(String rankCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRankCode(rankCode);

		List<DmHistoryRankRating> list = findByRankCode(rankCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history rank ratings before and after the current dm history rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param id the primary key of the current dm history rank rating
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a dm history rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating[] findByRankCode_PrevAndNext(int id,
		String rankCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = findByPrimaryKey(id);

		

		try {
			

			DmHistoryRankRating[] array = new DmHistoryRankRating[3];

			array[0] = getByRankCode_PrevAndNext(dmHistoryRankRating,
					rankCode, orderByComparator, true);

			array[1] = dmHistoryRankRating;

			array[2] = getByRankCode_PrevAndNext(dmHistoryRankRating,
					rankCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryRankRating getByRankCode_PrevAndNext(
		DmHistoryRankRating dmHistoryRankRating, String rankCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYRANKRATING_WHERE);

		if (rankCode == null) {
			query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_1);
		}
		else {
			if (rankCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_2);
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
			query.append(DmHistoryRankRatingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (rankCode != null) {
			builder.appendNamedParameterMap("rankCode", rankCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryRankRating);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryRankRating> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history rank rating where rankCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException} if it could not be found.
	 *
	 * @param rankCode the rank code
	 * @param syncVersion the sync version
	 * @return the matching dm history rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRankRatingException if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating findByRankCodeAndSyncVersion(String rankCode,
		String syncVersion)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = fetchByRankCodeAndSyncVersion(rankCode,
				syncVersion);

		if (dmHistoryRankRating == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("rankCode=");
			msg.append(rankCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryRankRatingException(msg.toString());
		}

		return dmHistoryRankRating;
	}

	/**
	 * Returns the dm history rank rating where rankCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param rankCode the rank code
	 * @param syncVersion the sync version
	 * @return the matching dm history rank rating, or <code>null</code> if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating fetchByRankCodeAndSyncVersion(String rankCode,
		String syncVersion) throws SystemException {
		return fetchByRankCodeAndSyncVersion(rankCode, syncVersion, true);
	}

	/**
	 * Returns the dm history rank rating where rankCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param rankCode the rank code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history rank rating, or <code>null</code> if a matching dm history rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating fetchByRankCodeAndSyncVersion(String rankCode,
		String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryRankRating dmHistoryRankRating = null;
		if (dmHistoryRankRating == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYRANKRATING_WHERE);

			if (rankCode == null) {
				query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_1);
			}
			else {
				if (rankCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryRankRatingModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryRankRating.class).build();

				

				if (rankCode != null) {
					builder.appendNamedParameterMap("rankCode", rankCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryRankRating = (DmHistoryRankRating) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryRankRating;
	}

	/**
	 * Returns all the dm history rank ratings.
	 *
	 * @return the dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history rank ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history rank ratings
	 * @param end the upper bound of the range of dm history rank ratings (not inclusive)
	 * @return the range of dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history rank ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history rank ratings
	 * @param end the upper bound of the range of dm history rank ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRankRating> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryRankRating> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYRANKRATING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYRANKRATING.concat(DmHistoryRankRatingModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryRankRating>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history rank ratings where rankCode = &#63; from the database.
	 *
	 * @param rankCode the rank code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRankCode(String rankCode) throws SystemException {
		for (DmHistoryRankRating dmHistoryRankRating : findByRankCode(rankCode)) {
			repository.delete(dmHistoryRankRating);
		}
	}

	/**
	 * Removes the dm history rank rating where rankCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param rankCode the rank code
	 * @param syncVersion the sync version
	 * @return the dm history rank rating that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRankRating removeByRankCodeAndSyncVersion(String rankCode,
		String syncVersion)
		throws NoSuchDmHistoryRankRatingException, SystemException {
		DmHistoryRankRating dmHistoryRankRating = findByRankCodeAndSyncVersion(rankCode,
				syncVersion);

		repository.delete(dmHistoryRankRating);
			return dmHistoryRankRating;
	}

	/**
	 * Removes all the dm history rank ratings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryRankRating dmHistoryRankRating : findAll()) {
			repository.delete(dmHistoryRankRating);
		}
	}

	/**
	 * Returns the number of dm history rank ratings where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @return the number of matching dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRankCode(String rankCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYRANKRATING_WHERE);

			if (rankCode == null) {
				query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_1);
			}
			else {
				if (rankCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODE_RANKCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (rankCode != null) {
					builder.appendNamedParameterMap("rankCode", rankCode);
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
	 * Returns the number of dm history rank ratings where rankCode = &#63; and syncVersion = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRankCodeAndSyncVersion(String rankCode, String syncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYRANKRATING_WHERE);

			if (rankCode == null) {
				query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_1);
			}
			else {
				if (rankCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (rankCode != null) {
					builder.appendNamedParameterMap("rankCode", rankCode);
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
	 * Returns the number of dm history rank ratings.
	 *
	 * @return the number of dm history rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYRANKRATING).build();

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
	 * Initializes the dm history rank rating persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYRANKRATING = "SELECT dmHistoryRankRating FROM DmHistoryRankRating dmHistoryRankRating";
	private static final String _SQL_SELECT_DMHISTORYRANKRATING_WHERE = "SELECT dmHistoryRankRating FROM DmHistoryRankRating dmHistoryRankRating WHERE ";
	private static final String _SQL_COUNT_DMHISTORYRANKRATING = "SELECT COUNT(dmHistoryRankRating) FROM DmHistoryRankRating dmHistoryRankRating";
	private static final String _SQL_COUNT_DMHISTORYRANKRATING_WHERE = "SELECT COUNT(dmHistoryRankRating) FROM DmHistoryRankRating dmHistoryRankRating WHERE ";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_1 = "dmHistoryRankRating.rankCode IS NULL";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_2 = "dmHistoryRankRating.rankCode =:rankCode";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_3 = "(dmHistoryRankRating.rankCode IS NULL OR dmHistoryRankRating.rankCode =:rankCode)";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_1 =
		"dmHistoryRankRating.rankCode IS NULL AND ";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_2 =
		"dmHistoryRankRating.rankCode =:rankCode AND ";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_RANKCODE_3 =
		"(dmHistoryRankRating.rankCode IS NULL OR dmHistoryRankRating.rankCode =:rankCode) AND ";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryRankRating.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryRankRating.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_RANKCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryRankRating.syncVersion IS NULL OR dmHistoryRankRating.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryRankRating.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryRankRating exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryRankRating exists with the key {";
	

	
}
