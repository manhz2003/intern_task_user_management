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
import com.fds.nsw.nghiepvu.model.TempHealthQuestion;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempHealthQuestionRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempHealthQuestionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempHealthQuestionPersistenceImpl extends BasePersistence {
	@Autowired
	TempHealthQuestionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempHealthQuestion> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempHealthQuestionUtil} to access the temp health question persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempHealthQuestion create(long id) {
		TempHealthQuestion tempHealthQuestion = new TempHealthQuestion();

		
		//tempHealthQuestion.setPrimaryKey(id);

		return tempHealthQuestion;
	}

	/**
	 * Removes the temp health question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp health question
	 * @return the temp health question that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion remove(long id)
		throws NoSuchTempHealthQuestionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp health question with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp health question
	 * @return the temp health question that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempHealthQuestion remove(Serializable primaryKey)
		throws NoSuchTempHealthQuestionException, SystemException {
		

		try {
			

			TempHealthQuestion tempHealthQuestion = findByPrimaryKey(primaryKey);

			if (tempHealthQuestion == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempHealthQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempHealthQuestion);
			return tempHealthQuestion;
		}
		catch (NoSuchTempHealthQuestionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempHealthQuestion remove(TempHealthQuestion TempHealthQuestion) throws SystemException {
	removeImpl(TempHealthQuestion);
	return TempHealthQuestion;
}

protected TempHealthQuestion removeImpl(
		TempHealthQuestion tempHealthQuestion) throws SystemException {
		try {
			repository.delete(tempHealthQuestion);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempHealthQuestion;
	}

	
	public TempHealthQuestion updateImpl(
		com.fds.nsw.nghiepvu.model.TempHealthQuestion tempHealthQuestion,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempHealthQuestion);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempHealthQuestion;
	}

	
	public TempHealthQuestion findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp health question with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException} if it could not be found.
	 *
	 * @param id the primary key of the temp health question
	 * @return the temp health question
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion findByPrimaryKey(long id)
		throws NoSuchTempHealthQuestionException, SystemException {
		TempHealthQuestion tempHealthQuestion = fetchByPrimaryKey(id);

		if (tempHealthQuestion == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempHealthQuestionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempHealthQuestion;
	}

	/**
	 * Returns the temp health question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp health question
	 * @return the temp health question, or <code>null</code> if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempHealthQuestion fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp health question with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp health question
	 * @return the temp health question, or <code>null</code> if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion fetchByPrimaryKey(long id)
		throws SystemException {
		TempHealthQuestion tempHealthQuestion = null;

		

		if (tempHealthQuestion == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempHealthQuestion> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempHealthQuestion = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempHealthQuestion;
	}

	/**
	 * Returns all the temp health questions where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp health questions where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp health questions
	 * @param end the upper bound of the range of temp health questions (not inclusive)
	 * @return the range of matching temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp health questions where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp health questions
	 * @param end the upper bound of the range of temp health questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempHealthQuestion> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPHEALTHQUESTION_WHERE);

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

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempHealthQuestionModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempHealthQuestion>)queryFactory.getResultList(builder);
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
	 * Returns the first temp health question in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp health question
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a matching temp health question could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempHealthQuestionException, SystemException {
		TempHealthQuestion tempHealthQuestion = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempHealthQuestion != null) {
			return tempHealthQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempHealthQuestionException(msg.toString());
	}

	/**
	 * Returns the first temp health question in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp health question, or <code>null</code> if a matching temp health question could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempHealthQuestion> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp health question in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp health question
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a matching temp health question could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempHealthQuestionException, SystemException {
		TempHealthQuestion tempHealthQuestion = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempHealthQuestion != null) {
			return tempHealthQuestion;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempHealthQuestionException(msg.toString());
	}

	/**
	 * Returns the last temp health question in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp health question, or <code>null</code> if a matching temp health question could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempHealthQuestion> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp health questions before and after the current temp health question in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp health question
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp health question
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempHealthQuestionException if a temp health question with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempHealthQuestion[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempHealthQuestionException, SystemException {
		TempHealthQuestion tempHealthQuestion = findByPrimaryKey(id);

		

		try {
			

			TempHealthQuestion[] array = new TempHealthQuestion[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempHealthQuestion, requestCode, orderByComparator, true);

			array[1] = tempHealthQuestion;

			array[2] = getByRequestCode_PrevAndNext(
					tempHealthQuestion, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempHealthQuestion getByRequestCode_PrevAndNext(
		TempHealthQuestion tempHealthQuestion, String requestCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPHEALTHQUESTION_WHERE);

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
			query.append(TempHealthQuestionModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempHealthQuestion);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempHealthQuestion> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp health questions.
	 *
	 * @return the temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp health questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp health questions
	 * @param end the upper bound of the range of temp health questions (not inclusive)
	 * @return the range of temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp health questions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp health questions
	 * @param end the upper bound of the range of temp health questions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempHealthQuestion> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempHealthQuestion> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPHEALTHQUESTION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPHEALTHQUESTION.concat(TempHealthQuestionModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempHealthQuestion>) queryFactory.getResultList(builder);
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
	 * Removes all the temp health questions where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempHealthQuestion tempHealthQuestion : findByRequestCode(
				requestCode)) {
			repository.delete(tempHealthQuestion);
		}
	}

	/**
	 * Removes all the temp health questions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempHealthQuestion tempHealthQuestion : findAll()) {
			repository.delete(tempHealthQuestion);
		}
	}

	/**
	 * Returns the number of temp health questions where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPHEALTHQUESTION_WHERE);

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
	 * Returns the number of temp health questions.
	 *
	 * @return the number of temp health questions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPHEALTHQUESTION).build();

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
	 * Initializes the temp health question persistence.
	 */
	private static final String _SQL_SELECT_TEMPHEALTHQUESTION = "SELECT tempHealthQuestion FROM TempHealthQuestion tempHealthQuestion";
	private static final String _SQL_SELECT_TEMPHEALTHQUESTION_WHERE = "SELECT tempHealthQuestion FROM TempHealthQuestion tempHealthQuestion WHERE ";
	private static final String _SQL_COUNT_TEMPHEALTHQUESTION = "SELECT COUNT(tempHealthQuestion) FROM TempHealthQuestion tempHealthQuestion";
	private static final String _SQL_COUNT_TEMPHEALTHQUESTION_WHERE = "SELECT COUNT(tempHealthQuestion) FROM TempHealthQuestion tempHealthQuestion WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempHealthQuestion.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempHealthQuestion.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempHealthQuestion.requestCode IS NULL OR tempHealthQuestion.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempHealthQuestion.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempHealthQuestion exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempHealthQuestion exists with the key {";
	

	
}
