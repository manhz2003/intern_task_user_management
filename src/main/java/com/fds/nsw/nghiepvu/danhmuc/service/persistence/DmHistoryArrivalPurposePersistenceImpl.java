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
import com.fds.nsw.nghiepvu.model.DmHistoryArrivalPurpose;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryArrivalPurposeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryArrivalPurposeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryArrivalPurposePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryArrivalPurposeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryArrivalPurpose> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryArrivalPurposeUtil} to access the dm history arrival purpose persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryArrivalPurpose create(int id) {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = new DmHistoryArrivalPurpose();

		
		//dmHistoryArrivalPurpose.setPrimaryKey(id);

		return dmHistoryArrivalPurpose;
	}

	/**
	 * Removes the dm history arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history arrival purpose
	 * @return the dm history arrival purpose that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose remove(int id)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history arrival purpose with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history arrival purpose
	 * @return the dm history arrival purpose that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryArrivalPurpose remove(Serializable primaryKey)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		

		try {
			

			DmHistoryArrivalPurpose dmHistoryArrivalPurpose = findByPrimaryKey(primaryKey);

			if (dmHistoryArrivalPurpose == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryArrivalPurposeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryArrivalPurpose);
			return dmHistoryArrivalPurpose;
		}
		catch (NoSuchDmHistoryArrivalPurposeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryArrivalPurpose remove(DmHistoryArrivalPurpose DmHistoryArrivalPurpose) throws SystemException {
	removeImpl(DmHistoryArrivalPurpose);	return DmHistoryArrivalPurpose;
}

protected DmHistoryArrivalPurpose removeImpl

(
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose)
		throws SystemException {
		try {
			repository.delete(dmHistoryArrivalPurpose);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryArrivalPurpose;
	}

	
	public DmHistoryArrivalPurpose updateImpl(
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryArrivalPurpose);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryArrivalPurpose;
	}

	
	public DmHistoryArrivalPurpose findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history arrival purpose with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException} if it could not be found.
	 *
	 * @param id the primary key of the dm history arrival purpose
	 * @return the dm history arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose findByPrimaryKey(int id)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = fetchByPrimaryKey(id);

		if (dmHistoryArrivalPurpose == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryArrivalPurposeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryArrivalPurpose;
	}

	/**
	 * Returns the dm history arrival purpose with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history arrival purpose
	 * @return the dm history arrival purpose, or <code>null</code> if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryArrivalPurpose fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history arrival purpose with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history arrival purpose
	 * @return the dm history arrival purpose, or <code>null</code> if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = null;

		

		if (dmHistoryArrivalPurpose == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryArrivalPurpose> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryArrivalPurpose = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryArrivalPurpose;
	}

	/**
	 * Returns all the dm history arrival purposes where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @return the matching dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findByPurposeCode(String purposeCode)
		throws SystemException {
		return findByPurposeCode(purposeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history arrival purposes where purposeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeCode the purpose code
	 * @param start the lower bound of the range of dm history arrival purposes
	 * @param end the upper bound of the range of dm history arrival purposes (not inclusive)
	 * @return the range of matching dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findByPurposeCode(String purposeCode,
		int start, int end) throws SystemException {
		return findByPurposeCode(purposeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history arrival purposes where purposeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param purposeCode the purpose code
	 * @param start the lower bound of the range of dm history arrival purposes
	 * @param end the upper bound of the range of dm history arrival purposes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findByPurposeCode(String purposeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryArrivalPurpose> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYARRIVALPURPOSE_WHERE);

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
				query.append(DmHistoryArrivalPurposeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (purposeCode != null) {
					builder.appendNamedParameterMap("purposeCode", purposeCode);
				}

				list = (List<DmHistoryArrivalPurpose>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose findByPurposeCode_First(String purposeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = fetchByPurposeCode_First(purposeCode,
				orderByComparator);

		if (dmHistoryArrivalPurpose != null) {
			return dmHistoryArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeCode=");
		msg.append(purposeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the first dm history arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history arrival purpose, or <code>null</code> if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose fetchByPurposeCode_First(
		String purposeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryArrivalPurpose> list = findByPurposeCode(purposeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose findByPurposeCode_Last(String purposeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = fetchByPurposeCode_Last(purposeCode,
				orderByComparator);

		if (dmHistoryArrivalPurpose != null) {
			return dmHistoryArrivalPurpose;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("purposeCode=");
		msg.append(purposeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryArrivalPurposeException(msg.toString());
	}

	/**
	 * Returns the last dm history arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history arrival purpose, or <code>null</code> if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose fetchByPurposeCode_Last(String purposeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByPurposeCode(purposeCode);

		List<DmHistoryArrivalPurpose> list = findByPurposeCode(purposeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history arrival purposes before and after the current dm history arrival purpose in the ordered set where purposeCode = &#63;.
	 *
	 * @param id the primary key of the current dm history arrival purpose
	 * @param purposeCode the purpose code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a dm history arrival purpose with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose[] findByPurposeCode_PrevAndNext(int id,
		String purposeCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = findByPrimaryKey(id);

		

		try {
			

			DmHistoryArrivalPurpose[] array = new DmHistoryArrivalPurpose[3];

			array[0] = getByPurposeCode_PrevAndNext(
					dmHistoryArrivalPurpose, purposeCode, orderByComparator,
					true);

			array[1] = dmHistoryArrivalPurpose;

			array[2] = getByPurposeCode_PrevAndNext(
					dmHistoryArrivalPurpose, purposeCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryArrivalPurpose getByPurposeCode_PrevAndNext(
		 DmHistoryArrivalPurpose dmHistoryArrivalPurpose,
		String purposeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYARRIVALPURPOSE_WHERE);

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
			query.append(DmHistoryArrivalPurposeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (purposeCode != null) {
			builder.appendNamedParameterMap("purposeCode", purposeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryArrivalPurpose);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryArrivalPurpose> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm history arrival purpose where purposeCode = &#63; and syncVersion = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException} if it could not be found.
	 *
	 * @param purposeCode the purpose code
	 * @param syncVersion the sync version
	 * @return the matching dm history arrival purpose
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryArrivalPurposeException if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose findByPurposeCodeAndSyncVersion(
		String purposeCode, String syncVersion)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = fetchByPurposeCodeAndSyncVersion(purposeCode,
				syncVersion);

		if (dmHistoryArrivalPurpose == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("purposeCode=");
			msg.append(purposeCode);

			msg.append(", syncVersion=");
			msg.append(syncVersion);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmHistoryArrivalPurposeException(msg.toString());
		}

		return dmHistoryArrivalPurpose;
	}

	/**
	 * Returns the dm history arrival purpose where purposeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param purposeCode the purpose code
	 * @param syncVersion the sync version
	 * @return the matching dm history arrival purpose, or <code>null</code> if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose fetchByPurposeCodeAndSyncVersion(
		String purposeCode, String syncVersion) throws SystemException {
		return fetchByPurposeCodeAndSyncVersion(purposeCode, syncVersion, true);
	}

	/**
	 * Returns the dm history arrival purpose where purposeCode = &#63; and syncVersion = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param purposeCode the purpose code
	 * @param syncVersion the sync version
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm history arrival purpose, or <code>null</code> if a matching dm history arrival purpose could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose fetchByPurposeCodeAndSyncVersion(
		String purposeCode, String syncVersion, boolean retrieveFromCache)
		throws SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = null;
		if (dmHistoryArrivalPurpose == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMHISTORYARRIVALPURPOSE_WHERE);

			if (purposeCode == null) {
				query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_1);
			}
			else {
				if (purposeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			query.append(DmHistoryArrivalPurposeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmHistoryArrivalPurpose.class).build();

				

				if (purposeCode != null) {
					builder.appendNamedParameterMap("purposeCode", purposeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				dmHistoryArrivalPurpose = (DmHistoryArrivalPurpose) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmHistoryArrivalPurpose;
	}

	/**
	 * Returns all the dm history arrival purposes.
	 *
	 * @return the dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history arrival purposes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history arrival purposes
	 * @param end the upper bound of the range of dm history arrival purposes (not inclusive)
	 * @return the range of dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history arrival purposes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history arrival purposes
	 * @param end the upper bound of the range of dm history arrival purposes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryArrivalPurpose> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryArrivalPurpose> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYARRIVALPURPOSE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYARRIVALPURPOSE.concat(DmHistoryArrivalPurposeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryArrivalPurpose>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history arrival purposes where purposeCode = &#63; from the database.
	 *
	 * @param purposeCode the purpose code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByPurposeCode(String purposeCode)
		throws SystemException {
		for (DmHistoryArrivalPurpose dmHistoryArrivalPurpose : findByPurposeCode(
				purposeCode)) {
			repository.delete(dmHistoryArrivalPurpose);
		}
	}

	/**
	 * Removes the dm history arrival purpose where purposeCode = &#63; and syncVersion = &#63; from the database.
	 *
	 * @param purposeCode the purpose code
	 * @param syncVersion the sync version
	 * @return the dm history arrival purpose that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryArrivalPurpose removeByPurposeCodeAndSyncVersion(
		String purposeCode, String syncVersion)
		throws NoSuchDmHistoryArrivalPurposeException, SystemException {
		DmHistoryArrivalPurpose dmHistoryArrivalPurpose = findByPurposeCodeAndSyncVersion(purposeCode,
				syncVersion);

		repository.delete(dmHistoryArrivalPurpose);
			return dmHistoryArrivalPurpose;
	}

	/**
	 * Removes all the dm history arrival purposes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryArrivalPurpose dmHistoryArrivalPurpose : findAll()) {
			repository.delete(dmHistoryArrivalPurpose);
		}
	}

	/**
	 * Returns the number of dm history arrival purposes where purposeCode = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @return the number of matching dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPurposeCode(String purposeCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYARRIVALPURPOSE_WHERE);

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
	 * Returns the number of dm history arrival purposes where purposeCode = &#63; and syncVersion = &#63;.
	 *
	 * @param purposeCode the purpose code
	 * @param syncVersion the sync version
	 * @return the number of matching dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByPurposeCodeAndSyncVersion(String purposeCode,
		String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMHISTORYARRIVALPURPOSE_WHERE);

			if (purposeCode == null) {
				query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_1);
			}
			else {
				if (purposeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_2);
				}
			}

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (purposeCode != null) {
					builder.appendNamedParameterMap("purposeCode", purposeCode);
				}

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
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
	 * Returns the number of dm history arrival purposes.
	 *
	 * @return the number of dm history arrival purposes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYARRIVALPURPOSE).build();

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
	 * Initializes the dm history arrival purpose persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYARRIVALPURPOSE = "SELECT dmHistoryArrivalPurpose FROM DmHistoryArrivalPurpose dmHistoryArrivalPurpose";
	private static final String _SQL_SELECT_DMHISTORYARRIVALPURPOSE_WHERE = "SELECT dmHistoryArrivalPurpose FROM DmHistoryArrivalPurpose dmHistoryArrivalPurpose WHERE ";
	private static final String _SQL_COUNT_DMHISTORYARRIVALPURPOSE = "SELECT COUNT(dmHistoryArrivalPurpose) FROM DmHistoryArrivalPurpose dmHistoryArrivalPurpose";
	private static final String _SQL_COUNT_DMHISTORYARRIVALPURPOSE_WHERE = "SELECT COUNT(dmHistoryArrivalPurpose) FROM DmHistoryArrivalPurpose dmHistoryArrivalPurpose WHERE ";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_1 = "dmHistoryArrivalPurpose.purposeCode IS NULL";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_2 = "dmHistoryArrivalPurpose.purposeCode =:purposeCode";
	private static final String _FINDER_COLUMN_PURPOSECODE_PURPOSECODE_3 = "(dmHistoryArrivalPurpose.purposeCode IS NULL OR dmHistoryArrivalPurpose.purposeCode =:purposeCode)";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_1 =
		"dmHistoryArrivalPurpose.purposeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_2 =
		"dmHistoryArrivalPurpose.purposeCode =:purposeCode AND ";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_PURPOSECODE_3 =
		"(dmHistoryArrivalPurpose.purposeCode IS NULL OR dmHistoryArrivalPurpose.purposeCode =:purposeCode) AND ";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_1 =
		"dmHistoryArrivalPurpose.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_2 =
		"dmHistoryArrivalPurpose.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_PURPOSECODEANDSYNCVERSION_SYNCVERSION_3 =
		"(dmHistoryArrivalPurpose.syncVersion IS NULL OR dmHistoryArrivalPurpose.syncVersion =:syncVersion)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryArrivalPurpose.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryArrivalPurpose exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryArrivalPurpose exists with the key {";
	

	
}
