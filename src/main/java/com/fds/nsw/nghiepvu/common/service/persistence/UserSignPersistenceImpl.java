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
import com.fds.nsw.nghiepvu.model.UserSign;
import com.fds.nsw.nghiepvu.repository.UserSignRepository;
import com.fds.nsw.nghiepvu.service.exception.NoSuchUserSignException;

import com.fds.nsw.nghiepvu.modelImpl.UserSignModelImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserSignPersistenceImpl extends BasePersistence {
	@Autowired
	UserSignRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<UserSign> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link UserSignUtil} to access the user sign persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public UserSign create(long id) {
		UserSign userSign = new UserSign();

		
		//userSign.setPrimaryKey(id);

		return userSign;
	}

	/**
	 * Removes the user sign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the user sign
	 * @return the user sign that was removed
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign remove(long id)
		throws NoSuchUserSignException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the user sign with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the user sign
	 * @return the user sign that was removed
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public UserSign remove(Serializable primaryKey)
		throws NoSuchUserSignException, SystemException {
		

		try {
			

			UserSign userSign = findByPrimaryKey(primaryKey);

			if (userSign == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchUserSignException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(userSign);
			return userSign;
		}
		catch (NoSuchUserSignException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public UserSign remove(UserSign UserSign) throws SystemException {
	removeImpl(UserSign);
	return UserSign;
}

protected UserSign removeImpl(UserSign userSign) throws SystemException {
		try {
			repository.delete(userSign);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return userSign;
	}

	
	public UserSign updateImpl(UserSign userSign,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(userSign);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return userSign;
	}

	
	public UserSign findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user sign with the primary key or throws a {@link vn.gt.dao.common.NoSuchUserSignException} if it could not be found.
	 *
	 * @param id the primary key of the user sign
	 * @return the user sign
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign findByPrimaryKey(long id)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = fetchByPrimaryKey(id);

		if (userSign == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchUserSignException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return userSign;
	}

	/**
	 * Returns the user sign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the user sign
	 * @return the user sign, or <code>null</code> if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public UserSign fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the user sign with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the user sign
	 * @return the user sign, or <code>null</code> if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign fetchByPrimaryKey(long id) throws SystemException {
		UserSign userSign = null;

		

		if (userSign == null) {
			

			boolean hasException = false;

			try {
				

				Optional<UserSign> optional = repository.findById(id);
				if (optional.isPresent()) {
					userSign = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return userSign;
	}

	/**
	 * Returns all the user signs where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the matching user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findByPortCode(String portCode)
		throws SystemException {
		return findByPortCode(portCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the user signs where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of user signs
	 * @param end the upper bound of the range of user signs (not inclusive)
	 * @return the range of matching user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findByPortCode(String portCode, int start, int end)
		throws SystemException {
		return findByPortCode(portCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the user signs where portCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portCode the port code
	 * @param start the lower bound of the range of user signs
	 * @param end the upper bound of the range of user signs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findByPortCode(String portCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserSign> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_USERSIGN_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(UserSignModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

				list = (List<UserSign>)queryFactory.getResultList(builder);
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
	 * Returns the first user sign in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user sign
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign findByPortCode_First(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = fetchByPortCode_First(portCode, orderByComparator);

		if (userSign != null) {
			return userSign;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserSignException(msg.toString());
	}

	/**
	 * Returns the first user sign in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching user sign, or <code>null</code> if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign fetchByPortCode_First(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserSign> list = findByPortCode(portCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last user sign in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user sign
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign findByPortCode_Last(String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = fetchByPortCode_Last(portCode, orderByComparator);

		if (userSign != null) {
			return userSign;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portCode=");
		msg.append(portCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchUserSignException(msg.toString());
	}

	/**
	 * Returns the last user sign in the ordered set where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching user sign, or <code>null</code> if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign fetchByPortCode_Last(String portCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPortCode(portCode);

		List<UserSign> list = findByPortCode(portCode, count - 1, count,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the user signs before and after the current user sign in the ordered set where portCode = &#63;.
	 *
	 * @param id the primary key of the current user sign
	 * @param portCode the port code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next user sign
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a user sign with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign[] findByPortCode_PrevAndNext(long id, String portCode,
		OrderByComparator orderByComparator)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = findByPrimaryKey(id);

		

		try {
			

			UserSign[] array = new UserSign[3];

			array[0] = getByPortCode_PrevAndNext(userSign, portCode,
					orderByComparator, true);

			array[1] = userSign;

			array[2] = getByPortCode_PrevAndNext(userSign, portCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected UserSign getByPortCode_PrevAndNext(
		UserSign userSign, String portCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_USERSIGN_WHERE);

		if (portCode == null) {
			query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
		}
		else {
			if (portCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
			}
			else {
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
			query.append(UserSignModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portCode != null) {
			builder.appendNamedParameterMap("portCode", portCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(userSign);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<UserSign> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the user sign where userId = &#63; or throws a {@link vn.gt.dao.common.NoSuchUserSignException} if it could not be found.
	 *
	 * @param userId the user ID
	 * @return the matching user sign
	 * @throws vn.gt.dao.common.NoSuchUserSignException if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign findByUserId(long userId)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = fetchByUserId(userId);

		if (userSign == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("userId=");
			msg.append(userId);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchUserSignException(msg.toString());
		}

		return userSign;
	}

	/**
	 * Returns the user sign where userId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param userId the user ID
	 * @return the matching user sign, or <code>null</code> if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign fetchByUserId(long userId) throws SystemException {
		return fetchByUserId(userId, true);
	}

	/**
	 * Returns the user sign where userId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param userId the user ID
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching user sign, or <code>null</code> if a matching user sign could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign fetchByUserId(long userId, boolean retrieveFromCache)
		throws SystemException {
		UserSign userSign = null;
		if (userSign == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_USERSIGN_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			query.append(UserSignModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(UserSign.class).build();

				

				builder.appendNamedParameterMap("userId", userId);

				userSign = (UserSign) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return userSign;
	}

	/**
	 * Returns all the user signs.
	 *
	 * @return the user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the user signs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user signs
	 * @param end the upper bound of the range of user signs (not inclusive)
	 * @return the range of user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findAll(int start, int end) throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the user signs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of user signs
	 * @param end the upper bound of the range of user signs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of user signs
	 * @throws SystemException if a system exception occurred
	 */
	public List<UserSign> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<UserSign> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_USERSIGN);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_USERSIGN.concat(UserSignModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<UserSign>) queryFactory.getResultList(builder);
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
	 * Removes all the user signs where portCode = &#63; from the database.
	 *
	 * @param portCode the port code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPortCode(String portCode) throws SystemException {
		for (UserSign userSign : findByPortCode(portCode)) {
			repository.delete(userSign);
		}
	}

	/**
	 * Removes the user sign where userId = &#63; from the database.
	 *
	 * @param userId the user ID
	 * @return the user sign that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public UserSign removeByUserId(long userId)
		throws NoSuchUserSignException, SystemException {
		UserSign userSign = findByUserId(userId);

		repository.delete(userSign);
			return userSign;
	}

	/**
	 * Removes all the user signs from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (UserSign userSign : findAll()) {
			repository.delete(userSign);
		}
	}

	/**
	 * Returns the number of user signs where portCode = &#63;.
	 *
	 * @param portCode the port code
	 * @return the number of matching user signs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPortCode(String portCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERSIGN_WHERE);

			if (portCode == null) {
				query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_1);
			}
			else {
				if (portCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTCODE_PORTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portCode != null) {
					builder.appendNamedParameterMap("portCode", portCode);
				}

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
	 * Returns the number of user signs where userId = &#63;.
	 *
	 * @param userId the user ID
	 * @return the number of matching user signs
	 * @throws SystemException if a system exception occurred
	 */
	public int countByUserId(long userId) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_USERSIGN_WHERE);

			query.append(_FINDER_COLUMN_USERID_USERID_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("userId", userId);

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
	 * Returns the number of user signs.
	 *
	 * @return the number of user signs
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_USERSIGN).build();

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
	 * Initializes the user sign persistence.
	 */
	private static final String _SQL_SELECT_USERSIGN = "SELECT userSign FROM UserSign userSign";
	private static final String _SQL_SELECT_USERSIGN_WHERE = "SELECT userSign FROM UserSign userSign WHERE ";
	private static final String _SQL_COUNT_USERSIGN = "SELECT COUNT(userSign) FROM UserSign userSign";
	private static final String _SQL_COUNT_USERSIGN_WHERE = "SELECT COUNT(userSign) FROM UserSign userSign WHERE ";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_1 = "userSign.portCode IS NULL";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_2 = "userSign.portCode =:portCode";
	private static final String _FINDER_COLUMN_PORTCODE_PORTCODE_3 = "(userSign.portCode IS NULL OR userSign.portCode =:portCode)";
	private static final String _FINDER_COLUMN_USERID_USERID_2 = "userSign.userId =:userId";
	private static final String _ORDER_BY_ENTITY_ALIAS = "userSign.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No UserSign exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No UserSign exists with the key {";
	

	
}
