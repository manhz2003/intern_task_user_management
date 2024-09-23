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
import com.fds.nsw.nghiepvu.model.DmVmaTugboat;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaTugboatRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaTugboatModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaTugboatPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaTugboatRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaTugboat> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaTugboatUtil} to access the dm vma tugboat persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaTugboat create(long id) {
		DmVmaTugboat dmVmaTugboat = new DmVmaTugboat();

		
		//dmVmaTugboat.setPrimaryKey(id);

		return dmVmaTugboat;
	}

	/**
	 * Removes the dm vma tugboat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma tugboat
	 * @return the dm vma tugboat that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat remove(long Id)
		throws NoSuchDmVmaTugboatException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma tugboat with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma tugboat
	 * @return the dm vma tugboat that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaTugboat remove(Serializable primaryKey)
		throws NoSuchDmVmaTugboatException, SystemException {
		

		try {
			

			DmVmaTugboat dmVmaTugboat = findByPrimaryKey(primaryKey);

			if (dmVmaTugboat == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaTugboatException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaTugboat);
			return dmVmaTugboat;
		}
		catch (NoSuchDmVmaTugboatException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaTugboat remove(DmVmaTugboat DmVmaTugboat) throws SystemException {
	removeImpl(DmVmaTugboat);	return DmVmaTugboat;
}

protected DmVmaTugboat removeImpl

(DmVmaTugboat dmVmaTugboat)
		throws SystemException {
		try {
			repository.delete(dmVmaTugboat);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaTugboat;
	}

	
	public DmVmaTugboat updateImpl(
		DmVmaTugboat dmVmaTugboat, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmVmaTugboat);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaTugboat;
	}

	
	public DmVmaTugboat findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma tugboat with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma tugboat
	 * @return the dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByPrimaryKey(long Id)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByPrimaryKey(Id);

		if (dmVmaTugboat == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaTugboatException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaTugboat;
	}

	/**
	 * Returns the dm vma tugboat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma tugboat
	 * @return the dm vma tugboat, or <code>null</code> if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaTugboat fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma tugboat with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma tugboat
	 * @return the dm vma tugboat, or <code>null</code> if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByPrimaryKey(long Id) throws SystemException {
		DmVmaTugboat dmVmaTugboat = null;

		

		if (dmVmaTugboat == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaTugboat> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaTugboat = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaTugboat;
	}

	/**
	 * Returns all the dm vma tugboats where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma tugboats where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @return the range of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma tugboats where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaTugboat> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

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
				query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaTugboat>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma tugboat in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaTugboat != null) {
			return dmVmaTugboat;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatException(msg.toString());
	}

	/**
	 * Returns the first dm vma tugboat in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaTugboat> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma tugboat in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaTugboat != null) {
			return dmVmaTugboat;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatException(msg.toString());
	}

	/**
	 * Returns the last dm vma tugboat in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaTugboat> list = findByF_maritimeCode(MaritimeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma tugboats before and after the current dm vma tugboat in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma tugboat
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = findByPrimaryKey(Id);

		

		try {
			

			DmVmaTugboat[] array = new DmVmaTugboat[3];

			array[0] = getByF_maritimeCode_PrevAndNext(dmVmaTugboat,
					MaritimeCode, orderByComparator, true);

			array[1] = dmVmaTugboat;

			array[2] = getByF_maritimeCode_PrevAndNext(dmVmaTugboat,
					MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaTugboat getByF_maritimeCode_PrevAndNext(
		DmVmaTugboat dmVmaTugboat, String MaritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

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
			query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaTugboat);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaTugboat> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm vma tugboats where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode_tugboatCompanyCode(
		String MaritimeCode, String TugboatCompanyCode)
		throws SystemException {
		return findByF_maritimeCode_tugboatCompanyCode(MaritimeCode,
			TugboatCompanyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma tugboats where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @return the range of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode_tugboatCompanyCode(
		String MaritimeCode, String TugboatCompanyCode, int start, int end)
		throws SystemException {
		return findByF_maritimeCode_tugboatCompanyCode(MaritimeCode,
			TugboatCompanyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma tugboats where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findByF_maritimeCode_tugboatCompanyCode(
		String MaritimeCode, String TugboatCompanyCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaTugboat> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_2);
				}
			}

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				list = (List<DmVmaTugboat>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma tugboat in the ordered set where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_maritimeCode_tugboatCompanyCode_First(
		String MaritimeCode, String TugboatCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_maritimeCode_tugboatCompanyCode_First(MaritimeCode,
				TugboatCompanyCode, orderByComparator);

		if (dmVmaTugboat != null) {
			return dmVmaTugboat;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(", TugboatCompanyCode=");
		msg.append(TugboatCompanyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatException(msg.toString());
	}

	/**
	 * Returns the first dm vma tugboat in the ordered set where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_maritimeCode_tugboatCompanyCode_First(
		String MaritimeCode, String TugboatCompanyCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaTugboat> list = findByF_maritimeCode_tugboatCompanyCode(MaritimeCode,
				TugboatCompanyCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma tugboat in the ordered set where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_maritimeCode_tugboatCompanyCode_Last(
		String MaritimeCode, String TugboatCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_maritimeCode_tugboatCompanyCode_Last(MaritimeCode,
				TugboatCompanyCode, orderByComparator);

		if (dmVmaTugboat != null) {
			return dmVmaTugboat;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(", TugboatCompanyCode=");
		msg.append(TugboatCompanyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatException(msg.toString());
	}

	/**
	 * Returns the last dm vma tugboat in the ordered set where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_maritimeCode_tugboatCompanyCode_Last(
		String MaritimeCode, String TugboatCompanyCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode_tugboatCompanyCode(MaritimeCode,
				TugboatCompanyCode);

		List<DmVmaTugboat> list = findByF_maritimeCode_tugboatCompanyCode(MaritimeCode,
				TugboatCompanyCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma tugboats before and after the current dm vma tugboat in the ordered set where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma tugboat
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a dm vma tugboat with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat[] findByF_maritimeCode_tugboatCompanyCode_PrevAndNext(
		long Id, String MaritimeCode, String TugboatCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = findByPrimaryKey(Id);

		

		try {
			

			DmVmaTugboat[] array = new DmVmaTugboat[3];

			array[0] = getByF_maritimeCode_tugboatCompanyCode_PrevAndNext(
					dmVmaTugboat, MaritimeCode, TugboatCompanyCode,
					orderByComparator, true);

			array[1] = dmVmaTugboat;

			array[2] = getByF_maritimeCode_tugboatCompanyCode_PrevAndNext(
					dmVmaTugboat, MaritimeCode, TugboatCompanyCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaTugboat getByF_maritimeCode_tugboatCompanyCode_PrevAndNext(
		 DmVmaTugboat dmVmaTugboat, String MaritimeCode,
		String TugboatCompanyCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_2);
			}
		}

		if (TugboatCompanyCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1);
		}
		else {
			if (TugboatCompanyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2);
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
			query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (TugboatCompanyCode != null) {
			builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaTugboat);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaTugboat> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException} if it could not be found.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @return the matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_tugboatCompanyCode_shipCode(
		String TugboatCompanyCode, String ShipCode)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_tugboatCompanyCode_shipCode(TugboatCompanyCode,
				ShipCode);

		if (dmVmaTugboat == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TugboatCompanyCode=");
			msg.append(TugboatCompanyCode);

			msg.append(", ShipCode=");
			msg.append(ShipCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaTugboatException(msg.toString());
		}

		return dmVmaTugboat;
	}

	/**
	 * Returns the dm vma tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @return the matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_tugboatCompanyCode_shipCode(
		String TugboatCompanyCode, String ShipCode) throws SystemException {
		return fetchByF_tugboatCompanyCode_shipCode(TugboatCompanyCode,
			ShipCode, true);
	}

	/**
	 * Returns the dm vma tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_tugboatCompanyCode_shipCode(
		String TugboatCompanyCode, String ShipCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaTugboat dmVmaTugboat = null;
		if (dmVmaTugboat == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_2);
				}
			}

			query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaTugboat.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
				}

				dmVmaTugboat = (DmVmaTugboat) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaTugboat;
	}

	/**
	 * Returns the dm vma tugboat where ShipCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException} if it could not be found.
	 *
	 * @param ShipCode the ship code
	 * @return the matching dm vma tugboat
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatException if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat findByF_shipCode(String ShipCode)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = fetchByF_shipCode(ShipCode);

		if (dmVmaTugboat == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("ShipCode=");
			msg.append(ShipCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaTugboatException(msg.toString());
		}

		return dmVmaTugboat;
	}

	/**
	 * Returns the dm vma tugboat where ShipCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param ShipCode the ship code
	 * @return the matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_shipCode(String ShipCode)
		throws SystemException {
		return fetchByF_shipCode(ShipCode, true);
	}

	/**
	 * Returns the dm vma tugboat where ShipCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param ShipCode the ship code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma tugboat, or <code>null</code> if a matching dm vma tugboat could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat fetchByF_shipCode(String ShipCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaTugboat dmVmaTugboat = null;
		if (dmVmaTugboat == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMATUGBOAT_WHERE);

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_2);
				}
			}

			query.append(DmVmaTugboatModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaTugboat.class).build();

				

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
				}

				dmVmaTugboat = (DmVmaTugboat) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaTugboat;
	}

	/**
	 * Returns all the dm vma tugboats.
	 *
	 * @return the dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma tugboats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @return the range of dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma tugboats.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma tugboats
	 * @param end the upper bound of the range of dm vma tugboats (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboat> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaTugboat> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMATUGBOAT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMATUGBOAT.concat(DmVmaTugboatModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaTugboat>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma tugboats where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaTugboat dmVmaTugboat : findByF_maritimeCode(MaritimeCode)) {
			repository.delete(dmVmaTugboat);
		}
	}

	/**
	 * Removes all the dm vma tugboats where MaritimeCode = &#63; and TugboatCompanyCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode_tugboatCompanyCode(String MaritimeCode,
		String TugboatCompanyCode) throws SystemException {
		for (DmVmaTugboat dmVmaTugboat : findByF_maritimeCode_tugboatCompanyCode(
				MaritimeCode, TugboatCompanyCode)) {
			repository.delete(dmVmaTugboat);
		}
	}

	/**
	 * Removes the dm vma tugboat where TugboatCompanyCode = &#63; and ShipCode = &#63; from the database.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @return the dm vma tugboat that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat removeByF_tugboatCompanyCode_shipCode(
		String TugboatCompanyCode, String ShipCode)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = findByF_tugboatCompanyCode_shipCode(TugboatCompanyCode,
				ShipCode);

		repository.delete(dmVmaTugboat);
			return dmVmaTugboat;
	}

	/**
	 * Removes the dm vma tugboat where ShipCode = &#63; from the database.
	 *
	 * @param ShipCode the ship code
	 * @return the dm vma tugboat that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboat removeByF_shipCode(String ShipCode)
		throws NoSuchDmVmaTugboatException, SystemException {
		DmVmaTugboat dmVmaTugboat = findByF_shipCode(ShipCode);

		repository.delete(dmVmaTugboat);
			return dmVmaTugboat;
	}

	/**
	 * Removes all the dm vma tugboats from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaTugboat dmVmaTugboat : findAll()) {
			repository.delete(dmVmaTugboat);
		}
	}

	/**
	 * Returns the number of dm vma tugboats where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMATUGBOAT_WHERE);

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
	 * Returns the number of dm vma tugboats where MaritimeCode = &#63; and TugboatCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the number of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode_tugboatCompanyCode(String MaritimeCode,
		String TugboatCompanyCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMVMATUGBOAT_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_2);
				}
			}

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
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
	 * Returns the number of dm vma tugboats where TugboatCompanyCode = &#63; and ShipCode = &#63;.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param ShipCode the ship code
	 * @return the number of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_tugboatCompanyCode_shipCode(String TugboatCompanyCode,
		String ShipCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMVMATUGBOAT_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
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
	 * Returns the number of dm vma tugboats where ShipCode = &#63;.
	 *
	 * @param ShipCode the ship code
	 * @return the number of matching dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_shipCode(String ShipCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMATUGBOAT_WHERE);

			if (ShipCode == null) {
				query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_1);
			}
			else {
				if (ShipCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_SHIPCODE_SHIPCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (ShipCode != null) {
					builder.appendNamedParameterMap("ShipCode", ShipCode);
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
	 * Returns the number of dm vma tugboats.
	 *
	 * @return the number of dm vma tugboats
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMATUGBOAT).build();

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
	 * Initializes the dm vma tugboat persistence.
	 */
	private static final String _SQL_SELECT_DMVMATUGBOAT = "SELECT dmVmaTugboat FROM DmVmaTugboat dmVmaTugboat";
	private static final String _SQL_SELECT_DMVMATUGBOAT_WHERE = "SELECT dmVmaTugboat FROM DmVmaTugboat dmVmaTugboat WHERE ";
	private static final String _SQL_COUNT_DMVMATUGBOAT = "SELECT COUNT(dmVmaTugboat) FROM DmVmaTugboat dmVmaTugboat";
	private static final String _SQL_COUNT_DMVMATUGBOAT_WHERE = "SELECT COUNT(dmVmaTugboat) FROM DmVmaTugboat dmVmaTugboat WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaTugboat.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaTugboat.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaTugboat.MaritimeCode IS NULL OR dmVmaTugboat.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_1 =
		"dmVmaTugboat.MaritimeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_2 =
		"dmVmaTugboat.MaritimeCode =:MaritimeCode AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_MARITIMECODE_3 =
		"(dmVmaTugboat.MaritimeCode IS NULL OR dmVmaTugboat.MaritimeCode =:MaritimeCode) AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1 =
		"dmVmaTugboat.TugboatCompanyCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2 =
		"dmVmaTugboat.TugboatCompanyCode =:TugboatCompanyCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3 =
		"(dmVmaTugboat.TugboatCompanyCode IS NULL OR dmVmaTugboat.TugboatCompanyCode =:TugboatCompanyCode)";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_1 =
		"dmVmaTugboat.TugboatCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_2 =
		"dmVmaTugboat.TugboatCompanyCode =:TugboatCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_TUGBOATCOMPANYCODE_3 =
		"(dmVmaTugboat.TugboatCompanyCode IS NULL OR dmVmaTugboat.TugboatCompanyCode =:TugboatCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_1 =
		"dmVmaTugboat.ShipCode IS NULL";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_2 =
		"dmVmaTugboat.ShipCode =:ShipCode";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_SHIPCODE_SHIPCODE_3 =
		"(dmVmaTugboat.ShipCode IS NULL OR dmVmaTugboat.ShipCode =:ShipCode)";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SHIPCODE_1 = "dmVmaTugboat.ShipCode IS NULL";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SHIPCODE_2 = "dmVmaTugboat.ShipCode =:ShipCode";
	private static final String _FINDER_COLUMN_F_SHIPCODE_SHIPCODE_3 = "(dmVmaTugboat.ShipCode IS NULL OR dmVmaTugboat.ShipCode =:ShipCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaTugboat.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaTugboat exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaTugboat exists with the key {";
	

	
}
