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
import com.fds.nsw.nghiepvu.model.VmaShipCertificate;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaShipCertificateRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaShipCertificateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaShipCertificatePersistenceImpl extends BasePersistence {
	@Autowired
	VmaShipCertificateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaShipCertificate> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaShipCertificateUtil} to access the vma ship certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaShipCertificate create(long id) {
		VmaShipCertificate vmaShipCertificate = new VmaShipCertificate();

		
		//vmaShipCertificate.setPrimaryKey(id);

		return vmaShipCertificate;
	}

	/**
	 * Removes the vma ship certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma ship certificate
	 * @return the vma ship certificate that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate remove(long id)
		throws NoSuchVmaShipCertificateException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma ship certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma ship certificate
	 * @return the vma ship certificate that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaShipCertificate remove(Serializable primaryKey)
		throws NoSuchVmaShipCertificateException, SystemException {
		

		try {
			

			VmaShipCertificate vmaShipCertificate = findByPrimaryKey(primaryKey);

			if (vmaShipCertificate == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaShipCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
		}
		catch (NoSuchVmaShipCertificateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaShipCertificate remove(VmaShipCertificate VmaShipCertificate) throws SystemException {
	removeImpl(VmaShipCertificate);
	return VmaShipCertificate;
}

protected VmaShipCertificate removeImpl(
		VmaShipCertificate vmaShipCertificate) throws SystemException {
		try {
			repository.delete(vmaShipCertificate);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaShipCertificate;
	}

	
	public VmaShipCertificate updateImpl(
		com.fds.nsw.nghiepvu.model.VmaShipCertificate vmaShipCertificate,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaShipCertificate);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaShipCertificate;
	}

	
	public VmaShipCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma ship certificate with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param id the primary key of the vma ship certificate
	 * @return the vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByPrimaryKey(long id)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByPrimaryKey(id);

		if (vmaShipCertificate == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaShipCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma ship certificate
	 * @return the vma ship certificate, or <code>null</code> if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaShipCertificate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma ship certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma ship certificate
	 * @return the vma ship certificate, or <code>null</code> if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByPrimaryKey(long id)
		throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;

		

		if (vmaShipCertificate == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaShipCertificate> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaShipCertificate = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByimoNumber(String imoNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByimoNumber(imoNumber);

		if (vmaShipCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipCertificateException(msg.toString());
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByimoNumber(String imoNumber)
		throws SystemException {
		return fetchByimoNumber(imoNumber, true);
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByimoNumber(String imoNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;
		if (vmaShipCertificate == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShipCertificate.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				vmaShipCertificate = (VmaShipCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where callSign = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param callSign the call sign
	 * @return the matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findBycallSign(String callSign)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchBycallSign(callSign);

		if (vmaShipCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("callSign=");
			msg.append(callSign);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipCertificateException(msg.toString());
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where callSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param callSign the call sign
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchBycallSign(String callSign)
		throws SystemException {
		return fetchBycallSign(callSign, true);
	}

	/**
	 * Returns the vma ship certificate where callSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param callSign the call sign
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchBycallSign(String callSign,
		boolean retrieveFromCache) throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;
		if (vmaShipCertificate == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (callSign == null) {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShipCertificate.class).build();
				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				vmaShipCertificate = (VmaShipCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where vrCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByvrCode(String vrCode)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByvrCode(vrCode);

		if (vmaShipCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("vrCode=");
			msg.append(vrCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipCertificateException(msg.toString());
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where vrCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByvrCode(String vrCode)
		throws SystemException {
		return fetchByvrCode(vrCode, true);
	}

	/**
	 * Returns the vma ship certificate where vrCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param vrCode the vr code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByvrCode(String vrCode,
		boolean retrieveFromCache) throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;
		if (vmaShipCertificate == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (vrCode == null) {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_1);
			}
			else {
				if (vrCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShipCertificate.class).build();
				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
				}

				vmaShipCertificate = (VmaShipCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByregistryNumber(String registryNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByregistryNumber(registryNumber);

		if (vmaShipCertificate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registryNumber=");
			msg.append(registryNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipCertificateException(msg.toString());
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByregistryNumber(String registryNumber)
		throws SystemException {
		return fetchByregistryNumber(registryNumber, true);
	}

	/**
	 * Returns the vma ship certificate where registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByregistryNumber(String registryNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;
		if (vmaShipCertificate == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShipCertificate.class).build();

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaShipCertificate = (VmaShipCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShipCertificate;
	}

	/**
	 * Returns all the vma ship certificates where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findByimoNumber_callSign(String imoNumber,
		String callSign) throws SystemException {
		return findByimoNumber_callSign(imoNumber, callSign, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma ship certificates where imoNumber = &#63; and callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma ship certificates
	 * @param end the upper bound of the range of vma ship certificates (not inclusive)
	 * @return the range of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findByimoNumber_callSign(String imoNumber,
		String callSign, int start, int end) throws SystemException {
		return findByimoNumber_callSign(imoNumber, callSign, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma ship certificates where imoNumber = &#63; and callSign = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param start the lower bound of the range of vma ship certificates
	 * @param end the upper bound of the range of vma ship certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findByimoNumber_callSign(String imoNumber,
		String callSign, int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaShipCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				list = (List<VmaShipCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first vma ship certificate in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByimoNumber_callSign_First(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByimoNumber_callSign_First(imoNumber,
				callSign, orderByComparator);

		if (vmaShipCertificate != null) {
			return vmaShipCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaShipCertificateException(msg.toString());
	}

	/**
	 * Returns the first vma ship certificate in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByimoNumber_callSign_First(
		String imoNumber, String callSign, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaShipCertificate> list = findByimoNumber_callSign(imoNumber,
				callSign, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma ship certificate in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByimoNumber_callSign_Last(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByimoNumber_callSign_Last(imoNumber,
				callSign, orderByComparator);

		if (vmaShipCertificate != null) {
			return vmaShipCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("imoNumber=");
		msg.append(imoNumber);

		msg.append(", callSign=");
		msg.append(callSign);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaShipCertificateException(msg.toString());
	}

	/**
	 * Returns the last vma ship certificate in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByimoNumber_callSign_Last(String imoNumber,
		String callSign, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByimoNumber_callSign(imoNumber, callSign);

		List<VmaShipCertificate> list = findByimoNumber_callSign(imoNumber,
				callSign, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma ship certificates before and after the current vma ship certificate in the ordered set where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param id the primary key of the current vma ship certificate
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a vma ship certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate[] findByimoNumber_callSign_PrevAndNext(long id,
		String imoNumber, String callSign, OrderByComparator orderByComparator)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findByPrimaryKey(id);

		

		try {
			

			VmaShipCertificate[] array = new VmaShipCertificate[3];

			array[0] = getByimoNumber_callSign_PrevAndNext(
					vmaShipCertificate, imoNumber, callSign, orderByComparator,
					true);

			array[1] = vmaShipCertificate;

			array[2] = getByimoNumber_callSign_PrevAndNext(
					vmaShipCertificate, imoNumber, callSign, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaShipCertificate getByimoNumber_callSign_PrevAndNext(
		 VmaShipCertificate vmaShipCertificate,
		String imoNumber, String callSign, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

		if (imoNumber == null) {
			query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
		}
		else {
			if (imoNumber.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
			}
			else {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
			}
		}

		if (callSign == null) {
			query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
		}
		else {
			if (callSign.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
			}
			else {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
			}
		}

		if (orderByComparator != null) {
			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();

			if (orderByConditionFields.length > 0) {
				query.append(WHERE_AND);
			}

			for (int i = 0; i < orderByConditionFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByConditionFields[i]);

				if ((i + 1) < orderByConditionFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN_HAS_NEXT);
					}
					else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					}
					else {
						query.append(WHERE_LESSER_THAN);
					}
				}
			}

			query.append(ORDER_BY_CLAUSE);

			String[] orderByFields = orderByComparator.getOrderByFields();

			for (int i = 0; i < orderByFields.length; i++) {
				query.append(_ORDER_BY_ENTITY_ALIAS);
				query.append(orderByFields[i]);

				if ((i + 1) < orderByFields.length) {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC_HAS_NEXT);
					}
					else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				}
				else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					}
					else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (imoNumber != null) {
			builder.appendNamedParameterMap("imoNumber", imoNumber);
		}

		if (callSign != null) {
			builder.appendNamedParameterMap("callSign", callSign);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaShipCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaShipCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; and registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma ship certificate
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipCertificateException if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate findByvrCode_registryNumber(String imoNumber,
		String registryNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = fetchByvrCode_registryNumber(imoNumber,
				registryNumber);

		if (vmaShipCertificate == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(", registryNumber=");
			msg.append(registryNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipCertificateException(msg.toString());
		}

		return vmaShipCertificate;
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		return fetchByvrCode_registryNumber(imoNumber, registryNumber, true);
	}

	/**
	 * Returns the vma ship certificate where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship certificate, or <code>null</code> if a matching vma ship certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber, boolean retrieveFromCache)
		throws SystemException {
		VmaShipCertificate vmaShipCertificate = null;
		if (vmaShipCertificate == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShipCertificate.class).build();
				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaShipCertificate = (VmaShipCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShipCertificate;
	}

	/**
	 * Returns all the vma ship certificates.
	 *
	 * @return the vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma ship certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma ship certificates
	 * @param end the upper bound of the range of vma ship certificates (not inclusive)
	 * @return the range of vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma ship certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma ship certificates
	 * @param end the upper bound of the range of vma ship certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShipCertificate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaShipCertificate> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASHIPCERTIFICATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASHIPCERTIFICATE;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaShipCertificate>) queryFactory.getResultList(builder);
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
	 * Removes the vma ship certificate where imoNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @return the vma ship certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate removeByimoNumber(String imoNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findByimoNumber(imoNumber);

		repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
	}

	/**
	 * Removes the vma ship certificate where callSign = &#63; from the database.
	 *
	 * @param callSign the call sign
	 * @return the vma ship certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate removeBycallSign(String callSign)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findBycallSign(callSign);

		repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
	}

	/**
	 * Removes the vma ship certificate where vrCode = &#63; from the database.
	 *
	 * @param vrCode the vr code
	 * @return the vma ship certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate removeByvrCode(String vrCode)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findByvrCode(vrCode);

		repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
	}

	/**
	 * Removes the vma ship certificate where registryNumber = &#63; from the database.
	 *
	 * @param registryNumber the registry number
	 * @return the vma ship certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate removeByregistryNumber(String registryNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findByregistryNumber(registryNumber);

		repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
	}

	/**
	 * Removes all the vma ship certificates where imoNumber = &#63; and callSign = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		for (VmaShipCertificate vmaShipCertificate : findByimoNumber_callSign(
				imoNumber, callSign)) {
			repository.delete(vmaShipCertificate);
		}
	}

	/**
	 * Removes the vma ship certificate where imoNumber = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the vma ship certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShipCertificate removeByvrCode_registryNumber(String imoNumber,
		String registryNumber)
		throws NoSuchVmaShipCertificateException, SystemException {
		VmaShipCertificate vmaShipCertificate = findByvrCode_registryNumber(imoNumber,
				registryNumber);

		repository.delete(vmaShipCertificate);
			return vmaShipCertificate;
	}

	/**
	 * Removes all the vma ship certificates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaShipCertificate vmaShipCertificate : findAll()) {
			repository.delete(vmaShipCertificate);
		}
	}

	/**
	 * Returns the number of vma ship certificates where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber(String imoNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_IMONUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycallSign(String callSign) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (callSign == null) {
				query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_CALLSIGN_CALLSIGN_2);
				}
			}

			String sql = query.toString();

			

			try {QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode(String vrCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (vrCode == null) {
				query.append(_FINDER_COLUMN_VRCODE_VRCODE_1);
			}
			else {
				if (vrCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_VRCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByregistryNumber(String registryNumber)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the number of matching vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASHIPCERTIFICATE_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				count = (Long) queryFactory.getSingleResult(builder);
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
	 * Returns the number of vma ship certificates.
	 *
	 * @return the number of vma ship certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASHIPCERTIFICATE).build();

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
	 * Initializes the vma ship certificate persistence.
	 */
	private static final String _SQL_SELECT_VMASHIPCERTIFICATE = "SELECT vmaShipCertificate FROM VmaShipCertificate vmaShipCertificate";
	private static final String _SQL_SELECT_VMASHIPCERTIFICATE_WHERE = "SELECT vmaShipCertificate FROM VmaShipCertificate vmaShipCertificate WHERE ";
	private static final String _SQL_COUNT_VMASHIPCERTIFICATE = "SELECT COUNT(vmaShipCertificate) FROM VmaShipCertificate vmaShipCertificate";
	private static final String _SQL_COUNT_VMASHIPCERTIFICATE_WHERE = "SELECT COUNT(vmaShipCertificate) FROM VmaShipCertificate vmaShipCertificate WHERE ";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_1 = "vmaShipCertificate.imoNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_2 = "vmaShipCertificate.imoNumber =:imoNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_3 = "(vmaShipCertificate.imoNumber IS NULL OR vmaShipCertificate.imoNumber =:imoNumber)";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_1 = "vmaShipCertificate.callSign IS NULL";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_2 = "vmaShipCertificate.callSign =:callSign";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_3 = "(vmaShipCertificate.callSign IS NULL OR vmaShipCertificate.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_1 = "vmaShipCertificate.vrCode IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_2 = "vmaShipCertificate.vrCode =:vrCode";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_3 = "(vmaShipCertificate.vrCode IS NULL OR vmaShipCertificate.vrCode =:vrCode)";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1 = "vmaShipCertificate.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2 = "vmaShipCertificate.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3 = "(vmaShipCertificate.registryNumber IS NULL OR vmaShipCertificate.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1 = "vmaShipCertificate.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2 = "vmaShipCertificate.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3 = "(vmaShipCertificate.imoNumber IS NULL OR vmaShipCertificate.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1 = "vmaShipCertificate.callSign IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2 = "vmaShipCertificate.callSign =:callSign";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3 = "(vmaShipCertificate.callSign IS NULL OR vmaShipCertificate.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaShipCertificate.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaShipCertificate.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaShipCertificate.imoNumber IS NULL OR vmaShipCertificate.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaShipCertificate.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaShipCertificate.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaShipCertificate.registryNumber IS NULL OR vmaShipCertificate.registryNumber =:registryNumber)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaShipCertificate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaShipCertificate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaShipCertificate exists with the key {";
	

	
}
