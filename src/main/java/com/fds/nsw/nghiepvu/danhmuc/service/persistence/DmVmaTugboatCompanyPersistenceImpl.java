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
import com.fds.nsw.nghiepvu.model.DmVmaTugboatCompany;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaTugboatCompanyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaTugboatCompanyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaTugboatCompanyPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaTugboatCompanyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaTugboatCompany> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaTugboatCompanyUtil} to access the dm vma tugboat company persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaTugboatCompany create(long id) {
		DmVmaTugboatCompany dmVmaTugboatCompany = new DmVmaTugboatCompany();

		
		//dmVmaTugboatCompany.setPrimaryKey(id);

		return dmVmaTugboatCompany;
	}

	/**
	 * Removes the dm vma tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma tugboat company
	 * @return the dm vma tugboat company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany remove(long Id)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma tugboat company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma tugboat company
	 * @return the dm vma tugboat company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaTugboatCompany remove(Serializable primaryKey)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		

		try {
			

			DmVmaTugboatCompany dmVmaTugboatCompany = findByPrimaryKey(primaryKey);

			if (dmVmaTugboatCompany == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaTugboatCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaTugboatCompany);
			return dmVmaTugboatCompany;
		}
		catch (NoSuchDmVmaTugboatCompanyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaTugboatCompany remove(DmVmaTugboatCompany DmVmaTugboatCompany) throws SystemException {
	removeImpl(DmVmaTugboatCompany);	return DmVmaTugboatCompany;
}

protected DmVmaTugboatCompany removeImpl

(
		DmVmaTugboatCompany dmVmaTugboatCompany) throws SystemException {
		try {
			repository.delete(dmVmaTugboatCompany);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaTugboatCompany;
	}

	
	public DmVmaTugboatCompany updateImpl(
		DmVmaTugboatCompany dmVmaTugboatCompany,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmVmaTugboatCompany);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaTugboatCompany;
	}

	
	public DmVmaTugboatCompany findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma tugboat company with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma tugboat company
	 * @return the dm vma tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany findByPrimaryKey(long Id)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = fetchByPrimaryKey(Id);

		if (dmVmaTugboatCompany == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaTugboatCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaTugboatCompany;
	}

	/**
	 * Returns the dm vma tugboat company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma tugboat company
	 * @return the dm vma tugboat company, or <code>null</code> if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaTugboatCompany fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma tugboat company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma tugboat company
	 * @return the dm vma tugboat company, or <code>null</code> if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany fetchByPrimaryKey(long Id)
		throws SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = null;

		

		if (dmVmaTugboatCompany == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaTugboatCompany> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaTugboatCompany = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaTugboatCompany;
	}

	/**
	 * Returns all the dm vma tugboat companies where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma tugboat companies where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma tugboat companies
	 * @param end the upper bound of the range of dm vma tugboat companies (not inclusive)
	 * @return the range of matching dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma tugboat companies where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma tugboat companies
	 * @param end the upper bound of the range of dm vma tugboat companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaTugboatCompany> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMATUGBOATCOMPANY_WHERE);

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
				query.append(DmVmaTugboatCompanyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaTugboatCompany>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma tugboat company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaTugboatCompany != null) {
			return dmVmaTugboatCompany;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatCompanyException(msg.toString());
	}

	/**
	 * Returns the first dm vma tugboat company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma tugboat company, or <code>null</code> if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany fetchByF_maritimeCode_First(
		String MaritimeCode, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaTugboatCompany> list = findByF_maritimeCode(MaritimeCode, 0,
				1, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma tugboat company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaTugboatCompany != null) {
			return dmVmaTugboatCompany;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaTugboatCompanyException(msg.toString());
	}

	/**
	 * Returns the last dm vma tugboat company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma tugboat company, or <code>null</code> if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaTugboatCompany> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma tugboat companies before and after the current dm vma tugboat company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma tugboat company
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a dm vma tugboat company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = findByPrimaryKey(Id);

		

		try {
			

			DmVmaTugboatCompany[] array = new DmVmaTugboatCompany[3];

			array[0] = getByF_maritimeCode_PrevAndNext(
					dmVmaTugboatCompany, MaritimeCode, orderByComparator, true);

			array[1] = dmVmaTugboatCompany;

			array[2] = getByF_maritimeCode_PrevAndNext(
					dmVmaTugboatCompany, MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaTugboatCompany getByF_maritimeCode_PrevAndNext(
		 DmVmaTugboatCompany dmVmaTugboatCompany,
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

		query.append(_SQL_SELECT_DMVMATUGBOATCOMPANY_WHERE);

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
			query.append(DmVmaTugboatCompanyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaTugboatCompany);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaTugboatCompany> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma tugboat company where TugboatCompanyCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException} if it could not be found.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the matching dm vma tugboat company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaTugboatCompanyException if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany findByF_tugboatCompanyCode(
		String TugboatCompanyCode)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = fetchByF_tugboatCompanyCode(TugboatCompanyCode);

		if (dmVmaTugboatCompany == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("TugboatCompanyCode=");
			msg.append(TugboatCompanyCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaTugboatCompanyException(msg.toString());
		}

		return dmVmaTugboatCompany;
	}

	/**
	 * Returns the dm vma tugboat company where TugboatCompanyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the matching dm vma tugboat company, or <code>null</code> if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany fetchByF_tugboatCompanyCode(
		String TugboatCompanyCode) throws SystemException {
		return fetchByF_tugboatCompanyCode(TugboatCompanyCode, true);
	}

	/**
	 * Returns the dm vma tugboat company where TugboatCompanyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma tugboat company, or <code>null</code> if a matching dm vma tugboat company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany fetchByF_tugboatCompanyCode(
		String TugboatCompanyCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = null;
		if (dmVmaTugboatCompany == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMATUGBOATCOMPANY_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			query.append(DmVmaTugboatCompanyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaTugboatCompany.class).build();

				

				if (TugboatCompanyCode != null) {
					builder.appendNamedParameterMap("TugboatCompanyCode", TugboatCompanyCode);
				}

				dmVmaTugboatCompany = (DmVmaTugboatCompany) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaTugboatCompany;
	}

	/**
	 * Returns all the dm vma tugboat companies.
	 *
	 * @return the dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma tugboat companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma tugboat companies
	 * @param end the upper bound of the range of dm vma tugboat companies (not inclusive)
	 * @return the range of dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma tugboat companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma tugboat companies
	 * @param end the upper bound of the range of dm vma tugboat companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaTugboatCompany> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaTugboatCompany> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMATUGBOATCOMPANY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMATUGBOATCOMPANY.concat(DmVmaTugboatCompanyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaTugboatCompany>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma tugboat companies where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaTugboatCompany dmVmaTugboatCompany : findByF_maritimeCode(
				MaritimeCode)) {
			repository.delete(dmVmaTugboatCompany);
		}
	}

	/**
	 * Removes the dm vma tugboat company where TugboatCompanyCode = &#63; from the database.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the dm vma tugboat company that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaTugboatCompany removeByF_tugboatCompanyCode(
		String TugboatCompanyCode)
		throws NoSuchDmVmaTugboatCompanyException, SystemException {
		DmVmaTugboatCompany dmVmaTugboatCompany = findByF_tugboatCompanyCode(TugboatCompanyCode);

		repository.delete(dmVmaTugboatCompany);
			return dmVmaTugboatCompany;
	}

	/**
	 * Removes all the dm vma tugboat companies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaTugboatCompany dmVmaTugboatCompany : findAll()) {
			repository.delete(dmVmaTugboatCompany);
		}
	}

	/**
	 * Returns the number of dm vma tugboat companies where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMATUGBOATCOMPANY_WHERE);

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
	 * Returns the number of dm vma tugboat companies where TugboatCompanyCode = &#63;.
	 *
	 * @param TugboatCompanyCode the tugboat company code
	 * @return the number of matching dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_tugboatCompanyCode(String TugboatCompanyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMATUGBOATCOMPANY_WHERE);

			if (TugboatCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1);
			}
			else {
				if (TugboatCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

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
	 * Returns the number of dm vma tugboat companies.
	 *
	 * @return the number of dm vma tugboat companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMATUGBOATCOMPANY).build();

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
	 * Initializes the dm vma tugboat company persistence.
	 */
	private static final String _SQL_SELECT_DMVMATUGBOATCOMPANY = "SELECT dmVmaTugboatCompany FROM DmVmaTugboatCompany dmVmaTugboatCompany";
	private static final String _SQL_SELECT_DMVMATUGBOATCOMPANY_WHERE = "SELECT dmVmaTugboatCompany FROM DmVmaTugboatCompany dmVmaTugboatCompany WHERE ";
	private static final String _SQL_COUNT_DMVMATUGBOATCOMPANY = "SELECT COUNT(dmVmaTugboatCompany) FROM DmVmaTugboatCompany dmVmaTugboatCompany";
	private static final String _SQL_COUNT_DMVMATUGBOATCOMPANY_WHERE = "SELECT COUNT(dmVmaTugboatCompany) FROM DmVmaTugboatCompany dmVmaTugboatCompany WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaTugboatCompany.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaTugboatCompany.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaTugboatCompany.MaritimeCode IS NULL OR dmVmaTugboatCompany.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_1 =
		"dmVmaTugboatCompany.TugboatCompanyCode IS NULL";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_2 =
		"dmVmaTugboatCompany.TugboatCompanyCode =:TugboatCompanyCode";
	private static final String _FINDER_COLUMN_F_TUGBOATCOMPANYCODE_TUGBOATCOMPANYCODE_3 =
		"(dmVmaTugboatCompany.TugboatCompanyCode IS NULL OR dmVmaTugboatCompany.TugboatCompanyCode =:TugboatCompanyCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaTugboatCompany.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaTugboatCompany exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaTugboatCompany exists with the key {";
	

	
}
