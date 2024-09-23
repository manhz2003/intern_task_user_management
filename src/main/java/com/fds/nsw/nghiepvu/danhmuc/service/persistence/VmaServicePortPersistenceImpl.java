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
import com.fds.nsw.nghiepvu.model.VmaServicePort;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaServicePortRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaServicePortModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaServicePortPersistenceImpl extends BasePersistence {
	@Autowired
	VmaServicePortRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaServicePort> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaServicePortUtil} to access the vma service port persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaServicePort create(long id) {
		VmaServicePort vmaServicePort = new VmaServicePort();

		
		//vmaServicePort.setPrimaryKey(id);

		return vmaServicePort;
	}

	/**
	 * Removes the vma service port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma service port
	 * @return the vma service port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaServicePortException if a vma service port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaServicePort remove(long id)
		throws NoSuchVmaServicePortException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma service port with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma service port
	 * @return the vma service port that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaServicePortException if a vma service port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaServicePort remove(Serializable primaryKey)
		throws NoSuchVmaServicePortException, SystemException {
		

		try {
			

			VmaServicePort vmaServicePort = findByPrimaryKey(primaryKey);

			if (vmaServicePort == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaServicePortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaServicePort);
			return vmaServicePort;
		}
		catch (NoSuchVmaServicePortException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaServicePort remove(VmaServicePort VmaServicePort) throws SystemException {
	removeImpl(VmaServicePort);	return VmaServicePort;
}

protected VmaServicePort removeImpl

(VmaServicePort vmaServicePort)
		throws SystemException {
		try {
			repository.delete(vmaServicePort);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaServicePort;
	}

	
	public VmaServicePort updateImpl(
		VmaServicePort vmaServicePort, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(vmaServicePort);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaServicePort;
	}

	
	public VmaServicePort findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma service port with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaServicePortException} if it could not be found.
	 *
	 * @param id the primary key of the vma service port
	 * @return the vma service port
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaServicePortException if a vma service port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaServicePort findByPrimaryKey(long id)
		throws NoSuchVmaServicePortException, SystemException {
		VmaServicePort vmaServicePort = fetchByPrimaryKey(id);

		if (vmaServicePort == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaServicePortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaServicePort;
	}

	/**
	 * Returns the vma service port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma service port
	 * @return the vma service port, or <code>null</code> if a vma service port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaServicePort fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma service port with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma service port
	 * @return the vma service port, or <code>null</code> if a vma service port with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaServicePort fetchByPrimaryKey(long id) throws SystemException {
		VmaServicePort vmaServicePort = null;

		

		if (vmaServicePort == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaServicePort> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaServicePort = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaServicePort;
	}

	/**
	 * Returns all the vma service ports.
	 *
	 * @return the vma service ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaServicePort> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma service ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma service ports
	 * @param end the upper bound of the range of vma service ports (not inclusive)
	 * @return the range of vma service ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaServicePort> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma service ports.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma service ports
	 * @param end the upper bound of the range of vma service ports (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma service ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaServicePort> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaServicePort> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASERVICEPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASERVICEPORT;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaServicePort>) queryFactory.getResultList(builder);
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
	 * Removes all the vma service ports from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaServicePort vmaServicePort : findAll()) {
			repository.delete(vmaServicePort);
		}
	}

	/**
	 * Returns the number of vma service ports.
	 *
	 * @return the number of vma service ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASERVICEPORT).build();

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
	 * Initializes the vma service port persistence.
	 */
	private static final String _SQL_SELECT_VMASERVICEPORT = "SELECT vmaServicePort FROM VmaServicePort vmaServicePort";
	private static final String _SQL_COUNT_VMASERVICEPORT = "SELECT COUNT(vmaServicePort) FROM VmaServicePort vmaServicePort";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaServicePort.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaServicePort exists with the primary key ";
	

	
}
