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
import com.fds.nsw.nghiepvu.model.VmaTransactionConversion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionConversionRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionConversionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionConversionPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionConversionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionConversion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionConversionUtil} to access the vma transaction conversion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionConversion create(long id) {
		VmaTransactionConversion vmaTransactionConversion = new VmaTransactionConversion();

		
		//vmaTransactionConversion.setPrimaryKey(id);

		return vmaTransactionConversion;
	}

	/**
	 * Removes the vma transaction conversion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction conversion
	 * @return the vma transaction conversion that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException if a vma transaction conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion remove(long id)
		throws NoSuchVmaTransactionConversionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction conversion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction conversion
	 * @return the vma transaction conversion that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException if a vma transaction conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionConversion remove(Serializable primaryKey)
		throws NoSuchVmaTransactionConversionException, SystemException {
		

		try {
			

			VmaTransactionConversion vmaTransactionConversion = findByPrimaryKey(primaryKey);

			if (vmaTransactionConversion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionConversion);
			return vmaTransactionConversion;
		}
		catch (NoSuchVmaTransactionConversionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionConversion remove(VmaTransactionConversion VmaTransactionConversion) throws SystemException {
	removeImpl(VmaTransactionConversion);
	return VmaTransactionConversion;
}

protected VmaTransactionConversion removeImpl(
		VmaTransactionConversion vmaTransactionConversion)
		throws SystemException {
		try {
			repository.delete(vmaTransactionConversion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionConversion;
	}

	
	public VmaTransactionConversion updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTransactionConversion vmaTransactionConversion,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionConversion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionConversion;
	}

	
	public VmaTransactionConversion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction conversion with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction conversion
	 * @return the vma transaction conversion
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException if a vma transaction conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion findByPrimaryKey(long id)
		throws NoSuchVmaTransactionConversionException, SystemException {
		VmaTransactionConversion vmaTransactionConversion = fetchByPrimaryKey(id);

		if (vmaTransactionConversion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionConversionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionConversion;
	}

	/**
	 * Returns the vma transaction conversion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction conversion
	 * @return the vma transaction conversion, or <code>null</code> if a vma transaction conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionConversion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction conversion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction conversion
	 * @return the vma transaction conversion, or <code>null</code> if a vma transaction conversion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionConversion vmaTransactionConversion = null;

		

		if (vmaTransactionConversion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionConversion> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionConversion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionConversion;
	}

	/**
	 * Returns the vma transaction conversion where shipTypeMT = &#63; and functionType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException} if it could not be found.
	 *
	 * @param shipTypeMT the ship type m t
	 * @param functionType the function type
	 * @return the matching vma transaction conversion
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion findByshipTypeMT_functionType(
		String shipTypeMT, String functionType)
		throws NoSuchVmaTransactionConversionException, SystemException {
		VmaTransactionConversion vmaTransactionConversion = fetchByshipTypeMT_functionType(shipTypeMT,
				functionType);

		if (vmaTransactionConversion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shipTypeMT=");
			msg.append(shipTypeMT);

			msg.append(", functionType=");
			msg.append(functionType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionConversionException(msg.toString());
		}

		return vmaTransactionConversion;
	}

	/**
	 * Returns the vma transaction conversion where shipTypeMT = &#63; and functionType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shipTypeMT the ship type m t
	 * @param functionType the function type
	 * @return the matching vma transaction conversion, or <code>null</code> if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion fetchByshipTypeMT_functionType(
		String shipTypeMT, String functionType) throws SystemException {
		return fetchByshipTypeMT_functionType(shipTypeMT, functionType, true);
	}

	/**
	 * Returns the vma transaction conversion where shipTypeMT = &#63; and functionType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shipTypeMT the ship type m t
	 * @param functionType the function type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction conversion, or <code>null</code> if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion fetchByshipTypeMT_functionType(
		String shipTypeMT, String functionType, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionConversion vmaTransactionConversion = null;
		if (vmaTransactionConversion == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONCONVERSION_WHERE);

			if (shipTypeMT == null) {
				query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_1);
			}
			else {
				if (shipTypeMT.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_2);
				}
			}

			if (functionType == null) {
				query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_1);
			}
			else {
				if (functionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionConversion.class).build();

				if (shipTypeMT != null) {
					builder.appendNamedParameterMap("shipTypeMT", shipTypeMT);
				}

				if (functionType != null) {
					builder.appendNamedParameterMap("functionType", functionType);
				}

				vmaTransactionConversion = (VmaTransactionConversion) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionConversion;
	}

	/**
	 * Returns the vma transaction conversion where shipTypeCode = &#63; and functionType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException} if it could not be found.
	 *
	 * @param shipTypeCode the ship type code
	 * @param functionType the function type
	 * @return the matching vma transaction conversion
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTransactionConversionException if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion findByshipTypeCode_functionType(
		String shipTypeCode, String functionType)
		throws NoSuchVmaTransactionConversionException, SystemException {
		VmaTransactionConversion vmaTransactionConversion = fetchByshipTypeCode_functionType(shipTypeCode,
				functionType);

		if (vmaTransactionConversion == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("shipTypeCode=");
			msg.append(shipTypeCode);

			msg.append(", functionType=");
			msg.append(functionType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionConversionException(msg.toString());
		}

		return vmaTransactionConversion;
	}

	/**
	 * Returns the vma transaction conversion where shipTypeCode = &#63; and functionType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param shipTypeCode the ship type code
	 * @param functionType the function type
	 * @return the matching vma transaction conversion, or <code>null</code> if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion fetchByshipTypeCode_functionType(
		String shipTypeCode, String functionType) throws SystemException {
		return fetchByshipTypeCode_functionType(shipTypeCode, functionType, true);
	}

	/**
	 * Returns the vma transaction conversion where shipTypeCode = &#63; and functionType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param shipTypeCode the ship type code
	 * @param functionType the function type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction conversion, or <code>null</code> if a matching vma transaction conversion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion fetchByshipTypeCode_functionType(
		String shipTypeCode, String functionType, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionConversion vmaTransactionConversion = null;
		if (vmaTransactionConversion == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONCONVERSION_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_2);
				}
			}

			if (functionType == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_1);
			}
			else {
				if (functionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionConversion.class).build();
				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				if (functionType != null) {
					builder.appendNamedParameterMap("functionType", functionType);
				}

				vmaTransactionConversion = (VmaTransactionConversion) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionConversion;
	}

	/**
	 * Returns all the vma transaction conversions.
	 *
	 * @return the vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionConversion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction conversions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction conversions
	 * @param end the upper bound of the range of vma transaction conversions (not inclusive)
	 * @return the range of vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionConversion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction conversions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction conversions
	 * @param end the upper bound of the range of vma transaction conversions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionConversion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionConversion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONCONVERSION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONCONVERSION;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionConversion>) queryFactory.getResultList(builder);
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
	 * Removes the vma transaction conversion where shipTypeMT = &#63; and functionType = &#63; from the database.
	 *
	 * @param shipTypeMT the ship type m t
	 * @param functionType the function type
	 * @return the vma transaction conversion that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion removeByshipTypeMT_functionType(
		String shipTypeMT, String functionType)
		throws NoSuchVmaTransactionConversionException, SystemException {
		VmaTransactionConversion vmaTransactionConversion = findByshipTypeMT_functionType(shipTypeMT,
				functionType);

		repository.delete(vmaTransactionConversion);
			return vmaTransactionConversion;
	}

	/**
	 * Removes the vma transaction conversion where shipTypeCode = &#63; and functionType = &#63; from the database.
	 *
	 * @param shipTypeCode the ship type code
	 * @param functionType the function type
	 * @return the vma transaction conversion that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionConversion removeByshipTypeCode_functionType(
		String shipTypeCode, String functionType)
		throws NoSuchVmaTransactionConversionException, SystemException {
		VmaTransactionConversion vmaTransactionConversion = findByshipTypeCode_functionType(shipTypeCode,
				functionType);

		repository.delete(vmaTransactionConversion);
			return vmaTransactionConversion;
	}

	/**
	 * Removes all the vma transaction conversions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionConversion vmaTransactionConversion : findAll()) {
			repository.delete(vmaTransactionConversion);
		}
	}

	/**
	 * Returns the number of vma transaction conversions where shipTypeMT = &#63; and functionType = &#63;.
	 *
	 * @param shipTypeMT the ship type m t
	 * @param functionType the function type
	 * @return the number of matching vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipTypeMT_functionType(String shipTypeMT,
		String functionType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONCONVERSION_WHERE);

			if (shipTypeMT == null) {
				query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_1);
			}
			else {
				if (shipTypeMT.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_2);
				}
			}

			if (functionType == null) {
				query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_1);
			}
			else {
				if (functionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (shipTypeMT != null) {
					builder.appendNamedParameterMap("shipTypeMT", shipTypeMT);
				}

				if (functionType != null) {
					builder.appendNamedParameterMap("functionType", functionType);
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
	 * Returns the number of vma transaction conversions where shipTypeCode = &#63; and functionType = &#63;.
	 *
	 * @param shipTypeCode the ship type code
	 * @param functionType the function type
	 * @return the number of matching vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByshipTypeCode_functionType(String shipTypeCode,
		String functionType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_VMATRANSACTIONCONVERSION_WHERE);

			if (shipTypeCode == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_1);
			}
			else {
				if (shipTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_2);
				}
			}

			if (functionType == null) {
				query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_1);
			}
			else {
				if (functionType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (shipTypeCode != null) {
					builder.appendNamedParameterMap("shipTypeCode", shipTypeCode);
				}

				if (functionType != null) {
					builder.appendNamedParameterMap("functionType", functionType);
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
	 * Returns the number of vma transaction conversions.
	 *
	 * @return the number of vma transaction conversions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONCONVERSION).build();

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
	 * Initializes the vma transaction conversion persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONCONVERSION = "SELECT vmaTransactionConversion FROM VmaTransactionConversion vmaTransactionConversion";
	private static final String _SQL_SELECT_VMATRANSACTIONCONVERSION_WHERE = "SELECT vmaTransactionConversion FROM VmaTransactionConversion vmaTransactionConversion WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONCONVERSION = "SELECT COUNT(vmaTransactionConversion) FROM VmaTransactionConversion vmaTransactionConversion";
	private static final String _SQL_COUNT_VMATRANSACTIONCONVERSION_WHERE = "SELECT COUNT(vmaTransactionConversion) FROM VmaTransactionConversion vmaTransactionConversion WHERE ";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_1 =
		"vmaTransactionConversion.shipTypeMT IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_2 =
		"vmaTransactionConversion.shipTypeMT =:shipTypeMT AND ";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_SHIPTYPEMT_3 =
		"(vmaTransactionConversion.shipTypeMT IS NULL OR vmaTransactionConversion.shipTypeMT =:shipTypeMT) AND ";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_1 =
		"vmaTransactionConversion.functionType IS NULL";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_2 =
		"vmaTransactionConversion.functionType =:functionType";
	private static final String _FINDER_COLUMN_SHIPTYPEMT_FUNCTIONTYPE_FUNCTIONTYPE_3 =
		"(vmaTransactionConversion.functionType IS NULL OR vmaTransactionConversion.functionType =:functionType)";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_1 =
		"vmaTransactionConversion.shipTypeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_2 =
		"vmaTransactionConversion.shipTypeCode =:shipTypeCode AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_SHIPTYPECODE_3 =
		"(vmaTransactionConversion.shipTypeCode IS NULL OR vmaTransactionConversion.shipTypeCode =:shipTypeCode) AND ";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_1 =
		"vmaTransactionConversion.functionType IS NULL";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_2 =
		"vmaTransactionConversion.functionType =:functionType";
	private static final String _FINDER_COLUMN_SHIPTYPECODE_FUNCTIONTYPE_FUNCTIONTYPE_3 =
		"(vmaTransactionConversion.functionType IS NULL OR vmaTransactionConversion.functionType =:functionType)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionConversion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionConversion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionConversion exists with the key {";
	

	
}
