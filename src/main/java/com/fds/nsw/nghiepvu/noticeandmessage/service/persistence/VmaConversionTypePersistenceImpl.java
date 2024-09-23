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
import com.fds.nsw.nghiepvu.model.VmaConversionType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaConversionTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaConversionTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaConversionTypePersistenceImpl extends BasePersistence {
	@Autowired
	VmaConversionTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaConversionType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaConversionTypeUtil} to access the vma conversion type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaConversionType create(long id) {
		VmaConversionType vmaConversionType = new VmaConversionType();

		
		//vmaConversionType.setPrimaryKey(id);

		return vmaConversionType;
	}

	/**
	 * Removes the vma conversion type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma conversion type
	 * @return the vma conversion type that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaConversionTypeException if a vma conversion type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaConversionType remove(long id)
		throws NoSuchVmaConversionTypeException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma conversion type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma conversion type
	 * @return the vma conversion type that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaConversionTypeException if a vma conversion type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaConversionType remove(Serializable primaryKey)
		throws NoSuchVmaConversionTypeException, SystemException {
		

		try {
			

			VmaConversionType vmaConversionType = findByPrimaryKey(primaryKey);

			if (vmaConversionType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaConversionType);
			return vmaConversionType;
		}
		catch (NoSuchVmaConversionTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaConversionType remove(VmaConversionType VmaConversionType) throws SystemException {
	removeImpl(VmaConversionType);
	return VmaConversionType;
}

protected VmaConversionType removeImpl(VmaConversionType vmaConversionType)
		throws SystemException {
		try {
			repository.delete(vmaConversionType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaConversionType;
	}

	
	public VmaConversionType updateImpl(
		com.fds.nsw.nghiepvu.model.VmaConversionType vmaConversionType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaConversionType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaConversionType;
	}

	
	public VmaConversionType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma conversion type with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaConversionTypeException} if it could not be found.
	 *
	 * @param id the primary key of the vma conversion type
	 * @return the vma conversion type
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaConversionTypeException if a vma conversion type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaConversionType findByPrimaryKey(long id)
		throws NoSuchVmaConversionTypeException, SystemException {
		VmaConversionType vmaConversionType = fetchByPrimaryKey(id);

		if (vmaConversionType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaConversionTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaConversionType;
	}

	/**
	 * Returns the vma conversion type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma conversion type
	 * @return the vma conversion type, or <code>null</code> if a vma conversion type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaConversionType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma conversion type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma conversion type
	 * @return the vma conversion type, or <code>null</code> if a vma conversion type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaConversionType fetchByPrimaryKey(long id)
		throws SystemException {
		VmaConversionType vmaConversionType = null;

		

		if (vmaConversionType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaConversionType> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaConversionType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaConversionType;
	}

	/**
	 * Returns all the vma conversion types.
	 *
	 * @return the vma conversion types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaConversionType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma conversion types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma conversion types
	 * @param end the upper bound of the range of vma conversion types (not inclusive)
	 * @return the range of vma conversion types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaConversionType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma conversion types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma conversion types
	 * @param end the upper bound of the range of vma conversion types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma conversion types
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaConversionType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaConversionType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMACONVERSIONTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMACONVERSIONTYPE.concat(VmaConversionTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaConversionType>) queryFactory.getResultList(builder);
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
	 * Removes all the vma conversion types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaConversionType vmaConversionType : findAll()) {
			repository.delete(vmaConversionType);
		}
	}

	/**
	 * Returns the number of vma conversion types.
	 *
	 * @return the number of vma conversion types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMACONVERSIONTYPE).build();

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
	 * Initializes the vma conversion type persistence.
	 */
	private static final String _SQL_SELECT_VMACONVERSIONTYPE = "SELECT vmaConversionType FROM VmaConversionType vmaConversionType";
	private static final String _SQL_COUNT_VMACONVERSIONTYPE = "SELECT COUNT(vmaConversionType) FROM VmaConversionType vmaConversionType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaConversionType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaConversionType exists with the primary key ";
	

	
}
