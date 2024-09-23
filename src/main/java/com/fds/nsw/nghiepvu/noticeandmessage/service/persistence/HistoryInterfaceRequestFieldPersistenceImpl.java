/////**
//// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
//// *
//// * This library is free software; you can redistribute it and/or modify it under
//// * the terms of the GNU Lesser General Public License as published by the Free
//// * Software Foundation; either version 2.1 of the License, or (at your option)
//// * any later version.
//// *
//// * This library is distributed in the hope that it will be useful, but WITHOUT
//// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
//// * details.
//// */
////
////package com.fds.nsw.nghiepvu.noticeandmessage.service.persistence;
////
////import java.io.Serializable;
////import java.sql.Types;
////import java.util.List;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.beans.factory.annotation.Qualifier;
////import org.springframework.stereotype.Service;
////
////import com.fds.flex.common.utility.string.StringBundler;
////import com.fds.flex.common.utility.string.StringPool;
////import com.fds.nsw.kernel.dao.orm.BasePersistence;
////import com.fds.nsw.kernel.dao.orm.QueryUtil;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
////import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
////import com.fds.nsw.kernel.exception.SystemException;
////import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
////import com.fds.nsw.kernel.util.OrderByComparator;
////import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequestField;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.HistoryInterfaceRequestFieldRepository;
////import com.fds.nsw.nghiepvu.modelImpl.HistoryInterfaceRequestFieldModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class HistoryInterfaceRequestFieldPersistenceImpl extends BasePersistence {
////	@Autowired
////	HistoryInterfaceRequestFieldRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<HistoryInterfaceRequestField> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link HistoryInterfaceRequestFieldUtil} to access the history interface request field persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public HistoryInterfaceRequestField create(long id) {
////		HistoryInterfaceRequestField historyInterfaceRequestField = new HistoryInterfaceRequestField();
////
////
////		//historyInterfaceRequestField.setPrimaryKey(id);
////
////		return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Removes the history interface request field with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the history interface request field
////	 * @return the history interface request field that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField remove(long id)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the history interface request field with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the history interface request field
////	 * @return the history interface request field that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public HistoryInterfaceRequestField remove(Serializable primaryKey)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////
////
////		try {
////
////
////			HistoryInterfaceRequestField historyInterfaceRequestField = findByPrimaryKey(primaryKey);
////
////			if (historyInterfaceRequestField == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchHistoryInterfaceRequestFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(historyInterfaceRequestField);
////			return historyInterfaceRequestField;
////		}
////		catch (NoSuchHistoryInterfaceRequestFieldException nsee) {
////			throw nsee;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////
////	public HistoryInterfaceRequestField remove(HistoryInterfaceRequestField HistoryInterfaceRequestField) throws SystemException {
//	removeImpl(HistoryInterfaceRequestField);
//	return HistoryInterfaceRequestField;
//}
//
//protected HistoryInterfaceRequestField removeImpl(
////		HistoryInterfaceRequestField historyInterfaceRequestField)
////		throws SystemException {
////		try {
////			repository.delete(historyInterfaceRequestField);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return historyInterfaceRequestField;
////	}
////
////
////	public HistoryInterfaceRequestField updateImpl(
////		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequestField historyInterfaceRequestField,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(historyInterfaceRequestField);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return historyInterfaceRequestField;
////	}
////
////
////	public HistoryInterfaceRequestField findByPrimaryKey(
////		Serializable primaryKey) throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the history interface request field with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException} if it could not be found.
////	 *
////	 * @param id the primary key of the history interface request field
////	 * @return the history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByPrimaryKey(long id)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByPrimaryKey(id);
////
////		if (historyInterfaceRequestField == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchHistoryInterfaceRequestFieldException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Returns the history interface request field with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the history interface request field
////	 * @return the history interface request field, or <code>null</code> if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public HistoryInterfaceRequestField fetchByPrimaryKey(
////		Serializable primaryKey) throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the history interface request field with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the history interface request field
////	 * @return the history interface request field, or <code>null</code> if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByPrimaryKey(long id)
////		throws SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = null;
////
////
////
////		if (historyInterfaceRequestField == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<HistoryInterfaceRequestField> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					historyInterfaceRequestField = optional.get();
////				}
////			}
////			catch (Exception e) {
////				hasException = true;
////
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Returns the history interface request field where requestCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException} if it could not be found.
////	 *
////	 * @param requestCode the request code
////	 * @return the matching history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByRequestCode(String requestCode)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByRequestCode(requestCode);
////
////		if (historyInterfaceRequestField == null) {
////			StringBundler msg = new StringBundler(4);
////
////			msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////			msg.append("requestCode=");
////			msg.append(requestCode);
////
////			msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////			if (log.isWarnEnabled()) {
////				log.warn(msg.toString());
////			}
////
////			throw new NoSuchHistoryInterfaceRequestFieldException(msg.toString());
////		}
////
////		return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Returns the history interface request field where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
////	 *
////	 * @param requestCode the request code
////	 * @return the matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByRequestCode(String requestCode)
////		throws SystemException {
////		return fetchByRequestCode(requestCode, true);
////	}
////
////	/**
////	 * Returns the history interface request field where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
////	 *
////	 * @param requestCode the request code
////	 * @param retrieveFromCache whether to use the finder cache
////	 * @return the matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByRequestCode(String requestCode,
////		boolean retrieveFromCache) throws SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = null;
////		if (historyInterfaceRequestField == null) {
////			StringBundler query = new StringBundler(3);
////
////			query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (requestCode == null) {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
////			}
////			else {
////				if (requestCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
////				}
////			}
////
////			query.append(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (requestCode != null) {
////					builder.appendNamedParameterMap("requestCode", requestCode);
////				}
////
////				historyInterfaceRequestField = (HistoryInterfaceRequestField) queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////		return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Returns all the history interface request fields where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @return the matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByOrganizationCode(
////		String organizationCode) throws SystemException {
////		return findByOrganizationCode(organizationCode, QueryUtil.ALL_POS,
////			QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history interface request fields where organizationCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param organizationCode the organization code
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @return the range of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByOrganizationCode(
////		String organizationCode, int start, int end) throws SystemException {
////		return findByOrganizationCode(organizationCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history interface request fields where organizationCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param organizationCode the organization code
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByOrganizationCode(
////		String organizationCode, int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryInterfaceRequestField> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(3 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(3);
////			}
////
////			query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (organizationCode == null) {
////				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
////			}
////			else {
////				if (organizationCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
////				}
////			}
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (organizationCode != null) {
////					builder.appendNamedParameterMap("organizationCode", organizationCode);
////				}
////
////				list = (List<HistoryInterfaceRequestField>)queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Returns the first history interface request field in the ordered set where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByOrganizationCode_First(
////		String organizationCode, OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByOrganizationCode_First(organizationCode,
////				orderByComparator);
////
////		if (historyInterfaceRequestField != null) {
////			return historyInterfaceRequestField;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("organizationCode=");
////		msg.append(organizationCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryInterfaceRequestFieldException(msg.toString());
////	}
////
////	/**
////	 * Returns the first history interface request field in the ordered set where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByOrganizationCode_First(
////		String organizationCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<HistoryInterfaceRequestField> list = findByOrganizationCode(organizationCode,
////				0, 1, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last history interface request field in the ordered set where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByOrganizationCode_Last(
////		String organizationCode, OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByOrganizationCode_Last(organizationCode,
////				orderByComparator);
////
////		if (historyInterfaceRequestField != null) {
////			return historyInterfaceRequestField;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("organizationCode=");
////		msg.append(organizationCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryInterfaceRequestFieldException(msg.toString());
////	}
////
////	/**
////	 * Returns the last history interface request field in the ordered set where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByOrganizationCode_Last(
////		String organizationCode, OrderByComparator orderByComparator)
////		throws SystemException {
////		int count = countByOrganizationCode(organizationCode);
////
////		List<HistoryInterfaceRequestField> list = findByOrganizationCode(organizationCode,
////				count - 1, count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the history interface request fields before and after the current history interface request field in the ordered set where organizationCode = &#63;.
////	 *
////	 * @param id the primary key of the current history interface request field
////	 * @param organizationCode the organization code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField[] findByOrganizationCode_PrevAndNext(
////		long id, String organizationCode, OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			HistoryInterfaceRequestField[] array = new HistoryInterfaceRequestField[3];
////
////			array[0] = getByOrganizationCode_PrevAndNext(
////					historyInterfaceRequestField, organizationCode,
////					orderByComparator, true);
////
////			array[1] = historyInterfaceRequestField;
////
////			array[2] = getByOrganizationCode_PrevAndNext(
////					historyInterfaceRequestField, organizationCode,
////					orderByComparator, false);
////
////			return array;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////	protected HistoryInterfaceRequestField getByOrganizationCode_PrevAndNext(
////
////		HistoryInterfaceRequestField historyInterfaceRequestField,
////		String organizationCode, OrderByComparator orderByComparator,
////		boolean previous) {
////		StringBundler query = null;
////
////		if (orderByComparator != null) {
////			query = new StringBundler(6 +
////					(orderByComparator.getOrderByFields().length * 6));
////		}
////		else {
////			query = new StringBundler(3);
////		}
////
////		query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////		if (organizationCode == null) {
////			query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
////		}
////		else {
////			if (organizationCode.equals(StringPool.BLANK)) {
////				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
////			}
////			else {
////				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
////			}
////		}
////
////		if (orderByComparator != null) {
////			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
////
////			if (orderByConditionFields.length > 0) {
////				query.append(WHERE_AND);
////			}
////
////			for (int i = 0; i < orderByConditionFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByConditionFields[i]);
////
////				if ((i + 1) < orderByConditionFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN_HAS_NEXT);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN);
////					}
////				}
////			}
////
////			query.append(ORDER_BY_CLAUSE);
////
////			String[] orderByFields = orderByComparator.getOrderByFields();
////
////			for (int i = 0; i < orderByFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByFields[i]);
////
////				if ((i + 1) < orderByFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC_HAS_NEXT);
////					}
////					else {
////						query.append(ORDER_BY_DESC_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC);
////					}
////					else {
////						query.append(ORDER_BY_DESC);
////					}
////				}
////			}
////		}
////
////		else {
////			query.append(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		if (organizationCode != null) {
////			builder.appendNamedParameterMap("organizationCode", organizationCode);
////		}
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(historyInterfaceRequestField);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<HistoryInterfaceRequestField> list = queryFactory.getResultList(builder);
////
////		if (list.size() == 2) {
////			return list.get(1);
////		}
////		else {
////			return null;
////		}
////	}
////
////	/**
////	 * Returns all the history interface request fields where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @return the matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByLocCode(String locCode)
////		throws SystemException {
////		return findByLocCode(locCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history interface request fields where locCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param locCode the loc code
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @return the range of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByLocCode(String locCode,
////		int start, int end) throws SystemException {
////		return findByLocCode(locCode, start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history interface request fields where locCode = &#63;.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param locCode the loc code
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findByLocCode(String locCode,
////		int start, int end, OrderByComparator orderByComparator)
////		throws SystemException {
////		List<HistoryInterfaceRequestField> list = null;
////		if (list == null) {
////			StringBundler query = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(3 +
////						(orderByComparator.getOrderByFields().length * 3));
////			}
////			else {
////				query = new StringBundler(3);
////			}
////
////			query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (locCode == null) {
////				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
////			}
////			else {
////				if (locCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
////				}
////			}
////
////			if (orderByComparator != null) {
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////			}
////
////			else {
////				query.append(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (locCode != null) {
////					builder.appendNamedParameterMap("locCode", locCode);
////				}
////
////				list = (List<HistoryInterfaceRequestField>)queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Returns the first history interface request field in the ordered set where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByLocCode_First(String locCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByLocCode_First(locCode,
////				orderByComparator);
////
////		if (historyInterfaceRequestField != null) {
////			return historyInterfaceRequestField;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("locCode=");
////		msg.append(locCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryInterfaceRequestFieldException(msg.toString());
////	}
////
////	/**
////	 * Returns the first history interface request field in the ordered set where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the first matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByLocCode_First(String locCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryInterfaceRequestField> list = findByLocCode(locCode, 0, 1,
////				orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the last history interface request field in the ordered set where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField findByLocCode_Last(String locCode,
////		OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = fetchByLocCode_Last(locCode,
////				orderByComparator);
////
////		if (historyInterfaceRequestField != null) {
////			return historyInterfaceRequestField;
////		}
////
////		StringBundler msg = new StringBundler(4);
////
////		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
////
////		msg.append("locCode=");
////		msg.append(locCode);
////
////		msg.append(StringPool.CLOSE_CURLY_BRACE);
////
////		throw new NoSuchHistoryInterfaceRequestFieldException(msg.toString());
////	}
////
////	/**
////	 * Returns the last history interface request field in the ordered set where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the last matching history interface request field, or <code>null</code> if a matching history interface request field could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField fetchByLocCode_Last(String locCode,
////		OrderByComparator orderByComparator) throws SystemException {
////		int count = countByLocCode(locCode);
////
////		List<HistoryInterfaceRequestField> list = findByLocCode(locCode,
////				count - 1, count, orderByComparator);
////
////		if (!list.isEmpty()) {
////			return list.get(0);
////		}
////
////		return null;
////	}
////
////	/**
////	 * Returns the history interface request fields before and after the current history interface request field in the ordered set where locCode = &#63;.
////	 *
////	 * @param id the primary key of the current history interface request field
////	 * @param locCode the loc code
////	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
////	 * @return the previous, current, and next history interface request field
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestFieldException if a history interface request field with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField[] findByLocCode_PrevAndNext(long id,
////		String locCode, OrderByComparator orderByComparator)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = findByPrimaryKey(id);
////
////
////
////		try {
////
////
////			HistoryInterfaceRequestField[] array = new HistoryInterfaceRequestField[3];
////
////			array[0] = getByLocCode_PrevAndNext(
////					historyInterfaceRequestField, locCode, orderByComparator,
////					true);
////
////			array[1] = historyInterfaceRequestField;
////
////			array[2] = getByLocCode_PrevAndNext(
////					historyInterfaceRequestField, locCode, orderByComparator,
////					false);
////
////			return array;
////		}
////		catch (Exception e) {
////			throw processException(e);
////		}
////		finally {
////
////		}
////	}
////
////	protected HistoryInterfaceRequestField getByLocCode_PrevAndNext(
////
////		HistoryInterfaceRequestField historyInterfaceRequestField,
////		String locCode, OrderByComparator orderByComparator, boolean previous) {
////		StringBundler query = null;
////
////		if (orderByComparator != null) {
////			query = new StringBundler(6 +
////					(orderByComparator.getOrderByFields().length * 6));
////		}
////		else {
////			query = new StringBundler(3);
////		}
////
////		query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////		if (locCode == null) {
////			query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
////		}
////		else {
////			if (locCode.equals(StringPool.BLANK)) {
////				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
////			}
////			else {
////				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
////			}
////		}
////
////		if (orderByComparator != null) {
////			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
////
////			if (orderByConditionFields.length > 0) {
////				query.append(WHERE_AND);
////			}
////
////			for (int i = 0; i < orderByConditionFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByConditionFields[i]);
////
////				if ((i + 1) < orderByConditionFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN_HAS_NEXT);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(WHERE_GREATER_THAN);
////					}
////					else {
////						query.append(WHERE_LESSER_THAN);
////					}
////				}
////			}
////
////			query.append(ORDER_BY_CLAUSE);
////
////			String[] orderByFields = orderByComparator.getOrderByFields();
////
////			for (int i = 0; i < orderByFields.length; i++) {
////				query.append(_ORDER_BY_ENTITY_ALIAS);
////				query.append(orderByFields[i]);
////
////				if ((i + 1) < orderByFields.length) {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC_HAS_NEXT);
////					}
////					else {
////						query.append(ORDER_BY_DESC_HAS_NEXT);
////					}
////				}
////				else {
////					if (orderByComparator.isAscending() ^ previous) {
////						query.append(ORDER_BY_ASC);
////					}
////					else {
////						query.append(ORDER_BY_DESC);
////					}
////				}
////			}
////		}
////
////		else {
////			query.append(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////		}
////
////		String sql = query.toString();
////
////		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
////
////
////
////		if (locCode != null) {
////			builder.appendNamedParameterMap("locCode", locCode);
////		}
////
////		if (orderByComparator != null) {
////			Object[] values = orderByComparator.getOrderByConditionValues(historyInterfaceRequestField);
////
////						/*
////			for (Object value : values) {
////				builder.appendNamedParameterMap("value", value);
////			}
////			*/
////		}
////
////		List<HistoryInterfaceRequestField> list = queryFactory.getResultList(builder);
////
////		if (list.size() == 2) {
////			return list.get(1);
////		}
////		else {
////			return null;
////		}
////	}
////
////	/**
////	 * Returns all the history interface request fields.
////	 *
////	 * @return the history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findAll()
////		throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the history interface request fields.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @return the range of history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the history interface request fields.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of history interface request fields
////	 * @param end the upper bound of the range of history interface request fields (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<HistoryInterfaceRequestField> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<HistoryInterfaceRequestField> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_HISTORYINTERFACEREQUESTFIELD);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_HISTORYINTERFACEREQUESTFIELD.concat(HistoryInterfaceRequestFieldModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<HistoryInterfaceRequestField>) queryFactory.getResultList(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////
////
////
////			}
////		}
////
////		return list;
////	}
////
////	/**
////	 * Removes the history interface request field where requestCode = &#63; from the database.
////	 *
////	 * @param requestCode the request code
////	 * @return the history interface request field that was removed
////	 * @throws SystemException if a system exception occurred
////	 */
////	public HistoryInterfaceRequestField removeByRequestCode(String requestCode)
////		throws NoSuchHistoryInterfaceRequestFieldException, SystemException {
////		HistoryInterfaceRequestField historyInterfaceRequestField = findByRequestCode(requestCode);
////
////		repository.delete(historyInterfaceRequestField);
////			return historyInterfaceRequestField;
////	}
////
////	/**
////	 * Removes all the history interface request fields where organizationCode = &#63; from the database.
////	 *
////	 * @param organizationCode the organization code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByOrganizationCode(String organizationCode)
////		throws SystemException {
////		for (HistoryInterfaceRequestField historyInterfaceRequestField : findByOrganizationCode(
////				organizationCode)) {
////			repository.delete(historyInterfaceRequestField);
////		}
////	}
////
////	/**
////	 * Removes all the history interface request fields where locCode = &#63; from the database.
////	 *
////	 * @param locCode the loc code
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeByLocCode(String locCode) throws SystemException {
////		for (HistoryInterfaceRequestField historyInterfaceRequestField : findByLocCode(
////				locCode)) {
////			repository.delete(historyInterfaceRequestField);
////		}
////	}
////
////	/**
////	 * Removes all the history interface request fields from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (HistoryInterfaceRequestField historyInterfaceRequestField : findAll()) {
////			repository.delete(historyInterfaceRequestField);
////		}
////	}
////
////	/**
////	 * Returns the number of history interface request fields where requestCode = &#63;.
////	 *
////	 * @param requestCode the request code
////	 * @return the number of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByRequestCode(String requestCode) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (requestCode == null) {
////				query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1);
////			}
////			else {
////				if (requestCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (requestCode != null) {
////					builder.appendNamedParameterMap("requestCode", requestCode);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of history interface request fields where organizationCode = &#63;.
////	 *
////	 * @param organizationCode the organization code
////	 * @return the number of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByOrganizationCode(String organizationCode)
////		throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (organizationCode == null) {
////				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
////			}
////			else {
////				if (organizationCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (organizationCode != null) {
////					builder.appendNamedParameterMap("organizationCode", organizationCode);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of history interface request fields where locCode = &#63;.
////	 *
////	 * @param locCode the loc code
////	 * @return the number of matching history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countByLocCode(String locCode) throws SystemException {
////		Long count = null;
////		if (count == null) {
////			StringBundler query = new StringBundler(2);
////
////			query.append(_SQL_COUNT_HISTORYINTERFACEREQUESTFIELD_WHERE);
////
////			if (locCode == null) {
////				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
////			}
////			else {
////				if (locCode.equals(StringPool.BLANK)) {
////					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
////				}
////				else {
////					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
////				}
////			}
////
////			String sql = query.toString();
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////
////
////				if (locCode != null) {
////					builder.appendNamedParameterMap("locCode", locCode);
////				}
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Returns the number of history interface request fields.
////	 *
////	 * @return the number of history interface request fields
////	 * @throws SystemException if a system exception occurred
////	 */
////	public int countAll() throws SystemException {
////		Long count = null;
////
////		if (count == null) {
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_HISTORYINTERFACEREQUESTFIELD).build();
////
////				count = (Long)queryFactory.getSingleResult(builder);
////			}
////			catch (Exception e) {
////				throw processException(e);
////			}
////			finally {
////				if (count == null) {
////					count = Long.valueOf(0);
////				}
////
////
////
////
////			}
////		}
////
////		return count.intValue();
////	}
////
////	/**
////	 * Initializes the history interface request field persistence.
////	 */
////	private static final String _SQL_SELECT_HISTORYINTERFACEREQUESTFIELD = "SELECT historyInterfaceRequestField FROM HistoryInterfaceRequestField historyInterfaceRequestField";
////	private static final String _SQL_SELECT_HISTORYINTERFACEREQUESTFIELD_WHERE = "SELECT historyInterfaceRequestField FROM HistoryInterfaceRequestField historyInterfaceRequestField WHERE ";
////	private static final String _SQL_COUNT_HISTORYINTERFACEREQUESTFIELD = "SELECT COUNT(historyInterfaceRequestField) FROM HistoryInterfaceRequestField historyInterfaceRequestField";
////	private static final String _SQL_COUNT_HISTORYINTERFACEREQUESTFIELD_WHERE = "SELECT COUNT(historyInterfaceRequestField) FROM HistoryInterfaceRequestField historyInterfaceRequestField WHERE ";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "historyInterfaceRequestField.requestCode IS NULL";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "historyInterfaceRequestField.requestCode =:requestCode";
////	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(historyInterfaceRequestField.requestCode IS NULL OR historyInterfaceRequestField.requestCode =:requestCode)";
////	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1 =
////		"historyInterfaceRequestField.organizationCode IS NULL";
////	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2 =
////		"historyInterfaceRequestField.organizationCode =:organizationCode";
////	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3 =
////		"(historyInterfaceRequestField.organizationCode IS NULL OR historyInterfaceRequestField.organizationCode =:organizationCode)";
////	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_1 = "historyInterfaceRequestField.locCode IS NULL";
////	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_2 = "historyInterfaceRequestField.locCode =:locCode";
////	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_3 = "(historyInterfaceRequestField.locCode IS NULL OR historyInterfaceRequestField.locCode =:locCode)";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "historyInterfaceRequestField.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistoryInterfaceRequestField exists with the primary key ";
////	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HistoryInterfaceRequestField exists with the key {";
////
////
////
////}
