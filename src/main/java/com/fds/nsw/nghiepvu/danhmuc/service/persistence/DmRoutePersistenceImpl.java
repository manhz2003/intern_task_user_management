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
import com.fds.nsw.nghiepvu.model.DmRoute;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmRouteRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmRouteModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmRoutePersistenceImpl extends BasePersistence {
	@Autowired
	DmRouteRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmRoute> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmRouteUtil} to access the dm route persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmRoute create(int id) {
		DmRoute dmRoute = new DmRoute();

		
		//dmRoute.setPrimaryKey(id);

		return dmRoute;
	}

	/**
	 * Removes the dm route with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm route
	 * @return the dm route that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRouteException if a dm route with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRoute remove(int id)
		throws NoSuchDmRouteException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm route with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm route
	 * @return the dm route that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRouteException if a dm route with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRoute remove(Serializable primaryKey)
		throws NoSuchDmRouteException, SystemException {
		

		try {
			

			DmRoute dmRoute = findByPrimaryKey(primaryKey);

			if (dmRoute == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmRouteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmRoute);
			return dmRoute;
		}
		catch (NoSuchDmRouteException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmRoute remove(DmRoute DmRoute) throws SystemException {
	removeImpl(DmRoute);	return DmRoute;
}

protected DmRoute removeImpl

(DmRoute dmRoute) throws SystemException {
		try {
			repository.delete(dmRoute);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRoute;
	}

	
	public DmRoute updateImpl(DmRoute dmRoute,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmRoute);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmRoute;
	}

	
	public DmRoute findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm route with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmRouteException} if it could not be found.
	 *
	 * @param id the primary key of the dm route
	 * @return the dm route
	 * @throws vn.gt.dao.danhmuc.NoSuchDmRouteException if a dm route with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRoute findByPrimaryKey(int id)
		throws NoSuchDmRouteException, SystemException {
		DmRoute dmRoute = fetchByPrimaryKey(id);

		if (dmRoute == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmRouteException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmRoute;
	}

	/**
	 * Returns the dm route with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm route
	 * @return the dm route, or <code>null</code> if a dm route with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmRoute fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm route with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm route
	 * @return the dm route, or <code>null</code> if a dm route with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmRoute fetchByPrimaryKey(int id) throws SystemException {
		DmRoute dmRoute = null;

		

		if (dmRoute == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmRoute> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmRoute = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmRoute;
	}

	/**
	 * Returns all the dm routes.
	 *
	 * @return the dm routes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRoute> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm routes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm routes
	 * @param end the upper bound of the range of dm routes (not inclusive)
	 * @return the range of dm routes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRoute> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm routes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm routes
	 * @param end the upper bound of the range of dm routes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm routes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmRoute> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmRoute> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMROUTE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMROUTE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmRoute>) queryFactory.getResultList(builder);
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
	 * Removes all the dm routes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmRoute dmRoute : findAll()) {
			repository.delete(dmRoute);
		}
	}

	/**
	 * Returns the number of dm routes.
	 *
	 * @return the number of dm routes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMROUTE).build();

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
	 * Initializes the dm route persistence.
	 */
	private static final String _SQL_SELECT_DMROUTE = "SELECT dmRoute FROM DmRoute dmRoute";
	private static final String _SQL_COUNT_DMROUTE = "SELECT COUNT(dmRoute) FROM DmRoute dmRoute";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmRoute.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmRoute exists with the primary key ";
	

	
}
