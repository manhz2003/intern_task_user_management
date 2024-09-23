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
import com.fds.nsw.nghiepvu.model.DmHistoryPilot;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPilotRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPilotModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPilotPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPilotRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPilot> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPilotUtil} to access the dm history pilot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPilot create(long id) {
		DmHistoryPilot dmHistoryPilot = new DmHistoryPilot();

		
		//dmHistoryPilot.setPrimaryKey(id);

		return dmHistoryPilot;
	}

	/**
	 * Removes the dm history pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm history pilot
	 * @return the dm history pilot that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException if a dm history pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot remove(long Id)
		throws NoSuchDmHistoryPilotException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm history pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history pilot
	 * @return the dm history pilot that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException if a dm history pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPilot remove(Serializable primaryKey)
		throws NoSuchDmHistoryPilotException, SystemException {
		

		try {
			

			DmHistoryPilot dmHistoryPilot = findByPrimaryKey(primaryKey);

			if (dmHistoryPilot == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPilot);
			return dmHistoryPilot;
		}
		catch (NoSuchDmHistoryPilotException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPilot remove(DmHistoryPilot DmHistoryPilot) throws SystemException {
	removeImpl(DmHistoryPilot);	return DmHistoryPilot;
}

protected DmHistoryPilot removeImpl

(DmHistoryPilot dmHistoryPilot)
		throws SystemException {
		try {
			repository.delete(dmHistoryPilot);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPilot;
	}

	
	public DmHistoryPilot updateImpl(
		DmHistoryPilot dmHistoryPilot, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPilot);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPilot;
	}

	
	public DmHistoryPilot findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history pilot with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException} if it could not be found.
	 *
	 * @param Id the primary key of the dm history pilot
	 * @return the dm history pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException if a dm history pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot findByPrimaryKey(long Id)
		throws NoSuchDmHistoryPilotException, SystemException {
		DmHistoryPilot dmHistoryPilot = fetchByPrimaryKey(Id);

		if (dmHistoryPilot == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmHistoryPilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmHistoryPilot;
	}

	/**
	 * Returns the dm history pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history pilot
	 * @return the dm history pilot, or <code>null</code> if a dm history pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPilot fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm history pilot
	 * @return the dm history pilot, or <code>null</code> if a dm history pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot fetchByPrimaryKey(long Id) throws SystemException {
		DmHistoryPilot dmHistoryPilot = null;

		

		if (dmHistoryPilot == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPilot> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmHistoryPilot = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPilot;
	}

	/**
	 * Returns the dm history pilot where PilotCompanyCode = &#63; and PilotCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException} if it could not be found.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot findByF_pilotCompanyCode_pilotCode_syncVersion(
		String PilotCompanyCode, String PilotCode, String SyncVersion)
		throws NoSuchDmHistoryPilotException, SystemException {
		DmHistoryPilot dmHistoryPilot = fetchByF_pilotCompanyCode_pilotCode_syncVersion(PilotCompanyCode,
				PilotCode, SyncVersion);

		if (dmHistoryPilot == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCompanyCode=");
			msg.append(PilotCompanyCode);

			msg.append(", PilotCode=");
			msg.append(PilotCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPilotException(msg.toString());
		}

		return dmHistoryPilot;
	}

	/**
	 * Returns the dm history pilot where PilotCompanyCode = &#63; and PilotCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot, or <code>null</code> if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot fetchByF_pilotCompanyCode_pilotCode_syncVersion(
		String PilotCompanyCode, String PilotCode, String SyncVersion)
		throws SystemException {
		return fetchByF_pilotCompanyCode_pilotCode_syncVersion(PilotCompanyCode,
			PilotCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history pilot where PilotCompanyCode = &#63; and PilotCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history pilot, or <code>null</code> if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot fetchByF_pilotCompanyCode_pilotCode_syncVersion(
		String PilotCompanyCode, String PilotCode, String SyncVersion,
		boolean retrieveFromCache) throws SystemException {
		DmHistoryPilot dmHistoryPilot = null;
		if (dmHistoryPilot == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DMHISTORYPILOT_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_2);
				}
			}

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPilotModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPilot.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryPilot = (DmHistoryPilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPilot;
	}

	/**
	 * Returns the dm history pilot where PilotCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException} if it could not be found.
	 *
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotException if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot findByF_pilotCode_syncVersion(String PilotCode,
		String SyncVersion)
		throws NoSuchDmHistoryPilotException, SystemException {
		DmHistoryPilot dmHistoryPilot = fetchByF_pilotCode_syncVersion(PilotCode,
				SyncVersion);

		if (dmHistoryPilot == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCode=");
			msg.append(PilotCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPilotException(msg.toString());
		}

		return dmHistoryPilot;
	}

	/**
	 * Returns the dm history pilot where PilotCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot, or <code>null</code> if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot fetchByF_pilotCode_syncVersion(String PilotCode,
		String SyncVersion) throws SystemException {
		return fetchByF_pilotCode_syncVersion(PilotCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history pilot where PilotCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history pilot, or <code>null</code> if a matching dm history pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot fetchByF_pilotCode_syncVersion(String PilotCode,
		String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPilot dmHistoryPilot = null;
		if (dmHistoryPilot == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPILOT_WHERE);

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPilotModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPilot.class).build();;

				

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryPilot = (DmHistoryPilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPilot;
	}

	/**
	 * Returns all the dm history pilots.
	 *
	 * @return the dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilot> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history pilots
	 * @param end the upper bound of the range of dm history pilots (not inclusive)
	 * @return the range of dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilot> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history pilots
	 * @param end the upper bound of the range of dm history pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilot> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPilot> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPILOT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPILOT.concat(DmHistoryPilotModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPilot>) queryFactory.getResultList(builder);
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
	 * Removes the dm history pilot where PilotCompanyCode = &#63; and PilotCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the dm history pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot removeByF_pilotCompanyCode_pilotCode_syncVersion(
		String PilotCompanyCode, String PilotCode, String SyncVersion)
		throws NoSuchDmHistoryPilotException, SystemException {
		DmHistoryPilot dmHistoryPilot = findByF_pilotCompanyCode_pilotCode_syncVersion(PilotCompanyCode,
				PilotCode, SyncVersion);

		repository.delete(dmHistoryPilot);
			return dmHistoryPilot;
	}

	/**
	 * Removes the dm history pilot where PilotCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the dm history pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilot removeByF_pilotCode_syncVersion(String PilotCode,
		String SyncVersion)
		throws NoSuchDmHistoryPilotException, SystemException {
		DmHistoryPilot dmHistoryPilot = findByF_pilotCode_syncVersion(PilotCode,
				SyncVersion);

		repository.delete(dmHistoryPilot);
			return dmHistoryPilot;
	}

	/**
	 * Removes all the dm history pilots from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPilot dmHistoryPilot : findAll()) {
			repository.delete(dmHistoryPilot);
		}
	}

	/**
	 * Returns the number of dm history pilots where PilotCompanyCode = &#63; and PilotCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCompanyCode_pilotCode_syncVersion(
		String PilotCompanyCode, String PilotCode, String SyncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DMHISTORYPILOT_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_2);
				}
			}

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
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
	 * Returns the number of dm history pilots where PilotCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCode_syncVersion(String PilotCode,
		String SyncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPILOT_WHERE);

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
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
	 * Returns the number of dm history pilots.
	 *
	 * @return the number of dm history pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPILOT).build();

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
	 * Initializes the dm history pilot persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPILOT = "SELECT dmHistoryPilot FROM DmHistoryPilot dmHistoryPilot";
	private static final String _SQL_SELECT_DMHISTORYPILOT_WHERE = "SELECT dmHistoryPilot FROM DmHistoryPilot dmHistoryPilot WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPILOT = "SELECT COUNT(dmHistoryPilot) FROM DmHistoryPilot dmHistoryPilot";
	private static final String _SQL_COUNT_DMHISTORYPILOT_WHERE = "SELECT COUNT(dmHistoryPilot) FROM DmHistoryPilot dmHistoryPilot WHERE ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_1 =
		"dmHistoryPilot.PilotCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_2 =
		"dmHistoryPilot.PilotCompanyCode =:PilotCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCOMPANYCODE_3 =
		"(dmHistoryPilot.PilotCompanyCode IS NULL OR dmHistoryPilot.PilotCompanyCode =:PilotCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_1 =
		"dmHistoryPilot.PilotCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_2 =
		"dmHistoryPilot.PilotCode =:PilotCode AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_PILOTCODE_3 =
		"(dmHistoryPilot.PilotCode IS NULL OR dmHistoryPilot.PilotCode =:PilotCode) AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPilot.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPilot.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPilot.SyncVersion IS NULL OR dmHistoryPilot.SyncVersion =:SyncVersion)";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_1 =
		"dmHistoryPilot.PilotCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_2 =
		"dmHistoryPilot.PilotCode =:PilotCode AND ";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_PILOTCODE_3 =
		"(dmHistoryPilot.PilotCode IS NULL OR dmHistoryPilot.PilotCode =:PilotCode) AND ";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPilot.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPilot.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_PILOTCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPilot.SyncVersion IS NULL OR dmHistoryPilot.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPilot.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPilot exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPilot exists with the key {";
	

	
}
