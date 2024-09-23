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
import com.fds.nsw.nghiepvu.model.DmHistoryDocType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryDocTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryDocTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryDocTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryDocTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryDocType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryDocTypeUtil} to access the dm history doc type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryDocType create(int id) {
		DmHistoryDocType dmHistoryDocType = new DmHistoryDocType();

		
		//dmHistoryDocType.setPrimaryKey(id);

		return dmHistoryDocType;
	}

	/**
	 * Removes the dm history doc type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history doc type
	 * @return the dm history doc type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException if a dm history doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType remove(int id)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history doc type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history doc type
	 * @return the dm history doc type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException if a dm history doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryDocType remove(Serializable primaryKey)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		

		try {
			

			DmHistoryDocType dmHistoryDocType = findByPrimaryKey(primaryKey);

			if (dmHistoryDocType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryDocTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryDocType);
			return dmHistoryDocType;
		}
		catch (NoSuchDmHistoryDocTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryDocType remove(DmHistoryDocType DmHistoryDocType) throws SystemException {
	removeImpl(DmHistoryDocType);	return DmHistoryDocType;
}

protected DmHistoryDocType removeImpl

(DmHistoryDocType dmHistoryDocType)
		throws SystemException {
		try {
			repository.delete(dmHistoryDocType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryDocType;
	}

	
	public DmHistoryDocType updateImpl(
		DmHistoryDocType dmHistoryDocType, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryDocType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryDocType;
	}

	
	public DmHistoryDocType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history doc type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history doc type
	 * @return the dm history doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException if a dm history doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType findByPrimaryKey(int id)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		DmHistoryDocType dmHistoryDocType = fetchByPrimaryKey(id);

		if (dmHistoryDocType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryDocTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryDocType;
	}

	/**
	 * Returns the dm history doc type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history doc type
	 * @return the dm history doc type, or <code>null</code> if a dm history doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryDocType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history doc type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history doc type
	 * @return the dm history doc type, or <code>null</code> if a dm history doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType fetchByPrimaryKey(int id) throws SystemException {
		DmHistoryDocType dmHistoryDocType = null;

		

		if (dmHistoryDocType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryDocType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryDocType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryDocType;
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException} if it could not be found.
	 *
	 * @param documentType the document type
	 * @return the matching dm history doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType findByDocumentType(String documentType)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		DmHistoryDocType dmHistoryDocType = fetchByDocumentType(documentType);

		if (dmHistoryDocType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentType=");
			msg.append(documentType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryDocTypeException(msg.toString());
		}

		return dmHistoryDocType;
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentType the document type
	 * @return the matching dm history doc type, or <code>null</code> if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType fetchByDocumentType(String documentType)
		throws SystemException {
		return fetchByDocumentType(documentType, true);
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentType the document type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history doc type, or <code>null</code> if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType fetchByDocumentType(String documentType,
		boolean retrieveFromCache) throws SystemException {
		DmHistoryDocType dmHistoryDocType = null;
		if (dmHistoryDocType == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMHISTORYDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_2);
				}
			}

			query.append(DmHistoryDocTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryDocType.class).build();

				

				if (documentType != null) {
					builder.appendNamedParameterMap("documentType", documentType);
				}

				dmHistoryDocType = (DmHistoryDocType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryDocType;
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException} if it could not be found.
	 *
	 * @param documentType the document type
	 * @param syncVersion the sync version
	 * @return the matching dm history doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryDocTypeException if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType findByDocumentTypeAndSyncVersion(
		String documentType, String syncVersion)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		DmHistoryDocType dmHistoryDocType = fetchByDocumentTypeAndSyncVersion(documentType,
				syncVersion);

		if (dmHistoryDocType == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentType=");
			msg.append(documentType);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryDocTypeException(msg.toString());
		}

		return dmHistoryDocType;
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentType the document type
	 * @param syncVersion the sync version
	 * @return the matching dm history doc type, or <code>null</code> if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType fetchByDocumentTypeAndSyncVersion(
		String documentType, String syncVersion) throws SystemException {
		return fetchByDocumentTypeAndSyncVersion(documentType, syncVersion, true);
	}

	/**
	 * Returns the dm history doc type where documentType = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentType the document type
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history doc type, or <code>null</code> if a matching dm history doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType fetchByDocumentTypeAndSyncVersion(
		String documentType, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryDocType dmHistoryDocType = null;
		if (dmHistoryDocType == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryDocTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryDocType.class).build();

				

				if (documentType != null) {
					builder.appendNamedParameterMap("documentType", documentType);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryDocType = (DmHistoryDocType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryDocType;
	}

	/**
	 * Returns all the dm history doc types.
	 *
	 * @return the dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryDocType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history doc types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history doc types
	 * @param end the upper bound of the range of dm history doc types (not inclusive)
	 * @return the range of dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryDocType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history doc types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history doc types
	 * @param end the upper bound of the range of dm history doc types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryDocType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryDocType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYDOCTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYDOCTYPE.concat(DmHistoryDocTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryDocType>) queryFactory.getResultList(builder);
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
	 * Removes the dm history doc type where documentType = &#63; from the database.
	 *
	 * @param documentType the document type
	 * @return the dm history doc type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType removeByDocumentType(String documentType)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		DmHistoryDocType dmHistoryDocType = findByDocumentType(documentType);

		repository.delete(dmHistoryDocType);
			return dmHistoryDocType;
	}

	/**
	 * Removes the dm history doc type where documentType = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param documentType the document type
	 * @param syncVersion the sync version
	 * @return the dm history doc type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryDocType removeByDocumentTypeAndSyncVersion(
		String documentType, String syncVersion)
		throws NoSuchDmHistoryDocTypeException, SystemException {
		DmHistoryDocType dmHistoryDocType = findByDocumentTypeAndSyncVersion(documentType,
				syncVersion);

		repository.delete(dmHistoryDocType);
			return dmHistoryDocType;
	}

	/**
	 * Removes all the dm history doc types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryDocType dmHistoryDocType : findAll()) {
			repository.delete(dmHistoryDocType);
		}
	}

	/**
	 * Returns the number of dm history doc types where documentType = &#63;.
	 *
	 * @param documentType the document type
	 * @return the number of matching dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentType(String documentType)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (documentType != null) {
					builder.appendNamedParameterMap("documentType", documentType);
				}

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
	 * Returns the number of dm history doc types where documentType = &#63; and syncVersion = &#63;.
	 *
	 * @param documentType the document type
	 * @param syncVersion the sync version
	 * @return the number of matching dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentTypeAndSyncVersion(String documentType,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (documentType != null) {
					builder.appendNamedParameterMap("documentType", documentType);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

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
	 * Returns the number of dm history doc types.
	 *
	 * @return the number of dm history doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYDOCTYPE).build();

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
	 * Initializes the dm history doc type persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYDOCTYPE = "SELECT dmHistoryDocType FROM DmHistoryDocType dmHistoryDocType";
	private static final String _SQL_SELECT_DMHISTORYDOCTYPE_WHERE = "SELECT dmHistoryDocType FROM DmHistoryDocType dmHistoryDocType WHERE ";
	private static final String _SQL_COUNT_DMHISTORYDOCTYPE = "SELECT COUNT(dmHistoryDocType) FROM DmHistoryDocType dmHistoryDocType";
	private static final String _SQL_COUNT_DMHISTORYDOCTYPE_WHERE = "SELECT COUNT(dmHistoryDocType) FROM DmHistoryDocType dmHistoryDocType WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_1 = "dmHistoryDocType.documentType IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_2 = "dmHistoryDocType.documentType =:documentType";
	private static final String _FINDER_COLUMN_DOCUMENTTYPE_DOCUMENTTYPE_3 = "(dmHistoryDocType.documentType IS NULL OR dmHistoryDocType.documentType =:documentType)";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_1 =
		"dmHistoryDocType.documentType IS NULL AND ";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_2 =
		"dmHistoryDocType.documentType =:documentType AND ";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_DOCUMENTTYPE_3 =
		"(dmHistoryDocType.documentType IS NULL OR dmHistoryDocType.documentType =:documentType) AND ";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryDocType.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryDocType.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_DOCUMENTTYPEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryDocType.syncVersion IS NULL OR dmHistoryDocType.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryDocType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryDocType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryDocType exists with the key {";
	

	
}
