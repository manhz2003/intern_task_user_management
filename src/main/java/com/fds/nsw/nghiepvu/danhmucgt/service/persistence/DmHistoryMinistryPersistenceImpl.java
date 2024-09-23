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

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmHistoryMinistry;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryMinistryRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryMinistryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryMinistryPersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryMinistryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryMinistry> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryMinistryUtil} to access the dm history ministry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryMinistry create(long id) {
		DmHistoryMinistry dmHistoryMinistry = new DmHistoryMinistry();

		
		//dmHistoryMinistry.setPrimaryKey(id);

		return dmHistoryMinistry;
	}

	/**
	 * Removes the dm history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history ministry
	 * @return the dm history ministry that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException if a dm history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry remove(long id)
		throws NoSuchDmHistoryMinistryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history ministry
	 * @return the dm history ministry that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException if a dm history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryMinistry remove(Serializable primaryKey)
		throws NoSuchDmHistoryMinistryException, SystemException {
		

		try {
			

			DmHistoryMinistry dmHistoryMinistry = findByPrimaryKey(primaryKey);

			if (dmHistoryMinistry == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryMinistry);
			return dmHistoryMinistry;
		}
		catch (NoSuchDmHistoryMinistryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryMinistry remove(DmHistoryMinistry DmHistoryMinistry) throws SystemException {
	removeImpl(DmHistoryMinistry);
	return DmHistoryMinistry;
}

protected DmHistoryMinistry removeImpl(DmHistoryMinistry dmHistoryMinistry)
		throws SystemException {
		try {
			repository.delete(dmHistoryMinistry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryMinistry;
	}

	
	public DmHistoryMinistry updateImpl(
		DmHistoryMinistry dmHistoryMinistry,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryMinistry);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryMinistry;
	}

	
	public DmHistoryMinistry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history ministry with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException} if it could not be found.
	 *
	 * @param id the primary key of the dm history ministry
	 * @return the dm history ministry
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException if a dm history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry findByPrimaryKey(long id)
		throws NoSuchDmHistoryMinistryException, SystemException {
		DmHistoryMinistry dmHistoryMinistry = fetchByPrimaryKey(id);

		if (dmHistoryMinistry == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryMinistry;
	}

	/**
	 * Returns the dm history ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history ministry
	 * @return the dm history ministry, or <code>null</code> if a dm history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryMinistry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm history ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history ministry
	 * @return the dm history ministry, or <code>null</code> if a dm history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry fetchByPrimaryKey(long id)
		throws SystemException {
		DmHistoryMinistry dmHistoryMinistry = null;

		

		if (dmHistoryMinistry == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryMinistry> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryMinistry = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryMinistry;
	}

	/**
	 * Returns the dm history ministry where ministryCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException} if it could not be found.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching dm history ministry
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmHistoryMinistryException if a matching dm history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry findByMinistryCode(String ministryCode)
		throws NoSuchDmHistoryMinistryException, SystemException {
		DmHistoryMinistry dmHistoryMinistry = fetchByMinistryCode(ministryCode);

		if (dmHistoryMinistry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ministryCode=");
			msg.append(ministryCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryMinistryException(msg.toString());
		}

		return dmHistoryMinistry;
	}

	/**
	 * Returns the dm history ministry where ministryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching dm history ministry, or <code>null</code> if a matching dm history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry fetchByMinistryCode(String ministryCode)
		throws SystemException {
		return fetchByMinistryCode(ministryCode, true);
	}

	/**
	 * Returns the dm history ministry where ministryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ministryCode the ministry code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history ministry, or <code>null</code> if a matching dm history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry fetchByMinistryCode(String ministryCode,
		boolean retrieveFromCache) throws SystemException {
		DmHistoryMinistry dmHistoryMinistry = null;
		if (dmHistoryMinistry == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMHISTORYMINISTRY_WHERE);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2);
				}
			}

			query.append(DmHistoryMinistryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryMinistry.class).build();

				

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				dmHistoryMinistry = (DmHistoryMinistry) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryMinistry;
	}

	/**
	 * Returns all the dm history ministries.
	 *
	 * @return the dm history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMinistry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ministries
	 * @param end the upper bound of the range of dm history ministries (not inclusive)
	 * @return the range of dm history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMinistry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history ministries
	 * @param end the upper bound of the range of dm history ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryMinistry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryMinistry> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYMINISTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYMINISTRY.concat(DmHistoryMinistryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryMinistry>) queryFactory.getResultList(builder);
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
	 * Removes the dm history ministry where ministryCode = &#63; from the database.
	 *
	 * @param ministryCode the ministry code
	 * @return the dm history ministry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryMinistry removeByMinistryCode(String ministryCode)
		throws NoSuchDmHistoryMinistryException, SystemException {
		DmHistoryMinistry dmHistoryMinistry = findByMinistryCode(ministryCode);

		repository.delete(dmHistoryMinistry);
			return dmHistoryMinistry;
	}

	/**
	 * Removes all the dm history ministries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryMinistry dmHistoryMinistry : findAll()) {
			repository.delete(dmHistoryMinistry);
		}
	}

	/**
	 * Returns the number of dm history ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the number of matching dm history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMinistryCode(String ministryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYMINISTRY_WHERE);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
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
	 * Returns the number of dm history ministries.
	 *
	 * @return the number of dm history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYMINISTRY).build();

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
	 * Initializes the dm history ministry persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYMINISTRY = "SELECT dmHistoryMinistry FROM DmHistoryMinistry dmHistoryMinistry";
	private static final String _SQL_SELECT_DMHISTORYMINISTRY_WHERE = "SELECT dmHistoryMinistry FROM DmHistoryMinistry dmHistoryMinistry WHERE ";
	private static final String _SQL_COUNT_DMHISTORYMINISTRY = "SELECT COUNT(dmHistoryMinistry) FROM DmHistoryMinistry dmHistoryMinistry";
	private static final String _SQL_COUNT_DMHISTORYMINISTRY_WHERE = "SELECT COUNT(dmHistoryMinistry) FROM DmHistoryMinistry dmHistoryMinistry WHERE ";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1 = "dmHistoryMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2 = "dmHistoryMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3 = "(dmHistoryMinistry.ministryCode IS NULL OR dmHistoryMinistry.ministryCode =:ministryCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryMinistry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryMinistry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryMinistry exists with the key {";
	

	
}
