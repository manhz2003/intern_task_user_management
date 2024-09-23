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
import com.fds.nsw.nghiepvu.model.DmHistoryShipyard;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryShipyardRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryShipyardModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryShipyardPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryShipyardRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryShipyard> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryShipyardUtil} to access the dm history shipyard persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryShipyard create(long id) {
		DmHistoryShipyard dmHistoryShipyard = new DmHistoryShipyard();

		
		//dmHistoryShipyard.setPrimaryKey(id);

		return dmHistoryShipyard;
	}

	/**
	 * Removes the dm history shipyard with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history shipyard
	 * @return the dm history shipyard that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException if a dm history shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard remove(long id)
		throws NoSuchDmHistoryShipyardException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm history shipyard with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history shipyard
	 * @return the dm history shipyard that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException if a dm history shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipyard remove(Serializable primaryKey)
		throws NoSuchDmHistoryShipyardException, SystemException {
		

		try {
			

			DmHistoryShipyard dmHistoryShipyard = findByPrimaryKey(primaryKey);

			if (dmHistoryShipyard == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryShipyardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryShipyard);
			return dmHistoryShipyard;
		}
		catch (NoSuchDmHistoryShipyardException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryShipyard remove(DmHistoryShipyard DmHistoryShipyard) throws SystemException {
	removeImpl(DmHistoryShipyard);	return DmHistoryShipyard;
}

protected DmHistoryShipyard removeImpl

(DmHistoryShipyard dmHistoryShipyard)
		throws SystemException {
		try {
			repository.delete(dmHistoryShipyard);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipyard;
	}

	
	public DmHistoryShipyard updateImpl(
		DmHistoryShipyard dmHistoryShipyard,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryShipyard);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryShipyard;
	}

	
	public DmHistoryShipyard findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history shipyard with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException} if it could not be found.
	 *
	 * @param id the primary key of the dm history shipyard
	 * @return the dm history shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException if a dm history shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard findByPrimaryKey(long id)
		throws NoSuchDmHistoryShipyardException, SystemException {
		DmHistoryShipyard dmHistoryShipyard = fetchByPrimaryKey(id);

		if (dmHistoryShipyard == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryShipyardException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryShipyard;
	}

	/**
	 * Returns the dm history shipyard with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history shipyard
	 * @return the dm history shipyard, or <code>null</code> if a dm history shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryShipyard fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history shipyard with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history shipyard
	 * @return the dm history shipyard, or <code>null</code> if a dm history shipyard with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard fetchByPrimaryKey(long id)
		throws SystemException {
		DmHistoryShipyard dmHistoryShipyard = null;

		

		if (dmHistoryShipyard == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryShipyard> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryShipyard = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryShipyard;
	}

	/**
	 * Returns the dm history shipyard where ShipYardCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException} if it could not be found.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param SyncVersion the sync version
	 * @return the matching dm history shipyard
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryShipyardException if a matching dm history shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard findByF_shipYardCode_syncVersion(
		String ShipYardCode, String SyncVersion)
		throws NoSuchDmHistoryShipyardException, SystemException {
		DmHistoryShipyard dmHistoryShipyard = fetchByF_shipYardCode_syncVersion(ShipYardCode,
				SyncVersion);

		if (dmHistoryShipyard == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipYardCode=");
			msg.append(ShipYardCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryShipyardException(msg.toString());
		}

		return dmHistoryShipyard;
	}

	/**
	 * Returns the dm history shipyard where ShipYardCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param SyncVersion the sync version
	 * @return the matching dm history shipyard, or <code>null</code> if a matching dm history shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard fetchByF_shipYardCode_syncVersion(
		String ShipYardCode, String SyncVersion) throws SystemException {
		return fetchByF_shipYardCode_syncVersion(ShipYardCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history shipyard where ShipYardCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history shipyard, or <code>null</code> if a matching dm history shipyard could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard fetchByF_shipYardCode_syncVersion(
		String ShipYardCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryShipyard dmHistoryShipyard = null;
		if (dmHistoryShipyard == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSHIPYARD_WHERE);

			if (ShipYardCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_1);
			}
			else {
				if (ShipYardCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryShipyardModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryShipyard.class).build();

				

				if (ShipYardCode != null) {
					builder.appendNamedParameterMap("ShipYardCode", ShipYardCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryShipyard = (DmHistoryShipyard) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryShipyard;
	}

	/**
	 * Returns all the dm history shipyards.
	 *
	 * @return the dm history shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipyard> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history shipyards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history shipyards
	 * @param end the upper bound of the range of dm history shipyards (not inclusive)
	 * @return the range of dm history shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipyard> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history shipyards.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history shipyards
	 * @param end the upper bound of the range of dm history shipyards (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryShipyard> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryShipyard> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSHIPYARD);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSHIPYARD.concat(DmHistoryShipyardModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryShipyard>) queryFactory.getResultList(builder);
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
	 * Removes the dm history shipyard where ShipYardCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param SyncVersion the sync version
	 * @return the dm history shipyard that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryShipyard removeByF_shipYardCode_syncVersion(
		String ShipYardCode, String SyncVersion)
		throws NoSuchDmHistoryShipyardException, SystemException {
		DmHistoryShipyard dmHistoryShipyard = findByF_shipYardCode_syncVersion(ShipYardCode,
				SyncVersion);

		repository.delete(dmHistoryShipyard);
			return dmHistoryShipyard;
	}

	/**
	 * Removes all the dm history shipyards from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryShipyard dmHistoryShipyard : findAll()) {
			repository.delete(dmHistoryShipyard);
		}
	}

	/**
	 * Returns the number of dm history shipyards where ShipYardCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param ShipYardCode the ship yard code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipYardCode_syncVersion(String ShipYardCode,
		String SyncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSHIPYARD_WHERE);

			if (ShipYardCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_1);
			}
			else {
				if (ShipYardCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipYardCode != null) {
					builder.appendNamedParameterMap("ShipYardCode", ShipYardCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
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
	 * Returns the number of dm history shipyards.
	 *
	 * @return the number of dm history shipyards
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSHIPYARD).build();

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
	 * Initializes the dm history shipyard persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSHIPYARD = "SELECT dmHistoryShipyard FROM DmHistoryShipyard dmHistoryShipyard";
	private static final String _SQL_SELECT_DMHISTORYSHIPYARD_WHERE = "SELECT dmHistoryShipyard FROM DmHistoryShipyard dmHistoryShipyard WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSHIPYARD = "SELECT COUNT(dmHistoryShipyard) FROM DmHistoryShipyard dmHistoryShipyard";
	private static final String _SQL_COUNT_DMHISTORYSHIPYARD_WHERE = "SELECT COUNT(dmHistoryShipyard) FROM DmHistoryShipyard dmHistoryShipyard WHERE ";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_1 =
		"dmHistoryShipyard.ShipYardCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_2 =
		"dmHistoryShipyard.ShipYardCode =:ShipYardCode AND ";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SHIPYARDCODE_3 =
		"(dmHistoryShipyard.ShipYardCode IS NULL OR dmHistoryShipyard.ShipYardCode =:ShipYardCode) AND ";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryShipyard.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryShipyard.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_SHIPYARDCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryShipyard.SyncVersion IS NULL OR dmHistoryShipyard.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryShipyard.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryShipyard exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryShipyard exists with the key {";
	

	
}
