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

package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;

import java.io.Serializable;
import java.sql.Types;
import java.util.List;
import java.util.Optional;

import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
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
import com.fds.nsw.nghiepvu.model.TempHealthCrewPaxList;
import com.fds.nsw.nghiepvu.repository.TempHealthCrewPaxListRepository;
import com.fds.nsw.nghiepvu.service.exception.NoSuchTempHealthCrewPaxListException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j

public class TempHealthCrewPaxListPersistenceImpl extends BasePersistence {
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempHealthCrewPaxList> queryFactory;
	@Autowired
	TempHealthCrewPaxListRepository repository;

	/**
	 * Creates a new temp health crew passenger list with the primary key. Does not
	 * add the temp health crew passenger list to the database.
	 *
	 * @param id the primary key for the new temp health crew passenger list
	 * @return the new temp health crew passenger list
	 */
	public TempHealthCrewPaxList create(long id) {
		TempHealthCrewPaxList tempHealthCrewPaxList = new TempHealthCrewPaxList();

		tempHealthCrewPaxList.setId(id);

		return tempHealthCrewPaxList;
	}

	/**
	 * Removes the temp health crew passenger list with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list that was removed
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         with
	 *                                                                         the
	 *                                                                         primary
	 *                                                                         key
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */
	public TempHealthCrewPaxList remove(long id) throws NoSuchTempHealthCrewPaxListException, SystemException {
		return remove(Long.valueOf(id));
	}


	/**
	 * Removes the temp health crew passenger list with the primary key from the
	 * database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list that was removed
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         with
	 *                                                                         the
	 *                                                                         primary
	 *                                                                         key
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */

	public TempHealthCrewPaxList remove(Serializable primaryKey)
			throws NoSuchTempHealthCrewPaxListException, SystemException {

		Long id = ((Long) primaryKey).longValue();

		TempHealthCrewPaxList tempHealthCrewPassengerList = null;

		try {

			Optional<TempHealthCrewPaxList> optional = repository.findById(id);
			if (optional.isPresent()) {
				tempHealthCrewPassengerList = optional.get();
			}

			if (tempHealthCrewPassengerList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempHealthCrewPaxListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(tempHealthCrewPassengerList);

			return tempHealthCrewPassengerList;
		} catch (NoSuchTempHealthCrewPaxListException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public TempHealthCrewPaxList remove(TempHealthCrewPaxList TempHealthCrewPaxList) throws SystemException {
	removeImpl(TempHealthCrewPaxList);
	return TempHealthCrewPaxList;
}

protected TempHealthCrewPaxList removeImpl(TempHealthCrewPaxList tempHealthCrewPassengerList)
			throws SystemException {
		try {
			repository.delete(tempHealthCrewPassengerList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempHealthCrewPassengerList;
	}

	public TempHealthCrewPaxList updateImpl(TempHealthCrewPaxList tempHealthCrewPassengerList, boolean merge)
			throws SystemException {
		try {
			repository.saveAndFlush(tempHealthCrewPassengerList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempHealthCrewPassengerList;
	}

	/**
	 * Returns the temp health crew passenger list with the primary key or throws a
	 * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list
	 * @throws com.liferay.portal.NoSuchModelException if a temp health crew
	 *                                                 passenger list with the
	 *                                                 primary key could not be
	 *                                                 found
	 * @throws SystemException                         if a system exception
	 *                                                 occurred
	 */

	public TempHealthCrewPaxList findByPrimaryKey(Serializable primaryKey)
			throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the temp health crew passenger list with the primary key or throws a
	 * {@link com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException} if it
	 * could not be found.
	 *
	 * @param id the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         with
	 *                                                                         the
	 *                                                                         primary
	 *                                                                         key
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */
	public TempHealthCrewPaxList findByPrimaryKey(long id)
			throws NoSuchTempHealthCrewPaxListException, SystemException {
		TempHealthCrewPaxList tempHealthCrewPassengerList = fetchByPrimaryKey(id);

		if (tempHealthCrewPassengerList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempHealthCrewPaxListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return tempHealthCrewPassengerList;
	}

	/**
	 * Returns the temp health crew passenger list with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list, or <code>null</code> if a temp
	 *         health crew passenger list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public TempHealthCrewPaxList fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the temp health crew passenger list with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp health crew passenger list
	 * @return the temp health crew passenger list, or <code>null</code> if a temp
	 *         health crew passenger list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthCrewPaxList fetchByPrimaryKey(long id) throws SystemException {
		TempHealthCrewPaxList tempHealthCrewPassengerList = null;

		if (tempHealthCrewPassengerList == null) {

			boolean hasException = false;

			try {

				tempHealthCrewPassengerList = null;
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return tempHealthCrewPassengerList;
	}

	/**
	 * Returns all the temp health crew passenger lists where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findByRequestCode(String requestCode) throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp health crew passenger lists where requestCode
	 * = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start       the lower bound of the range of temp health crew passenger
	 *                    lists
	 * @param end         the upper bound of the range of temp health crew passenger
	 *                    lists (not inclusive)
	 * @return the range of matching temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findByRequestCode(String requestCode, int start, int end)
			throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp health crew passenger lists where
	 * requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param requestCode       the request code
	 * @param start             the lower bound of the range of temp health crew
	 *                          passenger lists
	 * @param end               the upper bound of the range of temp health crew
	 *                          passenger lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findByRequestCode(String requestCode, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		List<TempHealthCrewPaxList> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			} else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.firstResult(start).maxResults(end-start)
						.entityClass(TempHealthCrewPaxList.class).build();

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = null;
				queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (list == null) {

				} else {

				}

			}
		}

		return list;
	}

	/**
	 * Returns the first temp health crew passenger list in the ordered set where
	 * requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param requestCode       the request code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching temp health crew passenger list
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         matching
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */
	public TempHealthCrewPaxList findByRequestCode_First(String requestCode, OrderByComparator orderByComparator)
			throws NoSuchTempHealthCrewPaxListException, SystemException {
		List<TempHealthCrewPaxList> list = findByRequestCode(requestCode, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTempHealthCrewPaxListException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last temp health crew passenger list in the ordered set where
	 * requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param requestCode       the request code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching temp health crew passenger list
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         matching
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */
	public TempHealthCrewPaxList findByRequestCode_Last(String requestCode, OrderByComparator orderByComparator)
			throws NoSuchTempHealthCrewPaxListException, SystemException {
		int count = countByRequestCode(requestCode);

		List<TempHealthCrewPaxList> list = findByRequestCode(requestCode, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchTempHealthCrewPaxListException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the temp health crew passenger lists before and after the current
	 * temp health crew passenger list in the ordered set where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param id                the primary key of the current temp health crew
	 *                          passenger list
	 * @param requestCode       the request code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next temp health crew passenger list
	 * @throws com.fds.nsw.kernel.orm.exception.NoSuchTempHealthCrewPaxListException if a
	 *                                                                         temp
	 *                                                                         health
	 *                                                                         crew
	 *                                                                         passenger
	 *                                                                         list
	 *                                                                         with
	 *                                                                         the
	 *                                                                         primary
	 *                                                                         key
	 *                                                                         could
	 *                                                                         not
	 *                                                                         be
	 *                                                                         found
	 * @throws SystemException                                                 if a
	 *                                                                         system
	 *                                                                         exception
	 *                                                                         occurred
	 */
	public TempHealthCrewPaxList[] findByRequestCode_PrevAndNext(long id, String requestCode,
			OrderByComparator orderByComparator) throws NoSuchTempHealthCrewPaxListException, SystemException {
		TempHealthCrewPaxList tempHealthCrewPassengerList = findByPrimaryKey(id);

		try {

			TempHealthCrewPaxList[] array = new TempHealthCrewPaxList[3];

			array[0] = getByRequestCode_PrevAndNext(tempHealthCrewPassengerList, requestCode, orderByComparator, true);

			array[1] = tempHealthCrewPassengerList;

			array[2] = getByRequestCode_PrevAndNext(tempHealthCrewPassengerList, requestCode, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected TempHealthCrewPaxList getByRequestCode_PrevAndNext(TempHealthCrewPaxList tempHealthCrewPassengerList,
			String requestCode, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE);

		if (requestCode == null) {
			query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
		} else {
			if (requestCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
			} else {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(TempHealthCrewPaxList.class).build();

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempHealthCrewPassengerList);

			for (Object value : values) {
				//builder.appendNamedParameterMap("VALUE", value);
			}
		}

		List<TempHealthCrewPaxList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the temp health crew passenger lists.
	 *
	 * @return the temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp health crew passenger lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp health crew passenger lists
	 * @param end   the upper bound of the range of temp health crew passenger lists
	 *              (not inclusive)
	 * @return the range of temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp health crew passenger lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start             the lower bound of the range of temp health crew
	 *                          passenger lists
	 * @param end               the upper bound of the range of temp health crew
	 *                          passenger lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthCrewPaxList> findAll(int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<TempHealthCrewPaxList> list = null;

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST.concat(ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.firstResult(start).maxResults(end-start)
						.entityClass(TempHealthCrewPaxList.class).build();

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes all the temp health crew passenger lists where requestCode = &#63;
	 * from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode) throws SystemException {
		for (TempHealthCrewPaxList tempHealthCrewPassengerList : findByRequestCode(requestCode)) {
			repository.delete(tempHealthCrewPassengerList);
		}
	}

	/**
	 * Removes all the temp health crew passenger lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempHealthCrewPaxList tempHealthCrewPassengerList : findAll()) {
			repository.delete(tempHealthCrewPassengerList);
		}
	}

	/**
	 * Returns the number of temp health crew passenger lists where requestCode =
	 * &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			} else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.firstResult(QueryUtil.ALL_POS).maxResults(QueryUtil.ALL_POS)
						.entityClass(TempHealthCrewPaxList.class).build();

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of temp health crew passenger lists.
	 *
	 * @return the number of temp health crew passenger lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().queryString(_SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST)
						.sqlQuery(false).entityClass(Long.class).build();

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	public void destroy() {

	}

	private static final String _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST = "SELECT tempHealthCrewPassengerList FROM TempHealthCrewPaxList tempHealthCrewPassengerList";
	private static final String _SQL_SELECT_TEMPHEALTHCREWPASSENGERLIST_WHERE = "SELECT tempHealthCrewPassengerList FROM TempHealthCrewPaxList tempHealthCrewPassengerList WHERE ";
	private static final String _SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST = "SELECT COUNT(tempHealthCrewPassengerList) FROM TempHealthCrewPaxList tempHealthCrewPassengerList";
	private static final String _SQL_COUNT_TEMPHEALTHCREWPASSENGERLIST_WHERE = "SELECT COUNT(tempHealthCrewPassengerList) FROM TempHealthCrewPaxList tempHealthCrewPassengerList WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempHealthCrewPassengerList.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempHealthCrewPassengerList.requestCode = :requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempHealthCrewPassengerList.requestCode IS NULL OR tempHealthCrewPassengerList.requestCode = :requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempHealthCrewPassengerList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempHealthCrewPaxList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempHealthCrewPaxList exists with the key {";
	public static final String TABLE_NAME = "TEMP_HEALTH_CREW_PAX_LIST";
	public static final Object[][] TABLE_COLUMNS = { { "ID", Types.BIGINT }, { "RequestCode", Types.VARCHAR },
			{ "HealthCrewPassengerCode", Types.VARCHAR }, { "PassengerOrCrewCode", Types.VARCHAR },
			{ "PassengerOrCrewName", Types.VARCHAR }, { "ClassOrRating", Types.INTEGER },
			{ "StateVisitedCode", Types.VARCHAR }, { "PortVisitedCode", Types.VARCHAR },
			{ "FromDate", Types.TIMESTAMP }, { "ToDate", Types.TIMESTAMP } };
	public static final String TABLE_SQL_CREATE = "create table TEMP_HEALTH_CREW_PAX_LIST (ID LONG not null primary key,RequestCode VARCHAR(75) null,HealthCrewPassengerCode VARCHAR(75) null,PassengerOrCrewCode VARCHAR(75) null,PassengerOrCrewName VARCHAR(75) null,ClassOrRating INTEGER,StateVisitedCode VARCHAR(75) null,PortVisitedCode VARCHAR(75) null,FromDate DATE null,ToDate DATE null)";
	public static final String TABLE_SQL_DROP = "drop table TEMP_HEALTH_CREW_PAX_LIST";
	public static final String ORDER_BY_JPQL = " ORDER BY tempHealthCrewPassengerList.id ASC";
	public static final String ORDER_BY_SQL = " ORDER BY TEMP_HEALTH_CREW_PAX_LIST.ID ASC";
	public static final String DATA_SOURCE = "gtDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";

}