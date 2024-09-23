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

package com.fds.nsw.nghiepvu.result.service.persistence;

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
import com.fds.nsw.nghiepvu.model.ResultDeclaration;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultDeclarationRepository;
import com.fds.nsw.nghiepvu.modelImpl.ResultDeclarationModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultDeclarationPersistenceImpl extends BasePersistence {
	@Autowired
	ResultDeclarationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultDeclaration> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultDeclarationUtil} to access the result declaration persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultDeclaration create(long id) {
		ResultDeclaration resultDeclaration = new ResultDeclaration();

		
		//resultDeclaration.setPrimaryKey(id);

		return resultDeclaration;
	}

	/**
	 * Removes the result declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result declaration
	 * @return the result declaration that was removed
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration remove(long id)
		throws NoSuchResultDeclarationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result declaration with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result declaration
	 * @return the result declaration that was removed
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultDeclaration remove(Serializable primaryKey)
		throws NoSuchResultDeclarationException, SystemException {
		

		try {
			

			ResultDeclaration resultDeclaration = findByPrimaryKey(primaryKey);

			if (resultDeclaration == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultDeclarationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		repository.delete(resultDeclaration);
		return resultDeclaration;
		}
		catch (NoSuchResultDeclarationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultDeclaration remove(ResultDeclaration ResultDeclaration) throws SystemException {
	removeImpl(ResultDeclaration);
	return ResultDeclaration;
}

protected ResultDeclaration removeImpl(ResultDeclaration resultDeclaration)
	throws SystemException {
	try {
		repository.delete(resultDeclaration);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultDeclaration;
	}

	
	public ResultDeclaration updateImpl(
		com.fds.nsw.nghiepvu.model.ResultDeclaration resultDeclaration,
	boolean merge) throws SystemException {
	try {
		repository.saveAndFlush(resultDeclaration);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultDeclaration;
	}

	
	public ResultDeclaration findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result declaration with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultDeclarationException} if it could not be found.
	 *
	 * @param id the primary key of the result declaration
	 * @return the result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByPrimaryKey(long id)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByPrimaryKey(id);

		if (resultDeclaration == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultDeclarationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultDeclaration;
	}

	/**
	 * Returns the result declaration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result declaration
	 * @return the result declaration, or <code>null</code> if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultDeclaration fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result declaration with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result declaration
	 * @return the result declaration, or <code>null</code> if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByPrimaryKey(long id)
		throws SystemException {
	ResultDeclaration resultDeclaration = null;



	if (resultDeclaration == null) {


		boolean hasException = false;

		try {


		Optional<ResultDeclaration> optional = repository.findById(id);
if (optional.isPresent()) {
	resultDeclaration = optional.get();				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultDeclaration;
	}

	/**
	 * Returns the result declaration where requestCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultDeclarationException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByRequestCode(String requestCode)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByRequestCode(requestCode);

		if (resultDeclaration == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultDeclarationException(msg.toString());
		}

		return resultDeclaration;
	}

	/**
	 * Returns the result declaration where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the result declaration where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		ResultDeclaration resultDeclaration=null;if(resultDeclaration == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultDeclaration.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				resultDeclaration = (ResultDeclaration) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultDeclaration;
	}

	/**
	 * Returns all the result declarations where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @return the matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByBusinessTypeCode(int businessTypeCode)
		throws SystemException {
		return findByBusinessTypeCode(businessTypeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result declarations where businessTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByBusinessTypeCode(
		int businessTypeCode, int start, int end) throws SystemException {
		return findByBusinessTypeCode(businessTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations where businessTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByBusinessTypeCode(
		int businessTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				list = (List<ResultDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByBusinessTypeCode_First(
		int businessTypeCode, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByBusinessTypeCode_First(businessTypeCode,
				orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByBusinessTypeCode_First(
		int businessTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultDeclaration> list = findByBusinessTypeCode(businessTypeCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByBusinessTypeCode_Last(int businessTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByBusinessTypeCode_Last(businessTypeCode,
				orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByBusinessTypeCode_Last(
		int businessTypeCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByBusinessTypeCode(businessTypeCode);

		List<ResultDeclaration> list = findByBusinessTypeCode(businessTypeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result declarations before and after the current result declaration in the ordered set where businessTypeCode = &#63;.
	 *
	 * @param id the primary key of the current result declaration
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration[] findByBusinessTypeCode_PrevAndNext(long id,
		int businessTypeCode, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByPrimaryKey(id);

		

		try {
			

			ResultDeclaration[] array = new ResultDeclaration[3];

			array[0] = getByBusinessTypeCode_PrevAndNext(
					resultDeclaration, businessTypeCode, orderByComparator, true);

			array[1] = resultDeclaration;

			array[2] = getByBusinessTypeCode_PrevAndNext(
					resultDeclaration, businessTypeCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultDeclaration getByBusinessTypeCode_PrevAndNext(
		 ResultDeclaration resultDeclaration,
		int businessTypeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

		query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

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
			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
		int businessTypeCode, long documentName, int documentYear)
		throws SystemException {
		return findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
			documentName, documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
		int businessTypeCode, long documentName, int documentYear, int start,
		int end) throws SystemException {
		return findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
			documentName, documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
		int businessTypeCode, long documentName, int documentYear, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndBusinessTypeCodeAndDocumentYear_First(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndBusinessTypeCodeAndDocumentYear_First(businessTypeCode,
				documentName, documentYear, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndBusinessTypeCodeAndDocumentYear_First(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
				documentName, documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndBusinessTypeCodeAndDocumentYear_Last(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndBusinessTypeCodeAndDocumentYear_Last(businessTypeCode,
				documentName, documentYear, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndBusinessTypeCodeAndDocumentYear_Last(
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
				documentName, documentYear);

		List<ResultDeclaration> list = findByDocumentNameAndBusinessTypeCodeAndDocumentYear(businessTypeCode,
				documentName, documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result declarations before and after the current result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result declaration
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration[] findByDocumentNameAndBusinessTypeCodeAndDocumentYear_PrevAndNext(
		long id, int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByPrimaryKey(id);

		

		try {
			

			ResultDeclaration[] array = new ResultDeclaration[3];

			array[0] = getByDocumentNameAndBusinessTypeCodeAndDocumentYear_PrevAndNext(
					resultDeclaration, businessTypeCode, documentName,
					documentYear, orderByComparator, true);

			array[1] = resultDeclaration;

			array[2] = getByDocumentNameAndBusinessTypeCodeAndDocumentYear_PrevAndNext(
					resultDeclaration, businessTypeCode, documentName,
					documentYear, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultDeclaration getByDocumentNameAndBusinessTypeCodeAndDocumentYear_PrevAndNext(
		 ResultDeclaration resultDeclaration,
		int businessTypeCode, long documentName, int documentYear,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_BUSINESSTYPECODE_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode) throws SystemException {
		return findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
			documentName, documentYear, requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, int start, int end) throws SystemException {
		return findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
			documentName, documentYear, requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(6 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(6);
			}

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<ResultDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_First(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_First(businessTypeCode,
				documentName, documentYear, requestCode, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the first result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_First(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultDeclaration> list = findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
				documentName, documentYear, requestCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_Last(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_Last(businessTypeCode,
				documentName, documentYear, requestCode, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(10);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(", documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the last result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_Last(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
				documentName, documentYear, requestCode);

		List<ResultDeclaration> list = findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
				documentName, documentYear, requestCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result declarations before and after the current result declaration in the ordered set where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param id the primary key of the current result declaration
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration[] findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_PrevAndNext(
		long id, int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByPrimaryKey(id);

		

		try {
			

			ResultDeclaration[] array = new ResultDeclaration[3];

			array[0] = getByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_PrevAndNext(
					resultDeclaration, businessTypeCode, documentName,
					documentYear, requestCode, orderByComparator, true);

			array[1] = resultDeclaration;

			array[2] = getByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_PrevAndNext(
					resultDeclaration, businessTypeCode, documentName,
					documentYear, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultDeclaration getByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode_PrevAndNext(
		 ResultDeclaration resultDeclaration,
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2);

		if (requestCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1);
		}
		else {
			if (requestCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2);
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
			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the result declaration where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultDeclarationException} if it could not be found.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
				documentName, documentYear, requestCode);

		if (resultDeclaration == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("businessTypeCode=");
			msg.append(businessTypeCode);

			msg.append(", documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultDeclarationException(msg.toString());
		}

		return resultDeclaration;
	}

	/**
	 * Returns the result declaration where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode) throws SystemException {
		return fetchByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
			documentName, documentYear, requestCode, true);
	}

	/**
	 * Returns the result declaration where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode, boolean retrieveFromCache)
		throws SystemException {
		ResultDeclaration resultDeclaration=null;if(resultDeclaration == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2);
				}
			}

			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultDeclaration.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				resultDeclaration = (ResultDeclaration) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultDeclaration;
	}

	/**
	 * Returns all the result declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAndDocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAndDocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first result declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the first result declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultDeclaration> list = findByDocumentNameAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByDocumentNameAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the last result declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAndDocumentYear(documentName,
				documentYear);

		List<ResultDeclaration> list = findByDocumentNameAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result declarations before and after the current result declaration in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result declaration
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration[] findByDocumentNameAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByPrimaryKey(id);

		

		try {
			

			ResultDeclaration[] array = new ResultDeclaration[3];

			array[0] = getByDocumentNameAndDocumentYear_PrevAndNext(
					resultDeclaration, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultDeclaration;

			array[2] = getByDocumentNameAndDocumentYear_PrevAndNext(
					resultDeclaration, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultDeclaration getByDocumentNameAndDocumentYear_PrevAndNext(
		 ResultDeclaration resultDeclaration,
		long documentName, int documentYear,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

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
			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result declarations where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @return the matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByRequestCodeAndBusinessTypeCode(
		String requestCode, int businessTypeCode) throws SystemException {
		return findByRequestCodeAndBusinessTypeCode(requestCode,
			businessTypeCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result declarations where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByRequestCodeAndBusinessTypeCode(
		String requestCode, int businessTypeCode, int start, int end)
		throws SystemException {
		return findByRequestCodeAndBusinessTypeCode(requestCode,
			businessTypeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findByRequestCodeAndBusinessTypeCode(
		String requestCode, int businessTypeCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_BUSINESSTYPECODE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				list = (List<ResultDeclaration>)queryFactory.getResultList(builder);
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
	 * Returns the first result declaration in the ordered set where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByRequestCodeAndBusinessTypeCode_First(
		String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByRequestCodeAndBusinessTypeCode_First(requestCode,
				businessTypeCode, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(", businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the first result declaration in the ordered set where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByRequestCodeAndBusinessTypeCode_First(
		String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = findByRequestCodeAndBusinessTypeCode(requestCode,
				businessTypeCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result declaration in the ordered set where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration findByRequestCodeAndBusinessTypeCode_Last(
		String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = fetchByRequestCodeAndBusinessTypeCode_Last(requestCode,
				businessTypeCode, orderByComparator);

		if (resultDeclaration != null) {
			return resultDeclaration;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(", businessTypeCode=");
		msg.append(businessTypeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultDeclarationException(msg.toString());
	}

	/**
	 * Returns the last result declaration in the ordered set where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result declaration, or <code>null</code> if a matching result declaration could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration fetchByRequestCodeAndBusinessTypeCode_Last(
		String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCodeAndBusinessTypeCode(requestCode,
				businessTypeCode);

		List<ResultDeclaration> list = findByRequestCodeAndBusinessTypeCode(requestCode,
				businessTypeCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result declarations before and after the current result declaration in the ordered set where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param id the primary key of the current result declaration
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result declaration
	 * @throws vn.gt.dao.result.NoSuchResultDeclarationException if a result declaration with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration[] findByRequestCodeAndBusinessTypeCode_PrevAndNext(
		long id, String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByPrimaryKey(id);

		

		try {
			

			ResultDeclaration[] array = new ResultDeclaration[3];

			array[0] = getByRequestCodeAndBusinessTypeCode_PrevAndNext(
					resultDeclaration, requestCode, businessTypeCode,
					orderByComparator, true);

			array[1] = resultDeclaration;

			array[2] = getByRequestCodeAndBusinessTypeCode_PrevAndNext(
					resultDeclaration, requestCode, businessTypeCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultDeclaration getByRequestCodeAndBusinessTypeCode_PrevAndNext(
		 ResultDeclaration resultDeclaration,
		String requestCode, int businessTypeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTDECLARATION_WHERE);

		if (requestCode == null) {
			query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_1);
		}
		else {
			if (requestCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_2);
			}
		}

		query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_BUSINESSTYPECODE_2);

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
			query.append(ResultDeclarationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultDeclaration);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultDeclaration> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result declarations.
	 *
	 * @return the result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @return the range of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result declarations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result declarations
	 * @param end the upper bound of the range of result declarations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultDeclaration> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultDeclaration> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTDECLARATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTDECLARATION.concat(ResultDeclarationModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultDeclaration>) queryFactory.getResultList(builder);
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
	 * Removes the result declaration where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the result declaration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration removeByRequestCode(String requestCode)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByRequestCode(requestCode);

		repository.delete(resultDeclaration);
			return resultDeclaration;
	}

	/**
	 * Removes all the result declarations where businessTypeCode = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByBusinessTypeCode(int businessTypeCode)
		throws SystemException {
		for (ResultDeclaration resultDeclaration : findByBusinessTypeCode(
				businessTypeCode)) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Removes all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAndBusinessTypeCodeAndDocumentYear(
		int businessTypeCode, long documentName, int documentYear)
		throws SystemException {
		for (ResultDeclaration resultDeclaration : findByDocumentNameAndBusinessTypeCodeAndDocumentYear(
				businessTypeCode, documentName, documentYear)) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Removes all the result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode) throws SystemException {
		for (ResultDeclaration resultDeclaration : findByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
				businessTypeCode, documentName, documentYear, requestCode)) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Removes the result declaration where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63; from the database.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the result declaration that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultDeclaration removeByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode)
		throws NoSuchResultDeclarationException, SystemException {
		ResultDeclaration resultDeclaration = findByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(businessTypeCode,
				documentName, documentYear, requestCode);

		repository.delete(resultDeclaration);
			return resultDeclaration;
	}

	/**
	 * Removes all the result declarations where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (ResultDeclaration resultDeclaration : findByDocumentNameAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Removes all the result declarations where requestCode = &#63; and businessTypeCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCodeAndBusinessTypeCode(String requestCode,
		int businessTypeCode) throws SystemException {
		for (ResultDeclaration resultDeclaration : findByRequestCodeAndBusinessTypeCode(
				requestCode, businessTypeCode)) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Removes all the result declarations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultDeclaration resultDeclaration : findAll()) {
			repository.delete(resultDeclaration);
		}
	}

	/**
	 * Returns the number of result declarations where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
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
	 * Returns the number of result declarations where businessTypeCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByBusinessTypeCode(int businessTypeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

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
	 * Returns the number of result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndBusinessTypeCodeAndDocumentYear(
		int businessTypeCode, long documentName, int documentYear)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

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
	 * Returns the number of result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
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
	 * Returns the number of result declarations where businessTypeCode = &#63; and documentName = &#63; and documentYear = &#63; and requestCode = &#63;.
	 *
	 * @param businessTypeCode the business type code
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param requestCode the request code
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByfndDocumentNameAndBusinessTypeCodeAndDocumentYearRequestCode(
		int businessTypeCode, long documentName, int documentYear,
		String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
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
	 * Returns the number of result declarations where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

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
	 * Returns the number of result declarations where requestCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param businessTypeCode the business type code
	 * @return the number of matching result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCodeAndBusinessTypeCode(String requestCode,
		int businessTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTDECLARATION_WHERE);

			if (requestCode == null) {
				query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_1);
			}
			else {
				if (requestCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_BUSINESSTYPECODE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

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
	 * Returns the number of result declarations.
	 *
	 * @return the number of result declarations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTDECLARATION).build();

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
	 * Initializes the result declaration persistence.
	 */
	private static final String _SQL_SELECT_RESULTDECLARATION = "SELECT resultDeclaration FROM ResultDeclaration resultDeclaration";
	private static final String _SQL_SELECT_RESULTDECLARATION_WHERE = "SELECT resultDeclaration FROM ResultDeclaration resultDeclaration WHERE ";
	private static final String _SQL_COUNT_RESULTDECLARATION = "SELECT COUNT(resultDeclaration) FROM ResultDeclaration resultDeclaration";
	private static final String _SQL_COUNT_RESULTDECLARATION_WHERE = "SELECT COUNT(resultDeclaration) FROM ResultDeclaration resultDeclaration WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "resultDeclaration.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "resultDeclaration.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(resultDeclaration.requestCode IS NULL OR resultDeclaration.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_BUSINESSTYPECODE_BUSINESSTYPECODE_2 =
		"resultDeclaration.businessTypeCode =:businessTypeCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_BUSINESSTYPECODE_2 =
		"resultDeclaration.businessTypeCode =:businessTypeCode AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultDeclaration.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2 =
		"resultDeclaration.businessTypeCode =:businessTypeCode AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2 =
		"resultDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2 =
		"resultDeclaration.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1 =
		"resultDeclaration.requestCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2 =
		"resultDeclaration.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3 =
		"(resultDeclaration.requestCode IS NULL OR resultDeclaration.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_BUSINESSTYPECODE_2 =
		"resultDeclaration.businessTypeCode =:businessTypeCode AND ";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTNAME_2 =
		"resultDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_DOCUMENTYEAR_2 =
		"resultDeclaration.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_1 =
		"resultDeclaration.requestCode IS NULL";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_2 =
		"resultDeclaration.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_FNDDOCUMENTNAMEANDBUSINESSTYPECODEANDDOCUMENTYEARREQUESTCODE_REQUESTCODE_3 =
		"(resultDeclaration.requestCode IS NULL OR resultDeclaration.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultDeclaration.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultDeclaration.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_1 =
		"resultDeclaration.requestCode IS NULL AND ";
	private static final String _FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_2 =
		"resultDeclaration.requestCode =:requestCode AND ";
	private static final String _FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_REQUESTCODE_3 =
		"(resultDeclaration.requestCode IS NULL OR resultDeclaration.requestCode =:requestCode) AND ";
	private static final String _FINDER_COLUMN_REQUESTCODEANDBUSINESSTYPECODE_BUSINESSTYPECODE_2 =
		"resultDeclaration.businessTypeCode =:businessTypeCode";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultDeclaration.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultDeclaration exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultDeclaration exists with the key {";
	

	
}
