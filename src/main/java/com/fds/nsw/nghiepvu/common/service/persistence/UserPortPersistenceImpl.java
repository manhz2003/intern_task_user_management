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
import com.fds.nsw.nghiepvu.model.UserPort;
import com.fds.nsw.nghiepvu.service.exception.*;

import com.fds.nsw.nghiepvu.modelImpl.UserPortModelImpl;

import com.fds.nsw.nghiepvu.repository.UserPortRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserPortPersistenceImpl extends BasePersistence {
	@Autowired
	UserPortRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<UserPort> queryFactory;

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link
	 * UserPortUtil} to access the user port persistence. Modify
	 * <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public UserPort create(long id) {
		UserPort userPort = new UserPort();

		// userPort.setPrimaryKey(id);

		return userPort;
	}

	/**
	 * Removes the user port with the primary key from the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param id the primary key of the user port
	 * @return the user port that was removed
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a user port with the
	 *                                                  primary key could not be
	 *                                                  found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort remove(long id) throws NoSuchUserPortException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the user port with the primary key from the database. Also notifies
	 * the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user port
	 * @return the user port that was removed
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a user port with the
	 *                                                  primary key could not be
	 *                                                  found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */

	public UserPort remove(Serializable primaryKey) throws NoSuchUserPortException, SystemException {

		try {

			UserPort userPort = findByPrimaryKey(primaryKey);

			if (userPort == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(userPort);
			return userPort;
		} catch (NoSuchUserPortException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	public UserPort remove(UserPort UserPort) throws SystemException {
	removeImpl(UserPort);
	return UserPort;
}

protected UserPort removeImpl(UserPort userPort) throws SystemException {
		try {
			repository.delete(userPort);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return userPort;
	}

	public UserPort updateImpl(UserPort userPort, boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(userPort);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return userPort;
	}

	public UserPort findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the user port with the primary key or throws a
	 * {@link vn.gt.dao.common.NoSuchUserPortException} if it could not be found.
	 *
	 * @param id the primary key of the user port
	 * @return the user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a user port with the
	 *                                                  primary key could not be
	 *                                                  found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByPrimaryKey(long id) throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByPrimaryKey(id);

		if (userPort == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchUserPortException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
		}

		return userPort;
	}

	/**
	 * Returns the user port with the primary key or returns <code>null</code> if it
	 * could not be found.
	 *
	 * @param primaryKey the primary key of the user port
	 * @return the user port, or <code>null</code> if a user port with the primary
	 *         key could not be found
	 * @throws SystemException if a system exception occurred
	 */

	public UserPort fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the user port with the primary key or returns <code>null</code> if it
	 * could not be found.
	 *
	 * @param id the primary key of the user port
	 * @return the user port, or <code>null</code> if a user port with the primary
	 *         key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByPrimaryKey(long id) throws SystemException {
		UserPort userPort = null;

		if (userPort == null) {

			boolean hasException = false;

			try {

				Optional<UserPort> optional = repository.findById(id);
				if (optional.isPresent()) {
					userPort = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return userPort;
	}

	/**
	 * Returns all the user ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByPortCode(String portCode) throws SystemException {
		return findByPortCode(portCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user ports where portCode = &#63;.
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
	 * @param portCode the port code
	 * @param start    the lower bound of the range of user ports
	 * @param end      the upper bound of the range of user ports (not inclusive)
	 * @return the range of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByPortCode(String portCode, int start, int end) throws SystemException {
		return findByPortCode(portCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user ports where portCode = &#63;.
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
	 * @param portCode          the port code
	 * @param start             the lower bound of the range of user ports
	 * @param end               the upper bound of the range of user ports (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByPortCode(String portCode, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {
		List<UserPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USERPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			} else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(UserPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(UserPort.class).build();

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				list = (List<UserPort>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode          the port code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a matching user port
	 *                                                  could not be found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByPortCode_First(String portCode, OrderByComparator orderByComparator)
			throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByPortCode_First(portCode, orderByComparator);

		if (userPort != null) {
			return userPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserPortException(msg.toString());
	}

	/**
	 * Returns the first user port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode          the port code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user port, or <code>null</code> if a matching user
	 *         port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByPortCode_First(String portCode, OrderByComparator orderByComparator) throws SystemException {
		List<UserPort> list = findByPortCode(portCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode          the port code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a matching user port
	 *                                                  could not be found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByPortCode_Last(String portCode, OrderByComparator orderByComparator)
			throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByPortCode_Last(portCode, orderByComparator);

		if (userPort != null) {
			return userPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserPortException(msg.toString());
	}

	/**
	 * Returns the last user port in the ordered set where portCode = &#63;.
	 *
	 * @param portCode          the port code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user port, or <code>null</code> if a matching user
	 *         port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByPortCode_Last(String portCode, OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortCode(portCode);

		List<UserPort> list = findByPortCode(portCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user ports before and after the current user port in the ordered
	 * set where portCode = &#63;.
	 *
	 * @param id                the primary key of the current user port
	 * @param portCode          the port code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a user port with the
	 *                                                  primary key could not be
	 *                                                  found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort[] findByPortCode_PrevAndNext(long id, String portCode, OrderByComparator orderByComparator)
			throws NoSuchUserPortException, SystemException {
		UserPort userPort = findByPrimaryKey(id);

		try {

			UserPort[] array = new UserPort[3];

			array[0] = getByPortCode_PrevAndNext(userPort, portCode, orderByComparator, true);

			array[1] = userPort;

			array[2] = getByPortCode_PrevAndNext(userPort, portCode, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected UserPort getByPortCode_PrevAndNext(UserPort userPort, String portCode,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERPORT_WHERE);

		if (portCode == null) {
			query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
		} else {
			if (portCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
			} else {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
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
			query.append(UserPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		if (portCode != null) {
			builder.appendNamedParameterMap("portCode", portCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userPort);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<UserPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns the user port where userId = &#63; or throws a
	 * {@link vn.gt.dao.common.NoSuchUserPortException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a matching user port
	 *                                                  could not be found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByUserId(long userId) throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByUserId(userId);

		if (userPort == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserPortException(msg.toString());
		}

		return userPort;
	}

	/**
	 * Returns the user port where userId = &#63; or returns <code>null</code> if it
	 * could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user port, or <code>null</code> if a matching user port
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByUserId(long userId) throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user port where userId = &#63; or returns <code>null</code> if it
	 * could not be found, optionally using the finder cache.
	 *
	 * @param userId            the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user port, or <code>null</code> if a matching user port
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByUserId(long userId, boolean retrieveFromCache) throws SystemException {
		UserPort userPort = null;
		if (userPort == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USERPORT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			query.append(UserPortModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(UserPort.class).build();

				builder.appendNamedParameterMap("userId", userId);

				userPort = (UserPort) queryFactory.getSingleResult(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return userPort;
	}

	/**
	 * Returns all the user ports where departmentCode = &#63;.
	 *
	 * @param departmentCode the department code
	 * @return the matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByF_departmentCode(String departmentCode) throws SystemException {
		return findByF_departmentCode(departmentCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user ports where departmentCode = &#63;.
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
	 * @param departmentCode the department code
	 * @param start          the lower bound of the range of user ports
	 * @param end            the upper bound of the range of user ports (not
	 *                       inclusive)
	 * @return the range of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByF_departmentCode(String departmentCode, int start, int end) throws SystemException {
		return findByF_departmentCode(departmentCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user ports where departmentCode = &#63;.
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
	 * @param departmentCode    the department code
	 * @param start             the lower bound of the range of user ports
	 * @param end               the upper bound of the range of user ports (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findByF_departmentCode(String departmentCode, int start, int end,
			OrderByComparator orderByComparator) throws SystemException {
		List<UserPort> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USERPORT_WHERE);

			if (departmentCode == null) {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1);
			} else {
				if (departmentCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			else {
				query.append(UserPortModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(UserPort.class).build();

				if (departmentCode != null) {
					builder.appendNamedParameterMap("departmentCode", departmentCode);
				}

				list = (List<UserPort>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user port in the ordered set where departmentCode = &#63;.
	 *
	 * @param departmentCode    the department code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a matching user port
	 *                                                  could not be found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByF_departmentCode_First(String departmentCode, OrderByComparator orderByComparator)
			throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByF_departmentCode_First(departmentCode, orderByComparator);

		if (userPort != null) {
			return userPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("departmentCode=");
		msg.append(departmentCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserPortException(msg.toString());
	}

	/**
	 * Returns the first user port in the ordered set where departmentCode = &#63;.
	 *
	 * @param departmentCode    the department code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user port, or <code>null</code> if a matching user
	 *         port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByF_departmentCode_First(String departmentCode, OrderByComparator orderByComparator)
			throws SystemException {
		List<UserPort> list = findByF_departmentCode(departmentCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user port in the ordered set where departmentCode = &#63;.
	 *
	 * @param departmentCode    the department code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a matching user port
	 *                                                  could not be found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort findByF_departmentCode_Last(String departmentCode, OrderByComparator orderByComparator)
			throws NoSuchUserPortException, SystemException {
		UserPort userPort = fetchByF_departmentCode_Last(departmentCode, orderByComparator);

		if (userPort != null) {
			return userPort;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("departmentCode=");
		msg.append(departmentCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserPortException(msg.toString());
	}

	/**
	 * Returns the last user port in the ordered set where departmentCode = &#63;.
	 *
	 * @param departmentCode    the department code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user port, or <code>null</code> if a matching user
	 *         port could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort fetchByF_departmentCode_Last(String departmentCode, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByF_departmentCode(departmentCode);

		List<UserPort> list = findByF_departmentCode(departmentCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user ports before and after the current user port in the ordered
	 * set where departmentCode = &#63;.
	 *
	 * @param id                the primary key of the current user port
	 * @param departmentCode    the department code
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user port
	 * @throws vn.gt.dao.common.NoSuchUserPortException if a user port with the
	 *                                                  primary key could not be
	 *                                                  found
	 * @throws SystemException                          if a system exception
	 *                                                  occurred
	 */
	public UserPort[] findByF_departmentCode_PrevAndNext(long id, String departmentCode,
			OrderByComparator orderByComparator) throws NoSuchUserPortException, SystemException {
		UserPort userPort = findByPrimaryKey(id);

		try {

			UserPort[] array = new UserPort[3];

			array[0] = getByF_departmentCode_PrevAndNext(userPort, departmentCode, orderByComparator, true);

			array[1] = userPort;

			array[2] = getByF_departmentCode_PrevAndNext(userPort, departmentCode, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected UserPort getByF_departmentCode_PrevAndNext(UserPort userPort, String departmentCode,
			OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERPORT_WHERE);

		if (departmentCode == null) {
			query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1);
		} else {
			if (departmentCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3);
			} else {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2);
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
			query.append(UserPortModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.build();

		if (departmentCode != null) {
			builder.appendNamedParameterMap("departmentCode", departmentCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userPort);

			/*
			 * for (Object value : values) { builder.appendNamedParameterMap("value",
			 * value); }
			 */
		}

		List<UserPort> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the user ports.
	 *
	 * @return the user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user ports.
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
	 * @param start the lower bound of the range of user ports
	 * @param end   the upper bound of the range of user ports (not inclusive)
	 * @return the range of user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user ports.
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
	 * @param start             the lower bound of the range of user ports
	 * @param end               the upper bound of the range of user ports (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of user ports
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserPort> findAll(int start, int end, OrderByComparator orderByComparator) throws SystemException {
		List<UserPort> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERPORT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_USERPORT.concat(UserPortModelImpl.ORDER_BY_JPQL);
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(UserPort.class).build();

				list = (List<UserPort>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes all the user ports where portCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortCode(String portCode) throws SystemException {
		for (UserPort userPort : findByPortCode(portCode)) {
			repository.delete(userPort);
		}
	}

	/**
	 * Removes the user port where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user port that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public UserPort removeByUserId(long userId) throws NoSuchUserPortException, SystemException {
		UserPort userPort = findByUserId(userId);

		repository.delete(userPort);
		return userPort;
	}

	/**
	 * Removes all the user ports where departmentCode = &#63; from the database.
	 *
	 * @param departmentCode the department code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_departmentCode(String departmentCode) throws SystemException {
		for (UserPort userPort : findByF_departmentCode(departmentCode)) {
			repository.delete(userPort);
		}
	}

	/**
	 * Removes all the user ports from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserPort userPort : findAll()) {
			repository.delete(userPort);
		}
	}

	/**
	 * Returns the number of user ports where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the number of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCode(String portCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERPORT_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			} else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
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
	 * Returns the number of user ports where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERPORT_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				builder.appendNamedParameterMap("userId", userId);

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
	 * Returns the number of user ports where departmentCode = &#63;.
	 *
	 * @param departmentCode the department code
	 * @return the number of matching user ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_departmentCode(String departmentCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERPORT_WHERE);

			if (departmentCode == null) {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1);
			} else {
				if (departmentCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3);
				} else {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				if (departmentCode != null) {
					builder.appendNamedParameterMap("departmentCode", departmentCode);
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
	 * Returns the number of user ports.
	 *
	 * @return the number of user ports
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_USERPORT).build();

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
	 * Initializes the user port persistence.
	 */
	private static final String _SQL_SELECT_USERPORT = "SELECT userPort FROM UserPort userPort";
	private static final String _SQL_SELECT_USERPORT_WHERE = "SELECT userPort FROM UserPort userPort WHERE ";
	private static final String _SQL_COUNT_USERPORT = "SELECT COUNT(userPort) FROM UserPort userPort";
	private static final String _SQL_COUNT_USERPORT_WHERE = "SELECT COUNT(userPort) FROM UserPort userPort WHERE ";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_1 = "userPort.portCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_2 = "userPort.portCode =:portCode";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_3 = "(userPort.portCode IS NULL OR userPort.portCode =:portCode)";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userPort.userId =:userId";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1 = "userPort.departmentCode IS NULL";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2 = "userPort.departmentCode =:departmentCode";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3 = "(userPort.departmentCode IS NULL OR userPort.departmentCode =:departmentCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userPort.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserPort exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserPort exists with the key {";

}
