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

package com.fds.nsw.nghiepvu.asw.service.persistence;

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
import com.fds.nsw.nghiepvu.model.AswmsgMessagequeue;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.AswmsgMessagequeueRepository;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AswmsgMessagequeuePersistenceImpl extends BasePersistence {
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<AswmsgMessagequeue> queryFactory;
	@Autowired
	AswmsgMessagequeueRepository repository;

	/**
	 * Creates a new aswmsg message queue with the primary key. Does not add the
	 * aswmsg message queue to the database.
	 *
	 * @param id the primary key for the new aswmsg message queue
	 * @return the new aswmsg message queue
	 */
	public AswmsgMessagequeue create(long id) {
		AswmsgMessagequeue aswmsgMessageQueue = new AswmsgMessagequeue();

		aswmsgMessageQueue.setId(id);

		return aswmsgMessageQueue;
	}

	/**
	 * Removes the aswmsg message queue with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the aswmsg message queue
	 * @return the aswmsg message queue that was removed
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue remove(long id) throws NoSuchAswmsgMessageQueueException, SystemException {
		return remove(Long.valueOf(id));
	}

	public AswmsgMessagequeue remove(AswmsgMessagequeue CVHH) throws SystemException {
		repository.delete(CVHH);
		return CVHH;
	}

	/**
	 * Removes the aswmsg message queue with the primary key from the database. Also
	 * notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the aswmsg message queue
	 * @return the aswmsg message queue that was removed
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */

	public AswmsgMessagequeue remove(Serializable primaryKey)
			throws NoSuchAswmsgMessageQueueException, SystemException {

		Long id = ((Long) primaryKey).longValue();
		AswmsgMessagequeue aswmsgMessageQueue = null;
		try {
			Optional<AswmsgMessagequeue> optional = repository.findById(id);
			if (optional.isPresent()) {
				aswmsgMessageQueue = optional.get();
			}

			if (aswmsgMessageQueue == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchAswmsgMessageQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(aswmsgMessageQueue);

			return aswmsgMessageQueue;

		} catch (NoSuchAswmsgMessageQueueException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue removeImpl(AswmsgMessagequeue aswmsgMessageQueue) throws SystemException {
		try {
			repository.delete(aswmsgMessageQueue);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return aswmsgMessageQueue;
	}

	public AswmsgMessagequeue updateImpl(AswmsgMessagequeue aswmsgMessageQueue, boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(aswmsgMessageQueue);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return aswmsgMessageQueue;
	}

	/**
	 * Returns the aswmsg message queue with the primary key or throws a
	 * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the aswmsg message queue
	 * @return the aswmsg message queue
	 * @throws com.liferay.portal.NoSuchModelException if a aswmsg message queue
	 *                                                 with the primary key could
	 *                                                 not be found
	 * @throws SystemException                         if a system exception
	 *                                                 occurred
	 */

	public AswmsgMessagequeue findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the aswmsg message queue with the primary key or throws a
	 * {@link vn.dao.gt.asw.NoSuchAswmsgMessageQueueException} if it could not be
	 * found.
	 *
	 * @param id the primary key of the aswmsg message queue
	 * @return the aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByPrimaryKey(long id) throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = fetchByPrimaryKey(id);

		if (aswmsgMessageQueue == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchAswmsgMessageQueueException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return aswmsgMessageQueue;
	}

	/**
	 * Returns the aswmsg message queue with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the aswmsg message queue
	 * @return the aswmsg message queue, or <code>null</code> if a aswmsg message
	 *         queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public AswmsgMessagequeue fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the aswmsg message queue with the primary key or returns
	 * <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the aswmsg message queue
	 * @return the aswmsg message queue, or <code>null</code> if a aswmsg message
	 *         queue with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue fetchByPrimaryKey(long id) throws SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = null;

		if (aswmsgMessageQueue == null) {

			boolean hasException = false;

			try {
				Optional<AswmsgMessagequeue> optional = repository.findById(id);

				if (optional.isPresent()) {

					aswmsgMessageQueue = optional.get();
				}

			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return aswmsgMessageQueue;
	}

	/**
	 * Returns the aswmsg message queue where version = &#63; and messageId = &#63;
	 * or throws a {@link vn.dao.gt.asw.NoSuchAswmsgMessageQueueException} if it
	 * could not be found.
	 *
	 * @param version   the version
	 * @param messageId the message ID
	 * @return the matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByVersionAndMessageId(String version, String messageId)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = fetchByVersionAndMessageId(version, messageId);

		if (aswmsgMessageQueue == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("version=");
			msg.append(version);

			msg.append(", messageId=");
			msg.append(messageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		}

		return aswmsgMessageQueue;
	}

	/**
	 * Returns the aswmsg message queue where version = &#63; and messageId = &#63;
	 * or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param version   the version
	 * @param messageId the message ID
	 * @return the matching aswmsg message queue, or <code>null</code> if a matching
	 *         aswmsg message queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue fetchByVersionAndMessageId(String version, String messageId) throws SystemException {
		return fetchByVersionAndMessageId(version, messageId, true);
	}

	/**
	 * Returns the aswmsg message queue where version = &#63; and messageId = &#63;
	 * or returns <code>null</code> if it could not be found, optionally using the
	 * finder cache.
	 *
	 * @param version           the version
	 * @param messageId         the message ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching aswmsg message queue, or <code>null</code> if a matching
	 *         aswmsg message queue could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public AswmsgMessagequeue fetchByVersionAndMessageId(String version, String messageId, boolean retrieveFromCache)
			throws SystemException {

		AswmsgMessagequeue aswmsgMessageQueue = null;
		if (aswmsgMessageQueue == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			if (version == null) {
				query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_1);
			} else {
				if (version.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_3);
				} else {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_2);
				}
			}

			if (messageId == null) {
				query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_1);
			} else {
				if (messageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_3);
				} else {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_2);
				}
			}

			query.append(ORDER_BY_JPQL);

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(AswmsgMessagequeue.class).build();

				if (version != null) {

					builder.appendNamedParameterMap("version", version);
				}

				if (messageId != null) {

					builder.appendNamedParameterMap("messageId", messageId);
				}

				aswmsgMessageQueue = (AswmsgMessagequeue) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return aswmsgMessageQueue;
	}

	/**
	 * Returns all the aswmsg message queues where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByMessageId(String messageId) throws SystemException {
		return findByMessageId(messageId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues where messageId = &#63;.
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
	 * @param messageId the message ID
	 * @param start     the lower bound of the range of aswmsg message queues
	 * @param end       the upper bound of the range of aswmsg message queues (not
	 *                  inclusive)
	 * @return the range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByMessageId(String messageId, int start, int end) throws SystemException {
		return findByMessageId(messageId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues where messageId =
	 * &#63;.
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
	 * @param messageId         the message ID
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByMessageId(String messageId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		List<AswmsgMessagequeue> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			if (messageId == null) {
				query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_1);
			} else {
				if (messageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_3);
				} else {
					query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

				if (messageId != null) {
					builder.appendNamedParameterMap("messageId", messageId);
				}

				list = queryFactory.getResultList(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first aswmsg message queue in the ordered set where messageId =
	 * &#63;.
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
	 * @param messageId         the message ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByMessageId_First(String messageId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		List<AswmsgMessagequeue> list = findByMessageId(messageId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("messageId=");
			msg.append(messageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last aswmsg message queue in the ordered set where messageId =
	 * &#63;.
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
	 * @param messageId         the message ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByMessageId_Last(String messageId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		int count = countByMessageId(messageId);

		List<AswmsgMessagequeue> list = findByMessageId(messageId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("messageId=");
			msg.append(messageId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the aswmsg message queues before and after the current aswmsg message
	 * queue in the ordered set where messageId = &#63;.
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
	 * @param id                the primary key of the current aswmsg message queue
	 * @param messageId         the message ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue[] findByMessageId_PrevAndNext(long id, String messageId,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByPrimaryKey(id);

		try {

			AswmsgMessagequeue[] array = new AswmsgMessagequeue[3];

			array[0] = getByMessageId_PrevAndNext(aswmsgMessageQueue, messageId, orderByComparator, true);

			array[1] = aswmsgMessageQueue;

			array[2] = getByMessageId_PrevAndNext(aswmsgMessageQueue, messageId, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue getByMessageId_PrevAndNext(AswmsgMessagequeue aswmsgMessageQueue, String messageId,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

		if (messageId == null) {
			query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_1);
		} else {
			if (messageId.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_3);
			} else {
				query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);
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
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(AswmsgMessagequeue.class).build();

		if (messageId != null) {
			builder.appendNamedParameterMap("messageId", messageId);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(aswmsgMessageQueue);

			for (Object value : values) {
				// qPos.add(value);
			}
		}

		List<AswmsgMessagequeue> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the aswmsg message queues where hoSoThuTucId = &#63; and type =
	 * &#63; and function = &#63;.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @param type         the type
	 * @param function     the function
	 * @return the matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByHoSoThuTucId(long hoSoThuTucId, int type, String function)
			throws SystemException {
		return findByHoSoThuTucId(hoSoThuTucId, type, function, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues where hoSoThuTucId = &#63;
	 * and type = &#63; and function = &#63;.
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
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @param type         the type
	 * @param function     the function
	 * @param start        the lower bound of the range of aswmsg message queues
	 * @param end          the upper bound of the range of aswmsg message queues
	 *                     (not inclusive)
	 * @return the range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByHoSoThuTucId(long hoSoThuTucId, int type, String function, int start, int end)
			throws SystemException {
		return findByHoSoThuTucId(hoSoThuTucId, type, function, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues where hoSoThuTucId
	 * = &#63; and type = &#63; and function = &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param type              the type
	 * @param function          the function
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByHoSoThuTucId(long hoSoThuTucId, int type, String function, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		List<AswmsgMessagequeue> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_HOSOTHUTUCID_HOSOTHUTUCID_2);

			query.append(_FINDER_COLUMN_HOSOTHUTUCID_TYPE_2);

			if (function == null) {
				query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_1);
			} else {
				if (function.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_3);
				} else {
					query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

				builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);

				builder.appendNamedParameterMap("type", type);

				if (function != null) {

					builder.appendNamedParameterMap("function", function);
				}

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first aswmsg message queue in the ordered set where hoSoThuTucId
	 * = &#63; and type = &#63; and function = &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByHoSoThuTucId_First(long hoSoThuTucId, int type, String function,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		List<AswmsgMessagequeue> list = findByHoSoThuTucId(hoSoThuTucId, type, function, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("hoSoThuTucId=");
			msg.append(hoSoThuTucId);

			msg.append(", type=");
			msg.append(type);

			msg.append(", function=");
			msg.append(function);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last aswmsg message queue in the ordered set where hoSoThuTucId =
	 * &#63; and type = &#63; and function = &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByHoSoThuTucId_Last(long hoSoThuTucId, int type, String function,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		int count = countByHoSoThuTucId(hoSoThuTucId, type, function);

		List<AswmsgMessagequeue> list = findByHoSoThuTucId(hoSoThuTucId, type, function, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("hoSoThuTucId=");
			msg.append(hoSoThuTucId);

			msg.append(", type=");
			msg.append(type);

			msg.append(", function=");
			msg.append(function);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the aswmsg message queues before and after the current aswmsg message
	 * queue in the ordered set where hoSoThuTucId = &#63; and type = &#63; and
	 * function = &#63;.
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
	 * @param id                the primary key of the current aswmsg message queue
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue[] findByHoSoThuTucId_PrevAndNext(long id, long hoSoThuTucId, int type, String function,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByPrimaryKey(id);

		try {

			AswmsgMessagequeue[] array = new AswmsgMessagequeue[3];

			array[0] = getByHoSoThuTucId_PrevAndNext(aswmsgMessageQueue, hoSoThuTucId, type, function,
					orderByComparator, true);

			array[1] = aswmsgMessageQueue;

			array[2] = getByHoSoThuTucId_PrevAndNext(aswmsgMessageQueue, hoSoThuTucId, type, function,
					orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue getByHoSoThuTucId_PrevAndNext(AswmsgMessagequeue aswmsgMessageQueue, long hoSoThuTucId,
			int type, String function, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

		query.append(_FINDER_COLUMN_HOSOTHUTUCID_HOSOTHUTUCID_2);

		query.append(_FINDER_COLUMN_HOSOTHUTUCID_TYPE_2);

		if (function == null) {
			query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_1);
		} else {
			if (function.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_3);
			} else {
				query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_2);
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
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(AswmsgMessagequeue.class).build();

		builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);
		builder.appendNamedParameterMap("type", type);

		if (function != null) {

			builder.appendNamedParameterMap("function", function);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(aswmsgMessageQueue);

			for (Object value : values) {
				// qPos.add(value);
			}
		}

		List<AswmsgMessagequeue> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the aswmsg message queues where phieuXuLyPhuId = &#63; and type =
	 * &#63; and function = &#63;.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @param type           the type
	 * @param function       the function
	 * @return the matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByPhieuXuLyPhuId(long phieuXuLyPhuId, int type, String function)
			throws SystemException {
		return findByPhieuXuLyPhuId(phieuXuLyPhuId, type, function, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues where phieuXuLyPhuId = &#63;
	 * and type = &#63; and function = &#63;.
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
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @param type           the type
	 * @param function       the function
	 * @param start          the lower bound of the range of aswmsg message queues
	 * @param end            the upper bound of the range of aswmsg message queues
	 *                       (not inclusive)
	 * @return the range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByPhieuXuLyPhuId(long phieuXuLyPhuId, int type, String function, int start,
			int end) throws SystemException {
		return findByPhieuXuLyPhuId(phieuXuLyPhuId, type, function, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues where
	 * phieuXuLyPhuId = &#63; and type = &#63; and function = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param type              the type
	 * @param function          the function
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByPhieuXuLyPhuId(long phieuXuLyPhuId, int type, String function, int start,
			int end, OrderByComparator orderByComparator) throws SystemException {

		List<AswmsgMessagequeue> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(5 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(5);
			}

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_PHIEUXULYPHUID_PHIEUXULYPHUID_2);

			query.append(_FINDER_COLUMN_PHIEUXULYPHUID_TYPE_2);

			if (function == null) {
				query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_1);
			} else {
				if (function.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_3);
				} else {
					query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

				builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

				builder.appendNamedParameterMap("type", type);

				if (function != null) {
					builder.appendNamedParameterMap("function", function);
				}

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first aswmsg message queue in the ordered set where
	 * phieuXuLyPhuId = &#63; and type = &#63; and function = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByPhieuXuLyPhuId_First(long phieuXuLyPhuId, int type, String function,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		List<AswmsgMessagequeue> list = findByPhieuXuLyPhuId(phieuXuLyPhuId, type, function, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("phieuXuLyPhuId=");
			msg.append(phieuXuLyPhuId);

			msg.append(", type=");
			msg.append(type);

			msg.append(", function=");
			msg.append(function);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last aswmsg message queue in the ordered set where phieuXuLyPhuId
	 * = &#63; and type = &#63; and function = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByPhieuXuLyPhuId_Last(long phieuXuLyPhuId, int type, String function,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		int count = countByPhieuXuLyPhuId(phieuXuLyPhuId, type, function);

		List<AswmsgMessagequeue> list = findByPhieuXuLyPhuId(phieuXuLyPhuId, type, function, count - 1, count,
				orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(8);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("phieuXuLyPhuId=");
			msg.append(phieuXuLyPhuId);

			msg.append(", type=");
			msg.append(type);

			msg.append(", function=");
			msg.append(function);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the aswmsg message queues before and after the current aswmsg message
	 * queue in the ordered set where phieuXuLyPhuId = &#63; and type = &#63; and
	 * function = &#63;.
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
	 * @param id                the primary key of the current aswmsg message queue
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param type              the type
	 * @param function          the function
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue[] findByPhieuXuLyPhuId_PrevAndNext(long id, long phieuXuLyPhuId, int type,
			String function, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByPrimaryKey(id);

		try {

			AswmsgMessagequeue[] array = new AswmsgMessagequeue[3];

			array[0] = getByPhieuXuLyPhuId_PrevAndNext(aswmsgMessageQueue, phieuXuLyPhuId, type, function,
					orderByComparator, true);

			array[1] = aswmsgMessageQueue;

			array[2] = getByPhieuXuLyPhuId_PrevAndNext(aswmsgMessageQueue, phieuXuLyPhuId, type, function,
					orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue getByPhieuXuLyPhuId_PrevAndNext(AswmsgMessagequeue aswmsgMessageQueue,
			long phieuXuLyPhuId, int type, String function, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

		query.append(_FINDER_COLUMN_PHIEUXULYPHUID_PHIEUXULYPHUID_2);

		query.append(_FINDER_COLUMN_PHIEUXULYPHUID_TYPE_2);

		if (function == null) {
			query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_1);
		} else {
			if (function.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_3);
			} else {
				query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_2);
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
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(AswmsgMessagequeue.class).build();

		builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

		builder.appendNamedParameterMap("type", type);

		if (function != null) {

			builder.appendNamedParameterMap("function", function);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(aswmsgMessageQueue);

			for (Object value : values) {
				// qPos.add(value);
			}
		}

		List<AswmsgMessagequeue> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the aswmsg message queues where phieuXuLyPhuId = &#63;.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @return the matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayPhieuXuLyPhuId(long phieuXuLyPhuId) throws SystemException {
		return findByLayPhieuXuLyPhuId(phieuXuLyPhuId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues where phieuXuLyPhuId =
	 * &#63;.
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
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @param start          the lower bound of the range of aswmsg message queues
	 * @param end            the upper bound of the range of aswmsg message queues
	 *                       (not inclusive)
	 * @return the range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayPhieuXuLyPhuId(long phieuXuLyPhuId, int start, int end)
			throws SystemException {
		return findByLayPhieuXuLyPhuId(phieuXuLyPhuId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues where
	 * phieuXuLyPhuId = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayPhieuXuLyPhuId(long phieuXuLyPhuId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		List<AswmsgMessagequeue> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_LAYPHIEUXULYPHUID_PHIEUXULYPHUID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

				builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first aswmsg message queue in the ordered set where
	 * phieuXuLyPhuId = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByLayPhieuXuLyPhuId_First(long phieuXuLyPhuId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		List<AswmsgMessagequeue> list = findByLayPhieuXuLyPhuId(phieuXuLyPhuId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("phieuXuLyPhuId=");
			msg.append(phieuXuLyPhuId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last aswmsg message queue in the ordered set where phieuXuLyPhuId
	 * = &#63;.
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
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByLayPhieuXuLyPhuId_Last(long phieuXuLyPhuId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		int count = countByLayPhieuXuLyPhuId(phieuXuLyPhuId);

		List<AswmsgMessagequeue> list = findByLayPhieuXuLyPhuId(phieuXuLyPhuId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("phieuXuLyPhuId=");
			msg.append(phieuXuLyPhuId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the aswmsg message queues before and after the current aswmsg message
	 * queue in the ordered set where phieuXuLyPhuId = &#63;.
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
	 * @param id                the primary key of the current aswmsg message queue
	 * @param phieuXuLyPhuId    the phieu xu ly phu ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue[] findByLayPhieuXuLyPhuId_PrevAndNext(long id, long phieuXuLyPhuId,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByPrimaryKey(id);

		try {

			AswmsgMessagequeue[] array = new AswmsgMessagequeue[3];

			array[0] = getByLayPhieuXuLyPhuId_PrevAndNext(aswmsgMessageQueue, phieuXuLyPhuId, orderByComparator, true);

			array[1] = aswmsgMessageQueue;

			array[2] = getByLayPhieuXuLyPhuId_PrevAndNext(aswmsgMessageQueue, phieuXuLyPhuId, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue getByLayPhieuXuLyPhuId_PrevAndNext(AswmsgMessagequeue aswmsgMessageQueue,
			long phieuXuLyPhuId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

		query.append(_FINDER_COLUMN_LAYPHIEUXULYPHUID_PHIEUXULYPHUID_2);

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
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(AswmsgMessagequeue.class).build();

		builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(aswmsgMessageQueue);

			for (Object value : values) {
				// qPos.add(value);
			}
		}

		List<AswmsgMessagequeue> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the aswmsg message queues where hoSoThuTucId = &#63;.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @return the matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayHoSoThuTucId(long hoSoThuTucId) throws SystemException {
		return findByLayHoSoThuTucId(hoSoThuTucId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues where hoSoThuTucId = &#63;.
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
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @param start        the lower bound of the range of aswmsg message queues
	 * @param end          the upper bound of the range of aswmsg message queues
	 *                     (not inclusive)
	 * @return the range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayHoSoThuTucId(long hoSoThuTucId, int start, int end)
			throws SystemException {
		return findByLayHoSoThuTucId(hoSoThuTucId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues where hoSoThuTucId
	 * = &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findByLayHoSoThuTucId(long hoSoThuTucId, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {

		List<AswmsgMessagequeue> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_LAYHOSOTHUTUCID_HOSOTHUTUCID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(ORDER_BY_JPQL);
			}

			String sql = query.toString();

			QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
					.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

			try {
				builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first aswmsg message queue in the ordered set where hoSoThuTucId
	 * = &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByLayHoSoThuTucId_First(long hoSoThuTucId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		List<AswmsgMessagequeue> list = findByLayHoSoThuTucId(hoSoThuTucId, 0, 1, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("hoSoThuTucId=");
			msg.append(hoSoThuTucId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the last aswmsg message queue in the ordered set where hoSoThuTucId =
	 * &#63;.
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
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a matching aswmsg
	 *                                                         message queue could
	 *                                                         not be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue findByLayHoSoThuTucId_Last(long hoSoThuTucId, OrderByComparator orderByComparator)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		int count = countByLayHoSoThuTucId(hoSoThuTucId);

		List<AswmsgMessagequeue> list = findByLayHoSoThuTucId(hoSoThuTucId, count - 1, count, orderByComparator);

		if (list.isEmpty()) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("hoSoThuTucId=");
			msg.append(hoSoThuTucId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			throw new NoSuchAswmsgMessageQueueException(msg.toString());
		} else {
			return list.get(0);
		}
	}

	/**
	 * Returns the aswmsg message queues before and after the current aswmsg message
	 * queue in the ordered set where hoSoThuTucId = &#63;.
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
	 * @param id                the primary key of the current aswmsg message queue
	 * @param hoSoThuTucId      the ho so thu tuc ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next aswmsg message queue
	 * @throws vn.dao.gt.asw.NoSuchAswmsgMessageQueueException if a aswmsg message
	 *                                                         queue with the
	 *                                                         primary key could not
	 *                                                         be found
	 * @throws SystemException                                 if a system exception
	 *                                                         occurred
	 */
	public AswmsgMessagequeue[] findByLayHoSoThuTucId_PrevAndNext(long id, long hoSoThuTucId,
			OrderByComparator orderByComparator) throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByPrimaryKey(id);

		try {

			AswmsgMessagequeue[] array = new AswmsgMessagequeue[3];

			array[0] = getByLayHoSoThuTucId_PrevAndNext(aswmsgMessageQueue, hoSoThuTucId, orderByComparator, true);

			array[1] = aswmsgMessageQueue;

			array[2] = getByLayHoSoThuTucId_PrevAndNext(aswmsgMessageQueue, hoSoThuTucId, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected AswmsgMessagequeue getByLayHoSoThuTucId_PrevAndNext(AswmsgMessagequeue aswmsgMessageQueue,
			long hoSoThuTucId, OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE);

		query.append(_FINDER_COLUMN_LAYHOSOTHUTUCID_HOSOTHUTUCID_2);

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
			query.append(ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(AswmsgMessagequeue.class).build();

		builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(aswmsgMessageQueue);

			for (Object value : values) {
				// qPos.add(value);
			}
		}

		List<AswmsgMessagequeue> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the aswmsg message queues.
	 *
	 * @return the aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the aswmsg message queues.
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
	 * @param start the lower bound of the range of aswmsg message queues
	 * @param end   the upper bound of the range of aswmsg message queues (not
	 *              inclusive)
	 * @return the range of aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the aswmsg message queues.
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
	 * @param start             the lower bound of the range of aswmsg message
	 *                          queues
	 * @param end               the upper bound of the range of aswmsg message
	 *                          queues (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public List<AswmsgMessagequeue> findAll(int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<AswmsgMessagequeue> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_ASWMSGMESSAGEQUEUE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_ASWMSGMESSAGEQUEUE.concat(ORDER_BY_JPQL);
			}

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(AswmsgMessagequeue.class).build();

				list = queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes the aswmsg message queue where version = &#63; and messageId = &#63;
	 * from the database.
	 *
	 * @param version   the version
	 * @param messageId the message ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByVersionAndMessageId(String version, String messageId)
			throws NoSuchAswmsgMessageQueueException, SystemException {
		AswmsgMessagequeue aswmsgMessageQueue = findByVersionAndMessageId(version, messageId);

		repository.delete(aswmsgMessageQueue);

	}

	/**
	 * Removes all the aswmsg message queues where messageId = &#63; from the
	 * database.
	 *
	 * @param messageId the message ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByMessageId(String messageId) throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findByMessageId(messageId)) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Removes all the aswmsg message queues where hoSoThuTucId = &#63; and type =
	 * &#63; and function = &#63; from the database.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @param type         the type
	 * @param function     the function
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByHoSoThuTucId(long hoSoThuTucId, int type, String function) throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findByHoSoThuTucId(hoSoThuTucId, type, function)) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Removes all the aswmsg message queues where phieuXuLyPhuId = &#63; and type =
	 * &#63; and function = &#63; from the database.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @param type           the type
	 * @param function       the function
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPhieuXuLyPhuId(long phieuXuLyPhuId, int type, String function) throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findByPhieuXuLyPhuId(phieuXuLyPhuId, type, function)) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Removes all the aswmsg message queues where phieuXuLyPhuId = &#63; from the
	 * database.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLayPhieuXuLyPhuId(long phieuXuLyPhuId) throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findByLayPhieuXuLyPhuId(phieuXuLyPhuId)) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Removes all the aswmsg message queues where hoSoThuTucId = &#63; from the
	 * database.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByLayHoSoThuTucId(long hoSoThuTucId) throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findByLayHoSoThuTucId(hoSoThuTucId)) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Removes all the aswmsg message queues from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (AswmsgMessagequeue aswmsgMessageQueue : findAll()) {
			repository.delete(aswmsgMessageQueue);
			;
		}
	}

	/**
	 * Returns the number of aswmsg message queues where version = &#63; and
	 * messageId = &#63;.
	 *
	 * @param version   the version
	 * @param messageId the message ID
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByVersionAndMessageId(String version, String messageId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			if (version == null) {
				query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_1);
			} else {
				if (version.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_3);
				} else {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_2);
				}
			}

			if (messageId == null) {
				query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_1);
			} else {
				if (messageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_3);
				} else {
					query.append(_FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_2);
				}
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				if (version != null) {
					builder.appendNamedParameterMap("version", version);
				}

				if (messageId != null) {
					builder.appendNamedParameterMap(messageId, messageId);
				}

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
	 * Returns the number of aswmsg message queues where messageId = &#63;.
	 *
	 * @param messageId the message ID
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMessageId(String messageId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			if (messageId == null) {
				query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_1);
			} else {
				if (messageId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_3);
				} else {
					query.append(_FINDER_COLUMN_MESSAGEID_MESSAGEID_2);
				}
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				if (messageId != null) {
					builder.appendNamedParameterMap("messageId", messageId);
				}

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
	 * Returns the number of aswmsg message queues where hoSoThuTucId = &#63; and
	 * type = &#63; and function = &#63;.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @param type         the type
	 * @param function     the function
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByHoSoThuTucId(long hoSoThuTucId, int type, String function) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_HOSOTHUTUCID_HOSOTHUTUCID_2);

			query.append(_FINDER_COLUMN_HOSOTHUTUCID_TYPE_2);

			if (function == null) {
				query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_1);
			} else {
				if (function.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_3);
				} else {
					query.append(_FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_2);
				}
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);

				builder.appendNamedParameterMap("type", type);

				if (function != null) {
					builder.appendNamedParameterMap("function", function);
				}

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
	 * Returns the number of aswmsg message queues where phieuXuLyPhuId = &#63; and
	 * type = &#63; and function = &#63;.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @param type           the type
	 * @param function       the function
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPhieuXuLyPhuId(long phieuXuLyPhuId, int type, String function) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_PHIEUXULYPHUID_PHIEUXULYPHUID_2);

			query.append(_FINDER_COLUMN_PHIEUXULYPHUID_TYPE_2);

			if (function == null) {
				query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_1);
			} else {
				if (function.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_3);
				} else {
					query.append(_FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_2);
				}
			}

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(AswmsgMessagequeue.class).build();

				builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

				builder.appendNamedParameterMap("type", type);

				if (function != null) {

					builder.appendNamedParameterMap("function", function);
				}

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
	 * Returns the number of aswmsg message queues where phieuXuLyPhuId = &#63;.
	 *
	 * @param phieuXuLyPhuId the phieu xu ly phu ID
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLayPhieuXuLyPhuId(long phieuXuLyPhuId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_LAYPHIEUXULYPHUID_PHIEUXULYPHUID_2);

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(AswmsgMessagequeue.class).build();
				builder.appendNamedParameterMap("phieuXuLyPhuId", phieuXuLyPhuId);

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
	 * Returns the number of aswmsg message queues where hoSoThuTucId = &#63;.
	 *
	 * @param hoSoThuTucId the ho so thu tuc ID
	 * @return the number of matching aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countByLayHoSoThuTucId(long hoSoThuTucId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE);

			query.append(_FINDER_COLUMN_LAYHOSOTHUTUCID_HOSOTHUTUCID_2);

			String sql = query.toString();

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(AswmsgMessagequeue.class).build();

				builder.appendNamedParameterMap("hoSoThuTucId", hoSoThuTucId);

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
	 * Returns the number of aswmsg message queues.
	 *
	 * @return the number of aswmsg message queues
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_ASWMSGMESSAGEQUEUE)
						.entityClass(AswmsgMessagequeue.class).build();

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

	public void destroy() {

	}

	private static final String _SQL_SELECT_ASWMSGMESSAGEQUEUE = "SELECT aswmsgMessageQueue FROM AswmsgMessagequeue aswmsgMessageQueue";
	private static final String _SQL_SELECT_ASWMSGMESSAGEQUEUE_WHERE = "SELECT aswmsgMessageQueue FROM AswmsgMessagequeue aswmsgMessageQueue WHERE ";
	private static final String _SQL_COUNT_ASWMSGMESSAGEQUEUE = "SELECT COUNT(aswmsgMessageQueue) FROM AswmsgMessagequeue aswmsgMessageQueue";
	private static final String _SQL_COUNT_ASWMSGMESSAGEQUEUE_WHERE = "SELECT COUNT(aswmsgMessageQueue) FROM AswmsgMessagequeue aswmsgMessageQueue WHERE ";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_1 = "aswmsgMessageQueue.version IS NULL AND ";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_2 = "aswmsgMessageQueue.version = :version AND ";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_VERSION_3 = "(aswmsgMessageQueue.version IS NULL OR aswmsgMessageQueue.version = :version) AND ";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_1 = "aswmsgMessageQueue.messageId IS NULL";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_2 = "aswmsgMessageQueue.messageId = :messageId";
	private static final String _FINDER_COLUMN_VERSIONANDMESSAGEID_MESSAGEID_3 = "(aswmsgMessageQueue.messageId IS NULL OR aswmsgMessageQueue.messageId = :messageId)";
	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_1 = "aswmsgMessageQueue.messageId IS NULL";
	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_2 = "aswmsgMessageQueue.messageId = :messageId";
	private static final String _FINDER_COLUMN_MESSAGEID_MESSAGEID_3 = "(aswmsgMessageQueue.messageId IS NULL OR aswmsgMessageQueue.messageId = :messageId)";
	private static final String _FINDER_COLUMN_HOSOTHUTUCID_HOSOTHUTUCID_2 = "aswmsgMessageQueue.hoSoThuTucId = :hoSoThuTucId AND ";
	private static final String _FINDER_COLUMN_HOSOTHUTUCID_TYPE_2 = "aswmsgMessageQueue.type = :type AND ";
	private static final String _FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_1 = "aswmsgMessageQueue.function IS NULL";
	private static final String _FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_2 = "aswmsgMessageQueue.function = :function";
	private static final String _FINDER_COLUMN_HOSOTHUTUCID_FUNCTION_3 = "(aswmsgMessageQueue.function IS NULL OR aswmsgMessageQueue.function = :function)";
	private static final String _FINDER_COLUMN_PHIEUXULYPHUID_PHIEUXULYPHUID_2 = "aswmsgMessageQueue.phieuXuLyPhuId = :phieuXuLyPhuId AND ";
	private static final String _FINDER_COLUMN_PHIEUXULYPHUID_TYPE_2 = "aswmsgMessageQueue.type = :type AND ";
	private static final String _FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_1 = "aswmsgMessageQueue.function IS NULL";
	private static final String _FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_2 = "aswmsgMessageQueue.function = :function";
	private static final String _FINDER_COLUMN_PHIEUXULYPHUID_FUNCTION_3 = "(aswmsgMessageQueue.function IS NULL OR aswmsgMessageQueue.function = :function)";
	private static final String _FINDER_COLUMN_LAYPHIEUXULYPHUID_PHIEUXULYPHUID_2 = "aswmsgMessageQueue.phieuXuLyPhuId = :phieuXuLyPhuId";
	private static final String _FINDER_COLUMN_LAYHOSOTHUTUCID_HOSOTHUTUCID_2 = "aswmsgMessageQueue.hoSoThuTucId = :hoSoThuTucId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "aswmsgMessageQueue.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No AswmsgMessagequeue exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No AswmsgMessagequeue exists with the key {";
	public static final String TABLE_NAME = "aswmsg_messagequeue";
	public static final Object[][] TABLE_COLUMNS = { { "id", Types.BIGINT }, { "version", Types.VARCHAR },
			{ "messageId", Types.VARCHAR }, { "sendername", Types.VARCHAR }, { "senderidentity", Types.VARCHAR },
			{ "sendercountrycode", Types.VARCHAR }, { "senderministrycode", Types.VARCHAR },
			{ "senderorganizationcode", Types.VARCHAR }, { "senderunitcode", Types.VARCHAR },
			{ "receivername", Types.VARCHAR }, { "receiveridentity", Types.VARCHAR },
			{ "receivercountrycode", Types.VARCHAR }, { "receiverministrycode", Types.VARCHAR },
			{ "receiverorganizationcode", Types.VARCHAR }, { "receiverunitcode", Types.VARCHAR },
			{ "documenttype", Types.VARCHAR }, { "type", Types.INTEGER }, { "function", Types.VARCHAR },
			{ "reference", Types.BIGINT }, { "prereference", Types.BIGINT }, { "documentyear", Types.INTEGER },
			{ "senddate", Types.TIMESTAMP }, { "signature", Types.VARCHAR }, { "systemsignature", Types.VARCHAR },
			{ "allcontent", Types.VARCHAR }, { "createdtime", Types.TIMESTAMP }, { "webservice", Types.INTEGER },
			{ "validated", Types.INTEGER }, { "priority", Types.INTEGER }, { "description", Types.VARCHAR },
			{ "validationcode", Types.VARCHAR }, { "solangui", Types.INTEGER }, { "hoSoThuTucId", Types.BIGINT },
			{ "phieuXuLyPhuId", Types.BIGINT } };
	public static final String TABLE_SQL_CREATE = "create table aswmsg_messagequeue (id LONG not null primary key,version VARCHAR(75) null,messageId VARCHAR(75) null,sendername VARCHAR(75) null,senderidentity VARCHAR(75) null,sendercountrycode VARCHAR(75) null,senderministrycode VARCHAR(75) null,senderorganizationcode VARCHAR(75) null,senderunitcode VARCHAR(75) null,receivername VARCHAR(75) null,receiveridentity VARCHAR(75) null,receivercountrycode VARCHAR(75) null,receiverministrycode VARCHAR(75) null,receiverorganizationcode VARCHAR(75) null,receiverunitcode VARCHAR(75) null,documenttype VARCHAR(75) null,type INTEGER,function VARCHAR(75) null,reference LONG,prereference LONG,documentyear INTEGER,senddate DATE null,signature VARCHAR(75) null,systemsignature VARCHAR(75) null,allcontent VARCHAR(75) null,createdtime DATE null,webservice INTEGER,validated INTEGER,priority INTEGER,description VARCHAR(75) null,validationcode VARCHAR(75) null,solangui INTEGER,hoSoThuTucId LONG,phieuXuLyPhuId LONG)";
	public static final String TABLE_SQL_DROP = "drop table aswmsg_messagequeue";
	public static final String ORDER_BY_JPQL = " ORDER BY aswmsgMessageQueue.priority DESC";
	public static final String ORDER_BY_SQL = " ORDER BY aswmsg_messagequeue.priority DESC";
	public static final String DATA_SOURCE = "gtDataSource";
	public static final String SESSION_FACTORY = "liferaySessionFactory";
	public static final String TX_MANAGER = "liferayTransactionManager";

}