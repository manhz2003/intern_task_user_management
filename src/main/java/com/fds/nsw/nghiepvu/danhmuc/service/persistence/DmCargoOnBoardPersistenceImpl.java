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
import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import com.fds.nsw.nghiepvu.model.DmCargoOnBoard;
import com.fds.nsw.nghiepvu.repository.AswmsgMessagequeueRepository;
import com.fds.nsw.nghiepvu.repository.DmCargoOnBoardRepository;
import com.fds.nsw.nghiepvu.service.exception.NoSuchAswmsgMessageQueueException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmCargoOnBoardException;

import com.fds.nsw.nghiepvu.modelImpl.DmCargoOnBoardModelImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmCargoOnBoardPersistenceImpl extends BasePersistence {
	@Autowired
	DmCargoOnBoardRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmCargoOnBoard> queryFactory;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * DmCargoOnBoardUtil} to access the dm cargo on board persistence. Modify
	 * <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmCargoOnBoard create(int id) {
		DmCargoOnBoard dmCargoOnBoard = new DmCargoOnBoard();

		// dmCargoOnBoard.setPrimaryKey(id);

		return dmCargoOnBoard;
	}

	/**
	 * Removes the dm cargo on board with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm cargo on board
	 * @return the dm cargo on board that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a dm cargo on
	 *                                                         board with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard remove(int id) throws NoSuchDmCargoOnBoardException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm cargo on board with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm cargo on board
	 * @return the dm cargo on board that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a dm cargo on
	 *                                                         board with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */

	public DmCargoOnBoard remove(Serializable primaryKey) throws NoSuchDmCargoOnBoardException, SystemException {

		try {

			DmCargoOnBoard dmCargoOnBoard = findByPrimaryKey(primaryKey);

			if (dmCargoOnBoard == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmCargoOnBoardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(dmCargoOnBoard);
			return dmCargoOnBoard;
		} catch (NoSuchDmCargoOnBoardException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public DmCargoOnBoard remove(DmCargoOnBoard DmCargoOnBoard) throws SystemException {
	removeImpl(DmCargoOnBoard);	return DmCargoOnBoard;
}

protected DmCargoOnBoard removeImpl

(DmCargoOnBoard dmCargoOnBoard) throws SystemException {
		try {
			repository.delete(dmCargoOnBoard);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmCargoOnBoard;
	}

	public DmCargoOnBoard updateImpl(DmCargoOnBoard dmCargoOnBoard, boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmCargoOnBoard);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmCargoOnBoard;
	}

	public DmCargoOnBoard findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer) primaryKey).intValue());
	}

	/**
	 * Returns the dm cargo on board with the primary key or throws a
	 * {@link vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException} if it could not be
	 * found.
	 *
	 * @param id the primary key of the dm cargo on board
	 * @return the dm cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a dm cargo on
	 *                                                         board with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard findByPrimaryKey(int id) throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = fetchByPrimaryKey(id);

		if (dmCargoOnBoard == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmCargoOnBoardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return dmCargoOnBoard;
	}

	/**
	 * Returns the dm cargo on board with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm cargo on board
	 * @return the dm cargo on board, or <code>null</code> if a dm cargo on board
	 *         with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public DmCargoOnBoard fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Integer) primaryKey).intValue());
	}

	/**
	 * Returns the dm cargo on board with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm cargo on board
	 * @return the dm cargo on board, or <code>null</code> if a dm cargo on board
	 *         with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard fetchByPrimaryKey(int id) throws SystemException {
		DmCargoOnBoard dmCargoOnBoard = null;

		if (dmCargoOnBoard == null) {

			boolean hasException = false;

			try {

				Optional<DmCargoOnBoard> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmCargoOnBoard = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return dmCargoOnBoard;
	}

	/**
	 * Returns the dm cargo on board where goodsTypeCode = &#63; or throws a
	 * {@link vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException} if it could not be
	 * found.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the matching dm cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a matching dm
	 *                                                         cargo on board could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard findByF_goodsTypeCode(String goodsTypeCode)
			throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = fetchByF_goodsTypeCode(goodsTypeCode);

		if (dmCargoOnBoard == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("goodsTypeCode=");
			msg.append(goodsTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmCargoOnBoardException(msg.toString());
		}

		return dmCargoOnBoard;
	}

	/**
	 * Returns the dm cargo on board where goodsTypeCode = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the matching dm cargo on board, or <code>null</code> if a matching dm
	 *         cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard fetchByF_goodsTypeCode(String goodsTypeCode) throws SystemException {
		return fetchByF_goodsTypeCode(goodsTypeCode, true);
	}

	/**
	 * Returns the dm cargo on board where goodsTypeCode = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param goodsTypeCode     the goods type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm cargo on board, or <code>null</code> if a matching dm
	 *         cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard fetchByF_goodsTypeCode(String goodsTypeCode, boolean retrieveFromCache)
			throws SystemException {
		DmCargoOnBoard dmCargoOnBoard = null;
		if (dmCargoOnBoard == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMCARGOONBOARD_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_1);
			} else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_2);
				}
			}

			query.append(DmCargoOnBoardModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmCargoOnBoard.class).build();

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				dmCargoOnBoard = (DmCargoOnBoard) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return dmCargoOnBoard;
	}

	/**
	 * Returns all the dm cargo on boards where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @return the matching dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findByF_goodsTypeNameVN(String goodsTypeNameVN) throws SystemException {
		return findByF_goodsTypeNameVN(goodsTypeNameVN, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm cargo on boards where goodsTypeNameVN LIKE
	 * &#63;.
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
	 * @param goodsTypeNameVN the goods type name v n
	 * @param start           the lower bound of the range of dm cargo on boards
	 * @param end             the upper bound of the range of dm cargo on boards
	 *                        (not inclusive)
	 * @return the range of matching dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findByF_goodsTypeNameVN(String goodsTypeNameVN, int start, int end)
			throws SystemException {
		return findByF_goodsTypeNameVN(goodsTypeNameVN, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm cargo on boards where goodsTypeNameVN
	 * LIKE &#63;.
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
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param start             the lower bound of the range of dm cargo on boards
	 * @param end               the upper bound of the range of dm cargo on boards
	 *                          (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findByF_goodsTypeNameVN(String goodsTypeNameVN, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<DmCargoOnBoard> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMCARGOONBOARD_WHERE);

			if (goodsTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_1);
			} else {
				if (goodsTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(DmCargoOnBoardModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmCargoOnBoard.class).build();

				if (goodsTypeNameVN != null) {
					builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
				}

				list = (List<DmCargoOnBoard>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first dm cargo on board in the ordered set where goodsTypeNameVN
	 * LIKE &#63;.
	 *
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a matching dm
	 *                                                         cargo on board could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard findByF_goodsTypeNameVN_First(String goodsTypeNameVN, OrderByComparator orderByComparator)
			throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = fetchByF_goodsTypeNameVN_First(goodsTypeNameVN, orderByComparator);

		if (dmCargoOnBoard != null) {
			return dmCargoOnBoard;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeNameVN=");
		msg.append(goodsTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCargoOnBoardException(msg.toString());
	}

	/**
	 * Returns the first dm cargo on board in the ordered set where goodsTypeNameVN
	 * LIKE &#63;.
	 *
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching dm cargo on board, or <code>null</code> if a
	 *         matching dm cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard fetchByF_goodsTypeNameVN_First(String goodsTypeNameVN, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmCargoOnBoard> list = findByF_goodsTypeNameVN(goodsTypeNameVN, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm cargo on board in the ordered set where goodsTypeNameVN
	 * LIKE &#63;.
	 *
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a matching dm
	 *                                                         cargo on board could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard findByF_goodsTypeNameVN_Last(String goodsTypeNameVN, OrderByComparator orderByComparator)
			throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = fetchByF_goodsTypeNameVN_Last(goodsTypeNameVN, orderByComparator);

		if (dmCargoOnBoard != null) {
			return dmCargoOnBoard;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("goodsTypeNameVN=");
		msg.append(goodsTypeNameVN);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmCargoOnBoardException(msg.toString());
	}

	/**
	 * Returns the last dm cargo on board in the ordered set where goodsTypeNameVN
	 * LIKE &#63;.
	 *
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching dm cargo on board, or <code>null</code> if a
	 *         matching dm cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard fetchByF_goodsTypeNameVN_Last(String goodsTypeNameVN, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByF_goodsTypeNameVN(goodsTypeNameVN);

		List<DmCargoOnBoard> list = findByF_goodsTypeNameVN(goodsTypeNameVN, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm cargo on boards before and after the current dm cargo on board
	 * in the ordered set where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param id                the primary key of the current dm cargo on board
	 * @param goodsTypeNameVN   the goods type name v n
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next dm cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmCargoOnBoardException if a dm cargo on
	 *                                                         board with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public DmCargoOnBoard[] findByF_goodsTypeNameVN_PrevAndNext(int id, String goodsTypeNameVN,
			OrderByComparator orderByComparator) throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = findByPrimaryKey(id);

		try {

			DmCargoOnBoard[] array = new DmCargoOnBoard[3];

			array[0] = getByF_goodsTypeNameVN_PrevAndNext(dmCargoOnBoard, goodsTypeNameVN, orderByComparator, true);

			array[1] = dmCargoOnBoard;

			array[2] = getByF_goodsTypeNameVN_PrevAndNext(dmCargoOnBoard, goodsTypeNameVN, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected DmCargoOnBoard getByF_goodsTypeNameVN_PrevAndNext(DmCargoOnBoard dmCargoOnBoard, String goodsTypeNameVN,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMCARGOONBOARD_WHERE);

		if (goodsTypeNameVN == null) {
			query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_1);
		} else {
			if (goodsTypeNameVN.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_3);
			} else {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_2);
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
			query.append(DmCargoOnBoardModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		if (goodsTypeNameVN != null) {
			builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmCargoOnBoard);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<DmCargoOnBoard> list = queryFactory.getResultList(builder);

        if (list!= null && list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the dm cargo on boards.
	 *
	 * @return the dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm cargo on boards.
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
	 * @param start the lower bound of the range of dm cargo on boards
	 * @param end   the upper bound of the range of dm cargo on boards (not
	 *              inclusive)
	 * @return the range of dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm cargo on boards.
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
	 * @param start             the lower bound of the range of dm cargo on boards
	 * @param end               the upper bound of the range of dm cargo on boards
	 *                          (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmCargoOnBoard> findAll(int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmCargoOnBoard> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMCARGOONBOARD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_DMCARGOONBOARD.concat(DmCargoOnBoardModelImpl.ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmCargoOnBoard.class).build();

				list = (List<DmCargoOnBoard>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes the dm cargo on board where goodsTypeCode = &#63; from the database.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the dm cargo on board that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmCargoOnBoard removeByF_goodsTypeCode(String goodsTypeCode)
			throws NoSuchDmCargoOnBoardException, SystemException {
		DmCargoOnBoard dmCargoOnBoard = findByF_goodsTypeCode(goodsTypeCode);

		repository.delete(dmCargoOnBoard);
		return dmCargoOnBoard;
	}

	/**
	 * Removes all the dm cargo on boards where goodsTypeNameVN LIKE &#63; from the
	 * database.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_goodsTypeNameVN(String goodsTypeNameVN) throws SystemException {
		for (DmCargoOnBoard dmCargoOnBoard : findByF_goodsTypeNameVN(goodsTypeNameVN)) {
			repository.delete(dmCargoOnBoard);
		}
	}

	/**
	 * Removes all the dm cargo on boards from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmCargoOnBoard dmCargoOnBoard : findAll()) {
			repository.delete(dmCargoOnBoard);
		}
	}

	/**
	 * Returns the number of dm cargo on boards where goodsTypeCode = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @return the number of matching dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_goodsTypeCode(String goodsTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMCARGOONBOARD_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_1);
			} else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
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
	 * Returns the number of dm cargo on boards where goodsTypeNameVN LIKE &#63;.
	 *
	 * @param goodsTypeNameVN the goods type name v n
	 * @return the number of matching dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_goodsTypeNameVN(String goodsTypeNameVN) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMCARGOONBOARD_WHERE);

			if (goodsTypeNameVN == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_1);
			} else {
				if (goodsTypeNameVN.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (goodsTypeNameVN != null) {
					builder.appendNamedParameterMap("goodsTypeNameVN", goodsTypeNameVN);
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
	 * Returns the number of dm cargo on boards.
	 *
	 * @return the number of dm cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMCARGOONBOARD)
						.build();

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
	 * Initializes the dm cargo on board persistence.
	 */
	private static final String _SQL_SELECT_DMCARGOONBOARD = "SELECT dmCargoOnBoard FROM DmCargoOnBoard dmCargoOnBoard";
	private static final String _SQL_SELECT_DMCARGOONBOARD_WHERE = "SELECT dmCargoOnBoard FROM DmCargoOnBoard dmCargoOnBoard WHERE ";
	private static final String _SQL_COUNT_DMCARGOONBOARD = "SELECT COUNT(dmCargoOnBoard) FROM DmCargoOnBoard dmCargoOnBoard";
	private static final String _SQL_COUNT_DMCARGOONBOARD_WHERE = "SELECT COUNT(dmCargoOnBoard) FROM DmCargoOnBoard dmCargoOnBoard WHERE ";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_1 = "dmCargoOnBoard.goodsTypeCode IS NULL";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_2 = "dmCargoOnBoard.goodsTypeCode =:goodsTypeCode";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_GOODSTYPECODE_3 = "(dmCargoOnBoard.goodsTypeCode IS NULL OR dmCargoOnBoard.goodsTypeCode =:goodsTypeCode)";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_1 = "dmCargoOnBoard.goodsTypeNameVN LIKE NULL";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_2 = "dmCargoOnBoard.goodsTypeNameVN LIKE :goodsTypeNameVN";
	private static final String _FINDER_COLUMN_F_GOODSTYPENAMEVN_GOODSTYPENAMEVN_3 = "(dmCargoOnBoard.goodsTypeNameVN IS NULL OR dmCargoOnBoard.goodsTypeNameVN LIKE :goodsTypeNameVN)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmCargoOnBoard.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmCargoOnBoard exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmCargoOnBoard exists with the key {";

}
