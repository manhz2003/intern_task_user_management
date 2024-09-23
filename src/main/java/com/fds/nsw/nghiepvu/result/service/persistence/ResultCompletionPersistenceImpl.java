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
import java.util.List;
import java.util.Optional;

import com.fds.nsw.nghiepvu.modelImpl.ResultCompetionModelImpl;
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
import com.fds.nsw.nghiepvu.model.ResultCompletion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.ResultCompletionRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultCompletionPersistenceImpl extends BasePersistence {
	@Autowired
	ResultCompletionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<ResultCompletion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ResultCompetionUtil} to access the result competion persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public ResultCompletion create(long id) {
		ResultCompletion resultCompetion = new ResultCompletion();

		
		//resultCompetion.setPrimaryKey(id);

		return resultCompetion;
	}

	/**
	 * Removes the result competion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the result competion
	 * @return the result competion that was removed
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion remove(long id)
		throws NoSuchResultCompetionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the result competion with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the result competion
	 * @return the result competion that was removed
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultCompletion remove(Serializable primaryKey)
		throws NoSuchResultCompetionException, SystemException {
		

		try {
			

			ResultCompletion resultCompetion = findByPrimaryKey(primaryKey);

			if (resultCompetion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchResultCompetionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(resultCompetion);
			return resultCompetion;
		}
		catch (NoSuchResultCompetionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public ResultCompletion remove(ResultCompletion ResultCompletion) throws SystemException {
	removeImpl(ResultCompletion);
	return ResultCompletion;
}

protected ResultCompletion removeImpl(ResultCompletion resultCompetion)
		throws SystemException {
		try {
			repository.delete(resultCompetion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultCompetion;
	}

	
	public ResultCompletion updateImpl(
		com.fds.nsw.nghiepvu.model.ResultCompletion resultCompetion, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(resultCompetion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return resultCompetion;
	}

	
	public ResultCompletion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result competion with the primary key or throws a {@link vn.gt.dao.result.NoSuchResultCompetionException} if it could not be found.
	 *
	 * @param id the primary key of the result competion
	 * @return the result competion
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion findByPrimaryKey(long id)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = fetchByPrimaryKey(id);

		if (resultCompetion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchResultCompetionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return resultCompetion;
	}

	/**
	 * Returns the result competion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the result competion
	 * @return the result competion, or <code>null</code> if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public ResultCompletion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the result competion with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the result competion
	 * @return the result competion, or <code>null</code> if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion fetchByPrimaryKey(long id) throws SystemException {
		ResultCompletion resultCompetion = null;

		

		if (resultCompetion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<ResultCompletion> optional = repository.findById(id);
				if (optional.isPresent()) {
					resultCompetion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return resultCompetion;
	}

	/**
	 * Returns the result competion where requestCode = &#63; or throws a {@link vn.gt.dao.result.NoSuchResultCompetionException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching result competion
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion findByRequestCode(String requestCode)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = fetchByRequestCode(requestCode);

		if (resultCompetion == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchResultCompetionException(msg.toString());
		}

		return resultCompetion;
	}

	/**
	 * Returns the result competion where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching result competion, or <code>null</code> if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the result competion where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching result competion, or <code>null</code> if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		ResultCompletion resultCompetion = null;
		if (resultCompetion == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_RESULTCOMPETION_WHERE);

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

			query.append(ResultCompetionModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(ResultCompletion.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				resultCompetion = (ResultCompletion) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return resultCompetion;
	}

	/**
	 * Returns all the result competions where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear) throws SystemException {
		return findByDocumentNameAndDocumentYear(documentName, documentYear,
			QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result competions where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result competions
	 * @param end the upper bound of the range of result competions (not inclusive)
	 * @return the range of matching result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end)
		throws SystemException {
		return findByDocumentNameAndDocumentYear(documentName, documentYear,
			start, end, null);
	}

	/**
	 * Returns an ordered range of all the result competions where documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start the lower bound of the range of result competions
	 * @param end the upper bound of the range of result competions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findByDocumentNameAndDocumentYear(
		long documentName, int documentYear, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCompletion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_RESULTCOMPETION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(ResultCompetionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<ResultCompletion>)queryFactory.getResultList(builder);
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
	 * Returns the first result competion in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result competion
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion findByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = fetchByDocumentNameAndDocumentYear_First(documentName,
				documentYear, orderByComparator);

		if (resultCompetion != null) {
			return resultCompetion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCompetionException(msg.toString());
	}

	/**
	 * Returns the first result competion in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching result competion, or <code>null</code> if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion fetchByDocumentNameAndDocumentYear_First(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		List<ResultCompletion> list = findByDocumentNameAndDocumentYear(documentName,
				documentYear, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last result competion in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result competion
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion findByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = fetchByDocumentNameAndDocumentYear_Last(documentName,
				documentYear, orderByComparator);

		if (resultCompetion != null) {
			return resultCompetion;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchResultCompetionException(msg.toString());
	}

	/**
	 * Returns the last result competion in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching result competion, or <code>null</code> if a matching result competion could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion fetchByDocumentNameAndDocumentYear_Last(
		long documentName, int documentYear, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByDocumentNameAndDocumentYear(documentName,
				documentYear);

		List<ResultCompletion> list = findByDocumentNameAndDocumentYear(documentName,
				documentYear, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the result competions before and after the current result competion in the ordered set where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param id the primary key of the current result competion
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next result competion
	 * @throws vn.gt.dao.result.NoSuchResultCompetionException if a result competion with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion[] findByDocumentNameAndDocumentYear_PrevAndNext(
		long id, long documentName, int documentYear,
		OrderByComparator orderByComparator)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = findByPrimaryKey(id);

		

		try {
			

			ResultCompletion[] array = new ResultCompletion[3];

			array[0] = getByDocumentNameAndDocumentYear_PrevAndNext(
					resultCompetion, documentName, documentYear,
					orderByComparator, true);

			array[1] = resultCompetion;

			array[2] = getByDocumentNameAndDocumentYear_PrevAndNext(
					resultCompetion, documentName, documentYear,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected ResultCompletion getByDocumentNameAndDocumentYear_PrevAndNext(
		 ResultCompletion resultCompetion, long documentName,
		int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_RESULTCOMPETION_WHERE);

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
			query.append(ResultCompetionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(resultCompetion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<ResultCompletion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the result competions.
	 *
	 * @return the result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the result competions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result competions
	 * @param end the upper bound of the range of result competions (not inclusive)
	 * @return the range of result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the result competions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of result competions
	 * @param end the upper bound of the range of result competions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of result competions
	 * @throws SystemException if a system exception occurred
	 */
	public List<ResultCompletion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<ResultCompletion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_RESULTCOMPETION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_RESULTCOMPETION.concat(ResultCompetionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<ResultCompletion>) queryFactory.getResultList(builder);
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
	 * Removes the result competion where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the result competion that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public ResultCompletion removeByRequestCode(String requestCode)
		throws NoSuchResultCompetionException, SystemException {
		ResultCompletion resultCompetion = findByRequestCode(requestCode);

		repository.delete(resultCompetion);
			return resultCompetion;
	}

	/**
	 * Removes all the result competions where documentName = &#63; and documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		for (ResultCompletion resultCompetion : findByDocumentNameAndDocumentYear(
				documentName, documentYear)) {
			repository.delete(resultCompetion);
		}
	}

	/**
	 * Removes all the result competions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (ResultCompletion resultCompetion : findAll()) {
			repository.delete(resultCompetion);
		}
	}

	/**
	 * Returns the number of result competions where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching result competions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_RESULTCOMPETION_WHERE);

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
	 * Returns the number of result competions where documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching result competions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameAndDocumentYear(long documentName,
		int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_RESULTCOMPETION_WHERE);

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
	 * Returns the number of result competions.
	 *
	 * @return the number of result competions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_RESULTCOMPETION).build();

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
	 * Initializes the result competion persistence.
	 */
	private static final String _SQL_SELECT_RESULTCOMPETION = "SELECT resultCompetion FROM ResultCompletion resultCompetion";
	private static final String _SQL_SELECT_RESULTCOMPETION_WHERE = "SELECT resultCompetion FROM ResultCompletion resultCompetion WHERE ";
	private static final String _SQL_COUNT_RESULTCOMPETION = "SELECT COUNT(resultCompetion) FROM ResultCompletion resultCompetion";
	private static final String _SQL_COUNT_RESULTCOMPETION_WHERE = "SELECT COUNT(resultCompetion) FROM ResultCompletion resultCompetion WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "resultCompetion.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "resultCompetion.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(resultCompetion.requestCode IS NULL OR resultCompetion.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTNAME_2 =
		"resultCompetion.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEANDDOCUMENTYEAR_DOCUMENTYEAR_2 =
		"resultCompetion.documentYear =:documentYear";
	private static final String _ORDER_BY_ENTITY_ALIAS = "resultCompetion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ResultCompletion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No ResultCompletion exists with the key {";
	

	
}
