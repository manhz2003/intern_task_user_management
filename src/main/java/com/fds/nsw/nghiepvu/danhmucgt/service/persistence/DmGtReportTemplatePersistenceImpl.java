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
import com.fds.nsw.nghiepvu.model.DmGtReportTemplate;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmGtReportTemplateRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmGtReportTemplateModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmGtReportTemplatePersistenceImpl extends BasePersistence {
	@Autowired
	DmGtReportTemplateRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmGtReportTemplate> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmGtReportTemplateUtil} to access the dm gt report template persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmGtReportTemplate create(long id) {
		DmGtReportTemplate dmGtReportTemplate = new DmGtReportTemplate();

		
		//dmGtReportTemplate.setPrimaryKey(id);

		return dmGtReportTemplate;
	}

	/**
	 * Removes the dm gt report template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param id the primary key of the dm gt report template
	 * @return the dm gt report template that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate remove(long id)
		throws NoSuchDmGtReportTemplateException, SystemException {
		return remove(Long.valueOf(id));
	}

	/**
	 * Removes the dm gt report template with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm gt report template
	 * @return the dm gt report template that was removed
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtReportTemplate remove(Serializable primaryKey)
		throws NoSuchDmGtReportTemplateException, SystemException {
		

		try {
			

			DmGtReportTemplate dmGtReportTemplate = findByPrimaryKey(primaryKey);

			if (dmGtReportTemplate == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmGtReportTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmGtReportTemplate);
			return dmGtReportTemplate;
		}
		catch (NoSuchDmGtReportTemplateException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmGtReportTemplate remove(DmGtReportTemplate DmGtReportTemplate) throws SystemException {
	removeImpl(DmGtReportTemplate);
	return DmGtReportTemplate;
}

protected DmGtReportTemplate removeImpl(
		DmGtReportTemplate dmGtReportTemplate) throws SystemException {
		try {
			repository.delete(dmGtReportTemplate);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtReportTemplate;
	}

	
	public DmGtReportTemplate updateImpl(
		DmGtReportTemplate dmGtReportTemplate,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmGtReportTemplate);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmGtReportTemplate;
	}

	
	public DmGtReportTemplate findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt report template with the primary key or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException} if it could not be found.
	 *
	 * @param id the primary key of the dm gt report template
	 * @return the dm gt report template
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate findByPrimaryKey(long id)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = fetchByPrimaryKey(id);

		if (dmGtReportTemplate == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + id);
			}

			throw new NoSuchDmGtReportTemplateException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				id);
		}

		return dmGtReportTemplate;
	}

	/**
	 * Returns the dm gt report template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm gt report template
	 * @return the dm gt report template, or <code>null</code> if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmGtReportTemplate fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm gt report template with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param id the primary key of the dm gt report template
	 * @return the dm gt report template, or <code>null</code> if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate fetchByPrimaryKey(long id)
		throws SystemException {
		DmGtReportTemplate dmGtReportTemplate = null;

		

		if (dmGtReportTemplate == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmGtReportTemplate> optional = repository.findById(id);
				if (optional.isPresent()) {
					dmGtReportTemplate = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmGtReportTemplate;
	}

	/**
	 * Returns the dm gt report template where reportCode = &#63; or throws a {@link vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException} if it could not be found.
	 *
	 * @param reportCode the report code
	 * @return the matching dm gt report template
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate findByReportCode(int reportCode)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = fetchByReportCode(reportCode);

		if (dmGtReportTemplate == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("reportCode=");
			msg.append(reportCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmGtReportTemplateException(msg.toString());
		}

		return dmGtReportTemplate;
	}

	/**
	 * Returns the dm gt report template where reportCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param reportCode the report code
	 * @return the matching dm gt report template, or <code>null</code> if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate fetchByReportCode(int reportCode)
		throws SystemException {
		return fetchByReportCode(reportCode, true);
	}

	/**
	 * Returns the dm gt report template where reportCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param reportCode the report code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm gt report template, or <code>null</code> if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate fetchByReportCode(int reportCode,
		boolean retrieveFromCache) throws SystemException {
		DmGtReportTemplate dmGtReportTemplate = null;
		if (dmGtReportTemplate == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMGTREPORTTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_REPORTCODE_REPORTCODE_2);

			query.append(DmGtReportTemplateModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmGtReportTemplate.class).build();

				

				builder.appendNamedParameterMap("reportCode", reportCode);

				dmGtReportTemplate = (DmGtReportTemplate) queryFactory
.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmGtReportTemplate;
	}

	/**
	 * Returns all the dm gt report templates where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @return the matching dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findByreportType(int reportType)
		throws SystemException {
		return findByreportType(reportType, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt report templates where reportType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param reportType the report type
	 * @param start the lower bound of the range of dm gt report templates
	 * @param end the upper bound of the range of dm gt report templates (not inclusive)
	 * @return the range of matching dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findByreportType(int reportType, int start,
		int end) throws SystemException {
		return findByreportType(reportType, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt report templates where reportType = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param reportType the report type
	 * @param start the lower bound of the range of dm gt report templates
	 * @param end the upper bound of the range of dm gt report templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findByreportType(int reportType, int start,
		int end, OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportTemplate> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMGTREPORTTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_REPORTTYPE_REPORTTYPE_2);

			if (orderByComparator != null) {
				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);
			}

			else {
				query.append(DmGtReportTemplateModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				builder.appendNamedParameterMap("reportType", reportType);

				list = (List<DmGtReportTemplate>)queryFactory.getResultList(builder);
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
	 * Returns the first dm gt report template in the ordered set where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt report template
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate findByreportType_First(int reportType,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = fetchByreportType_First(reportType,
				orderByComparator);

		if (dmGtReportTemplate != null) {
			return dmGtReportTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reportType=");
		msg.append(reportType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtReportTemplateException(msg.toString());
	}

	/**
	 * Returns the first dm gt report template in the ordered set where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm gt report template, or <code>null</code> if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate fetchByreportType_First(int reportType,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportTemplate> list = findByreportType(reportType, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm gt report template in the ordered set where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt report template
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate findByreportType_Last(int reportType,
		OrderByComparator orderByComparator)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = fetchByreportType_Last(reportType,
				orderByComparator);

		if (dmGtReportTemplate != null) {
			return dmGtReportTemplate;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("reportType=");
		msg.append(reportType);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmGtReportTemplateException(msg.toString());
	}

	/**
	 * Returns the last dm gt report template in the ordered set where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm gt report template, or <code>null</code> if a matching dm gt report template could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate fetchByreportType_Last(int reportType,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByreportType(reportType);

		List<DmGtReportTemplate> list = findByreportType(reportType, count - 1,
				count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm gt report templates before and after the current dm gt report template in the ordered set where reportType = &#63;.
	 *
	 * @param id the primary key of the current dm gt report template
	 * @param reportType the report type
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm gt report template
	 * @throws vn.gt.dao.danhmucgt.NoSuchDmGtReportTemplateException if a dm gt report template with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate[] findByreportType_PrevAndNext(long id,
		int reportType, OrderByComparator orderByComparator)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = findByPrimaryKey(id);

		

		try {
			

			DmGtReportTemplate[] array = new DmGtReportTemplate[3];

			array[0] = getByreportType_PrevAndNext(dmGtReportTemplate,
					reportType, orderByComparator, true);

			array[1] = dmGtReportTemplate;

			array[2] = getByreportType_PrevAndNext(dmGtReportTemplate,
					reportType, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmGtReportTemplate getByreportType_PrevAndNext(
		DmGtReportTemplate dmGtReportTemplate, int reportType,
		OrderByComparator orderByComparator, boolean previous) {
		StringBundler query = null;

		if (orderByComparator != null) {
			query = new StringBundler(6 +
					(orderByComparator.getOrderByFields().length * 6));
		}
		else {
			query = new StringBundler(3);
		}

		query.append(_SQL_SELECT_DMGTREPORTTEMPLATE_WHERE);

		query.append(_FINDER_COLUMN_REPORTTYPE_REPORTTYPE_2);

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
			query.append(DmGtReportTemplateModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		builder.appendNamedParameterMap("reportType", reportType);

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmGtReportTemplate);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmGtReportTemplate> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns all the dm gt report templates.
	 *
	 * @return the dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm gt report templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt report templates
	 * @param end the upper bound of the range of dm gt report templates (not inclusive)
	 * @return the range of dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm gt report templates.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm gt report templates
	 * @param end the upper bound of the range of dm gt report templates (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmGtReportTemplate> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmGtReportTemplate> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMGTREPORTTEMPLATE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMGTREPORTTEMPLATE.concat(DmGtReportTemplateModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmGtReportTemplate>) queryFactory.getResultList(builder);
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
	 * Removes the dm gt report template where reportCode = &#63; from the database.
	 *
	 * @param reportCode the report code
	 * @return the dm gt report template that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmGtReportTemplate removeByReportCode(int reportCode)
		throws NoSuchDmGtReportTemplateException, SystemException {
		DmGtReportTemplate dmGtReportTemplate = findByReportCode(reportCode);

		repository.delete(dmGtReportTemplate);
			return dmGtReportTemplate;
	}

	/**
	 * Removes all the dm gt report templates where reportType = &#63; from the database.
	 *
	 * @param reportType the report type
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByreportType(int reportType) throws SystemException {
		for (DmGtReportTemplate dmGtReportTemplate : findByreportType(
				reportType)) {
			repository.delete(dmGtReportTemplate);
		}
	}

	/**
	 * Removes all the dm gt report templates from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmGtReportTemplate dmGtReportTemplate : findAll()) {
			repository.delete(dmGtReportTemplate);
		}
	}

	/**
	 * Returns the number of dm gt report templates where reportCode = &#63;.
	 *
	 * @param reportCode the report code
	 * @return the number of matching dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByReportCode(int reportCode) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTREPORTTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_REPORTCODE_REPORTCODE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("reportCode", reportCode);

				count = (Long) queryFactory
.getSingleResult(builder);
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
	 * Returns the number of dm gt report templates where reportType = &#63;.
	 *
	 * @param reportType the report type
	 * @return the number of matching dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countByreportType(int reportType) throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMGTREPORTTEMPLATE_WHERE);

			query.append(_FINDER_COLUMN_REPORTTYPE_REPORTTYPE_2);

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

				builder.appendNamedParameterMap("reportType", reportType);

				count = (Long) queryFactory
.getSingleResult(builder);
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
	 * Returns the number of dm gt report templates.
	 *
	 * @return the number of dm gt report templates
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMGTREPORTTEMPLATE).build();

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
	 * Initializes the dm gt report template persistence.
	 */
	private static final String _SQL_SELECT_DMGTREPORTTEMPLATE = "SELECT dmGtReportTemplate FROM DmGtReportTemplate dmGtReportTemplate";
	private static final String _SQL_SELECT_DMGTREPORTTEMPLATE_WHERE = "SELECT dmGtReportTemplate FROM DmGtReportTemplate dmGtReportTemplate WHERE ";
	private static final String _SQL_COUNT_DMGTREPORTTEMPLATE = "SELECT COUNT(dmGtReportTemplate) FROM DmGtReportTemplate dmGtReportTemplate";
	private static final String _SQL_COUNT_DMGTREPORTTEMPLATE_WHERE = "SELECT COUNT(dmGtReportTemplate) FROM DmGtReportTemplate dmGtReportTemplate WHERE ";
	private static final String _FINDER_COLUMN_REPORTCODE_REPORTCODE_2 = "dmGtReportTemplate.reportCode =:reportCode";
	private static final String _FINDER_COLUMN_REPORTTYPE_REPORTTYPE_2 = "dmGtReportTemplate.reportType =:reportType";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmGtReportTemplate.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmGtReportTemplate exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmGtReportTemplate exists with the key {";
	

	
}
