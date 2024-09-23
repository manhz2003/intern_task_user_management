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
import com.fds.nsw.nghiepvu.model.VmaPilotViolation;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.VmaPilotViolationRepository;
import com.fds.nsw.nghiepvu.modelImpl.VmaPilotViolationModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VmaPilotViolationPersistenceImpl extends BasePersistence {
	@Autowired
	VmaPilotViolationRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<VmaPilotViolation> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VmaPilotViolationUtil} to access the vma pilot violation persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public VmaPilotViolation create(long id) {
		VmaPilotViolation vmaPilotViolation = new VmaPilotViolation();

		
		//vmaPilotViolation.setPrimaryKey(id);

		return vmaPilotViolation;
	}

	/**
	 * Removes the vma pilot violation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the vma pilot violation
	 * @return the vma pilot violation that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation remove(long id)
		throws NoSuchVmaPilotViolationException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the vma pilot violation with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vma pilot violation
	 * @return the vma pilot violation that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPilotViolation remove(Serializable primaryKey)
		throws NoSuchVmaPilotViolationException, SystemException {
		

		try {
			

			VmaPilotViolation vmaPilotViolation = findByPrimaryKey(primaryKey);

			if (vmaPilotViolation == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVmaPilotViolationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(vmaPilotViolation);
			return vmaPilotViolation;
		}
		catch (NoSuchVmaPilotViolationException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public VmaPilotViolation remove(VmaPilotViolation VmaPilotViolation) throws SystemException {
	removeImpl(VmaPilotViolation);	return VmaPilotViolation;
}

protected VmaPilotViolation removeImpl

(VmaPilotViolation vmaPilotViolation)
		throws SystemException {
		try {
			repository.delete(vmaPilotViolation);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPilotViolation;
	}

	
	public VmaPilotViolation updateImpl(
		VmaPilotViolation vmaPilotViolation,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(vmaPilotViolation);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return vmaPilotViolation;
	}

	
	public VmaPilotViolation findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma pilot violation with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException} if it could not be found.
	 *
	 * @param id the primary key of the vma pilot violation
	 * @return the vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation findByPrimaryKey(long id)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = fetchByPrimaryKey(id);

		if (vmaPilotViolation == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchVmaPilotViolationException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return vmaPilotViolation;
	}

	/**
	 * Returns the vma pilot violation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vma pilot violation
	 * @return the vma pilot violation, or <code>null</code> if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public VmaPilotViolation fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the vma pilot violation with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the vma pilot violation
	 * @return the vma pilot violation, or <code>null</code> if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation fetchByPrimaryKey(long id)
		throws SystemException {
		VmaPilotViolation vmaPilotViolation = null;

		

		if (vmaPilotViolation == null) {
			

			boolean hasException = false;

			try {
				

				Optional<VmaPilotViolation> optional = repository.findById(id);
				if (optional.isPresent()) {
					vmaPilotViolation = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return vmaPilotViolation;
	}

	/**
	 * Returns all the vma pilot violations where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma pilot violations where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @return the range of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma pilot violations where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPilotViolation> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAPILOTVIOLATION_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaPilotViolationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<VmaPilotViolation>)queryFactory.getResultList(builder);
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
	 * Returns the first vma pilot violation in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (vmaPilotViolation != null) {
			return vmaPilotViolation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPilotViolationException(msg.toString());
	}

	/**
	 * Returns the first vma pilot violation in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma pilot violation, or <code>null</code> if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPilotViolation> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma pilot violation in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (vmaPilotViolation != null) {
			return vmaPilotViolation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPilotViolationException(msg.toString());
	}

	/**
	 * Returns the last vma pilot violation in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma pilot violation, or <code>null</code> if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<VmaPilotViolation> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma pilot violations before and after the current vma pilot violation in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param id the primary key of the current vma pilot violation
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation[] findByF_maritimeCode_PrevAndNext(long id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = findByPrimaryKey(id);

		

		try {
			

			VmaPilotViolation[] array = new VmaPilotViolation[3];

			array[0] = getByF_maritimeCode_PrevAndNext(
					vmaPilotViolation, MaritimeCode, orderByComparator, true);

			array[1] = vmaPilotViolation;

			array[2] = getByF_maritimeCode_PrevAndNext(
					vmaPilotViolation, MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPilotViolation getByF_maritimeCode_PrevAndNext(
		 VmaPilotViolation vmaPilotViolation,
		String MaritimeCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPILOTVIOLATION_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
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
			query.append(VmaPilotViolationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPilotViolation);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPilotViolation> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma pilot violations where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @return the matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_pilotCode(String PilotCode)
		throws SystemException {
		return findByF_pilotCode(PilotCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma pilot violations where PilotCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param PilotCode the pilot code
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @return the range of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_pilotCode(String PilotCode,
		int start, int end) throws SystemException {
		return findByF_pilotCode(PilotCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma pilot violations where PilotCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param PilotCode the pilot code
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findByF_pilotCode(String PilotCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<VmaPilotViolation> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_VMAPILOTVIOLATION_WHERE);

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(VmaPilotViolationModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
				}

				list = (List<VmaPilotViolation>)queryFactory.getResultList(builder);
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
	 * Returns the first vma pilot violation in the ordered set where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation findByF_pilotCode_First(String PilotCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = fetchByF_pilotCode_First(PilotCode,
				orderByComparator);

		if (vmaPilotViolation != null) {
			return vmaPilotViolation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("PilotCode=");
		msg.append(PilotCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPilotViolationException(msg.toString());
	}

	/**
	 * Returns the first vma pilot violation in the ordered set where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching vma pilot violation, or <code>null</code> if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation fetchByF_pilotCode_First(String PilotCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPilotViolation> list = findByF_pilotCode(PilotCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last vma pilot violation in the ordered set where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation findByF_pilotCode_Last(String PilotCode,
		OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = fetchByF_pilotCode_Last(PilotCode,
				orderByComparator);

		if (vmaPilotViolation != null) {
			return vmaPilotViolation;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("PilotCode=");
		msg.append(PilotCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchVmaPilotViolationException(msg.toString());
	}

	/**
	 * Returns the last vma pilot violation in the ordered set where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching vma pilot violation, or <code>null</code> if a matching vma pilot violation could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation fetchByF_pilotCode_Last(String PilotCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_pilotCode(PilotCode);

		List<VmaPilotViolation> list = findByF_pilotCode(PilotCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the vma pilot violations before and after the current vma pilot violation in the ordered set where PilotCode = &#63;.
	 *
	 * @param id the primary key of the current vma pilot violation
	 * @param PilotCode the pilot code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next vma pilot violation
	 * @throws vn.gt.dao.danhmuc.NoSuchVmaPilotViolationException if a vma pilot violation with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public VmaPilotViolation[] findByF_pilotCode_PrevAndNext(long id,
		String PilotCode, OrderByComparator orderByComparator)
		throws NoSuchVmaPilotViolationException, SystemException {
		VmaPilotViolation vmaPilotViolation = findByPrimaryKey(id);

		

		try {
			

			VmaPilotViolation[] array = new VmaPilotViolation[3];

			array[0] = getByF_pilotCode_PrevAndNext(vmaPilotViolation,
					PilotCode, orderByComparator, true);

			array[1] = vmaPilotViolation;

			array[2] = getByF_pilotCode_PrevAndNext(vmaPilotViolation,
					PilotCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected VmaPilotViolation getByF_pilotCode_PrevAndNext(
		VmaPilotViolation vmaPilotViolation, String PilotCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_VMAPILOTVIOLATION_WHERE);

		if (PilotCode == null) {
			query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_1);
		}
		else {
			if (PilotCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_2);
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
			query.append(VmaPilotViolationModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (PilotCode != null) {
			builder.appendNamedParameterMap("PilotCode", PilotCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(vmaPilotViolation);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<VmaPilotViolation> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the vma pilot violations.
	 *
	 * @return the vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vma pilot violations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @return the range of vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vma pilot violations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of vma pilot violations
	 * @param end the upper bound of the range of vma pilot violations (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public List<VmaPilotViolation> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<VmaPilotViolation> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_VMAPILOTVIOLATION);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VMAPILOTVIOLATION.concat(VmaPilotViolationModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<VmaPilotViolation>) queryFactory.getResultList(builder);
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
	 * Removes all the vma pilot violations where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (VmaPilotViolation vmaPilotViolation : findByF_maritimeCode(
				MaritimeCode)) {
			repository.delete(vmaPilotViolation);
		}
	}

	/**
	 * Removes all the vma pilot violations where PilotCode = &#63; from the database.
	 *
	 * @param PilotCode the pilot code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_pilotCode(String PilotCode) throws SystemException {
		for (VmaPilotViolation vmaPilotViolation : findByF_pilotCode(PilotCode)) {
			repository.delete(vmaPilotViolation);
		}
	}

	/**
	 * Removes all the vma pilot violations from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (VmaPilotViolation vmaPilotViolation : findAll()) {
			repository.delete(vmaPilotViolation);
		}
	}

	/**
	 * Returns the number of vma pilot violations where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPILOTVIOLATION_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
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
	 * Returns the number of vma pilot violations where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @return the number of matching vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCode(String PilotCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_VMAPILOTVIOLATION_WHERE);

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCODE_PILOTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();



				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
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
	 * Returns the number of vma pilot violations.
	 *
	 * @return the number of vma pilot violations
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_VMAPILOTVIOLATION).build();

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
	 * Initializes the vma pilot violation persistence.
	 */
	private static final String _SQL_SELECT_VMAPILOTVIOLATION = "SELECT vmaPilotViolation FROM VmaPilotViolation vmaPilotViolation";
	private static final String _SQL_SELECT_VMAPILOTVIOLATION_WHERE = "SELECT vmaPilotViolation FROM VmaPilotViolation vmaPilotViolation WHERE ";
	private static final String _SQL_COUNT_VMAPILOTVIOLATION = "SELECT COUNT(vmaPilotViolation) FROM VmaPilotViolation vmaPilotViolation";
	private static final String _SQL_COUNT_VMAPILOTVIOLATION_WHERE = "SELECT COUNT(vmaPilotViolation) FROM VmaPilotViolation vmaPilotViolation WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "vmaPilotViolation.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "vmaPilotViolation.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(vmaPilotViolation.MaritimeCode IS NULL OR vmaPilotViolation.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_1 = "vmaPilotViolation.PilotCode IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_2 = "vmaPilotViolation.PilotCode =:PilotCode";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_3 = "(vmaPilotViolation.PilotCode IS NULL OR vmaPilotViolation.PilotCode =:PilotCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vmaPilotViolation.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VmaPilotViolation exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No VmaPilotViolation exists with the key {";
	

	
}
