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
import com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.TempShipSecurityPortItemRepository;
import com.fds.nsw.nghiepvu.modelImpl.TempShipSecurityPortItemsModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TempShipSecurityPortItemsPersistenceImpl extends BasePersistence {
	@Autowired
	TempShipSecurityPortItemRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<TempShipSecurityPortItems> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link TempShipSecurityPortItemsUtil} to access the temp ship security port items persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public TempShipSecurityPortItems create(long id) {
		TempShipSecurityPortItems tempShipSecurityPortItems = new TempShipSecurityPortItems();

		
		//tempShipSecurityPortItems.setPrimaryKey(id);

		return tempShipSecurityPortItems;
	}

	/**
	 * Removes the temp ship security port items with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the temp ship security port items
	 * @return the temp ship security port items that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems remove(long id)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the temp ship security port items with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the temp ship security port items
	 * @return the temp ship security port items that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempShipSecurityPortItems remove(Serializable primaryKey)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		

		try {
			

			TempShipSecurityPortItems tempShipSecurityPortItems = findByPrimaryKey(primaryKey);

			if (tempShipSecurityPortItems == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchTempShipSecurityPortItemsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(tempShipSecurityPortItems);
			return tempShipSecurityPortItems;
		}
		catch (NoSuchTempShipSecurityPortItemsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public TempShipSecurityPortItems remove(TempShipSecurityPortItems TempShipSecurityPortItems) throws SystemException {
	removeImpl(TempShipSecurityPortItems);
	return TempShipSecurityPortItems;
}

protected TempShipSecurityPortItems removeImpl(
		TempShipSecurityPortItems tempShipSecurityPortItems)
		throws SystemException {
		try {
			repository.delete(tempShipSecurityPortItems);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempShipSecurityPortItems;
	}

	
	public TempShipSecurityPortItems updateImpl(
		com.fds.nsw.nghiepvu.model.TempShipSecurityPortItems tempShipSecurityPortItems,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(tempShipSecurityPortItems);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return tempShipSecurityPortItems;
	}

	
	public TempShipSecurityPortItems findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp ship security port items with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException} if it could not be found.
	 *
	 * @param id the primary key of the temp ship security port items
	 * @return the temp ship security port items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems findByPrimaryKey(long id)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = fetchByPrimaryKey(id);

		if (tempShipSecurityPortItems == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchTempShipSecurityPortItemsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return tempShipSecurityPortItems;
	}

	/**
	 * Returns the temp ship security port items with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the temp ship security port items
	 * @return the temp ship security port items, or <code>null</code> if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public TempShipSecurityPortItems fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the temp ship security port items with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the temp ship security port items
	 * @return the temp ship security port items, or <code>null</code> if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems fetchByPrimaryKey(long id)
		throws SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = null;

		

		if (tempShipSecurityPortItems == null) {
			

			boolean hasException = false;

			try {
				

				Optional<TempShipSecurityPortItems> optional = repository.findById(id);
				if (optional.isPresent()) {
					tempShipSecurityPortItems = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return tempShipSecurityPortItems;
	}

	/**
	 * Returns all the temp ship security port itemses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the matching temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findByRequestCode(String requestCode)
		throws SystemException {
		return findByRequestCode(requestCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp ship security port itemses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp ship security port itemses
	 * @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	 * @return the range of matching temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findByRequestCode(
		String requestCode, int start, int end) throws SystemException {
		return findByRequestCode(requestCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp ship security port itemses where requestCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param requestCode the request code
	 * @param start the lower bound of the range of temp ship security port itemses
	 * @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findByRequestCode(
		String requestCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempShipSecurityPortItems> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_TEMPSHIPSECURITYPORTITEMS_WHERE);

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
				query.append(TempShipSecurityPortItemsModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (requestCode != null) {
					builder.appendNamedParameterMap("requestCode", requestCode);
				}

				list = (List<TempShipSecurityPortItems>)queryFactory.getResultList(builder);
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
	 * Returns the first temp ship security port items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp ship security port items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a matching temp ship security port items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems findByRequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = fetchByRequestCode_First(requestCode,
				orderByComparator);

		if (tempShipSecurityPortItems != null) {
			return tempShipSecurityPortItems;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempShipSecurityPortItemsException(msg.toString());
	}

	/**
	 * Returns the first temp ship security port items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching temp ship security port items, or <code>null</code> if a matching temp ship security port items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems fetchByRequestCode_First(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<TempShipSecurityPortItems> list = findByRequestCode(requestCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last temp ship security port items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp ship security port items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a matching temp ship security port items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems findByRequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = fetchByRequestCode_Last(requestCode,
				orderByComparator);

		if (tempShipSecurityPortItems != null) {
			return tempShipSecurityPortItems;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("requestCode=");
		msg.append(requestCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchTempShipSecurityPortItemsException(msg.toString());
	}

	/**
	 * Returns the last temp ship security port items in the ordered set where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching temp ship security port items, or <code>null</code> if a matching temp ship security port items could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems fetchByRequestCode_Last(
		String requestCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByRequestCode(requestCode);

		List<TempShipSecurityPortItems> list = findByRequestCode(requestCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the temp ship security port itemses before and after the current temp ship security port items in the ordered set where requestCode = &#63;.
	 *
	 * @param id the primary key of the current temp ship security port items
	 * @param requestCode the request code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next temp ship security port items
	 * @throws vn.gt.dao.noticeandmessage.NoSuchTempShipSecurityPortItemsException if a temp ship security port items with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public TempShipSecurityPortItems[] findByRequestCode_PrevAndNext(long id,
		String requestCode, OrderByComparator orderByComparator)
		throws NoSuchTempShipSecurityPortItemsException, SystemException {
		TempShipSecurityPortItems tempShipSecurityPortItems = findByPrimaryKey(id);

		

		try {
			

			TempShipSecurityPortItems[] array = new TempShipSecurityPortItems[3];

			array[0] = getByRequestCode_PrevAndNext(
					tempShipSecurityPortItems, requestCode, orderByComparator,
					true);

			array[1] = tempShipSecurityPortItems;

			array[2] = getByRequestCode_PrevAndNext(
					tempShipSecurityPortItems, requestCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected TempShipSecurityPortItems getByRequestCode_PrevAndNext(
		 TempShipSecurityPortItems tempShipSecurityPortItems,
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

		query.append(_SQL_SELECT_TEMPSHIPSECURITYPORTITEMS_WHERE);

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
			query.append(TempShipSecurityPortItemsModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (requestCode != null) {
			builder.appendNamedParameterMap("requestCode", requestCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(tempShipSecurityPortItems);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<TempShipSecurityPortItems> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the temp ship security port itemses.
	 *
	 * @return the temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the temp ship security port itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp ship security port itemses
	 * @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	 * @return the range of temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the temp ship security port itemses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of temp ship security port itemses
	 * @param end the upper bound of the range of temp ship security port itemses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public List<TempShipSecurityPortItems> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<TempShipSecurityPortItems> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_TEMPSHIPSECURITYPORTITEMS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_TEMPSHIPSECURITYPORTITEMS.concat(TempShipSecurityPortItemsModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<TempShipSecurityPortItems>) queryFactory.getResultList(builder);
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
	 * Removes all the temp ship security port itemses where requestCode = &#63; from the database.
	 *
	 * @param requestCode the request code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByRequestCode(String requestCode)
		throws SystemException {
		for (TempShipSecurityPortItems tempShipSecurityPortItems : findByRequestCode(
				requestCode)) {
			repository.delete(tempShipSecurityPortItems);
		}
	}

	/**
	 * Removes all the temp ship security port itemses from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (TempShipSecurityPortItems tempShipSecurityPortItems : findAll()) {
			repository.delete(tempShipSecurityPortItems);
		}
	}

	/**
	 * Returns the number of temp ship security port itemses where requestCode = &#63;.
	 *
	 * @param requestCode the request code
	 * @return the number of matching temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int countByRequestCode(String requestCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_TEMPSHIPSECURITYPORTITEMS_WHERE);

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
	 * Returns the number of temp ship security port itemses.
	 *
	 * @return the number of temp ship security port itemses
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_TEMPSHIPSECURITYPORTITEMS).build();

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
	 * Initializes the temp ship security port items persistence.
	 */
	private static final String _SQL_SELECT_TEMPSHIPSECURITYPORTITEMS = "SELECT tempShipSecurityPortItems FROM TempShipSecurityPortItems tempShipSecurityPortItems";
	private static final String _SQL_SELECT_TEMPSHIPSECURITYPORTITEMS_WHERE = "SELECT tempShipSecurityPortItems FROM TempShipSecurityPortItems tempShipSecurityPortItems WHERE ";
	private static final String _SQL_COUNT_TEMPSHIPSECURITYPORTITEMS = "SELECT COUNT(tempShipSecurityPortItems) FROM TempShipSecurityPortItems tempShipSecurityPortItems";
	private static final String _SQL_COUNT_TEMPSHIPSECURITYPORTITEMS_WHERE = "SELECT COUNT(tempShipSecurityPortItems) FROM TempShipSecurityPortItems tempShipSecurityPortItems WHERE ";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_1 = "tempShipSecurityPortItems.requestCode IS NULL";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_2 = "tempShipSecurityPortItems.requestCode =:requestCode";
	private static final String _FINDER_COLUMN_REQUESTCODE_REQUESTCODE_3 = "(tempShipSecurityPortItems.requestCode IS NULL OR tempShipSecurityPortItems.requestCode =:requestCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "tempShipSecurityPortItems.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No TempShipSecurityPortItems exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No TempShipSecurityPortItems exists with the key {";
	

	
}
