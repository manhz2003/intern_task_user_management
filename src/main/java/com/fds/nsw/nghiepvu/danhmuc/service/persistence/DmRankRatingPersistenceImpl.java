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
import com.fds.nsw.nghiepvu.model.DmRankRating;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmRankRatingRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmRankRatingModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmRankRatingPersistenceImpl extends BasePersistence {
	@Autowired
	DmRankRatingRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmRankRating> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmRankRatingUtil} to access the dm rank rating persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmRankRating create(int id) {
		DmRankRating dmRankRating = new DmRankRating();

		
		//dmRankRating.setPrimaryKey(id);

		return dmRankRating;
	}

	/**
	 * Removes the dm rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm rank rating
	 * @return the dm rank rating that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating remove(int id)
		throws NoSuchDmRankRatingException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm rank rating with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm rank rating
	 * @return the dm rank rating that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRankRating remove(Serializable primaryKey)
		throws NoSuchDmRankRatingException, SystemException {
		

		try {
			

			DmRankRating dmRankRating = findByPrimaryKey(primaryKey);

			if (dmRankRating == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmRankRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmRankRating);
			return dmRankRating;
		}
		catch (NoSuchDmRankRatingException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmRankRating remove(DmRankRating DmRankRating) throws SystemException {
	removeImpl(DmRankRating);	return DmRankRating;
}

protected DmRankRating removeImpl

(DmRankRating dmRankRating)
		throws SystemException {
		try {
			repository.delete(dmRankRating);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRankRating;
	}

	
	public DmRankRating updateImpl(
		DmRankRating dmRankRating, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmRankRating);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRankRating;
	}

	
	public DmRankRating findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm rank rating with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmRankRatingException} if it could not be found.
	 *
	 * @param id the primary key of the dm rank rating
	 * @return the dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating findByPrimaryKey(int id)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = fetchByPrimaryKey(id);

		if (dmRankRating == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmRankRatingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmRankRating;
	}

	/**
	 * Returns the dm rank rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm rank rating
	 * @return the dm rank rating, or <code>null</code> if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRankRating fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm rank rating with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm rank rating
	 * @return the dm rank rating, or <code>null</code> if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating fetchByPrimaryKey(int id) throws SystemException {
		DmRankRating dmRankRating = null;

		

		if (dmRankRating == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmRankRating> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmRankRating = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmRankRating;
	}

	/**
	 * Returns all the dm rank ratings where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @return the matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByRankCode(String rankCode)
		throws SystemException {
		return findByRankCode(rankCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm rank ratings where rankCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankCode the rank code
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @return the range of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByRankCode(String rankCode, int start, int end)
		throws SystemException {
		return findByRankCode(rankCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm rank ratings where rankCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankCode the rank code
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByRankCode(String rankCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmRankRating> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMRANKRATING_WHERE);

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
				query.append(DmRankRatingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (rankCode != null) {
					builder.appendNamedParameterMap("rankCode", rankCode);
				}

				list = (List<DmRankRating>)queryFactory.getResultList(builder);
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
	 * Returns the first dm rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating findByRankCode_First(String rankCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = fetchByRankCode_First(rankCode,
				orderByComparator);

		if (dmRankRating != null) {
			return dmRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankCode=");
		msg.append(rankCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRankRatingException(msg.toString());
	}

	/**
	 * Returns the first dm rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm rank rating, or <code>null</code> if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating fetchByRankCode_First(String rankCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRankRating> list = findByRankCode(rankCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating findByRankCode_Last(String rankCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = fetchByRankCode_Last(rankCode,
				orderByComparator);

		if (dmRankRating != null) {
			return dmRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankCode=");
		msg.append(rankCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRankRatingException(msg.toString());
	}

	/**
	 * Returns the last dm rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm rank rating, or <code>null</code> if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating fetchByRankCode_Last(String rankCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRankCode(rankCode);

		List<DmRankRating> list = findByRankCode(rankCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm rank ratings before and after the current dm rank rating in the ordered set where rankCode = &#63;.
	 *
	 * @param id the primary key of the current dm rank rating
	 * @param rankCode the rank code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating[] findByRankCode_PrevAndNext(int id, String rankCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = findByPrimaryKey(id);

		

		try {
			

			DmRankRating[] array = new DmRankRating[3];

			array[0] = getByRankCode_PrevAndNext(dmRankRating,
					rankCode, orderByComparator, true);

			array[1] = dmRankRating;

			array[2] = getByRankCode_PrevAndNext(dmRankRating,
					rankCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmRankRating getByRankCode_PrevAndNext(
		DmRankRating dmRankRating, String rankCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMRANKRATING_WHERE);

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
			query.append(DmRankRatingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (rankCode != null) {
			builder.appendNamedParameterMap("rankCode", rankCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmRankRating);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmRankRating> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm rank ratings where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @return the matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByF_rankNameVNbyLike(String rankNameVN)
		throws SystemException {
		return findByF_rankNameVNbyLike(rankNameVN, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm rank ratings where rankNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankNameVN the rank name v n
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @return the range of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByF_rankNameVNbyLike(String rankNameVN,
		int start, int end) throws SystemException {
		return findByF_rankNameVNbyLike(rankNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm rank ratings where rankNameVN LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param rankNameVN the rank name v n
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findByF_rankNameVNbyLike(String rankNameVN,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmRankRating> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMRANKRATING_WHERE);

			if (rankNameVN == null) {
				query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_1);
			}
			else {
				if (rankNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmRankRatingModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (rankNameVN != null) {
					builder.appendNamedParameterMap("rankNameVN", rankNameVN);
				}

				list = (List<DmRankRating>)queryFactory.getResultList(builder);
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
	 * Returns the first dm rank rating in the ordered set where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating findByF_rankNameVNbyLike_First(String rankNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = fetchByF_rankNameVNbyLike_First(rankNameVN,
				orderByComparator);

		if (dmRankRating != null) {
			return dmRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankNameVN=");
		msg.append(rankNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRankRatingException(msg.toString());
	}

	/**
	 * Returns the first dm rank rating in the ordered set where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm rank rating, or <code>null</code> if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating fetchByF_rankNameVNbyLike_First(String rankNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRankRating> list = findByF_rankNameVNbyLike(rankNameVN, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm rank rating in the ordered set where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating findByF_rankNameVNbyLike_Last(String rankNameVN,
		OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = fetchByF_rankNameVNbyLike_Last(rankNameVN,
				orderByComparator);

		if (dmRankRating != null) {
			return dmRankRating;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("rankNameVN=");
		msg.append(rankNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmRankRatingException(msg.toString());
	}

	/**
	 * Returns the last dm rank rating in the ordered set where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm rank rating, or <code>null</code> if a matching dm rank rating could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating fetchByF_rankNameVNbyLike_Last(String rankNameVN,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_rankNameVNbyLike(rankNameVN);

		List<DmRankRating> list = findByF_rankNameVNbyLike(rankNameVN,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm rank ratings before and after the current dm rank rating in the ordered set where rankNameVN LIKE &#63;.
	 *
	 * @param id the primary key of the current dm rank rating
	 * @param rankNameVN the rank name v n
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm rank rating
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRankRatingException if a dm rank rating with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRankRating[] findByF_rankNameVNbyLike_PrevAndNext(int id,
		String rankNameVN, OrderByComparator orderByComparator)
		throws NoSuchDmRankRatingException, SystemException {
		DmRankRating dmRankRating = findByPrimaryKey(id);

		

		try {
			

			DmRankRating[] array = new DmRankRating[3];

			array[0] = getByF_rankNameVNbyLike_PrevAndNext(
					dmRankRating, rankNameVN, orderByComparator, true);

			array[1] = dmRankRating;

			array[2] = getByF_rankNameVNbyLike_PrevAndNext(
					dmRankRating, rankNameVN, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmRankRating getByF_rankNameVNbyLike_PrevAndNext(
		 DmRankRating dmRankRating, String rankNameVN,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMRANKRATING_WHERE);

		if (rankNameVN == null) {
			query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_1);
		}
		else {
			if (rankNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_2);
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
			query.append(DmRankRatingModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (rankNameVN != null) {
			builder.appendNamedParameterMap("rankNameVN", rankNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmRankRating);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmRankRating> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm rank ratings.
	 *
	 * @return the dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm rank ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @return the range of dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm rank ratings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm rank ratings
	 * @param end the upper bound of the range of dm rank ratings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRankRating> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRankRating> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMRANKRATING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMRANKRATING.concat(DmRankRatingModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmRankRating>) queryFactory.getResultList(builder);
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
	 * Removes all the dm rank ratings where rankCode = &#63; from the database.
	 *
	 * @param rankCode the rank code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRankCode(String rankCode) throws SystemException {
		for (DmRankRating dmRankRating : findByRankCode(rankCode)) {
			repository.delete(dmRankRating);
		}
	}

	/**
	 * Removes all the dm rank ratings where rankNameVN LIKE &#63; from the database.
	 *
	 * @param rankNameVN the rank name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_rankNameVNbyLike(String rankNameVN)
		throws SystemException {
		for (DmRankRating dmRankRating : findByF_rankNameVNbyLike(rankNameVN)) {
			repository.delete(dmRankRating);
		}
	}

	/**
	 * Removes all the dm rank ratings from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmRankRating dmRankRating : findAll()) {
			repository.delete(dmRankRating);
		}
	}

	/**
	 * Returns the number of dm rank ratings where rankCode = &#63;.
	 *
	 * @param rankCode the rank code
	 * @return the number of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRankCode(String rankCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMRANKRATING_WHERE);

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
	 * Returns the number of dm rank ratings where rankNameVN LIKE &#63;.
	 *
	 * @param rankNameVN the rank name v n
	 * @return the number of matching dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_rankNameVNbyLike(String rankNameVN)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMRANKRATING_WHERE);

			if (rankNameVN == null) {
				query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_1);
			}
			else {
				if (rankNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (rankNameVN != null) {
					builder.appendNamedParameterMap("rankNameVN", rankNameVN);
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
	 * Returns the number of dm rank ratings.
	 *
	 * @return the number of dm rank ratings
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMRANKRATING).build();

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
	 * Initializes the dm rank rating persistence.
	 */
	private static final String _SQL_SELECT_DMRANKRATING = "SELECT dmRankRating FROM DmRankRating dmRankRating";
	private static final String _SQL_SELECT_DMRANKRATING_WHERE = "SELECT dmRankRating FROM DmRankRating dmRankRating WHERE ";
	private static final String _SQL_COUNT_DMRANKRATING = "SELECT COUNT(dmRankRating) FROM DmRankRating dmRankRating";
	private static final String _SQL_COUNT_DMRANKRATING_WHERE = "SELECT COUNT(dmRankRating) FROM DmRankRating dmRankRating WHERE ";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_1 = "dmRankRating.rankCode IS NULL";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_2 = "dmRankRating.rankCode =:rankCode";
	private static final String _FINDER_COLUMN_RANKCODE_RANKCODE_3 = "(dmRankRating.rankCode IS NULL OR dmRankRating.rankCode =:rankCode)";
	private static final String _FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_1 = "dmRankRating.rankNameVN LIKE NULL";
	private static final String _FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_2 = "dmRankRating.rankNameVN LIKE :rankNameVN";
	private static final String _FINDER_COLUMN_F_RANKNAMEVNBYLIKE_RANKNAMEVN_3 = "(dmRankRating.rankNameVN IS NULL OR dmRankRating.rankNameVN LIKE :rankNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmRankRating.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmRankRating exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmRankRating exists with the key {";
	

	
}
