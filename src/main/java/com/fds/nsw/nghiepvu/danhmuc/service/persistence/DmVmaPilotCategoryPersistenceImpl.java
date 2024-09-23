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
import com.fds.nsw.nghiepvu.model.DmVmaPilotCategory;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaPilotCategoryRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaPilotCategoryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaPilotCategoryPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaPilotCategoryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaPilotCategory> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaPilotCategoryUtil} to access the dm vma pilot category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaPilotCategory create(long id) {
		DmVmaPilotCategory dmVmaPilotCategory = new DmVmaPilotCategory();

		
		//dmVmaPilotCategory.setPrimaryKey(id);

		return dmVmaPilotCategory;
	}

	/**
	 * Removes the dm vma pilot category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma pilot category
	 * @return the dm vma pilot category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory remove(long Id)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma pilot category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma pilot category
	 * @return the dm vma pilot category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilotCategory remove(Serializable primaryKey)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		

		try {
			

			DmVmaPilotCategory dmVmaPilotCategory = findByPrimaryKey(primaryKey);

			if (dmVmaPilotCategory == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaPilotCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaPilotCategory);
			return dmVmaPilotCategory;
		}
		catch (NoSuchDmVmaPilotCategoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaPilotCategory remove(DmVmaPilotCategory DmVmaPilotCategory) throws SystemException {
	removeImpl(DmVmaPilotCategory);	return DmVmaPilotCategory;
}

protected DmVmaPilotCategory removeImpl

(
		DmVmaPilotCategory dmVmaPilotCategory) throws SystemException {
		try {
			repository.delete(dmVmaPilotCategory);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilotCategory;
	}

	
	public DmVmaPilotCategory updateImpl(
		DmVmaPilotCategory dmVmaPilotCategory,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmVmaPilotCategory);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilotCategory;
	}

	
	public DmVmaPilotCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot category with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot category
	 * @return the dm vma pilot category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory findByPrimaryKey(long Id)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = fetchByPrimaryKey(Id);

		if (dmVmaPilotCategory == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaPilotCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaPilotCategory;
	}

	/**
	 * Returns the dm vma pilot category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma pilot category
	 * @return the dm vma pilot category, or <code>null</code> if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilotCategory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot category
	 * @return the dm vma pilot category, or <code>null</code> if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory fetchByPrimaryKey(long Id)
		throws SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = null;

		

		if (dmVmaPilotCategory == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaPilotCategory> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaPilotCategory = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaPilotCategory;
	}

	/**
	 * Returns the dm vma pilot category where PilotCategoryCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException} if it could not be found.
	 *
	 * @param PilotCategoryCode the pilot category code
	 * @return the matching dm vma pilot category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory findByF_pilotCategoryCode(
		String PilotCategoryCode)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = fetchByF_pilotCategoryCode(PilotCategoryCode);

		if (dmVmaPilotCategory == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCategoryCode=");
			msg.append(PilotCategoryCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaPilotCategoryException(msg.toString());
		}

		return dmVmaPilotCategory;
	}

	/**
	 * Returns the dm vma pilot category where PilotCategoryCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCategoryCode the pilot category code
	 * @return the matching dm vma pilot category, or <code>null</code> if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory fetchByF_pilotCategoryCode(
		String PilotCategoryCode) throws SystemException {
		return fetchByF_pilotCategoryCode(PilotCategoryCode, true);
	}

	/**
	 * Returns the dm vma pilot category where PilotCategoryCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCategoryCode the pilot category code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma pilot category, or <code>null</code> if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory fetchByF_pilotCategoryCode(
		String PilotCategoryCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = null;
		if (dmVmaPilotCategory == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMAPILOTCATEGORY_WHERE);

			if (PilotCategoryCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_1);
			}
			else {
				if (PilotCategoryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_2);
				}
			}

			query.append(DmVmaPilotCategoryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaPilotCategory.class).build();

				

				if (PilotCategoryCode != null) {
					builder.appendNamedParameterMap("PilotCategoryCode", PilotCategoryCode);
				}

				dmVmaPilotCategory = (DmVmaPilotCategory) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaPilotCategory;
	}

	/**
	 * Returns all the dm vma pilot categories where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @return the matching dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findByF_pilotCategoryName(
		String PilotCategoryName) throws SystemException {
		return findByF_pilotCategoryName(PilotCategoryName, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilot categories where PilotCategoryName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param start the lower bound of the range of dm vma pilot categories
	 * @param end the upper bound of the range of dm vma pilot categories (not inclusive)
	 * @return the range of matching dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findByF_pilotCategoryName(
		String PilotCategoryName, int start, int end) throws SystemException {
		return findByF_pilotCategoryName(PilotCategoryName, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilot categories where PilotCategoryName LIKE &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param start the lower bound of the range of dm vma pilot categories
	 * @param end the upper bound of the range of dm vma pilot categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findByF_pilotCategoryName(
		String PilotCategoryName, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilotCategory> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMAPILOTCATEGORY_WHERE);

			if (PilotCategoryName == null) {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_1);
			}
			else {
				if (PilotCategoryName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmVmaPilotCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (PilotCategoryName != null) {
					builder.appendNamedParameterMap("PilotCategoryName", PilotCategoryName);
				}

				list = (List<DmVmaPilotCategory>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma pilot category in the ordered set where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory findByF_pilotCategoryName_First(
		String PilotCategoryName, OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = fetchByF_pilotCategoryName_First(PilotCategoryName,
				orderByComparator);

		if (dmVmaPilotCategory != null) {
			return dmVmaPilotCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("PilotCategoryName=");
		msg.append(PilotCategoryName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotCategoryException(msg.toString());
	}

	/**
	 * Returns the first dm vma pilot category in the ordered set where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot category, or <code>null</code> if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory fetchByF_pilotCategoryName_First(
		String PilotCategoryName, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaPilotCategory> list = findByF_pilotCategoryName(PilotCategoryName,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma pilot category in the ordered set where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory findByF_pilotCategoryName_Last(
		String PilotCategoryName, OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = fetchByF_pilotCategoryName_Last(PilotCategoryName,
				orderByComparator);

		if (dmVmaPilotCategory != null) {
			return dmVmaPilotCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("PilotCategoryName=");
		msg.append(PilotCategoryName);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotCategoryException(msg.toString());
	}

	/**
	 * Returns the last dm vma pilot category in the ordered set where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot category, or <code>null</code> if a matching dm vma pilot category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory fetchByF_pilotCategoryName_Last(
		String PilotCategoryName, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_pilotCategoryName(PilotCategoryName);

		List<DmVmaPilotCategory> list = findByF_pilotCategoryName(PilotCategoryName,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma pilot categories before and after the current dm vma pilot category in the ordered set where PilotCategoryName LIKE &#63;.
	 *
	 * @param Id the primary key of the current dm vma pilot category
	 * @param PilotCategoryName the pilot category name
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma pilot category
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCategoryException if a dm vma pilot category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory[] findByF_pilotCategoryName_PrevAndNext(long Id,
		String PilotCategoryName, OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = findByPrimaryKey(Id);

		

		try {
			

			DmVmaPilotCategory[] array = new DmVmaPilotCategory[3];

			array[0] = getByF_pilotCategoryName_PrevAndNext(
					dmVmaPilotCategory, PilotCategoryName, orderByComparator,
					true);

			array[1] = dmVmaPilotCategory;

			array[2] = getByF_pilotCategoryName_PrevAndNext(
					dmVmaPilotCategory, PilotCategoryName, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaPilotCategory getByF_pilotCategoryName_PrevAndNext(
		 DmVmaPilotCategory dmVmaPilotCategory,
		String PilotCategoryName, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMAPILOTCATEGORY_WHERE);

		if (PilotCategoryName == null) {
			query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_1);
		}
		else {
			if (PilotCategoryName.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_2);
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
			query.append(DmVmaPilotCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (PilotCategoryName != null) {
			builder.appendNamedParameterMap("PilotCategoryName", PilotCategoryName);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaPilotCategory);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaPilotCategory> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm vma pilot categories.
	 *
	 * @return the dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilot categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilot categories
	 * @param end the upper bound of the range of dm vma pilot categories (not inclusive)
	 * @return the range of dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilot categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilot categories
	 * @param end the upper bound of the range of dm vma pilot categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCategory> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilotCategory> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMAPILOTCATEGORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMAPILOTCATEGORY.concat(DmVmaPilotCategoryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaPilotCategory>) queryFactory.getResultList(builder);
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
	 * Removes the dm vma pilot category where PilotCategoryCode = &#63; from the database.
	 *
	 * @param PilotCategoryCode the pilot category code
	 * @return the dm vma pilot category that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCategory removeByF_pilotCategoryCode(
		String PilotCategoryCode)
		throws NoSuchDmVmaPilotCategoryException, SystemException {
		DmVmaPilotCategory dmVmaPilotCategory = findByF_pilotCategoryCode(PilotCategoryCode);

		repository.delete(dmVmaPilotCategory);
			return dmVmaPilotCategory;
	}

	/**
	 * Removes all the dm vma pilot categories where PilotCategoryName LIKE &#63; from the database.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_pilotCategoryName(String PilotCategoryName)
		throws SystemException {
		for (DmVmaPilotCategory dmVmaPilotCategory : findByF_pilotCategoryName(
				PilotCategoryName)) {
			repository.delete(dmVmaPilotCategory);
		}
	}

	/**
	 * Removes all the dm vma pilot categories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaPilotCategory dmVmaPilotCategory : findAll()) {
			repository.delete(dmVmaPilotCategory);
		}
	}

	/**
	 * Returns the number of dm vma pilot categories where PilotCategoryCode = &#63;.
	 *
	 * @param PilotCategoryCode the pilot category code
	 * @return the number of matching dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCategoryCode(String PilotCategoryCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOTCATEGORY_WHERE);

			if (PilotCategoryCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_1);
			}
			else {
				if (PilotCategoryCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCategoryCode != null) {
					builder.appendNamedParameterMap("PilotCategoryCode", PilotCategoryCode);
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
	 * Returns the number of dm vma pilot categories where PilotCategoryName LIKE &#63;.
	 *
	 * @param PilotCategoryName the pilot category name
	 * @return the number of matching dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCategoryName(String PilotCategoryName)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOTCATEGORY_WHERE);

			if (PilotCategoryName == null) {
				query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_1);
			}
			else {
				if (PilotCategoryName.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCategoryName != null) {
					builder.appendNamedParameterMap("PilotCategoryName", PilotCategoryName);
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
	 * Returns the number of dm vma pilot categories.
	 *
	 * @return the number of dm vma pilot categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMAPILOTCATEGORY).build();

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
	 * Initializes the dm vma pilot category persistence.
	 */
	private static final String _SQL_SELECT_DMVMAPILOTCATEGORY = "SELECT dmVmaPilotCategory FROM DmVmaPilotCategory dmVmaPilotCategory";
	private static final String _SQL_SELECT_DMVMAPILOTCATEGORY_WHERE = "SELECT dmVmaPilotCategory FROM DmVmaPilotCategory dmVmaPilotCategory WHERE ";
	private static final String _SQL_COUNT_DMVMAPILOTCATEGORY = "SELECT COUNT(dmVmaPilotCategory) FROM DmVmaPilotCategory dmVmaPilotCategory";
	private static final String _SQL_COUNT_DMVMAPILOTCATEGORY_WHERE = "SELECT COUNT(dmVmaPilotCategory) FROM DmVmaPilotCategory dmVmaPilotCategory WHERE ";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_1 =
		"dmVmaPilotCategory.PilotCategoryCode IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_2 =
		"dmVmaPilotCategory.PilotCategoryCode =:PilotCategoryCode";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYCODE_PILOTCATEGORYCODE_3 =
		"(dmVmaPilotCategory.PilotCategoryCode IS NULL OR dmVmaPilotCategory.PilotCategoryCode =:PilotCategoryCode)";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_1 =
		"dmVmaPilotCategory.PilotCategoryName LIKE NULL";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_2 =
		"dmVmaPilotCategory.PilotCategoryName LIKE :PilotCategoryName";
	private static final String _FINDER_COLUMN_F_PILOTCATEGORYNAME_PILOTCATEGORYNAME_3 =
		"(dmVmaPilotCategory.PilotCategoryName IS NULL OR dmVmaPilotCategory.PilotCategoryName LIKE :PilotCategoryName)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaPilotCategory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaPilotCategory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaPilotCategory exists with the key {";
	

	
}
