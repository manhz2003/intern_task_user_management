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
import com.fds.nsw.nghiepvu.model.DmVmaPilot;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaPilotRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaPilotModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaPilotPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaPilotRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaPilot> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaPilotUtil} to access the dm vma pilot persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaPilot create(long id) {
		DmVmaPilot dmVmaPilot = new DmVmaPilot();

		
		//dmVmaPilot.setPrimaryKey(id);

		return dmVmaPilot;
	}

	/**
	 * Removes the dm vma pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma pilot
	 * @return the dm vma pilot that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot remove(long Id)
		throws NoSuchDmVmaPilotException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma pilot with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma pilot
	 * @return the dm vma pilot that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilot remove(Serializable primaryKey)
		throws NoSuchDmVmaPilotException, SystemException {
		

		try {
			

			DmVmaPilot dmVmaPilot = findByPrimaryKey(primaryKey);

			if (dmVmaPilot == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaPilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaPilot);
			return dmVmaPilot;
		}
		catch (NoSuchDmVmaPilotException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaPilot remove(DmVmaPilot DmVmaPilot) throws SystemException {
	removeImpl(DmVmaPilot);	return DmVmaPilot;
}

protected DmVmaPilot removeImpl

(DmVmaPilot dmVmaPilot)
		throws SystemException {
		try {
			repository.delete(dmVmaPilot);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilot;
	}

	
	public DmVmaPilot updateImpl(
		DmVmaPilot dmVmaPilot, boolean merge)
		throws SystemException {
		try {

			repository.saveAndFlush(dmVmaPilot);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilot;
	}

	
	public DmVmaPilot findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot
	 * @return the dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByPrimaryKey(long Id)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByPrimaryKey(Id);

		if (dmVmaPilot == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaPilotException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaPilot;
	}

	/**
	 * Returns the dm vma pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma pilot
	 * @return the dm vma pilot, or <code>null</code> if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilot fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot
	 * @return the dm vma pilot, or <code>null</code> if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByPrimaryKey(long Id) throws SystemException {
		DmVmaPilot dmVmaPilot = null;

		

		if (dmVmaPilot == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaPilot> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaPilot = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaPilot;
	}

	/**
	 * Returns all the dm vma pilots where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilots where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @return the range of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilots where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaPilot> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

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
				query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaPilot>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma pilot in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaPilot != null) {
			return dmVmaPilot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotException(msg.toString());
	}

	/**
	 * Returns the first dm vma pilot in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilot> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma pilot in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaPilot != null) {
			return dmVmaPilot;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotException(msg.toString());
	}

	/**
	 * Returns the last dm vma pilot in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaPilot> list = findByF_maritimeCode(MaritimeCode, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma pilots before and after the current dm vma pilot in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma pilot
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = findByPrimaryKey(Id);

		

		try {
			

			DmVmaPilot[] array = new DmVmaPilot[3];

			array[0] = getByF_maritimeCode_PrevAndNext(dmVmaPilot,
					MaritimeCode, orderByComparator, true);

			array[1] = dmVmaPilot;

			array[2] = getByF_maritimeCode_PrevAndNext(dmVmaPilot,
					MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaPilot getByF_maritimeCode_PrevAndNext(
		DmVmaPilot dmVmaPilot, String MaritimeCode,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

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
			query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaPilot);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaPilot> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm vma pilots where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @return the matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode_pilotCompanyCode(
		String MaritimeCode, String PilotCompanyCode) throws SystemException {
		return findByF_maritimeCode_pilotCompanyCode(MaritimeCode,
			PilotCompanyCode, QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilots where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @return the range of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode_pilotCompanyCode(
		String MaritimeCode, String PilotCompanyCode, int start, int end)
		throws SystemException {
		return findByF_maritimeCode_pilotCompanyCode(MaritimeCode,
			PilotCompanyCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilots where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findByF_maritimeCode_pilotCompanyCode(
		String MaritimeCode, String PilotCompanyCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilot> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(4 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(4);
			}

			query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_2);
				}
			}

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				list = (List<DmVmaPilot>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma pilot in the ordered set where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_maritimeCode_pilotCompanyCode_First(
		String MaritimeCode, String PilotCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_maritimeCode_pilotCompanyCode_First(MaritimeCode,
				PilotCompanyCode, orderByComparator);

		if (dmVmaPilot != null) {
			return dmVmaPilot;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(", PilotCompanyCode=");
		msg.append(PilotCompanyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotException(msg.toString());
	}

	/**
	 * Returns the first dm vma pilot in the ordered set where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_maritimeCode_pilotCompanyCode_First(
		String MaritimeCode, String PilotCompanyCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilot> list = findByF_maritimeCode_pilotCompanyCode(MaritimeCode,
				PilotCompanyCode, 0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma pilot in the ordered set where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_maritimeCode_pilotCompanyCode_Last(
		String MaritimeCode, String PilotCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_maritimeCode_pilotCompanyCode_Last(MaritimeCode,
				PilotCompanyCode, orderByComparator);

		if (dmVmaPilot != null) {
			return dmVmaPilot;
		}

		StringBundler msg = new StringBundler(6);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(", PilotCompanyCode=");
		msg.append(PilotCompanyCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotException(msg.toString());
	}

	/**
	 * Returns the last dm vma pilot in the ordered set where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_maritimeCode_pilotCompanyCode_Last(
		String MaritimeCode, String PilotCompanyCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode_pilotCompanyCode(MaritimeCode,
				PilotCompanyCode);

		List<DmVmaPilot> list = findByF_maritimeCode_pilotCompanyCode(MaritimeCode,
				PilotCompanyCode, count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma pilots before and after the current dm vma pilot in the ordered set where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma pilot
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a dm vma pilot with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot[] findByF_maritimeCode_pilotCompanyCode_PrevAndNext(
		long Id, String MaritimeCode, String PilotCompanyCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = findByPrimaryKey(Id);

		

		try {
			

			DmVmaPilot[] array = new DmVmaPilot[3];

			array[0] = getByF_maritimeCode_pilotCompanyCode_PrevAndNext(
					dmVmaPilot, MaritimeCode, PilotCompanyCode,
					orderByComparator, true);

			array[1] = dmVmaPilot;

			array[2] = getByF_maritimeCode_pilotCompanyCode_PrevAndNext(
					dmVmaPilot, MaritimeCode, PilotCompanyCode,
					orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaPilot getByF_maritimeCode_pilotCompanyCode_PrevAndNext(
		 DmVmaPilot dmVmaPilot, String MaritimeCode,
		String PilotCompanyCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

		if (MaritimeCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_1);
		}
		else {
			if (MaritimeCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_2);
			}
		}

		if (PilotCompanyCode == null) {
			query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1);
		}
		else {
			if (PilotCompanyCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2);
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
			query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (PilotCompanyCode != null) {
			builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaPilot);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaPilot> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma pilot where PilotCompanyCode = &#63; and PilotCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotException} if it could not be found.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @return the matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_pilotCompanyCode_pilotCode(
		String PilotCompanyCode, String PilotCode)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_pilotCompanyCode_pilotCode(PilotCompanyCode,
				PilotCode);

		if (dmVmaPilot == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCompanyCode=");
			msg.append(PilotCompanyCode);

			msg.append(", PilotCode=");
			msg.append(PilotCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaPilotException(msg.toString());
		}

		return dmVmaPilot;
	}

	/**
	 * Returns the dm vma pilot where PilotCompanyCode = &#63; and PilotCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @return the matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_pilotCompanyCode_pilotCode(
		String PilotCompanyCode, String PilotCode) throws SystemException {
		return fetchByF_pilotCompanyCode_pilotCode(PilotCompanyCode, PilotCode,
			true);
	}

	/**
	 * Returns the dm vma pilot where PilotCompanyCode = &#63; and PilotCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_pilotCompanyCode_pilotCode(
		String PilotCompanyCode, String PilotCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaPilot dmVmaPilot = null;
		if (dmVmaPilot == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_2);
				}
			}

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_2);
				}
			}

			query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaPilot.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
				}

				dmVmaPilot = (DmVmaPilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaPilot;
	}

	/**
	 * Returns the dm vma pilot where PilotCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotException} if it could not be found.
	 *
	 * @param PilotCode the pilot code
	 * @return the matching dm vma pilot
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotException if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot findByF_pilotCode(String PilotCode)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = fetchByF_pilotCode(PilotCode);

		if (dmVmaPilot == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCode=");
			msg.append(PilotCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaPilotException(msg.toString());
		}

		return dmVmaPilot;
	}

	/**
	 * Returns the dm vma pilot where PilotCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCode the pilot code
	 * @return the matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_pilotCode(String PilotCode)
		throws SystemException {
		return fetchByF_pilotCode(PilotCode, true);
	}

	/**
	 * Returns the dm vma pilot where PilotCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCode the pilot code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma pilot, or <code>null</code> if a matching dm vma pilot could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot fetchByF_pilotCode(String PilotCode,
		boolean retrieveFromCache) throws SystemException {
		DmVmaPilot dmVmaPilot = null;
		if (dmVmaPilot == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMAPILOT_WHERE);

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

			query.append(DmVmaPilotModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaPilot.class).build();

				

				if (PilotCode != null) {
					builder.appendNamedParameterMap("PilotCode", PilotCode);
				}

				dmVmaPilot = (DmVmaPilot) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaPilot;
	}

	/**
	 * Returns all the dm vma pilots.
	 *
	 * @return the dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @return the range of dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilots.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilots
	 * @param end the upper bound of the range of dm vma pilots (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilot> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilot> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMAPILOT);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMAPILOT.concat(DmVmaPilotModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaPilot>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma pilots where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaPilot dmVmaPilot : findByF_maritimeCode(MaritimeCode)) {
			repository.delete(dmVmaPilot);
		}
	}

	/**
	 * Removes all the dm vma pilots where MaritimeCode = &#63; and PilotCompanyCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode_pilotCompanyCode(String MaritimeCode,
		String PilotCompanyCode) throws SystemException {
		for (DmVmaPilot dmVmaPilot : findByF_maritimeCode_pilotCompanyCode(
				MaritimeCode, PilotCompanyCode)) {
			repository.delete(dmVmaPilot);
		}
	}

	/**
	 * Removes the dm vma pilot where PilotCompanyCode = &#63; and PilotCode = &#63; from the database.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @return the dm vma pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot removeByF_pilotCompanyCode_pilotCode(
		String PilotCompanyCode, String PilotCode)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = findByF_pilotCompanyCode_pilotCode(PilotCompanyCode,
				PilotCode);

		repository.delete(dmVmaPilot);
			return dmVmaPilot;
	}

	/**
	 * Removes the dm vma pilot where PilotCode = &#63; from the database.
	 *
	 * @param PilotCode the pilot code
	 * @return the dm vma pilot that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilot removeByF_pilotCode(String PilotCode)
		throws NoSuchDmVmaPilotException, SystemException {
		DmVmaPilot dmVmaPilot = findByF_pilotCode(PilotCode);

		repository.delete(dmVmaPilot);
			return dmVmaPilot;
	}

	/**
	 * Removes all the dm vma pilots from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaPilot dmVmaPilot : findAll()) {
			repository.delete(dmVmaPilot);
		}
	}

	/**
	 * Returns the number of dm vma pilots where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOT_WHERE);

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
	 * Returns the number of dm vma pilots where MaritimeCode = &#63; and PilotCompanyCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param PilotCompanyCode the pilot company code
	 * @return the number of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode_pilotCompanyCode(String MaritimeCode,
		String PilotCompanyCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMVMAPILOT_WHERE);

			if (MaritimeCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_1);
			}
			else {
				if (MaritimeCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_2);
				}
			}

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
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
	 * Returns the number of dm vma pilots where PilotCompanyCode = &#63; and PilotCode = &#63;.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param PilotCode the pilot code
	 * @return the number of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCompanyCode_pilotCode(String PilotCompanyCode,
		String PilotCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_DMVMAPILOT_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_2);
				}
			}

			if (PilotCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_1);
			}
			else {
				if (PilotCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

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
	 * Returns the number of dm vma pilots where PilotCode = &#63;.
	 *
	 * @param PilotCode the pilot code
	 * @return the number of matching dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCode(String PilotCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOT_WHERE);

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
	 * Returns the number of dm vma pilots.
	 *
	 * @return the number of dm vma pilots
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMAPILOT).build();

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
	 * Initializes the dm vma pilot persistence.
	 */
	private static final String _SQL_SELECT_DMVMAPILOT = "SELECT dmVmaPilot FROM DmVmaPilot dmVmaPilot";
	private static final String _SQL_SELECT_DMVMAPILOT_WHERE = "SELECT dmVmaPilot FROM DmVmaPilot dmVmaPilot WHERE ";
	private static final String _SQL_COUNT_DMVMAPILOT = "SELECT COUNT(dmVmaPilot) FROM DmVmaPilot dmVmaPilot";
	private static final String _SQL_COUNT_DMVMAPILOT_WHERE = "SELECT COUNT(dmVmaPilot) FROM DmVmaPilot dmVmaPilot WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaPilot.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaPilot.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaPilot.MaritimeCode IS NULL OR dmVmaPilot.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_1 =
		"dmVmaPilot.MaritimeCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_2 =
		"dmVmaPilot.MaritimeCode =:MaritimeCode AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_MARITIMECODE_3 =
		"(dmVmaPilot.MaritimeCode IS NULL OR dmVmaPilot.MaritimeCode =:MaritimeCode) AND ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1 =
		"dmVmaPilot.PilotCompanyCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2 =
		"dmVmaPilot.PilotCompanyCode =:PilotCompanyCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3 =
		"(dmVmaPilot.PilotCompanyCode IS NULL OR dmVmaPilot.PilotCompanyCode =:PilotCompanyCode)";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_1 =
		"dmVmaPilot.PilotCompanyCode IS NULL AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_2 =
		"dmVmaPilot.PilotCompanyCode =:PilotCompanyCode AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCOMPANYCODE_3 =
		"(dmVmaPilot.PilotCompanyCode IS NULL OR dmVmaPilot.PilotCompanyCode =:PilotCompanyCode) AND ";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_1 =
		"dmVmaPilot.PilotCode IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_2 =
		"dmVmaPilot.PilotCode =:PilotCode";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCODE_PILOTCODE_3 =
		"(dmVmaPilot.PilotCode IS NULL OR dmVmaPilot.PilotCode =:PilotCode)";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_1 = "dmVmaPilot.PilotCode IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_2 = "dmVmaPilot.PilotCode =:PilotCode";
	private static final String _FINDER_COLUMN_F_PILOTCODE_PILOTCODE_3 = "(dmVmaPilot.PilotCode IS NULL OR dmVmaPilot.PilotCode =:PilotCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaPilot.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaPilot exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaPilot exists with the key {";
	

	
}
