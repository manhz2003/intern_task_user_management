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
import com.fds.nsw.nghiepvu.model.DmDocType;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmDocTypeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmDocTypeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmDocTypePersistenceImpl extends BasePersistence {
	@Autowired
	DmDocTypeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmDocType> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmDocTypeUtil} to access the dm doc type persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmDocType create(int id) {
		DmDocType dmDocType = new DmDocType();

		
		//dmDocType.setPrimaryKey(id);

		return dmDocType;
	}

	/**
	 * Removes the dm doc type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm doc type
	 * @return the dm doc type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType remove(int id)
		throws NoSuchDmDocTypeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm doc type with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm doc type
	 * @return the dm doc type that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmDocType remove(Serializable primaryKey)
		throws NoSuchDmDocTypeException, SystemException {
		

		try {
			

			DmDocType dmDocType = findByPrimaryKey(primaryKey);

			if (dmDocType == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmDocTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmDocType);
			return dmDocType;
		}
		catch (NoSuchDmDocTypeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmDocType remove(DmDocType DmDocType) throws SystemException {
	removeImpl(DmDocType);	return DmDocType;
}

protected DmDocType removeImpl

(DmDocType dmDocType)
		throws SystemException {
		try {
			repository.delete(dmDocType);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmDocType;
	}

	
	public DmDocType updateImpl(DmDocType dmDocType,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmDocType);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmDocType;
	}

	
	public DmDocType findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm doc type with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmDocTypeException} if it could not be found.
	 *
	 * @param id the primary key of the dm doc type
	 * @return the dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByPrimaryKey(int id)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByPrimaryKey(id);

		if (dmDocType == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmDocTypeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmDocType;
	}

	/**
	 * Returns the dm doc type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm doc type
	 * @return the dm doc type, or <code>null</code> if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmDocType fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm doc type with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm doc type
	 * @return the dm doc type, or <code>null</code> if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByPrimaryKey(int id) throws SystemException {
		DmDocType dmDocType = null;

		

		if (dmDocType == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmDocType> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmDocType = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmDocType;
	}

	/**
	 * Returns all the dm doc types where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @return the matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByDocumentTypeCode(String documentTypeCode)
		throws SystemException {
		return findByDocumentTypeCode(documentTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm doc types where documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @return the range of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByDocumentTypeCode(String documentTypeCode,
		int start, int end) throws SystemException {
		return findByDocumentTypeCode(documentTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm doc types where documentTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeCode the document type code
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByDocumentTypeCode(String documentTypeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmDocType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMDOCTYPE_WHERE);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmDocTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
				}

				list = (List<DmDocType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm doc type in the ordered set where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByDocumentTypeCode_First(String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByDocumentTypeCode_First(documentTypeCode,
				orderByComparator);

		if (dmDocType != null) {
			return dmDocType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDocTypeException(msg.toString());
	}

	/**
	 * Returns the first dm doc type in the ordered set where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByDocumentTypeCode_First(String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmDocType> list = findByDocumentTypeCode(documentTypeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm doc type in the ordered set where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByDocumentTypeCode_Last(String documentTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByDocumentTypeCode_Last(documentTypeCode,
				orderByComparator);

		if (dmDocType != null) {
			return dmDocType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeCode=");
		msg.append(documentTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDocTypeException(msg.toString());
	}

	/**
	 * Returns the last dm doc type in the ordered set where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByDocumentTypeCode_Last(String documentTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentTypeCode(documentTypeCode);

		List<DmDocType> list = findByDocumentTypeCode(documentTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm doc types before and after the current dm doc type in the ordered set where documentTypeCode = &#63;.
	 *
	 * @param id the primary key of the current dm doc type
	 * @param documentTypeCode the document type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType[] findByDocumentTypeCode_PrevAndNext(int id,
		String documentTypeCode, OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = findByPrimaryKey(id);

		

		try {
			

			DmDocType[] array = new DmDocType[3];

			array[0] = getByDocumentTypeCode_PrevAndNext(dmDocType,
					documentTypeCode, orderByComparator, true);

			array[1] = dmDocType;

			array[2] = getByDocumentTypeCode_PrevAndNext(dmDocType,
					documentTypeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmDocType getByDocumentTypeCode_PrevAndNext(
		DmDocType dmDocType, String documentTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDOCTYPE_WHERE);

		if (documentTypeCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
		}
		else {
			if (documentTypeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
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
			query.append(DmDocTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (documentTypeCode != null) {
			builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDocType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmDocType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm doc types where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @return the matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByF_documentTypeName(String documentTypeName)
		throws SystemException {
		return findByF_documentTypeName(documentTypeName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm doc types where documentTypeName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeName the document type name
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @return the range of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByF_documentTypeName(String documentTypeName,
		int start, int end) throws SystemException {
		return findByF_documentTypeName(documentTypeName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm doc types where documentTypeName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentTypeName the document type name
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findByF_documentTypeName(String documentTypeName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmDocType> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMDOCTYPE_WHERE);

			if (documentTypeName == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_1);
			}
			else {
				if (documentTypeName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmDocTypeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (documentTypeName != null) {
					builder.appendNamedParameterMap("documentTypeName", documentTypeName);
				}

				list = (List<DmDocType>)queryFactory.getResultList(builder);
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
	 * Returns the first dm doc type in the ordered set where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByF_documentTypeName_First(String documentTypeName,
		OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByF_documentTypeName_First(documentTypeName,
				orderByComparator);

		if (dmDocType != null) {
			return dmDocType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeName=");
		msg.append(documentTypeName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDocTypeException(msg.toString());
	}

	/**
	 * Returns the first dm doc type in the ordered set where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByF_documentTypeName_First(String documentTypeName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmDocType> list = findByF_documentTypeName(documentTypeName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm doc type in the ordered set where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByF_documentTypeName_Last(String documentTypeName,
		OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByF_documentTypeName_Last(documentTypeName,
				orderByComparator);

		if (dmDocType != null) {
			return dmDocType;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentTypeName=");
		msg.append(documentTypeName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmDocTypeException(msg.toString());
	}

	/**
	 * Returns the last dm doc type in the ordered set where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByF_documentTypeName_Last(String documentTypeName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_documentTypeName(documentTypeName);

		List<DmDocType> list = findByF_documentTypeName(documentTypeName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm doc types before and after the current dm doc type in the ordered set where documentTypeName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm doc type
	 * @param documentTypeName the document type name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a dm doc type with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType[] findByF_documentTypeName_PrevAndNext(int id,
		String documentTypeName, OrderByComparator orderByComparator)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = findByPrimaryKey(id);

		

		try {
			

			DmDocType[] array = new DmDocType[3];

			array[0] = getByF_documentTypeName_PrevAndNext(dmDocType,
					documentTypeName, orderByComparator, true);

			array[1] = dmDocType;

			array[2] = getByF_documentTypeName_PrevAndNext(dmDocType,
					documentTypeName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmDocType getByF_documentTypeName_PrevAndNext(
		DmDocType dmDocType, String documentTypeName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMDOCTYPE_WHERE);

		if (documentTypeName == null) {
			query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_1);
		}
		else {
			if (documentTypeName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_2);
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
			query.append(DmDocTypeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (documentTypeName != null) {
			builder.appendNamedParameterMap("documentTypeName", documentTypeName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmDocType);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmDocType> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm doc type where documentType = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmDocTypeException} if it could not be found.
	 *
	 * @param documentType the document type
	 * @return the matching dm doc type
	 * @throws vn.gt.dao.danhmuc.NoSuchDmDocTypeException if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType findByF_documentType(String documentType)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = fetchByF_documentType(documentType);

		if (dmDocType == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentType=");
			msg.append(documentType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmDocTypeException(msg.toString());
		}

		return dmDocType;
	}

	/**
	 * Returns the dm doc type where documentType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentType the document type
	 * @return the matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByF_documentType(String documentType)
		throws SystemException {
		return fetchByF_documentType(documentType, true);
	}

	/**
	 * Returns the dm doc type where documentType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentType the document type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm doc type, or <code>null</code> if a matching dm doc type could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType fetchByF_documentType(String documentType,
		boolean retrieveFromCache) throws SystemException {
		DmDocType dmDocType = null;
		if (dmDocType == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_2);
				}
			}

			query.append(DmDocTypeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmDocType.class).build();

				

				if (documentType != null) {
					builder.appendNamedParameterMap("documentType", documentType);
				}

				dmDocType = (DmDocType) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmDocType;
	}

	/**
	 * Returns all the dm doc types.
	 *
	 * @return the dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm doc types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @return the range of dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm doc types.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm doc types
	 * @param end the upper bound of the range of dm doc types (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmDocType> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmDocType> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMDOCTYPE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMDOCTYPE.concat(DmDocTypeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmDocType>) queryFactory.getResultList(builder);
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
	 * Removes all the dm doc types where documentTypeCode = &#63; from the database.
	 *
	 * @param documentTypeCode the document type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentTypeCode(String documentTypeCode)
		throws SystemException {
		for (DmDocType dmDocType : findByDocumentTypeCode(documentTypeCode)) {
			repository.delete(dmDocType);
		}
	}

	/**
	 * Removes all the dm doc types where documentTypeName LIKE &#63; from the database.
	 *
	 * @param documentTypeName the document type name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_documentTypeName(String documentTypeName)
		throws SystemException {
		for (DmDocType dmDocType : findByF_documentTypeName(documentTypeName)) {
			repository.delete(dmDocType);
		}
	}

	/**
	 * Removes the dm doc type where documentType = &#63; from the database.
	 *
	 * @param documentType the document type
	 * @return the dm doc type that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmDocType removeByF_documentType(String documentType)
		throws NoSuchDmDocTypeException, SystemException {
		DmDocType dmDocType = findByF_documentType(documentType);

		repository.delete(dmDocType);
			return dmDocType;
	}

	/**
	 * Removes all the dm doc types from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmDocType dmDocType : findAll()) {
			repository.delete(dmDocType);
		}
	}

	/**
	 * Returns the number of dm doc types where documentTypeCode = &#63;.
	 *
	 * @param documentTypeCode the document type code
	 * @return the number of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentTypeCode(String documentTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMDOCTYPE_WHERE);

			if (documentTypeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_1);
			}
			else {
				if (documentTypeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (documentTypeCode != null) {
					builder.appendNamedParameterMap("documentTypeCode", documentTypeCode);
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
	 * Returns the number of dm doc types where documentTypeName LIKE &#63;.
	 *
	 * @param documentTypeName the document type name
	 * @return the number of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_documentTypeName(String documentTypeName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMDOCTYPE_WHERE);

			if (documentTypeName == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_1);
			}
			else {
				if (documentTypeName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (documentTypeName != null) {
					builder.appendNamedParameterMap("documentTypeName", documentTypeName);
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
	 * Returns the number of dm doc types where documentType = &#63;.
	 *
	 * @param documentType the document type
	 * @return the number of matching dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_documentType(String documentType)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMDOCTYPE_WHERE);

			if (documentType == null) {
				query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_1);
			}
			else {
				if (documentType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_2);
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
	 * Returns the number of dm doc types.
	 *
	 * @return the number of dm doc types
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMDOCTYPE).build();

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
	 * Initializes the dm doc type persistence.
	 */
	private static final String _SQL_SELECT_DMDOCTYPE = "SELECT dmDocType FROM DmDocType dmDocType";
	private static final String _SQL_SELECT_DMDOCTYPE_WHERE = "SELECT dmDocType FROM DmDocType dmDocType WHERE ";
	private static final String _SQL_COUNT_DMDOCTYPE = "SELECT COUNT(dmDocType) FROM DmDocType dmDocType";
	private static final String _SQL_COUNT_DMDOCTYPE_WHERE = "SELECT COUNT(dmDocType) FROM DmDocType dmDocType WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_1 =
		"dmDocType.documentTypeCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_2 =
		"dmDocType.documentTypeCode =:documentTypeCode";
	private static final String _FINDER_COLUMN_DOCUMENTTYPECODE_DOCUMENTTYPECODE_3 =
		"(dmDocType.documentTypeCode IS NULL OR dmDocType.documentTypeCode =:documentTypeCode)";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_1 =
		"dmDocType.documentTypeName LIKE NULL";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_2 =
		"dmDocType.documentTypeName LIKE :documentTypeName";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPENAME_DOCUMENTTYPENAME_3 =
		"(dmDocType.documentTypeName IS NULL OR dmDocType.documentTypeName LIKE :documentTypeName)";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_1 = "dmDocType.documentType IS NULL";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_2 = "dmDocType.documentType =:documentType";
	private static final String _FINDER_COLUMN_F_DOCUMENTTYPE_DOCUMENTTYPE_3 = "(dmDocType.documentType IS NULL OR dmDocType.documentType =:documentType)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmDocType.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmDocType exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmDocType exists with the key {";
	

	
}
