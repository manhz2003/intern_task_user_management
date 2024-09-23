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
import com.fds.nsw.nghiepvu.model.DmHistoryTugboatCompany;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryTugboatCompanyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryTugboatCompanyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryTugboatCompanyPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryTugboatCompanyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryTugboatCompany> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryTugboatCompanyUtil} to access the dm history tugboat company persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryTugboatCompany create(long id) {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = new DmHistoryTugboatCompany();

		
		//dmHistoryTugboatCompany.setPrimaryKey(id);

		return dmHistoryTugboatCompany;
	}

	/**
	 * Removes the dm history tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm history tugboat company
	 * @return the dm history tugboat company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException if a dm history tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany remove(long Id)
		throws NoSuchDmHistoryTugboatCompanyException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm history tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history tugboat company
	 * @return the dm history tugboat company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException if a dm history tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryTugboatCompany remove(Serializable primaryKey)
		throws NoSuchDmHistoryTugboatCompanyException, SystemException {
		

		try {
			

			DmHistoryTugboatCompany dmHistoryTugboatCompany = findByPrimaryKey(primaryKey);

			if (dmHistoryTugboatCompany == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryTugboatCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryTugboatCompany);
			return dmHistoryTugboatCompany;
		}
		catch (NoSuchDmHistoryTugboatCompanyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryTugboatCompany remove(DmHistoryTugboatCompany DmHistoryTugboatCompany) throws SystemException {
	removeImpl(DmHistoryTugboatCompany);	return DmHistoryTugboatCompany;
}

protected DmHistoryTugboatCompany removeImpl

(
		DmHistoryTugboatCompany dmHistoryTugboatCompany)
		throws SystemException {
		try {
			repository.delete(dmHistoryTugboatCompany);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryTugboatCompany;
	}

	
	public DmHistoryTugboatCompany updateImpl(
		DmHistoryTugboatCompany dmHistoryTugboatCompany,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryTugboatCompany);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryTugboatCompany;
	}

	
	public DmHistoryTugboatCompany findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history tugboat company with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException} if it could not be found.
	 *
	 * @param Id the primary key of the dm history tugboat company
	 * @return the dm history tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException if a dm history tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany findByPrimaryKey(long Id)
		throws NoSuchDmHistoryTugboatCompanyException, SystemException {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = fetchByPrimaryKey(Id);

		if (dmHistoryTugboatCompany == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmHistoryTugboatCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmHistoryTugboatCompany;
	}

	/**
	 * Returns the dm history tugboat company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history tugboat company
	 * @return the dm history tugboat company, or <code>null</code> if a dm history tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryTugboatCompany fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history tugboat company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm history tugboat company
	 * @return the dm history tugboat company, or <code>null</code> if a dm history tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany fetchByPrimaryKey(long Id)
		throws SystemException {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = null;

		

		if (dmHistoryTugboatCompany == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryTugboatCompany> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmHistoryTugboatCompany = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryTugboatCompany;
	}

	/**
	 * Returns the dm history tugboat company where TugboatCompanyCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException} if it could not be found.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryTugboatCompanyException if a matching dm history tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany findByF_tugboatCompanyCode_syncVersion(
		String TugboatCompanyCode, String SyncVersion)
		throws NoSuchDmHistoryTugboatCompanyException, SystemException {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = fetchByF_tugboatCompanyCode_syncVersion(TugboatCompanyCode,
				SyncVersion);

		if (dmHistoryTugboatCompany == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TugboatCompanyCode=");
			msg.append(TugboatCompanyCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryTugboatCompanyException(msg.toString());
		}

		return dmHistoryTugboatCompany;
	}

	/**
	 * Returns the dm history tugboat company where TugboatCompanyCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param SyncVersion the sync version
	 * @return the matching dm history tugboat company, or <code>null</code> if a matching dm history tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany fetchByF_tugboatCompanyCode_syncVersion(
		String TugboatCompanyCode, String SyncVersion)
		throws SystemException {
		return fetchByF_tugboatCompanyCode_syncVersion(TugboatCompanyCode,
			SyncVersion, true);
	}

	/**
	 * Returns the dm history tugboat company where TugboatCompanyCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history tugboat company, or <code>null</code> if a matching dm history tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany fetchByF_tugboatCompanyCode_syncVersion(
		String TugboatCompanyCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = null;
		if (dmHistoryTugboatCompany == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYTUGBOATCOMPANY_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryTugboatCompanyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryTugboatCompany.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistoryTugboatCompany = (DmHistoryTugboatCompany) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryTugboatCompany;
	}

	/**
	 * Returns all the dm history tugboat companies.
	 *
	 * @return the dm history tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboatCompany> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history tugboat companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history tugboat companies
	 * @param end the upper bound of the range of dm history tugboat companies (not inclusive)
	 * @return the range of dm history tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboatCompany> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history tugboat companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history tugboat companies
	 * @param end the upper bound of the range of dm history tugboat companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryTugboatCompany> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryTugboatCompany> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYTUGBOATCOMPANY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYTUGBOATCOMPANY.concat(DmHistoryTugboatCompanyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryTugboatCompany>) queryFactory.getResultList(builder);
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
	 * Removes the dm history tugboat company where TugboatCompanyCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param SyncVersion the sync version
	 * @return the dm history tugboat company that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryTugboatCompany removeByF_tugboatCompanyCode_syncVersion(
		String TugboatCompanyCode, String SyncVersion)
		throws NoSuchDmHistoryTugboatCompanyException, SystemException {
		DmHistoryTugboatCompany dmHistoryTugboatCompany = findByF_tugboatCompanyCode_syncVersion(TugboatCompanyCode,
				SyncVersion);

		repository.delete(dmHistoryTugboatCompany);
			return dmHistoryTugboatCompany;
	}

	/**
	 * Removes all the dm history tugboat companies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryTugboatCompany dmHistoryTugboatCompany : findAll()) {
			repository.delete(dmHistoryTugboatCompany);
		}
	}

	/**
	 * Returns the number of dm history tugboat companies where TugboatCompanyCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_tugboatCompanyCode_syncVersion(
		String TugboatCompanyCode, String SyncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYTUGBOATCOMPANY_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of dm history tugboat companies.
	 *
	 * @return the number of dm history tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYTUGBOATCOMPANY).build();

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
	 * Initializes the dm history tugboat company persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYTUGBOATCOMPANY = "SELECT dmHistoryTugboatCompany FROM DmHistoryTugboatCompany dmHistoryTugboatCompany";
	private static final String _SQL_SELECT_DMHISTORYTUGBOATCOMPANY_WHERE = "SELECT dmHistoryTugboatCompany FROM DmHistoryTugboatCompany dmHistoryTugboatCompany WHERE ";
	private static final String _SQL_COUNT_DMHISTORYTUGBOATCOMPANY = "SELECT COUNT(dmHistoryTugboatCompany) FROM DmHistoryTugboatCompany dmHistoryTugboatCompany";
	private static final String _SQL_COUNT_DMHISTORYTUGBOATCOMPANY_WHERE = "SELECT COUNT(dmHistoryTugboatCompany) FROM DmHistoryTugboatCompany dmHistoryTugboatCompany WHERE ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_1 =
		"dmHistoryTugboatCompany.TugboatCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_2 =
		"dmHistoryTugboatCompany.TugboatCompanyCode =:TugboatCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_TUGBOATCOMPANYCODE_3 =
		"(dmHistoryTugboatCompany.TugboatCompanyCode IS NULL OR dmHistoryTugboatCompany.TugboatCompanyCode =:TugboatCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistoryTugboatCompany.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistoryTugboatCompany.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryTugboatCompany.SyncVersion IS NULL OR dmHistoryTugboatCompany.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryTugboatCompany.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryTugboatCompany exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryTugboatCompany exists with the key {";
	

	
}
