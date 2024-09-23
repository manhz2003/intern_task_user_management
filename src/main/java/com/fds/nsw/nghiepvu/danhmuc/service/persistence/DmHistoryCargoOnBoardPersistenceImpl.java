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
import com.fds.nsw.nghiepvu.model.DmHistoryCargoOnBoard;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryCargoOnBoardRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryCargoOnBoardModelImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryCargoOnBoardPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryCargoOnBoardRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryCargoOnBoard> queryFactory;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * DmHistoryCargoOnBoardUtil} to access the dm history cargo on board
	 * persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to
	 * regenerate this class.
	 */
	public DmHistoryCargoOnBoard create(int id) {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = new DmHistoryCargoOnBoard();

		// dmHistoryCargoOnBoard.setPrimaryKey(id);

		return dmHistoryCargoOnBoard;
	}

	/**
	 * Removes the dm history cargo on board with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history cargo on board
	 * @return the dm history cargo on board that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException if a dm
	 *                                                                history cargo
	 *                                                                on board with
	 *                                                                the primary
	 *                                                                key could not
	 *                                                                be found
	 * @throws SystemException                                        if a system
	 *                                                                exception
	 *                                                                occurred
	 */
	public DmHistoryCargoOnBoard remove(int id) throws NoSuchDmHistoryCargoOnBoardException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history cargo on board with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history cargo on board
	 * @return the dm history cargo on board that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException if a dm
	 *                                                                history cargo
	 *                                                                on board with
	 *                                                                the primary
	 *                                                                key could not
	 *                                                                be found
	 * @throws SystemException                                        if a system
	 *                                                                exception
	 *                                                                occurred
	 */

	public DmHistoryCargoOnBoard remove(Serializable primaryKey)
			throws NoSuchDmHistoryCargoOnBoardException, SystemException {

		try {

			DmHistoryCargoOnBoard dmHistoryCargoOnBoard = findByPrimaryKey(primaryKey);

			if (dmHistoryCargoOnBoard == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryCargoOnBoardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(dmHistoryCargoOnBoard);
			return dmHistoryCargoOnBoard;
		} catch (NoSuchDmHistoryCargoOnBoardException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public DmHistoryCargoOnBoard remove(DmHistoryCargoOnBoard DmHistoryCargoOnBoard) throws SystemException {
	removeImpl(DmHistoryCargoOnBoard);	return DmHistoryCargoOnBoard;
}

protected DmHistoryCargoOnBoard removeImpl

(DmHistoryCargoOnBoard dmHistoryCargoOnBoard) throws SystemException {
		try {
			repository.delete(dmHistoryCargoOnBoard);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryCargoOnBoard;
	}

	public DmHistoryCargoOnBoard updateImpl(DmHistoryCargoOnBoard dmHistoryCargoOnBoard, boolean merge)
			throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryCargoOnBoard);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryCargoOnBoard;
	}

	public DmHistoryCargoOnBoard findByPrimaryKey(Serializable primaryKey)
			throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer) primaryKey).intValue());
	}

	/**
	 * Returns the dm history cargo on board with the primary key or throws a
	 * {@link vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException} if it could
	 * not be found.
	 *
	 * @param id the primary key of the dm history cargo on board
	 * @return the dm history cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException if a dm
	 *                                                                history cargo
	 *                                                                on board with
	 *                                                                the primary
	 *                                                                key could not
	 *                                                                be found
	 * @throws SystemException                                        if a system
	 *                                                                exception
	 *                                                                occurred
	 */
	public DmHistoryCargoOnBoard findByPrimaryKey(int id) throws NoSuchDmHistoryCargoOnBoardException, SystemException {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = fetchByPrimaryKey(id);

		if (dmHistoryCargoOnBoard == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryCargoOnBoardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return dmHistoryCargoOnBoard;
	}

	/**
	 * Returns the dm history cargo on board with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history cargo on board
	 * @return the dm history cargo on board, or <code>null</code> if a dm history
	 *         cargo on board with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public DmHistoryCargoOnBoard fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Integer) primaryKey).intValue());
	}

	/**
	 * Returns the dm history cargo on board with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history cargo on board
	 * @return the dm history cargo on board, or <code>null</code> if a dm history
	 *         cargo on board with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryCargoOnBoard fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = null;

		if (dmHistoryCargoOnBoard == null) {

			boolean hasException = false;

			try {

				Optional<DmHistoryCargoOnBoard> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryCargoOnBoard = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return dmHistoryCargoOnBoard;
	}

	/**
	 * Returns the dm history cargo on board where goodsTypeCode = &#63; and
	 * syncVersion = &#63; or throws a
	 * {@link vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException} if it could
	 * not be found.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion   the sync version
	 * @return the matching dm history cargo on board
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryCargoOnBoardException if a matching
	 *                                                                dm history
	 *                                                                cargo on board
	 *                                                                could not be
	 *                                                                found
	 * @throws SystemException                                        if a system
	 *                                                                exception
	 *                                                                occurred
	 */
	public DmHistoryCargoOnBoard findByF_goodsTypeCode_syncVersion(String goodsTypeCode, String syncVersion)
			throws NoSuchDmHistoryCargoOnBoardException, SystemException {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = fetchByF_goodsTypeCode_syncVersion(goodsTypeCode, syncVersion);

		if (dmHistoryCargoOnBoard == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("goodsTypeCode=");
			msg.append(goodsTypeCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryCargoOnBoardException(msg.toString());
		}

		return dmHistoryCargoOnBoard;
	}

	/**
	 * Returns the dm history cargo on board where goodsTypeCode = &#63; and
	 * syncVersion = &#63; or returns <code>null</code> if it could not be found.
	 * Uses the finder cache.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion   the sync version
	 * @return the matching dm history cargo on board, or <code>null</code> if a
	 *         matching dm history cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryCargoOnBoard fetchByF_goodsTypeCode_syncVersion(String goodsTypeCode, String syncVersion)
			throws SystemException {
		return fetchByF_goodsTypeCode_syncVersion(goodsTypeCode, syncVersion, true);
	}

	/**
	 * Returns the dm history cargo on board where goodsTypeCode = &#63; and
	 * syncVersion = &#63; or returns <code>null</code> if it could not be found,
	 * optionally using the finder cache.
	 *
	 * @param goodsTypeCode     the goods type code
	 * @param syncVersion       the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history cargo on board, or <code>null</code> if a
	 *         matching dm history cargo on board could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryCargoOnBoard fetchByF_goodsTypeCode_syncVersion(String goodsTypeCode, String syncVersion,
			boolean retrieveFromCache) throws SystemException {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = null;
		if (dmHistoryCargoOnBoard == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYCARGOONBOARD_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_1);
			} else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_1);
			} else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryCargoOnBoardModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryCargoOnBoard.class).build();

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryCargoOnBoard = (DmHistoryCargoOnBoard) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return dmHistoryCargoOnBoard;
	}

	/**
	 * Returns all the dm history cargo on boards.
	 *
	 * @return the dm history cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryCargoOnBoard> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history cargo on boards.
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
	 * @param start the lower bound of the range of dm history cargo on boards
	 * @param end   the upper bound of the range of dm history cargo on boards (not
	 *              inclusive)
	 * @return the range of dm history cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryCargoOnBoard> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history cargo on boards.
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
	 * @param start             the lower bound of the range of dm history cargo on
	 *                          boards
	 * @param end               the upper bound of the range of dm history cargo on
	 *                          boards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of dm history cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryCargoOnBoard> findAll(int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		List<DmHistoryCargoOnBoard> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYCARGOONBOARD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_DMHISTORYCARGOONBOARD.concat(DmHistoryCargoOnBoardModelImpl.ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(DmHistoryCargoOnBoard.class).build();

				list = (List<DmHistoryCargoOnBoard>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes the dm history cargo on board where goodsTypeCode = &#63; and
	 * syncVersion = &#63; from the database.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion   the sync version
	 * @return the dm history cargo on board that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryCargoOnBoard removeByF_goodsTypeCode_syncVersion(String goodsTypeCode, String syncVersion)
			throws NoSuchDmHistoryCargoOnBoardException, SystemException {
		DmHistoryCargoOnBoard dmHistoryCargoOnBoard = findByF_goodsTypeCode_syncVersion(goodsTypeCode, syncVersion);

		repository.delete(dmHistoryCargoOnBoard);
		return dmHistoryCargoOnBoard;
	}

	/**
	 * Removes all the dm history cargo on boards from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryCargoOnBoard dmHistoryCargoOnBoard : findAll()) {
			repository.delete(dmHistoryCargoOnBoard);
		}
	}

	/**
	 * Returns the number of dm history cargo on boards where goodsTypeCode = &#63;
	 * and syncVersion = &#63;.
	 *
	 * @param goodsTypeCode the goods type code
	 * @param syncVersion   the sync version
	 * @return the number of matching dm history cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_goodsTypeCode_syncVersion(String goodsTypeCode, String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYCARGOONBOARD_WHERE);

			if (goodsTypeCode == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_1);
			} else {
				if (goodsTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_1);
			} else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_3);
				} else {
					query.append(_FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (goodsTypeCode != null) {
					builder.appendNamedParameterMap("goodsTypeCode", goodsTypeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
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
	 * Returns the number of dm history cargo on boards.
	 *
	 * @return the number of dm history cargo on boards
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false)
						.queryString(_SQL_COUNT_DMHISTORYCARGOONBOARD).build();

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
	 * Initializes the dm history cargo on board persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYCARGOONBOARD = "SELECT dmHistoryCargoOnBoard FROM DmHistoryCargoOnBoard dmHistoryCargoOnBoard";
	private static final String _SQL_SELECT_DMHISTORYCARGOONBOARD_WHERE = "SELECT dmHistoryCargoOnBoard FROM DmHistoryCargoOnBoard dmHistoryCargoOnBoard WHERE ";
	private static final String _SQL_COUNT_DMHISTORYCARGOONBOARD = "SELECT COUNT(dmHistoryCargoOnBoard) FROM DmHistoryCargoOnBoard dmHistoryCargoOnBoard";
	private static final String _SQL_COUNT_DMHISTORYCARGOONBOARD_WHERE = "SELECT COUNT(dmHistoryCargoOnBoard) FROM DmHistoryCargoOnBoard dmHistoryCargoOnBoard WHERE ";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_1 = "dmHistoryCargoOnBoard.goodsTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_2 = "dmHistoryCargoOnBoard.goodsTypeCode =:goodsTypeCode AND ";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_GOODSTYPECODE_3 = "(dmHistoryCargoOnBoard.goodsTypeCode IS NULL OR dmHistoryCargoOnBoard.goodsTypeCode =:goodsTypeCode) AND ";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_1 = "dmHistoryCargoOnBoard.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_2 = "dmHistoryCargoOnBoard.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_F_GOODSTYPECODE_SYNCVERSION_SYNCVERSION_3 = "(dmHistoryCargoOnBoard.syncVersion IS NULL OR dmHistoryCargoOnBoard.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryCargoOnBoard.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryCargoOnBoard exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryCargoOnBoard exists with the key {";

}
