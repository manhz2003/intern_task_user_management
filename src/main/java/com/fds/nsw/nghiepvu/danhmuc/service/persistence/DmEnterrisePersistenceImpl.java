///**
// * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
// *
// * This library is free software; you can redistribute it and/or modify it under
// * the terms of the GNU Lesser General Public License as published by the Free
// * Software Foundation; either version 2.1 of the License, or (at your option)
// * any later version.
// *
// * This library is distributed in the hope that it will be useful, but WITHOUT
// * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
// * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
// * details.
// */
//
//package com.fds.nsw.nghiepvu.danhmuc.service.persistence;
//
//import java.io.Serializable;
//import java.sql.Types;
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.fds.flex.common.utility.string.StringBundler;
//import com.fds.flex.common.utility.string.StringPool;
//import com.fds.nsw.kernel.dao.orm.BasePersistence;
//import com.fds.nsw.kernel.dao.orm.QueryUtil;
//import com.fds.nsw.kernel.dao.orm.jpa.QueryBuilder;
//import com.fds.nsw.kernel.dao.orm.jpa.QueryFactory;
//import com.fds.nsw.kernel.exception.SystemException;
//import com.fds.nsw.kernel.orm.exception.NoSuchModelException;
//import com.fds.nsw.kernel.util.OrderByComparator;
//import com.fds.nsw.nghiepvu.model.DmEnterrise;
//import com.fds.nsw.nghiepvu.service.exception.*;
//import com.fds.nsw.nghiepvu.repository.DmEnterriseRepository;
//import com.fds.nsw.nghiepvu.modelImpl.DmEnterriseModelImpl;
//
//
//import lombok.extern.slf4j.Slf4j;
//
//@Slf4j
//@Service
//public class DmEnterrisePersistenceImpl extends BasePersistence {
//	@Autowired
//	DmEnterriseRepository repository;
//	@Autowired
//	@Qualifier("blQueryFactory")
//	QueryFactory<DmEnterrise> queryFactory;
//	/*
//	 * NOTE FOR DEVELOPERS:
//	 *
//	 * Never modify or reference this class directly. Always use {@link DmEnterriseUtil} to access the dm enterrise persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
//	 */
//	public DmEnterrise create(int id) {
//		DmEnterrise dmEnterrise = new DmEnterrise();
//
//
//		//dmEnterrise.setPrimaryKey(id);
//
//		return dmEnterrise;
//	}
//
//	/**
//	 * Removes the dm enterrise with the primary key from the database. Also notifies the appropriate model listeners.
//	 *
//	 * @param id the primary key of the dm enterrise
//	 * @return the dm enterrise that was removed
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise remove(int id)
//		throws NoSuchDmEnterriseException, SystemException {
//		return remove(Integer.valueOf(id));
//	}
//
//	/**
//	 * Removes the dm enterrise with the primary key from the database. Also notifies the appropriate model listeners.
//	 *
//	 * @param primaryKey the primary key of the dm enterrise
//	 * @return the dm enterrise that was removed
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//
//	public DmEnterrise remove(Serializable primaryKey)
//		throws NoSuchDmEnterriseException, SystemException {
//
//
//		try {
//
//
//			DmEnterrise dmEnterrise = findByPrimaryKey(primaryKey);
//
//			if (dmEnterrise == null) {
//				if (log.isWarnEnabled()) {
//					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
//				}
//
//				throw new NoSuchDmEnterriseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
//					primaryKey);
//			}
//
//			repository.delete(dmEnterrise);
//			return dmEnterrise;
//		}
//		catch (NoSuchDmEnterriseException nsee) {
//			throw nsee;
//		}
//		catch (Exception e) {
//			throw processException(e);
//		}
//		finally {
//
//		}
//	}
//
//
//	protected DmEnterrise removeImpl(DmEnterrise dmEnterrise)
//		throws SystemException {
//		try {
//			repository.delete(dmEnterrise);
//		} catch (Exception e) {
//			throw processException(e);
//		} finally {
//			// TODO update cache
//		}
//
//		return dmEnterrise;
//	}
//
//
//	public DmEnterrise updateImpl(
//		DmEnterrise dmEnterrise, boolean merge)
//		throws SystemException {
//		try {
//
//			repository.saveAndFlush(dmEnterrise);
//
//		} catch (Exception e) {
//			throw processException(e);
//		} finally {
//			// TODO update cache
//		}
//
//		return dmEnterrise;
//	}
//
//
//	public DmEnterrise findByPrimaryKey(Serializable primaryKey)
//		throws NoSuchModelException, SystemException {
//		return findByPrimaryKey(((Integer)primaryKey).intValue());
//	}
//
//	/**
//	 * Returns the dm enterrise with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmEnterriseException} if it could not be found.
//	 *
//	 * @param id the primary key of the dm enterrise
//	 * @return the dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise findByPrimaryKey(int id)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = fetchByPrimaryKey(id);
//
//		if (dmEnterrise == null) {
//			if (log.isWarnEnabled()) {
//				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
//			}
//
//			throw new NoSuchDmEnterriseException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
//				id);
//		}
//
//		return dmEnterrise;
//	}
//
//	/**
//	 * Returns the dm enterrise with the primary key or returns <code>null</code> if it could not be found.
//	 *
//	 * @param primaryKey the primary key of the dm enterrise
//	 * @return the dm enterrise, or <code>null</code> if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//
//	public DmEnterrise fetchByPrimaryKey(Serializable primaryKey)
//		throws SystemException {
//		return fetchByPrimaryKey(((Integer)primaryKey).intValue());
//	}
//
//	/**
//	 * Returns the dm enterrise with the primary key or returns <code>null</code> if it could not be found.
//	 *
//	 * @param id the primary key of the dm enterrise
//	 * @return the dm enterrise, or <code>null</code> if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise fetchByPrimaryKey(int id) throws SystemException {
//		DmEnterrise dmEnterrise = null;
//
//
//
//		if (dmEnterrise == null) {
//
//
//			boolean hasException = false;
//
//			try {
//
//
//				Optional<DmEnterrise> optional = repository.findById(id);
//				if (optional.isPresent()) {
//					dmEnterrise = optional.get();
//				}
//			}
//			catch (Exception e) {
//				hasException = true;
//
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return dmEnterrise;
//	}
//
//	/**
//	 * Returns all the dm enterrises where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @return the matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseCode(String enterpriseCode)
//		throws SystemException {
//		return findByEnterpriseCode(enterpriseCode, QueryUtil.ALL_POS,
//			QueryUtil.ALL_POS, null);
//	}
//
//	/**
//	 * Returns a range of all the dm enterrises where enterpriseCode = &#63;.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @return the range of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseCode(String enterpriseCode,
//		int start, int end) throws SystemException {
//		return findByEnterpriseCode(enterpriseCode, start, end, null);
//	}
//
//	/**
//	 * Returns an ordered range of all the dm enterrises where enterpriseCode = &#63;.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
//	 * @return the ordered range of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseCode(String enterpriseCode,
//		int start, int end, OrderByComparator orderByComparator)
//		throws SystemException {
//		List<DmEnterrise> list = null;
//		if (list == null) {
//			StringBundler query = null;
//
//			if (orderByComparator != null) {
//				query = new StringBundler(3 +
//						(orderByComparator.getOrderByFields().length * 3));
//			}
//			else {
//				query = new StringBundler(3);
//			}
//
//			query.append(_SQL_SELECT_DMENTERRISE_WHERE);
//
//			if (enterpriseCode == null) {
//				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
//			}
//			else {
//				if (enterpriseCode.equals(StringPool.BLANK)) {
//					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
//				}
//				else {
//					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
//				}
//			}
//
//			if (orderByComparator != null) {
//				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
//					orderByComparator);
//			}
//
//			else {
//				query.append(DmEnterriseModelImpl.ORDER_BY_JPQL);
//			}
//
//			String sql = query.toString();
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//
//
//				if (enterpriseCode != null) {
//					builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
//				}
//
//				list = (List<DmEnterrise>)queryFactory.getResultList(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return list;
//	}
//
//	/**
//	 * Returns the first dm enterrise in the ordered set where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the first matching dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise findByEnterpriseCode_First(String enterpriseCode,
//		OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = fetchByEnterpriseCode_First(enterpriseCode,
//				orderByComparator);
//
//		if (dmEnterrise != null) {
//			return dmEnterrise;
//		}
//
//		StringBundler msg = new StringBundler(4);
//
//		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
//
//		msg.append("enterpriseCode=");
//		msg.append(enterpriseCode);
//
//		msg.append(StringPool.CLOSE_CURLY_BRACE);
//
//		throw new NoSuchDmEnterriseException(msg.toString());
//	}
//
//	/**
//	 * Returns the first dm enterrise in the ordered set where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the first matching dm enterrise, or <code>null</code> if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise fetchByEnterpriseCode_First(String enterpriseCode,
//		OrderByComparator orderByComparator) throws SystemException {
//		List<DmEnterrise> list = findByEnterpriseCode(enterpriseCode, 0, 1,
//				orderByComparator);
//
//		if (!list.isEmpty()) {
//			return list.get(0);
//		}
//
//		return null;
//	}
//
//	/**
//	 * Returns the last dm enterrise in the ordered set where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the last matching dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise findByEnterpriseCode_Last(String enterpriseCode,
//		OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = fetchByEnterpriseCode_Last(enterpriseCode,
//				orderByComparator);
//
//		if (dmEnterrise != null) {
//			return dmEnterrise;
//		}
//
//		StringBundler msg = new StringBundler(4);
//
//		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
//
//		msg.append("enterpriseCode=");
//		msg.append(enterpriseCode);
//
//		msg.append(StringPool.CLOSE_CURLY_BRACE);
//
//		throw new NoSuchDmEnterriseException(msg.toString());
//	}
//
//	/**
//	 * Returns the last dm enterrise in the ordered set where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the last matching dm enterrise, or <code>null</code> if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise fetchByEnterpriseCode_Last(String enterpriseCode,
//		OrderByComparator orderByComparator) throws SystemException {
//		int count = countByEnterpriseCode(enterpriseCode);
//
//		List<DmEnterrise> list = findByEnterpriseCode(enterpriseCode,
//				count - 1, count, orderByComparator);
//
//		if (!list.isEmpty()) {
//			return list.get(0);
//		}
//
//		return null;
//	}
//
//	/**
//	 * Returns the dm enterrises before and after the current dm enterrise in the ordered set where enterpriseCode = &#63;.
//	 *
//	 * @param id the primary key of the current dm enterrise
//	 * @param enterpriseCode the enterprise code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the previous, current, and next dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise[] findByEnterpriseCode_PrevAndNext(int id,
//		String enterpriseCode, OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = findByPrimaryKey(id);
//
//
//
//		try {
//
//
//			DmEnterrise[] array = new DmEnterrise[3];
//
//			array[0] = getByEnterpriseCode_PrevAndNext(dmEnterrise,
//					enterpriseCode, orderByComparator, true);
//
//			array[1] = dmEnterrise;
//
//			array[2] = getByEnterpriseCode_PrevAndNext(dmEnterrise,
//					enterpriseCode, orderByComparator, false);
//
//			return array;
//		}
//		catch (Exception e) {
//			throw processException(e);
//		}
//		finally {
//
//		}
//	}
//
//	protected DmEnterrise getByEnterpriseCode_PrevAndNext(
//		DmEnterrise dmEnterrise, String enterpriseCode,
//		OrderByComparator orderByComparator, boolean previous) {
//		StringBundler query = null;
//
//		if (orderByComparator != null) {
//			query = new StringBundler(6 +
//					(orderByComparator.getOrderByFields().length * 6));
//		}
//		else {
//			query = new StringBundler(3);
//		}
//
//		query.append(_SQL_SELECT_DMENTERRISE_WHERE);
//
//		if (enterpriseCode == null) {
//			query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
//		}
//		else {
//			if (enterpriseCode.equals(StringPool.BLANK)) {
//				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
//			}
//			else {
//				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
//			}
//		}
//
//		if (orderByComparator != null) {
//			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
//
//			if (orderByConditionFields.length > 0) {
//				query.append(WHERE_AND);
//			}
//
//			for (int i = 0; i < orderByConditionFields.length; i++) {
//				query.append(_ORDER_BY_ENTITY_ALIAS);
//				query.append(orderByConditionFields[i]);
//
//				if ((i + 1) < orderByConditionFields.length) {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(WHERE_GREATER_THAN_HAS_NEXT);
//					}
//					else {
//						query.append(WHERE_LESSER_THAN_HAS_NEXT);
//					}
//				}
//				else {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(WHERE_GREATER_THAN);
//					}
//					else {
//						query.append(WHERE_LESSER_THAN);
//					}
//				}
//			}
//
//			query.append(ORDER_BY_CLAUSE);
//
//			String[] orderByFields = orderByComparator.getOrderByFields();
//
//			for (int i = 0; i < orderByFields.length; i++) {
//				query.append(_ORDER_BY_ENTITY_ALIAS);
//				query.append(orderByFields[i]);
//
//				if ((i + 1) < orderByFields.length) {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(ORDER_BY_ASC_HAS_NEXT);
//					}
//					else {
//						query.append(ORDER_BY_DESC_HAS_NEXT);
//					}
//				}
//				else {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(ORDER_BY_ASC);
//					}
//					else {
//						query.append(ORDER_BY_DESC);
//					}
//				}
//			}
//		}
//
//		else {
//			query.append(DmEnterriseModelImpl.ORDER_BY_JPQL);
//		}
//
//		String sql = query.toString();
//
//		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
//
//
//
//		if (enterpriseCode != null) {
//			builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
//		}
//
//		if (orderByComparator != null) {
//			Object[] values = orderByComparator.getOrderByConditionValues(dmEnterrise);
//
//						/*
//			for (Object value : values) {
//				builder.appendNamedParameterMap("value", value);
//			}
//			*/
//		}
//
//		List<DmEnterrise> list = queryFactory.getResultList(builder);
//
//		if (list.size() == 2) {
//			return list.get(1);
//		}
//		else {
//			return null;
//		}
//	}
//
//	/**
//	 * Returns all the dm enterrises where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @return the matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseTaxCode(String enterpriseTaxCode)
//		throws SystemException {
//		return findByEnterpriseTaxCode(enterpriseTaxCode, QueryUtil.ALL_POS,
//			QueryUtil.ALL_POS, null);
//	}
//
//	/**
//	 * Returns a range of all the dm enterrises where enterpriseTaxCode = &#63;.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @return the range of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseTaxCode(String enterpriseTaxCode,
//		int start, int end) throws SystemException {
//		return findByEnterpriseTaxCode(enterpriseTaxCode, start, end, null);
//	}
//
//	/**
//	 * Returns an ordered range of all the dm enterrises where enterpriseTaxCode = &#63;.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
//	 * @return the ordered range of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findByEnterpriseTaxCode(String enterpriseTaxCode,
//		int start, int end, OrderByComparator orderByComparator)
//		throws SystemException {
//		List<DmEnterrise> list = null;
//		if (list == null) {
//			StringBundler query = null;
//
//			if (orderByComparator != null) {
//				query = new StringBundler(3 +
//						(orderByComparator.getOrderByFields().length * 3));
//			}
//			else {
//				query = new StringBundler(3);
//			}
//
//			query.append(_SQL_SELECT_DMENTERRISE_WHERE);
//
//			if (enterpriseTaxCode == null) {
//				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
//			}
//			else {
//				if (enterpriseTaxCode.equals(StringPool.BLANK)) {
//					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
//				}
//				else {
//					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
//				}
//			}
//
//			if (orderByComparator != null) {
//				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
//					orderByComparator);
//			}
//
//			else {
//				query.append(DmEnterriseModelImpl.ORDER_BY_JPQL);
//			}
//
//			String sql = query.toString();
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//
//
//				if (enterpriseTaxCode != null) {
//					builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
//				}
//
//				list = (List<DmEnterrise>)queryFactory.getResultList(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return list;
//	}
//
//	/**
//	 * Returns the first dm enterrise in the ordered set where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the first matching dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise findByEnterpriseTaxCode_First(String enterpriseTaxCode,
//		OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = fetchByEnterpriseTaxCode_First(enterpriseTaxCode,
//				orderByComparator);
//
//		if (dmEnterrise != null) {
//			return dmEnterrise;
//		}
//
//		StringBundler msg = new StringBundler(4);
//
//		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
//
//		msg.append("enterpriseTaxCode=");
//		msg.append(enterpriseTaxCode);
//
//		msg.append(StringPool.CLOSE_CURLY_BRACE);
//
//		throw new NoSuchDmEnterriseException(msg.toString());
//	}
//
//	/**
//	 * Returns the first dm enterrise in the ordered set where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the first matching dm enterrise, or <code>null</code> if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise fetchByEnterpriseTaxCode_First(
//		String enterpriseTaxCode, OrderByComparator orderByComparator)
//		throws SystemException {
//		List<DmEnterrise> list = findByEnterpriseTaxCode(enterpriseTaxCode, 0,
//				1, orderByComparator);
//
//		if (!list.isEmpty()) {
//			return list.get(0);
//		}
//
//		return null;
//	}
//
//	/**
//	 * Returns the last dm enterrise in the ordered set where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the last matching dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise findByEnterpriseTaxCode_Last(String enterpriseTaxCode,
//		OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = fetchByEnterpriseTaxCode_Last(enterpriseTaxCode,
//				orderByComparator);
//
//		if (dmEnterrise != null) {
//			return dmEnterrise;
//		}
//
//		StringBundler msg = new StringBundler(4);
//
//		msg.append(_NO_SUCH_ENTITY_WITH_KEY);
//
//		msg.append("enterpriseTaxCode=");
//		msg.append(enterpriseTaxCode);
//
//		msg.append(StringPool.CLOSE_CURLY_BRACE);
//
//		throw new NoSuchDmEnterriseException(msg.toString());
//	}
//
//	/**
//	 * Returns the last dm enterrise in the ordered set where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the last matching dm enterrise, or <code>null</code> if a matching dm enterrise could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise fetchByEnterpriseTaxCode_Last(String enterpriseTaxCode,
//		OrderByComparator orderByComparator) throws SystemException {
//		int count = countByEnterpriseTaxCode(enterpriseTaxCode);
//
//		List<DmEnterrise> list = findByEnterpriseTaxCode(enterpriseTaxCode,
//				count - 1, count, orderByComparator);
//
//		if (!list.isEmpty()) {
//			return list.get(0);
//		}
//
//		return null;
//	}
//
//	/**
//	 * Returns the dm enterrises before and after the current dm enterrise in the ordered set where enterpriseTaxCode = &#63;.
//	 *
//	 * @param id the primary key of the current dm enterrise
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
//	 * @return the previous, current, and next dm enterrise
//	 * @throws vn.gt.dao.danhmuc.NoSuchDmEnterriseException if a dm enterrise with the primary key could not be found
//	 * @throws SystemException if a system exception occurred
//	 */
//	public DmEnterrise[] findByEnterpriseTaxCode_PrevAndNext(int id,
//		String enterpriseTaxCode, OrderByComparator orderByComparator)
//		throws NoSuchDmEnterriseException, SystemException {
//		DmEnterrise dmEnterrise = findByPrimaryKey(id);
//
//
//
//		try {
//
//
//			DmEnterrise[] array = new DmEnterrise[3];
//
//			array[0] = getByEnterpriseTaxCode_PrevAndNext(dmEnterrise,
//					enterpriseTaxCode, orderByComparator, true);
//
//			array[1] = dmEnterrise;
//
//			array[2] = getByEnterpriseTaxCode_PrevAndNext(dmEnterrise,
//					enterpriseTaxCode, orderByComparator, false);
//
//			return array;
//		}
//		catch (Exception e) {
//			throw processException(e);
//		}
//		finally {
//
//		}
//	}
//
//	protected DmEnterrise getByEnterpriseTaxCode_PrevAndNext(
//		DmEnterrise dmEnterrise, String enterpriseTaxCode,
//		OrderByComparator orderByComparator, boolean previous) {
//		StringBundler query = null;
//
//		if (orderByComparator != null) {
//			query = new StringBundler(6 +
//					(orderByComparator.getOrderByFields().length * 6));
//		}
//		else {
//			query = new StringBundler(3);
//		}
//
//		query.append(_SQL_SELECT_DMENTERRISE_WHERE);
//
//		if (enterpriseTaxCode == null) {
//			query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
//		}
//		else {
//			if (enterpriseTaxCode.equals(StringPool.BLANK)) {
//				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
//			}
//			else {
//				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
//			}
//		}
//
//		if (orderByComparator != null) {
//			String[] orderByConditionFields = orderByComparator.getOrderByConditionFields();
//
//			if (orderByConditionFields.length > 0) {
//				query.append(WHERE_AND);
//			}
//
//			for (int i = 0; i < orderByConditionFields.length; i++) {
//				query.append(_ORDER_BY_ENTITY_ALIAS);
//				query.append(orderByConditionFields[i]);
//
//				if ((i + 1) < orderByConditionFields.length) {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(WHERE_GREATER_THAN_HAS_NEXT);
//					}
//					else {
//						query.append(WHERE_LESSER_THAN_HAS_NEXT);
//					}
//				}
//				else {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(WHERE_GREATER_THAN);
//					}
//					else {
//						query.append(WHERE_LESSER_THAN);
//					}
//				}
//			}
//
//			query.append(ORDER_BY_CLAUSE);
//
//			String[] orderByFields = orderByComparator.getOrderByFields();
//
//			for (int i = 0; i < orderByFields.length; i++) {
//				query.append(_ORDER_BY_ENTITY_ALIAS);
//				query.append(orderByFields[i]);
//
//				if ((i + 1) < orderByFields.length) {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(ORDER_BY_ASC_HAS_NEXT);
//					}
//					else {
//						query.append(ORDER_BY_DESC_HAS_NEXT);
//					}
//				}
//				else {
//					if (orderByComparator.isAscending() ^ previous) {
//						query.append(ORDER_BY_ASC);
//					}
//					else {
//						query.append(ORDER_BY_DESC);
//					}
//				}
//			}
//		}
//
//		else {
//			query.append(DmEnterriseModelImpl.ORDER_BY_JPQL);
//		}
//
//		String sql = query.toString();
//
//		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();
//
//
//
//		if (enterpriseTaxCode != null) {
//			builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
//		}
//
//		if (orderByComparator != null) {
//			Object[] values = orderByComparator.getOrderByConditionValues(dmEnterrise);
//
//						/*
//			for (Object value : values) {
//				builder.appendNamedParameterMap("value", value);
//			}
//			*/
//		}
//
//		List<DmEnterrise> list = queryFactory.getResultList(builder);
//
//		if (list.size() == 2) {
//			return list.get(1);
//		}
//		else {
//			return null;
//		}
//	}
//
//	/**
//	 * Returns all the dm enterrises.
//	 *
//	 * @return the dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findAll() throws SystemException {
//		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
//	}
//
//	/**
//	 * Returns a range of all the dm enterrises.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @return the range of dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findAll(int start, int end)
//		throws SystemException {
//		return findAll(start, end, null);
//	}
//
//	/**
//	 * Returns an ordered range of all the dm enterrises.
//	 *
//	 * <p>
//	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
//	 * </p>
//	 *
//	 * @param start the lower bound of the range of dm enterrises
//	 * @param end the upper bound of the range of dm enterrises (not inclusive)
//	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
//	 * @return the ordered range of dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public List<DmEnterrise> findAll(int start, int end,
//		OrderByComparator orderByComparator) throws SystemException {
//		List<DmEnterrise> list = null;
//		if (list == null) {
//			StringBundler query = null;
//			String sql = null;
//
//			if (orderByComparator != null) {
//				query = new StringBundler(2 +
//						(orderByComparator.getOrderByFields().length * 3));
//
//				query.append(_SQL_SELECT_DMENTERRISE);
//
//				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
//					orderByComparator);
//
//				sql = query.toString();
//			}
//			else {
//				sql = _SQL_SELECT_DMENTERRISE.concat(DmEnterriseModelImpl.ORDER_BY_JPQL);
//			}
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//				list = (List<DmEnterrise>) queryFactory.getResultList(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//
//
//
//			}
//		}
//
//		return list;
//	}
//
//	/**
//	 * Removes all the dm enterrises where enterpriseCode = &#63; from the database.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @throws SystemException if a system exception occurred
//	 */
//	public void removeByEnterpriseCode(String enterpriseCode)
//		throws SystemException {
//		for (DmEnterrise dmEnterrise : findByEnterpriseCode(enterpriseCode)) {
//			repository.delete(dmEnterrise);
//		}
//	}
//
//	/**
//	 * Removes all the dm enterrises where enterpriseTaxCode = &#63; from the database.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @throws SystemException if a system exception occurred
//	 */
//	public void removeByEnterpriseTaxCode(String enterpriseTaxCode)
//		throws SystemException {
//		for (DmEnterrise dmEnterrise : findByEnterpriseTaxCode(
//				enterpriseTaxCode)) {
//			repository.delete(dmEnterrise);
//		}
//	}
//
//	/**
//	 * Removes all the dm enterrises from the database.
//	 *
//	 * @throws SystemException if a system exception occurred
//	 */
//	public void removeAll() throws SystemException {
//		for (DmEnterrise dmEnterrise : findAll()) {
//			repository.delete(dmEnterrise);
//		}
//	}
//
//	/**
//	 * Returns the number of dm enterrises where enterpriseCode = &#63;.
//	 *
//	 * @param enterpriseCode the enterprise code
//	 * @return the number of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public int countByEnterpriseCode(String enterpriseCode)
//		throws SystemException {
//		Long count = null;
//		if (count == null) {
//			StringBundler query = new StringBundler(2);
//
//			query.append(_SQL_COUNT_DMENTERRISE_WHERE);
//
//			if (enterpriseCode == null) {
//				query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1);
//			}
//			else {
//				if (enterpriseCode.equals(StringPool.BLANK)) {
//					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3);
//				}
//				else {
//					query.append(_FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2);
//				}
//			}
//
//			String sql = query.toString();
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//
//
//				if (enterpriseCode != null) {
//					builder.appendNamedParameterMap("enterpriseCode", enterpriseCode);
//				}
//
//				count = (Long)queryFactory.getSingleResult(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//				if (count == null) {
//					count = Long.valueOf(0);
//				}
//
//
//
//
//			}
//		}
//
//		return count.intValue();
//	}
//
//	/**
//	 * Returns the number of dm enterrises where enterpriseTaxCode = &#63;.
//	 *
//	 * @param enterpriseTaxCode the enterprise tax code
//	 * @return the number of matching dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public int countByEnterpriseTaxCode(String enterpriseTaxCode)
//		throws SystemException {
//		Long count = null;
//		if (count == null) {
//			StringBundler query = new StringBundler(2);
//
//			query.append(_SQL_COUNT_DMENTERRISE_WHERE);
//
//			if (enterpriseTaxCode == null) {
//				query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1);
//			}
//			else {
//				if (enterpriseTaxCode.equals(StringPool.BLANK)) {
//					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3);
//				}
//				else {
//					query.append(_FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2);
//				}
//			}
//
//			String sql = query.toString();
//
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();
//
//
//
//				if (enterpriseTaxCode != null) {
//					builder.appendNamedParameterMap("enterpriseTaxCode", enterpriseTaxCode);
//				}
//
//				count = (Long)queryFactory.getSingleResult(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//				if (count == null) {
//					count = Long.valueOf(0);
//				}
//
//
//
//
//			}
//		}
//
//		return count.intValue();
//	}
//
//	/**
//	 * Returns the number of dm enterrises.
//	 *
//	 * @return the number of dm enterrises
//	 * @throws SystemException if a system exception occurred
//	 */
//	public int countAll() throws SystemException {
//		Long count = null;
//
//		if (count == null) {
//
//
//			try {
//
//
//				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMENTERRISE).build();
//
//				count = (Long)queryFactory.getSingleResult(builder);
//			}
//			catch (Exception e) {
//				throw processException(e);
//			}
//			finally {
//				if (count == null) {
//					count = Long.valueOf(0);
//				}
//
//
//
//
//			}
//		}
//
//		return count.intValue();
//	}
//
//	/**
//	 * Initializes the dm enterrise persistence.
//	 */
//	private static final String _SQL_SELECT_DMENTERRISE = "SELECT dmEnterrise FROM DmEnterrise dmEnterrise";
//	private static final String _SQL_SELECT_DMENTERRISE_WHERE = "SELECT dmEnterrise FROM DmEnterrise dmEnterrise WHERE ";
//	private static final String _SQL_COUNT_DMENTERRISE = "SELECT COUNT(dmEnterrise) FROM DmEnterrise dmEnterrise";
//	private static final String _SQL_COUNT_DMENTERRISE_WHERE = "SELECT COUNT(dmEnterrise) FROM DmEnterrise dmEnterrise WHERE ";
//	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_1 = "dmEnterrise.enterpriseCode IS NULL";
//	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_2 = "dmEnterrise.enterpriseCode =:enterpriseCode";
//	private static final String _FINDER_COLUMN_ENTERPRISECODE_ENTERPRISECODE_3 = "(dmEnterrise.enterpriseCode IS NULL OR dmEnterrise.enterpriseCode =:enterpriseCode)";
//	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_1 =
//		"dmEnterrise.enterpriseTaxCode IS NULL";
//	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_2 =
//		"dmEnterrise.enterpriseTaxCode =:enterpriseTaxCode";
//	private static final String _FINDER_COLUMN_ENTERPRISETAXCODE_ENTERPRISETAXCODE_3 =
//		"(dmEnterrise.enterpriseTaxCode IS NULL OR dmEnterrise.enterpriseTaxCode =:enterpriseTaxCode)";
//	private static final String _ORDER_BY_ENTITY_ALIAS = "dmEnterrise.";
//	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmEnterrise exists with the primary key ";
//	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmEnterrise exists with the key {";
//
//
//
//}
