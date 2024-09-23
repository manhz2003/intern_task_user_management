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
import com.fds.nsw.nghiepvu.model.DmHistoryRepresentative;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryRepresentativeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryRepresentativeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryRepresentativePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryRepresentativeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryRepresentative> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryRepresentativeUtil} to access the dm history representative persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryRepresentative create(int id) {
		DmHistoryRepresentative dmHistoryRepresentative = new DmHistoryRepresentative();

		
		//dmHistoryRepresentative.setPrimaryKey(id);

		return dmHistoryRepresentative;
	}

	/**
	 * Removes the dm history representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history representative
	 * @return the dm history representative that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative remove(int id)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history representative with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history representative
	 * @return the dm history representative that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryRepresentative remove(Serializable primaryKey)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		

		try {
			

			DmHistoryRepresentative dmHistoryRepresentative = findByPrimaryKey(primaryKey);

			if (dmHistoryRepresentative == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryRepresentativeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryRepresentative);
			return dmHistoryRepresentative;
		}
		catch (NoSuchDmHistoryRepresentativeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryRepresentative remove(DmHistoryRepresentative DmHistoryRepresentative) throws SystemException {
	removeImpl(DmHistoryRepresentative);	return DmHistoryRepresentative;
}

protected DmHistoryRepresentative removeImpl

(
		DmHistoryRepresentative dmHistoryRepresentative)
		throws SystemException {
		try {
			repository.delete(dmHistoryRepresentative);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryRepresentative;
	}

	
	public DmHistoryRepresentative updateImpl(
		DmHistoryRepresentative dmHistoryRepresentative,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryRepresentative);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryRepresentative;
	}

	
	public DmHistoryRepresentative findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history representative with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history representative
	 * @return the dm history representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative findByPrimaryKey(int id)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = fetchByPrimaryKey(id);

		if (dmHistoryRepresentative == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryRepresentativeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryRepresentative;
	}

	/**
	 * Returns the dm history representative with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history representative
	 * @return the dm history representative, or <code>null</code> if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryRepresentative fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history representative with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history representative
	 * @return the dm history representative, or <code>null</code> if a dm history representative with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = null;

		

		if (dmHistoryRepresentative == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryRepresentative> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryRepresentative = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryRepresentative;
	}

	/**
	 * Returns the dm history representative where repCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException} if it could not be found.
	 *
	 * @param repCode the rep code
	 * @return the matching dm history representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative findByRepCode(String repCode)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = fetchByRepCode(repCode);

		if (dmHistoryRepresentative == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("repCode=");
			msg.append(repCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryRepresentativeException(msg.toString());
		}

		return dmHistoryRepresentative;
	}

	/**
	 * Returns the dm history representative where repCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param repCode the rep code
	 * @return the matching dm history representative, or <code>null</code> if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative fetchByRepCode(String repCode)
		throws SystemException {
		return fetchByRepCode(repCode, true);
	}

	/**
	 * Returns the dm history representative where repCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param repCode the rep code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history representative, or <code>null</code> if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative fetchByRepCode(String repCode,
		boolean retrieveFromCache) throws SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = null;
		if (dmHistoryRepresentative == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMHISTORYREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODE_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_2);
				}
			}

			query.append(DmHistoryRepresentativeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryRepresentative.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
				}

				dmHistoryRepresentative = (DmHistoryRepresentative) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryRepresentative;
	}

	/**
	 * Returns the dm history representative where repCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException} if it could not be found.
	 *
	 * @param repCode the rep code
	 * @param syncVersion the sync version
	 * @return the matching dm history representative
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryRepresentativeException if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative findByRepCodeAndSyncVersion(String repCode,
		String syncVersion)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = fetchByRepCodeAndSyncVersion(repCode,
				syncVersion);

		if (dmHistoryRepresentative == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("repCode=");
			msg.append(repCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryRepresentativeException(msg.toString());
		}

		return dmHistoryRepresentative;
	}

	/**
	 * Returns the dm history representative where repCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param repCode the rep code
	 * @param syncVersion the sync version
	 * @return the matching dm history representative, or <code>null</code> if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative fetchByRepCodeAndSyncVersion(
		String repCode, String syncVersion) throws SystemException {
		return fetchByRepCodeAndSyncVersion(repCode, syncVersion, true);
	}

	/**
	 * Returns the dm history representative where repCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param repCode the rep code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history representative, or <code>null</code> if a matching dm history representative could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative fetchByRepCodeAndSyncVersion(
		String repCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = null;
		if (dmHistoryRepresentative == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryRepresentativeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryRepresentative.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryRepresentative = (DmHistoryRepresentative) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryRepresentative;
	}

	/**
	 * Returns all the dm history representatives.
	 *
	 * @return the dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRepresentative> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history representatives
	 * @param end the upper bound of the range of dm history representatives (not inclusive)
	 * @return the range of dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRepresentative> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history representatives.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history representatives
	 * @param end the upper bound of the range of dm history representatives (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryRepresentative> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryRepresentative> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYREPRESENTATIVE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYREPRESENTATIVE.concat(DmHistoryRepresentativeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryRepresentative>) queryFactory.getResultList(builder);
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
	 * Removes the dm history representative where repCode = &#63; from the database.
	 *
	 * @param repCode the rep code
	 * @return the dm history representative that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative removeByRepCode(String repCode)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = findByRepCode(repCode);

		repository.delete(dmHistoryRepresentative);
			return dmHistoryRepresentative;
	}

	/**
	 * Removes the dm history representative where repCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param repCode the rep code
	 * @param syncVersion the sync version
	 * @return the dm history representative that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryRepresentative removeByRepCodeAndSyncVersion(
		String repCode, String syncVersion)
		throws NoSuchDmHistoryRepresentativeException, SystemException {
		DmHistoryRepresentative dmHistoryRepresentative = findByRepCodeAndSyncVersion(repCode,
				syncVersion);

		repository.delete(dmHistoryRepresentative);
			return dmHistoryRepresentative;
	}

	/**
	 * Removes all the dm history representatives from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryRepresentative dmHistoryRepresentative : findAll()) {
			repository.delete(dmHistoryRepresentative);
		}
	}

	/**
	 * Returns the number of dm history representatives where repCode = &#63;.
	 *
	 * @param repCode the rep code
	 * @return the number of matching dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRepCode(String repCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODE_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODE_REPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
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
	 * Returns the number of dm history representatives where repCode = &#63; and syncVersion = &#63;.
	 *
	 * @param repCode the rep code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRepCodeAndSyncVersion(String repCode, String syncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYREPRESENTATIVE_WHERE);

			if (repCode == null) {
				query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_1);
			}
			else {
				if (repCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (repCode != null) {
					builder.appendNamedParameterMap("repCode", repCode);
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
	 * Returns the number of dm history representatives.
	 *
	 * @return the number of dm history representatives
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYREPRESENTATIVE).build();

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
	 * Initializes the dm history representative persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYREPRESENTATIVE = "SELECT dmHistoryRepresentative FROM DmHistoryRepresentative dmHistoryRepresentative";
	private static final String _SQL_SELECT_DMHISTORYREPRESENTATIVE_WHERE = "SELECT dmHistoryRepresentative FROM DmHistoryRepresentative dmHistoryRepresentative WHERE ";
	private static final String _SQL_COUNT_DMHISTORYREPRESENTATIVE = "SELECT COUNT(dmHistoryRepresentative) FROM DmHistoryRepresentative dmHistoryRepresentative";
	private static final String _SQL_COUNT_DMHISTORYREPRESENTATIVE_WHERE = "SELECT COUNT(dmHistoryRepresentative) FROM DmHistoryRepresentative dmHistoryRepresentative WHERE ";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_1 = "dmHistoryRepresentative.repCode IS NULL";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_2 = "dmHistoryRepresentative.repCode =:repCode";
	private static final String _FINDER_COLUMN_REPCODE_REPCODE_3 = "(dmHistoryRepresentative.repCode IS NULL OR dmHistoryRepresentative.repCode =:repCode)";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_1 = "dmHistoryRepresentative.repCode IS NULL AND ";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_2 = "dmHistoryRepresentative.repCode =:repCode AND ";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_REPCODE_3 = "(dmHistoryRepresentative.repCode IS NULL OR dmHistoryRepresentative.repCode =:repCode) AND ";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryRepresentative.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryRepresentative.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_REPCODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryRepresentative.syncVersion IS NULL OR dmHistoryRepresentative.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryRepresentative.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryRepresentative exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryRepresentative exists with the key {";
	

	
}
