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
import com.fds.nsw.nghiepvu.model.DmGtVersion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtVersionRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtVersionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtVersionPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtVersionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtVersion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtVersionUtil} to access the dm gt version persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtVersion create(int id) {
		DmGtVersion dmGtVersion = new DmGtVersion();

		
		//dmGtVersion.setPrimaryKey(id);

		return dmGtVersion;
	}

	/**
	 * Removes the dm gt version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt version
	 * @return the dm gt version that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtVersionException if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion remove(int id)
		throws NoSuchDmGtVersionException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm gt version with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt version
	 * @return the dm gt version that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtVersionException if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtVersion remove(Serializable primaryKey)
		throws NoSuchDmGtVersionException, SystemException {
		

		try {
			

			DmGtVersion dmGtVersion = findByPrimaryKey(primaryKey);

			if (dmGtVersion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtVersion);
			return dmGtVersion;
		}
		catch (NoSuchDmGtVersionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtVersion remove(DmGtVersion DmGtVersion) throws SystemException {
	removeImpl(DmGtVersion);
	return DmGtVersion;
}

protected DmGtVersion removeImpl(DmGtVersion dmGtVersion)
		throws SystemException {
		try {
			repository.delete(dmGtVersion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtVersion;
	}

	
	public DmGtVersion updateImpl(
		DmGtVersion dmGtVersion, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmGtVersion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtVersion;
	}

	
	public DmGtVersion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm gt version with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtVersionException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt version
	 * @return the dm gt version
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtVersionException if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion findByPrimaryKey(int id)
		throws NoSuchDmGtVersionException, SystemException {
		DmGtVersion dmGtVersion = fetchByPrimaryKey(id);

		if (dmGtVersion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtVersionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtVersion;
	}

	/**
	 * Returns the dm gt version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt version
	 * @return the dm gt version, or <code>null</code> if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtVersion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm gt version with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt version
	 * @return the dm gt version, or <code>null</code> if a dm gt version with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtVersion fetchByPrimaryKey(int id) throws SystemException {
		DmGtVersion dmGtVersion = null;

		

		if (dmGtVersion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtVersion> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtVersion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtVersion;
	}

	/**
	 * Returns all the dm gt versions.
	 *
	 * @return the dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtVersion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt versions
	 * @param end the upper bound of the range of dm gt versions (not inclusive)
	 * @return the range of dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtVersion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt versions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt versions
	 * @param end the upper bound of the range of dm gt versions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtVersion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtVersion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTVERSION.concat(DmGtVersionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtVersion>) queryFactory.getResultList(builder);
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
	 * Removes all the dm gt versions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtVersion dmGtVersion : findAll()) {
			repository.delete(dmGtVersion);
		}
	}

	/**
	 * Returns the number of dm gt versions.
	 *
	 * @return the number of dm gt versions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTVERSION).build();

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
	 * Initializes the dm gt version persistence.
	 */
	private static final String _SQL_SELECT_DMGTVERSION = "SELECT dmGtVersion FROM DmGtVersion dmGtVersion";
	private static final String _SQL_COUNT_DMGTVERSION = "SELECT COUNT(dmGtVersion) FROM DmGtVersion dmGtVersion";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtVersion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtVersion exists with the primary key ";
	

	
}
