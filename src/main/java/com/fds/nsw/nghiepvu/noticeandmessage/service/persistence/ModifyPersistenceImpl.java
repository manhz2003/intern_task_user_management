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

package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;

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
import com.fds.nsw.nghiepvu.model.Modify;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.GtModifyRepository;
import com.fds.nsw.nghiepvu.modelImpl.ModifyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ModifyPersistenceImpl extends BasePersistence {
	@Autowired
	GtModifyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<Modify> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ModifyUtil} to access the modify persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public Modify create(long id) {
		Modify modify = new Modify();

		
		//modify.setPrimaryKey(id);

		return modify;
	}

	/**
	 * Removes the modify with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the modify
	 * @return the modify that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchModifyException if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Modify remove(long id) throws NoSuchModifyException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the modify with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the modify
	 * @return the modify that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchModifyException if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public Modify remove(Serializable primaryKey)
		throws NoSuchModifyException, SystemException {
		

		try {
			

			Modify modify = findByPrimaryKey(primaryKey);

			if (modify == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchModifyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(modify);
			return modify;
		}
		catch (NoSuchModifyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public Modify remove(Modify Modify) throws SystemException {
	removeImpl(Modify);
	return Modify;
}

protected Modify removeImpl(Modify modify) throws SystemException {
		try {
			repository.delete(modify);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return modify;
	}

	
	public Modify updateImpl(com.fds.nsw.nghiepvu.model.Modify modify,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(modify);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return modify;
	}

	
	public Modify findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the modify with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchModifyException} if it could not be found.
	 *
	 * @param id the primary key of the modify
	 * @return the modify
	 * @throws vn.gt.dao.noticeandmessage.NoSuchModifyException if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Modify findByPrimaryKey(long id)
		throws NoSuchModifyException, SystemException {
		Modify modify = fetchByPrimaryKey(id);

		if (modify == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchModifyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return modify;
	}

	/**
	 * Returns the modify with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the modify
	 * @return the modify, or <code>null</code> if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public Modify fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the modify with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the modify
	 * @return the modify, or <code>null</code> if a modify with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public Modify fetchByPrimaryKey(long id) throws SystemException {
		Modify modify = null;

		

		if (modify == null) {
			

			boolean hasException = false;

			try {
				

				Optional<Modify> optional = repository.findById(id);
				if (optional.isPresent()) {
					modify = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return modify;
	}

	/**
	 * Returns all the modifies.
	 *
	 * @return the modifies
	 * @throws SystemException if a system exception occurred
	 */
	public List<Modify> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the modifies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of modifies
	 * @param end the upper bound of the range of modifies (not inclusive)
	 * @return the range of modifies
	 * @throws SystemException if a system exception occurred
	 */
	public List<Modify> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the modifies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of modifies
	 * @param end the upper bound of the range of modifies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of modifies
	 * @throws SystemException if a system exception occurred
	 */
	public List<Modify> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<Modify> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_MODIFY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_MODIFY.concat(ModifyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<Modify>) queryFactory.getResultList(builder);
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
	 * Removes all the modifies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (Modify modify : findAll()) {
			repository.delete(modify);
		}
	}

	/**
	 * Returns the number of modifies.
	 *
	 * @return the number of modifies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_MODIFY).build();

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
	 * Initializes the modify persistence.
	 */
	private static final String _SQL_SELECT_MODIFY = "SELECT modify FROM Modify modify";
	private static final String _SQL_COUNT_MODIFY = "SELECT COUNT(modify) FROM Modify modify";
	private static final String _ORDER_BY_ENTITY_ALIAS = "modify.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No Modify exists with the primary key ";
	

	
}
