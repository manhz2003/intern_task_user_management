/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.fds.nsw.liferay.service.persistence;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
import com.fds.nsw.liferay.model.Group;
import com.fds.nsw.liferay.model.Organization;
import com.fds.nsw.liferay.model.Role;
import com.fds.nsw.liferay.model.User;
import com.fds.nsw.liferay.repository.UserRepository;
import com.fds.nsw.liferay.service.exception.NoSuchUserException;

import lombok.extern.slf4j.Slf4j;

/**
 * The persistence implementation for the user service.
 *
 * <p>
 * Caching information and settings can be found in
 * <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see UserPersistence
 * @see UserUtil
 * @generated
 */
@Service
@Slf4j
public class UserPersistenceImpl extends BasePersistence {

	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<User> queryFactory;

	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<Group> queryFactory2;

	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<Organization> queryFactory3;

	@Autowired
	@Qualifier("lrQueryFactory")
	QueryFactory<Role> queryFactory4;

	@Autowired
	UserRepository repository;

	public User create(long userId) {
		User user = new User();

		String uuid = UUID.randomUUID().toString();

		user.setUuid(uuid);

		return user;
	}

	/**
	 * Removes the user with the primary key from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param userId the primary key of the user
	 * @return the user that was removed
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User remove(long userId) throws NoSuchUserException, SystemException {
		return remove(Long.valueOf(userId));
	}

	/**
	 * Removes the user with the primary key from the database. Also notifies the
	 * appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user
	 * @return the user that was removed
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */

	public User remove(Serializable primaryKey) throws NoSuchUserException, SystemException {

		Long id = ((Long) primaryKey).longValue();

		User user = null;

		try {

			Optional<User> optional = repository.findById(id);

			if (optional.isPresent()) {
				user = optional.get();
			}

			if (user == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			repository.delete(user);

			return user;
		} catch (NoSuchUserException nsee) {
			throw nsee;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected User removeImpl(User user) throws SystemException {
		try {
			repository.delete(user);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return user;
	}

	public User updateImpl(User user, boolean merge) throws SystemException {
		try {
			repository.saveAndFlush(user);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return user;
	}

	/**
	 * Returns the user with the primary key or throws a
	 * {@link com.liferay.portal.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the user
	 * @return the user
	 * @throws com.liferay.portal.NoSuchModelException if a user with the primary
	 *                                                 key could not be found
	 * @throws SystemException                         if a system exception
	 *                                                 occurred
	 */

	public User findByPrimaryKey(Serializable primaryKey) throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the user with the primary key or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param userId the primary key of the user
	 * @return the user
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByPrimaryKey(long userId) throws NoSuchUserException, SystemException {
		User user = fetchByPrimaryKey(userId);

		if (user == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
			}

			throw new NoSuchUserException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + userId);
		}

		return user;
	}

	/**
	 * Returns the user with the primary key or returns <code>null</code> if it
	 * could not be found.
	 *
	 * @param primaryKey the primary key of the user
	 * @return the user, or <code>null</code> if a user with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */

	public User fetchByPrimaryKey(Serializable primaryKey) throws SystemException {
		return fetchByPrimaryKey(((Long) primaryKey).longValue());
	}

	/**
	 * Returns the user with the primary key or returns <code>null</code> if it
	 * could not be found.
	 *
	 * @param userId the primary key of the user
	 * @return the user, or <code>null</code> if a user with the primary key could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByPrimaryKey(long userId) throws SystemException {
		User user = null;

		if (user == null) {

			boolean hasException = false;

			try {
				Optional<User> optional = repository.findById(userId);

				if (optional.isPresent()) {
					user = optional.get();
				}
			} catch (Exception e) {
				hasException = true;

				throw processException(e);
			} finally {

			}
		}

		return user;
	}

	/**
	 * Returns all the users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByUuid(String uuid) throws SystemException {
		return findByUuid(uuid, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the users where uuid = &#63;.
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
	 * @param uuid  the uuid
	 * @param start the lower bound of the range of users
	 * @param end   the upper bound of the range of users (not inclusive)
	 * @return the range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByUuid(String uuid, int start, int end) throws SystemException {
		return findByUuid(uuid, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users where uuid = &#63;.
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
	 * @param uuid              the uuid
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByUuid(String uuid, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<User> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_USER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			} else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				} else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(User.class).build();

				if (uuid != null) {

					builder.appendNamedParameterMap("uuid", uuid);
				}

				list = (List<User>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid              the uuid
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByUuid_First(String uuid, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByUuid_First(uuid, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid              the uuid
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user, or <code>null</code> if a matching user
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByUuid_First(String uuid, OrderByComparator orderByComparator) throws SystemException {
		List<User> list = findByUuid(uuid, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid              the uuid
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByUuid_Last(String uuid, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByUuid_Last(uuid, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("uuid=");
		msg.append(uuid);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last user in the ordered set where uuid = &#63;.
	 *
	 * @param uuid              the uuid
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user, or <code>null</code> if a matching user could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByUuid_Last(String uuid, OrderByComparator orderByComparator) throws SystemException {
		int count = countByUuid(uuid);

		List<User> list = findByUuid(uuid, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the users before and after the current user in the ordered set where
	 * uuid = &#63;.
	 *
	 * @param userId            the primary key of the current user
	 * @param uuid              the uuid
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User[] findByUuid_PrevAndNext(long userId, String uuid, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = findByPrimaryKey(userId);

		try {

			User[] array = new User[3];

			array[0] = getByUuid_PrevAndNext(user, uuid, orderByComparator, true);

			array[1] = user;

			array[2] = getByUuid_PrevAndNext(user, uuid, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected User getByUuid_PrevAndNext(User user, String uuid, OrderByComparator orderByComparator,
			boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USER_WHERE);

		if (uuid == null) {
			query.append(_FINDER_COLUMN_UUID_UUID_1);
		} else {
			if (uuid.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_UUID_UUID_3);
			} else {
				query.append(_FINDER_COLUMN_UUID_UUID_2);
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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(User.class).build();

		if (uuid != null) {
			builder.appendNamedParameterMap("uuid", uuid);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(user);

			for (Object value : values) {
				// builder.appendNamedParameterMap("XXXXX", value);
			}
		}

		List<User> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the users where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByCompanyId(long companyId) throws SystemException {
		return findByCompanyId(companyId, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the users where companyId = &#63;.
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
	 * @param companyId the company ID
	 * @param start     the lower bound of the range of users
	 * @param end       the upper bound of the range of users (not inclusive)
	 * @return the range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByCompanyId(long companyId, int start, int end) throws SystemException {
		return findByCompanyId(companyId, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users where companyId = &#63;.
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
	 * @param companyId         the company ID
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByCompanyId(long companyId, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<User> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(User.class).build();

				builder.appendNamedParameterMap("companyId", companyId);

				list = (List<User>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user in the ordered set where companyId = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByCompanyId_First(long companyId, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByCompanyId_First(companyId, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first user in the ordered set where companyId = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user, or <code>null</code> if a matching user
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByCompanyId_First(long companyId, OrderByComparator orderByComparator) throws SystemException {
		List<User> list = findByCompanyId(companyId, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user in the ordered set where companyId = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByCompanyId_Last(long companyId, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByCompanyId_Last(companyId, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last user in the ordered set where companyId = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user, or <code>null</code> if a matching user could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByCompanyId_Last(long companyId, OrderByComparator orderByComparator) throws SystemException {
		int count = countByCompanyId(companyId);

		List<User> list = findByCompanyId(companyId, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the users before and after the current user in the ordered set where
	 * companyId = &#63;.
	 *
	 * @param userId            the primary key of the current user
	 * @param companyId         the company ID
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User[] findByCompanyId_PrevAndNext(long userId, long companyId, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = findByPrimaryKey(userId);

		try {

			User[] array = new User[3];

			array[0] = getByCompanyId_PrevAndNext(user, companyId, orderByComparator, true);

			array[1] = user;

			array[2] = getByCompanyId_PrevAndNext(user, companyId, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected User getByCompanyId_PrevAndNext(User user, long companyId, OrderByComparator orderByComparator,
			boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USER_WHERE);

		query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(User.class).build();

		builder.appendNamedParameterMap("companyId", companyId);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(user);

			for (Object value : values) {
				// builder.appendNamedParameterMap("XXXXX", value);
			}
		}

		List<User> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns the user where contactId = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param contactId the contact ID
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByContactId(long contactId) throws NoSuchUserException, SystemException {
		User user = fetchByContactId(contactId);

		if (user == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("contactId=");
			msg.append(contactId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where contactId = &#63; or returns <code>null</code> if it
	 * could not be found. Uses the finder cache.
	 *
	 * @param contactId the contact ID
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByContactId(long contactId) throws SystemException {
		return fetchByContactId(contactId, true);
	}

	/**
	 * Returns the user where contactId = &#63; or returns <code>null</code> if it
	 * could not be found, optionally using the finder cache.
	 *
	 * @param contactId         the contact ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByContactId(long contactId, boolean retrieveFromCache) throws SystemException {
		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("contactId", contactId);

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return user;
	}

	/**
	 * Returns all the users where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByEmailAddress(String emailAddress) throws SystemException {
		return findByEmailAddress(emailAddress, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the users where emailAddress = &#63;.
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
	 * @param emailAddress the email address
	 * @param start        the lower bound of the range of users
	 * @param end          the upper bound of the range of users (not inclusive)
	 * @return the range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByEmailAddress(String emailAddress, int start, int end) throws SystemException {
		return findByEmailAddress(emailAddress, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users where emailAddress = &#63;.
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
	 * @param emailAddress      the email address
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByEmailAddress(String emailAddress, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<User> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_USER_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			} else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
				} else {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(User.class).build();

				if (emailAddress != null) {
					builder.appendNamedParameterMap("User", emailAddress);
				}

				list = (List<User>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress      the email address
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByEmailAddress_First(String emailAddress, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByEmailAddress_First(emailAddress, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailAddress=");
		msg.append(emailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first user in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress      the email address
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user, or <code>null</code> if a matching user
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByEmailAddress_First(String emailAddress, OrderByComparator orderByComparator)
			throws SystemException {
		List<User> list = findByEmailAddress(emailAddress, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress      the email address
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByEmailAddress_Last(String emailAddress, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByEmailAddress_Last(emailAddress, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("emailAddress=");
		msg.append(emailAddress);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last user in the ordered set where emailAddress = &#63;.
	 *
	 * @param emailAddress      the email address
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user, or <code>null</code> if a matching user could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByEmailAddress_Last(String emailAddress, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByEmailAddress(emailAddress);

		List<User> list = findByEmailAddress(emailAddress, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the users before and after the current user in the ordered set where
	 * emailAddress = &#63;.
	 *
	 * @param userId            the primary key of the current user
	 * @param emailAddress      the email address
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User[] findByEmailAddress_PrevAndNext(long userId, String emailAddress, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = findByPrimaryKey(userId);

		try {

			User[] array = new User[3];

			array[0] = getByEmailAddress_PrevAndNext(user, emailAddress, orderByComparator, true);

			array[1] = user;

			array[2] = getByEmailAddress_PrevAndNext(user, emailAddress, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected User getByEmailAddress_PrevAndNext(User user, String emailAddress, OrderByComparator orderByComparator,
			boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USER_WHERE);

		if (emailAddress == null) {
			query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
		} else {
			if (emailAddress.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
			} else {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(User.class).build();

		if (emailAddress != null) {
			builder.appendNamedParameterMap("emailAddress", emailAddress);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(user);

			for (Object value : values) {
				// builder.appendNamedParameterMap("XXXXX", value);
			}
		}

		List<User> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns the user where portraitId = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param portraitId the portrait ID
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByPortraitId(long portraitId) throws NoSuchUserException, SystemException {
		User user = fetchByPortraitId(portraitId);

		if (user == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("portraitId=");
			msg.append(portraitId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where portraitId = &#63; or returns <code>null</code> if it
	 * could not be found. Uses the finder cache.
	 *
	 * @param portraitId the portrait ID
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByPortraitId(long portraitId) throws SystemException {
		return fetchByPortraitId(portraitId, true);
	}

	/**
	 * Returns the user where portraitId = &#63; or returns <code>null</code> if it
	 * could not be found, optionally using the finder cache.
	 *
	 * @param portraitId        the portrait ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByPortraitId(long portraitId, boolean retrieveFromCache) throws SystemException {

		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_PORTRAITID_PORTRAITID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("portraitId", portraitId);

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and userId = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param userId    the user ID
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_U(long companyId, long userId) throws NoSuchUserException, SystemException {
		User user = fetchByC_U(companyId, userId);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and userId = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param userId    the user ID
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_U(long companyId, long userId) throws SystemException {
		return fetchByC_U(companyId, userId, true);
	}

	/**
	 * Returns the user where companyId = &#63; and userId = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param userId            the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_U(long companyId, long userId, boolean retrieveFromCache) throws SystemException {

		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("userId", userId);

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and defaultUser = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId   the company ID
	 * @param defaultUser the default user
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_DU(long companyId, boolean defaultUser) throws NoSuchUserException, SystemException {
		User user = fetchByC_DU(companyId, defaultUser);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", defaultUser=");
			msg.append(defaultUser);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and defaultUser = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId   the company ID
	 * @param defaultUser the default user
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_DU(long companyId, boolean defaultUser) throws SystemException {
		return fetchByC_DU(companyId, defaultUser, true);
	}

	/**
	 * Returns the user where companyId = &#63; and defaultUser = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param defaultUser       the default user
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_DU(long companyId, boolean defaultUser, boolean retrieveFromCache) throws SystemException {

		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_DU_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_DU_DEFAULTUSER_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("defaultUser", defaultUser);

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and screenName = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId  the company ID
	 * @param screenName the screen name
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_SN(long companyId, String screenName) throws NoSuchUserException, SystemException {
		User user = fetchByC_SN(companyId, screenName);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", screenName=");
			msg.append(screenName);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and screenName = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId  the company ID
	 * @param screenName the screen name
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_SN(long companyId, String screenName) throws SystemException {
		return fetchByC_SN(companyId, screenName, true);
	}

	/**
	 * Returns the user where companyId = &#63; and screenName = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param screenName        the screen name
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_SN(long companyId, String screenName, boolean retrieveFromCache) throws SystemException {

		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_SN_COMPANYID_2);

			if (screenName == null) {
				query.append(_FINDER_COLUMN_C_SN_SCREENNAME_1);
			} else {
				if (screenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_SN_SCREENNAME_3);
				} else {
					query.append(_FINDER_COLUMN_C_SN_SCREENNAME_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (screenName != null) {
					builder.appendNamedParameterMap("screenName", screenName);
				}

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and emailAddress = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId    the company ID
	 * @param emailAddress the email address
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_EA(long companyId, String emailAddress) throws NoSuchUserException, SystemException {
		User user = fetchByC_EA(companyId, emailAddress);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", emailAddress=");
			msg.append(emailAddress);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and emailAddress = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId    the company ID
	 * @param emailAddress the email address
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_EA(long companyId, String emailAddress) throws SystemException {
		return fetchByC_EA(companyId, emailAddress, true);
	}

	/**
	 * Returns the user where companyId = &#63; and emailAddress = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param emailAddress      the email address
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_EA(long companyId, String emailAddress, boolean retrieveFromCache) throws SystemException {
		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_EA_COMPANYID_2);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_1);
			} else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_3);
				} else {
					query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (emailAddress != null) {
					builder.appendNamedParameterMap("emailAddress", emailAddress);
				}

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and facebookId = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId  the company ID
	 * @param facebookId the facebook ID
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_FID(long companyId, long facebookId) throws NoSuchUserException, SystemException {
		User user = fetchByC_FID(companyId, facebookId);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", facebookId=");
			msg.append(facebookId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and facebookId = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId  the company ID
	 * @param facebookId the facebook ID
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_FID(long companyId, long facebookId) throws SystemException {
		return fetchByC_FID(companyId, facebookId, true);
	}

	/**
	 * Returns the user where companyId = &#63; and facebookId = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param facebookId        the facebook ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_FID(long companyId, long facebookId, boolean retrieveFromCache) throws SystemException {

		User user = null;

		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_FID_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_FID_FACEBOOKID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("facebookId", facebookId);

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and openId = &#63; or throws a
	 * {@link com.liferay.portal.NoSuchUserException} if it could not be found.
	 *
	 * @param companyId the company ID
	 * @param openId    the open ID
	 * @return the matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_O(long companyId, String openId) throws NoSuchUserException, SystemException {
		User user = fetchByC_O(companyId, openId);

		if (user == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("companyId=");
			msg.append(companyId);

			msg.append(", openId=");
			msg.append(openId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserException(msg.toString());
		}

		return user;
	}

	/**
	 * Returns the user where companyId = &#63; and openId = &#63; or returns
	 * <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param companyId the company ID
	 * @param openId    the open ID
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_O(long companyId, String openId) throws SystemException {
		return fetchByC_O(companyId, openId, true);
	}

	/**
	 * Returns the user where companyId = &#63; and openId = &#63; or returns
	 * <code>null</code> if it could not be found, optionally using the finder
	 * cache.
	 *
	 * @param companyId         the company ID
	 * @param openId            the open ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user, or <code>null</code> if a matching user could not
	 *         be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_O(long companyId, String openId, boolean retrieveFromCache) throws SystemException {

		User user = null;
		if (user == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_O_COMPANYID_2);

			if (openId == null) {
				query.append(_FINDER_COLUMN_C_O_OPENID_1);
			} else {
				if (openId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_O_OPENID_3);
				} else {
					query.append(_FINDER_COLUMN_C_O_OPENID_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(User.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (openId != null) {
					builder.appendNamedParameterMap("openId", openId);
				}

				user = (User) queryFactory.getSingleResult(builder);

			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}
		return user;
	}

	/**
	 * Returns all the users where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status    the status
	 * @return the matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByC_S(long companyId, int status) throws SystemException {
		return findByC_S(companyId, status, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the users where companyId = &#63; and status = &#63;.
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
	 * @param companyId the company ID
	 * @param status    the status
	 * @param start     the lower bound of the range of users
	 * @param end       the upper bound of the range of users (not inclusive)
	 * @return the range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByC_S(long companyId, int status, int start, int end) throws SystemException {
		return findByC_S(companyId, status, start, end, null);
	}

	/**
	 * Returns an ordered range of all the users where companyId = &#63; and status
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
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findByC_S(long companyId, int status, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<User> list = null;

		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 + (orderByComparator.getOrderByFields().length * 3));
			} else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(User.class).build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("status", status);

				list = (List<User>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the first user in the ordered set where companyId = &#63; and status
	 * = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_S_First(long companyId, int status, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByC_S_First(companyId, status, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the first user in the ordered set where companyId = &#63; and status
	 * = &#63;.
	 *
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the first matching user, or <code>null</code> if a matching user
	 *         could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_S_First(long companyId, int status, OrderByComparator orderByComparator)
			throws SystemException {
		List<User> list = findByC_S(companyId, status, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user in the ordered set where companyId = &#63; and status =
	 * &#63;.
	 *
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user
	 * @throws com.liferay.portal.NoSuchUserException if a matching user could not
	 *                                                be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User findByC_S_Last(long companyId, int status, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = fetchByC_S_Last(companyId, status, orderByComparator);

		if (user != null) {
			return user;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("companyId=");
		msg.append(companyId);

		msg.append(", status=");
		msg.append(status);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserException(msg.toString());
	}

	/**
	 * Returns the last user in the ordered set where companyId = &#63; and status =
	 * &#63;.
	 *
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the last matching user, or <code>null</code> if a matching user could
	 *         not be found
	 * @throws SystemException if a system exception occurred
	 */
	public User fetchByC_S_Last(long companyId, int status, OrderByComparator orderByComparator)
			throws SystemException {
		int count = countByC_S(companyId, status);

		List<User> list = findByC_S(companyId, status, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the users before and after the current user in the ordered set where
	 * companyId = &#63; and status = &#63;.
	 *
	 * @param userId            the primary key of the current user
	 * @param companyId         the company ID
	 * @param status            the status
	 * @param orderByComparator the comparator to order the set by (optionally
	 *                          <code>null</code>)
	 * @return the previous, current, and next user
	 * @throws com.liferay.portal.NoSuchUserException if a user with the primary key
	 *                                                could not be found
	 * @throws SystemException                        if a system exception occurred
	 */
	public User[] findByC_S_PrevAndNext(long userId, long companyId, int status, OrderByComparator orderByComparator)
			throws NoSuchUserException, SystemException {
		User user = findByPrimaryKey(userId);

		try {

			User[] array = new User[3];

			array[0] = getByC_S_PrevAndNext(user, companyId, status, orderByComparator, true);

			array[1] = user;

			array[2] = getByC_S_PrevAndNext(user, companyId, status, orderByComparator, false);

			return array;
		} catch (Exception e) {
			throw processException(e);
		} finally {

		}
	}

	protected User getByC_S_PrevAndNext(User user, long companyId, int status, OrderByComparator orderByComparator,
			boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 + (orderByComparator.getOrderByFields().length * 6));
		} else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USER_WHERE);

		query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

		query.append(_FINDER_COLUMN_C_S_STATUS_2);

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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2)
				.entityClass(User.class).build();

		builder.appendNamedParameterMap("companyId", companyId);

		builder.appendNamedParameterMap("status", status);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(user);

			for (Object value : values) {
				// builder.appendNamedParameterMap("XXXXX", value);
			}
		}

		List<User> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		} else {
			return null;
		}
	}

	/**
	 * Returns all the users.
	 *
	 * @return the users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the users.
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
	 * @param start the lower bound of the range of users
	 * @param end   the upper bound of the range of users (not inclusive)
	 * @return the range of users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the users.
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
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of users
	 * @throws SystemException if a system exception occurred
	 */
	public List<User> findAll(int start, int end, OrderByComparator orderByComparator) throws SystemException {

		List<User> list = null;

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 + (orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			} else {
				sql = _SQL_SELECT_USER;
			}

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start)
						.maxResults(end - start).entityClass(User.class).build();

				list = (List<User>) queryFactory.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Removes all the users where uuid = &#63; from the database.
	 *
	 * @param uuid the uuid
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByUuid(String uuid) throws SystemException {
		for (User user : findByUuid(uuid)) {
			repository.delete(user);
		}
	}

	/**
	 * Removes all the users where companyId = &#63; from the database.
	 *
	 * @param companyId the company ID
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByCompanyId(long companyId) throws SystemException {
		for (User user : findByCompanyId(companyId)) {
			repository.delete(user);
		}
	}

	/**
	 * Removes the user where contactId = &#63; from the database.
	 *
	 * @param contactId the contact ID
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByContactId(long contactId) throws NoSuchUserException, SystemException {
		User user = findByContactId(contactId);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes all the users where emailAddress = &#63; from the database.
	 *
	 * @param emailAddress the email address
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEmailAddress(String emailAddress) throws SystemException {
		for (User user : findByEmailAddress(emailAddress)) {
			repository.delete(user);
		}
	}

	/**
	 * Removes the user where portraitId = &#63; from the database.
	 *
	 * @param portraitId the portrait ID
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByPortraitId(long portraitId) throws NoSuchUserException, SystemException {
		User user = findByPortraitId(portraitId);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and userId = &#63; from the
	 * database.
	 *
	 * @param companyId the company ID
	 * @param userId    the user ID
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_U(long companyId, long userId) throws NoSuchUserException, SystemException {
		User user = findByC_U(companyId, userId);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and defaultUser = &#63; from the
	 * database.
	 *
	 * @param companyId   the company ID
	 * @param defaultUser the default user
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_DU(long companyId, boolean defaultUser) throws NoSuchUserException, SystemException {
		User user = findByC_DU(companyId, defaultUser);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and screenName = &#63; from the
	 * database.
	 *
	 * @param companyId  the company ID
	 * @param screenName the screen name
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_SN(long companyId, String screenName) throws NoSuchUserException, SystemException {
		User user = findByC_SN(companyId, screenName);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and emailAddress = &#63; from the
	 * database.
	 *
	 * @param companyId    the company ID
	 * @param emailAddress the email address
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_EA(long companyId, String emailAddress) throws NoSuchUserException, SystemException {
		User user = findByC_EA(companyId, emailAddress);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and facebookId = &#63; from the
	 * database.
	 *
	 * @param companyId  the company ID
	 * @param facebookId the facebook ID
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_FID(long companyId, long facebookId) throws NoSuchUserException, SystemException {
		User user = findByC_FID(companyId, facebookId);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes the user where companyId = &#63; and openId = &#63; from the
	 * database.
	 *
	 * @param companyId the company ID
	 * @param openId    the open ID
	 * @return the user that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public User removeByC_O(long companyId, String openId) throws NoSuchUserException, SystemException {
		User user = findByC_O(companyId, openId);

		repository.delete(user);
		return user;
	}

	/**
	 * Removes all the users where companyId = &#63; and status = &#63; from the
	 * database.
	 *
	 * @param companyId the company ID
	 * @param status    the status
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByC_S(long companyId, int status) throws SystemException {
		for (User user : findByC_S(companyId, status)) {
			repository.delete(user);
		}
	}

	/**
	 * Removes all the users from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (User user : findAll()) {
			repository.delete(user);
		}
	}

	/**
	 * Returns the number of users where uuid = &#63;.
	 *
	 * @param uuid the uuid
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUuid(String uuid) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USER_WHERE);

			if (uuid == null) {
				query.append(_FINDER_COLUMN_UUID_UUID_1);
			} else {
				if (uuid.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_UUID_UUID_3);
				} else {
					query.append(_FINDER_COLUMN_UUID_UUID_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				if (uuid != null) {
					builder.appendNamedParameterMap("uuid", uuid);
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
	 * Returns the number of users where companyId = &#63;.
	 *
	 * @param companyId the company ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByCompanyId(long companyId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_COMPANYID_COMPANYID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

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
	 * Returns the number of users where contactId = &#63;.
	 *
	 * @param contactId the contact ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByContactId(long contactId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_CONTACTID_CONTACTID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("contactId", contactId);

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
	 * Returns the number of users where emailAddress = &#63;.
	 *
	 * @param emailAddress the email address
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEmailAddress(String emailAddress) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USER_WHERE);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1);
			} else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3);
				} else {
					query.append(_FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				if (emailAddress != null) {
					builder.appendNamedParameterMap("emailAddress", emailAddress);
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
	 * Returns the number of users where portraitId = &#63;.
	 *
	 * @param portraitId the portrait ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortraitId(long portraitId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_PORTRAITID_PORTRAITID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("portraitId", portraitId);

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
	 * Returns the number of users where companyId = &#63; and userId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param userId    the user ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_U(long companyId, long userId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_U_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_U_USERID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

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
	 * Returns the number of users where companyId = &#63; and defaultUser = &#63;.
	 *
	 * @param companyId   the company ID
	 * @param defaultUser the default user
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_DU(long companyId, boolean defaultUser) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_DU_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_DU_DEFAULTUSER_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("defaultUser", defaultUser);

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
	 * Returns the number of users where companyId = &#63; and screenName = &#63;.
	 *
	 * @param companyId  the company ID
	 * @param screenName the screen name
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_SN(long companyId, String screenName) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_SN_COMPANYID_2);

			if (screenName == null) {
				query.append(_FINDER_COLUMN_C_SN_SCREENNAME_1);
			} else {
				if (screenName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_SN_SCREENNAME_3);
				} else {
					query.append(_FINDER_COLUMN_C_SN_SCREENNAME_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (screenName != null) {
					builder.appendNamedParameterMap("screenName", screenName);
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
	 * Returns the number of users where companyId = &#63; and emailAddress = &#63;.
	 *
	 * @param companyId    the company ID
	 * @param emailAddress the email address
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_EA(long companyId, String emailAddress) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_EA_COMPANYID_2);

			if (emailAddress == null) {
				query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_1);
			} else {
				if (emailAddress.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_3);
				} else {
					query.append(_FINDER_COLUMN_C_EA_EMAILADDRESS_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (emailAddress != null) {
					builder.appendNamedParameterMap("emailAddress", emailAddress);
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
	 * Returns the number of users where companyId = &#63; and facebookId = &#63;.
	 *
	 * @param companyId  the company ID
	 * @param facebookId the facebook ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_FID(long companyId, long facebookId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_FID_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_FID_FACEBOOKID_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("facebookId", facebookId);

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
	 * Returns the number of users where companyId = &#63; and openId = &#63;.
	 *
	 * @param companyId the company ID
	 * @param openId    the open ID
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_O(long companyId, String openId) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_O_COMPANYID_2);

			if (openId == null) {
				query.append(_FINDER_COLUMN_C_O_OPENID_1);
			} else {
				if (openId.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_C_O_OPENID_3);
				} else {
					query.append(_FINDER_COLUMN_C_O_OPENID_2);
				}
			}

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				if (openId != null) {
					builder.appendNamedParameterMap("openId", openId);
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
	 * Returns the number of users where companyId = &#63; and status = &#63;.
	 *
	 * @param companyId the company ID
	 * @param status    the status
	 * @return the number of matching users
	 * @throws SystemException if a system exception occurred
	 */
	public int countByC_S(long companyId, int status) throws SystemException {

		Long count = null;

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_USER_WHERE);

			query.append(_FINDER_COLUMN_C_S_COMPANYID_2);

			query.append(_FINDER_COLUMN_C_S_STATUS_2);

			String sql = query.toString();

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class)
						.build();

				builder.appendNamedParameterMap("companyId", companyId);

				builder.appendNamedParameterMap("status", status);

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
	 * Returns the number of users.
	 *
	 * @return the number of users
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {

			try {
				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_USER)
						.entityClass(Long.class).build();

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
	 * Returns all the groups associated with the user.
	 *
	 * @param pk the primary key of the user
	 * @return the groups associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getGroups(long pk) throws SystemException {
		return getGroups(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the groups associated with the user.
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
	 * @param pk    the primary key of the user
	 * @param start the lower bound of the range of users
	 * @param end   the upper bound of the range of users (not inclusive)
	 * @return the range of groups associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getGroups(long pk, int start, int end) throws SystemException {
		return getGroups(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the groups associated with the user.
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
	 * @param pk                the primary key of the user
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of groups associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Group> getGroups(long pk, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<Group> list = null;

		if (list == null) {

			try {

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETGROUPS.concat(ORDER_BY_CLAUSE).concat(orderByComparator.getOrderBy());
				} else {
					sql = _SQL_GETGROUPS.concat(GroupPersistenceImpl.ORDER_BY_SQL);
				}

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Group.class)
						.build();

				builder.appendNamedParameterMap("userId", pk);

				list = queryFactory2.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the number of groups associated with the user.
	 *
	 * @param pk the primary key of the user
	 * @return the number of groups associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public int getGroupsSize(long pk) throws SystemException {

		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_GETGROUPSSIZE)
						.entityClass(Long.class).build();

				builder.appendNamedParameterMap("userId", pk);

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
	 * Returns <code>true</code> if the user has any groups associated with it.
	 *
	 * @param pk the primary key of the user to check for associations with groups
	 * @return <code>true</code> if the user has any groups associated with it;
	 *         <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsGroups(long pk) throws SystemException {
		if (getGroupsSize(pk) > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns all the organizations associated with the user.
	 *
	 * @param pk the primary key of the user
	 * @return the organizations associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getOrganizations(long pk) throws SystemException {
		return getOrganizations(pk, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
	}

	/**
	 * Returns a range of all the organizations associated with the user.
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
	 * @param pk    the primary key of the user
	 * @param start the lower bound of the range of users
	 * @param end   the upper bound of the range of users (not inclusive)
	 * @return the range of organizations associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getOrganizations(long pk, int start, int end) throws SystemException {
		return getOrganizations(pk, start, end, null);
	}

	/**
	 * Returns an ordered range of all the organizations associated with the user.
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
	 * @param pk                the primary key of the user
	 * @param start             the lower bound of the range of users
	 * @param end               the upper bound of the range of users (not
	 *                          inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally
	 *                          <code>null</code>)
	 * @return the ordered range of organizations associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public List<Organization> getOrganizations(long pk, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<Organization> list = null;

		if (list == null) {

			try {

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETORGANIZATIONS.concat(ORDER_BY_CLAUSE).concat(orderByComparator.getOrderBy());
				} else {
					sql = _SQL_GETORGANIZATIONS.concat(OrganizationPersistenceImpl.ORDER_BY_SQL);
				}

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql)
						.entityClass(Organization.class).build();

				builder.appendNamedParameterMap("userId", pk);

				list = (List<Organization>) queryFactory3.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	/**
	 * Returns the number of organizations associated with the user.
	 *
	 * @param pk the primary key of the user
	 * @return the number of organizations associated with the user
	 * @throws SystemException if a system exception occurred
	 */
	public int getOrganizationsSize(long pk) throws SystemException {

		Long count = null;

		if (count == null) {

			try {

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_GETORGANIZATIONSSIZE)
						.entityClass(Long.class).build();

				builder.appendNamedParameterMap("userId", pk);

				count = (Long) queryFactory3.getSingleResult(builder);
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
	 * Returns <code>true</code> if the user has any organizations associated with
	 * it.
	 *
	 * @param pk the primary key of the user to check for associations with
	 *           organizations
	 * @return <code>true</code> if the user has any organizations associated with
	 *         it; <code>false</code> otherwise
	 * @throws SystemException if a system exception occurred
	 */
	public boolean containsOrganizations(long pk) throws SystemException {
		if (getOrganizationsSize(pk) > 0) {
			return true;
		} else {
			return false;
		}
	}

	public List<Role> getRoles(long pk, int start, int end, OrderByComparator orderByComparator)
			throws SystemException {

		List<Role> list = null;

		if (list == null) {

			try {

				String sql = null;

				if (orderByComparator != null) {
					sql = _SQL_GETROLES.concat(ORDER_BY_CLAUSE).concat(orderByComparator.getOrderBy());
				} else {
					sql = _SQL_GETROLES.concat(RolePersistenceImpl.ORDER_BY_SQL);
				}

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Role.class)
						.build();

				builder.appendNamedParameterMap("userId", pk);

				list = queryFactory4.getResultList(builder);
			} catch (Exception e) {
				throw processException(e);
			} finally {

			}
		}

		return list;
	}

	private static final String _SQL_SELECT_USER = "SELECT user FROM User user";
	private static final String _SQL_SELECT_USER_WHERE = "SELECT user FROM User user WHERE ";
	private static final String _SQL_COUNT_USER = "SELECT COUNT(user) FROM User user";
	private static final String _SQL_COUNT_USER_WHERE = "SELECT COUNT(user) FROM User user WHERE ";
	private static final String _SQL_GETGROUPS = "SELECT Group_.* FROM Group_ INNER JOIN Users_Groups ON (Users_Groups.groupId = Group_.groupId) WHERE (Users_Groups.userId = :userId)";
	private static final String _SQL_GETGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Groups WHERE userId = :userId";
	private static final String _SQL_CONTAINSGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Groups WHERE userId = :userId AND groupId = :groupId";
	private static final String _SQL_GETORGANIZATIONS = "SELECT o FROM Organization o INNER JOIN UsersOrg uo ON (uo.organization = o) WHERE (uo.user.userId =:userId)";
	private static final String _SQL_GETORGANIZATIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM UsersOrg WHERE userId = :userId";
	private static final String _SQL_CONTAINSORGANIZATION = "SELECT COUNT(*) AS COUNT_VALUE FROM UsersOrg WHERE userId = :userId AND organizationId = :organizationId";
	private static final String _SQL_GETPERMISSIONS = "SELECT Permission_.* FROM Permission_ INNER JOIN Users_Permissions ON (Users_Permissions.permissionId = Permission_.permissionId) WHERE (Users_Permissions.userId :userId)";
	private static final String _SQL_GETPERMISSIONSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Permissions WHERE userId = :userId";
	private static final String _SQL_CONTAINSPERMISSION = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Permissions WHERE userId = :userId AND permissionId = :permissionId";
	private static final String _SQL_GETROLES = "SELECT role FROM Role role INNER JOIN UsersRole ur ON (ur.role = role) WHERE (ur.user.userId = :userId)";
	private static final String _SQL_GETROLESSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM UsersRole WHERE userId = :userId";
	private static final String _SQL_CONTAINSROLE = "SELECT COUNT(*) AS COUNT_VALUE FROM UsersRole WHERE userId = :userId AND roleId = :roleId";
	private static final String _SQL_GETTEAMS = "SELECT Team.* FROM Team INNER JOIN Users_Teams ON (Users_Teams.teamId = Team.teamId) WHERE (Users_Teams.userId = :userId)";
	private static final String _SQL_GETTEAMSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Teams WHERE userId = :userId";
	private static final String _SQL_CONTAINSTEAM = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_Teams WHERE userId = :userId AND teamId = :teamId";
	private static final String _SQL_GETUSERGROUPS = "SELECT UserGroup.* FROM UserGroup INNER JOIN Users_UserGroups ON (Users_UserGroups.userGroupId = UserGroup.userGroupId) WHERE (Users_UserGroups.userId = :userId)";
	private static final String _SQL_GETUSERGROUPSSIZE = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_UserGroups WHERE userId = :userId";
	private static final String _SQL_CONTAINSUSERGROUP = "SELECT COUNT(*) AS COUNT_VALUE FROM Users_UserGroups WHERE userId = :userId AND userGroupId = :userGroupId";
	private static final String _FINDER_COLUMN_UUID_UUID_1 = "user.uuid IS NULL";
	private static final String _FINDER_COLUMN_UUID_UUID_2 = "user.uuid = :uuid";
	private static final String _FINDER_COLUMN_UUID_UUID_3 = "(user.uuid IS NULL OR user.uuid = :uuid)";
	private static final String _FINDER_COLUMN_COMPANYID_COMPANYID_2 = "user.companyId = :companyId";
	private static final String _FINDER_COLUMN_CONTACTID_CONTACTID_2 = "user.contactId = :contactId";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_1 = "user.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_2 = "user.emailAddress = :emailAddress";
	private static final String _FINDER_COLUMN_EMAILADDRESS_EMAILADDRESS_3 = "(user.emailAddress IS NULL OR user.emailAddress = :emailAddress)";
	private static final String _FINDER_COLUMN_PORTRAITID_PORTRAITID_2 = "user.portraitId = :portraitId";
	private static final String _FINDER_COLUMN_C_U_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_U_USERID_2 = "user.userId = :userId";
	private static final String _FINDER_COLUMN_C_DU_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_DU_DEFAULTUSER_2 = "user.defaultUser = :defaultUser";
	private static final String _FINDER_COLUMN_C_SN_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_1 = "user.screenName IS NULL";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_2 = "user.screenName = :screenName";
	private static final String _FINDER_COLUMN_C_SN_SCREENNAME_3 = "(user.screenName IS NULL OR user.screenName = :screenName)";
	private static final String _FINDER_COLUMN_C_EA_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_1 = "user.emailAddress IS NULL";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_2 = "user.emailAddress = :emailAddress";
	private static final String _FINDER_COLUMN_C_EA_EMAILADDRESS_3 = "(user.emailAddress IS NULL OR user.emailAddress = :emailAddress)";
	private static final String _FINDER_COLUMN_C_FID_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_FID_FACEBOOKID_2 = "user.facebookId = :facebookId";
	private static final String _FINDER_COLUMN_C_O_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_O_OPENID_1 = "user.openId IS NULL";
	private static final String _FINDER_COLUMN_C_O_OPENID_2 = "user.openId = :openId";
	private static final String _FINDER_COLUMN_C_O_OPENID_3 = "(user.openId IS NULL OR user.openId = :openId)";
	private static final String _FINDER_COLUMN_C_S_COMPANYID_2 = "user.companyId = :companyId AND ";
	private static final String _FINDER_COLUMN_C_S_STATUS_2 = "user.status = :status";
	private static final String _ORDER_BY_ENTITY_ALIAS = "user.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No User exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No User exists with the key {";

}