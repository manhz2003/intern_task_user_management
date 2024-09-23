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
import com.fds.nsw.nghiepvu.model.VmaScheduleTransfer;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaScheduleTransferRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaScheduleTransferModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaScheduleTransferPersistenceImpl extends BasePersistence {
	@Autowired
	VmaScheduleTransferRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaScheduleTransfer> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaScheduleTransferUtil} to access the vma schedule transfer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaScheduleTransfer create(long id) {
		VmaScheduleTransfer vmaScheduleTransfer = new VmaScheduleTransfer();

		
		//vmaScheduleTransfer.setPrimaryKey(id);

		return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma schedule transfer
	 * @return the vma schedule transfer that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer remove(long id)
		throws NoSuchVmaScheduleTransferException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma schedule transfer with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma schedule transfer
	 * @return the vma schedule transfer that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleTransfer remove(Serializable primaryKey)
		throws NoSuchVmaScheduleTransferException, SystemException {
		

		try {
			

			VmaScheduleTransfer vmaScheduleTransfer = findByPrimaryKey(primaryKey);

			if (vmaScheduleTransfer == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaScheduleTransferException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
		}
		catch (NoSuchVmaScheduleTransferException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaScheduleTransfer remove(VmaScheduleTransfer VmaScheduleTransfer) throws SystemException {
	removeImpl(VmaScheduleTransfer);
	return VmaScheduleTransfer;
}

protected VmaScheduleTransfer removeImpl(
		VmaScheduleTransfer vmaScheduleTransfer) throws SystemException {
		try {
			repository.delete(vmaScheduleTransfer);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleTransfer;
	}

	
	public VmaScheduleTransfer updateImpl(
		com.fds.nsw.nghiepvu.model.VmaScheduleTransfer vmaScheduleTransfer,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaScheduleTransfer);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaScheduleTransfer;
	}

	
	public VmaScheduleTransfer findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule transfer with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param id the primary key of the vma schedule transfer
	 * @return the vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByPrimaryKey(long id)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByPrimaryKey(id);

		if (vmaScheduleTransfer == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaScheduleTransferException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma schedule transfer
	 * @return the vma schedule transfer, or <code>null</code> if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaScheduleTransfer fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma schedule transfer with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma schedule transfer
	 * @return the vma schedule transfer, or <code>null</code> if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByPrimaryKey(long id)
		throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;

		

		if (vmaScheduleTransfer == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaScheduleTransfer> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaScheduleTransfer = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByimoNumber(String imoNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByimoNumber(imoNumber);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("imoNumber=");
			msg.append(imoNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber(String imoNumber)
		throws SystemException {
		return fetchByimoNumber(imoNumber, true);
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber(String imoNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();
				

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where callSign = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param callSign the call sign
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findBycallSign(String callSign)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchBycallSign(callSign);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("callSign=");
			msg.append(callSign);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where callSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param callSign the call sign
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchBycallSign(String callSign)
		throws SystemException {
		return fetchBycallSign(callSign, true);
	}

	/**
	 * Returns the vma schedule transfer where callSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param callSign the call sign
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchBycallSign(String callSign,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();
				

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where vrCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByvrCode(String vrCode)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByvrCode(vrCode);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("vrCode=");
			msg.append(vrCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where vrCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param vrCode the vr code
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByvrCode(String vrCode)
		throws SystemException {
		return fetchByvrCode(vrCode, true);
	}

	/**
	 * Returns the vma schedule transfer where vrCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param vrCode the vr code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByvrCode(String vrCode,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();
				

				if (vrCode != null) {
					builder.appendNamedParameterMap("vrCode", vrCode);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByregistryNumber(String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByregistryNumber(registryNumber);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("registryNumber=");
			msg.append(registryNumber);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByregistryNumber(String registryNumber)
		throws SystemException {
		return fetchByregistryNumber(registryNumber, true);
	}

	/**
	 * Returns the vma schedule transfer where registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByregistryNumber(String registryNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByimoNumber_callSign(String imoNumber,
		String callSign)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByimoNumber_callSign(imoNumber,
				callSign);

		if (vmaScheduleTransfer == null) {
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

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber_callSign(String imoNumber,
		String callSign) throws SystemException {
		return fetchByimoNumber_callSign(imoNumber, callSign, true);
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber_callSign(String imoNumber,
		String callSign, boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByvrCode_registryNumber(String imoNumber,
		String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByvrCode_registryNumber(imoNumber,
				registryNumber);

		if (vmaScheduleTransfer == null) {
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

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		return fetchByvrCode_registryNumber(imoNumber, registryNumber, true);
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByvrCode_registryNumber(String imoNumber,
		String registryNumber, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByimoNumber_callSign_registryNumber(
		String imoNumber, String callSign, String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByimoNumber_callSign_registryNumber(imoNumber,
				callSign, registryNumber);

		if (vmaScheduleTransfer == null) {
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

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber_callSign_registryNumber(
		String imoNumber, String callSign, String registryNumber)
		throws SystemException {
		return fetchByimoNumber_callSign_registryNumber(imoNumber, callSign,
			registryNumber, true);
	}

	/**
	 * Returns the vma schedule transfer where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByimoNumber_callSign_registryNumber(
		String imoNumber, String callSign, String registryNumber,
		boolean retrieveFromCache) throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

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

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();

				if (imoNumber != null) {
					builder.appendNamedParameterMap("imoNumber", imoNumber);
				}

				if (callSign != null) {
					builder.appendNamedParameterMap("callSign", callSign);
				}

				if (registryNumber != null) {
					builder.appendNamedParameterMap("registryNumber", registryNumber);
				}

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns all the vma schedule transfers where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo(String itineraryNo)
		throws SystemException {
		return findByitineraryNo(itineraryNo, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule transfers where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @return the range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo(String itineraryNo,
		int start, int end) throws SystemException {
		return findByitineraryNo(itineraryNo, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule transfers where itineraryNo = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo(String itineraryNo,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleTransfer> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				list = (List<VmaScheduleTransfer>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule transfer in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByitineraryNo_First(itineraryNo,
				orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the first vma schedule transfer in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_First(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTransfer> list = findByitineraryNo(itineraryNo, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByitineraryNo_Last(itineraryNo,
				orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_Last(String itineraryNo,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo(itineraryNo);

		List<VmaScheduleTransfer> list = findByitineraryNo(itineraryNo,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule transfers before and after the current vma schedule transfer in the ordered set where itineraryNo = &#63;.
	 *
	 * @param id the primary key of the current vma schedule transfer
	 * @param itineraryNo the itinerary no
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer[] findByitineraryNo_PrevAndNext(long id,
		String itineraryNo, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTransfer[] array = new VmaScheduleTransfer[3];

			array[0] = getByitineraryNo_PrevAndNext(
					vmaScheduleTransfer, itineraryNo, orderByComparator, true);

			array[1] = vmaScheduleTransfer;

			array[2] = getByitineraryNo_PrevAndNext(
					vmaScheduleTransfer, itineraryNo, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTransfer getByitineraryNo_PrevAndNext(
		 VmaScheduleTransfer vmaScheduleTransfer,
		String itineraryNo, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
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

		else {
			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (itineraryNo != null) {
			builder.appendNamedParameterMap("itineraryNo", itineraryNo);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTransfer);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTransfer> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma schedule transfers where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType) throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule transfers where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @return the range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end)
		throws SystemException {
		return findByitineraryNo_noticeShipType(itineraryNo, noticeShipType,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule transfers where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByitineraryNo_noticeShipType(
		String itineraryNo, int noticeShipType, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTransfer> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

				list = (List<VmaScheduleTransfer>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule transfer in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByitineraryNo_noticeShipType_First(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the first vma schedule transfer in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_noticeShipType_First(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTransfer> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByitineraryNo_noticeShipType_Last(itineraryNo,
				noticeShipType, orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("itineraryNo=");
		msg.append(itineraryNo);

		msg.append(", noticeShipType=");
		msg.append(noticeShipType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_noticeShipType_Last(
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType);

		List<VmaScheduleTransfer> list = findByitineraryNo_noticeShipType(itineraryNo,
				noticeShipType, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule transfers before and after the current vma schedule transfer in the ordered set where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param id the primary key of the current vma schedule transfer
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer[] findByitineraryNo_noticeShipType_PrevAndNext(
		long id, String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTransfer[] array = new VmaScheduleTransfer[3];

			array[0] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleTransfer, itineraryNo, noticeShipType,
					orderByComparator, true);

			array[1] = vmaScheduleTransfer;

			array[2] = getByitineraryNo_noticeShipType_PrevAndNext(
					vmaScheduleTransfer, itineraryNo, noticeShipType,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTransfer getByitineraryNo_noticeShipType_PrevAndNext(
		 VmaScheduleTransfer vmaScheduleTransfer,
		String itineraryNo, int noticeShipType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

		if (itineraryNo == null) {
			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
		}
		else {
			if (itineraryNo.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
			}
			else {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
			}
		}

		query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

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

		else {
			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (itineraryNo != null) {
			builder.appendNamedParameterMap("itineraryNo", itineraryNo);
		}

		builder.appendNamedParameterMap("noticeShipType", noticeShipType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTransfer);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTransfer> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule transfer where itineraryNo = &#63; and debitnoteid = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByitineraryNo_debitnoteid(itineraryNo,
				debitnoteid);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryNo=");
			msg.append(itineraryNo);

			msg.append(", debitnoteid=");
			msg.append(debitnoteid);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where itineraryNo = &#63; and debitnoteid = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid) throws SystemException {
		return fetchByitineraryNo_debitnoteid(itineraryNo, debitnoteid, true);
	}

	/**
	 * Returns the vma schedule transfer where itineraryNo = &#63; and debitnoteid = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2);

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns all the vma schedule transfers where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @return the matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByF_debitnoteid(int debitnoteid)
		throws SystemException {
		return findByF_debitnoteid(debitnoteid, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule transfers where debitnoteid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param debitnoteid the debitnoteid
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @return the range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByF_debitnoteid(int debitnoteid,
		int start, int end) throws SystemException {
		return findByF_debitnoteid(debitnoteid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule transfers where debitnoteid = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param debitnoteid the debitnoteid
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findByF_debitnoteid(int debitnoteid,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaScheduleTransfer> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

			query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

				list = (List<VmaScheduleTransfer>)queryFactory.getResultList(builder);
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
	 * Returns the first vma schedule transfer in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByF_debitnoteid_First(int debitnoteid,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByF_debitnoteid_First(debitnoteid,
				orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("debitnoteid=");
		msg.append(debitnoteid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the first vma schedule transfer in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByF_debitnoteid_First(int debitnoteid,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTransfer> list = findByF_debitnoteid(debitnoteid, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByF_debitnoteid_Last(int debitnoteid,
		OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByF_debitnoteid_Last(debitnoteid,
				orderByComparator);

		if (vmaScheduleTransfer != null) {
			return vmaScheduleTransfer;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("debitnoteid=");
		msg.append(debitnoteid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaScheduleTransferException(msg.toString());
	}

	/**
	 * Returns the last vma schedule transfer in the ordered set where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByF_debitnoteid_Last(int debitnoteid,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_debitnoteid(debitnoteid);

		List<VmaScheduleTransfer> list = findByF_debitnoteid(debitnoteid,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma schedule transfers before and after the current vma schedule transfer in the ordered set where debitnoteid = &#63;.
	 *
	 * @param id the primary key of the current vma schedule transfer
	 * @param debitnoteid the debitnoteid
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a vma schedule transfer with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer[] findByF_debitnoteid_PrevAndNext(long id,
		int debitnoteid, OrderByComparator orderByComparator)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByPrimaryKey(id);

		

		try {
			

			VmaScheduleTransfer[] array = new VmaScheduleTransfer[3];

			array[0] = getByF_debitnoteid_PrevAndNext(
					vmaScheduleTransfer, debitnoteid, orderByComparator, true);

			array[1] = vmaScheduleTransfer;

			array[2] = getByF_debitnoteid_PrevAndNext(
					vmaScheduleTransfer, debitnoteid, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaScheduleTransfer getByF_debitnoteid_PrevAndNext(
		 VmaScheduleTransfer vmaScheduleTransfer,
		int debitnoteid, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

		query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

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

		else {
			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("debitnoteid", debitnoteid);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaScheduleTransfer);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaScheduleTransfer> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma schedule transfer where itineraryScheduleId = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException} if it could not be found.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule transfer
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaScheduleTransferException if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer findByF_ItineraryScheduleId(
		long itineraryScheduleId)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = fetchByF_ItineraryScheduleId(itineraryScheduleId);

		if (vmaScheduleTransfer == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("itineraryScheduleId=");
			msg.append(itineraryScheduleId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaScheduleTransferException(msg.toString());
		}

		return vmaScheduleTransfer;
	}

	/**
	 * Returns the vma schedule transfer where itineraryScheduleId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByF_ItineraryScheduleId(
		long itineraryScheduleId) throws SystemException {
		return fetchByF_ItineraryScheduleId(itineraryScheduleId, true);
	}

	/**
	 * Returns the vma schedule transfer where itineraryScheduleId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma schedule transfer, or <code>null</code> if a matching vma schedule transfer could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer fetchByF_ItineraryScheduleId(
		long itineraryScheduleId, boolean retrieveFromCache)
		throws SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = null;
		if (vmaScheduleTransfer == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMASCHEDULETRANSFER_WHERE);

			query.append(_FINDER_COLUMN_F_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			query.append(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaScheduleTransfer.class).build();

				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

				vmaScheduleTransfer = (VmaScheduleTransfer) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaScheduleTransfer;
	}

	/**
	 * Returns all the vma schedule transfers.
	 *
	 * @return the vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma schedule transfers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @return the range of vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma schedule transfers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma schedule transfers
	 * @param end the upper bound of the range of vma schedule transfers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaScheduleTransfer> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaScheduleTransfer> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMASCHEDULETRANSFER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMASCHEDULETRANSFER.concat(VmaScheduleTransferModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaScheduleTransfer>) queryFactory.getResultList(builder);
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
	 * Removes the vma schedule transfer where imoNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByimoNumber(String imoNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByimoNumber(imoNumber);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where callSign = &#63; from the database.
	 *
	 * @param callSign the call sign
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeBycallSign(String callSign)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findBycallSign(callSign);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where vrCode = &#63; from the database.
	 *
	 * @param vrCode the vr code
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByvrCode(String vrCode)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByvrCode(vrCode);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where registryNumber = &#63; from the database.
	 *
	 * @param registryNumber the registry number
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByregistryNumber(String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByregistryNumber(registryNumber);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where imoNumber = &#63; and callSign = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByimoNumber_callSign(String imoNumber,
		String callSign)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByimoNumber_callSign(imoNumber,
				callSign);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where imoNumber = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByvrCode_registryNumber(String imoNumber,
		String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByvrCode_registryNumber(imoNumber,
				registryNumber);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes the vma schedule transfer where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63; from the database.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByimoNumber_callSign_registryNumber(
		String imoNumber, String callSign, String registryNumber)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByimoNumber_callSign_registryNumber(imoNumber,
				callSign, registryNumber);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes all the vma schedule transfers where itineraryNo = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo(String itineraryNo)
		throws SystemException {
		for (VmaScheduleTransfer vmaScheduleTransfer : findByitineraryNo(
				itineraryNo)) {
			repository.delete(vmaScheduleTransfer);
		}
	}

	/**
	 * Removes all the vma schedule transfers where itineraryNo = &#63; and noticeShipType = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		for (VmaScheduleTransfer vmaScheduleTransfer : findByitineraryNo_noticeShipType(
				itineraryNo, noticeShipType)) {
			repository.delete(vmaScheduleTransfer);
		}
	}

	/**
	 * Removes the vma schedule transfer where itineraryNo = &#63; and debitnoteid = &#63; from the database.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByitineraryNo_debitnoteid(
		String itineraryNo, int debitnoteid)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByitineraryNo_debitnoteid(itineraryNo,
				debitnoteid);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes all the vma schedule transfers where debitnoteid = &#63; from the database.
	 *
	 * @param debitnoteid the debitnoteid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_debitnoteid(int debitnoteid)
		throws SystemException {
		for (VmaScheduleTransfer vmaScheduleTransfer : findByF_debitnoteid(
				debitnoteid)) {
			repository.delete(vmaScheduleTransfer);
		}
	}

	/**
	 * Removes the vma schedule transfer where itineraryScheduleId = &#63; from the database.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the vma schedule transfer that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaScheduleTransfer removeByF_ItineraryScheduleId(
		long itineraryScheduleId)
		throws NoSuchVmaScheduleTransferException, SystemException {
		VmaScheduleTransfer vmaScheduleTransfer = findByF_ItineraryScheduleId(itineraryScheduleId);

		repository.delete(vmaScheduleTransfer);
			return vmaScheduleTransfer;
	}

	/**
	 * Removes all the vma schedule transfers from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaScheduleTransfer vmaScheduleTransfer : findAll()) {
			repository.delete(vmaScheduleTransfer);
		}
	}

	/**
	 * Returns the number of vma schedule transfers where imoNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber(String imoNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where callSign = &#63;.
	 *
	 * @param callSign the call sign
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countBycallSign(String callSign) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

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
	 * Returns the number of vma schedule transfers where vrCode = &#63;.
	 *
	 * @param vrCode the vr code
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode(String vrCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where registryNumber = &#63;.
	 *
	 * @param registryNumber the registry number
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByregistryNumber(String registryNumber)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where imoNumber = &#63; and callSign = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign(String imoNumber, String callSign)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where imoNumber = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param registryNumber the registry number
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByvrCode_registryNumber(String imoNumber,
		String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where imoNumber = &#63; and callSign = &#63; and registryNumber = &#63;.
	 *
	 * @param imoNumber the imo number
	 * @param callSign the call sign
	 * @param registryNumber the registry number
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByimoNumber_callSign_registryNumber(String imoNumber,
		String callSign, String registryNumber) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

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
	 * Returns the number of vma schedule transfers where itineraryNo = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo(String itineraryNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
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
	 * Returns the number of vma schedule transfers where itineraryNo = &#63; and noticeShipType = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param noticeShipType the notice ship type
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_noticeShipType(String itineraryNo,
		int noticeShipType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				

				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("noticeShipType", noticeShipType);

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
	 * Returns the number of vma schedule transfers where itineraryNo = &#63; and debitnoteid = &#63;.
	 *
	 * @param itineraryNo the itinerary no
	 * @param debitnoteid the debitnoteid
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByitineraryNo_debitnoteid(String itineraryNo,
		int debitnoteid) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

			if (itineraryNo == null) {
				query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1);
			}
			else {
				if (itineraryNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3);
				}
				else {
					query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2);
				}
			}

			query.append(_FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();
				if (itineraryNo != null) {
					builder.appendNamedParameterMap("itineraryNo", itineraryNo);
				}

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

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
	 * Returns the number of vma schedule transfers where debitnoteid = &#63;.
	 *
	 * @param debitnoteid the debitnoteid
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_debitnoteid(int debitnoteid) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

			query.append(_FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("debitnoteid", debitnoteid);

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
	 * Returns the number of vma schedule transfers where itineraryScheduleId = &#63;.
	 *
	 * @param itineraryScheduleId the itinerary schedule ID
	 * @return the number of matching vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_ItineraryScheduleId(long itineraryScheduleId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMASCHEDULETRANSFER_WHERE);

			query.append(_FINDER_COLUMN_F_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2);

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("itineraryScheduleId", itineraryScheduleId);

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
	 * Returns the number of vma schedule transfers.
	 *
	 * @return the number of vma schedule transfers
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMASCHEDULETRANSFER).build();

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
	 * Initializes the vma schedule transfer persistence.
	 */
	private static final String _SQL_SELECT_VMASCHEDULETRANSFER = "SELECT vmaScheduleTransfer FROM VmaScheduleTransfer vmaScheduleTransfer";
	private static final String _SQL_SELECT_VMASCHEDULETRANSFER_WHERE = "SELECT vmaScheduleTransfer FROM VmaScheduleTransfer vmaScheduleTransfer WHERE ";
	private static final String _SQL_COUNT_VMASCHEDULETRANSFER = "SELECT COUNT(vmaScheduleTransfer) FROM VmaScheduleTransfer vmaScheduleTransfer";
	private static final String _SQL_COUNT_VMASCHEDULETRANSFER_WHERE = "SELECT COUNT(vmaScheduleTransfer) FROM VmaScheduleTransfer vmaScheduleTransfer WHERE ";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_1 = "vmaScheduleTransfer.imoNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_2 = "vmaScheduleTransfer.imoNumber =:imoNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_IMONUMBER_3 = "(vmaScheduleTransfer.imoNumber IS NULL OR vmaScheduleTransfer.imoNumber =:imoNumber)";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_1 = "vmaScheduleTransfer.callSign IS NULL";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_2 = "vmaScheduleTransfer.callSign =:callSign";
	private static final String _FINDER_COLUMN_CALLSIGN_CALLSIGN_3 = "(vmaScheduleTransfer.callSign IS NULL OR vmaScheduleTransfer.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_1 = "vmaScheduleTransfer.vrCode IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_2 = "vmaScheduleTransfer.vrCode =:vrCode";
	private static final String _FINDER_COLUMN_VRCODE_VRCODE_3 = "(vmaScheduleTransfer.vrCode IS NULL OR vmaScheduleTransfer.vrCode =:vrCode)";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_1 = "vmaScheduleTransfer.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_2 = "vmaScheduleTransfer.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_REGISTRYNUMBER_REGISTRYNUMBER_3 = "(vmaScheduleTransfer.registryNumber IS NULL OR vmaScheduleTransfer.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_1 = "vmaScheduleTransfer.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_2 = "vmaScheduleTransfer.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_IMONUMBER_3 = "(vmaScheduleTransfer.imoNumber IS NULL OR vmaScheduleTransfer.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_1 = "vmaScheduleTransfer.callSign IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_2 = "vmaScheduleTransfer.callSign =:callSign";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_CALLSIGN_3 = "(vmaScheduleTransfer.callSign IS NULL OR vmaScheduleTransfer.callSign =:callSign)";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaScheduleTransfer.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaScheduleTransfer.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaScheduleTransfer.imoNumber IS NULL OR vmaScheduleTransfer.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaScheduleTransfer.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaScheduleTransfer.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_VRCODE_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaScheduleTransfer.registryNumber IS NULL OR vmaScheduleTransfer.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_1 =
		"vmaScheduleTransfer.imoNumber IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_2 =
		"vmaScheduleTransfer.imoNumber =:imoNumber AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_IMONUMBER_3 =
		"(vmaScheduleTransfer.imoNumber IS NULL OR vmaScheduleTransfer.imoNumber =:imoNumber) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_1 =
		"vmaScheduleTransfer.callSign IS NULL AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_2 =
		"vmaScheduleTransfer.callSign =:callSign AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_CALLSIGN_3 =
		"(vmaScheduleTransfer.callSign IS NULL OR vmaScheduleTransfer.callSign =:callSign) AND ";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_1 =
		"vmaScheduleTransfer.registryNumber IS NULL";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_2 =
		"vmaScheduleTransfer.registryNumber =:registryNumber";
	private static final String _FINDER_COLUMN_IMONUMBER_CALLSIGN_REGISTRYNUMBER_REGISTRYNUMBER_3 =
		"(vmaScheduleTransfer.registryNumber IS NULL OR vmaScheduleTransfer.registryNumber =:registryNumber)";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_1 = "vmaScheduleTransfer.itineraryNo IS NULL";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_2 = "vmaScheduleTransfer.itineraryNo =:itineraryNo";
	private static final String _FINDER_COLUMN_ITINERARYNO_ITINERARYNO_3 = "(vmaScheduleTransfer.itineraryNo IS NULL OR vmaScheduleTransfer.itineraryNo =:itineraryNo)";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_1 =
		"vmaScheduleTransfer.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_2 =
		"vmaScheduleTransfer.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_ITINERARYNO_3 =
		"(vmaScheduleTransfer.itineraryNo IS NULL OR vmaScheduleTransfer.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_NOTICESHIPTYPE_NOTICESHIPTYPE_2 =
		"vmaScheduleTransfer.noticeShipType =:noticeShipType";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_1 =
		"vmaScheduleTransfer.itineraryNo IS NULL AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_2 =
		"vmaScheduleTransfer.itineraryNo =:itineraryNo AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_ITINERARYNO_3 =
		"(vmaScheduleTransfer.itineraryNo IS NULL OR vmaScheduleTransfer.itineraryNo =:itineraryNo) AND ";
	private static final String _FINDER_COLUMN_ITINERARYNO_DEBITNOTEID_DEBITNOTEID_2 =
		"vmaScheduleTransfer.debitnoteid =:debitnoteid";
	private static final String _FINDER_COLUMN_F_DEBITNOTEID_DEBITNOTEID_2 = "vmaScheduleTransfer.debitnoteid =:debitnoteid";
	private static final String _FINDER_COLUMN_F_ITINERARYSCHEDULEID_ITINERARYSCHEDULEID_2 =
		"vmaScheduleTransfer.itineraryScheduleId =:itineraryScheduleId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaScheduleTransfer.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaScheduleTransfer exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaScheduleTransfer exists with the key {";
	

	
}
