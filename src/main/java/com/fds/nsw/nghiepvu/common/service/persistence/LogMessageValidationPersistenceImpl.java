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

package com.fds.nsw.nghiepvu.common.service.persistence;

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
import com.fds.nsw.nghiepvu.model.LogMessageValidation;
import com.fds.nsw.nghiepvu.service.exception.*;

import com.fds.nsw.nghiepvu.modelImpl.LogMessageValidationModelImpl;

import com.fds.nsw.nghiepvu.repository.LogMessageValidationRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LogMessageValidationPersistenceImpl extends BasePersistence {
	@Autowired
	LogMessageValidationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<LogMessageValidation> queryFactory;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * LogMessageValidationUtil} to access the log message validation persistence.
	 * Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this
	 * class.
	 */
	public LogMessageValidation create(long id) {
		LogMessageValidation logMessageValidation = new LogMessageValidation();

		// logMessageValidation.setPrimaryKey(id);

		return logMessageValidation;
	}

	/**
	 * Removes the log message validation with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the log message validation
	 * @return the log message validation that was removed
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a log message
	 *                                                              validation with
	 *                                                              the primary key
	 *                                                              could not be
	 *                                                              found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */
	public LogMessageValidation remove(long id) throws NoSuchLogMessageValidationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the log message validation with the primary key from the database.
	 * Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the log message validation
	 * @return the log message validation that was removed
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a log message
	 *                                                              validation with
	 *                                                              the primary key
	 *                                                              could not be
	 *                                                              found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */

	public LogMessageValidation remove(Serializable primaryKey)
			throws NoSuchLogMessageValidationException, SystemException {

		try {

			LogMessageValidation logMessageValidation = findByPrimaryKey(primaryKey);

			if (logMessageValidation == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchLogMessageValidationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(logMessageValidation);
			return logMessageValidation;
		} catch (NoSuchLogMessageValidationException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public LogMessageValidation remove(LogMessageValidation LogMessageValidation) throws SystemException {
	removeImpl(LogMessageValidation);
	return LogMessageValidation;
}

protected LogMessageValidation removeImpl(LogMessageValidation logMessageValidation) throws SystemException {
		try {
			repository.delete(logMessageValidation);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return logMessageValidation;
	}

	public LogMessageValidation updateImpl(LogMessageValidation logMessageValidation, boolean merge)
			throws SystemException {
		try {

			repository.saveAndFlush(logMessageValidation);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return logMessageValidation;
	}

	public LogMessageValidation findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the log message validation with the primary key or throws a
	 * {@link vn.gt.dao.common.NoSuchLogMessageValidationException} if it could not
	 * be found.
	 *
	 * @param id the primary key of the log message validation
	 * @return the log message validation
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a log message
	 *                                                              validation with
	 *                                                              the primary key
	 *                                                              could not be
	 *                                                              found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */
	public LogMessageValidation findByPrimaryKey(long id) throws NoSuchLogMessageValidationException, SystemException {
		LogMessageValidation logMessageValidation = fetchByPrimaryKey(id);

		if (logMessageValidation == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchLogMessageValidationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return logMessageValidation;
	}

	/**
	 * Returns the log message validation with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the log message validation
	 * @return the log message validation, or <code>null</code> if a log message
	 *         validation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public LogMessageValidation fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the log message validation with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the log message validation
	 * @return the log message validation, or <code>null</code> if a log message
	 *         validation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LogMessageValidation fetchByPrimaryKey(long id) throws SystemException {
		LogMessageValidation logMessageValidation = null;

		if (logMessageValidation == null) {

			boolean hasException = false;

			try {

				Optional<LogMessageValidation> optional = repository.findById(id);
				if (optional.isPresent()) {
					logMessageValidation = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return logMessageValidation;
	}

	/**
	 * Returns all the log message validations where documentName = &#63; and
	 * documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the matching log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findByDocumentNameDocumentYear(long documentName, int documentYear)
			throws SystemException {
		return findByDocumentNameDocumentYear(documentName, documentYear, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the log message validations where documentName = &#63;
	 * and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @param start        the lower bound of the range of log message validations
	 * @param end          the upper bound of the range of log message validations
	 *                     (not inclusive)
	 * @return the range of matching log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findByDocumentNameDocumentYear(long documentName, int documentYear, int start,
			int end) throws SystemException {
		return findByDocumentNameDocumentYear(documentName, documentYear, start, end, null);
	}

	/**
	 * Returns an ordered range of all the log message validations where
	 * documentName = &#63; and documentYear = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param start             the lower bound of the range of log message
	 *                          validations
	 * @param end               the upper bound of the range of log message
	 *                          validations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findByDocumentNameDocumentYear(long documentName, int documentYear, int start,
			int end, OrderByComparator orderByComparator) throws SystemException {
		List<LogMessageValidation> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_LOGMESSAGEVALIDATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTYEAR_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(LogMessageValidationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(LogMessageValidation.class).build();

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				list = (List<LogMessageValidation>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first log message validation in the ordered set where
	 * documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching log message validation
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a matching
	 *                                                              log message
	 *                                                              validation could
	 *                                                              not be found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */
	public LogMessageValidation findByDocumentNameDocumentYear_First(long documentName, int documentYear,
			OrderByComparator orderByComparator) throws NoSuchLogMessageValidationException, SystemException {
		LogMessageValidation logMessageValidation = fetchByDocumentNameDocumentYear_First(documentName, documentYear,
				orderByComparator);

		if (logMessageValidation != null) {
			return logMessageValidation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogMessageValidationException(msg.toString());
	}

	/**
	 * Returns the first log message validation in the ordered set where
	 * documentName = &#63; and documentYear = &#63;.
	 *
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching log message validation, or <code>null</code> if a
	 *         matching log message validation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LogMessageValidation fetchByDocumentNameDocumentYear_First(long documentName, int documentYear,
			OrderByComparator orderByComparator) throws SystemException {
		List<LogMessageValidation> list = findByDocumentNameDocumentYear(documentName, documentYear, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last log message validation in the ordered set where documentName
	 * = &#63; and documentYear = &#63;.
	 *
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching log message validation
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a matching
	 *                                                              log message
	 *                                                              validation could
	 *                                                              not be found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */
	public LogMessageValidation findByDocumentNameDocumentYear_Last(long documentName, int documentYear,
			OrderByComparator orderByComparator) throws NoSuchLogMessageValidationException, SystemException {
		LogMessageValidation logMessageValidation = fetchByDocumentNameDocumentYear_Last(documentName, documentYear,
				orderByComparator);

		if (logMessageValidation != null) {
			return logMessageValidation;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("documentName=");
		msg.append(documentName);

		msg.append(", documentYear=");
		msg.append(documentYear);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchLogMessageValidationException(msg.toString());
	}

	/**
	 * Returns the last log message validation in the ordered set where documentName
	 * = &#63; and documentYear = &#63;.
	 *
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching log message validation, or <code>null</code> if a
	 *         matching log message validation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public LogMessageValidation fetchByDocumentNameDocumentYear_Last(long documentName, int documentYear,
			OrderByComparator orderByComparator) throws SystemException {
		int count = countByDocumentNameDocumentYear(documentName, documentYear);

		List<LogMessageValidation> list = findByDocumentNameDocumentYear(documentName, documentYear, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the log message validations before and after the current log message
	 * validation in the ordered set where documentName = &#63; and documentYear =
	 * &#63;.
	 *
	 * @param id                the primary key of the current log message
	 *                          validation
	 * @param documentName      the document name
	 * @param documentYear      the document year
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next log message validation
	 * @throws vn.gt.dao.common.NoSuchLogMessageValidationException if a log message
	 *                                                              validation with
	 *                                                              the primary key
	 *                                                              could not be
	 *                                                              found
	 * @throws SystemException                                      if a system
	 *                                                              exception
	 *                                                              occurred
	 */
	public LogMessageValidation[] findByDocumentNameDocumentYear_PrevAndNext(long id, long documentName,
			int documentYear, OrderByComparator orderByComparator)
			throws NoSuchLogMessageValidationException, SystemException {
		LogMessageValidation logMessageValidation = findByPrimaryKey(id);

		try {

			LogMessageValidation[] array = new LogMessageValidation[3];

			array[0] = getByDocumentNameDocumentYear_PrevAndNext(logMessageValidation, documentName, documentYear,
					orderByComparator, true);

			array[1] = logMessageValidation;

			array[2] = getByDocumentNameDocumentYear_PrevAndNext(logMessageValidation, documentName, documentYear,
					orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected LogMessageValidation getByDocumentNameDocumentYear_PrevAndNext(LogMessageValidation logMessageValidation,
			long documentName, int documentYear, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_LOGMESSAGEVALIDATION_WHERE);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTNAME_2);

		query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTYEAR_2);

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
					} else {
						query.append(WHERE_LESSER_THAN_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(WHERE_GREATER_THAN);
					} else {
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
					} else {
						query.append(ORDER_BY_DESC_HAS_NEXT);
					}
				} else {
					if (orderByComparator.isAscending() ^ previous) {
						query.append(ORDER_BY_ASC);
					} else {
						query.append(ORDER_BY_DESC);
					}
				}
			}
		}

		else {
			query.append(LogMessageValidationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(LogMessageValidation.class).build();

		builder.appendNamedParameterMap("documentName", documentName);

		builder.appendNamedParameterMap("documentYear", documentYear);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(logMessageValidation);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<LogMessageValidation> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the log message validations.
	 *
	 * @return the log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the log message validations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of log message validations
	 * @param end   the upper bound of the range of log message validations (not
	 *              inclusive)
	 * @return the range of log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the log message validations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code>
	 * instances. <code>start</code> and <code>end</code> are not primary keys, they
	 * are indexes in the result set. Thus, <code>0</code> refers to the first
	 * result in the set. Setting both <code>start</code> and <code>end</code> to
	 *  will return the
	 * full result set.
	 * </p>
	 *
	 * @param start             the lower bound of the range of log message
	 *                          validations
	 * @param end               the upper bound of the range of log message
	 *                          validations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public List<LogMessageValidation> findAll(int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		List<LogMessageValidation> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_LOGMESSAGEVALIDATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_LOGMESSAGEVALIDATION.concat(LogMessageValidationModelImpl.ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(LogMessageValidation.class).build();

				list = (List<LogMessageValidation>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes all the log message validations where documentName = &#63; and
	 * documentYear = &#63; from the database.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDocumentNameDocumentYear(long documentName, int documentYear) throws SystemException {
		for (LogMessageValidation logMessageValidation : findByDocumentNameDocumentYear(documentName, documentYear)) {
			repository.delete(logMessageValidation);
		}
	}

	/**
	 * Removes all the log message validations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (LogMessageValidation logMessageValidation : findAll()) {
			repository.delete(logMessageValidation);
		}
	}

	/**
	 * Returns the number of log message validations where documentName = &#63; and
	 * documentYear = &#63;.
	 *
	 * @param documentName the document name
	 * @param documentYear the document year
	 * @return the number of matching log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDocumentNameDocumentYear(long documentName, int documentYear) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_LOGMESSAGEVALIDATION_WHERE);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTNAME_2);

			query.append(_FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTYEAR_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(LogMessageValidation.class).build();

				builder.appendNamedParameterMap("documentName", documentName);

				builder.appendNamedParameterMap("documentYear", documentYear);

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Returns the number of log message validations.
	 *
	 * @return the number of log message validations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false)
						.queryString(_SQL_COUNT_LOGMESSAGEVALIDATION).entityClass(LogMessageValidation.class).build();

				count = (Long) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {
				if (count == null) {
					count = Long.valueOf(0);
				}

			}
		}

		return count.intValue();
	}

	/**
	 * Initializes the log message validation persistence.
	 */
	private static final String _SQL_SELECT_LOGMESSAGEVALIDATION = "SELECT logMessageValidation FROM LogMessageValidation logMessageValidation";
	private static final String _SQL_SELECT_LOGMESSAGEVALIDATION_WHERE = "SELECT logMessageValidation FROM LogMessageValidation logMessageValidation WHERE ";
	private static final String _SQL_COUNT_LOGMESSAGEVALIDATION = "SELECT COUNT(logMessageValidation) FROM LogMessageValidation logMessageValidation";
	private static final String _SQL_COUNT_LOGMESSAGEVALIDATION_WHERE = "SELECT COUNT(logMessageValidation) FROM LogMessageValidation logMessageValidation WHERE ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTNAME_2 = "logMessageValidation.documentName =:documentName AND ";
	private static final String _FINDER_COLUMN_DOCUMENTNAMEDOCUMENTYEAR_DOCUMENTYEAR_2 = "logMessageValidation.documentYear =:documentYear";
	private static final String _ORDER_BY_ENTITY_ALIAS = "logMessageValidation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No LogMessageValidation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No LogMessageValidation exists with the key {";

}
