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
import com.fds.nsw.nghiepvu.model.VmaCertificate;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaCertificateRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaCertificateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaCertificatePersistenceImpl extends BasePersistence {
	@Autowired
	VmaCertificateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaCertificate> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaCertificateUtil} to access the vma certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaCertificate create(int id) {
		VmaCertificate vmaCertificate = new VmaCertificate();

		
		//vmaCertificate.setPrimaryKey(id);

		return vmaCertificate;
	}

	/**
	 * Removes the vma certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma certificate
	 * @return the vma certificate that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaCertificateException if a vma certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaCertificate remove(int id)
		throws NoSuchVmaCertificateException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the vma certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma certificate
	 * @return the vma certificate that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaCertificateException if a vma certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaCertificate remove(Serializable primaryKey)
		throws NoSuchVmaCertificateException, SystemException {
		

		try {
			

			VmaCertificate vmaCertificate = findByPrimaryKey(primaryKey);

			if (vmaCertificate == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaCertificate);
			return vmaCertificate;
		}
		catch (NoSuchVmaCertificateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaCertificate remove(VmaCertificate VmaCertificate) throws SystemException {
	removeImpl(VmaCertificate);
	return VmaCertificate;
}

protected VmaCertificate removeImpl(VmaCertificate vmaCertificate)
		throws SystemException {
		try {
			repository.delete(vmaCertificate);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaCertificate;
	}

	
	public VmaCertificate updateImpl(
		com.fds.nsw.nghiepvu.model.VmaCertificate vmaCertificate,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaCertificate);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaCertificate;
	}

	
	public VmaCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the vma certificate with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaCertificateException} if it could not be found.
	 *
	 * @param id the primary key of the vma certificate
	 * @return the vma certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaCertificateException if a vma certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaCertificate findByPrimaryKey(int id)
		throws NoSuchVmaCertificateException, SystemException {
		VmaCertificate vmaCertificate = fetchByPrimaryKey(id);

		if (vmaCertificate == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaCertificate;
	}

	/**
	 * Returns the vma certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma certificate
	 * @return the vma certificate, or <code>null</code> if a vma certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaCertificate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the vma certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma certificate
	 * @return the vma certificate, or <code>null</code> if a vma certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaCertificate fetchByPrimaryKey(int id) throws SystemException {
		VmaCertificate vmaCertificate = null;

		

		if (vmaCertificate == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaCertificate> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaCertificate = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaCertificate;
	}

	/**
	 * Returns all the vma certificates.
	 *
	 * @return the vma certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaCertificate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma certificates
	 * @param end the upper bound of the range of vma certificates (not inclusive)
	 * @return the range of vma certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaCertificate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma certificates
	 * @param end the upper bound of the range of vma certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaCertificate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaCertificate> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMACERTIFICATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMACERTIFICATE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaCertificate>) queryFactory.getResultList(builder);
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
	 * Removes all the vma certificates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaCertificate vmaCertificate : findAll()) {
			repository.delete(vmaCertificate);
		}
	}

	/**
	 * Returns the number of vma certificates.
	 *
	 * @return the number of vma certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMACERTIFICATE).build();

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
	 * Initializes the vma certificate persistence.
	 */
	private static final String _SQL_SELECT_VMACERTIFICATE = "SELECT vmaCertificate FROM VmaCertificate vmaCertificate";
	private static final String _SQL_COUNT_VMACERTIFICATE = "SELECT COUNT(vmaCertificate) FROM VmaCertificate vmaCertificate";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaCertificate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaCertificate exists with the primary key ";
	

	
}