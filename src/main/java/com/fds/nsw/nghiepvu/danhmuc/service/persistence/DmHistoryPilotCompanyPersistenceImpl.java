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
import com.fds.nsw.nghiepvu.model.DmHistoryPilotCompany;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryPilotCompanyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryPilotCompanyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryPilotCompanyPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryPilotCompanyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryPilotCompany> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryPilotCompanyUtil} to access the dm history pilot company persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryPilotCompany create(long id) {
		DmHistoryPilotCompany dmHistoryPilotCompany = new DmHistoryPilotCompany();

		
		//dmHistoryPilotCompany.setPrimaryKey(id);

		return dmHistoryPilotCompany;
	}

	/**
	 * Removes the dm history pilot company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm history pilot company
	 * @return the dm history pilot company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException if a dm history pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany remove(long Id)
		throws NoSuchDmHistoryPilotCompanyException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm history pilot company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history pilot company
	 * @return the dm history pilot company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException if a dm history pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPilotCompany remove(Serializable primaryKey)
		throws NoSuchDmHistoryPilotCompanyException, SystemException {
		

		try {
			

			DmHistoryPilotCompany dmHistoryPilotCompany = findByPrimaryKey(primaryKey);

			if (dmHistoryPilotCompany == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryPilotCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryPilotCompany);
			return dmHistoryPilotCompany;
		}
		catch (NoSuchDmHistoryPilotCompanyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryPilotCompany remove(DmHistoryPilotCompany DmHistoryPilotCompany) throws SystemException {
	removeImpl(DmHistoryPilotCompany);	return DmHistoryPilotCompany;
}

protected DmHistoryPilotCompany removeImpl

(
		DmHistoryPilotCompany dmHistoryPilotCompany) throws SystemException {
		try {
			repository.delete(dmHistoryPilotCompany);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPilotCompany;
	}

	
	public DmHistoryPilotCompany updateImpl(
		DmHistoryPilotCompany dmHistoryPilotCompany,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryPilotCompany);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryPilotCompany;
	}

	
	public DmHistoryPilotCompany findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history pilot company with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException} if it could not be found.
	 *
	 * @param Id the primary key of the dm history pilot company
	 * @return the dm history pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException if a dm history pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany findByPrimaryKey(long Id)
		throws NoSuchDmHistoryPilotCompanyException, SystemException {
		DmHistoryPilotCompany dmHistoryPilotCompany = fetchByPrimaryKey(Id);

		if (dmHistoryPilotCompany == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmHistoryPilotCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmHistoryPilotCompany;
	}

	/**
	 * Returns the dm history pilot company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history pilot company
	 * @return the dm history pilot company, or <code>null</code> if a dm history pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryPilotCompany fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history pilot company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm history pilot company
	 * @return the dm history pilot company, or <code>null</code> if a dm history pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany fetchByPrimaryKey(long Id)
		throws SystemException {
		DmHistoryPilotCompany dmHistoryPilotCompany = null;

		

		if (dmHistoryPilotCompany == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryPilotCompany> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmHistoryPilotCompany = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryPilotCompany;
	}

	/**
	 * Returns the dm history pilot company where PilotCompanyCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException} if it could not be found.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryPilotCompanyException if a matching dm history pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany findByF_pilotCompanyCode_syncVersion(
		String PilotCompanyCode, String SyncVersion)
		throws NoSuchDmHistoryPilotCompanyException, SystemException {
		DmHistoryPilotCompany dmHistoryPilotCompany = fetchByF_pilotCompanyCode_syncVersion(PilotCompanyCode,
				SyncVersion);

		if (dmHistoryPilotCompany == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCompanyCode=");
			msg.append(PilotCompanyCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryPilotCompanyException(msg.toString());
		}

		return dmHistoryPilotCompany;
	}

	/**
	 * Returns the dm history pilot company where PilotCompanyCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param SyncVersion the sync version
	 * @return the matching dm history pilot company, or <code>null</code> if a matching dm history pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany fetchByF_pilotCompanyCode_syncVersion(
		String PilotCompanyCode, String SyncVersion) throws SystemException {
		return fetchByF_pilotCompanyCode_syncVersion(PilotCompanyCode,
			SyncVersion, true);
	}

	/**
	 * Returns the dm history pilot company where PilotCompanyCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history pilot company, or <code>null</code> if a matching dm history pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany fetchByF_pilotCompanyCode_syncVersion(
		String PilotCompanyCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryPilotCompany dmHistoryPilotCompany = null;
		if (dmHistoryPilotCompany == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYPILOTCOMPANY_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryPilotCompanyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryPilotCompany.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryPilotCompany = (DmHistoryPilotCompany) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryPilotCompany;
	}

	/**
	 * Returns all the dm history pilot companies.
	 *
	 * @return the dm history pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilotCompany> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history pilot companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history pilot companies
	 * @param end the upper bound of the range of dm history pilot companies (not inclusive)
	 * @return the range of dm history pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilotCompany> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history pilot companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history pilot companies
	 * @param end the upper bound of the range of dm history pilot companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryPilotCompany> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryPilotCompany> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYPILOTCOMPANY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYPILOTCOMPANY.concat(DmHistoryPilotCompanyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryPilotCompany>) queryFactory.getResultList(builder);
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
	 * Removes the dm history pilot company where PilotCompanyCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param SyncVersion the sync version
	 * @return the dm history pilot company that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryPilotCompany removeByF_pilotCompanyCode_syncVersion(
		String PilotCompanyCode, String SyncVersion)
		throws NoSuchDmHistoryPilotCompanyException, SystemException {
		DmHistoryPilotCompany dmHistoryPilotCompany = findByF_pilotCompanyCode_syncVersion(PilotCompanyCode,
				SyncVersion);

		repository.delete(dmHistoryPilotCompany);
			return dmHistoryPilotCompany;
	}

	/**
	 * Removes all the dm history pilot companies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryPilotCompany dmHistoryPilotCompany : findAll()) {
			repository.delete(dmHistoryPilotCompany);
		}
	}

	/**
	 * Returns the number of dm history pilot companies where PilotCompanyCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCompanyCode_syncVersion(String PilotCompanyCode,
		String SyncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYPILOTCOMPANY_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
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
	 * Returns the number of dm history pilot companies.
	 *
	 * @return the number of dm history pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYPILOTCOMPANY).build();

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
	 * Initializes the dm history pilot company persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYPILOTCOMPANY = "SELECT dmHistoryPilotCompany FROM DmHistoryPilotCompany dmHistoryPilotCompany";
	private static final String _SQL_SELECT_DMHISTORYPILOTCOMPANY_WHERE = "SELECT dmHistoryPilotCompany FROM DmHistoryPilotCompany dmHistoryPilotCompany WHERE ";
	private static final String _SQL_COUNT_DMHISTORYPILOTCOMPANY = "SELECT COUNT(dmHistoryPilotCompany) FROM DmHistoryPilotCompany dmHistoryPilotCompany";
	private static final String _SQL_COUNT_DMHISTORYPILOTCOMPANY_WHERE = "SELECT COUNT(dmHistoryPilotCompany) FROM DmHistoryPilotCompany dmHistoryPilotCompany WHERE ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_1 =
		"dmHistoryPilotCompany.PilotCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_2 =
		"dmHistoryPilotCompany.PilotCompanyCode =:PilotCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_PILOTCOMPANYCODE_3 =
		"(dmHistoryPilotCompany.PilotCompanyCode IS NULL OR dmHistoryPilotCompany.PilotCompanyCode =:PilotCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryPilotCompany.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryPilotCompany.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryPilotCompany.SyncVersion IS NULL OR dmHistoryPilotCompany.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryPilotCompany.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryPilotCompany exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryPilotCompany exists with the key {";
	

	
}
