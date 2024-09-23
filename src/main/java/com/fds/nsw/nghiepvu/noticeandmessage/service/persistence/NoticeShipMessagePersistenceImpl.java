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
////import com.fds.nsw.nghiepvu.model.NoticeShipMessage;
////import com.fds.nsw.nghiepvu.service.exception.*;
////import com.fds.nsw.nghiepvu.repository.NoticeShipMessageRepository;
////import com.fds.nsw.nghiepvu.modelImpl.NoticeShipMessageModelImpl;
////
////
////import lombok.extern.slf4j.Slf4j;
////
////@Slf4j
////@Service
////public class NoticeShipMessagePersistenceImpl extends BasePersistence {
////	@Autowired
////	NoticeShipMessageRepository repository;
////	@Autowired
////	@Qualifier("blQueryFactory")
////	QueryFactory<NoticeShipMessage> queryFactory;
////	/*
////	 * NOTE FOR DEVELOPERS:
////	 *
////	 * Never modify or reference this class directly. Always use {@link NoticeShipMessageUtil} to access the notice ship message persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
////	 */
////	public NoticeShipMessage create(long id) {
////		NoticeShipMessage noticeShipMessage = new NoticeShipMessage();
////
////
////		//noticeShipMessage.setPrimaryKey(id);
////
////		return noticeShipMessage;
////	}
////
////	/**
////	 * Removes the notice ship message with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param id the primary key of the notice ship message
////	 * @return the notice ship message that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchNoticeShipMessageException if a notice ship message with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public NoticeShipMessage remove(long id)
////		throws NoSuchNoticeShipMessageException, SystemException {
////		return remove(Long.valueOf(id));
////	}
////
////	/**
////	 * Removes the notice ship message with the primary key from the database. Also notifies the appropriate model listeners.
////	 *
////	 * @param primaryKey the primary key of the notice ship message
////	 * @return the notice ship message that was removed
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchNoticeShipMessageException if a notice ship message with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public NoticeShipMessage remove(Serializable primaryKey)
////		throws NoSuchNoticeShipMessageException, SystemException {
////
////
////		try {
////
////
////			NoticeShipMessage noticeShipMessage = findByPrimaryKey(primaryKey);
////
////			if (noticeShipMessage == null) {
////				if (log.isWarnEnabled()) {
////					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
////				}
////
////				throw new NoSuchNoticeShipMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////					primaryKey);
////			}
////
////			repository.delete(noticeShipMessage);
////			return noticeShipMessage;
////		}
////		catch (NoSuchNoticeShipMessageException nsee) {
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
////	public NoticeShipMessage remove(NoticeShipMessage NoticeShipMessage) throws SystemException {
//	removeImpl(NoticeShipMessage);
//	return NoticeShipMessage;
//}
//
//protected NoticeShipMessage removeImpl(NoticeShipMessage noticeShipMessage)
////		throws SystemException {
////		try {
////			repository.delete(noticeShipMessage);
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return noticeShipMessage;
////	}
////
////
////	public NoticeShipMessage updateImpl(
////		com.fds.nsw.nghiepvu.model.NoticeShipMessage noticeShipMessage,
////		boolean merge) throws SystemException {
////		try {
////
////			repository.saveAndFlush(noticeShipMessage);
////
////		} catch (Exception e) {
////			throw processException(e);
////		} finally {
////			// TODO update cache
////		}
////
////		return noticeShipMessage;
////	}
////
////
////	public NoticeShipMessage findByPrimaryKey(Serializable primaryKey)
////		throws NoSuchModelException, SystemException {
////		return findByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the notice ship message with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchNoticeShipMessageException} if it could not be found.
////	 *
////	 * @param id the primary key of the notice ship message
////	 * @return the notice ship message
////	 * @throws vn.gt.dao.noticeandmessage.NoSuchNoticeShipMessageException if a notice ship message with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public NoticeShipMessage findByPrimaryKey(long id)
////		throws NoSuchNoticeShipMessageException, SystemException {
////		NoticeShipMessage noticeShipMessage = fetchByPrimaryKey(id);
////
////		if (noticeShipMessage == null) {
////			if (log.isWarnEnabled()) {
////				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
////			}
////
////			throw new NoSuchNoticeShipMessageException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
////				id);
////		}
////
////		return noticeShipMessage;
////	}
////
////	/**
////	 * Returns the notice ship message with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param primaryKey the primary key of the notice ship message
////	 * @return the notice ship message, or <code>null</code> if a notice ship message with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////
////	public NoticeShipMessage fetchByPrimaryKey(Serializable primaryKey)
////		throws SystemException {
////		return fetchByPrimaryKey(((Long)primaryKey).longValue());
////	}
////
////	/**
////	 * Returns the notice ship message with the primary key or returns <code>null</code> if it could not be found.
////	 *
////	 * @param id the primary key of the notice ship message
////	 * @return the notice ship message, or <code>null</code> if a notice ship message with the primary key could not be found
////	 * @throws SystemException if a system exception occurred
////	 */
////	public NoticeShipMessage fetchByPrimaryKey(long id)
////		throws SystemException {
////		NoticeShipMessage noticeShipMessage = null;
////
////
////
////		if (noticeShipMessage == null) {
////
////
////			boolean hasException = false;
////
////			try {
////
////
////				Optional<NoticeShipMessage> optional = repository.findById(id);
////				if (optional.isPresent()) {
////					noticeShipMessage = optional.get();
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
////		return noticeShipMessage;
////	}
////
////	/**
////	 * Returns all the notice ship messages.
////	 *
////	 * @return the notice ship messages
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<NoticeShipMessage> findAll() throws SystemException {
////		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
////	}
////
////	/**
////	 * Returns a range of all the notice ship messages.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of notice ship messages
////	 * @param end the upper bound of the range of notice ship messages (not inclusive)
////	 * @return the range of notice ship messages
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<NoticeShipMessage> findAll(int start, int end)
////		throws SystemException {
////		return findAll(start, end, null);
////	}
////
////	/**
////	 * Returns an ordered range of all the notice ship messages.
////	 *
////	 * <p>
////	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
////	 * </p>
////	 *
////	 * @param start the lower bound of the range of notice ship messages
////	 * @param end the upper bound of the range of notice ship messages (not inclusive)
////	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
////	 * @return the ordered range of notice ship messages
////	 * @throws SystemException if a system exception occurred
////	 */
////	public List<NoticeShipMessage> findAll(int start, int end,
////		OrderByComparator orderByComparator) throws SystemException {
////		List<NoticeShipMessage> list = null;
////		if (list == null) {
////			StringBundler query = null;
////			String sql = null;
////
////			if (orderByComparator != null) {
////				query = new StringBundler(2 +
////						(orderByComparator.getOrderByFields().length * 3));
////
////				query.append(_SQL_SELECT_NOTICESHIPMESSAGE);
////
////				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
////					orderByComparator);
////
////				sql = query.toString();
////			}
////			else {
////				sql = _SQL_SELECT_NOTICESHIPMESSAGE.concat(NoticeShipMessageModelImpl.ORDER_BY_JPQL);
////			}
////
////
////
////			try {
////
////
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
////
////				list = (List<NoticeShipMessage>) queryFactory.getResultList(builder);
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
////	 * Removes all the notice ship messages from the database.
////	 *
////	 * @throws SystemException if a system exception occurred
////	 */
////	public void removeAll() throws SystemException {
////		for (NoticeShipMessage noticeShipMessage : findAll()) {
////			repository.delete(noticeShipMessage);
////		}
////	}
////
////	/**
////	 * Returns the number of notice ship messages.
////	 *
////	 * @return the number of notice ship messages
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
////				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_NOTICESHIPMESSAGE).build();
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
////	 * Initializes the notice ship message persistence.
////	 */
////	private static final String _SQL_SELECT_NOTICESHIPMESSAGE = "SELECT noticeShipMessage FROM NoticeShipMessage noticeShipMessage";
////	private static final String _SQL_COUNT_NOTICESHIPMESSAGE = "SELECT COUNT(noticeShipMessage) FROM NoticeShipMessage noticeShipMessage";
////	private static final String _ORDER_BY_ENTITY_ALIAS = "noticeShipMessage.";
////	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No NoticeShipMessage exists with the primary key ";
////
////
////
////}
