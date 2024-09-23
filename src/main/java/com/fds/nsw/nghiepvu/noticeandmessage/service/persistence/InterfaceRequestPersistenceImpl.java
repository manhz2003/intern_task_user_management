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
import com.fds.nsw.nghiepvu.model.InterfaceRequest;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.InterfaceRequestRepository;
import com.fds.nsw.nghiepvu.modelImpl.InterfaceRequestModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InterfaceRequestPersistenceImpl extends BasePersistence {
	@Autowired
	InterfaceRequestRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<InterfaceRequest> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link InterfaceRequestUtil} to access the interface request persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public InterfaceRequest create(long id) {
		InterfaceRequest interfaceRequest = new InterfaceRequest();

		
		//interfaceRequest.setPrimaryKey(id);

		return interfaceRequest;
	}

	/**
	 * Removes the interface request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the interface request
	 * @return the interface request that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest remove(long id)
		throws NoSuchInterfaceRequestException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the interface request with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the interface request
	 * @return the interface request that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public InterfaceRequest remove(Serializable primaryKey)
		throws NoSuchInterfaceRequestException, SystemException {
		

		try {
			

			InterfaceRequest interfaceRequest = findByPrimaryKey(primaryKey);

			if (interfaceRequest == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchInterfaceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(interfaceRequest);
			return interfaceRequest;
		}
		catch (NoSuchInterfaceRequestException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public InterfaceRequest remove(InterfaceRequest InterfaceRequest) throws SystemException {
	removeImpl(InterfaceRequest);
	return InterfaceRequest;
}

protected InterfaceRequest removeImpl(InterfaceRequest interfaceRequest)
		throws SystemException {
		try {
			repository.delete(interfaceRequest);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return interfaceRequest;
	}

	
	public InterfaceRequest updateImpl(
		com.fds.nsw.nghiepvu.model.InterfaceRequest interfaceRequest,
		boolean merge) throws SystemException {
		try {
			repository.saveAndFlush(interfaceRequest);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return interfaceRequest;
	}

	
	public InterfaceRequest findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the interface request with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException} if it could not be found.
	 *
	 * @param id the primary key of the interface request
	 * @return the interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByPrimaryKey(long id)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByPrimaryKey(id);

		if (interfaceRequest == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchInterfaceRequestException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return interfaceRequest;
	}

	/**
	 * Returns the interface request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the interface request
	 * @return the interface request, or <code>null</code> if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public InterfaceRequest fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the interface request with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the interface request
	 * @return the interface request, or <code>null</code> if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByPrimaryKey(long id)
		throws SystemException {
		InterfaceRequest interfaceRequest = null;

		

		if (interfaceRequest == null) {
			

			boolean hasException = false;

			try {
				

				Optional<InterfaceRequest> optional = repository.findById(id);
				if (optional.isPresent()) {
					interfaceRequest = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return interfaceRequest;
	}

	/**
	 * Returns the interface request where requestCode = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException} if it could not be found.
	 *
	 * @param requestCode the request code
	 * @return the matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByRequestCode(String requestCode)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByRequestCode(requestCode);

		if (interfaceRequest == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("requestCode=");
			msg.append(requestCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchInterfaceRequestException(msg.toString());
		}

		return interfaceRequest;
	}

	/**
	 * Returns the interface request where requestCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param requestCode the request code
	 * @return the matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByRequestCode(String requestCode)
		throws SystemException {
		return fetchByRequestCode(requestCode, true);
	}

	/**
	 * Returns the interface request where requestCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param requestCode the request code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByRequestCode(String requestCode,
		boolean retrieveFromCache) throws SystemException {
		InterfaceRequest interfaceRequest = null;
		if (interfaceRequest == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

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

			query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(InterfaceRequest.class).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				interfaceRequest = (InterfaceRequest) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return interfaceRequest;
	}

	/**
	 * Returns all the interface requests where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @return the matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByOrganizationCode(
		String organizationCode) throws SystemException {
		return findByOrganizationCode(organizationCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the interface requests where organizationCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param organizationCode the organization code
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @return the range of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByOrganizationCode(
		String organizationCode, int start, int end) throws SystemException {
		return findByOrganizationCode(organizationCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the interface requests where organizationCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param organizationCode the organization code
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByOrganizationCode(
		String organizationCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<InterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

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
				query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (organizationCode != null) {
					builder.appendNamedParameterMap("organizationCode", organizationCode);
				}

				list = (List<InterfaceRequest>)queryFactory.getResultList(builder);
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
	 * Returns the first interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByOrganizationCode_First(
		String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByOrganizationCode_First(organizationCode,
				orderByComparator);

		if (interfaceRequest != null) {
			return interfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationCode=");
		msg.append(organizationCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the first interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByOrganizationCode_First(
		String organizationCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<InterfaceRequest> list = findByOrganizationCode(organizationCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByOrganizationCode_Last(
		String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByOrganizationCode_Last(organizationCode,
				orderByComparator);

		if (interfaceRequest != null) {
			return interfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("organizationCode=");
		msg.append(organizationCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the last interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByOrganizationCode_Last(
		String organizationCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByOrganizationCode(organizationCode);

		List<InterfaceRequest> list = findByOrganizationCode(organizationCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the interface requests before and after the current interface request in the ordered set where organizationCode = &#63;.
	 *
	 * @param id the primary key of the current interface request
	 * @param organizationCode the organization code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest[] findByOrganizationCode_PrevAndNext(long id,
		String organizationCode, OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = findByPrimaryKey(id);

		

		try {
			

			InterfaceRequest[] array = new InterfaceRequest[3];

			array[0] = getByOrganizationCode_PrevAndNext(
					interfaceRequest, organizationCode, orderByComparator, true);

			array[1] = interfaceRequest;

			array[2] = getByOrganizationCode_PrevAndNext(
					interfaceRequest, organizationCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected InterfaceRequest getByOrganizationCode_PrevAndNext(
		 InterfaceRequest interfaceRequest,
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

		query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

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
			query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (organizationCode != null) {
			builder.appendNamedParameterMap("organizationCode", organizationCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(interfaceRequest);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<InterfaceRequest> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the interface requests where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @return the matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByLocCode(String locCode)
		throws SystemException {
		return findByLocCode(locCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the interface requests where locCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @return the range of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByLocCode(String locCode, int start,
		int end) throws SystemException {
		return findByLocCode(locCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the interface requests where locCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param locCode the loc code
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findByLocCode(String locCode, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<InterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

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
				query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (locCode != null) {
					builder.appendNamedParameterMap("locCode", locCode);
				}

				list = (List<InterfaceRequest>)queryFactory.getResultList(builder);
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
	 * Returns the first interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByLocCode_First(String locCode,
		OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByLocCode_First(locCode,
				orderByComparator);

		if (interfaceRequest != null) {
			return interfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the first interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByLocCode_First(String locCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<InterfaceRequest> list = findByLocCode(locCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByLocCode_Last(String locCode,
		OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByLocCode_Last(locCode,
				orderByComparator);

		if (interfaceRequest != null) {
			return interfaceRequest;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("locCode=");
		msg.append(locCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchInterfaceRequestException(msg.toString());
	}

	/**
	 * Returns the last interface request in the ordered set where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByLocCode_Last(String locCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByLocCode(locCode);

		List<InterfaceRequest> list = findByLocCode(locCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the interface requests before and after the current interface request in the ordered set where locCode = &#63;.
	 *
	 * @param id the primary key of the current interface request
	 * @param locCode the loc code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a interface request with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest[] findByLocCode_PrevAndNext(long id,
		String locCode, OrderByComparator orderByComparator)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = findByPrimaryKey(id);

		

		try {
			

			InterfaceRequest[] array = new InterfaceRequest[3];

			array[0] = getByLocCode_PrevAndNext(interfaceRequest,
					locCode, orderByComparator, true);

			array[1] = interfaceRequest;

			array[2] = getByLocCode_PrevAndNext(interfaceRequest,
					locCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected InterfaceRequest getByLocCode_PrevAndNext(
		InterfaceRequest interfaceRequest, String locCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

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
			query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (locCode != null) {
			builder.appendNamedParameterMap("locCode", locCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(interfaceRequest);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<InterfaceRequest> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the interface request where documentNameRef = &#63; and businessType = &#63; or throws a {@link vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException} if it could not be found.
	 *
	 * @param documentNameRef the document name ref
	 * @param businessType the business type
	 * @return the matching interface request
	 * @throws vn.gt.dao.noticeandmessage.NoSuchInterfaceRequestException if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest findByF_BY_documentNameRef(String documentNameRef,
		String businessType)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = fetchByF_BY_documentNameRef(documentNameRef,
				businessType);

		if (interfaceRequest == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("documentNameRef=");
			msg.append(documentNameRef);

			msg.append(", businessType=");
			msg.append(businessType);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchInterfaceRequestException(msg.toString());
		}

		return interfaceRequest;
	}

	/**
	 * Returns the interface request where documentNameRef = &#63; and businessType = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param documentNameRef the document name ref
	 * @param businessType the business type
	 * @return the matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByF_BY_documentNameRef(
		String documentNameRef, String businessType) throws SystemException {
		return fetchByF_BY_documentNameRef(documentNameRef, businessType, true);
	}

	/**
	 * Returns the interface request where documentNameRef = &#63; and businessType = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param documentNameRef the document name ref
	 * @param businessType the business type
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching interface request, or <code>null</code> if a matching interface request could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest fetchByF_BY_documentNameRef(
		String documentNameRef, String businessType, boolean retrieveFromCache)
		throws SystemException {
		InterfaceRequest interfaceRequest = null;
		if (interfaceRequest == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_INTERFACEREQUEST_WHERE);

			if (documentNameRef == null) {
				query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_1);
			}
			else {
				if (documentNameRef.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_2);
				}
			}

			if (businessType == null) {
				query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_1);
			}
			else {
				if (businessType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_2);
				}
			}

			query.append(InterfaceRequestModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(InterfaceRequest.class).build();

				

				if (documentNameRef != null) {
					builder.appendNamedParameterMap("documentNameRef", documentNameRef);
				}

				if (businessType != null) {
					builder.appendNamedParameterMap("businessType", businessType);
				}

				interfaceRequest = (InterfaceRequest) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return interfaceRequest;
	}

	/**
	 * Returns all the interface requests.
	 *
	 * @return the interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the interface requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @return the range of interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the interface requests.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of interface requests
	 * @param end the upper bound of the range of interface requests (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public List<InterfaceRequest> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<InterfaceRequest> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_INTERFACEREQUEST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_INTERFACEREQUEST.concat(InterfaceRequestModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<InterfaceRequest>) queryFactory.getResultList(builder);
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
	 * Removes the interface request where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @return the interface request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest removeByRequestCode(String requestCode)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = findByRequestCode(requestCode);

		repository.delete(interfaceRequest);
			return interfaceRequest;
	}

	/**
	 * Removes all the interface requests where organizationCode = &#63; from the database.
	 *
	 * @param organizationCode the organization code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByOrganizationCode(String organizationCode)
		throws SystemException {
		for (InterfaceRequest interfaceRequest : findByOrganizationCode(
				organizationCode)) {
			repository.delete(interfaceRequest);
		}
	}

	/**
	 * Removes all the interface requests where locCode = &#63; from the database.
	 *
	 * @param locCode the loc code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLocCode(String locCode) throws SystemException {
		for (InterfaceRequest interfaceRequest : findByLocCode(locCode)) {
			repository.delete(interfaceRequest);
		}
	}

	/**
	 * Removes the interface request where documentNameRef = &#63; and businessType = &#63; from the database.
	 *
	 * @param documentNameRef the document name ref
	 * @param businessType the business type
	 * @return the interface request that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public InterfaceRequest removeByF_BY_documentNameRef(
		String documentNameRef, String businessType)
		throws NoSuchInterfaceRequestException, SystemException {
		InterfaceRequest interfaceRequest = findByF_BY_documentNameRef(documentNameRef,
				businessType);

		repository.delete(interfaceRequest);
			return interfaceRequest;
	}

	/**
	 * Removes all the interface requests from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (InterfaceRequest interfaceRequest : findAll()) {
			repository.delete(interfaceRequest);
		}
	}

	/**
	 * Returns the number of interface requests where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_INTERFACEREQUEST_WHERE);

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
	 * Returns the number of interface requests where organizationCode = &#63;.
	 *
	 * @param organizationCode the organization code
	 * @return the number of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationCode(String organizationCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_INTERFACEREQUEST_WHERE);

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
	 * Returns the number of interface requests where locCode = &#63;.
	 *
	 * @param locCode the loc code
	 * @return the number of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLocCode(String locCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_INTERFACEREQUEST_WHERE);

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
	 * Returns the number of interface requests where documentNameRef = &#63; and businessType = &#63;.
	 *
	 * @param documentNameRef the document name ref
	 * @param businessType the business type
	 * @return the number of matching interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_BY_documentNameRef(String documentNameRef,
		String businessType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_INTERFACEREQUEST_WHERE);

			if (documentNameRef == null) {
				query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_1);
			}
			else {
				if (documentNameRef.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_2);
				}
			}

			if (businessType == null) {
				query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_1);
			}
			else {
				if (businessType.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (documentNameRef != null) {
					builder.appendNamedParameterMap("documentNameRef", documentNameRef);
				}

				if (businessType != null) {
					builder.appendNamedParameterMap("businessType", businessType);
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
	 * Returns the number of interface requests.
	 *
	 * @return the number of interface requests
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_INTERFACEREQUEST).build();

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
	 * Initializes the interface request persistence.
	 */
	private static final String _SQL_SELECT_INTERFACEREQUEST = "SELECT interfaceRequest FROM InterfaceRequest interfaceRequest";
	private static final String _SQL_SELECT_INTERFACEREQUEST_WHERE = "SELECT interfaceRequest FROM InterfaceRequest interfaceRequest WHERE ";
	private static final String _SQL_COUNT_INTERFACEREQUEST = "SELECT COUNT(interfaceRequest) FROM InterfaceRequest interfaceRequest";
	private static final String _SQL_COUNT_INTERFACEREQUEST_WHERE = "SELECT COUNT(interfaceRequest) FROM InterfaceRequest interfaceRequest WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "interfaceRequest.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "interfaceRequest.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(interfaceRequest.requestCode IS NULL OR interfaceRequest.requestCode =:requestCode)";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_1 =
		"interfaceRequest.organizationCode IS NULL";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_2 =
		"interfaceRequest.organizationCode =:organizationCode";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ORGANIZATIONCODE_3 =
		"(interfaceRequest.organizationCode IS NULL OR interfaceRequest.organizationCode =:organizationCode)";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_1 = "interfaceRequest.locCode IS NULL";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_2 = "interfaceRequest.locCode =:locCode";
	private static final String _FINDER_COLUMN_LOCCODE_LOCCODE_3 = "(interfaceRequest.locCode IS NULL OR interfaceRequest.locCode =:locCode)";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_1 =
		"interfaceRequest.documentNameRef IS NULL AND ";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_2 =
		"interfaceRequest.documentNameRef =:documentNameRef AND ";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_DOCUMENTNAMEREF_3 =
		"(interfaceRequest.documentNameRef IS NULL OR interfaceRequest.documentNameRef =:documentNameRef) AND ";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_1 =
		"interfaceRequest.businessType IS NULL AND  ( interfaceRequest.requestDirection = 'OUT' ) ";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_2 =
		"interfaceRequest.businessType =:businessType AND  ( interfaceRequest.requestDirection = 'OUT' ) ";
	private static final String _FINDER_COLUMN_F_BY_DOCUMENTNAMEREF_BUSINESSTYPE_3 =
		"(interfaceRequest.businessType IS NULL OR interfaceRequest.businessType =:businessType) AND  ( interfaceRequest.requestDirection = 'OUT' ) ";
	private static final String _ORDER_BY_ENTITY_ALIAS = "interfaceRequest.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No InterfaceRequest exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No InterfaceRequest exists with the key {";
	

	
}
