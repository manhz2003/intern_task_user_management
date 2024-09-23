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
import com.fds.nsw.nghiepvu.model.ResultMinistry;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultMinistryRepository;
import com.fds.nsw.nghiepvu.modelImpl.ResultMinistryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultMinistryPersistenceImpl extends BasePersistence {
	@Autowired
	ResultMinistryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultMinistry> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultMinistryUtil} to access the result ministry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultMinistry create(long id) {
		ResultMinistry resultMinistry = new ResultMinistry();

		
		//resultMinistry.setPrimaryKey(id);

		return resultMinistry;
	}

	/**
	 * Removes the result ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result ministry
	 * @return the result ministry that was removed
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry remove(long id)
		throws NoSuchResultMinistryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result ministry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result ministry
	 * @return the result ministry that was removed
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultMinistry remove(Serializable primaryKey)
		throws NoSuchResultMinistryException, SystemException {
		

		try {
			

			ResultMinistry resultMinistry = findByPrimaryKey(primaryKey);

			if (resultMinistry == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(resultMinistry);
			return resultMinistry;
		}
		catch (NoSuchResultMinistryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultMinistry remove(ResultMinistry ResultMinistry) throws SystemException {
	removeImpl(ResultMinistry);
	return ResultMinistry;
}

protected ResultMinistry removeImpl(ResultMinistry resultMinistry)
		throws SystemException {
		try {
			repository.delete(resultMinistry);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultMinistry;
	}

	
	public ResultMinistry updateImpl(
		com.fds.nsw.nghiepvu.model.ResultMinistry resultMinistry, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(resultMinistry);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultMinistry;
	}

	
	public ResultMinistry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result ministry with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultMinistryException} if it could not be found.
	 *
	 * @param id the primary key of the result ministry
	 * @return the result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByPrimaryKey(long id)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByPrimaryKey(id);

		if (resultMinistry == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultMinistryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultMinistry;
	}

	/**
	 * Returns the result ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result ministry
	 * @return the result ministry, or <code>null</code> if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultMinistry fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result ministry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result ministry
	 * @return the result ministry, or <code>null</code> if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByPrimaryKey(long id) throws SystemException {
		ResultMinistry resultMinistry = null;

		

		if (resultMinistry == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ResultMinistry> optional = repository.findById(id);
				if (optional.isPresent()) {
					resultMinistry = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultMinistry;
	}

	/**
	 * Returns the result ministry where requestCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultMinistryException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByRequestCode(String requestCode)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByRequestCode(requestCode);

		if (resultMinistry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultMinistryException(msg.toString());
		}

		return resultMinistry;
	}

	/**
	 * Returns the result ministry where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the result ministry where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		ResultMinistry resultMinistry = null;
		if (resultMinistry == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

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

			query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultMinistry.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				resultMinistry = (ResultMinistry) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultMinistry;
	}

	/**
	 * Returns all the result ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocumentNameAnddocumentYear(
		int documentName, int documentYear) throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @return the range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocumentNameAnddocumentYear(
		int documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAnddocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocumentNameAnddocumentYear(
		int documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByDocumentNameAnddocumentYear_First(
		int documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByDocumentNameAnddocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the first result ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocumentNameAnddocumentYear_First(
		int documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultMinistry> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByDocumentNameAnddocumentYear_Last(
		int documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByDocumentNameAnddocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the last result ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocumentNameAnddocumentYear_Last(
		int documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAnddocumentYear(documentName,
				documentYear);

		List<ResultMinistry> list = findByDocumentNameAnddocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result ministries before and after the current result ministry in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result ministry
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry[] findByDocumentNameAnddocumentYear_PrevAndNext(
		long id, int documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultMinistry[] array = new ResultMinistry[3];

			array[0] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultMinistry, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultMinistry;

			array[2] = getByDocumentNameAnddocumentYear_PrevAndNext(
					resultMinistry, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultMinistry getByDocumentNameAnddocumentYear_PrevAndNext(
		 ResultMinistry resultMinistry, int documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

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
			query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @return the matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocNameAndDocYearAndMinistryCode(
		int documentName, int documentYear, String ministryCode)
		throws SystemException {
		return findByDocNameAndDocYearAndMinistryCode(documentName,
			documentYear, ministryCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @return the range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocNameAndDocYearAndMinistryCode(
		int documentName, int documentYear, String ministryCode, int start,
		int end) throws SystemException {
		return findByDocNameAndDocYearAndMinistryCode(documentName,
			documentYear, ministryCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByDocNameAndDocYearAndMinistryCode(
		int documentName, int documentYear, String ministryCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<ResultMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				list = (List<ResultMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByDocNameAndDocYearAndMinistryCode_First(
		int documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByDocNameAndDocYearAndMinistryCode_First(documentName,
				documentYear, ministryCode, orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
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

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the first result ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocNameAndDocYearAndMinistryCode_First(
		int documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultMinistry> list = findByDocNameAndDocYearAndMinistryCode(documentName,
				documentYear, ministryCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByDocNameAndDocYearAndMinistryCode_Last(
		int documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByDocNameAndDocYearAndMinistryCode_Last(documentName,
				documentYear, ministryCode, orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
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

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the last result ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocNameAndDocYearAndMinistryCode_Last(
		int documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocNameAndDocYearAndMinistryCode(documentName,
				documentYear, ministryCode);

		List<ResultMinistry> list = findByDocNameAndDocYearAndMinistryCode(documentName,
				documentYear, ministryCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result ministries before and after the current result ministry in the ordered set where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param id the primary key of the current result ministry
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry[] findByDocNameAndDocYearAndMinistryCode_PrevAndNext(
		long id, int documentName, int documentYear, String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultMinistry[] array = new ResultMinistry[3];

			array[0] = getByDocNameAndDocYearAndMinistryCode_PrevAndNext(
					resultMinistry, documentName, documentYear, ministryCode,
					orderByComparator, true);

			array[1] = resultMinistry;

			array[2] = getByDocNameAndDocYearAndMinistryCode_PrevAndNext(
					resultMinistry, documentName, documentYear, ministryCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultMinistry getByDocNameAndDocYearAndMinistryCode_PrevAndNext(
		 ResultMinistry resultMinistry, int documentName,
		int documentYear, String ministryCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

		query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

		if (ministryCode == null) {
			query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_1);
		}
		else {
			if (ministryCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_2);
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
			query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (ministryCode != null) {
			builder.appendNamedParameterMap("ministryCode", ministryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByMinistryCode(String ministryCode)
		throws SystemException {
		return findByMinistryCode(ministryCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result ministries where ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @return the range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByMinistryCode(String ministryCode,
		int start, int end) throws SystemException {
		return findByMinistryCode(ministryCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the result ministries where ministryCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param ministryCode the ministry code
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findByMinistryCode(String ministryCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultMinistry> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

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
				query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				list = (List<ResultMinistry>)queryFactory.getResultList(builder);
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
	 * Returns the first result ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByMinistryCode_First(String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByMinistryCode_First(ministryCode,
				orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the first result ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByMinistryCode_First(String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultMinistry> list = findByMinistryCode(ministryCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByMinistryCode_Last(String ministryCode,
		OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByMinistryCode_Last(ministryCode,
				orderByComparator);

		if (resultMinistry != null) {
			return resultMinistry;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("ministryCode=");
		msg.append(ministryCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultMinistryException(msg.toString());
	}

	/**
	 * Returns the last result ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByMinistryCode_Last(String ministryCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByMinistryCode(ministryCode);

		List<ResultMinistry> list = findByMinistryCode(ministryCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result ministries before and after the current result ministry in the ordered set where ministryCode = &#63;.
	 *
	 * @param id the primary key of the current result ministry
	 * @param ministryCode the ministry code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a result ministry with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry[] findByMinistryCode_PrevAndNext(long id,
		String ministryCode, OrderByComparator orderByComparator)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = findByPrimaryKey(id);

		

		try {
			

			ResultMinistry[] array = new ResultMinistry[3];

			array[0] = getByMinistryCode_PrevAndNext(resultMinistry,
					ministryCode, orderByComparator, true);

			array[1] = resultMinistry;

			array[2] = getByMinistryCode_PrevAndNext(resultMinistry,
					ministryCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultMinistry getByMinistryCode_PrevAndNext(
		ResultMinistry resultMinistry, String ministryCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

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
			query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (ministryCode != null) {
			builder.appendNamedParameterMap("ministryCode", ministryCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultMinistry);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultMinistry> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultMinistryException} if it could not be found.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param businessTypeCode the business type code
	 * @return the matching result ministry
	 * @throws vn.gt.dao.result.NoSuchResultMinistryException if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, String ministryCode,
		int businessTypeCode)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = fetchByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(documentName,
				documentYear, ministryCode, businessTypeCode);

		if (resultMinistry == null) {
			StringBundler msg = new StringBundler(10);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentName=");
			msg.append(documentName);

			msg.append(", documentYear=");
			msg.append(documentYear);

			msg.append(", ministryCode=");
			msg.append(ministryCode);

			msg.append(", businessTypeCode=");
			msg.append(businessTypeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultMinistryException(msg.toString());
		}

		return resultMinistry;
	}

	/**
	 * Returns the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param businessTypeCode the business type code
	 * @return the matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, String ministryCode,
		int businessTypeCode) throws SystemException {
		return fetchByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(documentName,
			documentYear, ministryCode, businessTypeCode, true);
	}

	/**
	 * Returns the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param businessTypeCode the business type code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result ministry, or <code>null</code> if a matching result ministry could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry fetchByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, String ministryCode,
		int businessTypeCode, boolean retrieveFromCache)
		throws SystemException {
		ResultMinistry resultMinistry = null;
		if (resultMinistry == null) {
			StringBundler query = new StringBundler(6);

			query.append(_SQL_SELECT_RESULTMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_BUSINESSTYPECODE_2);

			query.append(ResultMinistryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultMinistry.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
				}

				builder.appendNamedParameterMap("businessTypeCode", businessTypeCode);

				resultMinistry = (ResultMinistry) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultMinistry;
	}

	/**
	 * Returns all the result ministries.
	 *
	 * @return the result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @return the range of result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result ministries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result ministries
	 * @param end the upper bound of the range of result ministries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultMinistry> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultMinistry> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTMINISTRY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTMINISTRY.concat(ResultMinistryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultMinistry>) queryFactory.getResultList(builder);
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
	 * Removes the result ministry where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the result ministry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry removeByRequestCode(String requestCode)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = findByRequestCode(requestCode);

		repository.delete(resultMinistry);
			return resultMinistry;
	}

	/**
	 * Removes all the result ministries where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAnddocumentYear(int documentName,
		int documentYear) throws SystemException {
		for (ResultMinistry resultMinistry : findByDocumentNameAnddocumentYear(
				documentName, documentYear)) {
			repository.delete(resultMinistry);
		}
	}

	/**
	 * Removes all the result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocNameAndDocYearAndMinistryCode(int documentName,
		int documentYear, String ministryCode) throws SystemException {
		for (ResultMinistry resultMinistry : findByDocNameAndDocYearAndMinistryCode(
				documentName, documentYear, ministryCode)) {
			repository.delete(resultMinistry);
		}
	}

	/**
	 * Removes all the result ministries where ministryCode = &#63; from the database.
	 *
	 * @param ministryCode the ministry code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMinistryCode(String ministryCode)
		throws SystemException {
		for (ResultMinistry resultMinistry : findByMinistryCode(ministryCode)) {
			repository.delete(resultMinistry);
		}
	}

	/**
	 * Removes the result ministry where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param businessTypeCode the business type code
	 * @return the result ministry that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultMinistry removeByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, String ministryCode,
		int businessTypeCode)
		throws NoSuchResultMinistryException, SystemException {
		ResultMinistry resultMinistry = findByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(documentName,
				documentYear, ministryCode, businessTypeCode);

		repository.delete(resultMinistry);
			return resultMinistry;
	}

	/**
	 * Removes all the result ministries from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultMinistry resultMinistry : findAll()) {
			repository.delete(resultMinistry);
		}
	}

	/**
	 * Returns the number of result ministries where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTMINISTRY_WHERE);

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
	 * Returns the number of result ministries where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYear(int documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTMINISTRY_WHERE);

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
	 * Returns the number of result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @return the number of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocNameAndDocYearAndMinistryCode(int documentName,
		int documentYear, String ministryCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_RESULTMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_2);
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
	 * Returns the number of result ministries where ministryCode = &#63;.
	 *
	 * @param ministryCode the ministry code
	 * @return the number of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMinistryCode(String ministryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTMINISTRY_WHERE);

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
	 * Returns the number of result ministries where documentName = &#63; and documentYear = &#63; and ministryCode = &#63; and businessTypeCode = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param ministryCode the ministry code
	 * @param businessTypeCode the business type code
	 * @return the number of matching result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAnddocumentYearMinistryCodeBusinessTypeCode(
		int documentName, int documentYear, String ministryCode,
		int businessTypeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(5);

			query.append(_SQL_COUNT_RESULTMINISTRY_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTYEAR_2);

			if (ministryCode == null) {
				query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_1);
			}
			else {
				if (ministryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_2);
				}
			}

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_BUSINESSTYPECODE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				if (ministryCode != null) {
					builder.appendNamedParameterMap("ministryCode", ministryCode);
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
	 * Returns the number of result ministries.
	 *
	 * @return the number of result ministries
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTMINISTRY).build();

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
	 * Initializes the result ministry persistence.
	 */
	private static final String _SQL_SELECT_RESULTMINISTRY = "SELECT resultMinistry FROM ResultMinistry resultMinistry";
	private static final String _SQL_SELECT_RESULTMINISTRY_WHERE = "SELECT resultMinistry FROM ResultMinistry resultMinistry WHERE ";
	private static final String _SQL_COUNT_RESULTMINISTRY = "SELECT COUNT(resultMinistry) FROM ResultMinistry resultMinistry";
	private static final String _SQL_COUNT_RESULTMINISTRY_WHERE = "SELECT COUNT(resultMinistry) FROM ResultMinistry resultMinistry WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "resultMinistry.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "resultMinistry.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(resultMinistry.requestCode IS NULL OR resultMinistry.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultMinistry.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultMinistry.documentYear =:documentYear";
	private static final String _FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTNAME_2 =
		"resultMinistry.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_DOCUMENTYEAR_2 =
		"resultMinistry.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_1 =
		"resultMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_2 =
		"resultMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_DOCNAMEANDDOCYEARANDMINISTRYCODE_MINISTRYCODE_3 =
		"(resultMinistry.ministryCode IS NULL OR resultMinistry.ministryCode =:ministryCode)";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_1 = "resultMinistry.ministryCode IS NULL";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_2 = "resultMinistry.ministryCode =:ministryCode";
	private static final String _FINDER_COLUMN_MINISTRYCODE_MINISTRYCODE_3 = "(resultMinistry.ministryCode IS NULL OR resultMinistry.ministryCode =:ministryCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTNAME_2 =
		"resultMinistry.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_DOCUMENTYEAR_2 =
		"resultMinistry.documentYear =:documentYear AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_1 =
		"resultMinistry.ministryCode IS NULL AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_2 =
		"resultMinistry.ministryCode =:ministryCode AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_MINISTRYCODE_3 =
		"(resultMinistry.ministryCode IS NULL OR resultMinistry.ministryCode =:ministryCode) AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEARMINISTRYCODEBUSINESSTYPECODE_BUSINESSTYPECODE_2 =
		"resultMinistry.businessTypeCode =:businessTypeCode";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultMinistry.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultMinistry exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultMinistry exists with the key {";
	

	
}
