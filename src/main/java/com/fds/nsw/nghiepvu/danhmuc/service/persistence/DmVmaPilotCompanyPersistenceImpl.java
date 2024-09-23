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
import com.fds.nsw.nghiepvu.model.DmVmaPilotCompany;
import com.fds.nsw.nghiepvu.service.exception.*;
import com.fds.nsw.nghiepvu.repository.DmVmaPilotCompanyRepository;
import com.fds.nsw.nghiepvu.modelImpl.DmVmaPilotCompanyModelImpl;


import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DmVmaPilotCompanyPersistenceImpl extends BasePersistence {
	@Autowired
	DmVmaPilotCompanyRepository repository;
	@Autowired
	@Qualifier("blQueryFactory")
	QueryFactory<DmVmaPilotCompany> queryFactory;
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link DmVmaPilotCompanyUtil} to access the dm vma pilot company persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public DmVmaPilotCompany create(long id) {
		DmVmaPilotCompany dmVmaPilotCompany = new DmVmaPilotCompany();

		
		//dmVmaPilotCompany.setPrimaryKey(id);

		return dmVmaPilotCompany;
	}

	/**
	 * Removes the dm vma pilot company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param Id the primary key of the dm vma pilot company
	 * @return the dm vma pilot company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany remove(long Id)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		return remove(Long.valueOf(Id));
	}

	/**
	 * Removes the dm vma pilot company with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the dm vma pilot company
	 * @return the dm vma pilot company that was removed
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilotCompany remove(Serializable primaryKey)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		

		try {
			

			DmVmaPilotCompany dmVmaPilotCompany = findByPrimaryKey(primaryKey);

			if (dmVmaPilotCompany == null) {
				if (log.isWarnEnabled()) {
					log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchDmVmaPilotCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			repository.delete(dmVmaPilotCompany);
			return dmVmaPilotCompany;
		}
		catch (NoSuchDmVmaPilotCompanyException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	
	public DmVmaPilotCompany remove(DmVmaPilotCompany DmVmaPilotCompany) throws SystemException {
	removeImpl(DmVmaPilotCompany);	return DmVmaPilotCompany;
}

protected DmVmaPilotCompany removeImpl

(DmVmaPilotCompany dmVmaPilotCompany)
		throws SystemException {
		try {
			repository.delete(dmVmaPilotCompany);
		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilotCompany;
	}

	
	public DmVmaPilotCompany updateImpl(
		DmVmaPilotCompany dmVmaPilotCompany,
		boolean merge) throws SystemException {
		try {

			repository.saveAndFlush(dmVmaPilotCompany);

		} catch (Exception e) {
			throw processException(e);
		} finally {
			// TODO update cache
		}

		return dmVmaPilotCompany;
	}

	
	public DmVmaPilotCompany findByPrimaryKey(Serializable primaryKey)
		throws NoSuchModelException, SystemException {
		return findByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot company with the primary key or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException} if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot company
	 * @return the dm vma pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany findByPrimaryKey(long Id)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = fetchByPrimaryKey(Id);

		if (dmVmaPilotCompany == null) {
			if (log.isWarnEnabled()) {
				log.warn(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + Id);
			}

			throw new NoSuchDmVmaPilotCompanyException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				Id);
		}

		return dmVmaPilotCompany;
	}

	/**
	 * Returns the dm vma pilot company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the dm vma pilot company
	 * @return the dm vma pilot company, or <code>null</code> if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	
	public DmVmaPilotCompany fetchByPrimaryKey(Serializable primaryKey)
		throws SystemException {
		return fetchByPrimaryKey(((Long)primaryKey).longValue());
	}

	/**
	 * Returns the dm vma pilot company with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param Id the primary key of the dm vma pilot company
	 * @return the dm vma pilot company, or <code>null</code> if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany fetchByPrimaryKey(long Id)
		throws SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = null;

		

		if (dmVmaPilotCompany == null) {
			

			boolean hasException = false;

			try {
				

				Optional<DmVmaPilotCompany> optional = repository.findById(Id);
				if (optional.isPresent()) {
					dmVmaPilotCompany = optional.get();
				}
			}
			catch (Exception e) {
				hasException = true;

				throw processException(e);
			}
			finally {
				

				
			}
		}

		return dmVmaPilotCompany;
	}

	/**
	 * Returns all the dm vma pilot companies where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the matching dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		return findByF_maritimeCode(MaritimeCode, QueryUtil.ALL_POS,
			QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilot companies where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma pilot companies
	 * @param end the upper bound of the range of dm vma pilot companies (not inclusive)
	 * @return the range of matching dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findByF_maritimeCode(String MaritimeCode,
		int start, int end) throws SystemException {
		return findByF_maritimeCode(MaritimeCode, start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilot companies where MaritimeCode = &#63;.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param MaritimeCode the maritime code
	 * @param start the lower bound of the range of dm vma pilot companies
	 * @param end the upper bound of the range of dm vma pilot companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findByF_maritimeCode(String MaritimeCode,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		List<DmVmaPilotCompany> list = null;
		if (list == null) {
			StringBundler query = null;

			if (orderByComparator != null) {
				query = new StringBundler(3 +
						(orderByComparator.getOrderByFields().length * 3));
			}
			else {
				query = new StringBundler(3);
			}

			query.append(_SQL_SELECT_DMVMAPILOTCOMPANY_WHERE);

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
				query.append(DmVmaPilotCompanyModelImpl.ORDER_BY_JPQL);
			}

			String sql = query.toString();

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				

				if (MaritimeCode != null) {
					builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
				}

				list = (List<DmVmaPilotCompany>)queryFactory.getResultList(builder);
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
	 * Returns the first dm vma pilot company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany findByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = fetchByF_maritimeCode_First(MaritimeCode,
				orderByComparator);

		if (dmVmaPilotCompany != null) {
			return dmVmaPilotCompany;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotCompanyException(msg.toString());
	}

	/**
	 * Returns the first dm vma pilot company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the first matching dm vma pilot company, or <code>null</code> if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany fetchByF_maritimeCode_First(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilotCompany> list = findByF_maritimeCode(MaritimeCode, 0, 1,
				orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the last dm vma pilot company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany findByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = fetchByF_maritimeCode_Last(MaritimeCode,
				orderByComparator);

		if (dmVmaPilotCompany != null) {
			return dmVmaPilotCompany;
		}

		StringBundler msg = new StringBundler(4);

		msg.append(_NO_SUCH_ENTITY_WITH_KEY);

		msg.append("MaritimeCode=");
		msg.append(MaritimeCode);

		msg.append(StringPool.CLOSE_CURLY_BRACE);

		throw new NoSuchDmVmaPilotCompanyException(msg.toString());
	}

	/**
	 * Returns the last dm vma pilot company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the last matching dm vma pilot company, or <code>null</code> if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany fetchByF_maritimeCode_Last(String MaritimeCode,
		OrderByComparator orderByComparator) throws SystemException {
		int count = countByF_maritimeCode(MaritimeCode);

		List<DmVmaPilotCompany> list = findByF_maritimeCode(MaritimeCode,
				count - 1, count, orderByComparator);

		if (!list.isEmpty()) {
			return list.get(0);
		}

		return null;
	}

	/**
	 * Returns the dm vma pilot companies before and after the current dm vma pilot company in the ordered set where MaritimeCode = &#63;.
	 *
	 * @param Id the primary key of the current dm vma pilot company
	 * @param MaritimeCode the maritime code
	 * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	 * @return the previous, current, and next dm vma pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a dm vma pilot company with the primary key could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany[] findByF_maritimeCode_PrevAndNext(long Id,
		String MaritimeCode, OrderByComparator orderByComparator)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = findByPrimaryKey(Id);

		

		try {
			

			DmVmaPilotCompany[] array = new DmVmaPilotCompany[3];

			array[0] = getByF_maritimeCode_PrevAndNext(
					dmVmaPilotCompany, MaritimeCode, orderByComparator, true);

			array[1] = dmVmaPilotCompany;

			array[2] = getByF_maritimeCode_PrevAndNext(
					dmVmaPilotCompany, MaritimeCode, orderByComparator, false);

			return array;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			
		}
	}

	protected DmVmaPilotCompany getByF_maritimeCode_PrevAndNext(
		 DmVmaPilotCompany dmVmaPilotCompany,
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

		query.append(_SQL_SELECT_DMVMAPILOTCOMPANY_WHERE);

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
			query.append(DmVmaPilotCompanyModelImpl.ORDER_BY_JPQL);
		}

		String sql = query.toString();

		QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(0).maxResults(2).build();

		

		if (MaritimeCode != null) {
			builder.appendNamedParameterMap("MaritimeCode", MaritimeCode);
		}

		if (orderByComparator != null) {
			Object[] values = orderByComparator.getOrderByConditionValues(dmVmaPilotCompany);

						/*
			for (Object value : values) {
				builder.appendNamedParameterMap("value", value);
			}
			*/
		}

		List<DmVmaPilotCompany> list = queryFactory.getResultList(builder);

		if (list.size() == 2) {
			return list.get(1);
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the dm vma pilot company where PilotCompanyCode = &#63; or throws a {@link vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException} if it could not be found.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @return the matching dm vma pilot company
	 * @throws vn.gt.dao.danhmuc.NoSuchDmVmaPilotCompanyException if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany findByF_pilotCompanyCode(String PilotCompanyCode)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = fetchByF_pilotCompanyCode(PilotCompanyCode);

		if (dmVmaPilotCompany == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("PilotCompanyCode=");
			msg.append(PilotCompanyCode);

			msg.append(StringPool.CLOSE_CURLY_BRACE);

			if (log.isWarnEnabled()) {
				log.warn(msg.toString());
			}

			throw new NoSuchDmVmaPilotCompanyException(msg.toString());
		}

		return dmVmaPilotCompany;
	}

	/**
	 * Returns the dm vma pilot company where PilotCompanyCode = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @return the matching dm vma pilot company, or <code>null</code> if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany fetchByF_pilotCompanyCode(String PilotCompanyCode)
		throws SystemException {
		return fetchByF_pilotCompanyCode(PilotCompanyCode, true);
	}

	/**
	 * Returns the dm vma pilot company where PilotCompanyCode = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @param retrieveFromCache whether to use the finder cache
	 * @return the matching dm vma pilot company, or <code>null</code> if a matching dm vma pilot company could not be found
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany fetchByF_pilotCompanyCode(
		String PilotCompanyCode, boolean retrieveFromCache)
		throws SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = null;
		if (dmVmaPilotCompany == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_DMVMAPILOTCOMPANY_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2);
				}
			}

			query.append(DmVmaPilotCompanyModelImpl.ORDER_BY_JPQL);

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(DmVmaPilotCompany.class).build();

				

				if (PilotCompanyCode != null) {
					builder.appendNamedParameterMap("PilotCompanyCode", PilotCompanyCode);
				}

				dmVmaPilotCompany = (DmVmaPilotCompany) queryFactory.getSingleResult(builder);
			}
			catch (Exception e) {
				throw processException(e);
			}
			finally {
				

				
			}
		}
		return dmVmaPilotCompany;
	}

	/**
	 * Returns all the dm vma pilot companies.
	 *
	 * @return the dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findAll() throws SystemException {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the dm vma pilot companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilot companies
	 * @param end the upper bound of the range of dm vma pilot companies (not inclusive)
	 * @return the range of dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findAll(int start, int end)
		throws SystemException {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the dm vma pilot companies.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to  will return the full result set.
	 * </p>
	 *
	 * @param start the lower bound of the range of dm vma pilot companies
	 * @param end the upper bound of the range of dm vma pilot companies (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public List<DmVmaPilotCompany> findAll(int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		List<DmVmaPilotCompany> list = null;
		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 3));

				query.append(_SQL_SELECT_DMVMAPILOTCOMPANY);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_DMVMAPILOTCOMPANY.concat(DmVmaPilotCompanyModelImpl.ORDER_BY_JPQL);
			}

			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).firstResult(start).maxResults(end-start).build();

				list = (List<DmVmaPilotCompany>) queryFactory.getResultList(builder);
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
	 * Removes all the dm vma pilot companies where MaritimeCode = &#63; from the database.
	 *
	 * @param MaritimeCode the maritime code
	 * @throws SystemException if a system exception occurred
	 */
	public void removeByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		for (DmVmaPilotCompany dmVmaPilotCompany : findByF_maritimeCode(
				MaritimeCode)) {
			repository.delete(dmVmaPilotCompany);
		}
	}

	/**
	 * Removes the dm vma pilot company where PilotCompanyCode = &#63; from the database.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @return the dm vma pilot company that was removed
	 * @throws SystemException if a system exception occurred
	 */
	public DmVmaPilotCompany removeByF_pilotCompanyCode(String PilotCompanyCode)
		throws NoSuchDmVmaPilotCompanyException, SystemException {
		DmVmaPilotCompany dmVmaPilotCompany = findByF_pilotCompanyCode(PilotCompanyCode);

		repository.delete(dmVmaPilotCompany);
			return dmVmaPilotCompany;
	}

	/**
	 * Removes all the dm vma pilot companies from the database.
	 *
	 * @throws SystemException if a system exception occurred
	 */
	public void removeAll() throws SystemException {
		for (DmVmaPilotCompany dmVmaPilotCompany : findAll()) {
			repository.delete(dmVmaPilotCompany);
		}
	}

	/**
	 * Returns the number of dm vma pilot companies where MaritimeCode = &#63;.
	 *
	 * @param MaritimeCode the maritime code
	 * @return the number of matching dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_maritimeCode(String MaritimeCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOTCOMPANY_WHERE);

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
	 * Returns the number of dm vma pilot companies where PilotCompanyCode = &#63;.
	 *
	 * @param PilotCompanyCode the pilot company code
	 * @return the number of matching dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countByF_pilotCompanyCode(String PilotCompanyCode)
		throws SystemException {
		Long count = null;
		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_DMVMAPILOTCOMPANY_WHERE);

			if (PilotCompanyCode == null) {
				query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1);
			}
			else {
				if (PilotCompanyCode.equals(StringPool.BLANK)) {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3);
				}
				else {
					query.append(_FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2);
				}
			}

			String sql = query.toString();

			

			try {


				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(sql).entityClass(Long.class).build();

				

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
	 * Returns the number of dm vma pilot companies.
	 *
	 * @return the number of dm vma pilot companies
	 * @throws SystemException if a system exception occurred
	 */
	public int countAll() throws SystemException {
		Long count = null;

		if (count == null) {
			

			try {
				

				QueryBuilder builder = QueryBuilder.builder().sqlQuery(false).queryString(_SQL_COUNT_DMVMAPILOTCOMPANY).build();

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
	 * Initializes the dm vma pilot company persistence.
	 */
	private static final String _SQL_SELECT_DMVMAPILOTCOMPANY = "SELECT dmVmaPilotCompany FROM DmVmaPilotCompany dmVmaPilotCompany";
	private static final String _SQL_SELECT_DMVMAPILOTCOMPANY_WHERE = "SELECT dmVmaPilotCompany FROM DmVmaPilotCompany dmVmaPilotCompany WHERE ";
	private static final String _SQL_COUNT_DMVMAPILOTCOMPANY = "SELECT COUNT(dmVmaPilotCompany) FROM DmVmaPilotCompany dmVmaPilotCompany";
	private static final String _SQL_COUNT_DMVMAPILOTCOMPANY_WHERE = "SELECT COUNT(dmVmaPilotCompany) FROM DmVmaPilotCompany dmVmaPilotCompany WHERE ";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_1 = "dmVmaPilotCompany.MaritimeCode IS NULL";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_2 = "dmVmaPilotCompany.MaritimeCode =:MaritimeCode";
	private static final String _FINDER_COLUMN_F_MARITIMECODE_MARITIMECODE_3 = "(dmVmaPilotCompany.MaritimeCode IS NULL OR dmVmaPilotCompany.MaritimeCode =:MaritimeCode)";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_1 =
		"dmVmaPilotCompany.PilotCompanyCode IS NULL";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_2 =
		"dmVmaPilotCompany.PilotCompanyCode =:PilotCompanyCode";
	private static final String _FINDER_COLUMN_F_PILOTCOMPANYCODE_PILOTCOMPANYCODE_3 =
		"(dmVmaPilotCompany.PilotCompanyCode IS NULL OR dmVmaPilotCompany.PilotCompanyCode =:PilotCompanyCode)";
	private static final String _ORDER_BY_ENTITY_ALIAS = "dmVmaPilotCompany.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No DmVmaPilotCompany exists with the primary key ";
	private static final String _NO_SUCH_ENTITY_WITH_KEY = "No DmVmaPilotCompany exists with the key {";
	

	
}
