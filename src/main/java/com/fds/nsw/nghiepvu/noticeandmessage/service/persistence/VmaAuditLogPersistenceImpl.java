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
import com.fds.nsw.nghiepvu.model.VmaAuditLog;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaAuditLogRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaAuditLogModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaAuditLogPersistenceImpl extends BasePersistence {
	@Autowired
	VmaAuditLogRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaAuditLog> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaAuditLogUtil} to access the vma audit log persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaAuditLog create(long id) {
		VmaAuditLog vmaAuditLog = new VmaAuditLog();

		
		//vmaAuditLog.setPrimaryKey(id);

		return vmaAuditLog;
	}

	/**
	 * Removes the vma audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma audit log
	 * @return the vma audit log that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAuditLogException if a vma audit log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAuditLog remove(long id)
		throws NoSuchVmaAuditLogException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma audit log with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma audit log
	 * @return the vma audit log that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAuditLogException if a vma audit log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAuditLog remove(Serializable primaryKey)
		throws NoSuchVmaAuditLogException, SystemException {
		

		try {
			

			VmaAuditLog vmaAuditLog = findByPrimaryKey(primaryKey);

			if (vmaAuditLog == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaAuditLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaAuditLog);
			return vmaAuditLog;
		}
		catch (NoSuchVmaAuditLogException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaAuditLog remove(VmaAuditLog VmaAuditLog) throws SystemException {
	removeImpl(VmaAuditLog);
	return VmaAuditLog;
}

protected VmaAuditLog removeImpl(VmaAuditLog vmaAuditLog)
		throws SystemException {
		try {
			repository.delete(vmaAuditLog);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAuditLog;
	}

	
	public VmaAuditLog updateImpl(
		com.fds.nsw.nghiepvu.model.VmaAuditLog vmaAuditLog, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(vmaAuditLog);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAuditLog;
	}

	
	public VmaAuditLog findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma audit log with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaAuditLogException} if it could not be found.
	 *
	 * @param id the primary key of the vma audit log
	 * @return the vma audit log
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAuditLogException if a vma audit log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAuditLog findByPrimaryKey(long id)
		throws NoSuchVmaAuditLogException, SystemException {
		VmaAuditLog vmaAuditLog = fetchByPrimaryKey(id);

		if (vmaAuditLog == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaAuditLogException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaAuditLog;
	}

	/**
	 * Returns the vma audit log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma audit log
	 * @return the vma audit log, or <code>null</code> if a vma audit log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAuditLog fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma audit log with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma audit log
	 * @return the vma audit log, or <code>null</code> if a vma audit log with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAuditLog fetchByPrimaryKey(long id) throws SystemException {
		VmaAuditLog vmaAuditLog = null;

		

		if (vmaAuditLog == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaAuditLog> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaAuditLog = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaAuditLog;
	}

	/**
	 * Returns all the vma audit logs.
	 *
	 * @return the vma audit logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAuditLog> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma audit logs
	 * @param end the upper bound of the range of vma audit logs (not inclusive)
	 * @return the range of vma audit logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAuditLog> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma audit logs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma audit logs
	 * @param end the upper bound of the range of vma audit logs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma audit logs
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAuditLog> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaAuditLog> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAAUDITLOG);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAAUDITLOG;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaAuditLog>) queryFactory.getResultList(builder);
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
	 * Removes all the vma audit logs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaAuditLog vmaAuditLog : findAll()) {
			repository.delete(vmaAuditLog);
		}
	}

	/**
	 * Returns the number of vma audit logs.
	 *
	 * @return the number of vma audit logs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAAUDITLOG).build();

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
	 * Initializes the vma audit log persistence.
	 */
	private static final String _SQL_SELECT_VMAAUDITLOG = "SELECT vmaAuditLog FROM VmaAuditLog vmaAuditLog";
	private static final String _SQL_COUNT_VMAAUDITLOG = "SELECT COUNT(vmaAuditLog) FROM VmaAuditLog vmaAuditLog";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaAuditLog.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaAuditLog exists with the primary key ";
	

	
}
