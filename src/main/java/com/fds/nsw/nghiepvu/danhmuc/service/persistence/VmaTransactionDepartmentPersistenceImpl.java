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
import com.fds.nsw.nghiepvu.model.VmaTransactionDepartment;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaTransactionDepartmentRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaTransactionDepartmentModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaTransactionDepartmentPersistenceImpl extends BasePersistence {
	@Autowired
	VmaTransactionDepartmentRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaTransactionDepartment> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaTransactionDepartmentUtil} to access the vma transaction department persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaTransactionDepartment create(long id) {
		VmaTransactionDepartment vmaTransactionDepartment = new VmaTransactionDepartment();

		
		//vmaTransactionDepartment.setPrimaryKey(id);

		return vmaTransactionDepartment;
	}

	/**
	 * Removes the vma transaction department with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma transaction department
	 * @return the vma transaction department that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment remove(long id)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma transaction department with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma transaction department
	 * @return the vma transaction department that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionDepartment remove(Serializable primaryKey)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		

		try {
			

			VmaTransactionDepartment vmaTransactionDepartment = findByPrimaryKey(primaryKey);

			if (vmaTransactionDepartment == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaTransactionDepartmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaTransactionDepartment);
			return vmaTransactionDepartment;
		}
		catch (NoSuchVmaTransactionDepartmentException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaTransactionDepartment remove(VmaTransactionDepartment VmaTransactionDepartment) throws SystemException {
	removeImpl(VmaTransactionDepartment);	return VmaTransactionDepartment;
}

protected VmaTransactionDepartment removeImpl

(
		VmaTransactionDepartment vmaTransactionDepartment)
		throws SystemException {
		try {
			repository.delete(vmaTransactionDepartment);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionDepartment;
	}

	
	public VmaTransactionDepartment updateImpl(
		VmaTransactionDepartment vmaTransactionDepartment,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaTransactionDepartment);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaTransactionDepartment;
	}

	
	public VmaTransactionDepartment findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction department with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException} if it could not be found.
	 *
	 * @param id the primary key of the vma transaction department
	 * @return the vma transaction department
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment findByPrimaryKey(long id)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = fetchByPrimaryKey(id);

		if (vmaTransactionDepartment == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaTransactionDepartmentException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaTransactionDepartment;
	}

	/**
	 * Returns the vma transaction department with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma transaction department
	 * @return the vma transaction department, or <code>null</code> if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaTransactionDepartment fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma transaction department with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma transaction department
	 * @return the vma transaction department, or <code>null</code> if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment fetchByPrimaryKey(long id)
		throws SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = null;

		

		if (vmaTransactionDepartment == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaTransactionDepartment> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaTransactionDepartment = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaTransactionDepartment;
	}

	/**
	 * Returns all the vma transaction departments where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @return the matching vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findByF_portOfAuthority(
		String portOfAuthority) throws SystemException {
		return findByF_portOfAuthority(portOfAuthority, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction departments where portOfAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portOfAuthority the port of authority
	 * @param start the lower bound of the range of vma transaction departments
	 * @param end the upper bound of the range of vma transaction departments (not inclusive)
	 * @return the range of matching vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findByF_portOfAuthority(
		String portOfAuthority, int start, int end) throws SystemException {
		return findByF_portOfAuthority(portOfAuthority, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction departments where portOfAuthority = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param portOfAuthority the port of authority
	 * @param start the lower bound of the range of vma transaction departments
	 * @param end the upper bound of the range of vma transaction departments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findByF_portOfAuthority(
		String portOfAuthority, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionDepartment> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMATRANSACTIONDEPARTMENT_WHERE);

			if (portOfAuthority == null) {
				query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portOfAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaTransactionDepartmentModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (portOfAuthority != null) {
					builder.appendNamedParameterMap("portOfAuthority", portOfAuthority);
				}

				list = (List<VmaTransactionDepartment>)queryFactory.getResultList(builder);
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
	 * Returns the first vma transaction department in the ordered set where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction department
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment findByF_portOfAuthority_First(
		String portOfAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = fetchByF_portOfAuthority_First(portOfAuthority,
				orderByComparator);

		if (vmaTransactionDepartment != null) {
			return vmaTransactionDepartment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portOfAuthority=");
		msg.append(portOfAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionDepartmentException(msg.toString());
	}

	/**
	 * Returns the first vma transaction department in the ordered set where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma transaction department, or <code>null</code> if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment fetchByF_portOfAuthority_First(
		String portOfAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaTransactionDepartment> list = findByF_portOfAuthority(portOfAuthority,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma transaction department in the ordered set where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction department
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment findByF_portOfAuthority_Last(
		String portOfAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = fetchByF_portOfAuthority_Last(portOfAuthority,
				orderByComparator);

		if (vmaTransactionDepartment != null) {
			return vmaTransactionDepartment;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("portOfAuthority=");
		msg.append(portOfAuthority);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaTransactionDepartmentException(msg.toString());
	}

	/**
	 * Returns the last vma transaction department in the ordered set where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma transaction department, or <code>null</code> if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment fetchByF_portOfAuthority_Last(
		String portOfAuthority, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByF_portOfAuthority(portOfAuthority);

		List<VmaTransactionDepartment> list = findByF_portOfAuthority(portOfAuthority,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma transaction departments before and after the current vma transaction department in the ordered set where portOfAuthority = &#63;.
	 *
	 * @param id the primary key of the current vma transaction department
	 * @param portOfAuthority the port of authority
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma transaction department
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a vma transaction department with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment[] findByF_portOfAuthority_PrevAndNext(
		long id, String portOfAuthority, OrderByComparator orderByComparator)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = findByPrimaryKey(id);

		

		try {
			

			VmaTransactionDepartment[] array = new VmaTransactionDepartment[3];

			array[0] = getByF_portOfAuthority_PrevAndNext(
					vmaTransactionDepartment, portOfAuthority,
					orderByComparator, true);

			array[1] = vmaTransactionDepartment;

			array[2] = getByF_portOfAuthority_PrevAndNext(
					vmaTransactionDepartment, portOfAuthority,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaTransactionDepartment getByF_portOfAuthority_PrevAndNext(
		 VmaTransactionDepartment vmaTransactionDepartment,
		String portOfAuthority, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMATRANSACTIONDEPARTMENT_WHERE);

		if (portOfAuthority == null) {
			query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
		}
		else {
			if (portOfAuthority.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
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
			query.append(VmaTransactionDepartmentModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (portOfAuthority != null) {
			builder.appendNamedParameterMap("portOfAuthority", portOfAuthority);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaTransactionDepartment);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaTransactionDepartment> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the vma transaction department where departmentCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException} if it could not be found.
	 *
	 * @param departmentCode the department code
	 * @return the matching vma transaction department
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaTransactionDepartmentException if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment findByF_departmentCode(
		String departmentCode)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = fetchByF_departmentCode(departmentCode);

		if (vmaTransactionDepartment == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("departmentCode=");
			msg.append(departmentCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchVmaTransactionDepartmentException(msg.toString());
		}

		return vmaTransactionDepartment;
	}

	/**
	 * Returns the vma transaction department where departmentCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param departmentCode the department code
	 * @return the matching vma transaction department, or <code>null</code> if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment fetchByF_departmentCode(
		String departmentCode) throws SystemException {
		return fetchByF_departmentCode(departmentCode, true);
	}

	/**
	 * Returns the vma transaction department where departmentCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param departmentCode the department code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching vma transaction department, or <code>null</code> if a matching vma transaction department could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment fetchByF_departmentCode(
		String departmentCode, boolean retrieveFromCache)
		throws SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = null;
		if (vmaTransactionDepartment == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_VMATRANSACTIONDEPARTMENT_WHERE);

			if (departmentCode == null) {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1);
			}
			else {
				if (departmentCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2);
				}
			}

			query.append(VmaTransactionDepartmentModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(VmaTransactionDepartment.class).build();

				

				if (departmentCode != null) {
					builder.appendNamedParameterMap("departmentCode", departmentCode);
				}

				vmaTransactionDepartment = (VmaTransactionDepartment) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return vmaTransactionDepartment;
	}

	/**
	 * Returns all the vma transaction departments.
	 *
	 * @return the vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma transaction departments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction departments
	 * @param end the upper bound of the range of vma transaction departments (not inclusive)
	 * @return the range of vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma transaction departments.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma transaction departments
	 * @param end the upper bound of the range of vma transaction departments (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaTransactionDepartment> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaTransactionDepartment> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMATRANSACTIONDEPARTMENT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMATRANSACTIONDEPARTMENT.concat(VmaTransactionDepartmentModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaTransactionDepartment>) queryFactory.getResultList(builder);
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
	 * Removes all the vma transaction departments where portOfAuthority = &#63; from the database.
	 *
	 * @param portOfAuthority the port of authority
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_portOfAuthority(String portOfAuthority)
		throws SystemException {
		for (VmaTransactionDepartment vmaTransactionDepartment : findByF_portOfAuthority(
				portOfAuthority)) {
			repository.delete(vmaTransactionDepartment);
		}
	}

	/**
	 * Removes the vma transaction department where departmentCode = &#63; from the database.
	 *
	 * @param departmentCode the department code
	 * @return the vma transaction department that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public VmaTransactionDepartment removeByF_departmentCode(
		String departmentCode)
		throws NoSuchVmaTransactionDepartmentException, SystemException {
		VmaTransactionDepartment vmaTransactionDepartment = findByF_departmentCode(departmentCode);

		repository.delete(vmaTransactionDepartment);
			return vmaTransactionDepartment;
	}

	/**
	 * Removes all the vma transaction departments from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaTransactionDepartment vmaTransactionDepartment : findAll()) {
			repository.delete(vmaTransactionDepartment);
		}
	}

	/**
	 * Returns the number of vma transaction departments where portOfAuthority = &#63;.
	 *
	 * @param portOfAuthority the port of authority
	 * @return the number of matching vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_portOfAuthority(String portOfAuthority)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONDEPARTMENT_WHERE);

			if (portOfAuthority == null) {
				query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_1);
			}
			else {
				if (portOfAuthority.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (portOfAuthority != null) {
					builder.appendNamedParameterMap("portOfAuthority", portOfAuthority);
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
	 * Returns the number of vma transaction departments where departmentCode = &#63;.
	 *
	 * @param departmentCode the department code
	 * @return the number of matching vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_departmentCode(String departmentCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMATRANSACTIONDEPARTMENT_WHERE);

			if (departmentCode == null) {
				query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1);
			}
			else {
				if (departmentCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (departmentCode != null) {
					builder.appendNamedParameterMap("departmentCode", departmentCode);
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
	 * Returns the number of vma transaction departments.
	 *
	 * @return the number of vma transaction departments
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMATRANSACTIONDEPARTMENT).build();

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
	 * Initializes the vma transaction department persistence.
	 */
	private static final String _SQL_SELECT_VMATRANSACTIONDEPARTMENT = "SELECT vmaTransactionDepartment FROM VmaTransactionDepartment vmaTransactionDepartment";
	private static final String _SQL_SELECT_VMATRANSACTIONDEPARTMENT_WHERE = "SELECT vmaTransactionDepartment FROM VmaTransactionDepartment vmaTransactionDepartment WHERE ";
	private static final String _SQL_COUNT_VMATRANSACTIONDEPARTMENT = "SELECT COUNT(vmaTransactionDepartment) FROM VmaTransactionDepartment vmaTransactionDepartment";
	private static final String _SQL_COUNT_VMATRANSACTIONDEPARTMENT_WHERE = "SELECT COUNT(vmaTransactionDepartment) FROM VmaTransactionDepartment vmaTransactionDepartment WHERE ";
	private static final String _FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_1 =
		"vmaTransactionDepartment.portOfAuthority IS NULL";
	private static final String _FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_2 =
		"vmaTransactionDepartment.portOfAuthority =:portOfAuthority";
	private static final String _FINDER_COLUMN_F_PORTOFAUTHORITY_PORTOFAUTHORITY_3 =
		"(vmaTransactionDepartment.portOfAuthority IS NULL OR vmaTransactionDepartment.portOfAuthority =:portOfAuthority)";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_1 =
		"vmaTransactionDepartment.departmentCode IS NULL";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_2 =
		"vmaTransactionDepartment.departmentCode =:departmentCode";
	private static final String _FINDER_COLUMN_F_DEPARTMENTCODE_DEPARTMENTCODE_3 =
		"(vmaTransactionDepartment.departmentCode IS NULL OR vmaTransactionDepartment.departmentCode =:departmentCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaTransactionDepartment.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaTransactionDepartment exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaTransactionDepartment exists with the key {";
	

	
}
