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
import com.fds.nsw.nghiepvu.model.VmaScheduleXline;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleXlineRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleXlineModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleXlinePersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleXlineRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleXline> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleXlineUtil} to access the vma schedule xline persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleXline create(long id) {
		VmaScheduleXline vmaScheduleXline = new VmaScheduleXline();

		
		//vmaScheduleXline.setPrimaryKey(id);

		return vmaScheduleXline;
	}

	/**
	 * Removes the vma schedule xline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule xline
	 * @return the vma schedule xline that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineException if a vma schedule xline with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXline remove(long id)
		throws NoSuchVmaScheduleXlineException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule xline with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule xline
	 * @return the vma schedule xline that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineException if a vma schedule xline with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleXline remove(Serializable primaryKey)
		throws NoSuchVmaScheduleXlineException, SystemException {
		

		try {
			

			VmaScheduleXline vmaScheduleXline = findByPrimaryKey(primaryKey);

			if (vmaScheduleXline == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleXlineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleXline);
			return vmaScheduleXline;
		}
		catch (NoSuchVmaScheduleXlineException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleXline remove(VmaScheduleXline VmaScheduleXline) throws SystemException {
	removeImpl(VmaScheduleXline);
	return VmaScheduleXline;
}

protected VmaScheduleXline removeImpl(VmaScheduleXline vmaScheduleXline)
		throws SystemException {
		try {
			repository.delete(vmaScheduleXline);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleXline;
	}

	
	public VmaScheduleXline updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleXline vmaScheduleXline,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleXline);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleXline;
	}

	
	public VmaScheduleXline findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule xline with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule xline
	 * @return the vma schedule xline
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleXlineException if a vma schedule xline with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXline findByPrimaryKey(long id)
		throws NoSuchVmaScheduleXlineException, SystemException {
		VmaScheduleXline vmaScheduleXline = fetchByPrimaryKey(id);

		if (vmaScheduleXline == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleXlineException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleXline;
	}

	/**
	 * Returns the vma schedule xline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule xline
	 * @return the vma schedule xline, or <code>null</code> if a vma schedule xline with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleXline fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule xline with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule xline
	 * @return the vma schedule xline, or <code>null</code> if a vma schedule xline with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleXline fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleXline vmaScheduleXline = null;

		

		if (vmaScheduleXline == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleXline> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleXline = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleXline;
	}

	/**
	 * Returns all the vma schedule xlines.
	 *
	 * @return the vma schedule xlines
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXline> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule xlines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule xlines
	 * @param end the upper bound of the range of vma schedule xlines (not inclusive)
	 * @return the range of vma schedule xlines
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXline> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule xlines.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule xlines
	 * @param end the upper bound of the range of vma schedule xlines (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule xlines
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleXline> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleXline> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULEXLINE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULEXLINE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleXline>) queryFactory.getResultList(builder);
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
	 * Removes all the vma schedule xlines from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleXline vmaScheduleXline : findAll()) {
			repository.delete(vmaScheduleXline);
		}
	}

	/**
	 * Returns the number of vma schedule xlines.
	 *
	 * @return the number of vma schedule xlines
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULEXLINE).build();

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
	 * Initializes the vma schedule xline persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULEXLINE = "SELECT vmaScheduleXline FROM VmaScheduleXline vmaScheduleXline";
	private static final String _SQL_COUNT_VMASCHEDULEXLINE = "SELECT COUNT(vmaScheduleXline) FROM VmaScheduleXline vmaScheduleXline";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleXline.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleXline exists with the primary key ";
	

	
}
