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

import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanelId;
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
import com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.IssueShiftingOrderChanelRepository;
import com.fds.nsw.nghiepvu.modelImpl.IssueShiftingOrderChanelModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class IssueShiftingOrderChanelPersistenceImpl extends BasePersistence {
	@Autowired
	IssueShiftingOrderChanelRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<IssueShiftingOrderChanel> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IssueShiftingOrderChanelUtil} to access the issue shifting order chanel persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public IssueShiftingOrderChanel create(
		IssueShiftingOrderChanelId issueShiftingOrderChanelPK) {
		IssueShiftingOrderChanel issueShiftingOrderChanel = new IssueShiftingOrderChanel();

		
		//issueShiftingOrderChanel.setPrimaryKey(id);

		return issueShiftingOrderChanel;
	}

	/**
	 * Removes the issue shifting order chanel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the issue shifting order chanel
	 * @return the issue shifting order chanel that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel remove(
		IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		return remove((Serializable)issueShiftingOrderChanelPK);
	}

	/**
	 * Removes the issue shifting order chanel with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the issue shifting order chanel
	 * @return the issue shifting order chanel that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueShiftingOrderChanel remove(Serializable primaryKey)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		

		try {
			

			IssueShiftingOrderChanel issueShiftingOrderChanel = findByPrimaryKey(primaryKey);

			if (issueShiftingOrderChanel == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIssueShiftingOrderChanelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(issueShiftingOrderChanel);
			return issueShiftingOrderChanel;
		}
		catch (NoSuchIssueShiftingOrderChanelException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public IssueShiftingOrderChanel remove(IssueShiftingOrderChanel IssueShiftingOrderChanel) throws SystemException {
	removeImpl(IssueShiftingOrderChanel);
	return IssueShiftingOrderChanel;
}

protected IssueShiftingOrderChanel removeImpl(
		IssueShiftingOrderChanel issueShiftingOrderChanel)
		throws SystemException {
		try {
			repository.delete(issueShiftingOrderChanel);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueShiftingOrderChanel;
	}

	
	public IssueShiftingOrderChanel updateImpl(
		com.fds.nsw.nghiepvu.model.IssueShiftingOrderChanel issueShiftingOrderChanel,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(issueShiftingOrderChanel);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return issueShiftingOrderChanel;
	}

	
	public IssueShiftingOrderChanel findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey((IssueShiftingOrderChanelId)primaryKey);
	}

	/**
	 * Returns the issue shifting order chanel with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException} if it could not be found.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the issue shifting order chanel
	 * @return the issue shifting order chanel
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel findByPrimaryKey(
		IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = fetchByPrimaryKey(issueShiftingOrderChanelPK);

		if (issueShiftingOrderChanel == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					issueShiftingOrderChanelPK);
			}

			throw new NoSuchIssueShiftingOrderChanelException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				issueShiftingOrderChanelPK);
		}

		return issueShiftingOrderChanel;
	}

	/**
	 * Returns the issue shifting order chanel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the issue shifting order chanel
	 * @return the issue shifting order chanel, or <code>null</code> if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public IssueShiftingOrderChanel fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey((IssueShiftingOrderChanelId)primaryKey);
	}

	/**
	 * Returns the issue shifting order chanel with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the issue shifting order chanel
	 * @return the issue shifting order chanel, or <code>null</code> if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel fetchByPrimaryKey(
		IssueShiftingOrderChanelId issueShiftingOrderChanelPK)
		throws SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = null;

		

		if (issueShiftingOrderChanel == null) {
			

			boolean hasException = false;

			try {
				

				Optional<IssueShiftingOrderChanel> optional = repository.findById(issueShiftingOrderChanelPK);
				if (optional.isPresent()) {
					issueShiftingOrderChanel = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return issueShiftingOrderChanel;
	}

	/**
	 * Returns all the issue shifting order chanels where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @return the matching issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findByShiftingOrderId(
		long shiftingOrderId) throws SystemException {
		return findByShiftingOrderId(shiftingOrderId, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting order chanels where shiftingOrderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param start the lower bound of the range of issue shifting order chanels
	 * @param end the upper bound of the range of issue shifting order chanels (not inclusive)
	 * @return the range of matching issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findByShiftingOrderId(
		long shiftingOrderId, int start, int end) throws SystemException {
		return findByShiftingOrderId(shiftingOrderId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting order chanels where shiftingOrderId = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param start the lower bound of the range of issue shifting order chanels
	 * @param end the upper bound of the range of issue shifting order chanels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findByShiftingOrderId(
		long shiftingOrderId, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrderChanel> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ISSUESHIFTINGORDERCHANEL_WHERE);

			query.append(_FINDER_COLUMN_SHIFTINGORDERID_SHIFTINGORDERID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(IssueShiftingOrderChanelModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("shiftingOrderId", shiftingOrderId);

				list = (List<IssueShiftingOrderChanel>)queryFactory.getResultList(builder);
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
	 * Returns the first issue shifting order chanel in the ordered set where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order chanel
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a matching issue shifting order chanel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel findByShiftingOrderId_First(
		long shiftingOrderId, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = fetchByShiftingOrderId_First(shiftingOrderId,
				orderByComparator);

		if (issueShiftingOrderChanel != null) {
			return issueShiftingOrderChanel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shiftingOrderId=");
		msg.append(shiftingOrderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderChanelException(msg.toString());
	}

	/**
	 * Returns the first issue shifting order chanel in the ordered set where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching issue shifting order chanel, or <code>null</code> if a matching issue shifting order chanel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel fetchByShiftingOrderId_First(
		long shiftingOrderId, OrderByComparator orderByComparator)
		throws SystemException {
		List<IssueShiftingOrderChanel> list = findByShiftingOrderId(shiftingOrderId,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last issue shifting order chanel in the ordered set where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order chanel
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a matching issue shifting order chanel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel findByShiftingOrderId_Last(
		long shiftingOrderId, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = fetchByShiftingOrderId_Last(shiftingOrderId,
				orderByComparator);

		if (issueShiftingOrderChanel != null) {
			return issueShiftingOrderChanel;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("shiftingOrderId=");
		msg.append(shiftingOrderId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchIssueShiftingOrderChanelException(msg.toString());
	}

	/**
	 * Returns the last issue shifting order chanel in the ordered set where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching issue shifting order chanel, or <code>null</code> if a matching issue shifting order chanel could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel fetchByShiftingOrderId_Last(
		long shiftingOrderId, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByShiftingOrderId(shiftingOrderId);

		List<IssueShiftingOrderChanel> list = findByShiftingOrderId(shiftingOrderId,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the issue shifting order chanels before and after the current issue shifting order chanel in the ordered set where shiftingOrderId = &#63;.
	 *
	 * @param issueShiftingOrderChanelPK the primary key of the current issue shifting order chanel
	 * @param shiftingOrderId the shifting order ID
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next issue shifting order chanel
	 * @throws vn.gt.dao.noticeandmessage.NoSuchIssueShiftingOrderChanelException if a issue shifting order chanel with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public IssueShiftingOrderChanel[] findByShiftingOrderId_PrevAndNext(
		IssueShiftingOrderChanelId issueShiftingOrderChanelPK,
		long shiftingOrderId, OrderByComparator orderByComparator)
		throws NoSuchIssueShiftingOrderChanelException, SystemException {
		IssueShiftingOrderChanel issueShiftingOrderChanel = findByPrimaryKey(issueShiftingOrderChanelPK);

		

		try {
			

			IssueShiftingOrderChanel[] array = new IssueShiftingOrderChanel[3];

			array[0] = getByShiftingOrderId_PrevAndNext(
					issueShiftingOrderChanel, shiftingOrderId,
					orderByComparator, true);

			array[1] = issueShiftingOrderChanel;

			array[2] = getByShiftingOrderId_PrevAndNext(
					issueShiftingOrderChanel, shiftingOrderId,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected IssueShiftingOrderChanel getByShiftingOrderId_PrevAndNext(
		 IssueShiftingOrderChanel issueShiftingOrderChanel,
		long shiftingOrderId, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ISSUESHIFTINGORDERCHANEL_WHERE);

		query.append(_FINDER_COLUMN_SHIFTINGORDERID_SHIFTINGORDERID_2);

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
			query.append(IssueShiftingOrderChanelModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("shiftingOrderId", shiftingOrderId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(issueShiftingOrderChanel);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<IssueShiftingOrderChanel> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the issue shifting order chanels.
	 *
	 * @return the issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the issue shifting order chanels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue shifting order chanels
	 * @param end the upper bound of the range of issue shifting order chanels (not inclusive)
	 * @return the range of issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the issue shifting order chanels.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of issue shifting order chanels
	 * @param end the upper bound of the range of issue shifting order chanels (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public List<IssueShiftingOrderChanel> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<IssueShiftingOrderChanel> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ISSUESHIFTINGORDERCHANEL);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_ISSUESHIFTINGORDERCHANEL.concat(IssueShiftingOrderChanelModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<IssueShiftingOrderChanel>) queryFactory.getResultList(builder);
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
	 * Removes all the issue shifting order chanels where shiftingOrderId = &#63; from the database.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByShiftingOrderId(long shiftingOrderId)
		throws SystemException {
		for (IssueShiftingOrderChanel issueShiftingOrderChanel : findByShiftingOrderId(
				shiftingOrderId)) {
			repository.delete(issueShiftingOrderChanel);
		}
	}

	/**
	 * Removes all the issue shifting order chanels from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (IssueShiftingOrderChanel issueShiftingOrderChanel : findAll()) {
			repository.delete(issueShiftingOrderChanel);
		}
	}

	/**
	 * Returns the number of issue shifting order chanels where shiftingOrderId = &#63;.
	 *
	 * @param shiftingOrderId the shifting order ID
	 * @return the number of matching issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public int countByShiftingOrderId(long shiftingOrderId)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ISSUESHIFTINGORDERCHANEL_WHERE);

			query.append(_FINDER_COLUMN_SHIFTINGORDERID_SHIFTINGORDERID_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("shiftingOrderId", shiftingOrderId);

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
	 * Returns the number of issue shifting order chanels.
	 *
	 * @return the number of issue shifting order chanels
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ISSUESHIFTINGORDERCHANEL).build();

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
	 * Initializes the issue shifting order chanel persistence.
	 */
	private static final String _SQL_SELECT_ISSUESHIFTINGORDERCHANEL = "SELECT issueShiftingOrderChanel FROM IssueShiftingOrderChanel issueShiftingOrderChanel";
	private static final String _SQL_SELECT_ISSUESHIFTINGORDERCHANEL_WHERE = "SELECT issueShiftingOrderChanel FROM IssueShiftingOrderChanel issueShiftingOrderChanel WHERE ";
	private static final String _SQL_COUNT_ISSUESHIFTINGORDERCHANEL = "SELECT COUNT(issueShiftingOrderChanel) FROM IssueShiftingOrderChanel issueShiftingOrderChanel";
	private static final String _SQL_COUNT_ISSUESHIFTINGORDERCHANEL_WHERE = "SELECT COUNT(issueShiftingOrderChanel) FROM IssueShiftingOrderChanel issueShiftingOrderChanel WHERE ";
	private static final String _FINDER_COLUMN_SHIFTINGORDERID_SHIFTINGORDERID_2 =
		"issueShiftingOrderChanel.id.shiftingOrderId =:shiftingOrderId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "issueShiftingOrderChanel.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IssueShiftingOrderChanel exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No IssueShiftingOrderChanel exists with the key {";
	

	
}
