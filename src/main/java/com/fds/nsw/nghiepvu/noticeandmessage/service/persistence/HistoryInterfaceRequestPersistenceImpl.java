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
import com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.HistoryInterfaceRequestRepository;
import com.fds.nsw.nghiepvu.modelImpl.HistoryInterfaceRequestModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class HistoryInterfaceRequestPersistenceImpl extends BasePersistence {
	@Autowired
	HistoryInterfaceRequestRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<HistoryInterfaceRequest> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistoryInterfaceRequestUtil} to access the history interface request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public HistoryInterfaceRequest create(long id) {
		HistoryInterfaceRequest historyInterfaceRequest = new HistoryInterfaceRequest();

		
		//historyInterfaceRequest.setPrimaryKey(id);

		return historyInterfaceRequest;
	}

	/**
	 * Removes the history interface request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the history interface request
	 * @return the history interface request that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest remove(long id)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the history interface request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the history interface request
	 * @return the history interface request that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public HistoryInterfaceRequest remove(Serializable primaryKey)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		

		try {
			

			HistoryInterfaceRequest historyInterfaceRequest = findByPrimaryKey(primaryKey);

			if (historyInterfaceRequest == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistoryInterfaceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(historyInterfaceRequest);
			return historyInterfaceRequest;
		}
		catch (NoSuchHistoryInterfaceRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}


public HistoryInterfaceRequest remove(HistoryInterfaceRequest HistoryInterfaceRequest) throws SystemException {
	removeImpl(HistoryInterfaceRequest);
	return HistoryInterfaceRequest;
}

protected HistoryInterfaceRequest removeImpl
(
		HistoryInterfaceRequest historyInterfaceRequest)
		throws SystemException {
		try {
			repository.delete(historyInterfaceRequest);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return historyInterfaceRequest;
	}

	
	public HistoryInterfaceRequest updateImpl(
		com.fds.nsw.nghiepvu.model.HistoryInterfaceRequest historyInterfaceRequest,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(historyInterfaceRequest);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return historyInterfaceRequest;
	}

	
	public HistoryInterfaceRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the history interface request with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException} if it could not be found.
	 *
	 * @param id the primary key of the history interface request
	 * @return the history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByPrimaryKey(long id)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByPrimaryKey(id);

		if (historyInterfaceRequest == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchHistoryInterfaceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return historyInterfaceRequest;
	}

	/**
	 * Returns the history interface request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the history interface request
	 * @return the history interface request, or <code>null</code> if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public HistoryInterfaceRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the history interface request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the history interface request
	 * @return the history interface request, or <code>null</code> if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByPrimaryKey(long id)
		throws SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = null;

		

		if (historyInterfaceRequest == null) {
			

			boolean hasException = false;

			try {
				

				Optional<HistoryInterfaceRequest> optional = repository.findById(id);
				if (optional.isPresent()) {
					historyInterfaceRequest = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return historyInterfaceRequest;
	}

	/**
	 * Returns the history interface request where requestCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByRequestCode(String requestCode)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByRequestCode(requestCode);

		if (historyInterfaceRequest == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchHistoryInterfaceRequestException(msg.toString());
		}

		return historyInterfaceRequest;
	}

	/**
	 * Returns the history interface request where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the history interface request where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = null;
		if (historyInterfaceRequest == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE);

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

			query.append(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(HistoryInterfaceRequest.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				historyInterfaceRequest = (HistoryInterfaceRequest) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return historyInterfaceRequest;
	}

	/**
	 * Returns all the history interface requests where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @return the matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByOrganizationCode(
		String organizationCode) throws SystemException {
		return findByOrganizationCode(organizationCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the history interface requests where organizationCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param organizationCode the organization code
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @return the range of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByOrganizationCode(
		String organizationCode, int start, int end) throws SystemException {
		return findByOrganizationCode(organizationCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the history interface requests where organizationCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param organizationCode the organization code
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByOrganizationCode(
		String organizationCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<HistoryInterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE);

			if (organizationCode == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
			}
			else {
				if (organizationCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (organizationCode != null) {
					builder.appendNamedParameterMap("organizationCode", organizationCode);
				}

				list = (List<HistoryInterfaceRequest>)queryFactory.getResultList(builder);
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
	 * Returns the first history interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByOrganizationCode_First(
		String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByOrganizationCode_First(organizationCode,
				orderByComparator);

		if (historyInterfaceRequest != null) {
			return historyInterfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationCode=");
		msg.append(organizationCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoryInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the first history interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByOrganizationCode_First(
		String organizationCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<HistoryInterfaceRequest> list = findByOrganizationCode(organizationCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last history interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByOrganizationCode_Last(
		String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByOrganizationCode_Last(organizationCode,
				orderByComparator);

		if (historyInterfaceRequest != null) {
			return historyInterfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationCode=");
		msg.append(organizationCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoryInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the last history interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByOrganizationCode_Last(
		String organizationCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOrganizationCode(organizationCode);

		List<HistoryInterfaceRequest> list = findByOrganizationCode(organizationCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the history interface requests before and after the current history interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param id the primary key of the current history interface request
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest[] findByOrganizationCode_PrevAndNext(
		long id, String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = findByPrimaryKey(id);

		

		try {
			

			HistoryInterfaceRequest[] array = new HistoryInterfaceRequest[3];

			array[0] = getByOrganizationCode_PrevAndNext(
					historyInterfaceRequest, organizationCode,
					orderByComparator, true);

			array[1] = historyInterfaceRequest;

			array[2] = getByOrganizationCode_PrevAndNext(
					historyInterfaceRequest, organizationCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected HistoryInterfaceRequest getByOrganizationCode_PrevAndNext(
		 HistoryInterfaceRequest historyInterfaceRequest,
		String organizationCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE);

		if (organizationCode == null) {
			query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
		}
		else {
			if (organizationCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
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
			query.append(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (organizationCode != null) {
			builder.appendNamedParameterMap("organizationCode", organizationCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historyInterfaceRequest);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<HistoryInterfaceRequest> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the history interface requests where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @return the matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByLocCode(String locCode)
		throws SystemException {
		return findByLocCode(locCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the history interface requests where locCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @return the range of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByLocCode(String locCode,
		int start, int end) throws SystemException {
		return findByLocCode(locCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the history interface requests where locCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findByLocCode(String locCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<HistoryInterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE);

			if (locCode == null) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
			}
			else {
				if (locCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (locCode != null) {
					builder.appendNamedParameterMap("locCode", locCode);
				}

				list = (List<HistoryInterfaceRequest>)queryFactory.getResultList(builder);
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
	 * Returns the first history interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByLocCode_First(String locCode,
		OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByLocCode_First(locCode,
				orderByComparator);

		if (historyInterfaceRequest != null) {
			return historyInterfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoryInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the first history interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByLocCode_First(String locCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<HistoryInterfaceRequest> list = findByLocCode(locCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last history interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest findByLocCode_Last(String locCode,
		OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = fetchByLocCode_Last(locCode,
				orderByComparator);

		if (historyInterfaceRequest != null) {
			return historyInterfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchHistoryInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the last history interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching history interface request, or <code>null</code> if a matching history interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest fetchByLocCode_Last(String locCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLocCode(locCode);

		List<HistoryInterfaceRequest> list = findByLocCode(locCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the history interface requests before and after the current history interface request in the ordered set where locCode = &#63;.
	 *
	 * @param id the primary key of the current history interface request
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next history interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchHistoryInterfaceRequestException if a history interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest[] findByLocCode_PrevAndNext(long id,
		String locCode, OrderByComparator orderByComparator)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = findByPrimaryKey(id);

		

		try {
			

			HistoryInterfaceRequest[] array = new HistoryInterfaceRequest[3];

			array[0] = getByLocCode_PrevAndNext(
					historyInterfaceRequest, locCode, orderByComparator, true);

			array[1] = historyInterfaceRequest;

			array[2] = getByLocCode_PrevAndNext(
					historyInterfaceRequest, locCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected HistoryInterfaceRequest getByLocCode_PrevAndNext(
		 HistoryInterfaceRequest historyInterfaceRequest,
		String locCode, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE);

		if (locCode == null) {
			query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
		}
		else {
			if (locCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
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
			query.append(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (locCode != null) {
			builder.appendNamedParameterMap("locCode", locCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(historyInterfaceRequest);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<HistoryInterfaceRequest> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the history interface requests.
	 *
	 * @return the history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the history interface requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @return the range of history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the history interface requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of history interface requests
	 * @param end the upper bound of the range of history interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<HistoryInterfaceRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<HistoryInterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_HISTORYINTERFACEREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTORYINTERFACEREQUEST.concat(HistoryInterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<HistoryInterfaceRequest>) queryFactory.getResultList(builder);
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
	 * Removes the history interface request where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the history interface request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public HistoryInterfaceRequest removeByRequestCode(String requestCode)
		throws NoSuchHistoryInterfaceRequestException, SystemException {
		HistoryInterfaceRequest historyInterfaceRequest = findByRequestCode(requestCode);

		repository.delete(historyInterfaceRequest);
			return historyInterfaceRequest;
	}

	/**
	 * Removes all the history interface requests where organizationCode = &#63; from the database.
	 *
	 * @param organizationCode the organization code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOrganizationCode(String organizationCode)
		throws SystemException {
		for (HistoryInterfaceRequest historyInterfaceRequest : findByOrganizationCode(
				organizationCode)) {
			repository.delete(historyInterfaceRequest);
		}
	}

	/**
	 * Removes all the history interface requests where locCode = &#63; from the database.
	 *
	 * @param locCode the loc code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLocCode(String locCode) throws SystemException {
		for (HistoryInterfaceRequest historyInterfaceRequest : findByLocCode(
				locCode)) {
			repository.delete(historyInterfaceRequest);
		}
	}

	/**
	 * Removes all the history interface requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (HistoryInterfaceRequest historyInterfaceRequest : findAll()) {
			repository.delete(historyInterfaceRequest);
		}
	}

	/**
	 * Returns the number of history interface requests where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORYINTERFACEREQUEST_WHERE);

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
	 * Returns the number of history interface requests where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @return the number of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationCode(String organizationCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORYINTERFACEREQUEST_WHERE);

			if (organizationCode == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1);
			}
			else {
				if (organizationCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (organizationCode != null) {
					builder.appendNamedParameterMap("organizationCode", organizationCode);
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
	 * Returns the number of history interface requests where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @return the number of matching history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLocCode(String locCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_HISTORYINTERFACEREQUEST_WHERE);

			if (locCode == null) {
				query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_1);
			}
			else {
				if (locCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_LOCCODE_LOCCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (locCode != null) {
					builder.appendNamedParameterMap("locCode", locCode);
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
	 * Returns the number of history interface requests.
	 *
	 * @return the number of history interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_HISTORYINTERFACEREQUEST).build();

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
	 * Initializes the history interface request persistence.
	 */
	private static final String _SQL_SELECT_HISTORYINTERFACEREQUEST = "SELECT historyInterfaceRequest FROM HistoryInterfaceRequest historyInterfaceRequest";
	private static final String _SQL_SELECT_HISTORYINTERFACEREQUEST_WHERE = "SELECT historyInterfaceRequest FROM HistoryInterfaceRequest historyInterfaceRequest WHERE ";
	private static final String _SQL_COUNT_HISTORYINTERFACEREQUEST = "SELECT COUNT(historyInterfaceRequest) FROM HistoryInterfaceRequest historyInterfaceRequest";
	private static final String _SQL_COUNT_HISTORYINTERFACEREQUEST_WHERE = "SELECT COUNT(historyInterfaceRequest) FROM HistoryInterfaceRequest historyInterfaceRequest WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "historyInterfaceRequest.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "historyInterfaceRequest.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(historyInterfaceRequest.requestCode IS NULL OR historyInterfaceRequest.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1 =
		"historyInterfaceRequest.organizationCode IS NULL";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2 =
		"historyInterfaceRequest.organizationCode =:organizationCode";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3 =
		"(historyInterfaceRequest.organizationCode IS NULL OR historyInterfaceRequest.organizationCode =:organizationCode)";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_1 = "historyInterfaceRequest.locCode IS NULL";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_2 = "historyInterfaceRequest.locCode =:locCode";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_3 = "(historyInterfaceRequest.locCode IS NULL OR historyInterfaceRequest.locCode =:locCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "historyInterfaceRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistoryInterfaceRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No HistoryInterfaceRequest exists with the key {";
	

	
}
