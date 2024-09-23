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
import com.fds.nsw.nghiepvu.model.VmaReportCategory;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaReportCategoryRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaReportCategoryModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaReportCategoryPersistenceImpl extends BasePersistence {
	@Autowired
	VmaReportCategoryRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaReportCategory> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaReportCategoryUtil} to access the vma report category persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaReportCategory create(long id) {
		VmaReportCategory vmaReportCategory = new VmaReportCategory();

		
		//vmaReportCategory.setPrimaryKey(id);

		return vmaReportCategory;
	}

	/**
	 * Removes the vma report category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma report category
	 * @return the vma report category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException if a vma report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory remove(long id)
		throws NoSuchVmaReportCategoryException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma report category with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma report category
	 * @return the vma report category that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException if a vma report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaReportCategory remove(Serializable primaryKey)
		throws NoSuchVmaReportCategoryException, SystemException {
		

		try {
			

			VmaReportCategory vmaReportCategory = findByPrimaryKey(primaryKey);

			if (vmaReportCategory == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaReportCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaReportCategory);
			return vmaReportCategory;
		}
		catch (NoSuchVmaReportCategoryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaReportCategory remove(VmaReportCategory VmaReportCategory) throws SystemException {
	removeImpl(VmaReportCategory);	return VmaReportCategory;
}

protected VmaReportCategory removeImpl

(VmaReportCategory vmaReportCategory)
		throws SystemException {
		try {
			repository.delete(vmaReportCategory);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaReportCategory;
	}

	
	public VmaReportCategory updateImpl(
		VmaReportCategory vmaReportCategory,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaReportCategory);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaReportCategory;
	}

	
	public VmaReportCategory findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma report category with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException} if it could not be found.
	 *
	 * @param id the primary key of the vma report category
	 * @return the vma report category
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException if a vma report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory findByPrimaryKey(long id)
		throws NoSuchVmaReportCategoryException, SystemException {
		VmaReportCategory vmaReportCategory = fetchByPrimaryKey(id);

		if (vmaReportCategory == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaReportCategoryException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaReportCategory;
	}

	/**
	 * Returns the vma report category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma report category
	 * @return the vma report category, or <code>null</code> if a vma report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaReportCategory fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma report category with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma report category
	 * @return the vma report category, or <code>null</code> if a vma report category with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory fetchByPrimaryKey(long id)
		throws SystemException {
		VmaReportCategory vmaReportCategory = null;

		

		if (vmaReportCategory == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaReportCategory> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaReportCategory = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaReportCategory;
	}

	/**
	 * Returns the vma report category where rptCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException} if it could not be found.
	 *
	 * @param rptCode the rpt code
	 * @return the matching vma report category
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaReportCategoryException if a matching vma report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory findByF_rptCode(String rptCode)
		throws NoSuchVmaReportCategoryException, SystemException {
		VmaReportCategory vmaReportCategory = fetchByF_rptCode(rptCode);

		if (vmaReportCategory == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("rptCode=");
			msg.append(rptCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaReportCategoryException(msg.toString());
		}

		return vmaReportCategory;
	}

	/**
	 * Returns the vma report category where rptCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param rptCode the rpt code
	 * @return the matching vma report category, or <code>null</code> if a matching vma report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory fetchByF_rptCode(String rptCode)
		throws SystemException {
		return fetchByF_rptCode(rptCode, true);
	}

	/**
	 * Returns the vma report category where rptCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param rptCode the rpt code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma report category, or <code>null</code> if a matching vma report category could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory fetchByF_rptCode(String rptCode,
		boolean retrieveFromCache) throws SystemException {
		VmaReportCategory vmaReportCategory = null;
		if (vmaReportCategory == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMAREPORTCATEGORY_WHERE);

			if (rptCode == null) {
				query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_1);
			}
			else {
				if (rptCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_2);
				}
			}

			query.append(VmaReportCategoryModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaReportCategory.class).build();

				

				if (rptCode != null) {
					builder.appendNamedParameterMap("rptCode", rptCode);
				}

				vmaReportCategory = (VmaReportCategory) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaReportCategory;
	}

	/**
	 * Returns all the vma report categories.
	 *
	 * @return the vma report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaReportCategory> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma report categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma report categories
	 * @param end the upper bound of the range of vma report categories (not inclusive)
	 * @return the range of vma report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaReportCategory> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma report categories.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma report categories
	 * @param end the upper bound of the range of vma report categories (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma report categories
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaReportCategory> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaReportCategory> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAREPORTCATEGORY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAREPORTCATEGORY.concat(VmaReportCategoryModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaReportCategory>) queryFactory.getResultList(builder);
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
	 * Removes the vma report category where rptCode = &#63; from the database.
	 *
	 * @param rptCode the rpt code
	 * @return the vma report category that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaReportCategory removeByF_rptCode(String rptCode)
		throws NoSuchVmaReportCategoryException, SystemException {
		VmaReportCategory vmaReportCategory = findByF_rptCode(rptCode);

		repository.delete(vmaReportCategory);
			return vmaReportCategory;
	}

	/**
	 * Removes all the vma report categories from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaReportCategory vmaReportCategory : findAll()) {
			repository.delete(vmaReportCategory);
		}
	}

	/**
	 * Returns the number of vma report categories where rptCode = &#63;.
	 *
	 * @param rptCode the rpt code
	 * @return the number of matching vma report categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_rptCode(String rptCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAREPORTCATEGORY_WHERE);

			if (rptCode == null) {
				query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_1);
			}
			else {
				if (rptCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_RPTCODE_RPTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (rptCode != null) {
					builder.appendNamedParameterMap("rptCode", rptCode);
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
	 * Returns the number of vma report categories.
	 *
	 * @return the number of vma report categories
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAREPORTCATEGORY).build();

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
	 * Initializes the vma report category persistence.
	 */
	private static final String _SQL_SELECT_VMAREPORTCATEGORY = "SELECT vmaReportCategory FROM VmaReportCategory vmaReportCategory";
	private static final String _SQL_SELECT_VMAREPORTCATEGORY_WHERE = "SELECT vmaReportCategory FROM VmaReportCategory vmaReportCategory WHERE ";
	private static final String _SQL_COUNT_VMAREPORTCATEGORY = "SELECT COUNT(vmaReportCategory) FROM VmaReportCategory vmaReportCategory";
	private static final String _SQL_COUNT_VMAREPORTCATEGORY_WHERE = "SELECT COUNT(vmaReportCategory) FROM VmaReportCategory vmaReportCategory WHERE ";
	private static final String _FINDER_COLUMN_F_RPTCODE_RPTCODE_1 = "vmaReportCategory.rptCode IS NULL";
	private static final String _FINDER_COLUMN_F_RPTCODE_RPTCODE_2 = "vmaReportCategory.rptCode =:rptCode";
	private static final String _FINDER_COLUMN_F_RPTCODE_RPTCODE_3 = "(vmaReportCategory.rptCode IS NULL OR vmaReportCategory.rptCode =:rptCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaReportCategory.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaReportCategory exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaReportCategory exists with the key {";
	

	
}
