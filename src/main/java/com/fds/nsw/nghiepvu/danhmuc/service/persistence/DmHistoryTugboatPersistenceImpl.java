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
import com.fds.nsw.nghiepvu.model.DmHistoryTugboat;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryTugboatRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryTugboatModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryTugboatPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryTugboatRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryTugboat> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryTugboatUtil} to access the dm history tugboat persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryTugboat create(long id) {
		DmHistoryTugboat dmHistoryTugboat = new DmHistoryTugboat();

		
		//dmHistoryTugboat.setPrimaryKey(id);

		return dmHistoryTugboat;
	}

	/**
	 * Removes the dm history tugboat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm history tugboat
	 * @return the dm history tugboat that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException if a dm history tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat remove(long Id)
		throws NoSuchDmHistoryTugboatException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm history tugboat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history tugboat
	 * @return the dm history tugboat that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException if a dm history tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryTugboat remove(Serializable primaryKey)
		throws NoSuchDmHistoryTugboatException, SystemException {
		

		try {
			

			DmHistoryTugboat dmHistoryTugboat = findByPrimaryKey(primaryKey);

			if (dmHistoryTugboat == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryTugboatException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryTugboat);
			return dmHistoryTugboat;
		}
		catch (NoSuchDmHistoryTugboatException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryTugboat remove(DmHistoryTugboat DmHistoryTugboat) throws SystemException {
	removeImpl(DmHistoryTugboat);	return DmHistoryTugboat;
}

protected DmHistoryTugboat removeImpl

(DmHistoryTugboat dmHistoryTugboat)
		throws SystemException {
		try {
			repository.delete(dmHistoryTugboat);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryTugboat;
	}

	
	public DmHistoryTugboat updateImpl(
		DmHistoryTugboat dmHistoryTugboat, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryTugboat);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryTugboat;
	}

	
	public DmHistoryTugboat findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history tugboat with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException} if it could not be found.
	 *
	 * @param Id the primary key of the dm history tugboat
	 * @return the dm history tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException if a dm history tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat findByPrimaryKey(long Id)
		throws NoSuchDmHistoryTugboatException, SystemException {
		DmHistoryTugboat dmHistoryTugboat = fetchByPrimaryKey(Id);

		if (dmHistoryTugboat == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmHistoryTugboatException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmHistoryTugboat;
	}

	/**
	 * Returns the dm history tugboat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history tugboat
	 * @return the dm history tugboat, or <code>null</code> if a dm history tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryTugboat fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history tugboat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm history tugboat
	 * @return the dm history tugboat, or <code>null</code> if a dm history tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat fetchByPrimaryKey(long Id)
		throws SystemException {
		DmHistoryTugboat dmHistoryTugboat = null;

		

		if (dmHistoryTugboat == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryTugboat> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmHistoryTugboat = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryTugboat;
	}

	/**
	 * Returns the dm history tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException} if it could not be found.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat findByF_tugboatCompanyCode_shipCode_syncVersion(
		String TugboatCompanyCode, String ShipCode, String SyncVersion)
		throws NoSuchDmHistoryTugboatException, SystemException {
		DmHistoryTugboat dmHistoryTugboat = fetchByF_tugboatCompanyCode_shipCode_syncVersion(TugboatCompanyCode,
				ShipCode, SyncVersion);

		if (dmHistoryTugboat == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TugboatCompanyCode=");
			msg.append(TugboatCompanyCode);

			msg.append(", ShipCode=");
			msg.append(ShipCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryTugboatException(msg.toString());
		}

		return dmHistoryTugboat;
	}

	/**
	 * Returns the dm history tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat, or <code>null</code> if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat fetchByF_tugboatCompanyCode_shipCode_syncVersion(
		String TugboatCompanyCode, String ShipCode, String SyncVersion)
		throws SystemException {
		return fetchByF_tugboatCompanyCode_shipCode_syncVersion(TugboatCompanyCode,
			ShipCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history tugboat, or <code>null</code> if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat fetchByF_tugboatCompanyCode_shipCode_syncVersion(
		String TugboatCompanyCode, String ShipCode, String SyncVersion,
		boolean retrieveFromCache) throws SystemException {
		DmHistoryTugboat dmHistoryTugboat = null;
		if (dmHistoryTugboat == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_DMHISTORYTUGBOAT_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2);
				}
			}

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryTugboatModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryTugboat.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryTugboat = (DmHistoryTugboat) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryTugboat;
	}

	/**
	 * Returns the dm history tugboat where ShipCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException} if it could not be found.
	 *
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatException if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat findByF_shipCode_syncVersion(String ShipCode,
		String SyncVersion)
		throws NoSuchDmHistoryTugboatException, SystemException {
		DmHistoryTugboat dmHistoryTugboat = fetchByF_shipCode_syncVersion(ShipCode,
				SyncVersion);

		if (dmHistoryTugboat == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipCode=");
			msg.append(ShipCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryTugboatException(msg.toString());
		}

		return dmHistoryTugboat;
	}

	/**
	 * Returns the dm history tugboat where ShipCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat, or <code>null</code> if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat fetchByF_shipCode_syncVersion(String ShipCode,
		String SyncVersion) throws SystemException {
		return fetchByF_shipCode_syncVersion(ShipCode, SyncVersion, true);
	}

	/**
	 * Returns the dm history tugboat where ShipCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history tugboat, or <code>null</code> if a matching dm history tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat fetchByF_shipCode_syncVersion(String ShipCode,
		String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryTugboat dmHistoryTugboat = null;
		if (dmHistoryTugboat == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYTUGBOAT_WHERE);

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryTugboatModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryTugboat.class).build();

				

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryTugboat = (DmHistoryTugboat) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryTugboat;
	}

	/**
	 * Returns all the dm history tugboats.
	 *
	 * @return the dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboat> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history tugboats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history tugboats
	 * @param end the upper bound of the range of dm history tugboats (not inclusive)
	 * @return the range of dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboat> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history tugboats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history tugboats
	 * @param end the upper bound of the range of dm history tugboats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboat> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryTugboat> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYTUGBOAT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYTUGBOAT.concat(DmHistoryTugboatModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryTugboat>) queryFactory.getResultList(builder);
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
	 * Removes the dm history tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the dm history tugboat that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat removeByF_tugboatCompanyCode_shipCode_syncVersion(
		String TugboatCompanyCode, String ShipCode, String SyncVersion)
		throws NoSuchDmHistoryTugboatException, SystemException {
		DmHistoryTugboat dmHistoryTugboat = findByF_tugboatCompanyCode_shipCode_syncVersion(TugboatCompanyCode,
				ShipCode, SyncVersion);

		repository.delete(dmHistoryTugboat);
			return dmHistoryTugboat;
	}

	/**
	 * Removes the dm history tugboat where ShipCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the dm history tugboat that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboat removeByF_shipCode_syncVersion(String ShipCode,
		String SyncVersion)
		throws NoSuchDmHistoryTugboatException, SystemException {
		DmHistoryTugboat dmHistoryTugboat = findByF_shipCode_syncVersion(ShipCode,
				SyncVersion);

		repository.delete(dmHistoryTugboat);
			return dmHistoryTugboat;
	}

	/**
	 * Removes all the dm history tugboats from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryTugboat dmHistoryTugboat : findAll()) {
			repository.delete(dmHistoryTugboat);
		}
	}

	/**
	 * Returns the number of dm history tugboats where TugboatCompanyCode = &#63; and ShipCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_tugboatCompanyCode_shipCode_syncVersion(
		String TugboatCompanyCode, String ShipCode, String SyncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_DMHISTORYTUGBOAT_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2);
				}
			}

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
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
	 * Returns the number of dm history tugboats where ShipCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param ShipCode the ship code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipCode_syncVersion(String ShipCode, String SyncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYTUGBOAT_WHERE);

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
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
	 * Returns the number of dm history tugboats.
	 *
	 * @return the number of dm history tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYTUGBOAT).build();

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
	 * Initializes the dm history tugboat persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYTUGBOAT = "SELECT dmHistoryTugboat FROM DmHistoryTugboat dmHistoryTugboat";
	private static final String _SQL_SELECT_DMHISTORYTUGBOAT_WHERE = "SELECT dmHistoryTugboat FROM DmHistoryTugboat dmHistoryTugboat WHERE ";
	private static final String _SQL_COUNT_DMHISTORYTUGBOAT = "SELECT COUNT(dmHistoryTugboat) FROM DmHistoryTugboat dmHistoryTugboat";
	private static final String _SQL_COUNT_DMHISTORYTUGBOAT_WHERE = "SELECT COUNT(dmHistoryTugboat) FROM DmHistoryTugboat dmHistoryTugboat WHERE ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1 =
		"dmHistoryTugboat.TugboatCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2 =
		"dmHistoryTugboat.TugboatCompanyCode =:TugboatCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3 =
		"(dmHistoryTugboat.TugboatCompanyCode IS NULL OR dmHistoryTugboat.TugboatCompanyCode =:TugboatCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_1 =
		"dmHistoryTugboat.ShipCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_2 =
		"dmHistoryTugboat.ShipCode =:ShipCode AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SHIPCODE_3 =
		"(dmHistoryTugboat.ShipCode IS NULL OR dmHistoryTugboat.ShipCode =:ShipCode) AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryTugboat.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryTugboat.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryTugboat.SyncVersion IS NULL OR dmHistoryTugboat.SyncVersion =:SyncVersion)";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_1 =
		"dmHistoryTugboat.ShipCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_2 =
		"dmHistoryTugboat.ShipCode =:ShipCode AND ";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SHIPCODE_3 =
		"(dmHistoryTugboat.ShipCode IS NULL OR dmHistoryTugboat.ShipCode =:ShipCode) AND ";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryTugboat.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryTugboat.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryTugboat.SyncVersion IS NULL OR dmHistoryTugboat.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryTugboat.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryTugboat exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryTugboat exists with the key {";
	

	
}
