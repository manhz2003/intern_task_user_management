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
import com.fds.nsw.nghiepvu.model.TempCrewEffectsItems;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempCrewEffectsItemRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempCrewEffectsItemsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempCrewEffectsItemsPersistenceImpl extends BasePersistence {
	@Autowired
	TempCrewEffectsItemRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempCrewEffectsItems> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempCrewEffectsItemsUtil} to access the temp crew effects items persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempCrewEffectsItems create(long id) {
		TempCrewEffectsItems tempCrewEffectsItems = new TempCrewEffectsItems();

		
		//tempCrewEffectsItems.setPrimaryKey(id);

		return tempCrewEffectsItems;
	}

	/**
	 * Removes the temp crew effects items with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp crew effects items
	 * @return the temp crew effects items that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems remove(long id)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp crew effects items with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp crew effects items
	 * @return the temp crew effects items that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewEffectsItems remove(Serializable primaryKey)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		

		try {
			

			TempCrewEffectsItems tempCrewEffectsItems = findByPrimaryKey(primaryKey);

			if (tempCrewEffectsItems == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempCrewEffectsItemsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempCrewEffectsItems);
			return tempCrewEffectsItems;
		}
		catch (NoSuchTempCrewEffectsItemsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempCrewEffectsItems remove(TempCrewEffectsItems TempCrewEffectsItems) throws SystemException {
	removeImpl(TempCrewEffectsItems);
	return TempCrewEffectsItems;
}

protected TempCrewEffectsItems removeImpl(
		TempCrewEffectsItems tempCrewEffectsItems) throws SystemException {
		try {
			repository.delete(tempCrewEffectsItems);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewEffectsItems;
	}

	
	public TempCrewEffectsItems updateImpl(
		com.fds.nsw.nghiepvu.model.TempCrewEffectsItems tempCrewEffectsItems,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempCrewEffectsItems);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempCrewEffectsItems;
	}

	
	public TempCrewEffectsItems findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew effects items with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException} if it could not be found.
	 *
	 * @param id the primary key of the temp crew effects items
	 * @return the temp crew effects items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems findByPrimaryKey(long id)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = fetchByPrimaryKey(id);

		if (tempCrewEffectsItems == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempCrewEffectsItemsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempCrewEffectsItems;
	}

	/**
	 * Returns the temp crew effects items with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp crew effects items
	 * @return the temp crew effects items, or <code>null</code> if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempCrewEffectsItems fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp crew effects items with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp crew effects items
	 * @return the temp crew effects items, or <code>null</code> if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems fetchByPrimaryKey(long id)
		throws SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = null;

		

		if (tempCrewEffectsItems == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempCrewEffectsItems> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempCrewEffectsItems = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempCrewEffectsItems;
	}

	/**
	 * Returns all the temp crew effects itemses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew effects itemses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew effects itemses
	 * @param end the upper bound of the range of temp crew effects itemses (not inclusive)
	 * @return the range of matching temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findByRequestCode(String requestCode,
		int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects itemses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp crew effects itemses
	 * @param end the upper bound of the range of temp crew effects itemses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findByRequestCode(String requestCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempCrewEffectsItems> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPCREWEFFECTSITEMS_WHERE);

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

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(TempCrewEffectsItemsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempCrewEffectsItems>)queryFactory.getResultList(builder);
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
	 * Returns the first temp crew effects items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a matching temp crew effects items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems findByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempCrewEffectsItems != null) {
			return tempCrewEffectsItems;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsItemsException(msg.toString());
	}

	/**
	 * Returns the first temp crew effects items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp crew effects items, or <code>null</code> if a matching temp crew effects items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems fetchByRequestCode_First(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsItems> list = findByRequestCode(requestCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp crew effects items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a matching temp crew effects items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems findByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempCrewEffectsItems != null) {
			return tempCrewEffectsItems;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempCrewEffectsItemsException(msg.toString());
	}

	/**
	 * Returns the last temp crew effects items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp crew effects items, or <code>null</code> if a matching temp crew effects items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems fetchByRequestCode_Last(String requestCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempCrewEffectsItems> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp crew effects itemses before and after the current temp crew effects items in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp crew effects items
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp crew effects items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempCrewEffectsItemsException if a temp crew effects items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempCrewEffectsItems[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempCrewEffectsItemsException, SystemException {
		TempCrewEffectsItems tempCrewEffectsItems = findByPrimaryKey(id);

		

		try {
			

			TempCrewEffectsItems[] array = new TempCrewEffectsItems[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempCrewEffectsItems, requestCode, orderByComparator, true);

			array[1] = tempCrewEffectsItems;

			array[2] = getByRequestCode_PrevAndNext(
					tempCrewEffectsItems, requestCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempCrewEffectsItems getByRequestCode_PrevAndNext(
		 TempCrewEffectsItems tempCrewEffectsItems,
		String requestCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_TEMPCREWEFFECTSITEMS_WHERE);

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
			query.append(TempCrewEffectsItemsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempCrewEffectsItems);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempCrewEffectsItems> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp crew effects itemses.
	 *
	 * @return the temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp crew effects itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew effects itemses
	 * @param end the upper bound of the range of temp crew effects itemses (not inclusive)
	 * @return the range of temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp crew effects itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp crew effects itemses
	 * @param end the upper bound of the range of temp crew effects itemses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempCrewEffectsItems> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempCrewEffectsItems> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPCREWEFFECTSITEMS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPCREWEFFECTSITEMS.concat(TempCrewEffectsItemsModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempCrewEffectsItems>) queryFactory.getResultList(builder);
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
	 * Removes all the temp crew effects itemses where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempCrewEffectsItems tempCrewEffectsItems : findByRequestCode(
				requestCode)) {
			repository.delete(tempCrewEffectsItems);
		}
	}

	/**
	 * Removes all the temp crew effects itemses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempCrewEffectsItems tempCrewEffectsItems : findAll()) {
			repository.delete(tempCrewEffectsItems);
		}
	}

	/**
	 * Returns the number of temp crew effects itemses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPCREWEFFECTSITEMS_WHERE);

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
	 * Returns the number of temp crew effects itemses.
	 *
	 * @return the number of temp crew effects itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPCREWEFFECTSITEMS).build();

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
	 * Initializes the temp crew effects items persistence.
	 */
	private static final String _SQL_SELECT_TEMPCREWEFFECTSITEMS = "SELECT tempCrewEffectsItems FROM TempCrewEffectsItems tempCrewEffectsItems";
	private static final String _SQL_SELECT_TEMPCREWEFFECTSITEMS_WHERE = "SELECT tempCrewEffectsItems FROM TempCrewEffectsItems tempCrewEffectsItems WHERE ";
	private static final String _SQL_COUNT_TEMPCREWEFFECTSITEMS = "SELECT COUNT(tempCrewEffectsItems) FROM TempCrewEffectsItems tempCrewEffectsItems";
	private static final String _SQL_COUNT_TEMPCREWEFFECTSITEMS_WHERE = "SELECT COUNT(tempCrewEffectsItems) FROM TempCrewEffectsItems tempCrewEffectsItems WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempCrewEffectsItems.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempCrewEffectsItems.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempCrewEffectsItems.requestCode IS NULL OR tempCrewEffectsItems.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempCrewEffectsItems.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempCrewEffectsItems exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempCrewEffectsItems exists with the key {";
	

	
}
