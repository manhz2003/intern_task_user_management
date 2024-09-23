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
import com.fds.nsw.nghiepvu.model.ResultHistoryMinistry;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultHistoryMinistryRepository;
import com.fds.nsw.nghiepvu.modelImpl.ResultHistoryMinistryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultHistoryMinistryPersistenceImpl extends BasePersistence {
	@Autowired
	ResultHistoryMinistryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultHistoryMinistry> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultHistoryMinistryUtil} to access the result history ministry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultHistoryMinistry create(long id) {
		ResultHistoryMinistry resultHistoryMinistry = new ResultHistoryMinistry();

		
		//resultHistoryMinistry.setPrimaryKey(id);

		return resultHistoryMinistry;
	}

	/**
	 * Removes the result history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result history ministry
	 * @return the result history ministry that was removed
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry remove(long id)
		throws NoSuchResultHistoryMinistryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result history ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result history ministry
	 * @return the result history ministry that was removed
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultHistoryMinistry remove(Serializable primaryKey)
		throws NoSuchResultHistoryMinistryException, SystemException {
		

		try {
			

			ResultHistoryMinistry resultHistoryMinistry = findByPrimaryKey(primaryKey);

			if (resultHistoryMinistry == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultHistoryMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(resultHistoryMinistry);
			return resultHistoryMinistry;
		}
		catch (NoSuchResultHistoryMinistryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultHistoryMinistry remove(ResultHistoryMinistry ResultHistoryMinistry) throws SystemException {
	removeImpl(ResultHistoryMinistry);
	return ResultHistoryMinistry;
}

protected ResultHistoryMinistry removeImpl(
		ResultHistoryMinistry resultHistoryMinistry) throws SystemException {
		try {
			repository.delete(resultHistoryMinistry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultHistoryMinistry;
	}

	
	public ResultHistoryMinistry updateImpl(
		com.fds.nsw.nghiepvu.model.ResultHistoryMinistry resultHistoryMinistry,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(resultHistoryMinistry);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultHistoryMinistry;
	}

	
	public ResultHistoryMinistry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result history ministry with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultHistoryMinistryException} if it could not be found.
	 *
	 * @param id the primary key of the result history ministry
	 * @return the result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByPrimaryKey(long id)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByPrimaryKey(id);

		if (resultHistoryMinistry == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultHistoryMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultHistoryMinistry;
	}

	/**
	 * Returns the result history ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result history ministry
	 * @return the result history ministry, or <code>null</code> if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultHistoryMinistry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result history ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result history ministry
	 * @return the result history ministry, or <code>null</code> if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByPrimaryKey(long id)
		throws SystemException {
		ResultHistoryMinistry resultHistoryMinistry = null;

		

		if (resultHistoryMinistry == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ResultHistoryMinistry> optional = repository.findById(id);
				if (optional.isPresent()) {
					resultHistoryMinistry = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultHistoryMinistry;
	}

	/**
	 * Returns the result history ministry where requestCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultHistoryMinistryException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByRequestCode(String requestCode)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByRequestCode(requestCode);

		if (resultHistoryMinistry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultHistoryMinistryException(msg.toString());
		}

		return resultHistoryMinistry;
	}

	/**
	 * Returns the result history ministry where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the result history ministry where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		ResultHistoryMinistry resultHistoryMinistry = null;
		if (resultHistoryMinistry == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

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

			query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultHistoryMinistry.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				resultHistoryMinistry = (ResultHistoryMinistry) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultHistoryMinistry;
	}

	/**
	 * Returns all the result history ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result history ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @return the range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result history ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultHistoryMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultHistoryMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result history ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByDocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the first result history ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByDocumentNameAnddocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultHistoryMinistry> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result history ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByDocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the last result history ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByDocumentNameAnddocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYear(documentName,
				documentYear);

		List<ResultHistoryMinistry> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result history ministries before and after the current result history ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result history ministry
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry[] findByDocumentNameAnddocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultHistoryMinistry[] array = new ResultHistoryMinistry[3];

			array[0] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultHistoryMinistry, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultHistoryMinistry;

			array[2] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultHistoryMinistry, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultHistoryMinistry getByDocumentNameAnddocumentYear_PrevAndNext(
		 ResultHistoryMinistry resultHistoryMinistry,
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

		query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

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
			query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultHistoryMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultHistoryMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result history ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @return the matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode)
		throws SystemException {
		return findByDocumentNameAnddocumentYearAndMinistryCode(documentName,
			documentYear, ministryCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the result history ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @return the range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode, int start,
		int end) throws SystemException {
		return findByDocumentNameAnddocumentYearAndMinistryCode(documentName,
			documentYear, ministryCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result history ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultHistoryMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				list = (List<ResultHistoryMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result history ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByDocumentNameAnddocumentYearAndMinistryCode_First(
		long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByDocumentNameAnddocumentYearAndMinistryCode_First(documentName,
				documentYear, ministryCode, orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the first result history ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByDocumentNameAnddocumentYearAndMinistryCode_First(
		long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultHistoryMinistry> list = findByDocumentNameAnddocumentYearAndMinistryCode(documentName,
				documentYear, ministryCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result history ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByDocumentNameAnddocumentYearAndMinistryCode_Last(
		long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByDocumentNameAnddocumentYearAndMinistryCode_Last(documentName,
				documentYear, ministryCode, orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(8);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(", ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the last result history ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByDocumentNameAnddocumentYearAndMinistryCode_Last(
		long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameAnddocumentYearAndMinistryCode(documentName,
				documentYear, ministryCode);

		List<ResultHistoryMinistry> list = findByDocumentNameAnddocumentYearAndMinistryCode(documentName,
				documentYear, ministryCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result history ministries before and after the current result history ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param id the primary key of the current result history ministry
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry[] findByDocumentNameAnddocumentYearAndMinistryCode_PrevAndNext(
		long id, long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultHistoryMinistry[] array = new ResultHistoryMinistry[3];

			array[0] = getByDocumentNameAnddocumentYearAndMinistryCode_PrevAndNext(
					resultHistoryMinistry, documentName, documentYear,
					ministryCode, orderByComparator, true);

			array[1] = resultHistoryMinistry;

			array[2] = getByDocumentNameAnddocumentYearAndMinistryCode_PrevAndNext(
					resultHistoryMinistry, documentName, documentYear,
					ministryCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultHistoryMinistry getByDocumentNameAnddocumentYearAndMinistryCode_PrevAndNext(
		 ResultHistoryMinistry resultHistoryMinistry,
		long documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

		if (ministryCode == null) {
			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_1);
		}
		else {
			if (ministryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_2);
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
			query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (ministryCode != null) {
			builder.appendNamedParameterMap("ministryCode", ministryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultHistoryMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultHistoryMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result history ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByMinistryCode(String ministryCode)
		throws SystemException {
		return findByMinistryCode(ministryCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result history ministries where ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @return the range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByMinistryCode(String ministryCode,
		int start, int end) throws SystemException {
		return findByMinistryCode(ministryCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result history ministries where ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findByMinistryCode(String ministryCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultHistoryMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				list = (List<ResultHistoryMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result history ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByMinistryCode_First(String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByMinistryCode_First(ministryCode,
				orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the first result history ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByMinistryCode_First(
		String ministryCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultHistoryMinistry> list = findByMinistryCode(ministryCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result history ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry findByMinistryCode_Last(String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = fetchByMinistryCode_Last(ministryCode,
				orderByComparator);

		if (resultHistoryMinistry != null) {
			return resultHistoryMinistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultHistoryMinistryException(msg.toString());
	}

	/**
	 * Returns the last result history ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result history ministry, or <code>null</code> if a matching result history ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry fetchByMinistryCode_Last(String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMinistryCode(ministryCode);

		List<ResultHistoryMinistry> list = findByMinistryCode(ministryCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result history ministries before and after the current result history ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param id the primary key of the current result history ministry
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result history ministry
	 * @throws vn.gt.dao.result.NoSuchResultHistoryMinistryException if a result history ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry[] findByMinistryCode_PrevAndNext(long id,
		String ministryCode, OrderByComparator orderByComparator)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultHistoryMinistry[] array = new ResultHistoryMinistry[3];

			array[0] = getByMinistryCode_PrevAndNext(
					resultHistoryMinistry, ministryCode, orderByComparator, true);

			array[1] = resultHistoryMinistry;

			array[2] = getByMinistryCode_PrevAndNext(
					resultHistoryMinistry, ministryCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultHistoryMinistry getByMinistryCode_PrevAndNext(
		 ResultHistoryMinistry resultHistoryMinistry,
		String ministryCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTHISTORYMINISTRY_WHERE);

		if (ministryCode == null) {
			query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1);
		}
		else {
			if (ministryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2);
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
			query.append(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (ministryCode != null) {
			builder.appendNamedParameterMap("ministryCode", ministryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultHistoryMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultHistoryMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result history ministries.
	 *
	 * @return the result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result history ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @return the range of result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result history ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result history ministries
	 * @param end the upper bound of the range of result history ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultHistoryMinistry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultHistoryMinistry> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTHISTORYMINISTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTHISTORYMINISTRY.concat(ResultHistoryMinistryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultHistoryMinistry>) queryFactory.getResultList(builder);
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
	 * Removes the result history ministry where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the result history ministry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultHistoryMinistry removeByRequestCode(String requestCode)
		throws NoSuchResultHistoryMinistryException, SystemException {
		ResultHistoryMinistry resultHistoryMinistry = findByRequestCode(requestCode);

		repository.delete(resultHistoryMinistry);
			return resultHistoryMinistry;
	}

	/**
	 * Removes all the result history ministries where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (ResultHistoryMinistry resultHistoryMinistry : findByDocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(resultHistoryMinistry);
		}
	}

	/**
	 * Removes all the result history ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode)
		throws SystemException {
		for (ResultHistoryMinistry resultHistoryMinistry : findByDocumentNameAnddocumentYearAndMinistryCode(
				documentName, documentYear, ministryCode)) {
			repository.delete(resultHistoryMinistry);
		}
	}

	/**
	 * Removes all the result history ministries where ministryCode = &#63; from the database.
	 *
	 * @param ministryCode the ministry code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMinistryCode(String ministryCode)
		throws SystemException {
		for (ResultHistoryMinistry resultHistoryMinistry : findByMinistryCode(
				ministryCode)) {
			repository.delete(resultHistoryMinistry);
		}
	}

	/**
	 * Removes all the result history ministries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultHistoryMinistry resultHistoryMinistry : findAll()) {
			repository.delete(resultHistoryMinistry);
		}
	}

	/**
	 * Returns the number of result history ministries where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTHISTORYMINISTRY_WHERE);

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
	 * Returns the number of result history ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTHISTORYMINISTRY_WHERE);

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
	 * Returns the number of result history ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @return the number of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearAndMinistryCode(
		long documentName, int documentYear, String ministryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTHISTORYMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
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
	 * Returns the number of result history ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the number of matching result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMinistryCode(String ministryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTHISTORYMINISTRY_WHERE);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
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
	 * Returns the number of result history ministries.
	 *
	 * @return the number of result history ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTHISTORYMINISTRY).build();

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
	 * Initializes the result history ministry persistence.
	 */
	private static final String _SQL_SELECT_RESULTHISTORYMINISTRY = "SELECT resultHistoryMinistry FROM ResultHistoryMinistry resultHistoryMinistry";
	private static final String _SQL_SELECT_RESULTHISTORYMINISTRY_WHERE = "SELECT resultHistoryMinistry FROM ResultHistoryMinistry resultHistoryMinistry WHERE ";
	private static final String _SQL_COUNT_RESULTHISTORYMINISTRY = "SELECT COUNT(resultHistoryMinistry) FROM ResultHistoryMinistry resultHistoryMinistry";
	private static final String _SQL_COUNT_RESULTHISTORYMINISTRY_WHERE = "SELECT COUNT(resultHistoryMinistry) FROM ResultHistoryMinistry resultHistoryMinistry WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "resultHistoryMinistry.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "resultHistoryMinistry.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(resultHistoryMinistry.requestCode IS NULL OR resultHistoryMinistry.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultHistoryMinistry.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultHistoryMinistry.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTNAME_2 =
		"resultHistoryMinistry.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_DOCUMENTYEAR_2 =
		"resultHistoryMinistry.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_1 =
		"resultHistoryMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_2 =
		"resultHistoryMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARANDMINISTRYCODE_MINISTRYCODE_3 =
		"(resultHistoryMinistry.ministryCode IS NULL OR resultHistoryMinistry.ministryCode =:ministryCode)";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1 = "resultHistoryMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2 = "resultHistoryMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3 = "(resultHistoryMinistry.ministryCode IS NULL OR resultHistoryMinistry.ministryCode =:ministryCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultHistoryMinistry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultHistoryMinistry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultHistoryMinistry exists with the key {";
	

	
}
