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
import com.fds.nsw.nghiepvu.model.DmHistoryEnterprise;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmHistoryEnterpriseRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmHistoryEnterriseModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmHistoryEnterrisePersistenceImpl extends BasePersistence {
	@Autowired
	DmHistoryEnterpriseRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmHistoryEnterprise> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmHistoryEnterriseUtil} to access the dm history enterrise persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmHistoryEnterprise create(int id) {
		DmHistoryEnterprise dmHistoryEnterrise = new DmHistoryEnterprise();

		
		//dmHistoryEnterrise.setPrimaryKey(id);

		return dmHistoryEnterrise;
	}

	/**
	 * Removes the dm history enterrise with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm history enterrise
	 * @return the dm history enterrise that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise remove(int id)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		return remove(Integer.valueOf(id));
	}

	/**
	 * Removes the dm history enterrise with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm history enterrise
	 * @return the dm history enterrise that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryEnterprise remove(Serializable primaryKey)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		

		try {
			

			DmHistoryEnterprise dmHistoryEnterrise = findByPrimaryKey(primaryKey);

			if (dmHistoryEnterrise == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmHistoryEnterriseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmHistoryEnterrise);
			return dmHistoryEnterrise;
		}
		catch (NoSuchDmHistoryEnterriseException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmHistoryEnterprise remove(DmHistoryEnterprise DmHistoryEnterprise) throws SystemException {
	removeImpl(DmHistoryEnterprise);	return DmHistoryEnterprise;
}

protected DmHistoryEnterprise removeImpl

(
		DmHistoryEnterprise dmHistoryEnterrise) throws SystemException {
		try {
			repository.delete(dmHistoryEnterrise);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryEnterrise;
	}

	
	public DmHistoryEnterprise updateImpl(
		DmHistoryEnterprise dmHistoryEnterrise,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmHistoryEnterrise);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmHistoryEnterrise;
	}

	
	public DmHistoryEnterprise findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history enterrise with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException} if it could not be found.
	 *
	 * @param id the primary key of the dm history enterrise
	 * @return the dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findByPrimaryKey(int id)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchByPrimaryKey(id);

		if (dmHistoryEnterrise == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmHistoryEnterriseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmHistoryEnterrise;
	}

	/**
	 * Returns the dm history enterrise with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm history enterrise
	 * @return the dm history enterrise, or <code>null</code> if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmHistoryEnterprise fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
	}

	/**
	 * Returns the dm history enterrise with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm history enterrise
	 * @return the dm history enterrise, or <code>null</code> if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchByPrimaryKey(int id)
		throws SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = null;

		

		if (dmHistoryEnterrise == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmHistoryEnterprise> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmHistoryEnterrise = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmHistoryEnterrise;
	}

	/**
	 * Returns all the dm history enterrises where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findBySyncVersion(String syncVersion)
		throws SystemException {
		return findBySyncVersion(syncVersion, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history enterrises where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @return the range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findBySyncVersion(String syncVersion,
		int start, int end) throws SystemException {
		return findBySyncVersion(syncVersion, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history enterrises where syncVersion = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param syncVersion the sync version
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findBySyncVersion(String syncVersion,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryEnterprise> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

				list = (List<DmHistoryEnterprise>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history enterrise in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchBySyncVersion_First(syncVersion,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the first dm history enterrise in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchBySyncVersion_First(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryEnterprise> list = findBySyncVersion(syncVersion, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchBySyncVersion_Last(syncVersion,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("syncVersion=");
		msg.append(syncVersion);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchBySyncVersion_Last(String syncVersion,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countBySyncVersion(syncVersion);

		List<DmHistoryEnterprise> list = findBySyncVersion(syncVersion,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history enterrises before and after the current dm history enterrise in the ordered set where syncVersion = &#63;.
	 *
	 * @param id the primary key of the current dm history enterrise
	 * @param syncVersion the sync version
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise[] findBySyncVersion_PrevAndNext(int id,
		String syncVersion, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = findByPrimaryKey(id);

		

		try {
			

			DmHistoryEnterprise[] array = new DmHistoryEnterprise[3];

			array[0] = getBySyncVersion_PrevAndNext(
					dmHistoryEnterrise, syncVersion, orderByComparator, true);

			array[1] = dmHistoryEnterrise;

			array[2] = getBySyncVersion_PrevAndNext(
					dmHistoryEnterrise, syncVersion, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryEnterprise getBySyncVersion_PrevAndNext(
		DmHistoryEnterprise dmHistoryEnterrise, String syncVersion,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

		if (syncVersion == null) {
			query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
		}
		else {
			if (syncVersion.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
			}
			else {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
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
			query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (syncVersion != null) {
			builder.appendNamedParameterMap("syncVersion", syncVersion);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryEnterrise);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryEnterprise> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm history enterrises where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @return the matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseCode(String enterpriseCode)
		throws SystemException {
		return findByEnterpriseCode(enterpriseCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history enterrises where enterpriseCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param enterpriseCode the enterprise code
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @return the range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseCode(
		String enterpriseCode, int start, int end) throws SystemException {
		return findByEnterpriseCode(enterpriseCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history enterrises where enterpriseCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param enterpriseCode the enterprise code
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseCode(
		String enterpriseCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryEnterprise> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

			if (enterpriseCode == null) {
				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
			}
			else {
				if (enterpriseCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (enterpriseCode != null) {
					builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
				}

				list = (List<DmHistoryEnterprise>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history enterrise in the ordered set where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findByEnterpriseCode_First(
		String enterpriseCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchByEnterpriseCode_First(enterpriseCode,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enterpriseCode=");
		msg.append(enterpriseCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the first dm history enterrise in the ordered set where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchByEnterpriseCode_First(
		String enterpriseCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryEnterprise> list = findByEnterpriseCode(enterpriseCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findByEnterpriseCode_Last(String enterpriseCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchByEnterpriseCode_Last(enterpriseCode,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enterpriseCode=");
		msg.append(enterpriseCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchByEnterpriseCode_Last(
		String enterpriseCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByEnterpriseCode(enterpriseCode);

		List<DmHistoryEnterprise> list = findByEnterpriseCode(enterpriseCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history enterrises before and after the current dm history enterrise in the ordered set where enterpriseCode = &#63;.
	 *
	 * @param id the primary key of the current dm history enterrise
	 * @param enterpriseCode the enterprise code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise[] findByEnterpriseCode_PrevAndNext(int id,
		String enterpriseCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = findByPrimaryKey(id);

		

		try {
			

			DmHistoryEnterprise[] array = new DmHistoryEnterprise[3];

			array[0] = getByEnterpriseCode_PrevAndNext(
					dmHistoryEnterrise, enterpriseCode, orderByComparator, true);

			array[1] = dmHistoryEnterrise;

			array[2] = getByEnterpriseCode_PrevAndNext(
					dmHistoryEnterrise, enterpriseCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryEnterprise getByEnterpriseCode_PrevAndNext(
		 DmHistoryEnterprise dmHistoryEnterrise,
		String enterpriseCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

		if (enterpriseCode == null) {
			query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
		}
		else {
			if (enterpriseCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
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
			query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (enterpriseCode != null) {
			builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryEnterrise);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryEnterprise> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm history enterrises where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @return the matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseTaxCode(
		String enterpriseTaxCode) throws SystemException {
		return findByEnterpriseTaxCode(enterpriseTaxCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history enterrises where enterpriseTaxCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @return the range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseTaxCode(
		String enterpriseTaxCode, int start, int end) throws SystemException {
		return findByEnterpriseTaxCode(enterpriseTaxCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history enterrises where enterpriseTaxCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findByEnterpriseTaxCode(
		String enterpriseTaxCode, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryEnterprise> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

			if (enterpriseTaxCode == null) {
				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
			}
			else {
				if (enterpriseTaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
				}
			}

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (enterpriseTaxCode != null) {
					builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
				}

				list = (List<DmHistoryEnterprise>)queryFactory.getResultList(builder);
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
	 * Returns the first dm history enterrise in the ordered set where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findByEnterpriseTaxCode_First(
		String enterpriseTaxCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchByEnterpriseTaxCode_First(enterpriseTaxCode,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enterpriseTaxCode=");
		msg.append(enterpriseTaxCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the first dm history enterrise in the ordered set where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchByEnterpriseTaxCode_First(
		String enterpriseTaxCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmHistoryEnterprise> list = findByEnterpriseTaxCode(enterpriseTaxCode,
				0, 1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise findByEnterpriseTaxCode_Last(
		String enterpriseTaxCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = fetchByEnterpriseTaxCode_Last(enterpriseTaxCode,
				orderByComparator);

		if (dmHistoryEnterrise != null) {
			return dmHistoryEnterrise;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("enterpriseTaxCode=");
		msg.append(enterpriseTaxCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmHistoryEnterriseException(msg.toString());
	}

	/**
	 * Returns the last dm history enterrise in the ordered set where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm history enterrise, or <code>null</code> if a matching dm history enterrise could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise fetchByEnterpriseTaxCode_Last(
		String enterpriseTaxCode, OrderByComparator orderByComparator)
		throws SystemException {
		int count = countByEnterpriseTaxCode(enterpriseTaxCode);

		List<DmHistoryEnterprise> list = findByEnterpriseTaxCode(enterpriseTaxCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm history enterrises before and after the current dm history enterrise in the ordered set where enterpriseTaxCode = &#63;.
	 *
	 * @param id the primary key of the current dm history enterrise
	 * @param enterpriseTaxCode the enterprise tax code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm history enterrise
	 * @throws vn.gt.dao.danhmuc.NoSuchDmHistoryEnterriseException if a dm history enterrise with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmHistoryEnterprise[] findByEnterpriseTaxCode_PrevAndNext(int id,
		String enterpriseTaxCode, OrderByComparator orderByComparator)
		throws NoSuchDmHistoryEnterriseException, SystemException {
		DmHistoryEnterprise dmHistoryEnterrise = findByPrimaryKey(id);

		

		try {
			

			DmHistoryEnterprise[] array = new DmHistoryEnterprise[3];

			array[0] = getByEnterpriseTaxCode_PrevAndNext(
					dmHistoryEnterrise, enterpriseTaxCode, orderByComparator,
					true);

			array[1] = dmHistoryEnterrise;

			array[2] = getByEnterpriseTaxCode_PrevAndNext(
					dmHistoryEnterrise, enterpriseTaxCode, orderByComparator,
					false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmHistoryEnterprise getByEnterpriseTaxCode_PrevAndNext(
		 DmHistoryEnterprise dmHistoryEnterrise,
		String enterpriseTaxCode, OrderByComparator orderByComparator,
		boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMHISTORYENTERRISE_WHERE);

		if (enterpriseTaxCode == null) {
			query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
		}
		else {
			if (enterpriseTaxCode.equals(StringPool.BLANK)) {
				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
			}
			else {
				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
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
			query.append(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (enterpriseTaxCode != null) {
			builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmHistoryEnterrise);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmHistoryEnterprise> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm history enterrises.
	 *
	 * @return the dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm history enterrises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @return the range of dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm history enterrises.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm history enterrises
	 * @param end the upper bound of the range of dm history enterrises (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmHistoryEnterprise> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmHistoryEnterprise> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMHISTORYENTERRISE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMHISTORYENTERRISE.concat(DmHistoryEnterriseModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmHistoryEnterprise>) queryFactory.getResultList(builder);
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
	 * Removes all the dm history enterrises where syncVersion = &#63; from the database.
	 *
	 * @param syncVersion the sync version
	 * @throws SystemException if a system exception occurred
	 */
	public void removeBySyncVersion(String syncVersion)
		throws SystemException {
		for (DmHistoryEnterprise dmHistoryEnterrise : findBySyncVersion(
				syncVersion)) {
			repository.delete(dmHistoryEnterrise);
		}
	}

	/**
	 * Removes all the dm history enterrises where enterpriseCode = &#63; from the database.
	 *
	 * @param enterpriseCode the enterprise code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEnterpriseCode(String enterpriseCode)
		throws SystemException {
		for (DmHistoryEnterprise dmHistoryEnterrise : findByEnterpriseCode(
				enterpriseCode)) {
			repository.delete(dmHistoryEnterrise);
		}
	}

	/**
	 * Removes all the dm history enterrises where enterpriseTaxCode = &#63; from the database.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByEnterpriseTaxCode(String enterpriseTaxCode)
		throws SystemException {
		for (DmHistoryEnterprise dmHistoryEnterrise : findByEnterpriseTaxCode(
				enterpriseTaxCode)) {
			repository.delete(dmHistoryEnterrise);
		}
	}

	/**
	 * Removes all the dm history enterrises from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmHistoryEnterprise dmHistoryEnterrise : findAll()) {
			repository.delete(dmHistoryEnterrise);
		}
	}

	/**
	 * Returns the number of dm history enterrises where syncVersion = &#63;.
	 *
	 * @param syncVersion the sync version
	 * @return the number of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public int countBySyncVersion(String syncVersion) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYENTERRISE_WHERE);

			if (syncVersion == null) {
				query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1);
			}
			else {
				if (syncVersion.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3);
				}
				else {
					query.append(_FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (syncVersion != null) {
					builder.appendNamedParameterMap("syncVersion", syncVersion);
				}

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
	 * Returns the number of dm history enterrises where enterpriseCode = &#63;.
	 *
	 * @param enterpriseCode the enterprise code
	 * @return the number of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEnterpriseCode(String enterpriseCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYENTERRISE_WHERE);

			if (enterpriseCode == null) {
				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
			}
			else {
				if (enterpriseCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (enterpriseCode != null) {
					builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
				}

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
	 * Returns the number of dm history enterrises where enterpriseTaxCode = &#63;.
	 *
	 * @param enterpriseTaxCode the enterprise tax code
	 * @return the number of matching dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public int countByEnterpriseTaxCode(String enterpriseTaxCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMHISTORYENTERRISE_WHERE);

			if (enterpriseTaxCode == null) {
				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
			}
			else {
				if (enterpriseTaxCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				if (enterpriseTaxCode != null) {
					builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
				}

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
	 * Returns the number of dm history enterrises.
	 *
	 * @return the number of dm history enterrises
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMHISTORYENTERRISE).build();

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
	 * Initializes the dm history enterrise persistence.
	 */
	private static final String _SQL_SELECT_DMHISTORYENTERRISE = "SELECT dmHistoryEnterrise FROM DmHistoryEnterprise dmHistoryEnterrise";
	private static final String _SQL_SELECT_DMHISTORYENTERRISE_WHERE = "SELECT dmHistoryEnterrise FROM DmHistoryEnterprise dmHistoryEnterrise WHERE ";
	private static final String _SQL_COUNT_DMHISTORYENTERRISE = "SELECT COUNT(dmHistoryEnterrise) FROM DmHistoryEnterprise dmHistoryEnterrise";
	private static final String _SQL_COUNT_DMHISTORYENTERRISE_WHERE = "SELECT COUNT(dmHistoryEnterrise) FROM DmHistoryEnterprise dmHistoryEnterrise WHERE ";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_1 = "dmHistoryEnterrise.syncVersion IS NULL";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_2 = "dmHistoryEnterrise.syncVersion =:syncVersion";
	private static final String _FINDER_COLUMN_SYNCVERSION_SYNCVERSION_3 = "(dmHistoryEnterrise.syncVersion IS NULL OR dmHistoryEnterrise.syncVersion =:syncVersion)";
	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1 = "dmHistoryEnterrise.enterpriseCode IS NULL";
	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2 = "dmHistoryEnterrise.enterpriseCode =:enterpriseCode";
	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3 = "(dmHistoryEnterrise.enterpriseCode IS NULL OR dmHistoryEnterrise.enterpriseCode =:enterpriseCode)";
	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1 =
		"dmHistoryEnterrise.enterpriseTaxCode IS NULL";
	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2 =
		"dmHistoryEnterrise.enterpriseTaxCode =:enterpriseTaxCode";
	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3 =
		"(dmHistoryEnterrise.enterpriseTaxCode IS NULL OR dmHistoryEnterrise.enterpriseTaxCode =:enterpriseTaxCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmHistoryEnterrise.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmHistoryEnterprise exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmHistoryEnterprise exists with the key {";
	

	
}
