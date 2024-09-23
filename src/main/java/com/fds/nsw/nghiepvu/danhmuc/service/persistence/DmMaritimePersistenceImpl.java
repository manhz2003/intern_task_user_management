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
import com.fds.nsw.nghiepvu.model.DmMaritime;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmMaritimeRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmMaritimeModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmMaritimePersistenceImpl extends BasePersistence {
	@Autowired
	DmMaritimeRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmMaritime> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmMaritimeUtil} to access the dm maritime persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmMaritime create(int id) {
		DmMaritime dmMaritime = new DmMaritime();

		
		//dmMaritime.setPrimaryKey(id);

		return dmMaritime;
	}

	/**
	 * Removes the dm maritime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm maritime
	 * @return the dm maritime that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime remove(int id)
		throws NoSuchDmMaritimeException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm maritime with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm maritime
	 * @return the dm maritime that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmMaritime remove(Serializable primaryKey)
		throws NoSuchDmMaritimeException, SystemException {
		

		try {
			

			DmMaritime dmMaritime = findByPrimaryKey(primaryKey);

			if (dmMaritime == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmMaritimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmMaritime);
			return dmMaritime;
		}
		catch (NoSuchDmMaritimeException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmMaritime remove(DmMaritime DmMaritime) throws SystemException {
	removeImpl(DmMaritime);	return DmMaritime;
}

protected DmMaritime removeImpl

(DmMaritime dmMaritime)
		throws SystemException {
		try {
			repository.delete(dmMaritime);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmMaritime;
	}

	
	public DmMaritime updateImpl(
		DmMaritime dmMaritime, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmMaritime);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmMaritime;
	}

	
	public DmMaritime findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm maritime with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmMaritimeException} if it could not be found.
	 *
	 * @param id the primary key of the dm maritime
	 * @return the dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime findByPrimaryKey(int id)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = fetchByPrimaryKey(id);

		if (dmMaritime == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmMaritimeException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmMaritime;
	}

	/**
	 * Returns the dm maritime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm maritime
	 * @return the dm maritime, or <code>null</code> if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmMaritime fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm maritime with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm maritime
	 * @return the dm maritime, or <code>null</code> if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByPrimaryKey(int id) throws SystemException {
		DmMaritime dmMaritime = null;

		

		if (dmMaritime == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmMaritime> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmMaritime = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmMaritime;
	}

	/**
	 * Returns the dm maritime where maritimeCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmMaritimeException} if it could not be found.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime findByMaritimeCode(String maritimeCode)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = fetchByMaritimeCode(maritimeCode);

		if (dmMaritime == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeCode=");
			msg.append(maritimeCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmMaritimeException(msg.toString());
		}

		return dmMaritime;
	}

	/**
	 * Returns the dm maritime where maritimeCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByMaritimeCode(String maritimeCode)
		throws SystemException {
		return fetchByMaritimeCode(maritimeCode, true);
	}

	/**
	 * Returns the dm maritime where maritimeCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeCode the maritime code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByMaritimeCode(String maritimeCode,
		boolean retrieveFromCache) throws SystemException {
		DmMaritime dmMaritime = null;
		if (dmMaritime == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			query.append(DmMaritimeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmMaritime.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				dmMaritime = (DmMaritime) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmMaritime;
	}

	/**
	 * Returns the dm maritime where maritimeReference = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmMaritimeException} if it could not be found.
	 *
	 * @param maritimeReference the maritime reference
	 * @return the matching dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime findByMaritimeReference(String maritimeReference)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = fetchByMaritimeReference(maritimeReference);

		if (dmMaritime == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("maritimeReference=");
			msg.append(maritimeReference);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmMaritimeException(msg.toString());
		}

		return dmMaritime;
	}

	/**
	 * Returns the dm maritime where maritimeReference = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param maritimeReference the maritime reference
	 * @return the matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByMaritimeReference(String maritimeReference)
		throws SystemException {
		return fetchByMaritimeReference(maritimeReference, true);
	}

	/**
	 * Returns the dm maritime where maritimeReference = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param maritimeReference the maritime reference
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByMaritimeReference(String maritimeReference,
		boolean retrieveFromCache) throws SystemException {
		DmMaritime dmMaritime = null;
		if (dmMaritime == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMMARITIME_WHERE);

			if (maritimeReference == null) {
				query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_1);
			}
			else {
				if (maritimeReference.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_2);
				}
			}

			query.append(DmMaritimeModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmMaritime.class).build();

				

				if (maritimeReference != null) {
					builder.appendNamedParameterMap("maritimeReference", maritimeReference);
				}

				dmMaritime = (DmMaritime) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmMaritime;
	}

	/**
	 * Returns all the dm maritimes where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @return the matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findByDelete(int isDelete, int markedAsDelete)
		throws SystemException {
		return findByDelete(isDelete, markedAsDelete, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm maritimes where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param start the lower bound of the range of dm maritimes
	 * @param end the upper bound of the range of dm maritimes (not inclusive)
	 * @return the range of matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findByDelete(int isDelete, int markedAsDelete,
		int start, int end) throws SystemException {
		return findByDelete(isDelete, markedAsDelete, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm maritimes where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param start the lower bound of the range of dm maritimes
	 * @param end the upper bound of the range of dm maritimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findByDelete(int isDelete, int markedAsDelete,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmMaritime> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMMARITIME_WHERE);

			query.append(_FINDER_COLUMN_DELETE_ISDELETE_2);

			query.append(_FINDER_COLUMN_DELETE_MARKEDASDELETE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmMaritimeModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

				builder.appendNamedParameterMap("markedAsDelete", markedAsDelete);

				list = (List<DmMaritime>)queryFactory.getResultList(builder);
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
	 * Returns the first dm maritime in the ordered set where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime findByDelete_First(int isDelete, int markedAsDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = fetchByDelete_First(isDelete, markedAsDelete,
				orderByComparator);

		if (dmMaritime != null) {
			return dmMaritime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(", markedAsDelete=");
		msg.append(markedAsDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmMaritimeException(msg.toString());
	}

	/**
	 * Returns the first dm maritime in the ordered set where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByDelete_First(int isDelete, int markedAsDelete,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmMaritime> list = findByDelete(isDelete, markedAsDelete, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm maritime in the ordered set where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime findByDelete_Last(int isDelete, int markedAsDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = fetchByDelete_Last(isDelete, markedAsDelete,
				orderByComparator);

		if (dmMaritime != null) {
			return dmMaritime;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(", markedAsDelete=");
		msg.append(markedAsDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmMaritimeException(msg.toString());
	}

	/**
	 * Returns the last dm maritime in the ordered set where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm maritime, or <code>null</code> if a matching dm maritime could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime fetchByDelete_Last(int isDelete, int markedAsDelete,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByDelete(isDelete, markedAsDelete);

		List<DmMaritime> list = findByDelete(isDelete, markedAsDelete,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm maritimes before and after the current dm maritime in the ordered set where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param id the primary key of the current dm maritime
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm maritime
	 * @throws vn.gt.dao.danhmuc.NoSuchDmMaritimeException if a dm maritime with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime[] findByDelete_PrevAndNext(int id, int isDelete,
		int markedAsDelete, OrderByComparator orderByComparator)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = findByPrimaryKey(id);

		

		try {
			

			DmMaritime[] array = new DmMaritime[3];

			array[0] = getByDelete_PrevAndNext(dmMaritime, isDelete,
					markedAsDelete, orderByComparator, true);

			array[1] = dmMaritime;

			array[2] = getByDelete_PrevAndNext(dmMaritime, isDelete,
					markedAsDelete, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmMaritime getByDelete_PrevAndNext(
		DmMaritime dmMaritime, int isDelete, int markedAsDelete,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMMARITIME_WHERE);

		query.append(_FINDER_COLUMN_DELETE_ISDELETE_2);

		query.append(_FINDER_COLUMN_DELETE_MARKEDASDELETE_2);

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
			query.append(DmMaritimeModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("isDelete", isDelete);

		builder.appendNamedParameterMap("markedAsDelete", markedAsDelete);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmMaritime);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmMaritime> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm maritimes.
	 *
	 * @return the dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm maritimes
	 * @param end the upper bound of the range of dm maritimes (not inclusive)
	 * @return the range of dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm maritimes.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm maritimes
	 * @param end the upper bound of the range of dm maritimes (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmMaritime> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmMaritime> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMMARITIME);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMMARITIME.concat(DmMaritimeModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmMaritime>) queryFactory.getResultList(builder);
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
	 * Removes the dm maritime where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @return the dm maritime that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime removeByMaritimeCode(String maritimeCode)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = findByMaritimeCode(maritimeCode);

		repository.delete(dmMaritime);
			return dmMaritime;
	}

	/**
	 * Removes the dm maritime where maritimeReference = &#63; from the database.
	 *
	 * @param maritimeReference the maritime reference
	 * @return the dm maritime that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmMaritime removeByMaritimeReference(String maritimeReference)
		throws NoSuchDmMaritimeException, SystemException {
		DmMaritime dmMaritime = findByMaritimeReference(maritimeReference);

		repository.delete(dmMaritime);
			return dmMaritime;
	}

	/**
	 * Removes all the dm maritimes where isDelete = &#63; and markedAsDelete = &#63; from the database.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByDelete(int isDelete, int markedAsDelete)
		throws SystemException {
		for (DmMaritime dmMaritime : findByDelete(isDelete, markedAsDelete)) {
			repository.delete(dmMaritime);
		}
	}

	/**
	 * Removes all the dm maritimes from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmMaritime dmMaritime : findAll()) {
			repository.delete(dmMaritime);
		}
	}

	/**
	 * Returns the number of dm maritimes where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMMARITIME_WHERE);

			if (maritimeCode == null) {
				query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (maritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
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
	 * Returns the number of dm maritimes where maritimeReference = &#63;.
	 *
	 * @param maritimeReference the maritime reference
	 * @return the number of matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByMaritimeReference(String maritimeReference)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMMARITIME_WHERE);

			if (maritimeReference == null) {
				query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_1);
			}
			else {
				if (maritimeReference.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_3);
				}
				else {
					query.append(_FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (maritimeReference != null) {
					builder.appendNamedParameterMap("maritimeReference", maritimeReference);
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
	 * Returns the number of dm maritimes where isDelete = &#63; and markedAsDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param markedAsDelete the marked as delete
	 * @return the number of matching dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countByDelete(int isDelete, int markedAsDelete)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMMARITIME_WHERE);

			query.append(_FINDER_COLUMN_DELETE_ISDELETE_2);

			query.append(_FINDER_COLUMN_DELETE_MARKEDASDELETE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

				builder.appendNamedParameterMap("markedAsDelete", markedAsDelete);

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
	 * Returns the number of dm maritimes.
	 *
	 * @return the number of dm maritimes
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMMARITIME).build();

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
	 * Initializes the dm maritime persistence.
	 */
	private static final String _SQL_SELECT_DMMARITIME = "SELECT dmMaritime FROM DmMaritime dmMaritime";
	private static final String _SQL_SELECT_DMMARITIME_WHERE = "SELECT dmMaritime FROM DmMaritime dmMaritime WHERE ";
	private static final String _SQL_COUNT_DMMARITIME = "SELECT COUNT(dmMaritime) FROM DmMaritime dmMaritime";
	private static final String _SQL_COUNT_DMMARITIME_WHERE = "SELECT COUNT(dmMaritime) FROM DmMaritime dmMaritime WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "dmMaritime.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "dmMaritime.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(dmMaritime.maritimeCode IS NULL OR dmMaritime.maritimeCode =:maritimeCode)";
	private static final String _FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_1 =
		"dmMaritime.maritimeReference IS NULL";
	private static final String _FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_2 =
		"dmMaritime.maritimeReference =:maritimeReference";
	private static final String _FINDER_COLUMN_MARITIMEREFERENCE_MARITIMEREFERENCE_3 =
		"(dmMaritime.maritimeReference IS NULL OR dmMaritime.maritimeReference =:maritimeReference)";
	private static final String _FINDER_COLUMN_DELETE_ISDELETE_2 = "dmMaritime.isDelete =:isDelete AND ";
	private static final String _FINDER_COLUMN_DELETE_MARKEDASDELETE_2 = "dmMaritime.markedAsDelete =:markedAsDelete";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmMaritime.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmMaritime exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmMaritime exists with the key {";
	

	
}
