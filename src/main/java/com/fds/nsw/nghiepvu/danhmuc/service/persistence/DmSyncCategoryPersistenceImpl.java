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
import com.fds.nsw.nghiepvu.model.DmSyncCategory;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmSyncCategoryRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmSyncCategoryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmSyncCategoryPersistenceImpl extends BasePersistence {
	@Autowired
	DmSyncCategoryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmSyncCategory> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmSyncCategoryUtil} to access the dm sync category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmSyncCategory create(long id) {
		DmSyncCategory dmSyncCategory = new DmSyncCategory();

		
		//dmSyncCategory.setPrimaryKey(id);

		return dmSyncCategory;
	}

	/**
	 * Removes the dm sync category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm sync category
	 * @return the dm sync category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSyncCategoryException if a dm sync category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSyncCategory remove(long id)
		throws NoSuchDmSyncCategoryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm sync category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm sync category
	 * @return the dm sync category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSyncCategoryException if a dm sync category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmSyncCategory remove(Serializable primaryKey)
		throws NoSuchDmSyncCategoryException, SystemException {
		

		try {
			

			DmSyncCategory dmSyncCategory = findByPrimaryKey(primaryKey);

			if (dmSyncCategory == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmSyncCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmSyncCategory);
			return dmSyncCategory;
		}
		catch (NoSuchDmSyncCategoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmSyncCategory remove(DmSyncCategory DmSyncCategory) throws SystemException {
	removeImpl(DmSyncCategory);	return DmSyncCategory;
}

protected DmSyncCategory removeImpl

(DmSyncCategory dmSyncCategory)
		throws SystemException {
		try {
			repository.delete(dmSyncCategory);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmSyncCategory;
	}

	
	public DmSyncCategory updateImpl(
		DmSyncCategory dmSyncCategory, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmSyncCategory);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmSyncCategory;
	}

	
	public DmSyncCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm sync category with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmSyncCategoryException} if it could not be found.
	 *
	 * @param id the primary key of the dm sync category
	 * @return the dm sync category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmSyncCategoryException if a dm sync category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSyncCategory findByPrimaryKey(long id)
		throws NoSuchDmSyncCategoryException, SystemException {
		DmSyncCategory dmSyncCategory = fetchByPrimaryKey(id);

		if (dmSyncCategory == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmSyncCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmSyncCategory;
	}

	/**
	 * Returns the dm sync category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm sync category
	 * @return the dm sync category, or <code>null</code> if a dm sync category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmSyncCategory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm sync category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm sync category
	 * @return the dm sync category, or <code>null</code> if a dm sync category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmSyncCategory fetchByPrimaryKey(long id) throws SystemException {
		DmSyncCategory dmSyncCategory = null;

		

		if (dmSyncCategory == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmSyncCategory> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmSyncCategory = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmSyncCategory;
	}

	/**
	 * Returns all the dm sync categories.
	 *
	 * @return the dm sync categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSyncCategory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm sync categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm sync categories
	 * @param end the upper bound of the range of dm sync categories (not inclusive)
	 * @return the range of dm sync categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSyncCategory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm sync categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm sync categories
	 * @param end the upper bound of the range of dm sync categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm sync categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmSyncCategory> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmSyncCategory> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMSYNCCATEGORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMSYNCCATEGORY.concat(DmSyncCategoryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmSyncCategory>) queryFactory.getResultList(builder);
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
	 * Removes all the dm sync categories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmSyncCategory dmSyncCategory : findAll()) {
			repository.delete(dmSyncCategory);
		}
	}

	/**
	 * Returns the number of dm sync categories.
	 *
	 * @return the number of dm sync categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMSYNCCATEGORY).build();

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
	 * Initializes the dm sync category persistence.
	 */
	private static final String _SQL_SELECT_DMSYNCCATEGORY = "SELECT dmSyncCategory FROM DmSyncCategory dmSyncCategory";
	private static final String _SQL_COUNT_DMSYNCCATEGORY = "SELECT COUNT(dmSyncCategory) FROM DmSyncCategory dmSyncCategory";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmSyncCategory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmSyncCategory exists with the primary key ";
	

	
}
