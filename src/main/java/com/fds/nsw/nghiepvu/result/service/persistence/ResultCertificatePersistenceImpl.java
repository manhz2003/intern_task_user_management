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
import com.fds.nsw.nghiepvu.model.ResultCertificate;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultCertificateRepository;
import com.fds.nsw.nghiepvu.modelImpl.ResultCertificateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultCertificatePersistenceImpl extends BasePersistence {
	@Autowired
	ResultCertificateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultCertificate> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultCertificateUtil} to access the result certificate persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultCertificate create(long id) {
		ResultCertificate resultCertificate = new ResultCertificate();

		
		//resultCertificate.setPrimaryKey(id);

		return resultCertificate;
	}

	/**
	 * Removes the result certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result certificate
	 * @return the result certificate that was removed
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate remove(long id)
		throws NoSuchResultCertificateException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result certificate with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result certificate
	 * @return the result certificate that was removed
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultCertificate remove(Serializable primaryKey)
		throws NoSuchResultCertificateException, SystemException {
		

		try {
			

			ResultCertificate resultCertificate = findByPrimaryKey(primaryKey);

			if (resultCertificate == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(resultCertificate);
			return resultCertificate;
		}
		catch (NoSuchResultCertificateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultCertificate remove(ResultCertificate ResultCertificate) throws SystemException {
	removeImpl(ResultCertificate);
	return ResultCertificate;
}

protected ResultCertificate removeImpl(ResultCertificate resultCertificate)
		throws SystemException {
		try {
			repository.delete(resultCertificate);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultCertificate;
	}

	
	public ResultCertificate updateImpl(
		com.fds.nsw.nghiepvu.model.ResultCertificate resultCertificate,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(resultCertificate);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultCertificate;
	}

	
	public ResultCertificate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result certificate with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultCertificateException} if it could not be found.
	 *
	 * @param id the primary key of the result certificate
	 * @return the result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByPrimaryKey(long id)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByPrimaryKey(id);

		if (resultCertificate == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultCertificateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultCertificate;
	}

	/**
	 * Returns the result certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result certificate
	 * @return the result certificate, or <code>null</code> if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultCertificate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result certificate with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result certificate
	 * @return the result certificate, or <code>null</code> if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByPrimaryKey(long id)
		throws SystemException {
		ResultCertificate resultCertificate = null;

		

		if (resultCertificate == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ResultCertificate> optional = repository.findById(id);
				if (optional.isPresent()) {
					resultCertificate = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultCertificate;
	}

	/**
	 * Returns all the result certificates where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result certificates where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @return the range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result certificates where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultCertificate> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYear(documentName,
				documentYear);

		List<ResultCertificate> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result certificates before and after the current result certificate in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result certificate
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate[] findByDocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = findByPrimaryKey(id);

		

		try {
			

			ResultCertificate[] array = new ResultCertificate[3];

			array[0] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultCertificate, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultCertificate;

			array[2] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultCertificate, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultCertificate getByDocumentNameAnddocumentYear_PrevAndNext(
		 ResultCertificate resultCertificate,
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

		query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

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
			query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result certificates where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @return the matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndMaritimeCode(
		long documentName, int documentYear, String maritimeCode)
		throws SystemException {
		return findByDocumentNameAnddocumentYearAndMaritimeCode(documentName,
			documentYear, maritimeCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the result certificates where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @return the range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndMaritimeCode(
		long documentName, int documentYear, String maritimeCode, int start,
		int end) throws SystemException {
		return findByDocumentNameAnddocumentYearAndMaritimeCode(documentName,
			documentYear, maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result certificates where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndMaritimeCode(
		long documentName, int documentYear, String maritimeCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTYEAR_2);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<ResultCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYearAndMaritimeCode_First(
		long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYearAndMaritimeCode_First(documentName,
				documentYear, maritimeCode, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYearAndMaritimeCode_First(
		long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = findByDocumentNameAnddocumentYearAndMaritimeCode(documentName,
				documentYear, maritimeCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYearAndMaritimeCode_Last(
		long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYearAndMaritimeCode_Last(documentName,
				documentYear, maritimeCode, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYearAndMaritimeCode_Last(
		long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAnddocumentYearAndMaritimeCode(documentName,
				documentYear, maritimeCode);

		List<ResultCertificate> list = findByDocumentNameAnddocumentYearAndMaritimeCode(documentName,
				documentYear, maritimeCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result certificates before and after the current result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current result certificate
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate[] findByDocumentNameAnddocumentYearAndMaritimeCode_PrevAndNext(
		long id, long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = findByPrimaryKey(id);

		

		try {
			

			ResultCertificate[] array = new ResultCertificate[3];

			array[0] = getByDocumentNameAnddocumentYearAndMaritimeCode_PrevAndNext(
					resultCertificate, documentName, documentYear,
					maritimeCode, orderByComparator, true);

			array[1] = resultCertificate;

			array[2] = getByDocumentNameAnddocumentYearAndMaritimeCode_PrevAndNext(
					resultCertificate, documentName, documentYear,
					maritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultCertificate getByDocumentNameAnddocumentYearAndMaritimeCode_PrevAndNext(
		 ResultCertificate resultCertificate,
		long documentName, int documentYear, String maritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTYEAR_2);

		if (maritimeCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (maritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_2);
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
			query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @return the matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndCertificateCode(
		long documentName, int documentYear, String certificateCode)
		throws SystemException {
		return findByDocumentNameAnddocumentYearAndCertificateCode(documentName,
			documentYear, certificateCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @return the range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndCertificateCode(
		long documentName, int documentYear, String certificateCode, int start,
		int end) throws SystemException {
		return findByDocumentNameAnddocumentYearAndCertificateCode(documentName,
			documentYear, certificateCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findByDocumentNameAnddocumentYearAndCertificateCode(
		long documentName, int documentYear, String certificateCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				list = (List<ResultCertificate>)queryFactory.getResultList(builder);
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
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYearAndCertificateCode_First(
		long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYearAndCertificateCode_First(documentName,
				documentYear, certificateCode, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the first result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYearAndCertificateCode_First(
		long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = findByDocumentNameAnddocumentYearAndCertificateCode(documentName,
				documentYear, certificateCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByDocumentNameAnddocumentYearAndCertificateCode_Last(
		long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByDocumentNameAnddocumentYearAndCertificateCode_Last(documentName,
				documentYear, certificateCode, orderByComparator);

		if (resultCertificate != null) {
			return resultCertificate;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", certificateCode=");
		msg.append(certificateCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCertificateException(msg.toString());
	}

	/**
	 * Returns the last result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByDocumentNameAnddocumentYearAndCertificateCode_Last(
		long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAnddocumentYearAndCertificateCode(documentName,
				documentYear, certificateCode);

		List<ResultCertificate> list = findByDocumentNameAnddocumentYearAndCertificateCode(documentName,
				documentYear, certificateCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result certificates before and after the current result certificate in the ordered set where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param id the primary key of the current result certificate
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a result certificate with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate[] findByDocumentNameAnddocumentYearAndCertificateCode_PrevAndNext(
		long id, long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = findByPrimaryKey(id);

		

		try {
			

			ResultCertificate[] array = new ResultCertificate[3];

			array[0] = getByDocumentNameAnddocumentYearAndCertificateCode_PrevAndNext(
					resultCertificate, documentName, documentYear,
					certificateCode, orderByComparator, true);

			array[1] = resultCertificate;

			array[2] = getByDocumentNameAnddocumentYearAndCertificateCode_PrevAndNext(
					resultCertificate, documentName, documentYear,
					certificateCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultCertificate getByDocumentNameAnddocumentYearAndCertificateCode_PrevAndNext(
		 ResultCertificate resultCertificate,
		long documentName, int documentYear, String certificateCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTYEAR_2);

		if (certificateCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_1);
		}
		else {
			if (certificateCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_2);
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
			query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (certificateCode != null) {
			builder.appendNamedParameterMap("certificateCode", certificateCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultCertificate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultCertificate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and certificateNo = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultCertificateException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param certificateNo the certificate no
	 * @return the matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByF_BY4(long documentName, int documentYear,
		String certificateCode, String certificateNo)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByF_BY4(documentName,
				documentYear, certificateCode, certificateNo);

		if (resultCertificate == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", certificateCode=");
			msg.append(certificateCode);

			msg.append(", certificateNo=");
			msg.append(certificateNo);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultCertificateException(msg.toString());
		}

		return resultCertificate;
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param certificateNo the certificate no
	 * @return the matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByF_BY4(long documentName, int documentYear,
		String certificateCode, String certificateNo) throws SystemException {
		return fetchByF_BY4(documentName, documentYear, certificateCode,
			certificateNo, true);
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and certificateNo = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param certificateNo the certificate no
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByF_BY4(long documentName, int documentYear,
		String certificateCode, String certificateNo, boolean retrieveFromCache)
		throws SystemException {
		ResultCertificate resultCertificate = null;
		if (resultCertificate == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_F_BY4_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_BY4_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_2);
				}
			}

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_2);
				}
			}

			query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultCertificate.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
				}

				resultCertificate = (ResultCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultCertificate;
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and comment = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultCertificateException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param comment the comment
	 * @return the matching result certificate
	 * @throws vn.gt.dao.result.NoSuchResultCertificateException if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate findByCrewNameAndCertificateCode(
		long documentName, int documentYear, String certificateCode,
		String comment)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = fetchByCrewNameAndCertificateCode(documentName,
				documentYear, certificateCode, comment);

		if (resultCertificate == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", certificateCode=");
			msg.append(certificateCode);

			msg.append(", comment=");
			msg.append(comment);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultCertificateException(msg.toString());
		}

		return resultCertificate;
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and comment = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param comment the comment
	 * @return the matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByCrewNameAndCertificateCode(
		long documentName, int documentYear, String certificateCode,
		String comment) throws SystemException {
		return fetchByCrewNameAndCertificateCode(documentName, documentYear,
			certificateCode, comment, true);
	}

	/**
	 * Returns the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and comment = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param comment the comment
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result certificate, or <code>null</code> if a matching result certificate could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate fetchByCrewNameAndCertificateCode(
		long documentName, int documentYear, String certificateCode,
		String comment, boolean retrieveFromCache) throws SystemException {
		ResultCertificate resultCertificate = null;
		if (resultCertificate == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			if (comment == null) {
				query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_1);
			}
			else {
				if (comment.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_2);
				}
			}

			query.append(ResultCertificateModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultCertificate.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (comment != null) {
					builder.appendNamedParameterMap("comment", comment);
				}

				resultCertificate = (ResultCertificate) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultCertificate;
	}

	/**
	 * Returns all the result certificates.
	 *
	 * @return the result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @return the range of result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result certificates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result certificates
	 * @param end the upper bound of the range of result certificates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCertificate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCertificate> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTCERTIFICATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTCERTIFICATE.concat(ResultCertificateModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultCertificate>) queryFactory.getResultList(builder);
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
	 * Removes all the result certificates where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (ResultCertificate resultCertificate : findByDocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(resultCertificate);
		}
	}

	/**
	 * Removes all the result certificates where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYearAndMaritimeCode(
		long documentName, int documentYear, String maritimeCode)
		throws SystemException {
		for (ResultCertificate resultCertificate : findByDocumentNameAnddocumentYearAndMaritimeCode(
				documentName, documentYear, maritimeCode)) {
			repository.delete(resultCertificate);
		}
	}

	/**
	 * Removes all the result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYearAndCertificateCode(
		long documentName, int documentYear, String certificateCode)
		throws SystemException {
		for (ResultCertificate resultCertificate : findByDocumentNameAnddocumentYearAndCertificateCode(
				documentName, documentYear, certificateCode)) {
			repository.delete(resultCertificate);
		}
	}

	/**
	 * Removes the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and certificateNo = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param certificateNo the certificate no
	 * @return the result certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate removeByF_BY4(long documentName, int documentYear,
		String certificateCode, String certificateNo)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = findByF_BY4(documentName,
				documentYear, certificateCode, certificateNo);

		repository.delete(resultCertificate);
			return resultCertificate;
	}

	/**
	 * Removes the result certificate where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and comment = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param comment the comment
	 * @return the result certificate that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCertificate removeByCrewNameAndCertificateCode(
		long documentName, int documentYear, String certificateCode,
		String comment)
		throws NoSuchResultCertificateException, SystemException {
		ResultCertificate resultCertificate = findByCrewNameAndCertificateCode(documentName,
				documentYear, certificateCode, comment);

		repository.delete(resultCertificate);
			return resultCertificate;
	}

	/**
	 * Removes all the result certificates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultCertificate resultCertificate : findAll()) {
			repository.delete(resultCertificate);
		}
	}

	/**
	 * Returns the number of result certificates where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTCERTIFICATE_WHERE);

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
	 * Returns the number of result certificates where documentName = &#63; and documentYear = &#63; and maritimeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param maritimeCode the maritime code
	 * @return the number of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearAndMaritimeCode(
		long documentName, int documentYear, String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTYEAR_2);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @return the number of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearAndCertificateCode(
		long documentName, int documentYear, String certificateCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
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
	 * Returns the number of result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and certificateNo = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param certificateNo the certificate no
	 * @return the number of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_BY4(long documentName, int documentYear,
		String certificateCode, String certificateNo) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_F_BY4_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_F_BY4_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATECODE_2);
				}
			}

			if (certificateNo == null) {
				query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_1);
			}
			else {
				if (certificateNo.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY4_CERTIFICATENO_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (certificateNo != null) {
					builder.appendNamedParameterMap("certificateNo", certificateNo);
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
	 * Returns the number of result certificates where documentName = &#63; and documentYear = &#63; and certificateCode = &#63; and comment = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param certificateCode the certificate code
	 * @param comment the comment
	 * @return the number of matching result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCrewNameAndCertificateCode(long documentName,
		int documentYear, String certificateCode, String comment)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESULTCERTIFICATE_WHERE);

			query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTYEAR_2);

			if (certificateCode == null) {
				query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_1);
			}
			else {
				if (certificateCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_2);
				}
			}

			if (comment == null) {
				query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_1);
			}
			else {
				if (comment.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_3);
				}
				else {
					query.append(_FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (certificateCode != null) {
					builder.appendNamedParameterMap("certificateCode", certificateCode);
				}

				if (comment != null) {
					builder.appendNamedParameterMap("comment", comment);
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
	 * Returns the number of result certificates.
	 *
	 * @return the number of result certificates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTCERTIFICATE).build();

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
	 * Initializes the result certificate persistence.
	 */
	private static final String _SQL_SELECT_RESULTCERTIFICATE = "SELECT resultCertificate FROM ResultCertificate resultCertificate";
	private static final String _SQL_SELECT_RESULTCERTIFICATE_WHERE = "SELECT resultCertificate FROM ResultCertificate resultCertificate WHERE ";
	private static final String _SQL_COUNT_RESULTCERTIFICATE = "SELECT COUNT(resultCertificate) FROM ResultCertificate resultCertificate";
	private static final String _SQL_COUNT_RESULTCERTIFICATE_WHERE = "SELECT COUNT(resultCertificate) FROM ResultCertificate resultCertificate WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultCertificate.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultCertificate.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTNAME_2 =
		"resultCertificate.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_DOCUMENTYEAR_2 =
		"resultCertificate.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_1 =
		"resultCertificate.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_2 =
		"resultCertificate.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMARITIMECODE_MARITIMECODE_3 =
		"(resultCertificate.maritimeCode IS NULL OR resultCertificate.maritimeCode =:maritimeCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTNAME_2 =
		"resultCertificate.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_DOCUMENTYEAR_2 =
		"resultCertificate.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_1 =
		"resultCertificate.certificateCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_2 =
		"resultCertificate.certificateCode =:certificateCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDCERTIFICATECODE_CERTIFICATECODE_3 =
		"(resultCertificate.certificateCode IS NULL OR resultCertificate.certificateCode =:certificateCode)";
	private static final String _FINDER_COLUMN_F_BY4_DOCUMENTNAME_2 = "resultCertificate.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_F_BY4_DOCUMENTYEAR_2 = "resultCertificate.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATECODE_1 = "resultCertificate.certificateCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATECODE_2 = "resultCertificate.certificateCode =:certificateCode AND ";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATECODE_3 = "(resultCertificate.certificateCode IS NULL OR resultCertificate.certificateCode =:certificateCode) AND ";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATENO_1 = "resultCertificate.certificateNo IS NULL";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATENO_2 = "resultCertificate.certificateNo =:certificateNo";
	private static final String _FINDER_COLUMN_F_BY4_CERTIFICATENO_3 = "(resultCertificate.certificateNo IS NULL OR resultCertificate.certificateNo =:certificateNo)";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTNAME_2 =
		"resultCertificate.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_DOCUMENTYEAR_2 =
		"resultCertificate.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_1 =
		"resultCertificate.certificateCode IS NULL AND ";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_2 =
		"resultCertificate.certificateCode =:certificateCode AND ";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_CERTIFICATECODE_3 =
		"(resultCertificate.certificateCode IS NULL OR resultCertificate.certificateCode =:certificateCode) AND ";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_1 =
		"resultCertificate.comment IS NULL";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_2 =
		"resultCertificate.comment =:comment";
	private static final String _FINDER_COLUMN_CREWNAMEANDCERTIFICATECODE_COMMENT_3 =
		"(resultCertificate.comment IS NULL OR resultCertificate.comment =:comment)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultCertificate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultCertificate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultCertificate exists with the key {";
	

	
}
