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

package com.fds.nsw.nghiepvu.danhmucgt.service.persistence;

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
import com.fds.nsw.nghiepvu.model.DmGtReportCategory;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtReportCategoryRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtReportCategoryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtReportCategoryPersistenceImpl extends BasePersistence {
	@Autowired
	DmGtReportCategoryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtReportCategory> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtReportCategoryUtil} to access the dm gt report category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtReportCategory create(long id) {
		DmGtReportCategory dmGtReportCategory = new DmGtReportCategory();

		
		//dmGtReportCategory.setPrimaryKey(id);

		return dmGtReportCategory;
	}

	/**
	 * Removes the dm gt report category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt report category
	 * @return the dm gt report category that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory remove(long id)
		throws NoSuchDmGtReportCategoryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm gt report category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt report category
	 * @return the dm gt report category that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtReportCategory remove(Serializable primaryKey)
		throws NoSuchDmGtReportCategoryException, SystemException {
		

		try {
			

			DmGtReportCategory dmGtReportCategory = findByPrimaryKey(primaryKey);

			if (dmGtReportCategory == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtReportCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtReportCategory);
			return dmGtReportCategory;
		}
		catch (NoSuchDmGtReportCategoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtReportCategory remove(DmGtReportCategory DmGtReportCategory) throws SystemException {
	removeImpl(DmGtReportCategory);
	return DmGtReportCategory;
}

protected DmGtReportCategory removeImpl(
		DmGtReportCategory dmGtReportCategory) throws SystemException {
		try {
			repository.delete(dmGtReportCategory);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtReportCategory;
	}

	
	public DmGtReportCategory updateImpl(
		DmGtReportCategory dmGtReportCategory,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGtReportCategory);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtReportCategory;
	}

	
	public DmGtReportCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt report category with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt report category
	 * @return the dm gt report category
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory findByPrimaryKey(long id)
		throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = fetchByPrimaryKey(id);

		if (dmGtReportCategory == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtReportCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtReportCategory;
	}

	/**
	 * Returns the dm gt report category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt report category
	 * @return the dm gt report category, or <code>null</code> if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtReportCategory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt report category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt report category
	 * @return the dm gt report category, or <code>null</code> if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtReportCategory dmGtReportCategory = null;

		

		if (dmGtReportCategory == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtReportCategory> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtReportCategory = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtReportCategory;
	}

	/**
	 * Returns the dm gt report category where category = &#63; and isDelete = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException} if it could not be found.
	 *
	 * @param category the category
	 * @param isDelete the is delete
	 * @return the matching dm gt report category
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory findByOrganizationCode(String category,
		int isDelete) throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = fetchByOrganizationCode(category,
				isDelete);

		if (dmGtReportCategory == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("category=");
			msg.append(category);

			msg.append(", isDelete=");
			msg.append(isDelete);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtReportCategoryException(msg.toString());
		}

		return dmGtReportCategory;
	}

	/**
	 * Returns the dm gt report category where category = &#63; and isDelete = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param category the category
	 * @param isDelete the is delete
	 * @return the matching dm gt report category, or <code>null</code> if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory fetchByOrganizationCode(String category,
		int isDelete) throws SystemException {
		return fetchByOrganizationCode(category, isDelete, true);
	}

	/**
	 * Returns the dm gt report category where category = &#63; and isDelete = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param category the category
	 * @param isDelete the is delete
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm gt report category, or <code>null</code> if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory fetchByOrganizationCode(String category,
		int isDelete, boolean retrieveFromCache) throws SystemException {
		DmGtReportCategory dmGtReportCategory = null;
		if (dmGtReportCategory == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMGTREPORTCATEGORY_WHERE);

			if (category == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_1);
			}
			else {
				if (category.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_2);
				}
			}

			query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ISDELETE_2);

			query.append(DmGtReportCategoryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtReportCategory.class).build();

				

				if (category != null) {
					builder.appendNamedParameterMap("category", category);
				}

				builder.appendNamedParameterMap("isDelete", isDelete);

				dmGtReportCategory = (DmGtReportCategory) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGtReportCategory;
	}

	/**
	 * Returns all the dm gt report categories where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @return the matching dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findByF_isDelete(int isDelete)
		throws SystemException {
		return findByF_isDelete(isDelete, QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			null);
	}

	/**
	 * Returns a range of all the dm gt report categories where isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt report categories
	 * @param end the upper bound of the range of dm gt report categories (not inclusive)
	 * @return the range of matching dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findByF_isDelete(int isDelete, int start,
		int end) throws SystemException {
		return findByF_isDelete(isDelete, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt report categories where isDelete = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param isDelete the is delete
	 * @param start the lower bound of the range of dm gt report categories
	 * @param end the upper bound of the range of dm gt report categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findByF_isDelete(int isDelete, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportCategory> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGTREPORTCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_F_ISDELETE_ISDELETE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtReportCategoryModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

				list = (List<DmGtReportCategory>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt report category in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt report category
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory findByF_isDelete_First(int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = fetchByF_isDelete_First(isDelete,
				orderByComparator);

		if (dmGtReportCategory != null) {
			return dmGtReportCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtReportCategoryException(msg.toString());
	}

	/**
	 * Returns the first dm gt report category in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt report category, or <code>null</code> if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory fetchByF_isDelete_First(int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportCategory> list = findByF_isDelete(isDelete, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt report category in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt report category
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory findByF_isDelete_Last(int isDelete,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = fetchByF_isDelete_Last(isDelete,
				orderByComparator);

		if (dmGtReportCategory != null) {
			return dmGtReportCategory;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("isDelete=");
		msg.append(isDelete);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtReportCategoryException(msg.toString());
	}

	/**
	 * Returns the last dm gt report category in the ordered set where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt report category, or <code>null</code> if a matching dm gt report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory fetchByF_isDelete_Last(int isDelete,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_isDelete(isDelete);

		List<DmGtReportCategory> list = findByF_isDelete(isDelete, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt report categories before and after the current dm gt report category in the ordered set where isDelete = &#63;.
	 *
	 * @param id the primary key of the current dm gt report category
	 * @param isDelete the is delete
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt report category
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportCategoryException if a dm gt report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory[] findByF_isDelete_PrevAndNext(long id,
		int isDelete, OrderByComparator orderByComparator)
		throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = findByPrimaryKey(id);

		

		try {
			

			DmGtReportCategory[] array = new DmGtReportCategory[3];

			array[0] = getByF_isDelete_PrevAndNext(dmGtReportCategory,
					isDelete, orderByComparator, true);

			array[1] = dmGtReportCategory;

			array[2] = getByF_isDelete_PrevAndNext(dmGtReportCategory,
					isDelete, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtReportCategory getByF_isDelete_PrevAndNext(
		DmGtReportCategory dmGtReportCategory, int isDelete,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTREPORTCATEGORY_WHERE);

		query.append(_FINDER_COLUMN_F_ISDELETE_ISDELETE_2);

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
			query.append(DmGtReportCategoryModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("isDelete", isDelete);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtReportCategory);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtReportCategory> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt report categories.
	 *
	 * @return the dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt report categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt report categories
	 * @param end the upper bound of the range of dm gt report categories (not inclusive)
	 * @return the range of dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt report categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt report categories
	 * @param end the upper bound of the range of dm gt report categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportCategory> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportCategory> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTREPORTCATEGORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTREPORTCATEGORY.concat(DmGtReportCategoryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtReportCategory>) queryFactory.getResultList(builder);
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
	 * Removes the dm gt report category where category = &#63; and isDelete = &#63; from the database.
	 *
	 * @param category the category
	 * @param isDelete the is delete
	 * @return the dm gt report category that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportCategory removeByOrganizationCode(String category,
		int isDelete) throws NoSuchDmGtReportCategoryException, SystemException {
		DmGtReportCategory dmGtReportCategory = findByOrganizationCode(category,
				isDelete);

		repository.delete(dmGtReportCategory);
			return dmGtReportCategory;
	}

	/**
	 * Removes all the dm gt report categories where isDelete = &#63; from the database.
	 *
	 * @param isDelete the is delete
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_isDelete(int isDelete) throws SystemException {
		for (DmGtReportCategory dmGtReportCategory : findByF_isDelete(isDelete)) {
			repository.delete(dmGtReportCategory);
		}
	}

	/**
	 * Removes all the dm gt report categories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtReportCategory dmGtReportCategory : findAll()) {
			repository.delete(dmGtReportCategory);
		}
	}

	/**
	 * Returns the number of dm gt report categories where category = &#63; and isDelete = &#63;.
	 *
	 * @param category the category
	 * @param isDelete the is delete
	 * @return the number of matching dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByOrganizationCode(String category, int isDelete)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMGTREPORTCATEGORY_WHERE);

			if (category == null) {
				query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_1);
			}
			else {
				if (category.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_3);
				}
				else {
					query.append(_FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_2);
				}
			}

			query.append(_FINDER_COLUMN_ORGANIZATIONCODE_ISDELETE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (category != null) {
					builder.appendNamedParameterMap("category", category);
				}

				builder.appendNamedParameterMap("isDelete", isDelete);

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
	 * Returns the number of dm gt report categories where isDelete = &#63;.
	 *
	 * @param isDelete the is delete
	 * @return the number of matching dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_isDelete(int isDelete) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTREPORTCATEGORY_WHERE);

			query.append(_FINDER_COLUMN_F_ISDELETE_ISDELETE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("isDelete", isDelete);

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
	 * Returns the number of dm gt report categories.
	 *
	 * @return the number of dm gt report categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTREPORTCATEGORY).build();

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
	 * Initializes the dm gt report category persistence.
	 */
	private static final String _SQL_SELECT_DMGTREPORTCATEGORY = "SELECT dmGtReportCategory FROM DmGtReportCategory dmGtReportCategory";
	private static final String _SQL_SELECT_DMGTREPORTCATEGORY_WHERE = "SELECT dmGtReportCategory FROM DmGtReportCategory dmGtReportCategory WHERE ";
	private static final String _SQL_COUNT_DMGTREPORTCATEGORY = "SELECT COUNT(dmGtReportCategory) FROM DmGtReportCategory dmGtReportCategory";
	private static final String _SQL_COUNT_DMGTREPORTCATEGORY_WHERE = "SELECT COUNT(dmGtReportCategory) FROM DmGtReportCategory dmGtReportCategory WHERE ";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_1 = "dmGtReportCategory.category IS NULL AND ";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_2 = "dmGtReportCategory.category =:category AND ";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_CATEGORY_3 = "(dmGtReportCategory.category IS NULL OR dmGtReportCategory.category =:category) AND ";
	private static final String _FINDER_COLUMN_ORGANIZATIONCODE_ISDELETE_2 = "dmGtReportCategory.isDelete =:isDelete";
	private static final String _FINDER_COLUMN_F_ISDELETE_ISDELETE_2 = "dmGtReportCategory.isDelete =:isDelete";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtReportCategory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtReportCategory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtReportCategory exists with the key {";
	

	
}
