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
import com.fds.nsw.nghiepvu.model.VmaShip;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaShipRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaShipModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaShipPersistenceImpl extends BasePersistence {
	@Autowired
	VmaShipRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaShip> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaShipUtil} to access the vma ship persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaShip create(long id) {
		VmaShip vmaShip = new VmaShip();

		
		//vmaShip.setPrimaryKey(id);

		return vmaShip;
	}

	/**
	 * Removes the vma ship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma ship
	 * @return the vma ship that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a vma ship with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip remove(long id)
		throws NoSuchVmaShipException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma ship with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma ship
	 * @return the vma ship that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a vma ship with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaShip remove(Serializable primaryKey)
		throws NoSuchVmaShipException, SystemException {
		

		try {
			

			VmaShip vmaShip = findByPrimaryKey(primaryKey);

			if (vmaShip == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaShip);
			return vmaShip;
		}
		catch (NoSuchVmaShipException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaShip remove(VmaShip VmaShip) throws SystemException {
	removeImpl(VmaShip);
	return VmaShip;
}

protected VmaShip removeImpl(VmaShip vmaShip) throws SystemException {
		try {
			repository.delete(vmaShip);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaShip;
	}

	
	public VmaShip updateImpl(
		com.fds.nsw.nghiepvu.model.VmaShip vmaShip, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(vmaShip);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaShip;
	}

	
	public VmaShip findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma ship with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param id the primary key of the vma ship
	 * @return the vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a vma ship with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByPrimaryKey(long id)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByPrimaryKey(id);

		if (vmaShip == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaShipException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma ship
	 * @return the vma ship, or <code>null</code> if a vma ship with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaShip fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma ship with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma ship
	 * @return the vma ship, or <code>null</code> if a vma ship with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByPrimaryKey(long id) throws SystemException {
		VmaShip vmaShip = null;

		

		if (vmaShip == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaShip> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaShip = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByimoNumber(String imoNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByimoNumber(imoNumber);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber(String imoNumber) throws SystemException {
		return fetchByimoNumber(imoNumber, true);
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber(String imoNumber, boolean retrieveFromCache)
		throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where callSign = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param callSign the call sign
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findBycallSign(String callSign)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchBycallSign(callSign);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("callSign=");
			msg.append(callSign);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where callSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param callSign the call sign
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchBycallSign(String callSign) throws SystemException {
		return fetchBycallSign(callSign, true);
	}

	/**
	 * Returns the vma ship where callSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param callSign the call sign
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchBycallSign(String callSign, boolean retrieveFromCache)
		throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();
				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where vrCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByvrCode(String vrCode)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByvrCode(vrCode);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("vrCode=");
			msg.append(vrCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where vrCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByvrCode(String vrCode) throws SystemException {
		return fetchByvrCode(vrCode, true);
	}

	/**
	 * Returns the vma ship where vrCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param vrCode the vr code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByvrCode(String vrCode, boolean retrieveFromCache)
		throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();

				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByregistryNumber(String registryNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByregistryNumber(registryNumber);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registryNumber=");
			msg.append(registryNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByregistryNumber(String registryNumber)
		throws SystemException {
		return fetchByregistryNumber(registryNumber, true);
	}

	/**
	 * Returns the vma ship where registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByregistryNumber(String registryNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();
				

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByimoNumber_callSign(String imoNumber, String callSign)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByimoNumber_callSign(imoNumber, callSign);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(", callSign=");
			msg.append(callSign);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		return fetchByimoNumber_callSign(imoNumber, callSign, true);
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber_callSign(String imoNumber, String callSign,
		boolean retrieveFromCache) throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
//				e.printStackTrace();
				return null;
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByvrCode_registryNumber(imoNumber, registryNumber);

		if (vmaShip == null) {
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

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		return fetchByvrCode_registryNumber(imoNumber, registryNumber, true);
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber, boolean retrieveFromCache)
		throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

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

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaShipException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the matching vma ship
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaShipException if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip findByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = fetchByimoNumber_callSign_registryNumber(imoNumber,
				callSign, registryNumber);

		if (vmaShip == null) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(", callSign=");
			msg.append(callSign);

			msg.append(", registryNumber=");
			msg.append(registryNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaShipException(msg.toString());
		}

		return vmaShip;
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber) throws SystemException {
		return fetchByimoNumber_callSign_registryNumber(imoNumber, callSign,
			registryNumber, true);
	}

	/**
	 * Returns the vma ship where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma ship, or <code>null</code> if a matching vma ship could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip fetchByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber, boolean retrieveFromCache)
		throws SystemException {
		VmaShip vmaShip = null;
		if (vmaShip == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_VMASHIP_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_2);
				}
			}

			query.append(VmaShipModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaShip.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaShip = (VmaShip) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaShip;
	}

	/**
	 * Returns all the vma ships.
	 *
	 * @return the vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShip> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma ships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma ships
	 * @param end the upper bound of the range of vma ships (not inclusive)
	 * @return the range of vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShip> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma ships.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma ships
	 * @param end the upper bound of the range of vma ships (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaShip> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaShip> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASHIP);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASHIP.concat(VmaShipModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaShip>) queryFactory.getResultList(builder);
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
	 * Removes the vma ship where imoNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByimoNumber(String imoNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByimoNumber(imoNumber);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where callSign = &#63; from the database.
	 *
	 * @param callSign the call sign
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeBycallSign(String callSign)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findBycallSign(callSign);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where vrCode = &#63; from the database.
	 *
	 * @param vrCode the vr code
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByvrCode(String vrCode)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByvrCode(vrCode);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where registryNumber = &#63; from the database.
	 *
	 * @param registryNumber the registry number
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByregistryNumber(String registryNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByregistryNumber(registryNumber);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where imoNumber = &#63; and callSign = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByimoNumber_callSign(String imoNumber, String callSign)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByimoNumber_callSign(imoNumber, callSign);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where imoNumber = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByvrCode_registryNumber(imoNumber, registryNumber);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes the vma ship where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the vma ship that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaShip removeByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber)
		throws NoSuchVmaShipException, SystemException {
		VmaShip vmaShip = findByimoNumber_callSign_registryNumber(imoNumber,
				callSign, registryNumber);

		repository.delete(vmaShip);
			return vmaShip;
	}

	/**
	 * Removes all the vma ships from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaShip vmaShip : findAll()) {
			repository.delete(vmaShip);
		}
	}

	/**
	 * Returns the number of vma ships where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber(String imoNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycallSign(String callSign) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode(String vrCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByregistryNumber(String registryNumber)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

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
	 * Returns the number of vma ships where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the number of matching vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASHIP_WHERE);

			if (imoNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_1);
			}
			else {
				if (imoNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_2);
				}
			}

			if (callSign == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_1);
			}
			else {
				if (callSign.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_2);
				}
			}

			if (registryNumber == null) {
				query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_1);
			}
			else {
				if (registryNumber.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_3);
				}
				else {
					query.append(_FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_2);
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
	 * Returns the number of vma ships.
	 *
	 * @return the number of vma ships
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASHIP).build();

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
	 * Initializes the vma ship persistence.
	 */
	private static final String _SQL_SELECT_VMASHIP = "SELECT vmaShip FROM VmaShip vmaShip";
	private static final String _SQL_SELECT_VMASHIP_WHERE = "SELECT vmaShip FROM VmaShip vmaShip WHERE ";
	private static final String _SQL_COUNT_VMASHIP = "SELECT COUNT(vmaShip) FROM VmaShip vmaShip";
	private static final String _SQL_COUNT_VMASHIP_WHERE = "SELECT COUNT(vmaShip) FROM VmaShip vmaShip WHERE ";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_1 = "vmaShip.imoNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_2 = "vmaShip.imoNumber =:imoNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_3 = "(vmaShip.imoNumber IS NULL OR vmaShip.imoNumber =:imoNumber)";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_1 = "vmaShip.callSign IS NULL";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_2 = "vmaShip.callSign =:callSign";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_3 = "(vmaShip.callSign IS NULL OR vmaShip.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_1 = "vmaShip.vrCode IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_2 = "vmaShip.vrCode =:vrCode";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_3 = "(vmaShip.vrCode IS NULL OR vmaShip.vrCode =:vrCode)";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1 = "vmaShip.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2 = "vmaShip.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3 = "(vmaShip.registryNumber IS NULL OR vmaShip.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1 = "vmaShip.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2 = "vmaShip.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3 = "(vmaShip.imoNumber IS NULL OR vmaShip.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1 = "vmaShip.callSign IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2 = "vmaShip.callSign =:callSign";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3 = "(vmaShip.callSign IS NULL OR vmaShip.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaShip.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaShip.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaShip.imoNumber IS NULL OR vmaShip.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaShip.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaShip.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaShip.registryNumber IS NULL OR vmaShip.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaShip.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaShip.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaShip.imoNumber IS NULL OR vmaShip.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_1 =
		"vmaShip.callSign IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_2 =
		"vmaShip.callSign =:callSign AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_3 =
		"(vmaShip.callSign IS NULL OR vmaShip.callSign =:callSign) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaShip.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaShip.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaShip.registryNumber IS NULL OR vmaShip.registryNumber =:registryNumber)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaShip.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaShip exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaShip exists with the key {";
	

	
}
