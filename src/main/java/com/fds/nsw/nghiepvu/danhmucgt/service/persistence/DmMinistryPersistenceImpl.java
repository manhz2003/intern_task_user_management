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
import com.fds.nsw.nghiepvu.model.DmMinistry;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmMinistryRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmMinistryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmMinistryPersistenceImpl extends BasePersistence {
	@Autowired
	DmMinistryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmMinistry> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmMinistryUtil} to access the dm ministry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmMinistry create(long id) {
		DmMinistry dmMinistry = new DmMinistry();

		
		//dmMinistry.setPrimaryKey(id);

		return dmMinistry;
	}

	/**
	 * Removes the dm ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm ministry
	 * @return the dm ministry that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmMinistryException if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry remove(long id)
		throws NoSuchDmMinistryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm ministry
	 * @return the dm ministry that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmMinistryException if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmMinistry remove(Serializable primaryKey)
		throws NoSuchDmMinistryException, SystemException {
		

		try {
			

			DmMinistry dmMinistry = findByPrimaryKey(primaryKey);

			if (dmMinistry == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmMinistry);
			return dmMinistry;
		}
		catch (NoSuchDmMinistryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmMinistry remove(DmMinistry DmMinistry) throws SystemException {
	removeImpl(DmMinistry);
	return DmMinistry;
}

protected DmMinistry removeImpl(DmMinistry dmMinistry)
		throws SystemException {
		try {
			repository.delete(dmMinistry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmMinistry;
	}

	
	public DmMinistry updateImpl(
		DmMinistry dmMinistry, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmMinistry);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmMinistry;
	}

	
	public DmMinistry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm ministry with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmMinistryException} if it could not be found.
	 *
	 * @param id the primary key of the dm ministry
	 * @return the dm ministry
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmMinistryException if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry findByPrimaryKey(long id)
		throws NoSuchDmMinistryException, SystemException {
		DmMinistry dmMinistry = fetchByPrimaryKey(id);

		if (dmMinistry == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmMinistry;
	}

	/**
	 * Returns the dm ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm ministry
	 * @return the dm ministry, or <code>null</code> if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmMinistry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm ministry
	 * @return the dm ministry, or <code>null</code> if a dm ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry fetchByPrimaryKey(long id) throws SystemException {
		DmMinistry dmMinistry = null;

		

		if (dmMinistry == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmMinistry> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmMinistry = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmMinistry;
	}

	/**
	 * Returns the dm ministry where ministryCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmMinistryException} if it could not be found.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching dm ministry
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmMinistryException if a matching dm ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry findByMinistryCode(String ministryCode)
		throws NoSuchDmMinistryException, SystemException {
		DmMinistry dmMinistry = fetchByMinistryCode(ministryCode);

		if (dmMinistry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ministryCode=");
			msg.append(ministryCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmMinistryException(msg.toString());
		}

		return dmMinistry;
	}

	/**
	 * Returns the dm ministry where ministryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching dm ministry, or <code>null</code> if a matching dm ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry fetchByMinistryCode(String ministryCode)
		throws SystemException {
		return fetchByMinistryCode(ministryCode, true);
	}

	/**
	 * Returns the dm ministry where ministryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ministryCode the ministry code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm ministry, or <code>null</code> if a matching dm ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry fetchByMinistryCode(String ministryCode,
		boolean retrieveFromCache) throws SystemException {
		DmMinistry dmMinistry = null;
		if (dmMinistry == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMMINISTRY_WHERE);

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

			query.append(DmMinistryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmMinistry.class).build();

				

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				dmMinistry = (DmMinistry) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmMinistry;
	}

	/**
	 * Returns all the dm ministries.
	 *
	 * @return the dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMinistry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ministries
	 * @param end the upper bound of the range of dm ministries (not inclusive)
	 * @return the range of dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMinistry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm ministries
	 * @param end the upper bound of the range of dm ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMinistry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmMinistry> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMMINISTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMMINISTRY.concat(DmMinistryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmMinistry>) queryFactory.getResultList(builder);
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
	 * Removes the dm ministry where ministryCode = &#63; from the database.
	 *
	 * @param ministryCode the ministry code
	 * @return the dm ministry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmMinistry removeByMinistryCode(String ministryCode)
		throws NoSuchDmMinistryException, SystemException {
		DmMinistry dmMinistry = findByMinistryCode(ministryCode);

		repository.delete(dmMinistry);
			return dmMinistry;
	}

	/**
	 * Removes all the dm ministries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmMinistry dmMinistry : findAll()) {
			repository.delete(dmMinistry);
		}
	}

	/**
	 * Returns the number of dm ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the number of matching dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMinistryCode(String ministryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMMINISTRY_WHERE);

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
	 * Returns the number of dm ministries.
	 *
	 * @return the number of dm ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMMINISTRY).build();

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
	 * Initializes the dm ministry persistence.
	 */
	private static final String _SQL_SELECT_DMMINISTRY = "SELECT dmMinistry FROM DmMinistry dmMinistry";
	private static final String _SQL_SELECT_DMMINISTRY_WHERE = "SELECT dmMinistry FROM DmMinistry dmMinistry WHERE ";
	private static final String _SQL_COUNT_DMMINISTRY = "SELECT COUNT(dmMinistry) FROM DmMinistry dmMinistry";
	private static final String _SQL_COUNT_DMMINISTRY_WHERE = "SELECT COUNT(dmMinistry) FROM DmMinistry dmMinistry WHERE ";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1 = "dmMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2 = "dmMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3 = "(dmMinistry.ministryCode IS NULL OR dmMinistry.ministryCode =:ministryCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmMinistry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmMinistry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmMinistry exists with the key {";
	

	
}
