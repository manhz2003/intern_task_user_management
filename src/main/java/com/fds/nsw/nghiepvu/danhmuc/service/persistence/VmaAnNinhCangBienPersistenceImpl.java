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
import com.fds.nsw.nghiepvu.model.VmaAnNinhCangBien;
import com.fds.nsw.nghiepvu.service.exception.*;
//import com.fds.nsw.nghiepvu.repository.VmaAnNinhCangBienRepository;
import com.fds.nsw.nghiepvu.repository.VmaAnninhCangbienRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaAnNinhCangBienModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaAnNinhCangBienPersistenceImpl extends BasePersistence {
	@Autowired
	VmaAnninhCangbienRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaAnNinhCangBien> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaAnNinhCangBienUtil} to access the vma an ninh cang bien persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaAnNinhCangBien create(long id) {
		VmaAnNinhCangBien vmaAnNinhCangBien = new VmaAnNinhCangBien();

		
		//vmaAnNinhCangBien.setPrimaryKey(id);

		return vmaAnNinhCangBien;
	}

	/**
	 * Removes the vma an ninh cang bien with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma an ninh cang bien
	 * @return the vma an ninh cang bien that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaAnNinhCangBienException if a vma an ninh cang bien with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAnNinhCangBien remove(long id)
		throws NoSuchVmaAnNinhCangBienException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma an ninh cang bien with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma an ninh cang bien
	 * @return the vma an ninh cang bien that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaAnNinhCangBienException if a vma an ninh cang bien with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAnNinhCangBien remove(Serializable primaryKey)
		throws NoSuchVmaAnNinhCangBienException, SystemException {
		

		try {
			

			VmaAnNinhCangBien vmaAnNinhCangBien = findByPrimaryKey(primaryKey);

			if (vmaAnNinhCangBien == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaAnNinhCangBienException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaAnNinhCangBien);
			return vmaAnNinhCangBien;
		}
		catch (NoSuchVmaAnNinhCangBienException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaAnNinhCangBien remove(VmaAnNinhCangBien VmaAnNinhCangBien) throws SystemException {
	removeImpl(VmaAnNinhCangBien);	return VmaAnNinhCangBien;
}

protected VmaAnNinhCangBien removeImpl

(VmaAnNinhCangBien vmaAnNinhCangBien)
		throws SystemException {
		try {
			repository.delete(vmaAnNinhCangBien);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAnNinhCangBien;
	}

	
	public VmaAnNinhCangBien updateImpl(
		VmaAnNinhCangBien vmaAnNinhCangBien,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaAnNinhCangBien);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAnNinhCangBien;
	}

	
	public VmaAnNinhCangBien findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma an ninh cang bien with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaAnNinhCangBienException} if it could not be found.
	 *
	 * @param id the primary key of the vma an ninh cang bien
	 * @return the vma an ninh cang bien
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaAnNinhCangBienException if a vma an ninh cang bien with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAnNinhCangBien findByPrimaryKey(long id)
		throws NoSuchVmaAnNinhCangBienException, SystemException {
		VmaAnNinhCangBien vmaAnNinhCangBien = fetchByPrimaryKey(id);

		if (vmaAnNinhCangBien == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaAnNinhCangBienException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaAnNinhCangBien;
	}

	/**
	 * Returns the vma an ninh cang bien with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma an ninh cang bien
	 * @return the vma an ninh cang bien, or <code>null</code> if a vma an ninh cang bien with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAnNinhCangBien fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma an ninh cang bien with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma an ninh cang bien
	 * @return the vma an ninh cang bien, or <code>null</code> if a vma an ninh cang bien with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAnNinhCangBien fetchByPrimaryKey(long id)
		throws SystemException {
		VmaAnNinhCangBien vmaAnNinhCangBien = null;

		

		if (vmaAnNinhCangBien == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaAnNinhCangBien> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaAnNinhCangBien = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaAnNinhCangBien;
	}

	/**
	 * Returns all the vma an ninh cang biens.
	 *
	 * @return the vma an ninh cang biens
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAnNinhCangBien> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma an ninh cang biens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma an ninh cang biens
	 * @param end the upper bound of the range of vma an ninh cang biens (not inclusive)
	 * @return the range of vma an ninh cang biens
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAnNinhCangBien> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma an ninh cang biens.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma an ninh cang biens
	 * @param end the upper bound of the range of vma an ninh cang biens (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma an ninh cang biens
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAnNinhCangBien> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaAnNinhCangBien> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAANNINHCANGBIEN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAANNINHCANGBIEN;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaAnNinhCangBien>) queryFactory.getResultList(builder);
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
	 * Removes all the vma an ninh cang biens from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaAnNinhCangBien vmaAnNinhCangBien : findAll()) {
			repository.delete(vmaAnNinhCangBien);
		}
	}

	/**
	 * Returns the number of vma an ninh cang biens.
	 *
	 * @return the number of vma an ninh cang biens
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAANNINHCANGBIEN).build();

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
	 * Initializes the vma an ninh cang bien persistence.
	 */
	private static final String _SQL_SELECT_VMAANNINHCANGBIEN = "SELECT vmaAnNinhCangBien FROM VmaAnNinhCangBien vmaAnNinhCangBien";
	private static final String _SQL_COUNT_VMAANNINHCANGBIEN = "SELECT COUNT(vmaAnNinhCangBien) FROM VmaAnNinhCangBien vmaAnNinhCangBien";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaAnNinhCangBien.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaAnNinhCangBien exists with the primary key ";
	

	
}
