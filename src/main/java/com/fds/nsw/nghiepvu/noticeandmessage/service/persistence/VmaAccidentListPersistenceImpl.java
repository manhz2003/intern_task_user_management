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
import com.fds.nsw.nghiepvu.model.VmaAccidentList;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaAccidentListRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaAccidentListModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaAccidentListPersistenceImpl extends BasePersistence {
	@Autowired
	VmaAccidentListRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaAccidentList> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaAccidentListUtil} to access the vma accident list persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaAccidentList create(long id) {
		VmaAccidentList vmaAccidentList = new VmaAccidentList();

		
		//vmaAccidentList.setPrimaryKey(id);

		return vmaAccidentList;
	}

	/**
	 * Removes the vma accident list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma accident list
	 * @return the vma accident list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList remove(long id)
		throws NoSuchVmaAccidentListException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma accident list with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma accident list
	 * @return the vma accident list that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAccidentList remove(Serializable primaryKey)
		throws NoSuchVmaAccidentListException, SystemException {
		

		try {
			

			VmaAccidentList vmaAccidentList = findByPrimaryKey(primaryKey);

			if (vmaAccidentList == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaAccidentListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaAccidentList);
			return vmaAccidentList;
		}
		catch (NoSuchVmaAccidentListException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaAccidentList remove(VmaAccidentList VmaAccidentList) throws SystemException {
	removeImpl(VmaAccidentList);
	return VmaAccidentList;
}

protected VmaAccidentList removeImpl(VmaAccidentList vmaAccidentList)
		throws SystemException {
		try {
			repository.delete(vmaAccidentList);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAccidentList;
	}

	
	public VmaAccidentList updateImpl(
		com.fds.nsw.nghiepvu.model.VmaAccidentList vmaAccidentList,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaAccidentList);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaAccidentList;
	}

	
	public VmaAccidentList findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma accident list with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException} if it could not be found.
	 *
	 * @param id the primary key of the vma accident list
	 * @return the vma accident list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList findByPrimaryKey(long id)
		throws NoSuchVmaAccidentListException, SystemException {
		VmaAccidentList vmaAccidentList = fetchByPrimaryKey(id);

		if (vmaAccidentList == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaAccidentListException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaAccidentList;
	}

	/**
	 * Returns the vma accident list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma accident list
	 * @return the vma accident list, or <code>null</code> if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaAccidentList fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma accident list with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma accident list
	 * @return the vma accident list, or <code>null</code> if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList fetchByPrimaryKey(long id) throws SystemException {
		VmaAccidentList vmaAccidentList = null;

		

		if (vmaAccidentList == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaAccidentList> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaAccidentList = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaAccidentList;
	}

	/**
	 * Returns all the vma accident lists where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the matching vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findByportofAuthority(String portofAuthority)
		throws SystemException {
		return findByportofAuthority(portofAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma accident lists where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma accident lists
	 * @param end the upper bound of the range of vma accident lists (not inclusive)
	 * @return the range of matching vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findByportofAuthority(String portofAuthority,
		int start, int end) throws SystemException {
		return findByportofAuthority(portofAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma accident lists where portofAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portofAuthority the portof authority
	 * @param start the lower bound of the range of vma accident lists
	 * @param end the upper bound of the range of vma accident lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findByportofAuthority(String portofAuthority,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaAccidentList> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMAACCIDENTLIST_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
				}

				list = (List<VmaAccidentList>)queryFactory.getResultList(builder);
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
	 * Returns the first vma accident list in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma accident list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a matching vma accident list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList findByportofAuthority_First(String portofAuthority,
		OrderByComparator orderByComparator)
		throws NoSuchVmaAccidentListException, SystemException {
		VmaAccidentList vmaAccidentList = fetchByportofAuthority_First(portofAuthority,
				orderByComparator);

		if (vmaAccidentList != null) {
			return vmaAccidentList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaAccidentListException(msg.toString());
	}

	/**
	 * Returns the first vma accident list in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma accident list, or <code>null</code> if a matching vma accident list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList fetchByportofAuthority_First(
		String portofAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaAccidentList> list = findByportofAuthority(portofAuthority, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma accident list in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma accident list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a matching vma accident list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList findByportofAuthority_Last(String portofAuthority,
		OrderByComparator orderByComparator)
		throws NoSuchVmaAccidentListException, SystemException {
		VmaAccidentList vmaAccidentList = fetchByportofAuthority_Last(portofAuthority,
				orderByComparator);

		if (vmaAccidentList != null) {
			return vmaAccidentList;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portofAuthority=");
		msg.append(portofAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaAccidentListException(msg.toString());
	}

	/**
	 * Returns the last vma accident list in the ordered set where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma accident list, or <code>null</code> if a matching vma accident list could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList fetchByportofAuthority_Last(String portofAuthority,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByportofAuthority(portofAuthority);

		List<VmaAccidentList> list = findByportofAuthority(portofAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma accident lists before and after the current vma accident list in the ordered set where portofAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma accident list
	 * @param portofAuthority the portof authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma accident list
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaAccidentListException if a vma accident list with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaAccidentList[] findByportofAuthority_PrevAndNext(long id,
		String portofAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaAccidentListException, SystemException {
		VmaAccidentList vmaAccidentList = findByPrimaryKey(id);

		

		try {
			

			VmaAccidentList[] array = new VmaAccidentList[3];

			array[0] = getByportofAuthority_PrevAndNext(
					vmaAccidentList, portofAuthority, orderByComparator, true);

			array[1] = vmaAccidentList;

			array[2] = getByportofAuthority_PrevAndNext(
					vmaAccidentList, portofAuthority, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaAccidentList getByportofAuthority_PrevAndNext(
		 VmaAccidentList vmaAccidentList,
		String portofAuthority, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAACCIDENTLIST_WHERE);

		if (portofAuthority == null) {
			query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
		}
		else {
			if (portofAuthority.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
			}
			else {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
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

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portofAuthority != null) {
			builder.appendNamedParameterMap("portofAuthority", portofAuthority);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaAccidentList);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaAccidentList> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma accident lists.
	 *
	 * @return the vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma accident lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma accident lists
	 * @param end the upper bound of the range of vma accident lists (not inclusive)
	 * @return the range of vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma accident lists.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma accident lists
	 * @param end the upper bound of the range of vma accident lists (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaAccidentList> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaAccidentList> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAACCIDENTLIST);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAACCIDENTLIST;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaAccidentList>) queryFactory.getResultList(builder);
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
	 * Removes all the vma accident lists where portofAuthority = &#63; from the database.
	 *
	 * @param portofAuthority the portof authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByportofAuthority(String portofAuthority)
		throws SystemException {
		for (VmaAccidentList vmaAccidentList : findByportofAuthority(
				portofAuthority)) {
			repository.delete(vmaAccidentList);
		}
	}

	/**
	 * Removes all the vma accident lists from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaAccidentList vmaAccidentList : findAll()) {
			repository.delete(vmaAccidentList);
		}
	}

	/**
	 * Returns the number of vma accident lists where portofAuthority = &#63;.
	 *
	 * @param portofAuthority the portof authority
	 * @return the number of matching vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countByportofAuthority(String portofAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAACCIDENTLIST_WHERE);

			if (portofAuthority == null) {
				query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portofAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portofAuthority != null) {
					builder.appendNamedParameterMap("portofAuthority", portofAuthority);
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
	 * Returns the number of vma accident lists.
	 *
	 * @return the number of vma accident lists
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAACCIDENTLIST).build();

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
	 * Initializes the vma accident list persistence.
	 */
	private static final String _SQL_SELECT_VMAACCIDENTLIST = "SELECT vmaAccidentList FROM VmaAccidentList vmaAccidentList";
	private static final String _SQL_SELECT_VMAACCIDENTLIST_WHERE = "SELECT vmaAccidentList FROM VmaAccidentList vmaAccidentList WHERE ";
	private static final String _SQL_COUNT_VMAACCIDENTLIST = "SELECT COUNT(vmaAccidentList) FROM VmaAccidentList vmaAccidentList";
	private static final String _SQL_COUNT_VMAACCIDENTLIST_WHERE = "SELECT COUNT(vmaAccidentList) FROM VmaAccidentList vmaAccidentList WHERE ";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaAccidentList.portofAuthority IS NULL";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaAccidentList.portofAuthority =:portofAuthority";
	private static final String _FINDER_COLUMN_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaAccidentList.portofAuthority IS NULL OR vmaAccidentList.portofAuthority =:portofAuthority)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaAccidentList.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaAccidentList exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaAccidentList exists with the key {";
	

	
}
