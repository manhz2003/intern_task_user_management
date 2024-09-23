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
import com.fds.nsw.nghiepvu.model.VmaHoatDongNaoVet;
import com.fds.nsw.nghiepvu.service.exception.*;
//import com.fds.nsw.nghiepvu.repository.VmaHoatDongNaoVetRepository;
import com.fds.nsw.nghiepvu.repository.VmaHoatdongNaovetRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaHoatDongNaoVetModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaHoatDongNaoVetPersistenceImpl extends BasePersistence {
	@Autowired
	VmaHoatdongNaovetRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaHoatDongNaoVet> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaHoatDongNaoVetUtil} to access the vma hoat dong nao vet persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaHoatDongNaoVet create(long id) {
		VmaHoatDongNaoVet vmaHoatDongNaoVet = new VmaHoatDongNaoVet();

		
		//vmaHoatDongNaoVet.setPrimaryKey(id);

		return vmaHoatDongNaoVet;
	}

	/**
	 * Removes the vma hoat dong nao vet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma hoat dong nao vet
	 * @return the vma hoat dong nao vet that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaHoatDongNaoVetException if a vma hoat dong nao vet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaHoatDongNaoVet remove(long id)
		throws NoSuchVmaHoatDongNaoVetException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma hoat dong nao vet with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma hoat dong nao vet
	 * @return the vma hoat dong nao vet that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaHoatDongNaoVetException if a vma hoat dong nao vet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaHoatDongNaoVet remove(Serializable primaryKey)
		throws NoSuchVmaHoatDongNaoVetException, SystemException {
		

		try {
			

			VmaHoatDongNaoVet vmaHoatDongNaoVet = findByPrimaryKey(primaryKey);

			if (vmaHoatDongNaoVet == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaHoatDongNaoVetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaHoatDongNaoVet);
			return vmaHoatDongNaoVet;
		}
		catch (NoSuchVmaHoatDongNaoVetException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaHoatDongNaoVet remove(VmaHoatDongNaoVet VmaHoatDongNaoVet) throws SystemException {
	removeImpl(VmaHoatDongNaoVet);	return VmaHoatDongNaoVet;
}

protected VmaHoatDongNaoVet removeImpl

(VmaHoatDongNaoVet vmaHoatDongNaoVet)
		throws SystemException {
		try {
			repository.delete(vmaHoatDongNaoVet);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaHoatDongNaoVet;
	}

	
	public VmaHoatDongNaoVet updateImpl(
		VmaHoatDongNaoVet vmaHoatDongNaoVet,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaHoatDongNaoVet);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaHoatDongNaoVet;
	}

	
	public VmaHoatDongNaoVet findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma hoat dong nao vet with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaHoatDongNaoVetException} if it could not be found.
	 *
	 * @param id the primary key of the vma hoat dong nao vet
	 * @return the vma hoat dong nao vet
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaHoatDongNaoVetException if a vma hoat dong nao vet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaHoatDongNaoVet findByPrimaryKey(long id)
		throws NoSuchVmaHoatDongNaoVetException, SystemException {
		VmaHoatDongNaoVet vmaHoatDongNaoVet = fetchByPrimaryKey(id);

		if (vmaHoatDongNaoVet == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaHoatDongNaoVetException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaHoatDongNaoVet;
	}

	/**
	 * Returns the vma hoat dong nao vet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma hoat dong nao vet
	 * @return the vma hoat dong nao vet, or <code>null</code> if a vma hoat dong nao vet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaHoatDongNaoVet fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma hoat dong nao vet with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma hoat dong nao vet
	 * @return the vma hoat dong nao vet, or <code>null</code> if a vma hoat dong nao vet with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaHoatDongNaoVet fetchByPrimaryKey(long id)
		throws SystemException {
		VmaHoatDongNaoVet vmaHoatDongNaoVet = null;

		

		if (vmaHoatDongNaoVet == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaHoatDongNaoVet> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaHoatDongNaoVet = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaHoatDongNaoVet;
	}

	/**
	 * Returns all the vma hoat dong nao vets.
	 *
	 * @return the vma hoat dong nao vets
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaHoatDongNaoVet> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma hoat dong nao vets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma hoat dong nao vets
	 * @param end the upper bound of the range of vma hoat dong nao vets (not inclusive)
	 * @return the range of vma hoat dong nao vets
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaHoatDongNaoVet> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma hoat dong nao vets.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma hoat dong nao vets
	 * @param end the upper bound of the range of vma hoat dong nao vets (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma hoat dong nao vets
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaHoatDongNaoVet> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaHoatDongNaoVet> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAHOATDONGNAOVET);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAHOATDONGNAOVET;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaHoatDongNaoVet>) queryFactory.getResultList(builder);
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
	 * Removes all the vma hoat dong nao vets from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaHoatDongNaoVet vmaHoatDongNaoVet : findAll()) {
			repository.delete(vmaHoatDongNaoVet);
		}
	}

	/**
	 * Returns the number of vma hoat dong nao vets.
	 *
	 * @return the number of vma hoat dong nao vets
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAHOATDONGNAOVET).build();

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
	 * Initializes the vma hoat dong nao vet persistence.
	 */
	private static final String _SQL_SELECT_VMAHOATDONGNAOVET = "SELECT vmaHoatDongNaoVet FROM VmaHoatDongNaoVet vmaHoatDongNaoVet";
	private static final String _SQL_COUNT_VMAHOATDONGNAOVET = "SELECT COUNT(vmaHoatDongNaoVet) FROM VmaHoatDongNaoVet vmaHoatDongNaoVet";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaHoatDongNaoVet.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaHoatDongNaoVet exists with the primary key ";
	

	
}
