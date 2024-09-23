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
import com.fds.nsw.nghiepvu.model.VmaTugboatCondition;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTugboatConditionRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTugboatConditionModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTugboatConditionPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTugboatConditionRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTugboatCondition> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTugboatConditionUtil} to access the vma tugboat condition persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTugboatCondition create(long id) {
		VmaTugboatCondition vmaTugboatCondition = new VmaTugboatCondition();

		
		//vmaTugboatCondition.setPrimaryKey(id);

		return vmaTugboatCondition;
	}

	/**
	 * Removes the vma tugboat condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma tugboat condition
	 * @return the vma tugboat condition that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition remove(long id)
		throws NoSuchVmaTugboatConditionException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma tugboat condition with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma tugboat condition
	 * @return the vma tugboat condition that was removed
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTugboatCondition remove(Serializable primaryKey)
		throws NoSuchVmaTugboatConditionException, SystemException {
		

		try {
			

			VmaTugboatCondition vmaTugboatCondition = findByPrimaryKey(primaryKey);

			if (vmaTugboatCondition == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTugboatConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTugboatCondition);
			return vmaTugboatCondition;
		}
		catch (NoSuchVmaTugboatConditionException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTugboatCondition remove(VmaTugboatCondition VmaTugboatCondition) throws SystemException {
	removeImpl(VmaTugboatCondition);
	return VmaTugboatCondition;
}

protected VmaTugboatCondition removeImpl(
		VmaTugboatCondition vmaTugboatCondition) throws SystemException {
		try {
			repository.delete(vmaTugboatCondition);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTugboatCondition;
	}

	
	public VmaTugboatCondition updateImpl(
		com.fds.nsw.nghiepvu.model.VmaTugboatCondition vmaTugboatCondition,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTugboatCondition);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTugboatCondition;
	}

	
	public VmaTugboatCondition findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma tugboat condition with the primary key or throws a {@link vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException} if it could not be found.
	 *
	 * @param id the primary key of the vma tugboat condition
	 * @return the vma tugboat condition
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition findByPrimaryKey(long id)
		throws NoSuchVmaTugboatConditionException, SystemException {
		VmaTugboatCondition vmaTugboatCondition = fetchByPrimaryKey(id);

		if (vmaTugboatCondition == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTugboatConditionException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTugboatCondition;
	}

	/**
	 * Returns the vma tugboat condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma tugboat condition
	 * @return the vma tugboat condition, or <code>null</code> if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTugboatCondition fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma tugboat condition with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma tugboat condition
	 * @return the vma tugboat condition, or <code>null</code> if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTugboatCondition vmaTugboatCondition = null;

		

		if (vmaTugboatCondition == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTugboatCondition> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTugboatCondition = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTugboatCondition;
	}

	/**
	 * Returns all the vma tugboat conditions where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the matching vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findBymaritimeCode(String maritimeCode)
		throws SystemException {
		return findBymaritimeCode(maritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma tugboat conditions where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of vma tugboat conditions
	 * @param end the upper bound of the range of vma tugboat conditions (not inclusive)
	 * @return the range of matching vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findBymaritimeCode(String maritimeCode,
		int start, int end) throws SystemException {
		return findBymaritimeCode(maritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma tugboat conditions where maritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param maritimeCode the maritime code
	 * @param start the lower bound of the range of vma tugboat conditions
	 * @param end the upper bound of the range of vma tugboat conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findBymaritimeCode(String maritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTugboatCondition> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(2);
			}

			query.append(_SQL_SELECT_VMATUGBOATCONDITION_WHERE);

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

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (maritimeCode != null) {
					builder.appendNamedParameterMap("maritimeCode", maritimeCode);
				}

				list = (List<VmaTugboatCondition>)queryFactory.getResultList(builder);
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
	 * Returns the first vma tugboat condition in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma tugboat condition
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a matching vma tugboat condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition findBymaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTugboatConditionException, SystemException {
		VmaTugboatCondition vmaTugboatCondition = fetchBymaritimeCode_First(maritimeCode,
				orderByComparator);

		if (vmaTugboatCondition != null) {
			return vmaTugboatCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTugboatConditionException(msg.toString());
	}

	/**
	 * Returns the first vma tugboat condition in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma tugboat condition, or <code>null</code> if a matching vma tugboat condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition fetchBymaritimeCode_First(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTugboatCondition> list = findBymaritimeCode(maritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma tugboat condition in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma tugboat condition
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a matching vma tugboat condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition findBymaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaTugboatConditionException, SystemException {
		VmaTugboatCondition vmaTugboatCondition = fetchBymaritimeCode_Last(maritimeCode,
				orderByComparator);

		if (vmaTugboatCondition != null) {
			return vmaTugboatCondition;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("maritimeCode=");
		msg.append(maritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTugboatConditionException(msg.toString());
	}

	/**
	 * Returns the last vma tugboat condition in the ordered set where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma tugboat condition, or <code>null</code> if a matching vma tugboat condition could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition fetchBymaritimeCode_Last(String maritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBymaritimeCode(maritimeCode);

		List<VmaTugboatCondition> list = findBymaritimeCode(maritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma tugboat conditions before and after the current vma tugboat condition in the ordered set where maritimeCode = &#63;.
	 *
	 * @param id the primary key of the current vma tugboat condition
	 * @param maritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma tugboat condition
	 * @throws vn.gt.dao.noticeandmessage.NoSuchVmaTugboatConditionException if a vma tugboat condition with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTugboatCondition[] findBymaritimeCode_PrevAndNext(long id,
		String maritimeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaTugboatConditionException, SystemException {
		VmaTugboatCondition vmaTugboatCondition = findByPrimaryKey(id);

		

		try {
			

			VmaTugboatCondition[] array = new VmaTugboatCondition[3];

			array[0] = getBymaritimeCode_PrevAndNext(
					vmaTugboatCondition, maritimeCode, orderByComparator, true);

			array[1] = vmaTugboatCondition;

			array[2] = getBymaritimeCode_PrevAndNext(
					vmaTugboatCondition, maritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTugboatCondition getBymaritimeCode_PrevAndNext(
		 VmaTugboatCondition vmaTugboatCondition,
		String maritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATUGBOATCONDITION_WHERE);

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

		

		if (maritimeCode != null) {
			builder.appendNamedParameterMap("maritimeCode", maritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTugboatCondition);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTugboatCondition> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma tugboat conditions.
	 *
	 * @return the vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma tugboat conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma tugboat conditions
	 * @param end the upper bound of the range of vma tugboat conditions (not inclusive)
	 * @return the range of vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma tugboat conditions.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma tugboat conditions
	 * @param end the upper bound of the range of vma tugboat conditions (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTugboatCondition> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTugboatCondition> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATUGBOATCONDITION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATUGBOATCONDITION;
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTugboatCondition>) queryFactory.getResultList(builder);
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
	 * Removes all the vma tugboat conditions where maritimeCode = &#63; from the database.
	 *
	 * @param maritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBymaritimeCode(String maritimeCode)
		throws SystemException {
		for (VmaTugboatCondition vmaTugboatCondition : findBymaritimeCode(
				maritimeCode)) {
			repository.delete(vmaTugboatCondition);
		}
	}

	/**
	 * Removes all the vma tugboat conditions from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTugboatCondition vmaTugboatCondition : findAll()) {
			repository.delete(vmaTugboatCondition);
		}
	}

	/**
	 * Returns the number of vma tugboat conditions where maritimeCode = &#63;.
	 *
	 * @param maritimeCode the maritime code
	 * @return the number of matching vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public int countBymaritimeCode(String maritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATUGBOATCONDITION_WHERE);

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
	 * Returns the number of vma tugboat conditions.
	 *
	 * @return the number of vma tugboat conditions
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATUGBOATCONDITION).build();

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
	 * Initializes the vma tugboat condition persistence.
	 */
	private static final String _SQL_SELECT_VMATUGBOATCONDITION = "SELECT vmaTugboatCondition FROM VmaTugboatCondition vmaTugboatCondition";
	private static final String _SQL_SELECT_VMATUGBOATCONDITION_WHERE = "SELECT vmaTugboatCondition FROM VmaTugboatCondition vmaTugboatCondition WHERE ";
	private static final String _SQL_COUNT_VMATUGBOATCONDITION = "SELECT COUNT(vmaTugboatCondition) FROM VmaTugboatCondition vmaTugboatCondition";
	private static final String _SQL_COUNT_VMATUGBOATCONDITION_WHERE = "SELECT COUNT(vmaTugboatCondition) FROM VmaTugboatCondition vmaTugboatCondition WHERE ";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_1 = "vmaTugboatCondition.maritimeCode IS NULL";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_2 = "vmaTugboatCondition.maritimeCode =:maritimeCode";
	private static final String _FINDER_COLUMN_MARITIMECODE_MARITIMECODE_3 = "(vmaTugboatCondition.maritimeCode IS NULL OR vmaTugboatCondition.maritimeCode =:maritimeCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTugboatCondition.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTugboatCondition exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTugboatCondition exists with the key {";
	

	
}
