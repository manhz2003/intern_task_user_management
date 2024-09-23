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

package com.fds.nsw.nghiepvu.danhmuc.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmArrivalPurpose;
import com.fds.nsw.nghiepvu.repository.AswmsgMessagequeueRepository;
import com.fds.nsw.nghiepvu.repository.DmArrivalPurposeRepository;
import com.fds.nsw.nghiepvu.service.exception.NoSuchAswmsgMessageQueueException;
import com.fds.nsw.nghiepvu.service.exception.NoSuchDmArrivalPurposeException;

import com.fds.nsw.nghiepvu.modelImpl.DmArrivalPurposeModelImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmArrivalPurposePersistenceImpl extends BasePersistence {
	@Autowired
	DmArrivalPurposeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmArrivalPurpose> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmArrivalPurposeUtil} to access the dm arrival purpose persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmArrivalPurpose create(int id) {
		DmArrivalPurpose dmArrivalPurpose = new DmArrivalPurpose();

		
		//dmArrivalPurpose.setPrimaryKey(id);

		return dmArrivalPurpose;
	}

	/**
	 * Removes the dm arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm arrival purpose
	 * @return the dm arrival purpose that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose remove(int id)
		throws NoSuchDmArrivalPurposeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm arrival purpose
	 * @return the dm arrival purpose that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmArrivalPurpose remove(Serializable primaryKey)
		throws NoSuchDmArrivalPurposeException, SystemException {
		

		try {
			

			DmArrivalPurpose dmArrivalPurpose = findByPrimaryKey(primaryKey);

			if (dmArrivalPurpose == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmArrivalPurposeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}
			
			repository.delete(dmArrivalPurpose);
			return dmArrivalPurpose;
		}
		catch (NoSuchDmArrivalPurposeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

public DmArrivalPurpose remove(DmArrivalPurpose DmArrivalPurpose) throws SystemException {
	removeImpl(DmArrivalPurpose);	return DmArrivalPurpose;
}

protected DmArrivalPurpose removeImpl


(DmArrivalPurpose dmArrivalPurpose)
		throws SystemException {
		try {
			repository.delete(dmArrivalPurpose);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmArrivalPurpose;
	}

	
	public DmArrivalPurpose updateImpl(
		DmArrivalPurpose dmArrivalPurpose, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmArrivalPurpose);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmArrivalPurpose;
	}

	
	public DmArrivalPurpose findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm arrival purpose with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException} if it could not be found.
	 *
	 * @param id the primary key of the dm arrival purpose
	 * @return the dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose findByPrimaryKey(int id)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = fetchByPrimaryKey(id);

		if (dmArrivalPurpose == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmArrivalPurposeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmArrivalPurpose;
	}

	/**
	 * Returns the dm arrival purpose with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm arrival purpose
	 * @return the dm arrival purpose, or <code>null</code> if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmArrivalPurpose fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm arrival purpose with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm arrival purpose
	 * @return the dm arrival purpose, or <code>null</code> if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose fetchByPrimaryKey(int id) throws SystemException {
		DmArrivalPurpose dmArrivalPurpose = null;

		

		if (dmArrivalPurpose == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmArrivalPurpose> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmArrivalPurpose = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmArrivalPurpose;
	}

	/**
	 * Returns all the dm arrival purposes where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @return the matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByPurposeCode(String purposeCode)
		throws SystemException {
		return findByPurposeCode(purposeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm arrival purposes where purposeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeCode the purpose code
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @return the range of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByPurposeCode(String purposeCode,
		int start, int end) throws SystemException {
		return findByPurposeCode(purposeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm arrival purposes where purposeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeCode the purpose code
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByPurposeCode(String purposeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmArrivalPurpose> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMARRIVALPURPOSE_WHERE);

			if (purposeCode == null) {
				query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_1);
			}
			else {
				if (purposeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmArrivalPurposeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (purposeCode != null) {
					builder.appendNamedParameterMap("purposeCode", purposeCode);
				}

				list = (List<DmArrivalPurpose>)queryFactory.getResultList(builder);
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
	 * Returns the first dm arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose findByPurposeCode_First(String purposeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = fetchByPurposeCode_First(purposeCode,
				orderByComparator);

		if (dmArrivalPurpose != null) {
			return dmArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeCode=");
		msg.append(purposeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the first dm arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm arrival purpose, or <code>null</code> if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose fetchByPurposeCode_First(String purposeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmArrivalPurpose> list = findByPurposeCode(purposeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose findByPurposeCode_Last(String purposeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = fetchByPurposeCode_Last(purposeCode,
				orderByComparator);

		if (dmArrivalPurpose != null) {
			return dmArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeCode=");
		msg.append(purposeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the last dm arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm arrival purpose, or <code>null</code> if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose fetchByPurposeCode_Last(String purposeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPurposeCode(purposeCode);

		List<DmArrivalPurpose> list = findByPurposeCode(purposeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm arrival purposes before and after the current dm arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param id the primary key of the current dm arrival purpose
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose[] findByPurposeCode_PrevAndNext(int id,
		String purposeCode, OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = findByPrimaryKey(id);

		

		try {
			

			DmArrivalPurpose[] array = new DmArrivalPurpose[3];

			array[0] = getByPurposeCode_PrevAndNext(dmArrivalPurpose,
					purposeCode, orderByComparator, true);

			array[1] = dmArrivalPurpose;

			array[2] = getByPurposeCode_PrevAndNext(dmArrivalPurpose,
					purposeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmArrivalPurpose getByPurposeCode_PrevAndNext(
		DmArrivalPurpose dmArrivalPurpose, String purposeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMARRIVALPURPOSE_WHERE);

		if (purposeCode == null) {
			query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_1);
		}
		else {
			if (purposeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_2);
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
			query.append(DmArrivalPurposeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (purposeCode != null) {
			builder.appendNamedParameterMap("purposeCode", purposeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmArrivalPurpose);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmArrivalPurpose> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm arrival purposes where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @return the matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByF_purposeName(String purposeName)
		throws SystemException {
		return findByF_purposeName(purposeName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm arrival purposes where purposeName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeName the purpose name
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @return the range of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByF_purposeName(String purposeName,
		int start, int end) throws SystemException {
		return findByF_purposeName(purposeName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm arrival purposes where purposeName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeName the purpose name
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findByF_purposeName(String purposeName,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmArrivalPurpose> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMARRIVALPURPOSE_WHERE);

			if (purposeName == null) {
				query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_1);
			}
			else {
				if (purposeName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmArrivalPurposeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (purposeName != null) {
					builder.appendNamedParameterMap("purposeName", purposeName);
				}

				list = (List<DmArrivalPurpose>)queryFactory.getResultList(builder);
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
	 * Returns the first dm arrival purpose in the ordered set where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose findByF_purposeName_First(String purposeName,
		OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = fetchByF_purposeName_First(purposeName,
				orderByComparator);

		if (dmArrivalPurpose != null) {
			return dmArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeName=");
		msg.append(purposeName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the first dm arrival purpose in the ordered set where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm arrival purpose, or <code>null</code> if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose fetchByF_purposeName_First(String purposeName,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmArrivalPurpose> list = findByF_purposeName(purposeName, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm arrival purpose in the ordered set where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose findByF_purposeName_Last(String purposeName,
		OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = fetchByF_purposeName_Last(purposeName,
				orderByComparator);

		if (dmArrivalPurpose != null) {
			return dmArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeName=");
		msg.append(purposeName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the last dm arrival purpose in the ordered set where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm arrival purpose, or <code>null</code> if a matching dm arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose fetchByF_purposeName_Last(String purposeName,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_purposeName(purposeName);

		List<DmArrivalPurpose> list = findByF_purposeName(purposeName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm arrival purposes before and after the current dm arrival purpose in the ordered set where purposeName LIKE &#63;.
	 *
	 * @param id the primary key of the current dm arrival purpose
	 * @param purposeName the purpose name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmArrivalPurposeException if a dm arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmArrivalPurpose[] findByF_purposeName_PrevAndNext(int id,
		String purposeName, OrderByComparator orderByComparator)
		throws NoSuchDmArrivalPurposeException, SystemException {
		DmArrivalPurpose dmArrivalPurpose = findByPrimaryKey(id);

		

		try {
			

			DmArrivalPurpose[] array = new DmArrivalPurpose[3];

			array[0] = getByF_purposeName_PrevAndNext(
					dmArrivalPurpose, purposeName, orderByComparator, true);

			array[1] = dmArrivalPurpose;

			array[2] = getByF_purposeName_PrevAndNext(
					dmArrivalPurpose, purposeName, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmArrivalPurpose getByF_purposeName_PrevAndNext(
		DmArrivalPurpose dmArrivalPurpose, String purposeName,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMARRIVALPURPOSE_WHERE);

		if (purposeName == null) {
			query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_1);
		}
		else {
			if (purposeName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_2);
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
			query.append(DmArrivalPurposeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (purposeName != null) {
			builder.appendNamedParameterMap("purposeName", purposeName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmArrivalPurpose);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmArrivalPurpose> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm arrival purposes.
	 *
	 * @return the dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm arrival purposes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @return the range of dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm arrival purposes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm arrival purposes
	 * @param end the upper bound of the range of dm arrival purposes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmArrivalPurpose> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmArrivalPurpose> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMARRIVALPURPOSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMARRIVALPURPOSE.concat(DmArrivalPurposeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmArrivalPurpose>) queryFactory.getResultList(builder);
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
	 * Removes all the dm arrival purposes where purposeCode = &#63; from the database.
	 *
	 * @param purposeCode the purpose code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPurposeCode(String purposeCode)
		throws SystemException {
		for (DmArrivalPurpose dmArrivalPurpose : findByPurposeCode(purposeCode)) {
			repository.delete(dmArrivalPurpose);
		}
	}

	/**
	 * Removes all the dm arrival purposes where purposeName LIKE &#63; from the database.
	 *
	 * @param purposeName the purpose name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_purposeName(String purposeName)
		throws SystemException {
		for (DmArrivalPurpose dmArrivalPurpose : findByF_purposeName(
				purposeName)) {
			repository.delete(dmArrivalPurpose);
		}
	}

	/**
	 * Removes all the dm arrival purposes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmArrivalPurpose dmArrivalPurpose : findAll()) {
			repository.delete(dmArrivalPurpose);
		}
	}

	/**
	 * Returns the number of dm arrival purposes where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @return the number of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPurposeCode(String purposeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMARRIVALPURPOSE_WHERE);

			if (purposeCode == null) {
				query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_1);
			}
			else {
				if (purposeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODE_PURPOSECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (purposeCode != null) {
					builder.appendNamedParameterMap("purposeCode", purposeCode);
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
	 * Returns the number of dm arrival purposes where purposeName LIKE &#63;.
	 *
	 * @param purposeName the purpose name
	 * @return the number of matching dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_purposeName(String purposeName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMARRIVALPURPOSE_WHERE);

			if (purposeName == null) {
				query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_1);
			}
			else {
				if (purposeName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (purposeName != null) {
					builder.appendNamedParameterMap("purposeName", purposeName);
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
	 * Returns the number of dm arrival purposes.
	 *
	 * @return the number of dm arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMARRIVALPURPOSE).build();

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
	 * Initializes the dm arrival purpose persistence.
	 */
	private static final String _SQL_SELECT_DMARRIVALPURPOSE = "SELECT dmArrivalPurpose FROM DmArrivalPurpose dmArrivalPurpose";
	private static final String _SQL_SELECT_DMARRIVALPURPOSE_WHERE = "SELECT dmArrivalPurpose FROM DmArrivalPurpose dmArrivalPurpose WHERE ";
	private static final String _SQL_COUNT_DMARRIVALPURPOSE = "SELECT COUNT(dmArrivalPurpose) FROM DmArrivalPurpose dmArrivalPurpose";
	private static final String _SQL_COUNT_DMARRIVALPURPOSE_WHERE = "SELECT COUNT(dmArrivalPurpose) FROM DmArrivalPurpose dmArrivalPurpose WHERE ";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_1 = "dmArrivalPurpose.purposeCode IS NULL";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_2 = "dmArrivalPurpose.purposeCode =:purposeCode";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_3 = "(dmArrivalPurpose.purposeCode IS NULL OR dmArrivalPurpose.purposeCode =:purposeCode)";
	private static final String _FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_1 = "dmArrivalPurpose.purposeName LIKE NULL";
	private static final String _FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_2 = "dmArrivalPurpose.purposeName LIKE :purposeName";
	private static final String _FINDER_COLUMN_F_PURPOSENAME_PURPOSENAME_3 = "(dmArrivalPurpose.purposeName IS NULL OR dmArrivalPurpose.purposeName LIKE :purposeName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmArrivalPurpose.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmArrivalPurpose exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmArrivalPurpose exists with the key {";
	

	
}
