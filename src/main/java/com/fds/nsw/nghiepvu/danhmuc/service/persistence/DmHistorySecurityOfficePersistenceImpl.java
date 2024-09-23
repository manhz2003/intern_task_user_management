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
import com.fds.nsw.nghiepvu.model.DmHistorySecurityOffice;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistorySecurityOfficeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistorySecurityOfficeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistorySecurityOfficePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistorySecurityOfficeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistorySecurityOffice> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistorySecurityOfficeUtil} to access the dm history security office persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistorySecurityOffice create(long id) {
		DmHistorySecurityOffice dmHistorySecurityOffice = new DmHistorySecurityOffice();

		
		//dmHistorySecurityOffice.setPrimaryKey(id);

		return dmHistorySecurityOffice;
	}

	/**
	 * Removes the dm history security office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history security office
	 * @return the dm history security office that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException if a dm history security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice remove(long id)
		throws NoSuchDmHistorySecurityOfficeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm history security office with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history security office
	 * @return the dm history security office that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException if a dm history security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistorySecurityOffice remove(Serializable primaryKey)
		throws NoSuchDmHistorySecurityOfficeException, SystemException {
		

		try {
			

			DmHistorySecurityOffice dmHistorySecurityOffice = findByPrimaryKey(primaryKey);

			if (dmHistorySecurityOffice == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistorySecurityOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistorySecurityOffice);
			return dmHistorySecurityOffice;
		}
		catch (NoSuchDmHistorySecurityOfficeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistorySecurityOffice remove(DmHistorySecurityOffice DmHistorySecurityOffice) throws SystemException {
	removeImpl(DmHistorySecurityOffice);	return DmHistorySecurityOffice;
}

protected DmHistorySecurityOffice removeImpl

(
		DmHistorySecurityOffice dmHistorySecurityOffice)
		throws SystemException {
		try {
			repository.delete(dmHistorySecurityOffice);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistorySecurityOffice;
	}

	
	public DmHistorySecurityOffice updateImpl(
		DmHistorySecurityOffice dmHistorySecurityOffice,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistorySecurityOffice);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistorySecurityOffice;
	}

	
	public DmHistorySecurityOffice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history security office with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history security office
	 * @return the dm history security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException if a dm history security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice findByPrimaryKey(long id)
		throws NoSuchDmHistorySecurityOfficeException, SystemException {
		DmHistorySecurityOffice dmHistorySecurityOffice = fetchByPrimaryKey(id);

		if (dmHistorySecurityOffice == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistorySecurityOfficeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistorySecurityOffice;
	}

	/**
	 * Returns the dm history security office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history security office
	 * @return the dm history security office, or <code>null</code> if a dm history security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistorySecurityOffice fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history security office with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history security office
	 * @return the dm history security office, or <code>null</code> if a dm history security office with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice fetchByPrimaryKey(long id)
		throws SystemException {
		DmHistorySecurityOffice dmHistorySecurityOffice = null;

		

		if (dmHistorySecurityOffice == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistorySecurityOffice> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistorySecurityOffice = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistorySecurityOffice;
	}

	/**
	 * Returns the dm history security office where SecurityOfficeCode = &#63; and SyncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException} if it could not be found.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param SyncVersion the sync version
	 * @return the matching dm history security office
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistorySecurityOfficeException if a matching dm history security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice findByF_securityOfficeCode_syncVersion(
		String SecurityOfficeCode, String SyncVersion)
		throws NoSuchDmHistorySecurityOfficeException, SystemException {
		DmHistorySecurityOffice dmHistorySecurityOffice = fetchByF_securityOfficeCode_syncVersion(SecurityOfficeCode,
				SyncVersion);

		if (dmHistorySecurityOffice == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("SecurityOfficeCode=");
			msg.append(SecurityOfficeCode);

			msg.append(", SyncVersion=");
			msg.append(SyncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistorySecurityOfficeException(msg.toString());
		}

		return dmHistorySecurityOffice;
	}

	/**
	 * Returns the dm history security office where SecurityOfficeCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param SyncVersion the sync version
	 * @return the matching dm history security office, or <code>null</code> if a matching dm history security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice fetchByF_securityOfficeCode_syncVersion(
		String SecurityOfficeCode, String SyncVersion)
		throws SystemException {
		return fetchByF_securityOfficeCode_syncVersion(SecurityOfficeCode,
			SyncVersion, true);
	}

	/**
	 * Returns the dm history security office where SecurityOfficeCode = &#63; and SyncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param SyncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history security office, or <code>null</code> if a matching dm history security office could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice fetchByF_securityOfficeCode_syncVersion(
		String SecurityOfficeCode, String SyncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistorySecurityOffice dmHistorySecurityOffice = null;
		if (dmHistorySecurityOffice == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYSECURITYOFFICE_WHERE);

			if (SecurityOfficeCode == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_1);
			}
			else {
				if (SecurityOfficeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistorySecurityOfficeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistorySecurityOffice.class).build();

				

				if (SecurityOfficeCode != null) {
					builder.appendNamedParameterMap("SecurityOfficeCode", SecurityOfficeCode);
				}

				if (SyncVersion != null) {
					builder.appendNamedParameterMap("SyncVersion", SyncVersion);
				}

				dmHistorySecurityOffice = (DmHistorySecurityOffice) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistorySecurityOffice;
	}

	/**
	 * Returns all the dm history security offices.
	 *
	 * @return the dm history security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityOffice> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history security offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history security offices
	 * @param end the upper bound of the range of dm history security offices (not inclusive)
	 * @return the range of dm history security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityOffice> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history security offices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history security offices
	 * @param end the upper bound of the range of dm history security offices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history security offices
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistorySecurityOffice> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistorySecurityOffice> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYSECURITYOFFICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYSECURITYOFFICE.concat(DmHistorySecurityOfficeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistorySecurityOffice>) queryFactory.getResultList(builder);
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
	 * Removes the dm history security office where SecurityOfficeCode = &#63; and SyncVersion = &#63; from the database.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param SyncVersion the sync version
	 * @return the dm history security office that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistorySecurityOffice removeByF_securityOfficeCode_syncVersion(
		String SecurityOfficeCode, String SyncVersion)
		throws NoSuchDmHistorySecurityOfficeException, SystemException {
		DmHistorySecurityOffice dmHistorySecurityOffice = findByF_securityOfficeCode_syncVersion(SecurityOfficeCode,
				SyncVersion);

		repository.delete(dmHistorySecurityOffice);
			return dmHistorySecurityOffice;
	}

	/**
	 * Removes all the dm history security offices from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistorySecurityOffice dmHistorySecurityOffice : findAll()) {
			repository.delete(dmHistorySecurityOffice);
		}
	}

	/**
	 * Returns the number of dm history security offices where SecurityOfficeCode = &#63; and SyncVersion = &#63;.
	 *
	 * @param SecurityOfficeCode the security office code
	 * @param SyncVersion the sync version
	 * @return the number of matching dm history security offices
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_securityOfficeCode_syncVersion(
		String SecurityOfficeCode, String SyncVersion)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYSECURITYOFFICE_WHERE);

			if (SecurityOfficeCode == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_1);
			}
			else {
				if (SecurityOfficeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_2);
				}
			}

			if (SyncVersion == null) {
				query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (SyncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (SecurityOfficeCode != null) {
					builder.appendNamedParameterMap("SecurityOfficeCode", SecurityOfficeCode);
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
	 * Returns the number of dm history security offices.
	 *
	 * @return the number of dm history security offices
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYSECURITYOFFICE).build();

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
	 * Initializes the dm history security office persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYSECURITYOFFICE = "SELECT dmHistorySecurityOffice FROM DmHistorySecurityOffice dmHistorySecurityOffice";
	private static final String _SQL_SELECT_DMHISTORYSECURITYOFFICE_WHERE = "SELECT dmHistorySecurityOffice FROM DmHistorySecurityOffice dmHistorySecurityOffice WHERE ";
	private static final String _SQL_COUNT_DMHISTORYSECURITYOFFICE = "SELECT COUNT(dmHistorySecurityOffice) FROM DmHistorySecurityOffice dmHistorySecurityOffice";
	private static final String _SQL_COUNT_DMHISTORYSECURITYOFFICE_WHERE = "SELECT COUNT(dmHistorySecurityOffice) FROM DmHistorySecurityOffice dmHistorySecurityOffice WHERE ";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_1 =
		"dmHistorySecurityOffice.SecurityOfficeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_2 =
		"dmHistorySecurityOffice.SecurityOfficeCode =:SecurityOfficeCode AND ";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SECURITYOFFICECODE_3 =
		"(dmHistorySecurityOffice.SecurityOfficeCode IS NULL OR dmHistorySecurityOffice.SecurityOfficeCode =:SecurityOfficeCode) AND ";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_1 =
		"dmHistorySecurityOffice.SyncVersion IS NULL";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_2 =
		"dmHistorySecurityOffice.SyncVersion =:SyncVersion";
	private static final String _FINDER_COLUMN_F_SECURITYOFFICECODE_SYNCVERSION_SYNCVERSION_3 =
		"(dmHistorySecurityOffice.SyncVersion IS NULL OR dmHistorySecurityOffice.SyncVersion =:SyncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistorySecurityOffice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistorySecurityOffice exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistorySecurityOffice exists with the key {";
	

	
}
